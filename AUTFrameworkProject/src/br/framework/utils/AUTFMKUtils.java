/**
 * 
 */
package br.framework.utils;

import java.io.IOException;
import java.io.InputStreamReader;

import br.framework.db.connectors.AUTDBUtils.AUT_TYPE_COMPARE_PROPERTIES;
import br.framework.gui.logs.AUTLogsManage;
import br.framework.gui.network.connectors.AUTNTWUtils;
import br.framework.gui.network.connectors.AUTNTWProjectsServerConnector.AUTObjectServer;

/**
 * 
 * Utilidades
 * 
 * @author Softtek-QA
 *
 */
public class AUTFMKUtils {
	/**
	 * 
	 * Validação de conteudo
	 * 
	 * @param valueSearch - Valor procurado (value)
	 * @param values - Valores possíveis
	 * 
	 * @return boolean - True em caso de sucesso false caso contrário
	 */
	public static boolean autContentIsEqual(Object[] values,Object valueSearch) {
		try {
			
			java.util.regex.Pattern defExp = java.util.regex.Pattern.compile(String.format("(?i:%s)", valueSearch.toString()));
			for(Object obj: values) {
				java.util.regex.Matcher strSearch = defExp.matcher(obj.toString());
				if(strSearch.find()) {
					return true;
				}
			}
			
			return false;			
		}
		catch(java.lang.Exception e) {
			AUTLogsManage.registrarLog(e, "AUT ERROR: VALIDATION CONTENT IS EQUAL");
			return false;
		}
	}

	public static Object autGetContentFromListItems(Object[] values,Object valueSearch) {
		try {
			
			java.util.regex.Pattern defExp = java.util.regex.Pattern.compile(String.format("(?i:%s)", valueSearch.toString()));
			for(Object obj: values) {
				java.util.regex.Matcher strSearch = defExp.matcher(obj.toString());
				if(strSearch.find()) {
					return strSearch.group();
				}
			}
			
			return "AUT_REG_EXP_ERROR";			
		}
		catch(java.lang.Exception e) {
			AUTLogsManage.registrarLog(e, "AUT ERROR: VALIDATION CONTENT IS EQUAL");
			
			return "AUT_REG_EXP_ERROR";
		}
	}

	
	public static class AUTStreamMonitor{	
		/**
		 * 
		 * Define a relação de comandos disponíveis para gerenciamento da stream de conexão
		 * 
		 * 
		 * @author Softtek-QA
		 *
		 */
		public enum AUT_STREAM_PROTOCOL{
			AUT_INIT_SECTION_TO_PRINT_CONSOLE,
			AUT_END_SECTION_TO_PRINT_CONSOLE;

			@Override
			public String toString() {
				switch(this) {
				case AUT_END_SECTION_TO_PRINT_CONSOLE:
				{
					return "AUT_CMD_END";
				}
				case AUT_INIT_SECTION_TO_PRINT_CONSOLE:{
					return "AUT_CMD_INIT";
				}
				default:{
					return "AUT_END";
				}
				}
			}
		}

		int indexLine = 0;
		String delimitInitParam = "$AUT_INIT$";

		public synchronized void autStartInputMonitor(java.io.InputStream input) {

			java.lang.Thread thInput = new Thread(new AUTThreadProcess() {
				byte[] bytesInput = null;
				java.lang.StringBuffer strBuf = null;

				@Override
				public boolean autExecProcess() {					
					try {
						System.out.println("AUT INFO: START INPUT STREAM MONITOR : PROCESS");
						java.io.InputStreamReader inputReader = new InputStreamReader(input);
						java.io.BufferedReader buffReader = new java.io.BufferedReader(inputReader);
						
						while(buffReader.ready()) {
							System.out.println(buffReader.readLine());
						}
						return true;
					}
					catch(java.lang.Exception e) {
						System.out.println("AUT ERROR: READ DATA FROM INPUT STREAM");
						System.out.println(e.getMessage());
						e.printStackTrace();
						return false;
					}
				}

				@Override
				public boolean autExecInit() {					
					System.out.println("AUT INFO: START INPUT STREAM MONITOR : INIT");
					bytesInput = new byte[400];
					strBuf = new java.lang.StringBuffer();
					return true;
				}

				@Override
				public boolean autExecEnd() {
					System.out.println("AUT INFO: START INPUT STREAM MONITOR : END");

					return false;
				}
			});

			thInput.setPriority(java.lang.Thread.MAX_PRIORITY);
			thInput.start();
		}



		public synchronized void autStartOutputMonitor(java.io.OutputStream outputData) {

			java.lang.Thread thOutput = new Thread(new AUTThreadProcess() {

				@Override
				public boolean autExecProcess() {					
					System.out.println("AUT INFO: START OUTPUT STREAM MONITOR : PROCESS");
					return true;
				}

				@Override
				public boolean autExecInit() {					
					System.out.println("AUT INFO: START OUTPUT STREAM MONITOR : INIT");
					return true;
				}

				@Override
				public boolean autExecEnd() {
					System.out.println("AUT INFO: START OUTPUT STREAM MONITOR : END");				
					return false;
				}
			});

			thOutput.setPriority(java.lang.Thread.MAX_PRIORITY);
			thOutput.start();
		}

		public synchronized boolean autWriteLine(java.io.OutputStream otpStream,Object data) {
			try {

				java.lang.Thread thOutput = new java.lang.Thread(new java.lang.Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							otpStream.write(data.toString().getBytes());
							otpStream.flush();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							System.out.println("AUT ERROR: WRITE LINE DATA IN OUTPUT STREAM");
							System.out.println(e.getMessage());

							e.printStackTrace();
						}						
					}
				});

				thOutput.start();
				thOutput.join();

				return true;
			}
			catch(java.lang.Exception e) {

				return false;
			}
		}

		public <TAUTObjectServer> void autStartStreamMonitor(TAUTObjectServer objectConfig,java.io.InputStream inputData,java.io.OutputStream outputData) {
		}

		public AUTStreamMonitor(java.io.InputStream inputData,java.io.OutputStream outputData) {
			autStartInputMonitor(inputData);
			autStartOutputMonitor(outputData);
		}
		
		public AUTStreamMonitor() {
			super();
		}	
	}
	/**
	 * 
	 * Gerenciamento de valores randômicos
	 * 
	 * @author Softtek-QA
	 *
	 */
	public class AUTRandomGeneratorObject extends java.util.Random{
		int index = 0;

	}
	/**
	 * 
	 * Processos paralelos
	 * 
	 * @author Softtek-QA
	 *
	 */
	public static abstract class AUTThreadProcess implements java.lang.Runnable{
		public boolean AUT_WAIT_TERMINATE_EXECUTION = false;
		public AUTObject AUT_OBJECT;
		public AUTObjectServer AUT_SERVER_OBJECT;
		private String nmThread = "AUTTHREAD000";

		/**
		 * 
		 * Rotinas de inicialização
		 * 
		 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
		 * 
		 */
		public abstract boolean autExecInit();

		/**
		 * 
		 * Execução de processos do negócio
		 * 
		 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
		 * 
		 */
		public abstract boolean autExecProcess();

		/**
		 * 
		 * Rotinas de finalização
		 * 
		 * @return boolean - True caso o processo seja finalizado com sucessso false caso contrário
		 */
		public abstract boolean autExecEnd();

		/**
		 * 
		 * Altera o nome da thread atual
		 * 
		 * @param name - Novo nome para thread atual
		 * 
		 * @return boolean - True caso o processo seja finalizado com sucesso
		 * 
		 */
		public boolean autChangeThreadName(String name) {
			try {

				System.out.println("AUT INFO: CHANGE THREAD NAME");

				nmThread = name;

				return true;
			}
			catch(java.lang.Exception e) {
				System.out.println("AUT ERROR: CHANGE THREAD NAME");
				System.out.println(e.getMessage());
				e.printStackTrace();
				return false;
			}
		}

		/**
		 * 
		 * Recupera o nome da thread atual
		 * 
		 * @return String - Nome do fluxo de execução paralelo - Thread
		 * 
		 */
		public String autGetThreadName() {
			return nmThread;
		}		

		/**
		 * 
		 * Rotinas para inicialização do objeto
		 * 
		 */
		@Override
		public void run() {
			System.out.println("AUT INFO: THREAD : START PROCESS FOR INICIALIZATION");
			autExecInit();
			System.out.println("AUT INFO: THREAD : START PROCESS EXECUTION");
			autExecProcess();
			System.out.println("AUT INFO: THREAD : START FINISH PROCESS");
			autExecEnd();
			System.out.println("AUT INFO: THREAD : START FINISHED PROCESS");
		}

		/**
		 * 
		 * Inicializa a execução dos processo definidos manualmente
		 * @exception InterruptedException - Erro de interrupção no fluxo de execução paralelo
		 */
		public void autStartProcess() throws java.lang.InterruptedException {
			java.lang.Thread thStart = new java.lang.Thread(this);
			thStart.setPriority(java.lang.Thread.MAX_PRIORITY);
			if(AUT_WAIT_TERMINATE_EXECUTION) {
				System.out.println("\n\nAUT INFO: THREAD MODE: WAIT");				
				thStart.start();

				try {

					thStart.join();			
					System.out.println(String.format("THREAD STATE: %s",thStart.getState()));

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				System.out.println("\n\nAUT INFO: THREAD MODE: PARALLEL");
				thStart.start();			
				System.out.println(String.format("THREAD STATE: %s",thStart.getState()));
			}
		}

		/**
		 * 
		 * Inicializa a execução dos processo definidos manualmente - Modo sincrono
		 * 
		 * @param execSync - valor é False, o processo será executado em paralelo
		 * 
		 */
		public void autStartProcess(boolean execSync) {
			AUT_WAIT_TERMINATE_EXECUTION = execSync;
			try {
				autStartProcess();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("AUT ERROR: THREAD FINISHED : MONITORED");
				System.out.println(e.getMessage());
			}
		}


		public void autStartProcess(boolean execSync,long secondTimeOut) {
			AUT_WAIT_TERMINATE_EXECUTION = execSync;			
			if(secondTimeOut > 0) {
				autFinishProcess(secondTimeOut, java.lang.Thread.currentThread());	
			}			

			try {
				autStartProcess();
			} catch (InterruptedException e) {
				System.out.println("AUT ERROR: THREAD FINISHED : MONITORED");
				System.out.println(e.getMessage());				
			}

		}


		/**
		 * 
		 * Finaliza o processo no tempo específicado
		 * 
		 * @param secondTimeOut - Tempo para finalizar o processo
		 * @param internalProcess - Thread de processamento paralelo
		 * 
		 */
		public void autFinishProcess(long secondTimeOut,java.lang.Thread internalProcess) {
			java.lang.Thread thMon = new java.lang.Thread(new java.lang.Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try 
					{
						java.lang.Thread.currentThread().sleep(secondTimeOut * 1000);
						System.out.println("AUT INFO: FINISH PROCESS BY INTERNAL COMMAND");
					} 
					catch (InterruptedException e) 
					{
						System.out.println("AUT INFO: FINISH PROCESS BY INTERNAL COMMAND");
						System.out.println(e.getMessage());
					}				
					internalProcess.interrupt();										
				}
			});

			thMon.setPriority(java.lang.Thread.MAX_PRIORITY);
			thMon.start();
		}


		public AUTThreadProcess() {
			super();
		}
	}
	/**
	 * 
	 * Objeto base da estrutura de dados 
	 * 
	 * @author Softtek-QA
	 *
	 */
	public static class AUTObject{
		String nmObject = "AUTOBJ000";
		public boolean AUT_EXEC_MOD_SYNC = false;
		public Integer AUT_SECOND_TIME_OUT_FINISH_EXEC=0;
		public Object AUT_ID;

		/**
		 * 
		 * Retorna o nome do objeto corrente atual
		 * 
		 * @return String - Nome do objeto corrente
		 * 
		 */
		public String autGetObjectName() {
			return autGetDescription();
		}

		public AUTObject(Object nomeObjeto) {
			nmObject = nomeObjeto.toString();
			System.out.println("AUT ALERT : KEY : ".concat(nmObject));
		}

		public String autGetDescription() {
			return String.format("AUTTHREAD:%s",this.hashCode());
		}

		public AUTObject() {
			nmObject = autGetDescription();
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return autGetDescription();
		}
	}

	
	public static class AUTThreadProcessCollection <KeyObject extends AUTObject,ProcessObject extends AUTThreadProcess> extends java.util.concurrent.ConcurrentHashMap<KeyObject,ProcessObject>{		
		/**
		 * 
		 * Adiciona um novo processo 
		 * 
		 * @param autoInit - Define se o processo irá iniciar a execução no momento em que é incluído na lista
		 * @param execSync - valor é FALSE, processo será executado em modo paralelo - concorrente
		 * @param key - Chave de pesquisa associada ao objeto
		 * @param processo - Definição do processo
		 * @return boolean - True caso o processo seja adicionado com sucesso a lista false caso contrário
		 * 
		 * 
		 */
		public boolean autAddProcessList(boolean autoInit,boolean execSync,KeyObject key,ProcessObject processo) {
			try {
				System.out.println("AUT INFO: ADD NEW PROCESS TO LIST : ".concat(key.toString()));
				key.AUT_EXEC_MOD_SYNC = execSync;
				this.put(key, processo);
				this.get(key).AUT_OBJECT=key;

				if(autoInit) {
					if(key.AUT_SECOND_TIME_OUT_FINISH_EXEC == 0) {
						this.get(key).autStartProcess(execSync);
					}
					else {
						this.get(key).autStartProcess(execSync,key.AUT_SECOND_TIME_OUT_FINISH_EXEC);
					}
				}
				else {
					this.get(key).AUT_WAIT_TERMINATE_EXECUTION = execSync;
				}				
				return true;
			}
			catch(java.lang.Exception e) {	

				System.out.println("AUT ERROR: ADD NEW PROCESSO TO LIST");
				System.out.println(e.getMessage());
				e.printStackTrace();

				return false;
			}
		}


		/**
		 * 
		 * Inicia a execução de todas as tarefas agendadas
		 * 
		 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
		 * 
		 */
		public boolean autStartProcess() {
			try {				
				System.out.println("AUT INFO: START EXECUTION PROCESS LIST");				
				for(AUTObject keyItem: keySet()) {
					get(keyItem).AUT_WAIT_TERMINATE_EXECUTION=keyItem.AUT_EXEC_MOD_SYNC;
					get(keyItem).autStartProcess(keyItem.AUT_EXEC_MOD_SYNC,keyItem.AUT_SECOND_TIME_OUT_FINISH_EXEC);	
				}				
				return true;
			}
			catch(java.lang.Exception e) {
				System.out.println("AUT ERROR: START EXECUTION PROCESS LIST");
				System.out.println(e.getMessage());
				e.printStackTrace();

				return false;
			}
		}

		public boolean autStartProcess(Integer delayOperation) {
			try {				
				System.out.println("AUT INFO: START EXECUTION PROCESS LIST");				
				for(AUTObject keyItem: keySet()) {
					get(keyItem).AUT_WAIT_TERMINATE_EXECUTION=keyItem.AUT_EXEC_MOD_SYNC;
					get(keyItem).autStartProcess(keyItem.AUT_EXEC_MOD_SYNC,keyItem.AUT_SECOND_TIME_OUT_FINISH_EXEC);
					java.lang.Thread.currentThread().sleep(delayOperation * 1000);
				}				
				return true;
			}
			catch(java.lang.Exception e) {
				System.out.println("AUT ERROR: START EXECUTION PROCESS LIST");
				System.out.println(e.getMessage());
				e.printStackTrace();

				return false;
			}
		}

		public AUTThreadProcessCollection(){

		}
	}

	public boolean autExecProcessScheduled() {
		try {
			System.out.println("AUT INFO: START EXECUTION OF PROCESS SCHEDULED - LIST");


			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: START EXECUTION OF PROCESS SCHEDULED - LIST");
			System.out.println(e.getMessage());
			e.printStackTrace();

			return false;
		}
	}

	/**
	 * 
	 * Função para pesquisa em um dicionário de dados
	 * 
	 * @param keyRegExp - Expressão regular de pesquisa
	 * @param hashItens - Dicionário de dados
	 * @return java.util.HashMap(Object,Object) - Dicionário com os itens correspondentes ao critério de pesquisa - keyRegExp
	 *
	 */
	public java.util.HashMap<String,Object> autFindItemFromHash(Object keyRegExp,java.util.HashMap<String,Object> hashItens) {
		try {
			java.util.regex.Pattern pad = java.util.regex.Pattern.compile(keyRegExp.toString());
			java.util.regex.Matcher als = null;
			java.util.HashMap<String,Object> itensOut = new java.util.HashMap<String,Object>();

			for(String itemKey : hashItens.keySet()) {
				als = pad.matcher(itemKey.toString());
				if(als.find()) {
					itensOut.put(itemKey, hashItens.get(itemKey));
				}
			}

			System.out.println("AUT INFO: SEARCH ITENS FINISHED: FIND ITENS");

			System.out.println(itensOut);

			return itensOut;
		}
		catch(java.lang.Exception e) {

			System.out.println("AUT ERROR: SEARCH ITEM FROM HASH MAP ITEM");
			System.out.println(e.getMessage());
			e.printStackTrace();

			return null;
		}
	}
	
	/**
	 * 
	 * Retorna o valor de uma propriedade configurada via enumeradores de dados
	 * 
	 * @param typeCompareEnumeration - Tipo de comparação de atributos 
	 * @param enumKey - Propriedade chave - Referência da pesquisa
	 * @param enumValue - Valor procurado
	 * 
	 * @return java.util.HashMap - 
	 */
	public java.util.HashMap<String, Object> autGetValueByEnumProperty(AUT_TYPE_COMPARE_PROPERTIES typeCompareEnumeration,Class<? extends java.lang.Enum> enumKey,Class<? extends java.lang.Enum> enumValue) {
		try {					
			java.util.HashMap<String,Object> hashMapColumns = new java.util.HashMap<String,Object>();			
			for(java.lang.Enum enumItemKey : enumKey.getEnumConstants()) {				
				for(java.lang.Enum enumItemValue : enumValue.getEnumConstants()) {
					if(typeCompareEnumeration.equals(AUT_TYPE_COMPARE_PROPERTIES.ENUMERATION_NAME)) {
						if(enumItemKey.name().equals(enumItemValue.name())) {
							switch(typeCompareEnumeration) {
							case ENUMERATION_CUSTOM:{
								hashMapColumns.put(enumItemKey.toString(), enumItemValue.toString());
								break;
							}
							case ENUMERATION_NAME:{
								hashMapColumns.put(enumItemKey.toString(), enumItemValue.toString());
								break;
							}
							case ENUMERATION_VALUE:{
								hashMapColumns.put(enumItemKey.toString(), enumItemValue.toString());
								break;
							}
							default:{
								hashMapColumns.put(enumItemKey.toString(), enumItemValue.toString());
								break;
							}
							}
						}						
					}
					else if(typeCompareEnumeration.equals(AUT_TYPE_COMPARE_PROPERTIES.ENUMERATION_VALUE)) {
						if(enumItemKey.toString().equals(enumItemValue.toString())) {
							switch(typeCompareEnumeration) {
							case ENUMERATION_CUSTOM:{
								hashMapColumns.put(enumItemKey.toString(), enumItemValue.toString());
								break;
							}
							case ENUMERATION_NAME:{
								hashMapColumns.put(enumItemKey.toString(), enumItemValue.toString());
								break;
							}
							case ENUMERATION_VALUE:{
								hashMapColumns.put(enumItemKey.toString(), enumItemValue.toString());
								break;
							}
							default:{
								hashMapColumns.put(enumItemKey.toString(), enumItemValue.toString());
								break;
							}
							}
						}
					}
				}
			}			
			System.out.println("\n\n\n************ LOADER MAP PROPERTIES FROM ENUMERATIONS ************");
			System.out.println(hashMapColumns);
			
			hashMapColumns.put("ENUM_KEY",enumKey);
			hashMapColumns.put("ENUM_VALUE",enumValue);
			
			return hashMapColumns;
		}
		catch(java.lang.Exception e) {			
			AUTLogsManage.registrarLog(e, "AUT ERROR: GET VALUE ON ENUMERATOR FOR ENUM KEY");			
			return null;
		}
	}

	
	/**
	 * 
	 * Construtor padrão
	 * 
	 */
	public AUTFMKUtils() {
		// TODO Auto-generated constructor stub
		super();
	}

}
