/**
 * 
 */
package br.stkframework.db.management;

import br.stkframework.db.management.AUTDBDataValueObjectRuntime.AUT_DATA_VALUE_OBJECT_RUNTIME_OPERATIONS;

/**
 * 
 * Gerenciamento de valores do tipo texto em tempo de execução - Para colunas
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBDataValueTextRuntime extends AUTDBDataColumnRuntime {
	
	/**
	 * 
	 * Comandos para o gerenciamento de valores do tipo texto em tempo de execução
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_DATA_VALUE_TEXT_RUNTIME_OPERATIONS{
		INSERT_DATA_VALUE_TEXT_RUNTIME_BY_COLUMN_ID,
		SELECT_DATA_VALUES_TEXT_RUNTIME_BY_ROW_NUMBER,
		SELECT_DATA_VALUES_TEXT_RUNTIME_COLUMN_ID,
		CLEAR_DATA_VALUES_TEXT_RUNTIME,
		LOADER_DATA_VALUES_TEXT_RUNTIME_TARGET,
		LOADER_DATA_VALUES_TEXT_RUNTIME_ORIGIN;
		
		@Override
		public String toString() {
			switch(this) {
			case CLEAR_DATA_VALUES_TEXT_RUNTIME:
			{
				return "delete from lry.aut_column_values_object_text_runtime where id > 0;";
			}
			case LOADER_DATA_VALUES_TEXT_RUNTIME_ORIGIN:{
				return "select * from lry.aut_column_values_object_text;";		
			}
			case LOADER_DATA_VALUES_TEXT_RUNTIME_TARGET:{
				return "select * from lry.aut_column_values_object_text_runtime;";
			}
			case INSERT_DATA_VALUE_TEXT_RUNTIME_BY_COLUMN_ID:{
				return "INSERT INTO lry.aut_column_values_object_text_runtime(COL_DAT_ID, VAL_DESCRIPTION, VAL_CONTENT, VAL_NUMBER_LINE) VALUES(?,?,?,?);";
			}		
			case SELECT_DATA_VALUES_TEXT_RUNTIME_COLUMN_ID:{
				return "SELECT VAL_ID FROM LRY.AUT_COLUMN_VALUES_OBJECT_TEXT_RUNTIME AS A WHERE A.COL_DAT_ID=?;";
			}
			case SELECT_DATA_VALUES_TEXT_RUNTIME_BY_ROW_NUMBER:{
				return "SELECT VAL_ID FROM lry.aut_column_values_object_text_runtime WHERE VAL_NUMBER_LINE=?";
			}
			default:{
				return this.name();
			}			
			}
		}
	}
	
	
	/**
	 * 
	 * Carrega as tabelas de conteudo do tipo em tempo de execução
	 * 
	 * @return boolean - True caso o processso seja finalizado com sucesso false caso contrário
	 * 
	 */
	public boolean autLoaderValuesTextRuntime() {
		try {
			System.out.println("AUT INFO: LOADER VALUES TEXT RUNTIME");
			
			autExecSubStatements(AUT_DATA_VALUE_TEXT_RUNTIME_OPERATIONS.CLEAR_DATA_VALUES_TEXT_RUNTIME.toString(), new Object[] {});
			autLoaderDataTable(AUT_DATA_VALUE_TEXT_RUNTIME_OPERATIONS.LOADER_DATA_VALUES_TEXT_RUNTIME_ORIGIN.toString(), AUT_DATA_VALUE_TEXT_RUNTIME_OPERATIONS.LOADER_DATA_VALUES_TEXT_RUNTIME_TARGET.toString(), new Object[] {}, new Object[] {});
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LOADER VALUES TEXT RUNTIME");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	/**
	 * 
	 * Inclui um novo valor do tipo texto em tempo de execução no banco dados por coluna - Id
	 * 
	 * @param columnId - Id da coluna associada ao valor
	 * @param valDescription - Descrição do valor
	 * @param valContent - Conteúdo do valor
	 * @param numberLine - Número da linha
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public boolean autInsertValueTextRuntimeByColumnId(String columnId, String valDescription,String valContent, String numberLine) {
		try {
			
			System.out.println("AUT INFO: INSERT VALUE TEXT RUNTIME BY COLUMN ID");
			
			autExecSubStatements(AUT_DATA_VALUE_TEXT_RUNTIME_OPERATIONS.INSERT_DATA_VALUE_TEXT_RUNTIME_BY_COLUMN_ID.toString(), 
					new Object[] {columnId, valDescription,valContent, numberLine});
			
			return true;
		}
		catch(java.lang.Exception e) {
		
			System.out.println("AUT ERROR: INSERT VALUE TEXT RUNTIME BY COLUMN ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}		
	}
	
	/**
	 * 
	 * Lista todos os valores das colunas na linha especificada em tempo de execução
	 * 
	 * @param lineNumber - Número da linha
	 * 
	 * @return java.util.List(Object) - Lista de valores na linha especificada por id
	 * 
	 */
	public java.util.List<Object> autListValuesTextRuntimeByLineNumber(String lineNumber){
		try {
			
			System.out.println("AUT INFO: LIST VALUES TEXT RUNTIME BY LINE NUMBER");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_VALUE_TEXT_RUNTIME_OPERATIONS.SELECT_DATA_VALUES_TEXT_RUNTIME_BY_ROW_NUMBER.toString(), 
					new Object[] {lineNumber});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST VALUES TEXT RUNTIME BY LINE NUMBER");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * 
	 * Lista todos os valores do tipo texto em tempo de execução para uma tabela específica
	 * 
	 * 
	 * @param columnId - Id da tabela de dados proprietária
	 * 
	 * @return java.util.List(Object) - Lista de valores do tipo objeto para tabela específica
	 * 
	 */
	public java.util.List<Object> autListValuesTextRuntimeByColumnId(String columnId){
		try {
			System.out.println("AUT INFO: LIST VALUES TEXT RUNTIME BY COLUMN ID");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_VALUE_TEXT_RUNTIME_OPERATIONS.SELECT_DATA_VALUES_TEXT_RUNTIME_COLUMN_ID.toString(), 
					new Object[] {columnId});
		
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST VALUES TEXT RUNTIME BY COLUMN ID");
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
	public AUTDBDataValueTextRuntime() {
		// TODO Auto-generated constructor stub
	}

}
