/**
 * 
 */
package br.framework.db.management;

import br.framework.db.management.AUTDBDataTableRuntime.AUT_DATA_TABLE_RUNTIME_OPERATIONS;

/**
 * 
 * Gerenciamento de registros da tabela runtime container
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBDataRowRuntime extends AUTDBDataTable {
	/**
	 * 
	 * Comandos para gerenciamento de registros na tabela de dados
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_DATA_ROW_RUNTIME_OPERATIONS{
		INSERT_DATA_ROW_RUNTIME_BY_TABLE,
		SELECT_DATA_ROWS_RUNTIME_BY_TABLE,
		SELECT_DATA_ROW_RUNTIME_BY_ID,
		SELECT_ALL_DATA_ROWS_RUNTIME_FOR_ALL_TABLES,
		LOADER_DATA_ROWS_RUNTIME_BY_TABLE_ID_ORIGIN,
		LOADER_DATA_ROWS_RUNTIME_BY_TABLE_ID_TARGET,		
		CLEAR_DATA_ROWS_RUNTIME_BY_TABLE_ID;

		@Override
		public String toString() {
			switch(this) {
			case CLEAR_DATA_ROWS_RUNTIME_BY_TABLE_ID:{
				return "DELETE FROM lry.aut_data_rows_runtime WHERE TBL_ID=?";
			}
			case LOADER_DATA_ROWS_RUNTIME_BY_TABLE_ID_ORIGIN:{
				return "SELECT * FROM lry.aut_data_rows WHERE TBL_ID=?;";
			}
			case LOADER_DATA_ROWS_RUNTIME_BY_TABLE_ID_TARGET:{
				return "SELECT * FROM lry.aut_data_rows_runtime WHERE TBL_ID=?;";
			}
			case INSERT_DATA_ROW_RUNTIME_BY_TABLE:{
				return "INSERT INTO lry.aut_data_rows_runtime(TBL_ID, ROW_DESCRIPTION, ROW_CONTENT, ROW_NUMBER_LINE, ROW_ENABLE) VALUES(?,?,?,?,?);";
			}
			case SELECT_ALL_DATA_ROWS_RUNTIME_FOR_ALL_TABLES:{
				return "SELECT ROW_ID FROM lry.aut_data_rows_runtime;";
			}
			case SELECT_DATA_ROW_RUNTIME_BY_ID:{
				return "SELECT ROW_ID FROM lry.aut_data_rows_runtime WHERE ROW_ID=?";
			}
			case SELECT_DATA_ROWS_RUNTIME_BY_TABLE:{
				return "SELECT ROW_ID FROM lry.aut_data_rows_runtime WHERE tbl_id=?;";
			}
			default:{
				return this.name();
			}
			}
		}
	}

	/**
	 * 
	 * Carrega o fluxo de dados em tempo de execução
	 * 
	 * 
	 * @return boolean - True caso o processo seja finalizado sucesso false caso contrário
	 * 
	 */
	public boolean autLoaderRowsDataRuntime() {
		try {
			java.sql.ResultSet rsTablesIds = autExecSubStatementsWithResult(AUT_DATA_TABLE_RUNTIME_OPERATIONS.SELECT_ALL_DATA_TABLE_RUNTIME_WITH_EXTERN_ID.toString(), new Object[] {});
			java.util.List<Object> tables = autGetListItems(rsTablesIds);

			for(Object tblId: tables) {
				System.out.println("AUT INFO: CLEAR DATA ROWS RUNTIME : TABLE ID: ".concat(tblId.toString()));
				autExecSubStatements(AUT_DATA_ROW_RUNTIME_OPERATIONS.CLEAR_DATA_ROWS_RUNTIME_BY_TABLE_ID.toString(), new Object[] {tblId.toString()});
				System.out.println("AUT INFO: LOADER DATA ROWS RUNTIME BY TABLE ID : ".concat(tblId.toString()));				
				//autExecSubStatements(AUT_DATA_ROW_RUNTIME_OPERATIONS.LOADER_DATA_ROWS_RUNTIME_BY_TABLE_ID_ORIGIN.toString(), new Object[] {tblId.toString()});
				autLoaderDataTable(AUT_DATA_ROW_RUNTIME_OPERATIONS.LOADER_DATA_ROWS_RUNTIME_BY_TABLE_ID_ORIGIN.toString(),
						AUT_DATA_ROW_RUNTIME_OPERATIONS.LOADER_DATA_ROWS_RUNTIME_BY_TABLE_ID_TARGET.toString(),
						new Object[] {tblId}, new Object[] {tblId});
				System.out.println("AUT INFO: LOADER DATA ROWS RUNTIME BY TABLE ID: FINISHED : TABLE ID: ".concat(tblId.toString()));			}

			return true;
		}
		catch(java.lang.Exception e) {

			System.out.println("AUT ERROR: LOADER DATA ROWS RUNTIME BY TABLE ID");
			System.out.println(e.getMessage());
			e.printStackTrace();

			return false;
		}
	}
	/**
	 * 
	 * Inclui uma nova linha na tabela runtime de dados
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
	public boolean autInsertRowByTableRuntimeId(String idTable, String rowDescription, String rowObject, String rowNumber, boolean rowEnable) {
		try {
			System.out.println("AUT INFO: INSERT ROW BY TABLE RUNTIME ID");
			autExecSubStatements(AUT_DATA_ROW_RUNTIME_OPERATIONS.INSERT_DATA_ROW_RUNTIME_BY_TABLE.toString(), 
					new Object[] {idTable, rowDescription, rowObject, rowNumber, rowEnable});

			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: INSERT ROW BY TABLE RUNTIME ID");
			System.out.println(e.getMessage());
			e.printStackTrace();

			return false;
		}
	}
	/**
	 * 
	 * Lista todas as linhas de todas as tabelas em tempo de execução do sistema
	 * 
	 * 
	 * @return java.util.List(Object) - Lista com as linhas selecionadas na tabela em tempo de execução
	 * 
	 */
	public java.util.List<Object> autListAllRowsRuntime(){
		try {
			System.out.println("AUT INFO: LIST ALL ROWS FROM ALL TABLE RUNTIME");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_ROW_RUNTIME_OPERATIONS.SELECT_ALL_DATA_ROWS_RUNTIME_FOR_ALL_TABLES.toString(), 
					new Object[] {});

			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL ROWS FROM ALL TABLES RUNTIME");
			System.out.println(e.getMessage());
			e.printStackTrace();		
			return null;
		}
	}

	/**
	 * 
	 * Seleciona uma linha da tabela pelo id da linha de dados na tabela em tempo de execução
	 * 
	 * @param idRow - Id da linha de dados
	 * 
	 * @return java.util.List(Object) - Lista com as linhas selecionadas na tabela em tempo de execução
	 * 
	 */
	public java.util.List<Object> autSelectRowRuntimeById(String idRow){
		try {
			System.out.println("AUT INFO: SELECT ROW RUNTIME BY ID");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_ROW_RUNTIME_OPERATIONS.SELECT_DATA_ROW_RUNTIME_BY_ID.toString(), 
					new Object[] {});

			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: SELECT ROW RUNTIME BY ID");
			System.out.println(e.getMessage());
			e.printStackTrace();		
			return null;
		}
	}

	/**
	 * 
	 * Lista linhas da tabela em tempo de execução
	 * 
	 * @param idTable - Id da tabela
	 * 
	 * @return java.util.List(Object) - Lista de linhas registradas no sistema por id
	 * 
	 */
	public java.util.List<Object> autListRowsRuntimeByTableId(String idTable){
		try {

			System.out.println("AUT INFO: LIST ALL ROWS RUNTIME BY TABLE ID");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_ROW_RUNTIME_OPERATIONS.SELECT_DATA_ROWS_RUNTIME_BY_TABLE.toString(), new Object[] {idTable});

			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL ROWS RUNTIME BY TABLE ID");
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
	public AUTDBDataRowRuntime() {
		// TODO Auto-generated constructor stub
	}

}
