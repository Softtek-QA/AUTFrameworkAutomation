/**
 * 
 */
package br.stk.framework.db.management;

/**
 * Gerenciamento de tabelas - Fluxos de dados
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBDataSet extends AUTDBProject {
	
	/**
	 * 
	 * Comandos de gerenciamento de dataset
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_DATA_SET_OPERATIONS{
		INSERT_DATA_SET_BY_PROJECT,
		SELECT_DATA_SET_BY_PROJECT,
		SELECT_ALL_DATA_SET;
		
		@Override
		public String toString() {
			switch(this) {
			case INSERT_DATA_SET_BY_PROJECT:{
				return "INSERT INTO lry.aut_data_projects(PJT_ID, DAT_TRACE_KEY, DAT_VERSION, DAT_STATE_OPERATIONAL) VALUES(?,?,?,?);";
			}
			case SELECT_ALL_DATA_SET:{
				return "SELECT DAT_ID FROM lry.aut_data_projects;";
			}
			case SELECT_DATA_SET_BY_PROJECT:{
				return "SELECT DAT_ID FROM lry.aut_data_projects WHERE PJT_ID=?;";
			}
			default:{
				return this.name();
			}
			}			
		}
	}
	
	
	
	/**
	 * 
	 * Inclui um novo dataset na base de dados
	 * 
	 * @param projId - Id do projeto proprietário do data set
	 * @param key - Chave 
	 * @param version - Versão do dataset
	 * @param stateOperational - Status operacional do componente
	 * 
	 * @return boolean - True caso o processo seja concluído com sucesso false caso contrário
	 * 
	 */
	public boolean autInsertDataSetByProjectId(String projId,String key, String version, String stateOperational) {
		try {
			
			System.out.println("AUT INFO: INSERT DATASET BY PROJECT ID");
			autExecSubStatements(AUT_DATA_SET_OPERATIONS.INSERT_DATA_SET_BY_PROJECT.toString(), 
					new Object[] {projId,key,version,stateOperational});
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT INFO: INSERT DATASET BY PROJECT ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	
	
	/**
	 * Lista todos os DataSets from all projects
	 * 
	 * @return java.util.List(Object) - Lista de dataset from all projects
	 */
	public java.util.List<Object> autListAllDataSet(){
		try {
			System.out.println("AUT INFO: LIST ALL DATASET FROM ALL PROJECT");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_SET_OPERATIONS.SELECT_ALL_DATA_SET.toString(), 
					new Object[] {});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL DATASET FROM ALL PROJECTS");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	/**
	 * 
	 * Lista os datasets cadastrados no sistema para um projeto
	 * 
	 * @param idProject - Id do projeto
	 * 
	 * @return java.util.List(Object) - Lista de datasets
	 * 
	 */
	public java.util.List<Object> autListDataSetByProjectId(String idProject){
		try {
			
			System.out.println("AUT INFO: LIST ALL DATASET BY PROJECT ID");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_SET_OPERATIONS.SELECT_ALL_DATA_SET.toString(), 
					new Object[] {idProject});
			
			return autGetListItems(rsData);
			
		}
		catch(java.lang.Exception e) {
		
			System.out.println("AUT ERROR: LIST ALL DATASET BY PROJECT ID");
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
	public AUTDBDataSet() {
		// TODO Auto-generated constructor stub
		super();
	}

}
