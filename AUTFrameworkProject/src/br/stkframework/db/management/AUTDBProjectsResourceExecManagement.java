/**
 * 
 */
package br.stkframework.db.management;

/**
 * 
 * Gerenciamento de recursos relacionados a execução de cada projeto
 * 
 * @author Sottek-QA
 *
 */
public class AUTDBProjectsResourceExecManagement extends AUTDBProjectsExecutionDetail {
	public  enum AUT_SQL_TABLE_PROPERTIES_RESOURCES_EXECUTION{
		RESOURCE_ID;
		
		@Override
		public String toString() {
			switch(this) {
			case RESOURCE_ID:{
				return "STD_ID";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	
	public enum AUT_PROJECT_RESOURCE_OPERATIONS{
		INSERT_IMAGE_SCREEN_DESKTOP_EXECUTION,
		UPDATE_IMAGE_RESOURCE_BY_PROJECT_ID,
		SELECT_ALL_IMAGE_RESOURCES,
		DELETE_RESOURCES_BY_EXECUTION_ID,
		SELECT_ID_RESOURCE_BY_SCENARIO;
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			switch(this) {
			case SELECT_ID_RESOURCE_BY_SCENARIO:{
				return "SELECT STD_ID FROM LRY.aut_projects_status_details WHERE PJT_ID=? AND STD_ITEM_CONFIGURATION=?";
			}
			case DELETE_RESOURCES_BY_EXECUTION_ID:{
				return "DELETE FROM LRY.AUT_PROJECTS_OUTPUT_DATA WHERE STD_ID IN ((SELECT STD_ID FROM LRY.aut_projects_status_details WHERE PJT_ID=?));";
			}
			case INSERT_IMAGE_SCREEN_DESKTOP_EXECUTION:{
				return "INSERT INTO LRY.AUT_PROJECTS_OUTPUT_DATA(STD_ID,OTD_NAME,OTD_DESCRIPTION,OTD_VALUE_IMG) VALUES(?,?,?,?)";
			}
			case SELECT_ALL_IMAGE_RESOURCES:{
				return "SELECT * FROM LRY.AUT_PROJECTS_OUTPUT_DATA;";
			}
			case UPDATE_IMAGE_RESOURCE_BY_PROJECT_ID:{
				return "UPDATE LRY.AUT_PROJECTS_OUTPUT_DATA SET OTD_VALUE_IMG=? WHERE PJT_ID=?";
			}
			}
			return super.toString();
		}
	}
	
	public Object autGetResourceIdByScenario(Object[] parameters) {
		try {		
			java.sql.ResultSet rsData = autExecSubStatementsWithResultDefault(AUT_PROJECT_RESOURCE_OPERATIONS.SELECT_ID_RESOURCE_BY_SCENARIO.toString(), parameters);
			if(rsData.last()) {
				return rsData.getString(AUT_SQL_TABLE_PROPERTIES_RESOURCES_EXECUTION.RESOURCE_ID.toString());
			}
			
			return null;
		}
		catch(java.lang.Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();	
			
			return null;
		}
	}
	
	public boolean autInsertResourceExecImage(Object[] parameters) {
		try {
			System.out.println("AUT INFO: INSERT NEW RESOURCE BY PROJECT EXECUTION : INIT");
			autExecSubStatementsDefault(AUT_PROJECT_RESOURCE_OPERATIONS.INSERT_IMAGE_SCREEN_DESKTOP_EXECUTION.toString(), parameters);
			
			System.out.println("AUT INFO: INSERT NEW RESOURCE BY PROJECT EXECUTION : END");
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: INSERT IMAGE VERIFICATION BY PROJECT EXECUTION");
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
	public AUTDBProjectsResourceExecManagement() {
		// TODO Auto-generated constructor stub
		super();
	}
}
