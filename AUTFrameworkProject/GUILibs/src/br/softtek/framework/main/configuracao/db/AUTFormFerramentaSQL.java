package br.softtek.framework.main.configuracao.db;

import java.awt.event.ActionEvent;

import br.softtek.framework.api.AUTAPI;

/**
 * 
 * CLASSE DE GERENCIAMENTO:
 * 
 * FERRAMENTA DE DESENVOLVIMENTO E TESTE DE CÓDIGO SQL
 * 
 * 
 * @author SOFTTEK - QA
 *
 */
public class AUTFormFerramentaSQL {

	javax.swing.JDialog formPrincipal = null;
	
	/**
	 * 
	 * CLASSE RESPONSÁVEL PELO GERENCIAMENTO E CONFIGURAÇÃO 
	 */
	public void configGUI() {
		javax.swing.JDialog formFerramentaSQL = new javax.swing.JDialog(formPrincipal);				
		java.awt.GridBagConstraints configLayout = new java.awt.GridBagConstraints();
		javax.swing.JLabel lbCodigoSQL = new javax.swing.JLabel("CÓDIGO SQL:");
		javax.swing.JTextArea txtCodSQL = new javax.swing.JTextArea(10,40);
		javax.swing.JScrollPane scrCodSQL = new javax.swing.JScrollPane(txtCodSQL,
				javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JTable tabelaDados = new javax.swing.JTable(new javax.swing.table.DefaultTableModel(10,20));
		javax.swing.JScrollPane scrTabDados = new javax.swing.JScrollPane(tabelaDados,
				javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JPanel pnControlesFerramenta = new javax.swing.JPanel();
		javax.swing.JLabel lbEstruturaDados = new javax.swing.JLabel("ESTRUTURAD DOS DADOS DA TABELA:");
		javax.swing.JTree treeEstruturaDados = new javax.swing.JTree();
		javax.swing.JScrollPane scrEstruturaDados = new javax.swing.JScrollPane(treeEstruturaDados,
				javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JButton btExecutarCMDSQL = new javax.swing.JButton("Executar");
		javax.swing.JButton btExportDadosTXT = new javax.swing.JButton("Exportar Dados em TXT");
		javax.swing.JButton btExportDadosExcel = new javax.swing.JButton("Exportar Dados em Excel");
		javax.swing.JButton btFecharApp = new javax.swing.JButton("Fechar");
		
				
		pnControlesFerramenta.setBackground(java.awt.Color.WHITE);
		pnControlesFerramenta.setLayout(new java.awt.GridLayout());
		pnControlesFerramenta.add(btExecutarCMDSQL);
		pnControlesFerramenta.add(btExportDadosExcel);
		pnControlesFerramenta.add(btExportDadosTXT);
		pnControlesFerramenta.add(btFecharApp);
		
		tabelaDados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		
		
		btFecharApp.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("INFO : FINALIZANDO APLICAÇÃO DE GESTÃO CÓDIGO SQL");
				formFerramentaSQL.setVisible(false);
			}
		});
		
		formFerramentaSQL.getContentPane().setBackground(java.awt.Color.WHITE);
		formFerramentaSQL.setSize(new java.awt.Dimension(800,700));
		formFerramentaSQL.setLocationRelativeTo(formPrincipal);
		formFerramentaSQL.setLayout(new java.awt.GridBagLayout());
		
		configLayout.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(5);
		configLayout.gridx = 0;
		configLayout.gridy = 0;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		formFerramentaSQL.add(lbCodigoSQL,configLayout);
		
		configLayout.gridx = 0;
		configLayout.gridy = 1;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
		formFerramentaSQL.add(scrCodSQL,configLayout);
		
		configLayout.gridx = 0;
		configLayout.gridy = 2;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
		formFerramentaSQL.add(scrTabDados,configLayout);	
		

		configLayout.gridx = 0;
		configLayout.gridy = 3;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		formFerramentaSQL.add(pnControlesFerramenta,configLayout);
		
		configLayout.gridx = 0;
		configLayout.gridy = 4;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		formFerramentaSQL.add(lbEstruturaDados,configLayout);
		
		configLayout.gridx = 0;
		configLayout.gridy = 5;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
		formFerramentaSQL.add(scrEstruturaDados,configLayout);
		
		formFerramentaSQL.setVisible(true);
	}
	
	/**
	 * 
	 * @param form - FORMULÁRIO SOBRE O QUAL SERÁ INICIALIZADO O APP DE GERENCIAMENTO DE CÓDIGO SQL
	 * 
	 */
	public AUTFormFerramentaSQL(javax.swing.JDialog form) {
		formPrincipal = form;
		configGUI();
	}
	
	/**
	 * 
	 * CONSTRUTOR PADRÃO DA CLASSE
	 * 
	 */
	public AUTFormFerramentaSQL() {
		configGUI();
	}
}
