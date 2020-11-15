import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ResponseHandlerTest {

    @Test
    public void responseHandlerTest() {
        try {
            ResponseHandler responseHandler = new ResponseHandler();

            responseHandler.setMessage("{\"type\":\"ticker\",\"sequence\":14165935228,\"product_id\":\"BTC-USD\",\"price\":\"9584.18\",\"open_24h\":\"9740.00000000\",\"volume_24h\":\"19882.16807137\",\"low_24h\":\"9436.97000000\",\"high_24h\":\"9957.25000000\",\"volume_30d\":\"654772.33925563\",\"best_bid\":\"9584.18\","
                    + "\"best_ask\":\"9584.19\",\"side\":\"sell\",\"time\":\"2020-05-18T12:32:02.331872Z\",\"trade_id\":92323410,\"last_size\":\"0.02252\"}");

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
    }
}
