import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Spaceship extends Entity {
    public void spawnSpaceShip() {
        Image spaceship = new Image("res/Spaceship.png", 50, 50, true, true, true); // create object of an image of the spaceship
        ImageView ssImage = new ImageView(spaceship); // ImageView allows the image to be displayed to the user

        ssImage.setLayoutX(50);
        ssImage.setLayoutY(50);
    }
}