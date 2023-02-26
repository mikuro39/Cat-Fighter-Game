package model;

//food is an item used to increase the cat's size
public class Food {
    private int value;

    //MODIFIES: this
    //EFFECTS: instantiates a new Food with given value
    public Food(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
