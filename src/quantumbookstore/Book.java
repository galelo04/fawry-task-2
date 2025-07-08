package quantumbookstore;

import java.time.LocalDate;

public abstract class Book {
    private String ISBN;
    private String title;
    private LocalDate publishDate;
    private double price;

    Book(String ISBN, String title, LocalDate publishDate, double price) {
        this.ISBN = ISBN;
        this.title = title;
        this.publishDate = publishDate;
        this.price = price;
    }

    public String getISBN() {
        return ISBN;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public String getTitle() {
        return title;
    }
}
