<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link href="./bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    
    
</head>
<body>

<table class="table table-striped" id="">
	<thead>
		<tr>
			<th>ID</th>
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
				<td class="updtId">${item.getId() }</td>
				<td class="updtNombre" contenteditable=true>${item.getNombre() }</td>
				<td class="updtApellidos" contenteditable=true>${item.getApellidos() }</td>
				<td class="updtDireccion" contenteditable=true>${item.getDireccion() }</td>
				<td class="updtTlf" contenteditable=true>${item.getTlf() }</td>
				<td class="updtMail" contenteditable=true>${item.getMail() }</td>
				<td class="updtNif" contenteditable=true>${item.getNif() }</td>
				<td  contenteditable=true>
					<select class="updtRol">
						<option value=${ item.getRol() } >${item.getRol()[0] }</option>
						<c:forEach begin="0" step="1" items="${Roles }" var="item2">
							<option value=${ item2.getNombre() }>${item2.getNombre() }</option>
						</c:forEach>
					</select>
				</td>
				<td class="updtPassword" contenteditable=true>${item.getPassword() }</td>
				<td><form:form> <input type="button" class="editarUsuario btn btn-primary"  value="Editar"/></form:form> </td>
				<td><form:form> <input type="button" class="eliminarUsuario btn btn-danger" data="${item.getId()}" value="Eliminar"/></form:form> </td>
				
			</tr>
		</c:forEach>
	</tbody>	
</table>


<script src="./js/jquery-3.6.1.min.js"></script>
    <script src="./bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script> 
</body>
<script src="./js/home.js"></script>
</html>
