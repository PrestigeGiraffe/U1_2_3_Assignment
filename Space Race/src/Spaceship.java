/*
 * Purpose: Creates an image of a spaceship, handles movement using W,A,S,D and creates instance of projectile when shooting
 * Author: Johnson Yep
 */

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

// Credit for image: https://www.vecteezy.com/vector-art/6101910-spaceship-cartoon-icon

public class Spaceship extends Entity {
    private int speed = 2; // speed of projectiles
    List<Projectile> projectiles = new ArrayList<>(); // List of projectiles to keep track of them
    ImageView spaceshipImageView;

    /**
     * Creates spaceship entity
     * @param size size of the spaceship
     */
    Spaceship(int size) {
        super(Paths.get("res", "Spaceship.png").toString(), size, size); // calls parent class' (Entity) constructor which then extends image to create an object of an image of spaceship.png

        // Setting entity fields
        this.setHealth(100);
        this.setDamage(10);
    }

    /**
     * Checks if anything that can deal damage has collided with the spaceship
     * @param asteroids list of asteroids that can do damage
     * @param alienProjectiles list of alien projectiles that can do damage
     */
    public void checkCollisions(List<Asteroid> asteroids, List<Projectile> alienProjectiles) {
        // Variables of the spaceship's dimensions and location so it can be used to check collisions
        double spaceshipWidth = spaceshipImageView.getLayoutBounds().getWidth();
        double spaceshipHeight = spaceshipImageView.getLayoutBounds().getHeight();
        double spaceshipX = spaceshipImageView.getLayoutX();
        double spaceshipY = spaceshipImageView.getLayoutY();

        for (Asteroid asteroid : asteroids) {
            // Variables of asteroid dimensions and locations
            double X = asteroid.getAsteroidImageView().getLayoutX();
            double Y = asteroid.getAsteroidImageView().getLayoutY();
            double Width = asteroid.getAsteroidImageView().getLayoutBounds().getWidth();
            double Height = asteroid.getAsteroidImageView().getLayoutBounds().getHeight();
            
            // Checks any of the asteroids are in the spaceship's radius
            if ((X <= spaceshipX+spaceshipWidth && X+Width >= spaceshipX) && (Y <= spaceshipY+spaceshipHeight && Y+Height >= spaceshipY)) {
                if (asteroid.getCanHit() == true) {
                    this.setHealth(this.getHealth() - asteroid.getDamage()); // damages player
                    asteroid.setCanHit(false); // stops asteroid from hitting player after first hit
                }
                
            }
        }

        for (Projectile projectile : alienProjectiles) {
            // Variables of projectile dimensions and locations
            double X = projectile.getX();
            double Y = projectile.getY();
            double Width = projectile.getWidth();
            double Height = projectile.getHeight();
            
            // Checks any of the projectiles are in the spaceship's radius
            if ((X <= spaceshipX+spaceshipWidth && X+Width >= spaceshipX) && (Y <= spaceshipY+spaceshipHeight && Y+Height >= spaceshipY)) {
                if (projectile.getCanHit() == true) {
                    Alien alien = projectile.getAlien(); // gets the parent alien of the projectile so we can access how much damage it does
                    this.setHealth(this.getHealth() - alien.getDamage()); // damages player
                    projectile.setCanHit(false); // stops projectile from hitting player after first hit
                }
                
            }
        }
    }

    /**
     * Returns the ImageView of the spaceship so it can be displayed
     * @param x x coordinate of spaceship
     * @param y y coordinate of spaceship
     * @return
     */
    public ImageView spawnSpaceShip(int x, int y) {
        spaceshipImageView = new ImageView(this); // ImageView allows the image to be displayed to the user
        spaceshipImageView.setLayoutX(x);
        spaceshipImageView.setLayoutY(y);
        return spaceshipImageView; // returns the ImageView so it can be displayed on the screen and so we can manipulate the position of it
    }

    /**
     * shoots projectile from the center of the spaceship
     * @param endX end X point of the projectile
     * @param endY end Y point of the projectile
     * @param size size of the projectile
     * @param speed speed of the projectile
     * @param root root that the projectile will be added to
     */
    public void shoot(double endX, double endY, int size, int speed, Pane root) {
        double startX = spaceshipImageView.getLayoutX() + (spaceshipImageView.getLayoutBounds().getWidth() / 2); // gets center X
        double startY = spaceshipImageView.getLayoutY() + (spaceshipImageView.getLayoutBounds().getHeight() / 2); // gets center Y

        // Creates a projectile from the center of the spaceship and adds it to the root so it can be displayed and list so the projectiles can be handled later
        Projectile projectile = new Projectile(startX, startY, endX, endY, size, speed, Paint.valueOf("Yellow"), this);
        root.getChildren().add(projectile);
        projectiles.add(projectile);
    }

    /**
     * Loops through all the projectiles on the screen and updates their position
     * @param scene scene that the projectile is in
     * @param root root that the projectile is in
     */
    public void updateProjectiles(Scene scene, Pane root) {
        for (int i = 0; i < projectiles.size(); i++) { // used a regular for loop instead of an enhanced for loop because it caused ConcurrentModificationExceptions when projectiles were being removed
            Projectile projectile = projectiles.get(i);
            projectile.move();

            // Deletes the projectile from the layout and the list once it goes off the screen
            if (projectile.getX() > scene.getWidth() || projectile.getY() > scene.getHeight() || projectile.getX() < 0 || projectile.getY() < 0) {
                root.getChildren().remove(projectile);
                projectiles.remove(projectile);
            }
        }
    }

    /**
     * Returns the list of projectiles
     * @return
     */
    public List<Projectile> getProjectiles() { // returns the list of projectiles so it can be used to check if any of the projectiles hit an alien
        return projectiles;
    }

    // HANDLE USER INPUTS
    
    /**
     * Move the spaceship up
     */
    public void moveUp() {
        double nextLocation = spaceshipImageView.getLayoutY() - speed;
        if (nextLocation >= 0) { // check if the spaceship is going to go off the screen
            spaceshipImageView.setLayoutY(nextLocation);
            spaceshipImageView.setRotate(0); // set rotate to make the spaceship face the direction it is flying, same logic for all the other move methods
        }
    }

    /**
     * Move the spaceship down
     * @param sceneHeight height of the scene to get boundaries
     */
    public void moveDown(double sceneHeight) {
        double nextLocation = spaceshipImageView.getLayoutY() + speed;
        if (nextLocation <= sceneHeight - this.getHeight()) { // check if the spaceship is going to go off the screen, needs getHeight because the reference point is the top left
            spaceshipImageView.setLayoutY(nextLocation);
            spaceshipImageView.setRotate(180);
        }
    }

    /**
     * Move the spaceship right
     * @param sceneWidth width of the scene to get boundaries
     */
    public void moveRight(double sceneWidth) {
        double nextLocation = spaceshipImageView.getLayoutX() + speed;
        if (nextLocation <= sceneWidth - this.getWidth()) { // check if the spaceship is going to go off the screen, needs getWidth for the same reason
            spaceshipImageView.setLayoutX(nextLocation);
            spaceshipImageView.setRotate(90);
        }
    }

    /**
     * Move the spaceship down
     */
    public void moveLeft() {
        double nextLocation = spaceshipImageView.getLayoutX() - speed;
        if (nextLocation >= 0) { // check if the spaceship is going to go off the screen
            spaceshipImageView.setLayoutX(nextLocation);
            spaceshipImageView.setRotate(270);
        }
    }
}