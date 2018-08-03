package br.stk.framework.gui.main.config.db;

import java.awt.event.ActionEvent;

import br.stk.framework.api.AUTAPI;

/**
 * 
 * RESPONSÁVEL PELA CRIAÇÃO DE BANCO DE DADOS
 * 
 * @author SOFTTEK - QA
 *
 */
public class AUTFormPadrao {

	javax.swing.JDialog formPrincipal = null;	
	
	/**
	 * 
	 * CLASSE PELA CONSTRUÇÃO DA INTERFACE GRÁFICA COM O USUÁRIO
	 */
	public void configGUI() {
		javax.swing.JDialog formGUI = new javax.swing.JDialog(formPrincipal);				
		
		formGUI.getContentPane().setBackground(java.awt.Color.WHITE);
		formGUI.setSize(new java.awt.Dimension(800,700));
		formGUI.setLocationRelativeTo(formPrincipal);
		formGUI.setLayout(new java.awt.GridBagLayout());		
		java.awt.GridBagConstraints configLayout = new java.awt.GridBagConstraints();

		configLayout.gridx = 0;
		configLayout.gridy = 3;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		
		formGUI.setVisible(true);
	}
	
	/**
	 * 
	 * @param form - FORMULÁRIO SOBRE O QUAL SERÁ INICIALIZADO O APP DE GERENCIAMENTO DE CÓDIGO SQL
	 * 
	 */
	public AUTFormPadrao(javax.swing.JDialog form) {
		formPrincipal = form;
		configGUI();
	}
	
	/**
	 * 
	 * CONSTRUTOR PADRÃO DA CLASSE
	 * 
	 */
	public AUTFormPadrao() {
		configGUI();
	}
}
