package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//tests for the cat class
public class CatTest {
    private String[] possibleColors = {"black", "white", "orange", "gray", "brown", "blue", "pink", "rainbow"};
    private Cat cat;
    private Cat rareCat;

    //REQUIRES: rarity >= 1
    //MODIFIES: this
    //EFFECTS: instantiates a cat with given name and rarity, random color, size 1, and power & hp depending on rarity
    @BeforeEach
    void runBefore() {
        cat = new Cat("generic cat", 1);
        rareCat = new Cat("rare", 5);
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
        rareCat.upgrade(upgradeItem);
        assertEquals(rareCat.getHP(), 10);
        assertEquals(rareCat.getPower(), 10);
    }

    @Test
    void testGetName() {
        assertEquals(cat.getName(), "generic cat");
        assertEquals(rareCat.getName(), "rare");
    }

    @Test
    void testGetColor() {
        String color = cat.getColor();
        boolean contains = false;
        for (String c : possibleColors) {
            if (c.equals(color)) {
                contains = true;
                break;
            }
        }
        assertTrue(contains);
    }

    @Test
    void testGetRarity() {
        assertEquals(cat.getRarity(), 1);
        assertEquals(rareCat.getRarity(), 5);
    }

    @Test
    void testGetSize() {
        assertEquals(cat.getSize(), 1);
        assertEquals(rareCat.getSize(), 1);
    }

    @Test
    void testGetPower() {
        assertEquals(cat.getPower(), 1);
        assertEquals(rareCat.getPower(), 5);
    }

    @Test
    void testGetHP() {
        assertEquals(cat.getHP(), 1);
        assertEquals(rareCat.getHP(), 5);
    }

    @Test
    void testSetHP() {
        cat.setHP(10);
        assertEquals(cat.getHP(), 10);
    }

    @Test
    void testChangeName() {
        cat.changeName("new name");
        assertEquals(cat.getName(), "new name");
    }
}
