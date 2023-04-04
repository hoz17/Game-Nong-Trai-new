package View;

import Controller.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawLand {
    GamePanel gp;

    public DrawLand(GamePanel gp) {
        this.gp = gp;
    }

    public void update(Graphics2D g2) {
        gp.land.setSign();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 4; j++) {
                if (gp.land.getState(i * 4 + j) == 1) {
                    draw(g2, gp.land.getTilledDirt(), (i + 3) * gp.tileSize, (j + 6) * gp.tileSize);
                }
                if (gp.land.getState(i * 4 + j) == 2) {
                    draw(g2, gp.land.getSign(), (i + 3) * gp.tileSize, (j + 6) * gp.tileSize);
                }
            }
    }

    public void draw(Graphics2D g2, BufferedImage image, int col, int row) {
        g2.drawImage(image, col, row, null);
    }
}
