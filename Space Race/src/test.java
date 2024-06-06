import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class test extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();

        // Create a Canvas to draw on
        Canvas canvas = new Canvas(400, 400);
        // Think of this as getting a brush to draw on the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Drawing a Rectangle using the "brush" gc
        gc.setFill(Color.BEIGE);
        gc.fillRect(50, 60, 50, 50);

        // Creating my custom colour
        Color c = Color.rgb(255, 140, 0);
        // Drawing an Oval with color c
        gc.setFill(c);
        gc.fillOval(100, 180, 60, 60);

        // Create a new Image object and draw it on the canvas.
        Image img = new Image("res\\Spaceship.png");
        gc.drawImage(img, 250, 300, 40, 40);

        // Adding the canvas to the root node
        root.getChildren().add(canvas);
        // Adding the root node to a scene
        Scene scene = new Scene(root, 400, 400, Color.AQUA);
        // Adding the scene to the stage and displaying the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Canvas Test");
        primaryStage.show();

        for (int i = 0; i < 100; i++) {
            gc.drawImage(img, 250 + i, 300 + i, 40, 40);
            wait(10);
        }
    }
}
