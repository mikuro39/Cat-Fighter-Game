package model;

//an upgrade item gives the cat an increase in power and hp during battles
public class UpgradeItem {
    private int power;
    private int hp;

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
