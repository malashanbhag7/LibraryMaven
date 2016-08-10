<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#FFFFCC">

${message }
<c:if test="${booklist ne null }">

<h4>book details</h4>
<table border="3.0">

<c:forEach var="book1" items="${booklist}" >
<tr>
<td><c:out value="${book1.name}" /></td>
<td><c:out value="${book1.copies}" /></td>
<td><form method="post" action="remove">
<input type="text" name="id" />
<input type="submit" value="remove" />>
</form>
</td>
</tr>

</c:forEach>


</table>
</c:if>
<br />
<form  action="home" method="get" >
<input type="submit" value="back">

</form>
</body>
</html>