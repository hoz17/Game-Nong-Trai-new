package Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Shop extends Entity {
    public Shop() {
        direction = "down";
        worldX = 48 * 14 + 20;
        worldY = 48 * 5;
        speed = 2;
        getImage();
    }

    public void getImage() {
        down1 = setup("/Tile/Decoration/oldman_down_1");
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        image = down1;
        g2.drawImage(image, worldX, worldY, 48, 48, null);
        g2.drawString("Shop Keeper", worldX - 10, worldY - 15);
    }
}
