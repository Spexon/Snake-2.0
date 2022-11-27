package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SnakeBody extends Snake {

  private ImageView snakeBodyView;
  private ImageView stationaryBody;
  private double stationaryBodyX;
  private double stationaryBodyY;


  public SnakeBody() {
  }

  public ImageView getSnakeBody() {
    return snakeBodyView;
  }

  public ImageView getStationaryBody() {
    return stationaryBody;
  }

  public double getStationaryBodyX() {
    return stationaryBodyX;
  }

  public double getStationaryBodyY() {
    return stationaryBodyY;
  }

  public void setStationaryBodyX(double stationaryBodyX) {
    this.stationaryBodyX = stationaryBodyX;
  }

  public void setStationaryBodyY(double stationaryBodyY) {
    this.stationaryBodyY = stationaryBodyY;
  }

  public void setSnakeBody(ImageView snakeBody) {
    this.snakeBodyView = snakeBody;
  }

  public void setStationaryBody(ImageView stationaryBody) {
    this.stationaryBody = stationaryBody;
  }

}
