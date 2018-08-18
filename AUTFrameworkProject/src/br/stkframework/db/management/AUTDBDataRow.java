/**
 * 
 */
package br.stkframework.db.management;

/**
 * 
 * Gerenciamento de registros da tabela container
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBDataRow extends AUTDBDataTable {
	/**
	 * 
	 * Comandos para gerenciamento de registros na tabela de dados
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_DATA_ROW_OPERATIONS{
		INSERT_DATA_ROW_BY_TABLE,
		SELECT_DATA_ROWS_BY_TABLE,
		SELECT_CURRENT_TABLES_BY_PROCESS,
		SELECT_DATA_ROW_BY_ID,
		SELECT_ALL_DATA_ROWS_FOR_ALL_TABLES;
		
		@Override
		public String toString() {
			switch(this) {
			case SELECT_CURRENT_TABLES_BY_PROCESS:{
				return "SELECT TBL_ID FROM LRY.aut_data_tables WHERE PRC_DAT_ID=?";
			}
			case INSERT_DATA_ROW_BY_TABLE:{
				return "INSERT INTO lry.aut_data_rows(TBL_ID, ROW_DESCRIPTION, ROW_CONTENT, ROW_NUMBER_LINE, ROW_ENABLE) VALUES(?,?,?,?,?);";
			}
			case SELECT_ALL_DATA_ROWS_FOR_ALL_TABLES:{
				return "SELECT ROW_ID FROM lry.aut_data_rows;";
			}
			case SELECT_DATA_ROW_BY_ID:{
				return "SELECT ROW_ID FROM lry.aut_data_rows WHERE ROW_ID=?";
			}
			case SELECT_DATA_ROWS_BY_TABLE:{
				return "SELECT ROW_ID FROM lry.aut_data_rows WHERE tbl_id=?;";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	/**
	 * 
	 * Inclui uma nova linha na tabela de dados
	 * 
	 * @param idTable - Id da tabela
	 * @param rowDescription - Descrição da linha de dados
	 * @param rowObject - Objeto associado ao registro
	 * @param rowNumber - Número da linha
	 * @param rowEnable - Indica se a linha está ou não habilitada na tabela para operações de leitura, atualização e exclusão
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso
	 * 
	 */
	public boolean autInsertRowByTableId(String idTable, String rowDescription, String rowObject, String rowNumber, boolean rowEnable) {
		try {
			
			java.sql.ResultSet rsTbls = autExecSubStatementsWithResult(AUT_DATA_ROW_OPERATIONS.SELECT_CURRENT_TABLES_BY_PROCESS.toString(), new Object[] {idTable});
			java.util.List<Object> ltTables = autGetListItems(rsTbls);
			
			for(Object tbId : ltTables) {
				System.out.println("AUT INFO: INSERT ROW BY TABLE ID");
				autExecSubStatements(AUT_DATA_ROW_OPERATIONS.INSERT_DATA_ROW_BY_TABLE.toString(), 
						new Object[] {tbId.toString(), rowDescription, rowObject, rowNumber, rowEnable});
			}
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: INSERT ROW BY TABLE ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	/**
	 * 
	 * Lista todas as linhas de todas as tabelas do sistema
	 * 
	 * 
	 * @return java.util.List(Object) - Lista com as linhas selecionadas na tabela
	 * 
	 */
	public java.util.List<Object> autListAllRows(){
		try {
			System.out.println("AUT INFO: LIST ALL ROWS FROM ALL TABLE");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_ROW_OPERATIONS.SELECT_ALL_DATA_ROWS_FOR_ALL_TABLES.toString(), 
					new Object[] {});
		
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL ROWS FROM ALL TABLES");
			System.out.println(e.getMessage());
			e.printStackTrace();		
			return null;
		}
	}
		
	/**
	 * 
	 * Seleciona uma linha da tabela pelo id da linha de dados na tabela
	 * 
	 * @param idRow - Id da linha de dados
	 * 
	 * @return java.util.List(Object) - Lista com as linhas selecionadas na tabela
	 * 
	 */
	public java.util.List<Object> autSelectRowById(String idRow){
		try {
			System.out.println("AUT INFO: SELECT ROW BY ID");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_ROW_OPERATIONS.SELECT_DATA_ROW_BY_ID.toString(), 
					new Object[] {});
		
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: SELECT ROW BY ID");
			System.out.println(e.getMessage());
			e.printStackTrace();		
			return null;
		}
	}
	
	/**
	 * 
	 * Lista linhas da tabela
	 * 
	 * @param idTable - Id da tabela
	 * 
	 * @return java.util.List(Object) - Lista de linhas registradas no sistema por id
	 * 
	 */
	public java.util.List<Object> autListRowsByTableId(String idTable){
		try {
			
			System.out.println("AUT INFO: LIST ALL ROWS BY TABLE ID");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_ROW_OPERATIONS.SELECT_DATA_ROWS_BY_TABLE.toString(), new Object[] {idTable});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL ROWS BY TABLE ID");
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
	public AUTDBDataRow() {
		// TODO Auto-generated constructor stub
	}

}
