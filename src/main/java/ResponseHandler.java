import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class ResponseHandler {

    private MessageHttpHandler messageHttpHandler = new MessageHttpHandler();

    public ResponseHandler(){
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
            httpServer.createContext("/rates", messageHttpHandler);
            httpServer.setExecutor(null);
            httpServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMessage(String message) {
        messageHttpHandler.setRate(message);
    }
}
