package View;

import Controller.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawMap {
    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, 1056, 672);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public void draw(Graphics2D g2, int currentMap) {
        BufferedImage map1 = setup("/Map/testMap11");
        BufferedImage map2 = setup("/Map/Map2");
        if (currentMap == 0) {
            g2.drawImage(map1, 0, 0, null);
        } else g2.drawImage(map2, 0, 0, null);
    }
}
