<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../header.jsp" %>

<table border="1">
    <tr>
        <td>Title</td>
        <td>Price</td>
        <td>Authors</td>
        <td>Categories</td>
    </tr>
    <tr>
        <td><c:out value="${book.bookTitle}"/></td>
        <td><c:out value="${book.bookPrice}"/></td>
        <td><c:out value="${book.getAuthorString()}"/></td>
        <td><c:out value="${book.getCategoryString()}"/></td>
    </tr>
</table>
<br>

<body>
(book.jsp)
<form name="test" method="post" action="update/${book.bookId}">
    <p>
        <b>Book Title</b><br>
        <input name="title" type="text" size="40" value="${book.bookTitle}">
    </p>

    <p>
        <b>Book Price</b><br>
        <input name="price" type="text" size="40" value="${book.bookPrice}">
    </p>

    <p>
        <b>Select author to add</b><br>
        <select name="authorId">
            <option value="0">don't add author</option>
            <c:forEach items="${authors}" var="author">
                <option value="${author.authorId}">${author.authorName}</option>
            </c:forEach>
        </select>
    </p>

    <p>
        <b>Select category to add</b><br>
        <select name="categoryId">
            <option value="0">don't add category</option>
            <c:forEach items="${categories}" var="category">
                <option value="${category.categoryId}">${category.categoryName}</option>
            </c:forEach>
        </select>
    </p>

    <p>
        <input type="submit" value="send">
    </p>
</form>
</body>