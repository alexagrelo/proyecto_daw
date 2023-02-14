<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html >
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="./bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="./js/jquery-3.6.1.min.js"></script>
    <script src="./bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script> 
    <script src="./js/home.js"></script>
</head>
<body>
  <div class="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark" style="width: 280px;">
    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
      <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"/></svg>
      <span class="fs-4">Sidebar</span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item">
        <a href="./" class="nav-link active" aria-current="page">
          Home
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-white" id="btnListarTareas">
          Tareas
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-white" id="btnListarUsuarios">
          Usuarios
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-white" data-toggle="modal" data-target="#NuevoUsuarioCenter">
          Nuevo Usuario
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-white">
          Customers
        </a>
      </li>
       <li class="nav-item">
      	<form id="logout" action="./logout" method="post" >
  			<input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"  />
		</form>
     
        <a href="javascript:document.getElementById('logout').submit()" class="nav-link text-white font-italic">
                  Salir
              </a>
        
      </li>
    </ul>
    
    <hr>
    
  </div>
</body>
</html>