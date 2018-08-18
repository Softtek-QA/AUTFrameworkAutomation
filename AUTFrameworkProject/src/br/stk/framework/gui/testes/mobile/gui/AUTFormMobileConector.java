package br.stk.framework.gui.testes.mobile.gui;

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

import br.stk.framework.api.AUTAPI;
import br.stk.framework.api.AUTAPI.*;
import br.stk.framework.api.AUTAPI.AUTFormularioUtils.AUT_TIPO_MSG_USUARIO;
import br.stk.framework.api.runtime.AUTRuntimeConfiguration;
import br.stk.framework.gui.testes.mobile.gui.AUTFormMobileConector.AUTDispositivoConfiguracao.AUT_ANDROID_CONFIGURACAO_IP;
import br.stk.framework.gui.testes.mobile.gui.AUTFormMobileTestStepsAutomation.AUTDadosConexao; 


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
	public static java.util.HashMap<String,AUTGUIParallelProcess> ltFunctions = new java.util.HashMap<String,AUTGUIParallelProcess>();
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
		public static String ANDROID_DEVICE_CONFIG_CUSTOM = "%s";
		public static Integer ANDROID_ID_IMG = 0;
		public static Integer ANDROID_ID_IMG_CUSTOM = 0;
		public static String HOST_NOME = "net.hostname";
		public static  String VERSAO = "selinux.policy_version";
		public static  String OPERADORA_PRINCIPAL = "gsm.operator.alpha";
		public static  String PAIS = "ro.csc.countryiso_code";
		public static  String VERSAO_BUILD_SDK = "ro.build.version.sdk";
		public static  String VERSAO_BUILD_INCREMENTAL = "ro.build.version.incremental";
		public static  String VERSAO_BUILD_RELEASE = "ro.build.version.release";
		public static  String VERSAO_BUILD_SECURITY_PATH = "ro.build.version.security_patch";
		public static  String CONEXAO_USB = "sys.usb.state";
		public static  String FUSO_HORARIO = "persist.sys.timezone";
		public static  String NOME_PRODUTO = "ro.product.name";
		public static  String FABRICANTE = "ro.product.manufacturer";
		public static  String MODELO_DISPOSITIVO = "ro.product.model";
		public static  String ID = "ro.serialno";
		public static  String DNS1_WLAN = "dhcp.wlan0.dns1";
		public static  String DNS2_WLAN = "dhcp.wlan0.dns2";
		public static  String DNS3_WLAN = "dhcp.wlan0.dns3";
		public static  String DNS4_WLAN = "dhcp.wlan0.dns4";
		public static  String DOMINIO_WLAN = "dhcp.wlan0.domain";
		public static  String GATEWAY_WLAN = "dhcp.wlan0.gateway";
		public static  String MASCARA_REDE_WLAN = "dhcp.wlan0.mask";
		public static  String IP = "dhcp.wlan0.ipaddress";
		public static Integer SERIAL_SCREEN = 1;
		public static String ESTACAO_LOCAL_PASTA_RAIZ = "C:\\Repositorios\\AUTOMACAO\\";
		public static String ESTACAO_LOCAL_EVIDENCIAS_RESULT = ESTACAO_LOCAL_PASTA_RAIZ.concat("RESULTADOS EXECUCAO\\");
		public static String ESTACAO_LOCAL_VDS = ESTACAO_LOCAL_EVIDENCIAS_RESULT.concat("VIDEOS\\");
		public static String ESTACAO_LOCAL_EVIDENCIAS_IMGS = ESTACAO_LOCAL_EVIDENCIAS_RESULT.concat("IMAGENS\\");
		public static String ESTACAO_LOCAL_EVIDENCIAS_WEB = ESTACAO_LOCAL_EVIDENCIAS_RESULT.concat("PAGINA WEB\\");
		public static String ESTACAO_LOCAL_TEMP_FILES = ESTACAO_LOCAL_EVIDENCIAS_RESULT.concat("DADOS\\");
		public static String ESTACAO_LOCAL_ESTRUTURA_ARQUIVOS = String.format("cmd /c mkdir \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\"",ESTACAO_LOCAL_PASTA_RAIZ,ESTACAO_LOCAL_EVIDENCIAS_RESULT,ESTACAO_LOCAL_VDS,ESTACAO_LOCAL_EVIDENCIAS_IMGS,ESTACAO_LOCAL_EVIDENCIAS_WEB,ESTACAO_LOCAL_TEMP_FILES);		
		public static String ESTACAO_LOCAL_TEMP_FILE_IMG_AVT = ESTACAO_LOCAL_TEMP_FILES.concat("\\AUTIMGATV00%s.png");
		public static String ESTACAO_LOCAL_TEMP_FILE_IMG_AVT_CUSTOM = ESTACAO_LOCAL_TEMP_FILES.concat("DADOS\\AUTIMGGER00%s.png");
		public static String ESTACAO_LOCAL_TEMP_FILE_IMG_AVT_CUSTOM_RTIME = ESTACAO_LOCAL_TEMP_FILES.concat("DADOS\\AUTIMGMOB_%s-DATAEHORA-%s.png");
		public static Integer ANDROID_TEMPO_SINCRONIZACAO_CAP_IMG = 5;
		public static Integer ANDROID_PERCENTUAL_REDUCAO_CAP_IMG = 50;
		public static boolean ANDROID_CAP_AUTOMATIC_IMG = false;
		public static String ANDROID_PASTA_RAIZ = "/sdcard/aut_arquivos";
		public static String ANDROID_PASTA_VIDEO = ANDROID_PASTA_RAIZ.concat("/videos");
		public static String ANDROID_PASTA_TEMP_FILES = ANDROID_PASTA_RAIZ.concat("/Dados/");
		public static String ANDROID_PASTA_ADB_XML_GUI_OBJECT = ANDROID_PASTA_TEMP_FILES.concat("GUIOBJ.xml");
		public static String ANDROID_PASTA_ADB_IMG_ATV = ANDROID_PASTA_TEMP_FILES.concat("AUTIMGATV001.png");
		public static String ANDROID_PASTA_ADB_IMG_ATV_CUSTOM = ANDROID_PASTA_TEMP_FILES.concat("AUTIMGGER001.png");
		public static String ANDROID_PASTA_LOCAL_XML_GUI_OBJECT = ESTACAO_LOCAL_TEMP_FILES.concat("GUIOBJ.xml");		
		public static String ANDROID_ARQUIVO_VIDEO_PADRAO = ANDROID_PASTA_VIDEO.concat("AUT_VIDEO_001.mp4");
		public static String ANDROID_ESTRUTURA_ARQUIVOS = String.format("mkdir \"%s\" \"%s\" \"%s\"",ANDROID_PASTA_RAIZ.toString(),ANDROID_PASTA_VIDEO.toString(),ANDROID_PASTA_TEMP_FILES.toString());
		public static String ANDROID_ARQUIVO_ATUAL_CORRENTE = ANDROID_PASTA_RAIZ.concat("/AUT_IMG_ATUAL_00%s.png");
		public static String ANDROID_ARQUIVO_ESPERADO_CORRENTE = ANDROID_PASTA_RAIZ.concat("/AUT_IMG_ESPERADO_00%s.png");
		public static String ANDROID_SHELL_EXEC_GET_FILE_GUI = String.format("adb shell uiautomator dump --compress -f \"%s\"",ANDROID_PASTA_ADB_XML_GUI_OBJECT);
		public static String ANDROID_SHELL_EXEC_GET_FILE_GUI_CUSTOM = String.format("adb -s %s shell uiautomator dump --compress -f \"%s\"",ANDROID_DEVICE_CONFIG_CUSTOM,ANDROID_PASTA_ADB_XML_GUI_OBJECT);
		public static String ANDROID_SHELL_COPY_FILE_GUI = String.format("adb pull \"%s\" \"%s\"",ANDROID_PASTA_ADB_XML_GUI_OBJECT,ANDROID_PASTA_LOCAL_XML_GUI_OBJECT);
		public static String ANDROID_SHELL_COPY_FILE_GUI_CUSTOM = String.format("adb -s %s pull \"%s\" \"%s\"",ANDROID_DEVICE_CONFIG_CUSTOM,ANDROID_PASTA_ADB_XML_GUI_OBJECT,ANDROID_PASTA_LOCAL_XML_GUI_OBJECT);
		public static String SCREEN_SCRIPT_ST_ATUAL_CAP = ANDROID_PASTA_RAIZ.concat(";screencap \"").concat(ANDROID_ARQUIVO_ATUAL_CORRENTE).concat("\"");
		public static String SCREEN_SCRIPT_ST_ESPERADO_CAP = ANDROID_PASTA_RAIZ.concat(";screencap \"").concat(ANDROID_ARQUIVO_ESPERADO_CORRENTE).concat("\"");
		public static String RECORD_VIDEO_CAP = " screenrecord \"".concat(ANDROID_ARQUIVO_VIDEO_PADRAO).concat("\"");		
		private static Integer portConexaoMin = 60100;
		private static Integer portConexaoMax = 60300;
		protected static java.util.HashMap<Integer,AUTDadosConexao> AUTDadosConexoesAtivas = new java.util.HashMap<Integer,AUTDadosConexao>();
		protected static java.util.List<Integer> configuracaoConexaoPortas = new java.util.ArrayList<Integer>();
		protected static java.util.HashMap<String,String> AUTDadosTransferenciaArquivos = new java.util.HashMap<String,String>();
		protected static java.util.HashMap<Integer,String[]> AUT_TECLAS_CONFIGURACAO = new java.util.HashMap<Integer,String[]>();				

		public static enum AUT_ANDROID_CONFIGURACAO_IP{
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

						String[] devices = AUTAPI.AUTProcessoExternoUtils.executarProcesso("adb devices").toString().split("\n");

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
					return String.format("adb -s %s shell getprop dhcp.wlan0.ipaddress", IP);
				}
				case IP_POR_DHCP:{					
					cmdAndroid =  String.format("adb shell getprop ", IP);
					ipv4 = true;
					break;
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

					return AUTAPI.AUTProcessoExternoUtils.executarProcesso(cmdAndroid).toString();
				}
				case ID_DISPOSITIVO:{

					cmdAndroid = String.format("adb shell getprop %s",ID);

					return AUTAPI.AUTProcessoExternoUtils.executarProcesso(cmdAndroid).toString();
				}
				default:{

					cmdAndroid =  String.format("adb shell getprop ", IP);

					break;					
				}
				}

				cmdOutput = "";

				if(ipv4) {
					cmdOutput = AUTAPI.AUTProcessoExternoUtils.executarProcesso(cmdAndroid).toString();
				}
				else if(ipRede) {
					cmdOutput = formatarIPRede(AUTAPI.AUTProcessoExternoUtils.executarProcesso(cmdAndroid).toString()).toString();
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
		public static void configurarPortasConexao() {
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
		public static void adicionarConexao() {

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
					AUTDispositivoConfiguracao.conectarTCPIP(AUTDadosConexoesAtivas.get(portaCon).IP, AUTDadosConexoesAtivas.get(portaCon).PORTA);
					System.out.println("INFO : CONFIGURACAO TCP IP REALIZADA COM SUCESSO");
					System.out.println(AUTDadosConexoesAtivas);
				}

			}
			else {
				AUTAPI.AUTFormularioUtils.exibirMensagem("SOFTTEK QA - ASSISTENTE DE CONEXÃO", "O dispositivo selecionado não possui um IP de conexão válido : \n" + 
						"\b1º - Habilitar conexão WIFI\n"
						+ "\b2º - Verifique nas opções avançadas de configuração de rede se um IP foi atribuido a conexão\n"
						+ "\b3º - O computador e o dispositivo devem está conectados na mesma rede\n"
						+ "\b4º - Se ainda sim falhar, reinicie o android e tente novamente"

						);

			}
		}

		public static void adicionarConexao(String IPConexao) {

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
					AUTDispositivoConfiguracao.conectarTCPIP(AUTDadosConexoesAtivas.get(portaCon).IP, AUTDadosConexoesAtivas.get(portaCon).PORTA);
					System.out.println("INFO : CONFIGURACAO TCP IP REALIZADA COM SUCESSO");
					System.out.println(AUTDadosConexoesAtivas);
				}

			}
			else {
				AUTAPI.AUTFormularioUtils.exibirMensagem("SOFTTEK QA - ASSISTENTE DE CONEXÃO", "O dispositivo selecionado não possui um IP de conexão válido : \n" + 
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
						AUTAPI.AUTProcessoExternoUtils.executarProcesso(cmd);
					}
					
					totTentativas = totTentativasPadrao;
					
				}
				else {
					for(contTentativas = 1;contTentativas <= totTentativas; contTentativas++) {
						System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(cmd));
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
		
		public static void adicionarConexao(String nomeDispositivo,String IPConexao,String portaConexao) {

			String ip = IPConexao.trim();
			
			Integer portaCon = Integer.parseInt(portaConexao.trim());
			
			AUTDadosConexoesAtivas.put(portaCon, new AUTDadosConexao());					
			AUTDadosConexoesAtivas.get(portaCon).ID = AUT_ANDROID_CONFIGURACAO_IP.ID_DISPOSITIVO.toString();
			AUTDadosConexoesAtivas.get(portaCon).IP = ip;
			AUTDadosConexoesAtivas.get(portaCon).PORTA = portaCon.toString();
			AUTDadosConexoesAtivas.get(portaCon).NOME_DISPOSITIVO = nomeDispositivo;
			
			AUTDispositivoConfiguracao.conectarTCPIP(AUTDadosConexoesAtivas.get(portaCon).NOME_DISPOSITIVO,AUTDadosConexoesAtivas.get(portaCon).IP, AUTDadosConexoesAtivas.get(portaCon).PORTA);
			
			
			
			System.out.println("INFO : CONFIGURACAO TCP IP REALIZADA COM SUCESSO");
			System.out.println(AUTDadosConexoesAtivas);
						
		}

		public static void conectarTCPIP(String IP,String porta) {
			porta = porta.trim();
			IP = IP.trim();

			System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso("adb -d tcpip ".concat(porta)));
			System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso("adb -d connect ".concat(IP).concat(":").concat(porta)));
			System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso("adb -d connect ".concat(IP).concat(":").concat(porta)));

			AUTFormularioUtils.exibirMensagem("Softtek - QA : Assistente de Configuração", "COMANDOS DE CONFIGURAÇÃO TCP IP EXECUTADOS:\n"
					+ "\b* DESCONECT O DISPOSITIVO DA PORTA USB E FAÇA O TESTE DE CONEXÃO");
		}

		public static void conectarTCPIP(String nomeDispositivo,String IP,String porta) {
			
			porta = porta.trim();
			IP = IP.trim();
			nomeDispositivo = nomeDispositivo.trim();
			
			System.out.println("@@@@CONNECTION-START:");
			System.out.println(nomeDispositivo);
			System.out.println(IP);
			System.out.println(porta);
			
			System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s tcpip %s",nomeDispositivo,porta)));
			System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb connect %s:%s",IP,porta)));

		}		
		

		public static void disconectarTCPIP(String IP,Integer porta) {
			System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso("adb -d disconnect ".concat(IP).concat(":").concat(porta.toString())));
			System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso("adb -d usb"));
			AUTFormularioUtils.exibirMensagem("Softtek - QA : Assistente de Configuração", "COMANDOS DE CONFIGURAÇÃO TCP IP EXECUTADOS:\n" +
					"\b* DESCONECT O DISPOSITIVO DA PORTA USB E FAÇA O TESTE DE CONEXÃO");
		}

		public static javax.swing.DefaultListModel carregarAplicativosInstalados(){
			javax.swing.DefaultListModel ddOut = new javax.swing.DefaultListModel();
			java.lang.StringBuffer appsInst = AUTAPI.AUTProcessoExternoUtils.executarProcesso("adb shell pm list packages");
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
			java.lang.StringBuffer appsInst = AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s shell pm list packages",nomeDispositivo));
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
		public static void fecharPortasComunicao() {
			System.out.println("INFO: LIBERANDO PORTAS DE COMUNICAÇÃO");			
			for(Integer porta : AUTDispositivoConfiguracao.AUTDadosConexoesAtivas.keySet()) {
				AUTDadosConexao con = AUTDadosConexoesAtivas.get(porta);
				System.out.println(String.format("INFO : IP : %s : PORTA : %s : ID :%s",con.IP,con.PORTA,con.ID));
			}


			System.out.println("****** INFO : RECONFIGURANDO PORTAS DE CONEXAO ******");

			configurarPortasConexao();
		}



		/**
		 * 
		 * inicialização do sistema
		 * 
		 */
		public static void inicializarConfiguracoes() {			
			/**
			 * 
			 * Configuração do dicionário de dados para caracteres de código android
			 * 
			 */

			AUT_TECLAS_CONFIGURACAO.put(8, new String[] {"DEL","DELETAR / BACKSPACE"});
			AUT_TECLAS_CONFIGURACAO.put(9, new String[] {"TAB","TABULACAO"});
			AUT_TECLAS_CONFIGURACAO.put(10, new String[] {"ENTER","CONFIRMAR / ENTRA"});
			//AUT_TECLAS_CONFIGURACAO.put(16, new String[] {"~","SHIFT : MODIFICADOR"});
			AUT_TECLAS_CONFIGURACAO.put(18, new String[] {"ENTER","CONFIRMAR / ENTRA"});
			AUT_TECLAS_CONFIGURACAO.put(27, new String[] {"ESCAPE","VOLTAR PARA TELA ANTERIOR"});
			AUT_TECLAS_CONFIGURACAO.put(32, new String[] {"SPACE","ESPACO"});
			AUT_TECLAS_CONFIGURACAO.put(33, new String[] {"PAGE_UP","NAVEGAR PARA PAGINA DE CIMA"});
			AUT_TECLAS_CONFIGURACAO.put(34, new String[] {"PAGE_DOWN","NAVEGAR PARA PAGINA DE BAIXO"});
			AUT_TECLAS_CONFIGURACAO.put(35, new String[] {"MOVE_END","NAVEGAR PARA O FIM DA PAGINA"});
			AUT_TECLAS_CONFIGURACAO.put(36, new String[] {"MOVE_HOME","NAVEGAR PARA O INICIO DA PAGINA"});
			AUT_TECLAS_CONFIGURACAO.put(37, new String[] {"DPAD_LEFT","TECLA DIRECIONAL ESQUERDA"});
			AUT_TECLAS_CONFIGURACAO.put(38, new String[] {"DPAD_UP","TECLA DIRECIONAL PARA CIMA"});
			AUT_TECLAS_CONFIGURACAO.put(39, new String[] {"DPAD_RIGHT","TECLA DIRECIONAL PARA DIREITA"});
			AUT_TECLAS_CONFIGURACAO.put(40, new String[] {"DPAD_DOWN","TECLA DIRECIONAL PARA BAIXO"});	




			/**
			 * 
			 * CONFIGURAÇÃO DA ESTRUTURA DE ARQUIVOS UTILIZADA DURANTE O PROCESSO DE EXECUÇÃO DOS TESTES
			 * 
			 * 
			 */
			AUTAPI.AUTProcessoExternoUtils.executarProcesso("adb shell ".concat(ANDROID_ESTRUTURA_ARQUIVOS));

			AUTAPI.AUTProcessoExternoUtils.executarProcesso(AUTDispositivoConfiguracao.ESTACAO_LOCAL_ESTRUTURA_ARQUIVOS);

			configurarPortasConexao();
		}


		public static void inicializarConfiguracoes(String nomeDispositivo) {	
			int totTentativas = 2;
			for(int tentativas = 0;tentativas < totTentativas;tentativas++) {
				AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s shell %s;\n",nomeDispositivo,ANDROID_ESTRUTURA_ARQUIVOS));
			}

			AUTAPI.AUTProcessoExternoUtils.executarProcesso(AUTDispositivoConfiguracao.ESTACAO_LOCAL_ESTRUTURA_ARQUIVOS);

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

			String[] teclados = AUTAPI.AUTProcessoExternoUtils.executarProcesso("adb shell cd system/usr/keychars;ls;").toString().split("\n");
			String[] tecladosLayout	 = AUTAPI.AUTProcessoExternoUtils.executarProcesso("adb shell cd system/usr/keylayout;ls;").toString().split("\n");

			for(String configItem : teclados) {
				if(!configItem.trim().isEmpty()) {
					System.out.println("INFO : CONFIGURACAO ENCONTRADA : ".concat(configItem));
					//String tecladoConfig = AUTAPI.AUTProcessoExternoUtils.executarProcesso("adb shell cat system/usr/keychars/".concat(configItem)).toString();
					System.out.println("INFO : ARQUIVO : ".concat(configItem));
					analiseExp = padrao.matcher(AUTAPI.AUTProcessoExternoUtils.executarProcesso("adb shell cat system/usr/keychars/".concat(configItem)));

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

			String[] teclados = AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s shell cd system/usr/keychars;ls;",nomeDispositivo)).toString().split("\n");
			String[] tecladosLayout	 = AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s shell cd system/usr/keylayout;ls;",nomeDispositivo)).toString().split("\n");

			for(String configItem : teclados) {
				if(!configItem.trim().isEmpty()) {
					System.out.println("INFO : CONFIGURACAO ENCONTRADA : ".concat(configItem));
					System.out.println("INFO : ARQUIVO : ".concat(configItem));
					analiseExp = padrao.matcher(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s shell cat system/usr/keychars/".concat(configItem),nomeDispositivo)));

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


	}

	/**
	 * 
	 * INTERFACE DE COMANDOS ANDROID
	 * 
	 */
	public static class AUTAndroidInterface{

		public static class AUTAndroidObject{

			/**
			 * Carrega o tipo do elemento que está sendo exibido na interface gráfica do sistema
			 * 
			 * @param item - Objeto XML passado como parametro (node)
			 * @return boolean - True em caso de sucesso false caso contrário
			 */
			public static String carregarTipoElementoGUI(org.w3c.dom.Node item) {						
				return item.getAttributes().getNamedItemNS(item.getNamespaceURI(), "class").toString();
			}


			public static String carregarLabelElementoGUI(org.w3c.dom.Node item) {						
				return item.getAttributes().getNamedItemNS(item.getNamespaceURI(), "text").getNodeValue();
			}


			public static AUTPainelImagem carregarImagem(Integer escalaReducao) {

				AUTPainelImagem img  = null;

				AUTDispositivoConfiguracao.ANDROID_ID_IMG++;

				String fileImg  = String.format(AUTDispositivoConfiguracao.ESTACAO_LOCAL_TEMP_FILE_IMG_AVT,AUTDispositivoConfiguracao.ANDROID_ID_IMG);

				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb shell screencap \"%s\"",AUTDispositivoConfiguracao.ANDROID_PASTA_ADB_IMG_ATV)));

				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb pull \"%s\" \"%s\"",AUTDispositivoConfiguracao.ANDROID_PASTA_ADB_IMG_ATV,fileImg)));

				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb shell rm \"%s\"",AUTDispositivoConfiguracao.ANDROID_PASTA_ADB_IMG_ATV)));

				img = new AUTAPI.AUTPainelImagem(fileImg,escalaReducao);

				return img;				
			}

			public static AUTPainelImagem carregarImagem() {

				AUTPainelImagem img  = null;

				AUTDispositivoConfiguracao.ANDROID_ID_IMG++;

				String fileImg  = String.format(AUTDispositivoConfiguracao.ESTACAO_LOCAL_TEMP_FILE_IMG_AVT,AUTDispositivoConfiguracao.ANDROID_ID_IMG);

				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb shell screencap \"%s\"",AUTDispositivoConfiguracao.ANDROID_PASTA_ADB_IMG_ATV)));

				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb pull \"%s\" \"%s\"",AUTDispositivoConfiguracao.ANDROID_PASTA_ADB_IMG_ATV,fileImg)));

				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb shell rm \"%s\"",AUTDispositivoConfiguracao.ANDROID_PASTA_ADB_IMG_ATV)));

				img = new AUTAPI.AUTPainelImagem(fileImg,AUTDispositivoConfiguracao.ANDROID_PERCENTUAL_REDUCAO_CAP_IMG);

				return img;				
			}

			public static AUTPainelImagem carregarImagem(String nomeDispositivo) {
				
				AUTPainelImagem img  = null;

				AUTDispositivoConfiguracao.ANDROID_ID_IMG++;
				
				AUTDispositivoConfiguracao.inicializarConfiguracoes(nomeDispositivo);

				String fileImg  = String.format(AUTDispositivoConfiguracao.ESTACAO_LOCAL_TEMP_FILE_IMG_AVT,AUTDispositivoConfiguracao.ANDROID_ID_IMG);

				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s shell screencap \"%s\"",nomeDispositivo,AUTDispositivoConfiguracao.ANDROID_PASTA_ADB_IMG_ATV)));

				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s pull \"%s\" \"%s\"",nomeDispositivo,AUTDispositivoConfiguracao.ANDROID_PASTA_ADB_IMG_ATV,fileImg)));

				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s shell rm \"%s\"",nomeDispositivo,AUTDispositivoConfiguracao.ANDROID_PASTA_ADB_IMG_ATV)));

				img = new AUTAPI.AUTPainelImagem(fileImg,AUTDispositivoConfiguracao.ANDROID_PERCENTUAL_REDUCAO_CAP_IMG);

				return img;				
			}

			public static AUTPainelImagem carregarImagem(int idImagem,int largura,int altura) {

				AUTPainelImagem img  = null;


				String fileImg  = String.format(AUTDispositivoConfiguracao.ESTACAO_LOCAL_TEMP_FILE_IMG_AVT_CUSTOM,idImagem);

				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb shell screencap \"%s\"",AUTDispositivoConfiguracao.ANDROID_PASTA_ADB_IMG_ATV_CUSTOM)));

				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb pull \"%s\" \"%s\"",AUTDispositivoConfiguracao.ANDROID_PASTA_ADB_IMG_ATV_CUSTOM,fileImg)));

				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb shell rm \"%s\"",AUTDispositivoConfiguracao.ANDROID_PASTA_ADB_IMG_ATV_CUSTOM)));

				img = new AUTAPI.AUTPainelImagem(fileImg,0,0,largura,altura);

				return img;				
			}

			public static AUTPainelImagem carregarImagem(String nomeDispositivo,int idImagem,int largura,int altura) {

				
				AUTPainelImagem img  = null;
				
				try {
					java.util.Date data = new java.util.Date();
					java.text.SimpleDateFormat formatData = new java.text.SimpleDateFormat();
					
					String idImagemTemp = formatData.format(data).concat("-").concat(formatData.getTimeInstance().format(data));
					idImagemTemp = idImagemTemp.replaceAll("\\W", "-");
					String nmDev =  nomeDispositivo.replaceAll("\\W", "");
					
					String fileImg  = String.format(AUTDispositivoConfiguracao.ESTACAO_LOCAL_TEMP_FILE_IMG_AVT_CUSTOM_RTIME,nmDev,idImagemTemp);
	
					System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s shell screencap \"%s\"",nomeDispositivo,AUTDispositivoConfiguracao.ANDROID_PASTA_ADB_IMG_ATV_CUSTOM)));
	
					System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s pull \"%s\" \"%s\"",nomeDispositivo,AUTDispositivoConfiguracao.ANDROID_PASTA_ADB_IMG_ATV_CUSTOM,fileImg)));
	
					System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb -s %s shell rm \"%s\"",nomeDispositivo,AUTDispositivoConfiguracao.ANDROID_PASTA_ADB_IMG_ATV_CUSTOM)));
	
					img = new AUTAPI.AUTPainelImagem(fileImg,0,0,largura,altura);

				}
				catch(java.lang.Exception e) {
					System.out.println("RTIMEERROR: ERRO AO GERAR IMAGEM EM TEMPO REAL...");
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
				return img;				
			}
			
			public static void carregarArvoreObjetosXML(org.w3c.dom.Document documentoXML,javax.swing.JTree objetoGUI){

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

			public static boolean pesquisarTexto(String textoElemento,org.w3c.dom.Document documentoXML) {
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


			public static org.w3c.dom.Document carregarXMLConfiguracao() {

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

			public static org.w3c.dom.Document carregarXMLConfiguracao(String nomeDispositivo) {

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

		public static String gerarXMLGUI() {

			String dirOutAdbXML = AUTDispositivoConfiguracao.ANDROID_PASTA_ADB_XML_GUI_OBJECT;

			System.out.println("*************** CARREGANDO XML GUI  DE CONFIGURAÇÃO DO APP: INICIO ***************************************");

			System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(AUTDispositivoConfiguracao.ANDROID_SHELL_EXEC_GET_FILE_GUI));
			System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(AUTDispositivoConfiguracao.ANDROID_SHELL_COPY_FILE_GUI));
			System.out.println("*************** CARREGANDO XML GUI  DE CONFIGURAÇÃO DO APP: INICIO ***************************************");

			return AUTDispositivoConfiguracao.ANDROID_PASTA_LOCAL_XML_GUI_OBJECT;
		}

		public static String gerarXMLGUI(String nomeDispositivo) {

			String dirOutAdbXML = AUTDispositivoConfiguracao.ANDROID_PASTA_ADB_XML_GUI_OBJECT;

			System.out.println("*************** CARREGANDO XML GUI  DE CONFIGURAÇÃO DO APP: INICIO ***************************************");

			System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format(AUTDispositivoConfiguracao.ANDROID_SHELL_EXEC_GET_FILE_GUI_CUSTOM.toString(),nomeDispositivo)));
			System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format(AUTDispositivoConfiguracao.ANDROID_SHELL_COPY_FILE_GUI_CUSTOM.toString(),nomeDispositivo)));
			System.out.println("*************** CARREGANDO XML GUI  DE CONFIGURAÇÃO DO APP: INICIO ***************************************");

			return AUTDispositivoConfiguracao.ANDROID_PASTA_LOCAL_XML_GUI_OBJECT;
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
		java.util.Stack<AUTAPI.AUTPainelImagem> pnImagens = new java.util.Stack<AUTAPI.AUTPainelImagem>();
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
				AUTGUIParallelProcess procExec = new AUTGUIParallelProcess() {

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


		configLayoutConfigParam.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(5);
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

		AUTGUIParallelProcess procLoaderApps = new AUTGUIParallelProcess() {

			@Override
			protected void rotinasInicializacao() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void rotinasExecucao() {

				if(!ltDevices.isSelectionEmpty()) {
					ltApps.setModel(AUTDispositivoConfiguracao.carregarAplicativosInstalados(ltDevices.getSelectedValue().toString()));
				}
				else {
					AUTAPI.AUTFormularioUtils.exibirMensagem("SOFTTEK : ASSISTENTE DE CONFIGURAÇÃO", "SELECIONE O DISPOSITIVO QUE DESEJA VISUALIZAR OS APPS INSTALADOS!!!");
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

		AUTGUIParallelProcess procLoaderImg = new AUTGUIParallelProcess() {

			@Override
			protected void rotinasInicializacao() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void rotinasExecucao() {
				System.out.println("INFO: CARREGANDO IMAGEM DA TELA ATUAL DO DISPOSITIVO CONECTADO");	
				if(!ltDevices.isSelectionEmpty())
				{
					containerImagens.add(AUTAndroidInterface.AUTAndroidObject.carregarImagem(ltDevices.getSelectedValue().toString()));	
					
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
					AUTAPI.AUTFormularioUtils.exibirMensagem("ASSISTENTE DE CONFIGURAÇÃO", "SELECIONE O DISPOSITIVO PARA CAPTURAR DE TELA");
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

		AUTGUIParallelProcess autCarregarGUI = new AUTGUIParallelProcess() {

			@Override
			protected void rotinasInicializacao() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void rotinasExecucao() {

				System.out.println("INFO : CARREGANDO ESTRUTURA DE OBJETOS GUI : TELA ATUAL");

				
				if(!ltDevices.isSelectionEmpty()) {
					AUTAndroidInterface.AUTAndroidObject.carregarArvoreObjetosXML(AUTAndroidInterface.AUTAndroidObject.carregarXMLConfiguracao(ltDevices.getSelectedValue().toString()), treeObjetosGUI);
				}
				else {
					AUTAPI.AUTFormularioUtils.exibirMensagem("SOFTTEK: ASSISTENTE DE CONFIGURAÇÃO", "SELECIONE UM DOS DISPOSITIVOS CONECTADOS PARA EXECUTAR ESSE COMANDO!!!!");
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
		configOps.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(10);
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

		configPainelGerenciamento.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(5);
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




		configLayout.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(5);
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

		AUTGUIParallelProcess procLoaderData = new AUTGUIParallelProcess() {

			@Override
			protected void rotinasInicializacao() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void rotinasExecucao() {
				if(ddLtTecladoConfig.size() > 0) {ddLtTecladoConfig.removeAllElements();}
				if(!ltDevices.isSelectionEmpty()) {
					for(Object obj: AUTDispositivoConfiguracao.carregarConfiguracaoTeclado(ltDevices.getSelectedValue().toString())) {
						if(obj.toString().length() > 1 && !obj.toString().equals("character")) {
							ddLtTecladoConfig.addElement(obj);
						}
					}
				}
				else {
					AUTAPI.AUTFormularioUtils.exibirMensagem("SOFTTEK: ASSISTENTE DE CONFIGURAÇÃO", "SELECIONE UM DISPOSITIVO PARA CARREGAR AS CONFIGURAÇÕES DE TECLADO");
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
					AUTFormMobileConsoleManage formGer = new AUTFormMobileConsoleManage(ltDevices.getSelectedValue().toString(),formMob);
				}
				else {
					AUTAPI.AUTFormularioUtils.exibirMensagem("MOBILE : ASSISTENTE DE CONFIGURAÇÃO", "SELECIONE O DISPOSITIVO QUE DESEJA GERENCIAR!!!!");
				}

			}
		});

		
		AUTGUIParallelProcess procAtualizacaoDevices = new AUTGUIParallelProcess() {

			@Override
			protected void rotinasInicializacao() {						
				System.out.println("INFO : TAREFA :  LISTAR DE DISPOSITIVOS CONECTADOS : INICIALIZANDO");	
			}

			@Override
			protected void rotinasExecucao() {						
				System.out.println("@@@RESET CONNECTION INIT: @@@@@@");
				AUTDispositivoConfiguracao.resetConnections();				
				System.out.println("@@@RESET CONNECTION END: @@@@@@");

				ddDevices.removeAllElements();

				System.out.println("@@@LOADERDEVICES: LISTA DE DISPOSITIVOS CONECTADOS");
				System.out.println(AUT_ANDROID_CONFIGURACAO_IP.LIST_DISPOSITIVOS);

				String[] hosts = AUT_ANDROID_CONFIGURACAO_IP.LIST_DISPOSITIVOS.toString().split("\n");
				Integer portaConexao = 60001;						
				for(String hst : hosts) {
					String ip1 = String.format(AUT_ANDROID_CONFIGURACAO_IP.IP_IPV4_MULT_CONECT_DINAMICA.toString(),hst);
					ip1 = AUTAPI.AUTProcessoExternoUtils.executarProcesso(ip1).toString();
					
					
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
								//AUTDispositivoConfiguracao.adicionarConexao(hst, ip1, portCon.toString());
							}
							catch(java.lang.Exception e) {
								
								System.out.println("INFOERRO: NAO FOI POSSIVEL HABILITAR O ACESSO WIFI");
								
								System.out.println(e.getMessage());
								
								e.printStackTrace();
							}
							ip1 = ip1.concat(":").concat(portCon.toString());
						}
						
						
						AUTDispositivoConfiguracao.adicionarConexao(hst, ip1, portaConexao.toString());
						
						System.out.println("INFO ADB : CONFIGURANDO ESTRUTURA DE DADOS DA APLICAÇÃO");
						
						try {
							AUTDispositivoConfiguracao.inicializarConfiguracoes(hst);
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
						ip2 = AUTAPI.AUTProcessoExternoUtils.executarProcesso(ip2).toString();
						if(!ip2.trim().isEmpty()) {
							System.out.println("INFO : PROCURANDO IP2");
							System.out.println(ip2);
							
							if(hst.contains("emulator")) {
								String port = hst.split("\\-")[1];
								Integer portCon = Integer.parseInt(port) + 1;
								//AUTDispositivoConfiguracao.adicionarConexao(hst, ip2, portCon.toString());
								ip2 = ip2.concat(":").concat(portCon.toString());
							}
							
							
							AUTDispositivoConfiguracao.adicionarConexao(hst, ip2, portaConexao.toString());
							
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
					
					AUTAPI.AUTFormularioUtils.exibirMensagem("SOFTTEK: ASSISTENTE DE CONFIGURAÇÃO", String.format("DISPOSITIVO (%s) EXCLUÍDO DA LISTA COM SUCESSO!!!",item));
				}
				else {
					AUTAPI.AUTFormularioUtils.exibirMensagem("SOFTTEK: ASSISTENTE DE CONFIGURAÇÃO", "ESCOLHA UM DOS DISPOSITIVOS CONECTADOS AO COMPUTADOR PARA EXCLUSÃO");
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
							AUTDispositivoConfiguracao.ANDROID_PERCENTUAL_REDUCAO_CAP_IMG = Integer.parseInt(txt.getText().trim());
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
							AUTDispositivoConfiguracao.ANDROID_TEMPO_SINCRONIZACAO_CAP_IMG = Integer.parseInt(txt.getText().trim());
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


		AUTGUIParallelProcess procCapTelaAut = new AUTGUIParallelProcess() {

			@Override
			protected void rotinasInicializacao() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void rotinasExecucao() {
				boolean continuar = true;
				System.out.println("INFO : INICIANDO PROCESSO DE CAPTURA DE TELA EM MODO AUTOMATICO");

				continuar = AUTDispositivoConfiguracao.ANDROID_CAP_AUTOMATIC_IMG;

				while(continuar) {

					procLoaderImg.executarProcesso();					

					try {
						java.lang.Thread.sleep(AUTDispositivoConfiguracao.ANDROID_TEMPO_SINCRONIZACAO_CAP_IMG * 1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}

					continuar = AUTDispositivoConfiguracao.ANDROID_CAP_AUTOMATIC_IMG;
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
					AUTDispositivoConfiguracao.ANDROID_CAP_AUTOMATIC_IMG = true;
					procCapTelaAut.executarProcesso();
				}
				else {
					AUTDispositivoConfiguracao.ANDROID_CAP_AUTOMATIC_IMG = false;
					try {
						procCapTelaAut.pararExecucao();
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}

				}	
			}
		});	


		AUTGUIParallelProcess procSincDadosGUI = new AUTGUIParallelProcess() {
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
					String[] itensDevsInput = AUT_ANDROID_CONFIGURACAO_IP.LIST_DISPOSITIVOS.toString().split("\n");
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
					AUTDispositivoConfiguracao.ANDROID_CAP_AUTOMATIC_IMG = true;
					procCapTelaAut.executarProcesso();
				}
				else {
					AUTDispositivoConfiguracao.ANDROID_CAP_AUTOMATIC_IMG = false;
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
