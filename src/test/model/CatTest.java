package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CatTest {

    private String name;
    private String color;
    private int rarity;
    private int size;
    private int power;
    private int hp;
    private String[] possibleColors = {"black", "white", "orange", "gray", "brown", "blue", "pink", "rainbow"};
    private Cat cat;
    private Cat rarecat;

    //REQUIRES: rarity >= 1
    //MODIFIES: this
    //EFFECTS: instantiates a cat with given name and rarity, random color, size 1, and power & hp depending on rarity
    @BeforeEach
    void runBefore() {
        cat = new Cat("generic cat", 1);
        rarecat = new Cat("rare", 5);
    }

    @Test
    void testFeedCat() {
        Food food = new Food(10);
        cat.feedCat(food);
        assertEquals(cat.getSize(), 11);
        cat.feedCat(food);
        assertEquals(cat.getSize(), 21);
    }

    @Test
    void testUpgrade() {
        UpgradeItem upgradeItem = new UpgradeItem(1, 1);
        cat.upgrade(upgradeItem);
        assertEquals(cat.getHP(), 2);
        assertEquals(cat.getPower(), 2);
        rarecat.upgrade(upgradeItem);
        assertEquals(rarecat.getHP(), 10);
        assertEquals(rarecat.getPower(), 10);
    }
}
