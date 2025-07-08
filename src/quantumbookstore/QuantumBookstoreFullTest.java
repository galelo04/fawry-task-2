package quantumbookstore;

import java.time.LocalDate;

public class QuantumBookstoreFullTest {
    public static void main(String[] args) {
        ShippingService shippingService = new ShippingService();
        MailService mailService = new MailService();
        QuantumBookstore store = new QuantumBookstore(shippingService, mailService);

        LocalDate date = LocalDate.now();
        // Add books
        store.addBook(new PaperBook("001", "Clean Code", date, 45.0, 10));
        store.addBook(new Ebook("002", "Effective Java", date, 30.0, "PDF"));
        store.addBook(new DemoBook("003", "Quantum Theory", date, 0.0));

        System.out.println("Quantum book store: Added 3 books.");

        store.removeOutdatedBooks(date);

        try {
            double amount = store.buyBook("001", 2, "user@example.com", "123 Book Street");
            System.out.println("Buy 2 copies of Clean Code for $" + amount);
        } catch (Exception e) {
            System.out.println("Quantum book store: " + e.getMessage());
        }

        try {
            double amount = store.buyBook("002", 1, "user@example.com", null);
            System.out.println("buy 1 copy of Effective Java for $" + amount);
        } catch (Exception e) {
            System.out.println("Quantum book store: " + e.getMessage());
        }

        try {
            store.buyBook("003", 1, "user@example.com", null);
        } catch (Exception e) {
            System.out.println("Quantum book store: " + e.getMessage());
        }
    }
}