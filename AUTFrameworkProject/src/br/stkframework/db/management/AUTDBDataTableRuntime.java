/**
 * 
 */
package br.stkframework.db.management;

import br.stkframework.db.management.AUTDBDataTable.AUT_DATA_TABLE_OPERATIONS;

/**
 * Gerenciamento de tabelas em tempo de execução relacionadas a um fluxo de dados
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBDataTableRuntime extends AUTDBDataFlow {
	AUTDBDataTable dbTbl = null; //Tabela local de origem dos matriz - Matriz externa
	
	/**
	 * 
	 * Comandos para o gerenciamento de tabela em tempo de execução
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_DATA_TABLE_RUNTIME_OPERATIONS{
		INSERT_DATA_TABLE_RUNTIME_BY_DATA_FLOW,
		SELECT_DATA_TABLE_RUNTIME_BY_DATA_FLOW,
		SELECT_ALL_DATA_TABLE_RUNTIME,
		SELECT_ALL_DATA_TABLE_RUNTIME_WITH_EXTERN_ID,
		LOADER_DATA_TABLE_RUNTIME,
		CLEAR_DATA_TABLE_RUNTIME;
		
		
		@Override
		public String toString() {
			switch(this) {
			case CLEAR_DATA_TABLE_RUNTIME:{
				return "DELETE FROM lry.aut_data_tables_runtime WHERE PRC_DAT_ID=?;";
			}
			case LOADER_DATA_TABLE_RUNTIME:{
				return "INSERT INTO lry.aut_data_tables_runtime(TBL_EXTERN_ID,TBL_ID,PRC_DAT_ID, TBL_NAME, TBL_DESCRIPTION, TBL_STATE_INCLUDE, TBL_STATE_READ,TBL_STATE_UPDATE,TBL_STATE_DELETE, TBL_WAIT_TIME, TBL_WAIT_SIGNAL, TBL_CREATION_DATE, TBL_UPDATE_DATE,TBL_SIGNAL) SELECT TBL_ID,TBL_ID,PRC_DAT_ID,TBL_NAME, TBL_DESCRIPTION, TBL_STATE_INCLUDE, TBL_STATE_READ,TBL_STATE_UPDATE,TBL_STATE_DELETE, TBL_WAIT_TIME, TBL_WAIT_SIGNAL, TBL_CREATION_DATE, TBL_UPDATE_DATE,TBL_SIGNAL FROM lry.aut_data_tables WHERE PRC_DAT_ID=?;";
			}
			case INSERT_DATA_TABLE_RUNTIME_BY_DATA_FLOW:{
				return "INSERT INTO lry.aut_data_tables_runtime(PRC_DAT_ID, TBL_NAME, TBL_DESCRIPTION, TBL_STATE_INCLUDE, TBL_STATE_READ, TBL_STATE_UPDATE, TBL_STATE_DELETE, TBL_WAIT_TIME, TBL_WAIT_SIGNAL, TBL_SIGNAL) VALUES(?,?,?,?,?,?,?,?,?,?);";
			}
			case SELECT_ALL_DATA_TABLE_RUNTIME:{
				return "SELECT TBL_ID FROM lry.aut_data_tables_runtime;";
			}
			case SELECT_ALL_DATA_TABLE_RUNTIME_WITH_EXTERN_ID:{
				return "SELECT TBL_EXTERN_ID FROM lry.aut_data_tables_runtime;";
			}
			case SELECT_DATA_TABLE_RUNTIME_BY_DATA_FLOW:{
				return "SELECT TBL_ID FROM lry.aut_data_tables_runtime WHERE PRC_DAT_ID=?";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	/**
	 * Recupera a instancia corrente do tabela de dados matriz associada 
	 * @param <DBTable> - Tabela de dados
	 * 
	 * @return DBTable - Classe que extende a classe principal - AUTDBDataTable
	 */
	public <DBTable extends AUTDBDataTable> DBTable autGetDataTable() {
		try {
			
			System.out.println("AUT INFO : GETTING DATA TABLE INSTANCE");			 
			if(dbTbl == null) {
				dbTbl = new AUTDBDataTable();
			}
						
			dbTbl.autSetConnection(this.getActiveConnection());
			
			return (DBTable)dbTbl;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: GETTING DATA TABLE INSTANCE");
			
			return null;
		}
	}
	/**
	 * 
	 * Altera a tabela de dados principal associada ao objeto local
	 * 
	 * @param tabela - Tabela de dados origem
	 * @param <DBTable> - Tabela de dados
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso constrário
	 * 
	 */
	public <DBTable extends AUTDBDataTable> boolean autChangeDataTableOrigem(DBTable tabela) {
		try {
			System.out.println("AUT INFO: CHANGE DATATABLE ATTACHED WITH THIS OBJECT");
			dbTbl = tabela;		
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: CHANGE DATATABLE ATTACHED WITH THIS OBJECT");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	
	/**
	 * Carrega a tabela de dados em tempo de execução
	 * 
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public boolean autLoaderTableRuntimeByProcessId() {
		try {
			java.util.List<Object> listTbl = autGetDataTable().autListAllDataTable();
			for(Object tblId: listTbl) {
				System.out.println("AUT INFO: LOADER TABLE RUNTIME BY DATA PROCESSO ID");
				autExecSubStatements(AUT_DATA_TABLE_RUNTIME_OPERATIONS.CLEAR_DATA_TABLE_RUNTIME.toString(), new Object[] {tblId.toString()});
				System.out.println("AUT INFO : CLEAR TABLE RUNTIME FOR RELOADER");
				autExecSubStatements(AUT_DATA_TABLE_RUNTIME_OPERATIONS.LOADER_DATA_TABLE_RUNTIME.toString(), new Object[] {tblId.toString()});			
				System.out.println("AUT INFO: FINISHED LOADER");
			}
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LOADER TABLE RUNTIME BY DATA PROCESS ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	
	/**
	 * Inclui uma nova tabela em tempo de execução ao fluxo de dados
	 * 
	 * 
	 * @param dataFlowId - Id do fluxo de dados
	 * @param tblName - Nome da tabela
	 * @param tblDescription - Descrição da tabela
	 * @param stateInclude - Registro em modo de inclusão
	 * @param stateRead - Registro em modo de leitura
	 * @param stateUpdate - Registro em modo  de atualização
	 * @param stateDelete - Registro em modo de exclusão
	 * @param sincForTime - Sincronização por tempo
	 * @param sincForSignal - Sincronização por sinal
	 * @param signalSinc - Sinal para sincronização
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 */
	public boolean autInsertDataTableRuntimeByDataFlow(String dataFlowId, String tblName, String tblDescription, boolean stateInclude, boolean stateRead, boolean stateUpdate, boolean stateDelete, boolean sincForTime, boolean sincForSignal, String signalSinc) {
		try {
			System.out.println("AUT INFO: INSERT DATA TABLE RUNTIME BY DATA FLOW ID");
			autExecSubStatements(AUT_DATA_TABLE_RUNTIME_OPERATIONS.INSERT_DATA_TABLE_RUNTIME_BY_DATA_FLOW.toString(), 
					new Object[] {dataFlowId, tblName, tblDescription, stateInclude, stateRead, stateUpdate, stateDelete, sincForTime, sincForSignal, signalSinc});
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: INSERT DATA TABLE RUNTIME BY DATA FLOW ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	
	/**
	 * 
	 * Lista todas as tabelas em tempo de execução cadastradas no sistema
	 * 
	 * @return java.util.List(Object) - Lista de tabelas do sistema por id
	 * 
	 */
	public java.util.List<Object> autListAllDataTableRuntime(){
		try {
			System.out.println("AUT INFO: LIST ALL DATA TABLES RUNTIME FROM ALL DATA FLOW");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_TABLE_RUNTIME_OPERATIONS.SELECT_ALL_DATA_TABLE_RUNTIME.toString(), 
					new Object[] {});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: LIST ALL DATA TABLES RUNTIME FROM ALL DATA FLOW");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * 
	 * Lista todas as tabelas em tempo de execução cadastradas no sistema para o fluxo de dado específico
	 * 
	 * @param idDataFlow - Id do fluxo de dados
	 * 
	 * @return java.util.List(Object) - Lista de tabelas em tempo de execução associadas ao fluxo de dados por id
	 * 
	 */
	public java.util.List<Object> autListDataTableRuntimeByDataFlowId(String idDataFlow){
		try {
			System.out.println("AUT INFO: LIST DATA TABLE RUNTIME BY DATA FLOW");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_TABLE_RUNTIME_OPERATIONS.SELECT_DATA_TABLE_RUNTIME_BY_DATA_FLOW.toString(), 
					new Object[] {idDataFlow});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST DATA TABLE RUNTIME BY DATA FLOW ID");
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
	public AUTDBDataTableRuntime() {
		// TODO Auto-generated constructor stub
	}

}
