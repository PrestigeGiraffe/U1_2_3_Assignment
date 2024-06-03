/*
 * Purpose: Space Race is a game with the main objective of surviving in a space. The player is controlling a space ship that can shoot lasers and has to defeat enemies in order to survive. Each enemy will reward points when defeated, and players are able to purchase power ups with these points.
 * Author: Johnson
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
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

        Scene intro, mainGame;

        // MAIN GAME SCENE
        GridPane layout = new GridPane();
        mainGame = new Scene(layout, 1440, 720);

        // Layout
        GridPane grid = new GridPane();
        grid.setVgap(5);

        // Intro Screen
        Label welcome = new Label("Welcome To");
        GridPane.setConstraints(welcome, 0, 0);

        Label title = new Label("Space Race");
        GridPane.setConstraints(title, 0, 1);
        
        Button playButton = new Button("PLAY");
        GridPane.setConstraints(playButton, 0, 2);

        playButton.setOnAction(e -> { // once the user clicks the play button, it changes the scene to the main game and makes the window full screen
            window.setScene(mainGame);
            window.setFullScreen(true);
        });

        // Center everything so it looks even
        GridPane.setHalignment(welcome, HPos.CENTER);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setHalignment(playButton, HPos.CENTER);



        intro = new Scene(grid, 300, 300);
        intro.getStylesheets().add("styles.css"); // add the styles.css style sheet so it can be used by the scene

        // Add objects to grid
        grid.getChildren().addAll(welcome, title, playButton);

        window.setScene(intro);
        window.show();

        

        
    }

    // public void drawStars(int size, int amount) {

    //     for (int i = 0; i < amount; i++) {
    //         Circle star = new Circle();
    //         star.setScaleX(size);
    //         star.setScaleY(size);
    //         star.setX(Math.random()*400);
    //         star.setY(Math.random()*300);
    //     }
    // }
}
