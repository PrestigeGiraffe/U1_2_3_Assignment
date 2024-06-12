/*
 * Purpose: Creates an image of a spaceship, handles movement using WASD, and creates instance of projectile when shooting
 * Author: Johnson Yep
 */

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

// Credit for image: https://www.vecteezy.com/vector-art/6101910-spaceship-cartoon-icon

public class Spaceship extends Entity {
    List<Projectile> projectiles = new ArrayList<>(); // List of projectiles to keep track of them
    ImageView spaceshipImageView;

    Spaceship(int size) {
        super("res\\Spaceship.png", size, size); // calls parent class' (Entity) constructor which then extends image to create an object of an image of spaceship.png

        // Setting entity fields
        this.setHealth(100);
        this.setDamage(10);
    }

    public void checkCollisions(List<Asteroid> asteroids) {
        double spaceshipWidth = spaceshipImageView.getLayoutBounds().getWidth();
        double spaceshipHeight = spaceshipImageView.getLayoutBounds().getHeight();
        double spaceshipX = spaceshipImageView.getLayoutX();
        double spaceshipY = spaceshipImageView.getLayoutY();

        for (Asteroid asteroid : asteroids) {
            double asteroidX = asteroid.getAsteroidImageView().getLayoutX();
            double asteroidY = asteroid.getAsteroidImageView().getLayoutY();
            double asteroidWidth = asteroid.getAsteroidImageView().getLayoutBounds().getWidth();
            double asteroidHeight = asteroid.getAsteroidImageView().getLayoutBounds().getHeight();
            
            // (asteroidX+asteroidWidth <= spaceshipX && asteroidX >= spaceshipWidth+spaceshipX) && (asteroidY >= spaceshipY && asteroidY <= spaceshipHeight+spaceshipY)
            // Checks any of the asteroids are in the 
            if ((asteroidX <= spaceshipX+spaceshipWidth && asteroidX+asteroidWidth >= spaceshipX) && (asteroidY <= spaceshipY+spaceshipHeight && asteroidY+asteroidHeight >= spaceshipY)) {
                if (asteroid.getCanHit() == true) {
                    this.setHealth(this.getHealth() - asteroid.getDamage());
                    asteroid.setCanHit(false); // stops asteroid from hitting player after first hit
                }
                
            }
        }

        for (Asteroid asteroid : asteroids) {
            double asteroidX = asteroid.getAsteroidImageView().getLayoutX();
            double asteroidY = asteroid.getAsteroidImageView().getLayoutY();
            double asteroidWidth = asteroid.getAsteroidImageView().getLayoutBounds().getWidth();
            double asteroidHeight = asteroid.getAsteroidImageView().getLayoutBounds().getHeight();
            
            // (asteroidX+asteroidWidth <= spaceshipX && asteroidX >= spaceshipWidth+spaceshipX) && (asteroidY >= spaceshipY && asteroidY <= spaceshipHeight+spaceshipY)
            // Checks any of the asteroids are in the 
            if ((asteroidX <= spaceshipX+spaceshipWidth && asteroidX+asteroidWidth >= spaceshipX) && (asteroidY <= spaceshipY+spaceshipHeight && asteroidY+asteroidHeight >= spaceshipY)) {
                if (asteroid.getCanHit() == true) {
                    this.setHealth(this.getHealth() - asteroid.getDamage());
                    asteroid.setCanHit(false); // stops asteroid from hitting player after first hit
                }
                
            }
        }
    }

    public ImageView spawnSpaceShip(int x, int y) {
        spaceshipImageView = new ImageView(this); // ImageView allows the image to be displayed to the user
        spaceshipImageView.setLayoutX(x);
        spaceshipImageView.setLayoutY(y);
        return spaceshipImageView; // returns the ImageView so it can be displayed on the screen and so we can manipulate the position of it
    }

    public void shoot(double endX, double endY, int size, int speed, Pane root) {
        double startX = spaceshipImageView.getLayoutX() + (spaceshipImageView.getLayoutBounds().getWidth() / 2); // gets center X
        double startY = spaceshipImageView.getLayoutY() + (spaceshipImageView.getLayoutBounds().getHeight() / 2); // gets center Y
        Projectile projectile = new Projectile(startX, startY, endX, endY, size, speed, Paint.valueOf("Yellow"));
        projectile.move();
        root.getChildren().add(projectile);
        projectiles.add(projectile);
    }

    // Loops through all the projectiles on the screen and updates their position
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

    // HANDLE USER INPUTS

    public void moveUp() {
        spaceshipImageView.setLayoutY(spaceshipImageView.getLayoutY() - 10);
    }

    public void moveDown() {
        spaceshipImageView.setLayoutY(spaceshipImageView.getLayoutY() + 10);
    }

    public void moveRight() {
        spaceshipImageView.setLayoutX(spaceshipImageView.getLayoutX() + 10);
    }

    public void moveLeft() {
        spaceshipImageView.setLayoutX(spaceshipImageView.getLayoutX() - 10);
    }
}