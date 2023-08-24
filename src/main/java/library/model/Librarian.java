package library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "librarians")
public class Librarian {

    @Id
    private int id;
    private String name;
    private String password;
    private String adress;
    private String city;
    private String phone_Number;

    public Librarian() {
    }

    public Librarian(int id, String name, String password, String adress, String city, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.adress = adress;
        this.city = city;
        this.phone_Number = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + " // " + "Name: " + this.name + " // " +
                "Password: " + this.password + " // " + "Address: " + this.adress + " // " +
                "City: " + this.city + " // " + "Phone number: " + this.phone_Number;

    }

}

