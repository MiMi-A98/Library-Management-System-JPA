# Library Management System with JPA

This mini project simulates a library management system using Java, JPA (Java Persistence API), Hibernate, and MySQL. The system allows an administrator to manage librarians and enables librarians to manage books and students. The project demonstrates the use of JPA and Hibernate for ORM (Object-Relational Mapping), providing a seamless interaction between the Java application and the MySQL database.

## Features:

- **Admin Features:**
  - **Login/Logout:** Authentication for the admin using stored credentials.
  - **Librarian Management:** Add, view, and manage librarians, with data stored in the MySQL database via JPA and Hibernate.


- **Librarian Features:**
  - **Login/Logout:** Authentication for librarians.
  - **Book Management:**
    - **Add Books:** Librarians can add new books to the library.
    - **View Books:** Display a list of all available books.
    - **Delete Books:** Remove books from the library.
  - **Student Management:**
    - **Add Students:** Register new students with unique IDs.
    - **View Students:** Display a list of all registered students.
  - **Issue Books:** Issue books to students and track issued books.
  - **Return Books:** Process book returns and update the inventory.

## Technical Details:

- **JPA and Hibernate:** Used for ORM to map Java objects to MySQL database tables, making database interactions straightforward and reducing the need for manual SQL queries.
- **MySQL:** Stores data for administrators, librarians, books, students, and issued books, ensuring persistence across sessions.

## Learning Focus:

- **ORM with JPA and Hibernate:** Practical application of ORM concepts to simplify database operations in Java.
- **Database Management:** Implementation of CRUD operations for managing librarians, books, students, and issued books.
- **Role Management:** Differentiated functionalities for admin and librarian roles.

This project effectively demonstrates the use of JPA and Hibernate for managing a database-driven application, with a focus on role-based access, CRUD operations, and persistent data management.