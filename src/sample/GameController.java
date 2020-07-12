package sample;

import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskListener;
import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;


public class GameController implements Initializable {

    public Label showDifficulty;
    public ImageView snakeHead;
    private boolean notDead = true;


    /**
     * Animates the snake so that it moves continuously.
     * Code idea retrieved from: http://wecode4fun.blogspot.com/2015/01/the-game-loop-game-we-create-is-2d.html
     */
    private void gameLoop() {

        // game loop
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {


                if (PreGameController.level.getDirectionFacing().equals("RIGHT")) {
                    snakeHead.setX(PreGameController.level.getxPos() + 2);
                } else if (PreGameController.level.getDirectionFacing().equals("LEFT")) {
                    snakeHead.setX(PreGameController.level.getxPos() - 2);
                } else if (PreGameController.level.getDirectionFacing().equals("UP")) {
                    snakeHead.setY(PreGameController.level.getyPos() + 2);
                } else if (PreGameController.level.getDirectionFacing().equals("DOWN")) {
                    snakeHead.setY(PreGameController.level.getyPos() - 2);
                } else {
                    System.out.println("Error in xyMovement!");
                }
                PreGameController.level.boundaryControl();
            }
        };
        timer.start();
    }

    public void handleSnakeMovement() {


    }

    public void handleDirectionChange(KeyEvent key) {

        PreGameController.level.setDirectionFacing(key.getCode().toString());

    }

    /**
     * Sets up difficulty and other objects as program starts. Everything done here should be possible to do in FXML
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showDifficulty.setText("Difficulty: " + PreGameController.level.getDifficulty());
        gameLoop();
    }
}
