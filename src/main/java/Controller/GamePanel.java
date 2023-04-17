package Controller;

import Model.*;
import View.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //Game settings
    public final int originalTileSize = 16; //16x16 tile
    public final int maxScreenCol = 22;
    public final int maxScreenRow = 14;
    public final int maxWorldCol = 24;
    public final int maxWorldRow = 16;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int selectSeed = 3;
    public final int selectTool = 4;
    public final int dialogueState = 5;
    public final int optionsState = 6;
    public final int inventoryState = 7;
    public final int shopBuyState = 8;
    public final int buyLand = 9;
    public final int notificationState = 10;
    public final int shopSellState = 11;
    public final int tradeState = 12;
    public final int chatState = 13;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;// 48x48 tile
    public final int screenWidth = tileSize * maxScreenCol; // 1056 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 672 pixels
    //Game state
    public int gameState;
    public boolean fullScreenOn = false;
    //    static String username, password;
//    Scanner sc = new Scanner(System.in);
//    public Main main = new Main();
    public LoginForm loginForm;

    // Draw component
    public DrawMap dMap = new DrawMap();
    public DrawLand dLand = new DrawLand(this);
    public DrawCrop dCrop = new DrawCrop(this);


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
    public CollisionChecker cChecker = new CollisionChecker(this);
    public UI ui = new UI(this);

    //System setting
    Tile tile = new Tile(this);

    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(new Color(0x91D8F2));
        addKeyListener(keyH); // phân tích nút
        setDoubleBuffered(true);
        setFocusable(true); // nhận nút
        loginForm = new LoginForm(this);
        gameState = titleState;
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // 0.1666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (Main.gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 0) {
                update();
                repaint();
//                System.out.println(player.worldX + " " + player.worldY);
                delta--;
            }
        }

    }

    public void update() {
        if (gameState == playState) {
            if (player != null) {
                playerMovement.update();
//            //NPC
                if (player.getPetID() != 0 && pet != null) {
                    pet.update();
                }
            }
        } else if (gameState == pauseState) {

        }
    }

//    public void openForm() {
//        loginForm = new LoginForm();
//    }

//    public void startGameThread() {
//        gameThread = new Thread(this);
//        gameThread.start();
//    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        dMap.draw(g2, currentMap);
        dMap.drawArrow(g2, currentMap);
        if (currentMap == 0)
            shop.draw(g2);
        if (gameState != titleState) {
            if (player != null) {
                if (currentMap == 1) {
                    dLand.update(g2);
                    dCrop.update(g2);
                    dCrop.checkSelect(g2);
                    eHandler.draw(g2);
                }
                dCrop.drawEGUI(g2);
                player.draw(g2);
                if (player.getPetID() != 0) {
                    pet.draw(g2);
                }
            }
        }
        ui.draw(g2);
        g2.dispose();
    }

    public void setUpGame() {
//        gameState = titleState;
        shop = new Shop();
        player.getPlayerImage(player.getGenderSkin());
        if (player.getPetID() != 0) {
//            pet = new Pet(player.getPetID(), this);
            pet.getImage(player.getPetID());
            aSetter.setPet();
        }
        //set player position
        aSetter.setPlayer();
//        clock = true;
//        gameState = playState;
        tile.setCollision();
//        Main.gameThread.start();
//        if(clock==true) System.out.println("setupgame");
//        gameThread.start();
//        Main.socketHandler.startGameThread();
    }
}
