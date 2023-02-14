package es.dsw.daos;

import java.sql.ResultSet;
import java.util.ArrayList;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.UsuarioRol;

public class UsuariosRolesDao {
	
	private boolean flagError;
	private String msgError;
	
	public UsuariosRolesDao() {
		this.flagError = false;
		this.msgError = "";
	}
	
	
public ArrayList<UsuarioRol> getById(int idUsuario) {
		
		MySqlConnection objConnection = new MySqlConnection();
		ArrayList<UsuarioRol> objTablaUsuarioRol = new ArrayList<UsuarioRol>();	
		
		try {
			  objConnection.open();
		
		if (!objConnection.isError()){
			String sql = "SELECT * FROM usuarios_roles WHERE id_usuario = " + idUsuario ;
			   ResultSet Result = objConnection.executeSelect(sql);
			   while(Result.next()) {
				   UsuarioRol objUsuarioRol = new UsuarioRol(); 
				   objUsuarioRol = new UsuarioRol();
				   
				   objUsuarioRol.setId(Result.getInt("id"));
				   objUsuarioRol.setIdUsuario(Result.getInt("id_usuario"));
				   objUsuarioRol.setIdRol(Result.getInt("id_rol"));
				   
				   
				   objTablaUsuarioRol.add(objUsuarioRol);
			   }
			
			} else {
					this.flagError = true;
					this.msgError = "Error en getOnce. El objeto MySqlConnection informa error al abrir conexión. +Info: " + objConnection.msgError();
				   }
	    } catch (Exception ex) {
				this.flagError = true;
				this.msgError = "Error en getOnce. +Info: " + ex.getMessage();
		} finally {
				objConnection.close();
		}
		
		return objTablaUsuarioRol;
	}

		
	public void setUsuarioRol(int idUsuario, int idRol) {
		MySqlConnection objConnection = new MySqlConnection();
		try {
			objConnection.open();
			if(!objConnection.isError()) {
				String sql = "INSERT INTO usuarios_roles "
						+ "(ID_USUARIO,"
						+ "ID_ROL)"
						+"VALUES "
						+"(" + idUsuario + ","
						+ " "+ idRol + ")";
				objConnection.executeInsert(sql);
			}else {
				this.flagError = true;
				this.msgError = "Error en setUsuarioRol. El objeto clsConectionMysql informa error al abrir conexión. +Info: " + objConnection.msgError();
			}
		}catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en setUsuarioRol. +Info: " + ex.getMessage();
    	} finally {
			objConnection.close();
    	}
	}

}
