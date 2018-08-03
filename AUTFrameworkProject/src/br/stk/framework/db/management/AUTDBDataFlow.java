/**
 * 
 */
package br.stk.framework.db.management;

/**
 * 
 * Gerenciamento de dados do processo de neg�cio
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBDataFlow extends AUTDBDataSet {
	
	/**
	 * 
	 * Comandos para gerenciamento dos fluxos de dados
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_DATA_FLOW_OPERATIONS{
		INSERT_DATA_FLOW,
		SELECT_DATA_FLOW_BY_DATASET_ID,
		SELECT_ALL_DATA_FLOW;
		
		@Override
		public String toString() {
			switch(this) {
			case INSERT_DATA_FLOW:{
				return "INSERT INTO lry.aut_process_data(DAT_ID, PRC_STATE_INCLUDE, PRC_STATE_READ, PRC_STATE_UPDATE, PRC_STATE_DELETE, PRC_WAIT_TIME, PRC_WAIT_SIGNAL, PRC_SIGNAL, PRC_TIME_INCLUDE, PRC_TIME_READ, PRC_TIME_UPDATE, PRC_TIME_DELETE, PRC_TIME_UNIT) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
			}
			case SELECT_ALL_DATA_FLOW:{
				return "SELECT PRC_DAT_ID FROM lry.aut_process_data;";
			}
			case SELECT_DATA_FLOW_BY_DATASET_ID:{
				return "SELECT PRC_DAT_ID FROM lry.aut_process_data WHERE DAT_ID=?";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	
	/**
	 * 
	 * Inclui um novo fluxo de dados para um dataset
	 * 
	 * @param dataSetId - Id do dataset propriet�rio
	 * @param prcStateInclude - modo operacional habilitado - Inclus�o
	 * @param prcStateRead - modo operacional habilitado - Leitura de registro
	 * @param prcStateUpdate - modo operacional habilitado - Sincroniza��o de registro
	 * @param prcStateDelete - modo operacional habilitado - Remo��o de registros
	 * @param timeInclude - Tempo padr�o de inclus�o
	 * @param timeRead - Tempo padr�o de leitura
	 * @param prcUpdate - Tempo padr�o de sincroniza��o
	 * @param prcDelete - Tempo padr�o de remo��o
	 * @param prcTypeTimeUnity - Unidade de tempo - para sincroniza��o dos modos de : inclus�o, leitura, atualiza��o e remo��o
	 * @param waitTimeSyncronize - Tempo de espera
	 * @param waitSignalSyncronize - Tempo de espera para emiss�o de sinal
	 * @param signalSyncronize - Tempo de espera entre sincroniza��es
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contr�rio
	 * 
	 */
	public boolean autInsertDataFlowByDataSetId(String dataSetId, boolean prcStateInclude, boolean prcStateRead, boolean prcStateUpdate,boolean prcStateDelete,boolean waitTimeSyncronize,boolean waitSignalSyncronize,String signalSyncronize, int timeInclude,int timeRead, int prcUpdate, int prcDelete, String prcTypeTimeUnity) {
		try {
			System.out.println("AUT INFO: INSERT DATA FLOW BY DATASET ID");
			autExecSubStatements(AUT_DATA_FLOW_OPERATIONS.INSERT_DATA_FLOW.toString(), 
					new Object[] {dataSetId,prcStateInclude,prcStateRead, prcStateUpdate,prcStateDelete,waitTimeSyncronize,waitSignalSyncronize,signalSyncronize,timeInclude,timeRead,prcUpdate,prcDelete,prcTypeTimeUnity});
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: INSERT DATA FLOW BY DATASET ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}		
	}

	/**
	 * Lista todos os fluxos de dados de todos os data set
	 * 
	 * @return java.util.List(Object) - Lista de Fluxos definidos no sistema por id
	 */
	public java.util.List<Object> autListAllDataFlow(){
		try {
			
			System.out.println("AUT INFO: LIST ALL DATA FLOW FROM ALL DATASET");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_FLOW_OPERATIONS.SELECT_ALL_DATA_FLOW.toString(), 
					new Object[] {});
			
			return autGetListItems(rsData);
			
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL DATAFLOW FROM ALL DATASET");
			System.out.println(e.getMessage());
			e.printStackTrace();			
			return null;
		}
	}
	
	/**
	 * 
	 * Lista os fluxos de dados cadastrados no sistema para dataset 
	 * 
	 * @param dataSetId - Id do dataset propriet�rio
	 * 
	 * @return java.util.List(Object) - Lista com id dos fluxos de dados por id
	 * 
	 */
	public java.util.List<Object> autListDataFlowByDataSetId(String dataSetId){
		try {
			System.out.println("AUT INFO: LIST DATA FLOW BY DATASET ID");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_DATA_FLOW_OPERATIONS.SELECT_DATA_FLOW_BY_DATASET_ID.toString(), 
					new Object[] {dataSetId});
		
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST DATA FLOW BY DATASET ID");
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
	public AUTDBDataFlow() {
		// TODO Auto-generated constructor stub
	}

}
