package View;

import Controller.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawMap {
    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public void drawArrow(Graphics2D g2, int currentMap) {
        BufferedImage arrow1 = setup("/Tile/Decoration/arrow1", 48, 48);
        BufferedImage arrow2 = setup("/Tile/Decoration/arrow2", 48, 48);
        if (currentMap == 0) {
            g2.drawImage(arrow1, 432, 192, null);
            g2.drawImage(arrow2, 480, 192, null);
        } else {
            g2.drawImage(arrow1, 480, 624, null);
            g2.drawImage(arrow2, 528, 624, null);
        }
    }

    public void draw(Graphics2D g2, int currentMap) {
        BufferedImage map1 = setup("/Map/testMap11", 1056, 672);
        BufferedImage map2 = setup("/Map/Map2", 1056, 672);

        if (currentMap == 0) {
            g2.drawImage(map1, 0, 0, null);
        } else g2.drawImage(map2, 0, 0, null);
    }
}
