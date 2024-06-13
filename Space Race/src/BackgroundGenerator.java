 /*
  * Purpose: Class that contains a method to draw stars as a background design element in a scene
  * Author: Johnson Yep
  */

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class BackgroundGenerator {
    public Group drawStars(int size, int amount, int width, int height) {
        Group root = new Group();

        for (int i = 0; i < amount; i++) {
            Circle star = new Circle(Math.random() * width, Math.random() * height, size, Paint.valueOf("WHITE")); // generates stars in random positions based on how big the users resolution is
            root.getChildren().add(star);
        }

        return root;
    }
}
