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
            // JPA 실습
            Member member = em.find(Member.class, 1L);
            System.out.println(member.getName());

            // JPQL 실습
            List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();
            for (Member m : resultList) {
                System.out.println(m.getName());
            }

            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
