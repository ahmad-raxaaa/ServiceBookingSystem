package models;

import java.util.*;

public class Admin extends User {
    private String adminLevel;

    public Admin(int id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password);
        this.adminLevel = "MODERATOR";
    }

    public Admin(int id, String name, String email, String phone, String password, String adminLevel) {
        super(id, name, email, phone, password);
        this.adminLevel = adminLevel;
    }

    @Override
    public String getRole() {
        return "ADMIN";
    }

    public String getAdminLevel() { return adminLevel; }

    public void verifyProvider(ServiceProvider provider) {
        provider.setVerified(true);
        System.out.println("✅ Provider '" + provider.getBusinessName() + "' has been VERIFIED!");
    }

    public void generateReport(int totalCustomers, int totalProviders, int totalBookings, double revenue) {
        System.out.println("\n========== SYSTEM REPORT ==========");
        System.out.println("Total Customers: " + totalCustomers);
        System.out.println("Total Providers: " + totalProviders);
        System.out.println("Total Bookings: " + totalBookings);
        System.out.println("Total Revenue: Rs." + revenue);
        System.out.println("====================================");
    }

    public String toFileString() {
        return id + "," + name + "," + email + "," + phone + "," + password + "," + adminLevel;
    }

    public static Admin fromFileString(String line) {
        String[] parts = line.split(",");
        return new Admin(
                Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5]
        );
    }
}