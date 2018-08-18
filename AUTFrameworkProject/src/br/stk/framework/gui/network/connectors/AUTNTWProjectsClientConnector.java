/**
 * 
 */
package br.stk.framework.gui.network.connectors;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * API Conexão para remota com a interface de serviços para projetos de automação
 * 
 * @author Softtek-QA
 *
 */
public class AUTNTWProjectsClientConnector extends AUTNTWConnection {
	private java.util.concurrent.ConcurrentHashMap<AUTObject, AUTThreadProcessCollection<AUTObject, AUTThreadProcess>> clientConnections;
	static Integer portLocal;
	/**
	 * 
	 * Gerencia conexões ativas
	 * 
	 * @author Softtek-QA
	 *
	 */
	public class AUTClientConnections extends AUTNTWUtils{
		private AUTThreadProcessCollection hashConnections;
		java.net.Socket sckClient = null;
		
		/**
		 * 
		 * Retorna uma lista com as conexões ativas
		 * 
		 * @return boolean - True em caso de sucesso false caso contrário
		 *
		 */
		public AUTThreadProcessCollection<AUTObject, AUTThreadProcess> autGetClientConnections(){
			return hashConnections;
		}
		/**
		 * 
		 * Configurações de inicialização do framework
		 * 
		 */
		public void configInit() {
			hashConnections = new AUTThreadProcessCollection<AUTObject,AUTThreadProcess>();
		}
		
		
		/**
		 * 
		 * Configurações de inicialização para objeto de multiplas conexões
		 * 
		 * @param host - Host destino da conexão
		 * @param portInit - Porta inicial de conexão
		 * @param portEnd - Porta final de conexão
		 * 
		 */
		public void configInitConnections(String host,int portInit,int portEnd) {
			portLocal = portInit;			
			while(portLocal < portEnd) {
				System.out.println("****** INDEX : ".concat(portLocal.toString()));
				
				AUTThreadProcess thPrc = new AUTThreadProcess() {					
					@Override
					public boolean autExecProcess() {
						System.out.println("AUT INFO: PROCESS CLIENT CONNECTIONS : ".concat(AUT_OBJECT.AUT_ID.toString()));
						AUTStreamMonitor autStreamMon = new AUTStreamMonitor() {

							@Override
							public <TAUTObjectServer> void autStartStreamMonitor(TAUTObjectServer objectConfig,
									InputStream inputData, OutputStream outputData) {
								
								System.out.println("AUT START MONITOR: INIT");									
								autStartInputMonitor(inputData);
								autStartOutputMonitor(outputData);
								
							}
							
						};	
						
						try {
							autStreamMon.autStartStreamMonitor(new Object(), sckClient.getInputStream(), sckClient.getOutputStream());
						} catch (IOException e) {
							System.out.println("AUT ERROR: START MONITOR FOR SOCKET STREAM");
							System.out.println(e.getMessage());
							e.printStackTrace();
						}
						
						return true;
					}
					
					@Override
					public boolean autExecInit() {
						// TODO Auto-generated method stub
						System.out.println("AUT INFO: INIT CLIENT CONNECTIONS : ".concat(AUT_OBJECT.AUT_ID.toString()));
						sckClient = autStartConnection(AUT_TYPE_CONNECTION.TCP_IPV4,host,new Integer(portLocal));
						
						return true;
					}
					
					@Override
					public boolean autExecEnd() {
						// TODO Auto-generated method stub
						System.out.println("AUT INFO: END CLIENT CONNECTIONS : ".concat(AUT_OBJECT.AUT_ID.toString()));
						return true;
					}
				};
			
				thPrc.AUT_OBJECT = new AUTObject(portLocal);
				thPrc.AUT_OBJECT.AUT_ID = portLocal;
				hashConnections.put(thPrc.AUT_OBJECT,thPrc);
				
				portLocal++;
			}
			
			
			hashConnections.autStartProcess();
		}
		
		
		/**
		 * 
		 * Construtor
		 * 
		 * @param portInit - Porta inicial de conexão
		 * @param portEnd - Porta final de conexão
		 * @param host - Host para execução
		 * 
		 */
		public AUTClientConnections(String host,int portInit,int portEnd) {
			super();
			configInit();
			configInitConnections(host, portInit, portEnd);
		}
		
	}
	
	
	
	/**
	 * 
	 * Configurações de inicialização
	 * 
	 */
	public void configInit() {
		clientConnections = new java.util.concurrent.ConcurrentHashMap<AUTObject,AUTThreadProcessCollection<AUTObject, AUTThreadProcess>>();		
	}
	
	public AUTThreadProcessCollection<AUTObject, AUTThreadProcess> autStartClientConnection(String host,Integer portInit,Integer portEnd){
		return new AUTClientConnections(host, portInit, portEnd).autGetClientConnections();
	}
	
	/**
	 * 
	 * Construtor padrão
	 * 
	 */
	public AUTNTWProjectsClientConnector() {
		// TODO Auto-generated constructor stub
		
	}

}
