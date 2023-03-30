package persistence;

import org.json.JSONObject;

//represents a Writable JSON interface
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
