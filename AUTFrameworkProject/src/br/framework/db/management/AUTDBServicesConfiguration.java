package br.framework.db.management;

/**
 * 
 * Gerenciamento de configura��es 
 * dos servi�os de aplica��o que estaram dispon�veis para esta��es de trabalho inclu�das 
 * no processo de automa��o
 *
 *@author Softtek-QA
 *
 */
public class AUTDBServicesConfiguration extends AUTDBProject {
	/**
	 * Comandos para gerenciamento de servi�os
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_SERVICES_OPERATIONS{
		INSERT_SERVICE_BY_PROJ_ID,
		SELECT_SERVICES_BY_PROJ_ID,
		SELECT_ALL_SERVICES;
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			
			switch(this)
			{
			case INSERT_SERVICE_BY_PROJ_ID:{				
				return "INSERT INTO lry.aut_execution_services(PJT_ID,SVR_NAME,SVR_DESCRIPTION,SVR_LOCATION,SVR_IP,SVR_PORT,SVR_STATUS) VALUES (?,?,?,?,?,?,?)";
			}
			case SELECT_SERVICES_BY_PROJ_ID:{
				return "SELECT SVR_ID FROM lry.aut_execution_services WHERE PJT_ID=?;";
			}
			case SELECT_ALL_SERVICES:{
				return "SELECT SVR_ID FROM lry.aut_execution_services";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	/**
	 * Lista todos os servi�os cadastrados para um projeto
	 * 
	 * @return java.util.List(String) - Lista de servi�os por ID
	 * 
	 */
	public java.util.List<String> autListServicesAll(){
		try {
			java.util.List<String> listOut = new java.util.ArrayList<String>();
			
			System.out.println("AUT INFO : LIST SERVICES ALL FOR PROJECTS ALL");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_SERVICES_OPERATIONS.SELECT_ALL_SERVICES.toString(), 
					new Object[] {});
			while(rsData.next()) {
				listOut.add(rsData.getString(1));
				System.out.println(rsData.getString(1));
			}
			
			return listOut;
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: LISTS ALL SERVICES");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	/**
	 * 
	 * Lista servi�os por projeto
	 * 
	 * @param idProject - Id do projeto propriet�rio do servi�o
	 * @return java.util.List(String) - Lista de servi�os por id
	 */
	public java.util.List<String> autListServicesByProjectId(String idProject){
		try {
			System.out.println("AUT INFO: LIST SERVICES BY PROJECT ID");	
			java.util.List<String> listOut = new java.util.ArrayList<String>();			
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_SERVICES_OPERATIONS.SELECT_SERVICES_BY_PROJ_ID.toString(),
					new Object[] {idProject});			
			while(rsData.next()) {
				listOut.add(rsData.getString(1));
				System.out.println(rsData.getString(1));
			}			
			return listOut;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST SERVICES BY PROJECT ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	/**
	 * Inclui um novo servi�o a base de dados
	 * 
	 * @param idProject - Id do projeto propriet�rio do servi�o
	 *
	 *@param name - Nome do servi�o
	 *@param description - Descri��o do servi�o
	 *@param location - Local 
	 *@param ip - IpV4
	 *@param port - Porta de comunica��o
	 *@param statusOperation - Status operacional
	 *
	 * @return boolean - True caso o processo seja realizado com sucesso e false caso contrario
	 *
	 */
	public boolean autInsertService(String idProject,String name,String description,String location,String ip,String port,String statusOperation) {
		try{		
			System.out.println("AUT INFO: SERVICE BY PROJECT ID");
			autExecSubStatements(AUT_SERVICES_OPERATIONS.INSERT_SERVICE_BY_PROJ_ID.toString(), 
					new Object[]{idProject,name,description,location,ip,port,statusOperation});			
			return true;
		}
		catch(Exception e){
			System.out.println("AUT ERROR: INSERT SERVICE BY PROJECT");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 
	 * Construtor padr�o da classe
	 * 
	 */
	public AUTDBServicesConfiguration() {
		super();
	}

}
