package Model;

import Controller.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.sql.Timestamp;

public class Land {
    public BufferedImage hoe;
    public BufferedImage wateringCan;
    private int[] slot;
    private int[] state;
    private Integer[] cropID;
    private Timestamp[] plantTime;
    private int[] waterLevel;
    private int[] landPrice = new int[32];
    private BufferedImage tilledDirt, sign;
    private int haveLand;
    private boolean[] harvestable = new boolean[32];

    public Land(int[] slot, int[] state, Integer[] cropID, Timestamp[] plantTime, int[] waterLevel) {

        this.slot = slot;
        this.state = state;
        this.cropID = cropID;
        this.plantTime = plantTime;
        this.waterLevel = waterLevel;
//        this.defaultPrice = 500;
//        this.landPrice[1][0] = defaultPrice;
        this.tilledDirt = setup("/Tile/Dirt/Tilled_dirt");
        this.sign = setup("/Tile/Decoration/Sign");
        this.haveLand = countLand();
    }

    public int getSlot(int slot) {
        return this.slot[slot];
    }

    public int getState(int slot) {
        return state[slot];
    }

    public Integer getCropID(int slot) {
        return cropID[slot];
    }

    public Timestamp getPlantTime(int slot) {
        return plantTime[slot];
    }

    public int getWaterLevel(int slot) {
        return waterLevel[slot];
    }

    public int getLandPrice(int slot) {
        return landPrice[slot];
    }

    public void setState(int slot, int state) {
        this.state[slot] = state;
    }

    public void setCropID(int slot, Integer cropID) {
        this.cropID[slot] = cropID;
    }

    public void setPlantTime(int slot, Timestamp plantTime) {
        this.plantTime[slot] = plantTime;
    }

    public void setWaterLevel(int slot, int waterLevel) {
        this.waterLevel[slot] = waterLevel;
    }

    public BufferedImage getSign() {
        return sign;
    }

    public BufferedImage getTilledDirt() {
        return tilledDirt;
    }

    public int getHaveLand() {
        return haveLand;
    }

    public void setHaveLand(int haveLand) {
        this.haveLand = haveLand;
    }

    public void setHarvestable(int slot, boolean harvestable) {
        this.harvestable[slot] = harvestable;
    }

    public boolean getHarvestable(int slot) {
        return harvestable[slot];
    }

    public int countLand() {
        int output = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++)
                if (getState(i * 4 + j) == 1) {
                    output++;
                }
        }
        return output;
    }

    public void setSign() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 4; j++)
                if (slot[i * 4 + j] == haveLand)
                    state[i * 4 + j] = 2;
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

    public BufferedImage getHoe() {
        BufferedImage image = setup("/GUI/hoe");
        return image;
    }
    public BufferedImage getWateringCan(){
        BufferedImage image = setup("/GUI/Watering_can");
        return image;
    }


    public void calculateLandPrice() {
        int delta = 500;
        int lastPrice = 0;
        for (int j = 0; j < 4; j++)
            for (int i = 1; i < 8; i++) {
                this.landPrice[i * 4 + j] = lastPrice + delta;
                delta += 500;
                lastPrice = landPrice[i * 4 + j];
//                System.out.println(landPrice[slot]);
            }
    }
}

