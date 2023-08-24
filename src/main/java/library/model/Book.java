package library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {

    @Id
    private String bookID;
    private String title;
    private String author;
    private int availableQuantity;
    private int issuedQuantity;
    private LocalDate addedDate;

    public Book() {
    }

    public Book(String bookID, String name, String author, int availableQuantity) {
        this.bookID = bookID;
        this.title = name;
        this.author = author;
        this.availableQuantity = availableQuantity;
        this.issuedQuantity = 0;
        this.addedDate = LocalDate.now();
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public int getIssuedQuantity() {
        return issuedQuantity;
    }

    public void setIssuedQuantity(int issuedQuantity) {
        this.issuedQuantity = issuedQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    @Override
    public String toString() {
        return "Book index: " + this.bookID + " // " + "Name: " + this.title + " // " +
                "Author: " + this.author + " // " + "Quantity: " + this.availableQuantity + " // " +
                "Books Issued: " + this.issuedQuantity + " // " + "Added Date: " + this.addedDate;
    }
}
