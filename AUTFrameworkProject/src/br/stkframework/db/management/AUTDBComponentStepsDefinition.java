/**
 * 
 */
package br.stkframework.db.management;

/**
 * Gerenciamento de modelagens do componente de neg�cio
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBComponentStepsDefinition extends AUTDBBusinessComponent {
	/**
	 * 
	 * Comandos para gerenciamento dos steps da modelagem
	 * 
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_STEPS_DEFINE_OPERATIONS{
		INSERT_STEP_BY_COMPONENT,
		SELECT_STEP_BY_COMPONENT,
		SELECT_ALL_STEPS;
		
		@Override
		public String toString() {
			switch(this) {
			case INSERT_STEP_BY_COMPONENT:{
				return "INSERT INTO lry.aut_component_steps_definitions(CMP_ID, STEP_EXECUTION_ORDER, STEP_NAME, STEP_DESCRIPTION, STEP_ACTION, STEP_EXPECT_RESULT, STEP_TYPE_EXPECT_RESULT, STEP_OPERATION_STATE, STEP_OPTIONAL) VALUES(?,?,?,?,?,?,?,?,?);";
			}
			case SELECT_ALL_STEPS:{
				return "SELECT STEP_ID FROM lry.aut_component_steps_definitions;";
			}
			case SELECT_STEP_BY_COMPONENT:
			{
				return "SELECT STEP_ID FROM lry.aut_component_steps_definitions WHERE CMP_ID=?;";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	/**
	 * 
	 * Inclu� um novo step no banco de dados associado ao componente de neg�cio
	 * 
	 * 
	 * @param idComponent - Id do componente de neg�cio
	 * @param stepOrderExecution - Order de execu��o para o step
	 * @param stepName - Nome do step
	 * @param stepDescription - Descri��o do step de neg�cio
	 * @param stepActions - A��es do step
	 * @param stepExpectResult - Resultado esperado do componente de neg�cio
	 * @param stepTypeExpectResult - Tipo da evid�ncia associada ao resultado esperado
	 * @param stepStateOperational - Status operacional
	 * @param isStepOptional - � um step opcional, caso seja flegado como true em caso de erro na execu��o desse step o mesmo ser� desconsiderado
	 * 
	 * @return boolean - True caso o  processo seja finalizado com sucesso false caso contr�rio
	 * 
	 */
	public boolean autInsertStepsDefinitions(String idComponent, String stepOrderExecution, String stepName, String stepDescription, String stepActions, String stepExpectResult, String stepTypeExpectResult,String stepStateOperational, boolean isStepOptional) {
		try {
			System.out.println("AUT INFO: INSERT STEP DEFINITION FOR BUSINESS COMPONENT");
			autExecSubStatements(AUT_STEPS_DEFINE_OPERATIONS.INSERT_STEP_BY_COMPONENT.toString(), 
					new Object[] {idComponent,stepOrderExecution,stepName,stepDescription,stepActions,stepExpectResult,stepTypeExpectResult,stepStateOperational,isStepOptional});
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: INSERT STEP DEFINITION FOR BUSINESS COMPONENT");
			System.out.println(e.getMessage());
			e.printStackTrace();			
			return false;
		}
	}
	/**
	 * Lista todos os steps configurados para o componente de neg�cio
	 * 
	 * @param idComponent - Id do componente de neg�cio
	 * 
	 * @return java.util.List(Object) - Lista de steps configuration by componente de neg�cio
	 * 
	 */
	public java.util.List<Object> autListStepsDefinitionsByComponent(String idComponent)
	{
		try {
			System.out.println("AUT INFO: LIST ALL STEPS DEFINITIONS FOR BUSINESS COMPONENT");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_STEPS_DEFINE_OPERATIONS.SELECT_STEP_BY_COMPONENT.toString(), 
					new Object[] {});
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL STEPS DEFINITIONS FOR BUSINESS COMPONENT");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 
	 * Lista todas as configura��es de steps j� cadastradas no sistema de todos os componentes
	 * 
	 * @return java.util.List(Object) - Lista de Steps por ID
	 * 
	 */
	public java.util.List<Object> autListAllStepsDefinitions(){
		try {
			
			System.out.println("AUT INFO: SELECT ALL STEPS DEFINITIONS");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_STEPS_DEFINE_OPERATIONS.SELECT_ALL_STEPS.toString(), 
					new Object[] {});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: SELECT ALL STEPS DEFINITIONS");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	/**
	 * 
	 * Construtor padr�o 
	 * 
	 */
	public AUTDBComponentStepsDefinition() {
		// TODO Auto-generated constructor stub
		super();
	}

}
