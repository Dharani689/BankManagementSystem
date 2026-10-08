package backend;

public class Customer {
    private String customerId;
    private String customerName;
    private String accountNumber;
    private String phoneNumber;
    private double balance;

    public Customer() {
    }

    public Customer(String customerId, String customerName, String accountNumber, String phoneNumber, double balance) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.accountNumber = accountNumber;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", balance=" + balance +
                '}';
    }

    public static void main(String[] args) {
        Customer customer = new Customer(
                "C001",
                "Dharani",
                "1234567890",
                "9876543210",
                50000.0
        );

        System.out.println(customer.toString());
    }
}
