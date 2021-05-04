package sample;


import java.io.File;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


public class GameController implements Initializable {


    @FXML private Label scoreDisplayAnimation100;
    @FXML private Label scoreDisplayAnimation200;
    @FXML private Label scoreDisplayAnimation300;
    @FXML private Rectangle screenOverlay;
    @FXML private Text screenOverlayText;
    @FXML private Label scoreDisplay;
    @FXML private Label deadLabel;
    @FXML private Label showDifficulty;
    @FXML private ImageView snakeHead;
    @FXML private ImageView snakeBody;
    @FXML private ImageView apple;
    private int score = 0;
    private ArrayList<SnakeBody> allBodies = new ArrayList<>();
    private double bodyTurnLocationX;
    private double bodyTurnLocationY;
    private String previousDirection = "RIGHT";

    private final Image snakeHeadRight = new Image(new FileInputStream(
        "C:\\Users\\Vladimir\\OneDrive - Florida Gulf Coast University\\Personal Projects\\Snake 2.0 (With Git Working)\\src\\sample\\Snake_Head_Right.png"));
    private final Image snakeHeadLeft = new Image(new FileInputStream(
        "C:\\Users\\Vladimir\\OneDrive - Florida Gulf Coast University\\Personal Projects\\Snake 2.0 (With Git Working)\\src\\sample\\Snake_Head_Left.png"));
    private final Image snakeHeadUp = new Image(new FileInputStream(
        "C:\\Users\\Vladimir\\OneDrive - Florida Gulf Coast University\\Personal Projects\\Snake 2.0 (With Git Working)\\src\\sample\\Snake_Head_Up.png"));
    private final Image snakeHeadDown = new Image(new FileInputStream(
        "C:\\Users\\Vladimir\\OneDrive - Florida Gulf Coast University\\Personal Projects\\Snake 2.0 (With Git Working)\\src\\sample\\Snake_Head_Down.png"));

    private boolean notDead = true;

    public GameController() throws FileNotFoundException {
    }

    /**
     * Generates all objects after game begins
     */
    public void startGame() {
        screenOverlay.setVisible(false);
        screenOverlayText.setVisible(false);
        gameLoop();
        apple.setVisible(true);
        generateApple();
        generateBody();
    }


    public void gameLoop() {

        // game loop experimental
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                SnakeBody body = generateBody();

                bodyTurnLocationX = (int) snakeHead.getX();
                bodyTurnLocationY = (int) snakeHead.getY();

                try {
                    PreGameController.snake.xyMovement();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                moveBody();

                if (PreGameController.snake.getDirectionFacing().equals("RIGHT")) {
                    snakeHead.setImage(snakeHeadRight);
                    snakeHead.setX(PreGameController.snake.getxPos());



                } else if (PreGameController.snake.getDirectionFacing().equals("LEFT")) {
                    snakeHead.setImage(snakeHeadLeft);
                    snakeHead.setX(PreGameController.snake.getxPos());



                } else if (PreGameController.snake.getDirectionFacing().equals("UP")) {
                    snakeHead.setImage(snakeHeadUp);
                    snakeHead.setY(PreGameController.snake.getyPos());



                } else if (PreGameController.snake.getDirectionFacing().equals("DOWN")) {
                    snakeHead.setImage(snakeHeadDown);
                    snakeHead.setY(PreGameController.snake.getyPos());



                }
                notDead = PreGameController.snake.boundaryControl();
                checkSnakeCollision();
                if (!notDead) {
                    deadLabel.setVisible(true);
                }
            }
        };
        timer.start();
    }

    public void handleDirectionChange(KeyEvent key) {
        previousDirection = PreGameController.snake.getDirectionFacing();
        PreGameController.snake.setDirectionFacing(key.getCode().toString());
    }


    /**
     * If the snake x and y were an array, this would move properly (new)
     */
    public void moveBody() {

        for (int z = allBodies.size() - 1; z > 0; z--) { // X position of snake body
            System.out.println("____Previous Direction____ " + previousDirection);
            System.out.println("Current Direction " + PreGameController.snake.getDirectionFacing());
                if (PreGameController.snake.getDirectionFacing().equals("RIGHT")) {

                    // Moves body to where the head was before it changed directions
                    if (snakeBody.getY() != bodyTurnLocationY && previousDirection.equals("DOWN")) {
                        snakeBody.setY(snakeBody.getY() + PreGameController.snake.getSpeed());
                        return;
                    }
                    else if (snakeBody.getX() != bodyTurnLocationX && previousDirection.equals("UP")) {
                        snakeBody.setY(snakeBody.getY() - PreGameController.snake.getSpeed());
                        return;
                    }

                        allBodies.get(z).getSnakeBody().setY(bodyTurnLocationY);
                        allBodies.get(z).getSnakeBody().setX(bodyTurnLocationX - 50);

                }   else if (PreGameController.snake.getDirectionFacing().equals("LEFT")) {

                    if (snakeBody.getY() != bodyTurnLocationY && previousDirection.equals("DOWN")) {
                        snakeBody.setY(snakeBody.getY() + PreGameController.snake.getSpeed());
                        return;
                    }
                    else if (snakeBody.getX() != bodyTurnLocationX && previousDirection.equals("UP")) {
                        snakeBody.setY(snakeBody.getY() - PreGameController.snake.getSpeed());
                        return;
                    }

                    allBodies.get(z).getSnakeBody().setY(bodyTurnLocationY);
                    allBodies.get(z).getSnakeBody().setX(bodyTurnLocationX + 50);

                }   else if (PreGameController.snake.getDirectionFacing().equals("DOWN")) {

                    if (snakeBody.getX() != bodyTurnLocationX && previousDirection.equals("RIGHT")) {
                        snakeBody.setX(snakeBody.getX() + PreGameController.snake.getSpeed());
                        return;
                    }
                    else if (snakeBody.getX() != bodyTurnLocationX && previousDirection.equals("LEFT")) {
                        snakeBody.setX(snakeBody.getX() - PreGameController.snake.getSpeed());
                        return;
                    }

                    allBodies.get(z).getSnakeBody().setY(bodyTurnLocationY - 50);
                    allBodies.get(z).getSnakeBody().setX(bodyTurnLocationX);

                }  else if (PreGameController.snake.getDirectionFacing().equals("UP")) {

                    if (snakeBody.getX() != bodyTurnLocationX && previousDirection.equals("RIGHT")) {
                        snakeBody.setX(snakeBody.getX() + PreGameController.snake.getSpeed());
                        return;
                    }
                    else if (snakeBody.getX() != bodyTurnLocationX && previousDirection.equals("LEFT")) {
                        snakeBody.setX(snakeBody.getX() - PreGameController.snake.getSpeed());
                        return;
                    }

                    allBodies.get(z).getSnakeBody().setY(bodyTurnLocationY + 50);
                    allBodies.get(z).getSnakeBody().setX(bodyTurnLocationX);

                }
        }
    }

    /**
     * Generates an apple and places it somewhere randomly on the screen
     */
    public void generateApple() {
        Random rand = new Random();
        apple.setX(rand.nextInt(1650) + 100);
        apple.setY(rand.nextInt(850) + 100);
    }

    /**
     * Generates a snake body and appends to the body arraylist.
     * Trying to dynamically add more bodies with apple consumption.
     */
    public SnakeBody generateBody() {
        SnakeBody body = new SnakeBody(snakeBody);
        allBodies.add(body);
        return body;
    }

    /**
     * Checks if the snake hits an apple between certain parameters (the hitbox of the apple) or itself.
     */
    public void checkSnakeCollision() {

        if (((snakeHead.getX() > apple.getX() - 50) && (snakeHead.getX() < apple.getX() + 50)) &&
                ((snakeHead.getY() > apple.getY() - 50) && (snakeHead.getY() < apple.getY() + 50))) {
            if(PreGameController.snake.getDifficulty() == 1) {
                setScore(scoreDisplayAnimation100);
            }
            else if(PreGameController.snake.getDifficulty() == 2) {
                setScore(scoreDisplayAnimation200);
            }
            else {
                setScore(scoreDisplayAnimation300);
            }
            generateApple();
            generateBody();
        }
    }

    /**
     * Assigns the score at the top of the screen, and animates the score across the screen once an
     * apple is eaten. Higher difficulty gives more score per apple
     */
    public void setScore(Label scoreLabel) {

        score += (100 * PreGameController.snake.getDifficulty());
        scoreDisplay.setText("Score: " + score);

        scoreLabel.setVisible(true);
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(1));
        translateTransition.setNode(scoreLabel);
        translateTransition.setFromX(apple.getX());
        translateTransition.setFromY(apple.getY() - 100);
        translateTransition.setToY(apple.getY() - 200);

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(1.2));
        fadeTransition.setNode(scoreLabel);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        translateTransition.play();
        fadeTransition.play();

    }

    /**
     * Sets up difficulty and other objects before program starts. Everything done here should be possible to do in FXML
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showDifficulty.setText("Difficulty: " + PreGameController.snake.getDifficulty());
    }
}
