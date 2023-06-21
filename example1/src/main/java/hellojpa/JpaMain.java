package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();

        try {
            Member member = new Member();
            member.setUsername("멤버");

            Locker locker = new Locker();
            locker.setName("사물함");
            locker.setMember(member);

            em.persist(member);
            em.persist(locker);

            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
