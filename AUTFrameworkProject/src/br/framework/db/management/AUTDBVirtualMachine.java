/**
 * 
 */
package br.framework.db.management;

/**
 * Gerenciamento de máquinas virtuais
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBVirtualMachine extends AUTDBPhysicalWorkStation {

	/**
	 *
	 *Define os comandos de gerenciamento para máquinas virtuais
	 * 
	 *@author Softtek-QA
	 *
	 */
	public enum AUT_VIRTUAL_MACHINE_OPERATIONS{
		INSERT_VIRTUAL_MACHINE,
		SELECT_ALL_VIRTUAL_MACHINE,
		SELECT_VIRTUAL_MACHINE_BY_RESOURCE,
		SELECT_VIRTUAL_MACHINE_BY_SERVICE;
		
		@Override
		public String toString() {
			switch(this) {
			case INSERT_VIRTUAL_MACHINE:{
				return "INSERT INTO lry.aut_virtual_hosts_services(RSC_ID, SVR_ID, VM_NAME, VM_DESCRIPTION, VM_IP_ADDRESS, VM_PORT_RDP, "
						+ "VM_PORT_STATE, VM_IP_SERVER_HYPERVISOR, VM_SERVER_PORT_RDP, VM_SERVER_PORT_STATE,VM_TYPE_OS) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
			}
			case SELECT_ALL_VIRTUAL_MACHINE:{
				return "SELECT VM_ID FROM lry.aut_virtual_hosts_services;";
			}
			case SELECT_VIRTUAL_MACHINE_BY_RESOURCE:{
				return "SELECT VM_ID FROM lry.aut_virtual_hosts_services WHERE RSC_ID=?";
			}
			case SELECT_VIRTUAL_MACHINE_BY_SERVICE:{
				return "SELECT VM_ID FROM lry.aut_virtual_hosts_services WHERE SVR_ID=?;";
			}
			default:{
				return this.name();
			}			
			}
		}
	}
	
	public boolean autInsertVirtualMachineByResourceId(String rscId, String svrId, String vmName, String vmDescription, String vmIpAddress, String vmPortRDP,String vmStateOper, String serverHyperVIP, String serverHyperVPortRDP, String serverHyperVPortState,String OSType) {
		try {
			
			System.out.println("AUT INFO: INSERT VIRTUAL MACHINE BY RESOURCE ID");
			
			autExecSubStatements(AUT_VIRTUAL_MACHINE_OPERATIONS.INSERT_VIRTUAL_MACHINE.toString(), 
					new Object[] {rscId,svrId,vmName,vmDescription,vmIpAddress,vmPortRDP,vmStateOper,serverHyperVIP,serverHyperVPortRDP,serverHyperVPortState,OSType});
		
			return true;
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: INSERT VIRTUAL MACHINE BY RESOURCE ID");
			
			return false;
		}
	}
	
	/**
	 * 
	 * Lista todas as máquinas virtuais cadastradas no sistema
	 * 
	 * @return java.util.List(Object) - Lista de máquinas virtuais cadastradas no sistema
	 * 
	 */
	public java.util.List<Object> autListVirtualMachineAll(){
		try {
			
			System.out.println("AUT INFO: LIST ALL VIRTUAL MACHINE");
			
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_VIRTUAL_MACHINE_OPERATIONS.SELECT_ALL_VIRTUAL_MACHINE.toString(), 
					new Object[] {});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: LIST ALL VIRTUAL MACHINE");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * Lista todas as máquinas virtuais associadas ao item de configuração do recurso
	 * 
	 * @param idResource - Id do item de configuração do recurso
	 * 
	 * @return java.util.List(Object) - Lista de máquinas virtuais cadastradas no sistema para o item de configuração informado
	 * 
	 */
	public java.util.List<Object> autListVirtualMachine(String idResource){
		try {
			
			System.out.println("AUT INFO : LIST ALL VIRTUAL MACHINE BY RESOURCE ID");			
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_VIRTUAL_MACHINE_OPERATIONS.SELECT_VIRTUAL_MACHINE_BY_RESOURCE.toString(), 
					new Object[] {idResource});
			
			return autGetListItems(rsData);
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: LIST ALL VIRTUAL MACHINE BY RESOURCE ID");
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
	public AUTDBVirtualMachine() {
		// TODO Auto-generated constructor stub
		super();
	}

}
