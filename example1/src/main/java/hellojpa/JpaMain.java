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
            Address address = new Address("도시", "스트릿", "집코드");

            Member member1 = new Member();
            member1.setUsername("헬로1");
            member1.setHomeAddress(address);

            Address newAddress = new Address(address);

            Member member2 = new Member();
            member2.setUsername("헬로2");
            member2.setHomeAddress(newAddress);

            em.persist(member1);
            em.persist(member2);

            ts.commit();
        } catch (Exception e) {
            ts.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
