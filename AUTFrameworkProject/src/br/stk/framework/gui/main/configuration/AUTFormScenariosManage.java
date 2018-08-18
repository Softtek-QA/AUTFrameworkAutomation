package br.stk.framework.gui.main.configuration;

import java.awt.event.ActionEvent;

import javax.swing.event.ChangeEvent;

import br.stk.framework.api.AUTAPI;

/**
 * CLASSE RESPONSÁVEL PELO GERENCIAMENTO DE CENÁRIOS  ASSOCIADOS A UM OU MAIS MÓDULOS DE NEGÓCIO
 * 
 * @author SOFTTEK - QA
 *
 */
public class AUTFormScenariosManage {
	private javax.swing.JDialog formPrincipal = null;
	/**
	 * 
	 * CLASSE RESPONSÁVEL PELA CONFIGURAÇÃO DA TELA DE GERENCIAMENTO DE CENÁRIOS  
	 * 
	 */
	public void configGUI() {
		javax.swing.JDialog formGerenciamentoCenarios = new javax.swing.JDialog(formPrincipal);
		javax.swing.JLabel lbTituloEstrutura = new javax.swing.JLabel("ESTRUTURA DO PROJETO: ");
		javax.swing.JTree treeEstruturaProjets = new javax.swing.JTree();
		javax.swing.JScrollPane scrEstruturaProj = new javax.swing.JScrollPane(treeEstruturaProjets,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
		javax.swing.JTabbedPane tabControleCenarios = new javax.swing.JTabbedPane();
		javax.swing.JPanel pnModManual = new javax.swing.JPanel();
		javax.swing.JPanel pnModAutomatico = new javax.swing.JPanel();
		javax.swing.JPanel pnModAutomaticoInput = new javax.swing.JPanel();
		javax.swing.JLabel lbMDManualNomeCenario = new javax.swing.JLabel("NOME:");
		javax.swing.JLabel lbMDManualNomeDescricao = new javax.swing.JLabel("DESCRIÇÃO:");
		javax.swing.JLabel lbMDModulos = new javax.swing.JLabel("MÓDULO:");
		javax.swing.JLabel lbMDModulos2 = new javax.swing.JLabel("MÓDULO:");
		
		
		javax.swing.JLabel lbMDAutNomeCenario = new javax.swing.JLabel("PREFIXO PADRÃO:");
		javax.swing.JLabel lbMDAutTotalCenarios = new javax.swing.JLabel("TOTAL:");
		javax.swing.JLabel lbMDAutOUCondicao = new javax.swing.JLabel(" OU ");
		javax.swing.JLabel lbMDAutDE = new javax.swing.JLabel("DE:");
		javax.swing.JLabel lbMDAutATE = new javax.swing.JLabel("ATE:");
		

		javax.swing.JTextField txtMDAutPrefixoNomeCenario = new javax.swing.JTextField(40);
		javax.swing.JTextField txtMDAutTotal = new javax.swing.JTextField(6);
		javax.swing.JTextField txtMDAutDE = new javax.swing.JTextField(15);
		javax.swing.JTextField txtMDAutATE = new javax.swing.JTextField(15);		
		
		javax.swing.JTextField txtMDManualNomeCenario = new javax.swing.JTextField(25);
		javax.swing.JTextArea txtMDManualDescCenario = new javax.swing.JTextArea(15,20);
		javax.swing.JScrollPane scrDescCenario = new javax.swing.JScrollPane(txtMDManualDescCenario,
				javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Object[] dadosModulos = new Object[] {"MÓDULO ATUAL","TODOS OS MÓDULOS DO PROJETO","SEM MÓDULO ASSOCIADO","CENÁRIO ALTERNATIVO (RECOVER CENÁRIO)"};
		javax.swing.JComboBox cboModulos = new javax.swing.JComboBox(dadosModulos);
		javax.swing.JComboBox cboModulos2 = new javax.swing.JComboBox(dadosModulos);
		
		
		
		javax.swing.JPanel pnControlesMDManual = new javax.swing.JPanel();
		javax.swing.JButton btAdicionarMDManual = new javax.swing.JButton("Adicionar");
		javax.swing.JButton btAtualizarMDManual = new javax.swing.JButton("Atualizar");
		javax.swing.JButton btExcluirMDManual = new javax.swing.JButton("Excluir");
		javax.swing.JButton btAnexosMDManual = new javax.swing.JButton("Anexos");
		javax.swing.JButton btFecharMDManual = new javax.swing.JButton("Fechar");
		
		
		javax.swing.JPanel pnControlesMDAut = new javax.swing.JPanel();
		javax.swing.JButton btAdicionarMDAut = new javax.swing.JButton("Adicionar");
		javax.swing.JButton btAtualizarMDAut = new javax.swing.JButton("Atualizar");
		javax.swing.JButton btAnexosMDAut = new javax.swing.JButton("Anexos");
		javax.swing.JButton btExcluirMDAut = new javax.swing.JButton("Excluir");
		javax.swing.JButton btFecharMDAut = new javax.swing.JButton("Fechar");

		
		pnControlesMDAut.setBackground(java.awt.Color.WHITE);
		pnControlesMDAut.setLayout(new java.awt.GridLayout());
		pnControlesMDAut.add(btAdicionarMDAut);
		pnControlesMDAut.add(btAnexosMDAut);
		pnControlesMDAut.add(btAtualizarMDAut);
		pnControlesMDAut.add(btExcluirMDAut);
		pnControlesMDAut.add(btFecharMDAut);
		
		btAnexosMDManual.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AUTFormAttachmentManage formAnexos = new AUTFormAttachmentManage(formGerenciamentoCenarios);
			}
		});
		
		
		btAnexosMDAut.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AUTFormAttachmentManage formAnexos = new AUTFormAttachmentManage(formGerenciamentoCenarios);
			}
		});
		
		btFecharMDAut.addActionListener(new java.awt.event.ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("INFO: FECHANDO APP DE GERENCIAMENTO DE CENÁRIOS DO TESTE");
				formGerenciamentoCenarios.setVisible(false);
			}
		});
		
		
		java.awt.GridBagConstraints configMDAut = new java.awt.GridBagConstraints();		
		pnModAutomatico.setBackground(java.awt.Color.WHITE);
		pnModAutomatico.setLayout(new java.awt.GridBagLayout());
		
		pnModAutomaticoInput.setBackground(java.awt.Color.WHITE);
		pnModAutomaticoInput.setLayout(new java.awt.GridLayout());
		
		
		configMDAut.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(10);
		configMDAut.anchor = configMDAut.LINE_START;
		configMDAut.gridx = 0;
		configMDAut.gridy = 0;
		configMDAut.weightx = 0;
		configMDAut.weighty = 0;
		configMDAut.fill = configMDAut.NONE;
		pnModAutomatico.add(lbMDModulos2,configMDAut);
		
		configMDAut.gridx = 1;
		configMDAut.gridy = 0;
		configMDAut.weightx = 1;
		configMDAut.weighty = 0;
		configMDAut.fill = configMDAut.HORIZONTAL;
		configMDAut.gridwidth = 6;
		pnModAutomatico.add(cboModulos2,configMDAut);
		configMDAut.gridwidth = 1;
		
		configMDAut.gridx = 0;
		configMDAut.gridy = 1;
		configMDAut.weightx = 0;
		configMDAut.weighty = 0;
		configMDAut.fill = configMDAut.NONE;
		pnModAutomatico.add(lbMDAutTotalCenarios,configMDAut);
				
		configMDAut.gridx = 1;
		configMDAut.gridy = 1;
		configMDAut.weightx = 1;
		configMDAut.weighty = 0;
		configMDAut.fill = configMDAut.HORIZONTAL;
		pnModAutomatico.add(txtMDAutTotal,configMDAut);
		
				
		configMDAut.gridx = 2;
		configMDAut.gridy = 1;
		configMDAut.weightx = 0;
		configMDAut.weighty = 0;
		configMDAut.fill = configMDAut.NONE;
		pnModAutomatico.add(lbMDAutOUCondicao,configMDAut);
		

		
		configMDAut.gridx = 3;
		configMDAut.gridy = 1;
		configMDAut.weightx = 0;
		configMDAut.weighty = 0;
		configMDAut.fill = configMDAut.NONE;
		pnModAutomatico.add(lbMDAutDE,configMDAut);
				
		configMDAut.gridx = 4;
		configMDAut.gridy = 1;
		configMDAut.weightx = 1;
		configMDAut.weighty = 0;
		configMDAut.fill = configMDAut.HORIZONTAL;
		pnModAutomatico.add(txtMDAutDE,configMDAut);

		configMDAut.gridx = 5;
		configMDAut.gridy = 1;
		configMDAut.weightx = 0;
		configMDAut.weighty = 0;
		configMDAut.fill = configMDAut.NONE;
		pnModAutomatico.add(lbMDAutATE,configMDAut);
				
		configMDAut.gridx = 6;
		configMDAut.gridy = 1;
		configMDAut.weightx = 1;
		configMDAut.weighty = 0;
		configMDAut.fill = configMDAut.HORIZONTAL;
		pnModAutomatico.add(txtMDAutATE,configMDAut);
		
		
		configMDAut.gridx = 0;
		configMDAut.gridy = 2;
		configMDAut.weightx = 0;
		configMDAut.weighty = 0;
		configMDAut.fill = configMDAut.NONE;
		pnModAutomatico.add(lbMDAutNomeCenario,configMDAut);
				
		configMDAut.gridx = 1;
		configMDAut.gridy = 2;
		configMDAut.weightx = 1;
		configMDAut.weighty = 0;
		configMDAut.fill = configMDAut.HORIZONTAL;
		configMDAut.gridwidth = 6;
		pnModAutomatico.add(txtMDAutPrefixoNomeCenario,configMDAut);
		

		configMDAut.gridx = 0;
		configMDAut.gridy = 3;
		configMDAut.weightx = 1;
		configMDAut.weighty = 0;
		configMDAut.fill = configMDAut.HORIZONTAL;
		configMDAut.gridwidth = 7;
		pnModAutomatico.add(pnControlesMDAut,configMDAut);

		
		
		
		pnControlesMDManual.setBackground(java.awt.Color.WHITE);
		pnControlesMDManual.setLayout(new java.awt.GridLayout(1, 4));
		pnControlesMDManual.add(btAdicionarMDManual);
		pnControlesMDManual.add(btAnexosMDManual);
		pnControlesMDManual.add(btAtualizarMDManual);
		pnControlesMDManual.add(btExcluirMDManual);
		pnControlesMDManual.add(btFecharMDManual);
		
		
		
		btFecharMDManual.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("INFO: FECHANDO APP DE GERENCIAMENTO DE CENÁRIOS DO TESTES");
				formGerenciamentoCenarios.setVisible(false);
			}
		});
		
		
		
		java.awt.GridBagConstraints configLayoutMDManual =  new java.awt.GridBagConstraints();
		pnModManual.setLayout(new java.awt.GridBagLayout());
		pnModManual.setBackground(java.awt.Color.WHITE);
		configLayoutMDManual.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(5);
		configLayoutMDManual.gridx = 0;
		configLayoutMDManual.gridy = 0;
		configLayoutMDManual.weightx = 0;
		configLayoutMDManual.weighty = 0;
		configLayoutMDManual.fill = configLayoutMDManual.NONE;
		pnModManual.add(lbMDModulos,configLayoutMDManual);
		
		configLayoutMDManual.gridx = 1;
		configLayoutMDManual.gridy = 0;
		configLayoutMDManual.weightx = 1;
		configLayoutMDManual.weighty = 0;
		configLayoutMDManual.fill = configLayoutMDManual.HORIZONTAL;
		pnModManual.add(cboModulos,configLayoutMDManual);
		
		configLayoutMDManual.gridx = 0;
		configLayoutMDManual.gridy = 1;
		configLayoutMDManual.weightx = 0;
		configLayoutMDManual.weighty = 0;
		configLayoutMDManual.fill = configLayoutMDManual.NONE;
		pnModManual.add(lbMDManualNomeCenario,configLayoutMDManual);
		
		
		configLayoutMDManual.gridx = 1;
		configLayoutMDManual.gridy = 1;
		configLayoutMDManual.weightx = 1;
		configLayoutMDManual.weighty = 0;
		configLayoutMDManual.fill = configLayoutMDManual.HORIZONTAL;
		pnModManual.add(txtMDManualNomeCenario,configLayoutMDManual);

		
		configLayoutMDManual.gridx = 0;
		configLayoutMDManual.gridy = 2;
		configLayoutMDManual.weightx = 0;
		configLayoutMDManual.weighty = 0;
		configLayoutMDManual.fill = configLayoutMDManual.NONE;
		configLayoutMDManual.gridwidth = 2;
		pnModManual.add(lbMDManualNomeDescricao,configLayoutMDManual);
		
		
		configLayoutMDManual.gridx = 0;
		configLayoutMDManual.gridy = 3;
		configLayoutMDManual.weightx = 1;
		configLayoutMDManual.weighty = 1;
		configLayoutMDManual.fill = configLayoutMDManual.BOTH;
		configLayoutMDManual.gridwidth = 2;
		pnModManual.add(scrDescCenario,configLayoutMDManual);
		
		
		configLayoutMDManual.gridx = 0;
		configLayoutMDManual.gridy = 4;
		configLayoutMDManual.weightx = 1;
		configLayoutMDManual.weighty = 0;
		configLayoutMDManual.fill = configLayoutMDManual.HORIZONTAL;
		configLayoutMDManual.gridwidth = 2;
		pnModManual.add(pnControlesMDManual,configLayoutMDManual);				
		
		tabControleCenarios.addTab("MODO: MANUAL", pnModManual);
		tabControleCenarios.addTab("MODO: AUTOMÁTICO", pnModAutomatico);
		
		
		formGerenciamentoCenarios.getContentPane().setBackground(java.awt.Color.WHITE);
		formGerenciamentoCenarios.setLayout(new java.awt.GridBagLayout());
		formGerenciamentoCenarios.setTitle("Sottek : Gerenciamento de Cenários dos Testes");
		formGerenciamentoCenarios.setSize(new java.awt.Dimension(700,500));
		
		java.awt.GridBagConstraints configLayout = new java.awt.GridBagConstraints();
		
		configLayout.gridx = 0;
		configLayout.gridy = 0;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(5);
		configLayout.fill = configLayout.BOTH;

		formGerenciamentoCenarios.add(tabControleCenarios,configLayout);
		
		configLayout.gridx = 0;
		configLayout.gridy = 1;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		
		formGerenciamentoCenarios.add(lbTituloEstrutura, configLayout);
		
		configLayout.gridx = 0;
		configLayout.gridy = 2;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		
		
		configLayout.gridx = 0;
		configLayout.gridy = 3;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
		formGerenciamentoCenarios.add(scrEstruturaProj, configLayout);
		
		formGerenciamentoCenarios.setLocationRelativeTo(formPrincipal);
		
		formGerenciamentoCenarios.setVisible(true);
	}
	
	/**
	 * 
	 * CONSTRUTOR
	 * 
	 */
	public AUTFormScenariosManage() {
		configGUI();		
	}
	
	
	/**
	 * 
	 * CONSTRUTOR
	 * 
	 * @param form - Formulário padrão
	 * 
	 */
	public AUTFormScenariosManage(javax.swing.JDialog form) {
		formPrincipal = form;
		configGUI();
	}
}
