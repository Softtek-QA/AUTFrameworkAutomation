/**
 * 
 */
package br.stk.framework.db.management;

/**
 * 
 * Gerenciamento de funções do componente de negócio
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
	 * Inclui uma função no banco de dados para o componente de negócio específico
	 * 
	 * 
	 * @param cmpId - Id do componente de negócio
	 * @param appId - Id do item de configuração da aplicação de gerenciamento da função
	 * @param globalPriority - Prioridade de execuçao global - Em relação aos processos de negócio
	 * @param localPriority - Prioridade local - Em relação as várias funções cadastradas para o componente de negócio
	 * @param fctionLoad - Função de carregamento
	 * @param fctionInit - Função de inicialização
	 * @param fcProcess - Função de processamento
	 * @param fcEnd - Função de finalização
	 * 
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
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
	 * Lista todas as funções relacionadas ao componente de negócio específico
	 * 
	 * 
	 * @param idComponent - Id do componente de negócio
	 * 
	 * @return java.util.List(Object) - Lista de funções relacionadas ao componente de negócio
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
	 * Lista todas as funções relacionadas ao componente de negócio
	 * 
	 * 
	 * @return java.util.List(Object) - Lista todas as funções de negócio cadastradas no sistema
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
	 * Construtor padrão da classe
	 * 
	 */
	public AUTDBBusinessComponentFunctions() {
		// TODO Auto-generated constructor stub
	}

}
