package es.dsw.models;

import java.util.ArrayList;

public class Usuario {
	
	private int id;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String tlf;
	private String mail;
	private String nif;
	private ArrayList<String> rol;
	private String password;
	
	public Usuario() {
	}


	public Usuario(int id, String nombre, String apellidos, String direccion, String tlf, String mail, String nif, ArrayList<String> rol, String password) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.tlf = tlf;
		this.mail = mail;
		this.nif = nif;
		this.rol = rol;
		this.password = password;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public ArrayList<String> getRol() {
		return rol;
	}


	public void setRol(ArrayList<String> rol) {
		this.rol = rol;
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


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getTlf() {
		return tlf;
	}


	public void setTlf(String tlf) {
		this.tlf = tlf;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getNif() {
		return nif;
	}


	public void setNif(String nif) {
		this.nif = nif;
	}
	
	
	
	
	
	

}
