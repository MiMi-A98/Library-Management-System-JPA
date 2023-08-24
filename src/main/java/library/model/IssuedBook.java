package library.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "issued_books")
public class IssuedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_name")
    @SequenceGenerator(name = "seq_gen_name", sequenceName = "my_sequence", initialValue = 1, allocationSize = 1)
    private int id;
    private String bookId;
    private String studentId;
    private LocalDate issuedDate;

    public IssuedBook() {
    }

    public IssuedBook(String bookId, String studentId) {
        this.bookId = bookId;
        this.studentId = studentId;
        this.issuedDate = LocalDate.now();
    }

    public String getBookId() {
        return bookId;
    }

    public String getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return "Book id: " + bookId + " // " + "Student id: " + studentId + " // " + "Issued date: " + issuedDate;
    }
}
