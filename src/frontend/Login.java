package frontend;

import javax.swing.*;

public class Login {

    Login() {

        JFrame frame = new JFrame("Login");

        JLabel title = new JLabel("BANK MANAGEMENT SYSTEM");
        title.setBounds(90, 20, 220, 30);

        JLabel user = new JLabel("Username:");
        user.setBounds(50, 70, 100, 30);

        JTextField username = new JTextField();
        username.setBounds(150, 70, 180, 30);

        JLabel pass = new JLabel("Password:");
        pass.setBounds(50, 120, 100, 30);

        JPasswordField password = new JPasswordField();
        password.setBounds(150, 120, 180, 30);

        JButton login = new JButton("Login");
        login.setBounds(150, 180, 100, 30);

        login.addActionListener(e -> {
            frame.dispose();
            new Dashboard();
        });

        frame.add(title);
        frame.add(user);
        frame.add(username);
        frame.add(pass);
        frame.add(password);
        frame.add(login);

        frame.setLayout(null);
        frame.setSize(420, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}