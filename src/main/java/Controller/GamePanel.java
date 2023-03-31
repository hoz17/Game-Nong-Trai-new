package Controller;

import Model.*;
import View.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    public boolean fullScreenOn = false;
    public final int tileSize = originalTileSize * scale;// 48x48 tile
    public final int maxScreenCol = 22;
    public final int maxScreenRow = 14;
    public final int maxWorldCol = 24;
    public final int maxWorldRow = 16;
    public final int screenWidth = tileSize * maxScreenCol; // 1056 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 672 pixels

    //System setting
    Thread gameThread;
    //    static String username, password;
//    Scanner sc = new Scanner(System.in);
//    public Main main = new Main();
    public LoginForm loginForm;


    // Draw component
    public DrawMap dMap = new DrawMap();

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
    public Player player;
    public Inventory inventory;
    public Land land;
    public Pet pet;
    public Shop shop = new Shop();

    //Setup Game
    public AssetSetter aSetter = new AssetSetter(this);
    public boolean clock = false;
    public int currentMap = 0;
    public int FPS = 60;
    public KeyHandler keyH = new KeyHandler(this);
    public PlayerMovement playerMovement = new PlayerMovement(this, keyH);
    public EventHandler eHandler = new EventHandler(this, keyH);

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // 0.1666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null && clock == true) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 0) {
                update();
                repaint();
//                System.out.println(this.player.worldX + " " + this.player.worldY);
                delta--;
            }
        }

    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(0x91D8F2));
        this.addKeyListener(keyH); // phân tích nút
        this.setDoubleBuffered(true);
        this.setFocusable(true); // nhận nút
        loginForm = new LoginForm(this);
    }

    public void update() {
        if (gameState == playState) {
            playerMovement.update();
//            //NPC
            if (this.player.getPetID() != 0) {
                pet.update();
            }
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        dMap.draw(g2, this.currentMap);
        if (currentMap == 0)
            shop.draw(g2);
        if (this.player != null) {
            this.player.draw(g2);
            if (this.player.getPetID() != 0) {
                pet.draw(g2);
            }
        }
    }

    public void setUpGame() {
        gameState = titleState;
        this.shop = new Shop();
        this.player.getPlayerImage(this.player.getGenderSkin());
        if (this.player.getPetID() != 0) {
            this.pet = new Pet(this.player.getPetID(), this);
            this.pet.getImage(this.player.getPetID());
            this.aSetter.setPet();
        }
        //set player position
        this.aSetter.setPlayer();
        //set shop
        this.aSetter.setShop();
        this.clock = true;
        this.gameState = this.playState;
//        if(clock==true) System.out.println("setupgame");
        run();
    }
}
