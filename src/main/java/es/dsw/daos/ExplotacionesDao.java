package es.dsw.daos;

import java.sql.ResultSet;
import java.util.ArrayList;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Explotacion;
import es.dsw.models.Variedad;

public class ExplotacionesDao {
	
	private boolean flagError;
	private String msgError;
	
	
	public ExplotacionesDao() {
		this.flagError = false;
		this.msgError = "";
	}
	
	
	public ArrayList<Explotacion> getAll(){
		MySqlConnection objConnection = new MySqlConnection();
		ArrayList<Explotacion> objTablaExplotacion = new ArrayList<>();
		
		try {
			objConnection.open();
			if(!objConnection.isError()) {
				ResultSet Result = objConnection.executeSelect("SELECT * FROM explotaciones");
				while(Result.next()) {
					Explotacion objExplotacion = new Explotacion();
					
					objExplotacion.setId(Result.getInt("ID"));
					objExplotacion.setNombre(Result.getString("NOMBRE"));
					objExplotacion.setSuperficie(Result.getDouble("SUPERFICIE_M2"));
					objExplotacion.setDireccion(Result.getString("DIRECCION"));
					objExplotacion.setFuenteAgua(Result.getString("FUENTE_AGUA"));
					
					VariedadesDao objVariedadesDao = new VariedadesDao();
					Variedad objVariedad= objVariedadesDao.getById(Result.getInt("ID_VARIEDAD"));
					
					objExplotacion.setVariedad(objVariedad.getNombre());
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
		
		
		
		return objTablaExplotacion;
	}
	
	
	public Explotacion getExplotacionById(int id) {
		MySqlConnection objConnection = new MySqlConnection();
		Explotacion objExplotacion = null;
		
		try {
			objConnection.open();
			if(!objConnection.isError()) {
				ResultSet Result = objConnection.executeSelect("SELECT * FROM variedades WHERE id = " + id);
				while(Result.next()) {
					objExplotacion = new Explotacion();
					objExplotacion.setId(Result.getInt("ID"));
					objExplotacion.setNombre(Result.getString("NOMBRE"));
					objExplotacion.setSuperficie(Result.getDouble("SUPERFICIE_M2"));
					objExplotacion.setDireccion(Result.getString("DIRECCION"));
					objExplotacion.setFuenteAgua(Result.getString("FUENTE_AGUA"));
					
					VariedadesDao objVariedadesDao = new VariedadesDao();
					Variedad objVariedad= objVariedadesDao.getById(Result.getInt("ID_VARIEDAD"));
					
					objExplotacion.setVariedad(objVariedad.getNombre());
				}
			}else {
				this.flagError = true;
				this.msgError = "Error en getAll. El objeto clsConectionMysql informa error al abrir conexión. +Info: " + objConnection.msgError();
			}
		}catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en getAll. +Info: " + ex.getMessage();
		}finally {
			objConnection.close();
		}
		
		
		
		return objExplotacion;
	}
	
	

}
