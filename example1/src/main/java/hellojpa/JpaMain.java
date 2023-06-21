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
            Team team = new Team();
            team.setName("1팀");

            Member member = new Member();
            member.setUsername("가나다");

            team.addMember(member);

            em.persist(member);
            em.persist(team);

            Team found = em.find(Team.class, team.getId());
            for (Member foundMember : found.getMembers()) {
                System.out.println(foundMember.getUsername());
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
