<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#FFFFCC">
<h1 style="color:blue;">Library Management System</h1>
<h3 style="color:red;">Admin Login</h3> 

${message }

<form action="login" method="post" >
<label style="color:blue;">User name</label>
<input type="text" name="name" /><br /><br />
<label style="color:blue;">Password</label>
<input type="password" name="password" /><br /><br />

<input type="submit" value="login" />
</form>
</body>
</html>