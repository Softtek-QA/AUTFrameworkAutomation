/**
 * 
 */
package br.framework.db.management;

/**
 * 
 * Gerenciamento de processos de neg�cio
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBBusinessProcess extends AUTDBBusinessAreas {
	/**
	 * 
	 * Comandos para gerenciamento de projeto
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_BUSINESS_PROCESS
	{
		INSERT_BUSINESS_PROCESS_BY_BSI_AREA,
		SELECT_ALL_BUSINESS_PROCESS,
		SELECT_BUSINESS_PROCESS_BY_BSI_AREA;		
		@Override
		public String toString() {
			switch(this) {
			case INSERT_BUSINESS_PROCESS_BY_BSI_AREA:{
				return "INSERT INTO lry.aut_business_process(BSI_ID,PRC_NAME,PRC_DESCRIPTION) VALUES(?,?,?);";
			}
			case SELECT_ALL_BUSINESS_PROCESS:{
				return "SELECT PRC_ID FROM lry.aut_business_process;";
			} 
			case SELECT_BUSINESS_PROCESS_BY_BSI_AREA:{
				return "SELECT PRC_ID FROM lry.aut_business_process WHERE BSI_ID=?";
			}
			default:{
				return this.toString();
			}
			}
		}
	}
	
	/**
	 * Inclui um novo processo de neg�cio para uma �rea espec�fica
	 * 
	 * @param idBSIArea - Id da �rea de neg�cio
	 * @param name - Nome da �rea de neg�cio
	 * @param description - Descri��o da �rea de neg�cio
	 * 
	 * @return boolean - True em caso o processo seja conclu�do com sucesso false caso constr�rio
	 */
	public boolean autInsertBusinessProcessByBSIArea(String idBSIArea,String name,String description) {
		try {
			System.out.println("AUT INFO : INSERT BUSINESS PROCESS BY BSI AREA");
			autExecSubStatements(AUT_BUSINESS_PROCESS.INSERT_BUSINESS_PROCESS_BY_BSI_AREA.toString(), 
					new Object[] {idBSIArea,name,description});
						
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: INSERT BUSINESS PROCESS BY BSI AREA");
			System.out.println(e.getMessage());
			e.printStackTrace();			
			return false;
		}
	}
	
	/**
	 * 
	 * Lista todos os processos de neg�cio cadastrados no sistema
	 * 
	 * 
	 * @return java.util.List(Object) - Lista de processos de neg�cio cadastrados no sistema
	 * 
	 */
	public java.util.List<Object> autListBusinessProcessAll(){
		try {			
			System.out.println("AUT INFO : LIST ALL BUSINESS PROCESS");
			
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_BUSINESS_PROCESS.SELECT_ALL_BUSINESS_PROCESS.toString(), 
					new Object[] {});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL BUSINESS PROCESS");
			System.out.println(e.getMessage());
			e.printStackTrace();			
			return null;
		}
	}
	/**
	 * 
	 * Carrega lista de processos de neg�cio por �rea de neg�cio
	 * 
	 * @param idBsiArea - Id da �rea de neg�cio
	 * 
	 * @return java.util.List(Object) - Lista de processos de neg�cio 
	 * 
	 */
	public java.util.List<Object> autListBusinessProcess(String idBsiArea){
		try {
			
			System.out.println("AUT INFO: LIST BUSINESS PROCESS BY BSI AREA");

			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_BUSINESS_PROCESS.SELECT_BUSINESS_PROCESS_BY_BSI_AREA.toString(), 
					new Object[] {idBsiArea});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST BUSINESS PROCESS BY BSI AREA");
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
	public AUTDBBusinessProcess() {
		// TODO Auto-generated constructor stub
	}

}
