package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

enum BookingStatus {
    PENDING, CONFIRMED, COMPLETED, CANCELLED
}

public class Booking {
    private static int counter = 1000;
    private int bookingId;
    private Customer customer;
    private ServiceProvider provider;
    private Service service;
    private LocalDateTime dateTime;
    private BookingStatus status;
    private double amount;
    private String paymentMethod;

    public Booking(Customer customer, ServiceProvider provider, Service service, LocalDateTime dateTime) {
        this.bookingId = ++counter;
        this.customer = customer;
        this.provider = provider;
        this.service = service;
        this.dateTime = dateTime;
        this.status = BookingStatus.PENDING;
        this.amount = service.getPrice();
        this.paymentMethod = "PENDING";
    }

    public void confirm() {
        if (status == BookingStatus.PENDING) {
            status = BookingStatus.CONFIRMED;
            customer.addLoyaltyPoints(amount * 0.05);
            System.out.println("✅ Booking #" + bookingId + " CONFIRMED!");
        }
    }

    public void complete() {
        if (status == BookingStatus.CONFIRMED) {
            status = BookingStatus.COMPLETED;
            customer.incrementBookings();
            System.out.println("✅ Service COMPLETED!");
        }
    }

    public void cancel() {
        if (status == BookingStatus.PENDING || status == BookingStatus.CONFIRMED) {
            status = BookingStatus.CANCELLED;
            System.out.println("❌ Booking #" + bookingId + " CANCELLED");
        }
    }

    public void setPaymentMethod(String method) { this.paymentMethod = method; }
    public int getBookingId() { return bookingId; }
    public Customer getCustomer() { return customer; }
    public ServiceProvider getProvider() { return provider; }
    public Service getService() { return service; }
    public BookingStatus getStatus() { return status; }
    public double getAmount() { return amount; }

    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return bookingId + "," + customer.getId() + "," + provider.getId() + ","
                + service.getId() + "," + dateTime.format(formatter) + ","
                + status + "," + amount + "," + paymentMethod;
    }

    public void display() {
        System.out.println("Booking #" + bookingId + " | " + service.getName() + " | " + dateTime + " | " + status);
    }
}