package payment;

import models.Booking;
import java.util.Scanner;

interface PaymentMethod {
    boolean pay(double amount);
    String getName();
}

class CashPayment implements PaymentMethod {
    @Override
    public boolean pay(double amount) {
        System.out.println("💰 Please pay Rs." + amount + " in cash at the time of service");
        return true;
    }
    @Override
    public String getName() { return "CASH"; }
}

class CardPayment implements PaymentMethod {
    private String cardNumber;
    public CardPayment(String cardNumber) {
        this.cardNumber = "****" + cardNumber.substring(cardNumber.length() - 4);
    }
    @Override
    public boolean pay(double amount) {
        System.out.println("💳 Card payment of Rs." + amount + " processed");
        System.out.println("   Card: " + cardNumber);
        return true;
    }
    @Override
    public String getName() { return "CARD"; }
}

class EasyPaisaPayment implements PaymentMethod {
    private String phoneNumber;
    public EasyPaisaPayment(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public boolean pay(double amount) {
        System.out.println("📱 EasyPaisa payment of Rs." + amount + " from " + phoneNumber);
        return true;
    }
    @Override
    public String getName() { return "EASYPAISA"; }
}

public class Payment {
    private static Scanner scanner = new Scanner(System.in);

    public static void processPayment(Booking booking) {
        System.out.println("\n=== PAYMENT ===");
        System.out.println("Amount: Rs." + booking.getAmount());
        System.out.println("1. Cash");
        System.out.println("2. Card");
        System.out.println("3. EasyPaisa");
        System.out.print("Choose payment method: ");

        int choice = Integer.parseInt(scanner.nextLine());
        PaymentMethod method = null;

        switch(choice) {
            case 1:
                method = new CashPayment();
                break;
            case 2:
                System.out.print("Enter card number: ");
                String card = scanner.nextLine();
                method = new CardPayment(card);
                break;
            case 3:
                System.out.print("Enter EasyPaisa number: ");
                String phone = scanner.nextLine();
                method = new EasyPaisaPayment(phone);
                break;
        }

        if (method.pay(booking.getAmount())) {
            booking.setPaymentMethod(method.getName());
            booking.confirm();
            System.out.println("✅ Payment successful via " + method.getName());
        }
    }
}