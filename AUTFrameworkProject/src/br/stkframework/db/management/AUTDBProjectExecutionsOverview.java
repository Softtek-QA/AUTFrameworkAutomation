/**
 * 
 */
package br.stkframework.db.management;

/**
 * 
 * Gerenciamento de execuções do projeto - frente de negócio
 * 
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBProjectExecutionsOverview extends AUTDBProject {
	/**
	 * Lista de propriedades da tabela de execução geral
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_PROJECT_EXEC_OVERVIEW_PROPERTIES{
		PJT_ID, MTC_DATE_EXECUTION, 
		MTC_TOTAL_MODULOS,
		MTC_TOTAL_SCENARIOS,
		MTC_TOTAL_TEST_CASE,
		MTC_TOTAL_MODULOS_STATUS_PASSED,
		MTC_TOTAL_SCENARIOS_STATUS_PASSED, 
		MTC_TOTAL_TEST_CASE_STATUS_PASSED,
		MTC_TOTAL_MODULOS_STATUS_FAILED, 
		MTC_TOTAL_SCENARIOS_STATUS_FAILED, 
		MTC_TOTAL_TEST_CASE_STATUS_FAILED,
		MTC_TOTAL_MODULOS_EXEC_IN_PROGRESS, 
		MTC_TOTAL_SCENARIOS_EXEC_IN_PROGRESS, 
		MTC_TOTAL_TEST_CASE_EXEC_IN_PROGRESS
	}
	
	/**
	 * Define as operações possível na tabela de execução geral do sistema
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_PROJECT_EXEC_OVERVIEW_OPERATIONS{
		INSERT_PROJ_EXEC_OVERVIEW,
		UPATE_PROJ_EXEC_OVERVIEW,
		SELECT_PROJ_EXEC_OVERVIEW,
		SELECT_ALL_PROJ_EXEC_OVERVIEW;
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			
			switch(this) {
			case INSERT_PROJ_EXEC_OVERVIEW:{
				return "INSERT INTO lry.aut_projects_status_execution(PJT_ID, MTC_TOTAL_MODULOS, "
						+ "MTC_TOTAL_SCENARIOS, MTC_TOTAL_TEST_CASE, MTC_TOTAL_MODULOS_STATUS_PASSED, MTC_TOTAL_SCENARIOS_STATUS_PASSED, MTC_TOTAL_TEST_CASE_STATUS_PASSED,"
						+ "MTC_TOTAL_MODULOS_STATUS_FAILED, MTC_TOTAL_SCENARIOS_STATUS_FAILED, MTC_TOTAL_TEST_CASE_STATUS_FAILED,"
						+ "MTC_TOTAL_MODULOS_EXEC_IN_PROGRESS, MTC_TOTAL_SCENARIOS_EXEC_IN_PROGRESS, MTC_TOTAL_TEST_CASE_EXEC_IN_PROGRESS)"
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
			}
			case SELECT_ALL_PROJ_EXEC_OVERVIEW:{
				return "SELECT * FROM lry.aut_projects_status_execution;";
			}
			case SELECT_PROJ_EXEC_OVERVIEW:{
				return "SELECT * FROM lry.aut_projects_status_execution;";
			}
			case UPATE_PROJ_EXEC_OVERVIEW:{
				return "UPDATE lry.aut_projects_status_execution SET PJT_ID, MTC_DATE_EXECUTION=current_timestamp, MTC_TOTAL_MODULOS=?, "
						+ "MTC_TOTAL_SCENARIOS=?, MTC_TOTAL_TEST_CASE=?, MTC_TOTAL_MODULOS_STATUS_PASSED=?, MTC_TOTAL_SCENARIOS_STATUS_PASSED=?, MTC_TOTAL_TEST_CASE_STATUS_PASSED=?"
						+ "MTC_TOTAL_MODULOS_STATUS_FAILED=?, MTC_TOTAL_SCENARIOS_STATUS_FAILED=?, MTC_TOTAL_TEST_CASE_STATUS_FAILED=?"
						+ "MTC_TOTAL_MODULOS_EXEC_IN_PROGRESS=?, MTC_TOTAL_SCENARIOS_EXEC_IN_PROGRESS=?, MTC_TOTAL_TEST_CASE_EXEC_IN_PROGRESS=? WHERE STU_ID=?";
			}
			}
			return super.toString();
		}
	}
	
	/**
	 * Inclui um novo registo de execução na base de dados
	 * 
	 * @param parametros - Parametros da tabela
	 */
	public void autInsertProjectExecutionOverview(Object[] parametros) {
		autStartDefaultConnection();
		boolean status = autExecSubStatements(AUT_PROJECT_EXEC_OVERVIEW_OPERATIONS.INSERT_PROJ_EXEC_OVERVIEW.toString(), parametros);	
		System.out.println("AUT : INSERT PROJECT EXECUTION OVERVIEW : OK");
		if(status) {
			//System.out.println("AUT : INSERT PROJECT EXECUTION OVERVIEW : OK");
		}
		else {
			//System.out.println("AUT : INSERT PROJECT EXECUTION OVERVIEW : FALHOU");
		}
	}
	
	/**
	 * 
	 * Atualiza um novo registo de execução na base de dados
	 * 
	 * @param parametros - Parametros da tabela
	 * 
	 */
	public void autUpdateProjectExecutionOverview(Object[] parametros) {	

		boolean status = autExecSubStatements(AUT_PROJECT_EXEC_OVERVIEW_OPERATIONS.UPATE_PROJ_EXEC_OVERVIEW.toString(), parametros);		
		if(status) {
			System.out.println("AUT : UPDATE PROJECT EXECUTION OVERVIEW : OK");
		}
		else {
			System.out.println("AUT : UPDATE PROJECT EXECUTION OVERVIEW : FALHOU");
		}
	}
	
	
	/**
	 * Recupera a relação de campos na tabela de execução
	 * 
	 * @return
	 */
	public java.util.HashMap<Integer,java.util.HashMap<String,Object>> autListProjectExecutionsOverview(){		
		return autGetDataTableByProperties(AUT_PROJECT_EXEC_OVERVIEW_OPERATIONS.SELECT_ALL_PROJ_EXEC_OVERVIEW.toString(),AUT_PROJECT_EXEC_OVERVIEW_PROPERTIES.MTC_DATE_EXECUTION.getClass(), new Object[] {});
	}
	
	
	public boolean autInitConfigurationProjectExecutionDB(Class<?> classExecution) {
		try {
			
			return true;
		}
		catch(java.lang.Exception e) {
			
			return false;
		}
	}
	
	/**
	 * 
	 * Construtor padrão da classe
	 * 
	 */
	public AUTDBProjectExecutionsOverview() {
		autStartDefaultConnection();
	}
}
