package es.dsw.daos;

import java.sql.ResultSet;
import java.util.ArrayList;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Variedad;

public class VariedadesDao {

	private boolean flagError;
	private String msgError;
	
	public VariedadesDao() {
		this.flagError = false;
		this.msgError = "";
	}
	
	
	
	public ArrayList<Variedad> getAll(){
		
		MySqlConnection objConnection = new MySqlConnection();
		ArrayList<Variedad> objTablaVariedad = new ArrayList<Variedad>();
		
		
		
		try {
			objConnection.open();
			if(!objConnection.isError()) {
				ResultSet Result = objConnection.executeSelect("SELECT * FROM variedades");
				while (Result.next()) {
					Variedad objVariedad = new Variedad();
					objVariedad.setId(Result.getInt("ID"));
					objVariedad.setNombre(Result.getString("NOMBRE"));
					objVariedad.setCosecha(Result.getString("cosecha_prevista"));
					objVariedad.setSiembra(Result.getString("siembra_prevista"));
					
					objTablaVariedad.add(objVariedad);
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
		
		return objTablaVariedad;
	}
	
	
	public Variedad getById(int id) {
		MySqlConnection objConnection = new MySqlConnection();
		Variedad objVariedad = null;
		
		try {
			objConnection.open();
			if(!objConnection.isError()) {
				ResultSet Result = objConnection.executeSelect("SELECT * FROM variedades where id = " + id);
				while(Result.next()) {
					objVariedad = new Variedad();
					
					objVariedad.setId(Result.getInt("ID"));
					objVariedad.setNombre(Result.getString("NOMBRE"));
					objVariedad.setCosecha(Result.getString("cosecha_prevista"));
					objVariedad.setSiembra(Result.getString("siembra_prevista"));;
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
		
		
		return objVariedad;
	}
}
