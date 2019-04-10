/**
 * 
 */
package br.stk.framework.db.management;

/**
 * Gerenciamento de áreas de negócio
 * 
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBBusinessAreas extends AUTDBProject {

	/**
	 * 
	 * Comandos para gerenciamento de áreas de negócio
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_BUSINESS_AREA_OPERATION{
		INSERT_BUSINESS_AREA,
		SELECT_BSI_BY_PROJECT_ID,
		SELECT_ALL_BUSINESS_AREA;
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			
			switch(this) {
			case INSERT_BUSINESS_AREA:{			
				return "INSERT INTO lry.aut_business_area(PJT_ID,BSI_NAME,BSI_DESCRIPTION,BSI_STATUS) VALUES(?,?,?,?)";
			}
			case SELECT_ALL_BUSINESS_AREA:{
				return "SELECT bsi_id FROM lry.aut_business_area;";
			}
			case SELECT_BSI_BY_PROJECT_ID:{
				return "SELECT bsi_id FROM lry.aut_business_area WHERE pjt_id=?";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	
	/**
	 * 
	 * Inseri uma nova area de negócio no sistema
	 * 
	 * @param idProject - Id do projeto
	 * @param name - Nome da área de negócio
	 * @param description - Descrição da área de negócio
	 * @param status - Status operacional da área de negócio
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 */
	public  boolean autInsertBusinessArea(String idProject,String name,String description,String status) {
		try {
			System.out.println("AUT INFO: INSERT BUSINESS AREA FOR PROJECT ID");
			autExecSubStatements(AUT_BUSINESS_AREA_OPERATION.INSERT_BUSINESS_AREA.toString(), 
					new Object[] {idProject,name,description,status});
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: INSERT BUSINESS AREA FOR PROJECT ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	/**
	 * 
	 * Lista todas as áreas de negócio cadastradas no sistema para o projeto específico
	 * 
	 * 
	 * @param idProject - Id do projeto proprietário da área de negócio
	 * @return java.util.List - Lista de áreas de negócio
	 * 
	 */
	public java.util.List<Object> autListBusinessAreaByIdProject(String idProject){
		try {
			System.out.println("AUT INFO: LIST BUSINESS AREA FOR PROJECT ID");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_BUSINESS_AREA_OPERATION.SELECT_BSI_BY_PROJECT_ID.toString(), 
					new Object[] {idProject});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST BUSINESS AREA FOR PROJECT ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * Lista todas as áreas de negócio cadastradas no sistema
	 * 
	 * 
	 * @return java.util.List - Lista de áreas de negócio cadastradas no sistema - Por ID
	 * 
	 */
	public java.util.List<Object> autListBusinessAreaAll(){
		try {
			System.out.println("AUT INFO : LIST ALL BUSINESS AREA FOR ALL PROJECTS");
			
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_BUSINESS_AREA_OPERATION.SELECT_ALL_BUSINESS_AREA.toString(), new Object[] {});
				
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL BUSINESS AREA FOR ALL PROJECTS");
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
	public AUTDBBusinessAreas() {
		// TODO Auto-generated constructor stub
	}

}
