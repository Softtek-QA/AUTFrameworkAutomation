/**
 * 
 */
package br.stkframework.db.management;

/**
 * 
 * Gerenciamento de agendamento de tarefas voltadas para processos de finalização:
 * FUNÇÕES - FINALIZAÇÃO
 *  
 * @author Softtek-QA
 *
 */
public class AUTDBScheduleEnd extends AUTDBScheduleTaskManager{
	
	/**
	 * 
	 * Comandos para o gerenciamento de tarefas de finalização
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_SCHEDULE_END_OPERATIONS{
		INSERT_SCHEDULE_END_BY_SCHED_MANAGER,
		SELECT_SCHEDULE_END_SCHED_MANAGER,
		SELECT_ALL_END_SCHEDULE;
		@Override
		public String toString() {
			switch(this) {
			case INSERT_SCHEDULE_END_BY_SCHED_MANAGER:{
				return "INSERT INTO lry.aut_sch_exec_end(SCH_ID, EXC_STATE, EXC_STATE_OBJECT, EXC_STATE_DESCRIPTION) VALUES(?,?,?,?);";
			}
			case SELECT_ALL_END_SCHEDULE:{
				return "SELECT EXC_ID FROM lry.aut_sch_exec_end;";
			}
			case SELECT_SCHEDULE_END_SCHED_MANAGER:{
				return "SELECT EXC_ID FROM lry.aut_sch_exec_end WHERE SCH_ID=?;";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	/**
	 * 
	 * Inclui agenda uma nova tarefa de finalização para uma tarefa de gerenciamento do componente de negócio
	 * 
	 * @param schId - Id da tarefa de gerenciamento
	 * @param stateExecution - Status da execução
	 * @param stateObject - Status da execução - Objeto
	 * @param stateDescription - Descrição de status da execução
	 * 
	 * @return boolean - True caso processo de agendamento da tarefa de finalização seja concluído com sucesso false caso constrário
	 * 
	 */
	public boolean autInsertScheduledEndBySchedManager(String schId, String stateExecution, String stateObject, String stateDescription) {
		try {
			
			System.out.println("AUT INFO: INSERT SCHEDULE END TASK BY SCHED MANAGER");
			autExecSubStatements(AUT_SCHEDULE_END_OPERATIONS.INSERT_SCHEDULE_END_BY_SCHED_MANAGER.toString(), 
					new Object[] {schId,stateExecution,stateObject,stateDescription});
			
			return true;
		}
		catch(java.lang.Exception e) {			
			System.out.println("AUT ERROR: INSERT SCHEDULE END TASK BY SCHED MANAGER");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	
	/**
	 * 
	 * Lista todas as tarefas de finalização registradas no sistema
	 * 
	 * @return java.util.List(Object) - Lista de tarefas agendadas no sistema por id
	 * 
	 */
	public java.util.List<Object> autListAllScheduledEnd(){
		try {
			System.out.println("AUT INFO: LIST ALL SCHEDULED END FOR ALL TASKS MANAGER");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_SCHEDULE_END_OPERATIONS.SELECT_ALL_END_SCHEDULE.toString(), 
					new Object[] {});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL SCHEDULE END FOR ALL TASKS MANAGER");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * 
	 * Lista todas as tarefas de finalização agendas para uma tarefa de gerenciamento
	 * 
	 * @param schIdManager - Id tarefa de gerenciamento
	 * 
	 * @return java.util.List(Object) - Lista de tarefas de inicialização por id
	 * 
	 */
	public java.util.List<Object> autListAllScheduledEndBySchedManager(String schIdManager){
		try {
			System.out.println("AUT INFO: LIST ALL SCHEDULED END TASKS");
			
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_SCHEDULE_END_OPERATIONS.SELECT_SCHEDULE_END_SCHED_MANAGER.toString(), 
					new Object[] {schIdManager});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: LIST ALL SCHEDULED END TASKS");
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
	public AUTDBScheduleEnd() {
		// TODO Auto-generated constructor stub
		super();
	}

}
