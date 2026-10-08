package frontend;

import javax.swing.*;

public class Dashboard {

    private static JFrame dashboardFrame;

    public Dashboard() {
        openDashboard();
    }

    public static void openDashboard() {
        if (dashboardFrame != null && dashboardFrame.isShowing()) {
            dashboardFrame.toFront();
            dashboardFrame.requestFocus();
            return;
        }

        dashboardFrame = new JFrame("Bank Management System - Dashboard");

        JLabel title = new JLabel("BANK MANAGEMENT SYSTEM");
        title.setBounds(100, 20, 250, 30);

        JButton create = new JButton("Create Account");
        JButton deposit = new JButton("Deposit");
        JButton withdraw = new JButton("Withdraw");
        JButton transfer = new JButton("Transfer Money");
        JButton balance = new JButton("Balance Enquiry");
        JButton history = new JButton("Transaction History");
        JButton customer = new JButton("Customer Details");
        JButton update = new JButton("Update Customer");
        JButton delete = new JButton("Delete Account");
        JButton logout = new JButton("Logout");

        create.setBounds(40, 70, 150, 35);
        deposit.setBounds(220, 70, 150, 35);

        withdraw.setBounds(40, 120, 150, 35);
        transfer.setBounds(220, 120, 150, 35);

        balance.setBounds(40, 170, 150, 35);
        history.setBounds(220, 170, 150, 35);

        customer.setBounds(40, 220, 150, 35);
        update.setBounds(220, 220, 150, 35);

        delete.setBounds(40, 270, 150, 35);
        logout.setBounds(220, 270, 150, 35);

        create.addActionListener(e -> {
            dashboardFrame.dispose();
            new CreateAccount();
        });

        deposit.addActionListener(e -> {
            dashboardFrame.dispose();
            new Deposit();
        });

        withdraw.addActionListener(e -> {
            dashboardFrame.dispose();
            new Withdraw();
        });

        transfer.addActionListener(e -> {
            dashboardFrame.dispose();
            new TransferMoney();
        });

        balance.addActionListener(e -> {
            dashboardFrame.dispose();
            new BalanceEnquiry();
        });

        history.addActionListener(e -> {
            dashboardFrame.dispose();
            new TransactionHistory();
        });

        customer.addActionListener(e -> {
            dashboardFrame.dispose();
            new CustomerDetails();
        });

        update.addActionListener(e -> {
            dashboardFrame.dispose();
            new UpdateCustomer();
        });

        delete.addActionListener(e -> {
            dashboardFrame.dispose();
            new DeleteAccount();
        });

        logout.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(
                    dashboardFrame,
                    "Are you sure you want to Logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                dashboardFrame.dispose();
                new Login();
            }
        });

        dashboardFrame.add(title);
        dashboardFrame.add(create);
        dashboardFrame.add(deposit);
        dashboardFrame.add(withdraw);
        dashboardFrame.add(transfer);
        dashboardFrame.add(balance);
        dashboardFrame.add(history);
        dashboardFrame.add(customer);
        dashboardFrame.add(update);
        dashboardFrame.add(delete);
        dashboardFrame.add(logout);

        dashboardFrame.setLayout(null);
        dashboardFrame.setSize(430, 380);
        dashboardFrame.setLocationRelativeTo(null);
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboardFrame.setVisible(true);
    }

    public static void main(String[] args) {
        openDashboard();
    }
}