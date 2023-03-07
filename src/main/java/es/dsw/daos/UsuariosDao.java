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
				ResultSet Result = objConnection.executeSelect("SELECT * FROM usuarios;");
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
		
		try {
			objConnection.open();
			if(!objConnection.isError()){
				String sql = "SELECT * FROM usuarios WHERE id = " + id;
				ResultSet Result = objConnection.executeSelect(sql);
				while(Result.next()) {
					objUsuario = new Usuario();
					
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
					
					
				}
			}else {
				this.flagError = true;
				this.msgError = "Error en getById. El objeto clsConectionMysql informa error al abrir conexión. +Info: " + objConnection.msgError();
			}
		}catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en getAll. +Info: " + ex.getMessage();
		}finally {
			objConnection.close();
		}
		
		return objUsuario;
	}
	
	public void setUsuario(Usuario objUsuario) {
		MySqlConnection objConnection = new MySqlConnection();
		try {
			objConnection.open();
			if(!objConnection.isError()) {
				String sql = "INSERT INTO usuarios"
						+ "(NOMBRE,"
						+ "APELLIDOS,"
						+ "DIRECCION,"
						+ "TLF,"
						+ "EMAIL,"
						+ "NIF,"
						+ "password)"
						+ "VALUES "
						+"('" + objUsuario.getNombre()+ "',"
						+" '"+objUsuario.getApellidos()+"',"
						+" '"+objUsuario.getDireccion()+"',"
						+" '"+objUsuario.getTlf()+"',"
						+" '"+objUsuario.getMail()+"',"
						+" '"+objUsuario.getNif()+"',"
						+" '"+objUsuario.getPassword()+"')";
				objConnection.executeInsert(sql);
			}else {
				this.flagError = true;
				this.msgError = "Error en setUsuario. El objeto clsConectionMysql informa error al abrir conexión. +Info: " + objConnection.msgError();
			}
		}catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en setUsuario. +Info: " + ex.getMessage();
    	} finally {
			objConnection.close();
    	}
	}

	public Usuario getUserByMail(String mail) {
		MySqlConnection objConnection = new MySqlConnection();
		Usuario objUsuario = null;
		
		try {
			objConnection.open();
			if(!objConnection.isError()){
				String sql = "SELECT * FROM usuarios WHERE email = '" + mail+"'";
				ResultSet Result = objConnection.executeSelect(sql);
				while(Result.next()) {
					objUsuario = new Usuario();
					
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
					
					
				}
			}else {
				this.flagError = true;
				this.msgError = "Error en getById. El objeto clsConectionMysql informa error al abrir conexión. +Info: " + objConnection.msgError();
			}
		}catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en getAll. +Info: " + ex.getMessage();
		}finally {
			objConnection.close();
		}
		
		return objUsuario;
	}
	

	
	public void deleteById(int idUsuario) {
		MySqlConnection objConnection = new MySqlConnection();
		try {
			objConnection.open();
			if(!objConnection.isError()) {
				String sql = "DELETE FROM usuarios_roles WHERE id_usuario = " + idUsuario;
				objConnection.executeUpdateOrDelete(sql);
				sql = "DELETE FROM usuarios WHERE id = " + idUsuario;
				
				objConnection.executeUpdateOrDelete(sql);
			}else {
			    this.flagError = true;
			    this.msgError = "Error en deleteById. El objeto clsConectionMySql informa error al abrir conexión. +Info: " + objConnection.msgError();
		   }
			
		} catch (Exception ex) {
		       this.flagError = true;
		       this.msgError = "Error en deleteById. +Info: " + ex.getMessage();
		} finally {
		       objConnection.close();
		}
	
	}
	
	
	public void updateUser(Usuario objUsuario) {
		MySqlConnection objConnection = new MySqlConnection();
		try {
			objConnection.open();
			if(!objConnection.isError()) {
				String sql = "UPDATE usuarios set "
						+ "NOMBRE = '" + objUsuario.getNombre() + "', "
						+ "APELLIDOS = '" + objUsuario.getApellidos() + "', "
						+ "DIRECCION = '" + objUsuario.getDireccion() + "', "
						+ "TLF = '" + objUsuario.getTlf() + "', "
						+ "EMAIL = '" + objUsuario.getMail() + "', "
						+ "NIF = '" + objUsuario.getNif() + "', "
						+ "password = '" + objUsuario.getPassword() + "' "
						+ "WHERE ID = " + objUsuario.getId();
				objConnection.executeUpdateOrDelete(sql);							
			}else {
				 this.flagError = true;
				 this.msgError = "Error en updateUser. El objeto clsConectionMySql informa error al abrir conexión. +Info: " + objConnection.msgError();
			}
		} catch (Exception ex) {
		       this.flagError = true;
		       this.msgError = "Error en updateUser. +Info: " + ex.getMessage();
		} finally {
		       objConnection.close();
		}
	}
	
	
	public Usuario getUserbyName(String userName) {
		MySqlConnection objConnection = new MySqlConnection();
		Usuario objUsuario = null;
		
		try {
			objConnection.open();
			if(!objConnection.isError()){
				String sql = "SELECT * FROM usuarios WHERE nombre = '" + userName+"'";
				ResultSet Result = objConnection.executeSelect(sql);
				while(Result.next()) {
					objUsuario = new Usuario();
					
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
					
					
				}
			}else {
				this.flagError = true;
				this.msgError = "Error en getById. El objeto clsConectionMysql informa error al abrir conexión. +Info: " + objConnection.msgError();
			}
		}catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en getAll. +Info: " + ex.getMessage();
		}finally {
			objConnection.close();
		}
		
		return objUsuario;
	}

}
