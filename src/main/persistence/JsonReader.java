package persistence;

import model.Cat;
import model.CatCollection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//Represents a reader that reads cat collection from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads cat collection from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CatCollection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCatCollection(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses cat collection from JSON object and returns it
    private CatCollection parseCatCollection(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        CatCollection cc = new CatCollection(name);
        addCats(cc, jsonObject);
        return cc;
    }

    // MODIFIES: cc
    // EFFECTS: parses cat collection from JSON object and adds them to cat collection
    private void addCats(CatCollection cc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("catList");
        for (Object json : jsonArray) {
            JSONObject nextCat = (JSONObject) json;
            addCat(cc, nextCat);
        }
    }

    // MODIFIES: cc
    // EFFECTS: parses thingy from JSON object and adds it to cat collection
    private void addCat(CatCollection cc, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String color = jsonObject.getString("color");
        int rarity = jsonObject.getInt("rarity");
        int size = jsonObject.getInt("size");
        int power = jsonObject.getInt("power");
        int hp = jsonObject.getInt("hp");
        Cat cat = new Cat(name, color, rarity, size, power, hp);
        cc.addCatDebug(cat);
    }
}
