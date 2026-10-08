package frontend;

import backend.BankService;

import javax.swing.*;

public class TransferMoney {

    public TransferMoney() {

        JFrame frame = new JFrame("Transfer Money");

        JLabel title = new JLabel("TRANSFER MONEY");
        title.setBounds(130, 20, 200, 30);

        JLabel fromLabel = new JLabel("From Account:");
        fromLabel.setBounds(40, 70, 120, 25);

        JTextField fromField = new JTextField();
        fromField.setBounds(170, 70, 180, 25);

        JLabel toLabel = new JLabel("To Account:");
        toLabel.setBounds(40, 120, 120, 25);

        JTextField toField = new JTextField();
        toField.setBounds(170, 120, 180, 25);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(40, 170, 120, 25);

        JTextField amountField = new JTextField();
        amountField.setBounds(170, 170, 180, 25);

        JButton transfer = new JButton("Transfer");
        transfer.setBounds(40, 250, 100, 30);

        JButton clear = new JButton("Clear");
        clear.setBounds(160, 250, 100, 30);

        JButton back = new JButton("Back");
        back.setBounds(280, 250, 80, 30);

        transfer.addActionListener(e -> {

            try {

                String fromAccount = fromField.getText().trim();
                String toAccount = toField.getText().trim();
                double transferAmount = Double.parseDouble(amountField.getText().trim());

                BankService service = new BankService();

                boolean success = service.transfer(
                        fromAccount,
                        toAccount,
                        transferAmount);

                if (success) {

                    JOptionPane.showMessageDialog(frame,
                            "Money Transferred Successfully!");

                    fromField.setText("");
                    toField.setText("");
                    amountField.setText("");

                } else {

                    JOptionPane.showMessageDialog(frame,
                            "Transfer Failed!\nCheck Account Number or Balance.");

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
            fromField.setText("");
            toField.setText("");
            amountField.setText("");
        });

        back.addActionListener(e -> {
            frame.dispose();
            new Dashboard();
        });

        frame.add(title);
        frame.add(fromLabel);
        frame.add(fromField);
        frame.add(toLabel);
        frame.add(toField);
        frame.add(amountLabel);
        frame.add(amountField);
        frame.add(transfer);
        frame.add(clear);
        frame.add(back);

        frame.setLayout(null);
        frame.setSize(430, 370);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new TransferMoney();
    }
}