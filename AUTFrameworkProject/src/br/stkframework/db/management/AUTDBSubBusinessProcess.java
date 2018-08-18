/**
 * 
 */
package br.stkframework.db.management;

/**
 * 
 * Gerenciamento de sub processos de negócio
 * 
 * @author Softtek-QA
 * 
 *
 */
public class AUTDBSubBusinessProcess extends AUTDBBSIBaseConfiguration {

	/**
	 * 
	 * Comandos de gerenciamento de sub processos
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_SUB_PROCESS_OPERATIONS{
		INSERT_SUB_PROCESS_BY_BASE_CONFIGURATION,
		SELECT_ALL_SUB_PROCESS,
		SELECT_SUB_PROCESS_BY_BASE_CONFIGURATION;
		
		@Override
		public String toString() {
			switch(this) {
			case INSERT_SUB_PROCESS_BY_BASE_CONFIGURATION:
			{
				return "INSERT INTO lry.aut_bsi_process(CFG_ID, PROC_NAME, PROC_DESCRIPTION, PROC_DEVELOP_LANGUAGE, PROC_ITEM, PROC_ITEM_KEY, PROC_LOCATION_INPUT, PROC_LOCATION_OUTPUT, PROC_EXEC_FUNCTION_LOAD, PROC_EXEC_FUNCTION_INIT, PROC_EXEC_FUNCTION_PROCESS, PROC_EXEC_FUNCTION_END, PROC_STATE_OPERATIONAL) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
			}
			case SELECT_ALL_SUB_PROCESS:{
				return "SELECT PROC_ID FROM lry.aut_bsi_process;";
			}
			case SELECT_SUB_PROCESS_BY_BASE_CONFIGURATION:{
				
				return "SELECT	PROC_ID FROM lry.aut_bsi_process WHERE CFG_ID=?;";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	

	/**
	 * 
	 * 
	 * Inclui um novo sub processo na base de dados do sistema
	 * 
	 * 
	 * @param cfgId - Id do item de configuração base
	 * @param prcName - Nome do processo de negócio
	 * @param prc_description - Descrição do processo de negócio
	 * @param prcImpLanguage - Linguagem de implementação do módulo
	 * @param rscItem - Item para upload no banco de dados
	 * @param itemKey - Chave do item principal
	 * @param locationInput - Local de entrada padrão
	 * @param locationOutput - Local de saída padrão
	 * @param execFuncLoad - Função de carregamento
	 * @param execFuncInit - Função de inicialização
	 * @param execFuncProcess - Função de processamento
	 * @param execFuncEnd - Função de finalização
	 * @param stateOperational - Status operacional do componente
	 * 
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public boolean autInsertSubProcessByIdConfigurationBase(String cfgId, String prcName, String prc_description, String prcImpLanguage, String rscItem, String itemKey, String locationInput, String locationOutput,boolean execFuncLoad, boolean execFuncInit, boolean execFuncProcess, boolean execFuncEnd, boolean stateOperational) {
		try {
			
			System.out.println("AUT INFO: INSERT SUB PROCESS BY CONFIGURATION BASE");
			autExecSubStatements(AUT_SUB_PROCESS_OPERATIONS.INSERT_SUB_PROCESS_BY_BASE_CONFIGURATION.toString(), 
					new Object[] {cfgId,prcName,prc_description,prcImpLanguage,rscItem,itemKey,locationInput,locationOutput,execFuncLoad,execFuncInit,execFuncProcess,execFuncEnd,stateOperational});
			
			return true;
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: INSERT SUB PROCESS BY CONFIGURATION BASE");
			System.out.println(e.getMessage());
			e.printStackTrace();
					
			return false;
		}
	}
	
	
	/**
	 * LISTA TODOS OS SUB PROCESSOS CADASTRADOS NO SISTEMA
	 * 
	 * 
	 * @return java.util.List(Object) - Lista de Sub Processos por id
	 * 
	 */
	public java.util.List<Object> autListAllSubProcess(){
		try {
			System.out.println("AUT INFO: LIST ALL SUB PROCESS FOR ALL CONFIGURATION BASE");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_SUB_PROCESS_OPERATIONS.SELECT_ALL_SUB_PROCESS.toString(), 
					new Object[] {});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: LIST ALL SUB PROCESSO FOR ALL CONFIGURATION BASE");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			
			return null;
		}
		}
	
	
	/**
	 *
	 * Retorna uma lista com todos os sub processos relacionados ao item de configuração base
	 * 
	 * @param idConfigBase - Id do item de configuração base
	 * @return java.util.List(Object) - Lista de sub processos cadastrados no sistema
	 * 
	 */
	public java.util.List<Object> autListSubProcessByIdConfigBase(String idConfigBase){
		try {
			
			System.out.println("AUT INFO: LIST ALL SUB PROCESS BY ALL CONFIGURATION BASE");			
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_SUB_PROCESS_OPERATIONS.SELECT_SUB_PROCESS_BY_BASE_CONFIGURATION.toString(), 
					new Object[] {idConfigBase});
			
			return autGetListItems(rsData);
			
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: LIST ALL SUB PROCESS BY ALL CONFIGURATION BASE");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
			
		}
	}
	
	/**
	 * 
	 * 
	 * Construtor padrão da classe
	 * 
	 * 
	 */
	public AUTDBSubBusinessProcess() {
		super();
	}

}
