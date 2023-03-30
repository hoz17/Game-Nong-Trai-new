package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JFrame {
    private JLabel usernameLabel, passwordLabel, retypePasswordLabel, imageLabel, playerNameLabel;
    private JTextField usernameField, playerNameField;
    private JPasswordField retypePasswordField, passwordField;
    private JButton registerButton;
    private JComboBox<ImageIcon> imageComboBox;
    String[] imageNames = {"/Tile/Decoration/Bush_1.png", "/Tile/Decoration/Bush_2.png"};

    public RegistrationForm() {
        usernameLabel = new JLabel("Tài khoản:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
        usernameField = new JTextField(20);

        passwordLabel = new JLabel("Mật khẩu:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
        passwordField = new JPasswordField(20);

        retypePasswordLabel = new JLabel("Nhập lại mật khẩu:");
        retypePasswordLabel.setForeground(Color.WHITE);
        retypePasswordLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
        retypePasswordField = new JPasswordField(20);

        playerNameLabel = new JLabel("Tên người chơi:");
        playerNameLabel.setForeground(Color.WHITE);
        playerNameLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
        playerNameField = new JTextField(20);

        imageLabel = new JLabel("Player");
        imageLabel.setForeground(Color.WHITE);
        imageLabel.setFont(new Font("Helvetica", Font.BOLD, 14));

        imageComboBox = new JComboBox();
        imageComboBox.setMaximumRowCount(4);
        for (int i = 0; i < 8; i++) {
            imageComboBox.addItem(new ImageIcon("/Player/" + i + "down1.png"));
        }
        imageComboBox.setSelectedIndex(0); // Đặt tùy chọn đầu tiên là mặc định
        imageComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code để xử lý sự kiện khi người dùng chọn một tùy chọn từ combobox
            }
        });
//

        registerButton = new JButton("Register");
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(new Color(0, 123, 255));
        registerButton.setBorderPainted(false);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to handle registration here
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 0, 0, 0);
        panel.add(usernameLabel, constraints);
        constraints.gridx = 1;
        panel.add(usernameField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);
        constraints.gridx = 1;
        panel.add(passwordField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(retypePasswordLabel, constraints);
        constraints.gridx = 1;
        panel.add(retypePasswordField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(playerNameLabel, constraints);
        constraints.gridx = 1;
        panel.add(playerNameField, constraints);
        constraints.gridx=0;
        constraints.gridy=4;
        panel.add(imageComboBox,constraints);


//        constraints.gridy = 4;
//        constraints.gridx = 1;
//        panel.add(imageComboBox, constraints);


        constraints.gridy = 5;
//        constraints.gridwidth = 2;
        panel.add(registerButton, constraints);
        panel.setBackground(new Color(52, 58, 64));

        this.add(panel);
        this.setTitle("Registration Form");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        RegistrationForm form = new RegistrationForm();
    }
}
