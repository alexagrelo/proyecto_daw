package es.dsw.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlConnection {
	
	private String host = "localhost";
	private String port = "3306";
	private String nameDB = "db_biofruit";
	private String user = "root";
	private String password = "1234";
	
	private boolean flagError;
	private String msgError;
	
	private Connection connection;
	
	private void _initialize() {
		this.flagError = false;
		this.msgError = "";
		this.connection = null;
	}
	private void _initializeError() {
		this.flagError = false;
		this.msgError = "";
	}	
	public MySqlConnection() {
		this._initialize();
	}
	
	public MySqlConnection(String _nameDB) {
		this._initialize();
		this.nameDB = _nameDB;
	}
	
	public MySqlConnection(String _host, String _port, String _nameDB, String _user, String _password)
	{
		this._initialize();
		this.host = _host;
		this.port = _port;
		this.nameDB = _nameDB;
		this.user = _user;
		this.password = _password;
	}
	
	
	public void open() {
		try {
			this._initializeError();
			if((this.connection == null) || ((this.connection != null) && (this.connection.isClosed()))) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				this.connection = DriverManager.getConnection("jdbc:mysql://"+this.host+":"+this.port+"/"+this.nameDB, this.user, this.password);
			}
		}
		catch(ClassNotFoundException ex) {
			this.flagError = true;
			this.msgError = "Error al registrar el driver. +Info: " + ex.getMessage();
		}
		catch(Exception ex) {
			this.flagError = true;
			this.msgError = "Error al abrir la conexión. +Info: " + ex.getMessage();
		}
	}
	
	public void close() {
		try
		{   this._initializeError();
			if ((this.connection != null) && (!this.connection.isClosed()))
			   this.connection.close();
		}
		catch (SQLException ex)
		{
			this.flagError = true;
			this.msgError = ex.getMessage();
		}
	}
	
	public ResultSet executeSelect(String _sql)
	{
		try {   
			this._initializeError();
			if (this.connection != null) {
				if (!this.connection.isClosed()) {
					java.sql.Statement objStament = this.connection.createStatement();	
					ResultSet rs = objStament.executeQuery (_sql);
					return rs;
				}
				else {
					this.flagError = true;
					this.msgError = "Error en ExecuteSelect. +Info: Conexión cerrada.";
				}
			}
			else {
				this.flagError = true;
				this.msgError = "Error en ExecuteSelect. +Info: Conexión no inicializada.";
			}
		}
		catch (SQLException ex) {
			this.flagError = true;
			this.msgError = "Error en ExecuteSelect. +Info: " + ex.getMessage();
		}

		return null; 
	}
	
	public ResultSet executeInsert(String _sql) {
			
			try {   
				 this._initializeError();
				 if (this.connection != null) {
					if (!this.connection.isClosed()) {
						PreparedStatement objStament = this.connection.prepareStatement(_sql,Statement.RETURN_GENERATED_KEYS);	
						objStament.execute();
						ResultSet rs = objStament.getGeneratedKeys();
						return rs;
					}
					else {
						this.flagError = true;
						this.msgError = "Error en ExecuteQuery. +Info: Conexión cerrada.";
					}
				 }
				 else {
					this.flagError = true;
					this.msgError = "Error en ExecuteQuery. +Info: Conexión no inicializada.";
				}
			   }
			   catch (SQLException ex) {
				    this.flagError = true;
				    this.msgError = "Error en ExecuteQuery. +Info: " + ex.getMessage();
			   }
			
			return null; 
		}
	
	//Método para ejecutar un update o delete. Devuelve el número de registros afectados.
	public int executeUpdateOrDelete(String _sql) {
		int NumRows = 0;
		try {   
			 this._initializeError();
			 if (this.connection != null) {
				if (!this.connection.isClosed()) {
					PreparedStatement objStament = this.connection.prepareStatement(_sql);	
					NumRows = objStament.executeUpdate();
				}
				else {
					this.flagError = true;
					this.msgError = "Error en ExecuteQuery. +Info: Conexión cerrada.";
				}
			 }
			 else {
				this.flagError = true;
				this.msgError = "Error en ExecuteQuery. +Info: Conexión no inicializada.";
			}
		   }
		   catch (SQLException ex) {
			    this.flagError = true;
			    this.msgError = "Error en ExecuteQuery. +Info: " + ex.getMessage();
		   }
		
		return NumRows;
	}
	
	//Devuelve el valor de la bandera de error
	public boolean isError() {
		return this.flagError;
	}
	
	//Devuelve la descripción del error
	public String msgError() {
		return this.msgError;
	}
}
