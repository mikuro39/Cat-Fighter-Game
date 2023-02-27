package model;

import java.util.ArrayList;

//Represents a fight between a CatCollection and a Bot
public class Fight {
    private CatCollection catCollection;
    private Bot bot;
    boolean gameOver;
    private ArrayList<Cat> deadCats;

    //MODIFIES: this
    //EFFECTS: instantiates a new Fight with given catCollection and Bot
    public Fight(CatCollection catCollection, Bot bot) {
        this.catCollection = catCollection;
        this.bot = bot;
        gameOver = false;
        deadCats = new ArrayList<>();
    }

    //REQUIRES: catCollection is not empty
    //EFFECTS: Cat and Bot reduce each other's hp by their respective power and die if their hp is below 0
    public String proceedByOneRound() {
        Cat firstCat = catCollection.findCat(0);
        bot.setHP(bot.getHP() - firstCat.getPower());
        firstCat.setHP(firstCat.getHP() - bot.getPower());
        if (bot.getHP() <= 0) {
            gameOver = true;
            if (firstCat.getHP() <= 0) {
                removeDeadCat(firstCat);
                return "Your cat " + firstCat.getName() + " died! You won!";
            } else {
                return "You won!";
            }
        } else if (firstCat.getHP() <= 0) {
            removeDeadCat(firstCat);
            if (catCollection.catListSize() == 0) {
                gameOver = true;
                return "You lost!";
            }
            return "Your cat " + firstCat.getName() + " died!";
        } else {
            return "The bot has been knocked down to " + bot.getHP() + "! \n Your cat is at " + firstCat.getHP() + "!";
        }
    }

    //REQUIRES: catCollection is not empty
    //MODIFIES: this
    //EFFECTS: adds a cat to deadCats and removes that Cat from the collection
    public void removeDeadCat(Cat cat) {
        deadCats.add(cat);
        catCollection.removeCat(0);
    }

    //REQUIRES: catCollection is not empty
    //MODIFIES: this
    //EFFECTS: upgrades the first Cat in the collection with the upgrade item
    public void upgradeCat(UpgradeItem upgradeItem) {
        Cat firstCat = catCollection.findCat(0);
        firstCat.upgrade(upgradeItem);
    }

    //REQUIRES: catCollection is not empty
    //EFFECTS: returns the first Cat in the catCollection
    public Cat getFirstCat() {
        return catCollection.findCat(0);
    }

    public boolean checkGameOver() {
        return gameOver;
    }

    //MODIFIES: this
    //EFFECTS: adds each dead Cat to catCollection
    public void repairCats() {
        for (Cat cat : deadCats) {
            catCollection.addDeadCat(cat);
        }
    }

    //EFFECTS: returns a list of all the dead Cats' names
    public ArrayList<String> showDeadCats() {
        ArrayList<String> deadCatNames = new ArrayList<>();
        for (Cat cat : deadCats) {
            deadCatNames.add(cat.getName());
        }
        return deadCatNames;
    }
}
