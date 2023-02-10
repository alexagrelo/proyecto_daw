package es.dsw.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.dsw.daos.UsuariosDao;
import es.dsw.models.Usuario;

@Controller
public class MainController {

	
	@GetMapping(value= {"/","/home"})
	public String home(Model objModel) {
		
		
		UsuariosDao objUsuarioDao = new UsuariosDao();
		ArrayList<Usuario> objTablaUsuario = objUsuarioDao.getAll();
		
		
		
		for(int i=0;i<objTablaUsuario.size();i++) {
			System.out.println(objTablaUsuario.get(i).getRol());
		}
		
		objModel.addAttribute("Usuarios", objTablaUsuario);
		
		return "home";
	}

	
	@GetMapping(value={"/login"})
	public String login() {
		return "login";
	}
	


}
