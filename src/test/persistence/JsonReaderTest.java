package persistence;

import model.Cat;
import model.CatCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//test class for JsonReader
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CatCollection c = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //test pass
        }
    }

    @Test
    void testReaderNoCats() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCatCollection.json");
        try {
            CatCollection cc = reader.read();
            assertEquals("My cat collection", cc.getName());
            assertEquals(0, cc.catListSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCatCollection() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCatCollection.json");
        try {
            CatCollection cc = reader.read();
            assertEquals("My cat collection", cc.getName());
            List<Cat> cats = cc.getCats();
            assertEquals(2, cats.size());
            checkCat("hi", "blue", 1, 1, 1, 1, cats.get(0));
            checkCat("hi2", "red", 2, 1, 3, 3, cats.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
