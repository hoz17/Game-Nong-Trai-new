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
}
