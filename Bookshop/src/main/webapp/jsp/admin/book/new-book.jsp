<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../header.jsp" %>

<body>
(new-book.jsp)
<form name="test" method="post" action="add">
    <p>
        <b>Book Title</b><br>
        <input name="title" type="text" size="40">
    </p>

    <p>
        <b>Book Price</b><br>
        <input name="price" type="text" size="40">
    </p>

    <p>
        <input type="submit" value="send">
    </p>
</form>
To add author or category go to edit reference
</body>
