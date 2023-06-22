package hellojpa;

import org.hibernate.Hibernate;
import org.hibernate.proxy.AbstractLazyInitializer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;
import java.util.List;

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
