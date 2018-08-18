/**
 * 
 */
package br.stkframework.db.management;

/**
 * 
 * Gerenciamento de sub rotinas associadas a uma library
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBBSIRSCLibrariesFunctions extends AUTDBBSIRSCLibraries {
	/**
	 * 
	 * Defini��o de comandos para o gerenciamento 
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_LIB_FUNCTIONS_OPERATIONS{
		INSERT_FUNCTION_BY_LIBRARY_ID,
		SELECT_ALL_FUNCTIONS,
		SELECT_FUNCTION_BY_LIBRARY_ID;
		
		@Override
		public String toString() {
			switch(this) {
			case INSERT_FUNCTION_BY_LIBRARY_ID:{
				return "INSERT INTO lry.aut_library_config(LIB_ID, FC_GLOBAL_PROCESS_PRIORITY, FC_CATEGORY_PRIORITY, FC_LOAD, FC_INIT_FUNCTION, FC_PROCESS_FUNCTION, FC_END_FUNCTION, APP_ID) VALUES(?,?,?,?,?,?,?,?);";
			}
			case SELECT_ALL_FUNCTIONS:{
				return "SELECT fc_id FROM lry.aut_library_config;";
			}
			case SELECT_FUNCTION_BY_LIBRARY_ID:{
				return "SELECT FC_ID FROM lry.aut_library_config WHERE LIB_ID=?";
			}
			default:{
				return this.name();
			}
			}			
		}
	}
	
	/**
	 * 
	 * Lista todas as fun��es cadastradas no sistema para todas as bibliotecas cadastradas no sistema
	 * 
	 * @return java.util.List - Lista de fun��es cadastradas no sistema 
	 * 
	 */
	public java.util.List<Object> autListAllFunctions(){
		try {
			
			System.out.println("AUT INFO: LIST ALL FUNCTION FOR ALL LIBRARIES");			
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_LIB_FUNCTIONS_OPERATIONS.SELECT_ALL_FUNCTIONS.toString(), 
					new Object[] {});
						
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: LIST ALL FUNCTIONS FOR ALL LIBRARIES");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	/**
	 * 
	 * Lista todas as fun��es associadas a uma biblioteca
	 * 
	 * 
	 * @param idLibrary - Id da biblioteca
	 * 
	 * @return java.util.List - Lista de fun��es associadas a biblioteca - por id
	 * 
	 */
	public java.util.List<Object> autListFunctionsByIdLib(String idLibrary){
		try {
			
			System.out.println("AUT INFO : LIST ALL LIBRARY FUNCTIONS");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_LIB_FUNCTIONS_OPERATIONS.SELECT_FUNCTION_BY_LIBRARY_ID.toString(), 
					new Object[] {idLibrary});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: LIST ALL LIBRARY FUNCTIONS");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}

	/**
	 * 
	 * Inseri fun��o associada a uma biblioteca de neg�cio
	 * 
	 * 
	 * @param libId - Id da biblioteca de neg�cio
	 * @param priorGlobalExec - Prioridade global de execu��o - Usada em fun��es de configura��o a n�vel de processos de neg�cio 
	 * @param priorLocalExecPorItemConfig - Prioridade local - Usada em fun��es a n�vel de componente de neg�cio
	 * @param funcLoader - Fun��es de carregamento
	 * @param funcInit - Fun��es de inicializa��o
	 * @param funcProcess - Fun��es de processamento - principal
	 * @param funcEnd - Fun��es de finaliza��o
	 * @param appIdManipulation - Item de configura��o do aplicativo de execu��o
	 * 
	 * @return boolean - True caso o processo seja finalizado  com sucesso false caso constr�rio
	 * 
	 */
	public boolean autInsertFunctionByLibrary(String libId, String priorGlobalExec, String priorLocalExecPorItemConfig, String funcLoader, String funcInit, String funcProcess, String funcEnd, String appIdManipulation) {
		try {
			
			System.out.println("AUT INFO:  INSERT FUNCTION FOR LIBRARY");
			
			autExecSubStatements(AUT_LIB_FUNCTIONS_OPERATIONS.INSERT_FUNCTION_BY_LIBRARY_ID.toString(), 
					new Object[] {libId,priorGlobalExec,priorLocalExecPorItemConfig,funcLoader,funcInit,funcProcess,funcEnd,appIdManipulation});
			
			System.out.println("AUT INFO: INSERT FUNCTION FOR LIBRARY: FINISHED");
			
			return true;
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: INSERT FUNCTION FOR LIBRARY");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	/**
	 * 
	 * Construtor padr�o da classe
	 * 
	 */
	public AUTDBBSIRSCLibrariesFunctions() {
		// TODO Auto-generated constructor stub
		super();
	}

}
