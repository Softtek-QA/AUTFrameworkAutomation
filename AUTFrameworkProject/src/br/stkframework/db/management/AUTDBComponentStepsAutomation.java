/**
 * 
 */
package br.stkframework.db.management;

/**
 * Gerenciamento de passos automatizados
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBComponentStepsAutomation extends AUTDBComponentStepsDefinition {
	/**
	 * Comandos de gerenciamento - Steps automation
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_STEPS_AUTOMATION_OPERATIONS{
		INSERT_STEP_AUTOMATION_BY_STEP_DEFINITION,
		SELECT_STEP_AUTOMATION_BY_STEP_DEFINITION,
		SELECT_ALL_STEPS_BY_COMPONENT,
		SELECT_ALL_STEPS;
		
		@Override
		public String toString() {			
			switch(this) {
			case INSERT_STEP_AUTOMATION_BY_STEP_DEFINITION:{
				return "INSERT INTO lry.aut_component_steps_automation(STEP_ID, EXC_ID, STEP_EXECUTION_ORDER, STEP_NAME, STEP_DESCRIPTION, STEP_ACTION, STEP_EXPECT_RESULT, STEP_TYPE_EXPECT_RESULT, STEP_BEFORE_STATUS, STEP_AFTER_STATUS, STEP_STATUS_LOG, STEP_OPERATION_STATE, STEP_OPTIONAL, STEP_ACTION_EXECUTION_STATUS) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			}
			case SELECT_ALL_STEPS:{
				return "SELECT step_aut_id FROM lry.aut_component_steps_automation;";
			}
			case SELECT_ALL_STEPS_BY_COMPONENT:{
				return "SELECT DEFAUT.STEP_AUT_ID FROM LRY.AUT_COMPONENT_STEPS_DEFINITIONS AS DEFSTEPS INNER JOIN LRY.AUT_COMPONENT_STEPS_AUTOMATION AS DEFAUT ON DEFAUT.STEP_ID=DEFSTEPS.STEP_ID AND DEFSTEPS.CMP_ID=?;";
			}
			case SELECT_STEP_AUTOMATION_BY_STEP_DEFINITION:{
				return "SELECT DEFAUT.STEP_AUT_ID FROM LRY.AUT_COMPONENT_STEPS_DEFINITIONS AS DEFSTEPS INNER JOIN LRY.AUT_COMPONENT_STEPS_AUTOMATION AS DEFAUT ON DEFAUT.STEP_ID=DEFSTEPS.STEP_ID AND DEFAUT.STEP_ID=?;";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	/**
	 * Inclui um novo step automatizado
	 * 
	 * 
	 * @param stepId - Id do step automatizado
	 * @param execId - Id de execução do item
	 * @param executionOrder - Ordem de execution
	 * @param stepName - Nome do step
	 * @param stepDescription - Descrição do step
	 * @param stepActions - Ações do step
	 * @param expectResult - Resultado esperado do step
	 * @param typeExpectResult - Tipo de resultado esperado
	 * @param beforeExec - Status antes da execução do step
	 * @param afterExec - Status após execução do step
	 * @param statusLog - Log do status atual
	 * @param stateOperational - Status operacional
	 * @param isOptionalStep - É um step opcional
	 * @param stateExecution - Status de execução
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 */
	public boolean autInsertStepAutomation(String stepId, String execId, String executionOrder, String stepName, String stepDescription, String stepActions, String expectResult, String typeExpectResult, String beforeExec, String afterExec, String statusLog, String stateOperational, boolean isOptionalStep, String stateExecution) {
		try {
			
			System.out.println("AUT INFO : INSERT STEP AUTOMATION BY STEP DEFINITION");
			autExecSubStatements(AUT_STEPS_AUTOMATION_OPERATIONS.INSERT_STEP_AUTOMATION_BY_STEP_DEFINITION.toString(), 
					new Object[] {stepId, execId, executionOrder, stepName, stepDescription, stepActions, expectResult, typeExpectResult, beforeExec, afterExec, statusLog, stateOperational, isOptionalStep, stateExecution});
			
			return true;
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: INSERT STEP AUTOMATION BY STEP DEFINITION");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	/**
	 * 
	 * Lista todos os steps automatizados por step modelado - Manual - base de definição
	 * 
	 * @param idStepModel - Id do step manual
	 * 
	 * @return java.util.List(Object) - Lista de steps automatizados por id
	 * 
	 */
	public java.util.List<Object> autListStepsAutomationByStepManual(String idStepModel){
		try {
			System.out.println("AUT INFO: LIST STEPS AUTOMATION BY STEP MODEL - DEFINITION - MANUAL");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_STEPS_AUTOMATION_OPERATIONS.SELECT_STEP_AUTOMATION_BY_STEP_DEFINITION.toString(), 
					new Object[] {idStepModel});
			
			return autGetListItems(rsData);
			
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST STEPS AUTOMATION BY STEP MODEL - DEFINITION - MANUAL");
			System.out.println(e.getMessage());
			e.getMessage();
			
			return null;
		}
	}
	/**
	 * 
	 * Lista todos os steps automatizados para o componente de negócio especificado
	 * 
	 * @param idComponent - Id do componente de negócio
	 * 
	 * @return java.util.List(Object) - Lista de steps automatizados por id
	 */
	public java.util.List<Object> autListStepsAutomationByComponent(String idComponent){
		try {
			System.out.println("AUT INFO: LIST STEPS AUTOMATION BY BUSINESS COMPONENT");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_STEPS_AUTOMATION_OPERATIONS.SELECT_ALL_STEPS_BY_COMPONENT.toString(), new Object[] {idComponent});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: LIST STEPS AUTOMATION BY BUSINESS COMPONENT");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	/**
	 * 
	 * Lista todos os steps automatizados incluídos no sistema para os componentes de negócio
	 * 
	 * @return java.util.List(Object) - Lista de steps automatizados por id
	 * 
	 */
	public java.util.List<Object> autListAllStepsAutomation(){
		try {
			System.out.println("AUT INFO: LIST ALL STEPS AUTOMATION FOR ALL BUSINESS COMPONENT");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_STEPS_AUTOMATION_OPERATIONS.SELECT_ALL_STEPS.toString(), 
					new Object[] {});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL STEPS AUTOMATION FOR ALL BUSINESS COMPONENT");
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
	public AUTDBComponentStepsAutomation() {
		// TODO Auto-generated constructor stub
		super();
	}

}
