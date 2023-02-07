package es.dsw.models;

public class Tarea {
	
	private int id;
	private int idUsuarioCrea;
	private int idExplotacion;
	private int idOperario;
	private String status;
	private String tipo;
	
	
	public Tarea() {
	}


	public Tarea(int id, int idUsuarioCrea, int idExplotacion, int idOperario, String status, String tipo) {
		this.id = id;
		this.idUsuarioCrea = idUsuarioCrea;
		this.idExplotacion = idExplotacion;
		this.idOperario = idOperario;
		this.status = status;
		this.tipo = tipo;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIdUsuarioCrea() {
		return idUsuarioCrea;
	}


	public void setIdUsuarioCrea(int idUsuarioCrea) {
		this.idUsuarioCrea = idUsuarioCrea;
	}


	public int getIdExplotacion() {
		return idExplotacion;
	}


	public void setIdExplotacion(int idExplotacion) {
		this.idExplotacion = idExplotacion;
	}


	public int getIdOperario() {
		return idOperario;
	}


	public void setIdOperario(int idOperario) {
		this.idOperario = idOperario;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	

}
