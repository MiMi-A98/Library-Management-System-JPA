package library.controller;

import library.model.Librarian;
import library.repository.LibrarianRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class AdminController {
    boolean isLoggedIn = false;

    public void login(String id, String password) {
        try {
            InputStream credentialsResource = AdminController.class.getClassLoader().getResourceAsStream("AdminCredentials.properties");

            Properties prop = new Properties();
            prop.load(credentialsResource);

            if (id.equals(prop.get("ID")) && password.equals(prop.get("Password"))) {
                System.out.println("Welcome Admin");
                isLoggedIn = true;
            } else {
                System.out.println("Incorrect id or password ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logout() {
        isLoggedIn = false;
        System.out.println("\nYou have logged out of account Admin");
    }

    public void addLibrarian(int id, String name, String password, String address, String city, String phoneNumber) {
        if (isLoggedIn == false) {
            System.out.println("You are not logged in!");
        } else {
            LibrarianRepository.createLibrarian(id, name, password, address, city, phoneNumber);
            System.out.printf("\nLibrarian %s was created", name);
        }
    }

    public void viewLibrarians() {
        if (isLoggedIn == false) {
            System.out.println("You are not logged in!");
        } else {
            System.out.println("\nLIBRARIAN lIST:");
            List<Librarian> librarianList = LibrarianRepository.readAllLibrarians();
            for (Librarian l : librarianList) {
                System.out.println(l.toString());
            }
        }
    }

    public void deleteLibrarian(int id) {
        if (isLoggedIn == false) {
            System.out.println("You are not logged in!");
        } else {
            LibrarianRepository.deleteLibrarian(id);
        }
    }
}
