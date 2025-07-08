package quantumbookstore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuantumBookstore {
    private HashMap<String, BookEntry> Inventory;
    private ShippingService shippingService;
    private MailService mailService;

    QuantumBookstore(ShippingService shippingService, MailService mailService) {
        this.shippingService = shippingService;
        this.mailService = mailService;
    }

    public void addBook(Book book) {
        if (!this.Inventory.containsKey(book.getISBN())) {
            BookEntry entry = new BookEntry(book, 1);
            this.Inventory.put(book.getISBN(), entry);
        } else {
            BookEntry entry = this.Inventory.get(book.getISBN());
            entry.setQuantity(entry.getQuantity() + 1);
            this.Inventory.put(book.getISBN(), entry);
        }
    }

    public List<Book> removeOutdatedBooks(LocalDate outdateDate) {
        List<Book> outdatedBooks = new ArrayList<Book>();
        for (Map.Entry<String, BookEntry> entry : this.Inventory.entrySet()) {
            if (entry.getValue().getBook().getPublishDate().isBefore(outdateDate)) {
                this.Inventory.remove(entry.getKey());
                outdatedBooks.add(entry.getValue().getBook());
            }
        }
        return outdatedBooks;
    }

    public double buyBook(String ISBN, int quantity, String email, String address) {
        BookEntry entry = this.Inventory.get(ISBN);
        if (entry.getBook() instanceof DemoBook) {
            throw new IllegalAccessError("Demo books aren't for sale");
        }
        if (!this.Inventory.containsKey(ISBN)) {
            throw new IllegalArgumentException("Book is unavailable");
        }
        if (entry.getQuantity() - quantity <= 0) {
            throw new IllegalArgumentException("Book quantity isn't enough");
        }
        entry.setQuantity(entry.getQuantity() - quantity);
        this.Inventory.put(ISBN, entry);
        if (entry.getBook() instanceof Mailable) {
            mailService.mail((Mailable) entry.getBook());
        } else if (entry.getBook() instanceof Shippable) {
            shippingService.ship((Shippable) entry.getBook());
        }
        return quantity * entry.getBook().getPrice();
    }
}
