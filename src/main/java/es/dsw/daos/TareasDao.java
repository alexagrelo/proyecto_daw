package es.dsw.daos;

import java.sql.ResultSet;
import java.util.ArrayList;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Explotacion;
import es.dsw.models.Tarea;
import es.dsw.models.Usuario;

public class TareasDao {
	
	private boolean flagError;
	private String msgError;
	
	
	public TareasDao() {
		this.flagError = false;
		this.msgError = "";
	}
	
	
	public ArrayList<Tarea> getAll(){
		
		MySqlConnection objConnection = new MySqlConnection();
		ArrayList<Tarea> objTablaTarea = new ArrayList<Tarea>();
		
		try {
			objConnection.open();
			if(!objConnection.isError()) {
				ResultSet Result = objConnection.executeSelect("SELECT * FROM tareas");
				while(Result.next()) {
					Tarea objTarea = new Tarea();
					
					objTarea.setId(Result.getInt("ID"));
					objTarea.setStatus(Result.getString("STATUS"));
					objTarea.setTipo(Result.getString("TIPO"));
					
					UsuariosDao objUsuariosDao = new UsuariosDao();
					Usuario objUsuarioCrea = objUsuariosDao.getUserById(Result.getInt("ID_USUARIO_CREA"));
					objTarea.setUsuarioCrea(objUsuarioCrea.getNombre());
					
					ExplotacionesDao objExplotacionesDao = new ExplotacionesDao();
					Explotacion objExplotacion = objExplotacionesDao.getExplotacionById(Result.getInt("ID_EXPLOTACION"));
					objTarea.setExplotacion(objExplotacion.getNombre());
					
					
					Usuario objUsuarioOperario = objUsuariosDao.getUserById(Result.getInt("ID_OPERARIO_ASIGNADO"));
					objTarea.setOperario(objUsuarioOperario.getNombre());				
					
					
					objTablaTarea.add(objTarea);
				}
			}			
			else {
				this.flagError = true;
				this.msgError = "Error en getAll. El objeto MySqlConnection informa error al abrir conexión. +Info: " + objConnection.msgError();
			   }
			
		}catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en getAll. +Info: " + ex.getMessage();
		}finally {
			objConnection.close();
		}
		
		
		
		return objTablaTarea;
	}
	
	
	public void setTarea(Tarea objTarea) {
		MySqlConnection objConnection = new MySqlConnection();
		try {
			objConnection.open();
			if(!objConnection.isError()) {
				String sql = "INSERT INTO tareas"
						+ "(ID_USUARIO_CREA,"
						+ "ID_EXPLOTACION,"
						+ "ID_OPERARIO_ASIGNADO,"
						+ "STATUS,"
						+ "TIPO)"
						+ "VALUES "
						+ "("+objTarea.getIdUsuarioCrea() + ","
						+ " "+objTarea.getIdExplotacion() + ","
						+ " "+objTarea.getIdOperario() + ","
						+ " '"+objTarea.getStatus()+"',"
						+ " '"+objTarea.getTipo()+"')";
				
				objConnection.executeInsert(sql);
			}else {
				 this.flagError = true;
				 this.msgError = "Error en setTarea. El objeto clsConectionMySql informa error al abrir conexión. +Info: " + objConnection.msgError();
			}
			
		}catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en setTarea. +Info: " + ex.getMessage();
    	} finally {
			objConnection.close();
    	}
	}

}
