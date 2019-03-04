/**
 * 
 */
package br.stk.framework.db.management;

/**
 * 
 * Gerenciamento de execuções detalhadas por frente de negócio
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBProjectsExecutionDetail extends AUTDBProject {
	private AUTDBProjectsResourceExecManagement pjtResourceExecution = null;
	
	
	/**
	 * 
	 * Conjunto de propriedades de configuração da tabele de dados do sistema de cadastro de cenários
	 * 
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_PROJECT_DETAIL_PROPERTIES{
		PJT_ID,
		DSK_ID, 
		VM_ID, 
		DSK_NAME,
		VM_NAME,
		DSK_IP,
		VM_IP,
		STD_ITEM_CONFIGURATION,
		STD_STATUS
	}
	
	
	
	/**
	 * 
	 * Define o conjunto de operações utilizadas para o gerenciamento de cenários que teste que serão executados em modo de debug
	 * 
	 * @author Softtek-QA
	 * 
	 *
	 */
	public enum AUT_PROJECT_EXEC_DETAIL_OPERATIONS{
		INSERT_MODULE_ITEM,
		SELECT_ALL_MODULE_ITEM,
		UPDATE_STATE_MODULE_ITEM,
		INSERT_SCENARIO_ITEM,
		SELECT_ALL_SCENARIO_ITEM,
		UPDATE_STATE_SCENARIO_ITEM,
		INSERT_TEST_CASE_ITEM,
		SELECT_ALL_TEST_CASE_ITEM,
		UPDATE_STATE_TEST_CASE_ITEM,
		DELETE_DETAILS_EXECUTION_BY_ID,
		DELETE_DETAILS_EXECUTION_BY_ITEM_TEST_ID;
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub			
			switch(this) {
			case DELETE_DETAILS_EXECUTION_BY_ID:{
				return "DELETE FROM lry.aut_projects_status_details WHERE PJT_ID=?;";
			}
			case DELETE_DETAILS_EXECUTION_BY_ITEM_TEST_ID:{
				return "DELETE FROM lry.aut_projects_status_details WHERE PJT_ID=? AND STD_ITEM_CONFIGURATION=?;";
			}
			case INSERT_MODULE_ITEM:{
				return "INSERT INTO lry.aut_projects_status_details(PJT_ID," + 
						"DSK_ID, VM_ID, DSK_NAME," + 
						"VM_NAME," + 
						"DSK_IP, " + 
						"VM_IP," + 
						"STD_ITEM_CONFIGURATION," + 
						"STD_STATUS)VALUES(?,?,?,?,?,?,?,?,?)";
			}
			case INSERT_SCENARIO_ITEM:{
				return "INSERT INTO lry.aut_projects_status_details(PJT_ID," + 
						"DSK_ID, VM_ID, DSK_NAME," + 
						"VM_NAME," + 
						"DSK_IP, " + 
						"VM_IP," + 
						"STD_ITEM_CONFIGURATION," + 
						"STD_STATUS)VALUES(?,?,?,?,?,?,?,?,?)";
			}
			case INSERT_TEST_CASE_ITEM:{
				return "INSERT INTO lry.aut_projects_status_details(PJT_ID," + 
						"DSK_ID, VM_ID, DSK_NAME," + 
						"VM_NAME," + 
						"DSK_IP, " + 
						"VM_IP," + 
						"STD_ITEM_CONFIGURATION," + 
						"STD_STATUS)VALUES(?,?,?,?,?,?,?,?,?)";
			}
			case SELECT_ALL_MODULE_ITEM:{
				return "SELECT * FROM lry.aut_projects_status_details;";
			}
			case SELECT_ALL_SCENARIO_ITEM:{
				return "SELECT * FROM lry.aut_projects_status_details;";
			}
			case SELECT_ALL_TEST_CASE_ITEM:{
				return "SELECT * FROM lry.aut_projects_status_details;";
			}
			case UPDATE_STATE_MODULE_ITEM:{
				return "UPDATE lry.aut_projects_status_details SET PJT_ID=?," + 
						"DSK_ID=?, VM_ID=?, DSK_NAME=?," + 
						"VM_NAME=?," + 
						"DSK_IP=?, " + 
						"VM_IP=?," + 
						"STD_ITEM_CONFIGURATION=?," + 
						"STD_STATUS)VALUES(?,?,?,?,?,?,?,?,?) WHERE STD_ID=?";
			}
			case UPDATE_STATE_SCENARIO_ITEM:{
				return "UPDATE lry.aut_projects_status_details SET PJT_ID=?," + 
						"DSK_ID=?, VM_ID=?, DSK_NAME=?," + 
						"VM_NAME=?," + 
						"DSK_IP=?, " + 
						"VM_IP=?," + 
						"STD_ITEM_CONFIGURATION=?," + 
						"STD_STATUS)VALUES(?,?,?,?,?,?,?,?,?) WHERE STD_ID=?";
			}
			case UPDATE_STATE_TEST_CASE_ITEM:{
				return "UPDATE lry.aut_projects_status_details SET PJT_ID=?," + 
						"DSK_ID=?, VM_ID=?, DSK_NAME=?," + 
						"VM_NAME=?," + 
						"DSK_IP=?, " + 
						"VM_IP=?," + 
						"STD_ITEM_CONFIGURATION=?," + 
						"STD_STATUS)VALUES(?,?,?,?,?,?,?,?,?) WHERE STD_ID=?";
			}
			}
			return super.toString();
		}
	}
		
	/**
	 * 
	 * Retorna o gerenciados de recursos da execução relacionada ao projeto
	 * 
	 * @return TResourcesManagement - Classe que extende o gerenciador de projetos
	 * 
	 */
	private <TResourcesManagement extends AUTDBProjectsResourceExecManagement> AUTDBProjectsResourceExecManagement autGetResourceManagement() {
		pjtResourceExecution = new AUTDBProjectsResourceExecManagement();
		pjtResourceExecution.autSetConnection(getActiveConnection());
		return pjtResourceExecution;
	}
	
	/**
	 * 
	 * Inclui a imagem da relacionada ao status
	 * 
	 * @param projectId - Id do projeto
	 * @param scenarioName - Nome do cenário
	 * 
	 * @return boolean - Retorna true caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public boolean autInsertResourceImageExecution(String projectId,String scenarioName,Object resourceItem) {
		try {		
			System.out.println("AUT INFO : RESOURCE EXECUTION : INSERT NEW IMAGE : INIT");
			autGetResourceManagement().autInsertResourceExecImage(new Object[] {autGetResourceManagement().autGetResourceIdByScenario(new Object[] {projectId,scenarioName}),projectId,scenarioName,resourceItem});			
			System.out.println("AUT INFO: RESOURCE EXECUTION : INSERT NEW IMAGE : END");						
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR : INSERT NEW IMAGE");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	
	public boolean autInsertResourceImageExecution(java.sql.Connection con,String projectId,String scenarioName,Object resourceItem) {
		try {		
			System.out.println("AUT INFO : RESOURCE EXECUTION : INSERT NEW IMAGE : INIT");
			autGetResourceManagement().autInsertResourceExecImage(new Object[] {autGetResourceManagement().autGetResourceIdByScenario(new Object[] {projectId,scenarioName}),projectId,scenarioName,resourceItem});
			System.out.println("AUT INFO: RESOURCE EXECUTION : INSERT NEW IMAGE : END");						
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR : INSERT NEW IMAGE");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	/**
	 * 
	 * Inclui um novo cenário na base de dados
	 * 
	 */
	public void autInsertScenarioExecutionDetail(Object[] parameters) {
		autStartDefaultConnection();
		autExecSubStatements(AUT_PROJECT_EXEC_DETAIL_OPERATIONS.INSERT_SCENARIO_ITEM.toString(), parameters);
		System.out.println("AUT INSERT PROJECT RESULT DETAIL : OK");
	}
	
	/**
	 * 
	 * Retorna uma lista com os projetos cadastrados no sistema para as frentes de projeto
	 * 
	 * 
	 * @return HashMap<Integer,java.util.HashMap<String,Object>> - Hash de dados 
	 */
	public java.util.HashMap<Integer,java.util.HashMap<String,Object>> autListProjectsResultDetails(){
		return autGetDataTableByProperties(AUT_PROJECT_EXEC_DETAIL_OPERATIONS.SELECT_ALL_SCENARIO_ITEM.toString(), AUT_PROJECT_DETAIL_PROPERTIES.class, new Object[] {});
	}
	
	/**
	 * 
	 * Limpa as configurações e itens relacionados a execução do projeto específicado
	 * 
	 * @param parameters -  Parametros de configuração para o comando
	 * 
	 * @return boolean - Sucesso caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public boolean autDeleteProjetExecutionDetails(Object[] parameters) {
		try {
			System.out.println("AUT INFO: DELETE PROJECTS EXECUTION DETAILS BY ID: INIT");
			
			autExecSubStatementsDefault(AUT_PROJECT_EXEC_DETAIL_OPERATIONS.DELETE_DETAILS_EXECUTION_BY_ID.toString(), parameters);
			
			System.out.println("AUT INFO: DELETE PROJECTS EXECUTION DETAILS BY ID: END");
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: DELETE PROJECTS EXECUTION DETAILS BY ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	/**
	 * 
	 * Construtor padrão
	 * 
	 */
	public AUTDBProjectsExecutionDetail() {
		autStartDefaultConnection();
	}
}
