/**
 * 
 */
package br.stk.framework.db.management;

/**
 * Gerenciamento de estações de trabalho físicas
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBPhysicalWorkStation extends AUTDBResourcesConfiguration {

	/**
	 * 
	 * Define as operações básicas para gerenciamento de estações de trabalho
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_PHYSICAL_HOST_OPERATIONS{
		INSERT_WORKSTATION_BY_RESOURCE_ID,
		SELECT_ALL_WORKSTATION,
		SELECT_WORKSTATION_BY_RESOURCE_ID;
		
		@Override
		public String toString() {
			switch(this) {
			case INSERT_WORKSTATION_BY_RESOURCE_ID:{
				return "INSERT INTO lry.aut_physical_hosts(RSC_ID, SVR_ID, DSK_NAME, DSK_DESCRIPTION, DSK_IP_ADDRESS, DSK_PORT_RDP, "
						+ "DSK_PORT_STATE, DSK_IP_SERVER_HYPERVISOR, DSK_SERVER_PORT_RDP, DSK_SERVER_PORT_STATE) VALUES(?,?,?,?,?,?,?,?,?,?);";
			}
			case SELECT_ALL_WORKSTATION:{
				return "SELECT DSK_ID FROM lry.aut_physical_hosts";
			}
			case SELECT_WORKSTATION_BY_RESOURCE_ID:{
				return "SELECT DSK_ID FROM lry.aut_physical_hosts WHERE RSC_ID=?;";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	/**
	 * 
	 * Inseri uma nova estação de trabalho associada ao recurso de configuração
	 * 
	 * @param rscId - Id do item de configuração de recurso
	 * @param svrId - Id do item de configuração de serviço
	 * @param dskName - Nome da máquina - HostName
	 * @param dskDescription - Descrição da máquina - Finalidade, recursos e etc...
	 * @param dskIP - Ip da máquina na rede - Se utiliza configuração de IP estática
	 * @param dskPortRDP - Porta RDP de conexão
	 * @param dskPortState - Estado operacional da porta de conexão
	 * @param dskIpServHypervidor - Ip do servidor de virtualização - Caso dependa de algum recurso hypervisor
	 * @param servHypervisorPortRDP - Porta de conexão RDP no hypervisor
	 * @param servHypervisorPortState - Estado da porta de conexão no hypervisor
	 * 
	 * @return boolean - Caso a operação seja realizada com sucesso false caso contrário
	 * 
	 * 
	 */
	public boolean autInsertWorkStation(String rscId, String svrId,String dskName, String dskDescription,String dskIP, String dskPortRDP, String dskPortState, String dskIpServHypervidor, String servHypervisorPortRDP, String servHypervisorPortState) {
		try {
			
			System.out.println("AUT INFO : INSERT WORKSTATION BY PROJECT ID");
			
			autExecSubStatements(AUT_PHYSICAL_HOST_OPERATIONS.INSERT_WORKSTATION_BY_RESOURCE_ID.toString(), 
					new Object[] {rscId,svrId,dskName,dskDescription,dskIP,dskPortRDP,dskPortState,dskIpServHypervidor,servHypervisorPortRDP,servHypervisorPortState});
			
			return true;
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: INSERT WORKSTATION BY RESOURCE ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	
	/**
	 * 
	 * Lista todas as estações de trabalho de todos os projetos já cadastradas no sistema
	 * 
	 * @return java.util.List(String) - Lista de máquinas físicas cadastradas no sistema
	 * 
	 */
	public java.util.List<Object> autListWorkStationAll(){
		try {
			System.out.println("AUT INFO : LIST ALL WORKSTATION OF CONFIGURATION RESOURCES");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_PHYSICAL_HOST_OPERATIONS.SELECT_ALL_WORKSTATION.toString(), 
					new Object[] {});
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL WORKSTATION OF CONFIGURATION RESOURCES");
			System.out.println(e.getMessage());
			e.printStackTrace();			
			return null;
		}		
	}
	
	/**
	 * 
	 * Lista todas as estações de trabalho associadas ao um projeto
	 * 
	 * @param idProject - Id do projeto
	 * @return java.util.List(Object) - Lista de estações de trabalho associadas ao projeto
	 * 
	 */
	public java.util.List<Object> autListWorkStationByResourceId(String idProject){
		try {
			
			System.out.println("AUT INFO: LIST WORKSTATION BY RESOURCE ID");
			
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_PHYSICAL_HOST_OPERATIONS.SELECT_WORKSTATION_BY_RESOURCE_ID.toString(), 
					new Object[] {idProject});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST WORKSTATION BY RESOURCE ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
						
			return null;
			
		}
	}
	
	/**
	 * 
	 * Construtor padrão da classe
	 */
	public AUTDBPhysicalWorkStation() {
		super();
	}

}
