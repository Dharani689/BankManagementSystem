package frontend;


import backend.BankService;
import backend.Transaction;
import java.util.List;
import javax.swing.*;

public class TransactionHistory {

    public TransactionHistory() {

        JFrame frame = new JFrame("Transaction History");

        JLabel title = new JLabel("TRANSACTION HISTORY");
        title.setBounds(120,20,220,30);

        JLabel accountLabel = new JLabel("Account Number:");
        accountLabel.setBounds(40,60,120,25);

        JTextField accountField = new JTextField();
        accountField.setBounds(170,60,180,25);

        JTextArea area = new JTextArea();
        area.setEditable(false);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(40,100,340,180);

        JButton search = new JButton("Search");
        search.setBounds(40,300,90,30);

        JButton clear = new JButton("Clear");
        clear.setBounds(150,300,90,30);

        JButton back = new JButton("Back");
        back.setBounds(260,300,90,30);

        search.addActionListener(e -> {

            try {

                String accountNumber = accountField.getText().trim();

                BankService service = new BankService();

                List<Transaction> list =
                        service.getTransactionHistory(accountNumber);

                area.setText("");

                if (list.isEmpty()) {

                    area.setText("No Transactions Found.");

                } else {

                    for (Transaction t : list) {

                        area.append(
                                "Transaction ID : " + t.getTransactionId()
                                + "\nAccount Number : " + t.getAccountNumber()
                                + "\nType : " + t.getTransactionType()
                                + "\nAmount : ₹" + t.getAmount()
                                + "\nDate : " + t.getTransactionDate()
                                + "\n--------------------------------------\n");

                    }

                }

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(frame,
                        "Error : " + ex.getMessage());

            }

        });

        clear.addActionListener(e -> {

            accountField.setText("");
            area.setText("");

        });

        back.addActionListener(e -> {

            frame.dispose();
            new Dashboard();

        });

        frame.add(title);
        frame.add(accountLabel);
        frame.add(accountField);
        frame.add(scroll);
        frame.add(search);
        frame.add(clear);
        frame.add(back);

        frame.setLayout(null);
        frame.setSize(450,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new TransactionHistory();
    }
}