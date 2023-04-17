package View;

import Controller.GamePanel;
import Controller.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginForm extends JFrame {
    GamePanel gp;
    private JLabel nameLabel, passwordLabel;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginForm(GamePanel gp) {
        this.gp = gp;
        this.setAlwaysOnTop(true);
        nameLabel = new JLabel("Tài khoản:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
        nameField = new JTextField(20);


        passwordLabel = new JLabel("Mật khẩu:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
        passwordField = new JPasswordField(20);


        loginButton = new JButton("  Đăng nhập  ");
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String username, password;
                    username = nameField.getText();
                    if (username.isEmpty()) throw new Exception("Vui lòng nhập tài khoản !");
                    password = passwordField.getText();
                    if (password.isEmpty()) throw new Exception("Vui lòng nhập mật khẩu !");
                    try {
                        Main.socketHandler.write("client-verify=" + username + "=" + password);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                }
            }

        });


        registerButton = new JButton("Đăng ký");
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(new Color(0, 123, 255));
        registerButton.setBorderPainted(false);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gp.loginForm.setVisible(false);
                RegistrationForm form = new RegistrationForm();
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(nameLabel, constraints);
        constraints.gridx = 1;
        panel.add(nameField, constraints);


        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(passwordLabel, constraints);
        constraints.gridx = 1;
        panel.add(passwordField, constraints);
        constraints.gridy = 3;
        constraints.gridx = 1;
        constraints.gridwidth = 4;
        constraints.insets = new Insets(5, -100, 10, 0); // giảm khoảng cách trên cùng của nút Register xuống 5 pixel
        panel.add(loginButton, constraints);
        constraints.gridy = 3;
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(5, 100, 10, 0); // giảm khoảng cách trên cùng của nút Register xuống 5 pixel
        panel.add(registerButton, constraints);


        panel.setBackground(new Color(52, 58, 64));

        this.add(panel);

        this.setTitle("Registration Form");
        this.setSize(350, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void wrongUser() {
        JOptionPane.showMessageDialog(rootPane, "Sai tài khoản hoặc mật khẩu !");
    }
//    public static void main(String[] args) {
//        login form = new login();
//    }
}
