package sample;

import javafx.scene.image.ImageView;

public class SnakeBody extends Snake {

  private ImageView snakeBody;
  private ImageView stationaryBody;
  private double stationaryBodyX;
  private double stationaryBodyY;


  public SnakeBody(ImageView snakeBody, ImageView stationaryBody) {
    this.snakeBody = snakeBody;
    this.stationaryBody = stationaryBody;
  }

  public ImageView getSnakeBody() {
    return snakeBody;
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
    this.snakeBody = snakeBody;
  }

  public void setStationaryBody(ImageView stationaryBody) {
    this.stationaryBody = stationaryBody;
  }

}
