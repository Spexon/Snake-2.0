package sample;


import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;


public class GameController {


    @FXML private Label scoreDisplayAnimation100;
    @FXML private Label scoreDisplayAnimation200;
    @FXML private Label scoreDisplayAnimation300;
    @FXML private Rectangle screenOverlay;
    @FXML private Text screenOverlayText;
    @FXML private Label scoreDisplay;
    @FXML private Label deadLabel;
    @FXML private Label showDifficulty;
    @FXML private ImageView snakeHead;
    @FXML private ImageView stationaryBody;

    @FXML private ImageView snakeBodyView; // The view is what gets controlled in the scene
    @FXML private ImageView apple;
    private int score = 0;
    private final ArrayList<SnakeBody> allBodies = new ArrayList<>();
    private double bodyTurnLocationX;
    private double bodyTurnLocationY;
    private String previousDirection = "RIGHT";
    private AnimationTimer timer;

  /**
   * An image by itself cannot be added to the scene graph.
   * It must first be embedded in an ImageView.
   */
  private final Image snakeHeadRight = new Image(new FileInputStream(
      "C:\\Users\\Vladimir\\OneDrive - Florida Gulf Coast University\\Personal Projects\\Snake 2.0 (With Git Working)\\src\\sample\\Snake_Head_Right.png"));
    private final Image snakeHeadLeft = new Image(new FileInputStream(
        "C:\\Users\\Vladimir\\OneDrive - Florida Gulf Coast University\\Personal Projects\\Snake 2.0 (With Git Working)\\src\\sample\\Snake_Head_Left.png"));
    private final Image snakeHeadUp = new Image(new FileInputStream(
        "C:\\Users\\Vladimir\\OneDrive - Florida Gulf Coast University\\Personal Projects\\Snake 2.0 (With Git Working)\\src\\sample\\Snake_Head_Up.png"));
    private final Image snakeHeadDown = new Image(new FileInputStream(
        "C:\\Users\\Vladimir\\OneDrive - Florida Gulf Coast University\\Personal Projects\\Snake 2.0 (With Git Working)\\src\\sample\\Snake_Head_Down.png"));

//    private final Image snakeBody = new Image(new FileInputStream(
//     "C:\\Users\\Vladimir\\OneDrive - Florida Gulf Coast University\\Personal Projects\\Snake 2.0 (With Git Working)\\src\\sample\\Snake_Body.png"));

    private boolean notDead = true;

    public GameController() throws FileNotFoundException {
    }

    /**
     * Generates all objects after game begins
     */
    public void startGame() {
        showDifficulty.setText("Difficulty: " + PreGameController.snake.getDifficulty());
        screenOverlay.setVisible(false);
        screenOverlayText.setVisible(false);
        apple.setVisible(true);
        generateApple();
        generateBody(2);
        gameLoop();
    }


    public void gameLoop() {
        // game loop
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                bodyTurnLocationX = (int) snakeHead.getX();
                bodyTurnLocationY = (int) snakeHead.getY();

                PreGameController.snake.xyMovement();

                moveBody();

                // SnakeHead image movement
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
                // Snake collision
                notDead = PreGameController.snake.boundaryControl();
                checkSnakeCollision();
                if (!notDead) {
                    deadLabel.setVisible(true);
                    timer.stop();
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
     * Moves body so that it trails behind the head after each direction change
     */
    public void moveBody() {

       for (int z = allBodies.size() - 1; z > 0; z--) { // reevaluate what this does <---
        System.out.println("bodies: " + allBodies.size());

                if (PreGameController.snake.getDirectionFacing().equals("RIGHT")) {

                    // Moves body to where the head was before it changed directions
                    if (snakeBodyView.getY() < bodyTurnLocationY && previousDirection.equals("DOWN")) {
                        snakeBodyView.setY(snakeBodyView.getY() + PreGameController.snake.getSpeed());
                        stationaryBody.setY(snakeBodyView.getY() - 50);
                        return;
                    }
                    else if (snakeBodyView.getY() > bodyTurnLocationY && previousDirection.equals("UP")) {
                        snakeBodyView.setY(snakeBodyView.getY() - PreGameController.snake.getSpeed());
                        stationaryBody.setY(snakeBodyView.getY() + 50);
                        return;
                    }
                    if (stationaryBody.getY() < bodyTurnLocationY && previousDirection.equals("DOWN")) {
                        allBodies.get(z).getSnakeBody().setX(bodyTurnLocationX - 50);
                        stationaryBody.setY(stationaryBody.getY() + PreGameController.snake
                            .getSpeed());
                        return;
                    }
                    else if (stationaryBody.getY() > bodyTurnLocationY && previousDirection.equals("UP")) {
                        allBodies.get(z).getSnakeBody().setX(bodyTurnLocationX - 50);
                        stationaryBody.setY(stationaryBody.getY() - PreGameController.snake
                            .getSpeed());
                        return;
                    }

                        allBodies.get(z).getSnakeBody().setX(bodyTurnLocationX - 50);
                        stationaryBody.setX(snakeBodyView.getX() - 50);


                }else if (PreGameController.snake.getDirectionFacing().equals("LEFT")) {

                    if (snakeBodyView.getY() < bodyTurnLocationY && previousDirection.equals("DOWN")) {
                        snakeBodyView.setY(snakeBodyView.getY() + PreGameController.snake.getSpeed());
                        stationaryBody.setY(snakeBodyView.getY() - 50);
                        return;
                    }
                    else if (snakeBodyView.getY() > bodyTurnLocationY && previousDirection.equals("UP")) {
                        snakeBodyView.setY(snakeBodyView.getY() - PreGameController.snake.getSpeed());
                        stationaryBody.setY(snakeBodyView.getY() + 50);
                        return;
                    }
                    if (stationaryBody.getY() < bodyTurnLocationY && previousDirection.equals("DOWN")) {
                        allBodies.get(z).getSnakeBody().setX(bodyTurnLocationX + 50);
                        stationaryBody.setY(stationaryBody.getY() + PreGameController.snake
                            .getSpeed());
                        return;
                    }
                    else if (stationaryBody.getY() > bodyTurnLocationY && previousDirection.equals("UP")) {
                        allBodies.get(z).getSnakeBody().setX(bodyTurnLocationX + 50);
                        stationaryBody.setY(stationaryBody.getY() - PreGameController.snake
                            .getSpeed());
                        return;
                    }


                    allBodies.get(z).getSnakeBody().setX(bodyTurnLocationX + 50);
                    stationaryBody.setX(snakeBodyView.getX() + 50);

                }else if (PreGameController.snake.getDirectionFacing().equals("DOWN")) {

                    if (snakeBodyView.getX() < bodyTurnLocationX && previousDirection.equals("RIGHT")) {
                        snakeBodyView.setX(snakeBodyView.getX() + PreGameController.snake.getSpeed());
                        stationaryBody.setX(snakeBodyView.getX() - 50);
                        return;
                    }
                    else if (snakeBodyView.getX() > bodyTurnLocationX && previousDirection.equals("LEFT")) {
                        snakeBodyView.setX(snakeBodyView.getX() - PreGameController.snake.getSpeed());
                        stationaryBody.setX(snakeBodyView.getX() + 50);
                        return;
                    }
                    if (stationaryBody.getX() < bodyTurnLocationX && previousDirection.equals("RIGHT")) {
                        allBodies.get(z).getSnakeBody().setY(bodyTurnLocationY - 50);
                        stationaryBody.setX(stationaryBody.getX() + PreGameController.snake
                            .getSpeed());
                        return;
                    }
                    else if (stationaryBody.getX() > bodyTurnLocationX && previousDirection.equals("LEFT")) {
                        allBodies.get(z).getSnakeBody().setY(bodyTurnLocationY - 50);
                        stationaryBody.setX(stationaryBody.getX() - PreGameController.snake
                            .getSpeed());
                        return;
                    }


                    allBodies.get(z).getSnakeBody().setY(bodyTurnLocationY - 50);
                    stationaryBody.setY(snakeBodyView.getY() - 50);

                }else if (PreGameController.snake.getDirectionFacing().equals("UP")) {

                    if (snakeBodyView.getX() < bodyTurnLocationX && previousDirection.equals("RIGHT")) {
                        snakeBodyView.setX(snakeBodyView.getX() + PreGameController.snake.getSpeed());
                        stationaryBody.setX(snakeBodyView.getX() - 50);
                        return;
                    }
                    else if (snakeBodyView.getX() > bodyTurnLocationX && previousDirection.equals("LEFT")) {
                        snakeBodyView.setX(snakeBodyView.getX() - PreGameController.snake.getSpeed());
                        stationaryBody.setX(snakeBodyView.getX() + 50);
                        return;
                    }
                    if (stationaryBody.getX() < bodyTurnLocationX && previousDirection.equals("RIGHT")) {
                        allBodies.get(z).getSnakeBody().setY(bodyTurnLocationY + 50);
                        stationaryBody.setX(stationaryBody.getX() + PreGameController.snake
                            .getSpeed());
                        return;
                    }
                    else if (stationaryBody.getX() > bodyTurnLocationX && previousDirection.equals("LEFT")) {
                        allBodies.get(z).getSnakeBody().setY(bodyTurnLocationY + 50);
                        stationaryBody.setX(stationaryBody.getX() - PreGameController.snake
                            .getSpeed());
                        return;
                    }

                    allBodies.get(z).getSnakeBody().setY(bodyTurnLocationY + 50);
                    stationaryBody.setY(snakeBodyView.getY() + 50);

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
      System.out.println(apple.isVisible() + " " + apple.getX() + ", " + apple.getY());
    }

    /**
     * Generates a number of snake bodies and appends them to the body arraylist.
     * Trying to dynamically add more bodies with apple consumption.
     */
    public void generateBody(int num) {
//        SnakeBody body;
//        for (int i = 0; i < num; i++) {
//            body = new SnakeBody(snakeBodyView, stationaryBody);
//            allBodies.add(body);
//
//        }
        //return body;
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
            generateBody(1);
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
}
