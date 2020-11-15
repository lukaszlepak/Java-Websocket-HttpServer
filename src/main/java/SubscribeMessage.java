import org.json.JSONArray;
import org.json.JSONObject;

public class SubscribeMessage {

    private String[] channels;

    private String[] products;

    public SubscribeMessage(String[] channels, String[] products) {
        this.channels = channels;
        this.products = products;
    }

    public String getMessage(){
        JSONObject json = new JSONObject();

        json.put("type", "subscribe");
        json.put("channels", new JSONArray(channels));
        json.put("product_ids", new JSONArray(products));

        return json.toString();
    }
}
