package Model;

import Controller.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Crop {
    private int[] cropID;
    private String[] cropName;
    private BufferedImage[][] cropImage = new BufferedImage[20][6];
    private int[] cropGrowTime;
    private int[] cropBuyPrice;
    private int[] cropSellPrice;
    private int[] waterLevel;



    public Crop(int[] cropID, String[] cropName, int[] cropGrowTime, int[] cropBuyPrice, int[] cropSellPrice, int[] waterLevel) {
        this.cropID = cropID;
        this.cropName = cropName;
        this.cropGrowTime = cropGrowTime;
        this.cropBuyPrice = cropBuyPrice;
        this.cropSellPrice = cropSellPrice;
        this.waterLevel = waterLevel;
        loadCropImage();
    }


    public void loadCropImage() {
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 6; j++) {
                cropImage[i][j] = setup("/Crop/" + i + "/" + j);
            }
    }

    public BufferedImage getSelect() {
        BufferedImage image = setup("/Tile/select");
        return image;
    }

    public BufferedImage getEGUI() {
        BufferedImage image = setup("/GUI/E1");
        return image;
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

    public String getCropName(int cropID) {
        return cropName[cropID];
    }

    public BufferedImage getCropImage(int cropID, int waterLevel) {
        if (cropID != -1)
            return cropImage[cropID][waterLevel];
        return null;
    }

    public int getCropGrowTime(int cropID) {
        return cropGrowTime[cropID];
    }

    public int getCropBuyPrice(int cropID) {
        return cropBuyPrice[cropID];
    }

    public int getCropSellPrice(int cropID) {
        return cropSellPrice[cropID];
    }

    public int getWaterLevel(int cropID) {
        return waterLevel[cropID];
    }

}
