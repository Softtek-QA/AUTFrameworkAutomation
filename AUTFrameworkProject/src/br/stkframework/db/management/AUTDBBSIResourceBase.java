/**
 * 
 */
package br.stkframework.db.management;

import br.stk.framework.db.connectors.AUTDBUtils.AUTBytesManipulation;
import br.stkframework.db.management.AUTDBResourcesConfiguration.AUT_RESOURCES_OPERATIONS;
/**
 * 
 * Gerenciamento de recursos b�sicos do processo de neg�cio
 * 
 * @author Softtek-QA
 * 
 *
 */
public class AUTDBBSIResourceBase extends AUTDBBSIBaseConfiguration {
	
	/**
	 * 
	 * Define os tipos de opera��es b�sicas para gerenciar os recursos associados a cada item de configura��o base associado ao processo
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_RESOURCES_BASE_BSI{		
		INSERT_BSI_RESOURCES_BASE,
		SELECT_ALL_BSI_RESOURCES_BASE,
		SELECT_BSI_RESOURCES_BASE_BY_CFG_BASE_ID;
		
		public String toString() {
			// TODO Auto-generated method stub
			switch(this) {
			case INSERT_BSI_RESOURCES_BASE:{
				return "INSERT INTO lry.aut_bsi_prc_cfg_resources(CFG_ID, RSC_NAME, RSC_DESCRIPTION, RSC_LOCATION_INPUT, RSC_LOCATION_OUTPUT, RSC_ITEM, RSC_ITEM_KEY) VALUES(?,?,?,?,?,?,?);";
			}
			case SELECT_ALL_BSI_RESOURCES_BASE:{
				return "SELECT RSC_ID FROM lry.aut_bsi_prc_cfg_resources;";
			}
			case SELECT_BSI_RESOURCES_BASE_BY_CFG_BASE_ID:{
				return "SELECT RSC_ID FROM lry.aut_bsi_prc_cfg_resources WHERE CFG_ID=?;";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	/**
	 * 
	 * Inclui um novo recurso na base de dados do sistema
	 * 
	 * @param idConfiguration - Id do item de configura��o associado ao processo de neg�cio
	 * @param rscName - Nome abreviado do recurso
	 * @param rscDesription - Descri��o detalhada do recurso
	 * @param rscLocationInput - Local de origem do recurso
	 * @param rscLocationOutput - Local de despejo-sa�da-download do arquivo de dados
	 * @param rscItem - C�digo bin�rio do item - em bytes
	 * @param rscTypeItem - Tipo do recurso 
	 * 
	 * @return booleano - True caso o processo seja finalizado com sucesso false caso contr�rio
	 * 
	 * 
	 */
	public boolean autInsertResourceBaseByConfigBSId(String idConfiguration, String rscName, String rscDesription, String rscLocationInput,String rscLocationOutput, String rscItem, String rscTypeItem) {
		try {
			System.out.println("AUT INFO : INSERT RESOURCE BASE BY CONFIGURATION FOR PROCESS BSI");
			java.util.HashMap<String,Object> fileInfo = autGetFileInfo(rscItem);
			
			autExecSubStatements(AUT_RESOURCES_BASE_BSI.INSERT_BSI_RESOURCES_BASE.toString(), 
					new Object[] {idConfiguration,rscName,rscDesription,rscLocationInput,rscLocationOutput,rscItem,rscTypeItem});
			System.out.println("AUT INFO : INSERT RESOURCE BASE BY CONFIGURATION : FINISHED");
			
			return true;
		}
		catch(java.lang.Exception e) {
			
			
			return false;
		}
	}	
	/**
	 * 
	 * Lista todos os recursos associados a um item de configura��o
	 * 
	 * @param idConfigBSIBase - Id do item de configura��o base associado a um processo de neg�cio
	 * 
	 * @return java.util.List - Lista de recursos associados a um item de configura��o
	 *
	 */
	public java.util.List<Object> autListResourceBaseByConfigBSId(String idConfigBSIBase){
		try {
			
			System.out.println("AUT INFO : LIST RESOURCES BASE BY CONFIGURATION BASE ID");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_RESOURCES_BASE_BSI.SELECT_BSI_RESOURCES_BASE_BY_CFG_BASE_ID.toString(), 
					new Object[] {});
			
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR : LIST RESOURCES BY CONFIGURATION BASE ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	/**
	 * 
	 * Lista todos os recursos associados a um item de configura��o do processo
	 * 
	 * @return java.util.List - List de recursos associados ao item de configura��o base
	 * 
	 */
	public java.util.List<Object> autListResourceBaseByConfigBSALL(){
		try {
			
			System.out.println("AUT INFO : LIST RESOURCES BASE FOR CONFIGURATION BASE OF PROCESS BSI");
			
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_RESOURCES_BASE_BSI.SELECT_ALL_BSI_RESOURCES_BASE.toString(), 
					new Object[] {});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST RESOURCES BASE FOR CONFIGURATION BASE OF PROCESS BSI");
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
	public AUTDBBSIResourceBase() {
		// TODO Auto-generated constructor stub
	}

}
