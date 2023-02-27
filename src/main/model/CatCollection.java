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
    //EFFECTS: adds a cat of random rarity to catList
    public Cat addCat(String name) {
        int range = (int)Math.floor(Math.random() * 15 + 1);
        int rarity;
        if (range <= 5) {
            rarity = 1;
        } else if (range <= 9) {
            rarity = 2;
        } else if (range <= 12) {
            rarity = 3;
        } else if (range <= 14) {
            rarity = 4;
        } else {
            rarity = 5;
        }
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

    //REQUIRES: 1 <= rarity <= 5
    //MODIFIES: this
    //EFFECTS: adds a new cat to the collection with given name and rarity
    public void addCatWithRarity(String name, int rarity) {
        Cat cat = new Cat(name, rarity);
        catList.add(cat);
    }

    //MODIFIES: this
    //EFFECTS: adds a Cat to the collection
    public void addCatDebug(Cat cat) {
        catList.add(cat);
    }
}
