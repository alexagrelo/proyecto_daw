<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EL RINCÓN DEL CINE - Acceso</title>
    <link href="./styles/login.css" rel="stylesheet" id="bootstrap-css">
    <link href="./bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="./js/jquery-3.6.1.min.js"></script>
    <script src="./bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="sidenav">
        <div class="login-main-text">
           <h2>BioFRUIT</h2>
           <p>Administración y gestión</p>
        </div>
     </div>
     <div class="main">
        <div class="col-md-6 col-sm-12">
           <div class="login-form">
              <form:form action="./loginprocess" method="POST">
                 <div class="form-group">
                    <label>Nombre de usuario</label>
                    <input type="text" class="form-control" placeholder="User Name" name="username">
                 </div>
                 <div class="form-group">
                    <label>Contraseña</label>
                    <input type="password" class="form-control" placeholder="Password" name ="password">
                 </div>
                 <button type="submit" class="btn btn-dark text-white">Entrar</button>
                 <a href="#" class="btn btn-secondary">Ayuda</a>
              </form:form>
           </div>
        </div>
     </div>
</body>
</html>