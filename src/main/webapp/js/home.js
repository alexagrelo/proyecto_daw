
 $('body').on('click', '#btnListarUsuarios', function(){

  $("#tables").load("./usuariosView");
})

 $('body').on('click', '#btnListarTareas', function(){

  $("#tables").load("./tareasView");
})

$('body').on('click', ".eliminar", function(){
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");
	
	let idUsuario =$(this).attr("data");
	
	$.ajax({
		type:"POST",
		url:"./eliminarUsuario",
		data: "eliminar="+idUsuario,
		dataType:"json",
		beforeSend: request => request.setRequestHeader(header, token),

	})
	
	setTimeout(() => {
			$("#tables").load("./usuariosView");			
		},"1000")
})


$(document).ready(function(){
	$("body").on("click", "#GuardarUsuario", function(){
		
		
		
		var data_nombre = $("#formNombre").val();
		var data_apellidos = $("#formApellidos").val();
		var data_direccion = $("#formDireccion").val();
		var data_telefono = $("#formTelefono").val();
		var data_mail = $("#formMail").val();
		var data_nif = $("#formNif").val();
		var data_password = $("#formPassword").val();
		var data_passwordrep = $("#formPasswordRep").val();
		var data_rol = $("#formRol").val();
		
		
		$("#errorUsuario").html("");
		
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");
		
		
		$.ajax({
			url:"./nuevoUsuario",
			method: "POST",
			data:{nombre: data_nombre, apellidos: data_apellidos, direccion: data_direccion, telefono: data_telefono, mail: data_mail, nif: data_nif, password: data_password, passwordrep: data_passwordrep, rol: data_rol},
			beforeSend: request => request.setRequestHeader(header,token),
			success: function(resultText){
				$("#errorUsuario").html(resultText);
				if(data_nombre !="" && data_mail!= "" && data_rol != -1 && data_password != "" && data_passwordrep != "" && data_password == data_passwordrep){
					
					setTimeout(() => {
						$("#cerrarFormUsuario").trigger("click");
						$("#newUserForm").trigger("reset");
						$("#errorUsuario").html("");
						$("#tables").load("./usuariosView");
					},"2000");
				}
			},
			error: function(jqXHR, exception) {
			$('#errorUsuario').html('<p>Ha ocurrido un error fatal.</p>');
			}
		})
	})
})