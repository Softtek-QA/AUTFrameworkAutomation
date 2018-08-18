/**
 * 
 */
package br.stkframework.db.management;

/**
 * 
 * Gerenciamento de agendamento de tarefas voltadas para processos de inicializa��o:
 * FUN��ES - CARREGAMENTO E INICIALIZA��O
 *  
 * @author Softtek-QA
 *
 */
public class AUTDBScheduleInit extends AUTDBScheduleTaskManager{
	
	/**
	 * 
	 * Comandos para o gerenciamento de tarefas de inicializa��o
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_SCHEDULE_INIT_OPERATIONS{
		INSERT_SCHEDULE_INIT_BY_SCHED_MANAGER,
		SELECT_SCHEDULE_BY_SCHED_MANAGER,
		SELECT_ALL_SCHEDULE;
		@Override
		public String toString() {
			switch(this) {
			case INSERT_SCHEDULE_INIT_BY_SCHED_MANAGER:{
				return "INSERT INTO lry.aut_sch_exec_init(SCH_ID, EXC_STATE, EXC_STATE_OBJECT, EXC_STATE_DESCRIPTION) VALUES(?,?,?,?);";
			}
			case SELECT_ALL_SCHEDULE:{
				return "SELECT EXC_ID FROM lry.aut_sch_exec_init;";
			}
			case SELECT_SCHEDULE_BY_SCHED_MANAGER:{
				return "SELECT EXC_ID FROM lry.aut_sch_exec_init WHERE SCH_ID=?;";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	/**
	 * 
	 * Inclui agenda uma nova tarefa de inicializa��o para uma tarefa de gerenciamento do componente de neg�cio
	 * 
	 * @param schId - Id da tarefa de gerenciamento
	 * @param stateExecution - Status da execu��o
	 * @param stateObject - Status da execu��o - Objeto
	 * @param stateDescription - Descri��o de status da execu��o
	 * 
	 * @return boolean - True caso processo de agendamento da tarefa de inicializa��o seja conclu�do com sucesso false caso constr�rio
	 * 
	 */
	public boolean autInsertScheduledInitBySchedManager(String schId, String stateExecution, String stateObject, String stateDescription) {
		try {
			
			System.out.println("AUT INFO: INSERT SCHEDULED TASK BY SCHED MANAGER");
			autExecSubStatements(AUT_SCHEDULE_INIT_OPERATIONS.INSERT_SCHEDULE_INIT_BY_SCHED_MANAGER.toString(), 
					new Object[] {schId,stateExecution,stateObject,stateDescription});
			
			return true;
		}
		catch(java.lang.Exception e) {			
			System.out.println("AUT ERROR: INSERT SCHEDULED TASK BY SCHED MANAGER");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	/**
	 * 
	 * Lista todas as tarefas de inicializa��o registradas no sistema
	 * 
	 * @return java.util.List(Object) - Lista de tarefas agendadas no sistema por id
	 * 
	 */
	public java.util.List<Object> autListAllScheduledInit(){
		try {
			System.out.println("AUT INFO: LIST ALL TASKS INIT FOR ALL TASKS MANAGER");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_SCHEDULE_INIT_OPERATIONS.SELECT_ALL_SCHEDULE.toString(), 
					new Object[] {});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL TASKS INIT FOR ALL TASKS MANAGER");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * 
	 * Lista todas as tarefas de inicializa��o agendas para uma tarefa de gerenciamento
	 * 
	 * @param schIdManager - Id tarefa de gerenciamento
	 * 
	 * @return java.util.List(Object) - Lista de tarefas de inicializa��o por id
	 * 
	 */
	public java.util.List<Object> autListAllScheduledInitBySchedManager(String schIdManager){
		try {
			System.out.println("AUT INFO: LIST ALL SCHEDULED INIT TASKS");
			
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_SCHEDULE_INIT_OPERATIONS.SELECT_SCHEDULE_BY_SCHED_MANAGER.toString(), 
					new Object[] {schIdManager});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: LIST ALL SCHEDULED INIT TASKS");
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
	public AUTDBScheduleInit() {
		// TODO Auto-generated constructor stub
		super();
	}

}
