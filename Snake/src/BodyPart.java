import java.awt.*;

public class BodyPart {

    private int xCoor;
    private int yCoor;
    private final int width;
    private final int height;
    private static Color bodyPartColor = new Color(250, 250, 0);

    /**
     * Constructor
     * @param xCoor the x coordinate of the body part
     * @param yCoor the y coordinate of the body part
     * @param tileSize the tile size of the body part
     */
    public BodyPart(int xCoor,int yCoor, int tileSize){
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        width = tileSize;
        height = tileSize;
    }

    /**
     * Makes the color of the body parts brighter
     */
    public void changeColor(){
        if(bodyPartColor.getBlue() != 250) {
            bodyPartColor = new Color(250, 250, bodyPartColor.getBlue() + 5);
            System.out.println(bodyPartColor.getBlue());
        }
    }

    /**
     * Draws the body part
     * @param g Graphics class
     */
    public void draw(Graphics g){
        g.setColor(bodyPartColor);
        g.fillRect(xCoor * width, yCoor * height, width, height);
    }

    // getters and setters
    public int getxCoor(){
        return xCoor;
    }

    public int getyCoor(){
        return yCoor;
    }

    public void setxCoor(int xCoor){
        this.xCoor = xCoor;
    }

    public void setyCoor(int yCoor){
        this.yCoor = yCoor;
    }
}
