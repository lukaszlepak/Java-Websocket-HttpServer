import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.notification.RunNotifier;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiTest {

    @Test
    public void apiTest() {
        Thread thread = new Thread(() -> {
            try {
                String destinationUri = "wss://ws-feed.pro.coinbase.com";
                String[] channels = {"ticker"};
                String[] products = {"BTC-USD", "BTC-EUR", "ETH-USD", "ETH-EUR"};

                String subscribeMessage = new SubscribeMessage(channels, products).getMessage();

                ResponseHandler responseHandler = new ResponseHandler();

                WsClientStarter wsClient = new WsClientStarter(destinationUri, subscribeMessage, responseHandler);
                wsClient.start();

                HttpClient httpClient = HttpClient.newHttpClient();
                HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8001/rates")).build();

                HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

                Assert.assertTrue(httpResponse.body().contains("instrument"));
                Assert.assertTrue(httpResponse.body().contains("time"));
                Assert.assertTrue(httpResponse.body().contains("last"));
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail(e.getMessage());
            }
        });

        thread.start();

        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertTrue(thread.isAlive());
    }
}
