import org.junit.Assert;
import org.junit.Test;

public class SubscribeMessageTest {

    @Test
    public void subscribeMessageTest() {
        String[] channels = {"ticker"};
        String[] products = {"ETH-USD", "ETH-EUR"};

        String subscribeMessage = new SubscribeMessage(channels, products).getMessage();

        String wantedResult = "{\"channels\":[\"ticker\"],\"product_ids\":[\"ETH-USD\",\"ETH-EUR\"],\"type\":\"subscribe\"}";

        Assert.assertEquals(wantedResult, subscribeMessage);
    }
}
