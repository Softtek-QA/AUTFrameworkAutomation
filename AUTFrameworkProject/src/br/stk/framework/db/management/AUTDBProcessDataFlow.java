/**
 * 
 */
package br.stk.framework.db.management;

import static org.junit.Assert.assertNotNull;

import java.text.spi.DateFormatProvider;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.text.DateFormatter;

import org.junit.Test;

import br.stk.framework.tests.AUTFWKTestObjectBase;
import br.stk.framework.tests.AUTFWKTestObjectBase.AUTRuntimeExecutionScenario;
import jdk.nashorn.internal.parser.DateParser;

/**
 * 
 * Gerenciamento de interface para parametrização da execução
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBProcessDataFlow extends AUTDBProject {
	String AUT_TIME_SERVER_DB = "";
	/**
	 * 
	 * Condições para execução de determinados procedimentos
	 * 
	 * @author Softtek-QA
	 *
	 */
	public static enum AUT_SQL_OPERATIONS_CONDITIONS{
		UPDATE_DATAFLOW_PARAMETER_BY_PROJECT,
		UPDATE_DATAFLOW_PARAMETER_BY_PROJECT_AND_SCENARIO,
		DELETE_DATAFLOW_PARAMETER_BY_PROJECT,
		DETETE_DATAFLOW_PARAMETER_BY_PROJECT_AND_SCENARIO;		
		@Override
		public String toString() {
			switch(this) {
			case DELETE_DATAFLOW_PARAMETER_BY_PROJECT:{
				return " where pjt_id=?";
			}
			case DETETE_DATAFLOW_PARAMETER_BY_PROJECT_AND_SCENARIO:{
				return " where pjt_id=? and drv_process_name like ?;";
			}
			case UPDATE_DATAFLOW_PARAMETER_BY_PROJECT:{
				return " where pjt_id=?";
			}
			case UPDATE_DATAFLOW_PARAMETER_BY_PROJECT_AND_SCENARIO:{
				return " where pjt_id=? and drv_process_name=?;";
			}
			default:{
				return "";
			}
			}
		}
	}


	/**
	 * 
	 * Conjunto de tabelas que suportam dados de configuração do dataflow
	 * 
	 * @author Softtek-QA
	 *
	 */
	public static enum AUT_SQL_DATAFLOW_TABLES{
		PARAMETERS_SCENARIO_EXECUTION;		
		@Override
		public String toString() {
			switch(this) {
			case PARAMETERS_SCENARIO_EXECUTION:{
				return "lry.aut_projects_process_datadrivers";
			}
			default:{
				return super.toString();
			}
			}
		}
	}

	/**
	 * 
	 * Enumera as propriedades da tabela de configuração associada ao objeto
	 * 
	 * @author Softtek-QA
	 *
	 */
	public static enum AUT_SQL_PROPERTIES{
		DRV_ID,
		PJT_ID,
		DRV_PROCESS_NAME,
		DRV_PROCESS_DESCRIPTION,
		DRV_PARAMETER_NAME,
		DRV_PARAMETER_VALUE,
		DRV_IS_SYNCRONIZED,
		DRV_IS_DEPENDENT,
		DRV_IS_UPLOAD_LOCAL_DATA,
		DRV_DATE_UPLOAD,
		DRV_DATE_CHANGE,
		DRV_ROW
	}

	public static enum AUT_TYPE_FIELD_DATAFLOW{
		KEY,
		ROW,
		COLUMN,
		VALUE,
		ALL;
		@Override
		public String toString() {
			switch(this) {
			case ALL:{
				return ".*";
			}
			case COLUMN:{
				return ALL.toString();
			}
			case KEY:{
				return ALL.toString();
			}
			case ROW:{
				return ALL.toString();
			}
			case VALUE:{
				return ALL.toString();
			}
			default:{
				return ALL.toString();
			}
			}
		}
	}


	public static enum AUT_SQL_OPERATIONS_PROCESS_PARAMETERS{
		DELETE_PARAMETER,
		INSERT_PARAMETER,
		UPDATE_PARAMETER,
		SELECT_PARAMETER,
		AUT_INSERT_NEW_PARAMETERS,
		AUT_DELETE_PARAMETERS,
		AUT_DELETE_PARAMETERS_BY_SCENARIO,
		AUT_SELECT_ALL_PARAMETERS,
		AUT_SELECT_PARAMETERS_BY_SCENARIO,
		AUT_SELECT_PARAMETERS_BY_NAME,
		AUT_SELECT_PARAMETERS_BY_VALUE;

		@Override
		public String toString() {
			switch(this) {
			case DELETE_PARAMETER:{
				return "delete from lry.aut_projects_process_datadrivers %s";
			}
			case INSERT_PARAMETER:{
				return "insert into lry.aut_projects_process_datadrivers(%s) values(%s)";
			}
			case UPDATE_PARAMETER:{
				return "update lry.aut_projects_process_datadrivers set %s=? ";
			}
			case AUT_INSERT_NEW_PARAMETERS:{
				return "INSERT INTO lry.aut_projects_process_datadrivers(pjt_id,drv_process_name,drv_process_description,drv_parameter_name,drv_parameter_value,drv_row) VALUES(?,?,?,?,?,?);";
			}
			case AUT_DELETE_PARAMETERS:{
				return "delete from lry.aut_projects_process_datadrivers where pjt_id=?;";
			}
			case AUT_SELECT_ALL_PARAMETERS:{
				return "select * from lry.aut_projects_process_datadrivers;";
			}
			case AUT_SELECT_PARAMETERS_BY_NAME:{
				return AUT_SELECT_ALL_PARAMETERS.toString();
			}
			case AUT_SELECT_PARAMETERS_BY_SCENARIO:{
				return "select * from lry.aut_projects_process_datadrivers where drv_process_name like ?";
			}
			case AUT_SELECT_PARAMETERS_BY_VALUE:{
				return AUT_SELECT_ALL_PARAMETERS.toString();
			}
			case AUT_DELETE_PARAMETERS_BY_SCENARIO:{
				return "delete from lry.aut_projects_process_datadrivers where drv_process_name like ? and pjt_id=?";
			}
			default:{
				return this.name();
			}
			}
		}
	}

	/**
	 * 
	 * Retorna uma nova instância do objeto que representa um valor - Padrão para qualquer tabela
	 * 
	 * @return Object - Objeto para de retorno da função
	 * 
	 */
	private <T extends Object> T autGetDefaultObject(){
		return (T)new Object();
	}


	/**
	 * 
	 * 	
	 *  DRV_ID,
		PJT_ID,
		DRV_PROCESS_NAME,
		DRV_PROCESS_DESCRIPTION,
		DRV_PARAMETER_NAME,
		DRV_PARAMETER_VALUE,
		DRV_IS_SYNCRONIZED,
		DRV_IS_DEPENDENT,
		DRV_IS_UPLOAD_LOCAL_DATA,
		DRV_DATE_UPLOAD,
		DRV_DATE_CHANGE,
		DRV_ROW
	 */	
	public java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object> autGetRowParameter(){
		return new java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object>(br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES.DRV_ROW,autGetDefaultObject());
	}

	public java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object> autGetDateChangeParameter(){
		return new java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object>(br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES.DRV_DATE_CHANGE,autGetDefaultObject());
	}

	public java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object> autGetDateUploadParameter(){
		return new java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object>(br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES.DRV_DATE_UPLOAD,autGetDefaultObject());
	}


	public java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object> autGetIsUploadParameter(){
		return new java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object>(br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES.DRV_IS_UPLOAD_LOCAL_DATA,autGetDefaultObject());
	}


	public java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object> autGetIsDependentParameter(){
		return new java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object>(br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES.DRV_IS_DEPENDENT,autGetDefaultObject());
	}


	public java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object> autGetIsSyncronizedParameter(){
		return new java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object>(br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES.DRV_IS_SYNCRONIZED,autGetDefaultObject());
	}


	public java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object> autGetValueParameter(){
		return new java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object>(br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES.DRV_PARAMETER_VALUE,autGetDefaultObject());
	}


	public java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object> autGetNameParameter(){
		return new java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object>(br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES.DRV_PARAMETER_NAME,autGetDefaultObject());
	}


	public java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object> autGetProcessDescriptionParameter(){
		return new java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object>(br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES.DRV_PROCESS_DESCRIPTION,autGetDefaultObject());
	}

	public java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object> autGetProcessNameParameter(){
		return new java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object>(br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES.DRV_PROCESS_NAME,autGetDefaultObject());
	}

	public java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object> autGetProjectIdParameter(){
		return new java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object>(br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES.PJT_ID,autGetDefaultObject());
	}

	/**
	 * 
	 * Retorna a data e hora padrão do sistema de banco de dados
	 * 
	 * @return String - Data e hora no formato padrão do banco de dados
	 * 
	 */
	public String autDBDefaultDateTime() {
		java.util.Date dt = new Date();
		String dateOut = dt.toInstant().toString().replaceAll("[a-zA-Z]+", " ").trim();
		System.out.println(String.format("AUT INFO : DATE FORMAT BY INPUT DB",dateOut));
		return dateOut;
	}

	/**
	 * 
	 * Remove os parametros configurados para o projeto e processo específico:
	 * 
	 * PROJECT_ID - Id do projeto específico
	 * PROCESS_NAME - Nome do projeto
	 * 
	 * @param parameters - Parametros de configuração do procedimento
	 * 
	 * @return boolean caso o processo seja finalizado com sucesso
	 * 
	 */
	public boolean autUpdateParameters(java.util.HashMap<String,Object> parameters) {
		try {
			java.util.List<java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object>> parametersInput = new java.util.ArrayList<java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES,Object>>();
			java.util.List<java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object>> parametersConditions = new java.util.ArrayList<java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES,Object>>();

			System.out.println("AUT INFO: UPDATE PARAMETER BY PROJECT ID");
			System.out.println("AUT INFO: UPDATE PROCESS: INIT");

			java.util.AbstractMap.SimpleEntry<AUT_SQL_PROPERTIES,Object> item = null;
			
			item = new java.util.AbstractMap.SimpleEntry<AUT_SQL_PROPERTIES,Object>(AUT_SQL_PROPERTIES.valueOf(parameters.get("COLUMN_NAME").toString()),new Object());			
			item.setValue(parameters.get("COLUMN_VALUE"));			
			parametersInput.add(item);
			
			
			item = autGetProjectIdParameter();
			item.setValue(parameters.get("PROJECT_ID"));
			parametersConditions.add(item);

			item = autGetProcessNameParameter();
			item.setValue(parameters.get("PROCESS_NAME"));			
			parametersConditions.add(item);

			autDBParameterManagerDataFlow(AUT_SQL_OPERATIONS_PROCESS_PARAMETERS.UPDATE_PARAMETER, null, AUT_SQL_OPERATIONS_CONDITIONS.UPDATE_DATAFLOW_PARAMETER_BY_PROJECT_AND_SCENARIO, parametersInput, parametersConditions);
			System.out.println("AUT INFO : UPDATE PROCESS: END");			

			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: REMOVE PARAMETER BY PROJECT ID");
			System.out.println(e.getMessage());
			e.printStackTrace();

			return false;
		}
	}

	
	
	/**
	 * 
	 * Remove os parametros configurados para o projeto e processo específico:
	 * 
	 * PROJECT_ID - Id do projeto específico
	 * PROCESS_NAME - Nome do projeto
	 * 
	 * @param parameters - Parametros de configuração do procedimento
	 * 
	 * @return boolean caso o processo seja finalizado com sucesso
	 * 
	 */
	public boolean autRemoveParameters(java.util.HashMap<String,Object> parameters) {
		try {
			java.util.List<java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object>> parametersInput = new java.util.ArrayList<java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES,Object>>();
			java.util.List<java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object>> parametersConditions = new java.util.ArrayList<java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES,Object>>();

			System.out.println("AUT INFO: REMOVE PARAMETER BY PROJECT ID");
			System.out.println("AUT INFO: REMOVE PROCESS: INIT");

			java.util.AbstractMap.SimpleEntry<AUT_SQL_PROPERTIES,Object> item = autGetProjectIdParameter();
			item.setValue(parameters.get("PROJECT_ID"));
			parametersConditions.add(item);

			item = autGetProcessNameParameter();
			item.setValue(parameters.get("PROCESS_NAME"));			
			parametersConditions.add(item);

			autDBParameterManagerDataFlow(AUT_SQL_OPERATIONS_PROCESS_PARAMETERS.DELETE_PARAMETER, null, AUT_SQL_OPERATIONS_CONDITIONS.DETETE_DATAFLOW_PARAMETER_BY_PROJECT_AND_SCENARIO, parametersInput, parametersConditions);
			System.out.println("AUT INFO : REMOVE PROCESS: END");			

			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: REMOVE PARAMETER BY PROJECT ID");
			System.out.println(e.getMessage());
			e.printStackTrace();

			return false;
		}
	}

	/**
	 * <BR>
	 * Incluí um novo parametro de negócio para o processo de negócio específico<BR><BR><BR><BR>
	 * 
	 * 
	 * 
	 * 
	 * Atributos de configuração do parametro:<BR>
	 * PROJECT_ID - Id do projeto / Processo de negócio<BR><BR>
	 * 
	 * PROCESS_NAME - Nome do processo de negócio / Cenário de teste<BR><BR>
	 * 
	 * PROCESS_DESCRIPTION - Descrição do processo de negócio / Cenário de teste<BR><BR>
	 * 
	 * ROW_NUMBER - Numero da linha relacionada ao parametro na tabela de dados<BR><BR>
	 * 
	 * IS_DEPENDENT - Define se o parametro têm alguma dependência com o cenário anterior<BR><BR>
	 * 
	 * IS_SYNCRONIZED - Define o parametro será sincronizado de maneira sincrona caso possua depêndencia<BR><BR>
	 * 
	 * PARAMETER_NAME - Nome do parametro<BR><BR>
	 * 
	 * PARAMETER_VALUE - Valor do parametro<BR><BR>
	 * 
	 * PARAMETER_DATE_UPDATE - Data da última atualização feita no valor do parametro<BR>
	 * Formato: 2019-05-05 00:00:00<BR>
	 * yyyy-mm-dd hh:mm:ss<BR><BR>
	 * 
	 * PARAMETER_DATE_CHANGE - Data da última alteração feita nos atributos de configuração do parametro<BR>
	 * Formato: 2019-05-05 00:00:00<BR>
	 * yyyy-mm-dd hh:mm:ss<BR><BR><BR><BR>
	 *  
	 * 
	 * 
	 * Inseri um novo parametro no banco de dados para o processo associado<BR><BR>
	 * 
	 * @param parameter - Conjunto de atributos de configuração do parametro<BR><BR>
	 * @return boolean - Retorna true caso o processo seja finalizado com sucesso , false caso contrário
	 */
	public boolean autDBAddParameter(java.util.HashMap<String,Object> parameter) {
		try {
			System.out.println("AUT INFO: ADD PARAMETER : INIT");

			try {
				java.util.List<java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object>> paramInput = new java.util.ArrayList<java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES,Object>>();
				java.util.List<java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES, Object>> paramConditions = new java.util.ArrayList<java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES,Object>>();

				java.util.AbstractMap.SimpleEntry<br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_PROPERTIES,Object> item = null;

				item = autGetProjectIdParameter();
				item.setValue((parameter.get("PROJECT_ID")==null? 0 : parameter.get("PROJECT_ID")));
				paramInput.add(item);

				item = autGetProcessNameParameter();
				item.setValue(parameter.get("PROCESS_NAME"));
				paramInput.add(item);				

				item = autGetProcessDescriptionParameter();
				item.setValue(parameter.get("PROCESS_DESCRIPTION"));
				paramInput.add(item);


				item = autGetRowParameter();
				item.setValue(( parameter.get("ROW_NUMBER") == null ? 1 :parameter.get("ROW_NUMBER")));
				paramInput.add(item);

				item = autGetIsDependentParameter();
				item.setValue((parameter.get("IS_DEPENDENT")==null?false:parameter.get("IS_DEPENDENT")));
				paramInput.add(item);

				item = autGetIsSyncronizedParameter();
				item.setValue((parameter.get("IS_SYNCRONIZED")==null ? false : parameter.get("IS_SYNCRONIZED")));
				paramInput.add(item);


				item = autGetNameParameter();
				item.setValue(parameter.get("PARAMETER_NAME"));
				paramInput.add(item);

				item = autGetValueParameter();
				item.setValue(parameter.get("PARAMETER_VALUE"));
				paramInput.add(item);

				//"2019-05-05 00:00:00"
				item = autGetDateUploadParameter();
				item.setValue((parameter.get("PARAMETER_DATE_UPDATE")==null ? autDBDefaultDateTime() : parameter.get("PARAMETER_DATE_UPDATE")));
				paramInput.add(item);

				item = autGetDateChangeParameter();
				item.setValue((parameter.get("PARAMETER_DATE_CHANGE")==null ? autDBDefaultDateTime() : parameter.get("PARAMETER_DATE_CHANGE")));
				paramInput.add(item);


				autDBParameterManagerDataFlow(br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_OPERATIONS_PROCESS_PARAMETERS.INSERT_PARAMETER, 
						null, 
						br.stk.framework.db.management.AUTDBProcessDataFlow.AUT_SQL_OPERATIONS_CONDITIONS.DETETE_DATAFLOW_PARAMETER_BY_PROJECT_AND_SCENARIO, 
						paramInput, paramConditions);		

			}
			catch(java.lang.Exception e) {

			}

			System.out.println("AUT INFO: ADD PARAMETER : END");
			return true;
		}
		catch(java.lang.Exception e) {

			return false;
		}
	}

	/**
	 * Lista de colunas da estrutura de dados
	 * 
	 * @param columns - Colunas da tabela
	 * @return String - Colunas separadas por virgula
	 */	
	public String autGetDataFlowColumnsConfiguration(java.util.List<String> columns){
		try {
			System.out.println("AUT INFO : LIST COLUMNS CONFIGURATION BY DATAFLOW PROCESS");
			java.lang.StringBuffer strOut = new StringBuffer();
			String str = null;
			for(String col: columns) {
				strOut.append(col).append(",");
			}
			str = strOut.toString().substring(0, strOut.toString().length()-1);
			return str;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR : LIST COLUMNS CONFIGURATION BY DATAFLOW PROCESS");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return "";
		}
	}


	public String autGetDataFlowColumnsConfigurationWithInconigParameter(java.util.List<String> columns){
		try {
			if(columns.size() > 0 ) {
				System.out.println("AUT INFO : LIST COLUMNS CONFIGURATION BY DATAFLOW PROCESS");
				java.lang.StringBuffer strOut = new StringBuffer();
				String str = null;
				for(String col: columns) {
					strOut.append("?").append(",");
				}
				str = strOut.toString().substring(0, strOut.toString().length()-1);
				return str;
			}
			else {
				return "";
			}
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR : LIST COLUMNS CONFIGURATION BY DATAFLOW PROCESS");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * Adiciona um parametro na tabela de configurações
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso, false caso contrário
	 *  
	 */
	public <
	TTableFunction extends java.lang.Enum<AUT_SQL_OPERATIONS_PROCESS_PARAMETERS>,
	TTableCondition extends java.lang.Enum<AUT_SQL_OPERATIONS_CONDITIONS>,
	TTableOption extends java.lang.Enum<AUT_SQL_DATAFLOW_TABLES>,
	TTableField extends java.lang.Enum<AUT_SQL_PROPERTIES>,
	TItem extends java.util.List<java.util.AbstractMap.SimpleEntry<TTableField, Object>>> boolean autDBParameterManagerDataFlow(TTableFunction sqlFunction,TTableFunction sqlFunctionClear, TTableCondition condition,TItem parametersInput,TItem parametersConditions) {
		try {
			System.out.println("AUT INFO: ADD PARAMETER FROM TABLE CONFIGURATION : INIT");
			autStartDefaultConnection();	

			Object[] valuesParameters = new Object[parametersInput.size() + parametersConditions.size()];			
			int i1=0, i2=0;
			java.util.List<String> columnsInputTmp = new java.util.ArrayList<String>();		
			java.util.List<String> columnsCoditionsTmp = new java.util.ArrayList<String>();
			
			for(int i = 0; i< (valuesParameters.length) ; i++) {
				if(i<parametersInput.size()) {
					valuesParameters[i]=parametersInput.get(i1).getValue();
					columnsInputTmp.add(parametersInput.get(i1).getKey().toString());
					i1++;
				}
				else if(parametersInput.size()==0 && parametersConditions.size() > 0) {
					valuesParameters[i]=parametersConditions.get(i2).getValue();
					columnsCoditionsTmp.add(parametersConditions.get(i2).getKey().toString());
					i2++;
				}
				else if(i>=parametersInput.size()){
					valuesParameters[i]=parametersConditions.get(i2).getValue();
					columnsCoditionsTmp.add(parametersConditions.get(i2).getKey().toString());
					i2++;
				}
			}
			switch((AUT_SQL_OPERATIONS_PROCESS_PARAMETERS)sqlFunction) {
			case INSERT_PARAMETER:{
				autExecSubStatements(String.format(sqlFunction.toString(),autGetDataFlowColumnsConfiguration(columnsInputTmp),autGetDataFlowColumnsConfigurationWithInconigParameter(columnsInputTmp)), valuesParameters);
				break;
			}
			case DELETE_PARAMETER:{
				autExecSubStatements(String.format(sqlFunction.toString(),condition.toString()), valuesParameters);
				break;
			}
			case UPDATE_PARAMETER:{
				autExecSubStatements(String.format(String.format(sqlFunction.toString(),parametersInput.get(0).getKey()).concat(" %s "),condition.toString()), valuesParameters);
				break;
			}
			case SELECT_PARAMETER:{
				autExecSubStatements(sqlFunction.toString(), valuesParameters);
				break;
			}
			default:{
				System.out.println("AUT INFO: SQL PROCESS NOT DEFINED FOR OPERATION");	
				break;
			}
			}		
			System.out.println("AUT INFO: ADD PARAMETER FROM TABLE CONFIGURATION : END");					
			return true;
		}
		catch(java.lang.Exception e) {			
			System.out.println(String.format("AUT ERROR: ADD PARAMETER FROM TABLE CONFIGURATION"));
			System.out.println(e.getMessage());
			e.printStackTrace();			
			return false;
		}
	}


	/**
	 * 
	 */
	public AUTDBProcessDataFlow() {
		// TODO Auto-generated constructor stub
	}

}
