package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class KeyHandler implements KeyListener {
    public boolean upPressed, spacePressed, downPressed, leftPressed, rightPressed,
            ePressed, enterPressed, choosePressed, closeSSelected;
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
        if (code == KeyEvent.VK_E) {
            gp.eHandler.event(gp);
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
            if (gp.ui.posSeed > 0)
                gp.ui.posSeed--;
            if (gp.ui.selectS > 0)
                gp.ui.selectS--;
            if (gp.ui.selectCol > 0)
                gp.ui.selectCol--;
            gp.ui.selectCol--;
            if (gp.ui.cropID > 0)
                gp.ui.cropID--;
            if (i > 0) {
                i--;
            }
            if (i <= 0) {
                gp.ui.indexSeed--;
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
                    Main.socketHandler.write("water=" + slot);
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
            if (gp.ui.number > 0)
                gp.ui.number--;
        if (code == KeyEvent.VK_RIGHT)
            if (gp.ui.number < 10)
                gp.ui.number++;
        if (code == KeyEvent.VK_ENTER) {
            int slot = gp.ui.npcSlotCol + gp.ui.npcSlotRow * 4;
            if (gp.player.getMoney() < gp.crop.getCropBuyPrice(slot) * gp.ui.number) {
                gp.ui.notification = "Bạn không đủ tiền";
                gp.gameState = gp.notificationState;
            } else {
                try {
                    Main.socketHandler.write("buy-seed=" + slot + "=" + gp.ui.number);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                gp.ui.number = 1;
            }
        }
    }

    public void shopSellState(int code) {

    }
}
