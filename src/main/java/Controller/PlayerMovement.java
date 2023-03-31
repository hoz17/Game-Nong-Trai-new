package Controller;

public class PlayerMovement {
    GamePanel gp;
    KeyHandler keyH;

    public PlayerMovement(GamePanel gp, KeyHandler keyH) {


        this.keyH = keyH;
        this.gp = gp;
    }

    public void update() {
        if (keyH.upPressed == true || keyH.downPressed == true
                || keyH.leftPressed == true || keyH.rightPressed == true) { // phải bấm nhân vật mới chuyển động , nếu
            // bỏ thì nhân vật nhảy đổi chân tại chỗ
            if (keyH.upPressed == true) {
                gp.player.direction = "up";
            } else if (keyH.downPressed == true) {
                gp.player.direction = "down";
            } else if (keyH.leftPressed == true) {
                gp.player.direction = "left";
            } else if (keyH.rightPressed == true) {
                gp.player.direction = "right";
            }
            gp.eHandler.checkEvent();
            if (gp.player.collisionOn == false) {
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
            gp.player.screenX = gp.player.worldX - 48;
            gp.player.screenY = gp.player.worldY - 48;
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
