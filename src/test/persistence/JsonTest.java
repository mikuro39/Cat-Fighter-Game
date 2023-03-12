package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import model.Cat;

//test class for Json
public class JsonTest {
    protected void checkCat(String name, String color, int rarity, int size, int power, int hp, Cat cat) {
        assertEquals(name, cat.getName());
        assertEquals(color, cat.getColor());
        assertEquals(rarity, cat.getRarity());
        assertEquals(size, cat.getSize());
        assertEquals(power, cat.getPower());
        assertEquals(hp, cat.getHP());
    }
}
