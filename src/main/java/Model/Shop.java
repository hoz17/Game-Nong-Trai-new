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
        g2.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        drawSubWindow(g2,worldX - 48, worldY - 43,150,40);
        g2.drawString("Shop Keeper", worldX - 34, worldY - 15);
    }

    public void drawSubWindow(Graphics2D g2, int x, int y, int width, int height) {
        Color c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(0, 0, 0);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
}
