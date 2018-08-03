package br.stk.framework.gui.main.configuration;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

import org.apache.derby.iapi.services.io.FormatIdUtil;

/**
 * GERENCIA OS TIPOS DE ANEXO QUE SERAM CARREGADOS PELO SISTEMA
 * 
 * @author SOFTTEK - QA
 *
 */
public class AUTFormAttachTypeSelect {
	private javax.swing.JDialog formPrincipal =  null;
	
	/**
	 *
	 * CONFIGURAÇÃO A INTERFACE GRÁFICA DO FORMULÁRIO DE GERENCIAMENTO DE TIPOS DE ANEXO DO OBJETO
	 *
	 */
	public void configGUI() {
		javax.swing.JDialog formTipoAnexo = new javax.swing.JDialog(formPrincipal);
		javax.swing.JPanel pnInput = new javax.swing.JPanel();
		javax.swing.JButton btFechar = new javax.swing.JButton("Confirmar");
		javax.swing.JLabel lbTipoAnexo = new javax.swing.JLabel("TIPO ANEXO:");
		javax.swing.JComboBox cboTipoAnexo = new javax.swing.JComboBox(new Object[] {
				"",
				"DOCUMENTAÇÃO DE PROJETO",
				"DOCUMENTAÇÃO DE CÓDIGO",
				"ESPECIFICAÇÃO DE PROJETO",
				"ESPECIFICAÇÃO DE CÓDIGO",
				"DOCUMENTAÇÃO DE PROPÓSITO GERAL",
				"HELP DO PROJETO - FLUXO DE NEGÓCIO",
				"HELP DO PROJETO - FLUXO DE DADOS",
				"HELP DO PROJETO - ARQUITETURA GERAL",
				"FRAMEWORK - PARA TESTES DA APLICAÇÃO",
				"SCRIPTS - BAT (WINDOWS)",
				"SCRIPTS - POWERSHELL",
				"SCRIPTS - VBSCRIPT",
				"PROJETO - JAVA",
				"PROJETO - .NET(C#)",
				"PROJETO - .NET(VBNET)",
				"CLASSE - JAVA",
				"CLASSE - .NET(C#)",
				"CLASSE - .NET(VBNET)",
				"PROJETO TESTE(AUTOMATIZADO) - UFT(HP / MICROFOCUS)",
				"PROJETO TESTE(AUTOMATIZADO) - SILK TEST (MICROFOCUS)",
				"PROJETO TESTE(AUTOMATIZADO) - JUNIT",
				"PROJETO TESTE(AUTOMATIZADO) - SELENIUM",
				"PROJETO TESTE(AUTOMATIZADO) - SILK4J",
				"PROJETO TESTE(AUTOMATIZADO-MOBILE) - CALABASH",
				"PROJETO TESTE(AUTOMATIZADO-MOBILE) - ANDROID GENERICO"
				});
		formTipoAnexo.setTitle("SOFTTEK : GERENCIAMENTO DE ANEXOS");
		formTipoAnexo.setLocationRelativeTo(formPrincipal);
		formTipoAnexo.getContentPane().setBackground(java.awt.Color.WHITE);
		formTipoAnexo.setLayout(new java.awt.GridBagLayout());
		java.awt.GridBagConstraints configLayout = new java.awt.GridBagConstraints();
		
		configLayout.gridx = 0;
		configLayout.gridy = 0;
		configLayout.weightx = 0;
		configLayout.fill = configLayout.NONE;
		formTipoAnexo.add(lbTipoAnexo,configLayout);
		
		configLayout.gridx = 1;
		configLayout.gridy = 0;
		configLayout.weightx = 1;
		configLayout.fill = configLayout.HORIZONTAL;
		
		formTipoAnexo.add(cboTipoAnexo, configLayout);
		formTipoAnexo.add(btFechar, configLayout);
		
		configLayout.gridx = 2;
		configLayout.weightx = 0;
		configLayout.fill = configLayout.NONE;
		
		formTipoAnexo.add(btFechar, configLayout);
		
		
		
		btFechar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				formTipoAnexo.setVisible(false);
			}
		});
		formTipoAnexo.setSize(new java.awt.Dimension(550,150));
		
		cboTipoAnexo.addItemListener(new java.awt.event.ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				cboTipoAnexo.setToolTipText(cboTipoAnexo.getSelectedItem().toString());
			}
		});
		
		formTipoAnexo.setVisible(true);
	}
	
	
	/**
	 *
	 * CONSTRUTOR PADRÃO DA CLASSE
	 *
	 */
	public AUTFormAttachTypeSelect() {
		configGUI();
	}
	
	/**
	 * 
	 * CONSTRUTOR
	 * 
	 * @param form - Formulário sobre o qual será exibido a tela de gerenciamento de anexos do projeto
	 * 
	 */
	public AUTFormAttachTypeSelect(javax.swing.JDialog form) {
		formPrincipal = form;
		configGUI();
	}	
}
