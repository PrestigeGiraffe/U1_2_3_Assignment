/*
 * Purpose: Class that creates a projectile, and contains methods to move the projectile
 * Author: Johnson Yep
 */

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Projectile extends Rectangle {
    // class variables
    private double speedX, speedY;
    private boolean canHit = true;
    private Alien alien = null; // Property used to find fields during hit detection
    private Spaceship spaceship = null;

    Projectile() {}

    // Creates a new projectile and stores the values passed into the constructor as

    // Constructor for spaceship
    Projectile(double startX, double startY, double endX, double endY, int size, int speed, Paint color, Spaceship spaceship) {
        super(startX, startY, size, size);
        
        // Get hypotenuse of the x and y values of the end point to calculate the right speed for the x value to be changed by so they can reach the same point at the same time if the x and y end points are different
        double totalX = endX - startX;
        double totalY = endY - startY;

        double hyp = Math.sqrt(Math.pow(totalX, 2) + Math.pow(totalY, 2)); // ratio for speed calculation
        speedX = (totalX / hyp) * speed;
        speedY = (totalY / hyp) * speed;
        this.setFill(color);
        this.spaceship = spaceship;
    }

    // Constructor for alien
    Projectile(double startX, double startY, double endX, double endY, int size, int speed, Paint color, Alien alien) {
        super(startX, startY, size, size);
        
        // Get hypotenuse of the x and y values of the end point to calculate the right speed for the x value to be changed by so they can reach the same point at the same time if the x and y end points are different
        double totalX = endX - startX;
        double totalY = endY - startY;

        double hyp = Math.sqrt(Math.pow(totalX, 2) + Math.pow(totalY, 2)); // ratio for speed calculation
        speedX = (totalX / hyp) * speed;
        speedY = (totalY / hyp) * speed;
        this.setFill(color);
        this.alien = alien;
    }

    public void move() {
        this.setX(this.getX() + speedX);
        this.setY(this.getY() + speedY);
    }

    // Loops through all the alien projectiles on the screen and updates their position
    public void updateProjectiles(Scene scene, Pane root, List<Projectile> alienProjectiles) {
        for (int i = 0; i < alienProjectiles.size(); i++) { // used a regular for loop instead of an enhanced for loop because it caused ConcurrentModificationExceptions when projectiles were being removed
            Projectile projectile = alienProjectiles.get(i);
            projectile.move();

            // Deletes the projectile from the layout and the list once it goes off the screen
            if (projectile.getX() > scene.getWidth() || projectile.getY() > scene.getHeight() || projectile.getX() < 0 || projectile.getY() < 0) {
                root.getChildren().remove(projectile);
                alienProjectiles.remove(projectile);
            }
        }
    }

    // GETTER AND SETTER
    public boolean getCanHit() {
        return canHit;
    }

    public void setCanHit(boolean canHit) {
        this.canHit = canHit;
    }

    public Alien getAlien() {
        return alien;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }
}
