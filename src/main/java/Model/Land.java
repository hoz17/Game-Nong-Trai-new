package Model;

import Controller.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.sql.Timestamp;

public class Land {
    private int slot[][];
    private int state[][];
    private int cropID[][];
    private Timestamp plantTime[][];
    private int waterLevel[][];
    private int landPrice[][] = new int[8][4];
    private BufferedImage tilledDirt, sign;
    private int haveLand;

    public int getSlot(int i, int j) {
        return slot[i][j];
    }

    public int getState(int i, int j) {
        return state[i][j];
    }

    public int getCropID(int i, int j) {
        return cropID[i][j];
    }

    public Timestamp getPlantTime(int i, int j) {
        return plantTime[i][j];
    }

    public int getWaterLevel(int i, int j) {
        return waterLevel[i][j];
    }

    public int getLandPrice(int i, int j) {
        return landPrice[i][j];
    }

    public void setState(int i, int j, int state) {
        this.state[i][j] = state;
    }

    public void setCropID(int i, int j, int cropID) {
        this.cropID[i][j] = cropID;
    }

    public void setPlantTime(int i, int j, Timestamp plantTime) {
        this.plantTime[i][j] = plantTime;
    }

    public void setWaterLevel(int i, int j, int waterLevel) {
        this.waterLevel[i][j] = waterLevel;
    }

    public BufferedImage getSign() {
        return sign;
    }

    public BufferedImage getTilledDirt() {
        return tilledDirt;
    }


    public Land(int[][] slot, int[][] state, int[][] cropID, Timestamp[][] plantTime, int[][] waterLevel) {

        this.slot = slot;
        this.state = state;
        this.cropID = cropID;
        this.plantTime = plantTime;
        this.waterLevel = waterLevel;
//        this.defaultPrice = 500;
//        this.landPrice[1][0] = defaultPrice;
        this.tilledDirt = setup("/Tile/Dirt/Tilled_dirt");
        this.sign = setup("/Tile/Decoration/Sign");
    }

    public int getHaveLand() {
        return haveLand;
    }

    public void setHaveLand(int haveLand) {
        this.haveLand = haveLand;
    }

    public int countLand() {
        int output = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++)
                if (getState(i, j) == 1) {
                    output++;
                }
        }
        return output;
    }

    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, 48, 48);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }


    public void calculateLandPrice() {
        int delta = 500;
        int lastPrice = 0;
        for (int j = 0; j < 4; j++)
            for (int i = 1; i < 8; i++) {
                this.landPrice[i][j] = lastPrice + delta;
                delta += 500;
                lastPrice = landPrice[i][j];
//                System.out.println(landPrice[i][j]);
            }
    }
}

