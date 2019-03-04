/**
 * 
 */
package br.stk.framework.db.management;

/**
 * 
 * Gerenciamento de agendamento de tarefas voltadas para funções de processamento:
 * 
 * FUNÇÕES - PROCESSAMENTO
 *  
 * @author Softtek-QA
 *
 */
public class AUTDBScheduleProcess extends AUTDBScheduleTaskManager{
	
	/**
	 * 
	 * Comandos para o gerenciamento de tarefas de processamento
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_SCHEDULE_PROCESS_OPERATIONS{
		INSERT_SCHEDULE_PROCESS_BY_SCHED_MANAGER,
		SELECT_SCHEDULE_PROCESS_BY_SCHED_MANAGER,
		SELECT_ALL_SCHEDULE_PROCESS;
		@Override
		public String toString() {
			switch(this) {
			case INSERT_SCHEDULE_PROCESS_BY_SCHED_MANAGER:{
				return "INSERT INTO lry.aut_sch_exec_process(SCH_ID, EXC_STATE, EXC_STATE_OBJECT, EXC_STATE_DESCRIPTION) VALUES(?,?,?,?);";
			}
			case SELECT_ALL_SCHEDULE_PROCESS:{
				return "SELECT EXC_ID FROM lry.aut_sch_exec_process;";
			}
			case SELECT_SCHEDULE_PROCESS_BY_SCHED_MANAGER:{
				return "SELECT EXC_ID FROM lry.aut_sch_exec_process WHERE SCH_ID=?;";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	/**
	 * 
	 * Inclui agenda uma nova tarefa de processamento para uma tarefa de gerenciamento do componente de negócio
	 * 
	 * @param schId - Id da tarefa de gerenciamento
	 * @param stateExecution - Status da execução
	 * @param stateObject - Status da execução - Objeto
	 * @param stateDescription - Descrição de status da execução
	 * 
	 * @return boolean - True caso processo de agendamento da tarefa de inicialização seja concluído com sucesso false caso constrário
	 * 
	 */
	public boolean autInsertScheduledProcessBySchedManager(String schId, String stateExecution, String stateObject, String stateDescription) {
		try {
			
			System.out.println("AUT INFO: INSERT SCHEDULED PROCESS TASK BY SCHED MANAGER");
			autExecSubStatements(AUT_SCHEDULE_PROCESS_OPERATIONS.INSERT_SCHEDULE_PROCESS_BY_SCHED_MANAGER.toString(), 
					new Object[] {schId,stateExecution,stateObject,stateDescription});
			
			return true;
		}
		catch(java.lang.Exception e) {			
			System.out.println("AUT ERROR: INSERT SCHEDULED PROCESS TASK BY SCHED MANAGER");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	
	/**
	 * 
	 * Lista todas as tarefas de processamento registradas no sistema
	 * 
	 * @return java.util.List(Object) - Lista de tarefas agendadas no sistema por id
	 * 
	 */
	public java.util.List<Object> autListAllScheduledProcess(){
		try {
			System.out.println("AUT INFO: LIST ALL SCHEDULED PROCESS FOR ALL TASKS MANAGER");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_SCHEDULE_PROCESS_OPERATIONS.SELECT_ALL_SCHEDULE_PROCESS.toString(), 
					new Object[] {});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL SCHEDULED PROCESS FOR ALL TASKS MANAGER");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * 
	 * Lista todas as tarefas de processamento agendas para uma tarefa de gerenciamento
	 * 
	 * @param schIdManager - Id tarefa de gerenciamento
	 * 
	 * @return java.util.List(Object) - Lista de tarefas de processamento por id
	 * 
	 */
	public java.util.List<Object> autListAllScheduledProcessBySchedManager(String schIdManager){
		try {
			System.out.println("AUT INFO: LIST ALL SCHEDULED PROCESS TASKS");
			
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_SCHEDULE_PROCESS_OPERATIONS.SELECT_SCHEDULE_PROCESS_BY_SCHED_MANAGER.toString(), 
					new Object[] {schIdManager});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: LIST ALL SCHEDULED PROCESS TASKS");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * 
	 * Construtor padrão
	 * 
	 */
	public AUTDBScheduleProcess() {
		// TODO Auto-generated constructor stub
		super();
	}

}
