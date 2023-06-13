package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();

        try {
            // 엔티티 생명주기 - 비영속
            Member member = new Member();
            member.setId(4L);
            member.setName("테오");

            // 엔티티 생명주기 - 영속
            System.out.println("=== BEFORE ===");
            em.persist(member);
            System.out.println("=== AFTER ===");

            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
