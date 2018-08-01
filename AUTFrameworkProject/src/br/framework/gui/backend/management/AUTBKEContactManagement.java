/**
 * 
 */
package br.framework.gui.backend.management;

import br.framework.db.connectors.AUTDBUtils.AUT_TYPE_COMPARE_PROPERTIES;
import br.framework.db.management.AUTDBContacts.AUT_CONTACTS_OPERATIONS;
import br.framework.db.management.AUTDBContacts.AUT_CONTACTS_SQL_PROPERTIES;
import br.framework.gui.backend.management.AUTBKEContactManagement.AUT_CONTACT_GUI_PROPERTIES;
import br.framework.gui.backend.management.AUTBKEUtils.AUTGUITable;
import br.framework.gui.backend.management.AUTBKEUtils.AUTGUITreeObject;
import br.framework.gui.logs.AUTLogsManage;

/**
 * Gerenciamento de contatos via GUI
 * 
 * @author Softtek-QA
 *
 */
public class AUTBKEContactManagement extends AUTBKEProjectManament {	
	
	/**
	 * Definição de propriedades de configuração GUI
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_CONTACT_GUI_PROPERTIES{
		ID_PROJECT,
		ID,
		NAME,
		TEL_PHONE,
		MOB_PHONE,
		EMAIL,
		SUBJECT_USER,
		NEW_VALUE;
		
		@Override
		public String toString() {
			switch(this) {
			case ID:{
				return "ID";
			}
			case ID_PROJECT:{
				return "ID PROJETO";
			}
			case MOB_PHONE:{
				return "CELULAR";
			}
			case NAME:{
				return "NOME";
			}
			case SUBJECT_USER:{
				return "ASSUNTO";
			}
			case TEL_PHONE:{
				return "TELEFONE";
			}
			case EMAIL:{
				return "EMAIL";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	
	/**
	 * 
	 * Valida as propriedades configuradas para tabela de contatos
	 * 
	 * @param projId - Id do projeto
	 * 
	 * @return boolean - True caso o processo seja realizado com sucesso false caso contrário
	 * 
	 */
	public boolean autValidationContactsProperties(String projId) {
		try {
			System.out.println("AUT INFO: VALIDATION PROPERTIES BY CONTACT OBJECT");
			autGetDataTableByProperties(AUT_CONTACTS_OPERATIONS.SELECT_ALL_COLUMNS_FROM_CONTACT_BY_PROJ_ID.toString(), AUT_CONTACTS_SQL_PROPERTIES.class, new Object[] {projId});			
			return true;
		}
		catch(java.lang.Exception e) {
			AUTLogsManage.registrarLog(e, "AUT ERROR: VALIDATION PROPERTIES BY CONTACT OBJECT");

			return false;
		}
	}
	
	/**
	 * 
	 * 
	 * Remove o contato pelo - ID
	 * 
	 * @param project - Objeto Projeto base
	 * @param idContact - Id do contato para exclusão
	 * @param <TProject> - Objeto projeto base
	 * 
	 * @return boolean - Caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public <TProject extends AUTBKEProjectManament> boolean	autGUIRemoveContact(TProject project,String idContact) {
		try {
			
			System.out.println(String.format("AUT INFO: REMOVE CONTACT BY ID : %s : INIT",idContact));
			
			project.autExecSubStatements(AUT_CONTACTS_OPERATIONS.DELETE_CONTACTS_BY_PROJECT_ID.toString(), new Object[] {idContact});
			
			System.out.println(String.format("AUT INFO: REMOVE CONTACT BY ID : %s : END",idContact));
			
			return true;
		}
		catch(java.lang.Exception e) {
			
			AUTLogsManage.registrarLog(e, "AUT ERROR: REMOVE CONTACT BY ID");
						
			return false;
		}
	}
	/**
	 * 
	 * Carrega estrutura de dados em uma tabela que pode ser associada a um componente GUI - JTable + Listener de monitoramento de eventos
	 * 
	 * 
	 * @param autDBProject - Objeto base do projeto
	 * @param projectId - Id do projeto
	 * @param <TProject> - Objeto projeto base
	 * 
	 * @return AUTGUITable - Modelo de dados - DefaultTableModel
	 * 
	 */
	public <TProject extends AUTBKEProjectManament> AUTGUITable autGUIGetNewTableFromContacts(TProject autDBProject,String projectId) {		
		return new AUTGUITable(
				autDBProject, 
				AUT_CONTACTS_OPERATIONS.SELECT_ALL_COLUMNS_FROM_CONTACT_BY_PROJ_ID.toString(),
				AUT_CONTACTS_SQL_PROPERTIES.class, 
				autDBProject.autGetValueByEnumProperty(AUT_TYPE_COMPARE_PROPERTIES.ENUMERATION_NAME, AUT_CONTACTS_SQL_PROPERTIES.class, AUT_CONTACT_GUI_PROPERTIES.class), 
				new Object[] {projectId}
		);
	}
	
	/**
	 * 
	 * Construtor padrão
	 * 
	 */
	public AUTBKEContactManagement() {
		// TODO Auto-generated constructor stub
	}

	public AUTBKEContactManagement(AUTBKEProjectManament project) {
		autSetConnection(project.getActiveConnection());
	}

}
