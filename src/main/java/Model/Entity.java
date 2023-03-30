package Model;

import Controller.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity {
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public int worldX, worldY;
    public int speed;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public int actionLockCounter = 0;
    public Rectangle solidArea = new Rectangle(0, 9, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public ArrayList<Crop> inventory = new ArrayList<>();

    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, 48, 48);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
