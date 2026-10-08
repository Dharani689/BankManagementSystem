
package frontend;

import backend.BankService;
import backend.Customer;
import javax.swing.*;

public class CreateAccount {

    public CreateAccount() {

        JFrame frame = new JFrame("Create Account");

        JLabel title = new JLabel("CREATE ACCOUNT");
        title.setBounds(140, 20, 200, 30);

        JLabel name = new JLabel("Customer Name:");
        name.setBounds(40, 70, 120, 25);

        JTextField nameField = new JTextField();
        nameField.setBounds(170, 70, 180, 25);

        JLabel account = new JLabel("Account Number:");
        account.setBounds(40, 110, 120, 25);

        JTextField accountField = new JTextField();
        accountField.setBounds(170, 110, 180, 25);

        JLabel phone = new JLabel("Phone Number:");
        phone.setBounds(40, 150, 120, 25);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(170, 150, 180, 25);

        JLabel address = new JLabel("Address:");
        address.setBounds(40, 190, 120, 25);

        JTextField addressField = new JTextField();
        addressField.setBounds(170, 190, 180, 25);

        JLabel balance = new JLabel("Opening Balance:");
        balance.setBounds(40, 230, 120, 25);

        JTextField balanceField = new JTextField();
        balanceField.setBounds(170, 230, 180, 25);

        JButton save = new JButton("Save");
        save.setBounds(50, 300, 80, 30);

        JButton clear = new JButton("Clear");
        clear.setBounds(150, 300, 80, 30);

        JButton back = new JButton("Back");
        back.setBounds(250, 300, 80, 30);

        save.addActionListener(e -> {

            try {
                String customerName = nameField.getText().trim();
                String accountNumber = accountField.getText().trim();
                String phoneNumber = phoneField.getText().trim();
                String openingBalanceText = balanceField.getText().trim();

                if (customerName.isEmpty() || accountNumber.isEmpty() || phoneNumber.isEmpty()
                        || openingBalanceText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all required fields.");
                    return;
                }

                double openingBalance = Double.parseDouble(openingBalanceText);
                if (openingBalance < 0) {
                    JOptionPane.showMessageDialog(frame, "Opening balance cannot be negative.");
                    return;
                }

                Customer customer = new Customer();
                customer.setCustomerId("C" + System.currentTimeMillis());
                customer.setCustomerName(customerName);
                customer.setAccountNumber(accountNumber);
                customer.setPhoneNumber(phoneNumber);
                customer.setBalance(openingBalance);

                BankService service = new BankService();

                if (service.createAccount(customer)) {
                    JOptionPane.showMessageDialog(frame, "Account Created Successfully!");

                    nameField.setText("");
                    accountField.setText("");
                    phoneField.setText("");
                    addressField.setText("");
                    balanceField.setText("");

                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to Create Account!");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid numeric balance.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Input!");
            }

        });

        clear.addActionListener(e -> {
            nameField.setText("");
            accountField.setText("");
            phoneField.setText("");
            addressField.setText("");
            balanceField.setText("");
        });

        back.addActionListener(e -> {
            frame.dispose();
            new Dashboard();
        });

        frame.add(title);
        frame.add(name);
        frame.add(nameField);
        frame.add(account);
        frame.add(accountField);
        frame.add(phone);
        frame.add(phoneField);
        frame.add(address);
        frame.add(addressField);
        frame.add(balance);
        frame.add(balanceField);
        frame.add(save);
        frame.add(clear);
        frame.add(back);

        frame.setLayout(null);
        frame.setSize(430, 420);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CreateAccount();
    }
}