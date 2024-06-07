/*
 * Purpose: Space Race is a game with the main objective of surviving in a space. The player is controlling a space ship that can shoot lasers and has to defeat enemies in order to survive. Each enemy will reward points when defeated, and players are able to purchase power ups with these points.
 * Author: Johnson
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Group;

public class SpaceRace extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    BackgroundGenerator bgGen = new BackgroundGenerator();

    @Override
    public void start(Stage stage) throws Exception {
        // Create object of stage
        stage.setTitle("Space Race - Intro");

        Scene introScene;

        // INTRO SCENE
        Pane introRoot = new Pane();

        Label welcome = new Label("Welcome To");
        GridPane.setConstraints(welcome, 0, 0);

        Label title = new Label("Space Race");
        GridPane.setConstraints(title, 0, 1);

        Button playButton = new Button("PLAY");
        GridPane.setConstraints(playButton, 0, 2);

        Label movementInstructions = new Label("Use W, A, S, D to move");
        movementInstructions.setStyle("-fx-font-size: 12pt");
        GridPane.setConstraints(movementInstructions, 0, 3);

        Label shootingInstructions = new Label("Left MB to shoot");
        shootingInstructions.setStyle("-fx-font-size: 12pt");
        GridPane.setConstraints(shootingInstructions, 0, 4);

        // Layout
        GridPane introMenuGrid = new GridPane();
        introMenuGrid.setVgap(10);

        // Center everything so it looks even
        GridPane.setHalignment(welcome, HPos.CENTER);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setHalignment(playButton, HPos.CENTER);
        GridPane.setHalignment(movementInstructions, HPos.CENTER);
        GridPane.setHalignment(shootingInstructions, HPos.CENTER);

        introScene = new Scene(introRoot, 400, 800);
        introScene.getStylesheets().add("introStyles.css"); // add the styles.css style sheet so it can be used by the
                                                            // scene

        // Generate stars for background
        Group stars = bgGen.drawStars(1, 100, (int) introScene.getWidth(), (int) introScene.getHeight());

        // Add objects to grid
        introMenuGrid.getChildren().addAll(welcome, title, playButton, movementInstructions, shootingInstructions); // adds
                                                                                                                    // all
                                                                                                                    // text
                                                                                                                    // and
                                                                                                                    // buttons
                                                                                                                    // to
                                                                                                                    // grid

        introRoot.getChildren().add(stars); // adds grid and stars to group, so that the stars don't become apart of the
        // grid and mis align everything else
        introRoot.getChildren().add(introMenuGrid);

        stage.setScene(introScene);
        stage.show();

        // The position of the introMenuGrid can only be calculated after the stage has
        // been shown due to how JavaFX is implemented
        introMenuGrid.setLayoutX((int) ((introRoot.getWidth() / 2) - (introMenuGrid.getWidth() / 2)));
        introMenuGrid.setLayoutY((int) ((introRoot.getHeight() / 2) - (introMenuGrid.getHeight() / 2)));

        // HANDLING BUTTON EVENT
        playButton.setOnAction(e -> { // once the user clicks the play button, it changes the scene to the main game
            Scene mainGameScene = initiateGame();
            stage.setScene(mainGameScene);
            stage.setFullScreen(true); // makes the stage full screen for game play

        });
    }

    public Scene initiateGame() {
        // MAIN GAME SCENE
        Pane mainGameRoot = new Pane();
        Scene mainGameScene = new Scene(mainGameRoot, 1920, 1080);
        mainGameScene.getStylesheets().add("mainGameStyles.css"); // add styles.css so all the UI designs/colors apply
                                                                  // to this scene as well
        Group stars = bgGen.drawStars(1, 500, (int) mainGameScene.getWidth(), (int) mainGameScene.getHeight());

        int size = 100;
        Spaceship spaceship = new Spaceship(size);
        ImageView ss = spaceship.spawnSpaceShip(50, (int) (mainGameScene.getHeight() / 2 - size / 2)); // makes the spaceship spawn in the center vertically, and 50 pixels out from 0 horizontally
        mainGameRoot.getChildren().addAll(ss, stars);

        // DETECT INPUTS FROM THIS SCENE
        mainGameScene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:
                    spaceship.moveUp();
                    break;
                case S:
                    spaceship.moveDown();
                    break;
                case D:
                    spaceship.moveRight();
                    break;
                case A:
                    spaceship.moveLeft();
                    break;
                default:
                    break;
            }
        });

        mainGameScene.setOnMouseClicked(e -> { // When the scene detects a mouse click, call the shoot method from the spaceship class
            spaceship.shoot(e.getX(), e.getY(), 5, 3, mainGameRoot);
        });

        AnimationTimer timer = new AnimationTimer() { // too lazy to put in a separate class so I implemented it inline

            @Override
            public void handle(long arg0) {
                spaceship.updateProjectiles(); // essentially loops to update the position of projectiles
            }

        };

        timer.start();

        return mainGameScene;
    }

}
