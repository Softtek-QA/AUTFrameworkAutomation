/**
 * 
 */
package br.stk.framework.db.management;

import br.stk.framework.gui.logs.AUTLogsManage;

/**
 * 
 * Classe responsável pelo gerenciamento de contatos relacionados ao projeto
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBContacts extends AUTDBProject {


	/**
	 * 
	 * Definição de propriedades SQL
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_CONTACTS_SQL_PROPERTIES{
		ID,
		ID_PROJECT,
		NAME,
		TEL_PHONE,
		MOB_PHONE,
		EMAIL,
		SUBJECT_USER;

		@Override
		public String toString() {
			switch(this) {
			case ID:{
				return "CTC_ID";
			}
			case EMAIL:{
				return "CTC_EMAIL";
			}
			case ID_PROJECT:{
				return "PJT_ID";
			}
			case MOB_PHONE:{
				return "CTC_MOB_PHONE";
			}
			case NAME:{
				return "CTC_NAME";
			}
			case SUBJECT_USER:{
				return "CTC_SUBJECT_BY_USER";
			}
			case TEL_PHONE:{
				return "CTC_TEL_PHONE";
			}
			default:{
				return "CTC_ID";
			}
			}			
		}
	}

	/**
	 * 
	 * Define os comandos para gerenciamento de contatos relacionados aos projetos
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_CONTACTS_OPERATIONS{
		INSERT_CONTACT_BY_PROJECT_ID,
		SELECT_CONTACTS_BY_PROJECT_ID,
		SELECT_ALL_COLUMNS_FROM_CONTACT_BY_PROJ_ID,
		SELECT_ALL_COLUMNS_FROM_CONTACT,
		UPDATE_CONTACTS_BY_PROJECT_ID,
		DELETE_CONTACTS_BY_PROJECT_ID,
		DEFAULT_TABLE_IN_SGDB_APLICATION,
		DEFAULT_TABLE_CONDITION_FOR_ALL_OPERATION;		
		@Override
		public String toString() {
			switch(this) {
			case DELETE_CONTACTS_BY_PROJECT_ID:{
				return "DELETE FROM LRY.AUT_PROJ_CONTACTS WHERE CTC_ID=?";
			}
			case DEFAULT_TABLE_CONDITION_FOR_ALL_OPERATION:{
				return " CTC_ID=%s ";
			}
			case DEFAULT_TABLE_IN_SGDB_APLICATION:{
				return "LRY.AUT_PROJ_CONTACTS";
			}
			case SELECT_ALL_COLUMNS_FROM_CONTACT:{
				return String.format("SELECT * FROM %s",DEFAULT_TABLE_IN_SGDB_APLICATION.toString());
			}
			case SELECT_ALL_COLUMNS_FROM_CONTACT_BY_PROJ_ID:{
				return String.format("SELECT * FROM %s WHERE PJT_ID=?",DEFAULT_TABLE_IN_SGDB_APLICATION.toString());
			}
			case INSERT_CONTACT_BY_PROJECT_ID:{
				return "INSERT INTO LRY.AUT_PROJ_CONTACTS(PJT_ID, CTC_NAME, CTC_TEL_PHONE, CTC_MOB_PHONE, CTC_EMAIL, CTC_SUBJECT_BY_USER) VALUES(?,?,?,?,?,?);";
			}
			case SELECT_CONTACTS_BY_PROJECT_ID:{
				return "SELECT CTC_ID FROM LRY.AUT_PROJ_CONTACTS;";
			}
			case UPDATE_CONTACTS_BY_PROJECT_ID:{
				return "UPDATE LRY.AUT_PROJ_CONTACTS SET CTC_NAME=?,CTC_TEL_PHONE=?,CTC_MOB_PHONE=?,CTC_EMAIL=?,CTC_SUBJECT_BY_USER=? WHERE PJT_ID=?;";
			}
			default:{
				return this.name();
			}
			}
		}
	}

	/**
	 * 
	 * Inclui um novo contato na base de dados
	 * 
	 * 
	 * @param name - Nome do contato
	 * @param telPhone - Número do telefone
	 * @param mobPhone - Número do celular
	 * @param email - Email do contato
	 * @param subject - Assunto 
	 * @param idProject - Id do projeto
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 *
	 */
	public boolean autInsertContact(String idProject,String name,String telPhone,String mobPhone,String email,String subject) {
		try {

			System.out.println("AUT INFO: INSERT NEW CONTACT BY PROJECT");
			autExecSubStatements(AUT_CONTACTS_OPERATIONS.INSERT_CONTACT_BY_PROJECT_ID.toString(), new Object[] {idProject,name,telPhone,mobPhone,email,subject});
			return true;
		}
		catch(java.lang.Exception e) {
			AUTLogsManage.registrarLog(e, "AUT ERROR: INSERT NEW CONTACT BY PROJECT : DB INTERFACE");
			return false;
		}
	}

	/**
	 *
	 *
	 *Contrutor padrão
	 *
	 */
	public AUTDBContacts() {
		// TODO Auto-generated constructor stub
	}

	public <TProject extends AUTDBProject>AUTDBContacts(TProject project){
		autSetConnection(project.getActiveConnection());
	}
}
