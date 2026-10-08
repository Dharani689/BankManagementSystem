
package frontend;

import backend.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDetails {

    public CustomerDetails() {

        JFrame frame = new JFrame("Customer Details");

        JLabel title = new JLabel("CUSTOMER DETAILS");
        title.setBounds(120, 20, 200, 30);

        JTextArea area = new JTextArea();
        area.setEditable(false);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(40, 70, 320, 180);

        JButton search = new JButton("Search");
        search.setBounds(40, 270, 100, 30);

        JButton back = new JButton("Back");
        back.setBounds(260, 270, 100, 30);

        search.addActionListener(e -> {

            String account = JOptionPane.showInputDialog(frame,
                    "Enter Account Number");

            if (account == null || account.trim().isEmpty()) {
                return;
            }

            try {

                Connection con = new DatabaseConnection().getConnection();

                if (con == null) {
                    JOptionPane.showMessageDialog(frame,
                            "Database Connection Failed!");
                    return;
                }

                String sql = "SELECT * FROM customers WHERE account_number = ?";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, account.trim());

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    area.setText(
                            "Customer ID : " + rs.getString("customer_id")
                                    + "\n\nCustomer Name : " + rs.getString("customer_name")
                                    + "\n\nAccount Number : " + rs.getString("account_number")
                                    + "\n\nPhone : " + rs.getString("phone_number")
                                    + "\n\nBalance : ₹" + rs.getDouble("balance"));

                } else {

                    area.setText("");
                    JOptionPane.showMessageDialog(frame,
                            "Customer Not Found!");

                }

                rs.close();
                ps.close();
                con.close();

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        frame,
                        "Error : " + ex.getMessage());

            }

        });

        back.addActionListener(e -> {
            frame.dispose();
            new Dashboard();
        });

        frame.add(title);
        frame.add(scroll);
        frame.add(search);
        frame.add(back);

        frame.setLayout(null);
        frame.setSize(420, 360);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CustomerDetails();
    }
}