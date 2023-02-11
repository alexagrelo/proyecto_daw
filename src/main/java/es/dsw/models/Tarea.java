package es.dsw.models;

public class Tarea {
	
	private int id;
	private int idUsuarioCrea;
	private String usuarioCrea;
	private int idExplotacion;
	private String explotacion;
	private int idOperario;
	private String operario;
	private String status;
	private String tipo;
	
	
	public Tarea() {
	}


	public Tarea(int id, int idUsuarioCrea, String usuarioCrea, int idExplotacion, String explotacion, int idOperario,
			String operario, String status, String tipo) {
		this.id = id;
		this.idUsuarioCrea = idUsuarioCrea;
		this.usuarioCrea = usuarioCrea;
		this.idExplotacion = idExplotacion;
		this.explotacion = explotacion;
		this.idOperario = idOperario;
		this.operario = operario;
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


	public String getUsuarioCrea() {
		return usuarioCrea;
	}


	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}


	public String getExplotacion() {
		return explotacion;
	}


	public void setExplotacion(String explotacion) {
		this.explotacion = explotacion;
	}


	public String getOperario() {
		return operario;
	}


	public void setOperario(String operario) {
		this.operario = operario;
	}
	
	
	

}
