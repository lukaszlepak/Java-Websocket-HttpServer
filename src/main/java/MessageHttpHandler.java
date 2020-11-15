import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class MessageHttpHandler implements HttpHandler {

    Map<String, JSONObject> currencies = new HashMap<>();

    public void setRate(String message) {

        JSONObject json = new JSONObject(message);

        if (json.opt("type") != null) {
            if (json.get("type").equals("ticker")) {
                JSONObject resultJson = new JSONObject();
                resultJson.put("instrument", json.get("product_id").toString().replace("-", ""));
                resultJson.put("bid", json.get("best_bid"));
                resultJson.put("ask", json.get("best_ask"));
                resultJson.put("last", json.get("price"));
                resultJson.put("time", json.get("time").toString().substring(11,19));
                currencies.put(resultJson.get("instrument").toString(), resultJson);
            }
        }
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = currencies.values().toString();
        httpExchange.getResponseHeaders().add("Content-Type", "application/json");
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
