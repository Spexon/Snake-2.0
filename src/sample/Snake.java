package sample;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Snake {

  private int difficulty; // Easy = 1, Normal = 2, Hard = 3
  private double speed;
  private double xPos = 200;
  private double yPos = 150;
  private String directionFacing = "RIGHT";
  private boolean canMoveRight = true;
  private boolean canMoveLeft = false;
  private boolean canMoveUp = true;
  private boolean canMoveDown = true;



  /**
   * Continuously moves the snake in the desired direction depending on keyboard input, speed is
   * based on difficulty
   */
  public void xyMovement() throws FileNotFoundException {

    GameController gc = new GameController();
    //gc.moveBody();
    speed = difficulty * 2.0;
    if (directionFacing.equals("RIGHT")) {
      xPos += speed;
    } else if (directionFacing.equals("LEFT")) {
      xPos -= speed;
    } else if (directionFacing.equals("UP")) {
      yPos -= speed;
    } else if (directionFacing.equals("DOWN")) {
      yPos += speed;
    }
  }




  /**
   * Determines if the snake goes out of bounds. Values may be different than the exact size of the
   * boundary because of the dimensions of the snake object itself.
   */
  public boolean boundaryControl() {

    boolean notDead = true;
    // Left Boundary
    if (xPos < 100) {
      notDead = false;
    }
    // Right Boundary
    else if (xPos > 1750) {
      notDead = false;
    }
    // Top Boundary
    else if (yPos < 100) {
      notDead = false;
    }
    // Bottom Boundary
    else if (yPos > 950) {
      notDead = false;
    }
    return notDead;
  }

  /**
   * Getters and setters below
   */

  public int getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  public String getDirectionFacing() {
    return directionFacing;
  }

  public double getSpeed() {
    return speed;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }


  /**
   * Ensures that only keypad values are accepted and that the snake cannot move into itself.
   *
   * @param directionFacing the direction that the snake is being requested to face.
   */
  public void setDirectionFacing(String directionFacing) {
    if (directionFacing.equals("RIGHT") && canMoveRight) {
      this.directionFacing = directionFacing;
      canMoveRight = true;
      canMoveLeft = false;
      canMoveUp = true;
      canMoveDown = true;
    } else if (directionFacing.equals("LEFT") && canMoveLeft) {
      this.directionFacing = directionFacing;
      canMoveRight = false;
      canMoveLeft = true;
      canMoveUp = true;
      canMoveDown = true;
    } else if (directionFacing.equals("DOWN") && canMoveDown) {
      this.directionFacing = directionFacing;
      canMoveRight = true;
      canMoveLeft = true;
      canMoveUp = false;
      canMoveDown = true;
    } else if (directionFacing.equals("UP") && canMoveUp) {
      this.directionFacing = directionFacing;
      canMoveRight = true;
      canMoveLeft = true;
      canMoveUp = true;
      canMoveDown = false;

    }
  }

  public double getxPos() {
    return xPos;
  }

  public void setxPos(double xPos) {
    this.xPos = xPos;
  }

  public double getyPos() {
    return yPos;
  }

  public void setyPos(double yPos) {
    this.yPos = yPos;
  }

  public boolean getCanMoveRight() {
    return canMoveRight;
  }

  public void setCanMoveRight(boolean canMoveRight) {
    this.canMoveRight = canMoveRight;
  }

  public boolean getCanMoveLeft() {
    return canMoveLeft;
  }

  public void setCanMoveLeft(boolean canMoveLeft) {
    this.canMoveLeft = canMoveLeft;
  }

  public boolean getCanMoveUp() {
    return canMoveUp;
  }

  public void setCanMoveUp(boolean canMoveUp) {
    this.canMoveUp = canMoveUp;
  }

  public boolean getCanMoveDown() {
    return canMoveDown;
  }

  public void setCanMoveDown(boolean canMoveDown) {
    this.canMoveDown = canMoveDown;
  }


}
