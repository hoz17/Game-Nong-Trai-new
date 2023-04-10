package Model;

import Controller.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    public boolean collisionOn = false;
    private String playerName;
    private int money;
    private int genderSkin;
    private int petID;
    private int poiterX, pointerY;

    public Player(String playerName, int money, int genderSkin, int petID) {
        this.playerName = playerName;
        this.money = money;
        this.genderSkin = genderSkin;
        this.petID = petID;
        this.direction = "up";
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
//        solidArea = new Rectangle(10, 18, 28, 30);
    }

    public void getPlayerImage(int genderSkin) {
        this.up1 = setup("/Player/" + genderSkin + "/up1");
        this.up2 = setup("/Player/" + genderSkin + "/up2");
        this.down1 = setup("/Player/" + genderSkin + "/down1");
        this.down2 = setup("/Player/" + genderSkin + "/down2");
        this.left1 = setup("/Player/" + genderSkin + "/left1");
        this.left2 = setup("/Player/" + genderSkin + "/left2");
        this.right1 = setup("/Player/" + genderSkin + "/right1");
        this.right2 = setup("/Player/" + genderSkin + "/right2");
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getGenderSkin() {
        return genderSkin;
    }

    public int getPetID() {
        return petID;
    }

    public void setPetID(int petID) {
        this.petID = petID;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (this.direction) {
            case "up":
                if (this.spriteNum == 1) {
                    image = this.up1;
                }
                if (this.spriteNum == 2) {
                    image = this.up2;
                }
                break;
            case "down":
                if (this.spriteNum == 1) {
                    image = this.down1;
                }
                if (this.spriteNum == 2) {
                    image = this.down2;
                }
                break;
            case "left":
                if (this.spriteNum == 1) {
                    image = this.left1;
                }
                if (this.spriteNum == 2) {
                    image = this.left2;
                }
                break;
            case "right":
                if (this.spriteNum == 1) {
                    image = this.right1;
                }
                if (this.spriteNum == 2) {
                    image = this.right2;
                }
                break;
            default:
                break;
        }
        g2.drawImage(image, screenX, screenY, 48, 48, null);
        g2.setFont(new Font("Bahnschrift",Font.BOLD,20));
        g2.drawString(playerName,screenX,screenY);
//        g2.setColor(Color.BLACK);
//        g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
//        g2.drawImage(image, worldX, worldY, 48, 48, null);
    }
    public void loadComponent(GamePanel  gp){
        gp.aSetter.setPlayer();
    }
}
