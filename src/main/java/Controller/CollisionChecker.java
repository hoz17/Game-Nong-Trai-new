package Controller;

import Model.Pet;
import Model.Player;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkPetCollision(Pet entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x + gp.tileSize;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width + gp.tileSize;
        int entityTopWorldY = entity.worldY + entity.solidArea.y + gp.tileSize;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height + gp.tileSize;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

//        int tileNum1, tileNum2, tileNum3;
        try {
            switch (entity.direction) {
                case "up":
                    entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
//                    tileNum1 = gp.tile.mapTileNum[entityLeftCol][entityTopRow];
//                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                    if (gp.tile.collision[gp.currentMap][entityLeftCol][entityTopRow] || gp.tile.collision[gp.currentMap][entityRightCol][entityTopRow]) {
//                        System.out.println("true");
                        entity.collisionOn = true;
                    }
                    break;
                case "down":
                    entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
//                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
//                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                    if (gp.tile.collision[gp.currentMap][entityLeftCol][entityBottomRow] || gp.tile.collision[gp.currentMap][entityRightCol][entityBottomRow]) {
                        entity.collisionOn = true;
                    }
                    break;
                case "left":
                    entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
//                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
//                    tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                    if (gp.tile.collision[gp.currentMap][entityLeftCol][entityTopRow] || gp.tile.collision[gp.currentMap][entityLeftCol][entityBottomRow]) {
                        entity.collisionOn = true;
                    }
                    break;
                case "right":
                    entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
//                    tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
//                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                    if (gp.tile.collision[gp.currentMap][entityRightCol][entityTopRow] || gp.tile.collision[gp.currentMap][entityRightCol][entityBottomRow]) {
                        entity.collisionOn = true;
                    }
                    break;

            }

        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public boolean checkPlayer(Pet entity) {
        boolean contactPlayer = false;
        // Get entity solid are position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        // Get the object solid are position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x - gp.tileSize;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y - gp.tileSize;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;

        }
        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        return contactPlayer;
    }

    public void checkPlayerCollision(Player entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

//        int tileNum1, tileNum2, tileNum3;
        try {
            switch (entity.direction) {
                case "up":
                    entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
//                    tileNum1 = gp.tile.mapTileNum[entityLeftCol][entityTopRow];
//                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                    if (gp.tile.collision[gp.currentMap][entityLeftCol][entityTopRow] || gp.tile.collision[gp.currentMap][entityRightCol][entityTopRow]) {
//                        System.out.println("true");
                        entity.collisionOn = true;
                    }
                    break;
                case "down":
                    entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
//                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
//                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                    if (gp.tile.collision[gp.currentMap][entityLeftCol][entityBottomRow] || gp.tile.collision[gp.currentMap][entityRightCol][entityBottomRow]) {
                        entity.collisionOn = true;
                    }
                    break;
                case "left":
                    entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
//                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
//                    tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                    if (gp.tile.collision[gp.currentMap][entityLeftCol][entityTopRow] || gp.tile.collision[gp.currentMap][entityLeftCol][entityBottomRow]) {
                        entity.collisionOn = true;
                    }
                    break;
                case "right":
                    entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
//                    tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
//                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                    if (gp.tile.collision[gp.currentMap][entityRightCol][entityTopRow] || gp.tile.collision[gp.currentMap][entityRightCol][entityBottomRow]) {
                        entity.collisionOn = true;
                    }
                    break;

            }

        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
}
