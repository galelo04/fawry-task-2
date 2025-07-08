package quantumbookstore;

import java.time.LocalDate;

public class Ebook extends Book implements Mailable {
    private String fileType;

    Ebook(String ISBN, String title, LocalDate publishDate, double price, String fileType) {
        super(ISBN, title, publishDate, price);
        this.fileType = fileType;
    }
}
