import org.json.JSONObject;

public class TickerMessageParser implements MessageParser {
    public String parseMessage(String message) {
        JSONObject json = new JSONObject(message);

        if (json.get("type").equals("ticker")) {
            JSONObject resultJson = new JSONObject();
            resultJson.put("instrument", json.get("product_id").toString().replace("-", ""));
            resultJson.put("bid", json.get("best_bid"));
            resultJson.put("ask", json.get("best_ask"));
            resultJson.put("last", json.get("price"));
            resultJson.put("time", json.get("time").toString().substring(11));
            return resultJson.toString();
        }
        return message;
    }
}
