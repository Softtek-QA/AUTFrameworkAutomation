package br.stk.framework.test.mobile.gui;

import java.awt.Graphics;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.FlavorMap;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.spi.DateFormatProvider;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.TooManyListenersException;
import java.util.Vector;

import javax.swing.DropMode;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.text.Position;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.xml.parsers.ParserConfigurationException;
import org.h2.tools.Script;
import org.xml.sax.SAXException;

import br.stk.framework.gui.api.AUTAPIGUI;
import br.stk.framework.gui.api.AUTAPIGUI.*;
import br.stk.framework.gui.api.AUTAPIGUI.AUTFormularioUtils.AUT_TIPO_MSG_USUARIO;
import br.stk.framework.gui.api.runtime.AUTRuntimeConfiguration;
import br.stk.framework.test.mobile.gui.AUTFormMobileConector.*;
import br.stk.framework.test.mobile.gui.AUTFormMobileConector.AUTDispositivoConfiguracao.*;
import br.stk.framework.test.mobile.gui.AUTFormMobileTesteManual.AUTDadosConexao; 


/**
 * CLASSE RESPONSÁVEL PELO GERENCIAMENTO DE CONEXÕES PARA DISPOSITIVOS MOBILE
 * 
 * @author SOFTTEK - QA
 *
 */
public class AUTFormMobileConector {
	int posImg = 0;
	java.util.List<AUTPainelImagem> containerImagens = new java.util.ArrayList<AUTPainelImagem>();
	javax.swing.JScrollPane painelImagemMobile = new javax.swing.JScrollPane(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	
	boolean  HabilitarResetTCPIP = false;
	public static java.util.HashMap<String,AUTProcessoParalelo> ltFunctions = new java.util.HashMap<String,AUTProcessoParalelo>();
	public AUTDispositivoConfiguracao autAdbConfig = new AUTDispositivoConfiguracao();	
	public AUTAndroidInterface adbObjectInterface = autAdbConfig.autAdbInterfaceFactory();
	public AUTAndroidInterface.AUTAndroidObject autAdbObject = adbObjectInterface.autFactoryAdbObject();

	AUT_ANDROID_CONFIGURACAO_IP configIP = AUT_ANDROID_CONFIGURACAO_IP.LIST_DISPOSITIVOS;
	
	public static class AUTDragData implements FlavorMap
	{
		
		@Override
		public Map<String, DataFlavor> getFlavorsForNatives(String[] arg0) {
			// TODO Auto-generated method stub
			
			return null;
		}

		@Override
		public Map<DataFlavor, String> getNativesForFlavors(DataFlavor[] arg0) {
			// TODO Auto-generated method stub
			return null;
		}

	}	

	/**
	 * 
	 * VARIAVEIS DO AMBIENTE
	 * 
	 */
	private javax.swing.JDialog formPrincipal = null;


	/**
	 *
	 * OBJETOS AUXILIARES
	 * 
	 */
	public static class AUTDispositivoConfiguracao{
		
		public AUTDispositivoConfiguracao autAdbConfig = this;
		public  String ANDROID_DEVICE_CONFIG_CUSTOM = "%s";
		public  Integer ANDROID_ID_IMG = 0;
		public  Integer ANDROID_ID_IMG_CUSTOM = 0;
		public  String HOST_NOME = "net.hostname";
		public  String VERSAO = "selinux.policy_version";
		public  String OPERADORA_PRINCIPAL = "gsm.operator.alpha";
		public  String PAIS = "ro.csc.countryiso_code";
		public  String VERSAO_BUILD_SDK = "ro.build.version.sdk";
		public  String VERSAO_BUILD_INCREMENTAL = "ro.build.version.incremental";
		public  String VERSAO_BUILD_RELEASE = "ro.build.version.release";
		public  String VERSAO_BUILD_SECURITY_PATH = "ro.build.version.security_patch";
		public  String CONEXAO_USB = "sys.usb.state";
		public  String FUSO_HORARIO = "persist.sys.timezone";
		public  String NOME_PRODUTO = "ro.product.name";
		public  String FABRICANTE = "ro.product.manufacturer";
		public  String MODELO_DISPOSITIVO = "ro.product.model";
		public  String ID = "ro.serialno";
		public  String DNS1_WLAN = "dhcp.wlan0.dns1";
		public  String DNS2_WLAN = "dhcp.wlan0.dns2";
		public  String DNS3_WLAN = "dhcp.wlan0.dns3";
		public  String DNS4_WLAN = "dhcp.wlan0.dns4";
		public  String DOMINIO_WLAN = "dhcp.wlan0.domain";
		public  String GATEWAY_WLAN = "dhcp.wlan0.gateway";
		public  String MASCARA_REDE_WLAN = "dhcp.wlan0.mask";
		public  String IP = "dhcp.wlan0.ipaddress";
		public  Integer SERIAL_SCREEN = 1;
		public  String ESTACAO_LOCAL_PASTA_RAIZ = "C:\\Repositorios\\AUTOMACAO\\";
		public  String ESTACAO_LOCAL_EVIDENCIAS_RESULT = ESTACAO_LOCAL_PASTA_RAIZ.concat("RESULTADOS EXECUCAO\\");
		public  String ESTACAO_LOCAL_VDS = ESTACAO_LOCAL_EVIDENCIAS_RESULT.concat("VIDEOS\\");
		public  String ESTACAO_LOCAL_EVIDENCIAS_IMGS = ESTACAO_LOCAL_EVIDENCIAS_RESULT.concat("IMAGENS\\");
		public  String ESTACAO_LOCAL_EVIDENCIAS_WEB = ESTACAO_LOCAL_EVIDENCIAS_RESULT.concat("PAGINA WEB\\");
		public  String ESTACAO_LOCAL_TEMP_FILES = ESTACAO_LOCAL_EVIDENCIAS_RESULT.concat("DADOS\\");
		public  String ESTACAO_LOCAL_ESTRUTURA_ARQUIVOS = String.format("cmd /c mkdir \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\"",ESTACAO_LOCAL_PASTA_RAIZ,ESTACAO_LOCAL_EVIDENCIAS_RESULT,ESTACAO_LOCAL_VDS,ESTACAO_LOCAL_EVIDENCIAS_IMGS,ESTACAO_LOCAL_EVIDENCIAS_WEB,ESTACAO_LOCAL_TEMP_FILES);		
		public  String ESTACAO_LOCAL_TEMP_FILE_IMG_AVT = ESTACAO_LOCAL_TEMP_FILES.concat("AUTIMGATV00%s.png");
		public  String ESTACAO_LOCAL_TEMP_FILE_IMG_AVT_CUSTOM = ESTACAO_LOCAL_TEMP_FILES.concat("DADOS\\AUTIMGGER00%s.png");
		public  String ESTACAO_LOCAL_TEMP_FILE_IMG_AVT_CUSTOM_RTIME = ESTACAO_LOCAL_TEMP_FILES.concat("DADOS\\AUTIMGMOB_%s-DATAEHORA-%s.png");
		public  Integer ANDROID_TEMPO_SINCRONIZACAO_CAP_IMG = 5;
		public  Integer ANDROID_PERCENTUAL_REDUCAO_CAP_IMG = 50;
		public  boolean ANDROID_CAP_AUTOMATIC_IMG = false;
		public  String ANDROID_PASTA_RAIZ = "/sdcard/aut_arquivos";
		public  String ANDROID_PASTA_VIDEO = ANDROID_PASTA_RAIZ.concat("/videos");
		public  String ANDROID_PASTA_TEMP_FILES = ANDROID_PASTA_RAIZ.concat("/Dados/");
		public  String ANDROID_PASTA_ADB_XML_GUI_OBJECT = ANDROID_PASTA_TEMP_FILES.concat("GUIOBJ.xml");
		public  String ANDROID_PASTA_ADB_IMG_ATV = ANDROID_PASTA_TEMP_FILES.concat("AUTIMGATV001.png");
		public  String ANDROID_PASTA_ADB_IMG_ATV_CUSTOM = ANDROID_PASTA_TEMP_FILES.concat("AUTIMGGER001.png");
		public  String ANDROID_PASTA_LOCAL_XML_GUI_OBJECT = ESTACAO_LOCAL_TEMP_FILES.concat("GUIOBJ.xml");		
		public  String ANDROID_ARQUIVO_VIDEO_PADRAO = ANDROID_PASTA_VIDEO.concat("AUT_VIDEO_001.mp4");
		public  String ANDROID_ESTRUTURA_ARQUIVOS = String.format("mkdir \"%s\" \"%s\" \"%s\"",ANDROID_PASTA_RAIZ.toString(),ANDROID_PASTA_VIDEO.toString(),ANDROID_PASTA_TEMP_FILES.toString());
		public  String ANDROID_ARQUIVO_ATUAL_CORRENTE = ANDROID_PASTA_RAIZ.concat("/AUT_IMG_ATUAL_00%s.png");
		public  String ANDROID_ARQUIVO_ESPERADO_CORRENTE = ANDROID_PASTA_RAIZ.concat("/AUT_IMG_ESPERADO_00%s.png");
		public  String ANDROID_SHELL_EXEC_GET_FILE_GUI = String.format("adb shell uiautomator dump --compress -f \"%s\"",ANDROID_PASTA_ADB_XML_GUI_OBJECT);
		public  String ANDROID_SHELL_EXEC_GET_FILE_GUI_CUSTOM = String.format("adb -s %s shell uiautomator dump --compress -f \"%s\"",ANDROID_DEVICE_CONFIG_CUSTOM,ANDROID_PASTA_ADB_XML_GUI_OBJECT);
		public  String ANDROID_SHELL_COPY_FILE_GUI = String.format("adb pull \"%s\" \"%s\"",ANDROID_PASTA_ADB_XML_GUI_OBJECT,ANDROID_PASTA_LOCAL_XML_GUI_OBJECT);
		public  String ANDROID_SHELL_COPY_FILE_GUI_CUSTOM = String.format("adb -s %s pull \"%s\" \"%s\"",ANDROID_DEVICE_CONFIG_CUSTOM,ANDROID_PASTA_ADB_XML_GUI_OBJECT,ANDROID_PASTA_LOCAL_XML_GUI_OBJECT);
		public  String SCREEN_SCRIPT_ST_ATUAL_CAP = ANDROID_PASTA_RAIZ.concat(";screencap \"").concat(ANDROID_ARQUIVO_ATUAL_CORRENTE).concat("\"");
		public  String SCREEN_SCRIPT_ST_ESPERADO_CAP = ANDROID_PASTA_RAIZ.concat(";screencap \"").concat(ANDROID_ARQUIVO_ESPERADO_CORRENTE).concat("\"");
		public  String RECORD_VIDEO_CAP = " screenrecord \"".concat(ANDROID_ARQUIVO_VIDEO_PADRAO).concat("\"");		
		public  Integer portConexaoMin = 60100;
		public  Integer portConexaoMax = 60300;
		public java.util.HashMap<Integer,AUTDadosConexao> AUTDadosConexoesAtivas = new java.util.HashMap<Integer,AUTDadosConexao>();
		public java.util.List<Integer> configuracaoConexaoPortas = new java.util.ArrayList<Integer>();
		public java.util.HashMap<String,String> AUTDadosTransferenciaArquivos = new java.util.HashMap<String,String>();
		public java.util.HashMap<Integer,String[]> AUT_TECLAS_CONFIGURACAO = new java.util.HashMap<Integer,String[]>();				
		

		public enum AUT_ANDROID_CONFIGURACAO_IP{
			IP_IPV4_MULT_CONECT_DINAMICA,
			IP_IPV4_MULT_CONECT_DINAMICA_2,
			IP_IPV4_PESQUISA_DINAMICA,
			IP_POR_DHCP,
			IP_CONFIGURACAO_REDE,
			NOME_DISPOSITIVO,
			ID_DISPOSITIVO,
			LIST_DISPOSITIVOS,
			REDEFINIR_CONEXOES_DISPOSITIVOS_MOVEIS;		

			@Override
			public String toString() {				
				String cmdAndroid = "";
				String cmdOutput;
				
				boolean ipv4 = false,ipRede = false,ipPesquisaDinamica = false,hostName = false;
				StringBuffer listaDispositivos = new java.lang.StringBuffer();
				java.util.regex.Pattern padraoLinhaDevice = java.util.regex.Pattern.compile("[\\w]+[\\-]{0,}[\\w]+\\s+device");


				switch(this) {		
				case REDEFINIR_CONEXOES_DISPOSITIVOS_MOVEIS:{
					return "adb kill-server\nadb start-server";
				}
				case LIST_DISPOSITIVOS:{
					try {

						String[] devices = AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso("adb devices").toString().split("\n");

						for(String dev : devices) {
							if(!dev.contains("List of")) {
								String hostAdb = dev.split("\\s+device")[0];
								if(hostAdb.trim().length() > 5) {
									System.out.println("INFODEVICE: PROCESSANDO SAIDA DO CONSOLE");
									System.out.println(hostAdb);
									
									listaDispositivos.append(hostAdb.toString().trim().concat("\n"));
								}
							}
						}

						return listaDispositivos.toString();
					}
					catch(java.lang.Exception e) {
						return "ERROCAPDEV - CAPTURA DE DISPOSITIVOS CONECTADOS";
					}
				}
				case IP_IPV4_MULT_CONECT_DINAMICA:{					
					return "adb -s %s shell ip -f inet addr";
				}
				case IP_IPV4_MULT_CONECT_DINAMICA_2:{					
					return "adb -s %s shell getprop dhcp.wlan0.ipaddress";
				}
				case IP_CONFIGURACAO_REDE:{				

					cmdAndroid = "adb shell ip -f inet addr";	

					ipRede = true;

					break;
				}
				case IP_IPV4_PESQUISA_DINAMICA:{

					ipPesquisaDinamica = true;

					break;
				}
				case NOME_DISPOSITIVO:{

					cmdAndroid = "adb shell printenv HOSTNAME";

					return AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(cmdAndroid).toString();
				}
				default:{
					break;					
				}
				}

				cmdOutput = "";

				if(ipv4) {
					cmdOutput = AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(cmdAndroid).toString();
				}
				else if(ipRede) {
					cmdOutput = formatarIPRede(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(cmdAndroid).toString()).toString();
					if(cmdOutput.trim().isEmpty()) {
						cmdOutput = "ERROIPREDE";
					}
				}
				else if(ipPesquisaDinamica) {
					cmdOutput = AUT_ANDROID_CONFIGURACAO_IP.IP_POR_DHCP.toString().trim();
					if(cmdOutput.isEmpty()) {
						cmdOutput = AUT_ANDROID_CONFIGURACAO_IP.IP_CONFIGURACAO_REDE.toString().trim();
						if(cmdOutput.isEmpty()) {
							cmdOutput = AUT_ANDROID_CONFIGURACAO_IP.NOME_DISPOSITIVO.toString().concat(" : ID : ".concat(AUT_ANDROID_CONFIGURACAO_IP.ID_DISPOSITIVO.toString()));
						}
					}
				}				

				return cmdOutput;
			}

		}


		/**
		 * 
		 * configuração de portas de conexão do dispositivo mobile
		 * 
		 */
		public void configurarPortasConexao() {
			System.out.println("INFO : PORTAS DE CONEXAO CONFIGURADAS");

			for(Integer i = portConexaoMin; i <= portConexaoMax;i++) {
				System.out.println("INFO : CONFIGURANDO RESERVA PORTAS DE CONEXAO: ".concat(i.toString()));

				configuracaoConexaoPortas.add(i);
			}

		}


		/**
		 * 
		 * Atribui uma porta de comunicação para o dispositivo
		 * 
		 */
		public void adicionarConexao() {

			String ip = AUT_ANDROID_CONFIGURACAO_IP.IP_IPV4_PESQUISA_DINAMICA.toString();

			if(ip.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {

				Integer portaCon = (configuracaoConexaoPortas.size() > 0 ? configuracaoConexaoPortas.get(configuracaoConexaoPortas.size()-1) : 60000);
				System.out.println(String.format("INFO : TOTAL PORTAS DISPONIVEL: %s : IP : %s : PORTA : %s",configuracaoConexaoPortas.size()-1,ip,portaCon));

				configuracaoConexaoPortas.remove(portaCon);

				System.out.println(String.format("INFO : PORTAS DISPONIVEIS (-1 utilizada): %s",configuracaoConexaoPortas.size()-1));

				AUTDadosConexao configItem = new AUTDadosConexao();


				
				if(!AUTDadosConexoesAtivas.containsKey(portaCon)) {
					AUTDadosConexoesAtivas.put(portaCon, new AUTDadosConexao());					
					AUTDadosConexoesAtivas.get(portaCon).ID = AUT_ANDROID_CONFIGURACAO_IP.ID_DISPOSITIVO.toString();
					AUTDadosConexoesAtivas.get(portaCon).IP = ip;
					AUTDadosConexoesAtivas.get(portaCon).PORTA = portaCon.toString();
					conectarTCPIP(AUTDadosConexoesAtivas.get(portaCon).IP, AUTDadosConexoesAtivas.get(portaCon).PORTA);
					System.out.println("INFO : CONFIGURACAO TCP IP REALIZADA COM SUCESSO");
					System.out.println(AUTDadosConexoesAtivas);
				}

			}
			else {
				AUTAPIGUI.AUTFormularioUtils.exibirMensagem("SOFTTEK QA - ASSISTENTE DE CONEXÃO", "O dispositivo selecionado não possui um IP de conexão válido : \n" + 
						"\b1º - Habilitar conexão WIFI\n"
						+ "\b2º - Verifique nas opções avançadas de configuração de rede se um IP foi atribuido a conexão\n"
						+ "\b3º - O computador e o dispositivo devem está conectados na mesma rede\n"
						+ "\b4º - Se ainda sim falhar, reinicie o android e tente novamente"

						);

			}
		}

		public void adicionarConexao(String IPConexao) {

			String ip = IPConexao;

			if(ip.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {

				Integer portaCon = (configuracaoConexaoPortas.size() > 0 ? configuracaoConexaoPortas.get(configuracaoConexaoPortas.size()-1) : 60000);
				System.out.println(String.format("INFO : TOTAL PORTAS DISPONIVEL: %s : IP : %s : PORTA : %s",configuracaoConexaoPortas.size()-1,ip,portaCon));

				configuracaoConexaoPortas.remove(portaCon);

				System.out.println(String.format("INFO : PORTAS DISPONIVEIS (-1 utilizada): %s",configuracaoConexaoPortas.size()-1));

				AUTDadosConexao configItem = new AUTDadosConexao();


				if(!AUTDadosConexoesAtivas.containsKey(portaCon)) {
					AUTDadosConexoesAtivas.put(portaCon, new AUTDadosConexao());					
					AUTDadosConexoesAtivas.get(portaCon).ID = AUT_ANDROID_CONFIGURACAO_IP.ID_DISPOSITIVO.toString();
					AUTDadosConexoesAtivas.get(portaCon).IP = ip;
					AUTDadosConexoesAtivas.get(portaCon).PORTA = portaCon.toString();
					conectarTCPIP(AUTDadosConexoesAtivas.get(portaCon).IP, AUTDadosConexoesAtivas.get(portaCon).PORTA);
					System.out.println("INFO : CONFIGURACAO TCP IP REALIZADA COM SUCESSO");
					System.out.println(AUTDadosConexoesAtivas);
				}

			}
			else {
				AUTAPIGUI.AUTFormularioUtils.exibirMensagem("SOFTTEK QA - ASSISTENTE DE CONEXÃO", "O dispositivo selecionado não possui um IP de conexão válido : \n" + 
						"\b1º - Habilitar conexão WIFI\n"
						+ "\b2º - Verifique nas opções avançadas de configuração de rede se um IP foi atribuido a conexão\n"
						+ "\b3º - O computador e o dispositivo devem está conectados na mesma rede\n"
						+ "\b4º - Se ainda sim falhar, reinicie o android e tente novamente"

						);

			}
		}

		public static void resetConnections() {
			try {
			System.out.println("PROC 1");
			String[] cmds = AUT_ANDROID_CONFIGURACAO_IP.REDEFINIR_CONEXOES_DISPOSITIVOS_MOVEIS.toString().split("\n");
			System.out.println("INFO ADB : ENVIANDO COMANDOS DE REDEFINICAO DE CONEXOES TCPIP");
			int contTentativas = 0;
			int totTentativasPadrao = 3;
			int totTentativas = 3;
			System.out.println("PROC 2");
			for(String cmd: cmds) {
				if(cmd.contains("kill")) {
					System.out.println("INFO : PROCEDIMENTO CRÍTICO DE FINALIZACAO");
					for(contTentativas = 0,totTentativas = 3;contTentativas < totTentativas;contTentativas++) {
						AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(cmd);
					}
					
					totTentativas = totTentativasPadrao;
					
				}
				else {
					for(contTentativas = 1;contTentativas <= totTentativas; contTentativas++) {
						System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(cmd));
					}
				}
				
			}
			
			}
			catch(java.lang.Exception e) {
				System.out.println("RESET PROCESSO : ERROR");
				System.out.println(e.getMessage());
				e.printStackTrace();
				
			}
		}
		
		public void adicionarConexao(String nomeDispositivo,String IPConexao,String portaConexao) {

			String ip = IPConexao.trim();
			
			Integer portaCon = Integer.parseInt(portaConexao.trim());
			
			AUTDadosConexoesAtivas.put(portaCon, new AUTDadosConexao());					
			AUTDadosConexoesAtivas.get(portaCon).ID = AUT_ANDROID_CONFIGURACAO_IP.ID_DISPOSITIVO.toString();
			AUTDadosConexoesAtivas.get(portaCon).IP = ip;
			AUTDadosConexoesAtivas.get(portaCon).PORTA = portaCon.toString();
			AUTDadosConexoesAtivas.get(portaCon).NOME_DISPOSITIVO = nomeDispositivo;
			
			conectarTCPIP(AUTDadosConexoesAtivas.get(portaCon).NOME_DISPOSITIVO,AUTDadosConexoesAtivas.get(portaCon).IP, AUTDadosConexoesAtivas.get(portaCon).PORTA);
			
			
			
			System.out.println("INFO : CONFIGURACAO TCP IP REALIZADA COM SUCESSO");
			System.out.println(AUTDadosConexoesAtivas);
						
		}

		public static void conectarTCPIP(String IP,String porta) {
			porta = porta.trim();
			IP = IP.trim();

			System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso("adb -d tcpip ".concat(porta)));
			System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso("adb -d connect ".concat(IP).concat(":").concat(porta)));
			System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso("adb -d connect ".concat(IP).concat(":").concat(porta)));

			AUTFormularioUtils.exibirMensagem("Softtek - QA : Assistente de Configuração", "COMANDOS DE CONFIGURAÇÃO TCP IP EXECUTADOS:\n"
					+ "\b* DESCONECT O DISPOSITIVO DA PORTA USB E FAÇA O TESTE DE CONEXÃO");
		}

		public void conectarTCPIP(String nomeDispositivo,String IP,String porta) {
			
			porta = porta.trim();
			IP = IP.trim();
			nomeDispositivo = nomeDispositivo.trim();
			
			System.out.println("@@@@CONNECTION-START:");
			System.out.println(nomeDispositivo);
			System.out.println(IP);
			System.out.println(porta);
			
			System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s tcpip %s",nomeDispositivo,porta)));
			String outConsole = AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb connect %s:%s",IP,porta)).toString();
			
			int numTentativas = 4;
			int contTentativas = 0;
			while(outConsole.contains("unable to connect") || outConsole.isEmpty() || !outConsole.contains("connected")) {
				
				outConsole = AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb connect %s:%s",IP,porta)).toString();
				System.out.println(outConsole);
				try {
					java.lang.Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(contTentativas == numTentativas) {
					break;
				}
				else {
					contTentativas++;
				}
			}
			System.out.println("AUT ADB INFO : DISPOSITIVO CONECTADO COM SUCESSO!!!!");

		}		
		

		public static void disconectarTCPIP(String IP,Integer porta) {
			System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso("adb -d disconnect ".concat(IP).concat(":").concat(porta.toString())));
			System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso("adb -d usb"));
			AUTFormularioUtils.exibirMensagem("Softtek - QA : Assistente de Configuração", "COMANDOS DE CONFIGURAÇÃO TCP IP EXECUTADOS:\n" +
					"\b* DESCONECT O DISPOSITIVO DA PORTA USB E FAÇA O TESTE DE CONEXÃO");
		}

		public static javax.swing.DefaultListModel carregarAplicativosInstalados(){
			javax.swing.DefaultListModel ddOut = new javax.swing.DefaultListModel();
			java.lang.StringBuffer appsInst = AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso("adb shell pm list packages");
			String[] appItens = appsInst.toString().split("\n");

			java.util.regex.Pattern padrao = java.util.regex.Pattern.compile("(\\w+\\..*)+");
			java.util.regex.Matcher verif = padrao.matcher(appsInst);

			while(verif.find()) {
				ddOut.addElement(verif.group());
			}

			return ddOut;
		}

		
		public static javax.swing.DefaultListModel carregarAplicativosInstalados(String nomeDispositivo){
			javax.swing.DefaultListModel ddOut = new javax.swing.DefaultListModel();
			java.lang.StringBuffer appsInst = AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s shell pm list packages",nomeDispositivo));
			String[] appItens = appsInst.toString().split("\n");

			java.util.regex.Pattern padrao = java.util.regex.Pattern.compile("(\\w+\\..*)+");
			java.util.regex.Matcher verif = padrao.matcher(appsInst);

			while(verif.find()) {
				ddOut.addElement(verif.group());
			}

			return ddOut;
		}
		/**
		 * 
		 * libera as portas de comunição atualmente utilizadas
		 * 
		 */
		public  void fecharPortasComunicao() {
			System.out.println("INFO: LIBERANDO PORTAS DE COMUNICAÇÃO");			
			for(Integer porta : AUTDadosConexoesAtivas.keySet()) {
				AUTDadosConexao con = AUTDadosConexoesAtivas.get(porta);
				System.out.println(String.format("INFO : IP : %s : PORTA : %s : ID :%s",con.IP,con.PORTA,con.ID));
			}


			System.out.println("****** INFO : RECONFIGURANDO PORTAS DE CONEXAO ******");

			configurarPortasConexao();
		}





		public void inicializarConfiguracoes(String nomeDispositivo) {	
			int totTentativas = 2;
			for(int tentativas = 0;tentativas < totTentativas;tentativas++) {
				AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s shell %s;\n",nomeDispositivo,ANDROID_ESTRUTURA_ARQUIVOS));
			}

			
			AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(ESTACAO_LOCAL_ESTRUTURA_ARQUIVOS);
			
		}

		
		public static java.lang.StringBuffer formatarIPRede(String conteudoProcessamento) {
			String expLocation = "inet \\d+.\\d+.\\d+.\\d+";	
			String expSplit = "inet ";
			java.lang.StringBuffer output = new java.lang.StringBuffer();
			java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(expLocation);
			java.util.regex.Matcher analiseExp = padrao.matcher(conteudoProcessamento);
			System.out.println("INFO : PROCESSANDO DADOS DE CONFIGURACAO DE REDE : ");
			System.out.println("EXPRESSAO REGULAR DE PROCESSAMENTO (IP) : ".concat(expLocation));
			System.out.println("CONTEUDO PARA PROCESSAMENTO: ");
			System.out.println(conteudoProcessamento);

			while(analiseExp.find()) {

				System.out.println("CORRESPONDENCIA ENCONTRADA: ".concat(analiseExp.group()));
				String[] ipItens = analiseExp.group().split(expSplit);
				if(!ipItens[1].trim().equals("127.0.0.1")) {
					output.append(ipItens[1]);
				}				
			}

			return output;
		}

		public static java.util.List<String> formatarConfigTeclado(java.lang.StringBuffer layoutTecladoConteudo){
			java.util.List<String> ltConfig = new java.util.ArrayList<String>();

			java.util.regex.Pattern padrao = java.util.regex.Pattern.compile("key\\s+[\\w_]{1,}");
			java.util.regex.Matcher analiseExp = padrao.matcher(layoutTecladoConteudo);
			System.out.println("INFO : PROCESSANDO CONTEUDO DE CONFIGURAÇÃO DO TECLADO MOBILE");

			while(analiseExp.find()) {

				System.out.println("INFO : KEY COMMAND ENCONTRADO: ".concat(analiseExp.group()));

				ltConfig.add(analiseExp.group());
			}

			return ltConfig;
		}

		public static Object[] carregarConfiguracaoTeclado(){
			java.util.List<String> listaCmds = new java.util.ArrayList<String>();
			java.util.regex.Pattern padrao = java.util.regex.Pattern.compile("key\\s+[\\w_]{1,}");
			java.util.regex.Matcher analiseExp;
			System.out.println("INFO : PROCESSANDO CONTEUDO DE CONFIGURAÇÃO DO TECLADO MOBILE");

			String[] teclados = AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso("adb shell cd system/usr/keychars;ls;").toString().split("\n");
			String[] tecladosLayout	 = AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso("adb shell cd system/usr/keylayout;ls;").toString().split("\n");

			for(String configItem : teclados) {
				if(!configItem.trim().isEmpty()) {
					System.out.println("INFO : CONFIGURACAO ENCONTRADA : ".concat(configItem));
					//String tecladoConfig = AUTAPI.AUTProcessoExternoUtils.executarProcesso("adb shell cat system/usr/keychars/".concat(configItem)).toString();
					System.out.println("INFO : ARQUIVO : ".concat(configItem));
					analiseExp = padrao.matcher(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso("adb shell cat system/usr/keychars/".concat(configItem)));

					while(analiseExp.find()) {
						String cmd = analiseExp.group().trim().split("key ")[1];
						System.out.println("INFO : KEY COMMAND ENCONTRADO: ".concat(cmd));
						if(!listaCmds.contains(cmd)) {
							listaCmds.add(cmd);
						}						
					}
				}
			}

			padrao = java.util.regex.Pattern.compile("key\\s+[\\w_]{1,}");


			return listaCmds.toArray();
		}

		public static Object[] carregarConfiguracaoTeclado(String nomeDispositivo){
			java.util.List<String> listaCmds = new java.util.ArrayList<String>();
			java.util.regex.Pattern padrao = java.util.regex.Pattern.compile("key\\s+[\\w_]{1,}");
			java.util.regex.Matcher analiseExp;
			System.out.println("INFO : PROCESSANDO CONTEUDO DE CONFIGURAÇÃO DO TECLADO MOBILE");

			String[] teclados = AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s shell cd system/usr/keychars;ls;",nomeDispositivo)).toString().split("\n");
			String[] tecladosLayout	 = AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s shell cd system/usr/keylayout;ls;",nomeDispositivo)).toString().split("\n");

			for(String configItem : teclados) {
				if(!configItem.trim().isEmpty()) {
					System.out.println("INFO : CONFIGURACAO ENCONTRADA : ".concat(configItem));
					System.out.println("INFO : ARQUIVO : ".concat(configItem));
					analiseExp = padrao.matcher(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s shell cat system/usr/keychars/".concat(configItem),nomeDispositivo)));

					while(analiseExp.find()) {
						String cmd = analiseExp.group().trim().split("key ")[1];
						System.out.println("INFO : KEY COMMAND ENCONTRADO: ".concat(cmd));
						if(!listaCmds.contains(cmd)) {
							listaCmds.add(cmd);
						}						
					}
				}
			}

			padrao = java.util.regex.Pattern.compile("key\\s+[\\w_]{1,}");


			return listaCmds.toArray();
		}


		
		/**
		 * 
		 * INTERFACE DE COMANDOS ANDROID
		 * 
		 */
		public class AUTAndroidInterface{

			
			public class AUTAndroidObject{

				/**
				 * Carrega o tipo do elemento que está sendo exibido na interface gráfica do sistema
				 * 
				 * @param item - Objeto XML passado como parametro (node)
				 * @return
				 */
				public String carregarTipoElementoGUI(org.w3c.dom.Node item) {						
					return item.getAttributes().getNamedItemNS(item.getNamespaceURI(), "class").toString();
				}


				public String carregarLabelElementoGUI(org.w3c.dom.Node item) {						
					return item.getAttributes().getNamedItemNS(item.getNamespaceURI(), "text").getNodeValue();
				}


				public AUTPainelImagem carregarImagem(Integer escalaReducao) {

					AUTPainelImagem img  = null;

					autAdbConfig.ANDROID_ID_IMG++;

					String fileImg  = String.format(autAdbConfig.ESTACAO_LOCAL_TEMP_FILE_IMG_AVT,autAdbConfig.ANDROID_ID_IMG);

					System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb shell screencap \"%s\"",autAdbConfig.ANDROID_PASTA_ADB_IMG_ATV)));

					System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb pull \"%s\" \"%s\"",autAdbConfig.ANDROID_PASTA_ADB_IMG_ATV,fileImg)));

					System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb shell rm \"%s\"",autAdbConfig.ANDROID_PASTA_ADB_IMG_ATV)));

					img = new AUTAPIGUI.AUTPainelImagem(fileImg,escalaReducao);

					return img;				
				}

				public AUTPainelImagem carregarImagem() {

					AUTPainelImagem img  = null;

					autAdbConfig.ANDROID_ID_IMG++;

					String fileImg  = String.format(autAdbConfig.ESTACAO_LOCAL_TEMP_FILE_IMG_AVT,autAdbConfig.ANDROID_ID_IMG);

					System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb shell screencap \"%s\"",autAdbConfig.ANDROID_PASTA_ADB_IMG_ATV)));

					System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb pull \"%s\" \"%s\"",autAdbConfig.ANDROID_PASTA_ADB_IMG_ATV,fileImg)));

					System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb shell rm \"%s\"",autAdbConfig.ANDROID_PASTA_ADB_IMG_ATV)));

					img = new AUTAPIGUI.AUTPainelImagem(fileImg,autAdbConfig.ANDROID_PERCENTUAL_REDUCAO_CAP_IMG);

					return img;				
				}

				public AUTPainelImagem carregarImagem(String nomeDispositivo) {
					
					AUTPainelImagem img  = null;

					autAdbConfig.ANDROID_ID_IMG++;
				
					autAdbConfig.inicializarConfiguracoes(nomeDispositivo);

					String fileImg  = String.format(autAdbConfig.ESTACAO_LOCAL_TEMP_FILE_IMG_AVT,autAdbConfig.ANDROID_ID_IMG);

					System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s shell screencap \"%s\"",nomeDispositivo,autAdbConfig.ANDROID_PASTA_ADB_IMG_ATV)));

					System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s pull \"%s\" \"%s\"",nomeDispositivo,autAdbConfig.ANDROID_PASTA_ADB_IMG_ATV,fileImg)));

					System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s shell rm \"%s\"",nomeDispositivo,autAdbConfig.ANDROID_PASTA_ADB_IMG_ATV)));

					img = new AUTAPIGUI.AUTPainelImagem(fileImg,autAdbConfig.ANDROID_PERCENTUAL_REDUCAO_CAP_IMG);

					return img;				
				}

				public AUTPainelImagem carregarImagem(int idImagem,int largura,int altura) {

					AUTPainelImagem img  = null;


					String fileImg  = String.format(autAdbConfig.ESTACAO_LOCAL_TEMP_FILE_IMG_AVT_CUSTOM,idImagem);

					System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb shell screencap \"%s\"",autAdbConfig.ANDROID_PASTA_ADB_IMG_ATV_CUSTOM)));

					System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb pull \"%s\" \"%s\"",autAdbConfig.ANDROID_PASTA_ADB_IMG_ATV_CUSTOM,fileImg)));

					System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb shell rm \"%s\"",autAdbConfig.ANDROID_PASTA_ADB_IMG_ATV_CUSTOM)));

					img = new AUTAPIGUI.AUTPainelImagem(fileImg,0,0,largura,altura);

					return img;				
				}

				public AUTPainelImagem carregarImagem(String nomeDispositivo,int idImagem,int largura,int altura) {

					
					AUTPainelImagem img  = null;
					
					try {
						java.util.Date data = new java.util.Date();
						java.text.SimpleDateFormat formatData = new java.text.SimpleDateFormat();
						
						String idImagemTemp = formatData.format(data).concat("-").concat(formatData.getTimeInstance().format(data));
						idImagemTemp = idImagemTemp.replaceAll("\\W", "-");
						String nmDev =  nomeDispositivo.replaceAll("\\W", "");
						
						String fileImg  = String.format(autAdbConfig.ESTACAO_LOCAL_TEMP_FILE_IMG_AVT_CUSTOM_RTIME,nmDev,idImagemTemp);
		
						System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s shell screencap \"%s\"",nomeDispositivo,autAdbConfig.ANDROID_PASTA_ADB_IMG_ATV_CUSTOM)));
		
						System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s pull \"%s\" \"%s\"",nomeDispositivo,autAdbConfig.ANDROID_PASTA_ADB_IMG_ATV_CUSTOM,fileImg)));
		
						System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s shell rm \"%s\"",nomeDispositivo,autAdbConfig.ANDROID_PASTA_ADB_IMG_ATV_CUSTOM)));
		
						img = new AUTAPIGUI.AUTPainelImagem(fileImg,0,0,largura,altura);

					}
					catch(java.lang.Exception e) {
						System.out.println("RTIMEERROR: ERRO AO GERAR IMAGEM EM TEMPO REAL...");
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
					
					return img;				
				}
				
				public void carregarArvoreObjetosXML(org.w3c.dom.Document documentoXML,javax.swing.JTree objetoGUI){

					org.w3c.dom.NodeList itemsXML = documentoXML.getElementsByTagName("node");

					javax.swing.tree.TreePath dados;
					java.util.List<org.w3c.dom.Node> itemsOut = new java.util.ArrayList<org.w3c.dom.Node>();
					int tot = itemsXML.getLength();
					javax.swing.tree.DefaultMutableTreeNode dadosArvore = new javax.swing.tree.DefaultMutableTreeNode("Objetos Interface Gráfica", true);
					System.out.println(String.format("TOTAL DE ITENS XML: %s", tot));

					for(int i = 0 ; i < tot ; i++) {
						try {
							org.w3c.dom.Node itemCorrente = itemsXML.item(i);

							itemsOut.add(itemCorrente);	
							String itemAttr = "";						
							javax.swing.tree.DefaultMutableTreeNode item = new javax.swing.tree.DefaultMutableTreeNode(String.format("Item :    %s   :   COMPONENTE GUI : %s",i,carregarLabelElementoGUI(itemCorrente)),true);

							for(int c = 0 ; c < itemCorrente.getAttributes().getLength();c++) {
								String nomeAtributo = itemCorrente.getAttributes().item(c).getNodeName();
								Object valorAtributo = itemCorrente.getAttributes().item(c).getNodeValue();

								System.out.println(String.format("ATRIBUTO : %s VALOR : %s",nomeAtributo,valorAtributo));	

								javax.swing.tree.DefaultMutableTreeNode itemAttrNome = new javax.swing.tree.DefaultMutableTreeNode(nomeAtributo,true);
								itemAttrNome.add(new javax.swing.tree.DefaultMutableTreeNode(valorAtributo));					

								item.add(itemAttrNome);
							}	

							dadosArvore.add(item);
							System.out.println(itemsXML.item(i).getAttributes());
							System.out.println(itemsXML.item(i));

						}
						catch(java.lang.Exception e) {
							System.out.println("INFO: ERRO NA CAPTURA DO ELEMENTO DE TELA");
							System.out.println(e.getMessage());
							e.printStackTrace();
						}
					}

					if(AUTRuntimeConfiguration.AUT_ARVORE_OBJETOS_EXIBICAO_EXPANDIDA) {
						objetoGUI.setModel(new javax.swing.tree.DefaultTreeModel(dadosArvore));

						for(int i = 0;i < objetoGUI.getRowCount();i++) {						
							objetoGUI.expandRow(i);
						}					
					}
					else {
						objetoGUI.setModel(new javax.swing.tree.DefaultTreeModel(dadosArvore));
					}
				}

				public boolean pesquisarTexto(String textoElemento,org.w3c.dom.Document documentoXML) {
					try {

						System.out.println(String.format("INFO : PROCURANDO ELEMENTO  :",textoElemento));
						System.out.println("INFO CONTEUDO ANALISADO : ");
						System.out.println(documentoXML.getNodeValue().toString());					

						return true;
					}
					catch(java.lang.Exception e) {


						return false;
					}
				}


				public org.w3c.dom.Document carregarXMLConfiguracao() {

					try {

						javax.xml.parsers.DocumentBuilderFactory autDomBuilder = javax.xml.parsers.DocumentBuilderFactory.newInstance();

						java.io.File autFile = new java.io.File(gerarXMLGUI());

						org.w3c.dom.Document autDoc = autDomBuilder.newDocumentBuilder().parse(autFile);


						return autDoc;

					} catch (ParserConfigurationException e) {

						System.out.println("INFOERROR : PARSER DO XML");

						System.out.println(e.getMessage());

						e.printStackTrace();

						return null;

					} catch (SAXException e) {

						System.out.println("INFOERROR : PARSER SAXEXCEPTION");

						System.out.println(e.getMessage());

						e.printStackTrace();

						return null;

					} catch (IOException e) {
						System.out.println("INFOERROR : PARSER IOEXCEPTION");

						System.out.println(e.getMessage());

						e.printStackTrace();

						return null;
					}	
				}

				public org.w3c.dom.Document carregarXMLConfiguracao(String nomeDispositivo) {

					try {

						javax.xml.parsers.DocumentBuilderFactory autDomBuilder = javax.xml.parsers.DocumentBuilderFactory.newInstance();

						java.io.File autFile = new java.io.File(gerarXMLGUI(nomeDispositivo));

						org.w3c.dom.Document autDoc = autDomBuilder.newDocumentBuilder().parse(autFile);


						return autDoc;

					} catch (ParserConfigurationException e) {

						System.out.println("INFOERROR : PARSER DO XML");

						System.out.println(e.getMessage());

						e.printStackTrace();

						return null;

					} catch (SAXException e) {

						System.out.println("INFOERROR : PARSER SAXEXCEPTION");

						System.out.println(e.getMessage());

						e.printStackTrace();

						return null;

					} catch (IOException e) {
						System.out.println("INFOERROR : PARSER IOEXCEPTION");

						System.out.println(e.getMessage());

						e.printStackTrace();

						return null;
					}	
				}


				public AUTAndroidObject() {

				}

			}

			public AUTAndroidObject autFactoryAdbObject() {
				return new AUTAndroidObject();
			}
			
			public AUTAndroidInterface autAdbInterfaceFactory() {
				return new AUTAndroidInterface();
			}

			public String gerarXMLGUI() {

				String dirOutAdbXML = autAdbConfig.ANDROID_PASTA_ADB_XML_GUI_OBJECT;

				System.out.println("*************** CARREGANDO XML GUI  DE CONFIGURAÇÃO DO APP: INICIO ***************************************");

				System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(autAdbConfig.ANDROID_SHELL_EXEC_GET_FILE_GUI));
				System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(autAdbConfig.ANDROID_SHELL_COPY_FILE_GUI));
				System.out.println("*************** CARREGANDO XML GUI  DE CONFIGURAÇÃO DO APP: INICIO ***************************************");

				return autAdbConfig.ANDROID_PASTA_LOCAL_XML_GUI_OBJECT;
			}

			public String gerarXMLGUI(String nomeDispositivo) {

				String dirOutAdbXML = autAdbConfig.ANDROID_PASTA_ADB_XML_GUI_OBJECT;

				System.out.println("*************** CARREGANDO XML GUI  DE CONFIGURAÇÃO DO APP: INICIO ***************************************");

				System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format(autAdbConfig.ANDROID_SHELL_EXEC_GET_FILE_GUI_CUSTOM.toString(),nomeDispositivo)));
				System.out.println(AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(String.format(autAdbConfig.ANDROID_SHELL_COPY_FILE_GUI_CUSTOM.toString(),nomeDispositivo)));
				System.out.println("*************** CARREGANDO XML GUI  DE CONFIGURAÇÃO DO APP: INICIO ***************************************");

				return autAdbConfig.ANDROID_PASTA_LOCAL_XML_GUI_OBJECT;
			}

			public javax.swing.JTree carregarXMLConfiguracao(String arquivo) {
				javax.swing.JTree arvoreObjetos = new javax.swing.JTree();
				try {


					return arvoreObjetos;
				}
				catch(java.lang.Exception e) {				
					System.out.println("INFOERROR: NÃO FOI POSSÍVEL CARREGAR O XML DE CONFIGURAÇÃO CLASSE");
					System.out.println(e.getMessage());
					e.printStackTrace();
					return null;
				}
			}
			/**
			 * 
			 * CONSTRUTOR PADRÃO DA CLASSE
			 * 
			 */
			public AUTAndroidInterface() {

			}
		}	

		public AUTAndroidInterface autAdbInterfaceFactory() {
			return new AUTAndroidInterface();
		}
		
		public br.stk.framework.test.mobile.gui.AUTFormMobileConector.AUTDispositivoConfiguracao.AUTAndroidInterface.AUTAndroidObject autAdbObjectFactory() {
			return new AUTAndroidInterface().autFactoryAdbObject();
		}
		
		/**
		 * 
		 * Construtor padrão da classe
		 * 
		 */
		public AUTDispositivoConfiguracao() {
			
		}
	}




	/**
	 * 
	 * ARVORE DE  OBJETOS ANDROID
	 * 
	 * 
	 */


	public static class AUTArvoreObjetosGUI extends javax.swing.tree.DefaultMutableTreeNode{
		public AUTArvoreObjetosGUI() {

		}
	}


	/**
	 * 
	 * CLASSE RESPONSÁVEL PELA CONFIGURAÇÃO DA INTERFACE GRÁFICA
	 * DO APP
	 */
	public void configGUI() {
		try {
			autAdbConfig = new AUTDispositivoConfiguracao();	
			adbObjectInterface = null; //autAdbConfig.autAdbInterfaceFactory();
			autAdbObject = null; //autAdbObject = adbObjectInterface.autFactoryAdbObject();
			System.out.println("AUT INFO  ADB INIT : SUCESSO");
			
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR : INIT CLASS ADB CONFIG");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		javax.swing.JDialog formMob = new javax.swing.JDialog(formPrincipal);
		javax.swing.JLabel lbDevices = new javax.swing.JLabel("DISPOSITIVOS CONECTADOS:");
		javax.swing.JList ltDevices = new javax.swing.JList();
		javax.swing.JScrollPane scrDevices = new javax.swing.JScrollPane(ltDevices,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JLabel lbApps = new javax.swing.JLabel("APLICATIVOS DO DISPOSITIVO:");
		javax.swing.JList ltApps = new javax.swing.JList();
		javax.swing.JScrollPane scrApps = new javax.swing.JScrollPane(ltApps,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JLabel lbTecladosConfig = new javax.swing.JLabel("CONFIGURAÇÕES DE TECLADO:");
		javax.swing.JList ltTecladosConfig = new javax.swing.JList();
		javax.swing.JScrollPane scrTecladosConfig = new javax.swing.JScrollPane(ltTecladosConfig,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JLabel lbObjetosGUI = new javax.swing.JLabel("OBJETOS DA INTERFACE GRÁFICA:");
		javax.swing.tree.DefaultMutableTreeNode ddObjectGUI = new javax.swing.tree.DefaultMutableTreeNode("GUI");
		ddObjectGUI.setAllowsChildren(true);		
		javax.swing.JTree treeObjetosGUI = new javax.swing.JTree();
		javax.swing.DefaultListModel ddObjetosGUI = new javax.swing.DefaultListModel();
		javax.swing.JScrollPane scrTeclados = new javax.swing.JScrollPane(treeObjetosGUI,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JLabel lbTerminalCMD = new javax.swing.JLabel("TERMINAL DE COMANDOS INTERATIVOS ANDROID:");
		javax.swing.JList ltTerminalComandos = new javax.swing.JList();
		javax.swing.JScrollPane scrTerminalComandos = new javax.swing.JScrollPane(ltTerminalComandos,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		java.util.Stack<AUTAPIGUI.AUTPainelImagem> pnImagens = new java.util.Stack<AUTAPIGUI.AUTPainelImagem>();
		javax.swing.JPanel pnControlesDevices = new javax.swing.JPanel();
		javax.swing.JPanel pnControlesApps = new javax.swing.JPanel();
		javax.swing.JPanel pnControlesTeclado = new javax.swing.JPanel();
		javax.swing.JPopupMenu menusControleDevices = new javax.swing.JPopupMenu();
		javax.swing.DefaultListModel ddDevices = new javax.swing.DefaultListModel();
		javax.swing.DefaultListModel ddLtTecladoConfig = new javax.swing.DefaultListModel();
		javax.swing.JPanel barraFerramentas = new javax.swing.JPanel();
		javax.swing.JButton btAplicarAlteracoes = new javax.swing.JButton("Aplicar");
		javax.swing.JMenuItem mnExcluirItemListaDevices = menusControleDevices.add("Excluir Dispositivo");
		javax.swing.JMenuItem mnAtualizarListaDevices = menusControleDevices.add("Atualizar Lista de Dispositivos");
		javax.swing.JMenuItem mnCarregarTelaDevices = menusControleDevices.add("Carregar Tela do Dispositivo");
		javax.swing.JMenuItem mnGerencMobile = menusControleDevices.add("Abrir Janela de Gerenciamento");
		javax.swing.DefaultListModel ddApps = new javax.swing.DefaultListModel();		
		javax.swing.JPopupMenu menuApps = new javax.swing.JPopupMenu();
		javax.swing.JMenuItem mnLoaderApps = menuApps.add("Carregar Aplicativos Instalados");
		javax.swing.JPanel pnControlesGerenciamento = new javax.swing.JPanel();
		javax.swing.JPanel pnConfigParamInit = new javax.swing.JPanel();
		javax.swing.JLabel lbConfigPercentReducaoIMG = new javax.swing.JLabel("Reduzir IMG (%) : ");
		javax.swing.JTextField txtConfigPercentReducaoIMG = new javax.swing.JTextField(4);
		javax.swing.JLabel lbConfigTempoSincronizacaoIMG = new javax.swing.JLabel("Tempo Sincronização (Segundos): ");
		javax.swing.JTextField txtTempoSincronizacaoIMG = new javax.swing.JTextField(4);
		java.awt.GridBagConstraints configLayoutConfigParam = new java.awt.GridBagConstraints();
		javax.swing.JCheckBox chkHabCapAutomatica = new javax.swing.JCheckBox("Captura de Tela Automática");
		javax.swing.JCheckBox chkHabSincAutomatica = new javax.swing.JCheckBox("Habilitar Processos de Sincronização da Aplicação");		
		javax.swing.JCheckBox chkHabCapAutComValidacao = new javax.swing.JCheckBox("Captura Telas Com Texto Específico");
		javax.swing.JCheckBox chkHabTCPIP = new javax.swing.JCheckBox("Habilitar Reset TCP-IP");
				
				
		javax.swing.JTable tabCapTextosVerificacao = new javax.swing.JTable();
		javax.swing.JScrollPane scrTabCapTextosVerif = new javax.swing.JScrollPane(tabCapTextosVerificacao,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.table.DefaultTableModel ddTabCapVerifTextos = new javax.swing.table.DefaultTableModel(new Object[] {"ITEM","PROPRIEDADE","VALOR","STATUS"},1);
		javax.swing.JPanel pnCapTextosVerif = new javax.swing.JPanel();
		javax.swing.JPanel pnConfigOpcoesGerenciamento = new javax.swing.JPanel();			
		
		
		chkHabCapAutomatica.setToolTipText("Habilita a função de captura automática de tela(Mobile) no tempo determinado (Tempo de Sincronização)");
		chkHabSincAutomatica.setToolTipText("Habilita a função que sincroniza os dados da tela atual de gerenciamento de dispositivos móveis (Mobile Conector)\n\n* 	Ex: Atualizar a lista de dispositivos conectados via USB");
		chkHabCapAutComValidacao.setToolTipText("Habilita a captura de tela caso exista o texto especificado nas opções de filtro");
		chkHabTCPIP.setToolTipText("Habilita configuração TCP-IP (Redefinição quando um dispositivo é conectado no USB)");
		
		ltDevices.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		abstract class AUTDropItem extends java.awt.dnd.DropTarget{
			private javax.swing.JTree arvoreDados = null;
			private javax.swing.JTable tabDados = null;

			private void configArvoreDados(javax.swing.JTree arvore) {
				arvoreDados = arvore;
			}

			private void configTabelaDados(javax.swing.JTable tabela) {
				tabDados = tabela;
			}

			public void processarDadosArvoreTabela(javax.swing.JTree itemGUI,javax.swing.JTable tab) {
				configArvoreDados(itemGUI);
				configTabelaDados(tab);
				javax.swing.table.DefaultTableModel ddDados =  (javax.swing.table.DefaultTableModel)tab.getModel();
				tab.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

				for(javax.swing.tree.TreePath item : itemGUI.getSelectionPaths()) {
					java.util.Enumeration<javax.swing.tree.TreePath> enumItems = itemGUI.getExpandedDescendants(item);
					while(enumItems.hasMoreElements()) {
						javax.swing.tree.TreePath itemSelect = enumItems.nextElement();
						System.out.println(itemSelect);
						try {
							String propriedade = itemSelect.toString().split(",")[2].replace("]", "");
							ddDados.insertRow(0,new Object[] {"",propriedade,"",""});
						}
						catch(java.lang.Exception e) {

						}
					}

				}


			}

			public void configProcessArvoreTabela(javax.swing.JTree itemGUI,javax.swing.JTable tab) {}

			public void iniciarExecucao(javax.swing.JTree itemGUI,javax.swing.JTable tab) {
				AUTProcessoParalelo procExec = new AUTProcessoParalelo() {

					@Override
					protected void rotinasInicializacao() {
						// TODO Auto-generated method stub

					}

					@Override
					protected void rotinasExecucao() {
						System.out.println("INFO : INICIANDO PROCESSAMENTO DROP CUSTOMIZADO");
						processarDadosArvoreTabela(itemGUI, tabCapTextosVerificacao);
					}

					@Override
					protected void rotinasFinalizacao() {
						// TODO Auto-generated method stub

					}

					@Override
					protected void rotinasMonitoramentoProcesso() {
						// TODO Auto-generated method stub

					}

				};


				procExec.executarProcesso();
			}


			public AUTDropItem() {
				super();
			}

			public AUTDropItem(javax.swing.JTree itemGUI,javax.swing.JTable tab) {
				super();
				try {
					this.addDropTargetListener(new java.awt.dnd.DropTargetListener() {

						@Override
						public void dragEnter(DropTargetDragEvent dtde) {
							// TODO Auto-generated method stub

						}

						@Override
						public void dragExit(DropTargetEvent dte) {
							// TODO Auto-generated method stub

						}

						@Override
						public void dragOver(DropTargetDragEvent dtde) {
							// TODO Auto-generated method stub

						}

						@Override
						public void drop(DropTargetDropEvent dtde) {

							iniciarExecucao(itemGUI, tabCapTextosVerificacao);

						}

						@Override
						public void dropActionChanged(DropTargetDragEvent dtde) {
							// TODO Auto-generated method stub

						}

					});
				} catch (TooManyListenersException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		tabCapTextosVerificacao.setModel(ddTabCapVerifTextos);
		tabCapTextosVerificacao.setDropTarget(new AUTDropItem(treeObjetosGUI,tabCapTextosVerificacao) {});
		treeObjetosGUI.setDragEnabled(true);


		pnCapTextosVerif.setLayout(new javax.swing.BoxLayout(pnCapTextosVerif,javax.swing.BoxLayout.Y_AXIS));
		pnCapTextosVerif.setBackground(java.awt.Color.WHITE);
		pnCapTextosVerif.add(scrTabCapTextosVerif);


		pnConfigOpcoesGerenciamento.setLayout(new java.awt.GridLayout(6,1,5,5));
		pnConfigOpcoesGerenciamento.add(chkHabCapAutomatica);
		pnConfigOpcoesGerenciamento.add(chkHabCapAutComValidacao);
		pnConfigOpcoesGerenciamento.add(chkHabSincAutomatica);
		pnConfigOpcoesGerenciamento.add(chkHabTCPIP);
		pnConfigOpcoesGerenciamento.setBackground(java.awt.Color.WHITE);


		pnConfigParamInit.setBackground(java.awt.Color.WHITE);
		pnConfigParamInit.setLayout(new javax.swing.BoxLayout(pnConfigParamInit,javax.swing.BoxLayout.X_AXIS));

		txtConfigPercentReducaoIMG.setText("35");
		txtTempoSincronizacaoIMG.setText("3");


		configLayoutConfigParam.insets = AUTAPIGUI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(5);
		configLayoutConfigParam.gridx = 0;
		configLayoutConfigParam.gridy = 0;
		configLayoutConfigParam.weightx = 0;
		configLayoutConfigParam.weighty = 1;
		configLayoutConfigParam.fill = configLayoutConfigParam.NONE;

		pnConfigParamInit.add(lbConfigPercentReducaoIMG);

		configLayoutConfigParam.gridx = 1;
		configLayoutConfigParam.gridy = 0;
		configLayoutConfigParam.weightx = 0;
		configLayoutConfigParam.weighty = 1;
		configLayoutConfigParam.fill = configLayoutConfigParam.NONE;

		pnConfigParamInit.add(txtConfigPercentReducaoIMG);

		configLayoutConfigParam.gridx = 2;
		configLayoutConfigParam.gridy = 0;
		configLayoutConfigParam.weightx = 0;
		configLayoutConfigParam.weighty = 1;
		configLayoutConfigParam.fill = configLayoutConfigParam.NONE;

		pnConfigParamInit.add(lbConfigTempoSincronizacaoIMG);

		configLayoutConfigParam.gridx = 3;
		configLayoutConfigParam.gridy = 0;
		configLayoutConfigParam.weightx = 0;
		configLayoutConfigParam.weighty = 1;
		configLayoutConfigParam.fill = configLayoutConfigParam.NONE;

		pnConfigParamInit.add(txtTempoSincronizacaoIMG);


		ltApps.setComponentPopupMenu(menuApps);



		treeObjetosGUI.setModel(null);

		AUTProcessoParalelo procLoaderApps = new AUTProcessoParalelo() {

			@Override
			protected void rotinasInicializacao() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void rotinasExecucao() {

				if(!ltDevices.isSelectionEmpty()) {
					ltApps.setModel(autAdbConfig.carregarAplicativosInstalados(ltDevices.getSelectedValue().toString()));
				}
				else {
					AUTAPIGUI.AUTFormularioUtils.exibirMensagem("SOFTTEK : ASSISTENTE DE CONFIGURAÇÃO", "SELECIONE O DISPOSITIVO QUE DESEJA VISUALIZAR OS APPS INSTALADOS!!!");
				}

			}

			@Override
			protected void rotinasFinalizacao() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void rotinasMonitoramentoProcesso() {
				// TODO Auto-generated method stub

			}

		};

		mnLoaderApps.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				procLoaderApps.executarProcesso();
			}
		});

		AUTProcessoParalelo procLoaderImg = new AUTProcessoParalelo() {
			public AUTDispositivoConfiguracao autAdbConfig = new AUTDispositivoConfiguracao();	
			public AUTAndroidInterface adbObjectInterface = autAdbConfig.autAdbInterfaceFactory();
			public AUTAndroidInterface.AUTAndroidObject autAdbObject = adbObjectInterface.autFactoryAdbObject();
			
			@Override
			protected void rotinasInicializacao() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void rotinasExecucao() {
				System.out.println("INFO: CARREGANDO IMAGEM DA TELA ATUAL DO DISPOSITIVO CONECTADO");	
				
				if(!ltDevices.isSelectionEmpty())
				{
					containerImagens.add(autAdbObject.carregarImagem(ltDevices.getSelectedValue().toString()));	
					
					try {
						javax.swing.JScrollPane scrItem = new javax.swing.JScrollPane(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
								javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

						
						painelImagemMobile.setBackground(java.awt.Color.WHITE);					
						painelImagemMobile.setViewportView(containerImagens.get(posImg));
						//painelImagemMobile.setViewportView(scrItem);

						posImg++;

					}
					catch(java.lang.Exception e) {

						System.out.println("INFO : ERRO AO CARREGAR IMAGEM DO SISTEMA");

						System.out.println(e.getMessage());

						e.printStackTrace();
					}					
				}
				else {
					AUTAPIGUI.AUTFormularioUtils.exibirMensagem("ASSISTENTE DE CONFIGURAÇÃO", "SELECIONE O DISPOSITIVO PARA CAPTURAR DE TELA");
				}
				
			}

			@Override
			protected void rotinasFinalizacao() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void rotinasMonitoramentoProcesso() {
				// TODO Auto-generated method stub

			}

		};

		

		painelImagemMobile.setName("AUTIMG_CONTAINER001");
		mnCarregarTelaDevices.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				procLoaderImg.executarProcesso();	
			}
		});


		ltDevices.setComponentPopupMenu(menusControleDevices);

		ltTecladosConfig.setModel(ddLtTecladoConfig);

		pnControlesTeclado.add(btAplicarAlteracoes);

		pnControlesApps.setLayout(new java.awt.GridLayout());

		pnControlesDevices.setLayout(new java.awt.GridLayout());

		java.awt.GridBagConstraints configLayout = new java.awt.GridBagConstraints();

		formMob.getContentPane().setBackground(java.awt.Color.WHITE);
		formMob.setSize(formPrincipal.getSize());

		formMob.setLayout(new java.awt.GridBagLayout());

		formMob.setLocationRelativeTo(formPrincipal);

		AUTProcessoParalelo autCarregarGUI = new AUTProcessoParalelo() {

			@Override
			protected void rotinasInicializacao() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void rotinasExecucao() {

				System.out.println("INFO : CARREGANDO ESTRUTURA DE OBJETOS GUI : TELA ATUAL");

				
				if(!ltDevices.isSelectionEmpty()) {
					
					autAdbObject.carregarArvoreObjetosXML(autAdbObject.carregarXMLConfiguracao(ltDevices.getSelectedValue().toString()), treeObjetosGUI);
				}
				else {
					AUTAPIGUI.AUTFormularioUtils.exibirMensagem("SOFTTEK: ASSISTENTE DE CONFIGURAÇÃO", "SELECIONE UM DOS DISPOSITIVOS CONECTADOS PARA EXECUTAR ESSE COMANDO!!!!");
				}
				
			}

			@Override
			protected void rotinasFinalizacao() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void rotinasMonitoramentoProcesso() {
				// TODO Auto-generated method stub

			}

		};


		javax.swing.JPopupMenu menuObjetosGUI = new javax.swing.JPopupMenu();
		javax.swing.JMenuItem mnCarregarObjetos = menuObjetosGUI.add("Carregar Objetos : Tela Atual");

		treeObjetosGUI.setComponentPopupMenu(menuObjetosGUI);	

		treeObjetosGUI.setBackground(java.awt.Color.WHITE);

		mnCarregarObjetos.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("INFO : INICIANDO CARREGAMENTO DE ELEMENTOS GRÁFICOS");
				autCarregarGUI.executarProcesso();
			}
		});




		java.awt.GridBagConstraints configOps = new java.awt.GridBagConstraints();
		barraFerramentas.setLayout(new java.awt.GridBagLayout());		
		barraFerramentas.setBackground(java.awt.Color.WHITE);

		javax.swing.JCheckBox chkTela = new javax.swing.JCheckBox("Tela Visualização");
		configOps.gridx = 0;
		configOps.gridy = 0;
		configOps.weightx = 1;
		configOps.fill = configOps.HORIZONTAL;
		configOps.insets = AUTAPIGUI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(10);
		barraFerramentas.add(chkTela,configOps);	

		chkTela.setSelected(true);

		chkTela.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				javax.swing.JCheckBox chk = (javax.swing.JCheckBox)e.getSource();

				if(chk.isSelected()) {
					painelImagemMobile.setVisible(true);
				}
				else {
					painelImagemMobile.setVisible(false);
				}

			}

		});

		configLayout.gridx = 0;
		configLayout.gridy = 0;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;

		formMob.add(barraFerramentas,configLayout);	

		configLayout.gridx = 0;
		configLayout.gridy = 1;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.gridheight = 5;
		configLayout.fill = configLayout.BOTH;

		//scrCapImgs.setSize(new java.awt.Dimension(300,400));

		formMob.add(painelImagemMobile,configLayout);



		java.awt.GridBagConstraints configPainelGerenciamento = new java.awt.GridBagConstraints();
		pnControlesGerenciamento.setLayout(new javax.swing.BoxLayout(pnControlesGerenciamento,javax.swing.BoxLayout.Y_AXIS));
		pnControlesGerenciamento.setBackground(java.awt.Color.WHITE);

		configPainelGerenciamento.insets = AUTAPIGUI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(5);
		configPainelGerenciamento.gridx = 0;
		configPainelGerenciamento.gridy = 0;		
		configPainelGerenciamento.weightx = 1;
		configPainelGerenciamento.weighty = 0;
		configPainelGerenciamento.anchor = configPainelGerenciamento.PAGE_START;
		configPainelGerenciamento.fill = configPainelGerenciamento.HORIZONTAL;	
		pnControlesGerenciamento.add(pnConfigParamInit);

		configPainelGerenciamento.gridx = 0;
		configPainelGerenciamento.gridy = 1;	
		configPainelGerenciamento.weightx = 1;
		configPainelGerenciamento.weighty = 0;
		configPainelGerenciamento.fill = configPainelGerenciamento.HORIZONTAL;	

		pnControlesGerenciamento.add(pnConfigOpcoesGerenciamento);


		configPainelGerenciamento.gridx = 0;
		configPainelGerenciamento.gridy = 2;	
		configPainelGerenciamento.weightx = 1;
		configPainelGerenciamento.weighty = 1;
		configPainelGerenciamento.gridheight = 3;
		configPainelGerenciamento.fill = configPainelGerenciamento.BOTH;	
		pnControlesGerenciamento.add(scrTabCapTextosVerif);

		configLayout.gridheight = 1;
		configLayout.gridx = 1;
		configLayout.gridy = 1;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.gridwidth = 2;
		configLayout.gridheight = 1;
		configLayout.fill = configLayout.BOTH;

		formMob.add(pnControlesGerenciamento,configLayout);




		configLayout.insets = AUTAPIGUI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(5);
		configLayout.gridx = 1;
		configLayout.gridy = 2;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.gridheight = 1;
		configLayout.gridwidth = 1;
		configLayout.fill = configLayout.BOTH;		
		formMob.add(scrDevices,configLayout);



		configLayout.gridx = 1;
		configLayout.gridy = 3;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		formMob.add(lbDevices,configLayout);		


		configLayout.gridx = 2;
		configLayout.gridy = 2;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
		formMob.add(scrApps,configLayout);


		configLayout.gridx = 2;
		configLayout.gridy = 3;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		formMob.add(lbApps,configLayout);


		configLayout.gridx = 1;
		configLayout.gridy = 4;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
		formMob.add(scrTecladosConfig,configLayout);


		configLayout.gridx = 1;
		configLayout.gridy = 5;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		formMob.add(lbTecladosConfig,configLayout);


		configLayout.gridx = 2;
		configLayout.gridy = 4;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
		formMob.add(scrTeclados,configLayout);

		configLayout.gridx = 2;
		configLayout.gridy = 5;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		formMob.add(lbObjetosGUI,configLayout);


		ltDevices.setModel(ddDevices);	

		javax.swing.JPopupMenu menuConfigTeclado = new javax.swing.JPopupMenu();
		javax.swing.JMenuItem mnAtualizarConfigTeclado = menuConfigTeclado.add("Atualizar Lista de Comandos");

		ltTecladosConfig.setComponentPopupMenu(menuConfigTeclado);

		AUTProcessoParalelo procLoaderData = new AUTProcessoParalelo() {

			@Override
			protected void rotinasInicializacao() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void rotinasExecucao() {
				if(ddLtTecladoConfig.size() > 0) {ddLtTecladoConfig.removeAllElements();}
				if(!ltDevices.isSelectionEmpty()) {
					for(Object obj: autAdbConfig.carregarConfiguracaoTeclado(ltDevices.getSelectedValue().toString())) {
						if(obj.toString().length() > 1 && !obj.toString().equals("character")) {
							ddLtTecladoConfig.addElement(obj);
						}
					}
				}
				else {
					AUTAPIGUI.AUTFormularioUtils.exibirMensagem("SOFTTEK: ASSISTENTE DE CONFIGURAÇÃO", "SELECIONE UM DISPOSITIVO PARA CARREGAR AS CONFIGURAÇÕES DE TECLADO");
				}

			}

			@Override
			protected void rotinasFinalizacao() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void rotinasMonitoramentoProcesso() {
				// TODO Auto-generated method stub

			}

		};

		mnAtualizarConfigTeclado.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				procLoaderData.executarProcesso();
			}
		});


		/**
		 * 
		 * Tela de gerenciamento do dispositivo
		 * 
		 */
		mnGerencMobile.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(!ltDevices.isSelectionEmpty()) {
					AUTFormConsoleMobile formGer = new AUTFormConsoleMobile(ltDevices.getSelectedValue().toString(),formMob);
					
					
				}
				else {
					AUTAPIGUI.AUTFormularioUtils.exibirMensagem("MOBILE : ASSISTENTE DE CONFIGURAÇÃO", "SELECIONE O DISPOSITIVO QUE DESEJA GERENCIAR!!!!");
				}

			}
		});

		
		AUTProcessoParalelo procAtualizacaoDevices = new AUTProcessoParalelo() {

			@Override
			protected void rotinasInicializacao() {						
				System.out.println("INFO : TAREFA :  LISTAR DE DISPOSITIVOS CONECTADOS : INICIALIZANDO");	
			}

			@Override
			protected void rotinasExecucao() {						
				System.out.println("@@@RESET CONNECTION INIT: @@@@@@");
				autAdbConfig.resetConnections();				
				System.out.println("@@@RESET CONNECTION END: @@@@@@");

				ddDevices.removeAllElements();

				System.out.println("@@@LOADERDEVICES: LISTA DE DISPOSITIVOS CONECTADOS");
				System.out.println(AUT_ANDROID_CONFIGURACAO_IP.LIST_DISPOSITIVOS);

				String[] hosts = AUT_ANDROID_CONFIGURACAO_IP.LIST_DISPOSITIVOS.toString().split("\n");
				Integer portaConexao = 60001;						
				for(String hst : hosts) {
					String ip1 = String.format(AUT_ANDROID_CONFIGURACAO_IP.IP_IPV4_MULT_CONECT_DINAMICA.toString(),hst);
					ip1 = AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(ip1).toString();
					
					
					if(!ip1.trim().isEmpty()) {
						String expLocation = "inet \\d+.\\d+.\\d+.\\d+";	
						String expSplit = "inet ";
						java.util.regex.Pattern analise = java.util.regex.Pattern.compile(expLocation);
						java.util.regex.Matcher itemExp = analise.matcher(ip1);
						String ipTemp = "";
						while(itemExp.find()) {
							ipTemp = itemExp.group().split("\\s+")[1];
						}
						
						ip1 = ipTemp;
						
						System.out.println("INFO : PROCURANDO IP1");
						System.out.println(ip1);
						
						if(hst.contains("emulator")) {
							String port = hst.split("\\-")[1];
							Integer portCon = Integer.parseInt(port) + 1;
							try {
								//autAdbConfig.adicionarConexao(hst, ip1, portCon.toString());
							}
							catch(java.lang.Exception e) {
								
								System.out.println("INFOERRO: NAO FOI POSSIVEL HABILITAR O ACESSO WIFI");
								
								System.out.println(e.getMessage());
								
								e.printStackTrace();
							}
							ip1 = ip1.concat(":").concat(portCon.toString());
						}
						
						
						autAdbConfig.adicionarConexao(hst, ip1, portaConexao.toString());
						
						System.out.println("INFO ADB : CONFIGURANDO ESTRUTURA DE DADOS DA APLICAÇÃO");
						
						try {
							autAdbConfig.inicializarConfiguracoes(hst);
						}
						catch(java.lang.Exception e) {
							System.out.println("INFO ADB : NÃO FOI POSSIVEL CRIAR A ESTRUTURA DE PASTA ADEQUADAMENTE NO DISPOSITIVO REMOTO");
							System.out.println(hst);
							System.out.println(e.getMessage());
							e.printStackTrace();
						}
						System.out.println("INFO ADB : CONFIG ESTRUTURA DE DADOS DA APLICAÇÃO : FINALIZADA");
						
						
						ddDevices.addElement(ip1.concat(":").concat(portaConexao.toString()));
						ddDevices.addElement(hst);
						
						portaConexao+=2;
						
					}
					else { 
						String ip2 = String.format(AUT_ANDROID_CONFIGURACAO_IP.IP_IPV4_MULT_CONECT_DINAMICA_2.toString(),hst);
						ip2 = AUTAPIGUI.AUTProcessoExternoUtils.executarProcesso(ip2).toString();
						if(!ip2.trim().isEmpty()) {
							System.out.println("INFO : PROCURANDO IP2");
							System.out.println(ip2);
							
							if(hst.contains("emulator")) {
								String port = hst.split("\\-")[1];
								Integer portCon = Integer.parseInt(port) + 1;
								//autAdbConfig.adicionarConexao(hst, ip2, portCon.toString());
								ip2 = ip2.concat(":").concat(portCon.toString());
							}
							
							
							autAdbConfig.adicionarConexao(hst, ip2, portaConexao.toString());
							
							ddDevices.addElement(ip2.concat(":").concat(portaConexao.toString()));
							ddDevices.addElement(hst);
							
							portaConexao+=2;
						}
						else {
							System.out.println("INFO : ERRO IP NAO CARREGADO");
							ddDevices.addElement("ERRODEVICE".concat(hst));
						}
					}
					
				}
				
			}

			@Override
			protected void rotinasFinalizacao() {

				System.out.println("INFO : TAREFA :  LISTAR DE DISPOSITIVOS CONECTADOS : CONCLUÍDO");

			}

			@Override
			protected void rotinasMonitoramentoProcesso() {
				// TODO Auto-generated method stub

			}

		};


		mnAtualizarListaDevices.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				procAtualizacaoDevices.executarProcesso();
			}
		});

		
		chkHabTCPIP.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chkHabTCPIP.isEnabled()) {		
					HabilitarResetTCPIP = true;										
				}							
			}
		});
		
		
		mnExcluirItemListaDevices.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!ltDevices.isSelectionEmpty()) {
					String item = ltDevices.getSelectedValue().toString();
					ddDevices.remove(ltDevices.getSelectedIndex());
					
					AUTAPIGUI.AUTFormularioUtils.exibirMensagem("SOFTTEK: ASSISTENTE DE CONFIGURAÇÃO", String.format("DISPOSITIVO (%s) EXCLUÍDO DA LISTA COM SUCESSO!!!",item));
				}
				else {
					AUTAPIGUI.AUTFormularioUtils.exibirMensagem("SOFTTEK: ASSISTENTE DE CONFIGURAÇÃO", "ESCOLHA UM DOS DISPOSITIVOS CONECTADOS AO COMPUTADOR PARA EXCLUSÃO");
				}
			}				
		});

		txtConfigPercentReducaoIMG.addKeyListener(new java.awt.event.KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent obj) {
				try {
					System.out.println("INFO : ESCALA DA IMAGEM MOBILE ALTERADA:");
					System.out.println(obj.getKeyCode());

					if(obj.getKeyCode()==10 || obj.getKeyCode()==13) {
						try {
							javax.swing.JTextField txt = (javax.swing.JTextField)obj.getSource();
							autAdbConfig.ANDROID_PERCENTUAL_REDUCAO_CAP_IMG = Integer.parseInt(txt.getText().trim());
							System.out.println(txt.getText());
							procLoaderImg.executarProcesso();
						}
						catch(java.lang.Exception e) {

						}
					}					

				}
				catch(java.lang.Exception e) {

					System.out.println("ERRORINFO : ERRO NA DEFINICAO (PERCENTUAL DE REDUCAO DA IMAGEM ADB)");

					System.out.println(e.getMessage());

					e.printStackTrace();

				}

			}

			@Override
			public void keyTyped(KeyEvent obj) {

			}
		});


		txtTempoSincronizacaoIMG.addKeyListener(new java.awt.event.KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent obj) {
				try {
					System.out.println("INFO : TEMPO DE SINCRONIZACAO DA CAPTURA DE TELA ALTERADO:");
					System.out.println(obj.getKeyCode());

					if(obj.getKeyCode()==10 || obj.getKeyCode()==13) {
						try {

							javax.swing.JTextField txt = (javax.swing.JTextField)obj.getSource();
							autAdbConfig.ANDROID_TEMPO_SINCRONIZACAO_CAP_IMG = Integer.parseInt(txt.getText().trim());
							System.out.println(txt.getText());

						}
						catch(java.lang.Exception e) {

						}
					}					

				}
				catch(java.lang.Exception e) {

					System.out.println("ERRORINFO : ERRO NA DEFINICAO (PERCENTUAL DE REDUCAO DA IMAGEM ADB)");

					System.out.println(e.getMessage());

					e.printStackTrace();

				}

			}

			@Override
			public void keyTyped(KeyEvent obj) {

			}
		});


		AUTProcessoParalelo procCapTelaAut = new AUTProcessoParalelo() {

			@Override
			protected void rotinasInicializacao() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void rotinasExecucao() {
				boolean continuar = true;
				System.out.println("INFO : INICIANDO PROCESSO DE CAPTURA DE TELA EM MODO AUTOMATICO");

				continuar = autAdbConfig.ANDROID_CAP_AUTOMATIC_IMG;

				while(continuar) {

					procLoaderImg.executarProcesso();					

					try {
						java.lang.Thread.sleep(autAdbConfig.ANDROID_TEMPO_SINCRONIZACAO_CAP_IMG * 1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}

					continuar = autAdbConfig.ANDROID_CAP_AUTOMATIC_IMG;
				}

				System.out.println("INFO : FINALIZANDO PROCESSO DE CAPTURA DE TELA");
			}

			@Override
			protected void rotinasFinalizacao() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void rotinasMonitoramentoProcesso() {
				// TODO Auto-generated method stub

			}

		};

		
		
		chkHabCapAutomatica.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				javax.swing.JCheckBox chk = (javax.swing.JCheckBox)e.getSource();

				if(chk.isEnabled()) {
					autAdbConfig.ANDROID_CAP_AUTOMATIC_IMG = true;
					procCapTelaAut.executarProcesso();
				}
				else {
					autAdbConfig.ANDROID_CAP_AUTOMATIC_IMG = false;
					try {
						procCapTelaAut.pararExecucao();
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}

				}	
			}
		});	


		AUTProcessoParalelo procSincDadosGUI = new AUTProcessoParalelo() {
			boolean continuarSinc = true;
			
			@Override
			protected void rotinasInicializacao() {				
				System.out.println("INFO : FUNÇÃO PARA SINCRONIZAÇÃO DE DADOS DA APLICAÇÃO HABILITADA");				
			}

			@Override
			protected void rotinasExecucao() {				
				System.out.println("INFO : LISTANDO DISPOSITIVOS CONECTADOS NO SISTEMA");
				System.out.println("CONECTANDO AO SERVIDOR ADB :");
				
				continuarSinc = chkHabSincAutomatica.isEnabled();
				
				
				while(continuarSinc) {					
					System.out.println("INFO-SINC-GUI: FAZENDO VARREDURA DO SISTEMA");
					java.util.Enumeration<String> devs = ddDevices.elements();
					String[] itensDevsInput = configIP.LIST_DISPOSITIVOS.toString().split("\n");
					java.util.Arrays.sort(itensDevsInput);
					
					while(devs.hasMoreElements()) {						
						String hst = devs.nextElement();
						
						System.out.println("INFO SINC DADOS: FAZENDO VARREDURA DO SISTEMA (PORTAS USB)");
						
						if(java.util.Arrays.binarySearch(itensDevsInput, hst) < 0 ) {
							
							System.out.println("INFO ADB DEV SINC SCANNER: EXCLUINDO DISPOSITIVO DA LISTA");
							
							ddDevices.removeElement(hst);
						}
						
						for(String itemDev : itensDevsInput) {
							if(!ddDevices.contains(itemDev)) {
								System.out.println(String.format("INFO ADB DEV SINC SCANNER: INCLUINDO DISPOSITIVO NA LISTA : %s",itemDev));
								ddDevices.addElement(itemDev);
							}		
						}											
					}
					
					System.out.println(AUT_ANDROID_CONFIGURACAO_IP.LIST_DISPOSITIVOS);
					
					try {
						java.lang.Thread.sleep(15000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					continuarSinc = chkHabSincAutomatica.isEnabled();
				}
				
			}

			@Override
			protected void rotinasFinalizacao() {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void rotinasMonitoramentoProcesso() {
							
			}
			
			
		};
		chkHabSincAutomatica.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				procSincDadosGUI.executarProcesso();
				
			}
		});

		chkHabCapAutComValidacao.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				javax.swing.JCheckBox chk = (javax.swing.JCheckBox)e.getSource();

				if(chk.isEnabled()) {
					autAdbConfig.ANDROID_CAP_AUTOMATIC_IMG = true;
					procCapTelaAut.executarProcesso();
				}
				else {
					autAdbConfig.ANDROID_CAP_AUTOMATIC_IMG = false;
					try {
						procCapTelaAut.pararExecucao();
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}

				}	
			}
		});	


		formMob.setVisible(true);
	}


	/**
	 * 
	 * CONSTRUTOR DA CLASSE
	 * 
	 * @param form - FORMULÁRIO SOBRE O QUAL SERÁ EXIBIDO O APP
	 */
	public AUTFormMobileConector(javax.swing.JDialog form) {
		formPrincipal = form;
		System.out.println("INFO : INICIALIZANDO GERENCIADOR DE DISPOSITIVOS MOBILE");
		try {
			configGUI();
		}
		catch(java.lang.Exception e) {
			System.out.println("INFOERROR: CONFIGURACAO DE INICIALIZACAO DO FRAMEWORK");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}


	/**
	 * 
	 * CONSTRUTOR
	 * 
	 */
	public AUTFormMobileConector() {
		try {
			configGUI();
		}
		catch(java.lang.Exception e) {
			System.out.println("INFOERROR: CONFIGURACAO DE INICIALIZACAO DO FRAMEWORK");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
