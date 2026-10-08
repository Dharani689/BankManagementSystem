package frontend;

import backend.BankService;
import javax.swing.*;

public class DeleteAccount {

    public DeleteAccount() {

        JFrame frame = new JFrame("Delete Account");

        JLabel title = new JLabel("DELETE ACCOUNT");
        title.setBounds(130, 20, 200, 30);

        JLabel acc = new JLabel("Account Number:");
        acc.setBounds(40, 80, 120, 25);

        JTextField accField = new JTextField();
        accField.setBounds(170, 80, 180, 25);

        JButton delete = new JButton("Delete");
        delete.setBounds(40, 170, 100, 30);

        JButton clear = new JButton("Clear");
        clear.setBounds(160, 170, 100, 30);

        JButton back = new JButton("Back");
        back.setBounds(280, 170, 80, 30);

        delete.addActionListener(e -> {

            try {

                String account = accField.getText().trim();

                if (account.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Enter Account Number");
                    return;
                }

                int option = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to delete this account?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

                if (option == JOptionPane.YES_OPTION) {

                    BankService service = new BankService();

                    if (service.deleteAccount(account)) {

                        JOptionPane.showMessageDialog(frame,
                                "Account Deleted Successfully!");

                        accField.setText("");

                    } else {

                        JOptionPane.showMessageDialog(frame,
                                "Account Not Found!");

                    }
                }

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(frame,
                        "Error : " + ex.getMessage());

            }

        });

        clear.addActionListener(e -> accField.setText(""));

        back.addActionListener(e -> {

            frame.dispose();
            new Dashboard();

        });

        frame.add(title);
        frame.add(acc);
        frame.add(accField);
        frame.add(delete);
        frame.add(clear);
        frame.add(back);

        frame.setLayout(null);
        frame.setSize(420, 280);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new DeleteAccount();
    }
}