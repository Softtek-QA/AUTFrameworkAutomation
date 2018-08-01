/**
 * 
 */
package br.framework.db.management;

import java.sql.SQLException;

import br.framework.db.management.AUTDBProfiles.AUT_PROFILES_OPERATIONS;
import br.framework.db.transactions.AUTJDBCProcess;
import br.framework.gui.logs.AUTLogsManage;
/**
 * 
 * Responsável pelo gerenciamento de projetos criados na estrutura
 * 
 * 
 * @author Softtek - QA
 *
 */
public class AUTDBProject extends AUTJDBCProcess {	
	String AUT_PARAM_KEY_PROJECT = "";
		
	/**
	 * 
	 * SQL-Define as propriedades gerais do projeto
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_PROJECT_SQL_PROPERTY{
		ID,
		NAME,
		KEY,
		DESCRIPTION,
		CREATION_DATE,
		LAST_UPDATE;

		@Override
		public String toString() {
			switch(this) {
			case KEY:{
				return "PJT_KEY";
			}
			case ID:{
				return "PJT_ID";
			}
			case NAME:{
				return "PJT_NAME";				
			}
			case DESCRIPTION:{
				return "PJT_DESCRIPTION";				
			}
			case CREATION_DATE:{
				return "PJT_DATE_CREATION";
			}
			case LAST_UPDATE:{
				return "PJT_DATE_CHANGE";
			}
			default:{
				return "PJT_ID";
			}			
			}
		}
	}



	/**
	 * Enumera as opções possível de operações que podem ser realizados para um projeto
	 * 
	 * - Define o procedimento SQL responsável pela manipulação de dados da estrutura
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_PROJECT_OPERATIONS{
		INSERT_PROJECT_V0,
		UPDATE_PROJECT_NAME_BY_ID,
		UPDATE_PROJECT_DESCRIPTION_BY_ID,
		UPDATE_PROJECT_V2,
		UPDATE_PROJECT_ALL_COLUMNS_BY_KEY,
		DELETE_PROJECT_V0,
		SELECT_PROJECT_KEY,
		SELECT_ALL_COLUMNS_PROJECTS,
		SELECT_ID_PROJECT_BY_KEY,
		SELECT_ALL_PROJECTS;


		@Override
		public String toString() {

			switch(this) {
			case UPDATE_PROJECT_ALL_COLUMNS_BY_KEY:{
				return "UPDATE lry.aut_projects SET pjt_name=?,pjt_description=?,pjt_date_change=current_timestamp where pjt_key=?";
			}
			case SELECT_ALL_COLUMNS_PROJECTS:{
				return "SELECT * FROM LRY.AUT_PROJECTS;";
			}
			case SELECT_ID_PROJECT_BY_KEY:{			
				return "SELECT PJT_ID,PJT_KEY FROM lry.aut_projects WHERE PJT_KEY=?;";
			}
			case SELECT_ALL_PROJECTS:{
				return "SELECT * FROM lry.aut_projects;";
			}
			case SELECT_PROJECT_KEY:{
				return "SELECT PJT_KEY FROM lry.aut_projects WHERE PJT_ID=?";
			}
			case DELETE_PROJECT_V0:{				
				return "DELETE FROM lry.aut_projects WHERE PJT_KEY=?";
			}
			case INSERT_PROJECT_V0:{				
				return "INSERT INTO lry.aut_projects(pjt_key,pjt_name,pjt_description) values(?,?,?);";
			}
			case UPDATE_PROJECT_NAME_BY_ID:{
				return "UPDATE lry.aut_projects SET pjt_name=? where pjt_id=?";
			}
			case UPDATE_PROJECT_DESCRIPTION_BY_ID:{
				return "UPDATE lry.aut_projects SET pjt_description=? where pjt_id=?";
			}
			default:{
				return this.name();
			}
			}					
		}		
	}

	/**
	 * 
	 * Define um conjunto de parametros globais que auxiliam no processo de configuração do framework
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_GLOBAL_CONFIGURATION{
		INPUT_CURRENT_FILE_LOADED;

		@Override
		public String toString() {
			switch(this) {
			case INPUT_CURRENT_FILE_LOADED:{
				return "CFILE";
			}
			default:{
				return this.name();
			}
			}
		}
	}



	/**
	 * 
	 * Remove o projeto específicado da base de dados
	 * 
	 * @param key - Chave do projeto que será removido
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public boolean autRemoveProjectByKey(String key) {
		try {
			System.out.println("AUT INFO: REMOVE PROJECT FROM DATABASE : INIT");
			autExecSubStatements(AUT_PROJECT_OPERATIONS.DELETE_PROJECT_V0.toString(), new Object[] {key});
			System.out.println("AUT INFO: REMOVE PROJECT FROM DATABASE: END: OK");
			return true;
		}
		catch(java.lang.Exception e) {
			AUTLogsManage.registrarLog(e, "AUT ERROR: REMOVE PROJECT FROM DATABASE");
			return false;
		}
	}

	/**
	 * 
	 * Retorna o objeto para gerenciamento de contatos do projeto
	 * 
	 * @return AUTDBContacts - Objeto de gerenciamento dos contatos
	 * 
	 */
	public  AUTDBContacts autGetProjectContactsObject() {
		try {		
			return new AUTDBContacts(this);
		}
		catch(java.lang.Exception e) {
			AUTLogsManage.registrarLog(e, "AUT ERROR: GET CONTACTS BY PROJECT KEY");
			return null;
		}
	}
	/**
	 * 
	 * Altera os parametros de configuração do projeto usando a chave 
	 * 
	 * @param key - Chave do projeto que será alterado
	 * @param name - Novo conteúdo para o campo nome
	 * @param description - Novo conteúdo para o campo descriçao
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public boolean autChangeProjectByKey(String key,String name,String description) {
		try {
			System.out.println("AUT INFO: CHANGE PROJECT BY KEY");
			autExecSubStatements(AUT_PROJECT_OPERATIONS.UPDATE_PROJECT_ALL_COLUMNS_BY_KEY.toString(), new Object[] {name,description,key});
			System.out.println("AUT INFO: CHAHGE PROJECT BY KEY FINISHED : OK");			
			return true;
		}
		catch(java.lang.Exception e) {
			AUTLogsManage.registrarLog(e, "AUT ERROR: CHANGE PROJECT BY KEY");			
			return false;
		}
	}
	/**
	 * 
	 * Retorna o id do projeto corrente
	 * 
	 * 
	 * @return String - Id do projeto corrente
	 * 
	 */
	public String autCurrentIdProject() {
		String keyProject = "";
		try {
			keyProject = autCurrentKeyProject();

			//System.out.println("AUT INFO : LOADER ID PROJECT : INIT");
			String id = "";
			java.sql.ResultSet dados = autExecSubStatementsWithResult(AUT_PROJECT_OPERATIONS.SELECT_ID_PROJECT_BY_KEY.toString(),
					new Object[] {keyProject});
			if(dados.next()) {
				System.out.println("AUT INFO : PROJECT ID FOUND");
				id = dados.getString(1);
				System.out.println(id);
			}
			else {
				System.out.println("AUT INFO : PROJECT ID NOT FOUND");
			}			
			//System.out.println("AUT INFO : LOADER ID PROJECT : END");			
			return id;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR : SEARCH PROJECT ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * 
	 * Retorna a chave corrente do projeto
	 * 
	 * @return String - Chave do projeto atual
	 * 
	 */
	public String autCurrentKeyProject() {
		return AUT_PARAM_KEY_PROJECT;
	}

	/**
	 * 
	 * Retorna o Id do projeto pela chave
	 * 
	 * @param keyProject - Chave exclusiva do projeto
	 * 
	 * @return String - Id do projeto
	 * 
	 */
	public String autLoaderIdProject(String keyProject) {
		try {
			//System.out.println("AUT INFO : LOADER ID PROJECT : INIT");
			String id = "";
			java.sql.ResultSet dados = autExecSubStatementsWithResult(AUT_PROJECT_OPERATIONS.SELECT_ID_PROJECT_BY_KEY.toString(),
					new Object[] {keyProject});
			if(dados.next()) {
				//System.out.println("AUT INFO : PROJECT ID FOUND");
				id = dados.getString(1);
				AUT_PARAM_KEY_PROJECT = dados.getString(2);
				System.out.println(AUT_PARAM_KEY_PROJECT);
			}
			else {
				System.out.println("AUT INFO : PROJECT ID NOT FOUND");
			}			
			System.out.println("AUT INFO : LOADER ID PROJECT : END");			
			return AUT_PARAM_KEY_PROJECT;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR : SEARCH PROJECT ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * 
	 * Carrega a chave do projeto
	 * 
	 * @param idProject - Id de cadastro do projeto no banco de dados
	 * @return String - Chave do projeto criada durante o cadastro
	 */
	public String autLoaderKeyProject(Integer idProject) {

		try {
			java.sql.ResultSet dados = autExecSubStatementsWithResult(AUT_PROJECT_OPERATIONS.SELECT_PROJECT_KEY.toString(), 
					new Object[] {idProject});

			if(dados.next()) {
				System.out.println("AUT INFO : PROJECT KEY FOUND :");
				AUT_PARAM_KEY_PROJECT = dados.getString(1);				
				System.out.println(AUT_PARAM_KEY_PROJECT);
			}
			else {
				System.out.println("AUT INFO : PROJECT KEY NOT FOUND  : FOR PROJECT ID : ".concat(idProject.toString())); 
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}
	/**
	 * 
	 * Gera uma nova chave exclusivo para o projeto corrente
	 * 
	 *  - Chave de rastreabilidade de projeto
	 *  - Usada como referência para todas as operações do projeto
	 * 
	 * @return String - Nova chave do Projeto
	 */
	public String autGenerateKeyProject() {
		java.util.Random rnd = new java.util.Random();

		String keyPrefix = "autkey";
		Integer index = rnd.nextInt(10000);
		String[] keyLetters = new String[] {"a","b","c","d","e","f","g","h","i","z","w","y","j"};
		String letters = keyLetters[rnd.nextInt(keyLetters.length-1)] 
				+= keyLetters[rnd.nextInt(keyLetters.length-1)]
						+= keyLetters[rnd.nextInt(keyLetters.length-1)]
								+= keyLetters[rnd.nextInt(keyLetters.length-1)]
										+= keyLetters[rnd.nextInt(keyLetters.length-1)]
												+= keyLetters[rnd.nextInt(keyLetters.length-1)];
		String keyOut = keyPrefix.concat(index.toString()).concat(letters);
		AUT_PARAM_KEY_PROJECT = keyOut;
		System.out.println("AUT INFO : KEY OUT : ");
		System.out.println(keyOut);
		return keyOut;
	}
	/**
	 * 
	 * Altera o nome do projeto
	 * 
	 * @param idProj - Id do projeto
	 * @param newNameProject - Novo nome do projeto
	 * 
	 * @return boolean - Retorna true caso a operação tenha sido realizada com sucesso false caso contrário
	 * 
	 */
	public boolean autChangeProjectName(Integer idProj,String newNameProject) {
		try {

			System.out.println("AUT INFO : ATUALIZANDO CONFIGURAÇÕES DO PROJETO : NOME : INICIO");

			autExecSubStatements(AUT_PROJECT_OPERATIONS.UPDATE_PROJECT_NAME_BY_ID.toString(), new Object[] {newNameProject,idProj});

			System.out.println("AUT INFO : ATUALIZANDO CONFIGURAÇÕES DO PROJETO : NOME : FINAL");

			return true;
		}
		catch(java.lang.Exception e) {
			return false;
		}	
	}

	/**
	 * Altera a descrição associada ao projeto
	 * 
	 * @param idProj - Id do projeto para alteração
	 * @param newDescription - Nova descrição para o projeto
	 * 
	 * @return boleano - Retorna true se a operação tiver sido realizada com sucesso false caso contrário
	 * 
	 */
	public boolean autChangeProjectDescription(Integer idProj,String newDescription) {
		try {

			System.out.println("AUT INFO : ATUALIZANDO CONFIGURAÇÕES DO PROJETO : NOME : INICIO");

			autExecSubStatements(AUT_PROJECT_OPERATIONS.UPDATE_PROJECT_DESCRIPTION_BY_ID.toString(), new Object[] {newDescription,idProj});

			System.out.println("AUT INFO : ATUALIZANDO CONFIGURAÇÕES DO PROJETO : NOME : FINAL");

			return true;
		}
		catch(java.lang.Exception e) {
			return false;
		}	
	}


	/**
	 * Inclui um novo projeto na estrutura
	 * 
	 * @param name - Nome do projeto
	 * @param description - Descrição do projeto
	 * @return booleano - True em caso de sucesso se não false
	 */
	public boolean autInsertProject(String name,String description) {

		try {

			AUT_PARAM_KEY_PROJECT = autGenerateKeyProject();

			autExecSubStatements(AUT_PROJECT_OPERATIONS.INSERT_PROJECT_V0.toString(), new Object[] {AUT_PARAM_KEY_PROJECT,name,description});

			return true;

		}
		catch(java.lang.Exception e) {

			System.out.println("AUT ERROR : INCLUSÃO DE PROJETO");
			System.out.println(e.getMessage());
			e.printStackTrace();

			return true;
		}

	}

	public java.util.List<String> autGetAllProjectName(){
		try {

			return null;			
		}
		catch(java.lang.Exception e) {
			AUTLogsManage.registrarLog(e, "AUT ERROR: LIST ALL PROJECT NAME");
			return null;
		}
	}
	/**
	 * 
	 * construtor padrão da classe
	 * 
	 */
	public AUTDBProject() {
		super();
	}
}
