package Model;

public class Tile {
    public boolean[][][] collision = new boolean[2][24][16];


    public void setCollision() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 24; j++)
                for (int k = 0; k < 16; k++) {
                    if (j == 0 || j == 23 || k == 0 || k == 15) {
                        collision[i][j][k] = true;
                    } else collision[i][j][k] = false;
                }
        }
        for (int j = 0; j < 24; j++) {
            if (j < 9 || j > 12) {
                collision[0][j][5] = true;
            }
        }
    }
}
