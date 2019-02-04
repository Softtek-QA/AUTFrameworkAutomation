/**
 * 
 */
package br.stk.framework.db.connectors;

import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.Time;

import com.mysql.jdbc.Driver;

import br.stk.framework.db.connectors.AUTConnectorGlobalConfiguration.AUT_GLOBAL_CONFIGURATION;

/**
 * 
 * Gerenciamento de conexões com o banco de dados
 * 
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBConnection extends AUTDBUtils{
	private java.sql.Connection connection = null; //Objeto da conexão local estabelecida com a base de dados
	/**
	 * Enumera os tipos possíveis de banco de dados - Para integração com framework
	 * 
	 * @author Softtek - QA
	 *
	 */
	public static enum AUT_TYPE_SGDB{
		MYSQL,
		SQLSERVER,
		ORACLE,
		FIREBIRD,
		JAVADERBY,
		JAVAH2
	}
	
	/**
	 * 
	 * Retorna a conexão ativa com o banco de dados
	 * 
	 * @return java.sql.Connection - Objeto que representa a conexão ativa com o banco de dados
	 * 
	 */
	public java.sql.Connection getActiveConnection(){
		return this.connection;			
	}
	
	
	/**
	 * 
	 * Conecta ao banco de dados hospedado em um servidor - MYSQL
	 * 
	 * @param host - Servidor do banco de dados
	 * @param port - Porta de conexão
	 * @param dataBase - Nome do schema (Banco de dados)
	 * @param user - Usuário usado na conexão
	 * @param pwd - Senha usuário
	 * @return java.sql.Connection - Objeto de conexão que implementa (Protocolo JDBC)
	 * @throws SQLException - Qualquer Exeção gerado durante o processo de conexão
	 */
	public java.sql.Connection autConnectMYSQL(String host,Integer port,String dataBase,String user,String pwd) throws SQLException {
		com.mysql.cj.jdbc.Driver driver = null;
		java.util.Properties propItems = null;
		java.sql.Connection con = null;
		System.out.println("AUT INFO : ABRINDO CONEXAO : SGDB : MYSQL");	
		driver = new com.mysql.cj.jdbc.Driver();
		propItems = new java.util.Properties();
		propItems.setProperty("user", user);
		propItems.setProperty("password", pwd);
		propItems.setProperty("port", port.toString());
		propItems.setProperty("serverTimezone", "UTC");			
		propItems.setProperty("verifyServerCertificate", "false");
		con = driver.connect(String.format("jdbc:mysql://%s/%s",host,dataBase),propItems);
		System.out.println(pwd);
		System.out.println(String.format("SGDB: %s : VERSION: %s : CONNECTOR JDBC: %s",
				con.getMetaData().getDatabaseProductName(),
				con.getMetaData().getDatabaseProductVersion(),
				con.getMetaData().getDriverName()));
		System.out.println("AUT INFO : CONEXAO ESTABELECIDA COM SUCESSO");
		
		return con;
	}
	
	public java.sql.Connection autStartNewConnection(AUT_TYPE_SGDB typeSGDB,String host,Integer port,String dataBase,String user,String pwd){		
		System.out.println("*SGDB -- 0001*");		
		try {
			System.out.println("************************************ CONNECTION *********************************************\n\n");
			
			com.mysql.cj.jdbc.Driver driver = null;
			java.util.Properties propItems = null;
			switch(typeSGDB) {
			case FIREBIRD:
			{
				break;
			}
			case JAVADERBY:
			{
				break;
			}
			case JAVAH2:{
				break;
			}
			case MYSQL:{
				
				connection = autConnectMYSQL(host, port, dataBase, user, pwd);
				
				break;
			}
			case ORACLE:{
				break;
			}
			case SQLSERVER:{
				break;
			}
			}			
			System.out.println("\n\n************************************ CONNECTION *********************************************");
			return connection;
		}
		catch(java.lang.Exception e) 
		{
			System.out.println("*SGDB -- 0001 - ERROR*");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Altera a conexão atual
	 * 
	 * @param newConnection - Nova conexão
	 * 
	 * @return boolean - Returna true em caso de sucesso false caso contrário
	 * 
	 */
	public boolean autSetConnection(java.sql.Connection newConnection) {
		try {
			System.out.println("AUT INFO : SET DATABASE CONNECTION");
			connection = newConnection;			
			return true;
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: SET DATABASE CONNECTION");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}

	
	public void autConfigInit() {
		
	}
	
	/**
	 * Construtor padrão da classe
	 * 
	 */
	public AUTDBConnection() {
		System.out.println("AUT INFO : INICIALIZANDO CONECTOR  : MYSQL");
		
	}
}
