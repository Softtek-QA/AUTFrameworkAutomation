/**
 * 
 */
package br.stkframework.db.management;

/**
 * Gerenciamento de tabelas relacionadas a um fluxo de dados
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBDataTable extends AUTDBDataFlow {
	/**
	 * 
	 * Comandos para o gerenciamento de tabela
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_DATA_TABLE_OPERATIONS{
		INSERT_DATA_TABLE_BY_DATA_FLOW,
		SELECT_DATA_TABLE_BY_DATA_FLOW,
		SELECT_ALL_DATA_TABLE;
		
		@Override
		public String toString() {
			switch(this) {
			case INSERT_DATA_TABLE_BY_DATA_FLOW:{
				return "INSERT INTO lry.aut_data_tables(PRC_DAT_ID, TBL_NAME, TBL_DESCRIPTION, TBL_STATE_INCLUDE, TBL_STATE_READ, TBL_STATE_UPDATE, TBL_STATE_DELETE, TBL_WAIT_TIME, TBL_WAIT_SIGNAL, TBL_SIGNAL) VALUES(?,?,?,?,?,?,?,?,?,?);";
			}
			case SELECT_ALL_DATA_TABLE:{
				return "SELECT TBL_ID FROM lry.aut_data_tables;";
			}
			case SELECT_DATA_TABLE_BY_DATA_FLOW:{
				return "SELECT TBL_ID FROM lry.aut_data_tables WHERE PRC_DAT_ID=?";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	/**
	 * Inclui uma nova tabela ao fluxo de dados
	 * 
	 * 
	 * @param dataFlowId - Id do fluxo de dados
	 * @param tblName - Nome da tabela
	 * @param tblDescription - Descri��o da tabela
	 * @param stateInclude - Registro em modo de inclus�o
	 * @param stateRead - Registro em modo de leitura
	 * @param stateUpdate - Registro em modo  de atualiza��o
	 * @param stateDelete - Registro em modo de exclus�o
	 * @param sincForTime - Sincroniza��o por tempo
	 * @param sincForSignal - Sincroniza��o por sinal
	 * @param signalSinc - Sinal para sincroniza��o
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contr�rio
	 */
	public boolean autInsertDataTableByDataFlow(String dataFlowId, String tblName, String tblDescription, boolean stateInclude, boolean stateRead, boolean stateUpdate, boolean stateDelete, boolean sincForTime, boolean sincForSignal, String signalSinc) {
		try {
			System.out.println("AUT INFO: INSERT DATA TABLE BY DATA FLOW ID");
			autExecSubStatements(AUT_DATA_TABLE_OPERATIONS.INSERT_DATA_TABLE_BY_DATA_FLOW.toString(), 
					new Object[] {dataFlowId, tblName, tblDescription, stateInclude, stateRead, stateUpdate, stateDelete, sincForTime, sincForSignal, signalSinc});
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: INSERT DATA TABLE BY DATA FLOW ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	
	/**
	 * 
	 * Lista todas as tabelas cadastradas no sistema
	 * 
	 * @return java.util.List(Object) - Lista de tabelas do sistema por id
	 * 
	 */
	public java.util.List<Object> autListAllDataTable(){
		try {
			System.out.println("AUT INFO: LIST ALL DATA TABLES FROM ALL DATA FLOW");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_TABLE_OPERATIONS.SELECT_ALL_DATA_TABLE.toString(), 
					new Object[] {});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: LIST ALL DATA TABLES FROM ALL DATA FLOW");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * 
	 * Lista todas as tabelas cadastradas no sistema para o fluxo de dado espec�fico
	 * 
	 * @param idDataFlow - Id do fluxo de dados
	 * 
	 * @return java.util.List(Object) - Lista de tabelas associadas ao fluxo de dados por id
	 * 
	 */
	public java.util.List<Object> autListDataTableByDataFlowId(String idDataFlow){
		try {
			System.out.println("AUT INFO: LIST DATA TABLE BY DATA FLOW");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_TABLE_OPERATIONS.SELECT_DATA_TABLE_BY_DATA_FLOW.toString(), 
					new Object[] {idDataFlow});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST DATA TABLE BY DATA FLOW ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	/**
	 * 
	 * Construtor padr�o
	 * 
	 */
	public AUTDBDataTable() {
		// TODO Auto-generated constructor stub
	}

}
