package controller;

import dao.OrderDao;
import dao.OrderDaoImpl;
import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.OrderService;

/**
 * Created by Malyar on 22.04.2015.
 */
@Controller
@RequestMapping(value = "/admin")
public class OrderController {

    @Autowired
    private OrderService orderService;
    //private OrderDao orderService = new OrderDaoImpl();

    @RequestMapping(value = "/orders" ,method = RequestMethod.GET)
    public String showOrders(ModelMap model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin/order/orders";
    }

    @RequestMapping(value = "/orders/setpending/{id}" ,method = RequestMethod.GET)
    public String setOrderPending(@PathVariable("id") String orderId) {
        Order order = orderService.getOrderById(Long.parseLong(orderId));
        orderService.updateOrderStatus(order, "pending");
        return "redirect:/admin/orders";
    }

    @RequestMapping(value = "/orders/setclosed/{id}" ,method = RequestMethod.GET)
    public String setOrderClosed(@PathVariable("id") String orderId) {
        Order order = orderService.getOrderById(Long.parseLong(orderId));
        orderService.updateOrderStatus(order, "closed");
        return "redirect:/admin/orders";
    }

}
