package br.stk.framework.db.management;

/**
 * Gerenciamento das configura��es b�sicas do processo de neg�cio
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBBSIBaseConfiguration extends AUTDBBusinessProcess {
	
	/**
	 * 
	 * Comandos para gerenciamento de configura��es b�sicas do processo de neg�cio
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
	 * Carrega o arquivo espec�ficado do diret�rio informado
	 * 
	 * @param file - Diret�rio completo do arquivo para upload
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contr�rio
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
	 * Inseri um item de configura��o base para o processo de neg�cio informado como parametro
	 * 
	 * 
	 * 
	 * @param idBSIProcess - Processo de neg�cio
	 * @param name - Nome do processo de neg�cio
	 * @param description - Descri��o do processo de neg�cio
	 * @param keyItem - Item chave
	 * @param typeItemBSI - Tipo de item para para o processo de neg�cio
	 * @param typeFileSystemExtension - Tipo de arquivo no sistema operacional
	 * 
	 * @return boolean - True caso o processo seja realizado com sucesso false caso contr�rio
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
	 * Lista todas as configura��es b�sicas por processo de neg�cio
	 * 
	 * @param idBSIProcess - Id do processo de neg�cio
	 * 
	 * @return java.util.List(Object) - Lista de configura��es base do processo
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
	 * Lista todas as configura��es b�sicas de todos os processos de neg�cio
	 * 
	 * @return java.util.List(Object) - Lista de configura��es b�sicas dos processos de neg�cio
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
	 * Construtor padr�o da classe
	 * 
	 */
	public AUTDBBSIBaseConfiguration() {
		// TODO Auto-generated constructor stub		
		super();
	}

}
