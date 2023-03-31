package Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    private String playerName;
    private int money;
    private int genderSkin;
    private int petID;
    private int poiterX, pointerY;
    public boolean collisionOn = false;

    public Player(String playerName, int money, int genderSkin, int petID) {
        this.playerName = playerName;
        this.money = money;
        this.genderSkin = genderSkin;
        this.petID = petID;
        this.direction = "up";
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

    public int getGenderSkin() {
        return genderSkin;
    }

    public int getPetID() {
        return petID;
    }

    public void setMoney(int money) {
        this.money = money;
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
    }
}
