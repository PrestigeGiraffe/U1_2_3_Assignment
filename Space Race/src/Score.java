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

            if 
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found.");
        }
        
    }

    // GETTERS AND SETTERS FOR CLASS FIELDS
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public int getTimeSurvived() {
        return timeSurvived;
    }
    public void setTimeSurvived(int timeSurvived) {
        this.timeSurvived = timeSurvived;
    }

    public int getDamageDone() {
        return damageDone;
    }

    public void setDamageDone(int damageDone) {
        this.damageDone = damageDone;
    }
}
