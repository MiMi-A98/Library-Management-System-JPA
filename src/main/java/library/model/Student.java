package library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
    @Id
    private String id;
    private String name;
    private String phone_Number;

    public Student() {
    }

    public Student(String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phone_Number = phoneNumber;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + " // " + "Student name: " + this.name + " // " + "Student phone number: " + this.phone_Number;
    }
}


