import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SpaceRace extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create object of stage
        Stage window = primaryStage;
        window.setTitle("Space Race - Intro");

        // Layout
        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(20);

        // Intro Screen
        Label welcome = new Label("Welcome To");
        Label title = new Label("Space Race");
        Scene intro = new Scene(null);

        window.setScene(intro);
        window.show();
    }
}
