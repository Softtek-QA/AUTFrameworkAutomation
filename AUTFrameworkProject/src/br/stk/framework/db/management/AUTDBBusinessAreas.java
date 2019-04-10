/**
 * 
 */
package br.stk.framework.db.management;

/**
 * Gerenciamento de �reas de neg�cio
 * 
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBBusinessAreas extends AUTDBProject {

	/**
	 * 
	 * Comandos para gerenciamento de �reas de neg�cio
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
	 * Inseri uma nova area de neg�cio no sistema
	 * 
	 * @param idProject - Id do projeto
	 * @param name - Nome da �rea de neg�cio
	 * @param description - Descri��o da �rea de neg�cio
	 * @param status - Status operacional da �rea de neg�cio
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contr�rio
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
	 * Lista todas as �reas de neg�cio cadastradas no sistema para o projeto espec�fico
	 * 
	 * 
	 * @param idProject - Id do projeto propriet�rio da �rea de neg�cio
	 * @return java.util.List - Lista de �reas de neg�cio
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
	 * Lista todas as �reas de neg�cio cadastradas no sistema
	 * 
	 * 
	 * @return java.util.List - Lista de �reas de neg�cio cadastradas no sistema - Por ID
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
	 * Construtor padr�o da classe
	 * 
	 */
	public AUTDBBusinessAreas() {
		// TODO Auto-generated constructor stub
	}

}
