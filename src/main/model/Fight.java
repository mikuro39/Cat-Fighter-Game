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

    public void introduceFight(Cat cat) {
        System.out.println("The battle is between: " + cat.getName() + " and bot " + bot.getHP());
    }

    //EFFECTS: proceeds one round of fight
    public void proceedByOneRound() {
        Cat firstCat = catCollection.findCat(0);
        introduceFight(firstCat);
        if (firstCat.getHP() > 0 && bot.getHP() > 0) {
            bot.setHP(bot.getHP() - firstCat.getPower());
            firstCat.setHP(firstCat.getHP() - bot.getPower());
        }
        if (bot.getHP() <= 0) {
            System.out.println("You won!");
            gameOver = true;
        } else if (firstCat.getHP() <= 0) {
            deadCats.add(firstCat);
            catCollection.removeCat(0);
            System.out.println("Your cat " + firstCat.getName() + " died!");
            if (catCollection.catListSize() == 0) {
                gameOver = true;
            }
        } else {
            System.out.println("The bot has been knocked down to " + bot.getHP() + "!");
            System.out.println("Your cat is at " + firstCat.getHP() + "!");
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
