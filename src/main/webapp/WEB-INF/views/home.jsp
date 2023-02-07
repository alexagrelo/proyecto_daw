<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>EStás en home</h1>

<table>
	<thead>
		<tr>
			<th>id</th>
			<th>nombre</th>
			<th>apellidos</th>
			<th>direccion</th>
			<th>telefono</th>
			<th>mail</th>
			<th>nif</th>
			<th>rol</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach begin="0" step ="1" items="${Usuarios}" var="item">
			<tr>
				<td>${item.getId() }</td>
				<td>${item.getNombre() }</td>
				<td>${item.getApellidos() }</td>
				<td>${item.getDireccion() }</td>
				<td>${item.getTlf() }</td>
				<td>${item.getMail() }</td>
				<td>${item.getNif() }</td>
				<td>${item.getRol() }</td>
		</c:forEach>
	</tbody>	
</table>
</body>
</html>