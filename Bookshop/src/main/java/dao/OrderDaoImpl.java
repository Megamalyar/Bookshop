package dao;

import model.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Malyar on 22.04.2015.
 */
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext(unitName = "bookshopUnit")
    private static EntityManager em;

    private static EntityTransaction tx;

    static {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("bookshopUnit");
        System.out.println(entityManagerFactory.getClass().getSimpleName());
        em = entityManagerFactory.createEntityManager();
        System.out.println("EntityManager Created : " + em);
        tx = em.getTransaction();
    }

    @Override
    public Order getOrderById(Long id) {
        Order order = em.find(Order.class, id);
        return order;
    }

    @Override
    public Serializable createOrder(Order order) {
        tx.begin();
        em.persist(order);
        tx.commit();
        return order.getOrderId();
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> allOrders = em.createQuery("SELECT a FROM Order a",
                Order.class).getResultList();
        return allOrders;
    }

    @Override
    public Order updateOrderStatus(Order order, String status) {
        tx.begin();
        order.setOrderStatus(status);
        em.merge(order);
        tx.commit();
        return order;
    }
}
