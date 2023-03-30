package Controller;

import Model.Player;
import View.LoginForm;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static SocketHandler socketHandler;
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
        gp.startGameThread();
        socketHandler.run();


//        loginForm = new LoginForm();
//        loginForm.setVisible(true);
    }
}
