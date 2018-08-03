/**
 * 
 */
package br.stk.framework.db.management;

import java.applet.AppletStub;

/**
 * 
 * Gerenciamento de configurações dos aplicativos de execução dos projetos
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBApplicationConfiguration extends AUTDBProject {
	public enum AUT_APPLICATION_OPERATIONS{
		INSERT_APPLICATION_BY_PROJECT,
		SELECT_ALL_APPLICATIONS,
		SELECT_APPLICATION_BY_PROJECT;
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			
			switch(this) {
			case INSERT_APPLICATION_BY_PROJECT:{
				return "INSERT INTO lry.aut_application_configuration(PJT_ID, APP_KEY, APP_NAME, APP_DESCRIPTION, APP_EXECUTABLE, APP_STATIC_PARAMETERS, APP_CUSTOMS_PARAMETERS) VALUES (?,?,?,?,?,?,?);";
			}
			case SELECT_ALL_APPLICATIONS:{
				return "SELECT APP_ID FROM lry.aut_application_configuration;";
			}
			case SELECT_APPLICATION_BY_PROJECT:{
				return "SELECT APP_ID FROM lry.aut_application_configuration WHERE PJT_ID=?";
			}
			default:{
				return this.name();
			}
			}
		}			
	}
	
	
	/**
	 * 
	 * Inclui uma nova configuração de aplicativo para o projeto
	 * 
	 * 
	 * @param projectId - Id do projeto
	 * @param appKey - Chave de configuração
	 * @param appName - Nome do aplicativo
	 * @param appDescription - Descrição de suas funcionalidades
	 * @param appDirExec - Diretório completo do executável do  aplicativo
	 * @param staticParameters - Parametros estáticos do executável - Parametros fixos
	 * @param dynamicParams - Parametros dinâmicos
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 *  */
	public boolean autInsertApplicationConfiguration(String projectId, String appKey, String appName, String appDescription, String appDirExec, String staticParameters, String dynamicParams) {
		try {
			
			System.out.println("AUT INFO : INSERT APPLICATION CONFIGURATION FOR PROJECT ID");
			autExecSubStatements(AUT_APPLICATION_OPERATIONS.INSERT_APPLICATION_BY_PROJECT.toString(), 
					new Object[] {projectId,appKey,appName,appDescription,appDirExec,staticParameters,dynamicParams});
						
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: INSERT APPLICATION CONFIGURATION FOR PROJECT ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	/**
	 * Lista todos os projetos por id de projeto
	 * 
	 * @param idProject - Id do projeto associado ao item de configuração
	 * 
	 * @return java.util.List(Object) - Lista de itens de configuração por id
	 * 
	 */
	public java.util.List<Object> autListApplicationConfigByProjectId(String idProject){
		try {
			System.out.println("AUT INFO: LIST APPLICATION CONFIGURATION BY PROJECT ID");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_APPLICATION_OPERATIONS.SELECT_APPLICATION_BY_PROJECT.toString(), 
					new Object[] {});
			
			return  autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR : LIST APPLICATION CONFIGURATION BY PROJECT ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	/**
	 * 
	 * Lista todos os itens de configurações de aplicativo
	 * 
	 * @return java.util.List(Object) - Lista de Configurações APP por id
	 * 
	 */
	public java.util.List<Object> autListAllApplicationConfiguration(){
		try {			
			System.out.println("AUT INFO : LIST ALL APPLICATION CONFIGURATION");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_APPLICATION_OPERATIONS.SELECT_ALL_APPLICATIONS.toString(), 
					new Object[] {});
			
			return autGetListItems(rsData);
			
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: LIST ALL APPLICATION CONFIGURATION");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}

	/**
	 *
	 * Construtor padrão da classe
	 * 
	 */
	public AUTDBApplicationConfiguration() {
		// TODO Auto-generated constructor stub
		super();
	}

}
