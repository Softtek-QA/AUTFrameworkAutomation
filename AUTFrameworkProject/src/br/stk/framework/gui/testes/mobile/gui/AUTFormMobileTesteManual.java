package br.stk.framework.gui.testes.mobile.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.util.Map;
import java.util.stream.Collector.Characteristics;

import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;

import org.apache.derby.iapi.types.CharStreamHeaderGenerator;

import br.stk.framework.api.AUTAPI;
import br.stk.framework.api.AUTAPI.*;
import br.stk.framework.api.runtime.AUTRuntimeConfiguration;

/**
 * Classe responsável pelo gerenciamento básico dos testes manuais para mobile
 * @author Softtek - QA
 *
 */
public class AUTFormMobileTesteManual{	
	public static class AUTDadosConexao{
		public String ID;
		public String NOME_DISPOSITIVO;
		public String IP;
		public String PORTA;
		
		@Override
		public String toString() {			
			return String.format("ID: %s : IP : %s : PORTA : %s", ID,IP,PORTA);
		}
	}



	public static class AUTDispositivoConfiguracao{
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
		public static String ANDROID_PASTA_RAIZ = "/sdcard/aut_arquivos";
		public static String ANDROID_PASTA_VIDEO = ANDROID_PASTA_RAIZ.concat("/videos");
		public static String ANDROID_ARQUIVO_VIDEO_PADRAO = ANDROID_PASTA_VIDEO.concat("AUT_VIDEO_001.mp4");
		public static String ANDROID_ESTRUTURA_ARQUIVOS = String.format("mkdir \"%s\" \"%s\"\\",ANDROID_PASTA_RAIZ.toString(),ANDROID_PASTA_VIDEO.toString());
		public static String ANDROID_ARQUIVO_ATUAL_CORRENTE = ANDROID_PASTA_RAIZ.concat("/AUT_IMG_ATUAL_00%s.png");
		public static String ANDROID_ARQUIVO_ESPERADO_CORRENTE = ANDROID_PASTA_RAIZ.concat("/AUT_IMG_ESPERADO_00%s.png");
		public static String SCREEN_SCRIPT_ST_ATUAL_CAP = ANDROID_PASTA_RAIZ.concat(";screencap \"").concat(ANDROID_ARQUIVO_ATUAL_CORRENTE).concat("\"");
		public static String SCREEN_SCRIPT_ST_ESPERADO_CAP = ANDROID_PASTA_RAIZ.concat(";screencap \"").concat(ANDROID_ARQUIVO_ESPERADO_CORRENTE).concat("\"");
		public static String RECORD_VIDEO_CAP = " screenrecord \"".concat(ANDROID_ARQUIVO_VIDEO_PADRAO).concat("\"");		
		private static Integer portConexao = 60100;
		protected static java.util.HashMap<String, AUTDadosConexao> conexoesDispositivos = new java.util.HashMap<String,AUTDadosConexao>();
		protected static java.util.HashMap<String,String> AUTDadosTransferenciaArquivos = new java.util.HashMap<String,String>();
		protected static java.util.HashMap<Integer,String[]> AUT_TECLAS_CONFIGURACAO = new java.util.HashMap<Integer,String[]>();
		
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
			
			
		}
		
		public static String enviarComando(String cmd) {
			String comando,recScreenAtual,recScreenEsperado,recVideo;
			
			if(AUTRuntimeConfiguration.AUT_HABILITAR_CAPTURA_SCREEN_E_VIDEO) {
				recScreenAtual = String.format(SCREEN_SCRIPT_ST_ATUAL_CAP.toString(), SERIAL_SCREEN.toString());
				recScreenEsperado = String.format(SCREEN_SCRIPT_ST_ESPERADO_CAP.toString(), SERIAL_SCREEN.toString());
				recVideo = RECORD_VIDEO_CAP;
				SERIAL_SCREEN++;
				comando = String.format("adb shell %s;input keyevent %s;%s;",recScreenAtual,cmd,recScreenEsperado);
				
				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(comando));
				AUTDadosTransferenciaArquivos.put(String.format(ANDROID_ARQUIVO_ATUAL_CORRENTE.toString(),SERIAL_SCREEN.toString()),
						String.format(ANDROID_ARQUIVO_ESPERADO_CORRENTE.toString(),SERIAL_SCREEN.toString()));
			}
			else if(AUTRuntimeConfiguration.AUT_HABILITAR_CAPTURA_SCREEN) {
				recScreenAtual = String.format(SCREEN_SCRIPT_ST_ATUAL_CAP.toString(), SERIAL_SCREEN.toString());
				recScreenEsperado = String.format(SCREEN_SCRIPT_ST_ESPERADO_CAP.toString(), SERIAL_SCREEN.toString());
				SERIAL_SCREEN++;
				comando = String.format("adb shell %s;input keyevent %s;%s;",recScreenAtual,cmd,recScreenEsperado);
				
				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(comando));	
				AUTDadosTransferenciaArquivos.put(String.format(ANDROID_ARQUIVO_ATUAL_CORRENTE.toString(),SERIAL_SCREEN.toString()),
						String.format(ANDROID_ARQUIVO_ESPERADO_CORRENTE.toString(),SERIAL_SCREEN.toString()));
			}
			else if(AUTRuntimeConfiguration.AUT_HABILITAR_TERMINAL_DE_COMANDO) {
				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb shell input keyevent %s;",cmd)));
			}
			
			return cmd;
		}

		public static void enviarConteudo(String conteudo) {
			System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb shell input keyboard text \"%s\"",conteudo)));
		}

		public enum AUTKEY{
			NAVEGACAO_DIREITA,
			NAVEGACAO_ESQUERDA,
			NAVEGACAO_ACIMA,
			NAVEGACAO_ABAIXO,
			PROXIMO_ITEM_TAB,
			ABORTAR,
			BARRA_INVERTIDA,
			PONTUACAO_ESPACO_EM_BRANCO,
			PONTUACAO_VIRGULA,
			PONTUACAO_PONTO_FINAL,
			PONTUACAO_ACENTO_GRAVE,
			OPERADOR_SINAL_MENOS,
			CONFIRMAR;

			@Override
			public String toString() {
				switch(this) {
				case NAVEGACAO_DIREITA:
				{
					return "NPAD_RIGHT";
				}
				case NAVEGACAO_ESQUERDA:
				{
					return "NPAD_LEFT";
				}
				case NAVEGACAO_ACIMA:
				{
					return "NPAD_UP";
				}
				case NAVEGACAO_ABAIXO:
				{
					return "PAGE_DOWN";
				}
				case PROXIMO_ITEM_TAB:
				{
					return "TAB";
				}
				case CONFIRMAR:
				{
					return "ENTER";
				}
				case ABORTAR:
				{
					return "ESCAPE";
				}
				case PONTUACAO_ESPACO_EM_BRANCO:
				{
					return "SPACE";
				}
				case PONTUACAO_VIRGULA:
				{
					return "COMMA";
				}
				case PONTUACAO_PONTO_FINAL:{
					return "PERIOD";
				}
				case BARRA_INVERTIDA:{
					return "SLASH";
				}
				default:{
					return "";
				}
				}


			}
		}	


		public static String buscarIP() {
			//return AUTAPI.AUTProcessoExternoUtils.executarProcesso("adb shell getprop ".concat(IP)).toString().trim();
			return AUTAPI.AUTProcessoExternoUtils.executarProcesso("adb shell getprop ".concat(IP)).toString().trim();
		}

		
		public static String buscarHostNome() {
			return AUTAPI.AUTProcessoExternoUtils.executarProcesso("adb shell getprop net.hostname").toString();
		}
		
		public static String buscarNomeHost() {
			return AUTAPI.AUTProcessoExternoUtils.executarProcesso("adb shell getprop ".concat(HOST_NOME)).toString().trim();
		}

		public static String configurarConexaoTCPIP(String IP) {
			AUTDadosConexao dados;
			if(!conexoesDispositivos.containsKey(IP)) {
				portConexao++;
				dados = new AUTDadosConexao();
				dados.ID = IP;
				dados.IP = IP;
				dados.PORTA = portConexao.toString();

				System.out.println("INFO: CONFIGURANDO CONEXAO TCP IP COM DISPOSIVO CONECTADO : ".concat(buscarNomeHost().concat(" PORTA: ".concat(portConexao.toString()))));
				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb disconnect %s:%s",IP,portConexao)));
				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb tcpip %s",portConexao)));				
				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb connect %s:%s",IP,portConexao)));


				System.out.println("INFO : FIM DA CONFIGURACAO DE REDE");

				conexoesDispositivos.put(IP, dados);
			}
			else {
				dados = conexoesDispositivos.get(IP);

				System.out.println("INFO: TENTANDO RECONECTAR COM DISPOSIVO: ".concat(dados.IP.concat(" PORTA: ".concat(dados.PORTA))));
				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb disconnect %s:%s",dados.IP,dados.PORTA)));
				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb tcpip %s",dados.PORTA)));				
				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb connect %s:%s",dados.IP,dados.PORTA)));
				System.out.println("INFO : FIM DA CONFIGURACAO DE REDE");			
			}

			return dados.IP.concat(":").concat(dados.PORTA);
		}


		public static void configurarConexaoUSB(String IP) {
			AUTDadosConexao dados = conexoesDispositivos.get(IP);

			System.out.println("INFO: RECONFIGURANDO CONEXAO USB: ".concat(dados.IP.concat(" PORTA: ".concat(dados.PORTA))));
			System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb disconnect %s:%s",dados.IP,dados.PORTA)));

		}
	}





	public static abstract class AUTProcessoMonitorUSB{
		java.lang.Process processo = null;
		String linhaComando = "";

		private java.lang.Process configurarLinhaComando(String comandoInicializacao){
			linhaComando = comandoInicializacao;

			try 
			{												
				return java.lang.Runtime.getRuntime().exec(comandoInicializacao);

			} catch (IOException e) {

				System.out.println("ERRO : DURANTE A INICIALIZAÇÃO DO PROCESSO DE MONITORAMENTO DE PORTAS USB");

				System.out.println(e.getMessage());

				e.printStackTrace();

				return null;
			}
		}


		/**
		 * Retorna a linha de comando utilizada na inicialização do processo de monitoramento de portas USB
		 * 
		 * @return String - Linha de Comando
		 * 
		 */
		public String retornarLinhaComandoInicializacao() {
			return this.linhaComando;
		} 

		/**
		 * Responsável por iniciar o processo de monitoramento das portas de conexão USB
		 */
		public void iniciarProcesso() {
			System.out.println("INFO: INICIANDO PROCESSO DE MONITORAMENTO : PORTAS DE ESCUTA USB");
			processo = configurarLinhaComando(linhaComando);
			System.out.println("INFO: MONITORAMENTO DE PORTA USB: INICIALIZADO COM SUCESSO");
		}


		/**
		 * 
		 * Finaliza o processo em execução imediatamente
		 * 
		 */
		public  void pararMonitoramento() {
			System.out.println("INFO : FINALIZANDO PROCESSO DE MONITORAMENTO DE PORTAS : USB");
			processo.exitValue();
			processo.destroyForcibly();
		};




	}

	private javax.swing.JDialog formPrincipal = null;
	/**

	 * Função responsável pela configuração básica da interface gráfica
	 * 
	 */


	private void configGUI() {

		javax.swing.JDialog formMobile = new javax.swing.JDialog(formPrincipal);
		javax.swing.JPanel pnPrincipal = new javax.swing.JPanel();
		javax.swing.JList listDevices = new javax.swing.JList();
		javax.swing.JScrollPane pnDevicesConnect = new JScrollPane(listDevices,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JPanel pnBotoes = new javax.swing.JPanel();
		javax.swing.JLabel lbDevices = new javax.swing.JLabel("DISPOSITIVOS CONECTADOS: TIPO CONEXAO : USB");
		javax.swing.JPanel pnApps = new javax.swing.JPanel();
		javax.swing.JPanel pnBotoesGerApps = new javax.swing.JPanel();
		javax.swing.JLabel lbApps = new javax.swing.JLabel("LISTA DE APLICATIVOS DISPONÍVEIS: ");
		javax.swing.JList listApps = new javax.swing.JList();
		javax.swing.JScrollPane pnListApps = new javax.swing.JScrollPane(listApps,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JButton btDownloadApp = new javax.swing.JButton("Download");
		javax.swing.JButton btUploadApp = new javax.swing.JButton("Upload");
		javax.swing.JButton btRemoverApp = new javax.swing.JButton("Desinstalar");
		javax.swing.JButton btAtualizar = new javax.swing.JButton("Atualizar");
		javax.swing.JButton btExcluir = new javax.swing.JButton("Excluir");
		javax.swing.JButton btTestar = new javax.swing.JButton("Testar Conexão");
		javax.swing.JButton btCarregarAplicativos = new javax.swing.JButton("Carregar Aplicação");		
		javax.swing.JPanel pnDevicesUSB = new javax.swing.JPanel();
		java.awt.GridBagConstraints configLayoutApp = new java.awt.GridBagConstraints();
		javax.swing.JLabel lbInfoDispositivos = new javax.swing.JLabel("INFORMAÇÕES DO DISPOSITIVO SELECIONADO:");
		javax.swing.JPanel containerInfoDispositivos = new javax.swing.JPanel();
		javax.swing.JPanel pnTerminalComando = new javax.swing.JPanel();
		javax.swing.JPanel pnInputDirTermSaida = new javax.swing.JPanel();
		javax.swing.JLabel lbDiretorioSaidaTerminal = new javax.swing.JLabel("Diretório :");
		javax.swing.JLabel lbLogMsg = new javax.swing.JLabel("GRAVAÇÃO DE REGISTROS:");
		javax.swing.JList listaLogMsg = new javax.swing.JList();
		javax.swing.JScrollPane scrLogMgs = new javax.swing.JScrollPane(listaLogMsg,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JPanel pnMsgTerminal = new javax.swing.JPanel();
		javax.swing.JTextField txtDiretorioSaidaTerminal = new javax.swing.JTextField(40);
		javax.swing.JButton btSelectDirSaida = new javax.swing.JButton("Pasta");
		javax.swing.JButton btDownloadEvidencias = new javax.swing.JButton("Sincronizar");
		javax.swing.JRadioButton rdHabilitarCMDInterativo = new javax.swing.JRadioButton("Habilitar Terminal de Comando");
		javax.swing.JRadioButton rdHabilitarCMDIntComImagem = new javax.swing.JRadioButton("Habilitar Terminal + Evidências em Imagens");
		javax.swing.JRadioButton rdHabilitarIntComImgEVd = new javax.swing.JRadioButton("Habilitar Terminal + Evidências: Imagens + Video");
		javax.swing.JRadioButton rdDesabilitarCMDInterativo = new javax.swing.JRadioButton("Desabilitar Terminal de Comando",true);
		javax.swing.JButton botaoFinalizarExecTest = new javax.swing.JButton("Iniciar Gravação");
		javax.swing.JPanel pnStatusExec = new javax.swing.JPanel();
		javax.swing.JTextArea txtConsoleSaida = new javax.swing.JTextArea(1,40);
		javax.swing.JScrollPane scrConsoleSaida = new javax.swing.JScrollPane(txtConsoleSaida,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JPanel pnAtividades = new javax.swing.JPanel();
		javax.swing.JPanel containerScreenDevice = new javax.swing.JPanel();
		javax.swing.JScrollPane scrScreenDevice = new javax.swing.JScrollPane(containerScreenDevice,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.DefaultListModel dadosMsg = new javax.swing.DefaultListModel();				
		java.awt.GridBagConstraints configLayoutLogMsg = new java.awt.GridBagConstraints();		
		
		AUTDispositivoConfiguracao.inicializarConfiguracoes();
		listaLogMsg.setModel(dadosMsg);
		
		
		botaoFinalizarExecTest.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				javax.swing.SwingWorker swkRec = new javax.swing.SwingWorker() {		
					java.lang.Process processoGravacao = null;
					@Override
					protected Object doInBackground() throws Exception {
						String diretorioSaida = txtDiretorioSaidaTerminal.getText().trim();
						
						if(!diretorioSaida.isEmpty()) {
							if(!AUTRuntimeConfiguration.AUT_GRAVACAO_VIDEO_ATIVA) {
																
								AUTRuntimeConfiguration.AUT_GRAVACAO_VIDEO_ATIVA = true;
								AUTAPI.AUTFormularioUtils.exibirMensagem("Softtek : Assistente de Configuração", "Gravação iniciada com sucesso!!!!");
								
								AUTRuntimeConfiguration.AUT_PROCESSO_GRAVACAO_VIDEO = new java.lang.Thread(new java.lang.Runnable() {
									@Override
									public void run() {
										// TODO Auto-generated method stub
										processoGravacao = AUTAPI.AUTProcessoExternoUtils.executarProcessoExterno("adb shell screenrecord \"".concat(AUTFormMobileTesteManual.AUTDispositivoConfiguracao.ANDROID_ARQUIVO_VIDEO_PADRAO.concat("\"")));
										
									}
								});
								
								AUTRuntimeConfiguration.AUT_PROCESSO_GRAVACAO_VIDEO.start();
								
								botaoFinalizarExecTest.setText("Parar Gravação");
								System.out.println("INFO: INICIANDO A GRAVAÇÃO DE VIDEO : THREAD START");
																						
							}
							else {
								System.out.println("INFO: PARANDO GRAVAÇÃO DE VIDEO");
								botaoFinalizarExecTest.setText("Iniciar Gravação");
								AUTRuntimeConfiguration.AUT_GRAVACAO_VIDEO_ATIVA = false;
								AUTRuntimeConfiguration.AUT_PROCESSO_GRAVACAO_VIDEO.start();
								System.out.println(AUTRuntimeConfiguration.AUT_PROCESSO_GRAVACAO_VIDEO.interrupted());
								AUTAPI.AUTFormularioUtils.exibirMensagem("Softtek : Assistente de Configuração", "Gravação finalizada com sucesso, navegue até o diretório informado para visualizar o arquivo (.mp4)");
							}
						}
						else {
							AUTAPI.AUTFormularioUtils.exibirMensagem("Softtek : Assistente de Configuração", "Informe o diretório de despejo do arquivo de video");
						}
						
						return null;
					}
					
					@Override
					protected void done() {
						// TODO Auto-generated method stub
						super.done();
					}
				};
				
				swkRec.execute();
				
			}
		});
		
		pnMsgTerminal.setLayout(new javax.swing.BoxLayout(pnMsgTerminal,javax.swing.BoxLayout.Y_AXIS));
		pnMsgTerminal.setBackground(java.awt.Color.WHITE);
		configLayoutLogMsg.weightx = 1;
		configLayoutLogMsg.gridx = 0;
		configLayoutLogMsg.gridy = 0;
		pnStatusExec.setBackground(java.awt.Color.WHITE);
		pnStatusExec.add(lbLogMsg);
		pnStatusExec.add(botaoFinalizarExecTest);
		
		pnMsgTerminal.add(pnStatusExec,configLayoutLogMsg);
		pnMsgTerminal.add(scrLogMgs,configLayoutLogMsg);
		
		pnInputDirTermSaida.setLayout(new javax.swing.BoxLayout(pnInputDirTermSaida,javax.swing.BoxLayout.X_AXIS));
		pnInputDirTermSaida.setBackground(java.awt.Color.WHITE);
		pnInputDirTermSaida.add(lbDiretorioSaidaTerminal);
		pnInputDirTermSaida.add(txtDiretorioSaidaTerminal);
		pnInputDirTermSaida.add(btSelectDirSaida);
		pnInputDirTermSaida.add(btDownloadEvidencias);
		
		btDownloadEvidencias.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				javax.swing.SwingWorker swkFormProgresso = new javax.swing.SwingWorker() {
					@Override
					protected Object doInBackground() throws Exception {
						
						System.out.println("INFO: ARQUIVOS PARA SINCRONIZACAO:");
						String diretorio = txtDiretorioSaidaTerminal.getText().trim();
						
						if(!diretorio.isEmpty()) {
							
							AUTFormStatusSincronizacao formProgresso = new AUTFormStatusSincronizacao(formMobile,diretorio);
							
						}
						else {
							AUTAPI.AUTFormularioUtils.exibirMensagem("Assistente de Configuração", "Selecione um diretório para sincronização das evidências");
						}
						
						System.out.println(AUTDispositivoConfiguracao.AUTDadosTransferenciaArquivos);				
						
						return null;
					}
					
					@Override
					protected void done() {
						// TODO Auto-generated method stub
						super.done();
					}
				};
				
				
				swkFormProgresso.execute();
				
			}
		});
		
		
		btSelectDirSaida.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String dir = AUTAPI.gerarCaixaDialog(formMobile);
				txtDiretorioSaidaTerminal.setText(dir);
			}
		});
		
		rdHabilitarCMDInterativo.addChangeListener(new javax.swing.event.ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent objeto) {
				javax.swing.JRadioButton rd = (javax.swing.JRadioButton)objeto.getSource();
				if(rd.isSelected()) {
					rdDesabilitarCMDInterativo.setSelected(false);
					rdHabilitarCMDIntComImagem.setSelected(false);
					rdHabilitarIntComImgEVd.setSelected(false);
					AUTRuntimeConfiguration.AUT_HABILITAR_CAPTURA_SCREEN = false;
					AUTRuntimeConfiguration.AUT_HABILITAR_CAPTURA_SCREEN_E_VIDEO = false;
					AUTRuntimeConfiguration.AUT_HABILITAR_TERMINAL_DE_COMANDO=true;
					
				}				
			}
		});

		rdHabilitarCMDIntComImagem.addChangeListener(new javax.swing.event.ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent objeto) {
				javax.swing.JRadioButton rd = (javax.swing.JRadioButton)objeto.getSource();
				if(rd.isSelected()) {
					rdHabilitarCMDInterativo.setSelected(false);
					rdHabilitarIntComImgEVd.setSelected(false);
					rdDesabilitarCMDInterativo.setSelected(false);
					
					AUTRuntimeConfiguration.AUT_HABILITAR_CAPTURA_SCREEN = true;
					AUTRuntimeConfiguration.AUT_HABILITAR_CAPTURA_SCREEN_E_VIDEO = false;
					AUTRuntimeConfiguration.AUT_HABILITAR_TERMINAL_DE_COMANDO=false;
					
					botaoFinalizarExecTest.setText("Parar Gravação");
				}				
			}
		});
		
		
		rdHabilitarIntComImgEVd.addChangeListener(new javax.swing.event.ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent objeto) {
				javax.swing.SwingWorker swkProcessoVD = new javax.swing.SwingWorker() {
					@Override
					protected Object doInBackground() throws Exception {
						javax.swing.JRadioButton rd = (javax.swing.JRadioButton)objeto.getSource();
						if(rd.isSelected()) {
							rdDesabilitarCMDInterativo.setSelected(false);
							rdHabilitarCMDInterativo.setSelected(false);
							rdHabilitarCMDIntComImagem.setSelected(false);
							
							AUTRuntimeConfiguration.AUT_HABILITAR_CAPTURA_SCREEN = false;
							AUTRuntimeConfiguration.AUT_HABILITAR_CAPTURA_SCREEN_E_VIDEO = true;
							AUTRuntimeConfiguration.AUT_HABILITAR_TERMINAL_DE_COMANDO=false;
							
							botaoFinalizarExecTest.setText("Parar Gravação");
											
							AUTProcessoParalelo procRecVD = new AUTProcessoParalelo() {
								java.lang.Process processoItem = null;
								boolean continuar = AUTRuntimeConfiguration.AUT_HABILITAR_CAPTURA_SCREEN_E_VIDEO;
								@Override
								protected void rotinasMonitoramentoProcesso() {
								
								}
								
								@Override
								protected void rotinasExecucao() {
									
									System.out.println("INFO : INICIANDO PROCESSO DE GRAVACAO DE VIDEO:");
									processoItem = AUTAPI.AUTProcessoExternoUtils.executarProcessoExterno("adb shell ".concat(AUTDispositivoConfiguracao.ANDROID_ARQUIVO_VIDEO_PADRAO));	
									try {
									rotinasFinalizacao();
									}
									catch(java.lang.Exception e) {
										System.out.println(e.getMessage());
										e.printStackTrace();
									}
								}
								
								@Override
								protected void rotinasFinalizacao() {
									System.out.println("INFO : FINALIZANDO GRAVAÇÃO DE VIDEO");
									processoItem.exitValue();
									processoItem.destroy();
									System.out.println("INFO : GRAVAÇÃO DE VIDEO FINALIZADA COM SUCESSO");
								}
								
								@Override
								protected void rotinasInicializacao() {
									processoItem = null;
									
								}
							};
							
							procRecVD.executarProcesso();

						}
						return true;
					}
					
					@Override
					protected void done() {
						// TODO Auto-generated method stub
						super.done();
					}
				};
				
				
				swkProcessoVD.execute();
			}
			
		});
		
		
		rdDesabilitarCMDInterativo.addChangeListener(new javax.swing.event.ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent objeto) {
				javax.swing.JRadioButton rd = (javax.swing.JRadioButton)objeto.getSource();
				if(rd.isSelected()) {
					rdHabilitarCMDInterativo.setSelected(false);
					rdHabilitarCMDIntComImagem.setSelected(false);
					rdHabilitarIntComImgEVd.setSelected(false);
					
					AUTRuntimeConfiguration.AUT_HABILITAR_CAPTURA_SCREEN = false;
					AUTRuntimeConfiguration.AUT_HABILITAR_CAPTURA_SCREEN_E_VIDEO = false;
					AUTRuntimeConfiguration.AUT_HABILITAR_TERMINAL_DE_COMANDO=false;
					
					botaoFinalizarExecTest.setText("Iniciar Gravação");
				}				
			}
		});

		pnTerminalComando.setLayout(new java.awt.GridLayout(2, 2));
		pnTerminalComando.add(rdHabilitarCMDInterativo);
		pnTerminalComando.add(rdHabilitarCMDIntComImagem);
		pnTerminalComando.add(rdHabilitarIntComImgEVd);
		pnTerminalComando.add(rdDesabilitarCMDInterativo);
		pnTerminalComando.setBackground(java.awt.Color.WHITE);		


		
		txtConsoleSaida.addKeyListener(new java.awt.event.KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				//System.out.println("INFO: keypressed");
				//System.out.println(arg0.paramString());
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println("INFO: typed");
				//System.out.println(arg0.paramString());
			}

			@Override
			public void keyReleased(KeyEvent key) {
				if(rdHabilitarCMDInterativo.isSelected() || rdHabilitarCMDIntComImagem.isSelected() 
						|| rdHabilitarIntComImgEVd.isSelected()) {
					// TODO Auto-generated method stub
					System.out.println("INFO: release");
					System.out.println(key.paramString());

					if(AUTDispositivoConfiguracao.AUT_TECLAS_CONFIGURACAO.containsKey(key.getKeyCode())) {
						listaLogMsg.setSelectionMode(javax.swing.DefaultListSelectionModel.SINGLE_SELECTION);
						dadosMsg.addElement(AUTDispositivoConfiguracao.AUT_TECLAS_CONFIGURACAO.get(key.getKeyCode())[1]);						
					}
					else {
						
					}
					
					
					
					switch(key.getKeyCode()) {
					case 8:{
						AUTFormMobileTesteManual.AUTDispositivoConfiguracao.enviarComando("DEL");
						break;
					}
					case 9:{
						AUTFormMobileTesteManual.AUTDispositivoConfiguracao.enviarComando("TAB");
						break;
					}
					case 10:{
						AUTFormMobileTesteManual.AUTDispositivoConfiguracao.enviarComando("ENTER");
						break;
					}
					case 18:{
						//AUTFormMobileTesteManual.AUTDispositivoConfiguracao.enviarComando("ENTER");
						break;
					}
					case 27:{
						if(!key.isAltDown()) {
							AUTFormMobileTesteManual.AUTDispositivoConfiguracao.enviarComando("BACK");
						}
						else {
							AUTFormMobileTesteManual.AUTDispositivoConfiguracao.enviarComando("ESCAPE");
						}
					}
					case 32:{
						AUTFormMobileTesteManual.AUTDispositivoConfiguracao.enviarComando("SPACE");
						break;
					}
					case 33:{
						AUTFormMobileTesteManual.AUTDispositivoConfiguracao.enviarComando("PAGE_UP");
						break;
					}
					case 34:{
						AUTFormMobileTesteManual.AUTDispositivoConfiguracao.enviarComando("PAGE_DOWN");
						break;
					}
					case 35:{
						AUTFormMobileTesteManual.AUTDispositivoConfiguracao.enviarComando("MOVE_END");
						break;
					}
					case 36:{
						AUTFormMobileTesteManual.AUTDispositivoConfiguracao.enviarComando("MOVE_HOME");
						break;
					}
					case 37:{
						AUTFormMobileTesteManual.AUTDispositivoConfiguracao.enviarComando("DPAD_LEFT");
						break;
					}
					case 38:{
						AUTFormMobileTesteManual.AUTDispositivoConfiguracao.enviarComando("DPAD_UP");
						break;
					}
					case 39:{
						AUTFormMobileTesteManual.AUTDispositivoConfiguracao.enviarComando("DPAD_RIGHT");
						break;
					}
					case 40:{
						AUTFormMobileTesteManual.AUTDispositivoConfiguracao.enviarComando("DPAD_DOWN");
						break;
					}				
					default:{
						Character cmd = key.getKeyChar();
						AUTFormMobileTesteManual.AUTDispositivoConfiguracao.enviarConteudo(cmd.toString());
						dadosMsg.addElement(cmd.toString());
					}
					}
					listaLogMsg.setSelectionMode(javax.swing.DefaultListSelectionModel.SINGLE_SELECTION);
					listaLogMsg.setSelectedIndex(listaLogMsg.getModel().getSize());
				}

			}
		});

		java.awt.GridBagConstraints configTerm = new java.awt.GridBagConstraints();
		configTerm.gridx = 0;
		configTerm.gridy = 0;
		configTerm.fill = configTerm.BOTH;
		configTerm.weightx = 1;
		configTerm.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(10);
		pnAtividades.setLayout(new java.awt.GridBagLayout());
		pnAtividades.setBackground(java.awt.Color.WHITE);

		pnAtividades.add(lbInfoDispositivos,configTerm);
		configTerm.gridy = 1;
		pnAtividades.add(pnTerminalComando,configTerm);
		configTerm.gridy = 2;
		pnAtividades.add(pnInputDirTermSaida,configTerm);
		configTerm.gridy = 3;
		configTerm.weighty = 1;
		pnAtividades.add(scrConsoleSaida,configTerm);
		configTerm.gridx = 1;
		configTerm.gridy = 1;
		configTerm.gridheight = 5;
		pnAtividades.add(pnMsgTerminal,configTerm);
		
		
		listApps.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		listDevices.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

		//javax.swing.JScrollPane pnEditCode = new javax.swing.JScrollPane(app,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		configLayoutApp.gridx = 0;
		configLayoutApp.gridy = 0;
		configLayoutApp.fill = configLayoutApp.BOTH;
		pnApps.setBackground(java.awt.Color.WHITE);
		pnApps.setLayout(new java.awt.GridBagLayout());


		pnApps.setLayout(new javax.swing.BoxLayout(pnApps,javax.swing.BoxLayout.Y_AXIS));
		pnBotoesGerApps.setBackground(java.awt.Color.WHITE);
		pnBotoesGerApps.setLayout(new java.awt.GridLayout(1,3));



		pnBotoesGerApps.add(btDownloadApp);
		pnBotoesGerApps.add(btUploadApp);
		pnBotoesGerApps.add(btRemoverApp);	


		pnApps.add(lbApps);
		pnApps.add(pnListApps);
		pnApps.add(pnBotoesGerApps);

		java.awt.GridBagConstraints configLayoutPrincipal = new java.awt.GridBagConstraints();

		formMobile.setLayout(new java.awt.GridBagLayout());

		pnDevicesUSB.setLayout(new java.awt.GridBagLayout());

		formMobile.setTitle("Mobile : Execução de Testes");


		listApps.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				javax.swing.JList lista = (javax.swing.JList)e.getSource();
				javax.swing.DefaultListModel<Object> itensInput = new javax.swing.DefaultListModel<Object>();

				System.out.println("INFO : ITEM DA LISTA ALTERADO....");
			}
		});


		formMobile.setSize(new java.awt.Dimension(
				(int)java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds().getWidth(),
				(int)java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds().getHeight()-50
				));

		formMobile.getContentPane().setBackground(java.awt.Color.WHITE);


		btRemoverApp.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("INFO : DESINSTALANDO APLICATIVO : ".concat(listApps.getSelectedValue().toString()));
				System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb shell pm uninstall %s",listApps.getSelectedValue().toString().trim())));
				listApps.setModel(AUTAPI.AUTFormularioUtils.carregarFonteDados(2,"package:","\"powershell\" \"adb shell pm list packages | ForEach-Object -process{$_.replace('package:','')};\""));
				AUTAPI.AUTFormularioUtils.exibirMensagem(AUTAPI.AUTFormularioUtils.AUT_TIPO_MSG_USUARIO.INFORMATIVA,
						"Softtek : Assistente de Configuração",
						"APLICATIVO DESINSTALADO COM SUCESSO!!!");								
			}
		});


		btUploadApp.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String arquivo = AUTAPI.gerarCaixaDialog(formMobile);
				String arquivoDestino = "/sdcard/aut_apks/".concat(java.nio.file.Paths.get(arquivo).getFileName().toString());
				if(arquivo.contains(".apk")) {
					System.out.println("INFO : FAZENDO UPLOAD DO ARQUIVO DE INSTALAÇÃO : APK");
					System.out.println("ARQUIVO : ".concat(java.nio.file.Paths.get(arquivo).getFileName().toString()));
					System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso("adb shell mkdir /sdcard/aut_apks"));
					System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb push \"%s\" \"%s\"",arquivo,arquivoDestino)));
					System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb shell pm install \"%s\"",arquivoDestino)));
					listApps.setModel(AUTAPI.AUTFormularioUtils.carregarFonteDados(2,"package:","\"powershell\" \"adb shell pm list packages | ForEach-Object -process{$_.replace('package:','')};\""));
					AUTAPI.AUTFormularioUtils.exibirMensagem(AUTAPI.AUTFormularioUtils.AUT_TIPO_MSG_USUARIO.INFORMATIVA,
							"Softtek : Assistente de Configuração",
							"APLICATIVO INSTALADO COM SUCESSO!!!");
				}
				else {
					System.out.println("INFO ARQUIVO INVÁLIDO PARA INSTALAÇÃO DE APLICATIVO");
					AUTAPI.AUTFormularioUtils.exibirMensagem(AUTAPI.AUTFormularioUtils.AUT_TIPO_MSG_USUARIO.ATENCAO_ALERTA,
							"Softtek : Assistente de Configuração",
							"APLICATIVO PARA INSTALAÇÃO INVÁLIDO");
				}
			}
		});
		pnBotoes.setLayout(new java.awt.GridLayout(1,3));
		pnBotoes.add(btTestar);
		pnBotoes.add(btCarregarAplicativos);
		pnBotoes.add(btAtualizar);


		pnBotoes.setBackground(java.awt.Color.WHITE);
		pnDevicesUSB.setBackground(java.awt.Color.WHITE);
		configLayoutPrincipal.fill = configLayoutPrincipal.BOTH;
		configLayoutPrincipal.gridx = 0;		
		configLayoutPrincipal.gridy = 0;
		configLayoutPrincipal.weightx = 1;
		configLayoutPrincipal.weighty = 1;

		pnDevicesUSB.add(lbDevices,configLayoutPrincipal);
		configLayoutPrincipal.gridy = 1;
		pnDevicesUSB.add(pnDevicesConnect,configLayoutPrincipal);
		configLayoutPrincipal.gridy = 2;
		pnDevicesUSB.add(pnBotoes,configLayoutPrincipal);

		configLayoutPrincipal.gridx = 0;		
		configLayoutPrincipal.gridy = 3;

		formMobile.add(pnDevicesUSB,configLayoutPrincipal);

		configLayoutPrincipal.gridx = 0;
		configLayoutPrincipal.gridy = 4;
		formMobile.add(pnApps,configLayoutPrincipal);

		configLayoutPrincipal.gridx = 0;
		configLayoutPrincipal.gridy = 5;
		formMobile.add(pnAtividades,configLayoutPrincipal);

		configLayoutPrincipal.gridx = 2;
		configLayoutPrincipal.gridy = 0;
		configLayoutPrincipal.gridheight = 10;
		configLayoutPrincipal.fill = configLayoutPrincipal.BOTH;
		javax.swing.DefaultListModel<javax.swing.JComponent> dadosImg = new javax.swing.DefaultListModel<javax.swing.JComponent>();
		AUTAPI.AUTPainelImagem item1 = new AUTAPI.AUTPainelImagem("C:\\Repositorios\\img001.png", 0, 0, 500, 800);

		formMobile.add(item1,configLayoutPrincipal);

		/**
		 * 
		 * Função específica para testes de conexão com o dispositivo movel conectado via USB
		 * 
		 */
		btAtualizar.addActionListener(new java.awt.event.ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {

				javax.swing.DefaultListModel dados = (javax.swing.DefaultListModel)listDevices.getModel();

				/*
				if(
						
						!AUTDispositivoConfiguracao.buscarIP().trim().isEmpty() 
						&& !dados.contains(AUTDispositivoConfiguracao.buscarIP().trim())
						) {
					
					dados.addElement(AUTDispositivoConfiguracao.buscarIP().trim());
				}				
*/
				dados.addElement(AUTDispositivoConfiguracao.buscarIP().trim());
				dados.addElement(AUTDispositivoConfiguracao.buscarHostNome());
			}
		});	

		btCarregarAplicativos.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!listDevices.isSelectionEmpty()) {
					listApps.setModel(AUTAPI.AUTFormularioUtils.carregarFonteDados(2,"package:","\"powershell\" \"adb shell pm list packages | ForEach-Object -process{$_.replace('package:','')};\""));
				}
				else {
					AUTAPI.AUTFormularioUtils.exibirMensagem(AUTAPI.AUTFormularioUtils.AUT_TIPO_MSG_USUARIO.ATENCAO_ALERTA,
							"Softtek : Assistente de Configuração",
							"SELECIONE UM DISPOSITIVO DISPONÍVEL NA LISTA, CASO NÃO TENHA NENHUM CLIQUE EM ATUALIZAR!!!!");
				}
			}
		});


		listDevices.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(!listDevices.isSelectionEmpty()) {
					listApps.setModel(AUTAPI.AUTFormularioUtils.carregarFonteDados(2,"package:","\"powershell\" \"adb shell pm list packages | ForEach-Object -process{$_.replace('package:','')};\""));					
				}
				else {

				}				
			}
		});

		javax.swing.JPopupMenu menuListDevices = new javax.swing.JPopupMenu();
		javax.swing.JMenuItem menuHabilitarWifi = new javax.swing.JMenuItem("Habilitar Acesso Wifi");
		javax.swing.JMenuItem menuDesHabilitarWifi = new javax.swing.JMenuItem("Desabilitar Acesso Wifi");


		menuHabilitarWifi.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!listDevices.isSelectionEmpty()) {
					AUTDispositivoConfiguracao.configurarConexaoTCPIP(listDevices.getSelectedValue().toString());

					AUTAPI.AUTFormularioUtils.exibirMensagem("Softtek - Assistente de Configuração", "SERVIÇO DE ACESSO VIA WIFI HABILITADO, DESCONECTE O CABO USB E FAÇA O TESTE");
				}
				else {
					AUTAPI.AUTFormularioUtils.exibirMensagem("Softtek - Assistente de Configuração", "SELECIONE O DISPOSITIVO QUE DESEJA HABILITAR O ACESSO VIA WIFI");
				}
			}	
		});


		menuDesHabilitarWifi.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!listDevices.isSelectionEmpty()) {
					AUTDispositivoConfiguracao.configurarConexaoUSB(listDevices.getSelectedValue().toString());

					AUTAPI.AUTFormularioUtils.exibirMensagem("Softtek - Assistente de Configuração", "SERVIÇO DE ACESSO VIA WIFI DESABILITADO, CONECTE O CABO USB E FAÇA O TESTE");
				}
				else {
					AUTAPI.AUTFormularioUtils.exibirMensagem("Softtek - Assistente de Configuração", "SELECIONE O DISPOSITIVO QUE DESEJA DESABILITAR O ACESSO POR WIFI");
				}
			}	
		});

		menuListDevices.add(menuHabilitarWifi);
		menuListDevices.add(menuDesHabilitarWifi);


		listDevices.setComponentPopupMenu(menuListDevices);

		btDownloadApp.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String diretorioSaida = "";
				java.util.List<String> itensApk = new java.util.ArrayList<String>();

				if(!listApps.isSelectionEmpty()) {
					diretorioSaida = AUTAPI.gerarCaixaDialog(formMobile);

					for(Object app: listApps.getSelectedValuesList()) {
						String apk = AUTAPI.AUTProcessoExternoUtils.executarProcesso("\"powershell\" \"adb shell pm path ".concat(app.toString().trim()).concat(" | ForEach-Object -process{$_.replace('package:','')};\"")).toString();
						if(!apk.trim().isEmpty()) {
							itensApk.add(app.toString().concat(":").concat(apk));
						}						
					}

					for(String apk:itensApk) {
						String pacote = apk.split(":")[0].trim();
						String apkInst = apk.split(":")[1].trim();

						System.out.println("INFO : APP SELECIONADO : ".concat(apk));
						System.out.println("PACOTE: ".concat(pacote));
						System.out.println("URL:".concat(apkInst));

						System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("\"powershell\" \"adb pull '%s' '%s'\"",apkInst,diretorioSaida)).toString());


						System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("\"powershell\" \"adb shell pm dump '%s'\"",pacote)).toString());
					}

				}
				else {
					AUTAPI.AUTFormularioUtils.exibirMensagem("Softtek : Assistente de Configuração", "SELECIONE UM OU MAIS APLICATIVOS PARA BAIXAR DO DISPOSITIVO");
				}

				System.out.println("INFO: DIRETORIO DE SAIDA SELECIONADO PELO USUÁRIO : ".concat(diretorioSaida));

			}
		});

		formMobile.addWindowListener(new java.awt.event.WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent e) {
				
				

			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpened(WindowEvent e) {

				System.out.println("INFO : JANELA INICIALIZADA COM SUCESSO!!!!!!");

				javax.swing.DefaultListModel dados = new javax.swing.DefaultListModel();

				dados.addElement(AUTDispositivoConfiguracao.buscarIP().trim());

				listDevices.setModel(dados);
			}

		});
		formMobile.setVisible(true);
	}


	/**
	 * 
	 * Construtor padrão
	 * 
	 */
	public AUTFormMobileTesteManual() {
		configGUI();
	}

	public AUTFormMobileTesteManual(javax.swing.JDialog form) {
		this.formPrincipal = form;
		configGUI();
	}
}
