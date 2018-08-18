/**
 * 
 */
package br.stkframework.db.management;

/**
 * 
 * Gerenciamento de parametros estáticos do sistema
 * 
 * @author Softtek-QA
 * 
 *
 */
public class AUTDBAPPDynamicParameters extends AUTDBApplicationConfiguration {
	public enum AUT_DYNAMIC_PARAMETERS_OPERATIONS{
		INSERT_DYNAMIC_PARAMETER_BY_APP_ID,
		CHANGE_DYNAMIC_PARAMETER_BY_PRM_ID,
		SELECT_ALL_DYNAMIC_PARAMETERS,
		SELECT_DYNAMIC_PARAMETERS_BY_APP_ID;
		
		@Override
		public String toString() {
			switch(this) {
			case CHANGE_DYNAMIC_PARAMETER_BY_PRM_ID:{
				return "UPDATE lry.aut_app_dynamic_parameters SET APP_ID=?,PRM_NAME=?,PRM_VALUE=? WHERE PRM_ID=?;";	
			}
			case INSERT_DYNAMIC_PARAMETER_BY_APP_ID:{
				return "INSERT INTO lry.aut_app_dynamic_parameters(APP_ID,PRM_NAME,PRM_VALUE) VALUES(?,?,?);";
			}
			case SELECT_ALL_DYNAMIC_PARAMETERS:
			{
				return "SELECT PRM_ID FROM lry.aut_app_dynamic_parameters;";
			}
			case SELECT_DYNAMIC_PARAMETERS_BY_APP_ID:{
				return "SELECT PRM_ID FROM lry.aut_app_dynamic_parameters WHERE APP_ID=?;";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	
	/**
	 * 
	 * Incluí um novo parametro na base de dados
	 * 
	 * @param appId - Id do aplicativo
	 * @param paramName - Nome do parametro
	 * @param paramValue - Valor do parametro
	 * 
	 * @return boolean - True caso o processo seja realizado com sucesso false caso contrário
	 * 
	 */
	public boolean autInsertStaticParameterByParamId(String appId,String paramName,String paramValue) {
		try {
			System.out.println("AUT INFO : INSERT DYNAMIC PARAMETER BY ID APPLICATION");
			autExecSubStatements(AUT_DYNAMIC_PARAMETERS_OPERATIONS.INSERT_DYNAMIC_PARAMETER_BY_APP_ID.toString(), 
					new Object[] {appId,paramName,paramValue});
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: INSERT DYNAMIC PARAMETER BY ID APPLICATION");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	/**
	 * 
	 * Altera parametros dynamic relacionado ao item de configuração do aplicativo
	 * 
	 * @param idParameter - Id do parametro
	 * @param ipApplication - Id do aplicativo
	 * @param name - Nome do parametro
	 * @param value - valor do parametro
	 * 
	 * @return boolean - True caso o processo seja realizado com sucesso
	 * 
	 */
	public boolean autChangeStaticParameter(String ipApplication,String name,String value,String idParameter) {
		try {
			
			System.out.println("AUT INFO : CHANGE DYNAMIC PARAMETER BY ID PARAMETER");
			
			autExecSubStatements(AUT_DYNAMIC_PARAMETERS_OPERATIONS.CHANGE_DYNAMIC_PARAMETER_BY_PRM_ID.toString(), 
					new Object[] {ipApplication,name,value,idParameter});
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: CHANGE DYNAMIC PARAMETER BY ID PARAMETER");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}		
	}
	
	/**
	 * 
	 * LISTA TODOS OS PARAMETROS DINAMICO RELACIONADOS A UM APLICATIVO
	 * 
	 * @param idApp - ID DO APLICATIVO
	 * 
	 * @return java.util.List(Object) - Lista de parametros estáticos por id
	 * 
	 */
	public java.util.List<Object> autListStaticParametersByAppId(String idApp){
		try {
			System.out.println("AUT INFO : LIST DYNAMIC PARAMETERS BY ID APPLICATION");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DYNAMIC_PARAMETERS_OPERATIONS.SELECT_DYNAMIC_PARAMETERS_BY_APP_ID.toString(), 
					new Object[] {idApp});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST DYNAMIC PARAMETERS BY ID APPLICATION");
			System.out.println(e.getMessage());
			
			return null;
		}
	}
	
	/**
	 * 
	 * Lista todos os parametros dinâmicos associados a um item de configuração do aplicativo
	 * 
	 * @return java.util.List(Object) - Lista de todos os parametros estáticos por Id
	 * 
	 */
	public java.util.List<Object> autListAllStaticParameters(){
		try {
			System.out.println("AUT INFO: LIST ALL DYNAMIC PARAMETERS FOR ALL APPLICATIONS");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DYNAMIC_PARAMETERS_OPERATIONS.SELECT_ALL_DYNAMIC_PARAMETERS.toString(), new Object[] {});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL DYNAMIC PARAMETERS FOR ALL APPLICATIONS");
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
	public AUTDBAPPDynamicParameters() {
		super();
	}

}
