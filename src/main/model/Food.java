package model;

//represents a food item used to increase a Cat's size
public class Food {
    private final int value;

    //MODIFIES: this
    //EFFECTS: instantiates a new Food with given value
    public Food(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
