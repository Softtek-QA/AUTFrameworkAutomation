package br.stk.framework.db.management;

/**
 * Gerenciamento das configurações básicas do processo de negócio
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBBSIBaseConfiguration extends AUTDBBusinessProcess {
	
	/**
	 * 
	 * Comandos para gerenciamento de configurações básicas do processo de negócio
	 *  
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_BSI_PROCESS_CONFIGURATION_BASE{
		INSERT_BSI_PROC_CONFIGURATION_BASE,
		SELECT_ALL_BSI_PROC_CONFIGURATION_BASE,
		SELECT_BSI_PROC_CONFIG_BASE_BY_PROC_ID;
		
		@Override
		public String toString() {
			switch(this) {
			case INSERT_BSI_PROC_CONFIGURATION_BASE:{
				return "INSERT INTO lry.aut_bsi_prc_configuration_base(PRC_ID,RSC_ITEM_KEY, CFG_NAME, CFG_DESCRIPTION, CFG_TYPE, CFG_DEFINE_TYPE) VALUES(?,?,?,?,?,?);";
			}
			case SELECT_ALL_BSI_PROC_CONFIGURATION_BASE:{
				return "SELECT CFG_ID FROM lry.aut_bsi_prc_configuration_base;";
			}
			case SELECT_BSI_PROC_CONFIG_BASE_BY_PROC_ID:{
				return "SELECT CFG_ID FROM lry.aut_bsi_prc_configuration_base WHERE PRC_ID=?;";
			}
			default:{
				return this.name();
			}
			}
		}
		
	}
	
	/**
	 * 
	 * Carrega o arquivo específicado do diretório informado
	 * 
	 * @param file - Diretório completo do arquivo para upload
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public byte[] autBSILoaderFileDataBytes(String file) {
		try {
			
			java.util.HashMap<String,Object> fileData = autGetFileOnWindows(file);			

			System.out.println("AUT INFO : LOADING FILE DATA FOR BUSINESS PROCESS".concat(fileData.get("FILENAMEFULL").toString()));
			Long sizeFile = (Long)fileData.get("SIZE");
			
			System.out.println("AUT INFO : TAMANHO DO ARQUIVO");
			System.out.println(fileData.get("SIZE"));
			byte[] bytesOutFile = new byte[sizeFile.intValue()];
									
			return bytesOutFile;
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: LOADER FILE FOR BUSINESS PROCESS");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * Inseri um item de configuração base para o processo de negócio informado como parametro
	 * 
	 * 
	 * 
	 * @param idBSIProcess - Processo de negócio
	 * @param name - Nome do processo de negócio
	 * @param description - Descrição do processo de negócio
	 * @param keyItem - Item chave
	 * @param typeItemBSI - Tipo de item para para o processo de negócio
	 * @param typeFileSystemExtension - Tipo de arquivo no sistema operacional
	 * 
	 * @return boolean - True caso o processo seja realizado com sucesso false caso contrário
	 * 
	 */
	public boolean autInsertConfigurationBaseByBSIProcess(String idBSIProcess,String keyItem,String name,String description,String typeItemBSI,String typeFileSystemExtension) {
		try {
			
			System.out.println("AUT INFO : INSERT CONFIGURATION BASE BY BSI PROCESS ID");
			
			autExecSubStatements(AUT_BSI_PROCESS_CONFIGURATION_BASE.INSERT_BSI_PROC_CONFIGURATION_BASE.toString(), 
					new Object[] {idBSIProcess,keyItem,name,description,typeItemBSI,typeFileSystemExtension});			
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT-ERROR: INSERT CONFIGURATION BASE BY BSI PROCESS ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	
	
	
	/**
	 * 
	 * Lista todas as configurações básicas por processo de negócio
	 * 
	 * @param idBSIProcess - Id do processo de negócio
	 * 
	 * @return java.util.List(Object) - Lista de configurações base do processo
	 * 
	 */
	public java.util.List<Object> autListBSIProcessConfigBaseByProcessId(String idBSIProcess){
		try {
			System.out.println("AUT INFO : LIST ALL CONFIGURATION BASE BY BSI PROCESS");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_BSI_PROCESS_CONFIGURATION_BASE.SELECT_BSI_PROC_CONFIG_BASE_BY_PROC_ID.toString(), 
					new Object[] {idBSIProcess});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL CONFIGURATION BASE FOR BSI PROCESS ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * Lista todas as configurações básicas de todos os processos de negócio
	 * 
	 * @return java.util.List(Object) - Lista de configurações básicas dos processos de negócio
	 * 
	 */
	public java.util.List<Object> autListBSIProcessConfigBaseAll(){
		try {
			System.out.println("AUT INFO: LIST ALL BSI PROCESS CONFIGURATION BASE");
			
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_BSI_PROCESS_CONFIGURATION_BASE.SELECT_ALL_BSI_PROC_CONFIGURATION_BASE.toString(), 
					new Object[] {});
			
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST CONFIGURATION BASE BY BSI PROCESS ID");
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
	public AUTDBBSIBaseConfiguration() {
		// TODO Auto-generated constructor stub		
		super();
	}

}
