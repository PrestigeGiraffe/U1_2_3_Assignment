/*
 * Purpose: Class that contains template values for entities in this game
 * Author: Johnson
 */

public class Entity {
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