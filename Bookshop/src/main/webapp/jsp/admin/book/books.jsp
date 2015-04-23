<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../header.jsp" %>

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

            <td>
                <a href="books/edit/${book.bookId}">Edit</a>&nbsp;
                <a href="books/delete/${book.bookId}">Delete</a>
            </td>

        </tr>
    </c:forEach>
</table>
<br>

<body>
<form name="test" method="get" action="books/new">
    <p>
        <b>Add new book</b><br>
        <input type="submit" value="ADD">
    </p>
</form>
</body>