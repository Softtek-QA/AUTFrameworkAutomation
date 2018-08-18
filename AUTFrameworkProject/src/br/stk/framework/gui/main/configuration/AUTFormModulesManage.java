package br.stk.framework.gui.main.configuration;

import java.awt.event.ActionEvent;

import javax.swing.event.ChangeEvent;

import br.stk.framework.api.AUTAPI;

/**
 * 
 * CLASSE RESPONSÁVEL PELO DE GERENCIAMENTO DE MÓDULOS DO SISTEMA
 * 
 * @author SOFTTEK - QA
 * 
 *
 */
public class AUTFormModulesManage {
	private javax.swing.JDialog formPrincipal = null;
	
	/**
	 * 
	 * Método responsável pela configuração da interface gráfica do gerenciamento de módulos dos sistema
	 * 
	 */
	public void configGUI() {
		javax.swing.JDialog formGerenciamentoModulos = new javax.swing.JDialog(formPrincipal);
		javax.swing.JLabel lbTituloProjetos = new javax.swing.JLabel("ESTRUTURA DE PROJETOS:");
		javax.swing.JTree treeProjetos  = new javax.swing.JTree();
		javax.swing.JScrollPane scrProjetos = new javax.swing.JScrollPane(treeProjetos,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JPanel pnModuloInput = new javax.swing.JPanel();
		
		javax.swing.JLabel lbNomeMod = new javax.swing.JLabel("NOME MÓDULO:");
		javax.swing.JLabel lbDescricao = new javax.swing.JLabel("DESCRIÇÃO DE FUNCIONALIDADES:");
		javax.swing.JLabel lbPrioridadeProj = new javax.swing.JLabel("PRIORIDADE:");
		
		javax.swing.JTextField txtNomeMod = new javax.swing.JTextField(30);
		javax.swing.JTextArea txtDescricaoMod = new javax.swing.JTextArea(10,20);
		javax.swing.JScrollPane scrDescricaoMod = new javax.swing.JScrollPane(txtDescricaoMod,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JComboBox cboPrioridade = new javax.swing.JComboBox(new Object[] {"Alta","Média","Baixa"});
		
		javax.swing.JPanel pnModuloControles = new javax.swing.JPanel();
		javax.swing.JButton btAdicionar = new javax.swing.JButton("Adicionar");
		javax.swing.JButton btAtualizar = new javax.swing.JButton("Atualizar");
		javax.swing.JButton btExcluir = new javax.swing.JButton("Excluir");
		javax.swing.JButton btFechar = new javax.swing.JButton("Fechar");
		javax.swing.JButton btGerenciarAnexos = new javax.swing.JButton("Gerenciar Anexos");
		
		javax.swing.JLabel lbTituloModulos = new javax.swing.JLabel("MÓDULOS DO PROJETO SELECIONADO:");
		javax.swing.JTree treeModulos = new javax.swing.JTree();
		javax.swing.JScrollPane scrModulos = new javax.swing.JScrollPane(treeModulos,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		javax.swing.JPanel pnConfigCNS = new javax.swing.JPanel();
		javax.swing.JLabel lbTituloConfiguracoes = new javax.swing.JLabel("CONFIGURAÇÕES DO MÓDULO SELECIONADO:");
		javax.swing.JTree treeConfiguracoes = new javax.swing.JTree();
		javax.swing.JScrollPane scrConfiguracoes = new javax.swing.JScrollPane(treeConfiguracoes,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		javax.swing.JLabel lbTituloCenarios = new javax.swing.JLabel("CENÁRIOS DO MÓDULO SELECIONADO:");
		javax.swing.JTree treeCenarios = new javax.swing.JTree();
		javax.swing.JScrollPane scrCenarios = new javax.swing.JScrollPane(treeCenarios,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JButton btCriarCN = new javax.swing.JButton("Criar");
		javax.swing.JButton btExcluirCN = new javax.swing.JButton("Excluir");
		javax.swing.JButton btConfigurarCN = new javax.swing.JButton("Configurar");
		
		
		javax.swing.JPopupMenu menusConfig = new javax.swing.JPopupMenu();
		javax.swing.JMenu menuHabilitarConfig = new javax.swing.JMenu("Habilitar Configurações");
		javax.swing.JMenu menuDesabilitarConfig = new javax.swing.JMenu("Desabilitar Configurações");
		
		javax.swing.JPopupMenu menusControleCenario = new javax.swing.JPopupMenu();
		javax.swing.JMenuItem menuAdicionarCenario = menusControleCenario.add(new javax.swing.JMenuItem("Adicionar Cenário"));
		javax.swing.JMenuItem menuExcluirCenario = menusControleCenario.add(new javax.swing.JMenuItem("Excluir Cenário"));
		javax.swing.JMenuItem menuCopiarCenario = menusControleCenario.add(new javax.swing.JMenuItem("Copiar de Cenário..."));
		javax.swing.JMenuItem menuReplicarCenario = menusControleCenario.add(new javax.swing.JMenuItem("Replicar Cenário"));
		
		treeCenarios.setComponentPopupMenu(menusControleCenario);
		
		pnConfigCNS.setBackground(java.awt.Color.WHITE);
		pnConfigCNS.setLayout(new java.awt.GridLayout(2,2));
				
		pnConfigCNS.add(lbTituloConfiguracoes);
		pnConfigCNS.add(lbTituloCenarios);
		pnConfigCNS.add(scrConfiguracoes);
		pnConfigCNS.add(scrCenarios);				
		
		pnModuloControles.setBackground(java.awt.Color.WHITE);
		pnModuloControles.setLayout(new java.awt.GridLayout(1,5));
		pnModuloControles.add(btAdicionar);
		pnModuloControles.add(btGerenciarAnexos);
		pnModuloControles.add(btAtualizar);
		pnModuloControles.add(btExcluir);
		pnModuloControles.add(btFechar);
		
		btGerenciarAnexos.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AUTFormAttachmentManage formAnexos = new AUTFormAttachmentManage(formGerenciamentoModulos);
			}
		});
		
		btFechar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("INFO: FECHANDO APLICATIVO DE GERENCIAMENTO DE MÓDULO DO SISTEMA");
				
				formGerenciamentoModulos.setVisible(false);
			}
		});
		
		
		
		java.awt.GridBagConstraints configLayoutMod = new java.awt.GridBagConstraints();
		
		pnModuloInput.setLayout(new java.awt.GridBagLayout());
		pnModuloInput.setBackground(java.awt.Color.WHITE);
		configLayoutMod.gridx = 0;
		configLayoutMod.gridy = 0;
		configLayoutMod.weightx = 0;
		configLayoutMod.weighty = 0;
		configLayoutMod.fill = configLayoutMod.NONE;
		configLayoutMod.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(5);
		
		pnModuloInput.add(lbNomeMod,configLayoutMod);
		configLayoutMod.gridx = 1;
		configLayoutMod.gridy = 0;
		configLayoutMod.weightx = 1;
		configLayoutMod.fill = configLayoutMod.HORIZONTAL;
		pnModuloInput.add(txtNomeMod,configLayoutMod);
		
		configLayoutMod.gridx = 0;
		configLayoutMod.gridy = 1;
		configLayoutMod.weightx = 1;
		configLayoutMod.weighty = 0;
		configLayoutMod.fill = configLayoutMod.HORIZONTAL;
		configLayoutMod.gridwidth = 2;
		
		pnModuloInput.add(lbDescricao,configLayoutMod);
		
		
		configLayoutMod.gridx = 0;
		configLayoutMod.gridy = 2;
		configLayoutMod.weightx = 1;
		configLayoutMod.weighty = 1;
		configLayoutMod.gridwidth = 2;
		configLayoutMod.fill = configLayoutMod.BOTH;
		
		pnModuloInput.add(scrDescricaoMod,configLayoutMod);
		
		configLayoutMod.gridx = 0;
		configLayoutMod.gridy = 3;
		configLayoutMod.gridwidth = 2;
		configLayoutMod.weightx = 0;
		configLayoutMod.weighty = 0;
		configLayoutMod.gridwidth = 1;
		configLayoutMod.fill = configLayoutMod.NONE;
		
		pnModuloInput.add(lbPrioridadeProj,configLayoutMod);
		
		configLayoutMod.gridx = 1;
		configLayoutMod.gridy = 3;
		configLayoutMod.gridwidth = 1;
		configLayoutMod.weightx = 1;
		configLayoutMod.fill = configLayoutMod.HORIZONTAL;
		
		pnModuloInput.add(cboPrioridade,configLayoutMod);
		
		
		
		
		java.awt.GridBagConstraints configLayout = new java.awt.GridBagConstraints();
		
		formGerenciamentoModulos.setLayout(new java.awt.GridBagLayout());
		
		formGerenciamentoModulos.getContentPane().setBackground(java.awt.Color.WHITE);
		
		configLayout.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(5);		
		configLayout.gridx = 0;
		configLayout.gridy = 0;
		configLayout.weightx = 1;
		configLayout.fill = configLayout.HORIZONTAL;
		
		formGerenciamentoModulos.add(lbTituloProjetos,configLayout);
		
		configLayout.gridx = 0;
		configLayout.gridy = 1;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
		
		formGerenciamentoModulos.add(scrProjetos, configLayout);
		
		configLayout.gridx = 0;
		configLayout.gridy = 2;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
		
		formGerenciamentoModulos.add(pnModuloInput,configLayout);
		
		configLayout.gridx = 0;
		configLayout.gridy = 3;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		formGerenciamentoModulos.add(pnModuloControles,configLayout);
		
		configLayout.gridx = 0;
		configLayout.gridy = 4;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		
		formGerenciamentoModulos.add(lbTituloModulos,configLayout);
		
		configLayout.gridx = 0;
		configLayout.gridy = 5;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
		
		formGerenciamentoModulos.add(scrModulos,configLayout);
		

		
		configLayout.gridx = 0;
		configLayout.gridy = 6;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
		
		for(Integer i = 1;i < 35;i++) {
			javax.swing.JCheckBoxMenuItem mnHab = (javax.swing.JCheckBoxMenuItem)menuHabilitarConfig.add(new javax.swing.JCheckBoxMenuItem("Configuração ".concat(i.toString())));
			javax.swing.JCheckBoxMenuItem mnDes = (javax.swing.JCheckBoxMenuItem)menuDesabilitarConfig.add(new javax.swing.JCheckBoxMenuItem("Configuração ".concat(i.toString())));			
			mnHab.addChangeListener(new javax.swing.event.ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					javax.swing.JCheckBoxMenuItem obj = (javax.swing.JCheckBoxMenuItem)e.getSource();
					
					if(obj.isSelected()) {
						mnDes.setSelected(true);
					}
				}
			});
			
			mnDes.addChangeListener(new javax.swing.event.ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					javax.swing.JCheckBoxMenuItem obj = (javax.swing.JCheckBoxMenuItem)e.getSource();
					
					if(!obj.isSelected()) {
						mnHab.setSelected(false);
					}
				}
			});			
		}
		
		
		menusConfig.add(menuHabilitarConfig);
		menusConfig.add(menuDesabilitarConfig);
		treeConfiguracoes.setComponentPopupMenu(menusConfig);
		
		formGerenciamentoModulos.add(pnConfigCNS,configLayout);
		
		formGerenciamentoModulos.setSize(new java.awt.Dimension(800,650));
		
		formGerenciamentoModulos.setLocationRelativeTo(formPrincipal);
		
		formGerenciamentoModulos.setVisible(true);
	}
	
	/**
	 * 
	 * Construtor padrão da classe
	 * 
	 */
	public AUTFormModulesManage() {
		configGUI();
	}	
	
	/**
	 * 
	 * Construtor 
	 * 
	 * @param form - Formulário sobre o qual será exibido o formulário para gerenciamento de módulos
	 * 
	 */
	public AUTFormModulesManage(javax.swing.JDialog form) {
		formPrincipal = form;
		configGUI();
		
	}
}
