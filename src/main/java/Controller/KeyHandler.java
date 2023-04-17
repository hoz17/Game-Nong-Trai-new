package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class KeyHandler implements KeyListener {
    public boolean upPressed, spacePressed, downPressed, leftPressed, rightPressed,
            ePressed, enterPressed, choosePressed, closeSSelected;
    public char input;
    public String chatMessage = "";
    public int slot;
    GamePanel gp;
    private int i = 0;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (gp.gameState == gp.chatState) {

            input = e.getKeyChar();
        }
        if (gp.gameState == gp.playState) {
            playState(code);
        }
        if (gp.gameState == gp.titleState) {
            titleState(code);
        }
        if (gp.gameState == gp.buyLand) {
            buyLand(code);
        }
        if (gp.gameState == gp.notificationState) {
            notificationState(code);
        }
        if (gp.gameState == gp.selectSeed) {
            selectSeed(code);
        }
        if (gp.gameState == gp.selectTool) {
            selectTool(code);
        }
        if (gp.gameState == gp.tradeState) {
            tradeState(code);
        }
        if (gp.gameState == gp.shopBuyState) {
            shopBuyState(code);
        }
        if (gp.gameState == gp.shopSellState) {
            shopSellState(code);
        }
        if (gp.gameState == gp.inventoryState) {
            inventoryState(code);
        }
        if (gp.gameState == gp.chatState) {
            chatState(code);
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }

    }

    public void playState(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_B) {
            gp.gameState = gp.inventoryState;
        }
        if (code == KeyEvent.VK_E) {
            gp.eHandler.event(gp);
        }
        if (code == KeyEvent.VK_T) {
            gp.gameState = gp.chatState;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = false;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = false;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = false;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = false;
            }
        }
    }

    public void titleState(int code) {
        if (gp.ui.titleScreenState == 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
//                gp.playSE(5);
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
//                gp.playSE(5);
                if (gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
//                    gp.setUpGame();
                    gp.gameState = gp.playState;
                }
            }
        }
    }

    public void buyLand(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                if (gp.player.getMoney() >= gp.land.getLandPrice(gp.land.getHaveLand())) {
                    gp.gameState = gp.playState;
//                int col = gp.dCrop.col;
//                int row = gp.dCrop.row;
//                gp.land.setState(col * 4 + row, 1);

                    // gửi server
                    try {
                        Main.socketHandler.write("buy-farmland=" + gp.land.getHaveLand());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    gp.ui.npcSlotCol = 0;
                    gp.ui.npcSlotRow = 0;
                } else {
                    gp.ui.notification = "Bạn không đủ tiền để thực hiện điều này!";
                    gp.gameState = gp.notificationState;
                }
//                gp.land.setHaveLand(gp.land.getHaveLand() + 1);
            } else if (gp.ui.commandNum == 1) {
                gp.gameState = gp.playState;
            }
        }
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum < 1) {
                gp.ui.commandNum = 0;
            }
        }
    }

    public void selectSeed(int code) {
        if (code == KeyEvent.VK_A) {
            gp.ui.posSeed--;
            gp.ui.selectS--;

            gp.ui.selectCol--;
            if (gp.ui.cropID > 0)
                gp.ui.cropID--;
            if (i > 0) {
                i--;
            }
            if (i <= 0) {
                gp.ui.indexSeed--;
            }
            if (gp.ui.posSeed <= 0) {
                gp.ui.posSeed = 0;
            }
            if (gp.ui.selectCol <= 0) {
                gp.ui.selectCol = 0;
            }
            if (gp.ui.selectS <= 0) {
                gp.ui.selectS = 0;
            }
            if (gp.ui.indexSeed <= 4) {
                gp.ui.indexSeed = 4;
            }
        }
        if (code == KeyEvent.VK_D) {
            gp.ui.posSeed++;
            gp.ui.selectS++;
            gp.ui.selectCol++;
            if (gp.ui.cropID < 19)
                gp.ui.cropID++;
            if (gp.ui.posSeed >= 5) {
                gp.ui.posSeed = 4;
            }
            if (i <= 4) {
                i++;
            }
            if (i >= 5) {
                gp.ui.indexSeed++;
                if (gp.ui.selectCol > 4) {
                    gp.ui.selectCol = 4;
                }
                if (gp.ui.selectS > 19) {
                    gp.ui.selectS = 19;
                }
                if (gp.ui.indexSeed == 20) {
                    gp.ui.indexSeed = 19;
                }
            }
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER) {
//            System.out.println("Bạn đã trồng cây " + gp.ui.selectS);
            if (gp.inventory.getSeedAmount(gp.ui.cropID) > 0) {
                slot = gp.dCrop.col * 4 + gp.dCrop.row;
                System.out.println(gp.ui.cropID);
                try {
                    Main.socketHandler.write("plant=" + slot + "=" + gp.ui.cropID);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                gp.gameState = gp.playState;
            } else {
                gp.ui.notification = "Bạn không đủ hạt giống !";
                gp.gameState = gp.notificationState;
            }
//            gp.ui.cropID=0;

        }
    }

    public void notificationState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.ui.notification = "";
            gp.gameState = gp.playState;
        }
    }

    public void selectTool(int code) {
        if (code == KeyEvent.VK_A) {
            if (gp.ui.selectTool > 0)
                gp.ui.selectTool--;
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.selectTool < 1)
                gp.ui.selectTool++;
        }
        if (code == KeyEvent.VK_ENTER) {
            int slot = gp.dCrop.col * 4 + gp.dCrop.row;
            try {
                if (gp.ui.selectTool == 0) {
                    Main.socketHandler.write("trample=" + slot);
                }
                if (gp.ui.selectTool == 1) {
                    if (gp.land.getWaterLevel(slot) < 3)
                        Main.socketHandler.write("water=" + slot);
                    else gp.gameState = gp.playState;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void tradeState(int code) {
        if (code == KeyEvent.VK_W) {
            if (gp.ui.subState > 0)
                gp.ui.subState--;
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.subState < 2)
                gp.ui.subState++;
        }
        if (code == KeyEvent.VK_ENTER) {
            switch (gp.ui.subState) {
                case 0:
                    gp.gameState = gp.shopBuyState;
                    break;
                case 1:
                    gp.gameState = gp.shopSellState;
                    break;
                case 2:
                    gp.gameState = gp.playState;
                    break;
            }
        }
    }

    public void shopBuyState(int code) {
        if (code == KeyEvent.VK_W)
            if (gp.ui.npcSlotRow > 0)
                gp.ui.npcSlotRow--;
        if (code == KeyEvent.VK_S)
            if (gp.ui.npcSlotRow < 4)
                gp.ui.npcSlotRow++;
        if (code == KeyEvent.VK_A)
            if (gp.ui.npcSlotCol > 0)
                gp.ui.npcSlotCol--;
        if (code == KeyEvent.VK_D)
            if (gp.ui.npcSlotCol < 3)
                gp.ui.npcSlotCol++;
        if (code == KeyEvent.VK_LEFT)
            if (gp.ui.buyNumber > 0)
                gp.ui.buyNumber--;
        if (code == KeyEvent.VK_RIGHT)
            if (gp.ui.buyNumber < 10)
                gp.ui.buyNumber++;
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.buyNumber > 0) {
                int slot = gp.ui.npcSlotCol + gp.ui.npcSlotRow * 4;
                if (gp.player.getMoney() < gp.crop.getCropBuyPrice(slot) * gp.ui.buyNumber) {
                    gp.ui.notification = "Bạn không đủ tiền";
                    gp.gameState = gp.notificationState;
                } else {
                    try {
                        Main.socketHandler.write("buy-seed=" + slot + "=" + gp.ui.buyNumber);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    gp.ui.buyNumber = 1;
                }
            }
        }
    }

    public void inventoryState(int code) {
        if (code == KeyEvent.VK_A)
            if (gp.ui.inventoryCol > 0)
                gp.ui.inventoryCol--;
        if (code == KeyEvent.VK_D)
            if (gp.ui.inventoryCol < 3)
                gp.ui.inventoryCol++;
        if (code == KeyEvent.VK_W)
            if (gp.ui.inventoryRow > 0)
                gp.ui.inventoryRow--;
        if (code == KeyEvent.VK_S)
            if (gp.ui.inventoryRow < 4)
                gp.ui.inventoryRow++;
    }

    public void shopSellState(int code) {
        int id = gp.ui.getItemIndexOnSlot(gp.ui.playerlotCol, gp.ui.playerlotRow);
        if (code == KeyEvent.VK_W) {
            if (gp.ui.playerlotRow > 0) {
                gp.ui.playerlotRow--;
                gp.ui.sellNumber = 0;
            }
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.playerlotRow < 4) {
                gp.ui.playerlotRow++;
                gp.ui.sellNumber = 0;
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.playerlotCol > 0) {
                gp.ui.playerlotCol--;
                gp.ui.sellNumber = 0;
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.playerlotCol < 3) {
                gp.ui.playerlotCol++;
                gp.ui.sellNumber = 0;
            }
        }
        if (code == KeyEvent.VK_LEFT) {
            if (gp.ui.sellNumber > 0)
                gp.ui.sellNumber--;
        }
        if (code == KeyEvent.VK_RIGHT) {
            if (gp.ui.sellNumber < gp.inventory.getCropAmount(id))
                gp.ui.sellNumber++;
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.sellNumber > 0) {
                try {
                    Main.socketHandler.write("sell-crop=" + id + "=" + gp.ui.sellNumber);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                gp.ui.sellNumber = 0;
            }
//            else {
//                gp.ui.notification = "Bạn không đủ trong kho !";
//                gp.gameState = gp.notificationState;
//            }
        }
        // sell an item
//        if (gp.keyH.enterPressed) {
//            if (buyNumber > gp.player.inventory.size()) {
//                subState = 0;
//                gp.gameState = gp.dialogueState;
//                currentDialouge = "You cannot sell an equipped item !";
//                drawDialougeScreen();
//                buyNumber = 1;
//            } else {
//                gp.player.coin += (npc.price * sellNumber + 20);
//                buyNumber = 1;
//            }
//        }
    }

    public void chatState(int code) {
        if (code != KeyEvent.VK_ENTER) {
            if (Character.isLetter(input))
                chatMessage += input;
            if (code == KeyEvent.VK_SPACE) {
                chatMessage += " ";
            }
            if (code == KeyEvent.VK_BACK_SPACE) {
                if (chatMessage.length() > 0)
                    chatMessage = chatMessage.substring(0, chatMessage.length() - 1);
            }
        } else {
            if (chatMessage.matches(".*\\S+.*"))
                try {
                    Main.socketHandler.write("world-chat=" + gp.player.getPlayerName() + "=" + chatMessage);
                } catch (IOException e) {

                }
            chatMessage = "";
            gp.gameState = gp.playState;
        }
    }
}
