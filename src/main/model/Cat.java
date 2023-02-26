package model;

import java.util.ArrayList;

public class Cat {
    private String name;
    private String color;
    private int rarity;
    private int size;
    private int power;
    private int hp;
    private String[] possibleColors = {"black", "white", "orange", "gray", "brown", "blue", "pink", "rainbow"};


    //REQUIRES: rarity >= 1
    //MODIFIES: this
    //EFFECTS: instantiates a cat with given name and rarity, random color, size 1, and power & hp depending on rarity
    public Cat(String name, int rarity) {
        this.name = name;
        color = possibleColors[(int)Math.floor(Math.random() * 8)];
        this.rarity = rarity;
        size = 1;
        power = rarity;
        hp = rarity;
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


}
