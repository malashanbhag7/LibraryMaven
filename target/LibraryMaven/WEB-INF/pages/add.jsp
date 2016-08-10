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

${message }<br />
<form method="post">
Enter Book name:<input type="text" name="name" /><br />
Enter no. of copies<input type="text" name="copies" /><br />
<input type="submit" value="add">
</form>
<br />
<form  action="home" method="get" >
<input type="submit" value="back">

</form>
</body>
</html>