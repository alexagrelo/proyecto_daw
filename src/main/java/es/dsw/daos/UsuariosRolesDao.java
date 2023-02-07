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
			   ResultSet Result = objConnection.executeSelect("SELECT * FROM userrol_film WHERE iduser_urf = " + idUsuario);
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
	
	public ArrayList<Integer> getidRolByIdUser(int idUser) {
		
		MySqlConnection objConnection = new MySqlConnection();
		ArrayList<Integer> objTablaIdRol = new ArrayList<Integer>();
		
		try {
			objConnection.open();
			
			if(!objConnection.isError()) {
				ResultSet Result = objConnection.executeSelect("SELECT * FROM usuarios_roles WHERE id_usuario = " + idUser);
				while(Result.next()) {
					Integer idRol = Result.getInt("ID_ROL");
					objTablaIdRol.add(idRol);
				}
			}else {
				this.flagError = true;
				this.msgError = "Error en getOnce. El objeto MySqlConnection informa error al abrir conexión. +Info: " + objConnection.msgError();
			   }
			
		} catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en getOnce. +Info: " + ex.getMessage();
	} finally {
			objConnection.close();
	}
		
		
		
		return objTablaIdRol;
		
	}

}
