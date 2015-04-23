<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<table width="100%">
    <tr>
        <td align="center"><h2><a href="books">Books Management</a></h2></td>
        <td align="center"><h2><a href="authors">Authors Management</a></h2></td>
        <td align="center"><h2><a href="categories">Categories Management</a></h2></td>
        <td align="center"><h2><a href="orders">Orders Management</a></h2></td>
        <td align="center"><h2><a href="<c:url value="/j_spring_security_logout" />">Logout</a></h2></td>
    </tr>
</table>
<br/>