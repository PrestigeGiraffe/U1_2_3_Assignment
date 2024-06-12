/*
 * Purpose: Class that creates an alien entity. Also contains methods to move the alien and check for collisions
 * Author: Johnson
 */

// Credit for image: https://www.freepik.com/premium-vector/alien-ufo-cartoon-illustration_49417092.htm

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class Alien extends Entity {
    List<Projectile> alienProjectiles = new ArrayList<>(); // List of projectiles to keep track of them
    ImageView alienImageView;

    // Same logic as the spaceship's constructor
    public Alien(int size) {
        super("res\\Alien.png", size, size); // Create image of alien

        // Setting entity fields
        this.setDamage(10);
        this.setHealth(50);
    }

    // Same logic behind spawnSpaceship but just for Alien
    public ImageView spawnAlien(int x, int y) {
        alienImageView = new ImageView(this);
        alienImageView.setLayoutX(x);
        alienImageView.setLayoutY(y);
        return alienImageView;
    }

    public void shoot(ImageView spaceship, int size, int speed, Pane root, List<Projectile> alienProjectiles) {
        // Set the target of the projectile to the middle of the player's spaceship
        double endX = spaceship.getLayoutX() + (spaceship.getLayoutBounds().getWidth() / 2);
        double endY = spaceship.getLayoutY() + (spaceship.getLayoutBounds().getHeight() / 2);
        
        double startX = alienImageView.getLayoutX() + (alienImageView.getLayoutBounds().getWidth() / 2); // gets center X
        double startY = alienImageView.getLayoutY() + (alienImageView.getLayoutBounds().getHeight() / 2); // gets center Y
        Projectile projectile = new Projectile(startX, startY, endX, endY, size, speed, Paint.valueOf("Lime"));
        projectile.move();
        root.getChildren().add(projectile);
        alienProjectiles.add(projectile);
        alienProjectiles.add(projectile);
    }

    // Loops through all the projectiles on the screen and updates their position
    public void updateProjectiles(Scene scene, Pane root) {
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
}
