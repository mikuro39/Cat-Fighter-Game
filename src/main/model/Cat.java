package model;


import org.json.JSONObject;
import persistence.Writable;

//represents a Cat with name, color, rarity, size, power, and hp(hit points)
public class Cat implements Writable {
    private String name;
    private final String color;
    private final int rarity;
    private int size;
    private int power;
    private int hp;

    //REQUIRES: rarity >= 1
    //MODIFIES: this
    //EFFECTS: instantiates a Cat with given name and rarity, random color, size 1, and power & hp depending on rarity
    public Cat(String name, int rarity) {
        this.name = name;
        String[] possibleColors = {"black", "white", "orange", "gray", "brown", "blue", "pink", "rainbow"};
        color = possibleColors[(int)Math.floor(Math.random() * 8)];
        this.rarity = rarity;
        size = 1;
        power = rarity;
        hp = rarity;
    }

    //MODIFIES: this
    //EFFECTS: instantiates a Cat with given name, rarity, size, power, and hp
    public Cat(String name, String color, int rarity, int size, int power, int hp) {
        this.name = name;
        this.color = color;
        this.rarity = rarity;
        this.size = size;
        this.power = power;
        this.hp = hp;
    }

    //MODIFIES: this
    //EFFECTS: adds food's value to cat's size
    public void feedCat(Food food) {
        this.size += food.getValue();
    }

    //MODIFIES: this
    //EFFECTS: upgrades cat's power and hp by item's power and hp multiplied by rarity
    public void upgrade(UpgradeItem upgradeItem) {
        this.power += upgradeItem.getPower() * rarity;
        this.hp += upgradeItem.getHP() * rarity;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public int getRarity() {
        return this.rarity;
    }

    public int getSize() {
        return this.size;
    }

    public int getPower() {
        return this.power;
    }

    public int getHP() {
        return this.hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public void changeName(String newName) {
        this.name = newName;
    }

    //MODIFIES: this
    //EFFECTS: converts the cat data to json data
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("color", color);
        json.put("rarity", rarity);
        json.put("size", size);
        json.put("power", power);
        json.put("hp", hp);
        return json;
    }
}
