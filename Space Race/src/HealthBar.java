/*
 * Purpose: Creates a health bar when the constructor is called, and contains method to manipulate the health bar
 * Author: Johnson Yep
 */

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class HealthBar extends Rectangle {
    private Rectangle insideHealthBar;
    private Label healthText;
    private int width, maxHealth;

    HealthBar(int x, int y, int width, int height) {
        super(x, y, width, height);
        
        this.setFill(Paint.valueOf("White"));
        this.width = width;

        insideHealthBar = new Rectangle(x, y, width, height);
        insideHealthBar.setFill(Paint.valueOf("Lime"));

        healthText = new Label();
        healthText.setLayoutX(x);
        healthText.setLayoutY(y);
        healthText.setStyle("-fx-text-fill: OLIVEDRAB");
    }

    public Rectangle getInsideHealthBar() {
        return insideHealthBar;
    }

    public Label getHealthText(double maxHealth) {
        this.maxHealth = (int)maxHealth;
        return healthText;
    }
    
    public void setHealth(double health) {
        insideHealthBar.setWidth((health/maxHealth)*width); // scales the bar to the player's health out of the starting health (maxHealth)
        healthText.setText("Health: " + (int)health + "/" + maxHealth);
    }
}
