<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  


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
				<td><form:form> <input type="button" class="editar btn btn-primary" data="${item.getId()}" value="Editar"/></form:form> </td>
				<td><form:form> <input type="button" class="eliminar btn btn-danger" data="${item.getId()}" value="Eliminar"/></form:form> </td>
				
			</tr>
		</c:forEach>
	</tbody>	
</table>
