package quantumbookstore;

import java.time.LocalDate;

public class PaperBook extends Book implements Shippable {
    private int stock;

    PaperBook(String ISBN, String title, LocalDate publisDate, double price, int stock) {
        super(ISBN, title, publisDate, price);
        this.stock = stock;
    }
}
