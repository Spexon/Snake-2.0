package sample;


import java.util.*;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GameController {

  public Group bodyGroup;
  @FXML private Label scoreDisplayAnimation100;
    @FXML private Label scoreDisplayAnimation200;
    @FXML private Label scoreDisplayAnimation300;
    @FXML private Rectangle screenOverlay;
    @FXML private Text screenOverlayText;
    @FXML private Label scoreDisplay;
    @FXML private Label deadLabel;
    @FXML private Label showDifficulty;
    @FXML private ImageView snakeHead;
    @FXML private ImageView snakeBodyView; // The view is what gets controlled in the scene
    @FXML private ImageView apple;
    private int score = 0;
    private ArrayList<ImageView> totalBodies = new ArrayList<>();
    //private double [] headTurnLocation = new double[2]; // x = 0, y = 1

    private Queue<double[]> headTurnLocation = new LinkedList<>();
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
    private final Image appleImg = new Image(new FileInputStream("C:\\Users\\Vladimir\\OneDrive - Florida Gulf Coast University\\Personal Projects\\Snake 2.0 (With Git Working)\\src\\sample\\Apple.png"));

    private final Image snakeBodyImg = new Image(new FileInputStream(
     "C:\\Users\\Vladimir\\OneDrive - Florida Gulf Coast University\\Personal Projects\\Snake 2.0 (With Git Working)\\src\\sample\\Snake_Body.png"));

    private boolean notDead = true;

    public GameController() throws FileNotFoundException {
    }

    /**
     * Generates all objects after game begins
     */
    public void startGame() {
      initializeQueue();

      apple.setImage(appleImg);
      snakeBodyView.setImage(snakeBodyImg);

      showDifficulty.setText("Difficulty: " + PreGameController.snake.getDifficulty());
      screenOverlay.setVisible(false);
      screenOverlayText.setVisible(false);
      apple.setVisible(true);
      totalBodies.add(snakeBodyView);

      generateBody();
      generateApple();
      gameLoop();
    }


    public void gameLoop() {
        // game loop
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

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

        headTurnLocation.add(new double[] {PreGameController.snake.getxPos(),PreGameController.snake.getyPos()} );
//        headTurnLocation[0] = PreGameController.snake.getxPos();
//        headTurnLocation[1] = PreGameController.snake.getyPos();
        previousDirection = PreGameController.snake.getDirectionFacing();
        PreGameController.snake.setDirectionFacing(key.getCode().toString());
    }

  /**
   * Moves body so that it trails behind the head after each direction change
   */
    public void moveBody() {

      for (int i = 0, bodyFactor = 0; i < totalBodies.size(); i++, bodyFactor+=50 ) {

        switch (PreGameController.snake.getDirectionFacing()) {
          case "RIGHT" -> {
            System.out.println(headTurnLocation.peek()[1]);
            if (previousDirection.equals("DOWN") && totalBodies.get(i).getY() < headTurnLocation.peek()[1]) {
              totalBodies.get(i).setY(totalBodies.get(i).getY() + PreGameController.snake.getSpeed());
              break;
            } else if (previousDirection.equals("UP") && totalBodies.get(i).getY() > headTurnLocation.peek()[1]) {
              totalBodies.get(i).setY(totalBodies.get(i).getY() - PreGameController.snake.getSpeed());
              break;
            }
            headTurnLocation.poll();
            totalBodies.get(i).setX(snakeHead.getX() - bodyFactor - 50);
            totalBodies.get(i).setY(snakeHead.getY());
          }
          case "LEFT" -> {
            if (previousDirection.equals("DOWN") && totalBodies.get(i).getY() < headTurnLocation.peek()[1]) {
              totalBodies.get(i).setY(totalBodies.get(i).getY() + PreGameController.snake.getSpeed());
              break;
            } else if (previousDirection.equals("UP") && totalBodies.get(i).getY() > headTurnLocation.peek()[1]) {
              totalBodies.get(i).setY(totalBodies.get(i).getY() - PreGameController.snake.getSpeed());
              break;
            }
            totalBodies.get(i).setX(snakeHead.getX() + bodyFactor + 50);
            totalBodies.get(i).setY(snakeHead.getY());
          }
          case "DOWN" -> {
            if (previousDirection.equals("RIGHT") && totalBodies.get(i).getX() < headTurnLocation.peek()[0]) {
              totalBodies.get(i).setX(totalBodies.get(i).getX() + PreGameController.snake.getSpeed());
              break;
            } else if (previousDirection.equals("LEFT") && totalBodies.get(i).getX() > headTurnLocation.peek()[0]) {
              totalBodies.get(i).setX(totalBodies.get(i).getX() - PreGameController.snake.getSpeed());
              break;
            }
            totalBodies.get(i).setX(snakeHead.getX());
            totalBodies.get(i).setY(snakeHead.getY() - bodyFactor - 50);
          }
          case "UP" -> {
            if (previousDirection.equals("RIGHT") && totalBodies.get(i).getX() < headTurnLocation.peek()[0]) {
              totalBodies.get(i).setX(totalBodies.get(i).getX() + PreGameController.snake.getSpeed());
              break;
            } else if (previousDirection.equals("LEFT") && totalBodies.get(i).getX() > headTurnLocation.peek()[0]) {
              totalBodies.get(i).setX(totalBodies.get(i).getX() - PreGameController.snake.getSpeed());
              break;
            }
            totalBodies.get(i).setX(snakeHead.getX());
            totalBodies.get(i).setY(snakeHead.getY() + bodyFactor + 50);
          }
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
     * Generates a number of snake bodies and appends them to the body arraylist.
     * Trying to dynamically add more bodies with apple consumption.
     * This should probably be
     */
    public void generateBody() {
      ImageView body = new ImageView();
      body.setImage(snakeBodyImg);
      body.setX(totalBodies.get(totalBodies.size() - 1).getX());
      body.setY(totalBodies.get(totalBodies.size() - 1).getY());
      body.setFitHeight(50);
      body.setFitWidth(50);
      bodyGroup.getChildren().add(body);
      totalBodies.add(body);

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

    public void initializeQueue() {
      headTurnLocation.add(new double[] {200, 150});
    }
}
