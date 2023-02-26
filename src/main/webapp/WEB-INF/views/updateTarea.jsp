<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<title>Insert title here</title>
	
</head>
<body>
<c:if test="${error}">
	<p style="color:red">${mensaje}</p>
</c:if>
<c:if test="${!error}">
	<p style="color:green">${mensaje}</p>
</c:if>

</body>
</html>