$(document).ready(function(e){
	

 $('body').on('click', '#btnListarUsuarios', function(e){
	e.preventDefault();
	e.stopImmediatePropagation();
  	$("#tables").load("./usuariosView");
})

 $('body').on('click', '#btnListarTareas', function(e){
	e.preventDefault();
	e.stopImmediatePropagation();
  $("#tables").load("./tareasView");
})


/*
			LÓGICA PARA LOS USUARIOS

*/

	$("body").on("click", "#GuardarUsuario", function(e){
		
		e.preventDefault();
		e.stopImmediatePropagation();
		
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
		$("#mensajeUpdate").html("");
		
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");
		
		
		$.ajax({
			url:"./nuevoUsuario",
			method: "POST",
			data:{nombre: data_nombre, apellidos: data_apellidos, direccion: data_direccion, telefono: data_telefono, mail: data_mail, nif: data_nif, password: data_password, passwordrep: data_passwordrep, rol: data_rol},
			beforeSend: request => request.setRequestHeader(header,token),
			success: function(resultText){
				$("#errorUsuario").html(resultText);
				if(data_nombre !="" && data_mail!= "" && data_rol != -1 && data_password != "" && data_passwordrep != "" && data_password == data_passwordrep && /^\w+([.-_+]?\w+)*@\w+([.-]?\w+)*(\.\w{2,10})+$/.test(data_mail)==true){
					
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
	
	$('body').on('click', ".eliminarUsuario", function(e){
		
	e.preventDefault();
	e.stopImmediatePropagation();
	
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");
	
	var idUsuario =$(this).attr("data");
	
	$.ajax({
		method:"POST",
		url:"./eliminarUsuario",
		data: {eliminar: idUsuario},
		dataType:"json",
		beforeSend: request => request.setRequestHeader(header, token),

	})
	
	setTimeout(() => {
			$("#tables").load("./usuariosView");			
		},"1000")
})


$('body').on('click', '.editarUsuario', function(e){
	
	e.preventDefault();
	e.stopImmediatePropagation();
	
	
	var data_id = $(this).parents("tr").find(".updtId").html();
	var data_nombre = $(this).parents("tr").find('.updtNombre').html();
	var data_apellidos = $(this).parents("tr").find('.updtApellidos').html();
	var data_direccion = $(this).parents("tr").find('.updtDireccion').html();
	var data_tlf = $(this).parents("tr").find('.updtTlf').html();
	var data_mail = $(this).parents("tr").find('.updtMail').html();
	var data_nif = $(this).parents("tr").find('.updtNif').html();
	var data_rol = $(this).parents("tr").find('.updtRol').val();
	var data_password = $(this).parents("tr").find('.updtPassword').html();
	
	$("#mensajeUpdate").html("");
	
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");
	
	
	$.ajax({
		url:"./updateUsuario",
		method: "POST",
		data:{id: data_id, nombre: data_nombre, apellidos: data_apellidos, direccion: data_direccion, tlf : data_tlf, mail: data_mail, nif: data_nif, rol: data_rol, password: data_password },
		beforeSend: request => request.setRequestHeader(header,token),
		success: function(resultText){
			console.log(data_rol);
			$("#mensajeUpdate").html(resultText);
			if(data_nombre != "" && data_password != "" /*&& data_password.length()>=6*/ && data_mail != "" && data_rol != ""/*&&(data_rol == "[operario]" || data_rol == "[capataz]" || data_rol == "[cliente]" || data_rol == "[administrador]") && /^\w+([.-_+]?\w+)*@\w+([.-]?\w+)*(\.\w{2,10})+$/.test(data_mail)==true*/){ 
			setTimeout(() => {
				$("#tables").load("./usuariosView");
				$("#mensajeUpdate").html("");
			}, "2000")}
			
		},
			error: function(jqXHR, exception) {
			$('#mensajeUpdate').html('<p>Ha ocurrido un error fatal.</p>');
			}
	})
	
	
	
})



/*
		LÓGICA DE LAS TAREAS		
*/


$("body").on("click", "#GuardarTarea", function(e){
		
		e.preventDefault();
		e.stopImmediatePropagation();
		
		
		var data_explotacion = $("#formExplotacion").val();
		var data_operario = $("#formOperario").val();
		var data_tipo = $("#formTipo").val();
		
		
		
		$("#errorTarea").html("");
		$("#mensajeUpdate").html("");
		
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");
		
		
		$.ajax({
			url:"./nuevaTarea",
			method: "POST",
			data:{explotacion: data_explotacion, operario: data_operario, tipo: data_tipo},
			beforeSend: request => request.setRequestHeader(header,token),
			success: function(resultText){
				$("#errorTarea").html(resultText);
				if(data_explotacion !="" && data_operario!= "" && data_tipo != ""){
					
					setTimeout(() => {
						$("#cerrarFormTarea").trigger("click");
						$("#newTareaForm").trigger("reset");
						$("#errorTarea").html("");
						$("#tables").load("./tareasView");
					},"2000");
				}
			},
			error: function(jqXHR, exception) {
			$('#errorTarea').html('<p>Ha ocurrido un error fatal.</p>');
			}
		})
	})
	
	
	
	
	
$('body').on('click', ".eliminarTarea", function(e){
	
	e.preventDefault();
	e.stopImmediatePropagation();
	
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");
	
	var idTarea =$(this).attr("data");
	console.log(idTarea)
	
	$.ajax({
		method:"POST",
		url:"./eliminarTarea",
		data: {eliminar: idTarea},
		dataType:"json",
		beforeSend: request => request.setRequestHeader(header, token),

	})
	
	setTimeout(() => {
			$("#tables").load("./tareasView");			
		},"1000")
})


	
	
	$('body').on('click','.editarTarea', function(e){
		

		e.preventDefault();
		e.stopImmediatePropagation();
	
		var data_id = $(this).parents('tr').find('.updtIdTarea').html();
		var data_usuarioCrea =$(this).parents('tr').find('.updtUsuarioCreaTarea').html();
		var data_explotacion =$(this).parents('tr').find('.updtExplotacionTarea').html();
		var data_operario =$(this).parents('tr').find('.updtOperarioTarea').val();
		var data_status =$(this).parents('tr').find('.updtStatusTarea').val();
		var data_tipo =$(this).parents('tr').find('.updtTipoTarea').html();
		
		$("#mensajeUpdate").html("");
		
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");
		
		$.ajax({
			url: "./updateTarea",
			mehtod: "POST",
			data: {id: data_id, usuarioCrea: data_usuarioCrea, explotacion: data_explotacion, operario: data_operario, status: data_status, tipo: data_tipo},
			beforeSend: request => request.setRequestHeader(header,token),
			success: function(resultText){
				console.log(data_status);
				$("#mensajeUpdate").html(resultText);
				if(data_usuarioCrea != "" && data_explotacion != "" && data_operario != "" && data_status != "" && data_tipo != "" ){
					setTimeout(() => {
						$("#tables").load("./tareasView");
						$("#mensajeUpdate").html("");
					}, "2000")
				}
			},
			error: function(jqXHR, exception) {
			$('#mensajeUpdate').html('<p>Ha ocurrido un error fatal.</p>');
			}
		})
	})
	

})

