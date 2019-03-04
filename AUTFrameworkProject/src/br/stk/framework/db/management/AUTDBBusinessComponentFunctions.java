/**
 * 
 */
package br.stk.framework.db.management;

/**
 * 
 * Gerenciamento de fun��es do componente de neg�cio
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBBusinessComponentFunctions extends AUTDBBusinessComponent {
	
	
	/**
	 * Comandos para o gerenciamento de componentes
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_BSI_COMPONENT_FUNCTIONS{
		INSERT_BSI_CMP_FUNCTION,
		SELECT_ALL_BSI_CMP_FUNCTIONS,
		SELECT_BSI_CMP_FUNCTION_BY_CMP_ID;
		
		@Override
		public String toString() {
			switch(this) {
			case INSERT_BSI_CMP_FUNCTION:{
				return "INSERT INTO lry.aut_bsi_component_config(CMP_ID, FC_GLOBAL_PROCESS_PRIORITY, FC_CATEGORY_PRIORITY, FC_EXEC_FUNC_LOAD, FC_EXEC_FUNC_INIT, FC_EXEC_FUNC_PROCESS, FC_EXEC_FUNC_END,APP_ID) VALUES(?,?,?,?,?,?,?,?);";
			}
			case SELECT_ALL_BSI_CMP_FUNCTIONS:{
				return "SELECT FC_ID FROM lry.aut_bsi_component_config;";
			}
			case SELECT_BSI_CMP_FUNCTION_BY_CMP_ID:{
				return "SELECT FC_ID FROM lry.aut_bsi_component_config WHERE CMP_ID=?";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	
	
	/**
	 * 
	 * Inclui uma fun��o no banco de dados para o componente de neg�cio espec�fico
	 * 
	 * 
	 * @param cmpId - Id do componente de neg�cio
	 * @param appId - Id do item de configura��o da aplica��o de gerenciamento da fun��o
	 * @param globalPriority - Prioridade de execu�ao global - Em rela��o aos processos de neg�cio
	 * @param localPriority - Prioridade local - Em rela��o as v�rias fun��es cadastradas para o componente de neg�cio
	 * @param fctionLoad - Fun��o de carregamento
	 * @param fctionInit - Fun��o de inicializa��o
	 * @param fcProcess - Fun��o de processamento
	 * @param fcEnd - Fun��o de finaliza��o
	 * 
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contr�rio
	 * 
	 * 
	 */
	public boolean autInsertFunctionByBSIComponent(String cmpId, String appId,String globalPriority, String localPriority, String fctionLoad, String fctionInit, String fcProcess, String fcEnd) {
		try {
			
			System.out.println("AUT INFO : INSERT FUNCTION BY BSI COMPONENT");
			autExecSubStatements(AUT_BSI_COMPONENT_FUNCTIONS.INSERT_BSI_CMP_FUNCTION.toString(), 
					new Object[] {cmpId,globalPriority,localPriority,fctionLoad,fctionInit,fcProcess,fcEnd,appId});			
			
			return true;
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: INSERT FUNCTION BY BSI COMPONENT");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
		
	}
	
	/**
	 * 
	 * Lista todas as fun��es relacionadas ao componente de neg�cio espec�fico
	 * 
	 * 
	 * @param idComponent - Id do componente de neg�cio
	 * 
	 * @return java.util.List(Object) - Lista de fun��es relacionadas ao componente de neg�cio
	 *
	 */
	public java.util.List<Object> autListFunctionsByBSIComponent(String idComponent){
		try {
			System.out.println("AUT INFO : LIST FUNCTIONS BY BSI COMPONENT");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_BSI_COMPONENT_FUNCTIONS.SELECT_BSI_CMP_FUNCTION_BY_CMP_ID.toString(), 
					new Object[] {idComponent});
						
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR : LIST FUNCTIONS BY BSI COMPONENT");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * 
	 * Lista todas as fun��es relacionadas ao componente de neg�cio
	 * 
	 * 
	 * @return java.util.List(Object) - Lista todas as fun��es de neg�cio cadastradas no sistema
	 * 
	 */
	public java.util.List<Object> autListAllFunctionsBusinessComponent(){
		try {
			
			System.out.println("AUT INFO: LIST ALL BUSINESS PROCESS BY BSI COMPONENT ID");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_BSI_COMPONENT_FUNCTIONS.SELECT_ALL_BSI_CMP_FUNCTIONS.toString(), 
					new Object[] {});
						
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: LIST ALL FUNCTIONS BY BSI COMPONENT ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	/**
	 * 
	 * Construtor padr�o da classe
	 * 
	 */
	public AUTDBBusinessComponentFunctions() {
		// TODO Auto-generated constructor stub
	}

}
