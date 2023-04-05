package View;

import Controller.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;

public class DrawCrop {
    public int worldX, worldY, pointerX, pointerY, col, row, effectX, effectY;
    int count = 0;
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
                if (gp.land.getCropID(i * 4 + j) != -1) {
                    plantTime = Instant.ofEpochMilli(gp.land.getPlantTime(i * 4 + j).getTime());
                    time = Duration.between(plantTime, now).toHours(); //lấy thời gian đã trồng rồi chuyển sang giờ
                    if (gp.land.getState(i * 4 + j) == 1 && gp.land.getCropID(i * 4 + j) != -1) {
                        if (gp.land.getWaterLevel(i * 4 + j) == 3) { // xét trường hợp khi waterlevel = 3
                            if (time >= gp.crop.getCropGrowTime(gp.land.getCropID(i * 4 + j))) {
                                gp.land.setHarvestable(i * 4 + j, true);
                                draw(g2, gp.crop.getCropImage(gp.land.getCropID(i * 4 + j), 4), (i + 3) * gp.tileSize, (j + 6) * gp.tileSize - 10);
                            } else {
                                gp.land.setHarvestable(i * 4 + j, false);
                                draw(g2, gp.crop.getCropImage(gp.land.getCropID(i * 4 + j), gp.land.getWaterLevel(i * 4 + j)), (i + 3) * gp.tileSize, (j + 6) * gp.tileSize - 10);
                            }
                        } else {
                            gp.land.setHarvestable(i * 4 + j, false);
                            draw(g2, gp.crop.getCropImage(gp.land.getCropID(i * 4 + j), gp.land.getWaterLevel(i * 4 + j) + 1),
                                    (i + 3) * gp.tileSize, (j + 6) * gp.tileSize - 10);
                        }
                    }
                }
            }
    }

    public void checkSelect(Graphics2D g2) {
        if (gp.currentMap == 1 &&
                (gp.player.screenX < gp.tileSize * 11 && gp.player.screenX > gp.tileSize * 3) &&
                (gp.player.screenY < gp.tileSize * 10 && gp.player.screenY > gp.tileSize * 6)) {
            worldX = (gp.player.screenX) / gp.tileSize;
            worldY = (gp.player.screenY) / gp.tileSize;
            pointerX = worldX * gp.tileSize;
            pointerY = worldY * gp.tileSize;
            col = worldX - 3;
            row = worldY - 6;
            //System.out.println(((pointerX/gp.tileSize)-3)+" "+((pointerY/gp.tileSize)-6));
            if (gp.land.getState(col * 4 + row) != 0) {
                draw(g2, gp.crop.getSelect(), pointerX, pointerY);
                draw(g2, gp.crop.getEGUI(), gp.player.screenX + gp.tileSize, gp.player.screenY - gp.tileSize);
            }
        }
//        else if (gp.currentMap == 0) {
//            if (gp.eHandler.hit(15, 7, "any") || gp.eHandler.hit(16, 7, "any")) {
//                draw(g2, gp.crop.getEGUI(), gp.player.screenX + gp.tileSize, gp.player.screenY - gp.tileSize);
//            }
//        }
    }

    public void drawEGUI(Graphics2D g2) {
        if (gp.currentMap == 0)
            if (gp.eHandler.hit(15, 7, "any") || gp.eHandler.hit(16, 7, "any")) {
                draw(g2, gp.crop.getEGUI(), gp.player.screenX + gp.tileSize, gp.player.screenY - gp.tileSize);
            }
    }

    public void draw(Graphics2D g2, BufferedImage image, int col, int row) {
        g2.drawImage(image, col, row, null);
    }
//public void drawEGUI(int x,int y){
//        draw(g2,);
//}
}
