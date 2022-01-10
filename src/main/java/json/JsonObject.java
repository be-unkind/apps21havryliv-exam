package json;

import java.util.HashMap;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    private final HashMap<String, Json> info = new HashMap<>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair jsonPair: jsonPairs){
            this.info.put(jsonPair.key, jsonPair.value);
        }
    }

    @Override
    public String toJson() {
        String result = "{";
        for (String key: this.info.keySet()){
            result += key + ": " + this.info.get(key).toJson() + ", ";
        }
        if (result == "{"){
            return "{}";
        }
        return result.substring(0, result.length() - 2) + "}";
    }

    public void add(JsonPair jsonPair) {
        this.info.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return this.info.get(name);
    }

    public boolean contains(String name){
        return this.info.containsKey(name);
    }

    public JsonObject projection(String... names) {
        JsonObject jsonObject = new JsonObject();

        for (String name: names){
            if (this.info.containsKey(name)){
                jsonObject.add(new JsonPair(name, this.info.get(name)));
            }
        }
        return jsonObject;
    }
}
