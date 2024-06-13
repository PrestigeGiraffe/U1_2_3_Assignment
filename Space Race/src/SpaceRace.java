/*
 * Purpose: Space Race is a game with the main objective of surviving in outer space. The player is controlling a space ship that can shoot lasers and has to defeat aliens and dodge asteroids in order to survive.
 * Author: Johnson Yep
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;

public class SpaceRace extends Application {
    BackgroundGenerator bgGen = new BackgroundGenerator();
    Stage stage;
    Stats stats = new Stats();
    // The reason I also used keycode instead of only event.setOnKeyPressed because it had a delay between when you first press it and when it starts to continuously move it, since it detects key inputs and doesn't detect if it is held down (e.g. if you hold down any letter on your keyboard, it will take half a second before it spams the letter), which made it hard to dodge the asteroids that were coming at you
    Map<KeyCode, Boolean> keyStates = new HashMap<>(); // From https://docs.oracle.com/javase/8/docs/api/java/util/Map.html, simply keeps track of keys that have been pressed

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage; // sets the stage variable to the stage that is made in the start method so other methods can access the stage
        
        stage.setTitle("Space Race");

        Pane introRoot = new Pane(); // Pane layout to implement background stars without disorganizing everything in the grid

        // Grid layout for text and buttons
        GridPane introMenuGrid = new GridPane();
        introMenuGrid.setVgap(10); // set the spacing between the grid elements

        ArrayList<Node> gridElements = new ArrayList<>(); // ArrayList to store elements in the grid so a loop can be used to add it to center them and to add them to the GridPane

        // Creating the grid elements and adding them to the gridElements ArrayList

        Label title = new Label("Space Race");
        title.setStyle("-fx-text-fill: AQUA;"+"-fx-font-size: 35pt;");
        GridPane.setConstraints(title, 0, 0);
        gridElements.add(title);

        Label objective1 = new Label("The objective of this game is to");
        GridPane.setConstraints(objective1, 0, 1);
        gridElements.add(objective1);

        Label objective2 = new Label("dodge asteroids, shoot aliens, and");
        GridPane.setConstraints(objective2, 0, 2);
        gridElements.add(objective2);

        Label objective3 = new Label("SURVIVE");
        objective3.setStyle("-fx-text-fill: red;");
        GridPane.setConstraints(objective3, 0, 3);
        gridElements.add(objective3);

        Label controlsTitle = new Label("Controls");
        controlsTitle.setStyle("-fx-font-size: 25pt;" + "-fx-text-fill: yellow;");
        GridPane.setConstraints(controlsTitle, 0, 4);
        gridElements.add(controlsTitle);

        Label movementInstructions = new Label("- W, A, S, D to move");
        GridPane.setConstraints(movementInstructions, 0, 5);
        gridElements.add(movementInstructions);

        Label shootingInstructions = new Label("- Left Click to shoot");
        GridPane.setConstraints(shootingInstructions, 0, 6);
        gridElements.add(shootingInstructions);
        
        Button playButton = new Button("PLAY");
        GridPane.setConstraints(playButton, 0, 7);
        gridElements.add(playButton);

        // HANDLING BUTTON EVENT
        playButton.setOnAction(e -> initiateGame()); // once the user clicks the play button, it calls a method that changes the scene to the main game

        // Loop to center everything and add them to the grid layout and to center them
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

        // The position of the introMenuGrid can only be calculated after the stage has been shown due to how JavaFX is implemented
        introMenuGrid.setLayoutX((int) ((introRoot.getWidth() / 2) - (introMenuGrid.getWidth() / 2)));
        introMenuGrid.setLayoutY((int) ((introRoot.getHeight() / 2) - (introMenuGrid.getHeight() / 2)));
    }

    // Method to handle everything that happens in the actual game play scene
    public Scene initiateGame() {
        Pane mainGameRoot = new Pane();
        Scene mainGameScene = new Scene(mainGameRoot, 1920, 1080);
        mainGameScene.getStylesheets().add("textStyles.css"); // add styles.css so all the UI designs/colors apply

        stage.setScene(mainGameScene);
        stage.setFullScreen(true); // makes the stage full screen for game play

        // background stars
        Group stars = bgGen.drawStars(1, 500, (int) mainGameScene.getWidth(), (int) mainGameScene.getHeight());

        // object of spaceship
        int spaceshipSize = 100;
        Spaceship spaceship = new Spaceship(spaceshipSize);
        ImageView spaceshipImageView = spaceship.spawnSpaceShip(50, (int) (mainGameScene.getHeight() / 2 - spaceshipSize / 2)); // makes the spaceship spawn in the center vertically, and 50 pixels out from 0 horizontally
        
        // health bar for the player's spaceship
        HealthBar health = new HealthBar(25, 25, 350, 30);
        Rectangle insideHealthBar = health.getInsideHealthBar();
        Label healthText = health.getHealthText(spaceship.getHealth());

        mainGameRoot.getChildren().addAll(spaceshipImageView, stars, health, insideHealthBar, healthText); // add all these to a Pane, so it can be displayed in the scene

        // DETECT INPUTS FROM THIS SCENE
        mainGameScene.setOnKeyPressed(e -> {
            keyStates.put(e.getCode(), true);
        });

        mainGameScene.setOnKeyReleased(e -> {
            keyStates.put(e.getCode(), false);
        });

        mainGameScene.setOnMouseClicked(e -> { // When the scene detects a mouse click, call the shoot method from the spaceship class
            spaceship.shoot(e.getX(), e.getY(), 5, 3, mainGameRoot);
        });


        List<Asteroid> asteroids = new ArrayList<>(); // List to store asteroids so w4e can loop through it to move existing asteroids (same logic as projectiles)
        List<Alien> aliens = new ArrayList<>(); // List to store aliens so we can loop through to have each of them shoot at the player
        List<Projectile> alienProjectiles = new ArrayList<>(); // List of alien projectiles to keep track of them and update them
        Projectile projectile = new Projectile();

        AnimationTimer timer = new AnimationTimer() {
            // variables to determine time elapsed
            // From: https://www.tutorialspoint.com/java/lang/system_currenttimemillis.htm. All this method does is return the amount of time that has passed since January 1, 1970
            long asteroidSpawnTime = System.currentTimeMillis(); 
            long timeSurvivedStartTime = System.currentTimeMillis();
            long alienSpawnTime = System.currentTimeMillis();
            long alienShootTime = System.currentTimeMillis();

            @Override
            public void handle(long arg0) {
                // We use a map instead of catching the event directly because of how javafx handles keys being held, which made movement sluggish
                // This essentially loops through to see which keys are press down, and if W,A,S,D are pressed down then call the corresponding method to move the spaceship
                if (keyStates.getOrDefault(KeyCode.W, false)) {
                    spaceship.moveUp();
                }
                if (keyStates.getOrDefault(KeyCode.S, false)) {
                    spaceship.moveDown(mainGameScene.getHeight());
                }
                if (keyStates.getOrDefault(KeyCode.D, false)) {
                    spaceship.moveRight(mainGameScene.getWidth());
                }
                if (keyStates.getOrDefault(KeyCode.A, false)) {
                    spaceship.moveLeft();
                }

                spaceship.updateProjectiles(mainGameScene, mainGameRoot); // essentially loops to update the position of projectiles
                projectile.updateProjectiles(mainGameScene, mainGameRoot, alienProjectiles); // same logic but for alien projectiles
                updateAsteroids(mainGameScene, mainGameRoot, asteroids, 5); // same logic as projectiles
                spaceship.checkCollisions(asteroids, alienProjectiles); // loops to check if the spaceship has touched anything that deals damage to it
                health.setHealth(spaceship.getHealth()); // sets the max health of the spaceship (starting health)

                // subtracts the elapsed time between when the timer first starts and now, if it is a time between 1-4 seconds (1000-4000ms) then reset the startTime and spawn an asteroid
                if (System.currentTimeMillis() - asteroidSpawnTime >= 1000 + (Math.random()*2000)) {
                    asteroidSpawnTime = System.currentTimeMillis(); // reset the start time or else it will be greater than the condition in the if statement and infinitely loop
                    Asteroid asteroid = new Asteroid(200 + (int)(Math.random()*200), mainGameScene); // Spawns asteroid at a random size between 200-400 pixels
                    asteroids.add(asteroid); // add the new object to the list
                    mainGameRoot.getChildren().add(asteroid.getAsteroidImageView()); // add to root so it can be displayed on scene
                }

                if (System.currentTimeMillis() - timeSurvivedStartTime >= 1000) { // every second increase the time survived
                    timeSurvivedStartTime = System.currentTimeMillis();
                    stats.setTimeSurvived(stats.getTimeSurvived() + 1);
                }

                if (System.currentTimeMillis() - alienSpawnTime >= 10000) { // every 10 seconds, spawn a new alien
                    alienSpawnTime = System.currentTimeMillis();
                    int alienSize = 200;
                    Alien alien = new Alien(alienSize);
                    aliens.add(alien); // add alien object to list

                    // Spawns alien anywhere on the right half of the screen
                    ImageView alienImageView = alien.spawnAlien((int)((mainGameScene.getWidth() / 2) + (Math.random()*(mainGameScene.getWidth() / 2))), (int)(Math.random()*(mainGameScene.getHeight() - alienSize)));
                    mainGameRoot.getChildren().add(alienImageView);
                }

                // Loop to handle alien 
                for (int i = 0; i < aliens.size(); i++) { // regular loop instead of enhanced loop for the same reason as the updateAsteroids method
                    Alien alien = aliens.get(i);

                    if (System.currentTimeMillis() - alienShootTime >= 2000) { // every two seconds, make aliens shoot
                        alienShootTime = System.currentTimeMillis();
                        alien.shoot(spaceshipImageView, 5, 3, mainGameRoot, alienProjectiles);
                    }

                    if (alien.getHealth() <= 0) { // Once the alien reaches 0 HP, delete it from both the scene/root and the ArrayList
                        aliens.remove(alien);
                        mainGameRoot.getChildren().remove(alien.getImageView());
                        stats.setKills(stats.getKills() + 1); // Give the player a kill
                    }

                    alien.move();
                    alien.checkCollisions(spaceship.getProjectiles(), stats); // checks if alien is touching the spaceship
                }
                

                // Health manager
                if (spaceship.getHealth() <= 0) {
                    this.stop(); // stops timer
                    keyStates.clear(); // Clears all the keys that were pressed, to save storage and to prevent the spaceship from moving as soon as you press the "Play Again" button if you died while moving
                    initiateGameOver(); // call method to end the game and switch the scene
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
        gameOver.setStyle("-fx-font-size: 20pt;" + "-fx-text-fill: red;");
        GridPane.setConstraints(gameOver, 0, 0);
        gridElements.add(gameOver);

        Label highScores = new Label("High Scores: ");
        highScores.setStyle("-fx-font-size: 12pt");
        GridPane.setConstraints(highScores, 0, 1);
        gridElements.add(highScores);

        // Write this game's scores to a file
        stats.saveStats();

        // Get and display high scores
        Stats highestStats = returnHighest();
        Label kills = new Label("Kills: " + highestStats.getKills());
        Label timeSurvived = new Label("Time Survived: " + highestStats.getTimeSurvived() + " seconds");
        Label damageDone = new Label("Damage Done: " + highestStats.getDamageDone());
        GridPane.setConstraints(kills, 0, 2);
        GridPane.setConstraints(timeSurvived, 0, 3);
        GridPane.setConstraints(damageDone, 0, 4);
        gridElements.add(kills);
        gridElements.add(timeSurvived);
        gridElements.add(damageDone);

        // Retry button so the player doesn't have to close the game and reopen it to play
        Button retry = new Button("Play Again");
        GridPane.setConstraints(retry, 0, 5);
        retry.setOnMouseClicked(e -> initiateGame());
        gridElements.add(retry);
        
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
            if (asteroidImageView.getLayoutX() < (-asteroidImageView.getLayoutX() - asteroidImageView.getLayoutBounds().getWidth()) || asteroidImageView.getLayoutY() < (-asteroidImageView.getLayoutY())) {
                root.getChildren().remove(asteroidImageView); // remove asteroid from the root/scene
                asteroids.remove(asteroid); // remove the asteroid from the list
            }
        }
    }

    // Method that returns the highest stats of each section in the HighScores.txt file
    public Stats returnHighest() {
        try {
            File file = new File("stats.txt");
            Scanner read = new Scanner(file);

            ArrayList<Stats> statsList = new ArrayList<>();
            
            while (read.hasNextLine()) { // reads each line and splits each line up into separate values and adds them to a list so the values can be sorted
                String currentStatsRaw = read.nextLine();
                String[] currentStats = currentStatsRaw.split(" "); // Method that splits a string into different substrings between a specific string/character, website I learned it from: https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#split-java.lang.String-
                statsList.add(new Stats(currentStats[0], currentStats[1], currentStats[2])); // creates a new object of Stats class, and adds it to the stats ArrayList created above
            }
            
            
            Stats highestStats = new Stats(0, 0, 0); // set all to 0 so that it will be replaced by any value that is higher than 0
            // if there has only been one game played then just print the first stats
            if (statsList.size() == 1) {
                highestStats.setKills(statsList.get(0).getKills());
                highestStats.setTimeSurvived(statsList.get(0).getTimeSurvived());
                highestStats.setDamageDone(statsList.get(0).getDamageDone());
            }
            else {
                // Loop to bubble sort all the stats in the file, and then create a new Stats class to store the highest values and return it back to where this method is called
                for (int gameNum = 0; gameNum < statsList.size(); gameNum++) {
                    if (highestStats.getKills() < statsList.get(gameNum).getKills()) { // 
                        highestStats.setKills(statsList.get(gameNum).getKills());
                    }
                    if (highestStats.getTimeSurvived() < statsList.get(gameNum).getTimeSurvived()) { // 
                        highestStats.setTimeSurvived(statsList.get(gameNum).getTimeSurvived());
                    }
                    if (highestStats.getDamageDone() < statsList.get(gameNum).getDamageDone()) { // 
                        highestStats.setDamageDone(statsList.get(gameNum).getDamageDone());
                    }
                }
            }

            read.close();
            return highestStats;
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return null;
    }
}
