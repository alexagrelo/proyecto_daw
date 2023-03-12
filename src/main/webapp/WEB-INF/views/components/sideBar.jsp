<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="./bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"
	id="bootstrap-css">
<script src="./js/jquery-3.6.1.min.js"></script>
<script src="./bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<script src="./js/home.js"></script>
</head>
<body>
	<div class="sidenav p-3 text-white bg-dark">
		<a href="./"
			class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
			<span class="sidenav-title">BioFruit</span>
			<!-- <img alt="Logo de BioFruit" src="./img/logo_biofruit.png"> -->
		</a>
		<hr>
		<ul class="nav nav-pills flex-column mb-auto">
			<li><a href="./" class="nav-link text-white"
				aria-current="page"> <i class="bi bi-house-door-fill"></i> Home
			</a></li>
			<security:authorize access="hasRole('Administrador')">
				<li><a href="#" class="nav-link text-white"
					id="btnListarUsuarios"> <i class="bi bi-people-fill"></i>
						Usuarios
				</a></li>
				<li><a href="#" class="nav-link text-white" data-toggle="modal"
					data-target="#NuevoUsuarioCenter"><i
						class="bi bi-person-fill-add"></i> Nuevo Usuario </a></li>
			</security:authorize>
			<li><a href="#" class="nav-link text-white" id="btnListarTareas"><i
					class="bi bi-list-task"></i> Tareas </a></li>
			<security:authorize
				access="hasRole('Administrador') || hasRole('Capataz')">
				<li><a href="#" class="nav-link text-white" data-toggle="modal"
					data-target="#NuevaTareaCenter"><i class="bi bi-plus-circle"></i>
						Nueva Tarea </a></li>
			</security:authorize>
			<li class="nav-item">
				<form id="logout" action="./logout" method="post">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form> <a href="javascript:document.getElementById('logout').submit()"
				class="nav-link text-white font-italic"><i
					class="bi bi-door-closed"></i> Salir </a>

			</li>
		</ul>

		<hr>

	</div>
</body>
</html>