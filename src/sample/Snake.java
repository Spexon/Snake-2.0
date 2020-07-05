package sample;

public class Snake {

    private int difficulty; // Easy = 1, Normal = 2, Hard = 3
    private int speed;
    private int xPos;
    private int yPos;


    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
