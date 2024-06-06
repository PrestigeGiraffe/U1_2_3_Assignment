/*
 * Purpose: Creates an image of a spaceship, handles movement using WASD, and creates instance of projectile when shooting
 * Author: Johnson Yep
 */

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Spaceship extends Entity {
    List<Projectile> projectiles = new ArrayList<>(); // List of projectiles to keep track of them

    Spaceship(int size) {
        super("res\\Spaceship.png", size, size); // calls parent class' (Entity) constructor
    }

    ImageView ssImage;

    public ImageView spawnSpaceShip(int x, int y) {
        ssImage = new ImageView(this); // ImageView allows the image to be displayed to the user

        ssImage.setLayoutX(x);
        ssImage.setLayoutY(y);

        return ssImage;
    }

    // HANDLE USER INPUTS

    public void moveUp() {
        ssImage.setLayoutY(ssImage.getLayoutY()-10);
    }
    public void moveDown() {
        ssImage.setLayoutY(ssImage.getLayoutY()+10);
    }
    public void moveRight() {
        ssImage.setLayoutX(ssImage.getLayoutX()+10);
    }
    public void moveLeft() {
        ssImage.setLayoutX(ssImage.getLayoutX()-10);
    }

    public void shoot(double endX, double endY, int size, int speed, Pane root) {
        double startX = ssImage.getLayoutX() + (ssImage.getScaleX()/2); // gets center X
        double startY = ssImage.getLayoutY() + (ssImage.getScaleY()/2); // gets center Y
        Projectile projectile = new Projectile(startX + ssImage.getLayoutBounds().getWidth()/2, startY, endX, endY, size, speed);
        projectile.move();
        root.getChildren().add(projectile);
        projectiles.add(projectile);
    }

    // Loops through all the projectiles on the screen and updates their position
    public void updateProjectiles() {
        for (Projectile projectile : projectiles) {
            projectile.move();
        }
    }
}