package model;

import java.util.ArrayList;

public class CatCollection {
    private ArrayList<Cat> catList;

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: instantiates catList as a new empty ArrayList of type Cat
    public CatCollection() {
        catList = new ArrayList<>();
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: adds a cat of random rarity to catList
    public void addCat(Cat cat) {
        catList.add(cat);
        //TODO
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: searches for cat in catList and removes if found
    public void removeCat(Cat cat) {
        for (int i = 0; i < catList.size(); i++) {
            if (catList.get(i).getName() == cat.getName()) {
                catList.remove(i);
                i--;
            }
        }
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns index of cat in lisst or -1 if not in list
    public int findCat(Cat cat) {
        for (int i = 0; i < catList.size(); i++) {
            if (catList.get(i).getName() == cat.getName()) {
                return i;
            }
        }

        return -1;
    }

    public int catListSize() {
        return catList.size();
    }
}
