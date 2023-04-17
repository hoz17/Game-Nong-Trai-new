package Controller;

import Model.*;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SocketHandler implements Runnable {

    GamePanel gp;
    Thread socketThread;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
    private BufferedWriter os;
    private BufferedReader is;
    private Socket socketOfClient;
    private int ID_Server;

    public SocketHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void startGameThread() {
        socketThread = new Thread(this);
        socketThread.start();
    }

    @Override
    public void run() {
        try {
            // Gửi yêu cầu kết nối tới Server đang lắng nghe
            socketOfClient = new Socket("localhost", 7777);
            System.out.println("Kết nối thành công!");
            // Tạo luồng đầu ra tại client (Gửi dữ liệu tới server)
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
            // Luồng đầu vào tại Client (Nhận dữ liệu từ server).
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
            String message;
            while (true) {
                message = is.readLine();
                System.out.println("receive " + message);
                if (message == null) {
                    break;
                }
                String[] messageSplit = message.split("=");
                if (messageSplit[0].equals("server-send-id")) {
                    ID_Server = Integer.parseInt(messageSplit[1]);
                    write("load-crop-data=");
//                    System.out.print("Nhập tên tài khoản: ");
//                    username = sc.nextLine();
//                    System.out.print("Nhập mật khẩu: ");
//                    password = sc.nextLine();
//                    write("client-verify=" + username + "=" + password);

                }

                //Đăng nhập thành công
                if (messageSplit[0].equals("login-success")) {
                    System.out.println("Đăng nhập thành công");
                    Player player = getUserFromString(1, messageSplit);
                    gp.player = player;
                    if (gp.player.getPetID() != 0)
                        gp.pet = new Pet(gp.player.getPetID(), gp);
                    gp.loginForm.dispose();
                    write("load-player-data=");
                }
                if (messageSplit[0].equals("player-data")) {
                    gp.inventory = getInventoryData(1, messageSplit);
                    gp.land = getLandData(61, messageSplit);
                    gp.land.calculateLandPrice();
                    gp.setUpGame();
                }
                if (messageSplit[0].equals("crop-data")) {
                    gp.crop = getCropData(1, messageSplit);
                }
                //Thông tin tài khoản sai
                if (messageSplit[0].equals("wrong-user")) {
                    gp.loginForm.wrongUser();
                }
                if (messageSplit[0].equals("harvest-complete")) {
                    int slot = Integer.parseInt(messageSplit[1]);
                    int cropID = Integer.parseInt(messageSplit[2]);
                    int newCropAmount = Integer.parseInt(messageSplit[3]);
                    for (int i = 0; i < 32; i++) {
                        if (gp.land.getSlot(i) == slot) {
                            gp.land.setCropID(i, -1);
                            gp.inventory.setCropAmount(cropID, newCropAmount);
                        }
                    }
                }
                if (messageSplit[0].equals("buy-farmland-complete")) {
                    int slot = Integer.parseInt(messageSplit[1]);
                    int newMoney = Integer.parseInt(messageSplit[2]);
                    gp.player.setMoney(newMoney);
                    gp.land.setState(slot, 1);
                    gp.land.setHaveLand(gp.land.getHaveLand() + 1);
                }
                if (messageSplit[0].equals("plant-complete")) {
                    int slot = Integer.parseInt(messageSplit[1]);
                    Integer cropID = Integer.parseInt(messageSplit[2]);
                    Timestamp newPlantTime;
                    try {
                        Date parseDate = dateFormat.parse(messageSplit[3]);
                        Timestamp timestamp = new Timestamp(parseDate.getTime());
                        newPlantTime = timestamp;
                    } catch (ParseException e) {
                        newPlantTime = null;
                    }
                    int newSeedAmount = Integer.parseInt(messageSplit[4]);

//                    int slot = gp.dCrop.col * 4 + gp.dCrop.row;
                    gp.land.setPlantTime(slot, newPlantTime);
                    gp.land.setCropID(slot, cropID); //Đặt ID là ID của hạt đã chọn
                    gp.land.setWaterLevel(slot, 0); // Đặt WaterLevel = 0 là hạt chưa phát triển
                    gp.inventory.setSeedAmount(cropID, newSeedAmount);
                }
                if (messageSplit[0].equals("water-complete")) {
                    int slot = Integer.parseInt(messageSplit[1]);
                    int newWaterLevel = Integer.parseInt(messageSplit[2]);
                    gp.land.setWaterLevel(slot, newWaterLevel);
                    gp.gameState = gp.playState;
                }
                if (messageSplit[0].equals("trample-complete")) {
                    int slot = Integer.parseInt(messageSplit[1]);
                    gp.land.setCropID(slot, -1);
                    gp.gameState = gp.playState;
                }
                if (messageSplit[0].equals("buy-seed-complete")) {
                    int cropID = Integer.parseInt(messageSplit[1]);
                    int newSeedAmount = Integer.parseInt(messageSplit[2]);
                    int newMoney = Integer.parseInt(messageSplit[3]);
                    gp.player.setMoney(newMoney);
                    gp.inventory.setSeedAmount(cropID, newSeedAmount);
                }
                if (messageSplit[0].equals("sell-seed-complete")) {
                    int cropID = Integer.parseInt(messageSplit[1]);
                    int newCropAmount = Integer.parseInt(messageSplit[2]);
                    int newMoney = Integer.parseInt(messageSplit[3]);
                    gp.player.setMoney(newMoney);
                    gp.inventory.setCropAmount(cropID, newCropAmount);
                }
                if (messageSplit[0].equals("chat-message")) {
                    String playerName = "", msg = "";
                    if (!messageSplit[1].equals(""))
                        playerName = messageSplit[1];
                    if (!messageSplit[2].equals(""))
                        msg = messageSplit[2];
                    if (!playerName.equals("") && !msg.equals("")) {
                        gp.ui.chatMessage.add(new Message(playerName, msg));
                        gp.ui.messageCountdown = 300;
                    }
                }
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Player getUserFromString(int start, String[] message) {
        return new Player(message[start],
                Integer.parseInt(message[start + 1]),
                Integer.parseInt(message[start + 2]),
                Integer.parseInt(message[start + 3])
        );
    }

    public Inventory getInventoryData(int start, String[] message) {
        int[] cropID = new int[20];
        int[] seedAmount = new int[20];
        int[] cropAmount = new int[20];
        for (int i = 0; i < 20; i++) {
            cropID[i] = Integer.parseInt(message[i * 3 + start]);
            cropAmount[i] = Integer.parseInt(message[i * 3 + start + 1]);
            seedAmount[i] = Integer.parseInt(message[i * 3 + start + 2]);
        }
        return new Inventory(cropID, cropAmount, seedAmount);
    }

    public Land getLandData(int start, String[] message) {
        int[] slot = new int[32];
        int[] state = new int[32];
        Integer[] cropID = new Integer[32];
        Timestamp[] plantTime = new Timestamp[32];
        int[] waterLevel = new int[32];

        for (int i = 0; i < 32; i++) {
            slot[i] = Integer.parseInt(message[i * 5 + start]);
//            System.out.println(slot[i]);
            state[i] = Integer.parseInt(message[i * 5 + start + 1]);
//            System.out.println(state[i]);
            try {
                cropID[i] = Integer.parseInt(message[i * 5 + start + 2]);
            } catch (Exception e) {
                cropID[i] = -1;
            }
//                cropID[i] = Integer.parseInt(message[i * 5 + start + 2]);
//            System.out.println(cropID[i]);
            try {
//                System.out.println(message[i * 5 + start + 3]);
                Date parseDate = dateFormat.parse(message[i * 5 + start + 3]);
                Timestamp timestamp = new Timestamp(parseDate.getTime());
                plantTime[i] = timestamp;
//                System.out.println(plantTime[i]);
            } catch (ParseException e) {
                plantTime[i] = null;
            }
//            System.out.println(plantTime[i]);
            waterLevel[i] = Integer.parseInt(message[i * 5 + start + 4]);
//            System.out.println(waterLevel[i]);
        }
        return new Land(slot, state, cropID, plantTime, waterLevel);
    }


    public Crop getCropData(int start, String[] message) {
        int[] cropID = new int[21];
        String[] cropName = new String[21];
        int[] cropGrowTime = new int[21];
        int[] cropBuyPrice = new int[21];
        int[] cropSellPrice = new int[21];
        int[] waterLevel = new int[21];
        for (int i = 0; i < 21; i++) {
            cropID[i] = Integer.parseInt(message[i * 6 + start]);
//            System.out.println(cropID[i]);
            cropName[i] = message[i * 6 + start + 1];
//            System.out.println(cropName[i]);
            cropGrowTime[i] = Integer.parseInt(message[i * 6 + start + 2]);
//            System.out.println(cropGrowTime[i]);
            cropBuyPrice[i] = Integer.parseInt(message[i * 6 + start + 3]);
//            System.out.println(cropBuyPrice[i]);
            cropSellPrice[i] = Integer.parseInt(message[i * 6 + start + 4]);
//            System.out.println(cropSellPrice[i]);
            waterLevel[i] = Integer.parseInt(message[i * 6 + start + 5]);
//            System.out.println(waterLevel[i]);
        }
        return new Crop(cropID, cropName, cropGrowTime, cropBuyPrice, cropSellPrice, waterLevel);
    }

    public void write(String message) throws IOException {
        os.write(message);
        System.out.println("send " + message);
        os.newLine();
        os.flush();
    }
}
