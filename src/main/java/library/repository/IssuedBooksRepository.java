package library.repository;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import library.model.IssuedBook;

import java.util.List;

public class IssuedBooksRepository {
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

    public static void createIssuedBook(String bookId, String studentId) {
        createEntity();
        IssuedBook issuedBook = new IssuedBook(bookId, studentId);
        em.persist(issuedBook);
        closeEntity();
    }

    public static List<IssuedBook> readAllIssuedBooks() {
        createEntity();
        CriteriaBuilder criteria = em.getCriteriaBuilder();
        CriteriaQuery<IssuedBook> cq = criteria.createQuery(IssuedBook.class);

        Root<IssuedBook> issuedBookRoot = cq.from(IssuedBook.class);
        CriteriaQuery<IssuedBook> select = cq.select(issuedBookRoot);
        TypedQuery<IssuedBook> query = em.createQuery(select);
        List<IssuedBook> list = query.getResultList();
        closeEntity();
        return list;
    }

    public static void deleteIssuedBook(String bookId, String studentId) {
        createEntity();

        CriteriaBuilder criteria = em.getCriteriaBuilder();
        CriteriaQuery<IssuedBook> cq = criteria.createQuery(IssuedBook.class);
        Root<IssuedBook> issuedBookRoot = cq.from(IssuedBook.class);
        cq.select(issuedBookRoot).where(criteria.and(
                criteria.like(issuedBookRoot.get("bookId"), bookId),
                criteria.like(issuedBookRoot.get("studentId"), studentId)));
        TypedQuery<IssuedBook> query = em.createQuery(cq);
        try {
            IssuedBook issuedBook = query.getSingleResult();
            em.remove(issuedBook);
        } catch (NoResultException e) {
            System.out.printf("No issued book found for bookId %s and studentId %s%n", bookId, studentId);
        }

        closeEntity();
    }
}
