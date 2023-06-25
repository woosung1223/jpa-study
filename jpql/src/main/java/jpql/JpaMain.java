package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();

        try {
            Member member = new Member();
            member.setUsername("테오");
            member.setAge(21);
            em.persist(member);

            // 1. Query 타입 조회
            Query query = em.createQuery("select m.username, m.age from Member as m where m.age > 20");
            List resultList = query.getResultList();
            Object o = resultList.get(0);
            Object[] oList = (Object[]) o;
            System.out.println(oList[0]); // 테오
            System.out.println(oList[1]); // 21

            // 2. Object[] 타입 조회
            List<Object[]> objectList =
                    em.createQuery("select m.username, m.age from Member as m where m.age > 20")
                    .getResultList();
            Object[] oList2 = objectList.get(0);
            System.out.println(oList2[0]); // 테오
            System.out.println(oList2[1]); // 21

            // 3. new 명령어로 조회
            List<MemberDto> memberDtos =
                    em.createQuery("select new jpql.MemberDto(m.username, m.age) from Member as m where m.age > 20", MemberDto.class)
                    .getResultList();
            System.out.println(memberDtos.get(0).getUsername());
            System.out.println(memberDtos.get(0).getAge());

            ts.commit();
        } catch (Exception e) {
            e.printStackTrace();
            ts.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
