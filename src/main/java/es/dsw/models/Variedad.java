package es.dsw.models;

public class Variedad {
	
	private int id;
	private String siembra;
	private String cosecha;
	private String nombre;
	
	
	public Variedad() {
	}


	public Variedad(int id, String siembra, String cosecha, String nombre) {
		this.id = id;
		this.siembra = siembra;
		this.cosecha = cosecha;
		this.nombre = nombre;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getSiembra() {
		return siembra;
	}


	public void setSiembra(String siembra) {
		this.siembra = siembra;
	}


	public String getCosecha() {
		return cosecha;
	}


	public void setCosecha(String cosecha) {
		this.cosecha = cosecha;
	}
	
	

}
