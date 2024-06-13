/*
 * Purpose: Class that creates an alien entity. Also contains methods to move the alien and check for collisions
 * Author: Johnson Yep
 */

// Credit for image: https://www.freepik.com/premium-vector/alien-ufo-cartoon-illustration_49417092.htm

import java.util.ArrayList;
import java.util.List;

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

    public ImageView getImageView() {
        return alienImageView;
    }

    public void shoot(ImageView spaceship, int size, int speed, Pane root, List<Projectile> alienProjectiles) {
        // Set the target of the projectile to the middle of the player's spaceship
        double endX = spaceship.getLayoutX() + (spaceship.getLayoutBounds().getWidth() / 2);
        double endY = spaceship.getLayoutY() + (spaceship.getLayoutBounds().getHeight() / 2);
        
        double startX = alienImageView.getLayoutX() + (alienImageView.getLayoutBounds().getWidth() / 2); // gets center X
        double startY = alienImageView.getLayoutY() + (alienImageView.getLayoutBounds().getHeight() / 2); // gets center Y

        // Create new projectile and add it to the root so it can be displayed and the List so it can be accessed later to check for hits and to delete it
        Projectile projectile = new Projectile(startX, startY, endX, endY, size, speed, Paint.valueOf("Lime"), this);
        root.getChildren().add(projectile);
        alienProjectiles.add(projectile);
    }

    // same concept as the spaceship's checkCollisions method
    public void checkCollisions(List<Projectile> spaceshipProjectiles, Stats stats) {
        double alienWidth = alienImageView.getLayoutBounds().getWidth();
        double alienHeight = alienImageView.getLayoutBounds().getHeight();
        double alienX = alienImageView.getLayoutX();
        double alienY = alienImageView.getLayoutY();

        for (Projectile projectile : spaceshipProjectiles) {
            double X = projectile.getX();
            double Y = projectile.getY();
            double Width = projectile.getWidth();
            double Height = projectile.getHeight();
            
            // Checks any of the projectiles are in the spaceship's radius
            if ((X <= alienX+alienWidth && X+Width >= alienX) && (Y <= alienY+alienHeight && Y+Height >= alienY)) {
                if (projectile.getCanHit() == true) {
                    Spaceship spaceship = projectile.getSpaceship(); // gets the parent spaceship of the projectile so we can access how much damage it does
                    this.setHealth(this.getHealth() - spaceship.getDamage());
    
                    projectile.setCanHit(false); // stops projectile from hitting player after first hit

                    // Increase the damage done stat so it can be displayed in game over screen
                    stats.setDamageDone((int)(stats.getDamageDone() + spaceship.getDamage()));
                }
                
            }
        }
    }
}
