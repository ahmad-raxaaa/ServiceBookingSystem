package models;

import java.util.*;

public class ServiceProvider extends User {
    private String businessName;
    private String businessType;
    private boolean isVerified;
    private double rating;
    private List<Service> services;

    public ServiceProvider(int id, String name, String email, String phone, String password,
                           String businessName, String businessType) {
        super(id, name, email, phone, password);
        this.businessName = businessName;
        this.businessType = businessType;
        this.isVerified = false;
        this.rating = 0;
        this.services = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "PROVIDER";
    }

    public void addService(Service service) {
        services.add(service);
    }

    public List<Service> getServices() { return services; }
    public String getBusinessName() { return businessName; }
    public String getBusinessType() { return businessType; }
    public boolean isVerified() { return isVerified; }
    public void setVerified(boolean verified) { this.isVerified = verified; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String toFileString() {
        return id + "," + name + "," + email + "," + phone + "," + password + ","
                + businessName + "," + businessType + "," + isVerified + "," + rating;
    }

    public static ServiceProvider fromFileString(String line) {
        String[] parts = line.split(",");
        ServiceProvider sp = new ServiceProvider(
                Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]
        );
        sp.setVerified(Boolean.parseBoolean(parts[7]));
        sp.setRating(Double.parseDouble(parts[8]));
        return sp;
    }

    public void display() {
        System.out.println("🏢 " + businessName + " (" + businessType + ")");
        System.out.println("   Rating: " + rating + " | Verified: " + (isVerified ? "Yes" : "No"));
        System.out.println("   Services: " + services.size());
    }
}