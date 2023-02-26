<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html>
<head>
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link href="./bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="./js/jquery-3.6.1.min.js"></script>
    <script src="./bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script> 
    <script src="./js/home.js"></script>
</head>
<body>
<security:authorize access="hasRole('Administrador') || hasRole('Capataz')">
<table class="table table-striped">
	<thead>
		<tr>
			<th>ID</th>
			<th>UsuarioCrea</th>
			<th>Explotacion</th>
			<th>Operario</th>
			<th>Status</th>
			<th>Tipo</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach begin="0" step ="1" items="${Tareas}" var="item">
			<tr>
				<td class="updtIdTarea" contenteditable=true>${item.getId() }</td>
				<td class="updtUsuarioTarea" contenteditable=true>${item.getUsuarioCrea() }</td>
				<td class="updtExplotacionTarea" contenteditable=true>${item.getExplotacion() }</td>
				<td class="updtOperarioTarea" contenteditable=true>
					<select>
						<option>${item.getOperario() }</option>
						<c:forEach begin="0" step="1" items="${Usuarios }" var="item2">
							<option>${item2.getNombre() }</option>
						</c:forEach>
					</select>
				</td>
				<td class="updtStatusTarea" contenteditable=true>${item.getStatus() }</td>
				<td class="updtTipoTarea" contenteditable=true>${item.getTipo() }</td>
				
				<td><form:form> <input type="button" class="editarTarea btn btn-primary"  value="Editar"/></form:form> </td>
				<td><form:form> <input type="button" class="eliminarTarea btn btn-danger" data="${item.getId()}" value="Eliminar"/></form:form> </td>
			</tr>
		</c:forEach>
	</tbody>	
</table>

</security:authorize>
<security:authorize access="hasRole('Operario')">

<table class="table table-striped">
	<thead>
		<tr>
			<th>UsuarioCrea</th>
			<th>Explotacion</th>
			<th>Operario</th>
			<th>Status</th>
			<th>Tipo</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach begin="0" step ="1" items="${TareasOperario}" var="item">
			<tr>
				<td class="updtUsuarioCreaTarea">${item.getUsuarioCrea() }</td>
				<td class="updtExplotacionTarea">${item.getExplotacion() }</td>
				<td class="updtOperarioTarea">${item.getOperario() }</td>
				<td class="updtStatusTarea" contenteditable=true>${item.getStatus() }</td>
				<td class="updtTipoTarea">${item.getTipo() }</td>
				
				<td><form:form> <input type="button" class="editarTarea btn btn-primary"  value="Editar"/></form:form> </td>
			</tr>
		</c:forEach>
	</tbody>	
</table>
</security:authorize>
</body>
</html>