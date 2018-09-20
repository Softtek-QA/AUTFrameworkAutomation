/**
 * 
 */
package br.stk.framework.tests;

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
			public static String AUT_STD_STATUS;
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
	 * Retorna o objeto de gerenciamento para execuções dos projetos - Execução resumidas por frente de negócio
	 * 
	 * @return TProjectExecOverview - Tipo que extende AUTDBProjectExecutionsOverview
	 * 
	 */
	public <TProjectExecOverview extends AUTDBProjectExecutionsOverview> TProjectExecOverview autGetProjectManagerOverview() {
		return (TProjectExecOverview) new AUTDBProjectExecutionsOverview();
	}

	public void autInitConfigurationProjectExecution(Class<?> classTests) {
		AUTJUnitManager junitMng = autGetJUnitManager();

		java.util.HashMap<String,String> prmConfigFrenteProj = junitMng.autFindItemOnHash("AUT_\\d+_FRT\\d+", ".", junitMng.autGetTestList(classTests));
		for(String key : prmConfigFrenteProj.keySet()) {
			java.util.regex.Pattern padrao = java.util.regex.Pattern.compile("\\d+");
			java.util.regex.Matcher analise = padrao.matcher(key);
			if(analise.find()) {
				AUT_TEST_CONFIGURATION.AUT_ID_PROJETO = Integer.parseInt(analise.group());
				padrao = padrao.compile("FRT\\d+");
				analise = padrao.matcher(key);
				if(analise.find()) {
					AUT_TEST_CONFIGURATION.AUT_FRENTE_PROJETO=analise.group();

					System.out.println(String.format("FRENTE DE PROJETO: %s", AUT_TEST_CONFIGURATION.AUT_FRENTE_PROJETO));		
					System.out.println(String.format("ID DO PROJETO: %s", AUT_TEST_CONFIGURATION.AUT_ID_PROJETO));

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
					AUT_TEST_CONFIGURATION.AUT_TEST_CONFIG.AUT_STD_STATUS=AUT_TIPO_STATUS_OBJETO.FALHOU.toString();
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

	public <TProjectExecDetail extends AUTDBProjectsExecutionDetail> TProjectExecDetail autGetProjectDetailManagement() {
		return (TProjectExecDetail) new AUTDBProjectsExecutionDetail();
	}
}
