package es.dsw.daos;

import java.sql.ResultSet;
import java.util.ArrayList;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Rol;

public class RolesDao {
	
	private boolean flagError;
	private String msgError;
	
	
	public RolesDao() {
		this.flagError = false;
		this.msgError = "";
	}
	
	
	
	public ArrayList<Rol> getById(int idRol){
		MySqlConnection objConnection = new MySqlConnection();
		ArrayList<Rol> objTablaRol = new ArrayList<Rol>();
		
		try {
			objConnection.open();
			if(!objConnection.isError()) {
				String sql = "SELECT * FROM roles WHERE id = " + idRol;
				ResultSet Result = objConnection.executeSelect(sql);
				while(Result.next()) {
					Rol objRol = new Rol();
					objRol.setId(Result.getInt("ID"));
					objRol.setNombre(Result.getString("nombre"));
					
					objTablaRol.add(objRol);
				}
			}else {
				this.flagError = true;
				this.msgError = "Error en getOnce. El objeto MySqlConnection informa error al abrir conexión. +Info: " + objConnection.msgError();
			}
			
		}catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en getOnce. +Info: " + ex.getMessage();
		} finally {
			objConnection.close();
		}
		
		return objTablaRol;
	}
	
	
	public ArrayList<Rol> getAll(){
		MySqlConnection objConnection = new MySqlConnection();
		ArrayList<Rol> objTablaRol = new ArrayList<Rol>();
		
		try {
			objConnection.open();
			if(!objConnection.isError()) {
				String sql = "SELECT * FROM roles";
				ResultSet Result = objConnection.executeSelect(sql);
				while(Result.next()) {
					Rol objRol = new Rol();
					objRol.setId(Result.getInt("ID"));
					objRol.setNombre(Result.getString("nombre"));
					
					objTablaRol.add(objRol);
				}
			}else {
				this.flagError = true;
				this.msgError = "Error en getOnce. El objeto MySqlConnection informa error al abrir conexión. +Info: " + objConnection.msgError();
			}
			
		}catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en getOnce. +Info: " + ex.getMessage();
		} finally {
			objConnection.close();
		}
		
		return objTablaRol;
	}

}
