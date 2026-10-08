package frontend;

import backend.BankService;
import javax.swing.*;

public class BalanceEnquiry {

    public BalanceEnquiry() {

        JFrame frame = new JFrame("Balance Enquiry");

        JLabel title = new JLabel("BALANCE ENQUIRY");
        title.setBounds(120, 20, 200, 30);

        JLabel accLabel = new JLabel("Account Number:");
        accLabel.setBounds(40, 80, 120, 25);

        JTextField accField = new JTextField();
        accField.setBounds(170, 80, 180, 25);

        JButton check = new JButton("Check");
        check.setBounds(40, 150, 100, 30);

        JButton clear = new JButton("Clear");
        clear.setBounds(160, 150, 100, 30);

        JButton back = new JButton("Back");
        back.setBounds(280, 150, 80, 30);

        check.addActionListener(e -> {

            try {

                String accountNumber = accField.getText().trim();

                BankService service = new BankService();

                double balance = service.checkBalance(accountNumber);

                if (balance >= 0) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Available Balance : ₹" + balance
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Account Not Found!"
                    );

                }

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        frame,
                        "Error : " + ex.getMessage()
                );

            }

        });

        clear.addActionListener(e -> {
            accField.setText("");
        });

        back.addActionListener(e -> {
            frame.dispose();
            new Dashboard();
        });

        frame.add(title);
        frame.add(accLabel);
        frame.add(accField);
        frame.add(check);
        frame.add(clear);
        frame.add(back);

        frame.setLayout(null);
        frame.setSize(420, 270);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry();
    }
}