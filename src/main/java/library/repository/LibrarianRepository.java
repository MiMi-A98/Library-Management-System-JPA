package library.repository;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import library.model.Librarian;

import java.util.List;


public class LibrarianRepository {
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

    public static boolean librarianExists(String name, String password) {
        createEntity();
        boolean librarianExists = false;

        CriteriaBuilder criteria = em.getCriteriaBuilder();
        CriteriaQuery<Librarian> cq = criteria.createQuery(Librarian.class);
        Root<Librarian> librarianRoot = cq.from(Librarian.class);
        cq.select(librarianRoot).where(criteria.and(
                criteria.like(librarianRoot.get("name"), name),
                criteria.like(librarianRoot.get("password"), password)));
        TypedQuery<Librarian> query = em.createQuery(cq);

        try {
            Librarian librarian = query.getSingleResult();
            librarianExists = true;
        } catch (NoResultException e) {
            System.out.println("Incorrect id or password");
        }
        closeEntity();
        return librarianExists;
    }

    public static void createLibrarian(int id, String name, String password, String address, String city, String phoneNumber) {
        createEntity();
        Librarian librarian = new Librarian(id, name, password, address, city, phoneNumber);
        em.persist(librarian);
        closeEntity();
    }

    public static List<Librarian> readAllLibrarians() {
        createEntity();

        CriteriaBuilder criteria = em.getCriteriaBuilder();
        CriteriaQuery<Librarian> cq = criteria.createQuery(Librarian.class);

        Root<Librarian> librarianRoot = cq.from(Librarian.class);
        CriteriaQuery<Librarian> select = cq.select(librarianRoot);
        TypedQuery<Librarian> query = em.createQuery(select);
        List<Librarian> list = query.getResultList();

        closeEntity();
        return list;
    }

    public static void deleteLibrarian(int id) {
        createEntity();
        Librarian librarian = em.find(Librarian.class, id);
        em.remove(librarian);
        closeEntity();
    }

}
