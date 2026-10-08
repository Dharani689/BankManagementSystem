package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankService {

    private final Connection connection;

    public BankService() {
        this.connection = new DatabaseConnection().getConnection();
    }

    public boolean createAccount(Customer customer) {

        String sql = "INSERT INTO customers (customer_id, customer_name, account_number, phone_number, balance) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customer.getCustomerId());
            statement.setString(2, customer.getCustomerName());
            statement.setString(3, customer.getAccountNumber());
            statement.setString(4, customer.getPhoneNumber());
            statement.setDouble(5, customer.getBalance());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println("Create account failed: " + e.getMessage());
            return false;

        }
    }

    public Customer getCustomerByAccount(String accountNumber) {

        String sql = "SELECT * FROM customers WHERE account_number=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                return new Customer(
                        rs.getString("customer_id"),
                        rs.getString("customer_name"),
                        rs.getString("account_number"),
                        rs.getString("phone_number"),
                        rs.getDouble("balance"));

            }

        } catch (SQLException e) {

            System.err.println("Fetch customer failed: " + e.getMessage());

        }

        return null;
    }

    public boolean deposit(String accountNumber, double amount) {

        if (amount <= 0)
            return false;

        if (!updateBalance(accountNumber, amount))
            return false;

        return recordTransaction(accountNumber, "DEPOSIT", amount);
    }

    public boolean withdraw(String accountNumber, double amount) {

        if (amount <= 0)
            return false;

        Customer customer = getCustomerByAccount(accountNumber);

        if (customer == null || customer.getBalance() < amount)
            return false;

        if (!updateBalance(accountNumber, -amount))
            return false;

        return recordTransaction(accountNumber, "WITHDRAW", amount);
    }

    public boolean transfer(String fromAccount, String toAccount, double amount) {

        if (amount <= 0)
            return false;

        Customer sender = getCustomerByAccount(fromAccount);
        Customer receiver = getCustomerByAccount(toAccount);

        if (sender == null || receiver == null || sender.getBalance() < amount)
            return false;

        boolean debit = updateBalance(fromAccount, -amount);
        boolean credit = updateBalance(toAccount, amount);

        if (!debit || !credit)
            return false;

        return recordTransaction(fromAccount, "TRANSFER_OUT", amount)
                && recordTransaction(toAccount, "TRANSFER_IN", amount);
    }

    public double checkBalance(String accountNumber) {

        Customer customer = getCustomerByAccount(accountNumber);

        return customer != null ? customer.getBalance() : -1;
    }

    // ADD THIS METHOD
    public boolean updateCustomer(String accountNumber, String phoneNumber) {

        String sql = "UPDATE customers SET phone_number=? WHERE account_number=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, phoneNumber);
            statement.setString(2, accountNumber);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println("Update Customer Failed: " + e.getMessage());
            return false;

        }
    }
    public boolean deleteAccount(String accountNumber) {

    try {

        connection.setAutoCommit(false);

        // First delete transactions
        String sql1 = "DELETE FROM transactions WHERE account_number=?";

        PreparedStatement ps1 = connection.prepareStatement(sql1);
        ps1.setString(1, accountNumber);
        ps1.executeUpdate();

        // Then delete customer
        String sql2 = "DELETE FROM customers WHERE account_number=?";

        PreparedStatement ps2 = connection.prepareStatement(sql2);
        ps2.setString(1, accountNumber);

        int rows = ps2.executeUpdate();

        connection.commit();

        return rows > 0;

    } catch (SQLException e) {

        try {
            connection.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        e.printStackTrace();
        return false;

    } finally {

        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
    public List<Transaction> getTransactionHistory(String accountNumber) {

        List<Transaction> transactionList = new ArrayList<>();

        String sql = "SELECT * FROM transactions WHERE account_number=? ORDER BY transaction_id DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                transactionList.add(new Transaction(
                        rs.getInt("transaction_id"),
                        rs.getString("account_number"),
                        rs.getString("transaction_type"),
                        rs.getDouble("amount"),
                        rs.getTimestamp("transaction_date").toString()
                ));

            }

        } catch (SQLException e) {

            System.err.println("Transaction History Failed: " + e.getMessage());

        }

        return transactionList;
    }

    private boolean recordTransaction(String accountNumber,
                                      String transactionType,
                                      double amount) {

        String sql = "INSERT INTO transactions(account_number, transaction_type, amount) VALUES(?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);
            statement.setString(2, transactionType);
            statement.setDouble(3, amount);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println("Record Transaction Failed: " + e.getMessage());
            return false;

        }
    }

    private boolean updateBalance(String accountNumber,
                                  double amount) {

        String sql = "UPDATE customers SET balance = balance + ? WHERE account_number=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, amount);
            statement.setString(2, accountNumber);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println("Update Balance Failed: " + e.getMessage());
            return false;

        }
    }

    public static void main(String[] args) {

        BankService bankService = new BankService();

        String customerId = "C" + System.currentTimeMillis();
        String accountNumber = "ACC" + System.currentTimeMillis();

        Customer customer = new Customer(
                customerId,
                "Dharani",
                accountNumber,
                "9876543210",
                50000.0
        );

        System.out.println("Create Account : "
                + bankService.createAccount(customer));

        System.out.println("Balance : "
                + bankService.checkBalance(accountNumber));

        System.out.println("Deposit : "
                + bankService.deposit(accountNumber, 2000));

        System.out.println("Withdraw : "
                + bankService.withdraw(accountNumber, 1000));

        System.out.println("History Size : "
                + bankService.getTransactionHistory(accountNumber).size());

    }
}
     