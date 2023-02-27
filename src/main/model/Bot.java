package model;

//represents a Bot with power and hp that is used during fights
public class Bot {
    private int power;
    private int hp;

    //MODIFIES: this
    //EFFECTS: instantiates a new Bot with given power and hp
    public Bot(int power, int hp) {
        this.power = power;
        this.hp = hp;
    }

    public int getPower() {
        return power;
    }

    public int getHP() {
        return hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }
}
