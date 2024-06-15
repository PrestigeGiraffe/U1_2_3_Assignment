/*
 * Purpose: Creates a health bar when the constructor is called, and contains method to manipulate the size and text of the health bar
 * Author: Johnson Yep
 */

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class HealthBar extends Rectangle {
    private Rectangle insideHealthBar;
    private Label healthText;
    private int width, maxHealth;
    
    /**
     * Creates a health bar that scales to the entity's health
     * @param x x coordinate
     * @param y y coordinate
     * @param width width of the health bar
     * @param height height of the health bar
     */
    HealthBar(int x, int y, int width, int height) { // passes these values to rectangle
        super(x, y, width, height);
        
        this.setFill(Paint.valueOf("White"));
        this.width = width;

        insideHealthBar = new Rectangle(x, y, width, height);
        insideHealthBar.setFill(Paint.valueOf("Lime"));

        healthText = new Label();
        healthText.setLayoutX(x);
        healthText.setLayoutY(y);
        healthText.setStyle("-fx-text-fill: OLIVEDRAB;" + "-fx-font-size: 20pt;");
    }

    /**
     * Returns the actual health bar part of the health bar
     * @return
     */
    public Rectangle getInsideHealthBar() {
        return insideHealthBar;
    }

    /**
     * Returns the text inside the health bar
     * @param maxHealth how much health the entity starts out with
     * @return
     */
    public Label getHealthText(double maxHealth) {
        this.maxHealth = (int)maxHealth; // sets the maxHealth of the health bar so the text can display something like: 50/100
        return healthText;
    }
    
    /**
     * Sets the size of the health bar and the text inside the health bar
     * @param health
     */
    public void setHealth(double health) {
        insideHealthBar.setWidth((health/maxHealth)*width); // scales the bar to the player's health out of the starting health (maxHealth)
        healthText.setText("Health: " + (int)health + "/" + maxHealth); // displays player's HP in text
    }
}
