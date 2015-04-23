<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../header.jsp" %>

<table border="1">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Books</td>
    </tr>
    <tr>
        <td><c:out value="${author.authorId}"/></td>
        <td><c:out value="${author.authorName}"/></td>
        <td><c:out value="${author.getBookString()}"/></td>
    </tr>
</table>
<br>

<body>
(author.jsp)
<form name="test" method="post" action="update/${author.authorId}">
    <p>
        <b>Author Name</b><br>
        <input name="name" type="text" size="40" value="${author.authorName}">
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