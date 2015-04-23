package dao;

import model.Order;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Malyar on 22.04.2015.
 */
public interface OrderDao {

    public Order getOrderById(Long id);

    public Serializable createOrder(Order order);

    public List<Order> getAllOrders();

    public Order updateOrderStatus(Order order, String status);
}
