package library;

import library.controller.AdminController;
import library.controller.LibrarianController;
import library.repository.IssuedBooksRepository;

public class Main {
    static LibrarianController librarianController = new LibrarianController();
    static AdminController adminController = new AdminController();

    public static void main(String[] args) {

        initializeData();

        adminController.login("admin", "admin98");
        adminController.viewLibrarians();
        adminController.logout();

        librarianController.login("mara", "mara");

        librarianController.viewBooks();
        librarianController.viewStudents();

        librarianController.deleteBook("v2");
        librarianController.viewBooks();

        librarianController.viewIssuedBooks();

        librarianController.returnBook("v2", "103");
        librarianController.returnBook("v2", "104");
        librarianController.returnBook("v2", "sm004");
        librarianController.returnBook("v2", "31");

        IssuedBooksRepository.deleteIssuedBook("v2", "103");

        librarianController.returnBook("v2", "31");
        librarianController.returnBook("a43", "31");
        librarianController.returnBook("z123", "sm004");

        librarianController.viewBooks();

        librarianController.logout();

    }

    public static void initializeData() {
        adminController.login("admin", "admin98");

        adminController.addLibrarian(2,"mara", "mara", "iasi", "iasi", "8976");
        adminController.addLibrarian(3,"cristina", "mar", "iasi", "iasi", "8976");
        adminController.addLibrarian(4,"simona", "para", "iasi", "iasi", "8976");
        adminController.addLibrarian(5,"ioana", "mere", "iasi", "iasi", "8976");

        adminController.logout();

        librarianController.login("mara", "mara");

        librarianController.addBook("a433", "abc", "abc", 4);
        librarianController.addBook("z123", "abc", "abc", 4);
        librarianController.addBook("v2", "abc", "abc", 4);
        librarianController.addBook("l45", "abc", "abc", 4);

        librarianController.addStudent("sm34", "mara", "876");
        librarianController.addStudent("sm004", "simona", "876");
        librarianController.addStudent("sl004", "mimi", "876");

        librarianController.logout();
    }
}