package Controller;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setPlayer() {
        gp.player.worldX = gp.tileSize * 11 - 16;
        gp.player.worldY = gp.tileSize * 13 - 10;
        gp.player.screenX = gp.player.worldX - 48;
        gp.player.screenY = gp.player.worldY - 48;
        gp.player.speed = 3;
        gp.player.direction = "up";
    }

    public void setPet() {
        gp.pet.worldX = gp.tileSize * 10 - 16;
        gp.pet.worldY = gp.tileSize * 11;
    }


}
