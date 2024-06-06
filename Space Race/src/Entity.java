/*
 * Purpose: Class that contains template values for entities in this game
 * Author: Johnson
 */

import javafx.scene.image.Image;

public class Entity extends Image {

    Entity() {
        super(null, 50, 50, true, true, true);
    }

    Entity(String image, double x, double y) { // passes these values to the Image class constructor
        super(image, x, y, true, true, true); // essentially
    }

    // private variables to prevent inadvertent direct changes to variables
    private double health, maxHealth, damage;

    // GETTERS AND SETTERS TO GET AND SET VALUES OF PRIVATE VARIABLES 
    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}