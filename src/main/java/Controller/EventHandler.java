package Controller;

import java.io.IOException;

public class EventHandler {
    GamePanel gp;
    KeyHandler keyH;
    EventRect eventRect[][];
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
            gp.pet.worldX = gp.player.worldX - 48;
            gp.pet.worldY = gp.player.worldY - 48;
        } else if (gp.currentMap == 1 && (hit(11, 14, "any") ||
                hit(12, 14, "any"))) {
            gp.currentMap = 0;
            gp.player.worldX = gp.tileSize * 11 - 16;
            gp.player.worldY = gp.tileSize * 6;
            gp.pet.worldX = gp.player.worldX - 48;
            gp.pet.worldY = gp.player.worldY - 48;
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
        if ((gp.player.worldX < gp.tileSize * 11 && gp.player.worldX > gp.tileSize * 3) &&
                (gp.player.worldY < gp.tileSize * 10 && gp.player.worldY > gp.tileSize * 6)) {
            //System.out.println(col+ " " + row);
//            if (gp.keyH.ePressed == true) {
            interact(gp.land.getState(gp.dCrop.col, gp.dCrop.row));

//            keyH.ePressed = false;
        }
    }

    public void interact(int state) {
        int col = gp.dCrop.col;
        int row = gp.dCrop.row;
        switch (state) {
            case 0:
                //System.out.println("chưa mua đất");
                break;
            case 1:
                //System.out.println("đã mua đất");
                // MỞ Ô CHỌN HẠT GIỐNG
                if (gp.land.getCropID(col, row) == -1) {
                    gp.gameState = gp.selectSeed;
                } else if (gp.land.getWaterLevel(col, row) < 3) {
                    gp.gameState = gp.selectTool;
                } else if (gp.land.getWaterLevel(col, row) == 3) {
                    if (gp.land.getHarvestable(col, row)) {
//                        System.out.println("thu hoạch được rồi");
                        // thu hoạch cây
                        try {
                            Main.socketHandler.write("harvest=" + gp.land.getCropID(col, row) + "=" + gp.land.getSlot(col, row));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else gp.gameState = gp.selectTool;
                }
                break;
            case 2:
                gp.gameState = gp.buyLand;
                break;
        }
    }

//    public void teleport(int map, int col, int row) {
//    }
}
