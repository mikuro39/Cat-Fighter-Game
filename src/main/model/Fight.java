package model;

import java.util.ArrayList;

public class Fight {
    private CatCollection catCollection;
    private Bot bot;
    boolean gameOver;
    private ArrayList<Cat> deadCats;

    //MODIFIES: this
    //EFFECTS: instantiates a new Fight with given cat and bot
    public Fight(CatCollection catCollection, Bot bot) {
        this.catCollection = catCollection;
        this.bot = bot;
        gameOver = false;
        deadCats = new ArrayList<>();
    }

    //REQUIRES: cat collection is not empty
    //EFFECTS: proceeds one round of fight
    @SuppressWarnings("methodlength")
    public String proceedByOneRound() {
        Cat firstCat = catCollection.findCat(0);
        bot.setHP(bot.getHP() - firstCat.getPower());
        firstCat.setHP(firstCat.getHP() - bot.getPower());
        if (bot.getHP() <= 0) {
            gameOver = true;
            if (firstCat.getHP() <= 0) {
                deadCats.add(firstCat);
                catCollection.removeCat(0);
                return "Your cat " + firstCat.getName() + " died! You won!";
            } else {
                return "You won!";
            }
        } else if (firstCat.getHP() <= 0) {
            deadCats.add(firstCat);
            catCollection.removeCat(0);
            if (catCollection.catListSize() == 0) {
                gameOver = true;
                return "You lost!";
            }
            return "Your cat " + firstCat.getName() + " died!";
        } else {
            return "The bot has been knocked down to " + bot.getHP() + "! \n Your cat is at " + firstCat.getHP() + "!";
        }
    }

    public void upgradeCat(UpgradeItem upgradeItem) {
        Cat firstCat = catCollection.findCat(0);
        firstCat.upgrade(upgradeItem);
    }

    public Cat getFirstCat() {
        return catCollection.findCat(0);
    }

    public boolean checkGameOver() {
        return gameOver;
    }

    public void repairCats() {
        for (Cat cat : deadCats) {
            catCollection.addDeadCat(cat);
        }
    }

    public ArrayList<String> showDeadCats() {
        ArrayList<String> deadCatNames = new ArrayList<>();
        for (Cat cat : deadCats) {
            deadCatNames.add(cat.getName());
        }
        return deadCatNames;
    }
}
