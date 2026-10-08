package frontend;

import javax.swing.*;

public class SplashScreen {

    SplashScreen() {

        JFrame frame = new JFrame("Bank Management System");

        JLabel title = new JLabel("WELCOME TO BANK MANAGEMENT SYSTEM", SwingConstants.CENTER);
        title.setBounds(30, 100, 340, 40);

        frame.add(title);

        frame.setLayout(null);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }

        frame.dispose();
        new Login();
    }
}
