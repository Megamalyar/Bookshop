package dao;

import model.Order;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Malyar on 20.05.2015.
 */
@Repository
public class HibernateOrderDao implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Order getOrderById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Order order = (Order) session.get(Order.class, id);
        session.getTransaction().commit();
        //session.close();
        return order;
    }

    @Override
    public Serializable createOrder(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Serializable id = session.save(order);
        session.getTransaction().commit();
        //session.close();
        return id;
    }

    @Override
    public List<Order> getAllOrders() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        SQLQuery q = session.createSQLQuery("select * from order_table");
        q.addEntity(Order.class);
        List<Order> orderList = (List<Order>) q.list();
        //session.close();
        return orderList;
    }

    @Override
    public Order updateOrderStatus(Order order, String status) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        order.setOrderStatus(status);
        session.update(order);
        session.getTransaction().commit();
        //session.close();
        return order;        
    }
}
