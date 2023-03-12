package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//represents a collection of Cats
public class CatCollection implements Writable {
    private ArrayList<Cat> catList;
    private String name;

    //MODIFIES: this
    //EFFECTS: instantiates catList as a new empty ArrayList of type Cat
    public CatCollection(String name) {
        catList = new ArrayList<>();
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: adds a cat of given rarity to catList
    public Cat addCat(String name, int rarity) {
        Cat newCat = new Cat(name, rarity);
        catList.add(newCat);
        return newCat;
    }

    //REQUIRES: j < catList.size()
    //MODIFIES: this
    //EFFECTS: searches for cat in catList and removes if found
    public void removeCat(int j) {
        catList.remove(j);
    }

    //MODIFIES: this
    //EFFECTS: adds a cat to the collection with HP at 1
    public void addDeadCat(Cat cat) {
        cat.setHP(1);
        catList.add(cat);
    }

    //REQUIRES: 0 <= j < catList.size()
    //EFFECTS: returns cat with index j in list
    public Cat findCat(int j) {
        return catList.get(j);
    }

    public int catListSize() {
        return catList.size();
    }

    public ArrayList<Cat> getCatList() {
        return catList;
    }

    //EFFECTS: returns an ArrayList containing all cats' names
    public ArrayList<String> getCatNameList() {
        ArrayList<String> names = new ArrayList<>();
        int i = 1;
        for (Cat cat : catList) {
            names.add("[" + i + "] " + cat.getName());
            i++;
        }
        return names;
    }

    //MODIFIES: this
    //EFFECTS: adds a Cat to the collection
    public void addCatDebug(Cat cat) {
        catList.add(cat);
    }

    //MODIFIES: this
    //EFFECTS: converts the cat collection data to json data
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("catList", catsToJson());
        return json;
    }

    // EFFECTS: returns things in this cat collection as a JSON array
    private JSONArray catsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Cat t : catList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns an unmodifiable list of cats in this cat collection
    public List<Cat> getCats() {
        return Collections.unmodifiableList(catList);
    }

    public String getName() {
        return name;
    }
}
