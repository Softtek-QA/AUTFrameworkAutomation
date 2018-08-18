package br.stk.framework.gui.main.config;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.lang.Character.UnicodeScript;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.stk.framework.api.AUTAPI;
import br.stk.framework.gui.main.config.db.AUTFormFerramentaSQL;

/**
 * 
 * CLASSE RESPONSÁVEL PELO GERENCIAMENTO DE TESTES
 * 
 * - MODELAGEM
 * - CONFIGURAÇÃO
 * 
 * @author SOFTTEK - QA
 *
 */
public class AUTFormGerenciamentoTestes {
	javax.swing.JDialog formPrincipal = null;
	
	public void configGUI() {
		javax.swing.JDialog formGerTestes = new javax.swing.JDialog(formPrincipal);
		javax.swing.JTabbedPane tabContainer = new javax.swing.JTabbedPane();
		java.awt.GridBagConstraints configLayout = new java.awt.GridBagConstraints();
		javax.swing.JLabel lbIcone = new javax.swing.JLabel("abcdfgh");
		String mensagem = "r";
		formGerTestes.setTitle("Softtek-QA: Gerenciamento de Testes");
		formGerTestes.getContentPane().setBackground(java.awt.Color.WHITE);
		formGerTestes.setSize(new java.awt.Dimension(850,700));
		formGerTestes.setLayout(new java.awt.GridBagLayout());
		formGerTestes.setLocationRelativeTo(formPrincipal);
		
		configLayout.gridx = 0;
		configLayout.gridy = 0;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
	
		tabContainer.setBackground(java.awt.Color.WHITE);
		
		javax.swing.JLabel lbTeste = new javax.swing.JLabel();
	
		try {
			tabContainer.add("QA - BANCO DE DADOS", configGUITabDB(formGerTestes));
			tabContainer.add("QA - MODELAGEM", new javax.swing.JLabel(mensagem));
			tabContainer.add("QA - DADOS", new javax.swing.JLabel(mensagem));
			tabContainer.add("QA - AUTOMAÇÃO", new javax.swing.JLabel(mensagem));
			tabContainer.add("QA - RELATÓRIO EXECUÇÃO", new javax.swing.JLabel(mensagem));
			tabContainer.add("QA - ESTRUTURA", new javax.swing.JLabel(mensagem));
		}
		catch(java.lang.Exception e) {			
			System.out.println("ERRO: DURANTE A CONFIGURAÇÃO DE INTERFACE GRÁFICA DO GERENCIAMENTO DE TESTES");			
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		formGerTestes.add(tabContainer,configLayout);
				
		formGerTestes.setVisible(true);
	}
	
	
	public javax.swing.JPanel configGUITabDB(javax.swing.JDialog frm){
		
		try {
		javax.swing.JPanel pnInputDB = new javax.swing.JPanel();		
		
		javax.swing.JLabel lbNomeServidor = new javax.swing.JLabel("SERVIDOR DB: ");
		javax.swing.JTextField txtServidor = new javax.swing.JTextField(30);		
		javax.swing.JLabel lbListaDB = new javax.swing.JLabel("BANCO DE DADOS:");
		javax.swing.JList ltListaDB = new javax.swing.JList();		
		javax.swing.JScrollPane scrListaDB = new javax.swing.JScrollPane(ltListaDB,
				javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JLabel lbListaTabelas = new javax.swing.JLabel("TABELAS DO SISTEMA:");
		javax.swing.JList ltListaTabelas = new javax.swing.JList();		
		javax.swing.JScrollPane scrListaTabelas = new javax.swing.JScrollPane(ltListaTabelas,
				javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JLabel lbEstruturaDados  =new javax.swing.JLabel("ESTRUTURA DE DADOS DA TABELA:");
		javax.swing.JTree treeEstruturaDados = new javax.swing.JTree();
		javax.swing.JScrollPane scrEstruturaDados  = new javax.swing.JScrollPane(treeEstruturaDados,
				javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JPanel pnConstrolesDB = new javax.swing.JPanel();
		javax.swing.JButton btAdicionarConexao = new javax.swing.JButton("Adicionar Conexão");
		javax.swing.JButton btAlterarConexao = new javax.swing.JButton("Alterar Parametros da Conexão");
		javax.swing.JButton btStatusConexao = new javax.swing.JButton("Exibir Status Conexão");
		javax.swing.JButton btExcluirConexao = new javax.swing.JButton("Excluir Conexão");
		javax.swing.JButton btFecharApp = new javax.swing.JButton("Fechar");		
		javax.swing.JPopupMenu menuCMDGerDB = new javax.swing.JPopupMenu();		
		
		java.awt.GridBagConstraints configLayout = new java.awt.GridBagConstraints();
		
		javax.swing.JMenuItem menuEditorCodigo = menuCMDGerDB.add("Ferramenta : Edição e Teste de SQL");
		javax.swing.JMenuItem menuGerConnect = menuCMDGerDB.add("Gerenciar Conexões");
		javax.swing.JMenuItem menuCriarTabela = menuCMDGerDB.add("Criar Tabela");
		javax.swing.JMenuItem menuExcluirTabela = menuCMDGerDB.add("Excluir Tabela");
		javax.swing.JMenuItem menuAlterarTabela = menuCMDGerDB.add("Alterar Tabela");
		javax.swing.JMenu menuTabelaSelecionada = new javax.swing.JMenu("Tabela Selecionada");
		menuCMDGerDB.add(menuTabelaSelecionada);
		javax.swing.JMenuItem menuSubTabelaLimparRegistros = menuTabelaSelecionada.add("Limpar Todos os Registros");
		javax.swing.JMenuItem menuSubTabelaExibirRegistros = menuTabelaSelecionada.add("Exibir Registros Salvos");
		javax.swing.JMenu menuSubExpRegs = new javax.swing.JMenu("Exportar Registros");
		menuTabelaSelecionada.add(menuSubExpRegs);
		javax.swing.JMenuItem menuTabelaExpExcel = menuSubExpRegs.add("Arquivo Excel");
		javax.swing.JMenuItem menuTabelaExpXML = menuSubExpRegs.add("Arquivo XML");
		javax.swing.JMenuItem menuTabelaExpJSON= menuSubExpRegs.add("Arquivo JSON");
		
		javax.swing.JMenu menuBancoDados= new javax.swing.JMenu("SGDB: Bando de Dados");
		menuSubExpRegs.add(menuBancoDados);
		javax.swing.JMenuItem menuDBMysql= menuBancoDados.add("MySQL");
		javax.swing.JMenuItem menuDBSQLServer= menuBancoDados.add("SQL Server");
		javax.swing.JMenuItem menuDBJavaDerby = menuBancoDados.add("JAVA: Java Derby");
		javax.swing.JMenuItem menuDBJavaH2 = menuBancoDados.add("JAVA: H2");
		javax.swing.JMenuItem menuDBIBMDB2 = menuBancoDados.add("IBM DB2");

		javax.swing.JMenu menuImportarTabela = new javax.swing.JMenu("Importar Tabela");
		menuCMDGerDB.add(menuImportarTabela);
		javax.swing.JMenuItem subMenuImpTabXML = menuImportarTabela.add("Tabela em XML");
		javax.swing.JMenuItem subMenuImpTabExcel = menuImportarTabela.add("Tabela em Excel");
		javax.swing.JMenuItem subMenuImpTabSQLPadrao = menuImportarTabela.add("Tabela em SQL Padrão");
		javax.swing.JMenuItem subMenuImpTabCSV = menuImportarTabela.add("Tabela em CSV");
		javax.swing.JMenuItem subMenuImpTabWebServiceWSDL = menuImportarTabela.add("Tabela em WebService (WSDL)");
		javax.swing.JMenuItem subMenuImpTabWebServiceREST = menuImportarTabela.add("Tabela em WebService (REST)");
		javax.swing.JMenuItem subMenuImpTabWebServiceJAXWS = menuImportarTabela.add("Tabela em WebService (JAX-WS)");
		javax.swing.JMenu subMenuImpTabMainframe = new javax.swing.JMenu("Terminal Mainframe");
		menuImportarTabela.add(subMenuImpTabMainframe);
		javax.swing.JMenuItem subMenuImpTabTermMainframeTMP1 = subMenuImpTabMainframe.add("Template 1");
		javax.swing.JMenuItem subMenuImpTabTermMainframeTMP2 = subMenuImpTabMainframe.add("Template 2");
		javax.swing.JMenuItem subMenuImpTabTermMainframeTMP3 = subMenuImpTabMainframe.add("Template 3");
		javax.swing.JMenuItem subMenuImpTabTermMainframeCUSTOM1 = subMenuImpTabMainframe.add("Customizado 1");
		javax.swing.JMenuItem subMenuImpTabTermMainframeCUSTOM2 = subMenuImpTabMainframe.add("Customizado 2");
		javax.swing.JMenuItem subMenuImpTabTermMainframeCUSTOM3 = subMenuImpTabMainframe.add("Customizado 3");		
		
		treeEstruturaDados.setComponentPopupMenu(menuCMDGerDB);
		
		pnConstrolesDB.setBackground(java.awt.Color.WHITE);
		pnConstrolesDB.setLayout(new java.awt.GridLayout());
		pnConstrolesDB.add(btAdicionarConexao);
		pnConstrolesDB.add(btAlterarConexao);
		pnConstrolesDB.add(btExcluirConexao);
		pnConstrolesDB.add(btStatusConexao);
		pnConstrolesDB.add(btFecharApp);		
			
		menuGerConnect.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AUTConexoesBaseDados formConnect = new AUTConexoesBaseDados(formPrincipal);
			}
		});
		
		/**
		 * inicializa a ferramenta para gerenciamento de scripts SQL
		 */
		menuEditorCodigo.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AUTFormFerramentaSQL formFerramentaSQL = new AUTFormFerramentaSQL(frm);
			}
		});
		
		
		/**
		 * Fecha o APP de gerenciamento de testes
		 */
		btFecharApp.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				System.out.println("INFO: FECHANDO TELA DE GERENCIAMENTO DE BANCO DE DADOS");
				frm.setVisible(false);
			}
		});
				
		pnInputDB.setLayout(new java.awt.GridBagLayout());
		pnInputDB.setBackground(java.awt.Color.WHITE);
		configLayout.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(5);
		configLayout.gridx = 0;
		configLayout.gridy = 0;
		configLayout.weightx  = 0;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.NONE;		
		
		pnInputDB.add(lbNomeServidor,configLayout);
		
		configLayout.gridx = 1;
		configLayout.gridy = 0;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill  =configLayout.HORIZONTAL;
		
		pnInputDB.add(txtServidor,configLayout);

		configLayout.gridwidth = 2;
		configLayout.gridx = 0;
		configLayout.gridy = 1;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill  =configLayout.HORIZONTAL;
		
		pnInputDB.add(lbListaDB,configLayout);
		
		
		configLayout.gridx = 0;
		configLayout.gridy = 2;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		
		configLayout.fill  =configLayout.BOTH;
		
		pnInputDB.add(scrListaDB,configLayout);
		
		
		configLayout.gridwidth = 2;
		configLayout.gridx = 0;
		configLayout.gridy = 3;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill  =configLayout.HORIZONTAL;
		
		pnInputDB.add(lbListaTabelas,configLayout);
		
		
		configLayout.gridx = 0;
		configLayout.gridy = 4;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		
		configLayout.fill  =configLayout.BOTH;
		
		pnInputDB.add(scrListaTabelas,configLayout);		
		
		
		configLayout.gridwidth = 2;
		configLayout.gridx = 0;
		configLayout.gridy = 5;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill  =configLayout.HORIZONTAL;
		
		pnInputDB.add(lbEstruturaDados,configLayout);
		
		configLayout.gridx = 0;
		configLayout.gridy = 6;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		
		configLayout.fill  =configLayout.BOTH;
		
		pnInputDB.add(scrEstruturaDados,configLayout);		

		
		configLayout.gridwidth = 2;
		configLayout.gridx = 0;
		configLayout.gridy = 7;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill  =configLayout.HORIZONTAL;
		
		pnInputDB.add(pnConstrolesDB,configLayout);
		
		return pnInputDB;
		
		}
		catch(java.lang.Exception e) {
			System.out.println("ERRO: CONFIGURACAO : GERENCIAMENTO DE TESTES : GER.BANCO DE DADOS");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return new javax.swing.JPanel();
		}
		
	}
	
	/**
	 * 
	 * CONSTRUTOR
	 * 
	 */
	public AUTFormGerenciamentoTestes(javax.swing.JDialog form) {
		formPrincipal = form;
		configGUI();
	}
}
