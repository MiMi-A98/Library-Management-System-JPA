package library.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import library.model.Book;

import java.util.List;

public class BookRepository {
    private static EntityManager em;
    private static EntityManagerFactory emf;

    static void createEntity() {
        emf = Persistence.createEntityManagerFactory("library_pu");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    static void closeEntity() {
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public static void createBook(String bookID, String name, String author, int availableQuantity) {
        createEntity();
        Book book = new Book(bookID, name, author, availableQuantity);
        em.persist(book);
        closeEntity();
    }

    public static List<Book> readAllBooks() {
        createEntity();
        CriteriaBuilder criteria = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = criteria.createQuery(Book.class);
        Root<Book> bookRoot = cq.from(Book.class);
        CriteriaQuery<Book> select = cq.select(bookRoot);
        TypedQuery<Book> query = em.createQuery(select);
        List<Book> list = query.getResultList();
        closeEntity();
        return list;
    }

    public static boolean bookExists(String id) {
        boolean bookExists = false;
        createEntity();
        Book book = em.find(Book.class, id);
        closeEntity();
        if (book != null) {
            bookExists = true;
        }
        return bookExists;
    }

    public static void updateBookIssued(String id) {
        createEntity();
        Book book = em.find(Book.class, id);
        if (book.getAvailableQuantity() == 0) {
            System.out.println("No available copies in the library!");
        } else {
            book.setIssuedQuantity(book.getIssuedQuantity() + 1);
            book.setAvailableQuantity(book.getAvailableQuantity() - 1);
        }
        closeEntity();
    }

    public static void updateBookReturned(String id) {
        createEntity();
        Book book = em.find(Book.class, id);
        if (book.getIssuedQuantity() == 0) {
            System.out.println("The book you try to return doesn't have any issued copies!");
        } else {
            book.setIssuedQuantity(book.getIssuedQuantity() - 1);
            book.setAvailableQuantity(book.getAvailableQuantity() + 1);
        }
        closeEntity();
    }

    public static void deleteBook(String id) {
        createEntity();
        Book book = em.find(Book.class, id);
        em.remove(book);
        closeEntity();
    }
}
