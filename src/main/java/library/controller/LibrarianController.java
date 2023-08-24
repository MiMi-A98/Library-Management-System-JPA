package library.controller;

import library.model.Book;
import library.model.IssuedBook;
import library.model.Student;
import library.repository.BookRepository;
import library.repository.IssuedBooksRepository;
import library.repository.LibrarianRepository;
import library.repository.StudentRepository;

import java.util.List;


public class LibrarianController {
    private boolean isLoggedIn = false;

    public void login(String name, String password) {
        isLoggedIn = LibrarianRepository.librarianExists(name, password);
        if (isLoggedIn == true) {
            System.out.println("Welcome " + name);
        }
    }

    public void logout() {
        isLoggedIn = false;
        System.out.println("You have logged out");
    }

    public void addBook(String bookId, String name, String author, int quantity) {
        if (isLoggedIn == false) {
            System.out.println("You are not logged in!");
        } else {
            BookRepository.createBook(bookId, name, author, quantity);
            System.out.println("\nBook was added!");
        }
    }

    public void addStudent(String id, String name, String studentPhoneNumber) {
        if (isLoggedIn == false) {
            System.out.println("You are not logged in!");
        } else {
            StudentRepository.createStudent(id, name, studentPhoneNumber);
            System.out.println("\nStudent was added!");
        }
    }

    public void viewBooks() {
        if (isLoggedIn == false) {
            System.out.println("You are not logged in!");
        } else {
            List<Book> list = BookRepository.readAllBooks();
            for (Book b : list) {
                System.out.println(b.toString());
            }
        }
    }

    public void issueBook(String bookId, String studentId) {
        if (isLoggedIn == false) {
            System.out.println("You are not logged in!");
        } else {
           boolean bookExists = BookRepository.bookExists(bookId);
           boolean studentExists = StudentRepository.studentExists(studentId);

           if (bookExists == true && studentExists == true){
            IssuedBooksRepository.createIssuedBook(bookId, studentId);
            BookRepository.updateBookIssued(bookId);
           } else {
               System.out.println("Book or student not found!");
           }
        }
    }

    public void returnBook(String bookId, String studentId) {
        if (isLoggedIn == false) {
            System.out.println("You are not logged in!");
        } else {
            IssuedBooksRepository.deleteIssuedBook(bookId, studentId);
            BookRepository.updateBookReturned(bookId);
        }
    }

    public void deleteBook(String bookId) {
        if (isLoggedIn == false) {
            System.out.println("You are not logged in!");
        } else {
            BookRepository.deleteBook(bookId);
        }
    }

    public void viewIssuedBooks() {
        if (isLoggedIn == false) {
            System.out.println("You are not logged in!");
        } else {
            List<IssuedBook> issuedBooklist = IssuedBooksRepository.readAllIssuedBooks();
            for (IssuedBook ib : issuedBooklist) {
                System.out.println(ib.toString());
            }
        }
    }

    public void viewStudents() {
        if (isLoggedIn == false) {
            System.out.println("You are not logged in!");
        } else {
            List<Student> studentList = StudentRepository.readAllStudents();
            for (Student s : studentList) {
                System.out.println(s.toString());
            }
        }
    }
}






