package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Malyar on 22.04.2015.
 */
@Entity
@Table(name = "order_table")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "book_price")
    private Double bookPrice;

    @Column(name = "order_status")
    private String orderStatus;

    public Order() {}

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "bookId=" + bookId +
                ", orderId=" + orderId +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookPrice=" + bookPrice +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (!orderId.equals(order.orderId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return orderId.hashCode();
    }
}
