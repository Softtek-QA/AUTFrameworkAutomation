/**
 * 
 */
package br.stkframework.db.management;

/**
 * Gerenciamento de funções relacionados aos processos de negócio
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBSubProcessFunctions extends AUTDBSubBusinessProcess {
	
	/**
	 * Comandos de gerenciamento de funções
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_BSI_SUBPROCESS_FUNCTIONS_OPERATIONS{
		INSERT_FUNCTION_BY_SUBPROCESS_ID,
		SELECT_FUNCTION_BY_SUBPROCESS_ID,
		SELECT_ALL_FUNCTIONS;
		
		@Override
		public String toString() {
			switch(this) {
			case INSERT_FUNCTION_BY_SUBPROCESS_ID:
			{
				return "INSERT INTO lry.aut_process_config(PROC_ID, APP_ID, FC_GLOBAL_PROCESS_PRIORITY, FC_CATEGORY_PRIORITY, FC_LOAD, FC_INIT_FUNCTION, FC_PROCESS_FUNCTION, FC_END_FUNCTION) VALUES(?,?,?,?,?,?,?,?);";
			}
			case SELECT_ALL_FUNCTIONS:{
				return "SELECT FC_ID FROM lry.aut_process_config;";
			}
			case SELECT_FUNCTION_BY_SUBPROCESS_ID:{
				return "SELECT FC_ID FROM lry.aut_process_config WHERE PROC_ID=?";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	
	public boolean autInsertFunctionBySubProcessId(String prcId,String appId, String funcGlobalPriority, String funcLocalPriority, String funcLoad, String funcInit, String funcProcess,String funcEnd) 
	{
		try {
			
			System.out.println("AUT INFO : INSERT FUNCTION BY SUB PROCESS ID");
			autExecSubStatements(AUT_BSI_SUBPROCESS_FUNCTIONS_OPERATIONS.INSERT_FUNCTION_BY_SUBPROCESS_ID.toString(), 
					new Object[] {prcId,appId,funcGlobalPriority,funcLocalPriority,funcLoad,funcInit,funcProcess,funcEnd});
						
			return true;
		}	
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: INSERT FUNCTION BY SUB PROCESS ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	/**
	 * 
	 * Lista todas as funções relacionadas ao processo de negócio
	 * 
	 * @param idSubProcess - Id do processo de negócio
	 * @return java.util.List(Object) - Lista de funções
	 * 
	 */
	public java.util.List<Object> autListFunctionsBySubProcessId(String idSubProcess){
		try {
			
			System.out.println("AUT INFO : LIST FUNCTIONS BY SUB PROCESS ID");
			java.sql.ResultSet rsData =  autExecSubStatementsWithResult(AUT_SUB_PROCESS_OPERATIONS.SELECT_SUB_PROCESS_BY_BASE_CONFIGURATION.toString(), 
					new Object[] {idSubProcess});
						
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: LIST FUNCTIONS BY SUB PROCESS ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
						
			return null;
		}
	}
	
	
	/**
	 * 
	 * Lista todas as funções relacionadas
	 * 
	 * @return java.util.List(Object) - Lista de sub processo por id
	 */
	public java.util.List<Object> autListAllSubProcessFunctions(){
		try {
			System.out.println("AUT INFO: LIST ALL FUNCTIONS BY ALL BUSINESS PROCESS");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_BSI_SUBPROCESS_FUNCTIONS_OPERATIONS.SELECT_ALL_FUNCTIONS.toString(), 
					new Object[] {});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL FUNCTIONS BY ALL BUSINESS PROCESS");
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
	public AUTDBSubProcessFunctions() {
		super();
	}
}
