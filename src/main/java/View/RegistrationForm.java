package View;

import Controller.UtilityTool;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class RegistrationForm extends JFrame {
    private JLabel usernameLabel, passwordLabel, retypePasswordLabel, imageLabel, playerNameLabel, avatarLabel;
    private JTextField usernameField, playerNameField;
    private JPasswordField retypePasswordField, passwordField;
    private JButton registerButton, returnButton;
    private JComboBox<Icon> imageComboBox;
    private ImageIcon[] imageIcon = new ImageIcon[8];

    public RegistrationForm() {
        this.setAlwaysOnTop(true);
        getImageIcon();
        Integer[] intArray = new Integer[8];
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

        avatarLabel = new JLabel("Trang phục");
        avatarLabel.setForeground(Color.WHITE);
        avatarLabel.setFont(new Font("Helvetica", Font.BOLD, 14));

        imageComboBox = new JComboBox(intArray);
        imageComboBox.setMaximumRowCount(4);
//        for (int i = 1; i < 9; i++) {
//            imageComboBox.addItem(new ImageIcon("/Player/" + i + "/down1.png"));
//        }
        imageComboBox.setModel(loadImage());
//        imageComboBox.setRenderer(new DefaultListCellRenderer() {
//            @Override
//            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
//                label.setIcon((Icon) value);
//                return label;
//            }
//        });
        imageComboBox.setPreferredSize(new Dimension(100, 80));
        imageComboBox.setSelectedIndex(0); // Đặt tùy chọn đầu tiên là mặc định
        System.out.println(imageComboBox.getSelectedIndex());
        ComboBoxRenderer renderer = new ComboBoxRenderer();
        imageComboBox.setRenderer(renderer);
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

        returnButton = new JButton("Trở lại");
        returnButton.setForeground(Color.WHITE);
        returnButton.setBackground(new Color(0, 123, 255));
        returnButton.setBorderPainted(false);
        returnButton.setFocusPainted(false);
        returnButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to handle registration here
                dispose();

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
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(avatarLabel, constraints);
        constraints.gridx = 1;
        panel.add(imageComboBox, constraints);


//        constraints.gridy = 4;
//        constraints.gridx = 1;
//        panel.add(imageComboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
//        constraints.gridwidth = 2;
        panel.add(registerButton, constraints);
        constraints.gridx = 1;
        panel.add(returnButton, constraints);

        panel.setBackground(new Color(52, 58, 64));
        this.add(panel);
        this.setTitle("Registration Form");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        int a = 1;
    }

    private DefaultComboBoxModel<Icon> loadImage() {
        DefaultComboBoxModel<Icon> dc = new DefaultComboBoxModel<Icon>();
        for (int i = 0; i < 8; i++) {
            dc.addElement(imageIcon[i]);
        }
        return dc;
    }

    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, 48, 48);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public void getImageIcon() {
        for (int i = 0; i < 8; i++) {
            imageIcon[i] = new ImageIcon(setup("/Player/" + i + "/down1"));
        }
    }

    class ComboBoxRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {
            //Get the selected index. (The index param isn't
            //always valid, so just use the value.)

            JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            renderer.setIcon(imageIcon[index]);
            return renderer;
//            int selectedIndex = (Integer) value;

//            if (isSelected) {
//                setBackground(list.getSelectionBackground());
//                setForeground(list.getSelectionForeground());
//            } else {
//                setBackground(list.getBackground());
//                setForeground(list.getForeground());
//            }

            //Set the icon and text.  If icon was null, say so.
//            ImageIcon icon = imageIcon[selectedIndex];
//            if (index != -1)
//                setIcon(icon);
////            System.out.println(index);
//            return this;
        }
    }
}
