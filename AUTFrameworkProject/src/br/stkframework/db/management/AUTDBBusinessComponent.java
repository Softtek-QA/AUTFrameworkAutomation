/**
 * 
 */
package br.stkframework.db.management;

/**
 * 
 * Gerenciamento de componentes de neg�cio
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
	 * Lista todos os componentes de neg�cio com base em uma configura��o base
	 * 
	 * @return java.util.List(Object) - Lista de componentes de neg�cio por ID
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
	 * Lista os componentes de neg�cio relacionados ao item de configura��o base
	 * 
	 * @param idItemConfigBase - Id do item de configura��o base
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
	 * Inclui um  novo componente de neg�cio na base de dados
	 * 
	 * @param configId - Id do item base de configura��o
	 * @param cmpName - Nome do componente de neg�cio
	 * @param cmpDescription - Descri��o do componente de neg�cio
	 * @param cmpDevLanguage - Linguage de programa��o - Implementa��o do componente
	 * @param cmpItem - Arquivo de implementa��o
	 * @param cmpItemKey - Chave do componente de neg�cio
	 * @param cmpInputLocation - Origem padr�o do arquivo de dados
	 * @param cmpOutputLocation - Destino padr�o do componente de neg�cio
	 * @param cmpOperationalState - Estado do sistema operacional
	 * @param execFuncLoad - Fun��o de carregamento
	 * @param execFuncInit - Fun��o de inicializa��o
	 * @param execFuncProcess - Fun��o de processamento
	 * @param execFuncEnd - Fun��o de finaliza��o
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contr�rio
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
	 * Construtor padr�o da classe
	 * 
	 */
	public AUTDBBusinessComponent() {
		super();
	}

}
