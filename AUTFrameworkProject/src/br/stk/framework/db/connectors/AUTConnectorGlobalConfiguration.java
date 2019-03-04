/**
 * 
 */
package br.stk.framework.db.connectors;

import br.stk.framework.db.management.AUTDBSessionsUsers;

/**
 * 
 * Gerenciamento de configura��es globais do sistema
 * 
 * @author Softtek-QA
 *
 */
public class AUTConnectorGlobalConfiguration {
	public static String AUT_MYSQL_SERVER = "192.168.0.101"; //Vari�vel global de configura��o do IP de servi�o para banco de dados MYSQL
	public static String AUT_SQL_SERVER = "localhost"; //Vari�vel global de configura��o do IP de servi�o para banco de dados MYSQL
	public static String AUT_JAVA_DERBY_SERVER = "localhost"; //Vari�vel global de configura��o do IP de servi�o para banco de dados MYSQL
	public static String AUT_H2_SERVER = "mphst001"; //Vari�vel global de configura��o do IP de servi�o para banco de dados MYSQL
	public static String AUT_MYSQL_PORT_CONNECT = "3306"; //Porta de conex�o mysql
	public static String AUT_SQL_SERVER_PORT_CONNECT = "1422"; //Porta de conex�o sql server
	public static String AUT_JAVA_DERBY_PORT_CONNECT = "65000"; //Porta de conex�o java derby
	public static String AUT_JAVA_H2_PORT_CONNECT = "65001"; //porta de conex�o java H2
	public AUTConnectorSessions AUT_CONNECTOR_SESSION;
	/**
	 * 
	 * Classe respons�vel pelo gerenciamento da sess�o corrente com o banco de dados
	 * 
	 * 
	 * @author Softtek-QA
	 *
	 */
	public static class AUTConnectorSessions extends AUTDBSessionsUsers{
		public String 		AUT_HOST_DB = "localhost";	
		public Integer 		AUT_HOST_PORT_DB = 3306;
		public String 		AUT_DATA_BASE_NAME = "lry";
		public String 		AUT_USER_LOGIN_DB = "admin";
		public String 		AUT_PWD_LOGIN_DB = "Stk1234!";
				
		/**
		 * 
		 * Comandos para gerenciamento dos parametros de configura��o do framework
		 * 
		 * 
		 * @author Softtek-QA
		 *
		 */
		public enum AUT_FRAMEWORK_OPERATIONS{
			AUT_FWK_SELECT_TABLE_INIT_CONFIG;
			
			@Override
			public String toString() {
				// TODO Auto-generated method stub
				switch(this) {
				case AUT_FWK_SELECT_TABLE_INIT_CONFIG:{
					return "SELECT * FROM LRY.AUT_FWK_INIT_CONFIGURATION";
				}
				default:{
					return this.name();
				}
				}
			}
		}
		
		/** 
		 * 
		 * Carrega as configura��es de inicializa��o para sess�o a
		 * 
		 * @return java.util.HashMap - Conjunto de parametros de configura��o cadastrados no banco de dados
		 * 
		 * 
		 */
		public java.util.HashMap<String,Object> autGetInitConfigFromFWK(){
			try {				
				java.util.HashMap<String,Object> paramsOut = new java.util.HashMap<String,Object>();
				System.out.println("AUT INFO: GET INIT CONFIGURATION FROM FRAMEWORK AUTOMATION");
				autStartNewConnection(AUT_TYPE_SGDB.MYSQL, AUT_HOST_DB, AUT_HOST_PORT_DB, AUT_DATA_BASE_NAME, AUT_USER_LOGIN_DB, AUT_PWD_LOGIN_DB);
							
				return paramsOut;
				
			}
			catch(java.lang.Exception e) {
				System.out.println("AUT ERROR: GET INIT CONFIGURATION  FROM FRAMEWORK AUTOMATION");
				System.out.println(e.getMessage());
				e.printStackTrace();
				
				return null;
			}
		}
		
		
		/**
		 * 
		 * Construtor padr�o da classe de gerenciamento de sess�es
		 * 
		 */
		public AUTConnectorSessions(){
			super();
		}
	}
	
	/**
	 * 
	 * Comandos pr� definidos de configura��o do framework de configura��o do framework
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_GLOBAL_CONFIGURATION{
		MYSQL_SERVER_IP,
		MYSQL_SERVER_PORT,
		MYSQL_SERVER_DATA_BASE_NAME,
		MYSQL_SERVER_DATA_BASE_VALUE,
		DEFAULT_SERVER_IP_NAME,
		DEFAULT_SERVER_PORT_NAME,
		DEFAULT_SERVER_IP_VALUE,
		DEFAULT_SERVER_PORT_VALUE,
		DEFAULT_SERVER_DATA_BASE_NAME,
		DEFAULT_SERVER_DATA_BASE_VALUE,
		DEFAULT_USR_DB,
		DEFAULT_PWD_DB;
		@Override
		public String toString() {
			switch(this) {
			case DEFAULT_USR_DB:{
				return "admin";
			}
			case MYSQL_SERVER_DATA_BASE_NAME:{
				return "lry";
			}
			case DEFAULT_SERVER_DATA_BASE_NAME:{
				return MYSQL_SERVER_DATA_BASE_NAME.name();
			}
			case DEFAULT_SERVER_IP_NAME:{
				return MYSQL_SERVER_IP.name();
			}
			case DEFAULT_SERVER_PORT_NAME:{
				return MYSQL_SERVER_PORT.name();
			}
			case MYSQL_SERVER_IP:{
				return AUT_MYSQL_SERVER;
			}
			case DEFAULT_PWD_DB:{
				return "Stk1234!";
			}
			case MYSQL_SERVER_PORT:{
				return AUT_MYSQL_PORT_CONNECT;
			}
			case  DEFAULT_SERVER_IP_VALUE:{
				return valueOf(DEFAULT_SERVER_IP_NAME.toString()).toString();
			}
			case DEFAULT_SERVER_PORT_VALUE:{
				return valueOf(DEFAULT_SERVER_PORT_NAME.toString()).toString();
			}
			case DEFAULT_SERVER_DATA_BASE_VALUE:{
				return valueOf(MYSQL_SERVER_DATA_BASE_NAME.name()).toString();
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	/**
	 * 
	 * Recupera as configura��es global de configura��o do framework
	 * 
	 * @return java.util.HashMap - Conjunto de parametros de configura��o do sistema
	 * 
	 */
	public java.util.HashMap<String,Object> autGetDefaultGlobalConfiguration(){
		try {
			java.util.HashMap<String,Object> paramOut = new java.util.HashMap<String,Object>();

			paramOut.put(AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_IP_NAME.name(), AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_IP_NAME.toString());
			paramOut.put(AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_PORT_NAME.name(), AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_PORT_NAME.toString());
			paramOut.put(AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_DATA_BASE_NAME.name(), AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_DATA_BASE_NAME.toString());
			paramOut.put(AUT_GLOBAL_CONFIGURATION.valueOf(AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_IP_NAME.toString()).name(),AUT_GLOBAL_CONFIGURATION.valueOf(AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_IP_NAME.toString()).toString());
			paramOut.put(AUT_GLOBAL_CONFIGURATION.valueOf(AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_PORT_NAME.toString()).name(),AUT_GLOBAL_CONFIGURATION.valueOf(AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_PORT_NAME.toString()).toString());
			paramOut.put(AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_PORT_VALUE.name(), AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_PORT_VALUE.toString());
			paramOut.put(AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_IP_VALUE.name(), AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_IP_VALUE.toString());
			paramOut.put(AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_DATA_BASE_VALUE.name(), AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_DATA_BASE_VALUE.toString());
			
			return paramOut;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: GET DEFAULT CONFIGUTION FROM FRAMEWORK");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}

	/**
	 * 
	 * Configura��es de inicializa��o do projeto
	 * 
	 */
	public void configInit() {
		AUT_CONNECTOR_SESSION = new AUTConnectorSessions();
	}
	/**
	 *
	 * Construtor padr�o
	 * 
	 */
	public AUTConnectorGlobalConfiguration() {
		configInit();
		// TODO Auto-generated constructor stub
	}

}
