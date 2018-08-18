/**
 * 
 */
package br.stk.framework.gui.backend.management;

import java.util.HashMap;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import br.stk.framework.api.AUTAPI.AUTJDBCConector;
import br.stk.framework.db.connectors.AUTDBUtils.AUT_DB_STATEMENTS_TEMPLATE;
import br.stk.framework.db.connectors.AUTDBUtils.AUT_TYPE_COMPARE_PROPERTIES;
import br.stk.framework.db.transactions.AUTJDBCProcess;
import br.stk.framework.gui.backend.management.AUTBKEContactManagement.AUT_CONTACT_GUI_PROPERTIES;
import br.stk.framework.gui.logs.AUTLogsManage;
import br.stk.framework.utils.AUTFMKUtils;
import br.stk.framework.utils.AUTFMKUtils.AUTThreadProcess;
import br.stkframework.db.management.AUTDBProject;
import br.stkframework.db.management.AUTDBContacts.AUT_CONTACTS_OPERATIONS;
import br.stkframework.db.management.AUTDBContacts.AUT_CONTACTS_SQL_PROPERTIES;
import br.stkframework.db.management.AUTDBProject.AUT_GLOBAL_CONFIGURATION;
import br.stkframework.db.management.AUTDBProject.AUT_PROJECT_OPERATIONS;
import br.stkframework.db.management.AUTDBProject.AUT_PROJECT_SQL_PROPERTY;


/**
 * Gerenciamento de interfaces com SGDB
 * 
 * @author Softtek-QA
 *
 */
public class AUTBKEUtils{
	/**
	 * 
	 * Define as categorias de visualização da informação
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_TYPE_DATA_FILTER_VIEW{
		ITEM_ID,
		ITEM_LABEL,
		ITEM_DESCRIPTION,
		SUB_ITENS,
		SUB_ITENS_ID,
		SUB_ITENS_LABEL,
		SUB_ITENS_DESCRIPTION
	}
	
	/**
	 * 
	 * Classe de componentes GUI reutizáveis 
	 * 
	 * @author Softtek-QA
	 *
	 */
	public static class AUTGUIObjectBase extends AUTDBProject{						
		public AUTGUITreeObject autGUITreeNodeFromProject(boolean loaderAssync) {
			return new AUTGUITreeObject(loaderAssync,AUT_PROJECT_SQL_PROPERTY.NAME.toString(), 
					autGetDataTableByProperties(AUT_PROJECT_OPERATIONS.SELECT_ALL_COLUMNS_PROJECTS.toString(), 
							AUT_PROJECT_SQL_PROPERTY.class, new Object[] {}));
		}

		/**
		 * 
		 * Construtor padrão
		 * 
		 */
		public AUTGUIObjectBase() {
			super();
		}
	}
	
	
	/**
	 * Classe responsável pelo gerenciamento de tabela baseadas em GUI
	 * 
	 * @author Softtek-QA
	 *
	 * @param <TAUTGUIProject> - uma classe que extende AUTGUIObjectBase
	 * 
	 */
	public static class AUTGUITable <TAUTGUIProject extends AUTGUIObjectBase> extends javax.swing.table.DefaultTableModel{			
		TAUTGUIProject currentProj = null;	
		public Integer AUT_LAST_COLUMN_CHANGED,AUT_LAST_ROW_CHANGED;
		public TableModelEvent AUT_TABLE_MODEL_EVENT;
		public java.util.HashMap<String,String> AUT_COLUMN_HEADER_COLLECTIONS;
		/**
		 * 
		 * Tabela de configurações para manipulação de eventos GUI 
		 * 
		 * @author Softtek-QA
		 *
		 * @param <TProject> - Projeto corrente
		 * 
		 */
		public static abstract class AUTGUITableDefinition <TProject extends AUTDBProject>{
			public abstract TProject autGetProject();
			public abstract String autGetTable();
			public abstract String autGetColumn();
			public abstract String autGetWhereCondition();
			public abstract Object autGetNewValueForColumn();
			public abstract AUTGUITable autGetGUITableInstance();
		}
		
		
		/**
		 * 
		 * Retorna o valor da propriedade
		 * 
		 * @param <TOption> - Enumerador de opções
		 * 
		 * @return Object  - Valor do campo
		 *
		 */
		public synchronized <TOption extends java.lang.Enum> Object autGetValueByColumnSelected() {
			try {
				
				System.out.println("AUT INFO: RETURN VALUE BY PROPERTY ENUMERATION FROM TABLE");
				
				return getValueAt(AUT_LAST_ROW_CHANGED, AUT_LAST_COLUMN_CHANGED);
				
			}
			catch(java.lang.Exception e) {
				
				AUTLogsManage.registrarLog(e, "AUT ERROR: RETURN VALUE BY PROPERTY ENUMERATION FROM TABLE");
				
				return null;
			}
		}

		
		/**
		 * 
		 * Retorna o valor da propriedade definida pelo enumerador
		 * 
		 * @param <TOption> - Enumerador de opções
		 * 
		 * @param row - Número da linha
		 * @param columnName - Opção selecionada no enumerador
		 * @return Object - Valor retornado
		 * 
		 */
		public synchronized <TOption extends java.lang.Enum> Object autGetValueByProperty(Integer row,TOption columnName) {
			try {
				
				System.out.println("AUT INFO: RETURN VALUE BY PROPERTY ENUMERATION FROM TABLE");
				
				return getValueAt(row, findColumn(columnName.toString()));
				
			}
			catch(java.lang.Exception e) {
				
				AUTLogsManage.registrarLog(e, "AUT ERROR: RETURN VALUE BY PROPERTY ENUMERATION FROM TABLE");
				
				return null;
			}
		}
		
		
		/**
		 * 
		 * Retorna o valor da propriedade especificada
		 * 
		 * @param columnName  - Opção selecionada no enumerador
		 * @param <TOption> - Enumerador de opções
		 * 
		 * @return Object - Valor do campo da última linha
		 * 
		 */
		public synchronized <TOption extends java.lang.Enum> Object autGetValueByProperty(TOption columnName) {
			try {
				
				System.out.println("AUT INFO: RETURN VALUE BY PROPERTY ENUMERATION FROM TABLE");
				
				return getValueAt(AUT_LAST_ROW_CHANGED, findColumn(columnName.toString()));				
			}
			catch(java.lang.Exception e) {
				
				AUTLogsManage.registrarLog(e, "AUT ERROR: RETURN VALUE BY PROPERTY ENUMERATION FROM TABLE");
				
				return null;
			}
		}
		
		public <TOptions extends java.lang.Enum> Object[][] autConfigInit(TAUTGUIProject project,String sqlDefinition,Class<? extends java.lang.Enum> enumPropertiesValues,java.util.HashMap<String,Object> mapEnumProperties,Object[] inputParameters) {			
			java.util.HashMap<Integer,java.util.HashMap<String,Object>> inputData = project.autGetDataTableByProperties(sqlDefinition, enumPropertiesValues, inputParameters);			
			for(Integer rowIndex: inputData.keySet()) {			
				for(String column : inputData.get(rowIndex).keySet()) {
					if(findColumn(column)==-1) {					
						addColumn(mapEnumProperties.get(column));
						System.out.println(project.autGetValueByEnumProperty(AUT_TYPE_COMPARE_PROPERTIES.ENUMERATION_NAME,AUT_TYPE_COMPARE_PROPERTIES.class,enumPropertiesValues));
					}
				}				
				break;
			}	
			Class enumKeyInput = (java.lang.Class)mapEnumProperties.get("ENUM_KEY");
			Class enumValueInput = (java.lang.Class)mapEnumProperties.get("ENUM_VALUE");
			AUT_COLUMN_HEADER_COLLECTIONS = new java.util.HashMap<String,String>();
			
			java.util.HashMap<String, Object> hashProps = project.autGetValueByEnumProperty(AUT_TYPE_COMPARE_PROPERTIES.ENUMERATION_NAME,enumValueInput,enumKeyInput);
			Integer rowCount = 0;			
			
			for(Integer rowIndex: inputData.keySet()) {		
				addRow(new Object[] {});
				for(String column : inputData.get(rowIndex).keySet()) {					
					setValueAt(inputData.get(rowIndex).get(column), rowCount,findColumn(mapEnumProperties.get(column).toString()));	
					AUT_COLUMN_HEADER_COLLECTIONS.put(mapEnumProperties.get(column).toString(), column);
				}		
				rowCount++;
			}	
					
			return null;
		}
				
		
		/**
		 * 
		 * Adiciona um manipulador de eventos para a tabela
		 * 
		 * @param tableDefinition - Definição dos dados de configuração do evento
		 * 
		 * @return boolean true caso o processo seja finalizado com sucesso false caso contrário
		 * 
		 */
		public boolean autAddListiner(AUTGUITableDefinition tableDefinition) {
			try {
				System.out.println("AUT INFO: START CONFIGURATION FOR EVENT HANDLE");
				
				this.addTableModelListener(new javax.swing.event.TableModelListener(){
					@Override
					public void tableChanged(TableModelEvent e) {
						AUT_LAST_COLUMN_CHANGED = e.getLastRow();
						AUT_LAST_ROW_CHANGED = e.getLastRow();
						AUT_TABLE_MODEL_EVENT = e;
						
						autBKEChangeTable(tableDefinition.autGetProject(), tableDefinition.autGetTable(), AUT_COLUMN_HEADER_COLLECTIONS.get(tableDefinition.autGetColumn()), tableDefinition.autGetWhereCondition(), tableDefinition.autGetNewValueForColumn());
						
					}					
				});
				
				return true;
			}
			catch(java.lang.Exception e) {
				AUTLogsManage.registrarLog(e, "AUT ERROR: ADD TABLE LISTENER FOR TABLE MODEL");
				return false;
			}
		}
		/**
		 * Construtor padrão
		 * 
		 * @param project - Projeto referência para cópia local
		 * 
		 */
		public AUTGUITable(TAUTGUIProject project) {
			super();
			currentProj = project;
		}
			
		public <TOptions extends java.lang.Enum> AUTGUITable(TAUTGUIProject project,String sqlDefinition,Class<? extends java.lang.Enum> enumProperties,java.util.HashMap<String,Object> mapEnumProperties,Object[] inputParameters) {
			super();
			autConfigInit(project, sqlDefinition, enumProperties, mapEnumProperties,inputParameters);
		}
				
		public AUTGUITable() {
			
		}
	}
		
	/**
	 * 
	 * Gerenciamento de elementos de interface gráfica baseados em arvore
	 * @param <TAUTGUIProject> - Classe base de projeto
	 * 
	 * @author Softtek-QA
	 *
	 */
	public static class AUTGUITreeObject <TAUTGUIProject extends AUTGUIObjectBase> extends javax.swing.tree.DefaultMutableTreeNode{			
		/**
		 * 
		 * Carrega o valor de uma propriedade especifica na arvore de dados - TreeObject
		 * @param name - Nome da propriedade
		 * @param autParentNode - Item da arvore de objetos
		 * @param <TOption> - Enumerador de opções disponíveis
		 * 
		 * @return Object - Retorna um objeto
		 * 
		 */
		public  static <TOption extends java.lang.Enum> Object autGetValueByPropertyConfig(TOption name,Object autParentNode) {
			try {				
				
				javax.swing.tree.DefaultMutableTreeNode nodeItens = (javax.swing.tree.DefaultMutableTreeNode)autParentNode;				
				Object objOut = null;				
				System.out.println(String.format("AUT INFO: GET VALUE BY NODE NAME : %s",name));
				java.util.Enumeration<javax.swing.tree.DefaultMutableTreeNode> enumNodes = nodeItens.children();				
				while(enumNodes.hasMoreElements()) {
					javax.swing.tree.DefaultMutableTreeNode nodeItem = enumNodes.nextElement();
					System.out.println(nodeItem.getUserObject());
					if(name.toString().equals(nodeItem.getUserObject().toString())) {
						objOut = nodeItem.getUserObject();
						java.util.Enumeration<javax.swing.tree.DefaultMutableTreeNode> childItems = nodeItem.children();
						if(childItems.hasMoreElements()) {
							objOut = childItems.nextElement().getUserObject();
							return objOut;
						}									
					}					
				}
				
				return objOut;
			}
			catch(java.lang.Exception e) {
				
				AUTLogsManage.registrarLog(e, "AUT ERROR: GET PROPERTY BY ENUM CONFIGURATION");
				
				return null;
			}			
		}
		
		/**
		 * Configurações iniciais da árvore de objetos
		 * 
		 * 
		 * @param columnKey - Coluna chave
		 * @param inputData - Dados de entrada para estrutura
		 * 
		 */
		public void configInit(String columnKey,java.util.HashMap<Integer,java.util.HashMap<String,Object>> inputData) {			
			for(Integer rowIndex: inputData.keySet()) {
				javax.swing.tree.DefaultMutableTreeNode nodeKey = new javax.swing.tree.DefaultMutableTreeNode(inputData.get(rowIndex).get(columnKey));
				nodeKey.setAllowsChildren(true);
				for(Object columnName : inputData.get(rowIndex).keySet()) {														
					javax.swing.tree.DefaultMutableTreeNode nodeColName = new javax.swing.tree.DefaultMutableTreeNode(columnName);				
					nodeColName.setAllowsChildren(true);
					nodeColName.insert(new javax.swing.tree.DefaultMutableTreeNode(inputData.get(rowIndex).get(columnName)), 0);
					nodeKey.insert(nodeColName, 0);
				}				
				this.insert(nodeKey, rowIndex);				
			}
			
			
		}	
		
		/**
		 * 
		 * Define as configurações de inicialização do objeto
		 * @param autTreeObject - Arvore de objetos
		 * 
		 */
		public void configInit(Object autTreeObject) {
			this.setUserObject(autTreeObject);
			this.setAllowsChildren(true);	
			
		}
		public void configInit() {
			this.setAllowsChildren(true);	
			
		}
		
		
		/**
		 * 
		 * Construtor 
		 * 
		 * @param columnKey - Coluna chave - Elemento root
		 * @param inputData - Sub itens
		 * @param loaderAssync - Define o modo de carregamento - Assincrono (True) sincrono (false)
		 * 
		 * 
		 */
		public AUTGUITreeObject(boolean loaderAssync,String columnKey,java.util.HashMap<Integer,java.util.HashMap<String,Object>> inputData)
		{
			super("PROJETOS");	
			AUTThreadProcess thProc = new AUTThreadProcess() {
				
				@Override
				public boolean autExecProcess() {
					// TODO Auto-generated method stub
					configInit(columnKey, inputData);
					return true;
				}
				
				@Override
				public boolean autExecInit() {
					// TODO Auto-generated method stub
					AUTLogsManage.registrarLog("AUT INFO: INIT : LOADER PROJECTS FROM DATABASE");
					return true;
				}
				
				@Override
				public boolean autExecEnd() {
					// TODO Auto-generated method stub
					AUTLogsManage.registrarLog("AUT INFO: END : LOADER PROJECTS FROM DATABASE");
					
					return true;
				}
			};

			if(loaderAssync) {				
				thProc.autStartProcess(false);				
			}
			else {
				thProc.autStartProcess(true);
			}
		}
		
		/**
		 * 
		 * Construtor padrão
		 * 
		 */
		public AUTGUITreeObject() {
			super();
			configInit();
			// TODO Auto-generated constructor stub
		}

		/**
		 * Construtor
		 * 
		 * @param autTreeObject - Item baseado em árvore
		 * @param allowsChildren - boolean  - Permite inclusão de sub itens ? 
		 * 
		 */
		public AUTGUITreeObject(Object autTreeObject, boolean allowsChildren) {
			super(autTreeObject, allowsChildren);
			configInit(autTreeObject);
			// TODO Auto-generated constructor stub
		}

		/**
		 * 
		 * Construtor 
		 * 
		 * @param autTreeObject - Item baseado em árvore
		 * 
		 */
		public AUTGUITreeObject(Object autTreeObject) {
			super(autTreeObject);
			configInit(autTreeObject);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	
	
	/**
	 * 
	 * Altera o campo na tabela de dados
	 * 
	 * @param table - Tabela de dados
	 * @param column - Coluna 
	 * @param value - Novo valor
	 * @param project - Projeto alvo
	 * @param whereCondition - Codição SQL
	 * @param <TProject> - Projeto
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public synchronized static <TProject extends AUTDBProject> boolean autBKEChangeTable(TProject project,String table,String column,String whereCondition,Object value)
	{
		try {			
			System.out.println("AUT INFO: CHANGE TABLE FUNCION: INIT");
			project.autExecSubStatements(String.format(AUT_DB_STATEMENTS_TEMPLATE.STANDAR_UPDATE_WITH_CONDITIONS.toString(), table,column,whereCondition), new Object[] {value});
			System.out.println("AUT INFO: CHANGE TABLE FUNCTION: FINISHED");			
			return true;
		}
		catch(java.lang.Exception e) {
			
			AUTLogsManage.registrarLog(e, "ERROR: CHANGE TABLE FUNCTION");
			
			return false;
		}
	}
	/**
	 * 
	 * Construtor padrão da classe
	 * 
	 */
	public AUTBKEUtils() {
		// TODO Auto-generated constructor stub
	}

}
