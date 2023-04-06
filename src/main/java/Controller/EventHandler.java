package Controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class EventHandler {
    public int posX, posY, targetY;
    GamePanel gp;
    KeyHandler keyH;
    EventRect eventRect[][];
    BufferedImage effect;
    boolean drawEffect = false;
//    SocketHandler socketHandler;

    public EventHandler(GamePanel gp, KeyHandler keyH) {
        this.keyH = keyH;
        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 0;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 48;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent() {
        if (gp.currentMap == 0 && (hit(10, 5, "any") ||
                hit(11, 5, "any") || hit(12, 5, "any") || hit(9, 5, "any"))) {
//            System.out.println(System.nanoTime());
            gp.currentMap = 1;
            gp.player.worldX = gp.tileSize * 11 + 16;
            gp.player.worldY = gp.tileSize * 13;
            if (gp.player.getPetID() != 0) {
                gp.pet.worldX = gp.player.worldX - 48;
                gp.pet.worldY = gp.player.worldY - 48;
            }
        } else if (gp.currentMap == 1 && (hit(11, 14, "any") ||
                hit(12, 14, "any"))) {
            gp.currentMap = 0;
            gp.player.worldX = gp.tileSize * 11 - 16;
            gp.player.worldY = gp.tileSize * 6;
            if (gp.player.getPetID() != 0) {
                gp.pet.worldX = gp.player.worldX - 48;
                gp.pet.worldY = gp.player.worldY - 48;
            }
        } else if (gp.currentMap == 0 && (hit(15, 7, "any") || hit(16, 7, "any"))) {

        }
//         else if (gp.currentMap == 1 && keyH.ePressed == true) {
//            gp.crop.event(gp, keyH);
//        }
//        else if (gp.currentMap == 0 && (hit(1, 5, "up") ||
//                hit(2, 5, "up"))) {
//            gp.player.collisionOn = true;
//        } else if (worldBorder(gp.currentMap)) {
//            gp.player.collisionOn = true;
//        } else gp.player.collisionOn = false;
    }

//    public boolean worldBorder(int currentMap) {
//        boolean hit = false;
//        switch (gp.player.direction) {
//            case "right":
//                if (gp.player.worldX > gp.tileSize * 22) {
//                    hit = true;
//                }
//                break;
//            case "left":
//                if (gp.player.worldX < gp.tileSize) {
//                    hit = true;
//                }
//                break;
//            case "up":
//                if (currentMap == 0) {
//                    if (gp.player.worldY < gp.tileSize * 6 - 15) {
//                        if (gp.player.worldX < gp.tileSize * 9 || gp.player.worldX > gp.tileSize * 12) {
//                            hit = true;
//                        }
//                    }
//                }
//                break;
//            case "down":
//                if (currentMap == 0) {
//                    if (gp.player.worldY > gp.tileSize * 13 + 1) {
//                        hit = true;
//                    }
//                }
//                break;
//        }
//        return hit;
//    }

    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = false;
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;
        if (gp.player.solidArea.intersects(eventRect[col][row])) {
            if (gp.player.direction.contentEquals(reqDirection) ||
                    reqDirection.contentEquals("any")) {
                hit = true;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
        return hit;
    }

    public void event(GamePanel gp) {
        if (gp.currentMap == 0) {
            if (hit(15, 7, "any") || hit(16, 7, "any")) {
                int random = new Random().nextInt(5);
                gp.ui.message1 = gp.ui.message[random];
                gp.gameState = gp.tradeState;
            }
        } else if (gp.currentMap == 1) {
            if ((gp.player.screenX < gp.tileSize * 11 && gp.player.screenX > gp.tileSize * 3) &&
                    (gp.player.screenY < gp.tileSize * 10 && gp.player.screenY > gp.tileSize * 6)) {
                //System.out.println(col+ " " + row);
//            if (gp.keyH.ePressed == true) {

                interact(gp.land.getState(gp.dCrop.col * 4 + gp.dCrop.row));

//            keyH.ePressed = false;
            }
        }
    }

    public void interact(int state) {
        int col = gp.dCrop.col;
        int row = gp.dCrop.row;
        int slot = col * 4 + row;
//        System.out.println(slot);

        switch (state) {
            case 0:
                //System.out.println("chưa mua đất");
                break;
            case 1:
                //System.out.println("đã mua đất");
                // MỞ Ô CHỌN HẠT GIỐNG
                if (gp.land.getCropID(slot) == -1) {
                    gp.gameState = gp.selectSeed;
                } else if (gp.land.getWaterLevel(slot) < 3) {
                    gp.gameState = gp.selectTool;
                } else if (gp.land.getWaterLevel(slot) == 3) {
                    if (gp.land.getHarvestable(slot)) {
//                        System.out.println("thu hoạch được rồi");
                        // thu hoạch cây
                        effect = gp.crop.getCropImage(gp.land.getCropID(slot), 4);
                        posX = (col + 3) * gp.tileSize;
                        posY = (row + 6) * gp.tileSize;
                        targetY = posY - 28;
                        drawEffect = true;
                        try {
                            Main.socketHandler.write("harvest=" + gp.land.getCropID(slot) + "=" + gp.land.getSlot(slot));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        gp.land.setHarvestable(slot, false);
                    } else gp.gameState = gp.selectTool;
                }
                break;
            case 2:
                gp.gameState = gp.buyLand;
                break;
        }
    }

    public void draw(Graphics2D g2) {
        if (drawEffect) {
            draw(g2, effect, posX, posY);
            posY -= 2;
        }
        if (posY == targetY) {
            drawEffect = false;
        }
    }

    public void draw(Graphics2D g2, BufferedImage image, int x, int y) {
        g2.drawImage(image, x, y, null);
    }

//    public void teleport(int map, int col, int row) {
//    }
}
