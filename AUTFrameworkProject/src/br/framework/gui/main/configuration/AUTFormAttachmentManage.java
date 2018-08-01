package br.framework.gui.main.configuration;

import java.awt.event.ActionEvent;

import javax.swing.event.ChangeEvent;

import br.framework.api.AUTAPI;

/**
 * CLASSE RESPONSÁVEL PELO GERENCIAMENTO DE ANEXOS
 * 
 * @author Softtek - QA
 *
 */
public class AUTFormAttachmentManage {
	private javax.swing.JDialog formPrincipal = null;
	
	/**
	 * 
	 * Método responsável pela configuração da interface gráfica do gerenciador de anexos
	 * 
	 */
	public void configGUI() {
		javax.swing.JDialog formGerenciadorAnexo = new javax.swing.JDialog(formPrincipal);
		javax.swing.JLabel lbEntidadePrincipal = new javax.swing.JLabel("SINCRONIZAÇÃO:");
		javax.swing.JLabel lbTituloEstrutura = new javax.swing.JLabel("ESTRUTURA DO PROJETO:");
		javax.swing.JRadioButton rdInterativa = new javax.swing.JRadioButton("Interativa e Automátiva");
		javax.swing.JRadioButton rdManual = new javax.swing.JRadioButton("Manual");
		javax.swing.JPanel pnOpcoesSinc = new javax.swing.JPanel();
		javax.swing.JTree treEstruturaMaster = new javax.swing.JTree();
		javax.swing.JScrollPane scrEstruturaMaster = new javax.swing.JScrollPane(treEstruturaMaster,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JPanel pnAnexosControles = new javax.swing.JPanel();
		javax.swing.JButton btAdicionar = new javax.swing.JButton("Adicionar");
		javax.swing.JButton btAtualizar  = new javax.swing.JButton("Atualizar");
		javax.swing.JButton btExcluir = new javax.swing.JButton("Excluir");
		javax.swing.JButton btSair = new javax.swing.JButton("Fechar");
		javax.swing.JPanel pnHabModSelect = new javax.swing.JPanel();
		javax.swing.JRadioButton rdHabSelMultipla = new javax.swing.JRadioButton("Hab.Sel.Multiplos Arquivos");
		javax.swing.JRadioButton rdDesabSelMultipla = new javax.swing.JRadioButton("Des.Sel.Multiplos Arquivos");
		formGerenciadorAnexo.setSize(new java.awt.Dimension(550,400));
		formGerenciadorAnexo.getContentPane().setBackground(java.awt.Color.WHITE);
		formGerenciadorAnexo.setLayout(new java.awt.GridBagLayout());		
		java.awt.GridBagConstraints configLayout = new java.awt.GridBagConstraints();
		javax.swing.JPopupMenu menuEstruturaProj = new javax.swing.JPopupMenu();
		
		btAdicionar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AUTFormAttachTypeSelect formTipoAnexo = new AUTFormAttachTypeSelect(formGerenciadorAnexo);
			}
		});
		
		configLayout.gridx = 0;
		configLayout.gridy = 0;
		configLayout.weightx = 0;
		configLayout.fill = configLayout.NONE;
		configLayout.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(15);
		formGerenciadorAnexo.add(lbEntidadePrincipal,configLayout);
		
		configLayout.gridx = 1;
		configLayout.weightx = 1;
		configLayout.fill = configLayout.HORIZONTAL;
		
		pnOpcoesSinc.setLayout(new java.awt.GridLayout(1,2,10,0));
		pnOpcoesSinc.setBackground(java.awt.Color.WHITE);
		pnOpcoesSinc.add(rdInterativa);
		pnOpcoesSinc.add(rdManual);
		
		formGerenciadorAnexo.add(pnOpcoesSinc);
		
		
		configLayout.gridx = 0;
		configLayout.gridy = 1;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		configLayout.gridwidth = 2;
		formGerenciadorAnexo.add(lbTituloEstrutura,configLayout);
		
		configLayout.gridx = 0;
		configLayout.gridy = 2;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
		
		formGerenciadorAnexo.add(scrEstruturaMaster,configLayout);
		
		
		pnHabModSelect.setLayout(new java.awt.GridLayout(1,2));
		pnHabModSelect.setBackground(java.awt.Color.WHITE);
		pnHabModSelect.add(rdHabSelMultipla);
		pnHabModSelect.add(rdDesabSelMultipla);
		
		configLayout.gridx = 0;
		configLayout.gridy = 3;
		configLayout.weightx = 1;
		configLayout.weighty = 0;		
		configLayout.fill = configLayout.HORIZONTAL;
		configLayout.gridwidth = 2;
		
		formGerenciadorAnexo.add(pnHabModSelect,configLayout);
		
		pnAnexosControles.setLayout(new java.awt.GridLayout(1,5));
		pnAnexosControles.setBackground(java.awt.Color.WHITE);
		
		pnAnexosControles.add(btAdicionar);
		pnAnexosControles.add(btAtualizar);
		pnAnexosControles.add(btExcluir);
		pnAnexosControles.add(btSair);
		
		
		configLayout.gridx = 0;
		configLayout.gridy = 4;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.gridwidth = 2;
		configLayout.fill = configLayout.HORIZONTAL;
		
		formGerenciadorAnexo.add(pnAnexosControles,configLayout);
		
		
		formGerenciadorAnexo.setTitle("Softtek : Gerenciador de Anexos");
		
		formGerenciadorAnexo.setLocationRelativeTo(formPrincipal);
		
		
		javax.swing.JMenuItem mnVisualizar = menuEstruturaProj.add("Visualizar");
		javax.swing.JMenuItem mnAdicionar = menuEstruturaProj.add("Adicionar Anexo");
		javax.swing.JMenuItem mnAtualizar = menuEstruturaProj.add("Atualizar Anexo");
		javax.swing.JMenu mnExcluir = new javax.swing.JMenu("Excluir Anexo");
		javax.swing.JMenuItem mnExcluirTodos = menuEstruturaProj.add("Excluir Todos os Anexos");
		
		
		mnVisualizar.setBackground(java.awt.Color.WHITE);
		mnAdicionar.setBackground(java.awt.Color.WHITE);
		mnAtualizar.setBackground(java.awt.Color.WHITE);
		mnExcluir.setBackground(java.awt.Color.WHITE);
		menuEstruturaProj.setBackground(java.awt.Color.WHITE);
		
		javax.swing.JPopupMenu menuSubExcluir = new javax.swing.JPopupMenu();
		
		mnExcluir.add(new javax.swing.JMenuItem("Anexo 2"));
		mnExcluir.add(new javax.swing.JMenuItem("Anexo 3"));
		mnExcluir.add(new javax.swing.JMenuItem("Anexo 4"));
		mnExcluir.add(new javax.swing.JMenuItem("Anexo 5"));
		mnExcluir.add(new javax.swing.JMenuItem("Anexo 6"));
		mnExcluir.add(new javax.swing.JMenuItem("Anexo 7"));
		mnExcluir.add(new javax.swing.JMenuItem("Anexo 8"));
		mnExcluir.add(new javax.swing.JMenuItem("Anexo 9"));
		mnExcluir.add(new javax.swing.JMenuItem("Anexo 10"));
		mnExcluir.add(new javax.swing.JMenuItem("Anexo 11"));
		mnExcluir.add(new javax.swing.JMenuItem("Anexo 12"));
		mnExcluir.add(new javax.swing.JMenuItem("Anexo 13"));
		
		menuEstruturaProj.add(mnExcluir);
		
		rdHabSelMultipla.addChangeListener(new javax.swing.event.ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent obj) {
				javax.swing.JRadioButton rd = (javax.swing.JRadioButton)obj.getSource();
				
				if(rd.isSelected()) {
					rdDesabSelMultipla.setSelected(false);
				}				
			}
		});
		
		
		rdDesabSelMultipla.addChangeListener(new javax.swing.event.ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent obj) {
				javax.swing.JRadioButton rd = (javax.swing.JRadioButton)obj.getSource();
				
				if(rd.isSelected()) {
					rdHabSelMultipla.setSelected(false);
				}				
			}
		});
		
		
		
		
		rdInterativa.addChangeListener(new javax.swing.event.ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent obj) {
				javax.swing.JRadioButton rd = (javax.swing.JRadioButton)obj.getSource();
				
				if(rd.isSelected()) {
					rdManual.setSelected(false);
					treEstruturaMaster.setComponentPopupMenu(menuEstruturaProj);
				}				
			}
		});
		
		
		rdManual.addChangeListener(new javax.swing.event.ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent obj) {
				javax.swing.JRadioButton rd = (javax.swing.JRadioButton)obj.getSource();
				
				if(rd.isSelected()) {
					rdInterativa.setSelected(false);
					treEstruturaMaster.setComponentPopupMenu(null);
				}				
			}
		});
		
		btSair.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("INFO: FECHANDO APP DE GERENCIAMENTO DE ANEXOS");
				formGerenciadorAnexo.setVisible(false);
			}
		});
		formGerenciadorAnexo.setVisible(true);		
	}
	
	/**
	 * 
	 * Construtor padrão da classe
	 * 
	 * @param form - Formulário sobre o qual o gerenciador de anexos será exibido
	 * 
	 */
	public AUTFormAttachmentManage(javax.swing.JDialog form) {
		formPrincipal = form;
		configGUI();
	}
	
	public AUTFormAttachmentManage() {
		configGUI();
	}
}
