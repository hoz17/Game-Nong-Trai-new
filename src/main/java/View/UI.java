package View;

import Controller.GamePanel;
import Model.Message;
import Model.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class UI {
    public int titleScreenState = 0;// 0 : the first screen ; 1 : the second .....
    public String currentDialogue = "";
    public int commandNum = 0;
    public String notification = "";
    public boolean notificationOn = false;
    public int indexSeed = 4;
    public int selectS = 0;
    public int posSeed;
    public int playerlotCol = 0;
    public int playerlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    public int buyNumber = 0;
    public int sellNumber = 0;
    public int selectCol;
    public int cropID = 0;
    public int subState = 0;
    public int selectTool = 0;
    public int inventoryCol = 0;
    public int inventoryRow = 0;
    public String message1 = "";
    public String[] message = {"Xin chào, bạn cần gì ?",
            "Thời tiết hôm nay thật đẹp !",
            "Muốn mua vài món đồ không ?",
            "Ngươi đến xem hàng hay xem ta ?",
            "...",
            "Ta vừa nhập được vài món hàng mới,\ncó muốn xem không ?"
    };
    public ArrayList<Message> chatMessage = new ArrayList<>();
    public ArrayList<Player> leaderboard = new ArrayList<>();
    public int messageCountdown = 0;
    GamePanel gp;
    Font arial_40, arial_80B, comfortaa_20;
    Graphics2D g2;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        comfortaa_20 = new Font("Comfortaa", Font.PLAIN, 20);
        defaultMessage();
    }

    public void defaultMessage() {
        chatMessage.add(new Message("", ""));
        chatMessage.add(new Message("", ""));
        chatMessage.add(new Message("", ""));
        chatMessage.add(new Message("", ""));
        chatMessage.add(new Message("", ""));
        chatMessage.add(new Message("", ""));
        chatMessage.add(new Message("", ""));
        chatMessage.add(new Message("", ""));
        chatMessage.add(new Message("", ""));
        chatMessage.add(new Message("", ""));
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        //Title State
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        } else if (gp.gameState == gp.pauseState) {
            //Pause State
            drawPauseScreen();
        } else if (gp.gameState == gp.dialogueState) {
            //Dialouge State
//            drawDialougeScreen();
//            subState = 0;
        } else if (gp.gameState == gp.optionsState) {
            //Option State
//            drawmessenger();
            drawOptionScreen();
        } else if (gp.gameState == gp.selectSeed) {
            //Seed Selection State
            drawSeedSelection();
        } else if (gp.gameState == gp.inventoryState) {
            drawInventoryState();
        } else if (gp.gameState == gp.tradeState) {
            tradeState();
        } else if (gp.gameState == gp.shopSellState) {
            shopSellState();
        } else if (gp.gameState == gp.shopBuyState) {
            shopBuyState();
        } else if (gp.gameState == gp.buyLand) {
            //Buy Land State
            buyLandState();
        } else if (gp.gameState == gp.selectTool) {
            //Select Tool State
            drawSelectTool();
        } else if (gp.gameState == gp.notificationState) {
            drawNotification();
        } else if (gp.gameState == gp.chatState) {
            drawChat();

        } else if (gp.gameState == gp.playState) {
            //Play State
            if (chatMessage != null)
                drawChatFlow(0, gp.tileSize * 9);
        } else if (gp.gameState == gp.duplicateLoginState) {
            drawDuplicateLogin();
        } else if (gp.gameState == gp.leaderBoardState) {
            drawLeaderBoard();
        } else if (gp.gameState == gp.keymapState) {
            options_control();
        } else if (gp.gameState == gp.logoutState) {
            options_endGameConfirmation();
        }

    }

    public void drawTitleScreen() {
        if (titleScreenState == 0) {
            //Background
            //g2.setColor(new Color(0, 0, 0));
            try {
                g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/thumb2.png")), 0, 0, 1056, 672, null);
            } catch (IOException e) {
            }

            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 66F));
            String text = "Game Nông Trại";
            int x = getXCenterText(text);
            int y = gp.tileSize * 3;
            //Shadow
            g2.setColor(Color.gray);
            g2.drawString(text, x + 5, y + 5);
            //Main Color
            g2.setColor(Color.black);
            g2.drawString(text, x, y);
            //Image
            x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
            y += gp.tileSize * 2;
//            g2.drawImage(gp.player.face, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
            //Menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 46F));

            text = "BẮT ĐẦU";
            x = getXCenterText(text);
            y += gp.tileSize * 3.5;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            String text2 = "Thoát";
            x = getXCenterText(text);
            y += gp.tileSize + 4;
            g2.drawString(text2, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
//        else if (titleScreenState == 1) {
//            //Background
//            g2.setColor(new Color(0, 0, 0));
//            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
//            //Class Selection Screen
//            g2.setColor(Color.white);
//            g2.setFont(g2.getFont().deriveFont(42F));
//
//            String text = "Vui lọng chọn lớp nhân vật !";
//            int x = getXCenterText(text);
//            int y = gp.tileSize * 3;
//            g2.drawString(text, x, y);
//
//            text = "Thần Thú Giang Nam ";
//            x = getXCenterText(text);
//            y += gp.tileSize * 3;
//            g2.drawString(text, x, y);
//            if (commandNum == 0) {
//                g2.drawString(">", x - gp.tileSize, y);
//            }
//            String text2 = "Tiên Nữ Giang Nam ";
//            x = getXCenterText(text);
//            y += gp.tileSize;
//            g2.drawString(text2, x, y);
//            if (commandNum == 1) {
//                g2.drawString(">", x - gp.tileSize, y);
//            }
//            text2 = "Tinh Linh Giang Nam ";
//            x = getXCenterText(text);
//            y += gp.tileSize;
//            g2.drawString(text2, x, y);
//            if (commandNum == 2) {
//                g2.drawString(">", x - gp.tileSize, y);
//            }
//            text = "Quay Lại ";
//            x = getXCenterText(text);
//            y += gp.tileSize * 2;
//            g2.drawString(text, x, y);
//            if (commandNum == 3) {
//                g2.drawString(">", x - gp.tileSize, y);
//            }
//        }
    }

    public int getXCenterText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXCenterText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public void buyLandState() {
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));
        //Sub Window
        int frameX = gp.tileSize * 8;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        currentDialogue = "Mua ô đất này\nvới giá " + gp.land.getLandPrice(gp.dCrop.col * 4 + gp.dCrop.row) + " ? ";
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        //Yes, I do :))))
        String text = "Yes";
        textX = getXCenterText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
//            if (gp.keyH.enterPressed == true) {
//                gp.gameState = gp.playState;
//                gp.land.setHaveLand(gp.land.getHaveLand() + 1);
//                gp.keyH.enterPressed = false;
//            }
        }
        // No, Đcmm
        text = "No";
        textX = getXCenterText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
//            if (gp.keyH.enterPressed == true) {
//                gp.gameState = gp.playState;
//                gp.keyH.enterPressed = false;
//            }
        }
    }

    public void drawSeedSelection() {
//        int frameX = (gp.player.worldX / gp.tileSize) * gp.tileSize - gp.tileSize * 2;
//        int frameY = (gp.player.worldY / gp.tileSize) * gp.tileSize - gp.tileSize - 24;
        int frameX = (gp.dCrop.col + 1) * gp.tileSize - 6;
        int frameY = (gp.dCrop.row + 5) * gp.tileSize - 30;
        // vẽ nền ô chọn
        int frameWidth = gp.tileSize * 5 + 12;
        int frameHeight = gp.tileSize + 35;
        int pointer = 0;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        g2.setFont(g2.getFont().deriveFont(16F));
//        String text = "Cây số ";
        int frameX2 = frameX + frameWidth / 3;
        int frameY2 = frameY + 22 + gp.tileSize;

        // vẽ hạt giống
        frameX += 5;
        frameY += 7;
        int cursorX = frameX + (gp.tileSize * selectCol);
        int cursorY = frameY;

        if (indexSeed < 5) {
            for (int i = 0; i <= indexSeed; i++) {
                g2.drawImage(gp.crop.getCropImage(i, 5), frameX, frameY, gp.tileSize, gp.tileSize, null);
                frameX += gp.tileSize;

            }
            if (posSeed == selectCol) {
                g2.drawImage(gp.crop.getSelect(), cursorX, cursorY, null);
            }
            if (posSeed != selectCol) {
                selectCol = 4;
                g2.drawImage(gp.crop.getSelect(), cursorX, cursorY, null);
            }
            g2.drawString(gp.crop.getCropName(cropID) + ": " + gp.inventory.getSeedAmount(cropID), frameX2, frameY2);
        }
        if (indexSeed >= 5) {
            for (int i = (indexSeed - 4); i <= indexSeed; i++) {
                g2.drawImage(gp.crop.getCropImage(i, 5), frameX, frameY, gp.tileSize, gp.tileSize, null);
                frameX += gp.tileSize;

            }
            if (posSeed == selectCol) {
                g2.drawImage(gp.crop.getSelect(), cursorX, cursorY, null);
            }
            if (posSeed != selectCol) {
                selectCol = 4;
                g2.drawImage(gp.crop.getSelect(), cursorX, cursorY, null);
            }
            g2.drawString(gp.crop.getCropName(cropID) + ": " + gp.inventory.getSeedAmount(cropID), frameX2, frameY2);
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(0, 0, 0);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public void drawNotification() {
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));
        drawSubWindow(gp.tileSize * 4, gp.tileSize, gp.tileSize * 14, gp.tileSize * 2);
        g2.drawString(notification, gp.tileSize * 5, gp.tileSize * 2 + 10);
    }

    public void drawSelectTool() {
        int frameX = (gp.dCrop.col + 2) * gp.tileSize + 18;
        int frameY = (gp.dCrop.row + 5) * gp.tileSize - 30;
        // vẽ nền ô chọn
        int frameWidth = gp.tileSize * 2 + 21;
        int frameHeight = gp.tileSize + 14;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        frameX += 7;
        frameY += 7;

        g2.drawImage(gp.land.getHoe(), frameX, frameY, 48, 48, null);
        if (selectTool == 0) {
            g2.drawImage(gp.crop.getSelect(), frameX, frameY, 48, 48, null);
        }
        frameX += gp.tileSize;
        g2.drawImage(gp.land.getWateringCan(), frameX, frameY, 48, 48, null);
        if (selectTool == 1) {
            g2.drawImage(gp.crop.getSelect(), frameX, frameY, 48, 48, null);
        }
    }

    public void tradeState() {
        drawDialogueScreen(message1);

        // draw window
        int x = gp.tileSize * 15;
        int y = gp.tileSize * 4;
        int width = gp.tileSize * 3;
        int height = (int) (gp.tileSize * 3.5);
        drawSubWindow(x, y, width, height);

        //draw text
        x += gp.tileSize;
        y += gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(24F));
        g2.drawString("Mua", x, y);
        if (subState == 0) {
            g2.drawString(">", x - 24, y);
        }
        y += gp.tileSize;
        g2.drawString("Bán", x, y);
        if (subState == 1) {
            g2.drawString(">", x - 24, y);
        }
        y += gp.tileSize;
        g2.drawString("Rời đi", x, y);
        if (subState == 2) {
            g2.drawString(">", x - 24, y);
        }
    }

    public void drawDialogueScreen(String message1) {
        //Window
        int x = gp.tileSize * 3;
        int y = gp.tileSize / 2;
        int width = gp.tileSize * 14;
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line : message1.split("\n")) {
            g2.drawString(line, x, y);
            y += 30;
        }
    }

    public void shopSellState() {
        drawInventory(0, true, false);
// draw coin
        int x = gp.tileSize * 7;
        int y = gp.tileSize * 11;
        int width = gp.tileSize * 5;
        int height = gp.tileSize;
        drawSubWindow(x, y, width, height);
        g2.drawString("Ví tiền: " + gp.player.getMoney(), x + 24, y + 35);

        // draw price
        int itemIndex = getItemIndexOnSlot(playerlotCol, playerlotRow);
        if (itemIndex < 20) {
            x = (int) (gp.tileSize * 8.8);
            y = (int) (gp.tileSize * 6.5);
            width = (int) (gp.tileSize * 3.3);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawString("Số lượng: " + sellNumber, x + 10, y + 32);

        }
    }

    public void shopBuyState() {
        drawInventory(1, true, true);
        drawInventory(0, false, true);
        int x = gp.tileSize * 12;
        int y = gp.tileSize * 9 + 24;
        int width = gp.tileSize * 5;
        int height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Túi đồ", x + 70, y + 40);
        g2.drawString("Ví tiền: " + gp.player.getMoney(), x + 24, y + 75);

        // draw price
        int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
        if (itemIndex < 20) {
            x = gp.tileSize * 12;
            y = gp.tileSize * 8 + 24;
            width = gp.tileSize * 5;
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawString("Số lượng: " + buyNumber, x + 24, y + 34);

            // buy an item
//            if (gp.keyH.enterPressed == true) {
//                if (npc.price * number > gp.player.coin) {
//                    subState = 0;
//                    gp.gameState = gp.dialogueState;
//                    currentDialouge = "You need more coin to buy that !";
//                    drawDialougeScreen();
//                    number = 1;
//                } else {
//                    gp.player.coin -= (npc.price * number);
//                    number = 1;
//                }
//            }
        }
    }

    public void drawInventory(int index, boolean cursor, boolean type) {
        int frameX;
        int frameY;
        int frameWidth;
        int frameHeight;
        int slotCol;
        int slotRow;
        int slotSize;
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        if (index == 0) {
            frameX = gp.tileSize * 12;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 5;
            frameHeight = gp.tileSize * 8 - 24;
            slotCol = playerlotCol;
            slotRow = playerlotRow;
        } else {
            frameX = gp.tileSize * 5;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 5;
            frameHeight = gp.tileSize * 7 + 9;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }

        // create inventory

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // slot
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        if (cursor)
            slotSize = gp.tileSize;
        else
            slotSize = gp.tileSize + 3;

        // draw item inventory
        for (int i = 0; i < 20; i++) {
            g2.drawImage(gp.crop.getCropImage(i, 5), slotX, slotY, null);
            if (index == 0) {
                if (type) {
                    g2.drawString("" + gp.inventory.getSeedAmount(i), slotX + 20, slotY + 64);
                } else g2.drawString("" + gp.inventory.getCropAmount(i), slotX + 20, slotY + 64);
            }
            slotX += slotSize;
            if (i == 3 || i == 7 || i == 11 || i == 15) {
                slotX = slotXstart;
                slotY += slotSize + 15;
            }
        }

        // cursor
        if (cursor) {
            int cursorX = slotXstart + (slotSize * slotCol);
            int cursorY = slotYstart + ((slotSize + 15) * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;

            // draw cursor
            g2.setColor(Color.YELLOW);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            // description frame
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize * 4;
            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            // description text
            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(26F));
            int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
            if (itemIndex < 20) {
                g2.drawString(gp.crop.getCropName(itemIndex), textX, textY);
                textY += 32;
                g2.drawString("Giá mua: " + gp.crop.getCropBuyPrice(itemIndex), textX, textY);
                textY += 32;
                g2.drawString("Giá bán: " + gp.crop.getCropSellPrice(itemIndex), textX, textY);
                textY += 32;
                g2.drawString("Thời Gian: " + gp.crop.getCropGrowTime(itemIndex) + " giờ", textX, textY);
                if (!type) {
                    textY += 32;
                    g2.drawString("Đang có: " + gp.inventory.getCropAmount(itemIndex), textX, textY);
                }
            }
        }
    }

    public void drawInventoryState() {
        int defaultX = gp.tileSize * 4;
        int defaultY = gp.tileSize * 3;
        int slotSizeX = gp.tileSize + 5;
        int slotSizeY = gp.tileSize + 15;
        int x = defaultX;
        int y = defaultY;
        int width = slotSizeX * 13;
        int height = slotSizeY * 5;
        int slotXStart = x + 10;
        drawSubWindow(x, y, width, height);
        x += 10;
        y += 5;
        for (int i = 0; i < 20; i++) {
            g2.drawImage(gp.crop.getCropImage(i, 5), x, y, 48, 48, null);
            x += slotSizeX;
            if (i % 4 == 3) {
                x = slotXStart;
                y += slotSizeY;
            }
        }
        g2.setColor(Color.yellow);
        g2.drawRoundRect(defaultX + 10 + inventoryCol * slotSizeX, defaultY + 5 + inventoryRow * slotSizeY, 48, 48, 10, 10);
        g2.setColor(Color.black);
        g2.drawLine(slotXStart + slotSizeX * 4, gp.tileSize * 3 + 5, slotXStart + slotSizeX * 4, gp.tileSize * 3 + slotSizeY * 5 - 10);
        // in thông tin cây
        x = defaultX + slotSizeX * 4 + 20;
        y = defaultY + gp.tileSize;
        g2.setFont(comfortaa_20);
        g2.drawString(gp.crop.getCropName(inventoryCol + inventoryRow * 4), x, y);
        y += 40;
        g2.drawString("Số lượng hạt: " + gp.inventory.getSeedAmount(inventoryCol + inventoryRow * 4), x, y);
        y += 40;
        g2.drawString("Đang có: " + gp.inventory.getCropAmount(inventoryCol + inventoryRow * 4), x, y);
        y += 40;
        g2.drawString("Giá mua: " + gp.crop.getCropBuyPrice(inventoryCol + inventoryRow * 4), x, y);
        y += 40;
        g2.drawString("Giá bán: " + gp.crop.getCropSellPrice(inventoryCol + inventoryRow * 4), x, y);
        y += 40;
        g2.drawString("Thời gian lớn: " + gp.crop.getCropGrowTime(inventoryCol + inventoryRow * 4) + " giờ", x, y);
        g2.drawLine(x + 220, gp.tileSize * 3 + 5, x + 220, gp.tileSize * 3 + slotSizeY * 5 - 10);
        // hiện thông tin người chơi
        x = defaultX + slotSizeX * 9 - 10;
        y = defaultY;
        g2.drawImage(gp.player.down1, x + 48 + 40, y + 24, 48, 48, null);
        y += 48 + 54;
        g2.drawString("Tên: " + gp.player.getPlayerName(), x, y);
        y += 30;
        g2.drawString("Ví tiền: " + gp.player.getMoney(), x, y);
        y += 30;
        g2.drawString("Số ô đất đã mở: " + gp.land.getHaveLand(), x, y);
        y += 30;
        g2.drawLine(x - 15, y, x + 215, y);
        y += 30;
        g2.drawString("Pet", x, y);
        y += 30;
        if (gp.player.getPetID() == 0)
            g2.drawString("Bạn không sở hữu thú nuôi !", x, y);
        else
            g2.drawImage(gp.pet.down1, x + 85, y, 32, 32, null);

    }

    public void drawChat() {
        drawSubWindow(0, gp.tileSize * 13, gp.tileSize * 22, 48);
        g2.setFont(comfortaa_20);
        g2.drawString(gp.keyH.chatMessage, 24, gp.tileSize * 13 + 30);
        drawChatFlow(0, gp.tileSize * 9);
        messageCountdown = 300;
    }

    public int getItemIndexOnSlot(int slotCol, int slotRow) {
        int itemIndex = slotCol + (slotRow * 4);
        return itemIndex;
    }

    public void drawChatFlow(int x, int y) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        int size = chatMessage.size();
        g2.setFont(comfortaa_20);
        if (messageCountdown > 0) {
            g2.setColor(Color.black);
            g2.fillRect(x, y, gp.tileSize * 6, gp.tileSize * 4);
            g2.setColor(Color.white);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            for (int i = size - 1; i >= size - 9; i--) {
                Message mes = chatMessage.get(i);
                if (!mes.getPlayerName().equals(""))
                    g2.drawString(mes.getPlayerName() + ": " + mes.getMessage(), x, y + gp.tileSize * 4 - 12);
                y -= 20;
            }
//            else {
//                for (Message mes : chatMessage) {
//                    g2.drawString(mes.getPlayerName() + ": " + mes.getMessage(), x, y);
//                    y -= 20;
//                }
//            }

        }
        if (gp.gameState != gp.chatState)
            messageCountdown--;
    }

    public void drawDuplicateLogin() {
        drawSubWindow(gp.tileSize * 3, gp.tileSize * 3, gp.tileSize * 16, gp.tileSize * 3);
        g2.drawString("Tài khoản của bạn được đăng nhập ", gp.tileSize * 3 + 24, gp.tileSize * 4);
        g2.drawString("tại nơi khác !  ", gp.tileSize * 3 + 24, gp.tileSize * 4 + 40);
        g2.setFont(arial_40);
        g2.drawString("Bấm ENTER", gp.tileSize * 8, gp.tileSize * 4 + 80);
    }

    public void drawLeaderBoard() {
        int x = gp.tileSize * 4;
        int y = gp.tileSize;
        g2.setFont(comfortaa_20);
        drawSubWindow(x, y, gp.tileSize * 7, gp.tileSize * 6);
        x += 10;
        y += 30;
        g2.drawString("BẢNG XẾP HẠNG", x + 70, y + 5);
        y += 30;
        g2.drawString("STT  Tên người chơi     Số tiền", x, y);
        x += 10;
        y += 40;
        for (Player player : leaderboard) {
            g2.drawString(String.valueOf(player.getPos()), x, y);
            g2.drawString(player.getPlayerName(), x + 40, y);
            g2.drawString(String.valueOf(player.getMoney()), x + 220, y);
            y += 20;
        }
    }

    public void drawOptionScreen() {
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));


        //Sub Window
        int frameX = gp.tileSize * 8;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;

        int textX;
        int textY;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        //Title
        String text = "Options";
        textX = getXCenterText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        //Music
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Nhạc", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
        }
        //SE
        textY += gp.tileSize;
        g2.drawString("Hiệu ứng", textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
        }
        //Control
        textY += gp.tileSize;
        g2.drawString("Điều khiển", textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
        }
        //Quit Game
        textY += gp.tileSize;
        g2.drawString("Thoát game", textX, textY);
        if (commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
        }
        //Back
        textY += gp.tileSize * 2;
        g2.drawString("Quay lại", textX, textY);
        if (commandNum == 4) {
            g2.drawString(">", textX - 25, textY);
        }

        //Music Volume
        textX = frameX + (int) (gp.tileSize * 4.8);
        textY = frameY + gp.tileSize + 24;
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);
        //SE Volume
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        int seWidth = 24 * gp.SE.volumeScale;
        g2.fillRect(textX, textY, seWidth, 24);
    }


    public void options_control() {
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));


        //Sub Window
        int frameX = gp.tileSize * 8;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;

        int textX;
        int textY;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        //Title
        String text = "Control";
        textX = getXCenterText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Di chuyển", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Tương tác", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Túi đồ", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Chat", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Tạm dừng/Tiếp tục", textX, textY);
        textY += gp.tileSize;

        textX = frameX + (int) (gp.tileSize * 4.5);
        textY = frameY + gp.tileSize * 2;
        g2.drawString("WASD", textX, textY);
        textY += gp.tileSize;
        g2.drawString("E", textX, textY);
        textY += gp.tileSize;
        g2.drawString("B", textX, textY);
        textY += gp.tileSize;
        g2.drawString("T", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ESC", textX, textY);
        textY += gp.tileSize;

        //Back
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
        }

    }

    public void options_endGameConfirmation() {
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));


        //Sub Window
        int frameX = gp.tileSize * 8;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        currentDialogue = "Đăng xuất và \ntrở lại màn hình đăng nhập ? ";
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        //Yes, I do :))))
        String text = "Yes";
        textX = getXCenterText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
        }
        // No, Đcmm
        text = "No";
        textX = getXCenterText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
        }
    }
}
