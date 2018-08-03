/**
 * 
 */
package br.stk.framework.db.management;

/**
 * 
 * Gerenciamento de colunas
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBDataColumn extends AUTDBDataTable {
	
	/**
	 * Comandos para gerenciamento de colunas
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_DATA_COLUMN_OPERATIONS{
		INSERT_COLUMN_DATA_TABLE,
		SELECT_COLUMNS_BY_DATA_TABLE_ID,
		SELECT_CURRENT_TABLE_BY_PROCESS_ID,
		SELECT_CURRENT_ROWS_BY_PROCESS_ID,
		SELECT_ALL_COLUMNS;
		
		@Override
		public String toString() {
			switch(this) {
			case SELECT_CURRENT_ROWS_BY_PROCESS_ID:{
				return "SELECT a.row_id FROM LRY.aut_data_rows as a inner join lry.aut_data_tables as b on a.tbl_id=b.tbl_id and b.prc_dat_id=? LIMIT 1;";
			}
			case SELECT_CURRENT_TABLE_BY_PROCESS_ID:{
				return "SELECT TBL_ID FROM LRY.aut_data_tables WHERE PRC_DAT_ID=?";
			}
			case INSERT_COLUMN_DATA_TABLE:{
				return "INSERT INTO lry.aut_data_columns(TBL_ID, ROW_ID, COL_DAT_NAME, COL_DAT_DESCRIPTION, COL_DAT_TYPE_VALUE) VALUES(?,?,?,?,?);";
			}
			case SELECT_ALL_COLUMNS:{
				return "SELECT COL_DAT_ID FROM lry.aut_data_columns;";
			}
			case SELECT_COLUMNS_BY_DATA_TABLE_ID:{
				return "SELECT COL_DAT_ID FROM lry.aut_data_columns WHERE TBL_ID=?;";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	
	/**
	 * 
	 * Inclui uma nova coluna para tabela de dados associada
	 * 
	 * @param tblId - Id tabela de dados
	 * @param rowId - Id da linha padrão associada
	 * @param colName - Nome da coluna
	 * @param colDescription - Descrição da coluna
	 * @param typeValue - Tipo de valor associado
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public boolean autInsertDataColumn(String tblId, String rowId, String colName, String colDescription,String typeValue) {
		try {
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_COLUMN_OPERATIONS.SELECT_CURRENT_TABLE_BY_PROCESS_ID.toString(), new Object[] {tblId});
			java.util.List<Object> ltTbls = autGetListItems(rsData);
			
			for(Object tbl : ltTbls) {		
				java.sql.ResultSet rsRows = autExecSubStatementsWithResult(AUT_DATA_COLUMN_OPERATIONS.SELECT_CURRENT_ROWS_BY_PROCESS_ID.toString(), new Object[] {tblId});
				java.util.List<Object> ltRows = autGetListItems(rsRows);
				for(Object rowTmpId: ltRows) {
					System.out.println("AUT INFO: INSERT DATA COLUMN BY TABLE ID");
					autExecSubStatements(AUT_DATA_COLUMN_OPERATIONS.INSERT_COLUMN_DATA_TABLE.toString(), 
							new Object[] {tbl,rowTmpId,colName,colDescription,typeValue});
				}
			}
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT INFO: INSERT DATA COLUMN BY TABLE ID");
			System.out.println(e.getMessage());
			
			e.printStackTrace();
			
			return false;
		}
	}
	
	
	/**
	 * 
	 * Lista todas as colunas relacionadas a uma tabela
	 * 
	 * @param tableId - Id da tabela
	 * 
	 * @return java.util.List(Object) - Lista de colunas por id
	 * 
	 */
	public java.util.List<Object> autListDataColumnsByTableId(String tableId){
		try{
			System.out.println("AUT INFO: LIST ALL COLUMNS BY TABLE ID");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_COLUMN_OPERATIONS.SELECT_COLUMNS_BY_DATA_TABLE_ID.toString(), 
					new Object[] {tableId});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL COLUMNS BY TABLE ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	/**
	 * 
	 * Lista todas as colunas cadastradas no sistema de todas as tabelas
	 * 
	 * @return java.util.List(Object) - Lista de colunas por id
	 * 
	 */
	public java.util.List<Object> autListAllDataColumns(){
		try {
			System.out.println("AUT INFO: LIST ALL DATA COLUMNS");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_COLUMN_OPERATIONS.SELECT_ALL_COLUMNS.toString(), 
					new Object[] {});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT INFO: LIST ALL DATA COLUMNS");
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
	public AUTDBDataColumn() {
		// TODO Auto-generated constructor stub
		super();
	}

}
