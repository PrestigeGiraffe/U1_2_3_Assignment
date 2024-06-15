/*
 * Purpose: Class that contains template values for entities in this game
 * Author: Johnson Yep
 */

import javafx.scene.image.Image;

public class Entity extends Image {
    // private variables to prevent inadvertent direct changes to variables
    private double health, damage;

    /**
     * Creates an entity
     * @param image image of the entity
     * @param x width
     * @param y height
     */
    Entity(String image, double x, double y) { // Second constructor 
        super(image, x, y, true, true, true);  // passes these values to the Image class constructor
    }

    // GETTERS AND SETTERS TO GET AND SET VALUES OF PRIVATE VARIABLES
    
    /**
     * Returns health of entity
     * @return
     */
    public double getHealth() {
        return health;
    }

    /**
     * Sets health of entity
     * @param health
     */
    public void setHealth(double health) {
        this.health = health;
    }

    /**
     * Returns damage of entity
     * @return
     */
    public double getDamage() {
        return damage;
    }

    /**
     * Sets damage of entity
     * @param damage
     */
    public void setDamage(double damage) {
        this.damage = damage;
    }
}