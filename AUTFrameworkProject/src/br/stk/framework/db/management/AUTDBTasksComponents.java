/**
 * 
 */
package br.stk.framework.db.management;

/**
 * 
 * Gerenciamento de tarefas relaciondas ao componente de negócio
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBTasksComponents extends AUTDBBusinessComponent {
	/**
	 * 
	 * comandos para gerenciamento de tarefas
	 * 
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_TASK_COMPONENT_OPERATIONS{
		INSERT_TASK_COMPONENT_BY_COMPONENT_ID,
		SELECT_TASK_BY_COMPONENT_ID,
		SELECT_ALL_TASKS;
		
		@Override
		public String toString() {
			switch(this) {
			case INSERT_TASK_COMPONENT_BY_COMPONENT_ID:{
				return "INSERT INTO lry.aut_tasks_component(CMP_ID, TSK_NAME, TSK_DESCRIPTION, TSK_TIME_EXECUTION_MINUTES) VALUES(?,?,?,?);";
			}
			case SELECT_ALL_TASKS:
			{
				return "SELECT TSK_ID FROM lry.aut_tasks_component;";
			}
			case SELECT_TASK_BY_COMPONENT_ID:{
				return "SELECT TSK_ID FROM lry.aut_tasks_component WHERE CMP_ID=?;";
			}
			default:{
				return this.name();
			}
			}
			}		
	}
	
	
	/**
	 * 
	 * Inclui uma nova tarefa para o componente específicado
	 * 
	 * @param cmpId - Id do componente
	 * @param taskName - Nome da tarefa
	 * @param taskDescription - Descrição da tarefa
	 * @param timeForExecution - Tempo limite para execução da tarefa
	 * 
	 * @return boolean - True caso o processo seja executado com sucesso false caso contrário
	 */
	public boolean autInsertTaskByComponent(String cmpId, String taskName, String taskDescription, String timeForExecution) {
		try {
			System.out.println("AUT INFO : INSERT TASK BY COMPONENT ID");
			autExecSubStatements(AUT_TASK_COMPONENT_OPERATIONS.INSERT_TASK_COMPONENT_BY_COMPONENT_ID.toString(), 
					new Object[] {cmpId,taskName,taskDescription,timeForExecution});
						
			return true;
		}
		catch(java.lang.Exception e) {
			
			return false;
		}
	}
	
	/**
	 * 
	 *Lista todas as tarefas de todos os componentes de negócio do sistema
	 * 
	 * @return java.util.List(Object) - Lista de tarefas registradas no sistema por ID
	 *
	 */
	public java.util.List<Object> autListAllTasksComponents(){
		try {
			
			System.out.println("AUT INFO: LISTA ALL TASKS OF ALL COMPONENTS");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_TASK_COMPONENT_OPERATIONS.SELECT_ALL_TASKS.toString(), new Object[] {});
						
			return autGetListItems(rsData);			
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL TASKS OF ALL COMPONENTS");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			
			return null;
		}
	}
	/**
	 * 
	 * 
	 * Lista todas as tarefas registradas para um componente de negócio
	 * 
	 * @param idComponent - Id do componente de negócio
	 * 
	 * @return java.util.List(Object) - Lista de Tarefas para o componente
	 * 
	 */
	public java.util.List<Object> autListTasksByComponentId(String idComponent){
		try {
			System.out.println("AUT INFO: LIST TASKS BY COMPONENT ID");			
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_TASK_COMPONENT_OPERATIONS.SELECT_TASK_BY_COMPONENT_ID.toString(), 
					new Object[] {idComponent});
			
			return autGetListItems(rsData);			
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST TASKS BY COMPONENT ID");
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
	public AUTDBTasksComponents() {
		super();
	}

}
