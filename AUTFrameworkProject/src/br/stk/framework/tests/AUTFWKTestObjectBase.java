/**
 * 
 */
package br.stk.framework.tests;

import java.io.IOException;
import java.util.Date;

import br.stk.framework.db.connectors.AUTConnectorGlobalConfiguration.AUT_GLOBAL_CONFIGURATION;
import br.stk.framework.gui.network.connectors.AUTNTWUtils;
import br.stkframework.db.management.AUTDBProjectExecutionsOverview;
import br.stkframework.db.management.AUTDBProjectsExecutionDetail;
import junit.framework.JUnit4TestAdapter;

/**
 * 
 * Componente base para implementação de testes de software
 * 
 * 
 * @author Softtek - QA
 *
 */
public class AUTFWKTestObjectBase {
	private String autEnvironmentDirDefault = "temp";
	private String autDefaultDirOutput = "C:\\Users\\ADMINI~1\\AppData\\Local\\Temp\\AUT_OUTPUT_FILES";
	public com.borland.silktest.jtf.Desktop AUT_AGENT_SILK4J = null;
	public java.util.List<String> AUT_LIST_PROJECTS_LOADER; //Lista de projetos corrente de projetos associados a execução atual
	/**
	 * Enumera as opções de configuração para expressões regulares de configuração, usadas na carga de parametros em empo 
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
		 * Retorna o dígito numérico relacionado ao ID do projeto
		 * 
		 * @return String - digito número do projeto
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
		 * Imprime os dados carregados em tempo de execução em tempo de execução
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
	 * Lista de status possíveis para configuração dos testes
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
				return "EM EXECUÇÃO";
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
	 * Item de configuração do projeto - Entidade de testes
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
				return "MÓDULO NEGÓCIO";
			}
			case PROJECT:{
				return "FRENTE DE NEGÓCIO";
			}
			case SCENARIO:{
				return "CENÁRIO DE NEGÓCIO";
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
	 * Entidade para configuração da estrutura de dados do projeto
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
		 * Carrega os registros do Hash de dados onde a chave e o valor correspondem as expressões regulares
		 * 
		 * @param regExpKey - Expressão regular para chave do hash
		 * @param regExpValue - Expressao regular para o valor relacionado a chave corrente
		 * @param parametros - Hash de dados para pesquisa
		 * 
		 * @return Hash - registros que correspondem as expressões regulares
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
		 * Método base de configuração
		 * 
		 */
		public void autConfig() {

		}

		/**
		 * 
		 * Construtor padrão da classe
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
	 * Define o diretório para despejo dos arquivo de evidência
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
	 * Retorna o objeto de gerenciamento para execuções dos projetos - Execução resumidas por frente de negócio
	 * 
	 * @return TProjectExecOverview - Tipo que extende AUTDBProjectExecutionsOverview
	 * 
	 */
	public <TProjectExecOverview extends AUTDBProjectExecutionsOverview> TProjectExecOverview autGetProjectManagerOverview() {
		return (TProjectExecOverview) new AUTDBProjectExecutionsOverview();
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
	 * Recupera o objeto desktop ativo pré configurado - SILK4J
	 * 
	 */
	public com.borland.silktest.jtf.Desktop autGetDesktopAgent() {
		return AUT_AGENT_SILK4J;
	}

	/**
	 *
	 *Altera o agente de execução ativo - SILK4J
	 * 
	 */
	public void autSetDesktopAgent(com.borland.silktest.jtf.Desktop agentDesktop) {
		AUT_AGENT_SILK4J = agentDesktop;
	}

	/**
	 * 
	 * Valida se existe um cenário na pilha de execução
	 * 
	 */
	public static <TScenarioConfig extends AUTRuntimeExecutionScenario> TScenarioConfig autGetCurrentScenarioRuntime() {
		AUTRuntimeExecutionScenario rtmScn = new AUTRuntimeExecutionScenario();
		try {	
			String value = autGetRuntimeScenarioExecution(AUT_REG_EXPRESSION_CONFIG_TESTS.SCENARIO_TEST.toString()); //Verifica a existência de um cenário na pilha de execução dos testes			
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
	 * Retorna o item correspondente a expressão regular na pilha de execução
	 * 
	 * @param regExpForScenarioExecution - Expresssão Regular
	 * 
	 * @return String - Nome do método que corresponde a expressão regular de configuração
	 */
	public static String autGetRuntimeScenarioExecution(String regExpForScenarioExecution) {
		java.util.regex.Pattern regExp  = java.util.regex.Pattern.compile(regExpForScenarioExecution); //Inicializa o objeto de compilação da expressão regular
		java.util.regex.Matcher verif =null; //Define a variável de análise da expressão
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
	 * Captura a tela atual da máquina virtual
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

			System.out.println("FWK OBJECT : ERROR : SYNCRONIZE SCREEN : END");

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
	 * Retorna uma coleção de items definidos como - []
	 * 
	 * @param items - Coleção de itens
	 * @return String - Representado pela
	 * 
	 */
	public String autGetStringValue(java.util.List<String> items) {
		return items.toString().replaceAll("[\\[\\]\\s]", "");
	}	

	/**
	 * 
	 * Carrega uma lista com a relação dos testes definidos no arquivo de dados
	 * 
	 * @param classTests - Classe que implementa suite de testes
	 * 
	 */
	public void autInitConfigurationProjectExecution(Class<?> classTests) {
		AUTJUnitManager junitMng = autGetJUnitManager();

		java.util.HashMap<String,String> prmConfigFrenteProj = junitMng.autFindItemOnHash("AUT_\\d+_FRT\\d+", ".", junitMng.autGetTestList(classTests));
		for(String key : prmConfigFrenteProj.keySet()) {
			java.util.regex.Pattern padrao = java.util.regex.Pattern.compile("\\d+");
			java.util.regex.Matcher analise = padrao.matcher(key);
			if(analise.find()) {
				AUT_TEST_CONFIGURATION.AUT_ID_PROJETO = Integer.parseInt(analise.group());
				AUT_LIST_PROJECTS_LOADER.add(AUT_TEST_CONFIGURATION.AUT_ID_PROJETO.toString());

				padrao = padrao.compile("FRT\\d+");
				analise = padrao.matcher(key);
				if(analise.find()) {
					AUT_TEST_CONFIGURATION.AUT_FRENTE_PROJETO=analise.group();

					System.out.println(String.format("FRENTE DE PROJETO: %s", AUT_TEST_CONFIGURATION.AUT_FRENTE_PROJETO));		
					System.out.println(String.format("ID DO PROJETO: %s", AUT_TEST_CONFIGURATION.AUT_ID_PROJETO));
					autGetProjectDetailManagement().autDeleteProjetExecutionDetails(new Object[] {AUT_TEST_CONFIGURATION.AUT_ID_PROJETO});
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
					//AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_STD_STATUS=A;
					for(String item : junitMng.autGetTestList(classTests).keySet()) {
						AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_STD_ITEM_CONFIGURATION = item;
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
		}


	}

	public void autInitConfigurationProjectExecution(Class<?> classTests,String statusPadrao) {
		AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_STD_STATUS=statusPadrao;
		autInitConfigurationProjectExecution(classTests);
	}

	public <TProjectExecDetail extends AUTDBProjectsExecutionDetail> TProjectExecDetail autGetProjectDetailManagement() {		
		return (TProjectExecDetail) new AUTDBProjectsExecutionDetail();
	}

	public boolean autInsertScreenByScenario(String projectId,String scenario) {
		try {
			System.out.println("AUT INFO  : INSERT SCREEN BY PROJECT : INIT");
			AUTDBProjectsExecutionDetail pjt = autGetProjectDetailManagement();
			pjt.autStartDefaultConnection();
			pjt.autInsertResourceImageExecution(projectId, scenario, autSyncronizeScreen());

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
	 * 
	 * Construtor padrão da classe
	 * 
	 */
	public AUTFWKTestObjectBase() {
		AUT_LIST_PROJECTS_LOADER = new java.util.ArrayList<String>();
	}
}
