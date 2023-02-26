package ui;

import model.*;

import java.util.Scanner;

//main class for the game's ui
public class Main {
    private static CatCollection catCollection = new CatCollection();
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.println("welcome to cat fighter game! what would you like to do?");
        mainMenu();
    }

    private static void mainMenu() {
        scanner = new Scanner(System.in);
        System.out.println("Main Menu");
        System.out.println("What would you like to do?");
        System.out.println("1. collect new cat");
        System.out.println("2. view cat inventory");
        System.out.println("3. enter battle");
        System.out.println("4. exit game");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            collectCat();
        } else if (choice.equals("2")) {
            catInventory();
        } else if (choice.equals("3")) {
            fighter();
        } else if (choice.equals("4")) {
            System.out.println("Thanks for playing! See you next time.");
        } else {
            System.out.println("Sorry, you entered an invalid number. Sending you back to the main menu:");
            mainMenu();
        }
    }

    private static void collectCat() {
        scanner = new Scanner(System.in);
        System.out.println("What would you like to name your cat? ");
        String name = scanner.nextLine();
        System.out.println("generating new cat...");
        Cat c = catCollection.addCat(name);
        System.out.println("Check out your new cat!");
        System.out.println("It has name " + name + ", color " + c.getColor()
                + ", and rarity " + c.getRarity());
        makeChoices();
    }

    private static void catInventory() {
        if (catCollection.catListSize() == 0) {
            empty();
        } else {
            nonEmpty();
        }
    }

    private static void empty() {
        scanner = new Scanner(System.in);
        System.out.println("You have no cats yet!");
        System.out.println("Where would you like to go? ");
        System.out.println("1. main menu");
        System.out.println("2. exit game");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            mainMenu();
        } else if (choice.equals("2")) {
            System.out.println("Thanks for playing! See you next time.");
        } else {
            System.out.println("Sorry, you entered an invalid number. Sending you back to the main menu:");
            mainMenu();
        }
    }

    private static void nonEmpty() {
        scanner = new Scanner(System.in);
        System.out.println("Here are your cats: " + catCollection.getCatNameList());
        System.out.println("Where would you like to go? \n 1. view cat \n 2. main menu \n 3. exit game");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            System.out.println("What is the id of the cat you would like to view? ");
            int c = scanner.nextInt() - 1;
            Cat cat = catCollection.findCat(c);
            getStats(cat);
            System.out.println("What would you like to do with this cat? \n1. edit name \n2. feed cat");
            System.out.println("3. delete cat \n4. return to menu");
            editCat(cat, c);
            makeChoices();
        } else if (choice.equals("2")) {
            mainMenu();
        } else if (choice.equals("3")) {
            System.out.println("Thanks for playing! See you next time.");
        } else {
            System.out.println("Sorry, you entered an invalid number. Sending you back to the main menu:");
            mainMenu();
        }
    }

    private static void getStats(Cat cat) {
        System.out.println("name: " + cat.getName());
        System.out.println("color: " + cat.getColor());
        System.out.println("rarity: " + cat.getRarity());
        System.out.println("size: " + cat.getSize());
        System.out.println("power: " + cat.getPower());
        System.out.println("hp: " + cat.getHP());
    }

    private static void editCat(Cat cat, int c) {
        scanner = new Scanner(System.in);
        String ch = scanner.nextLine();
        if (ch.equals("1")) {
            System.out.println("What would you like to change this cat's name to? ");
            String name = scanner.nextLine();
            cat.changeName(name);
            System.out.println("Your cat's name is now " + name);
        } else if (ch.equals("2")) {
            cat.feedCat(enterFoodShop());
            System.out.println("You fed your cat! Now its size is " + cat.getSize() + ".");
        } else if (ch.equals("3")) {
            catCollection.removeCat(c);
            System.out.println("Successfully removed cat");
        } else if (ch.equals("4")) {
            mainMenu();
        } else {
            System.out.println("Sorry, you entered an invalid number. Sending you back to the main menu:");
            mainMenu();
        }
    }

    private static Food enterFoodShop() {
        scanner = new Scanner(System.in);
        Food food = new Food(0);
        System.out.println("Please choose the type of food you would like to feed your cat!");
        System.out.println("1. Fish (+1 size)");
        System.out.println("2. Premium Fish (+5 size)");
        System.out.println("3. Crazy Fish (+10 size)");
        int choice = scanner.nextInt();
        if (choice == 1) {
            food = new Food(1);
        } else if (choice == 2) {
            food = new Food(5);
        } else if (choice == 3) {
            food = new Food(10);
        } else {
            System.out.println("Sorry, the choice you made is invalid.");
            enterFoodShop();
        }
        return food;
    }

    private static void fighter() {
        scanner = new Scanner(System.in);
        if (catCollection.catListSize() == 0) {
            System.out.println("You have no cats to fight with!");
        } else {
            Bot bot = initializeBot();
            Fight fight = new Fight(catCollection, bot);
            while (!fight.checkGameOver()) {
                fighting(fight, bot);
            }
            System.out.println("The fight ended!");
            System.out.println("Here are all the cats that died in battle.");
            System.out.println(fight.showDeadCats());
            System.out.println("Would you like to repair them? (yes/no)");
            String choice3 = scanner.nextLine();
            if (choice3.equals("yes")) {
                fight.repairCats();
                System.out.println("Your cats have been returned to your cat inventory at 1 HP. Please feed them.");
            } else if (choice3.equals("no")) {
                System.out.println("Your cats have sadly gone to sleep...");
            }
        }
        makeChoices();
    }

    private static void makeChoices() {
        scanner = new Scanner(System.in);
        System.out.println("Where would you like to go? ");
        System.out.println("1. main menu");
        System.out.println("2. exit game");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            mainMenu();
        } else if (choice.equals("2")) {
            System.out.println("Thanks for playing! See you next time.");
        } else {
            System.out.println("Sorry, you entered an invalid number. Sending you back to the main menu:");
            mainMenu();
        }
    }

    private static Bot initializeBot() {
        scanner = new Scanner(System.in);
        Bot bot;
        System.out.println("Get ready to fight!");
        System.out.println("Choose a difficulty: easy/medium/hard");
        String choice1 = scanner.nextLine();
        if (choice1.equals("easy")) {
            bot = new Bot(1, 1);
        } else if (choice1.equals("medium")) {
            bot = new Bot(10, 10);
        } else if (choice1.equals("hard")) {
            bot = new Bot(100, 100);
        } else {
            bot = new Bot(1, 1);
        }
        return bot;
    }

    private static void fighting(Fight fight, Bot bot) {
        scanner = new Scanner(System.in);
        System.out.println("The battle is between: " + fight.getFirstCat().getName() + " and bot " + bot.getHP());
        System.out.println(fight.proceedByOneRound());
        System.out.println("Would you like to upgrade your next cat? (yes/no)");
        String choice5 = scanner.nextLine();
        if (choice5.equals("yes")) {
            fight.upgradeCat(makeItem());
            System.out.println("Your new cat " + fight.getFirstCat().getName() + " is at "
                    + fight.getFirstCat().getHP() + "HP and "
                    + fight.getFirstCat().getPower() + "power.");
        } else {
            System.out.println("ok");
        }
        fight.checkGameOver();
    }

    private static UpgradeItem makeItem() {
        scanner = new Scanner(System.in);
        UpgradeItem upgradeItem;
        System.out.println("Choose an item to upgrade your cat with. \n1. mini item (+1 power/hp)");
        System.out.println("2. mega item (+5 power/hp) \n3. crazy item (+10 power/hp)");
        String choice6 = scanner.nextLine();
        if (choice6.equals("1")) {
            upgradeItem = new UpgradeItem(1, 1);
        } else if (choice6.equals("2")) {
            upgradeItem = new UpgradeItem(5, 5);
        } else if (choice6.equals("3")) {
            upgradeItem = new UpgradeItem(10, 10);
        } else {
            upgradeItem = new UpgradeItem(0, 0);
        }
        return upgradeItem;
    }

}
