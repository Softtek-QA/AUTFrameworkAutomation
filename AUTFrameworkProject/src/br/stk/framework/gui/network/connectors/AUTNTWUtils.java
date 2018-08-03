/**
 * 
 */
package br.stk.framework.gui.network.connectors;

import java.io.IOException;
import java.io.ObjectOutputStream.PutField;
import java.net.Socket;
import java.util.HashMap;

import br.stk.framework.utils.AUTFMKUtils;

/**
 * 
 * API - Utilidades de rede e propósitos gerais do contexto
 * 
 * 
 * @author Softtek-QA
 *
 */
public class AUTNTWUtils extends AUTFMKUtils{
	
	/**
	 * 
	 * Define as opções possíveis de conexão em rede suportadas pelo framework
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_TYPE_CONNECTION{
		TCP_LISTENER_IPV4,
		TCP_IPV4,
		TCP_IPV6,
		UDP
	}
	
	
	/**
	 * 
	 * Objeto de conexão TCP/IP
	 * 
	 * @author Softtek-QA
	 *
	 */
	public class AUTTCPIPConnection{
		java.io.InputStream inputData = null;   //Input stream connection
		java.io.OutputStream outputData = null; //Output stream connection
		
		boolean readOutData = true;
		
		/**
		 * 
		 * Retorna a stream para entrada de dados ativa
		 * 
		 * @return java.io.InputStream - Stream da conexão TCP/IP ativa
		 * 
		 */
		public synchronized java.io.InputStream autGetInputStreamTCP() {
			return inputData;
		}
		
		/**
		 * 
		 * Altera a stream para entrada de dados ativa
		 * 
		 * @param inputStm - A
		 */
		public synchronized void autSetInputStreamTCP(java.io.InputStream inputStm){			
			inputData = inputStm;
		}
		
		/**
		 * 
		 * Inicia o processo de monitoramento da stream de entrada de dados
		 * 
		 * @param typeConnection - Tipo de conexão
		 * @param host - Host para execução
		 * @param port - Porta de comunicação
		 * 
		 */
		public synchronized void autStartReadInputStream(AUT_TYPE_CONNECTION typeConnection,String host,Integer port) {
			AUTThreadProcess threadInput = new AUTThreadProcess() {
				
				@Override
				public boolean autExecProcess() {
					// TODO Auto-generated method stub
					return true;
				}
				
				@Override
				public boolean autExecInit() {
					// TODO Auto-generated method stub
					
					try {
						autSetInputStreamTCP(autStartConnection(typeConnection, host, port).getInputStream());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return true;
				}
				
				@Override
				public boolean autExecEnd() {
					// TODO Auto-generated method stub
					return true;
				}
			};
		}
		
		public synchronized void autWriteLine() {
			
		}
		/**
		 * 
		 * READ DATA FROM SOCKET
		 * 
		 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
		 * 
		 */
		public boolean autStartReadSocket() {
			try {
				System.out.println("AUT INFO: READ DATA FROM SOCKET");
				AUTThreadProcess threadStream = new AUTThreadProcess() {
					
					@Override
					public boolean autExecProcess() {						
						System.out.println("AUT INFO : START MONITORING INPUT DATA IN INPUTSTREAM : PROCESS");												
						
						return true;
					}
					
					@Override
					public boolean autExecInit() {
						System.out.println("AUT INFO : START MONITORING INPUT DATA IN INPUTSTREAM : INIT");										
						
						return true;
					}
					
					@Override
					public boolean autExecEnd() {						
						System.out.println("AUT INFO: START MONITORING INPUT DATA IN INPUTSTREAM : FINISHED");
						
						return true;
					}
				};
				
				return true;
			}
			catch(java.lang.Exception e) {
				System.out.println("AUT ERROR: READ DATA FROM SOCKET");
				System.out.println(e.getMessage());
				e.printStackTrace();
				
				return false;
			}
		}
	}
	
	/**
	 * Inicia o serviço de escuta na porta específicada
	 * 
	 * @param port - Porta local de escuta
	 * 
	 * @return java.net.ServerSocket - Objeto de escuta
	 * 
	 */
	public java.net.Socket autStartListener(Integer port){
		try {
			
			java.net.ServerSocket sckServer = new java.net.ServerSocket(port);						
			System.out.println(String.format("AUT INFO: SERVER LISTENER : PORT : %s : OK", port));
			return sckServer.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("AUT ERROR: START SERVER LISTENER");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	public java.net.Socket autStartConnectionTCPIPV4(String host,Integer port) {
		try {
			System.out.println("AUT INFO: START CONNECTION IPV4 : ".concat(port.toString()));
			java.net.Socket sckCon = new java.net.Socket(host, port);	
			System.out.println("AUT INFO: START CONNECTION SUCESS".concat(port.toString()));
						
			//sckCon.close();
			
			return sckCon;
		}
		catch(java.net.ConnectException e1) {			
			System.out.println("AUT ERROR: START NETWORK CONNECTION IPV4 : ".concat(host).concat(" : PORT : ".concat(port.toString())));
			System.out.println(e1.getMessage());
			e1.printStackTrace();
			
			return null;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: START CONNECTION IPV4");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	/**
	 * 
	 * Inicia uma conexão com o host especificado
	 * 
	 * @param typeConnection - Tipo da conexão que será estabelecida
	 * @param host - Nome ou IP do host destino da conexão
	 * @param port - Porta de conexão
	 * 
	 * @return boolean - True em caso de sucesso false caso contrário
	 */
	public java.net.Socket autStartConnection(AUT_TYPE_CONNECTION typeConnection,String host,int port) {
		try {			
			switch(typeConnection) {
			case TCP_IPV4:{				
				return autStartConnectionTCPIPV4(host, port);				
			}
			case TCP_IPV6:{
				return null;
			}
			case UDP:{
				return null;
			}
			case TCP_LISTENER_IPV4:{
				return autStartListener(port);
			}
			default:{
				return null;
			}
			}
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: START CONNECTION");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}

	public java.net.Socket autStartNewConnection(AUT_TYPE_CONNECTION typeConnection,String host,int portInit,int portEnd){
		return autStartConnection(typeConnection, host, portInit);
	}
	
	public java.net.Socket autStartNewConnection(AUT_TYPE_CONNECTION typeConnection,String host,int port){
		return autStartConnection(typeConnection, host, port);
	}

	
	public java.net.Socket autStartNewConnection(AUT_TYPE_CONNECTION typeConnection,int port){
		return autStartConnection(typeConnection,port);
	}
	/**
	 * Estabele um conexão com o host remoto usando ip local de loop back
	 * 
	 * @param typeConnection - Tipo da conexão que será estabelecida
	 * @param port - Numero da porta de comunicação
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso false caso contrário
	 * 
	 */
	public java.net.Socket autStartConnection(AUT_TYPE_CONNECTION typeConnection,int port) {
		try {
			java.util.HashMap<String, Object> itensSearch = autFindItemFromHash("HOSTNAME",autGetInterfaceInfo());		
			String host = "127.0.0.1";	
			switch(typeConnection) {
			case TCP_IPV4:{				
				return autStartConnectionTCPIPV4(host, port);				
			}
			case TCP_IPV6:{
				return null;
			}
			case UDP:{
				return null;
			}
			case TCP_LISTENER_IPV4:{
				System.out.println(String.format("TYPE SELECT TCP/IP: %s", typeConnection.name()));
				return autStartListener(port);
			}
			default:{
				return null;
			}
			}
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: START CONNECTION");
			System.out.println(e.getMessage());
			e.printStackTrace();			
			return null;
		}
	}

	
	/**
	 * 
	 * Recupera um conjunto de informações da interface de rede atual
	 * 
	 * @return java.util.HashMap(String, Object) - Conjunto de configurações da interface de rede
	 * 
	 * Parametros de saída:
	 * X = Index da interface de rede para o sistema operacional
	 * 
	 * - DESCRIPTION: X - Descrição da interface de rede - Provedor de serviços
	 * - NAME: X - Nome da interface de rede
	 * - HOST: X - Host / IP
	 * - HOSTNAME: X - Nome da máquina
	 * - HOSTADDRESS: X - Ip da máquina
	 * - ADDRESS: X - Configuração de rede - Exemplo [/127.0.0.1/8 [/127.255.255.255]
	 * 
	 */
	public static java.util.HashMap<String, Object> autGetInterfaceInfo(){
		try {
			java.util.HashMap<String,Object> netParams = new java.util.HashMap<String,Object>();			
			System.out.println("AUT INFO: LOADER NETWORK CONFIGURATIONS");			
			java.util.Enumeration<java.net.NetworkInterface> enumNwt = java.net.NetworkInterface.getNetworkInterfaces();
			if(enumNwt.hasMoreElements()) {
				while(enumNwt.hasMoreElements()) {					
					java.net.NetworkInterface nwt = enumNwt.nextElement();
					java.util.List<java.net.InterfaceAddress> ltAddress = nwt.getInterfaceAddresses();
					if(ltAddress.size() > 0 && nwt.isUp()) {
						Integer indexNwt = nwt.getIndex();
						netParams.put("DESCRIPTION:".concat(indexNwt.toString()), nwt.getDisplayName());
						netParams.put("NAME:".concat(indexNwt.toString()), nwt.getName());									
						for(java.net.InterfaceAddress inet : ltAddress) {
							netParams.put("HOST:".concat(indexNwt.toString()),inet.getAddress().getLocalHost().toString());
							netParams.put("HOSTNAME:".concat(indexNwt.toString()), inet.getAddress().getLocalHost().getHostName());
							netParams.put("HOSTADDRESS:".concat(indexNwt.toString()), new String(inet.getAddress().getLocalHost().getHostAddress()));
							netParams.put("ADDRESS:".concat(indexNwt.toString()), nwt.getInterfaceAddresses());
						}
						System.out.println("\n\n************** FIM **************");
					}
				}
			}
			else {
				System.out.println("AUT INFO: NOT EXISTS AN INTERFACE NETWORK DEFINED");				
			}			
			System.out.println(netParams);
			return netParams;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: GET INFORMATION FOR NETWORK INTERFACES");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	
	/**
	 * 
	 * Construtor padrão
	 * 
	 */
	public AUTNTWUtils() {
		// TODO Auto-generated constructor stub
	}

}
