/**
 * 
 */
package br.framework.db.management;

/**
 * 
 * Gerenciamento de valores do tipo object- Para colunas
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBDataValueObject extends AUTDBDataColumn {
	
	/**
	 * 
	 * Comandos para o gerenciamento de valores do tipo Object
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_DATA_VALUE_OBJECT_OPERATIONS{
		INSERT_DATA_VALUE_OBJECT_BY_COLUMN_ID,
		SELECT_DATA_VALUES_OBJECT_BY_ROW_NUMBER,
		SELECT_DATA_VALUES_OBJECT_COLUMN_ID;
		
		@Override
		public String toString() {
			switch(this) {
			case INSERT_DATA_VALUE_OBJECT_BY_COLUMN_ID:{
				return "INSERT INTO lry.aut_column_values_object(COL_DAT_ID, VAL_DESCRIPTION, VAL_CONTENT, VAL_NUMBER_LINE) VALUES(?,?,?,?);";
			}		
			case SELECT_DATA_VALUES_OBJECT_COLUMN_ID:{
				return "SELECT VAL_ID FROM LRY.AUT_COLUMN_VALUES_OBJECT AS A WHERE A.COL_DAT_ID=?;";
			}
			case SELECT_DATA_VALUES_OBJECT_BY_ROW_NUMBER:{
				return "SELECT VAL_ID FROM lry.aut_column_values_object WHERE VAL_NUMBER_LINE=?";
			}
			default:{
				return this.name();
			}			
			}
		}
	}
	
	
	/**
	 * 
	 * Inclui um novo valor do tipo objeto no banco dados por coluna - Id
	 * 
	 * @param columnId - Id da coluna associada ao valor
	 * @param valDescription - Descrição do valor
	 * @param valContent - Conteúdo do valor
	 * @param numberLine - Número da linha
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public boolean autInsertValueObjectByColumnId(String columnId, String valDescription,String valContent, String numberLine) {
		try {
			
			System.out.println("AUT INFO: INSERT VALUE OBJECT BY COLUMN ID");
			
			autExecSubStatements(AUT_DATA_VALUE_OBJECT_OPERATIONS.INSERT_DATA_VALUE_OBJECT_BY_COLUMN_ID.toString(), 
					new Object[] {columnId, valDescription,valContent, numberLine});
			
			return true;
		}
		catch(java.lang.Exception e) {
		
			System.out.println("AUT ERROR: INSERT VALUE OBJECT BY COLUMN ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}		
	}
	/**
	 * 
	 * Lista todos os valores das colunas na linha especificada
	 * 
	 * @param lineNumber - Número da linha
	 * 
	 * @return java.util.List(Object) - Lista de valores na linha especificada por id
	 * 
	 */
	public java.util.List<Object> autListValuesObjectByLineNumber(String lineNumber){
		try {
			
			System.out.println("AUT INFO: LIST VALUES OBJECT BY LINE NUMBER");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_VALUE_OBJECT_OPERATIONS.SELECT_DATA_VALUES_OBJECT_BY_ROW_NUMBER.toString(), 
					new Object[] {lineNumber});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST VALUES OBJECT BY LINE NUMBER");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	/**
	 * 
	 * Lista todos os valores do tipo objeto para uma tabela específica
	 * 
	 * 
	 * @param columnId - Id da tabela de dados proprietária
	 * 
	 * @return java.util.List(Object) - Lista de valores do tipo objeto para tabela específica
	 * 
	 */
	public java.util.List<Object> autListValuesObjectByColumnId(String columnId){
		try {
			System.out.println("AUT INFO: LIST VALUES OBJECT BY COLUMN ID");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_VALUE_OBJECT_OPERATIONS.SELECT_DATA_VALUES_OBJECT_COLUMN_ID.toString(), 
					new Object[] {columnId});
		
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST VALUES OBJECT BY COLUMN ID");
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
	public AUTDBDataValueObject() {
		// TODO Auto-generated constructor stub
	}

}
