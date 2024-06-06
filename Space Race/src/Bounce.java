import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Bounce extends Application {
    Ball ball1, ball2; // Creating two balls.
    Scene scene;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage window) throws Exception {
        // Create two balls. One blue and the other red.
        ball1 = new Ball(50, 60, 30, Color.BLUE);
        ball2 = new Ball(150, 160, 30, Color.RED);

        // Create the root node and add the balls.
        Group root = new Group(ball1, ball2);

        // Create the scene and add the root node
        scene = new Scene(root, 640, 480);

        // Add the scene to the window and show it.
        window.setScene(scene);
        window.show();

        // One way to detect if the mouse is touching the ball.
        ball1.setOnMouseEntered(e -> {
            System.out.println("You hit ball 1");
        });

        // Second way to check if the mouse is touching the ball.
        scene.setOnMouseMoved(e -> {
            if (ball2.contains(e.getX(), e.getY())) {
                System.out.println("You hit ball 2");
            }
        });

        // Set the timer and start it.
        MyTimer time = new MyTimer();
        time.start();
    }

    // Inner class MyTimer that creates the Animation Timer.
    private class MyTimer extends AnimationTimer {
        @Override
        public void handle(long now) {
            // Move both balls.
            ball1.move(scene.getWidth(), scene.getHeight());
            ball2.move(scene.getWidth(), scene.getHeight());

            // Check if the balls are colliding.
            if (ball1.intersects(ball2.getBoundsInLocal())) {
                System.out.println("Balls collided");
                ball1.bounce();
                ball2.bounce();
            }
        }
    }
}
