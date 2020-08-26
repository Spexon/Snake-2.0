package sample;


import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


public class GameController implements Initializable {


    @FXML private Label scoreDisplayAnimation;
    @FXML private Label scoreDisplay;
    @FXML private Label deadLabel;
    @FXML private Label showDifficulty;
    @FXML private ImageView snakeHead;
    @FXML private ImageView snakeBody;
    @FXML private ImageView apple;
    private int score = 0;
    private double bodyTurnLocationX;
    private double bodyTurnLocationY;
    private final Image snakeHeadRight = new Image(new FileInputStream("C:\\Users\\Vladimir\\OneDrive - Florida Gulf Coast University\\Personal Projects\\Snake 2.0\\src\\sample\\Snake_Head_Right.png"));
    private final Image snakeHeadLeft = new Image(new FileInputStream("C:\\Users\\Vladimir\\OneDrive - Florida Gulf Coast University\\Personal Projects\\Snake 2.0\\src\\sample\\Snake_Head_Left.png"));
    private final Image snakeHeadUp = new Image(new FileInputStream("C:\\Users\\Vladimir\\OneDrive - Florida Gulf Coast University\\Personal Projects\\Snake 2.0\\src\\sample\\Snake_Head_Up.png"));
    private final Image snakeHeadDown = new Image(new FileInputStream("C:\\Users\\Vladimir\\OneDrive - Florida Gulf Coast University\\Personal Projects\\Snake 2.0\\src\\sample\\Snake_Head_Down.png"));

    private boolean notDead = true;

    public GameController() throws FileNotFoundException {
    }

    /**
     * Animates the snake so that it moves continuously.
     * Code idea retrieved from: http://wecode4fun.blogspot.com/2015/01/the-game-loop-game-we-create-is-2d.html
     */
    private void gameLoop() {

        // game loop
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                bodyTurnLocationX = snakeHead.getX();
                bodyTurnLocationY = snakeHead.getY();
                PreGameController.snake.xyMovement();
                if (PreGameController.snake.getDirectionFacing().equals("RIGHT")) {
                    snakeHead.setImage(snakeHeadRight);
                    snakeHead.setX(PreGameController.snake.getxPos());
                    moveBody();
                    snakeBody.setY(bodyTurnLocationY);
                    snakeBody.setX(PreGameController.snake.getxPos() - 50);
                } else if (PreGameController.snake.getDirectionFacing().equals("LEFT")) {
                    snakeHead.setImage(snakeHeadLeft);
                    snakeHead.setX(PreGameController.snake.getxPos());
                    moveBody();
                    snakeBody.setY(bodyTurnLocationY);
                    snakeBody.setX(PreGameController.snake.getxPos() + 50);
                } else if (PreGameController.snake.getDirectionFacing().equals("UP")) {
                    snakeHead.setImage(snakeHeadUp);
                    snakeHead.setY(PreGameController.snake.getyPos());
                    moveBody();
                    snakeBody.setX(bodyTurnLocationX);
                    snakeBody.setY(PreGameController.snake.getyPos() + 50);
                } else if (PreGameController.snake.getDirectionFacing().equals("DOWN")) {
                    snakeHead.setImage(snakeHeadDown);
                    snakeHead.setY(PreGameController.snake.getyPos());
                    moveBody();
                    snakeBody.setX(bodyTurnLocationX);
                    snakeBody.setY(PreGameController.snake.getyPos() - 50);
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
        PreGameController.snake.setDirectionFacing(key.getCode().toString());
    }

    /**
     * If the snake x and y were an array, this would move properly
     */
    public void moveBody() {
        for (int z = 50; z > 0; z--) { // X and Y position of snake

            snakeBody.setX(z - 1);
            snakeBody.setY(z - 1);

            //    X[z] = X[(z - 1)];
            //    Y[z] = Y[(z - 1)];
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
     * Checks if the snake hits an apple between certain parameters (the hitbox of the apple) or itself.
     */
    public void checkSnakeCollision() {

        if (((snakeHead.getX() > apple.getX() - 50) && (snakeHead.getX() < apple.getX() + 50)) &&
                ((snakeHead.getY() > apple.getY() - 50) && (snakeHead.getY() < apple.getY() + 50))) {
            setScore();
            generateApple();
        }
    }

    /**
     * Assigns the score at the top, and animates the score across the screen once an apple is eaten
     */
    public void setScore() {
        score += 100;
        scoreDisplay.setText("Score: " + score);
        scoreDisplayAnimation.setVisible(true);

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(1));
        translateTransition.setNode(scoreDisplayAnimation);
        translateTransition.setFromX(apple.getX());
        translateTransition.setFromY(apple.getY() - 100);
        translateTransition.setToY(apple.getY() - 200);

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(1.2));
        fadeTransition.setNode(scoreDisplayAnimation);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        translateTransition.play();
        fadeTransition.play();

    }

    /**
     * Sets up difficulty and other objects as program starts. Everything done here should be possible to do in FXML
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showDifficulty.setText("Difficulty: " + PreGameController.snake.getDifficulty());
        gameLoop();
        generateApple();
    }
}
