/**
 * 
 */
package br.framework.db.management;

import br.framework.db.management.AUTDBDataColumn.AUT_DATA_COLUMN_OPERATIONS;

/**
 * 
 * Gerenciamento de colunas em tempo de execução
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBDataColumnRuntime extends AUTDBDataTableRuntime {
	AUTDBDataRowRuntime rowsManag = null;
	/**
	 * Comandos para gerenciamento de colunas em tempo de execução
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_DATA_COLUMN_RUNTIME_OPERATIONS{
		INSERT_COLUMN_RUNTIME_DATA_TABLE,
		SELECT_COLUMNS_RUNTIME_BY_DATA_TABLE_ID,
		SELECT_ALL_COLUMNS_RUNTIME,
		CLEAR_COLUMNS_DATA_RUNTIME_BY_TABLE,
		LOADER_COLUMNS_DATA_RUNTIME_BY_TABLE_ID_ORIGIN,
		LOADER_COLUMNS_DATA_RUNTIME_BY_TABLE_ID_TARGET;
		
		@Override
		public String toString() {
			switch(this) {
			case LOADER_COLUMNS_DATA_RUNTIME_BY_TABLE_ID_TARGET:{
				return "SELECT * FROM LRY.aut_data_columns_runtime WHERE TBL_ID=?";
			}
			case LOADER_COLUMNS_DATA_RUNTIME_BY_TABLE_ID_ORIGIN:{
				return "SELECT * FROM LRY.aut_data_columns WHERE TBL_ID=?";
			}
			case CLEAR_COLUMNS_DATA_RUNTIME_BY_TABLE:{
				return "DELETE FROM LRY.aut_data_columns_runtime WHERE TBL_ID=?;";
			}
			case INSERT_COLUMN_RUNTIME_DATA_TABLE:{
				return "INSERT INTO lry.aut_data_columns_runtime(TBL_ID, ROW_ID, COL_DAT_NAME, COL_DAT_DESCRIPTION, COL_DAT_TYPE_VALUE) VALUES(?,?,?,?,?);";
			}
			case SELECT_ALL_COLUMNS_RUNTIME:{
				return "SELECT COL_DAT_ID FROM lry.aut_data_columns_runtime;";
			}
			case SELECT_COLUMNS_RUNTIME_BY_DATA_TABLE_ID:{
				return "SELECT COL_DAT_ID FROM lry.aut_data_columns_runtime WHERE TBL_ID=?;";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	/**
	 * 
	 * Carrega a matriz de definição das colunas de cada tabela
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public boolean autLoaderColumnsDataRuntime() {
		try {
			java.util.List<Object> listaTabelas = autGetDataTable().autListAllDataTable();
			for(Object tblId : listaTabelas) {
				System.out.println("AUT INFO: LOADER COLUMNS DATA RUNTIME BY ALL TABLES : TABLE ID: ".concat(tblId.toString()));
				autLoaderDataTable(AUT_DATA_COLUMN_RUNTIME_OPERATIONS.LOADER_COLUMNS_DATA_RUNTIME_BY_TABLE_ID_ORIGIN.toString(), 
						AUT_DATA_COLUMN_RUNTIME_OPERATIONS.LOADER_COLUMNS_DATA_RUNTIME_BY_TABLE_ID_TARGET.toString(), new Object[] {tblId}, new Object[] {tblId});
				System.out.println("AUT INFO: LOADER COLUMNS DATA RUNTIME BY ALL TABLES : FINISHED : TABLE ID : ".concat(tblId.toString()));
			}
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LOADER COLUMNS DATA RUNTIME BY ALL TABLES");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	/**
	 * 
	 * Inclui uma nova coluna em tempo de execução para tabela de dados associada
	 * 
	 * @param tblId - Id tabela de dados
	 * @param rowId - Id da linha padrão associada
	 * @param colName - Nome da coluna
	 * @param colDescription - Descrição da coluna
	 * @param typeValue - Tipo de valor associado
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public boolean autInsertDataColumnRuntime(String tblId, String rowId, String colName, String colDescription,String typeValue) {
		try {
			System.out.println("AUT INFO: INSERT DATA COLUMN RUNTIME BY TABLE ID");
			autExecSubStatements(AUT_DATA_COLUMN_RUNTIME_OPERATIONS.INSERT_COLUMN_RUNTIME_DATA_TABLE.toString(), 
					new Object[] {tblId,rowId,colName,colDescription,typeValue});
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT INFO: INSERT DATA COLUMN RUNTIME BY TABLE ID");
			System.out.println(e.getMessage());
			
			e.printStackTrace();
			
			return false;
		}
	}
	
	
	/**
	 * 
	 * Lista todas as colunas relacionadas a uma tabela em tempo de execução
	 * 
	 * @param tableId - Id da tabela
	 * 
	 * @return java.util.List(Object) - Lista de colunas por id
	 * 
	 */
	public java.util.List<Object> autListDataColumnsRuntimeByTableId(String tableId){
		try{
			System.out.println("AUT INFO: LIST ALL COLUMNS RUNTIME BY TABLE ID");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_COLUMN_RUNTIME_OPERATIONS.SELECT_COLUMNS_RUNTIME_BY_DATA_TABLE_ID.toString(), 
					new Object[] {tableId});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL COLUMNS RUNTIME BY TABLE ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	/**
	 * 
	 * Lista todas as colunas cadastradas no sistema de todas as tabelas em tempo de execução
	 * 
	 * @return java.util.List(Object) - Lista de colunas por id
	 * 
	 */
	public java.util.List<Object> autListAllDataColumnsRuntime(){
		try {
			System.out.println("AUT INFO: LIST ALL DATA COLUMNS RUNTIME");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_COLUMN_RUNTIME_OPERATIONS.SELECT_ALL_COLUMNS_RUNTIME.toString(), 
					new Object[] {});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT INFO: LIST ALL DATA COLUMNS RUNTIME");
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
	public AUTDBDataColumnRuntime() {
		// TODO Auto-generated constructor stub
		super();
	}

}
