package br.stk.framework.gui.main.config.db;

import java.awt.event.ActionEvent;

import br.stk.framework.api.AUTAPI;

/**
 * 
 * RESPONS�VEL PELA CRIA��O DE BANCO DE DADOS
 * 
 * @author SOFTTEK - QA
 *
 */
public class AUTFormPadrao {

	javax.swing.JDialog formPrincipal = null;	
	
	/**
	 * 
	 * CLASSE PELA CONSTRU��O DA INTERFACE GR�FICA COM O USU�RIO
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
	 * @param form - FORMUL�RIO SOBRE O QUAL SER� INICIALIZADO O APP DE GERENCIAMENTO DE C�DIGO SQL
	 * 
	 */
	public AUTFormPadrao(javax.swing.JDialog form) {
		formPrincipal = form;
		configGUI();
	}
	
	/**
	 * 
	 * CONSTRUTOR PADR�O DA CLASSE
	 * 
	 */
	public AUTFormPadrao() {
		configGUI();
	}
}
