import java.awt.*;

public class Apple {

    private int xCoor;
    private int yCoor;
    private final int width;
    private final int height;

    /**
     * Constructor
     * @param xCoor the x coordinate of the apple
     * @param yCoor the y coordinate of the apple
     * @param tileSize the tile size of the apple
     */
    public Apple(int xCoor, int yCoor, int tileSize) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        width = tileSize;
        height = tileSize;
    }

    /**
     * Draws the apple
     * @param g graphics class
     */
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(xCoor * width, yCoor * height, width, height);
    }

    // Getters and setters

    public int getxCoor() {
        return xCoor;
    }

    public void setxCoor(int xCoor) {
        this.xCoor = xCoor;
    }

    public int getyCoor() {
        return yCoor;
    }

    public void setyCoor(int yCoor) {
        this.yCoor = yCoor;
    }
}

