import org.junit.Assert;
import org.junit.Test;

public class TickerMessageParserTest {

    @Test
    public void tickerMessageParserTest() {
        MessageParser messageParser = new TickerMessageParser();

        String messageToParse = "{\"type\":\"ticker\",\"sequence\":14165935228,\"product_id\":\"BTC-USD\",\"price\":\"9584.18\",\"open_24h\":\"9740.00000000\",\"volume_24h\":\"19882.16807137\",\"low_24h\":\"9436.97000000\",\"high_24h\":\"9957.25000000\",\"volume_30d\":\"654772.33925563\",\"best_bid\":\"9584.18\","
               + "\"best_ask\":\"9584.19\",\"side\":\"sell\",\"time\":\"2020-05-18T12:32:02.331872Z\",\"trade_id\":92323410,\"last_size\":\"0.02252\"}";

        String message = messageParser.parseMessage(messageToParse);

        System.out.println(message);

        String wantedResult = "{\"last\":\"9584.18\",\"ask\":\"9584.19\",\"instrument\":\"BTCUSD\",\"time\":\"12:32:02.331872Z\",\"bid\":\"9584.18\"}";

        Assert.assertEquals(wantedResult, message);
    }
}
