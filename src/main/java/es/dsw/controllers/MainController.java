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

@Controller
public class MainController {

	
	@GetMapping(value= {"/","/home"})
	public String home(Model objModel) {
		
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
}
