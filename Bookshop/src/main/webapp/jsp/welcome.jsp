<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <body>
        <h3>Welcome : ${username}</h3>
        <a href="<c:url value="/j_spring_security_logout" />">Logout</a>
        <h2><a href="bookshop">show books</a></h2>
    </body>
</html>