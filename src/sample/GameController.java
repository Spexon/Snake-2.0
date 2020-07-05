package sample;

import javafx.scene.control.Label;


public class GameController {

    public Label showDifficulty;

    public void showDifficulty() {

        showDifficulty.setText("Difficulty: " + PreGameController.level.getDifficulty());
    }

}
