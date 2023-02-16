package es.dsw.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.dsw.daos.RolesDao;
import es.dsw.daos.TareasDao;
import es.dsw.daos.UsuariosDao;
import es.dsw.daos.UsuariosRolesDao;
import es.dsw.models.Rol;
import es.dsw.models.Tarea;
import es.dsw.models.Usuario;
import es.dsw.models.UsuarioRol;

@Controller
public class MainController {

	
	@RequestMapping(value= {"/","/home"})
	public String home(Model objModel, @RequestParam(name="modificar", required=false, defaultValue="1") String modificar) {
		
		RolesDao objRolDao = new RolesDao();
		ArrayList<Rol> objTablaRol = objRolDao.getAll();
		
		objModel.addAttribute("Roles", objTablaRol);
		
				

		return "home";
	}

	
	@GetMapping(value={"/login"})
	public String login() {
		return "login";
	}
	
	
	@GetMapping(value= {"/tareasView"})
	public String tareasView(Model objModel) {
		
		TareasDao objTareasDao = new TareasDao();
		ArrayList<Tarea> objTablaTarea = objTareasDao.getAll();
		
		objModel.addAttribute("Tareas", objTablaTarea);

		
		return "tareasView";
	}

	@GetMapping(value={"/usuariosView"})
	public String usuariosView(Model objModel) {
		UsuariosDao objUsuarioDao = new UsuariosDao();
		ArrayList<Usuario> objTablaUsuario = objUsuarioDao.getAll();
		
		
		
		objModel.addAttribute("Usuarios", objTablaUsuario);
		
		return "usuariosView";
	}
	
	
	@RequestMapping(value={"/","/nuevoUsuario"}, method = RequestMethod.POST)
	public String nuevoUsuario(@RequestParam("nombre") String nombre, @RequestParam("apellidos") String apellidos, @RequestParam("direccion") String direccion, @RequestParam("telefono") String telefono, @RequestParam("mail") String mail, @RequestParam("nif") String nif, @RequestParam("password") String password, @RequestParam("passwordrep") String passwordrep, @RequestParam(name = "rol", defaultValue="-1" ) int rol, Model objModel) {
		
		String mensaje ="";
		boolean error = false;
		ArrayList<Integer> roles = new ArrayList<>();
		
		if(nombre != "" && password != "" && passwordrep != "" && password.equals(passwordrep)  && mail != "" && rol != -1) {
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
			
			
			
		}
		
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
		
		
		objModel.addAttribute("mensaje", mensaje);
		objModel.addAttribute("error", error);
		
		return "nuevoUsuario";
	}
	
	
	@RequestMapping(value={"/","/eliminarUsuario"}, method = RequestMethod.POST, produces="application/json")
	private void eliminarUsuario(@RequestParam("eliminar") String eliminar) {
		
		int idUser = Integer.parseInt(eliminar);
		
		UsuariosDao objUsuarioDao = new UsuariosDao();
		objUsuarioDao.deleteById(idUser);
	}
	
	
	@RequestMapping(value= {"/updateUsuario"}, method = RequestMethod.POST)
	private String updateUsuario(@RequestParam("id") String id,@RequestParam("nombre") String nombre, @RequestParam("apellidos") String apellidos, @RequestParam("direccion") String direccion, @RequestParam("tlf") String tlf, @RequestParam("mail") String mail, @RequestParam("nif") String nif, @RequestParam("password") String password, @RequestParam("rol") String rol, Model objModel) {
		
		int idUser = Integer.parseInt(id);
		String subRol = rol.substring(1,rol.length()-1).toLowerCase();
		
		System.out.println(subRol);
		
		String mensaje="";
		boolean error = false;
		
		ArrayList<String> roles = new ArrayList<>();
		
		

		
		if(nombre != "" && password != "" && mail != "" && rol != "" ||(subRol == "operario" || subRol == "capataz" || subRol == "cliente" || subRol == "administrador")) {
			
			Usuario user = new Usuario();
			
			
			
			RolesDao objRolDao = new RolesDao();
			Rol objRol = objRolDao.getByName(subRol);
			
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
			
			
			mensaje = mensaje + "El usuario ha sido modificado correctamente";
			
			
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
			
		}
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
		if(subRol != "operario" && subRol != "capataz" && subRol != "cliente" && subRol != "administrador") {
			mensaje = mensaje + "Los roles deben ser operario, capataz, cliente o administrador";
			error =  true;
		}
		
		
		objModel.addAttribute("mensaje", mensaje);
		objModel.addAttribute("error", error);
		
	
		
		
		
		return "updateUsuario";
	}
}
