<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../header.jsp" %>

<table border="1">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Books</td>
    </tr>
    <c:forEach items="${categories}" var="category">
        <tr>
            <td><c:out value="${category.categoryId}"/></td>
            <td><c:out value="${category.categoryName}"/></td>
            <td><c:out value="${category.getBookString()}"/></td>

            <td>
                <a href="categories/edit/${category.categoryId}">Edit</a>&nbsp;
                <a href="categories/delete/${category.categoryId}">Delete</a>
            </td>

        </tr>
    </c:forEach>
</table>
<br>

<body>
<form name="test" method="get" action="categories/new">
    <p>
        <b>Add new category</b><br>
        <input type="submit" value="ADD">
    </p>
</form>
</body>