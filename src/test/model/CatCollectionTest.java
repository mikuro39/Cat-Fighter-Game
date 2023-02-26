package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO: class level comment
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
        assertEquals(cc.getCatNameList().get(0), "cat 1");
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
        assertEquals(cc.getCatNameList().get(0), "2");
    }

    //REQUIRES: j < catList.size()
    //EFFECTS: returns index of cat in list or -1 if not in list
    @Test
    void testFindCat() {
        cc.addCat("1");

    }

    @Test
    void testCatListSize() {

    }

}
