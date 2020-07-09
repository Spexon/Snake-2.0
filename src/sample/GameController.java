package sample;

import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskListener;
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


    public void handleSnakeMovement() {

        while (notDead) {
            notDead = PreGameController.level.boundaryControl();
            snakeHead.setX(PreGameController.level.getxPos());
            snakeHead.setY(PreGameController.level.getyPos());
            PreGameController.level.xyMovement(); //I need to update the snake image realtime with the application

        }

    }

    public void handleDirectionChange(KeyEvent actionEvent) {
        System.out.println(actionEvent);
        PreGameController.level.boundaryControl();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Task<Snake> task = new Task() {
            @Override
            protected Object call() throws Exception {
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
        showDifficulty.setText("Difficulty: " + PreGameController.level.getDifficulty());
        handleSnakeMovement();
    }
}
