package Model;

import Controller.GamePanel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Tile {
    public boolean[][][] collision = new boolean[2][24][16];
    GamePanel gp;

    public Tile(GamePanel gp) {
        this.gp = gp;
    }

    public void setCollision() {
        loadMap("/Map/Map0.txt", 0);
        loadMap("/Map/Map1.txt", 1);
    }

    public void loadMap(String mapPath, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    if (num == 0) {
                        collision[map][col][row] = false;
//                        System.out.println(col + " " + row + " false");
                    } else if (num == 1) {
                        collision[map][col][row] = true;
//                        System.out.println(col + " " + row + " true");
                    }
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void setCollision() {
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 24; j++)
//                for (int k = 0; k < 16; k++) {
//                    if (j == 0 || j == 23 || k == 0 || k == 15) {
//                        collision[i][j][k] = true;
//                    } else collision[i][j][k] = false;
//                }
//        }
//        for (int j = 0; j < 24; j++) {
//            if (j < 9 || j > 12) {
//                collision[0][j][5] = true;
//            }
//        }
//    }
}
