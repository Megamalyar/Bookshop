<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasRole('ROLE_USER')">
    <h2>You entered as ${username}</h2>
    <a href="<c:url value="/j_spring_security_logout"/>">Logout</a><br>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <h2>You entered as ${username}</h2>
    <a href="<c:url value="/admin" />">Administration</a><br>
    <a href="<c:url value="/j_spring_security_logout"/>">Logout</a><br>
</sec:authorize>
<sec:authorize access="isAnonymous()">
    <a href="<c:url value="/login" />">Login</a><br>
</sec:authorize>

<br>
<body>
<form name="authorfilter" method="get" action="bookshop">
    <p>
        <b>Select author</b><br>
        <select name="authorId">
            <option value="0">all authors</option>
            <c:forEach items="${authors}" var="author">
                <option value="${author.authorId}">${author.authorName}</option>
            </c:forEach>
        </select>
    </p>

    <p>
        <input type="submit" value="filter">
    </p>
</form>
</body>
<br>

<table border="1">
    <tr>
        <td>Id</td>
        <td>Title</td>
        <td>Price</td>
        <td>Authors</td>
        <td>Categories</td>
    </tr>
    <c:forEach items="${books}" var="book">
        <tr>
            <td><c:out value="${book.bookId}"/></td>
            <td><c:out value="${book.bookTitle}"/></td>
            <td><c:out value="${book.bookPrice}"/></td>
            <td><c:out value="${book.getAuthorString()}"/></td>
            <td><c:out value="${book.getCategoryString()}"/></td>

            <sec:authorize access="hasRole('ROLE_USER')">
                <td>
                    <form name="test" method="post" action="bookshop/order/${book.bookId}">
                        <p>
                            <input type="submit" value="Order Book">
                        </p>
                    </form>
                </td>
            </sec:authorize>

        </tr>
    </c:forEach>
</table>
<br>
