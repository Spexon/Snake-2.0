package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Snake {

    private int difficulty; // Easy = 1, Normal = 2, Hard = 3
    private int speed;
    private int xPos = 77;
    private int yPos = 194;
    private String directionFacing = "right";


    /**
     * Continuously moves the snake in the desired direction depending on keyboard input
     */
    public void xyMovement() {

        if (directionFacing.equals("right")) {
            xPos++;
        } else if (directionFacing.equals("left")) {
            xPos--;
        } else if (directionFacing.equals("up")) {
            yPos--;
        } else if (directionFacing.equals("down")) {
            yPos++;
        } else {
            System.out.println("Error in xyMovement!");
        }
    }

    public void changeDirection(String keyboard) {

        //if (keyboard = );
    }

    /**
     * Determines if the snake goes out of bounds. Values may be different than the exact size of the boundary
     * because of the dimensions of the snake object itself.
     */
    public boolean boundaryControl() {

        boolean notDead = true;
        // Left Boundary
        if (xPos < 0) {
            System.out.println("Out of bounds LEFT");
            notDead = false;
        }
        // Right Boundary
        else if (xPos > 1920) {
            System.out.println("Out of bounds RIGHT");
            notDead = false;
        }
        // Top Boundary
        else if (yPos < 0) {
            System.out.println("Out of bounds TOP");
            notDead = false;
        }
        // Bottom Boundary
        else if (yPos > 1080) {
            System.out.println("Out of bounds BOTTOM");
            notDead = false;
        }
        return notDead;
    }


    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getDirectionFacing() {
        return directionFacing;
    }

    public void setDirectionFacing(String directionFacing) {
        this.directionFacing = directionFacing;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
