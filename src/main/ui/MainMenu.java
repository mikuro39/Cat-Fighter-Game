package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

//GUI for Cat Fighter Game
public class MainMenu extends JFrame {
    private static final String JSON_STORE = "./data/cats.json";
    private CatCollection catCollection = new CatCollection("collection");
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //MODIFIES: this
    //EFFECTS: creates a JsonWriter and JsonReader, then sends user to the main menu
    public MainMenu() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        menu();
    }

    //EFFECTS: creates a main menu with label and button panel
    private void menu() {
        JFrame menu = new JFrame("Main Menu");
        menu.setLayout(new BorderLayout());
        menu.setResizable(false);
        menu.setSize(800, 400);
        menu.setBackground(Color.decode("#c1dcf7"));
        JLabel label = new JLabel("Welcome to Cat Fighter Game! What would you like to do?");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.decode("#c1dcf7"));
        menu.add(label, BorderLayout.NORTH);
        menu.add(makeButtonPanel(menu), BorderLayout.CENTER);
        menu.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: creates a button panel with 6 buttons and an action listener for button1
    private JPanel makeButtonPanel(JFrame menu) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1));
        buttonPanel.setBackground(Color.decode("#c1dcf7"));
        button1 = new JButton("Create new cat");
        button2 = new JButton("View cat inventory");
        button3 = new JButton("Enter battle");
        button4 = new JButton("Save and exit");
        button5 = new JButton("Exit without saving");
        button6 = new JButton("Load cat collection");
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        button1.addActionListener(b -> {
            newCatScreen();
            menu.dispose();
        });
        addButtons(menu);
        return buttonPanel;
    }

    //EFFECTS: adds action listeners for the other 5 menu buttons
    private void addButtons(JFrame menu) {
        button2.addActionListener(b -> {
            if (catCollection.catListSize() != 0) {
                openCatCollection();
            } else {
                confirmationScreen(new Cat("", 0), "Cat collection", "You have no cats yet!");
            }
            menu.dispose();
        });
        button3.addActionListener(b2 -> {
            enterBattle();
            menu.dispose();
        });
        button4.addActionListener(b3 -> {
            saveAndExit();
            menu.dispose();
        });
        button5.addActionListener(b4 -> {
            exitWithoutSaving();
        });
        button6.addActionListener(b5 -> {
            loadCatCollection();
        });
    }

    //MODIFIES: this
    //EFFECTS: creates a JFrame for creating a new cat with a label and a text box for user to input their cat name
    private void newCatScreen() {
        JFrame createNewCat = new JFrame("Create new cat");
        createNewCat.setSize(800, 400);
        createNewCat.setBackground(Color.decode("#c1dcf7"));
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.decode("#c1dcf7"));
        JLabel textLabel = new JLabel("What would you like to name your cat?");
        JTextField textField = new JTextField();
        button1 = new JButton("Done!");
        button1.addActionListener(b -> nameCat(textField, createNewCat));
        panel.add(textLabel, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);
        panel.add(button1, BorderLayout.SOUTH);
        createNewCat.add(panel);
        createNewCat.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: processes user input and determines if the name string is empty
    private void nameCat(JTextField textField, JFrame createNewCat) {
        String name = textField.getText();
        if (name.equals("")) {
            confirmationScreen(new Cat("", 0), "Add unsuccessful",
                    "Please give your cat a name.");
        } else {
            Cat c = new Cat(name, rarityCalculator());
            catCollection.addCatDebug(c);
            createNewCat.dispose();
            confirmationScreen(c, "Added cat", "Check out your new cat!\n It has name " + c.getName()
                    + ", color " + c.getColor() + ", and rarity " + c.getRarity());
        }
    }

    //EFFECTS: removes the current frame and sends user back to main menu
    private void returnToMenu(JFrame frame) {
        menu();
        frame.dispose();
    }

    //MODIFIES: this
    //EFFECTS: a generic confirmation screen containing a custom name and label, as well as an icon with the button
    private void confirmationScreen(Cat c, String s, String s2) {
        JFrame frame = new JFrame(s);
        frame.setSize(800, 400);
        frame.setBackground(Color.decode("#c1dcf7"));
        JLabel label = new JLabel(s2);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.decode("#c1dcf7"));
        if (s2.contains("your new cat")) {
            ImageIcon image = new ImageIcon(getCatImage(c));
            label.setIcon(image);
        }
        label.setVerticalTextPosition(JLabel.TOP);
        button1 = new JButton("Main Menu");
        button1.addActionListener(e -> returnToMenu(frame));
        frame.add(label, BorderLayout.CENTER);
        frame.add(button1, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    //EFFECTS: returns a string corresponding to the image path of the color of the cat
    private String getCatImage(Cat c) {
        if (c.getColor().equals("black")) {
            return "src/main/ui/images/blackCat.jpeg";
        } else if (c.getColor().equals("blue")) {
            return "src/main/ui/images/blueCat.jpeg";
        } else if (c.getColor().equals("brown")) {
            return "src/main/ui/images/brownCat.jpeg";
        } else if (c.getColor().equals("gray")) {
            return "src/main/ui/images/grayCat.jpeg";
        } else if (c.getColor().equals("orange")) {
            return "src/main/ui/images/orangeCat.jpeg";
        } else if (c.getColor().equals("pink")) {
            return "src/main/ui/images/pinkCat.jpeg";
        } else if (c.getColor().equals("rainbow")) {
            return "src/main/ui/images/rainbowCat.jpeg";
        } else if (c.getColor().equals("white")) {
            return "src/main/ui/images/whiteCat.jpeg";
        }
        return "";
    }

    //EFFECTS: generates a random number from 1 to 5 with decreasing chances
    private int rarityCalculator() {
        int range = (int)Math.floor(Math.random() * 15 + 1);
        int rarity;
        if (range <= 5) {
            rarity = 1;
        } else if (range <= 9) {
            rarity = 2;
        } else if (range <= 12) {
            rarity = 3;
        } else if (range <= 14) {
            rarity = 4;
        } else {
            rarity = 5;
        }
        return rarity;
    }

    //MODIFIES: this
    //EFFECTS: displays a clickable list of the user's cat collection
    private void openCatCollection() {
        JFrame listFrame = new JFrame("Cat Name List");
        listFrame.setBackground(Color.decode("#c1dcf7"));
        listFrame.setSize(800, 400);
        List<Cat> cats = catCollection.getCats();
        java.awt.List catList = new java.awt.List();
        for (Cat cat : cats) {
            catList.add(cat.getName());
        }
        JScrollPane scrollPane = new JScrollPane(catList);
        scrollPane.setBackground(Color.decode("#c1dcf7"));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(800, 350));
        catList.addActionListener(e -> findCat(catList, cats, listFrame));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#c1dcf7"));
        buttonPanel.setLayout(new GridLayout(1, 1));
        button2 = new JButton("Main menu");
        buttonPanel.add(button2);
        button2.addActionListener(e -> returnToMenu(listFrame));
        listFrame.add(scrollPane, BorderLayout.CENTER);
        listFrame.add(buttonPanel, BorderLayout.SOUTH);
        listFrame.setVisible(true);
    }

    //EFFECTS: searches through the list of cats to find the cat
    private void findCat(java.awt.List catList, List<Cat> cats, JFrame frame) {
        String selectedCatName = catList.getSelectedItem();
        Cat selectedCat = null;
        for (Cat cat : cats) {
            if (cat.getName().equals(selectedCatName)) {
                selectedCat = cat;
                break;
            }
        }
        catStats(selectedCat);
        frame.dispose();
    }

    //MODIFIES: this
    //EFFECTS: shows a screen containing information about the cat and ways to alter it
    private void catStats(Cat cat) {
        JFrame catStats = new JFrame(cat.getName() + " Stats");
        catStats.setBackground(Color.decode("#c1dcf7"));
        catStats.setSize(800, 400);
        JPanel fullPanel = new JPanel(new BorderLayout());
        fullPanel.setBackground(Color.decode("#c1dcf7"));
        JPanel labelPanel = new JPanel(new GridLayout(6, 1));
        labelPanel.setBackground(Color.decode("#c1dcf7"));
        addLabels(labelPanel, cat);
        fullPanel.add(labelPanel, BorderLayout.WEST);
        ImageIcon image = new ImageIcon(getCatImage(cat));
        JLabel label = new JLabel(image);
        fullPanel.add(label, BorderLayout.EAST);
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        buttonPanel.setBackground(Color.decode("#c1dcf7"));
        buttonSetters(cat, catStats, buttonPanel);
        catStats.add(fullPanel, BorderLayout.CENTER);
        catStats.add(buttonPanel, BorderLayout.SOUTH);
        catStats.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: creates the rest of the buttons on the cat screen with action listeners
    private void buttonSetters(Cat cat, JFrame frame, JPanel panel) {
        button1 = new JButton("Edit name");
        button1.addActionListener(e -> {
            changeName(cat);
            frame.dispose();
        });
        button2 = new JButton("Feed Cat");
        button3 = new JButton("Delete Cat");
        button4 = new JButton("Main menu");
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        button2.addActionListener(e -> {
            feedCat(cat);
            frame.dispose();
        });
        button3.addActionListener(e -> {
            deleteCat(cat);
            frame.dispose();
        });
        button4.addActionListener(e -> returnToMenu(frame));
    }

    //EFFECTS: creates a panel of labels containing the cat's stats
    private void addLabels(JPanel labelPanel, Cat cat) {
        JLabel nameLabel = new JLabel("name: " + cat.getName());
        JLabel colorLabel = new JLabel("color: " + cat.getColor());
        JLabel rarityLabel = new JLabel("rarity: " + cat.getRarity());
        JLabel sizeLabel = new JLabel("size: " + cat.getSize());
        JLabel powerLabel = new JLabel("power: " + cat.getPower());
        JLabel hpLabel = new JLabel("hp: " + cat.getHP());
        labelPanel.add(nameLabel);
        labelPanel.add(colorLabel);
        labelPanel.add(rarityLabel);
        labelPanel.add(sizeLabel);
        labelPanel.add(powerLabel);
        labelPanel.add(hpLabel);
    }

    //MODIFIES: this
    //EFFECTS: changes cat's name to what the user inputs
    private void changeName(Cat cat) {
        JFrame inputFrame = new JFrame("Edit Cat Name");
        inputFrame.setSize(800, 400);
        inputFrame.setBackground(Color.decode("#c1dcf7"));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBackground(Color.decode("#c1dcf7"));
        JTextField textField = new JTextField();
        inputPanel.add(textField, BorderLayout.CENTER);
        JLabel textLabel = new JLabel("What would you like to rename your cat?");
        textLabel.setOpaque(true);
        textLabel.setBackground(Color.decode("#c1dcf7"));
        inputPanel.add(textLabel, BorderLayout.NORTH);
        button1 = new JButton("Done!");
        button1.addActionListener(e -> {
            String inputText = textField.getText();
            cat.changeName(inputText);
            inputFrame.dispose();
            confirmationScreen(cat, "Changed cat's name", "Your cat's name has been successfully changed to "
                    + cat.getName());
        });
        inputPanel.add(button1, BorderLayout.SOUTH);
        inputFrame.add(inputPanel);
        inputFrame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: creates a screen with options to feed the cat different fish to increase its size
    private void feedCat(Cat cat) {
        JFrame menu = new JFrame("Feed Cat");
        menu.setBackground(Color.decode("#c1dcf7"));
        menu.setSize(800, 400);
        menu.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1));
        buttonPanel.setBackground(Color.decode("#c1dcf7"));
        button1 = new JButton("Fish (+1 size)");
        button2 = new JButton("Premium Fish (+5 size)");
        button3 = new JButton("Crazy Fish (+10 size)");
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        JLabel label = new JLabel("Please choose the type of food you would like to feed your cat!");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.decode("#c1dcf7"));
        menu.add(label, BorderLayout.NORTH);
        menu.add(buttonPanel, BorderLayout.CENTER);
        button1.addActionListener(e -> feedCat(cat, menu, 1));
        button2.addActionListener(e -> feedCat(cat, menu, 15));
        button3.addActionListener(e -> feedCat(cat, menu, 10));
        menu.setVisible(true);
    }

    //EFFECTS: feeds the cat and shows a confirmation screen
    private void feedCat(Cat cat, JFrame menu, int i) {
        cat.feedCat(new Food(i));
        confirmationScreen(cat, "Fed cat", "Successfully fed cat. Its size is now " + cat.getSize());
        menu.dispose();
    }

    //EFFECTS: deletes the cat and shows a confirmation screen
    private void deleteCat(Cat cat) {
        catCollection.removeCatDebug(cat);
        confirmationScreen(cat, "Deleted cat", "Your cat has been successfully deleted.");
    }

    //MODIFIES: this
    //EFFECTS: enters a battle or gives the user an option to return to the main menu if they have no cats
    private void enterBattle() {
        if (catCollection.catListSize() == 0) {
            JFrame menu = new JFrame("Fight");
            menu.setSize(800, 400);
            menu.setLayout(new BorderLayout());
            menu.setBackground(Color.decode("#c1dcf7"));
            JLabel label = new JLabel("You have no cats to fight with!");
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setOpaque(true);
            label.setBackground(Color.decode("#c1dcf7"));
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(1, 1));
            buttonPanel.setBackground(Color.decode("#c1dcf7"));
            button1 = new JButton("Main menu");
            button1.addActionListener(e -> returnToMenu(menu));
            buttonPanel.add(button1);
            menu.add(label, BorderLayout.CENTER);
            menu.add(buttonPanel, BorderLayout.SOUTH);
            menu.setVisible(true);
        } else {
            initializeBot();
        }
    }

    //MODIFIES: this
    //EFFECTS: allows user to choose between different bots to fight with
    private void initializeBot() {
        JFrame menu = new JFrame("Select difficulty");
        menu.setSize(800, 400);
        menu.setLayout(new BorderLayout());
        menu.setBackground(Color.decode("#c1dcf7"));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1));
        buttonPanel.setBackground(Color.decode("#c1dcf7"));
        button1 = new JButton("Easy");
        button2 = new JButton("Medium");
        button3 = new JButton("Hard");
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        JLabel label = new JLabel("Get ready to fight! Choose a difficulty:");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.decode("#c1dcf7"));
        menu.add(label, BorderLayout.NORTH);
        menu.add(buttonPanel, BorderLayout.CENTER);
        button1.addActionListener(e -> createBot(menu, 1));
        button2.addActionListener(e -> createBot(menu, 10));
        button3.addActionListener(e -> createBot(menu, 100));
        menu.setVisible(true);
    }

    //EFFECTS: creates a fight between the cats and bot
    private void createBot(JFrame menu, int power) {
        Bot bot = new Bot(power, power);
        Fight fight = new Fight(catCollection, bot);
        initializeFight(fight, bot);
        menu.dispose();
    }

    //EFFECTS: initializes a fight and shows different panels for each cat that is in the fight
    private void initializeFight(Fight fight, Bot bot) {
        JFrame frame = new JFrame("Battle");
        frame.setSize(800, 400);
        frame.setLayout(new CardLayout());
        frame.setBackground(Color.decode("#c1dcf7"));
        JPanel fightingPanel = createFightingPanel(fight, bot, frame);
        fightingPanel.setBackground(Color.decode("#c1dcf7"));
        frame.add(fightingPanel);
        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: creates the panel that shows up when engaged in a fight
    private JPanel createFightingPanel(Fight fight, Bot bot, JFrame frame) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.decode("#c1dcf7"));
        JLabel label;
        JPanel buttonPanel;
        int hp = bot.getHP();
        String name = fight.getFirstCat().getName();
        String result = fight.proceedByOneRound();
        if (result.contains("You won!") || result.contains("You lost!")) {
            label = new JLabel("The battle is between: " + name + " and bot "
                    + hp + " \n." + result);
            buttonPanel = new JPanel(new GridLayout(1, 1));
            gameOver(name, hp, result, fight, buttonPanel, frame);
        } else {
            label = new JLabel("The battle is between: " + name + " and bot "
                    + hp + " \n" + result + "\n Would you like to upgrade your next cat?");
            label.setHorizontalAlignment(JLabel.CENTER);
            buttonPanel = new JPanel(new GridLayout(2, 1));
            notWonBattle(fight, bot, buttonPanel, frame);
        }
        addToPanel(label, buttonPanel, panel);
        return panel;
    }

    //EFFECTS: adds the label and button panel to the panel
    private void addToPanel(JLabel label, JPanel buttonPanel, JPanel panel) {
        label.setOpaque(true);
        label.setBackground(Color.decode("#c1dcf7"));
        buttonPanel.setBackground(Color.decode("#c1dcf7"));
        panel.add(label, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
    }

    //EFFECTS: creates the button for when the game is over
    private void gameOver(String  name, int hp, String result, Fight fight, JPanel buttonPanel, JFrame frame) {
        button1 = new JButton("Proceed");
        button1.addActionListener(e -> {
            createMenuPanel(fight);
            frame.dispose();
        });
        buttonPanel.add(button1);
    }

    //MODIFIES: this
    //EFFECTS: creates the buttons for when the user has not won the battle yet
    private void notWonBattle(Fight fight, Bot bot, JPanel buttonPanel, JFrame frame) {
        button1 = new JButton("Yes");
        button2 = new JButton("No");
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        button1.addActionListener(e -> {
            makeItem(fight.getFirstCat(), fight, bot);
            frame.dispose();
        });
        button2.addActionListener(e -> {
            initializeFight(fight, bot);
            frame.dispose();
            //createFightingPanel(fight, bot, frame);
        });
    }

    //EFFECTS: creates a frame that describes the outcome of the battle
    private void createMenuPanel(Fight fight) {
        JFrame frame = new JFrame("Results");
        frame.setSize(800, 400);
        frame.setBackground(Color.decode("#c1dcf7"));
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.decode("#c1dcf7"));
        JLabel label;
        JPanel buttonPanel = new JPanel();
        if (fight.showDeadCats().size() != 0) {
            label = new JLabel("The fight ended. Here are all the cats that died in battle: "
                    + fight.showDeadCats() + ". Would you like to repair them?");
            buttonStuff(buttonPanel, fight, frame);
        } else {
            label = new JLabel("The fight ended! None of your cats died.");
            noDeath(buttonPanel, frame);
        }
        label.setOpaque(true);
        label.setBackground(Color.decode("#c1dcf7"));
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(label, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    //EFFECTS: creates the button panel for when none of the cats die
    private void noDeath(JPanel buttonPanel, JFrame frame) {
        buttonPanel.setLayout(new GridLayout(1, 1));
        JButton button1 = new JButton("Main menu");
        buttonPanel.add(button1);
        button1.addActionListener(e -> returnToMenu(frame));
    }


    //MODIFIES: this
    //EFFECTS: creates the buttons for the options to repair cats after battle
    private void buttonStuff(JPanel buttonPanel, Fight fight, JFrame frame) {
        buttonPanel.setLayout(new GridLayout(2, 1));
        buttonPanel.setBackground(Color.decode("#c1dcf7"));
        button1 = new JButton("Yes");
        button2 = new JButton("No");
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        button1.addActionListener(e -> {
            fight.repairCats();
            confirmationScreen(new Cat("", 0), "Repaired cats", "Your cat(s) have been returned"
                    + " to your collection at 1 hp.");
            frame.dispose();
        });
        button2.addActionListener(e -> {
            confirmationScreen(new Cat("", 0), "Your cats died", "Your cat(s) have sadly gone"
                    + " to sleep...");
            frame.dispose();
        });
    }

    //MODIFIES: this
    //EFFECTS: creates a screen with items to upgrade the cat with
    private void makeItem(Cat cat, Fight fight, Bot bot) {
        JFrame menu = new JFrame("Upgrade Cat");
        menu.setSize(800, 400);
        menu.setLayout(new BorderLayout());
        menu.setBackground(Color.decode("#c1dcf7"));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1));
        buttonPanel.setBackground(Color.decode("#c1dcf7"));
        button1 = new JButton("Mini item (+1 stats)");
        button2 = new JButton("Mega item (+5 stats)");
        button3 = new JButton("Crazy item (+10 stats)");
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        JLabel label = new JLabel("Choose an item to upgrade your cat with.");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.decode("#c1dcf7"));
        menu.add(label, BorderLayout.NORTH);
        menu.add(buttonPanel, BorderLayout.CENTER);
        button1.addActionListener(e -> upgrade(cat, menu, 1, fight, bot));
        button2.addActionListener(e -> upgrade(cat, menu, 15, fight, bot));
        button3.addActionListener(e -> upgrade(cat, menu, 10, fight, bot));
        menu.setVisible(true);
    }

    //EFFECTS: upgrades the cat with the item and then closes the upgrade menu
    private void upgrade(Cat cat, JFrame menu, int power, Fight fight, Bot bot) {
        cat.upgrade(new UpgradeItem(power, power));
        initializeFight(fight, bot);
        menu.dispose();
    }

    //EFFECTS: saves collection to file
    private void saveAndExit() {
        try {
            jsonWriter.open();
            jsonWriter.write(catCollection);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        } finally {
            System.exit(0);
        }
    }

    //EFFECTS: exits without saving
    private void exitWithoutSaving() {
        System.exit(0);
    }

    //EFFECTS: loads collection from file
    private void loadCatCollection() {
        try {
            catCollection = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
