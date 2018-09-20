/**
 * 
 */
package br.stkframework.db.management;

/**
 * 
 * Gerenciamento de execuções detalhadas por frente de negócio
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBProjectsExecutionDetail extends AUTDBProject {
	
	
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
		UPDATE_STATE_TEST_CASE_ITEM;
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub			
			switch(this) {
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
	 * Construtor padrão
	 * 
	 */
	public AUTDBProjectsExecutionDetail() {
		
	}
}
