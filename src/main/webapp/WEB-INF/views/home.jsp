<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<title>BioFruit</title>
    <link href="./bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="./js/jquery-3.6.1.min.js"></script>
    <script src="./bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script> 
    <script src="./js/home.js"></script>
</head>
<body>

<div><%@ include file ="./components/sideBar.jsp" %></div>



<div id ="tables">
</div>


<div id="mensajeUpdate">
</div>

<div class="modal fade" id="NuevoUsuarioCenter" tabindex="-1" role="dialog" aria-labelledby="NuevoUsuarioCenter" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="NuevoUsuarioLongTitle">Nuevo Usuario</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
			<form:form id="newUserForm" method="POST" action="#">
				<div class="form-group">
					<label>Nombre</label>
					<input type="text" id="formNombre" >
				</div>
				<div class="form-group">
					<label>Apellidos</label>
					<input type="text" id="formApellidos">
				</div>
					<div class="form-group">
					<label>Dirección</label>
					<input type="text" id="formDireccion">
				</div>
				<div class="form-group">
					<label>Teléfono</label>
					<input type="text" id="formTelefono">
				</div>
				<div class="form-group">
					<label>Email</label>
					<input type="text" id="formMail">
				</div>
				<div class="form-group">
					<label>NIF</label>
					<input type="text" id="formNif">
				</div>
				<div class="form-group">
					<label>Rol</label>
					<select name="rol" id="formRol">
						<c:forEach begin="0" step="1" items="${Roles }" var="item">
							<option value=${ item.getId() }>${item.getNombre() }</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label>Password</label>
					<input type="password" id="formPassword">
				</div>
				<div class="form-group">
					<label>Repita el Password</label>
					<input type="password" id="formPasswordRep">
				</div>	
			</form:form>
        </div>
        <div id="errorUsuario"></div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal" id="cerrarFormUsuario">Cerrar</button>
          <button id ="GuardarUsuario" type="button" class="btn btn-dark">Guardar</button>
        </div>
      </div>
    </div>
 </div> 
 
 
 <div class="modal fade" id="UpdateUsuarioCenter" tabindex="-1" role="dialog" aria-labelledby="UpdateUsuarioCenter" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="UpdateUsuarioLongTitle">Modificar Usuario</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
			<form:form id="updateUserForm" method="POST" action="#">
				<div class="form-group">
					<label>Nombre</label>
					<input type="text" id="updformNombre" value="${ usuario.getNombre() }">
				</div>
				<div class="form-group">
					<label>Apellidos</label>
					<input type="text" id="updformApellidos">
				</div>
					<div class="form-group">
					<label>Dirección</label>
					<input type="text" id="updformDireccion">
				</div>
				<div class="form-group">
					<label>Teléfono</label>
					<input type="text" id="updformTelefono">
				</div>
				<div class="form-group">
					<label>Email</label>
					<input type="text" id="updformMail">
				</div>
				<div class="form-group">
					<label>NIF</label>
					<input type="text" id="updformNif">
				</div>
				<div class="form-group">
					<label>Rol</label>
					<select name="rol" id="updformRol">
						<c:forEach begin="0" step="1" items="${Roles }" var="item">
							<option value=${ item.getId() }>${item.getNombre() }</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label>Password</label>
					<input type="password" id="updformPassword">
				</div>
				<div class="form-group">
					<label>Repita el Password</label>
					<input type="password" id="updformPasswordRep">
				</div>	
			</form:form>
        </div>
        <div id="errorUpdateUsuario"></div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal" id="cerrarFormUsuario">Cerrar</button>
          <button id ="updateUsuario" type="button" class="btn btn-dark">Guardar</button>
        </div>
      </div>
    </div>
 </div> 
 

 
</body>
</html>