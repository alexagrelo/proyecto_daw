package es.dsw.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.dsw.daos.ExplotacionesDao;
import es.dsw.daos.RolesDao;
import es.dsw.daos.TareasDao;
import es.dsw.daos.UsuariosDao;
import es.dsw.daos.UsuariosRolesDao;
import es.dsw.models.Explotacion;
import es.dsw.models.Rol;
import es.dsw.models.Tarea;
import es.dsw.models.Usuario;
import es.dsw.models.UsuarioRol;

@Controller
public class MainController {

	
	@RequestMapping(value= {"/","/home"})
	public String home(Authentication authentication,Model objModel, @RequestParam(name="modificar", required=false, defaultValue="1") String modificar) {
		
		String Roles = "";
		
		RolesDao objRolDao = new RolesDao();
		ArrayList<Rol> objTablaRol = objRolDao.getAll();
		
				
		ExplotacionesDao objExplotacionDao = new ExplotacionesDao();
		ArrayList<Explotacion> objExplotacion = objExplotacionDao.getAll();
		
		UsuariosDao objUsuariosDao = new UsuariosDao();
		ArrayList<Usuario> objTablaUsuario = objUsuariosDao.getAll();
		
		objModel.addAttribute("Roles", objTablaRol);
		objModel.addAttribute("explotaciones", objExplotacion);
		objModel.addAttribute("Usuarios", objTablaUsuario);
		
		objModel.addAttribute("Rol", Roles);
				

		return "home";
	}

	
	@GetMapping(value={"/login"})
	public String login() {
		return "login";
	}
	
	
	@GetMapping(value= {"/tareasView"})
	public String tareasView(Model objModel, Authentication authentication) {
		
		TareasDao objTareasDao = new TareasDao();
		ArrayList<Tarea> objTablaTarea = objTareasDao.getAll();
		
		String userName = authentication.getName();
				
		
		UsuariosDao objUsuarioDao = new UsuariosDao();
		ArrayList<Usuario> objTablaUsuario = objUsuarioDao.getAll();
		
		Usuario user = objUsuarioDao.getUserByMail(userName);
		ArrayList<Tarea> objTablaTareaOperario = objTareasDao.getByOperario(user.getId());
		
		RolesDao objRolDao = new RolesDao();
		ArrayList<Rol> objTablaRol = objRolDao.getAll();		 
		
		
		objModel.addAttribute("Tareas", objTablaTarea);
		objModel.addAttribute("TareasOperario", objTablaTareaOperario);
		objModel.addAttribute("Usuarios", objTablaUsuario);
		objModel.addAttribute("Roles", objTablaRol);

		
		return "tareasView";
	}

	@GetMapping(value={"/usuariosView"})
	public String usuariosView(Model objModel) {
		UsuariosDao objUsuarioDao = new UsuariosDao();
		ArrayList<Usuario> objTablaUsuario = objUsuarioDao.getAll();
		
		RolesDao objRolDao = new RolesDao();
		ArrayList<Rol> objTablaRol = objRolDao.getAll();
		
		objModel.addAttribute("Usuarios", objTablaUsuario);
		objModel.addAttribute("Roles", objTablaRol);
		
		return "usuariosView";
	}
	
	
	@RequestMapping(value={"/nuevoUsuario"}, method = RequestMethod.POST)
	public String nuevoUsuario(@RequestParam("nombre") String nombre, @RequestParam("apellidos") String apellidos, @RequestParam("direccion") String direccion, @RequestParam("telefono") String telefono, @RequestParam("mail") String mail, @RequestParam("nif") String nif, @RequestParam("password") String password, @RequestParam("passwordrep") String passwordrep, @RequestParam(name = "rol", defaultValue="-1" ) int rol, Model objModel) {
		
		String mensaje ="";
		boolean error = false;
		ArrayList<Integer> roles = new ArrayList<>();
		
		/*Pattern pattern = Pattern
				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		
		Matcher matcher = pattern.matcher(mail);
		
		System.out.println("patron de mail" + matcher.find());*/
		
		
		
		if(nombre != "" /*&& matcher.find()*/ && password != "" && passwordrep != "" && password.equals(passwordrep)  && mail != "" && rol != -1 && password.length() >= 6) {
			Usuario user = new Usuario();
			Usuario userMail = new Usuario();
			
			UsuariosRolesDao objUsuarioRolDao = new UsuariosRolesDao();
			
			roles.add(rol);
			
			
			
			user.setNombre(nombre);
			user.setApellidos(apellidos);
			user.setDireccion(direccion);
			user.setTlf(telefono);
			user.setMail(mail);
			user.setNif(nif);
			user.setPassword(password);
			
			UsuariosDao objUsuarioDao = new UsuariosDao();
			objUsuarioDao.setUsuario(user);
			
			mensaje = mensaje + "El usuario se ha creado correctamente";
			error = false;
			
			try {
				Thread.sleep(3000);
				userMail = objUsuarioDao.getUserByMail(mail);
				int idUsuario = userMail.getId();
				for(int i=0;i<roles.size();i++) {
					objUsuarioRolDao.setUsuarioRol(idUsuario, roles.get(i));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			
		}else {
			
			if(nombre == "") {
				mensaje = mensaje + "Debe introducir el nombre del usuario. ";
				error = true;
			}
			if(password == "") {
				mensaje = mensaje + "Debe introducir el password. ";
				error = true;
			}
			if(passwordrep == "") {
				mensaje = mensaje + "Debe repetir el password. ";
				error = true;
			}
			if(!password.equals(passwordrep)) {
				mensaje = mensaje + "Las contraseñas no coinciden. ";
				error = true;
			}
			if(mail == "") {
				mensaje = mensaje + "Debe introducir el correo. ";
				error = true;
			}
			if(rol == -1) {
				mensaje = mensaje + "Debe introducir el rol del usuarios. ";
				error = true;
			}
			/*if(matcher.find()==false) {
				mensaje = mensaje + "El correo introducido no es correcto. ";
				error = true;
			}*/
			if(password.length()<6) {
				mensaje = mensaje + "El password debe tener al menos 6 caracteres";
				error = true;
			}
		}
		
		
		
		
		objModel.addAttribute("mensaje", mensaje);
		objModel.addAttribute("error", error);
		
		return "nuevoUsuario";
	}
	
	
	@RequestMapping(value={"/eliminarUsuario"}, method = RequestMethod.POST, produces="application/json")
	private void eliminarUsuario(@RequestParam("eliminar") String eliminar) {
		
		int idUser = Integer.parseInt(eliminar);
		
		UsuariosDao objUsuarioDao = new UsuariosDao();
		objUsuarioDao.deleteById(idUser);
	}
	
	
	@RequestMapping(value= {"/updateUsuario"}, method = RequestMethod.POST)
	private String updateUsuario(@RequestParam("id") String id,@RequestParam("nombre") String nombre, @RequestParam("apellidos") String apellidos, @RequestParam("direccion") String direccion, @RequestParam("tlf") String tlf, @RequestParam("mail") String mail, @RequestParam("nif") String nif, @RequestParam("password") String password, @RequestParam("rol") String rol, Model objModel) {
		
		int idUser = Integer.parseInt(id);
		if(rol.indexOf("[")!= -1) {
			
			rol = rol.substring(1,rol.length()-1).toLowerCase();
		}
		
		System.out.println(rol);
		
		String mensaje="";
		boolean error = false;
		
		ArrayList<String> roles = new ArrayList<>();
		
		/*Pattern pattern = Pattern
				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		
		Matcher matcher = pattern.matcher(mail);*/
		
		

		
		if(nombre != "" /*&& password.length() >= 6 && matcher.find()== true*/ && password != ""  && mail != "" && rol != "" /*||(rol == "operario" || rol == "capataz" || rol == "cliente" || rol == "administrador")*/) {
			
			Usuario user = new Usuario();
			
			
			
			RolesDao objRolDao = new RolesDao();
			Rol objRol = objRolDao.getByName(rol);
			
			roles.add(rol);
			
			user.setNombre(nombre);
			user.setApellidos(apellidos);
			user.setDireccion(direccion);
			user.setTlf(tlf);
			user.setMail(mail);
			user.setNif(nif);
			user.setPassword(password);
			user.setId(idUser);
			
			UsuariosDao objUsuarioDao = new UsuariosDao();
			objUsuarioDao.updateUser(user);
			
			
			mensaje = mensaje + "El usuario ha sido modificado correctamente. ";
			
			
			try {
				Thread.sleep(3000);
			int idRol = objRol.getId();			
			UsuariosRolesDao objUsuariosRolesDao = new UsuariosRolesDao();
			
			UsuarioRol objUsuarioRolmod = new UsuarioRol();
			objUsuarioRolmod.setIdRol(idRol);
			objUsuarioRolmod.setIdUsuario(idUser);
			
			objUsuariosRolesDao.updateUsuarioRol(objUsuarioRolmod);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}else {
		if(nombre == "") {
			mensaje = mensaje + "Debe introducir el nombre del usuario. ";
			error = true;
		}
		if(password == "") {
			mensaje = mensaje + "Debe introducir el password. ";
			error = true;
		}
		if(mail == "") {
			mensaje = mensaje + "Debe introducir el correo. ";
			error = true;
		}
		if(rol == "") {
			mensaje = mensaje + "Debe introducir el rol del usuarios. ";
			error = true;
		}
		/*if(rol != "operario" || rol != "capataz" || rol != "cliente" || rol != "administrador") {
			mensaje = mensaje + "Los roles deben ser operario, capataz, cliente o administrador";
			error =  true;
		}*/
		/*if(matcher.find()==false) {
			mensaje = mensaje + "El correo introducido no es correcto. ";
			error = true;
		}*/
		/*if(password.length()<6) {
			mensaje = mensaje + "El password debe tener al menos 6 caracteres";
			error = true;
		}*/
		}
		
		objModel.addAttribute("mensaje", mensaje);
		objModel.addAttribute("error", error);
		
	
		
		
		
		return "updateUsuario";
	}
	
	
	@RequestMapping(value={"/nuevaTarea"}, method = RequestMethod.POST)
	public String nuevaTarea(Authentication authentication, Model objModel, @RequestParam(name="explotacion", defaultValue="-1") int explotacion, @RequestParam(name="operario", defaultValue="-1") int operario, @RequestParam("tipo") String tipo) {
		
		String mensaje ="";
		boolean error = false;
		
		UsuariosDao objUsuarioDao = new UsuariosDao();
		Usuario user = objUsuarioDao.getUserByMail(authentication.getName());
		int idUser = user.getId();
		
		if(operario != -1 && explotacion != -1 && tipo != "") {
			Tarea tarea = new Tarea();
			
			tarea.setIdUsuarioCrea(idUser);
			tarea.setIdExplotacion(explotacion);
			
			tarea.setIdOperario(operario);
			tarea.setStatus("Pendiente");
			tarea.setTipo(tipo);
			
			
			TareasDao objTareasDao = new TareasDao();
			objTareasDao.setTarea(tarea);
			
			error = false;
		}
		
		if(operario == -1) {
			mensaje = mensaje + "Debe asignar un operario a la tarea. ";
			error = true;
		}
		if(explotacion == -1) {
			mensaje = mensaje + "Debe asignar una explotación a la tarea. ";
			error = true;
		}
		if(tipo == "") {
			mensaje = mensaje + "Debe añadir una accion a realizar en la tarea";
			error = true;
		}
		
		
		
		objModel.addAttribute("mensaje", mensaje);
		objModel.addAttribute("error", error);
		
		return "nuevaTarea";
	}
	
	
	@RequestMapping(value={"/eliminarTarea"}, method = RequestMethod.POST)
	private void eliminarTarea(@RequestParam("eliminar") String eliminar) {
		
		int idTarea = Integer.parseInt(eliminar);
		System.out.println(idTarea);
		
		TareasDao objTareaDao = new TareasDao();
		objTareaDao.deleteById(idTarea);
	}
	
	
	@RequestMapping(value= {"/updateTarea"}, method = RequestMethod.POST)
	private String updateTarea(@RequestParam("id") String id,@RequestParam("usuarioCrea") String usuarioCrea, @RequestParam("explotacion") String explotacion, @RequestParam("operario") String operario, @RequestParam("status") String status, @RequestParam("tipo") String tipo, Model objModel) {
		
		int idTarea = Integer.parseInt(id);
				
		String mensaje="";
		boolean error = false;		
		

		
		if(usuarioCrea != "" && explotacion != "" && operario != "" && status != "" && tipo != "") {
			
			Tarea objTarea = new Tarea();
			
			objTarea.setId(idTarea);
			
			UsuariosDao objUsuarioDao = new UsuariosDao();			
			Usuario objUsuarioCrea = objUsuarioDao.getUserbyName(usuarioCrea);
			objTarea.setIdUsuarioCrea(objUsuarioCrea.getId());
			System.out.println("id usuario crea tarea" + objUsuarioCrea.getId());
			
			ExplotacionesDao objExplotacionDao = new ExplotacionesDao();
			Explotacion objExplotacion = objExplotacionDao.getExplotacionByName(explotacion);
			objTarea.setIdExplotacion(objExplotacion.getId());
			System.out.println("id usuario explotacion tarea" + objExplotacion.getId());
			
			Usuario objUsuarioOperario = objUsuarioDao.getUserbyName(operario);
			objTarea.setIdOperario(objUsuarioOperario.getId());
			System.out.println("id operario tarea" + objUsuarioOperario.getId());
			
			objTarea.setStatus(status);
			objTarea.setTipo(tipo);
			
			
			mensaje = mensaje + "La tarea ha sido modificado correctamente";
						
		}
		if(usuarioCrea == "") {
			mensaje = mensaje + "Debe introducir el nombre del usuario que crea la tarea. ";
			error = true;
		}
		if(explotacion == "") {
			mensaje = mensaje + "Debe introducir la explotación. ";
			error = true;
		}
		if(operario == "") {
			mensaje = mensaje + "Debe introducir el operario que realizará la tarea. ";
			error = true;
		}
		if(status == "") {
			mensaje = mensaje + "Debe introducir el status de la tarea. ";
			error = true;
		}
		if(tipo == "") {
			mensaje = mensaje + "Debe introducir el tipo de tarea a realizar. ";
			error = true;
		}
		
		
		objModel.addAttribute("mensaje", mensaje);
		objModel.addAttribute("error", error);
		
	
		
		
		
		return "updateTarea";
	}
	
}
