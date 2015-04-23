<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../header.jsp" %>

<table border="1">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Books</td>
    </tr>
    <tr>
        <td><c:out value="${category.categoryId}"/></td>
        <td><c:out value="${category.categoryName}"/></td>
        <td><c:out value="${category.getBookString()}"/></td>
    </tr>
</table>
<br>

<body>
(category.jsp)
<form name="test" method="post" action="update/${category.categoryId}">
    <p>
        <b>Category Name</b><br>
        <input name="name" type="text" size="40" value="${category.categoryName}">
    </p>

    <p>
        <b>Select book to add</b><br>
        <select name="bookId">
            <option value="0">don't add book</option>
            <c:forEach items="${books}" var="book">
                <option value="${book.bookId}">${book.bookTitle}</option>
            </c:forEach>
        </select>
    </p>

    <p>
        <input type="submit" value="send">
    </p>
</form>
</body>