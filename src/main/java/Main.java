public class Main {
    public static void main(String[] args) {
        String destinationUri = "wss://ws-feed.pro.coinbase.com";
        String[] channels = {"ticker"};
        String[] products = {"BTC-USD", "BTC-EUR", "ETH-USD", "ETH-EUR"};

        String subscribeMessage = new SubscribeMessage(channels, products).getMessage();

        MessageParser messageParser = new TickerMessageParser();

        ResponseHandler responseHandler = new SystemPrintResponseHandler();

        WsClientStarter wsClient = new WsClientStarter(destinationUri, subscribeMessage, messageParser, responseHandler);
        wsClient.start();
    }
}