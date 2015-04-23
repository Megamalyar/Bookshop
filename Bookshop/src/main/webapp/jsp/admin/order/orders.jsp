<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../header.jsp" %>

<table border="1">
    <tr>
        <td>OrderId</td>
        <td>UserName</td>
        <td>BookId</td>
        <td>BookTitle</td>
        <td>BookPrice</td>
        <td>OrderStatus</td>
    </tr>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td><c:out value="${order.orderId}"/></td>
            <td><c:out value="${order.userName}"/></td>
            <td><c:out value="${order.bookId}"/></td>
            <td><c:out value="${order.bookTitle}"/></td>
            <td><c:out value="${order.bookPrice}"/></td>
            <td><c:out value="${order.orderStatus}"/></td>

            <td>
                <a href="orders/setpending/${order.orderId}">Set Pending</a>&nbsp;
                <a href="orders/setclosed/${order.orderId}">Set Closed</a>
            </td>

        </tr>
    </c:forEach>
</table>
<br>
