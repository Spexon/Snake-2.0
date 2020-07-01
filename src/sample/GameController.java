package sample;

import javafx.scene.control.Label;

public class GameController {

    public Label showDifficulty;

    public void showDifficulty() {
        Snake snake = new Snake();
        showDifficulty.setText("Difficulty: " + snake.getDifficulty());
    }

}
