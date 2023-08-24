package library.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import library.model.Student;

import java.util.List;

public class StudentRepository {
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

    public static void createStudent(String id, String name, String studentPhoneNumber) {
        createEntity();
        Student student = new Student(id, name, studentPhoneNumber);
        em.persist(student);
        closeEntity();
    }

    public static List<Student> readAllStudents() {
        createEntity();

        CriteriaBuilder criteria = em.getCriteriaBuilder();
        CriteriaQuery<Student> cq = criteria.createQuery(Student.class);

        Root<Student> studentRoot = cq.from(Student.class);
        CriteriaQuery<Student> select = cq.select(studentRoot);
        TypedQuery<Student> query = em.createQuery(select);
        List<Student> list = query.getResultList();

        closeEntity();
        return list;
    }

    public static boolean studentExists(String id) {
        boolean studentExists = false;
        createEntity();
        Student student = em.find(Student.class, id);
        closeEntity();
        if (student != null) {
            studentExists = true;
        }
        return studentExists;
    }
}
