package Controller;

public class PlayerMovement {
    GamePanel gp;
    KeyHandler keyH;

    public PlayerMovement(GamePanel gp, KeyHandler keyH) {


        this.keyH = keyH;
        this.gp = gp;
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed
                || keyH.leftPressed || keyH.rightPressed) { // phải bấm nhân vật mới chuyển động , nếu
            // bỏ thì nhân vật nhảy đổi chân tại chỗ
            if (keyH.upPressed) {
                gp.player.direction = "up";
            } else if (keyH.downPressed) {
                gp.player.direction = "down";
            } else if (keyH.leftPressed) {
                gp.player.direction = "left";
            } else if (keyH.rightPressed) {
                gp.player.direction = "right";
            }
            gp.player.collisionOn = false;
            gp.cChecker.checkPlayerCollision(gp.player);
//            if(gp.player.collisionOn ) System.out.println("true");
//            else System.out.println("false");
            gp.eHandler.checkEvent();
            if (!gp.player.collisionOn) {
                switch (gp.player.direction) {
                    case "up":
                        gp.player.worldY -= gp.player.speed;

                        break;
                    case "down":
                        gp.player.worldY += gp.player.speed;

                        break;
                    case "left":
                        gp.player.worldX -= gp.player.speed;

                        break;
                    case "right":
                        gp.player.worldX += gp.player.speed;

                        break;
                }
            }
            gp.player.screenX = gp.player.worldX - gp.tileSize;
            gp.player.screenY = gp.player.worldY - gp.tileSize;
            gp.player.spriteCounter++;
            if (gp.player.spriteCounter > 12) {
                if (gp.player.spriteNum == 1) {
                    gp.player.spriteNum = 2;
                } else if (gp.player.spriteNum == 2) {
                    gp.player.spriteNum = 1;
                }
                gp.player.spriteCounter = 0;
            }
        }
    }
}
