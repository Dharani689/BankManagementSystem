package frontend;


import backend.BankService;
import javax.swing.*;

public class UpdateCustomer {

    public UpdateCustomer() {

        JFrame frame = new JFrame("Update Customer");

        JLabel title = new JLabel("UPDATE CUSTOMER");
        title.setBounds(120,20,200,30);

        JLabel accLabel = new JLabel("Account Number:");
        accLabel.setBounds(40,70,120,25);

        JTextField accField = new JTextField();
        accField.setBounds(170,70,180,25);

        JLabel phoneLabel = new JLabel("New Phone:");
        phoneLabel.setBounds(40,120,120,25);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(170,120,180,25);

        JButton update = new JButton("Update");
        update.setBounds(40,220,100,30);

        JButton clear = new JButton("Clear");
        clear.setBounds(160,220,100,30);

        JButton back = new JButton("Back");
        back.setBounds(280,220,80,30);

        update.addActionListener(e -> {

            try {

                String account = accField.getText().trim();
                String phone = phoneField.getText().trim();

                BankService service = new BankService();

                if(service.updateCustomer(account, phone)){

                    JOptionPane.showMessageDialog(frame,
                            "Customer Updated Successfully!");

                    accField.setText("");
                    phoneField.setText("");

                }else{

                    JOptionPane.showMessageDialog(frame,
                            "Update Failed!");

                }

            }catch(Exception ex){

                JOptionPane.showMessageDialog(frame,
                        ex.getMessage());

            }

        });

        clear.addActionListener(e->{

            accField.setText("");
            phoneField.setText("");

        });

        back.addActionListener(e->{

            frame.dispose();
            new Dashboard();

        });

        frame.add(title);
        frame.add(accLabel);
        frame.add(accField);
        frame.add(phoneLabel);
        frame.add(phoneField);
        frame.add(update);
        frame.add(clear);
        frame.add(back);

        frame.setLayout(null);
        frame.setSize(430,330);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateCustomer();
    }
}