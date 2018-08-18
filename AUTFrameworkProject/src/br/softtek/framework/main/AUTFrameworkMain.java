package br.softtek.framework.main;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.lang.reflect.GenericArrayType;
import java.text.Normalizer.Form;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer.UIResource;

import org.apache.derby.iapi.services.io.FormatIdUtil;

import javax.swing.ImageIcon;
import javax.swing.ToolTipManager;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import br.softtek.framework.main.configuracao.*;
import br.softtek.framework.testes.arquivo.gui.AUTFormConfiguracaoArquivo;
import br.softtek.framework.testes.mobile.gui.AUTFormMobileConector;
import br.softtek.framework.testes.mobile.gui.AUTFormMobileTesteManual;
import br.softtek.framework.api.AUTAPIGUI;
import br.softtek.framework.api.AUTAPIGUI.AUTProcessoParalelo;
import br.softtek.framework.logs.AUTGerenciadorLogs;

/**
 * Classe respons�vel pelo gerenciamento de recursos do framework atrav�s de interface gr�fica
 * 
 * @author Softtek - QA
 *
 */
public class AUTFrameworkMain {	
	javax.swing.JDialog menuInicializacao;

	/**
	 * 
	 * Rotinas de inicializa��o do framework
	 * 
	 */
	public void configurarInicializacaoGUI() {	
		
		javax.swing.JPanel painelPlanoFundo = new javax.swing.JPanel();
		javax.swing.JMenu menuArquivo = new javax.swing.JMenu("Arquivo");
		javax.swing.JMenu menuConfiguracoes = new javax.swing.JMenu("Configura��es");
		javax.swing.JMenu menuTestes = new javax.swing.JMenu("Testes");
		javax.swing.JMenu menuRelatorios = new javax.swing.JMenu("Relat�rios");
		javax.swing.JMenu menuIntegracoes = new javax.swing.JMenu("Integra��es");
		javax.swing.JMenu menuAjuda = new javax.swing.JMenu("Ajuda");
		javax.swing.JMenuBar barraMenus = new javax.swing.JMenuBar();
		javax.swing.JLabel lbStatus = new javax.swing.JLabel(String.format("HOST : LOCALHOST%80s","*"));
		String imagemLogon = "C:\\Framework Softtek V1\\recursos\\imagem001.png";
		
		/**
		 * 
		 * Constru��o de interface gr�fica da aplica��o
		 * 
		 */
		menuInicializacao = new javax.swing.JDialog();
		menuInicializacao.getContentPane().setBackground(java.awt.Color.WHITE);
		menuInicializacao.setModal(true);
		menuInicializacao.setSize(menuInicializacao.getGraphicsConfiguration().getBounds().getSize());		
		
		menuInicializacao.setLayout(new java.awt.BorderLayout());
		
		
		menuArquivo.setMnemonic('A');
		menuConfiguracoes.setMnemonic('C');
		menuTestes.setMnemonic('T');
		menuRelatorios.setMnemonic('R');
		menuIntegracoes.setMnemonic('I');
		menuAjuda.setMnemonic('H');

		javax.swing.JMenuItem subMenuSair = menuArquivo.add(new javax.swing.JMenuItem("Sair"));
		subMenuSair.setMnemonic('S');
		javax.swing.JMenuItem subMenuImportar = menuArquivo.add(new javax.swing.JMenuItem("Importar..."));
		subMenuImportar.setMnemonic('I');
		javax.swing.JMenuItem subMenuExportar = menuArquivo.add(new javax.swing.JMenuItem("Exportar..."));
		subMenuExportar.setMnemonic('E');
		

		javax.swing.JMenuItem subMenuRepArquivos = menuConfiguracoes.add(new javax.swing.JMenuItem("Reposit�rio Arquivos..."));
		subMenuRepArquivos.setMnemonic('R');
		javax.swing.JMenuItem subMenuConectarDB = menuConfiguracoes.add(new javax.swing.JMenuItem("Conex�o Bando de Dados"));
		subMenuConectarDB.setMnemonic('C');
		javax.swing.JMenuItem subMenuManutencao = menuConfiguracoes.add(new javax.swing.JMenuItem("Manuten��o Bando de Dados"));
		subMenuManutencao.setMnemonic('M');
				
		javax.swing.JMenuItem subMenuRepositorioImags = menuTestes.add(new javax.swing.JMenuItem("Reposit�rio de Imagens"));
		javax.swing.JMenuItem subMenuConfigArquivos = menuTestes.add(new javax.swing.JMenuItem("Validador de Arquivos"));
		javax.swing.JMenuItem subMenuConfigMobileManualTeste = menuTestes.add(new javax.swing.JMenuItem("Mobile : Teste Manual"));
		javax.swing.JMenuItem subMenuConfigMobileAutomation = menuTestes.add(new javax.swing.JMenuItem("Mobile Conector"));
		javax.swing.JMenuItem subMenuConfigRegExp = menuTestes.add(new javax.swing.JMenuItem("Express�o Regular"));
		javax.swing.JMenu subMenuProjeto = (javax.swing.JMenu)menuTestes.add(new javax.swing.JMenu("Configura��es de Projetos"));
		javax.swing.JMenuItem subMenuConfigGerProjeto = subMenuProjeto.add(new javax.swing.JMenuItem("Projetos"));
		javax.swing.JMenuItem subMenuConfigGerModulos = subMenuProjeto.add(new javax.swing.JMenuItem("M�dulos"));
		javax.swing.JMenuItem subMenuConfigGerCenarios = subMenuProjeto.add(new javax.swing.JMenuItem("Cen�rios do Teste"));
		javax.swing.JMenuItem subMenuConfigGerTestes = subMenuProjeto.add(new javax.swing.JMenuItem("Testes"));
		javax.swing.JMenuItem subMenuConfigGerFluxoDeDados = subMenuProjeto.add(new javax.swing.JMenuItem("Fluxo de Dados"));
		javax.swing.JMenuItem subMenuConfigGerBibliotecas = subMenuProjeto.add(new javax.swing.JMenuItem("Bibliotecas / API"));
				
		subMenuConfigMobileAutomation.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AUTFormMobileConector frmMob  = new AUTFormMobileConector(menuInicializacao);
			/**
				AUTProcessoParalelo procFormMob = new AUTProcessoParalelo() {
					@Override
					protected void rotinasExecucao() {
						try {
							AUTFormMobileConector frmMob  = new AUTFormMobileConector(menuInicializacao);
							
							System.out.println("******** FORM CONNECT INIT SUCESS ***********");
						}
						catch(java.lang.Exception e) {
							System.out.println("AUT ERROR INIT FORM CONNECT MOBILE :");
							System.out.println(e.getMessage());
							e.printStackTrace();
						}
					}
					
					@Override
					protected void rotinasFinalizacao() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					protected void rotinasInicializacao() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					protected void rotinasMonitoramentoProcesso() {
						// TODO Auto-generated method stub
						
					}
				};
				
				procFormMob.executarProcesso();
		
				**/
			}
		});
		
		subMenuRepositorioImags.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				AUTFormImagensGraficas formImgs = new AUTFormImagensGraficas(menuInicializacao);
				
			}
		});
		
		subMenuConfigGerTestes.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				AUTFormGerenciamentoTestes formTestes = new AUTFormGerenciamentoTestes(menuInicializacao);
				
			}
		});
		
		subMenuConfigGerCenarios.addActionListener(new java.awt.event.ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				AUTFormGerenciarCenarios formGerCenarios = new AUTFormGerenciarCenarios(menuInicializacao);
			}
		});
		
		subMenuConfigGerProjeto.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				AUTGerenciamentoProjetos formGerProj = new AUTGerenciamentoProjetos(menuInicializacao);				
				
			}
		});
		
		
		subMenuConfigGerModulos.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				AUTFormGerenciamentoModulos formGerProj = new AUTFormGerenciamentoModulos(menuInicializacao);				
				
			}
		});
		
		subMenuConectarDB.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				AUTConexoesBaseDados formDB = new AUTConexoesBaseDados(menuInicializacao);
			}
		});
		
		subMenuConfigArquivos.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				AUTFormConfiguracaoArquivo formConfiguracao = new AUTFormConfiguracaoArquivo(menuInicializacao);
			}
		});
		
		subMenuConfigMobileManualTeste.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AUTFormMobileTesteManual formMobManual = new AUTFormMobileTesteManual(menuInicializacao);
								
			}
		});
		
		
		
		/**
		 * Configura��o de express�es regulares para formata��o de layout de arquivos de 
		 * teste
		 */
		subMenuConfigRegExp.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				AUTFormRegExp formRegExp = new AUTFormRegExp(menuInicializacao);
				
			}			
		});
		subMenuImportar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String mensagem = "INFO: FUNCIONALIDADE DE IMPORTA��O DE DADOS AINDA N�O CONFIGURADA ADEQUADAMENTE";
				AUTGerenciadorLogs.registrarLog(mensagem);
				javax.swing.JOptionPane.showConfirmDialog(menuInicializacao, mensagem,"Softtek - QA : Assistente ",javax.swing.JOptionPane.YES_OPTION);
				
			}
		});

		subMenuExportar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String mensagem = "INFO: FUNCIONALIDADE DE EXPORTA��O DE DADOS AINDA N�O CONFIGURADA ADEQUADAMENTE";
				AUTGerenciadorLogs.registrarLog(mensagem);
				javax.swing.JOptionPane.showConfirmDialog(menuInicializacao, mensagem);			}
		});
		
		subMenuSair.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				AUTGerenciadorLogs.registrarLog("INFO : FINALIZANDO APLICATIVO PRINCIPAL DE CONFIGURA��O DO FRAMEWORK");				
				menuInicializacao.setVisible(false);
			}
		});
		
		subMenuRepArquivos.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				AUTRepositorioArquivos formRep = new AUTRepositorioArquivos();
				
				formRep.configurarRepositorioDados(menuInicializacao);
			}
		});
		
		barraMenus.add(menuArquivo);
		barraMenus.add(menuConfiguracoes);
		barraMenus.add(menuTestes);
		barraMenus.add(menuRelatorios);
		barraMenus.add(menuIntegracoes);
		barraMenus.add(menuAjuda);
		
		AUTAPIGUI api = new AUTAPIGUI();	
		
		menuInicializacao.add(barraMenus,java.awt.BorderLayout.PAGE_START);
	
		menuInicializacao.add(api.carregarImagem(imagemLogon,350,50,550,450));
				
		menuInicializacao.setVisible(true);
	}
	
	public AUTFrameworkMain() {
		super();
		configurarInicializacaoGUI();
	}
}
