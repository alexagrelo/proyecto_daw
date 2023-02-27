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
							<label>Nombre</label> <input type="text" id="formNombre">
						</div>
						<div class="form-group">
							<label>Apellidos</label> <input type="text" id="formApellidos">
						</div>
						<div class="form-group">
							<label>Dirección</label> <input type="text" id="formDireccion">
						</div>
						<div class="form-group">
							<label>Teléfono</label> <input type="text" id="formTelefono">
						</div>
						<div class="form-group">
							<label>Email</label> <input type="text" id="formMail">
						</div>
						<div class="form-group">
							<label>NIF</label> <input type="text" id="formNif">
						</div>
						<div class="form-group">
							<label>Rol</label> <select name="rol" id="formRol">
								<c:forEach begin="0" step="1" items="${Roles }" var="item">
									<option value=${ item.getId() }>${item.getNombre() }</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label>Password</label> <input type="password" id="formPassword">
						</div>
						<div class="form-group">
							<label>Repita el Password</label> <input type="password"
								id="formPasswordRep">
						</div>
					</form:form>
				</div>
				<div id="errorUsuario"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" id="cerrarFormUsuario">Cerrar</button>
					<button id="GuardarUsuario" type="button" class="btn btn-dark">Guardar</button>
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
							<label>Explotación</label> <select name="explotacion"
								id="formExplotacion">
								<c:forEach begin="0" step="1" items="${explotaciones }"
									var="item">
									<option value=${item.getId() }>${item.getNombre() }</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label>Operario</label> <select name="operario" id="formOperario">
								<c:forEach begin="0" step="1" items="${Usuarios }" var="item">
									<option value=${item.getId() }>${item.getNombre() }</option>
								</c:forEach>
							</select>
						</div>

						<div class="form-group">
							<label>Tipo</label> <input type="text" id="formTipo">
						</div>
					</form:form>
				</div>
				<div id="errorTarea"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" id="cerrarFormTarea">Cerrar</button>
					<button id="GuardarTarea" type="button" class="btn btn-dark">Guardar</button>
				</div>
			</div>
		</div>
	</div>


</body>
</html>