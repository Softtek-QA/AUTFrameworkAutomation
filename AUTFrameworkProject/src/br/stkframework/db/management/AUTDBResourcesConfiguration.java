/**
 * 
 */
package br.stkframework.db.management;

/**
 * Gerenciamento de recursos do projeto - Configura��es
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBResourcesConfiguration extends AUTDBServicesConfiguration {

	/**
	 * 
	 * Defini��o de comandos para gerenciamento de recursos
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_RESOURCES_OPERATIONS{
		INSERT_RESOURCE_BY_PROJ_ID,
		SELECT_RESOURCES_BY_PROJ_ID,
		SELECT_ALL_RESOURCES;
		
		@Override
		public String toString() {
			switch(this) {
			case INSERT_RESOURCE_BY_PROJ_ID:
			{
				return "INSERT INTO lry.aut_execution_resources(PJT_ID,RSC_LOCATION,RSC_INPUT_FILES_LOCATION,RSC_OUTPUT_FILES_LOCATION,RSC_SHARED_LOCATION,RSC_STATUS,RSC_PLATAFORM_TYPE) VALUES(?,?,?,?,?,?,?);";
			}
			case SELECT_ALL_RESOURCES:{
				return "SELECT RSC_ID FROM lry.aut_execution_resources;";
			}
			case SELECT_RESOURCES_BY_PROJ_ID:{
				return "SELECT RSC_ID FROM lry.aut_execution_resources WHERE PJT_ID=?;";
			}
			default:{
				return this.name();
			}		
			}			
		}
	}
	
	/**
	 * 
	 * Lista todos as configura��es de recurso cadastradas na base de dados
	 * 
	 * @return java.util.List(Object) - Lista de valores
	 * 
	 */
	public java.util.List<Object> autListResourcesAll(){
		try {
			System.out.println("AUT INFO : LIST ALL RESOURCES");

			java.util.List<String> listOut = new java.util.ArrayList<String>();
								
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_RESOURCES_OPERATIONS.SELECT_ALL_RESOURCES.toString(), new Object[] {});
		
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
		
			System.out.println("AUT ERROR: LIST ALL RESOURCES");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}		
	}
	/**
	 * 
	 * Lista todas as configura��es de recurso para um projeto
	 * 
	 * 
	 * @param idProj - Id do projeto
	 * @return java.util.List(String) - Lista de configura��es cadastradas no sistema para o projeto
	 */
	public java.util.List<Object> autListResourcesByProjectId(String idProj){
		try {
			
			System.out.println("AUT LIST RESOURCES BY PROJECT ID");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_RESOURCES_OPERATIONS.SELECT_RESOURCES_BY_PROJ_ID.toString(), 
					new Object[] {idProj});
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: LIST RESOURCES BY PROJECT ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	
	/**
	 * 
	 * Inseri uma nova configura��o de recurso na base de dados para um projeto espec�fico
	 * 
	 * @param idProject - Id do projeto que ser� propriet�rio do recurso
	 * @param location - Localiza��o do recurso - Exemp: Leroy Merlin Granja Julieta ou Filial 1
	 * @param inputLoc - Diret�rio para upload de arquivos
	 * @param outputLoc - Diret�rio para download de arquivos
	 * @param sharedLoc - Diret�rio compartilhado com esse recurso 
	 * @param statusOperacional - Status operacional do recurso
	 * @param OSType - Tipo de sistema operacional
	 * @return boolean - True caso a opera��o seja realizada com sucesso false caso contr�rio
	 * 
	 */
	public boolean autInsertResource(String idProject,String location,String inputLoc, String outputLoc,String sharedLoc,String statusOperacional,String OSType) {
		try 
		{
			System.out.println("AUT INFO : INSERT RESOURCE BY PROJECT ID");			
			autExecSubStatements(AUT_RESOURCES_OPERATIONS.INSERT_RESOURCE_BY_PROJ_ID.toString(), 
					new Object[] {idProject,location,inputLoc,outputLoc,sharedLoc,statusOperacional,OSType});
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: INSERT RESOURCES BY PROJECT ID");
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
	public AUTDBResourcesConfiguration() {
		// TODO Auto-generated constructor stub
		super();
	}

}
