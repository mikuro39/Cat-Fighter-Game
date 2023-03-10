package model;

import java.util.ArrayList;

//represents a collection of Cats
public class CatCollection {
    private ArrayList<Cat> catList;

    //MODIFIES: this
    //EFFECTS: instantiates catList as a new empty ArrayList of type Cat
    public CatCollection() {
        catList = new ArrayList<>();
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
}
