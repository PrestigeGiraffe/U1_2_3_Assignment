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

    /**
     * Creates projectile with default values so methods inside class can be used
     */
    Projectile() {}

    // Creates a new projectile and stores the values passed into the constructor as

    /**
     * Creates a spaceship projectile with set fields
     * @param startX start point X
     * @param startY start point Y
     * @param endX end point X
     * @param endY end point Y
     * @param size size of the projectile
     * @param speed speed of the projectile
     * @param color color of the projectile
     * @param spaceship spaceship that spawned the projectile
     */
    // 
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

    /**
     * Constructor for alien
     * @param startX start point X
     * @param startY start point Y
     * @param endX end point X
     * @param endY end point Y
     * @param size size of projectile
     * @param speed speed of projectile
     * @param color color of projectile
     * @param alien alien that spawned the projectile
     */
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

    /**
     * Moves projectile based on speed calculations in method above
     */
    public void move() {
        this.setX(this.getX() + speedX);
        this.setY(this.getY() + speedY);
    }

    /**
     * Loops through all the alien projectiles on the screen and updates their position
     * @param scene scene that the projectile will be in
     * @param root root that the projectile will be in
     * @param alienProjectiles List that the projectile will be stored in
     */
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
    /**
     * Returns canHit
     * @return
     */
    public boolean getCanHit() {
        return canHit;
    }

    /**
     * Sets canHit
     * @param canHit
     */
    public void setCanHit(boolean canHit) {
        this.canHit = canHit;
    }

    /**
     * Returns alien object so entity fields can be accessed
     * @return
     */
    public Alien getAlien() {
        return alien;
    }

    /**
     * Returns spaceship so entity fields can be accessed
     * @return
     */
    public Spaceship getSpaceship() {
        return spaceship;
    }
}
