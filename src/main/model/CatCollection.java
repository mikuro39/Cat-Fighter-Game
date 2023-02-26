package model;

import java.util.ArrayList;
import java.util.Scanner;

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
        int rarity = (int)Math.floor(Math.random() * 5 + 1);
        //TODO: make rarity actually scale
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

    public void addDeadCat(Cat cat) {
        cat.setHP(1);
        catList.add(cat);
    }

    //REQUIRES: j < catList.size()
    //EFFECTS: returns index of cat in list or -1 if not in list
    public Cat findCat(int j) {
        //TODO: get rid of out of bounds error
        return catList.get(j);
    }

    public int catListSize() {
        return catList.size();
    }

    public ArrayList<Cat> getCatList() {
        return catList;
    }

    public ArrayList<String> getCatNameList() {
        ArrayList<String> names = new ArrayList<>();
        int i = 1;
        for (Cat cat : catList) {
            names.add("[" + i + "] " + cat.getName());
            i++;
        }
        return names;
    }
}
