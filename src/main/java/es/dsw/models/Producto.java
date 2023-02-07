package es.dsw.models;

public class Producto {
	
	private int id;
	private String nombre;
	private int idAlmacen;
	private String almacen;
	private double precioCoste;
	
	
	public Producto() {
	}


	public Producto(int id, String nombre, int idAlmacen, String almacen, double precioCoste) {
		this.id = id;
		this.nombre = nombre;
		this.idAlmacen = idAlmacen;
		this.almacen = almacen;
		this.precioCoste = precioCoste;
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


	public int getIdAlmacen() {
		return idAlmacen;
	}


	public void setIdAlmacen(int idAlmacen) {
		this.idAlmacen = idAlmacen;
	}


	public String getAlmacen() {
		return almacen;
	}


	public void setAlmacen(String almacen) {
		this.almacen = almacen;
	}


	public double getPrecioCoste() {
		return precioCoste;
	}


	public void setPrecioCoste(double precioCoste) {
		this.precioCoste = precioCoste;
	}
	
	
	

}
