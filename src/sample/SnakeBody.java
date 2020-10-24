package sample;

import javafx.scene.image.ImageView;

public class SnakeBody extends Snake {

  private ImageView snakeBody;


  public SnakeBody(ImageView snakeBody) {
    this.snakeBody = snakeBody;
  }

  public ImageView getSnakeBody() {
    return snakeBody;
  }

  public void setSnakeBody(ImageView snakeBody) {
    this.snakeBody = snakeBody;
  }

}
