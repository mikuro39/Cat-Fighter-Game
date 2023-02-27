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
        cc.addCat("cat 1");
        assertEquals(cc.catListSize(), 1);
        assertEquals(cc.findCat(0).getName(), "cat 1");
        cc.addCat("cat 2");
        assertEquals(cc.catListSize(), 2);
        assertEquals(cc.findCat(1).getName(), "cat 2");
        cc.addCat("cat 3");
        assertEquals(cc.catListSize(), 3);
        assertEquals(cc.findCat(2).getName(), "cat 3");
        cc.addCat("cat 4");
        assertEquals(cc.catListSize(), 4);
        assertEquals(cc.findCat(3).getName(), "cat 4");
        cc.addCat("cat 5");
        assertEquals(cc.catListSize(), 5);
        assertEquals(cc.findCat(4).getName(), "cat 5");
        cc.addCat("cat 6");
        cc.addCat("cat 7");
        cc.addCat("cat 8");
        cc.addCat("cat 9");
        cc.addCat("cat 10");
        cc.addCat("cat 11");
        cc.addCat("cat 12");
        cc.addCat("cat 13");
        cc.addCat("cat 14");
        cc.addCat("cat 15");
        cc.addCat("cat 16");
        cc.addCat("cat 17");
        cc.addCat("cat 18");
        cc.addCat("cat 19");
        cc.addCat("cat 20");
        cc.addCat("cat 21");
        cc.addCat("cat 22");
        cc.addCat("cat 23");
        cc.addCat("cat 24");
        cc.addCat("cat 25");
        cc.addCat("cat 26");
        cc.addCat("cat 27");
        cc.addCat("cat 28");
        cc.addCat("cat 29");
        cc.addCat("cat 30");
        cc.addCat("cat 31");
        cc.addCat("cat 32");
        cc.addCat("cat 33");
        cc.addCat("cat 34");
        cc.addCat("cat 35");
        assertEquals(cc.catListSize(), 35);
        assertEquals(cc.getCatList().size(), 35);
        assertEquals(cc.findCat(20).getName(), "cat 21");
    }

    @Test
    void testRemoveCat() {
        cc.addCat("1");
        cc.removeCat(0);
        assertEquals(cc.catListSize(), 0);
        cc.addCat("1");
        cc.removeCat(0);
        assertEquals(cc.catListSize(), 0);
        cc.addCat("1");
        cc.addCat("2");
        cc.addCat("3");
        cc.removeCat(0);
        assertEquals(cc.catListSize(), 2);
        assertEquals(cc.findCat(0).getName(), "2");
    }

    //REQUIRES: j < catList.size()
    //EFFECTS: returns index of cat in list or -1 if not in list
    @Test
    void testFindCat() {
        cc.addCat("1");
        Cat cat = cc.findCat(0);
        assertEquals(cat.getName(), "1");
        cc.addCat("2");
        Cat cat2 = cc.findCat(1);
        assertEquals(cat2.getName(), "2");
    }

    @Test
    void testCatListSize() {
        assertEquals(cc.catListSize(), 0);
        cc.addCat("name");
        assertEquals(cc.catListSize(), 1);
        cc.addCat("name2");
        assertEquals(cc.catListSize(), 2);
        cc.addCat("name3");
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
        cc.addCat("hi");
        catList = cc.getCatList();
        assertEquals(catList.size(), 1);
        assertEquals(catList.get(0).getName(), "hi");
        cc.removeCat(0);
        catList = cc.getCatList();
        assertEquals(catList.size(), 0);
        cc.addCat("a");
        catList = cc.getCatList();
        assertEquals(catList.size(), 1);
        assertEquals(catList.get(0).getName(), "a");
        cc.addCat("2");
        catList = cc.getCatList();
        assertEquals(catList.size(), 2);
        assertEquals(catList.get(0).getName(), "a");
        assertEquals(catList.get(1).getName(), "2");
    }

    @Test
    void testGetCatNameList() {
        ArrayList<String> catList = cc.getCatNameList();
        assertEquals(catList.size(), 0);
        cc.addCat("hi");
        catList = cc.getCatNameList();
        assertEquals(catList.size(), 1);
        assertEquals(catList.get(0), "[1] hi");
        cc.removeCat(0);
        catList = cc.getCatNameList();
        assertEquals(catList.size(), 0);
        cc.addCat("a");
        catList = cc.getCatNameList();
        assertEquals(catList.size(), 1);
        assertEquals(catList.get(0), "[1] a");
        cc.addCat("2");
        catList = cc.getCatNameList();
        assertEquals(catList.size(), 2);
        assertEquals(catList.get(0), "[1] a");
        assertEquals(catList.get(1), "[2] 2");
    }

}
