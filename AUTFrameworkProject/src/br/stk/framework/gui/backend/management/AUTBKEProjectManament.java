/**
 * 
 */
package br.stk.framework.gui.backend.management;

import br.stk.framework.api.AUTAPI;
import br.stk.framework.db.management.AUTDBProject;
import br.stk.framework.db.management.AUTDBProject.AUT_GLOBAL_CONFIGURATION;
import br.stk.framework.db.management.AUTDBProject.AUT_PROJECT_OPERATIONS;
import br.stk.framework.db.management.AUTDBProject.AUT_PROJECT_SQL_PROPERTY;
import br.stk.framework.gui.backend.management.AUTBKEUtils.AUTGUIObjectBase;
import br.stk.framework.gui.backend.management.AUTBKEUtils.AUTGUITreeObject;
import br.stk.framework.gui.logs.AUTLogsManage;


/**
 * 
 * Processamento back end para gerenciamento de projetos
 * 
 * @author Softtek-QA
 *
 */
public class AUTBKEProjectManament extends AUTGUIObjectBase{
	
	/**
	 * 
	 * Enumera o conjunto de propriedades configuradas para o projeto
	 * 
	 */
	public void autGUIGetEnumOptions() {
		autGetDataTableByProperties(AUT_PROJECT_OPERATIONS.SELECT_ALL_COLUMNS_PROJECTS.toString(), AUT_PROJECT_SQL_PROPERTY.class, new Object[] {});
	}

	
	
	/**
	 * 
	 * 
	 * Adiciona um novo contato para o projeto especificado
	 * 
	 * @param name - Nome do projeto
	 * @param telPhone - Número do telefone
	 * @param mobPhone - Número do celular
	 * @param email - Email do contato
	 * @param subject - Assuntos que podem ser tratados com o referido contato
	 * @param projId - Id do projeto
	 * 
	 * @return boolean - True caso o processo seja realizado com sucesso false caso contrário
	 * 
	 * 
	 * 
	 */
	public boolean autGUIInsertContact(String projId,String name,String telPhone,String mobPhone,String email,String subject) {
		try {
			autStartDefaultConnection();
			System.out.println("AUT INFO: INSERT NEW CONTACT FOR IN MANAGED PROJECT");
			
			autGetProjectContactsObject().autInsertContact(projId, name, telPhone, mobPhone, email, subject);
						
			return true;
		}
		catch(java.lang.Exception e) {

			AUTLogsManage.registrarLog(e, "AUT ERROR: INSERT NEW CONTACT FOR IN MANAGED PROJECT");
			
			return false;
		}
	}
	
	/**
	 * 
	 * Retorna o objeto para gerenciamento de contatos
	 * 
	 * @return AUTBKEContactManagement - Objeto para gerenciamento de contatos 
	 * 
	 */
	public AUTBKEContactManagement autGUIGetContactManager() {
		try {
			return new AUTBKEContactManagement(this);
		}
		catch(java.lang.Exception e) {
			AUTLogsManage.registrarLog(e, "AUT ERROR GUI: GET CONTACT MANAGER OBJECT");
			return null;
		}
	}	
	/**
	 * 
	 * Carrega a estrutura de dados de cada projeto
	 * 
	 * @return AUTGUITreeObject - Árvore de dados
	 * 
	 */
	public AUTGUITreeObject autGUIProjects(){
		try {
			
			AUTGUITreeObject treeItens = new AUTGUITreeObject();
						
			return treeItens;
		}
		catch(java.lang.Exception e) {
			
			AUTLogsManage.registrarLog(e, "ERROR: CREATE TREE NODE FROM SQL STATEMENTS");
					
			return null;
		}
		

	}
	
	
	/**
	 * 
	 * Remove o projeto da base de dados
	 * @param processAssync - Define se o processo será executado em modo assincrono(true) ou não (false)
	 * @param keyProject - Chave exclusiva do projeto
	 * 
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public boolean autGUIRemoveProjectByKey(boolean processAssync,String keyProject) {
		try {
			AUTThreadProcess thProc = new AUTThreadProcess() {
				
				@Override
				public boolean autExecProcess() {
					
					autRemoveProjectByKey(keyProject);
					
					return true;
				}
				
				@Override
				public boolean autExecInit() {
					// TODO Auto-generated method stub
					System.out.println("AUT INFO GUI: REMOVE PROJECT FROM DATABASE");					
					return true;
				}
				
				@Override
				public boolean autExecEnd() {
					// TODO Auto-generated method stub
					System.out.println("AUT INFO GUI: REMOVE PROJECT FROM DATABASE : OK");					
					return true;
				}
			};
			
			if(processAssync) {
				thProc.autStartProcess(false);
			}
			else
			{
				thProc.autStartProcess(true);
			}
			
			return true;
		}
		catch(java.lang.Exception e) {
			return false;
		}
	}
	/**
	 * 
	 * Altera os dados do projeto vai GUI
	 * 
	 * @param name - Novo nome para o projeto
	 * @param description - Nova descrição para o projeto
	 * @param key - Chave do projeto que será alterado
	 * @param projCon - Objeto de conexão com o projeto base
	 * @param <TProjectConnection> - Tipo de conexão
	 * @return boolean - caso o processo seja realizado com sucesso false caso contrário
	 * 
	 */
	public <TProjectConnection extends AUTBKEProjectManament> boolean autGUIChangeProject(TProjectConnection projCon,String key,String name,String description) {
		try {
			projCon.autChangeProjectByKey(key,name, description);			
			return true;
		}
		catch(java.lang.Exception e) {
			AUTLogsManage.registrarLog(e, "AUT ERROR: GUI CHANGE PROJECT DATA");
			return false;
		}
	}
	/**
	 * 
	 * Método para inclusão de um novo projeto ao banco de dados
	 * 
	 * @param name - Nome do projeto
	 * @param description - Descrição do projeto
	 * @param processAssync - Define o modo de execução do processo - Assincrono (true) ou sincrono(false)
	 * 
	 * @return boolean - True caso o processo seja realizado com sucesso false caso contrário
	 * 
	 */
	public boolean autGUIInsertProject(boolean processAssync,String name,String description) {
		try {
			AUTThreadProcess thProc = new AUTThreadProcess() {
				
				@Override
				public boolean autExecProcess() {
					System.out.println("AUT INFO: INSERT NEW PROJECT ON DATABASE");
					autInsertProject(name, description);
					// TODO Auto-generated method stub
					return true;
				}
				
				@Override
				public boolean autExecInit() {
					autStartDefaultConnection();					
					return false;
				}
				
				@Override
				public boolean autExecEnd() {
					
					System.out.println("AUT INFO: INSERT FINISHED : OK");
					
					return false;
				}
			};
			
			if(processAssync) {
			thProc.autStartProcess(false);
			}
			else {
				thProc.autStartProcess(true);
			}
							
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: INSERT NEW PROJECT ON DATABASE");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
}

