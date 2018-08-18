/**
 * 
 */
package br.stkframework.db.management;

/**
 * Gerenciamento de agenda de tarefas para componentes
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBScheduleTaskManager extends AUTDBTasksComponents {	
	/**
	 * 
	 * Gerenciamento de tarefas
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_TASK_SCHEDULE_OPERATIONS{
		INSERT_SCHEDULE_BY_TASK_ID,
		SELECT_SCHEDULE_BY_TASK_ID,
		SELECT_ALL_SCHEDULE;
		
		@Override
		public String toString() {
			switch(this) {
			case INSERT_SCHEDULE_BY_TASK_ID:{
				return "INSERT INTO lry.aut_tsk_schedule(TSK_ID, SCH_NAME, SCH_DESCRIPTION, SCH_STATE_INIT, SCH_STATE_PROCESS, SCH_STATE_END) VALUES(?,?,?,?,?,?);";
			}
			case SELECT_ALL_SCHEDULE:{
				return "SELECT SCH_ID FROM lry.aut_tsk_schedule;";
			}
			case SELECT_SCHEDULE_BY_TASK_ID:{
				return "SELECT SCH_ID FROM lry.aut_tsk_schedule WHERE TSK_ID=?;";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	/**
	 * 
	 * Inclui uma nova tarefa agendada no banco de dados relacionada ao componente de negócio
	 * @param idTarefaComponente - Id da tarefa associada
	 * @param nameSchedTask - Nome descritivo para o agendamento
	 * @param schedDescription - Descrição da tarefa
	 * @param stateSchedInit - Tarefa de inicialização
	 * @param stateSchedProcess - Tarefa de processamento
	 * @param stateSchedEnd - Tarefa de finalização
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public boolean autInsertScheduledByTaskId(String idTarefaComponente, String nameSchedTask, String schedDescription, String stateSchedInit, String stateSchedProcess, String stateSchedEnd) {
		try {
			System.out.println("AUT INFO: INSERT SCHEDULE TASK MANAGER BY TASK ID COMPONENT");
			autExecSubStatements(AUT_TASK_SCHEDULE_OPERATIONS.INSERT_SCHEDULE_BY_TASK_ID.toString(), 
					new Object[] {idTarefaComponente, nameSchedTask, schedDescription, stateSchedInit, stateSchedProcess, stateSchedEnd});
			
			return true;
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: INSERT SCHEDULE TASK MANAGER BY TASK ID COMPONENTE");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	
	/**
	 * Lista todas as tarefas agendadas por id de tarefa do componente proprietário
	 * 
	 * 
	 * @param taskId - Id de tarefa do componente
	 * 
	 * @return java.util.List(Object) - Lista de tarefas agendadas por id de tarefa do componente
	 * 
	 */
	public java.util.List<Object> autListScheduledByTaskId(String taskId){
		try {
			System.out.println("AUT INFO: LIST ALL SCHEDULE BY TASK ID");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_TASK_SCHEDULE_OPERATIONS.SELECT_SCHEDULE_BY_TASK_ID.toString(), 
					new Object[] {taskId});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL SCHEDULE BY TASK ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			
			return null;
		}
	}
	/**
	 * 
	 * Lista todas as tarefas agendadas do sistema
	 * 
	 * @return java.util.List(Object) - Lista de tarefas agendadas
	 * 
	 */
	public java.util.List<Object> autListAllScheduledTasks(){
		try {
			System.out.println("AUT INFO: LIST ALL SCHEDULED TASKS");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_TASK_SCHEDULE_OPERATIONS.SELECT_ALL_SCHEDULE.toString(),
					new Object[] {});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL SCHEDULED TASKS");
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
	public AUTDBScheduleTaskManager() {
		// TODO Auto-generated constructor stub
		super();
	}

}
