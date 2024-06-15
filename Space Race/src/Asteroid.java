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

    /**
     * Creates an asteroid
     * @param size size of asteroid
     * @param scene scene that the asteroid will be in
     */
    Asteroid(int size, Scene scene) {
        super("res\\Asteroid.png", size, size); // does what the spaceship constructor does but for asteroid.png
        asteroidImageView = new ImageView(this); // ImageView allows the image to be displayed to the user
        asteroidImageView.setLayoutX(scene.getWidth());
        asteroidImageView.setLayoutY((Math.random()*scene.getHeight()) - this.getHeight()); // spawns the asteroid at a random height on the screen

        // Setting fields from entity class
        this.setDamage(25);
    }
    
    /**
     * Returns ImageView of asteroid
     * @return
     */
    public ImageView getAsteroidImageView() {
        return asteroidImageView;
    }

    /**
     * Moves asteroid towards the left side of the screen
     * @param speed
     */
    public void moveAsteroid(int speed) {
        asteroidImageView.setLayoutX(asteroidImageView.getLayoutX() - speed); // moves the asteroid towards the left side of the screen
    }

    // GETTERS AND SETTERS
    /**
     * Returns canHit value
     * @return
     */
    public boolean getCanHit() {
        return canHit;
    }

    /**
     * Sets canHit value
     * @param canHit
     */
    public void setCanHit(boolean canHit) {
        this.canHit = canHit;
    }
}
