package Controller;

import Model.Inventory;
import Model.Land;
import Model.Player;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SocketHandler implements Runnable {
    private BufferedWriter os;
    private BufferedReader is;
    private Socket socketOfClient;
    private int ID_Server;
    GamePanel gp;

    public SocketHandler(GamePanel gp) {
        this.gp = gp;
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
                System.out.println(message);
                if (message == null) {
                    break;
                }
                String[] messageSplit = message.split("=");
                if (messageSplit[0].equals("server-send-id")) {
                    ID_Server = Integer.parseInt(messageSplit[1]);
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
                    gp.loginForm.dispose();
                    write("load-player-data=");
                }
                if (messageSplit[0].equals("player-data")) {
                    gp.inventory = getInventoryData(1, messageSplit);
                    gp.land = getLandData(61, messageSplit);
                    gp.land.calculateLandPrice();
                    gp.setUpGame();

                }
                //Thông tin tài khoản sai
                if (messageSplit[0].equals("wrong-user")) {
                    gp.loginForm.wrongUser();
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
        int cropID[] = new int[20];
        int seedAmount[] = new int[20];
        int cropAmount[] = new int[20];
        for (int i = 0; i < 20; i++) {
            cropID[i] = Integer.parseInt(message[i * 3 + start]);
            cropAmount[i] = Integer.parseInt(message[i * 3 + start + 1]);
            seedAmount[i] = Integer.parseInt(message[i * 3 + start + 2]);
        }
        return new Inventory(cropID, seedAmount, cropAmount);
    }

    public Land getLandData(int start, String[] message) {
        int[][] slot = new int[8][4];
        int[][] state = new int[8][4];
        int[][] cropID = new int[8][4];
        Timestamp plantTime[][] = new Timestamp[8][4];
        int[][] waterLevel = new int[8][4];
        int position = start;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 4; j++) {
                slot[i][j] = Integer.parseInt(message[position]);
//                System.out.println(slot[i][j]);
                position++;
                state[i][j] = Integer.parseInt(message[position]);
//                System.out.println(state[i][j]);
                position++;
                cropID[i][j] = Integer.parseInt(message[position]);
//                System.out.println(cropID[i][j]);
                position++;
                try {
                    Date parseDate = dateFormat.parse(message[position]);
                    Timestamp timestamp = new Timestamp(parseDate.getTime());
                    plantTime[i][j] = timestamp;
                } catch (ParseException e) {
                    plantTime[i][j] = null;
                }
//                System.out.println(plantTime[i][j]);
                position++;
                waterLevel[i][j] = Integer.parseInt(message[position]);
//                System.out.println(waterLevel[i][j]);
                position++;
            }
        return new Land(slot, state, cropID, plantTime, waterLevel);
    }

    public void write(String message) throws IOException {
        os.write(message);
        os.newLine();
        os.flush();
    }
}
