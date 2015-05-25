package service;

import dao.OrderDao;
import model.Order;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Malyar on 20.05.2015.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final static Logger log = Logger.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;
    
    @Override
    public Order getOrderById(Long id) {
        Order order = orderDao.getOrderById(id);
        log.info("order: = " + order + " chosen");
        return order;
    }

    @Override
    public Serializable createOrder(Order order) {
        Serializable aut = orderDao.createOrder(order);
        log.info("order: = " + order + " created");
        return aut;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = orderDao.getAllOrders();
        for (Order order : orders) {
            log.info("order: = " + order + " listed");
        }
        return orders;
    }

    @Override
    public Order updateOrderStatus(Order order, String status) {
        Order order1 = orderDao.updateOrderStatus(order, status);
        log.info("order: = " + order1 + " status updated");
        return order1;
    }
}
