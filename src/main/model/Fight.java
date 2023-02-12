package model;

public class Fight {
    //TODO: make it a list of cats insteaad
    //TODO: be able to use items in game
    private Cat cat;
    private Bot bot;

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: instantiates a new Fight with given cat and bot
    public Fight(Cat cat, Bot bot) {
        this.cat = cat;
        this.bot = bot;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: proceeds one round of fight
    public void proceedByOneRound() {
        if (cat.getHP() > 0 && bot.getHP() > 0) {
            bot.setHP(bot.getHP() - cat.getPower());
            cat.setHP(cat.getHP() - bot.getPower());
        }
        if (bot.getHP() <= 0) {
            System.out.println("You won!");
        } else if (cat.getHP() <= 0) {
            System.out.println("You died... choose another cat?");
        }
    }

}
