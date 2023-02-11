package es.dsw.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.dsw.daos.TareasDao;
import es.dsw.daos.UsuariosDao;
import es.dsw.models.Tarea;
import es.dsw.models.Usuario;

@Controller
public class MainController {

	
	@GetMapping(value= {"/","/home"})
	public String home(Model objModel) {
		
		
		UsuariosDao objUsuarioDao = new UsuariosDao();
		ArrayList<Usuario> objTablaUsuario = objUsuarioDao.getAll();
		
		TareasDao objTareasDao = new TareasDao();
		ArrayList<Tarea> objTablaTarea = objTareasDao.getAll();
		
		objModel.addAttribute("Usuarios", objTablaUsuario);
		objModel.addAttribute("Tareas", objTablaTarea);
		
		return "home";
	}

	
	@GetMapping(value={"/login"})
	public String login() {
		return "login";
	}
	
	
	@GetMapping(value= {"/tareasView"})
	public String tareasView() {
		
		
		
		return "tareasView";
	}

}
