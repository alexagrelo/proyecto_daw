<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<table class="table table-striped">
	<thead>
		<tr>
			<th>id</th>
			<th>usuarioCrea</th>
			<th>explotacion</th>
			<th>operario</th>
			<th>status</th>
			<th>tipo</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach begin="0" step ="1" items="${Tareas}" var="item">
			<tr>
				<td>${item.getId() }</td>
				<td>${item.getUsuarioCrea() }</td>
				<td>${item.getExplotacion() }</td>
				<td>${item.getOperario() }</td>
				<td>${item.getStatus() }</td>
				<td>${item.getTipo() }</td>
			</tr>
		</c:forEach>
	</tbody>	
</table>