/**
 * 
 */
package br.stkframework.db.management;

import br.stkframework.db.management.AUTDBDataColumnRuntime.AUT_DATA_COLUMN_RUNTIME_OPERATIONS;
import br.stkframework.db.management.AUTDBDataTableRuntime.AUT_DATA_TABLE_RUNTIME_OPERATIONS;

/**
 * 
 * Gerenciamento de valores do tipo object- Para colunas em tempo de execução
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBDataValueObjectRuntime extends AUTDBDataColumnRuntime {
	
	/**
	 * 
	 * Comandos para o gerenciamento de valores do tipo Object em tempo de execução
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_DATA_VALUE_OBJECT_RUNTIME_OPERATIONS{
		INSERT_DATA_VALUE_OBJECT_RUNTIME_BY_COLUMN_ID,
		SELECT_DATA_VALUES_OBJECT_RUNTIME_BY_ROW_NUMBER,
		SELECT_DATA_VALUES_OBJECT_RUNTIME_COLUMN_ID,
		CLEAR_DATA_VALUES_OBJECT_RUNTIME,
		LOADER_DATA_VALUES_OBJECT_RUNTIME_ORIGIN,
		LOADER_DATA_VALUES_OBJECT_RUNTIME_TARGET;
		
		@Override
		public String toString() {
			switch(this) {
			case CLEAR_DATA_VALUES_OBJECT_RUNTIME:{
				return "DELETE FROM lry.aut_column_values_object_runtime WHERE VAL_ID > 0";
			}
			case LOADER_DATA_VALUES_OBJECT_RUNTIME_ORIGIN:{
				//return "select * from lry.aut_column_values_object as a inner join lry.aut_data_columns as b inner join lry.aut_data_rows as c on a.col_dat_id=b.col_dat_id and b.tbl_id=c.tbl_id and c.tbl_id=?;";
				return "select * from lry.aut_column_values_object;";
				
			}
			case LOADER_DATA_VALUES_OBJECT_RUNTIME_TARGET:{
				return "select * from lry.aut_column_values_object_runtime limit 1;";
			}
			case INSERT_DATA_VALUE_OBJECT_RUNTIME_BY_COLUMN_ID:{
				return "INSERT INTO lry.aut_column_values_object_runtime(COL_DAT_ID, VAL_DESCRIPTION, VAL_CONTENT, VAL_NUMBER_LINE) VALUES(?,?,?,?);";
			}		
			case SELECT_DATA_VALUES_OBJECT_RUNTIME_COLUMN_ID:{
				return "SELECT VAL_ID FROM LRY.AUT_COLUMN_VALUES_OBJECT_RUNTIME AS A WHERE A.COL_DAT_ID=?;";
			}
			case SELECT_DATA_VALUES_OBJECT_RUNTIME_BY_ROW_NUMBER:{
				return "SELECT VAL_ID FROM lry.aut_column_values_object_runtime WHERE VAL_NUMBER_LINE=?";
			}
			default:{
				return this.name();
			}			
			}
		}
	}
	
	/**
	 * 
	 * Atualiza as tabelas em tempo de execução com os parametros atualizados na tabela de dados associada ao processo de negócio
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public boolean autLoaderValuesObjectRuntime() {
		try {
			System.out.println("AUT INFO: LOADER VALUES OBJECT RUNTIME FROM ALL COLUMNS OF ALL TABLES");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_TABLE_RUNTIME_OPERATIONS.SELECT_ALL_DATA_TABLE_RUNTIME.toString(), new Object[] {});
			java.util.List<Object> ltTables = autGetListItems(rsData);
			autExecSubStatements(AUT_DATA_VALUE_OBJECT_RUNTIME_OPERATIONS.CLEAR_DATA_VALUES_OBJECT_RUNTIME.toString(), new Object[] {});
			for(Object tblId: ltTables) {				
				autLoaderDataTable(AUT_DATA_VALUE_OBJECT_RUNTIME_OPERATIONS.LOADER_DATA_VALUES_OBJECT_RUNTIME_ORIGIN.toString(),AUT_DATA_VALUE_OBJECT_RUNTIME_OPERATIONS.LOADER_DATA_VALUES_OBJECT_RUNTIME_TARGET.toString(), new Object[] {}, new Object[] {});
			}
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LOADER VALUES OBJECT RUNTIME FROM ALL COLUMNS OF ALL TABLES");
			return false;
		}
	}
	/**
	 * 
	 * Inclui um novo valor do tipo objeto em tempo de execução no banco dados por coluna - Id
	 * 
	 * @param columnId - Id da coluna associada ao valor
	 * @param valDescription - Descrição do valor
	 * @param valContent - Conteúdo do valor
	 * @param numberLine - Número da linha
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public boolean autInsertValueObjectRuntimeByColumnId(String columnId, String valDescription,String valContent, String numberLine) {
		try {
			
			System.out.println("AUT INFO: INSERT VALUE OBJECT RUNTIME BY COLUMN ID");
			
			autExecSubStatements(AUT_DATA_VALUE_OBJECT_RUNTIME_OPERATIONS.INSERT_DATA_VALUE_OBJECT_RUNTIME_BY_COLUMN_ID.toString(), 
					new Object[] {columnId, valDescription,valContent, numberLine});
			
			return true;
		}
		catch(java.lang.Exception e) {
		
			System.out.println("AUT ERROR: INSERT VALUE OBJECT RUNTIME BY COLUMN ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}		
	}
	/**
	 * 
	 * Lista todos os valores das colunas em tempo de execução na linha especificada
	 * 
	 * @param lineNumber - Número da linha
	 * 
	 * @return java.util.List(Object) - Lista de valores na linha especificada por id
	 * 
	 */
	public java.util.List<Object> autListValuesObjectRuntimeByLineNumber(String lineNumber){
		try {
			
			System.out.println("AUT INFO: LIST VALUES OBJECT RUNTIME BY LINE NUMBER");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_VALUE_OBJECT_RUNTIME_OPERATIONS.SELECT_DATA_VALUES_OBJECT_RUNTIME_BY_ROW_NUMBER.toString(), 
					new Object[] {lineNumber});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST VALUES OBJECT RUNTIME BY LINE NUMBER");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * 
	 * Lista todos os valores do tipo objeto em tempo de execução para uma tabela específica
	 * 
	 * 
	 * @param columnId - Id da tabela de dados proprietária
	 * 
	 * @return java.util.List(Object) - Lista de valores do tipo objeto para tabela específica
	 * 
	 */
	public java.util.List<Object> autListValuesObjectRuntimeByColumnId(String columnId){
		try {
			System.out.println("AUT INFO: LIST VALUES OBJECT RUNTIME BY COLUMN ID");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_VALUE_OBJECT_RUNTIME_OPERATIONS.SELECT_DATA_VALUES_OBJECT_RUNTIME_COLUMN_ID.toString(), 
					new Object[] {columnId});
		
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST VALUES OBJECT RUNTIME BY COLUMN ID");
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
	public AUTDBDataValueObjectRuntime() {
		// TODO Auto-generated constructor stub
	}

}
