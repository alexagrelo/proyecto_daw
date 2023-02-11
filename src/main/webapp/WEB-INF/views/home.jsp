<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BioFruit</title>
    <link href="./bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="./js/jquery-3.6.1.min.js"></script>
    <script src="./bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script> 
    <script src="./js/home.js"></script>
</head>
<body>
<h1>Estás en home</h1>

<table class="table table-striped">
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
			<th>password</th>
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
				<td>${item.getPassword() }</td>
			</tr>
		</c:forEach>
	</tbody>	
</table>

<table class="table table-striped">
	<thead>
		<tr>
			<th>id</th>
			<th>usuarioCrea</th>
			<th>explotacion</th>
			<th>operario</th>
			<th>status</th>
			<th>tipo</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach begin="0" step ="1" items="${Tareas}" var="item">
			<tr>
				<td>${item.getId() }</td>
				<td>${item.getUsuarioCrea() }</td>
				<td>${item.getExplotacion() }</td>
				<td>${item.getOperario() }</td>
				<td>${item.getStatus() }</td>
				<td>${item.getTipo() }</td>
			</tr>
		</c:forEach>
	</tbody>	
</table>
</body>
</html>