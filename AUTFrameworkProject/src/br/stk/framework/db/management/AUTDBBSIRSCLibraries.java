/**
 * 
 */
package br.stk.framework.db.management;

/**
 * 
 * Gerenciamento de bibliotecas utilizadas pelos componentes em processos de negócio diversos
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBBSIRSCLibraries extends AUTDBBSIBaseConfiguration {
	/**
	 * 
	 * Comandos para o gerenciamento de bibliotecas compartilhadas entre os componentes do sistema
	 * 
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_LIBRARIES_OPERATIONS{
		INSERT_LIBRARY_RESOURCE,
		SELECT_LIBRARY_BY_ID_CONFIGURATION,
		SELECT_ALL_LIBRARIES;
		@Override
		public String toString() {
			switch(this) {
			case INSERT_LIBRARY_RESOURCE:{
				return "INSERT INTO lry.aut_bsi_prc_cfg_library(CFG_ID, LIB_NAME, LIB_DESCRIPTION, LIB_DEVELOP_LANGUAGE, RSC_ITEM, LIB_LOCATION_INPUT, LIB_LOCATION_OUTPUT, LIB_EXEC_FUNC_LOAD, LIB_EXEC_FUNC_INIT, LIB_EXEC_FUNC_PROCESS, LIB_EXEC_FUNC_END, LIB_STATUS_OPERATIONAL) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
			}
			case SELECT_ALL_LIBRARIES:{
				return "SELECT LIB_ID FROM lry.aut_bsi_prc_cfg_library";
			}
			case SELECT_LIBRARY_BY_ID_CONFIGURATION:{
				return "SELECT LIB_ID FROM lry.aut_bsi_prc_cfg_library WHERE CFG_ID=?;";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	
	/**
	 * 
	 * Inclui uma nova biblioteca a base de dados por item de configuração
	 * 
	 * 
	 * 
	 * @param idCfgBS - Id do item de configuração base
	 * @param libName - Nome da biblioteca
	 * @param LibDescription - Descrição da biblioteca
	 * @param typeLanguageDevelop - Tipo da linguagem de implementação
	 * @param rscItem - Caminho completo do arquivo para upload
	 * @param libLocInput - Diretório de origem do arquivo
	 * @param libLocOutput - Diretório destino do arquivo - despejo
	 * @param libStartInitFunc - Se true executa a rotina de inicialização para configuração da biblioteca
	 * @param libStartProcFunc - Se true executa uma função de processamento após rotina de inicialização
	 * @param libStartEndFunc - Se true executa uma função para finalização após rotina de processamento
	 * @param stateOperation - Status operacional do item - habilitado ou desabilitado
	 * @param libStartLoaderFunc - Lib de inicialização
	 * 
	 * @return True - Caso a operação seja realizada com sucesso false caso contrário
	 * 
	 */
	public boolean autInsertLibraryByIdConfigurationBase(String idCfgBS, String libName,String LibDescription, String typeLanguageDevelop, String rscItem, String libLocInput, String libLocOutput,boolean libStartLoaderFunc, boolean libStartInitFunc, boolean libStartProcFunc,boolean libStartEndFunc, boolean stateOperation) {
		try {
			System.out.println("AUT INFO : INSERT LIBRARY BY ID CONFIGURATION BASE");
			autExecSubStatements(AUT_LIBRARIES_OPERATIONS.INSERT_LIBRARY_RESOURCE.toString(), 
					new Object[] {idCfgBS,libName,LibDescription,typeLanguageDevelop,rscItem,libLocInput,libLocOutput,libStartLoaderFunc,libStartInitFunc,libStartProcFunc,libStartEndFunc,stateOperation});			
			System.out.println("AUT INFO: LIBRARY INSERTED : FINESHED");
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: INSERT LIBRARY BY ID CONFIGURATION BASE");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	/**
	 * 
	 * Lista bibliotecas associadas a configuração base associada
	 * 
	 * @param idConfiguracaoBase - Id do item de configuração base
	 * 
	 * @return java.util.List - Lista de bibliotecas associadas por id
	 * 
	 */
	public java.util.List<Object> autListLibrariesByConfigurationBSId(String idConfiguracaoBase){
		try {
			System.out.println("AUT INFO : LIST LIBRARIES BY CONFIGURATION BASE");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_LIBRARIES_OPERATIONS.SELECT_LIBRARY_BY_ID_CONFIGURATION.toString(), 
					new Object[] {idConfiguracaoBase});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST LIBRARIES BY CONFIGURATION BASE");
			System.out.println(e.getMessage());
			e.printStackTrace();			
			return null;
		}
	}
	/**
	 * 
	 * Lista todas as bibliotecas associadas ao módulo de configuração específico
	 * 
	 * @return java.util.List - Lista com valores da coluna relacionada
	 * 
	 */
	public java.util.List<Object> autListAllLibraries(){
		try {			
			System.out.println("AUT INFO : LIST ALL LIBRARIES");			
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_LIBRARIES_OPERATIONS.SELECT_ALL_LIBRARIES.toString(), 
					new Object[] {});						
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL LIBRARIES");
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
	public AUTDBBSIRSCLibraries() {
		super();
	}

}
