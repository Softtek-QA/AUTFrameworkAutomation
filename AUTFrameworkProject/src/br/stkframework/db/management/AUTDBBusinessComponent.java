/**
 * 
 */
package br.stkframework.db.management;

/**
 * 
 * Gerenciamento de componentes de negócio
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBBusinessComponent extends AUTDBBSIBaseConfiguration {
	
	/**
	 * 
	 * Comandos para gerenciamento de componentes
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_BSI_COMPONENT_OPERATIONS{
		INSERT_BSI_COMPONENT,
		SELECT_ALL_BSI_COMPONENT,
		SELECT_BSI_COMPONENT_BY_CONFIG_BASE;
		
		@Override
		public String toString() {
			switch(this) {
			case INSERT_BSI_COMPONENT:{
				return "INSERT INTO lry.aut_bsi_component(CFG_ID, CMP_NAME, CMP_DESCRIPTION, CMP_DEVELOP_LANGUAGE, CMP_ITEM, CMP_ITEM_KEY, CMP_LOCATION_INPUT, CMP_LOCATION_OUTPUT, CMP_STATE_OPERATIONAL, CMP_EXEC_FUNC_LOAD, CMP_EXEC_FUNC_INIT, CMP_EXEC_FUNC_PROCESS, CMP_EXEC_FUNC_END) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
			}
			case SELECT_ALL_BSI_COMPONENT:{
				return "SELECT CMP_ID FROM lry.aut_bsi_component;";
			}
			case SELECT_BSI_COMPONENT_BY_CONFIG_BASE:{
				return "SELECT CMP_ID FROM lry.aut_bsi_component WHERE cfg_id=?";
			}
			default:{
				return this.name();
			}
			}
		}
	}	
	
	
	
	/**
	 * 
	 * Lista todos os componentes de negócio com base em uma configuração base
	 * 
	 * @return java.util.List(Object) - Lista de componentes de negócio por ID
	 * 
	 */
	public java.util.List<Object> autListAllBusinessComponents(){
		try {
			System.out.println("AUT INFO : LIST ALL BUSINESS COMPONENT");
			
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_BSI_COMPONENT_OPERATIONS.SELECT_ALL_BSI_COMPONENT.toString(), 
					new Object[] {});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL BUSINESS COMPONENT");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	/**
	 * 
	 * Lista os componentes de negócio relacionados ao item de configuração base
	 * 
	 * @param idItemConfigBase - Id do item de configuração base
	 * 
	 * @return java.util.List(Object) - Lista de componentes por ID de componente
	 * 
	 */
	public java.util.List<Object> autListBusinessComponentByConfigBase(String idItemConfigBase){
		try {
			System.out.println("AUT INFO: LIST BUSINESS COMPONENT BY CONFIGURATION BASE");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_BSI_COMPONENT_OPERATIONS.SELECT_BSI_COMPONENT_BY_CONFIG_BASE.toString(), 
					new Object[] {idItemConfigBase});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST BUSINESS COMPONENT BY CONFIGURATION BASE");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	/**
	 * 
	 * Inclui um  novo componente de negócio na base de dados
	 * 
	 * @param configId - Id do item base de configuração
	 * @param cmpName - Nome do componente de negócio
	 * @param cmpDescription - Descrição do componente de negócio
	 * @param cmpDevLanguage - Linguage de programação - Implementação do componente
	 * @param cmpItem - Arquivo de implementação
	 * @param cmpItemKey - Chave do componente de negócio
	 * @param cmpInputLocation - Origem padrão do arquivo de dados
	 * @param cmpOutputLocation - Destino padrão do componente de negócio
	 * @param cmpOperationalState - Estado do sistema operacional
	 * @param execFuncLoad - Função de carregamento
	 * @param execFuncInit - Função de inicialização
	 * @param execFuncProcess - Função de processamento
	 * @param execFuncEnd - Função de finalização
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public boolean autInsertBSIComponent(String configId,String cmpName,String cmpDescription,String cmpDevLanguage, String cmpItem, String cmpItemKey, String cmpInputLocation, String cmpOutputLocation, boolean cmpOperationalState,boolean execFuncLoad,boolean execFuncInit,boolean execFuncProcess,boolean execFuncEnd) {
		try {
			
			System.out.println("AUT INFO: INSERT BUSINESS COMPONENT BY CONFIGURATION ID");
			autExecSubStatements(AUT_BSI_COMPONENT_OPERATIONS.INSERT_BSI_COMPONENT.toString(), 
					new Object[] {configId,cmpName,cmpDescription,cmpDevLanguage,cmpItem,cmpItemKey,cmpInputLocation,cmpOutputLocation,cmpOperationalState,execFuncLoad,execFuncInit,execFuncProcess,execFuncEnd});
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: INSERT BUSINESS COMPONENT BY CONFIGURATION ID");
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
	public AUTDBBusinessComponent() {
		super();
	}

}
