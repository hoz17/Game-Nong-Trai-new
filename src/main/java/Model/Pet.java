package Model;

import Controller.GamePanel;
import Controller.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Pet extends Entity {
    GamePanel gp;
    private int petID;

    public Pet() {
    }

    public Pet(int petID, GamePanel gp) {
        this.gp = gp;
        this.petID = petID;
        direction = "down";
        speed = 2;
//        worldX =gp.tileSize * 10 - 16;
//        worldY = gp.tileSize * 12;
    }

    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, 32, 32);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public void getImage(int petID) {
        up1 = setup("/Pet/" + petID + "/up1");
        up2 = setup("/Pet/" + petID + "/up2");
        down1 = setup("/Pet/" + petID + "/down1");
        down2 = setup("/Pet/" + petID + "/down2");
        left1 = setup("/Pet/" + petID + "/left1");
        left2 = setup("/Pet/" + petID + "/left2");
        right1 = setup("/Pet/" + petID + "/right1");
        right2 = setup("/Pet/" + petID + "/right2");
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, 32, 32, null);
//        g2.setColor(Color.black);
//        g2.drawRect(worldX, worldY, solidArea.width, solidArea.height);
    }

    public void setAction() {
        Walk();
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // random tá»« 1 - > 100
            if (i <= 25) {
                direction = "up";
            } else if (25 < i && i <= 50) {
                direction = "down";
            } else if (50 < i && i <= 75) {
                direction = "left";
            } else if (75 < i && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    public void update() {
        collisionOn = false;
        setAction();
        gp.cChecker.checkPetCollision(this);
        gp.cChecker.checkPlayer(this);
//        checkCollision();
        //Náº¿u "collision" lÃ  sai , player cÃ³ thá»ƒ di chuyá»ƒn
        if (!collisionOn) {
            if (direction == "up") {
                worldY -= speed;
            } else if (direction == "down") {
                worldY += speed;
            } else if (direction == "left") {
                worldX -= speed;
            } else if (direction == "right") {
                worldX += speed;
            }
        }
        screenX = worldX;
        screenY = worldY;
        spriteCounter++;
        if (!collisionOn) {
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void Walk() {
        int walkX = (worldX) - (gp.player.worldX);
        int walkY = (worldY) - (gp.player.worldY);
        if (walkX > 0 && walkX > gp.tileSize * 2) {
            direction = "left";
        } else if (walkX < 0 && walkX < gp.tileSize * -2) {
            direction = "right";
        } else if (walkY > 0 && walkY > gp.tileSize * 2) {
            direction = "up";
        } else if (walkY < 0 && walkY < gp.tileSize * -2) {
            direction = "down";
        }
    }
}
