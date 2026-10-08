
package frontend;

import backend.BankService;
import javax.swing.*;

public class Deposit {

    public Deposit() {

        JFrame frame = new JFrame("Deposit");

        JLabel title = new JLabel("DEPOSIT AMOUNT");
        title.setBounds(130, 20, 200, 30);

        JLabel accountLabel = new JLabel("Account Number:");
        accountLabel.setBounds(40, 80, 120, 25);

        JTextField accountField = new JTextField();
        accountField.setBounds(170, 80, 180, 25);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(40, 130, 120, 25);

        JTextField amountField = new JTextField();
        amountField.setBounds(170, 130, 180, 25);

        JButton deposit = new JButton("Deposit");
        deposit.setBounds(40, 220, 100, 30);

        JButton clear = new JButton("Clear");
        clear.setBounds(160, 220, 100, 30);

        JButton back = new JButton("Back");
        back.setBounds(280, 220, 80, 30);

        deposit.addActionListener(e -> {

            try {

                String accountNumber = accountField.getText().trim();

                double depositAmount = Double.parseDouble(
                        amountField.getText().trim());

                BankService service = new BankService();

                boolean success = service.deposit(accountNumber, depositAmount);

                if (success) {

                    JOptionPane.showMessageDialog(frame,
                            "Amount Deposited Successfully!");

                    accountField.setText("");
                    amountField.setText("");

                } else {

                    JOptionPane.showMessageDialog(frame,
                            "Deposit Failed!");

                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(frame,
                        "Please Enter Valid Amount!");

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(frame,
                        "Error : " + ex.getMessage());

            }

        });

        clear.addActionListener(e -> {
            accountField.setText("");
            amountField.setText("");
        });

        back.addActionListener(e -> {
            frame.dispose();
            new Dashboard();
        });

        frame.add(title);
        frame.add(accountLabel);
        frame.add(accountField);
        frame.add(amountLabel);
        frame.add(amountField);
        frame.add(deposit);
        frame.add(clear);
        frame.add(back);

        frame.setLayout(null);
        frame.setSize(430, 330);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Deposit();
    }
}