package models;

public class Customer extends User {
    private double loyaltyPoints;
    private int totalBookings;

    public Customer(int id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password);
        this.loyaltyPoints = 0;
        this.totalBookings = 0;
    }

    @Override
    public String getRole() {
        return "CUSTOMER";
    }

    public void addLoyaltyPoints(double points) {
        this.loyaltyPoints += points;
    }

    public double getLoyaltyPoints() { return loyaltyPoints; }
    public int getTotalBookings() { return totalBookings; }
    public void incrementBookings() { totalBookings++; }

    public String toFileString() {
        return id + "," + name + "," + email + "," + phone + "," + password + "," + loyaltyPoints + "," + totalBookings;
    }

    public static Customer fromFileString(String line) {
        String[] parts = line.split(",");
        Customer c = new Customer(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4]);
        c.loyaltyPoints = Double.parseDouble(parts[5]);
        c.totalBookings = Integer.parseInt(parts[6]);
        return c;
    }

    public void display() {
        System.out.println("Customer: " + name + " | Email: " + email + " | Points: " + (int)loyaltyPoints);
    }
}