package ui;

import java.io.FileNotFoundException;

//main class for the game
public class Main {
    public static void main(String[] args) {
        try {
            new FighterGame();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }

}
