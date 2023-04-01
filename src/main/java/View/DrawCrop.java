package View;

import Controller.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;

public class DrawCrop {
    GamePanel gp;

    public DrawCrop(GamePanel gp) {
        this.gp = gp;
    }

    public void update(Graphics2D g2) {
        Instant now = Instant.now();
        Instant plantTime;
        long time;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 4; j++) {
                if (gp.land.getCropID(i, j) != -1) {
                    plantTime = Instant.ofEpochMilli(gp.land.getPlantTime(i, j).getTime());
                    time = Duration.between(plantTime, now).toHours();
                    if (gp.land.getState(i, j) == 1 && gp.land.getCropID(i, j) != -1) {
                        if (gp.land.getWaterLevel(i, j) == 3)
                            if (time >= gp.crop.getCropGrowTime(gp.land.getCropID(i, j))) {
                                draw(g2, gp.crop.getCropImage(gp.land.getCropID(i, j), 4), (i + 3) * gp.tileSize, (j + 6) * gp.tileSize - 10);
                            } else
                                draw(g2, gp.crop.getCropImage(gp.land.getCropID(i, j), gp.land.getWaterLevel(i, j)), (i + 3) * gp.tileSize, (j + 6) * gp.tileSize - 10);

                        draw(g2, gp.crop.getCropImage(gp.land.getCropID(i, j), gp.land.getWaterLevel(i, j) + 1),
                                (i + 3) * gp.tileSize, (j + 6) * gp.tileSize - 10);
                    }
                }
            }
    }

    public void draw(Graphics2D g2, BufferedImage image, int col, int row) {
        g2.drawImage(image, col, row, null);
    }

}
