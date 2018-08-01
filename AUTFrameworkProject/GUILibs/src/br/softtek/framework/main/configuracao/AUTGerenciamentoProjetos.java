package br.softtek.framework.main.configuracao;

import java.awt.event.ActionEvent;

import br.softtek.framework.api.AUTAPI;

/**
 * GERENCIAMENTO DE DADOS DO PROJETOS
 * 
 *  
 * @author Softtek - QA
 *
 */
public class AUTGerenciamentoProjetos {
	public javax.swing.JDialog formPrincipal = null;
	
	public void configurarInicializacao() {
		javax.swing.JDialog formGerenciamentoProj = new javax.swing.JDialog(formPrincipal);
		java.awt.GridBagConstraints configLayout = new java.awt.GridBagConstraints();		
		javax.swing.JPanel pnInputProject = new javax.swing.JPanel();
		javax.swing.JLabel lbNomeProj = new javax.swing.JLabel("NOME PROJETO:");
		javax.swing.JTextField txtNomeProj = new javax.swing.JTextField(35);
		javax.swing.JLabel lbDecricaoProj = new javax.swing.JLabel("DESCRICAO: ");
		javax.swing.JTextArea txtDescricao = new javax.swing.JTextArea(5,30);
		javax.swing.JScrollPane scrDescricao = new javax.swing.JScrollPane(txtDescricao,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JPanel pnContatos = new javax.swing.JPanel();
		javax.swing.JPanel pnContatosInput = new javax.swing.JPanel();
		javax.swing.JPanel pnContatosControles = new javax.swing.JPanel();
		javax.swing.JPanel pnContatosLista = new javax.swing.JPanel();
		javax.swing.JTable tabelaContatos = new javax.swing.JTable(new javax.swing.table.DefaultTableModel(new Object[] {"NOME","TELEFONE","CELULAR","EMAIL","ASSUNTO"},10));
		javax.swing.JScrollPane scrTabelaContatos = new javax.swing.JScrollPane(tabelaContatos,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		javax.swing.JLabel lbTituloContatos = new javax.swing.JLabel("CONTATOS DO PROJETO:");
		javax.swing.JLabel lbNomeContato = new javax.swing.JLabel("NOME: ");
		javax.swing.JLabel lbTelContato = new javax.swing.JLabel("TELEFONE: ");
		javax.swing.JLabel lbCelContato = new javax.swing.JLabel("CELULAR: ");
		javax.swing.JLabel lbEmailContato = new javax.swing.JLabel("EMAIL: ");
		javax.swing.JLabel lbAssuntos  = new javax.swing.JLabel("ASSUNTOS DE SUA RESPONSABILIDADE:");
		javax.swing.JLabel lbTituloProjetos = new javax.swing.JLabel("PORTFÓLIO DE PROJETOS:");	
		javax.swing.JTree treProjetos = new javax.swing.JTree();
		javax.swing.JScrollPane scrProjetos = new javax.swing.JScrollPane(treProjetos,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JTextField txtNomeContato = new javax.swing.JTextField(30);
		javax.swing.JTextField txtTelContato  = new javax.swing.JTextField(15);
		javax.swing.JTextField txtCelContato  = new javax.swing.JTextField(15);
		javax.swing.JTextField txtEmailContato  = new javax.swing.JTextField(15);
		javax.swing.JTextArea txtAssuntoContato  = new javax.swing.JTextArea(5,30);
		javax.swing.JScrollPane scrAssuntoContato  = new javax.swing.JScrollPane(txtAssuntoContato,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JButton btAdicionarContato = new javax.swing.JButton("Adicionar Contato");
		javax.swing.JButton btAtualizarContato = new javax.swing.JButton("Atualizar Contato");
		javax.swing.JButton btExcluirContato = new javax.swing.JButton("Excluir Contato");
		javax.swing.JButton btFecharApp = new javax.swing.JButton("Sair");
		javax.swing.JPanel pnControlesProjeto = new javax.swing.JPanel();
		javax.swing.JButton btAdicionarProj = new javax.swing.JButton("Adicionar");
		javax.swing.JButton btAtualizarProj = new javax.swing.JButton("Atualizar");
		javax.swing.JButton btExcluirProj = new javax.swing.JButton("Excluir");
		javax.swing.JButton btGerenciarAnexosProj = new javax.swing.JButton("Gerenciar Anexos");
		
		formGerenciamentoProj.setLayout(new java.awt.GridBagLayout());
		
		configLayout.gridx = 0;
		configLayout.gridy = 0;
		configLayout.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(10);		
		formGerenciamentoProj.add(lbNomeProj,configLayout);	
		configLayout.gridx = 1;
		configLayout.fill = configLayout.HORIZONTAL;
		configLayout.weightx = 1;
				
		formGerenciamentoProj.add(txtNomeProj,configLayout);	
						
		
		configLayout.gridx = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		configLayout.weightx = 1;
		configLayout.gridwidth = 2;
		configLayout.gridy = 1;
		formGerenciamentoProj.add(lbDecricaoProj,configLayout);
		
		configLayout.gridy = 2;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
		formGerenciamentoProj.add(scrDescricao,configLayout);

		configLayout.gridx = 0;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.gridy = 3;
		configLayout.fill = configLayout.HORIZONTAL;
		
		formGerenciamentoProj.add(lbTituloContatos, configLayout);
		
		java.awt.GridBagConstraints configContInput = new java.awt.GridBagConstraints();		
		
		
		//configContInput.fill = configContInput.HORIZONTAL;
		pnContatosInput.setLayout(new java.awt.GridBagLayout());
		pnContatosInput.setBackground(java.awt.Color.WHITE);
		
		configContInput.gridx = 0;
		configContInput.gridy = 0;
		configContInput.weightx = 0;
		configContInput.fill = configContInput.NONE;
		configContInput.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(5);
		pnContatosInput.add(lbNomeContato,configContInput);
		configContInput.gridx = 1;
		configContInput.weightx = 1;
		configContInput.fill = configContInput.BOTH;
		pnContatosInput.add(txtNomeContato,configContInput);
		configContInput.weightx = 0;
		configContInput.gridx = 2;
		configContInput.fill = configContInput.NONE;
		pnContatosInput.add(lbTelContato,configContInput);
		configContInput.gridx = 3;
		configContInput.weightx = 1;
		configContInput.fill = configContInput.BOTH;
		pnContatosInput.add(txtTelContato,configContInput);
		configContInput.weightx = 0;
	    configContInput.gridx = 4;
	    configContInput.fill = configContInput.NONE;
	    pnContatosInput.add(lbCelContato,configContInput);
	    configContInput.gridx = 5;
	    configContInput.weightx = 1;
	    configContInput.fill = configContInput.BOTH;
		pnContatosInput.add(txtCelContato,configContInput);
	    
		configContInput.weightx = 0;
		configContInput.fill = configContInput.NONE;
		configContInput.weighty = 0;
		configContInput.gridx = 0;
		configContInput.gridy = 1;
		pnContatosInput.add(lbEmailContato,configContInput);
		
		configContInput.gridx = 1;
		configContInput.weightx = 1;
		configContInput.gridwidth = 4;		
		configContInput.fill = configContInput.HORIZONTAL;
				
		pnContatosInput.add(txtEmailContato, configContInput);
	
		configContInput.gridx = 0;
		configContInput.gridy = 2;
		configContInput.gridwidth = 6;
		configContInput.weightx = 1;
		configContInput.fill = configContInput.HORIZONTAL;
		
		pnContatosInput.add(lbAssuntos,configContInput);
		
		configContInput.gridx = 0;
		configContInput.gridy = 3;
		configContInput.weightx = 1;
		configContInput.weighty = 1;
		configContInput.fill = configContInput.BOTH;
		configContInput.gridwidth = 6;
		
		pnContatosInput.add(scrAssuntoContato,configContInput);
		
		pnContatosControles.setLayout(new java.awt.GridLayout(1,4));
		pnContatosControles.setBackground(java.awt.Color.WHITE);
		pnContatosControles.add(btAdicionarContato);
		pnContatosControles.add(btAtualizarContato);
		pnContatosControles.add(btExcluirContato);
	
		
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		configLayout.gridy = 4;
		
		formGerenciamentoProj.add(pnContatosInput,configLayout);		
		
				
		configLayout.gridx = 0;
		configLayout.gridy = 5;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
		
		formGerenciamentoProj.add(scrTabelaContatos,configLayout);
		
		configLayout.gridx = 0;
		configLayout.gridy = 6;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		
		formGerenciamentoProj.add(pnContatosControles,configLayout);
		
		configLayout.gridx = 0;
		configLayout.gridy = 7;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		
		formGerenciamentoProj.add(lbTituloProjetos, configLayout);
		
		configLayout.gridx = 0;
		configLayout.gridy = 8;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;		
		formGerenciamentoProj.add(scrProjetos, configLayout);
		
		
		
		pnControlesProjeto.setLayout(new java.awt.GridLayout(1,5));
		pnControlesProjeto.setBackground(java.awt.Color.WHITE);
		pnControlesProjeto.add(btAdicionarProj);
		pnControlesProjeto.add(btAtualizarProj);
		pnControlesProjeto.add(btExcluirProj);
		pnControlesProjeto.add(btGerenciarAnexosProj);
		pnControlesProjeto.add(btFecharApp);
		
		configLayout.gridx = 0;
		configLayout.gridy = 9;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		
		formGerenciamentoProj.add(pnControlesProjeto, configLayout);
		
		
		
		//TRATAMENTO DE EVENTOS GERADOS PELO SISTEMA
		
		btFecharApp.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("INFO: FECHANDO TELA DE GERENCIAMENTO DE PROJETOS");
				
				formGerenciamentoProj.setVisible(false);				
			}
		});
		
		
		btGerenciarAnexosProj.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				AUTFormAnexosGerenciamento formAnexos = new AUTFormAnexosGerenciamento(formGerenciamentoProj);
				
			}
		});
		
		
		formGerenciamentoProj.setSize(new java.awt.Dimension(850,700));

		formGerenciamentoProj.getContentPane().setBackground(java.awt.Color.WHITE);
		
		formGerenciamentoProj.setTitle("SOFTTEK: GERENCIAMENTO DE PROJETOS");
		
		formGerenciamentoProj.setLocationRelativeTo(formPrincipal);
		
		formGerenciamentoProj.setVisible(true);
		
	} 
	/**
	 * 
	 * CONSTRUTOR PADRÃO DO PROJETO
	 * 
	 */
	public AUTGerenciamentoProjetos() {
		configurarInicializacao();
	}
	
	public AUTGerenciamentoProjetos(javax.swing.JDialog form) {
		formPrincipal = form;
		configurarInicializacao();
	}
}
