package br.framework.gui.main.configuration;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;

import br.framework.api.AUTAPI;
import br.framework.api.AUTAPI.*;
import br.framework.api.AUTAPI.AUTJDBCConector.*;
import br.framework.api.runtime.AUTRuntimeConfiguration;
import br.framework.gui.eventos.AUTEventsWindowManage;
import br.framework.gui.logs.AUTLogsManage;

import org.apache.derby.jdbc.EmbeddedDriver;

/**
 * Classe responsável pelo gerenciamento de conexões com a base de dados
 * 
 * @author Softtek - QA
 *
 */
public class AUTFormSGDBIntegration {
	private javax.swing.JDialog formularioPrincipal = null;
	/**
	 * 
	 * Construtor padrão da classe
	 * 
	 */
	public AUTFormSGDBIntegration() {
		super();
		configurarGUI();
	}
	
	public AUTFormSGDBIntegration(javax.swing.JDialog formulario) {
		this.formularioPrincipal = formulario;
		configurarGUI();
	}	
	
	public String carregarURL(String nomeDB) {
		if(nomeDB.contains("Java Derby")) {
			return "jdbc:derby:C:\\tmp\\javaderbydb\\autpjt001;create=true;";
		}
		else if(nomeDB.contains("H2")) {
			return "jdbc:h2:C:/tmp/h2db/autpjt001";
		}
		else if(nomeDB.contains("DB2")) {
			return "jdbc:ibm.db2://localhost:3000/nomebancodedados";
		}
		else if(nomeDB.contains("SQL Server")) {
			return "jdbc:microsoft.sqlserver://localhost:3000/nomebancodedados";
		}
		else if(nomeDB.contains("MySQL")) {
			return "jdbc:mysql://localhost:3000/nomebancodedados";
		}
		else {
			return "jdbc.xxx://localhost:30000/nomebancodedados";
		}		
	}
	

	public String carregarClasseDriver(String nomeDB) {
		
		
		if(nomeDB.contains("Java Derby")) {
			return "org.apache.derby.jdbc.EmbeddedDriver";
		}
		else if(nomeDB.contains("H2")) {
			return "org.h2.Driver";
		}
		else if(nomeDB.contains("DB2")) {
			return "jdbc.ibm.db2://localhost:3000/nomebancodedados";
		}
		else if(nomeDB.contains("SQL Server")) {
			return "jdbc.microsoft.sqlserver";
		}
		else if(nomeDB.contains("MySQL")) {
			return "jdbc.mysql";
		}
		else {
			return "jdbc.xxx";
		}		
	}
	
	/**
	 * 
	 * Responsável pela configuração da interface gráfica
	 * 
	 */
	public void configurarGUI() {
		javax.swing.JDialog formularioConexao = new javax.swing.JDialog(formularioPrincipal);		
		java.awt.GridBagConstraints configuracao = new java.awt.GridBagConstraints();		
		
		String[] nomesDB = new String[]{"Java Derby","H2 (Java)","IBM DB2","MySQL","Microsoft SQL Server"};
		
		javax.swing.JComboBox cbNomesDb = new javax.swing.JComboBox(nomesDB);
		javax.swing.JLabel rotuloNomeDB = new javax.swing.JLabel("Banco de Dados:");
		javax.swing.JLabel rotuloURL = new javax.swing.JLabel("String de Conexão(URL): ");
		javax.swing.JLabel rotuloUsuario = new javax.swing.JLabel("Usuário: ");
		javax.swing.JLabel rotuloSenha = new javax.swing.JLabel("Senha: ");
		javax.swing.JLabel rotuloArquivoJar = new javax.swing.JLabel("Arquivo (Jar):");
		javax.swing.JLabel rotuloClassForName = new javax.swing.JLabel("Classe Conexão:");
		javax.swing.JTextField txtURL = new javax.swing.JTextField(30);
		javax.swing.JTextField txtUsuario = new javax.swing.JTextField(25);
		javax.swing.JTextField txtSenha = new javax.swing.JTextField(20);
		javax.swing.JTextField txtArquivoJar = new javax.swing.JTextField(25);
		javax.swing.JTextField txtClasseConexao = new javax.swing.JTextField(30);
		javax.swing.JButton botaoTestar = new javax.swing.JButton("Testar Conexão");
		javax.swing.JButton botaoSalvarConexao = new javax.swing.JButton("Salvar Conexão");
		javax.swing.JButton botaoIrParaManutencao = new javax.swing.JButton("Ir Manutenção");
		javax.swing.JButton botaoArquivoJar = new javax.swing.JButton("Arquivo");
		
		javax.swing.JTextArea txtLogMensagem = new javax.swing.JTextArea(8,44);
		javax.swing.JPanel containerMensagensLog = new javax.swing.JPanel();
		javax.swing.JScrollPane containerMsgLog = new javax.swing.JScrollPane(txtLogMensagem,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		
		javax.swing.JPanel containerDB = new javax.swing.JPanel();
		//javax.swing.JPanel containerArquivoJar = new javax.swing.JPanel();
		javax.swing.JPanel containerUsuario = new javax.swing.JPanel();
		javax.swing.JPanel containerSenha = new javax.swing.JPanel();
		javax.swing.JPanel containerURL = new javax.swing.JPanel();
		javax.swing.JPanel containerClasseConexao = new javax.swing.JPanel();
		javax.swing.JPanel containerComandos = new javax.swing.JPanel();
		javax.swing.JTable tabDBS = new javax.swing.JTable(new javax.swing.table.DefaultTableModel(10,12));
		javax.swing.JScrollPane scrTabDBS = new javax.swing.JScrollPane(tabDBS,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JPanel pnContainerPrincipal = new javax.swing.JPanel();
		javax.swing.JPanel pnContainerOutput = new javax.swing.JPanel();
		javax.swing.JPanel pnContainerInput = new javax.swing.JPanel();
		java.awt.GridBagConstraints configLayout = new java.awt.GridBagConstraints();
		pnContainerPrincipal.setBackground(java.awt.Color.WHITE);
		pnContainerPrincipal.setLayout(new java.awt.GridBagLayout());
		
		
		
		tabDBS.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		
		formularioConexao.setLayout(new java.awt.GridBagLayout());                
		formularioConexao.setSize(600, 550);
		formularioConexao.getContentPane().setBackground(java.awt.Color.WHITE);
		formularioConexao.setLocationRelativeTo(formularioPrincipal);

		
		
		botaoSalvarConexao.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AUTRuntimeConfiguration.AUT_DB_STATUS_CONEXAO_ATUAL = true;				
			}
		});
		
		containerComandos.setBackground(java.awt.Color.WHITE);
		containerComandos.add(botaoSalvarConexao);
		containerComandos.add(botaoTestar);
		containerComandos.add(botaoIrParaManutencao);
		
		containerMensagensLog.add(containerMsgLog);
		pnContainerInput.setBackground(java.awt.Color.WHITE);
		pnContainerInput.setLayout(new java.awt.GridBagLayout());
		pnContainerOutput.setBackground(java.awt.Color.WHITE);
		
		
		configLayout.anchor = configLayout.LINE_START;
		configLayout.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(5);
		
		
		
		configLayout.gridx = 0;
		configLayout.gridy = 0;
		configLayout.weightx = 0;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.NONE;				
		pnContainerInput.add(rotuloNomeDB,configLayout);
		
		configLayout.gridx = 1;
		configLayout.gridy = 0;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;	
		pnContainerInput.add(cbNomesDb,configLayout);		
		
		
		configLayout.gridx = 0;
		configLayout.gridy = 1;
		configLayout.weightx = 0;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.NONE;				
		pnContainerInput.add(rotuloUsuario,configLayout);
		
		configLayout.gridx = 1;
		configLayout.gridy = 1;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;	
		pnContainerInput.add(txtUsuario,configLayout);
		

		configLayout.gridx = 0;
		configLayout.gridy = 2;
		configLayout.weightx = 0;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.NONE;				
		pnContainerInput.add(rotuloSenha,configLayout);
		
		configLayout.gridx = 1;
		configLayout.gridy = 2;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		pnContainerInput.add(txtSenha,configLayout);

		
		configLayout.gridx = 0;
		configLayout.gridy = 3;
		configLayout.weightx = 0;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.NONE;				
		pnContainerInput.add(rotuloURL,configLayout);
		
		configLayout.gridx = 1;
		configLayout.gridy = 3;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		pnContainerInput.add(txtURL,configLayout);
		
				
		configLayout.gridx = 0;
		configLayout.gridy = 4;
		configLayout.weightx = 0;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.NONE;				
		pnContainerInput.add(rotuloClassForName,configLayout);
		
		configLayout.gridx = 1;
		configLayout.gridy = 4;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		pnContainerInput.add(txtClasseConexao,configLayout);
		
		configLayout.gridwidth = 2;
		configLayout.gridx = 0;
		configLayout.gridy = 5;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		pnContainerInput.add(containerComandos,configLayout);
		
		configLayout.gridwidth = 2;
		configLayout.gridx = 0;
		configLayout.gridy = 6;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
		pnContainerInput.add(containerMsgLog,configLayout);
		
		
		configLayout.gridwidth = 2;
		configLayout.gridx = 0;
		configLayout.gridy = 7;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
		pnContainerInput.add(scrTabDBS,configLayout);

		
		
		configLayout.gridx = 0;
		configLayout.gridy = 0;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
		formularioConexao.getContentPane().add(pnContainerInput, configLayout);
		
		
		botaoArquivoJar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				txtArquivoJar.setText(AUTAPI.gerarCaixaDialog(formularioConexao, new String[] {"jar"}));
			}
		});
		
		
		cbNomesDb.addItemListener(new java.awt.event.ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent item) {
				
				javax.swing.JComboBox combo = (javax.swing.JComboBox)item.getSource();
				
				txtURL.setText(carregarURL(combo.getSelectedItem().toString()));
				txtClasseConexao.setText(carregarClasseDriver(combo.getSelectedItem().toString()));
			}
			
		});
		
		formularioConexao.addWindowListener(new AUTEventsWindowManage() {			
			@Override
			public void janelaFechada(WindowEvent evento) {
				
			}
			
			@Override
			public void janelaExibidaNaTela(WindowEvent evento) {
				AUTLogsManage.registrarLog(evento);
				txtURL.setText(carregarURL(cbNomesDb.getSelectedItem().toString()));
				txtClasseConexao.setText(carregarClasseDriver(cbNomesDb.getSelectedItem().toString()));
			}
		});
		
		
		botaoTestar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				AUTLogsManage.habilitarCapturaListaLogs();
				AUTJDBCConector autCon = new AUTJDBCConector(txtClasseConexao.getText(),txtURL.getText(),txtUsuario.getText(),txtSenha.getText());
				AUTRuntimeConfiguration.AUT_DB_CONEXAO_PRINCIPAL = autCon.conectarBancoDeDados();	
				txtLogMensagem.removeAll();
				txtLogMensagem.setText("");
				for(Object item : AUTLogsManage.carregarMensagensLog()) {
					txtLogMensagem.append(item.toString().concat("\n"));
				}
			}
		});
		
		
		botaoIrParaManutencao.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				AUTLogsManage.registrarLog(String.format("INFO : VARIAVEL AMBIENTE : CLASSPATH :",java.lang.System.getenv("classpath")));
							
			}
		});
		
		formularioConexao.setVisible(true);
	}
}
