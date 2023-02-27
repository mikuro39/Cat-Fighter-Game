package model;

//represents an upgrade item that gives a Cat an increase in power and hp a fight
public class UpgradeItem {
    private final int power;
    private final int hp;

    //MODIFIES: this
    //EFFECTS: instantiates a new UpgradeItem with given power and hp
    public UpgradeItem(int power, int hp) {
        this.power = power;
        this.hp = hp;
    }

    public int getPower() {
        return power;
    }

    public int getHP() {
        return hp;
    }
}
