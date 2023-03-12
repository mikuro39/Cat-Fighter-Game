package persistence;

import model.Cat;
import model.CatCollection;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//test class for JsonWriter
class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            CatCollection cc = new CatCollection("My cat collection");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //passed test
        }
    }

    @Test
    void testWriterEmptyCatCollection() {
        try {
            CatCollection cc = new CatCollection("My cat collection");
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyCatCollection.json");
            writer.open();
            writer.write(cc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyCatCollection.json");
            cc = reader.read();
            assertEquals("My cat collection", cc.getName());
            assertEquals(0, cc.catListSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCatCollection() {
        try {
            CatCollection cc = new CatCollection("My cat collection");
            cc.addCatDebug(new Cat("hi", "blue", 1, 1, 1, 1));
            cc.addCatDebug(new Cat("hi2", "red", 2, 1, 3, 3));
            JsonWriter writer = new JsonWriter("./data/testReaderGeneralCatCollection.json");
            writer.open();
            writer.write(cc);
            writer.close();
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

        try {
            JsonReader reader = new JsonReader("./data/testReaderGeneralCatCollection.json");
            CatCollection cc = reader.read();
            assertEquals("My cat collection", cc.getName());
            List<Cat> cats = cc.getCats();
            assertEquals(2, cats.size());
            checkCat("hi", "blue", 1, 1, 1, 1, cats.get(0));
            checkCat("hi2", "red", 2, 1, 3, 3, cats.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
