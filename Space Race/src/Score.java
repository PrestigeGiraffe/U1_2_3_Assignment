/*
 * Purpose: Class that stores values of the player's score (kills), time survived in the game, and damage done to enemies in the game. Contains methods to print and read these values from a file
 * Author: Johnson Yep
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Score {
    // private int timesPlayed;
    // private int[][] scores = new int[3][timesPlayed]; // creates a 2D array that stores the values of the game scores at the game over screen
    
    private int score, timeSurvived, damageDone;

    public void updateScore(boolean progressed, int stat) {
        try {
            FileWriter file = new FileWriter("HighScores.txt");
            
            
            if (progressed) {
                file.write(stat);
                file.close();
            }
        }
        catch(IOException e) {
            System.out.println("File already exists.");
        }
    }

    public boolean checkScore() {
        try {
            File file = new File("HighScores.txt");
            Scanner read = new Scanner(file);

            int i = 0; // variable to keep track of how many iterations the while loop as gone through
            while (read.hasNextInt()) {
                if (i == 0 && this.score < read.nextInt()) {
                    this.score = read.nextInt();
                    break;
                }
                else if (i == 1 && this.timeSurvived < read.nextInt()) {
                    this.timeSurvived = read.nextInt();
                    break;
                }
                else if (i == 2 && this.damageDone < read.nextInt()) {
                    this.damageDone = read.nextInt();
                    break;
                }
                else {
                    read.close();
                    return false;
                }
            }

            read.close();
            return true;
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found.");
        }

        return false;
    }
}
