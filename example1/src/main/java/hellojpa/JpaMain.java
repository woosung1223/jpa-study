package hellojpa;

import org.hibernate.Hibernate;
import org.hibernate.proxy.AbstractLazyInitializer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;

public class JpaMain {


    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();

        try {
            Team team = new Team();
            team.setName("1팀");
            em.persist(team);

            Member member = new Member();
            member.setUsername("바바");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member found = em.find(Member.class, member.getId());
            System.out.println(found.getTeam().getName()); // 쿼리 나감!
            System.out.println(found.getTeam().getClass()); // proxy

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
