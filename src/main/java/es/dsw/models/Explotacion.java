package es.dsw.models;

public class Explotacion {
	
	private int id;
	private String nombre;
	private double superficie;
	private String direccion;
	private String fuenteAgua;
	private int idVariedad;
	private String variedad;
	
	
	public Explotacion() {
	}


	public Explotacion(int id, String nombre, double superficie, String direccion, String fuenteAgua, int idVariedad,
			String variedad) {
		this.id = id;
		this.nombre = nombre;
		this.superficie = superficie;
		this.direccion = direccion;
		this.fuenteAgua = fuenteAgua;
		this.idVariedad = idVariedad;
		this.variedad = variedad;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public double getSuperficie() {
		return superficie;
	}


	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getFuenteAgua() {
		return fuenteAgua;
	}


	public void setFuenteAgua(String fuenteAgua) {
		this.fuenteAgua = fuenteAgua;
	}


	public int getIdVariedad() {
		return idVariedad;
	}


	public void setIdVariedad(int idVariedad) {
		this.idVariedad = idVariedad;
	}


	public String getVariedad() {
		return variedad;
	}


	public void setVariedad(String variedad) {
		this.variedad = variedad;
	}
	
	
	

}
