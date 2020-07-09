package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PreGameController implements Initializable {

    public Button beginBtn;
    public static Snake level = new Snake();
    @FXML
    private AnchorPane anchorPane;

    /**
     * Difficulty selectors, tried to make this one method, but couldn't understand how to differentiate the buttons.
     * I created a class level snake object so that it could be used in other classes and not lose data.
     */
    public void easyDifficulty() {
        //Snake level = new Snake();
        level.setDifficulty(1);
        beginBtn.setVisible(true);
    }

    public void mediumDifficulty() {
        //Snake level = new Snake();
        level.setDifficulty(2);
        beginBtn.setVisible(true);
    }

    public void hardDifficulty() {
        //Snake level = new Snake();
        level.setDifficulty(3);
        beginBtn.setVisible(true);
    }

    /**
     * Creates a new scene and changes current scene to new one.
     * (Code used from: https://www.youtube.com/watch?v=rMQrXSYHl8w)
     */
    @FXML
    private void loadNextScene() {
        try {
            Parent secondView = FXMLLoader.load(getClass().getResource("mainScene.fxml"));

            Scene newScene = new Scene(secondView);

            Stage curStage = (Stage) anchorPane.getScene().getWindow();
            curStage.setScene(newScene);

            // Centers the window for the main scene
            // (Retrieved from https://stackoverflow.com/questions/29350181/how-to-center-a-window-properly-in-java-fx)
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            curStage.setX((screenBounds.getWidth() - 1930) / 2);
            curStage.setY((screenBounds.getHeight() - 1050) / 2);

            curStage.setTitle("Snake");
            curStage.show();

        } catch (IOException ex) {
            Logger.getLogger(PreGameController.class.getName()).log(Level.SEVERE, "Scene exception", ex);
        }
    }

    /**
     * Sets up buttons and other objects as program starts. Everything done here should be possible to do in FXML
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
