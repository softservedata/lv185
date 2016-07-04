<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Hello World</h1> <br> <br> <br>
<!-- Comment HTML -->
<p>Привіт</p>

<% int i=1; %>
<br>
Result is:
<%-- Comment JSP --%>
<%= new Integer(i+1) %><br>
<% if (request.getParameter("do") != null) {%>
Parameter do = 
<%= request.getParameter("do") %>
<%}%>

</body>
</html>