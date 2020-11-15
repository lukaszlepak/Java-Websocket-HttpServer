import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public class WsClientStarter {
    private String destinationUri;

    private String subscribeMessage;

    private ResponseHandler responseHandler;

    public WsClientStarter(String destinationUri, String subscribeMessage, ResponseHandler responseHandler) {
        this.destinationUri = destinationUri;
        this.subscribeMessage = subscribeMessage;
        this.responseHandler = responseHandler;
    }

    public void start() {
        WebSocketClient client = new WebSocketClient();
        WsClient socket = new WsClient(subscribeMessage, responseHandler);
        try
        {
            client.start();

            URI echoUri = new URI(destinationUri);
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            client.connect(socket, echoUri, request);

            socket.awaitClose(500, TimeUnit.SECONDS);
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
        finally
        {
            try
            {
                client.stop();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
