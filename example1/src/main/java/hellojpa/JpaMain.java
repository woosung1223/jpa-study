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
            Parent parent = new Parent();
            parent.setName("패런트");

            Child child1 = new Child();
            child1.setName("차일드1");

            Child child2 = new Child();
            child2.setName("차일드2");

            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
            em.flush();
            em.clear();

            Parent foundParent = em.find(Parent.class, parent.getId());
            em.remove(foundParent);

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
