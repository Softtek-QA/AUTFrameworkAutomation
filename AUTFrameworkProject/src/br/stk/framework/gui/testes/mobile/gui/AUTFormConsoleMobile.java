package br.stk.framework.gui.testes.mobile.gui;

import java.awt.Event;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.HierarchyEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.event.ChangeEvent;

import com.sun.org.apache.xerces.internal.impl.dv.dtd.NMTOKENDatatypeValidator;

import br.stk.framework.api.AUTAPI;
import br.stk.framework.api.AUTAPI.AUTFormularioUtils;
import br.stk.framework.api.AUTAPI.AUTPainelImagem;
import br.stk.framework.api.AUTAPI.AUTProcessoParalelo;
import br.stk.framework.api.runtime.AUTRuntimeConfiguration;
import br.stk.framework.gui.testes.mobile.gui.AUTFormMobileConector.AUTAndroidInterface;
import br.stk.framework.gui.testes.mobile.gui.AUTFormMobileConector.AUTDispositivoConfiguracao;
import br.stk.framework.gui.testes.mobile.gui.AUTFormMobileConector.AUTDispositivoConfiguracao.AUT_ANDROID_CONFIGURACAO_IP;


/**
 * 
 * Interface de gerenciamento mobile
 * 
 * 
 * @author SOFTTEK - QA
 * 
 * 
 *
 */
public class AUTFormConsoleMobile {
	public static java.util.List<AUTFormConsoleMobile> AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS = new java.util.ArrayList<AUTFormConsoleMobile>();
	public static Integer AUT_ID_MOB_CONSOLE = 0;
	Integer autIdClasse = 0 ;
	javax.swing.JPopupMenu menusDirectDevice = new javax.swing.JPopupMenu();
	javax.swing.JMenuItem menuItemComandoADB = new javax.swing.JMenuItem("Enviar Sequência de Comandos");		
	javax.swing.JMenuItem mnEnvParametro = new javax.swing.JMenuItem("Dados");
	javax.swing.JMenuItem mnEnvAtualizarMenu = new javax.swing.JMenuItem("Atualizar Lista Comandos");		
	java.util.HashMap<String,java.util.HashMap<String,Object>> ltCmdTerminal = new java.util.HashMap<String,java.util.HashMap<String,Object>>();	
	javax.swing.JDialog formPrincipal = null;
	java.util.List<AUTAPI.AUTPainelImagem> listIMGS = new java.util.ArrayList<AUTAPI.AUTPainelImagem>();
	String tituloApp = "SOFTTEK - GERENCIAMENTO DE DISPOSITIVO MOBILE: %s";
	String nmDispositivo = "";
	Integer posCorrente = 0;
	Integer posStepEdit = 0;
	Integer posStepCorrente = 0;

	javax.swing.JScrollPane scrIMG = new javax.swing.JScrollPane(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	
	public static interface IAUTObjectFunction{
		public void executarFuncao();
	}
	
	public static abstract class AUTMouseControls extends AUTProcessoParalelo implements java.awt.event.MouseListener{
		public abstract void executarQuandoMouseClick();
		public abstract void executarQuandoMouseSobreElemento();
		public abstract void executarQuandoMouseDeixarElemento();
		public abstract void executarQuandoTeclaMousePressionada();
		public abstract void executarQuandoTeclaMouseLiberada();

		@Override
		public void mouseClicked(MouseEvent e) {

			System.out.println("INFOMOUSE: MOUSE CLICADO");
			executarQuandoMouseClick();		

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			System.out.println("INFOMOUSE: MOUSE ENTROU NO ELEMENTO");
			executarQuandoMouseClick();				
		}

		@Override
		public void mouseExited(MouseEvent e) {
			System.out.println("INFOMOUSE: MOUSE SAIU DO ELEMENTO");
			executarQuandoMouseDeixarElemento();			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("INFOMOUSE: TECLA MOUSE PRECIONADA");
			executarQuandoTeclaMousePressionada();			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("INFOMOUSE: TECLA MOUSE LIBERADA");
			executarQuandoTeclaMouseLiberada();			
		}

		@Override
		protected void rotinasInicializacao() {
			// TODO Auto-generated method stub

		}

		@Override
		protected void rotinasExecucao() {
			/*
			executarQuandoMouseClick();
			executarQuandoMouseSobreElemento();
			executarQuandoMouseDeixarElemento();
			executarQuandoTeclaMousePressionada();
			executarQuandoTeclaMouseLiberada();	
			 */	

		}
		@Override
		protected void rotinasFinalizacao() {
			// TODO Auto-generated method stub

		}
		@Override
		protected void rotinasMonitoramentoProcesso() {
			// TODO Auto-generated method stub

		}		
	}


	/**
	 * Classe terminal de comandos
	 * 
	 * @author Softtek - QA
	 *
	 */
	public static class AUTFormTerminalComandos{
		
		
		public static enum AUT_ADB_CONFIG_TERMINAL{
			RECURSO_NOME_DEVICE,
			RECURSO_TERMINAL_PADRAO,
			RECURSO_MOUSE,
			RECURSO_TECLADO,
			RECURSO_JOYSTICK,
			RECURSO_TOUCH_NAVEGACAO,
			RECURSO_TOUCHPAD,
			RECURSO_TRACKBALL,
			RECURSO_STYLUS,
			RECURSO_DPAD,
			RECURSO_TOUCH_SCREEN,
			RECURSO_GAMEPAD,
			RECURSO_EVENTO_TECLADO,
			CONFIG_TERMINAL_PADRAO_PARCIAL,
			CONFIG_EVENTO_TECLADO_CMD_PARCIAL,
			CONFIG_ENVIO_DADOS_CMD_PARCIAL,
			CONFIG_CMD_PARCIAL,
			ACAO_ENVIO_CARACTERES_COMO_UNIDADE,
			ACAO_ENVIO_DIG_NUMERO_COMO_UNIDADE,
			ACAO_ENVIO_DIG_LETRA_COMO_UNIDADE,
			ACAO_ENVIO_COMANDO_MAIS_CARACTERES_COMO_UNIDADE,
			ACAO_ENVIO_COMANDO_MAIS_DIG_NUMERO_COMO_UNIDADE,
			ACAO_ENVIO_COMANDO_MAIS_DIG_LETRA_COMO_UNIDADE,
			ACAO_ENVIO_COMANDO_TECLADO;
			@Override
			public String toString() {
				switch(this) {
				case RECURSO_NOME_DEVICE:
				{
					return "%s";
				}
				case RECURSO_TERMINAL_PADRAO:
				{
					return "adb -s %s shell %s";
				}
				case CONFIG_TERMINAL_PADRAO_PARCIAL:{
					return "adb -s %s shell ";
				}
				case CONFIG_CMD_PARCIAL:{
					return "%s;";
				}
				case CONFIG_EVENTO_TECLADO_CMD_PARCIAL:{
					return String.format(CONFIG_CMD_PARCIAL.toString(), "input keyevent %s");
				}
				case CONFIG_ENVIO_DADOS_CMD_PARCIAL:{
					return String.format(CONFIG_CMD_PARCIAL.toString(), "input keyboard text \"%s\"");
				}
				case RECURSO_EVENTO_TECLADO:{
					return String.format(RECURSO_TERMINAL_PADRAO.toString(),RECURSO_NOME_DEVICE.toString(),"input keyevent %s");
				}
				case RECURSO_MOUSE:
				{
					return String.format(RECURSO_TERMINAL_PADRAO.toString(),RECURSO_NOME_DEVICE.toString(),"input mouse %s %s");
				}
				case RECURSO_JOYSTICK:{
					return String.format(RECURSO_TERMINAL_PADRAO.toString(),RECURSO_NOME_DEVICE.toString(),"input joystick %s");
				}
				case RECURSO_TOUCH_NAVEGACAO:{
					return String.format(RECURSO_TERMINAL_PADRAO.toString(),RECURSO_NOME_DEVICE.toString(),"input touchnavigation %s");
				}
				case RECURSO_TOUCHPAD:{
					return String.format(RECURSO_TERMINAL_PADRAO.toString(),RECURSO_NOME_DEVICE.toString(),"input touchpad %s");
				}
				case RECURSO_TRACKBALL:{
					return String.format(RECURSO_TERMINAL_PADRAO.toString(),RECURSO_NOME_DEVICE.toString(),"input trackball %s");
				}
				case RECURSO_STYLUS:{
					return String.format(RECURSO_TERMINAL_PADRAO.toString(),RECURSO_NOME_DEVICE.toString(),"input stylus %s");
				}
				case RECURSO_TOUCH_SCREEN:{
					return String.format(RECURSO_TERMINAL_PADRAO.toString(),RECURSO_NOME_DEVICE.toString(),"input touchscreen %s");
				}
				case RECURSO_GAMEPAD:{
					return String.format(RECURSO_TERMINAL_PADRAO.toString(),RECURSO_NOME_DEVICE.toString(),"input gamepad %s");
				}
				case RECURSO_TECLADO:{
					return String.format(RECURSO_TERMINAL_PADRAO.toString(),RECURSO_NOME_DEVICE.toString(),"input keyboard %s");
				}
				case ACAO_ENVIO_COMANDO_TECLADO:
				{
					return RECURSO_EVENTO_TECLADO.toString();
				}
				case ACAO_ENVIO_CARACTERES_COMO_UNIDADE:
				{
					return String.format(RECURSO_TECLADO.toString(), RECURSO_NOME_DEVICE.toString(),"text \"%s\"");
				}
				case ACAO_ENVIO_DIG_NUMERO_COMO_UNIDADE:
				{
					return String.format(RECURSO_TECLADO.toString(), RECURSO_NOME_DEVICE.toString(),"text %s");
				}
				case ACAO_ENVIO_DIG_LETRA_COMO_UNIDADE:
				{
					return String.format(RECURSO_TECLADO.toString(), RECURSO_NOME_DEVICE.toString(),"text \"%s\"");
				}
				case ACAO_ENVIO_COMANDO_MAIS_CARACTERES_COMO_UNIDADE:
				{
					return String.format(RECURSO_TECLADO.toString(), RECURSO_NOME_DEVICE.toString(),"text \"%s\"");
				}
				case ACAO_ENVIO_COMANDO_MAIS_DIG_LETRA_COMO_UNIDADE:
				{
					return String.format(RECURSO_TECLADO.toString(), RECURSO_NOME_DEVICE.toString(),"text %s");
				}
				case ACAO_ENVIO_COMANDO_MAIS_DIG_NUMERO_COMO_UNIDADE:
				{
					return String.format(RECURSO_TECLADO.toString(), RECURSO_NOME_DEVICE.toString(),"text \"%s\"");
				}
				}
				return super.toString();
			}

		}

		private javax.swing.JDialog formPrincipal = null;
		String tituloApp = "Softtek : Assistente de Configuração : %s";
		private String nmDevice = "DEV";
		private Object[] comandosTerminal = new Object[] {"DPAD_LEFT","DPAD_RIGHT","DPAD_DOWN","DPAD_UP","START","HOME"};
		private String configCMDADB = "adb -s %s shell input %s %s";
		public static class AUTDeviceTerminal extends br.stk.framework.gui.testes.mobile.gui.AUTFormMobileConector.AUTDispositivoConfiguracao{


			public static Object[] carregarComandosTerminal() {
				try {
					java.util.List<Object> comandos = new java.util.ArrayList<Object>();

					for(AUT_ADB_CONFIG_TERMINAL itemCmd : AUT_ADB_CONFIG_TERMINAL.values()) {
						if(itemCmd.name().contains("ACAO_")) {
							comandos.add(itemCmd.name());
						}
					}		

					return comandos.toArray();
				}
				catch(java.lang.Exception e) {

					System.out.println("INFO LOADER TERMINAL : NAO FOI POSSIVEL CARREGAR A LISTA DE COMANDOS DEFINIDAS NO TERMINAL");

					System.out.println(e.getMessage());

					e.printStackTrace();

					return new Object[] {};
				}				
			}

			public static String configEnvDadosTerminal(AUT_ADB_CONFIG_TERMINAL terminalOption,String nomeDispositivo,String eventoTeclado,Object dados,Integer tempoDelay) {
				String cmdOut = "";

				try {
					System.out.println("TERMINAL : COMANDO SELECIONADO");

					switch(terminalOption) {
					case ACAO_ENVIO_CARACTERES_COMO_UNIDADE:{
						cmdOut  = AUT_ADB_CONFIG_TERMINAL.ACAO_ENVIO_CARACTERES_COMO_UNIDADE.toString();

						cmdOut = String.format(cmdOut, nomeDispositivo,dados);

						System.out.println(AUT_ADB_CONFIG_TERMINAL.ACAO_ENVIO_CARACTERES_COMO_UNIDADE.name());
						System.out.println(cmdOut);

						break;
					}
					case ACAO_ENVIO_DIG_LETRA_COMO_UNIDADE:{

						cmdOut = AUT_ADB_CONFIG_TERMINAL.ACAO_ENVIO_DIG_LETRA_COMO_UNIDADE.toString();

						cmdOut = String.format(cmdOut, nomeDispositivo,dados);

						System.out.println(AUT_ADB_CONFIG_TERMINAL.ACAO_ENVIO_DIG_LETRA_COMO_UNIDADE.name());
						System.out.println(cmdOut);

						break;
					}
					case ACAO_ENVIO_DIG_NUMERO_COMO_UNIDADE:{

						cmdOut = AUT_ADB_CONFIG_TERMINAL.ACAO_ENVIO_DIG_NUMERO_COMO_UNIDADE.toString();

						cmdOut = String.format(cmdOut, nomeDispositivo,dados);

						System.out.println(AUT_ADB_CONFIG_TERMINAL.ACAO_ENVIO_DIG_NUMERO_COMO_UNIDADE.name());
						System.out.println(cmdOut);

						break;
					}	
					case ACAO_ENVIO_COMANDO_MAIS_CARACTERES_COMO_UNIDADE:{

						cmdOut = AUT_ADB_CONFIG_TERMINAL.CONFIG_TERMINAL_PADRAO_PARCIAL.toString().concat(
								AUT_ADB_CONFIG_TERMINAL.CONFIG_EVENTO_TECLADO_CMD_PARCIAL.toString()).concat(AUT_ADB_CONFIG_TERMINAL.CONFIG_ENVIO_DADOS_CMD_PARCIAL.toString());

						cmdOut = String.format(cmdOut, nomeDispositivo,eventoTeclado,dados);							

						System.out.println(AUT_ADB_CONFIG_TERMINAL.ACAO_ENVIO_COMANDO_MAIS_CARACTERES_COMO_UNIDADE.name());
						System.out.println(cmdOut);

						break;
					}
					case ACAO_ENVIO_COMANDO_MAIS_DIG_LETRA_COMO_UNIDADE:{

						cmdOut = AUT_ADB_CONFIG_TERMINAL.CONFIG_TERMINAL_PADRAO_PARCIAL.toString().concat(
								AUT_ADB_CONFIG_TERMINAL.CONFIG_EVENTO_TECLADO_CMD_PARCIAL.toString()).concat(AUT_ADB_CONFIG_TERMINAL.CONFIG_ENVIO_DADOS_CMD_PARCIAL.toString());

						cmdOut = String.format(cmdOut, nomeDispositivo,eventoTeclado,dados);


						System.out.println(AUT_ADB_CONFIG_TERMINAL.ACAO_ENVIO_COMANDO_MAIS_DIG_LETRA_COMO_UNIDADE.name());
						System.out.println(cmdOut);

						break;
					}
					case ACAO_ENVIO_COMANDO_MAIS_DIG_NUMERO_COMO_UNIDADE:{

						cmdOut = AUT_ADB_CONFIG_TERMINAL.CONFIG_TERMINAL_PADRAO_PARCIAL.toString().concat(
								AUT_ADB_CONFIG_TERMINAL.CONFIG_EVENTO_TECLADO_CMD_PARCIAL.toString()).concat(AUT_ADB_CONFIG_TERMINAL.CONFIG_ENVIO_DADOS_CMD_PARCIAL.toString());

						cmdOut = String.format(cmdOut, nomeDispositivo,eventoTeclado,dados);


						System.out.println(AUT_ADB_CONFIG_TERMINAL.ACAO_ENVIO_COMANDO_MAIS_DIG_NUMERO_COMO_UNIDADE.name());
						System.out.println(cmdOut);

						break;
					}
					case ACAO_ENVIO_COMANDO_TECLADO:{

						cmdOut = AUT_ADB_CONFIG_TERMINAL.ACAO_ENVIO_COMANDO_TECLADO.toString();
						cmdOut = String.format(cmdOut, nomeDispositivo,eventoTeclado);


						System.out.println(AUT_ADB_CONFIG_TERMINAL.ACAO_ENVIO_COMANDO_TECLADO.name());
						System.out.println(cmdOut);

						break;
					}
					}

					return cmdOut;

				}
				catch(java.lang.Exception e) {
					System.out.println("INFO ERROR DEF CMD TERMINAL : NAO FOI POSSIVEL CONFIGURAR O COMANDO TERM");
					System.out.println(e.getMessage());
					e.printStackTrace();

					return cmdOut;
				}
			}

			public void enviarComando(String comando){

			}

			public AUTDeviceTerminal() {

			}
		}

		public javax.swing.JPanel gerarLinhaDadosGUI(int totalColunas,int tamanhoPadrao) {
			javax.swing.JPanel pnOut = new javax.swing.JPanel();
			java.awt.GridBagConstraints configLayout = new java.awt.GridBagConstraints();
			pnOut.setBackground(java.awt.Color.WHITE);
			pnOut.setLayout(new java.awt.GridBagLayout());


			configLayout.gridx = 0;
			configLayout.gridy = 0;
			configLayout.weightx = 1;
			configLayout.weighty = 1;
			configLayout.fill = configLayout.BOTH;

			for(int i = 0; i < totalColunas;i++) {
				configLayout.gridx = i;
				pnOut.add(new javax.swing.JTextField(tamanhoPadrao),configLayout);
			}



			return pnOut;
		}
		
		public void initDataApp() {
			System.out.println("&&& INFO : CONFIGURANDO DADOS DE INICIALIZACAO DA APLICACAO");
			
			AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posCorrente = 0;
			AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posStepCorrente = 0;
			AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posStepEdit = 0;
			AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).ltCmdTerminal.clear();
			
			AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).listIMGS.clear();
			
		}
		
		public void configGUI() {
			initDataApp();
			javax.swing.JDialog formTerminal = new javax.swing.JDialog(formPrincipal);
			formTerminal.setName(String.format("AUT_FORM_TERMINAL_%s",AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.size()));
			
			javax.swing.JPanel pnInputComando = new javax.swing.JPanel();
			javax.swing.JPanel pnOutputConfig = new javax.swing.JPanel();
			javax.swing.JScrollPane scrOutput = new javax.swing.JScrollPane(pnOutputConfig,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

			javax.swing.JLabel lbStepTarget = new javax.swing.JLabel("PASSO:");
			javax.swing.JLabel lbTipoComando = new javax.swing.JLabel("Tipo Ação");
			javax.swing.JLabel lbInputComand = new javax.swing.JLabel("Comando");
			javax.swing.JLabel lbValorEntrada = new javax.swing.JLabel("Valor de Entrada");
			javax.swing.JLabel lbOutputComponente = new javax.swing.JLabel("Componente");
			javax.swing.JLabel lbOutputComand = new javax.swing.JLabel("Resulta Esperado");
			javax.swing.JLabel lbAdicionarComando = new javax.swing.JLabel();
			javax.swing.JLabel lbPlayExecutionComands = new javax.swing.JLabel();

			javax.swing.JComboBox cbStepTarget = new javax.swing.JComboBox(new Object[] {"STEP 1"});
			javax.swing.JComboBox cbTipoComando = new javax.swing.JComboBox(AUTDeviceTerminal.carregarComandosTerminal());
			javax.swing.JComboBox cbInputComand = new javax.swing.JComboBox(comandosTerminal);
			javax.swing.JComboBox cbValorEntrada = new javax.swing.JComboBox(new Object[] {""});
			javax.swing.JComboBox cbOutputComponente = new javax.swing.JComboBox(new Object[] {""});
			javax.swing.JComboBox cbOutputComand = new javax.swing.JComboBox(new Object[] {""});

			javax.swing.DefaultComboBoxModel<String> ddStepTarget = new javax.swing.DefaultComboBoxModel();
			cbStepTarget.setModel(ddStepTarget);

			java.util.regex.Pattern padraoExpDigitos = java.util.regex.Pattern.compile("\\d+");


			cbValorEntrada.setEditable(true);
			cbInputComand.setEditable(true);
			cbOutputComponente.setEditable(true);
			cbOutputComand.setEditable(true);

			pnOutputConfig.setBackground(java.awt.Color.WHITE);


			java.awt.event.MouseListener listenerLabelControlStep = new java.awt.event.MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					javax.swing.JLabel lb = (javax.swing.JLabel)e.getSource();

					lb.setForeground(java.awt.Color.YELLOW);					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					javax.swing.JLabel lb = (javax.swing.JLabel)e.getSource();

					lb.setForeground(java.awt.Color.GREEN);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					javax.swing.JLabel lb = (javax.swing.JLabel)e.getSource();

					lb.setForeground(java.awt.Color.BLUE);

				}

				@Override
				public void mousePressed(MouseEvent e) {



				}

				@Override
				public void mouseReleased(MouseEvent e) {
					javax.swing.JLabel lb = (javax.swing.JLabel)e.getSource();

					lb.setForeground(java.awt.Color.BLUE);

				}				
			};


			lbAdicionarComando.addMouseListener(listenerLabelControlStep);
			lbPlayExecutionComands.addMouseListener(listenerLabelControlStep);


			AUTProcessoParalelo procExecCMD = new AUTProcessoParalelo() {

				@Override
				protected void rotinasInicializacao() {
					// TODO Auto-generated method stub
					
				}

				@Override
				protected void rotinasExecucao() {
					// TODO Auto-generated method stub
					System.out.println("\n\n\n********   AUTOMAÇÃO MOBILE : INICIALIZANDO SCRIPT DE AUTOMAÇÃO  ********");
					java.util.regex.Matcher analise = null;
					
					Object[] keysCmd = AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).ltCmdTerminal.keySet().toArray();
					
					java.util.Arrays.sort(keysCmd);
					
					for(Object step : keysCmd) {
						
						analise = padraoExpDigitos.matcher(step.toString());
						
						if(analise.find()) {
							Integer numSt = Integer.parseInt(analise.group());
							if(numSt <= AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posStepEdit) {
								System.out.println("\n\n\b\b\b*********** STEP ***********");
								System.out.println(String.format("PASSO Nº: %s", analise.group()));
							}
							else {
								//break;
							}
						}
						
						int contAct = 1 ;
						for(Object actItem : AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).ltCmdTerminal.get(step).values()) {
							System.out.println(String.format("ACAO Nº: ", contAct));
							System.out.println(actItem.toString());
							
							System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(actItem.toString()));

							System.out.println("INFO: CARREGANDO IMAGEM DA TELA ATUAL DO DISPOSITIVO CONECTADO");	

							AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).listIMGS.add(AUTAndroidInterface.AUTAndroidObject.carregarImagem(nmDevice,AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posCorrente,AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG.getSize().width,AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG.getSize().height));

							AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG.add(AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).listIMGS.get(AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posCorrente));
							AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG.setViewportView(AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).listIMGS.get(AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posCorrente));

							AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posCorrente++;
						
						}
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
			
			lbPlayExecutionComands.addMouseListener(new java.awt.event.MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {

					procExecCMD.executarProcesso();
					
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
			cbTipoComando.addItemListener(new java.awt.event.ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent obj) {
					javax.swing.JComboBox cb = (javax.swing.JComboBox)obj.getSource();

					AUT_ADB_CONFIG_TERMINAL cmd = AUT_ADB_CONFIG_TERMINAL.valueOf(cb.getSelectedItem().toString());

					AUTDeviceTerminal.configEnvDadosTerminal(cmd, nmDevice, cbInputComand.getSelectedItem().toString(), cbValorEntrada.getSelectedItem().toString(), 0);

				}

			});

			lbAdicionarComando.addMouseListener(new java.awt.event.MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {

					System.out.println("INFO CONSOLE : START PROCESS ADD");					
					
					for(java.awt.Component comp : pnOutputConfig.getComponents()) {
						/*
						txtEntradaComando.setName(String.format("PRM_COMANDO_%s",i));
						txtEntradaPosterior.setName(String.format("PRM_DADOS_%s",i));
						txtComandoOutput.setName(String.format("PRM_RESULT_ESPERADO_%s",i));
						txtComponenteOutput.setName(String.format("PRM_COMPONENTE_%s",i));
						 */

						try {
							if(comp.getName().contains("PRM_")) {
								javax.swing.JTextField txt = (javax.swing.JTextField)comp;

								if(comp.getName().equals(String.format("PRM_COMANDO_%s",AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posStepEdit))) {

									txt.setText(cbInputComand.getSelectedItem().toString());
								}
								else if(comp.getName().equals(String.format("PRM_DADOS_%s",AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posStepEdit))) {
									txt.setText(cbValorEntrada.getSelectedItem().toString());
								}
								else if(comp.getName().equals(String.format("PRM_RESULT_ESPERADO_%s",AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posStepEdit))) {
									txt.setText(cbOutputComand.getSelectedItem().toString());
								}
								else if(comp.getName().equals(String.format("PRM_COMPONENTE_%s",AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posStepEdit))) {
									txt.setText(cbOutputComponente.getSelectedItem().toString());
								}
							}

							String nomeElementoGUI = "";
							try {
								nomeElementoGUI = comp.getName();
							}
							catch(java.lang.Exception e) {
								nomeElementoGUI = "";
							}

							if(nomeElementoGUI.equals(String.format("STEP_START_%s", AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posStepEdit))) {
								
								switch(AUT_ADB_CONFIG_TERMINAL.valueOf(cbTipoComando.getSelectedItem().toString())) {
								case ACAO_ENVIO_COMANDO_MAIS_CARACTERES_COMO_UNIDADE:{

									System.out.println("ADICIONANDO COMANDO ADB - EVENT: ");
									System.out.println(AUT_ADB_CONFIG_TERMINAL.ACAO_ENVIO_COMANDO_MAIS_CARACTERES_COMO_UNIDADE.name());

									
									break;
								}
								case ACAO_ENVIO_COMANDO_MAIS_DIG_LETRA_COMO_UNIDADE:{
									System.out.println("ADICIONANDO COMANDO ADB - EVENT: ");
									System.out.println(AUT_ADB_CONFIG_TERMINAL.ACAO_ENVIO_COMANDO_MAIS_DIG_LETRA_COMO_UNIDADE.name());

									break;
								}
								case ACAO_ENVIO_COMANDO_MAIS_DIG_NUMERO_COMO_UNIDADE:{
									System.out.println("ADICIONANDO COMANDO ADB - EVENT: ");
									System.out.println(AUT_ADB_CONFIG_TERMINAL.ACAO_ENVIO_COMANDO_MAIS_DIG_NUMERO_COMO_UNIDADE.name());

									break;
								}
								default:{
									System.out.println("ADICIONANDO COMANDO ADB - PADRAO: ");
									System.out.println(comp.getName());
								}

								}
								
								AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).ltCmdTerminal.get(nomeElementoGUI).put(AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posStepEdit.toString(), AUTDeviceTerminal.configEnvDadosTerminal(AUT_ADB_CONFIG_TERMINAL.valueOf(cbTipoComando.getSelectedItem().toString()), 
										nmDevice, cbInputComand.getSelectedItem().toString(), cbValorEntrada.getSelectedItem().toString(), 2));
							}

						}
						catch(java.lang.Exception e) {

						}

					}


					AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posStepEdit++;

				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}



			});


			lbAdicionarComando.setText("\u271A");
			lbAdicionarComando.setFont(lbAdicionarComando.getFont().deriveFont(20f));
			lbAdicionarComando.setForeground(java.awt.Color.BLUE);

			lbAdicionarComando.setName("CMD_ADD_COMMAND");
			lbPlayExecutionComands.setName("CMD_PLAY_EXEC");
			lbPlayExecutionComands.setText("\u25BA");
			lbPlayExecutionComands.setFont(lbAdicionarComando.getFont());
			lbPlayExecutionComands.setForeground(java.awt.Color.BLUE);



			formTerminal.setSize(new java.awt.Dimension(800,400));
			formTerminal.getContentPane().setBackground(java.awt.Color.WHITE);
			formTerminal.setTitle("Assistente de Configuração: Terminal de Comando");

			formTerminal.setLocationRelativeTo(formPrincipal);
			java.awt.GridBagConstraints configLayout = new java.awt.GridBagConstraints();

			formTerminal.setLayout(new java.awt.GridBagLayout());

			pnInputComando.setBackground(java.awt.Color.WHITE);
			java.awt.GridBagConstraints configInput = new java.awt.GridBagConstraints();
			pnInputComando.setLayout(new java.awt.GridBagLayout());

			javax.swing.JPanel pnControlsSteps = new javax.swing.JPanel();
			pnControlsSteps.setLayout(new javax.swing.BoxLayout(pnControlsSteps,javax.swing.BoxLayout.X_AXIS));

			configInput.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(10);
			configInput.anchor = configInput.CENTER;
			configInput.gridx = 0;
			configInput.gridy = 0;
			configInput.weightx = 1;
			configInput.weighty = 1;
			configInput.fill = configInput.BOTH;


			configInput.gridwidth=3;
			configInput.gridy = 0;
			configInput.gridx = 0;
			pnInputComando.add(lbTipoComando,configInput);	

			configInput.gridx = 0;
			configInput.gridy = 1;
			pnInputComando.add(cbTipoComando,configInput);			
			configInput.gridwidth=1;					

			java.awt.GridBagConstraints configStepControls = new java.awt.GridBagConstraints();
			configStepControls.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(10);
			configStepControls.gridx=0;
			configStepControls.gridy=0;
			configStepControls.anchor = configStepControls.LINE_END;
			lbAdicionarComando.setToolTipText("Adicionar passo para execução automatizada");
			lbPlayExecutionComands.setToolTipText("Executa os passos definitos pelo usuário");
			pnControlsSteps.setBackground(java.awt.Color.white);
			pnControlsSteps.setLayout(new java.awt.GridBagLayout());
			pnControlsSteps.add(lbAdicionarComando,configStepControls);
			configStepControls.gridx=1;
			configStepControls.gridy=0;
			pnControlsSteps.add(lbPlayExecutionComands,configStepControls);

			configInput.gridwidth=3;
			configInput.gridy = 1;
			configInput.gridx = 3;	
			configInput.weightx = 1;
			configInput.weighty  = 1;
			configInput.fill = configInput.BOTH;
			pnInputComando.add(pnControlsSteps,configInput);	
			configInput.gridwidth=1;
			configInput.fill = configInput.NONE;


			configInput.gridy = 2;
			configInput.gridx = 0;
			pnInputComando.add(lbInputComand,configInput);	

			configInput.gridx = 0;
			configInput.gridy = 3;
			pnInputComando.add(cbInputComand,configInput);	

			configInput.gridy = 2;
			configInput.gridx = 1;
			pnInputComando.add(lbValorEntrada,configInput);			
			configInput.gridx = 1;
			configInput.gridy = 3;
			pnInputComando.add(cbValorEntrada,configInput);	

			configInput.gridy = 2;
			configInput.gridx = 2;
			pnInputComando.add(lbOutputComand,configInput);			
			configInput.gridx = 2;
			configInput.gridy = 3;
			pnInputComando.add(cbOutputComand,configInput);	

			configInput.gridy = 2;
			configInput.gridx = 3;
			pnInputComando.add(lbOutputComponente,configInput);		
			configInput.gridy = 3;
			configInput.gridx = 3;
			pnInputComando.add(cbOutputComponente,configInput);	


			configInput.gridy = 2;
			configInput.gridx = 4;
			pnInputComando.add(lbStepTarget,configInput);	

			configInput.gridx = 4;
			configInput.gridy = 3;
			pnInputComando.add(cbStepTarget,configInput);			


			java.awt.GridBagConstraints configOut = new java.awt.GridBagConstraints();

			pnOutputConfig.setLayout(new java.awt.GridBagLayout());
			configOut.gridx = 0;
			configOut.gridy = 0;
			configOut.weightx = 1;
			configOut.weighty = 0 ;
			configOut.fill = configOut.HORIZONTAL;


			java.awt.event.MouseListener mouseControlsCMD = new java.awt.event.MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					for(java.awt.Component comp : pnOutputConfig.getComponents()) {
						try {
							if(comp.getName().contains("PRM")) {							
								javax.swing.JTextField txt = (javax.swing.JTextField)comp;

								if(comp.getName().equals(String.format("PRM_COMANDO_%s",AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posStepCorrente))) {								
									txt.setText("");
								}
								else if(comp.getName().equals(String.format("PRM_DADOS_%s",AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posStepCorrente))) {
									txt.setText("");
								}
								else if(comp.getName().equals(String.format("PRM_RESULT_ESPERADO_%s",AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posStepCorrente))) {
									txt.setText("");
								}
								else if(comp.getName().equals(String.format("PRM_COMPONENTE_%s",AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posStepCorrente))) {
									txt.setText("");
								}
							}
							else {

							}
						}
						catch(java.lang.Exception e1) {

						}

					}

				}

				@Override
				public void mouseEntered(MouseEvent e) {

					javax.swing.JComponent txt = (javax.swing.JComponent)e.getSource();
					java.util.regex.Matcher analise = padraoExpDigitos.matcher(txt.getName());
					System.out.println("LINHA ATIVA CMD: ");
					if(analise.find()) {
						AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posStepCorrente = Integer.parseInt(analise.group());
						System.out.println(analise.group());
					}

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

			};

			for(int i = 0 ;i < 300;i++) {
				configOut.gridx = 0;
				configOut.weightx = 0;
				configOut.fill = configOut.NONE;

				pnOutputConfig.add(new javax.swing.JLabel(String.format("\u27A0 PASSO Nº %s", i)),configOut);
				configOut.gridx++;

				configOut.weightx = 1;
				configOut.fill = configOut.HORIZONTAL;

				javax.swing.JTextField txtEntradaComando = new javax.swing.JTextField(cbInputComand.getModel().getSelectedItem().toString());
				javax.swing.JTextField txtEntradaPosterior = new javax.swing.JTextField(cbValorEntrada.getModel().getSelectedItem().toString());
				javax.swing.JTextField txtComandoOutput = new javax.swing.JTextField(cbOutputComand.getModel().getSelectedItem().toString());
				javax.swing.JTextField txtComponenteOutput = new javax.swing.JTextField(cbOutputComponente.getModel().getSelectedItem().toString());

				txtEntradaComando.setName(String.format("PRM_COMANDO_%s",i));
				txtEntradaPosterior.setName(String.format("PRM_DADOS_%s",i));
				txtComandoOutput.setName(String.format("PRM_RESULT_ESPERADO_%s",i));
				txtComponenteOutput.setName(String.format("PRM_COMPONENTE_%s",i));


				configOut.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(5);

				configOut.gridx++;
				pnOutputConfig.add(txtEntradaComando,configOut);
				configOut.gridx++;
				pnOutputConfig.add(txtEntradaPosterior,configOut);
				configOut.gridx++;
				pnOutputConfig.add(txtComandoOutput,configOut);
				configOut.gridx++;
				pnOutputConfig.add(txtComponenteOutput,configOut);


				configOut.gridx++;					
				configOut.weightx = 0;
				configOut.fill = configOut.NONE;

				javax.swing.JLabel lb = new javax.swing.JLabel("\u2716");				
				lb.setName(String.format("STEP_DELETE_%s", i));
				lb.setFont(lb.getFont().deriveFont(20f));
				lb.addMouseListener(listenerLabelControlStep);
				lb.addMouseListener(mouseControlsCMD);

				pnOutputConfig.add(lb,configOut);
				ddStepTarget.addElement(String.format("P %s", i));

				configOut.gridx++;

				lb = new javax.swing.JLabel("\u25BA");				
				lb.setName(String.format("STEP_START_%s", i));
				lb.setFont(lb.getFont().deriveFont(20f));
				lb.addMouseListener(listenerLabelControlStep);	
				lb.addMouseListener(mouseControlsCMD);

				AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).ltCmdTerminal.put(lb.getName(),new java.util.HashMap<String,Object>());

				pnOutputConfig.add(lb,configOut);


				txtEntradaComando.addMouseListener(mouseControlsCMD);
				txtEntradaPosterior.addMouseListener(mouseControlsCMD);
				txtComandoOutput.addMouseListener(mouseControlsCMD);
				txtComponenteOutput.addMouseListener(mouseControlsCMD);




				configOut.gridy++;

			}


			configLayout.gridx=0;
			configLayout.gridy=0;
			configLayout.weightx = 1;
			configLayout.weighty = 0;
			configLayout.fill = configLayout.HORIZONTAL;


			formTerminal.add(pnInputComando,configLayout);

			configLayout.gridx = 0;
			configLayout.gridy = 1;
			configLayout.weightx = 1;
			configLayout.weighty = 1;
			configLayout.fill = configLayout.BOTH;	
			formTerminal.add(scrOutput,configLayout);

			formTerminal.setTitle(String.format(tituloApp,nmDevice));

			formTerminal.setVisible(true);
		}

		/**
		 * 
		 * Construtor padrão da classe
		 * 
		 */
		public AUTFormTerminalComandos() {
			configGUI();
		}

		public AUTFormTerminalComandos(javax.swing.JDialog form) {
			formPrincipal = form;
			configGUI();
		}

		
		public AUTFormTerminalComandos(javax.swing.JDialog form,String nomeDispositivo,Object[] cmdKeys) {
			formPrincipal = form;
			nmDevice = nomeDispositivo;			
			comandosTerminal = cmdKeys;
			java.util.Arrays.sort(comandosTerminal);
			configGUI();
		}
	}




	public void initDataApp() {
		System.out.println("&&& INFO : CONFIGURANDO DADOS DE INICIALIZACAO DA APLICACAO");
		AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posCorrente = 0;
		AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posStepCorrente = 0;
		AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posStepEdit = 0;
		ltCmdTerminal.clear();
	}
	
	
	/**
	 * 
	 * Função de configuração de interface gráfica
	 * 
	 * 
	 */
	public void configGUI() {		
		
		AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.add(this);
		
		AUT_ID_MOB_CONSOLE=AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.size()-1;	
		
		
		
		
		initDataApp();
		
		System.out.println("########### INICIALIZANDO CONSOLE MOBILE ########################");
		System.out.println("ID CLASSE: ");
		System.out.println(AUT_ID_MOB_CONSOLE);
		
		javax.swing.JDialog formMobConsole = new javax.swing.JDialog(formPrincipal);
		formMobConsole.setName(String.format("AUT_FORM_CONSOLE_%s", AUT_ID_MOB_CONSOLE));
		
		javax.swing.JPanel pnControles = new javax.swing.JPanel();
		javax.swing.JButton btCapImg = new javax.swing.JButton("Carregar Imagem");
		javax.swing.JButton btFechar = new javax.swing.JButton("Fechar");
		javax.swing.JButton btInstalar = new javax.swing.JButton("Instalar Aplicativo");
		javax.swing.JButton btDesinstalar = new javax.swing.JButton("Desinstalar Aplicativo");
		javax.swing.JPanel pnConfigSincIMG = new javax.swing.JPanel();
		javax.swing.JRadioButton rdDesHabSincThread = new javax.swing.JRadioButton("Sincronização 1º Plano");
		javax.swing.JRadioButton rdHabSincThread = new javax.swing.JRadioButton("Sincronização 2º Plano",true);
		javax.swing.JCheckBox chkHabCapAut = new javax.swing.JCheckBox("Captura de Tela Automática");
		javax.swing.JCheckBox chkDesHabCapManual = new javax.swing.JCheckBox("Captura de Tela Manual");
		javax.swing.DefaultListModel ddLtCMDADB = new javax.swing.DefaultListModel();
		javax.swing.JList listComandosADB = new javax.swing.JList();
		javax.swing.JScrollPane scrComandos = new javax.swing.JScrollPane(listComandosADB,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JTree treeDadosEntrada = new javax.swing.JTree(); 
		javax.swing.JScrollPane scrArvoreObjetos = new javax.swing.JScrollPane(treeDadosEntrada,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JPanel pnMovControles = new javax.swing.JPanel();
		javax.swing.JLabel lbMovEsquerdo = new javax.swing.JLabel("<");
		javax.swing.JLabel lbMovDireito = new javax.swing.JLabel(">");
		javax.swing.JLabel lbMovUP = new javax.swing.JLabel("^");
		javax.swing.JLabel lbMovDown = new javax.swing.JLabel("*");

		javax.swing.JPanel pnCapAut = new javax.swing.JPanel();
		javax.swing.JLabel lbTimeSinc = new javax.swing.JLabel("Tempo: ");
		javax.swing.JTextField txtTime = new javax.swing.JTextField(10);

		pnMovControles.setBackground(java.awt.Color.WHITE);
		java.awt.GridBagConstraints configCapAut = new java.awt.GridBagConstraints();

		pnCapAut.setLayout(new java.awt.GridBagLayout());
		pnCapAut.setBackground(java.awt.Color.WHITE);

		configCapAut.gridx = 0;
		configCapAut.gridy = 0;
		configCapAut.weightx = 1;
		configCapAut.weighty = 1;
		configCapAut.fill = configCapAut.BOTH;
		pnCapAut.add(chkHabCapAut,configCapAut);

		configCapAut.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(3);
		configCapAut.gridx = 1;
		configCapAut.gridy = 0;
		pnCapAut.add(lbTimeSinc,configCapAut);		

		configCapAut.gridx = 2;
		configCapAut.gridy = 0;

		pnCapAut.add(txtTime,configCapAut);

		java.awt.event.KeyListener keyControleDirecional = new java.awt.event.KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("TECLA DE CONTROLE DIRECIONAL ATIVADA");
				System.out.println(e.paramString());				
			}

		};


		pnMovControles.addFocusListener(new java.awt.event.FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

				javax.swing.JPanel pn = (javax.swing.JPanel)e.getSource();

				pn.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLUE, 5));
			}

			@Override
			public void focusLost(FocusEvent e) {

				javax.swing.JPanel pn = (javax.swing.JPanel)e.getSource();
				pn.setBackground(java.awt.Color.WHITE);
				pn.setBorder(null);

			}

		});



		pnMovControles.setFocusable(true);
		pnMovControles.addKeyListener(keyControleDirecional);



		menusDirectDevice.add(mnEnvAtualizarMenu);
		menusDirectDevice.add(mnEnvParametro);

		lbMovUP.setComponentPopupMenu(menusDirectDevice);
		lbMovEsquerdo.setComponentPopupMenu(menusDirectDevice);
		lbMovDireito.setComponentPopupMenu(menusDirectDevice);
		lbMovDown.setComponentPopupMenu(menusDirectDevice);


		java.awt.event.MouseListener rotuloControlMobMouseControl = new java.awt.event.MouseListener() {

			@Override
			public void mouseClicked(MouseEvent obj) {
				javax.swing.JLabel lb = (javax.swing.JLabel)obj.getSource();

				System.out.println("INFO MOUSE COMAND : ACIONANDO COMANDO DIRECIONAL ADB: ".concat(lb.getName()));
				System.out.println(obj.paramString());

			}

			@Override
			public void mouseEntered(MouseEvent obj) {
				javax.swing.JLabel lb = (javax.swing.JLabel)obj.getSource();

				lb.setForeground(java.awt.Color.BLUE);				

			}

			@Override
			public void mouseExited(MouseEvent obj) {
				javax.swing.JLabel lb = (javax.swing.JLabel)obj.getSource();

				lb.setForeground(java.awt.Color.BLACK);

			}

			@Override
			public void mousePressed(MouseEvent obj) {
				javax.swing.JLabel lb = (javax.swing.JLabel)obj.getSource();

				lb.setForeground(java.awt.Color.GREEN);

			}

			@Override
			public void mouseReleased(MouseEvent obj) {
				// TODO Auto-generated method stub

			}

		};

		lbMovUP.setName("ADB_UP");
		lbMovEsquerdo.setName("ADB_LEFT");
		lbMovDireito.setName("ADB_RIGHT");
		lbMovDown.setName("ADB_DOWN");

		lbMovUP.addMouseListener(rotuloControlMobMouseControl);
		lbMovEsquerdo.addMouseListener(rotuloControlMobMouseControl);
		lbMovDireito.addMouseListener(rotuloControlMobMouseControl);
		lbMovDown.addMouseListener(rotuloControlMobMouseControl);

		lbMovUP.setFont(lbMovUP.getFont().deriveFont(java.awt.Font.BOLD, 50));		
		lbMovEsquerdo.setFont(lbMovEsquerdo.getFont().deriveFont(java.awt.Font.BOLD, 50));
		lbMovDireito.setFont(lbMovDireito.getFont().deriveFont(java.awt.Font.BOLD, 50));
		lbMovDown.setFont(lbMovDown.getFont().deriveFont(java.awt.Font.BOLD, 50));

		lbMovUP.setText("\u29CB");
		lbMovEsquerdo.setText("\u29CF");
		lbMovDireito.setText("\u29D0");
		lbMovDown.setText("\u29E8");


		java.awt.GridBagConstraints configMovControls = new java.awt.GridBagConstraints();
		configMovControls.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(10);
		configMovControls.gridx = 0;
		configMovControls.gridy = 0;
		configMovControls.gridwidth = 2;
		pnMovControles.setLayout(new java.awt.GridBagLayout());
		pnMovControles.add(lbMovUP,configMovControls);
		configMovControls.gridwidth = 1;
		configMovControls.gridx = 0;
		configMovControls.gridy = 1;

		pnMovControles.add(lbMovEsquerdo,configMovControls);
		configMovControls.gridx = 1;
		configMovControls.gridy = 1;
		pnMovControles.add(lbMovDireito,configMovControls);
		configMovControls.gridx = 0;
		configMovControls.gridy = 2;
		configMovControls.gridwidth = 2;
		pnMovControles.add(lbMovDown,configMovControls);


		treeDadosEntrada.setToolTipText("ÁRVORE DE OBJETOS SENDO EXIBIDOS NA INTERFACE GRÁFICA");
		listComandosADB.setToolTipText("LISTA DE COMANDOS DISPONÍVEIS PARA O DISPOSITIVO CONECTADO");
		java.awt.GridBagConstraints configLayout = new java.awt.GridBagConstraints();



		javax.swing.JPopupMenu menusListaComandos = new javax.swing.JPopupMenu();
		javax.swing.JMenuItem menuAtualizarCMD  = menusListaComandos.add("Atualizar Lista");
		listComandosADB.setComponentPopupMenu(menusListaComandos);


		pnConfigSincIMG.setLayout(new java.awt.GridBagLayout());

		treeDadosEntrada.setModel(null);

		listComandosADB.setModel(ddLtCMDADB);	

		pnConfigSincIMG.setBackground(java.awt.Color.WHITE);

		java.awt.GridBagConstraints configLayoutOptions = new java.awt.GridBagConstraints();


		configLayoutOptions.insets = AUTFormularioUtils.configurarEspacoInternoElementoGUI(5);
		configLayoutOptions.gridx = 0;
		configLayoutOptions.gridy = 0;
		configLayoutOptions.weightx = 1;
		configLayoutOptions.weighty = 0;
		configLayoutOptions.fill = configLayoutOptions.HORIZONTAL;		
		pnConfigSincIMG.add(rdDesHabSincThread,configLayoutOptions);
		configLayoutOptions.gridy = 1;
		pnConfigSincIMG.add(rdHabSincThread,configLayoutOptions);
		configLayoutOptions.gridy = 2;
		pnConfigSincIMG.add(pnCapAut,configLayoutOptions);
		configLayoutOptions.gridy = 3;
		pnConfigSincIMG.add(chkDesHabCapManual,configLayoutOptions);
		configLayoutOptions.gridy = 4;
		configLayoutOptions.weighty = 1;
		configLayoutOptions.fill = configLayoutOptions.HORIZONTAL;
		pnConfigSincIMG.add(pnMovControles,configLayoutOptions);


		java.awt.GridBagConstraints configLayoutControles = new java.awt.GridBagConstraints();
		pnControles.setLayout(new java.awt.GridBagLayout());
		pnControles.setBackground(java.awt.Color.WHITE);
		configLayoutControles.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(10);
		configLayoutControles.gridx = 0;
		configLayoutControles.gridy = 0;
		configLayoutControles.weightx = 1;
		configLayoutControles.weighty = 0;
		configLayoutControles.fill = configLayoutControles.HORIZONTAL;
		pnControles.add(pnConfigSincIMG,configLayoutControles);

		javax.swing.JPanel pnButtons = new javax.swing.JPanel();
		java.awt.GridBagConstraints configLayoutButtons = new java.awt.GridBagConstraints();
		pnButtons.setLayout(new java.awt.GridBagLayout());
		pnButtons.setBackground(java.awt.Color.WHITE);


		configLayoutButtons.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(15);
		configLayoutButtons.gridx = 0;
		configLayoutButtons.gridy = 0;
		configLayoutButtons.weightx = 1;
		configLayoutButtons.weighty = 0;
		configLayoutButtons.fill = configLayoutButtons.HORIZONTAL;
		pnButtons.add(btCapImg,configLayoutButtons);
		configLayoutButtons.gridx = 1;
		pnButtons.add(btFechar,configLayoutButtons);
		configLayoutButtons.gridx = 0;
		configLayoutButtons.gridy = 1;
		pnButtons.add(btInstalar,configLayoutButtons);
		configLayoutButtons.gridx = 1;
		configLayoutButtons.gridy = 1;
		pnButtons.add(btDesinstalar,configLayoutButtons);		

		configLayoutControles.gridx = 0;
		configLayoutControles.gridy = 1;
		configLayoutControles.weightx = 1;
		configLayoutControles.weighty = 0;
		configLayoutControles.fill = configLayoutControles.HORIZONTAL;

		pnControles.add(pnButtons,configLayoutControles);

		javax.swing.JLabel lbNagNext = new javax.swing.JLabel();

		lbNagNext.setText("\u0C44");

		configLayoutControles.gridx = 0;
		configLayoutControles.gridy = 2;
		configLayoutControles.weightx = 1;
		configLayoutControles.weighty = 1;
		configLayoutControles.fill = configLayoutControles.BOTH;

		pnControles.add(scrComandos,configLayoutControles);	


		configLayoutControles.gridx = 0;
		configLayoutControles.gridy = 3;
		configLayoutControles.weightx = 1;
		configLayoutControles.weighty = 1;
		configLayoutControles.fill = configLayoutControles.BOTH;		

		pnControles.add(scrArvoreObjetos,configLayoutControles);	

		javax.swing.JPopupMenu menus = new javax.swing.JPopupMenu();

		javax.swing.JMenuItem menuAtualizar = menus.add("Atualizar Arvore de Objetos");



		AUTProcessoParalelo autCarregarGUI = new AUTProcessoParalelo() {

			@Override
			protected void rotinasInicializacao() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void rotinasExecucao() {

				System.out.println("INFO : CARREGANDO ESTRUTURA DE OBJETOS GUI : TELA ATUAL");

				AUTAndroidInterface.AUTAndroidObject.carregarArvoreObjetosXML(AUTAndroidInterface.AUTAndroidObject.carregarXMLConfiguracao(), treeDadosEntrada);
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


		menuAtualizar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				autCarregarGUI.executarProcesso();
			}
		});


		treeDadosEntrada.setComponentPopupMenu(menus);

		rdDesHabSincThread.addChangeListener(new javax.swing.event.ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent obj) {
				javax.swing.JRadioButton rd = (javax.swing.JRadioButton)obj.getSource();

				if(rd.isSelected()) {
					rdHabSincThread.setSelected(false);
				}

			}

		});


		rdHabSincThread.addChangeListener(new javax.swing.event.ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent obj) {
				javax.swing.JRadioButton rd = (javax.swing.JRadioButton)obj.getSource();

				if(rd.isSelected()) {
					rdDesHabSincThread.setSelected(false);
				}

			}

		});


		AUTProcessoParalelo procAtualizarImg = new AUTProcessoParalelo() {

			@Override
			protected void rotinasInicializacao() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void rotinasExecucao() {
				try {					
					if(listIMGS.size() > 0) {
						AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG.setViewportView(new AUTAPI.AUTPainelImagem(listIMGS.get(AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posCorrente-1).extrairComponenteIMG(),AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG.getSize().width,AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG.getSize().height));
					}
				}
				catch(java.lang.Exception e) {
					System.out.println("ERROINFO : SINCRONIZACAO DE IMAGEM");
					System.out.println(e.getMessage());
					e.printStackTrace();
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



		formMobConsole.addWindowListener(new java.awt.event.WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}


			@Override
			public void windowOpened(WindowEvent arg0) {
				System.out.println(String.format("INFO : CONSOLE DE GERENCIAMENTO MOBILE : %s",formMobConsole.getTitle()));
				// TODO Auto-generated method stub
				AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {

					@Override
					public void ancestorMoved(HierarchyEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void ancestorResized(HierarchyEvent obj) {
						System.out.println("INFOCOMP : COMPONENTE REDIMENSIONADO, ATUALIZANDO IMAGEM");
						if(rdHabSincThread.isSelected()) {
							System.out.println("INFOCOMPONENTE : JANELA REDIMENSIONADA : SINC");
							procAtualizarImg.executarProcesso();
						}
						else {
							System.out.println("INFOCOMPONENTE : JANELA REDIMENSIONADA : ASSINC");

							if(listIMGS.size() > 0) {
								AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG.setViewportView(new AUTAPI.AUTPainelImagem(listIMGS.get(AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posCorrente-1).extrairComponenteIMG(),AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG.getSize().width,AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG.getSize().height));
							}

						}


						AUTRuntimeConfiguration.AUT_SINC_IMAGEM_CUSTOM = true;
					}

				});				
			}

		});





		AUTProcessoParalelo procLoaderImg = new AUTProcessoParalelo() {

			@Override
			protected void rotinasInicializacao() {
				// TODO Auto-generated method stub

			}


			@Override
			protected void rotinasExecucao() {

				System.out.println("INFO: CARREGANDO IMAGEM DA TELA ATUAL DO DISPOSITIVO CONECTADO");	

				listIMGS.add(AUTAndroidInterface.AUTAndroidObject.carregarImagem(nmDispositivo,AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posCorrente,AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG.getSize().width,AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG.getSize().height));

				AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG.add(listIMGS.get(AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posCorrente));
				AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG.setViewportView(listIMGS.get(AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posCorrente));

				AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posCorrente++;

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
		


		AUTProcessoParalelo procLoaderImgForTimeSlice = new AUTProcessoParalelo() {

			@Override
			protected void rotinasInicializacao() {
				// TODO Auto-generated method stub

			}


			@Override
			protected void rotinasExecucao() {
				while(true) {
					System.out.println("INFO: CARREGANDO IMAGEM DA TELA ATUAL DO DISPOSITIVO CONECTADO");	

					listIMGS.add(AUTAndroidInterface.AUTAndroidObject.carregarImagem(nmDispositivo,AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posCorrente,AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG.getSize().width,AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG.getSize().height));

					AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG.add(listIMGS.get(AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posCorrente));
					AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG.setViewportView(listIMGS.get(AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posCorrente));

					AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).posCorrente++;

					try {
						java.lang.Thread.currentThread().sleep(1000 * Integer.parseInt(txtTime.getText().trim()));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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


		AUTProcessoParalelo procLoaderDataCMDS = new AUTProcessoParalelo() {

			@Override
			protected void rotinasInicializacao() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void rotinasExecucao() {
				if(ddLtCMDADB.size() > 0) {ddLtCMDADB.removeAllElements();}


				for(Object obj: AUTDispositivoConfiguracao.carregarConfiguracaoTeclado()) {
					if(obj.toString().length() > 1 && !obj.toString().equals("character")) {
						ddLtCMDADB.addElement(obj);
					}
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

		AUTProcessoParalelo procLoaderDataMenuGUICMDS = new AUTProcessoParalelo() {

			@Override
			protected void rotinasInicializacao() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void rotinasExecucao() {
				if(ddLtCMDADB.size() > 0) {ddLtCMDADB.removeAllElements();}

				System.out.println("INFO LOADER MENU GUI CMD: INIT");

				lbMovUP.setComponentPopupMenu(null);						
				lbMovDown.setComponentPopupMenu(null);
				lbMovEsquerdo.setComponentPopupMenu(null);
				lbMovDireito.setComponentPopupMenu(null);	


				menuItemComandoADB.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						AUTProcessoParalelo procLoaderKeys = new AUTProcessoParalelo() {

							@Override
							protected void rotinasInicializacao() {
								// TODO Auto-generated method stub

							}

							@Override
							protected void rotinasExecucao() {
								AUTFormTerminalComandos frmTerminal = new AUTFormTerminalComandos(formMobConsole,nmDispositivo,AUTDispositivoConfiguracao.carregarConfiguracaoTeclado(nmDispositivo));		

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

						procLoaderKeys.executarProcesso();

					}
				});

				menusDirectDevice.add(menuItemComandoADB);
				menusDirectDevice.add(mnEnvAtualizarMenu);
				menusDirectDevice.add(mnEnvParametro);


				lbMovUP.setComponentPopupMenu(menusDirectDevice);						
				lbMovDown.setComponentPopupMenu(menusDirectDevice);
				lbMovEsquerdo.setComponentPopupMenu(menusDirectDevice);
				lbMovDireito.setComponentPopupMenu(menusDirectDevice);						


				System.out.println("INFO LOADER MENU GUI CMD: END");

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



		menuAtualizarCMD.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				procLoaderDataCMDS.executarProcesso();

			}
		});

		btCapImg.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				procLoaderImg.executarProcesso();
			}
		});


		btFechar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(String.format("INFO : CONSOLE DE GERENCIAMENTO MOBILE : %s",formMobConsole.getTitle()));
				formMobConsole.setVisible(false);
			}
		});




		mnEnvAtualizarMenu.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				procLoaderDataMenuGUICMDS.executarProcesso();
			}
		});

		chkHabCapAut.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent obj) {


				javax.swing.JCheckBox chk = (javax.swing.JCheckBox)obj.getSource();


				if(chk.isSelected()) {

					System.out.println("INFO : CAPTURA DE TELA AUT HABILITADA");

					procLoaderImgForTimeSlice.executarProcesso();

				}
				else {
					System.out.println("INFO : CAPTURA DE TELA AUT DISABILITADA");

				}
			}
		});

		formMobConsole.getContentPane().setBackground(java.awt.Color.WHITE);
		formMobConsole.setSize(new java.awt.Dimension(700,700));			
		formMobConsole.setLocationRelativeTo(formPrincipal);
		formMobConsole.setLayout(new java.awt.GridBagLayout());
		formMobConsole.setTitle(tituloApp);
		configLayout.gridx = 0;
		configLayout.gridy = 0;
		configLayout.weightx = 0;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.VERTICAL;

		formMobConsole.add(pnControles,configLayout);

		configLayout.gridx = 1;
		configLayout.gridy = 0;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;

		formMobConsole.add(AUT_LISTA_FORM_MOB_CONSOLE_INSTANCIAS.get(AUT_ID_MOB_CONSOLE).scrIMG, configLayout);


		
		formMobConsole.setVisible(true);

	}


	/**
	 * 
	 * Construtor padrão da classe
	 * 
	 * @param form - Formulário principal de gerenciamento do dispositivo
	 * 
	 * 
	 */
	public AUTFormConsoleMobile(javax.swing.JDialog form) {
		formPrincipal = form;

		configGUI();
	}

	public AUTFormConsoleMobile(String nomeDispositivo,javax.swing.JDialog form) {
		formPrincipal = form;
		nmDispositivo = nomeDispositivo;
		tituloApp = String.format(tituloApp,nmDispositivo);
		
		configGUI();
	}
}
