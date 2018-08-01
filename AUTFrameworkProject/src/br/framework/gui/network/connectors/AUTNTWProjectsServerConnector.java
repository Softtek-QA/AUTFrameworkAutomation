/**
 * 
 */
package br.framework.gui.network.connectors;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import br.framework.db.connectors.AUTConnectorGlobalConfiguration;
import br.framework.db.connectors.AUTConnectorGlobalConfiguration.AUT_GLOBAL_CONFIGURATION;
import br.framework.db.connectors.AUTDBConnection.AUT_TYPE_SGDB;

/**
 * 
 * Classe provedora de serviços em rede para projetos de automação
 * 
 * 
 * @author Softtek-QA
 *
 */
public class AUTNTWProjectsServerConnector extends AUTNTWConnection {

	public java.util.concurrent.ConcurrentHashMap<String, AUTServerListeners> hashConnections;
	Integer indexListener = 0;


	/**
	 * 
	 * Classe responsável por armazenar parametros de configuração do servidor local - Máquina local de execução
	 * 
	 * @author Softtek-QA
	 *
	 */
	public static class AUTObjectServer{
		java.util.HashMap<String,Object> AUT_INTERFACE_CONFIG;
		AUTConnectorGlobalConfiguration AUT_GLOBAL_CONFIGURATION;
		/**
		 * 
		 * Configurações de inicialização do objeto
		 * 
		 */
		public void configInit() {
			try {
				AUT_INTERFACE_CONFIG = autGetInterfaceInfo();
				AUT_GLOBAL_CONFIGURATION = new AUTConnectorGlobalConfiguration();
			}
			catch(java.lang.Exception e) {
				System.out.println("AUT ERROR: INTERFACE CONFIGURATION LOADER");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		/**
		 * 
		 * Construtor padrão
		 * 
		 */
		public AUTObjectServer() {
			super();
			configInit();
		}
	}

		
	public class AUTServerListeners extends AUTThreadProcessCollection<AUTObject, AUTThreadProcess>{					
		Integer portLocal = 0;
		java.net.Socket sckCon = null;
		
		public boolean autStartListener(int portInit,int portEnd) {			
			try {
				portLocal = portInit;
				while(portLocal >= portInit && portLocal <= portEnd) {
					this.put(new AUTObject(portLocal), new AUTThreadProcess() {		
						Integer index = new Integer(portLocal);
						@Override
						public boolean autExecProcess() {							
							sckCon = autStartNewConnection(AUT_TYPE_CONNECTION.TCP_LISTENER_IPV4, index);																		
							return true;
						}
						
						@Override
						public boolean autExecInit() {
							System.out.println(String.format("AUT INFO START LISTENER : PORT : %s", index));
							return true;
						}
						
						@Override
						public boolean autExecEnd() {							
							try {
								new AUTStreamMonitor(sckCon.getInputStream(), sckCon.getOutputStream());
							} catch (IOException e) {
								System.out.println("AUT ERROR: START MONITOR BY SERVER LISTENER");
								System.out.println(e.getMessage());
								e.printStackTrace();							
							}
							
							System.out.println(String.format("AUT INFO FINISHED LISTENER CONFIGURATION FOR PORT : %s", index));																					
							return true;
						}
					});
					System.out.println(String.format("AUT INFO: SERVICE LISTENER FOR PORT : %s : OK", portLocal));
					portLocal++;
				}

				autStartProcess();

				return true;
			}
			catch(java.lang.Exception e) {
				System.out.println(String.format("AUT ERROR: DEFINE SOCKETS FOR LISTENER : PORT INIT: %s PORT END: %s",portInit,portEnd));
				System.out.println(e.getMessage());

				return false;
			}
		}
		
		public <TAUTObject extends AUTObjectServer> boolean autStartListener(TAUTObject objectConfig,int portInit,int portEnd) {			
			try {
				portLocal = portInit;
				while(portLocal >= portInit && portLocal <= portEnd) {
					AUTThreadProcess thProc = new AUTThreadProcess() {		
						Integer index = new Integer(portLocal);
						
						@Override
						public boolean autExecProcess() {							
							sckCon = autStartNewConnection(AUT_TYPE_CONNECTION.TCP_LISTENER_IPV4, index);																		
							return true;
						}
						
						@Override
						public boolean autExecInit() {
							System.out.println(String.format("AUT INFO START LISTENER : PORT : %s", index));
							return true;
						}
						
						@Override
						public boolean autExecEnd() {							
							try {
								
								AUTStreamMonitor autMon = new AUTStreamMonitor() {
									@Override
									public <TAUTObjectServer> void autStartStreamMonitor(TAUTObjectServer objectConfig,
											InputStream inputData, OutputStream outputData) {
										// TODO Auto-generated method stub
										System.out.println("@@@@@@@@@ START MONITOR @@@@@@@@@@@@");
										AUTObjectServer objServer = (AUTObjectServer)objectConfig;
										String hostDB = objServer.AUT_GLOBAL_CONFIGURATION.autGetDefaultGlobalConfiguration().get(AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_IP_VALUE.name()).toString();
										Integer portDB = Integer.parseInt(objServer.AUT_GLOBAL_CONFIGURATION.autGetDefaultGlobalConfiguration().get(AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_PORT_VALUE.name()).toString());
										String dbName = objServer.AUT_GLOBAL_CONFIGURATION.autGetDefaultGlobalConfiguration().get(AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_DATA_BASE_VALUE.name()).toString();
										String pwdDB = AUT_GLOBAL_CONFIGURATION.DEFAULT_PWD_DB.toString();
										String usrDB = AUT_GLOBAL_CONFIGURATION.DEFAULT_USR_DB.toString();
										System.out.println(objServer.AUT_GLOBAL_CONFIGURATION.autGetDefaultGlobalConfiguration());
										System.out.println(objServer.AUT_GLOBAL_CONFIGURATION.autGetDefaultGlobalConfiguration().get(AUT_GLOBAL_CONFIGURATION.DEFAULT_SERVER_IP_VALUE.name()));
										objServer.AUT_GLOBAL_CONFIGURATION.AUT_CONNECTOR_SESSION.autGetInitConfigFromFWK();
										objServer.AUT_GLOBAL_CONFIGURATION.AUT_CONNECTOR_SESSION.autInsertProject("PJT001", "DESCRIPTION 001");
									}
								};
								
								autMon.autStartStreamMonitor(objectConfig, sckCon.getInputStream(), sckCon.getOutputStream());
								
								
							} catch (IOException e) {
								System.out.println("AUT ERROR: START MONITOR BY SERVER LISTENER");
								System.out.println(e.getMessage());
								e.printStackTrace();							
							}
							
							System.out.println(String.format("AUT INFO FINISHED LISTENER CONFIGURATION FOR PORT : %s", index));																					
							return true;
						}
					};
					
					thProc.AUT_SERVER_OBJECT = objectConfig;
					
					this.put(new AUTObject(portLocal), thProc);
					
					
					System.out.println(String.format("AUT INFO: SERVICE LISTENER FOR PORT : %s : OK", portLocal));
					portLocal++;
				}

				autStartProcess();

				return true;
			}
			catch(java.lang.Exception e) {
				System.out.println(String.format("AUT ERROR: DEFINE SOCKETS FOR LISTENER : PORT INIT: %s PORT END: %s",portInit,portEnd));
				System.out.println(e.getMessage());

				return false;
			}
		}
		
		
		/**
		 * 
		 * Construtor para objeto de escuta para as portas de comunicação entre os valores especificados
		 * 
		 * @param portInit - Porta inicial 
		 * @param portEnd - Porta final
		 * 
		 */
		public AUTServerListeners(int portInit,int portEnd) {
			autStartListener(portInit, portEnd);
		}
		
		public <TAUTObject extends AUTObjectServer> AUTServerListeners(TAUTObject objectConfig, int portInit,int portEnd) {
			autStartListener(objectConfig,portInit, portEnd);
		}
	}


	/**
	 * 
	 * Adiciona um novo range de objetos de escuta tcp ip
	 * 
	 * @param servListener - Objeto serviço de escuta
	 * 
	 * @return boolean - True caso o processo seja finalizado sucesso false caso contrário
	 * 
	 *
	 */
	public boolean autAddServiceListener(AUTServerListeners servListener) {
		try {
			System.out.println("AUT INFO: INSERT SERVICE LISTENER");
			hashConnections.put(indexListener.toString(), servListener);
			System.out.println("AUT INFO: INSERT SERVICE LISTENER : FINISHED");

			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: INSERT SERVICE LISTENER");
			System.out.println(e.getMessage());
			e.printStackTrace();

			return false;
		}
	}
	/**
	 * 
	 * Inicializa o serviço de escuta tcp nas portas específicas entre os valores informados
	 * 
	 * @param portInit - Porta inicial
	 * @param portEnd - Porta final
	 * 
	 * @return java.util.List(java.net.Socket) - Sockets de comunicação atribuídos a respectivas conexões
	 * 
	 * 
	 */
	public AUTServerListeners autStartServiceToConnections(int portInit,int portEnd){
		try {								
			return new AUTServerListeners(portInit, portEnd);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: INIT SERVER LISTENER");
			System.out.println(e.getMessage());
			e.printStackTrace();

			return null;
		}
	}

	
	public <TAUTObject extends AUTObjectServer> AUTServerListeners autStartServiceToConnections(TAUTObject objectConfig,int portInit,int portEnd){
		try {								
			return new AUTServerListeners(objectConfig,portInit, portEnd);
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: INIT SERVER LISTENER");
			System.out.println(e.getMessage());
			e.printStackTrace();

			return null;
		}
	}
	
	private void autConfigInit() {
		hashConnections = new ConcurrentHashMap<String,AUTServerListeners>();
	}

	/**
	 * 
	 * Construtor padrão da classe de serviço
	 * 
	 */
	public AUTNTWProjectsServerConnector() {
		super();
		autConfigInit();
	}

}
