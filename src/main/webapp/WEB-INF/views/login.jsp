<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BioFruit DAW</title>
    <link href="./styles/login.css" rel="stylesheet" id="login-css">
    <link href="./bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="./js/jquery-3.6.1.min.js"></script>
    <script src="./bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
</head>
<body>
     </div>
     <section class="section-content">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 text-center mb-5">
					<h2 class="text-center">BioFruit</h2>
					<!-- <img id="login-head-logo" src=""> -->
				</div>
			</div>
			<div class="login-content">
				<div class="col-md-6 col-sm-12 mx-auto">
					<div class="login-form">
						<form:form action="./loginprocess" method="POST">
							<div class="form-group">
								<label class="font-weight-bold">Nombre de usuario</label> <input type="text"
									class="form-control" placeholder="User Name" name="username">
							</div>
							<div class="form-group">
								<label class="font-weight-bold">Contraseña</label> <input type="password"
									class="form-control" placeholder="Password" name="password">
							</div>
							<button type="submit" class="btn btn-dark text-white">Acceder</button>
							<!-- <a href="#" class="btn btn-secondary">Ayuda</a> -->
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>