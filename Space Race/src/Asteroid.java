/*
 * Purpose: Class that manages instances of asteroids and contains method to move asteroid towards the left side of the screen
 * Author: Johnson Yep
 */
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

// Credit for image: https://stock.adobe.com/search?k=meteor+cartoon

public class Asteroid extends Entity {
    private ImageView asteroidImageView;
    private boolean canHit = true; // field that disables the asteroid from doing damage after it has already hit the player

    Asteroid(int size, Scene scene) {
        super("res\\Asteroid.png", size, size); // does what the spaceship constructor does but for asteroid.png
        asteroidImageView = new ImageView(this); // ImageView allows the image to be displayed to the user
        asteroidImageView.setLayoutX(scene.getWidth());
        asteroidImageView.setLayoutY((Math.random()*scene.getHeight()) - this.getHeight()); // spawns the asteroid at a random height on the screen

        // Setting fields from entity class
        this.setDamage(25);
    }
    
    public ImageView getAsteroidImageView() {
        return asteroidImageView;
    }

    public void moveAsteroid(int speed) {
        asteroidImageView.setLayoutX(asteroidImageView.getLayoutX() - speed); // moves the asteroid towards the left side of the screen
    }

    // GETTERS AND SETTERS

    public boolean getCanHit() {
        return canHit;
    }

    public void setCanHit(boolean canHit) {
        this.canHit = canHit;
    }
}
