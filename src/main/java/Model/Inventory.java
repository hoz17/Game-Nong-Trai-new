package Model;

public class Inventory {
    private int[] cropID;
    private int[] cropAmount;
    private int[] seedAmount;

    public Inventory(int[] cropID, int[] cropAmount, int[] seedAmount) {
        this.cropID = cropID;
        this.cropAmount = cropAmount;
        this.seedAmount = seedAmount;

    }

//    public Inventory(int userID) {
//        this.userID = userID;
//    }
//
//    public int getInventoryID() {
//        return inventoryID;
//    }
//
//    public int getUserID() {
//        return userID;
//    }

    public int[] getCropID() {
        return cropID;
    }

    public int getCropAmount(int cropID) {
        return cropAmount[cropID];
    }

    public int getSeedAmount(int cropID) {
        return seedAmount[cropID];
    }

//    public void setInventoryID(int inventoryID) {
//        this.inventoryID = inventoryID;
//    }

//    public void setUserID(int userID) {
//        this.userID = userID;
//    }

//    public void setCropID(int slot,int cropID) {
//        this.cropID[slot] = cropID;
//    }

    public void setCropAmount(int cropID, int cropAmount) {
        this.cropAmount[cropID] = cropAmount;
    }

    public void setSeedAmount(int cropID, int seedAmount) {
        this.seedAmount[cropID] = seedAmount;
    }
}
