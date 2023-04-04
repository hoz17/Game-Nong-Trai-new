package Controller;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static SocketHandler socketHandler;
    public static Thread gameThread, serverThread;
//    public static LoginForm loginForm;

    public Main() {
    }

    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Nông trại trồng gì cũng được");
        GamePanel gp = new GamePanel();
        socketHandler = new SocketHandler(gp);
        window.add(gp);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        //gp.setUpGame();
        gameThread = new Thread(gp);
        serverThread = new Thread(socketHandler);
//        thread1.start();
        serverThread.start();
        gameThread.start();
//        loginForm = new LoginForm();
//        loginForm.setVisible(true);
    }
}
