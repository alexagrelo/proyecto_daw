package es.dsw.daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Rol;
import es.dsw.models.Usuario;
import es.dsw.models.UsuarioRol;

public class UsuariosDao {
	
	private boolean flagError;
	private String msgError;
	
	public UsuariosDao() {
		this.flagError = false;
		this.msgError = "";
	}
	
	public ArrayList<Usuario> getAll(){
		MySqlConnection objConnection = new MySqlConnection();
		ArrayList<Usuario> objTablaUsuario = new ArrayList<Usuario>();
		
		try {
			objConnection.open();
			if(!objConnection.isError()) {
				ResultSet Result = objConnection.executeSelect("SELECT * FROM usuarios");
				while(Result.next()) {
					Usuario objUsuario = new Usuario();
					
					objUsuario.setId(Result.getInt("ID"));
					objUsuario.setNombre(Result.getString("NOMBRE"));
					objUsuario.setApellidos(Result.getString("APELLIDOS"));
					objUsuario.setDireccion(Result.getString("DIRECCION"));
					objUsuario.setTlf(Result.getString("TLF"));
					objUsuario.setMail(Result.getString("EMAIL"));
					objUsuario.setNif(Result.getString("NIF"));
					objUsuario.setPassword(Result.getString("password"));
					
					UsuariosRolesDao objUsuarioRolDao = new UsuariosRolesDao();
					ArrayList<UsuarioRol> objTablaUsuarioRol = objUsuarioRolDao.getById(Result.getInt("ID"));
					Iterator<UsuarioRol> iterator = objTablaUsuarioRol.iterator();

					RolesDao objRolDao = new RolesDao();
					
					ArrayList<Rol> objTablaRol = new ArrayList<Rol>();
					ArrayList<String> Roles = new ArrayList<String>();
					
					while(iterator.hasNext()) {
						UsuarioRol objUsuarioRol = new UsuarioRol();
						objUsuarioRol.setIdRol(iterator.next().getIdRol());
						
						objTablaRol = objRolDao.getById(objUsuarioRol.getIdRol());
						Iterator<Rol> iter = objTablaRol.iterator();
						
						while(iter.hasNext()) {
							Roles.add(iter.next().getNombre());
						}
					}
					objUsuario.setRol(Roles);
					objTablaUsuario.add(objUsuario);
				}
			}else {
				this.flagError = true;
				this.msgError = "Error en getOnce. El objeto MySqlConnection informa error al abrir conexión. +Info: " + objConnection.msgError();
			   }
			
		}catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en getAll. +Info: " + ex.getMessage();
		}finally {
			objConnection.close();
		}
		
		
		
		return objTablaUsuario;
	}
	
	
	public Usuario getUserById(int id) {
		MySqlConnection objConnection = new MySqlConnection();
		Usuario objUsuario = null;
		
		
		
		
		return objUsuario;
	}

}
