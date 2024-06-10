/*
 * Purpose: Space Race is a game with the main objective of surviving in a space. The player is controlling a space ship that can shoot lasers and has to defeat enemies in order to survive. Each enemy will reward points when defeated, and players are able to purchase power ups with these points.
 * Author: Johnson Yep
 */

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;

public class SpaceRace extends Application {
    Stage stage;
    public static void main(String[] args) {
        launch(args);
    }

    BackgroundGenerator bgGen = new BackgroundGenerator();

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        
        stage.setTitle("Space Race");

        // Scene and layout
        Pane introRoot = new Pane(); // Pane layout to implement background stars without disorganizing everything in the grid

        // Grid layout for text and buttons
        GridPane introMenuGrid = new GridPane();
        introMenuGrid.setVgap(10);

        ArrayList<Node> gridElements = new ArrayList<>(); // ArrayList to store elements in the grid so a loop can be used to add it to center them and to add them to the GridPane

        Label welcome = new Label("Welcome To");
        GridPane.setConstraints(welcome, 0, 0);
        gridElements.add(welcome);

        Label title = new Label("Space Race");
        GridPane.setConstraints(title, 0, 1);
        gridElements.add(title);

        Button playButton = new Button("PLAY");
        playButton.setStyle("-fx-background-color: lime");
        GridPane.setConstraints(playButton, 0, 2);
        gridElements.add(playButton);

        Label movementInstructions = new Label("Use W, A, S, D to move");
        movementInstructions.setStyle("-fx-font-size: 12pt");
        GridPane.setConstraints(movementInstructions, 0, 3);
        gridElements.add(movementInstructions);

        Label shootingInstructions = new Label("Left MB to shoot");
        shootingInstructions.setStyle("-fx-font-size: 12pt");
        GridPane.setConstraints(shootingInstructions, 0, 4);
        gridElements.add(shootingInstructions);

        Label objective1 = new Label("The objective of this game is to");
        objective1.setStyle("-fx-font-size: 12pt");
        GridPane.setConstraints(objective1, 0, 5);
        gridElements.add(objective1);

        Label objective2 = new Label("dodge asteroids, shoot aliens, and");
        objective2.setStyle("-fx-font-size: 12pt");
        GridPane.setConstraints(objective2, 0, 6);
        gridElements.add(objective2);

        Label objective3 = new Label("SURVIVE");
        objective3.setStyle("-fx-font-size: 20pt");
        objective3.setStyle("-fx-text-fill: red");
        GridPane.setConstraints(objective3, 0, 7);
        gridElements.add(objective3);

        // Loop to center everything and add them to the grid layout
        for(Node element : gridElements) {
            GridPane.setHalignment(element, HPos.CENTER);
            introMenuGrid.getChildren().add(element);
        }

        Scene introScene = new Scene(introRoot, 400, 800);
        introScene.getStylesheets().add("textStyles.css"); // add the styles.css style sheet so it can be used by the scene

        // Generate stars for background using the BackgroundGenerator Class
        Group stars = bgGen.drawStars(1, 100, (int) introScene.getWidth(), (int) introScene.getHeight());

        // Add the grid layout and stars to pane, because if the stars were apart of the grid they would mis-align everything else
        introRoot.getChildren().add(stars); 
        introRoot.getChildren().add(introMenuGrid);

        stage.setScene(introScene);
        stage.show();

        // The position of the introMenuGrid can only be calculated after the stage has
        // been shown due to how JavaFX is implemented
        introMenuGrid.setLayoutX((int) ((introRoot.getWidth() / 2) - (introMenuGrid.getWidth() / 2)));
        introMenuGrid.setLayoutY((int) ((introRoot.getHeight() / 2) - (introMenuGrid.getHeight() / 2)));

        // HANDLING BUTTON EVENT
        playButton.setOnAction(e -> { // once the user clicks the play button, it changes the scene to the main game
            initiateGame();
        });
    }

    // Method to handle everything that happens in the actual game play scene
    public Scene initiateGame() {
        // MAIN GAME SCENE
        Pane mainGameRoot = new Pane();
        Scene mainGameScene = new Scene(mainGameRoot, 1920, 1080);
        mainGameScene.getStylesheets().add("textStyles.css"); // add styles.css so all the UI designs/colors apply

        // Set stage
        stage.setScene(mainGameScene);
        stage.setFullScreen(true); // makes the stage full screen for game play

        // background stars
        Group stars = bgGen.drawStars(1, 500, (int) mainGameScene.getWidth(), (int) mainGameScene.getHeight());

        // object of spaceship
        int spaceshipSize = 100;
        Spaceship spaceship = new Spaceship(spaceshipSize);
        ImageView ss = spaceship.spawnSpaceShip(50, (int) (mainGameScene.getHeight() / 2 - spaceshipSize / 2)); // makes the spaceship spawn in the center vertically, and 50 pixels out from 0 horizontally
        
        // health bar
        HealthBar health = new HealthBar(25, 25, 350, 30);
        Rectangle insideHealthBar = health.getInsideHealthBar();
        Label healthText = health.getHealthText(spaceship.getHealth());

        mainGameRoot.getChildren().addAll(ss, stars, health, insideHealthBar, healthText);

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

        Score scores = new Score();

        List<Asteroid> asteroids = new ArrayList<>(); // List to store asteroids so we can loop through it to move existing asteroids (same logic as projectiles)
        AnimationTimer timer = new AnimationTimer() { // too lazy to put in a separate class so I implemented it inline
            long asteroidStartTime = System.currentTimeMillis(); // variable to determine time elapsed
            long timeSurvivedStartTime = System.currentTimeMillis();

            @Override
            public void handle(long arg0) {
                spaceship.updateProjectiles(mainGameScene, mainGameRoot); // essentially loops to update the position of projectiles
                updateAsteroids(mainGameScene, mainGameRoot, asteroids, 5); // same logic as projectiles
                spaceship.checkCollisions(asteroids); // loops to check if the spaceship has touched anything that deals damage to it

                health.setHealth(spaceship.getHealth());

                // subtracts the elapsed time between when the timer first starts and now, if it is 3 seconds (3000ms) then reset the startTime and spawn an asteroid
                if (System.currentTimeMillis() - asteroidStartTime >= 3000) {
                asteroidStartTime = System.currentTimeMillis();
                    Asteroid asteroid = new Asteroid(300, mainGameScene);
                    asteroids.add(asteroid); // add the new object to the list
                    mainGameRoot.getChildren().add(asteroid.getAsteroidImageView());
                }

                if (System.currentTimeMillis() - timeSurvivedStartTime >= 1000) { // every second increase the time survived
                    scores.setTimeSurvived(scores.getTimeSurvived() + 1);
                }

                // Health manager
                if (spaceship.getHealth() <= 0) {
                    this.stop();
                    initiateGameOver();
                }
            }

        };

        timer.start();

        return mainGameScene;
    }

    public void initiateGameOver() {
        // Grid layout for text and buttons
        GridPane gameOverGrid = new GridPane();
        gameOverGrid.setVgap(10);

        ArrayList<Node> gridElements = new ArrayList<>(); // ArrayList to store elements in the grid so a loop can be used to add it to center them and to add them to the GridPane

        Label gameOver = new Label("GAME OVER");
        gameOver.setStyle("-fx-font-size: 20pt");
        gameOver.setStyle("-fx-text-fill: red");
        GridPane.setConstraints(gameOver, 0, 0);
        gridElements.add(gameOver);

        Label highScores = new Label("High Scores: ");
        highScores.setStyle("-fx-font-size: 12pt");
        GridPane.setConstraints(highScores, 0, 1);
        gridElements.add(highScores);

        // Loop to center everything and add them to the grid layout
        for(Node element : gridElements) {
            GridPane.setHalignment(element, HPos.CENTER);
            gameOverGrid.getChildren().add(element);
        }

        Scene gameOverScene = new Scene(gameOverGrid, 400, 800);
        gameOverScene.getStylesheets().add("textStyles.css"); // add the styles.css style sheet so it can be used by the scene
        stage.setScene(gameOverScene);
    }

    // Basically the updateProjectiles method but for asteroids
    public void updateAsteroids(Scene scene, Pane root, List<Asteroid> asteroids, int speed) {
        for (int i = 0; i < asteroids.size(); i++) { // used a regular for loop instead of an enhanced for loop because it caused ConcurrentModificationExceptions when Asteroids were being removed
            Asteroid asteroid = asteroids.get(i);
            ImageView asteroidImageView = asteroid.getAsteroidImageView();

            asteroid.moveAsteroid(speed);

            // Deletes the asteroid from the layout and the list once it goes off the screen on the left
            if (asteroidImageView.getLayoutX() < (-asteroidImageView.getLayoutX()) || asteroidImageView.getLayoutY() < (-asteroidImageView.getLayoutY())) {
                root.getChildren().remove(asteroidImageView);
                asteroids.remove(asteroid);
            }
        }
    }

}
