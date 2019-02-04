/**
 * 
 */
package br.stk.framework.tests;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.borland.silktest.jtf.BaseState;
import com.borland.silktest.jtf.BrowserBaseState;
import com.borland.silktest.jtf.Desktop;

import br.stk.framework.db.connectors.AUTConnectorGlobalConfiguration.AUT_GLOBAL_CONFIGURATION;
import br.stk.framework.gui.network.connectors.AUTNTWUtils;
import br.stk.framework.tests.AUTFWKTestObjectBase.AUTBusinessObject;
import br.stkframework.db.management.AUTDBProjectExecutionsOverview;
import br.stkframework.db.management.AUTDBProjectsExecutionDetail;
import junit.framework.JUnit4TestAdapter;


class AUTProcessRuntimeData{
	public String AUT_SCANARIOS = "TESTES";
	public static enum AUT_TYPE_OF_SUITE_OBJECTS{
		AUT_STRING,
		AUT_OBJECT,
		AUT_HASH_DATA,
		AUT_ENUMERATION,
		AUT_TEST_OBJECT,
		AUT_SCENARIO_OBJECT,
		AUT_SUITE_OBJECT,
		AUT_ALL_OBJECT,
		AUT_BUSINESS_PARAMETERS;

		@Override
		public String toString() {
			switch(this) {
			case AUT_BUSINESS_PARAMETERS:{
				return "br\\.stk\\.framework\\.tests\\.AUTFWKTestObjectBase\\$AUTBusinessObject";
			}
			case AUT_HASH_DATA:{
				return "java\\.util\\.HashMap.*";
			}
			case AUT_OBJECT:{
				return "java\\.lang\\.Object.*";
			}
			case AUT_STRING:{
				return "java\\.lang\\.String.*";
			}
			case AUT_ENUMERATION:{
				return "java\\.lang\\.Enum.*";
			}
			case AUT_TEST_OBJECT:{
				return "AUT\\_.*";
			}
			case AUT_ALL_OBJECT:{
				String outTypes = "(?i:";
				for(java.lang.Enum enu : this.getClass().getEnumConstants()) {
					if(enu.name()!=AUT_ALL_OBJECT.name()) {
						outTypes+=enu.toString().concat("|");
					}
				}
				outTypes+=")";	
				outTypes = outTypes.replaceAll("\\|\\)", ")");
				return outTypes;
			}
			case AUT_SCENARIO_OBJECT:{
				return "AUT_SCENARIO_.*";
			}
			case AUT_SUITE_OBJECT:{
				return "AUT_SUITE_.*";
			}
			default:{
				return this.getClass().getName();
			}
			}
		}		
	}


	public  <TTypeClass extends br.stk.framework.tests.AUTFWKTestObjectBase.AUTBusinessObject<AUTFWKTestObjectBase>,
	TTypeObject extends java.lang.Enum<AUT_TYPE_OF_SUITE_OBJECTS>> boolean autRTIsSuperClass(TTypeClass classeObjeto,TTypeObject optionSelect) {
		return ((autGetRTSuperClass(classeObjeto, optionSelect)!=null ? true : false));
	} 
	
	public  static <TTypeClass extends br.stk.framework.tests.AUTFWKTestObjectBase.AUTBusinessObject<AUTFWKTestObjectBase>,
	TTypeObject extends java.lang.Enum<AUT_TYPE_OF_SUITE_OBJECTS>> String autGetRTSuperClass(TTypeClass classeObjeto,TTypeObject optionSelect) {
		Class currentItem = classeObjeto.getClass().getSuperclass();
		String nameOut = "";
		java.util.regex.Pattern regExp = java.util.regex.Pattern.compile(optionSelect.toString());
		boolean statusPesquisa = false;	
			while(currentItem != null) {				
				try {
					nameOut = currentItem.getName();
					java.util.regex.Matcher verifRegExp = regExp.matcher(nameOut);
					if(verifRegExp.find()) {
						System.out.println(String.format("AUT INFO: CLASS FOUND BY OBJECT: %s",optionSelect.name()));
						System.out.println(nameOut);
						statusPesquisa = true;
						return verifRegExp.group();
					}
					currentItem = currentItem.getSuperclass();
					
				}
				catch(java.lang.NullPointerException e) {
					currentItem = null;
				}
			}			
			
			if(!statusPesquisa) {
				nameOut = null;
			}
			
			return nameOut;
	}

	

	public static <TAUTObject extends AUTBusinessObject<AUTFWKTestObjectBase>> java.util.HashMap<String,Object> autGetRuntimeDataObjects(TAUTObject classObject,AUT_TYPE_OF_SUITE_OBJECTS typesOutput) throws IllegalArgumentException, IllegalAccessException{
		java.util.HashMap<String,Object> dataObjs = new java.util.HashMap<String,Object>();
		String searchObject = typesOutput.toString();
		java.util.regex.Pattern regExp = java.util.regex.Pattern.compile(searchObject);

		java.util.regex.Matcher verifExp = null;
		java.util.regex.Matcher verifTypeExp = null;
		
		for(java.lang.reflect.Field fld : classObject.getClass().getFields()) {
			String name = fld.getName();
			verifExp = regExp.matcher(name);
			verifTypeExp = regExp.matcher(fld.getType().getName());
			boolean bVerifName = verifExp.find();
			boolean bVerifType = verifTypeExp.find();

			if(bVerifName || bVerifType) {

				if(bVerifName) {
					System.out.println("AUT INFO: FIND BY NAME : NAME");				
				}
				else {
					System.out.println("AUT INFO: FIND BY TYPE : NAME");									
				}

				System.out.println("AUT INFO: DATA OBJECT : NAME");
				System.out.println(fld.getName());
				System.out.println("AUT INFO: DATA OBJECT : TYPE");
				System.out.println(fld.getType().getName());		
				System.out.println("AUT INFO: DATA OBJECT : VALUE");
				System.out.println(fld.get(classObject));	
				
				if(dataObjs.containsKey(name)) {
					dataObjs.remove(name);
					dataObjs.put(name, fld.get(classObject));
				}
				else {
					dataObjs.put(name, fld.get(classObject));				
				}
			}
		}
		return dataObjs;
	}


	/**
	 * 
	 * Retorna os definidos em tempo de execu��o
	 * 
	 * 
	 * @return java.util.HashMap - Dados gerados em tempo de execu��o
	 * 
	 */
	public  <TAUTObject extends AUTBusinessObject> java.util.HashMap<String,Object> autGetRuntimeDataObject(TAUTObject runtimeClass,AUT_TYPE_OF_SUITE_OBJECTS typeObject){
		java.util.HashMap<String,Object> dataOut = new java.util.HashMap<String,Object>();
		switch(typeObject) {
		case AUT_BUSINESS_PARAMETERS:{	
			System.out.println("AUT INFO : FILTRO DE PESQUISA (BUSINESS OBJECT) EM TEMPO DE EXECUCAO: HABILITADO");
			autGetRTSuperClass(runtimeClass,AUT_TYPE_OF_SUITE_OBJECTS.AUT_BUSINESS_PARAMETERS);
			try {
				dataOut = AUTProcessRuntimeData.autGetRuntimeDataObjects(runtimeClass, typeObject);			
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
			return dataOut;
		}
		case AUT_ALL_OBJECT:{
			System.out.println("AUT INFO : FILTRO DE PESQUISA (ALL OBJECTS TYPES) EM TEMPO DE EXECUCAO: HABILITADO");
			try {
				dataOut = AUTProcessRuntimeData.autGetRuntimeDataObjects(runtimeClass, typeObject);			
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
			return dataOut;			
		}
		case AUT_ENUMERATION:{
			System.out.println("AUT INFO : FILTRO DE PESQUISA (ENUMERATION) EM TEMPO DE EXECUCAO: HABILITADO");
			try {
				dataOut = AUTProcessRuntimeData.autGetRuntimeDataObjects(runtimeClass, typeObject);			
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
			return dataOut;			
		}
		case AUT_HASH_DATA:{
			System.out.println("AUT INFO : FILTRO DE PESQUISA (HASH OBJECT) EM TEMPO DE EXECUCAO: HABILITADO");
			try {
				dataOut = AUTProcessRuntimeData.autGetRuntimeDataObjects(runtimeClass, typeObject);			
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
			return dataOut;			
		}
		case AUT_OBJECT:{
			System.out.println("AUT INFO : FILTRO DE PESQUISA (TYPE OBJECT) EM TEMPO DE EXECUCAO: HABILITADO");
			try {
				dataOut = AUTProcessRuntimeData.autGetRuntimeDataObjects(runtimeClass, typeObject);			
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
			return dataOut;			
		}
		case AUT_SCENARIO_OBJECT:{
			try {
				System.out.println("AUT INFO : FILTRO DE PESQUISA (SCENARIO OBJECTS) EM TEMPO DE EXECUCAO: HABILITADO");
				dataOut = AUTProcessRuntimeData.autGetRuntimeDataObjects(runtimeClass, typeObject);			
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
			return dataOut;			
		}
		case AUT_STRING:{
			System.out.println("AUT INFO : FILTRO DE PESQUISA (STRING) EM TEMPO DE EXECUCAO: HABILITADO");
			try {
				dataOut = AUTProcessRuntimeData.autGetRuntimeDataObjects(runtimeClass, typeObject);			
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
			return dataOut;			
		}
		case AUT_SUITE_OBJECT:{
			System.out.println("AUT INFO : FILTRO DE PESQUISA (SUITE OBJECT) EM TEMPO DE EXECUCAO: HABILITADO");
			try {
				dataOut = AUTProcessRuntimeData.autGetRuntimeDataObjects(runtimeClass, typeObject);			
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
			return dataOut;			
		}
		case  AUT_TEST_OBJECT:{
			System.out.println("AUT INFO : FILTRO DE PESQUISA (TEST OBJECT) EM TEMPO DE EXECUCAO: HABILITADO");
			try {
				dataOut = AUTProcessRuntimeData.autGetRuntimeDataObjects(runtimeClass, typeObject);			
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
			return dataOut;			
		}
		default:{
			System.out.println("AUT INFO : FILTRO DE PESQUISA GERAL PARA OBJETOS EM TEMPO DE EXECUCAO: HABILITADO");
			try {
				dataOut = AUTProcessRuntimeData.autGetRuntimeDataObjects(runtimeClass, typeObject);			
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
			return dataOut;			
		}
		}
	}



	public AUTProcessRuntimeData() {
		// TODO Auto-generated constructor stub
	}

}

/**
 * 
 * Componente base para implementa��o de testes de software
 * 
 * 
 * @author Softtek - QA
 *
 */
public class AUTFWKTestObjectBase extends AUTProcessRuntimeData{
	private String autEnvironmentDirDefault = "temp";
	private String autDefaultDirOutput = "C:\\Users\\ADMINI~1\\AppData\\Local\\Temp\\AUT_OUTPUT_FILES";
	public static Desktop AUT_AGENT_SILK4J = new Desktop();  //Objeto de conex�o com aplica��o da automa��o
	public BrowserBaseState AUT_BASE_STATE_CONFIGURATION_BROWSER = null; //Objeto base de configura�ao do browser	
	public java.util.List<String> AUT_LIST_PROJECTS_LOADER; //Lista de projetos corrente de projetos associados a execu��o atual
	public AUTDBProjectsExecutionDetail AUT_PROJECT_EXECUTION_DETAIL_OBJ = null;
	/**
	 * Enumera as op��es de configura��o para express�es regulares de configura��o, usadas na carga de parametros em empo 
	 * 
	 */
	public static enum AUT_REG_EXPRESSION_CONFIG_TESTS{
		SCENARIO_NAME,
		SCENARIO_TEST,
		PROJECT_NAME,
		PROJECT_ID,
		RESPONSE_DATA_EMPTY,
		SCENARIO_DESCRIPTION_OUTPUT,
		SYSTEM,
		INDEX_EXECUTION;
		@Override
		public String toString() {
			switch(this) {
			case INDEX_EXECUTION:{
				return "IT\\d+";
			}
			case SYSTEM:{
				return "ST[a-zA-Z]+";
			}
			case SCENARIO_DESCRIPTION_OUTPUT:{
				return " : CENARIO : %s";
			}
			case RESPONSE_DATA_EMPTY:{
				return "ND";
			}
			case SCENARIO_NAME:{
				return "(?i:CN\\d+(\\_{0,}\\w+\\_{0,}))";
			}
			case PROJECT_ID:{
				return "ID\\d+";
			}
			case PROJECT_NAME:{
				return "FRT\\d+";
			}
			case SCENARIO_TEST:{
				return "(?i:AUT\\_IT\\d+\\_ST\\w+\\_ID\\d+\\_FRT\\d+\\_(\\w+\\_{0,})+)";
			}
			default:{
				return this.name();
			}
			}
		}
	}


	



	public static class AUTBusinessParameters extends AUTBusinessObject{
		java.util.HashMap<String,AUTBusinessObject> hashData = null;

		public java.util.HashMap<String,AUTBusinessObject> autGetRTBusinessData(){
			return hashData;
		}
	}


	/**
	 * 
	 * Objeto do neg�cio - Representa um objeto significativo para o neg�cio, seu tipo pode variar de um tipo primitivo de dados at�
	 * uma classe complexa com sistema pr�prio para o gerenciamento de dados
	 * 
	 * @author Softtek-QA
	 *
	 */
	public static abstract class AUTBusinessObject<TFrameworkObject extends AUTFWKTestObjectBase> implements Map.Entry{
		public boolean AUT_IS_DYNAMIC_DATA = false;
		private String AUT_NAME = this.getClass().getName();
		private Object AUT_VALUE = null;
		java.util.HashMap<String, String> hashData = null;
		TFrameworkObject dataManagement = null;
		
		public java.util.HashMap<String,Object> autGetRuntimeData(){
			dataManagement = (TFrameworkObject) new AUTFWKTestObjectBase();
			return dataManagement.autGetRuntimeDataObject(this, AUT_TYPE_OF_SUITE_OBJECTS.AUT_ALL_OBJECT);
		}
		
		/**
		 * 
		 * Desabilita a sincroniza��o de dados com o banco de dados para o parametros
		 * 
		 */
		public void disableDynamicData() {
			AUT_IS_DYNAMIC_DATA = false;
		}


		/**
		 * 
		 * Habilita a sincroniza��o de dados din�micamente com o banco de dados
		 * 
		 */
		public void enableDynamicData() {
			AUT_IS_DYNAMIC_DATA = true;
		}

		/**
		 * 
		 * Retorna a chave de identifica��o do objeto
		 * 
		 */
		@Override
		public Object getKey() {
			// TODO Auto-generated method stub
			return AUT_NAME;
		}

		/**
		 * 
		 * Retorna o valor da chave do objeto
		 * 
		 */
		@Override
		public Object getValue() {
			// TODO Auto-generated method stub
			return AUT_VALUE;
		}

		/**
		 * 
		 * 
		 */
		@Override
		public Object setValue(Object newValue) {
			// TODO Auto-generated method stub			
			AUT_VALUE = newValue;

			return getValue();
		}				
	}

	public static class AUTRuntimeExecutionScenario{
		java.util.regex.Pattern regExp = java.util.regex.Pattern.compile("\\d+");
		java.util.regex.Matcher verifId = null;
		String formatOut = "AUT DATA : PROJECT NAME : %s ID : %s : SCENARIO : %s : FULL NAME : %s : ID NUMBER: %s : ORDEM EXECUCAO : %s : SISTEMA : %s";
		public String AUT_PROJECT_NAME=null;
		public String AUT_PROJECT_ID=null;
		public String AUT_SCENARIO_NAME=null;	
		public String AUT_SCENARIO_FULL_NAME = null;
		public String AUT_SYSTEM = null;
		public String AUT_INDEX_EXECUTION = null;

		/**
		 * 
		 * Retorna o d�gito num�rico relacionado ao ID do projeto
		 * 
		 * @return String - digito n�mero do projeto
		 * 
		 */
		public String autGetIdNumber() {
			java.util.regex.Pattern regExp = java.util.regex.Pattern.compile("\\d+");
			java.util.regex.Matcher verif = regExp.matcher(AUT_PROJECT_ID);
			if(verif.find()) {
				Integer id = Integer.parseInt(verif.group());
				return id.toString();
			}
			else {
				return "";
			}
		}

		/**
		 * 
		 * Imprime os dados carregados em tempo de execu��o em tempo de execu��o
		 * 
		 */
		public void autPrintData() {
			System.out.println(String.format(formatOut, 
					AUT_PROJECT_NAME,
					AUT_PROJECT_ID,
					AUT_SCENARIO_NAME,
					AUT_SCENARIO_FULL_NAME
					,autGetIdNumber(),AUT_INDEX_EXECUTION,AUT_SYSTEM));			
		}
	} 


	public enum AUT_SCRIPTS_COMMANDS_CONFIGURATION{
		AUT_CREATE_DIRECTORY,
		AUT_DELETE_DIRECTORY;
		@Override
		public String toString() {
			switch(this) {
			case AUT_CREATE_DIRECTORY:{
				return "cmd /c mkdir %s";
			}
			case AUT_DELETE_DIRECTORY:{
				return "cmd /c rmdir /q /s %s";
			}
			default:{				
				return this.toString();
			}
			}
		}
	}
	/**
	 * 
	 * Lista de status poss�veis para configura��o dos testes
	 * 
	 * @author Softtek-QA
	 *
	 */
	public static enum AUT_TIPO_STATUS_OBJETO{
		PASSOU,
		FALHOU,
		CANCELADO,
		EM_EXECUCAO;

		@Override
		public String toString() {
			// TODO Auto-generated method stub

			switch(this) {
			case CANCELADO:{
				return "CANCELADO";
			}
			case EM_EXECUCAO:{
				return "EM EXECU��O";
			}
			case FALHOU:{
				return "FALHOU";
			}
			case PASSOU:{
				return "PASSOU";
			}
			}
			return super.toString();
		}
	}

	/**
	 * Item de configura��o do projeto - Entidade de testes
	 * 
	 * @author Softtek-QA
	 *
	 */
	public static enum AUT_TIPO_ITEM_CONFIGURACAO{
		PROJECT,
		TEST,
		SCENARIO,
		MODULE;

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			switch(this) {
			case MODULE:{
				return "M�DULO NEG�CIO";
			}
			case PROJECT:{
				return "FRENTE DE NEG�CIO";
			}
			case SCENARIO:{
				return "CEN�RIO DE NEG�CIO";
			}
			case TEST:{
				return "CASO DE TESTE";
			}
			}
			return super.toString();
		}
	}

	/**
	 * 
	 * Entidade para configura��o da estrutura de dados do projeto
	 * 
	 * @author Softtek-QA
	 *
	 */
	public static class AUT_TEST_CONFIGURATION{
		public static String AUT_FRENTE_PROJETO = "1";
		public static Integer AUT_ID_PROJETO = 1;
		public static String AUT_DATE_EXECUTION = ""; 
		public static Integer AUT_TOTAL_MODULOS = 1;
		public static Integer AUT_TOTAL_SCENARIOS = 1;
		public static Integer AUT_TOTAL_TEST_CASE = 1;
		public static Integer AUT_TOTAL_MODULOS_STATUS_PASSED = 1;
		public static Integer AUT_TOTAL_SCENARIOS_STATUS_PASSED = 1; 
		public static Integer AUT_TOTAL_TEST_CASE_STATUS_PASSED = 1;
		public static Integer AUT_TOTAL_MODULOS_STATUS_FAILED = 1; 
		public static Integer AUT_TOTAL_SCENARIOS_STATUS_FAILED = 1; 
		public static Integer AUT_TOTAL_TEST_CASE_STATUS_FAILED = 1;
		public static Integer AUT_TOTAL_MODULOS_EXEC_IN_PROGRESS = 1;
		public static Integer AUT_TOTAL_SCENARIOS_EXEC_IN_PROGRESS = 1; 
		public static Integer AUT_TOTAL_TEST_CASE_EXEC_IN_PROGRESS = 1;

		public static class AUT_TEST_CONFIG{
			public static Integer AUT_PJT_ID;
			public static String AUT_DSK_ID; 
			public static String AUT_VM_ID;
			public static String AUT_DSK_NAME;
			public static String AUT_VM_NAME;
			public static String AUT_DSK_IP;
			public static String AUT_VM_IP;
			public static String AUT_STD_ITEM_CONFIGURATION;
			public static String AUT_STD_STATUS = "WAIT";
		}
	}


	/**
	 * Interface de gerenciamento JUnit
	 * 
	 * @author Softtek-QA
	 *
	 */
	public static class AUTJUnitManager {		


		/**
		 * Carrega os registros do Hash de dados onde a chave e o valor correspondem as express�es regulares
		 * 
		 * @param regExpKey - Express�o regular para chave do hash
		 * @param regExpValue - Expressao regular para o valor relacionado a chave corrente
		 * @param parametros - Hash de dados para pesquisa
		 * 
		 * @return Hash - registros que correspondem as express�es regulares
		 */
		public java.util.HashMap<String,String> autFindItemOnHash(String regExpKey,String regExpValue,java.util.HashMap<String,String> parametros){
			java.util.HashMap<String,String> paramsOut = new java.util.HashMap<String,String>();
			java.util.regex.Pattern padraoKey = java.util.regex.Pattern.compile(regExpKey);
			java.util.regex.Pattern padraoValue = java.util.regex.Pattern.compile(regExpValue);
			for(String key : parametros.keySet()) {
				java.util.regex.Matcher analise = padraoKey.matcher(key);
				if(analise.find()) {
					analise = padraoValue.matcher(parametros.get(key));
					if(analise.find()) {
						System.out.println(String.format("SEARCH HASH: KEY: %s VALUE:%s", key,parametros.get(key)));
						paramsOut.put(key, parametros.get(key));
					}
				}
			}
			return paramsOut;
		}


		/**
		 * 
		 * Retorna uma lista com os testes implementados na classe especificada
		 *  
		 * @param classImplementaTest - Classe
		 * 
		 * @return Hash - Key - nome do teste - Value - pacote extendido
		 * 
		 */
		public java.util.HashMap<String, String> autGetTestList(Class<?> classImplementaTest){
			try {				
				java.util.HashMap<String,String> hashTests = new java.util.HashMap<String,String>();
				junit.framework.JUnit4TestAdapter junitAdap = new JUnit4TestAdapter(classImplementaTest);
				java.util.regex.Pattern padraoKey = java.util.regex.Pattern.compile("AUT\\_\\w+");
				java.util.regex.Pattern padraoValue = java.util.regex.Pattern.compile(".*");		
				java.util.regex.Matcher analiseKey = null, analiseValue = null;
				for(junit.framework.Test testItem : junitAdap.getTests()) {
					analiseKey = padraoKey.matcher(testItem.toString());
					if(analiseKey.find()) {
						analiseValue = padraoValue.matcher(testItem.toString());
						if(analiseValue.find()) {
							hashTests.put(analiseKey.group(), analiseValue.group());
						}
					}
				}
				System.out.println(hashTests);
				for(String key : hashTests.keySet()) {
					System.out.println(key);
					System.out.println(hashTests.get(key));
				}
				return hashTests;			
			}
			catch(java.lang.Exception e) {
				System.out.println("AUT JUNIT MANAGER : GET TEST LIST FROM CLASS");
				System.out.println(e.getMessage());
				e.printStackTrace();
				return null;
			}
		}

		/**
		 * 
		 * Retorna uma lista com os testes implementados na classe especificada
		 *  
		 * @param classImplementaTest - Classe
		 * 
		 * @return Hash - Key - nome do teste - Value - pacote extendido
		 * 
		 */
		public java.util.HashMap<String, String> autGetTestListQA(Class<?> classImplementaTest){
			try {				
				java.util.HashMap<String,String> hashTests = new java.util.HashMap<String,String>();
				junit.framework.JUnit4TestAdapter junitAdap = new JUnit4TestAdapter(classImplementaTest);
				java.util.regex.Pattern padraoKey = java.util.regex.Pattern.compile("AUT\\_\\w{2}\\d{5}(\\_\\w+)");
				java.util.regex.Pattern padraoValue = java.util.regex.Pattern.compile(".*");		
				java.util.regex.Matcher analiseKey = null, analiseValue = null;

				for(junit.framework.Test testItem : junitAdap.getTests()) {
					analiseKey = padraoKey.matcher(testItem.toString());
					if(analiseKey.find()) {
						analiseValue = padraoValue.matcher(testItem.toString());
						if(analiseValue.find()) {
							hashTests.put(analiseKey.group(), analiseValue.group());
						}
					}
				}

				System.out.println(hashTests);

				for(String key : hashTests.keySet()) {
					System.out.println(key);
					System.out.println(hashTests.get(key));
				}

				return hashTests;			
			}
			catch(java.lang.Exception e) {
				System.out.println("AUT JUNIT MANAGER : GET TEST LIST FROM CLASS");
				System.out.println(e.getMessage());
				e.printStackTrace();
				return null;
			}
		}

		/**
		 * 
		 * M�todo base de configura��o
		 * 
		 */
		public void autConfig() {

		}

		/**
		 * 
		 * Construtor padr�o da classe
		 * 
		 */
		public AUTJUnitManager() {
			try {
				autConfig();
			}
			catch(java.lang.Exception e) {
				System.out.println("AUT JUNIT MANAGER : ERROR CONFIGURATION ON INICIALIZATION");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}



	/**
	 * Retorna o objeto de gerenciamento JUnit
	 * 
	 * @return TJUnitManager - Classe que extende JUnit Manager
	 */
	public <TJUnitManager extends AUTJUnitManager> TJUnitManager autGetJUnitManager() {
		return (TJUnitManager) new AUTJUnitManager();
	}

	/**
	 * 
	 * Define o diret�rio para despejo dos arquivo de evid�ncia
	 * 
	 */
	public String autGetDefaultDirOutputFiles() {
		try {
			java.lang.Runtime.getRuntime().exec(
					String.format(AUT_SCRIPTS_COMMANDS_CONFIGURATION.AUT_CREATE_DIRECTORY.toString(),autDefaultDirOutput));	

			return autDefaultDirOutput;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("AUT FWK OBJECT : ERROR : DEFINE DEFAULT DIRECTORY FOR FILES OUTPUT");
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println("AUT FWK OBJECT : DIRETORIO PADRAO DEFINIDO AUTOMATICAMENTE");
			System.out.println(System.getenv(autEnvironmentDirDefault));

			return System.getenv(autEnvironmentDirDefault);
		}		

	}

	/**
	 * 
	 * Retorna o objeto de gerenciamento para execu��es dos projetos - Execu��o resumidas por frente de neg�cio
	 * 
	 * @return TProjectExecOverview - Tipo que extende AUTDBProjectExecutionsOverview
	 * 
	 */
	public <TProjectExecOverview extends AUTDBProjectExecutionsOverview> TProjectExecOverview autGetProjectManagerOverview() {
		return (TProjectExecOverview) new AUTDBProjectExecutionsOverview();
	}


	public <TStateExecution extends java.lang.Enum>boolean autSyncStateExecution(TStateExecution optionState) {
		try {
			System.out.println("AUT INFO: SYNC STATE FOR EXECUTION : INIT");
			String projectId = autGetCurrentScenarioRuntime().autGetIdNumber();
			String scenarioId = autGetCurrentScenarioRuntime().AUT_SCENARIO_FULL_NAME;
			autTestExecProcessDataBase(optionState.toString(), new Object[] {scenarioId,projectId});
			autSyncronizeScreen();
			System.out.println("AUT INFO: SYNC STATE FOR EXECUTION : END");
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: SYNC STATE FOR EXECUTION");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}


	public <TOption extends java.lang.Enum> void autTestExecProcessDataBase(String sqlDefinition,Object[] parameters) {
		try {
			System.out.println("AUT TEST OBJECT : PROCESS UPDATE PROJECTS BY IDS : START");
			autGetProjectManagerOverview().autExecSubStatementsDefault(sqlDefinition.toString(),parameters);
			autSyncronizeScreen();
			System.out.println("AUT TEST OBJECT : PROCESS UPDATE PROJECTS BY IDS : END");
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT TEST OBJECT : ERROR : EXEC UPDATE PROJECTS BY IDS : START");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Recupera o objeto desktop ativo pr� configurado - SILK4J
	 * 
	 */
	public com.borland.silktest.jtf.Desktop autGetDesktopAgent() {
		if(AUT_AGENT_SILK4J!=null) {
			return AUT_AGENT_SILK4J;
		}
		else {
			AUT_AGENT_SILK4J = new Desktop();
			return AUT_AGENT_SILK4J;
		}
	}

	/**
	 *
	 *Altera o agente de execu��o ativo - SILK4J
	 * 
	 */
	public void autSetDesktopAgent(com.borland.silktest.jtf.Desktop agentDesktop) {
		AUT_AGENT_SILK4J = agentDesktop;
	}

	/**
	 * 
	 * Valida se existe um cen�rio na pilha de execu��o
	 * 
	 * Formato padr�o do m�todo de chamada do cen�rio : AUT_IT000012_STVA_ID00001_FRT001_CN00001_CADASTRO_ITEMS
	 * 
	 * 
	 */
	public static <TScenarioConfig extends AUTRuntimeExecutionScenario> TScenarioConfig autGetCurrentScenarioRuntime() {
		AUTRuntimeExecutionScenario rtmScn = new AUTRuntimeExecutionScenario();
		try {	
			String value = autGetRuntimeScenarioExecution(AUT_REG_EXPRESSION_CONFIG_TESTS.SCENARIO_TEST.toString()); //Verifica a exist�ncia de um cen�rio na pilha de execu��o dos testes			
			String outScenario = AUT_REG_EXPRESSION_CONFIG_TESTS.SCENARIO_DESCRIPTION_OUTPUT.toString();
			Boolean status = (value==null ? false : true);		
			java.util.regex.Pattern regExp = null;
			if(status) {	
				regExp = java.util.regex.Pattern.compile(AUT_REG_EXPRESSION_CONFIG_TESTS.INDEX_EXECUTION.toString());
				java.util.regex.Matcher verifIndex = regExp.matcher(value);
				if(verifIndex.find()) {
					rtmScn.AUT_INDEX_EXECUTION = verifIndex.group();
					regExp = java.util.regex.Pattern.compile(AUT_REG_EXPRESSION_CONFIG_TESTS.SYSTEM.toString());
					java.util.regex.Matcher verifSystem = regExp.matcher(value);
					if(verifSystem.find()) {
						rtmScn.AUT_SYSTEM = verifSystem.group();
						regExp = java.util.regex.Pattern.compile(AUT_REG_EXPRESSION_CONFIG_TESTS.PROJECT_ID.toString());				
						java.util.regex.Matcher verifProjId = regExp.matcher(value);
						rtmScn.AUT_SCENARIO_FULL_NAME=value;
						if(verifProjId.find()) {
							rtmScn.AUT_PROJECT_ID=verifProjId.group();
							regExp = regExp.compile(AUT_REG_EXPRESSION_CONFIG_TESTS.PROJECT_NAME.toString());
							java.util.regex.Matcher verifProjName = regExp.matcher(value);
							if(verifProjName.find()) {
								rtmScn.AUT_PROJECT_NAME=verifProjName.group();
								regExp = regExp.compile(AUT_REG_EXPRESSION_CONFIG_TESTS.SCENARIO_NAME.toString());
								java.util.regex.Matcher verifScenarioName = regExp.matcher(value);
								if(verifScenarioName.find()) {
									rtmScn.AUT_SCENARIO_NAME=verifScenarioName.group();
									rtmScn.autPrintData();
								}
							}
						}			
					}
				}
				outScenario = String.format(outScenario,value).toUpperCase();		
				rtmScn.autPrintData();
				return (TScenarioConfig) rtmScn;
			}
			else {
				rtmScn.AUT_PROJECT_ID=AUT_REG_EXPRESSION_CONFIG_TESTS.RESPONSE_DATA_EMPTY.toString();
				rtmScn.AUT_PROJECT_NAME=AUT_REG_EXPRESSION_CONFIG_TESTS.RESPONSE_DATA_EMPTY.toString();
				rtmScn.AUT_SCENARIO_NAME=AUT_REG_EXPRESSION_CONFIG_TESTS.RESPONSE_DATA_EMPTY.toString();
				rtmScn.AUT_SCENARIO_FULL_NAME=AUT_REG_EXPRESSION_CONFIG_TESTS.RESPONSE_DATA_EMPTY.toString();
				rtmScn.autPrintData();
				return (TScenarioConfig) rtmScn;
			}

		}
		catch(java.lang.Exception e) {

			System.out.println("AUT ERROR: VALIDATE EXECUTION SCENARIO");
			System.out.println(e.getMessage());
			e.printStackTrace();
			rtmScn.autPrintData();
			return null;
		}
	}

	/**
	 * 
	 * Retorna o item correspondente a express�o regular na pilha de execu��o
	 * 
	 * @param regExpForScenarioExecution - Expresss�o Regular
	 * 
	 * @return String - Nome do m�todo que corresponde a express�o regular de configura��o
	 */
	public static String autGetRuntimeScenarioExecution(String regExpForScenarioExecution) {
		java.util.regex.Pattern regExp  = java.util.regex.Pattern.compile(regExpForScenarioExecution); //Inicializa o objeto de compila��o da express�o regular
		java.util.regex.Matcher verif =null; //Define a vari�vel de an�lise da express�o
		String valueOutput = null;
		System.out.println("AUT INFO: GET RUNTIME METHOD EXECUTION : INIT");
		for(StackTraceElement stack : Thread.currentThread().getStackTrace()) {
			verif = regExp.matcher(stack.getMethodName());
			if(verif.find()) {
				valueOutput = verif.group();
			}
		}
		System.out.println("AUT INFO: GET RUNTIME METHOD EXECUTION : END");
		return valueOutput;
	}

	/**
	 * 
	 * Captura a tela atual da m�quina virtual
	 * 
	 */
	public byte[] autSyncronizeScreen() {
		try {
			Date dt = new Date();
			String fileName = "AUT_".concat(dt.toInstant().toString().replaceAll("\\W", "")).concat(".png");		
			System.out.println("FWK OBJECT : INFO : SYNCRONIZE SCREEN : INIT");
			String dirOut = autGetDefaultDirOutputFiles();
			String FileOut = "%s\\%s";
			String fileNameFull = String.format(FileOut,dirOut,fileName);
			String fileNameOutSilk = autGetDesktopAgent().captureBitmap(String.format(FileOut,dirOut,fileName));
			byte[] bytesOut = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(fileNameOutSilk));
			System.out.println(fileNameOutSilk);

			System.out.println("FWK OBJECT : INFO : SYNCRONIZE SCREEN : END");

			return bytesOut;
		}
		catch(java.lang.Exception e) {
			System.out.println("FWK OBJECT : ERROR : SYNCRONIZE SCREEN");
			System.out.println(e.getMessage());
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * 
	 * Retorna uma cole��o de items definidos como - []
	 * 
	 * @param items - Cole��o de itens
	 * @return String - Representado pela
	 * 
	 */
	public String autGetStringValue(java.util.List<String> items) {
		return items.toString().replaceAll("[\\[\\]\\s]", "");
	}	

	/**
	 * 
	 * Carrega uma lista com a rela��o dos testes definidos no arquivo de dados
	 * 
	 * @param classTests - Classe que implementa suite de testes
	 * 
	 */
	public void autInitConfigurationProjectExecution(Class<?> classTests) {
		AUTJUnitManager junitMng = autGetJUnitManager();
		java.util.List<String> pjtIdsExclude = new java.util.ArrayList<String>();
		//java.util.HashMap<String,String> prmConfigFrenteProj = junitMng.autFindItemOnHash("AUT_\\d+_FRT\\d+", ".", junitMng.autGetTestList(classTests));
		java.util.HashMap<String,String> prmConfigFrenteProj = junitMng.autFindItemOnHash(AUT_REG_EXPRESSION_CONFIG_TESTS.SCENARIO_TEST.toString(), ".", junitMng.autGetTestList(classTests));

		for(String key : prmConfigFrenteProj.keySet()) {
			java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(AUT_REG_EXPRESSION_CONFIG_TESTS.PROJECT_NAME.toString());
			java.util.regex.Matcher analise = padrao.matcher(key);
			if(analise.find()) {
				java.util.regex.Pattern pdrPjtId = java.util.regex.Pattern.compile("\\d+");
				java.util.regex.Matcher anlPjtId = pdrPjtId.matcher(analise.group());
				if(anlPjtId.find()) {
					AUT_TEST_CONFIGURATION.AUT_ID_PROJETO = Integer.parseInt(anlPjtId.group());					
				}
				else {
					new Throwable("AUT ERROR: PROJECT ID NOT INCLUDED ON SUITE NAME FROM TEST");
				}

				padrao = padrao.compile(AUT_REG_EXPRESSION_CONFIG_TESTS.PROJECT_NAME.toString());
				analise = padrao.matcher(key);
				if(analise.find()) {
					AUT_TEST_CONFIGURATION.AUT_FRENTE_PROJETO=analise.group();

					System.out.println(String.format("FRENTE DE PROJETO: %s", AUT_TEST_CONFIGURATION.AUT_FRENTE_PROJETO));		
					System.out.println(String.format("ID DO PROJETO: %s", AUT_TEST_CONFIGURATION.AUT_ID_PROJETO));
				}

				autGetProjectManagerOverview().autInsertProjectExecutionOverview(new Object[] {
						AUT_TEST_CONFIGURATION.AUT_ID_PROJETO,			
						AUT_TEST_CONFIGURATION.AUT_TOTAL_MODULOS,
						AUT_TEST_CONFIGURATION.AUT_TOTAL_SCENARIOS,
						AUT_TEST_CONFIGURATION.AUT_TOTAL_TEST_CASE,
						AUT_TEST_CONFIGURATION.AUT_TOTAL_MODULOS_STATUS_PASSED,
						AUT_TEST_CONFIGURATION.AUT_TOTAL_SCENARIOS_STATUS_PASSED,
						AUT_TEST_CONFIGURATION.AUT_TOTAL_TEST_CASE_STATUS_PASSED,
						AUT_TEST_CONFIGURATION.AUT_TOTAL_MODULOS_STATUS_FAILED,
						AUT_TEST_CONFIGURATION.AUT_TOTAL_SCENARIOS_STATUS_FAILED,
						AUT_TEST_CONFIGURATION.AUT_TOTAL_TEST_CASE_STATUS_FAILED,
						AUT_TEST_CONFIGURATION.AUT_TOTAL_MODULOS_EXEC_IN_PROGRESS,
						AUT_TEST_CONFIGURATION.AUT_TOTAL_SCENARIOS_EXEC_IN_PROGRESS,
						AUT_TEST_CONFIGURATION.AUT_TOTAL_TEST_CASE_EXEC_IN_PROGRESS

				});

				String hostItem = AUTNTWUtils.autGetInterfaceInfo().get("HOST:1").toString();
				AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_PJT_ID=AUT_TEST_CONFIGURATION.AUT_ID_PROJETO;
				AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_DSK_ID="0";
				AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_VM_ID="0";
				AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_DSK_IP=hostItem.split("/")[1];
				AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_VM_IP=hostItem.split("/")[1];
				AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_VM_NAME=hostItem.split("/")[0];
				AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_DSK_NAME=hostItem.split("/")[0];
				AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_STD_ITEM_CONFIGURATION=AUT_TIPO_ITEM_CONFIGURACAO.SCENARIO.toString();

				AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_STD_ITEM_CONFIGURATION = key;
				if(!pjtIdsExclude.contains(AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_PJT_ID.toString())) {
					pjtIdsExclude.add(AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_PJT_ID.toString());
					autGetProjectDetailManagement().autDeleteProjetExecutionDetails(new Object[] {AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_PJT_ID.toString()});
				}
				autGetProjectDetailManagement().autInsertScenarioExecutionDetail(new Object[]{
						AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_PJT_ID,
						AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_DSK_ID, 
						AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_VM_ID,
						AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_DSK_NAME,
						AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_VM_NAME, 
						AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_DSK_IP,
						AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_VM_IP,
						AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_STD_ITEM_CONFIGURATION,
						AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_STD_STATUS
				});
			}
		}


	}

	public void autInitConfigurationProjectExecution(Class<?> classTests,String statusPadrao) {
		AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_STD_STATUS=statusPadrao;
		autInitConfigurationProjectExecution(classTests);
	}

	public <TTestStatus extends java.lang.Enum> void autInitConfigurationProjectExecution(Class<?> classTests,TTestStatus statusPadrao) {
		AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_STD_STATUS=statusPadrao.toString();
		autInitConfigurationProjectExecution(classTests);
	}

	public <TProjectExecDetail extends AUTDBProjectsExecutionDetail> TProjectExecDetail autGetProjectDetailManagement() {		
		if(AUT_PROJECT_EXECUTION_DETAIL_OBJ==null) {
			AUT_PROJECT_EXECUTION_DETAIL_OBJ = new AUTDBProjectsExecutionDetail();
			return (TProjectExecDetail) new AUTDBProjectsExecutionDetail();
		}
		else {
			return (TProjectExecDetail)AUT_PROJECT_EXECUTION_DETAIL_OBJ;
		}
	}

	public boolean autInsertScreenByScenario(String projectId,String scenario) {
		try {
			System.out.println("AUT INFO  : INSERT SCREEN BY PROJECT : INIT");
			autGetProjectDetailManagement().autInsertResourceImageExecution(projectId, scenario, autSyncronizeScreen());
			System.out.println("AUT INFO  : INSERT SCREEN BY PROJECT : END");
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: INSERT SCREEN BY PROJECT");
			System.out.println(e.getMessage());
			e.printStackTrace();

			return false;
		}
	}

	/**
	 * Inclus�o uma nova imagem de sincroniza��o do status de execu��o durante o processo de configura��o
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contr�rio
	 */
	public boolean autInsertScreenByScenario() {		
		String projectId = autGetCurrentScenarioRuntime().autGetIdNumber();
		String scenarioName = autGetCurrentScenarioRuntime().AUT_SCENARIO_FULL_NAME;

		autInsertScreenByScenario(projectId, scenarioName);

		return true;
	}

	/**
	 * 
	 * Construtor padr�o da classe
	 * 
	 */
	public AUTFWKTestObjectBase() {
		AUT_LIST_PROJECTS_LOADER = new java.util.ArrayList<String>();
	}
}
