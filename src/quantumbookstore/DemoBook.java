package quantumbookstore;

import java.time.LocalDate;

public class DemoBook extends Book {
    DemoBook(String ISBN, String title, LocalDate publishDate, double price) {
        super(ISBN, title, publishDate, price);
    }
}
