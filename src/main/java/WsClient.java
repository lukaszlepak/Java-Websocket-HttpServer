import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class WsClient
{
    @SuppressWarnings("unused")
    private Session session;

    private String subscribeMessage;
    private MessageParser messageParser;
    private ResponseHandler responseHandler;

    private final CountDownLatch closeLatch;

    public WsClient(String subscribeMessage, MessageParser jsonParser, ResponseHandler responseHandler)
    {
        this.subscribeMessage = subscribeMessage;
        this.messageParser = jsonParser;
        this.responseHandler = responseHandler;

        this.closeLatch = new CountDownLatch(1);
    }

    public boolean awaitClose(int duration, TimeUnit unit) throws InterruptedException
    {
        return this.closeLatch.await(duration, unit);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason)
    {
        System.out.printf("Connection closed: %d - %s%n", statusCode, reason);
        this.session = null;
        this.closeLatch.countDown();
    }

    @OnWebSocketConnect
    public void onConnect(Session session)
    {
        System.out.println("Got connect: " + session);
        this.session = session;
        try
        {
            Future<Void> fut;
            fut = session.getRemote().sendStringByFuture(subscribeMessage);
            fut.get(2, TimeUnit.SECONDS);
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
    }

    @OnWebSocketMessage
    public void onMessage(String msg)
    {
        responseHandler.getMessage(messageParser.parseMessage(msg));
    }

    @OnWebSocketError
    public void onError(Throwable cause)
    {
        System.out.println("WebSocket Error: ");
        cause.printStackTrace(System.out);
    }
}