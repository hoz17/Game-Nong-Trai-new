package Model;

public class Player extends Entity {
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
    }

    public void getPlayerImage(int genderSkin) {
        this.up1 = setup("/Player/" + genderSkin + "/up1");
        this.up2 = setup("/Player/" + genderSkin + "/up2");
        this.down1 = setup("/Player/" + genderSkin + "/down1");
        this.down2 = setup("/Player/" + genderSkin + "/down2");
        this.left1 = setup("/Player/" + genderSkin + "/left1");
        this.left2 = setup("/Player/" + genderSkin + "/left2");
        this.right1 = setup("/Player/" + genderSkin + "/right1");
        this.right1 = setup("/Player/" + genderSkin + "/right1");
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
}
