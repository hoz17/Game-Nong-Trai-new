package Controller;

public class EventHandler {
    GamePanel gp;
    KeyHandler keyH;
    EventRect eventRect[][];
    public EventHandler(GamePanel gp, KeyHandler keyH) {
        this.keyH = keyH;
        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 0;
            eventRect[col][row].y = 0;
            eventRect[col][row].width = 48;
            eventRect[col][row].height = 48;
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
        if (gp.currentMap == 0 && (hit(10, 5, "any") == true || hit(11, 5, "any") == true || hit(12, 5, "any") == true || hit(9, 5, "any") == true)) {
//            System.out.println(System.nanoTime());
            gp.currentMap = 1;
            gp.player.worldX = gp.tileSize * 11 + 16;
            gp.player.worldY = gp.tileSize * 13;
        } else if (gp.currentMap == 1 && (hit(11, 14, "any") == true || hit(12, 14, "any") == true)) {
            gp.currentMap = 0;
            gp.player.worldX = gp.tileSize * 11 - 16;
            gp.player.worldY = gp.tileSize * 6;
        }
//         else if (gp.currentMap == 1 && keyH.ePressed == true) {
//            gp.crop.event(gp, keyH);
//        }
        else  if (gp.currentMap==0&&(hit(1,5,"up")==true)){
            gp.player.collisionOn = true;
        }
    }
    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = false;
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;
        if (gp.player.solidArea.intersects(eventRect[col][row])) {
            if (
                    gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
        return hit;
    }
    public void teleport(int map, int col, int row) {
    }
}
