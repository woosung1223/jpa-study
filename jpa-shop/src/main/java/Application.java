import domain.Item;
import domain.Member;
import domain.Order;
import domain.OrderItem;
import domain.OrderStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class Application {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();

        try {
            Member member = new Member();
            member.setName("사람");

            Item item = new Item();
            item.setName("빵");
            item.setPrice(1000);
            item.setStockQuantity(1);

            Order order = new Order();
            order.setMember(member);
            order.setOrderDate(LocalDateTime.now());
            order.setStatus(OrderStatus.ORDER);

            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(1);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);

            em.persist(member);
            em.persist(item);
            em.persist(order);
            em.persist(orderItem);

            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
