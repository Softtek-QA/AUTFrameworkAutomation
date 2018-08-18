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
	 * Definição de comandos para o gerenciamento 
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
	 * Lista todas as funções cadastradas no sistema para todas as bibliotecas cadastradas no sistema
	 * 
	 * @return java.util.List - Lista de funções cadastradas no sistema 
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
	 * Lista todas as funções associadas a uma biblioteca
	 * 
	 * 
	 * @param idLibrary - Id da biblioteca
	 * 
	 * @return java.util.List - Lista de funções associadas a biblioteca - por id
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
	 * Inseri função associada a uma biblioteca de negócio
	 * 
	 * 
	 * @param libId - Id da biblioteca de negócio
	 * @param priorGlobalExec - Prioridade global de execução - Usada em funções de configuração a nível de processos de negócio 
	 * @param priorLocalExecPorItemConfig - Prioridade local - Usada em funções a nível de componente de negócio
	 * @param funcLoader - Funções de carregamento
	 * @param funcInit - Funções de inicialização
	 * @param funcProcess - Funções de processamento - principal
	 * @param funcEnd - Funções de finalização
	 * @param appIdManipulation - Item de configuração do aplicativo de execução
	 * 
	 * @return boolean - True caso o processo seja finalizado  com sucesso false caso constrário
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
	 * Construtor padrão da classe
	 * 
	 */
	public AUTDBBSIRSCLibrariesFunctions() {
		// TODO Auto-generated constructor stub
		super();
	}

}
