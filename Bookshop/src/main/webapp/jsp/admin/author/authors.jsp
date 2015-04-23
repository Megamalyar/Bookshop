<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../header.jsp" %>

<table border="1">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Books</td>
    </tr>
    <c:forEach items="${authors}" var="author">
        <tr>
            <td><c:out value="${author.authorId}"/></td>
            <td><c:out value="${author.authorName}"/></td>
            <td><c:out value="${author.getBookString()}"/></td>

            <td>
                <a href="authors/edit/${author.authorId}">Edit</a>&nbsp;
                <a href="authors/delete/${author.authorId}">Delete</a>
            </td>

        </tr>
    </c:forEach>
</table>
<br>

<body>
<form name="test" method="get" action="authors/new">
    <p>
        <b>Add new author</b><br>
        <input type="submit" value="ADD">
    </p>
</form>
</body>