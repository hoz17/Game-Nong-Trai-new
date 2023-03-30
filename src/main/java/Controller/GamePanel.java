package Controller;

import Model.*;
import View.LoginForm;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

public class GamePanel extends JPanel implements Runnable {
    public final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    public boolean fullScreenOn = false;
    public final int tileSize = originalTileSize * scale;// 48x48 tile
    public final int maxScreenCol = 22;
    public final int maxScreenRow = 14;
    public final int screenWidth = tileSize * maxScreenCol; // 1056 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 672 pixels

    //System setting
    Thread gameThread;
    //    static String username, password;
//    Scanner sc = new Scanner(System.in);
//    public Main main = new Main();
    public LoginForm loginForm;


    //GameState
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int selectSeed = 3;
    public final int selectTool = 4;
    public final int dialogueState = 5;
    public final int optionsState = 6;
    public final int inventoryState = 7;
    public final int tradeState = 8;
    public final int buyLand = 9;

    //Model
    public Crop crop;
    public Player player = null;
    public Inventory inventory;
    public Land land;
    public Pet pet;
    public Shop shop;

    //Setup Game
//    public


    public int FPS = 60;

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // 0.1666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 0) {
                update();
                repaint();
                delta--;
            }
        }

    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(0x91D8F2));
        //  this.addKeyListener(keyH); // phân tích nút
        this.setDoubleBuffered(true);
        this.setFocusable(true); // nhận nút
        loginForm = new LoginForm(this);
    }

    public void update() {
        if (gameState == playState) {
//            playerMovement.update();
//            //NPC
//            if (this.player.getPetID() != 0) {
//                petMovement.update();
//            }
        } else if (gameState == pauseState) {

        }
    }

//    public void openForm() {
//        loginForm = new LoginForm();
//    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setUpGame() {
        gameState = titleState;
        this.player.getPlayerImage(this.player.getGenderSkin());
        //set player position
        //set pet
        //set
    }
}
