<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../header.jsp" %>

<body>
(new-category.jsp)
<form name="test" method="post" action="add">
    <p>
        <b>Category Name</b><br>
        <input name="name" type="text" size="40">
    </p>

    <p>
        <input type="submit" value="send">
    </p>
</form>
To add book for this category go to edit reference
</body>
