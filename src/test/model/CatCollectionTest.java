package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//test class for the CatCollection class
public class CatCollectionTest {
    private CatCollection cc;

    @BeforeEach
    void runBefore() {
        cc = new CatCollection();
    }

    @Test
    void testAddCat() {
        cc.addCat("cat 1", 1);
        assertEquals(cc.catListSize(), 1);
        assertEquals(cc.findCat(0).getName(), "cat 1");
        cc.addCat("cat 2", 1);
        assertEquals(cc.catListSize(), 2);
        assertEquals(cc.findCat(1).getName(), "cat 2");
        cc.addCat("cat 3", 1);
        assertEquals(cc.catListSize(), 3);
        assertEquals(cc.findCat(2).getName(), "cat 3");
        cc.addCat("cat 4", 1);
        assertEquals(cc.catListSize(), 4);
        assertEquals(cc.findCat(3).getName(), "cat 4");
        cc.addCat("cat 5", 2);
        assertEquals(cc.catListSize(), 5);
        assertEquals(cc.findCat(4).getName(), "cat 5");
        cc.addCat("cat 6", 2);
        cc.addCat("cat 7", 2);
        cc.addCat("cat 8", 3);
        cc.addCat("cat 9", 3);
        cc.addCat("cat 10", 4);
        cc.addCat("cat 11", 5);
        assertEquals(cc.catListSize(), 11);
        assertEquals(cc.getCatList().size(), 11);
        assertEquals(cc.findCat(10).getName(), "cat 11");
    }

    @Test
    void testRemoveCat() {
        cc.addCat("1", 1);
        cc.removeCat(0);
        assertEquals(cc.catListSize(), 0);
        cc.addCat("1", 1);
        cc.removeCat(0);
        assertEquals(cc.catListSize(), 0);
        cc.addCat("1", 1);
        cc.addCat("2", 1);
        cc.addCat("3", 1);
        cc.removeCat(0);
        assertEquals(cc.catListSize(), 2);
        assertEquals(cc.findCat(0).getName(), "2");
    }

    //REQUIRES: j < catList.size()
    //EFFECTS: returns index of cat in list or -1 if not in list
    @Test
    void testFindCat() {
        cc.addCat("1", 1);
        Cat cat = cc.findCat(0);
        assertEquals(cat.getName(), "1");
        cc.addCat("2", 1);
        Cat cat2 = cc.findCat(1);
        assertEquals(cat2.getName(), "2");
    }

    @Test
    void testCatListSize() {
        assertEquals(cc.catListSize(), 0);
        cc.addCat("name", 1);
        assertEquals(cc.catListSize(), 1);
        cc.addCat("name2", 1);
        assertEquals(cc.catListSize(), 2);
        cc.addCat("name3", 1);
        assertEquals(cc.catListSize(), 3);
    }

    @Test
    void testAddDeadCat() {
        Cat cat = new Cat("Name", 1);
        cc.addDeadCat(cat);
        assertEquals(cc.catListSize(), 1);
        assertEquals(cc.findCat(0).getName(), "Name");
        assertEquals(cc.findCat(0).getHP(), 1);
    }

    @Test
    void testGetCatList() {
        ArrayList<Cat> catList = cc.getCatList();
        assertEquals(catList.size(), 0);
        cc.addCat("hi", 1);
        catList = cc.getCatList();
        assertEquals(catList.size(), 1);
        assertEquals(catList.get(0).getName(), "hi");
        cc.removeCat(0);
        catList = cc.getCatList();
        assertEquals(catList.size(), 0);
        cc.addCat("a", 1);
        catList = cc.getCatList();
        assertEquals(catList.size(), 1);
        assertEquals(catList.get(0).getName(), "a");
        cc.addCat("2", 1);
        catList = cc.getCatList();
        assertEquals(catList.size(), 2);
        assertEquals(catList.get(0).getName(), "a");
        assertEquals(catList.get(1).getName(), "2");
    }

    @Test
    void testGetCatNameList() {
        ArrayList<String> catList = cc.getCatNameList();
        assertEquals(catList.size(), 0);
        cc.addCat("hi", 1);
        catList = cc.getCatNameList();
        assertEquals(catList.size(), 1);
        assertEquals(catList.get(0), "[1] hi");
        cc.removeCat(0);
        catList = cc.getCatNameList();
        assertEquals(catList.size(), 0);
        cc.addCat("a", 1);
        catList = cc.getCatNameList();
        assertEquals(catList.size(), 1);
        assertEquals(catList.get(0), "[1] a");
        cc.addCat("2", 1);
        catList = cc.getCatNameList();
        assertEquals(catList.size(), 2);
        assertEquals(catList.get(0), "[1] a");
        assertEquals(catList.get(1), "[2] 2");
    }

}
