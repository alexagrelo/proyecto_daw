<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>BioFruit</title>
<link href="./bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"
	id="bootstrap-css">
<script src="./js/jquery-3.6.1.min.js"></script>
<script src="./bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="./js/home.js"></script>
<link href="./styles/home.css" rel="stylesheet" id="home-css">
</head>
<body>
	<div class="row" style="height: 100%">
		<div class="col-sm-2"><%@ include
				file="./components/sideBar.jsp"%></div>
		<div class="col-sm-10">
			<div id="tables"></div>
			<div id="mensajeUpdate" class="mt-3"></div>
		</div>
	</div>




	<!-- NUEVO USUARIO -->
	<div class="modal fade" id="NuevoUsuarioCenter" tabindex="-1"
		role="dialog" aria-labelledby="NuevoUsuarioCenter" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="NuevoUsuarioLongTitle">Nuevo
						Usuario</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form:form id="newUserForm" method="POST" action="#">
						<div class="form-group">
							<label class="form-label">Nombre</label> <input type="text" id="formNombre" class="form-control">
						</div>
						<div class="form-group">
							<label class="form-label">Apellidos</label> <input type="text" id="formApellidos" class="form-control">
						</div>
						<div class="form-group">
							<label class="form-label">Dirección</label> <input type="text" id="formDireccion" class="form-control">
						</div>
						<div class="form-group">
							<label class="form-label">Teléfono</label> <input type="text" id="formTelefono" class="form-control">
						</div>
						<div class="form-group">
							<label class="form-label">Email</label> <input type="text" id="formMail" class="form-control">
						</div>
						<div class="form-group">
							<label>NIF</label> <input type="text" id="formNif" class="form-control">
						</div>
						<div class="form-group">
							<label class="form-label">Rol</label> 
							<select name="rol" id="formRol" class="form-control">
								<c:forEach begin="0" step="1" items="${Roles }" var="item">
									<option value=${ item.getId() }>${item.getNombre() }</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label class="form-label">Password</label> <input type="password" id="formPassword" class="form-control">
						</div>
						<div class="form-group">
							<label class="form-label">Repita el Password</label> <input type="password"
								id="formPasswordRep" class="form-control">
						</div>
					</form:form>
				</div>
				<div id="errorUsuario"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-dismiss="modal" id="cerrarFormUsuario">Cerrar</button>
					<button id="GuardarUsuario" type="button" class="btn btn-success">Guardar</button>
				</div>
			</div>
		</div>
	</div>

	<!-- NUEVA TAREA -->

	<div class="modal fade" id="NuevaTareaCenter" tabindex="-1"
		role="dialog" aria-labelledby="NuevaTareaCenter" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="NuevaTareaLongTitle">Nueva Tarea</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form:form id="newTareaForm" method="POST" action="#">
						<div class="form-group">
							<label class="form-label">Explotación</label> <select name="explotacion"
								id="formExplotacion" class="form-control">
								<c:forEach begin="0" step="1" items="${explotaciones }"
									var="item">
									<option value=${item.getId() }>${item.getNombre() }</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label class="form-label">Operario</label> 
							<select name="operario" id="formOperario" class="form-control">
								<c:forEach begin="0" step="1" items="${Usuarios }" var="item">
									<option value=${item.getId() }>${item.getNombre() }</option>
								</c:forEach>
							</select>
						</div>

						<div class="form-group">
							<label class="form-label">Tipo</label> <input type="text" id="formTipo" class="form-control">
						</div>
					</form:form>
				</div>
				<div id="errorTarea"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-dismiss="modal" id="cerrarFormTarea">Cerrar</button>
					<button id="GuardarTarea" type="button" class="btn btn-success">Guardar</button>
				</div>
			</div>
		</div>
	</div>


</body>
</html>