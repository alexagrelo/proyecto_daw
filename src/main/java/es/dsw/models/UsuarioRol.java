package es.dsw.models;

public class UsuarioRol {

	private int id;
	private int idUsuario;
	private int idRol;
	
	
	public UsuarioRol() {
	}


	public UsuarioRol(int id, int idUsuario, int idRol) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.idRol = idRol;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public int getIdRol() {
		return idRol;
	}


	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	
	
	
}
