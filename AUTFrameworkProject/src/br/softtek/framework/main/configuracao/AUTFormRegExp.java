package br.softtek.framework.main.configuracao;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import br.softtek.framework.api.runtime.*;
import br.softtek.framework.api.AUTAPIGUI;

/**
 * Classe responsável pelo desenvolvimento de expressões regulares para teste e formatação de dados
 * 
 * @author Softtek - QA
 *
 */
public class AUTFormRegExp {

	javax.swing.JDialog formPrincipal = null;
	/**
	 * 
	 * Configuração dos componentes de interface gráfica da aplicação
	 * 
	 */
	public void configGUI() {
		javax.swing.JDialog formRegExp = new javax.swing.JDialog(formPrincipal);
		javax.swing.JPanel pnPrincipal = new javax.swing.JPanel(); 
		javax.swing.JLabel lbExpressaoRegular = new javax.swing.JLabel("EXPRESSÃO REGULAR:");
		javax.swing.JTextArea txtExpressaoRegular = new javax.swing.JTextArea(4,30);
		javax.swing.JLabel lbEntrada = new javax.swing.JLabel("ENTRADA DE DADOS:");
		javax.swing.JLabel lbSaida = new javax.swing.JLabel("SAIDA DE DADOS:");
		javax.swing.JTextArea txtEntrada = new javax.swing.JTextArea(10,30);
		javax.swing.JTextArea txtSaida = new javax.swing.JTextArea(10,30);
		javax.swing.JScrollPane scrEntrada = new javax.swing.JScrollPane(txtEntrada,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JScrollPane scrSaida = new javax.swing.JScrollPane(txtSaida,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JScrollPane scrInputRegEx = new javax.swing.JScrollPane(txtExpressaoRegular,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JPanel pnContainerEntrada = new javax.swing.JPanel();
		javax.swing.JPanel pnBotoes = new javax.swing.JPanel();
		javax.swing.JRadioButton rdHabAutomatica = new javax.swing.JRadioButton("Validação Automática", false);
		javax.swing.JRadioButton rdHabManual = new javax.swing.JRadioButton("Validação Manual", true);
		javax.swing.JPanel pnSelectMode = new javax.swing.JPanel();
		javax.swing.JButton btTestar = new javax.swing.JButton("Testar");
		javax.swing.JButton btLimpar = new javax.swing.JButton("Limpar");
		javax.swing.JButton btFechar = new javax.swing.JButton("Fechar");
		javax.swing.JPanel pnLbDevices = new javax.swing.JPanel();
		
		rdHabManual.setName("manual");
		rdHabAutomatica.setName("automatico");
		pnSelectMode.setLayout(new java.awt.GridLayout(1,2));
		pnSelectMode.add(rdHabAutomatica);
		pnSelectMode.add(rdHabManual);
		
		rdHabAutomatica.addChangeListener(new javax.swing.event.ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {

				javax.swing.JRadioButton bt =  (javax.swing.JRadioButton)e.getSource();
				
				if(bt.isSelected()) {
					AUTRuntimeConfiguration.AUT_REGEX_MODO_AUTOMATICO = true;
					rdHabManual.setSelected(false);
				}				
			}
		});

		
		rdHabManual.addChangeListener(new javax.swing.event.ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {

				javax.swing.JRadioButton bt =  (javax.swing.JRadioButton)e.getSource();
				
				if(bt.isSelected()) {
					AUTRuntimeConfiguration.AUT_REGEX_MODO_AUTOMATICO = false;
					rdHabAutomatica.setSelected(false);
				}				
			}
		});
		pnBotoes.setLayout(new java.awt.GridLayout(1,3,10,10));
		pnBotoes.setBackground(java.awt.Color.WHITE);

		pnBotoes.add(btTestar);
		pnBotoes.add(btLimpar);
		pnBotoes.add(btFechar);


		txtExpressaoRegular.addKeyListener(new java.awt.event.KeyListener() {
			@Override
			public void keyTyped(KeyEvent key) {
				//System.out.println("INFO : TECLA typed");
				//System.out.println(key.getKeyChar());				
			}

			@Override
			public void keyPressed(KeyEvent key) {
				//System.out.println("INFO : TECLA pressed");
				//System.out.println(key.getKeyChar());
			}

			@Override
			public void keyReleased(KeyEvent key) {
				if(AUTRuntimeConfiguration.AUT_REGEX_MODO_AUTOMATICO) {
					try {
						java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(
								(txtExpressaoRegular.getText().trim().isEmpty()?".{0,}":txtExpressaoRegular.getText().trim())							
								);
						
						java.util.regex.Matcher verifPadrao = padrao.matcher(txtEntrada.getText());

						txtSaida.removeAll();
						txtSaida.setText("");

						while(verifPadrao.find()) {
							txtSaida.append(verifPadrao.group().concat("\n"));
							System.out.println(verifPadrao.group());
						}
					}
					catch(java.util.regex.PatternSyntaxException e) {
						/*System.out.println("ERRO: SINTASE DA EXPRESSAO REGULAR FORNECIDA");
						System.out.println(e.getMessage());
						e.printStackTrace();*/
					}
					catch(java.lang.Exception e) {
						/*System.out.println("ERRO: GERAL DO SISTEMA : EXPRESSAO REGULAR");
						System.out.println(e.getMessage());
						e.printStackTrace();*/
					}					
					
				}
			}
		});

		btTestar.addActionListener(new java.awt.event.ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				String regExp  = txtExpressaoRegular.getText();

				if(!regExp.isEmpty() && !regExp.equals("")) {

					java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(regExp);						
					if(!txtEntrada.getText().isEmpty() && !txtEntrada.getText().equals("")) {
						try {
							java.util.regex.Matcher verifPadrao = padrao.matcher(txtEntrada.getText());

							txtSaida.removeAll();
							txtSaida.setText("");

							while(verifPadrao.find()) {
								txtSaida.append(verifPadrao.group().concat("\n"));
								System.out.println(verifPadrao.group());
							}
						}
						catch(java.util.regex.PatternSyntaxException e) {
							System.out.println("ERRO: SINTASE DA EXPRESSAO REGULAR FORNECIDA");
							System.out.println(e.getMessage());
							e.printStackTrace();
						}
						catch(java.lang.Exception e) {
							System.out.println("ERRO: GERAL DO SISTEMA : EXPRESSAO REGULAR");
							System.out.println(e.getMessage());
							e.printStackTrace();
						}					
					}
					else {
						AUTAPIGUI.AUTFormularioUtils.exibirMensagem("Assistente: Expressao Regular", "ATENÇÃO: INFORME O CONTEÚDO QUE SERÁ SUBMETIDO A EXPRESSÃO REGULAR");
					}							
				}
				else
				{
					AUTAPIGUI.AUTFormularioUtils.exibirMensagem(AUTAPIGUI.AUTFormularioUtils.AUT_TIPO_MSG_USUARIO.ERRO,"Assistente : Expressões Regulares","Info : Informe a expressão regular para ser avaliada!!!!!");										
				}				
			}
		});

		btLimpar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				System.out.println("INFO: LIMPANDO CAMPOS DE ENTRADA DE DADOS : FORM GER EXPRESSAO REGULAR");

				txtExpressaoRegular.removeAll();
				txtExpressaoRegular.setText("");

				txtEntrada.removeAll();
				txtEntrada.setText("");

				txtSaida.removeAll();
				txtSaida.setText("");
			}
		});



		btFechar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				System.out.println("INFO: FECHANDO APP : FORM GER EXPRESSAO REGULAR");

				formRegExp.setVisible(false);

			}
		});


		pnContainerEntrada.setBackground(java.awt.Color.WHITE);
		formRegExp.setSize(new java.awt.Dimension(600,700));

		java.awt.GridBagConstraints configLayout =new java.awt.GridBagConstraints();

		pnContainerEntrada.setLayout(new java.awt.GridBagLayout());
		configLayout.gridx = 0;
		configLayout.gridy = 0;
		configLayout.weightx = 0;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.BOTH;
		configLayout.insets = AUTAPIGUI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(10);

		pnSelectMode.setBackground(java.awt.Color.WHITE);

		pnContainerEntrada.add(pnSelectMode,configLayout);
		
		
		pnLbDevices.add(lbExpressaoRegular);
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		pnContainerEntrada.add(lbExpressaoRegular,configLayout);

		
		configLayout.gridy = 2;
		pnContainerEntrada.add(scrInputRegEx,configLayout);

		
		
		configLayout.gridy = 3;
		pnContainerEntrada.add(lbEntrada,configLayout);
		configLayout.gridy = 4;
		
		
		pnContainerEntrada.add(scrEntrada,configLayout);
		
		
		configLayout.gridy = 5;
		pnContainerEntrada.add(lbSaida,configLayout);

		
		configLayout.gridy = 6;
		pnContainerEntrada.add(scrSaida,configLayout);
	
		
		configLayout.gridy = 7;
		pnContainerEntrada.add(pnBotoes,configLayout);

		//pnContainerEntrada.setPreferredSize(formRegExp.getSize());

		formRegExp.add(pnContainerEntrada);

		System.out.println(formRegExp.getLayout());
		formRegExp.setVisible(true);				
	}


	/**
	 * 
	 * Construtor padrão da classe
	 * 
	 * 
	 * @param form - Formulário principal sobre o qual será inicializado formulário
	 */
	public AUTFormRegExp(javax.swing.JDialog form) {
		this.formPrincipal = form;
		configGUI();
	}


	/**
	 * 
	 * construtor padrão da classe de inicialização
	 * 
	 */
	public AUTFormRegExp() {
		configGUI();
	}
}
