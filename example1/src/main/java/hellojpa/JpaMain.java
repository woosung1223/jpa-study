package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class JpaMain {


    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();

        try {
            Album album = new Album();
            album.setName("김앨범");
            album.setArtist("김씨");
            album.setPrice(1000);

            em.persist(album);
            em.flush();
            em.clear();

            Item found = em.find(Item.class, album.getId());
            System.out.println(found.getName());

            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
