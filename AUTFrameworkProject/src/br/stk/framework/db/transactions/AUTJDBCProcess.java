/**
 * 
 */
package br.stk.framework.db.transactions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

import br.stk.framework.db.connectors.AUTConnectorGlobalConfiguration;
import br.stk.framework.db.connectors.AUTDBConnection;
import br.stk.framework.db.connectors.AUTConnectorGlobalConfiguration.AUT_GLOBAL_CONFIGURATION;
import br.stk.framework.db.connectors.AUTDBConnection.AUT_TYPE_SGDB;
import br.stk.framework.db.management.AUTDBProject.AUT_PROJECT_SQL_PROPERTY;
import br.stk.framework.gui.logs.AUTLogsManage;
import br.stk.framework.utils.AUTFMKUtils;
import br.stk.framework.utils.AUTFMKUtils.*;
/**
 * 
 * Responsável pelo gerenciamento dos processos que serão executados na SGDB destino :
 * - Query
 * - Procedure
 * - Etc
 * 
 * @author Softtek - QA
 *
 */
public class AUTJDBCProcess extends AUTDBConnection{
	public AUTConnectorGlobalConfiguration AUT_GLOBAL_CONFIG_OBJECT_CONNECTION;

	/**
	 * 
	 * Classe para definição da estrutura de dados da coluna
	 * 
	 * @author Softtek-QA
	 *
	 */
	public class AUT_COLUMN_DEFINITION{
		public String COLUMN_NAME;
		public String COLUMN_INDEX;
	}


	/**
	 * Classe para gerenciamento de coleções de colunas 
	 * @param <TColumn> - Tipo de coluna base
	 * @author Softtek-QA
	 *
	 */
	public class AUTColumnsCollection <TColumn extends AUT_COLUMN_DEFINITION> extends java.util.ArrayList<TColumn>{


		/**
		 * 
		 * Rotina de inicialização da estrutura de dados local
		 * 
		 */
		public void configData() {

		}

		public void configData(int capacity) {

		}


		public AUTColumnsCollection() {
			super();
			configData();
		}

		public AUTColumnsCollection(Collection<? extends TColumn> c) {
			super(c);
			configData();
			// TODO Auto-generated constructor stub
		}

		public AUTColumnsCollection(int initialCapacity) {
			super(initialCapacity);
			configData();
			// TODO Auto-generated constructor stub
		}

	}



	/**
	 * Enumera os tipos possíveis de operações que podem ser executadas em um banco de dados
	 * 
	 * 
	 * @author Softtek - QA
	 *
	 */
	public enum AUT_TYPE_SQL_OPERATIONS{
		EXEC_STATEMENT_NOTHING_RESULT,
		EXEC_STATEMENT_WITH_RESULT_IMPACT_ITENS,
		EXEC_STATEMENT_WITH_RESULT_RESULTSET
	}

	/**
	 * 
	 * Executa procedimento SQL sem retorno de dados
	 * 
	 * @param sql - Procedimento SQL
	 * @param parametros - Parametros do procedimento
	 * 
	 * @return boolean - True em caso de sucesso, false caso contrário
	 * 
	 */
	public boolean autExecSubStatements(String sql,Object[] parametros) {		
		try {

			boolean status = false;				
			java.sql.PreparedStatement cmdSQL = getActiveConnection().prepareStatement(sql);						
			java.sql.ParameterMetaData param;			
			int contParams = 1;			
			for(Object item: parametros) {
				if(autFileLocationValidation(item.toString())){
					try {
						java.io.FileInputStream flInput = new java.io.FileInputStream(item.toString());
						cmdSQL.setBlob(contParams, flInput);							
					} catch (IOException e) {							
						e.printStackTrace();
					}
				}
				else {
					cmdSQL.setObject(contParams, item);	
				}
				contParams++;
			}	

			status = cmdSQL.execute();			

			cmdSQL.close();

			//System.out.println(String.format("SQL: %s",sql));
			//System.out.println("AUT INFO : EXECUTANDO SQL : FIM");						
			return status;						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return false;
		}
	}

	
	public boolean autExecSubStatementsDefault(String sql,Object[] parametros) {
		autStartDefaultConnection();
		return autExecSubStatements(sql, parametros);
	}
	
	/**
	 * Executa procedimento SQL que retorna um conjunto de dados
	 * 
	 * @param sql - Procedimento SQL
	 * @param parametros - Parametros de configuração do procedimento SQL
	 * 
	 * @return java.sql.ResultSet - Dados de retorno do procedimento
	 */
	public java.sql.ResultSet autExecSubStatementsWithResult(String sql,Object[] parametros) {		
		try {
			java.sql.ResultSet rsData = null;
			java.sql.PreparedStatement cmdSQL = getActiveConnection().prepareStatement(sql);						
			java.sql.ParameterMetaData param;			

			int contParams = 1;
			for(Object item: parametros) {	
				cmdSQL.setObject(contParams, item.toString());
				contParams++;
			}					
			rsData = cmdSQL.executeQuery();		

			return rsData;						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}
	}

	
	public java.sql.ResultSet autExecSubStatementsWithResultDefault(String sql,Object[] parametros) {		
		autStartDefaultConnection();

		return autExecSubStatementsWithResult(sql, parametros);	
	}

	/**
	 * Lista todas as colunas incluídas em uma tabela de dados
	 * 
	 * @param fullTableNameWithSchemaDB - Nome do schema + Nome de tabela, Exemplo: SchemaDB.NomeDaTabela 
	 * @return java.util.List - Lista de colunas da tabela
	 * 
	 */
	public java.util.List<String> listColumnsTable(String fullTableNameWithSchemaDB){
		java.util.List<String> listCols = new java.util.ArrayList<String>();
		try {

			java.sql.ResultSet rsColumns = getActiveConnection().getMetaData().getColumns(getActiveConnection().getCatalog(), getActiveConnection().getSchema(), fullTableNameWithSchemaDB, null);
			System.out.println("\n\n****************** REGISTRO - COLUNAS ***************");
			while(rsColumns.next()) {
				//System.out.println("\n\n****************** REGISTRO - COLUNAS ***************");
				for(int i = 1;i < rsColumns.getMetaData().getColumnCount();i++) {		
					if(rsColumns.getMetaData().getColumnName(i).contains("COLUMN_NAME")) {
						String col = rsColumns.getObject(i).toString();
						//System.out.println(String.format("%s=%s",rsColumns.getMetaData().getColumnName(i),rsColumns.getObject(i)));
						if(!listCols.contains(col)) {
							listCols.add(col);
						}						
					}
				}				
			}				
			for(String coluna : listCols) {
				System.out.println(String.format("TABELA : %s COLUNA: %s",fullTableNameWithSchemaDB,coluna));
			}			
			System.out.println("************************ REGISTRO COLUNAS : FIM **************************");
			return listCols;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return listCols;
		}		
	}

	/**
	 * 
	 * Carrega tabela de entrada SQLInput na tabela de saída SQLOutput
	 * 
	 * @param SQLInput - SQL de definição da tabela origem
	 * @param SQLOutput - SQL de definição da tabela saída  - Despejo
	 * @param paramInput - Parametros para SQL de entrada
	 * @param paramOutput - Parametros para o SQL de saída
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public boolean autLoaderDataTable(String SQLInput,String SQLOutput,Object[] paramInput,Object[] paramOutput) {
		try {
			System.out.println("AUT INFO: LOADER DATA TABLE FROM DATABASE");
			java.sql.ResultSet rsDataInput = autExecSubStatementsWithResult(SQLInput, paramInput);
			java.sql.ResultSet rsDataOutput = autExecSubStatementsWithResult(SQLInput, paramOutput);			
			java.util.HashMap<String, Integer> hashColumnsInput = new java.util.HashMap<String,Integer>();
			java.util.HashMap<String, Integer> hashColumnsOutput = new java.util.HashMap<String,Integer>();
			java.util.HashMap<String, Integer> hashColumnsMerge = new java.util.HashMap<String,Integer>();
			java.util.HashMap<Integer, Integer> hashColumnsMergeIndex = new java.util.HashMap<Integer,Integer>();			

			System.out.println("AUT INFO: LOADING COLUMNS FROM TARGET TABLE");
			for(int c = 1; c < rsDataOutput.getMetaData().getColumnCount();c++) {
				String col = rsDataOutput.getMetaData().getColumnName(c);
				hashColumnsOutput.put(col, c);	
			}	

			System.out.println("AUT INFO: LOADING COLUMNS FROM ORIGIN TABLE");			
			for(int c = 1; c < rsDataInput.getMetaData().getColumnCount();c++) {
				String col = rsDataInput.getMetaData().getColumnName(c);
				hashColumnsInput.put(col, c);
			}			

			System.out.println("AUT INFO: MERGE COLUMNS");
			for(String col : hashColumnsInput.keySet()) {
				if(hashColumnsOutput.containsKey(col)) {
					hashColumnsMergeIndex.put(hashColumnsInput.get(col), hashColumnsOutput.get(col));
					hashColumnsMerge.put(col, hashColumnsInput.get(col));
				}
			}
			System.out.println("AUT INFO: COLUMNS MERGE LIST");		
			System.out.println(hashColumnsMerge);
			java.util.regex.Pattern padrao = java.util.regex.Pattern.compile("\\w+\\.\\w+");
			java.util.regex.Matcher analyseInput = padrao.matcher(SQLInput);
			java.util.regex.Matcher analyseOutput = padrao.matcher(SQLOutput);
			String tabOrig = "",tabTarg = "";				
			if(analyseInput.find()){
				tabOrig = analyseInput.group();
			}
			if(analyseOutput.find()) {
				tabTarg = analyseOutput.group();
			}						
			String partInsert = hashColumnsMerge.keySet().toString().replaceAll("\\[", "(").replaceAll("\\]", ")");			
			String colunsInsert = "INSERT INTO " + tabTarg.concat(" ") + partInsert + " VALUES" + partInsert.replaceAll("(\\w+\\_\\w+)+", "\\?");
			String colunsInsertValue = "INSERT INTO " + tabTarg.concat(" ") + partInsert + " VALUES" + partInsert;

			System.out.println(colunsInsert);	
			System.out.println(colunsInsertValue);

			System.out.println(String.format("TOT.COLUMNS ORIGIN TABLE : %s TOT.COLUMNS TARGET TABLE: %s", 
					rsDataOutput.getMetaData().getColumnCount(),
					rsDataOutput.getMetaData().getColumnCount()));									
			while(rsDataInput.next()) {
				java.sql.PreparedStatement preStatement = getActiveConnection().prepareStatement(colunsInsert);
				int c = 1;
				for(String col : hashColumnsMerge.keySet()) {
					preStatement.setObject(c, rsDataInput.getObject(hashColumnsInput.get(col)));
					c++;
				}
				preStatement.execute();
				preStatement.close();
			}

			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LOADER DATA TABLE FROM DATABASE");
			System.out.println(e.getMessage());
			e.printStackTrace();			
			return false;
		}
	}


	/**
	 * Lista todas as tabelas criadas no base de dados ativa
	 * 
	 * @return java.util.List - Lista de Tabelas
	 * 
	 */
	public java.util.List<String> listTablesDB(){
		java.util.List<String> listTables = new java.util.ArrayList<String>();		
		java.sql.ResultSet rsTables = null;
		try {
			rsTables = getActiveConnection().getMetaData().getTables(
					getActiveConnection().getCatalog(), 
					getActiveConnection().getSchema(), null,
					new String[] {"TABLE","VIEW"});		
			while(rsTables.next()) {				
				for(int i = 1; i <= rsTables.getMetaData().getColumnCount();i++) {								
					String tab = rsTables.getObject(1).toString().concat(".").concat(rsTables.getObject(3).toString());	
					if(!listTables.contains(tab)) {
						listTables.add(tab);			
					}
				}					
			}
			System.out.println("\n\n*********** REGISTROS DE TABELAS: ************\n");	
			for(String table : listTables) {				
				System.out.println("TABELA : ".concat(table));
			}						
			return listTables;		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
			return null;
		}		
	}


	/**
	 * 
	 * Inicia uma conexão pré estabelecida com o banco de dados - Padrão para o ambiente
	 * 
	 * @return boolean - True caso o processo seja finalizado sucesso false caso contrário
	 * 
	 */
	public boolean autStartDefaultConnection() {
		try {

			System.out.println("AUT INFO: START DEFAULT CONNECTION WITH DATABASE");

			AUT_GLOBAL_CONFIG_OBJECT_CONNECTION = new AUTConnectorGlobalConfiguration();

			String hostDB = AUT_GLOBAL_CONFIG_OBJECT_CONNECTION.autGetDefaultGlobalConfiguration().get(AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_IP_VALUE.name()).toString();
			Integer portDB = Integer.parseInt(AUT_GLOBAL_CONFIG_OBJECT_CONNECTION.autGetDefaultGlobalConfiguration().get(AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_PORT_VALUE.name()).toString());
			String dbName = AUT_GLOBAL_CONFIG_OBJECT_CONNECTION.autGetDefaultGlobalConfiguration().get(AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_DATA_BASE_VALUE.name()).toString();
			String pwdDB = AUT_GLOBAL_CONFIGURATION.DEFAULT_PWD_DB.toString();
			String usrDB = AUT_GLOBAL_CONFIGURATION.DEFAULT_USR_DB.toString();
			System.out.println(AUT_GLOBAL_CONFIG_OBJECT_CONNECTION.autGetDefaultGlobalConfiguration());
			System.out.println(AUT_GLOBAL_CONFIG_OBJECT_CONNECTION.autGetDefaultGlobalConfiguration().get(AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_IP_VALUE.name()));
			autSetConnection(autStartNewConnection(AUT_TYPE_SGDB.MYSQL, hostDB, portDB, dbName, usrDB, pwdDB));

			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: START DEFAULT CONNECTION WITH DATABASE");
			System.out.println(e.getMessage());
			e.printStackTrace();

			return false;
		}
	}
	
	/**
	 * 
	 * Carrega os campos correspondentes a uma lista de propriedades - Definidas por enumeradores
	 * 
	 * @param sqlDefinition - SQL de definição da tabela
	 * @param enumProperties - Enumerador de definição das propriedades que serão armazenadas
	 * @param inputParameters - Parametros de entrada para SQL de definição da tabela
	 * @return java.util.HashMap(Integer,java.util.HashMap(String,Object)) - Tabela de dados - Por linha
	 * 
	 */
	public  java.util.HashMap<Integer,java.util.HashMap<String,Object>> autGetDataTableByProperties(String sqlDefinition,Class<? extends java.lang.Enum> enumProperties,Object[] inputParameters){
		try {
			autStartDefaultConnection();
			java.util.HashMap<Integer,java.util.HashMap<String,Object>> hashDataRows = new java.util.HashMap<Integer,java.util.HashMap<String,Object>>();
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(sqlDefinition, inputParameters);
			java.util.HashMap<String, Integer> ltColumns = autListColumns(rsData);	
			java.util.HashMap<String, Integer> ltPropertiesSelect = new java.util.HashMap<String,Integer>();
			int contRows = 0;
			
			for(java.lang.Enum enumItem : enumProperties.getEnumConstants()) {
				boolean bStatusSearch = AUTFMKUtils.autContentIsEqual(ltColumns.keySet().toArray(), enumItem.toString());
				System.out.println(String.format("AUT INFO: PROPERTY TABLE VALIDATION: PROP.NAME : %s : EXISTS IN TABLE : %s", enumItem.name(),(bStatusSearch ? "SIM" : "NAO")));					
				if(bStatusSearch) {
					ltPropertiesSelect.put(enumItem.toString(), ltPropertiesSelect.size());
				}
			}	
			
			System.out.println(ltPropertiesSelect.keySet().toString().replaceAll("\\[", "\\(").replaceAll("\\]", "\\)"));
			
			while(rsData.next()) {
				hashDataRows.put(contRows, new java.util.HashMap<String,Object>());
				for(String column: ltPropertiesSelect.keySet()) {
					hashDataRows.get(contRows).put(column, rsData.getObject(ltColumns.get(autGetContentFromListItems(ltColumns.keySet().toArray(), column))));
				}
				contRows++;
			}
			
			System.out.println(hashDataRows);
			return hashDataRows;
		}
		catch(java.lang.Exception e) {

			AUTLogsManage.registrarLog(e, "AUT ERROR: GET DATA TABLE BY PROPERTIES DEFINITION");			

			return null;
		}		
	}

	public  <TOption extends java.lang.Enum> java.util.HashMap<Integer,java.util.HashMap<String,Object>> autGetDataTableByPropertiesForClass(boolean onlySelectProperty,TOption sqlDefinition,Class<? extends java.lang.Enum> enumProperties,Object[] inputParameters){
		try {

			autStartDefaultConnection();
			java.util.HashMap<Integer,java.util.HashMap<String,Object>> hashDataRows = new java.util.HashMap<Integer,java.util.HashMap<String,Object>>();
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(sqlDefinition.toString(), inputParameters);
			java.util.HashMap<String, Integer> ltColumns = autListColumns(rsData);	
			java.util.HashMap<String, Integer> ltPropertiesSelect = new java.util.HashMap<String,Integer>();
			int contRows = 0;
			if(!onlySelectProperty) {
				for(java.lang.Enum enumItem : enumProperties.getEnumConstants()) {
					boolean bStatusSearch = AUTFMKUtils.autContentIsEqual(ltColumns.keySet().toArray(), enumItem.toString());
					System.out.println(String.format("AUT INFO: PROPERTY TABLE VALIDATION: PROP.NAME : %s : EXISTS IN TABLE : %s", enumItem.name(),(bStatusSearch ? "SIM" : "NAO")));					
					if(bStatusSearch) {
						ltPropertiesSelect.put(enumItem.toString(), ltPropertiesSelect.size());
					}
				}	
			}

			else 
			{
				boolean bStatusSearch = AUTFMKUtils.autContentIsEqual(ltColumns.keySet().toArray(), enumProperties.toString());

				//System.out.println(String.format("AUT INFO: PROPERTY TABLE VALIDATION: PROP.NAME : %s : EXISTS IN TABLE : %s", enumProperties.name(),(bStatusSearch ? "SIM" : "NAO")));
				if(bStatusSearch) {
					ltPropertiesSelect.put(enumProperties.toString(), ltPropertiesSelect.size());
				}
			}
			System.out.println(ltPropertiesSelect.keySet().toString().replaceAll("\\[", "\\(").replaceAll("\\]", "\\)"));
			while(rsData.next()) {
				hashDataRows.put(contRows, new java.util.HashMap<String,Object>());
				for(String column: ltPropertiesSelect.keySet()) {
					hashDataRows.get(contRows).put(column, rsData.getObject(ltColumns.get(autGetContentFromListItems(ltColumns.keySet().toArray(), column))));
				}
				contRows++;
			}
			System.out.println(hashDataRows);
			return hashDataRows;
		}
		catch(java.lang.Exception e) {

			AUTLogsManage.registrarLog(e, "AUT ERROR: GET DATA TABLE BY PROPERTIES DEFINITION");			

			return null;
		}		
	}

	/**
	 * 
	 * Construtor padrão
	 * 		
	 */
	public AUTJDBCProcess() {
		super();
	}
}
