package br.framework.gui.test.file.gui;
import javax.swing.JFrame;
import javax.swing.JDialog;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.util.Enumeration;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.table.TableColumn;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter.Highlight;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.JTextArea;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;

import br.framework.api.AUTAPI;
import br.framework.gui.logs.*;
public class AUTFormConfiguracaoArquivo{
	private javax.swing.JDialog formularioConfiguracao;
	private javax.swing.JDialog formularioPrincipal = null;	
	private boolean habilitarCaptura = false;
	javax.swing.JTable tabelaItensSalvos;
	javax.swing.JTable tabelaItensCarregados;
	int tamanhoPadraoColunasConfiguracao = 200;
	int totalLinhasInicial = 400;
	int posicaoTabelaCorrenteSessaoEdit = 0;
	int posicaoTabelaCorrenteArquivoEdit = 0;
	int posicaoInicialCampo = 0;
	int posicaoFinalCampo = 0;
	int linhaTabelaSessaoSelecionada = 0;
	int linhaTabelaArquivoSelecionada = 0;
	int colunaTabelaSessaoSelecionada = 0;
	int colunaTabelaArquivoSelecionada = 0;
	javax.swing.text.DefaultHighlighter.DefaultHighlightPainter textoFormatoHighLight = new javax.swing.text.DefaultHighlighter.DefaultHighlightPainter(java.awt.Color.GREEN);
	javax.swing.text.DefaultHighlighter hightLightCorrente = null;
	String colunaCorrenteCapturaSessao = "";
	java.awt.Color corFundoCelulaTabelaEditada = java.awt.Color.LIGHT_GRAY;
	
	/**
	 * Analisa o conteúdo do campo através de expressões regulares bem definidas
	 * 
	 * @param conteudo - Conteúdo analisado
	 * 
	 */
	public void analisarConteudo(Object conteudo) {
		AUTGerenciadorLogs.registrarLog("VALIDANDO EXPRESSAO REGULAR");
		AUTGerenciadorLogs.registrarLog(conteudo);
		for(java.lang.Character caractere : conteudo.toString().toCharArray()) {
			AUTGerenciadorLogs.registrarLog(String.format("Caractere: %s Especial : %s Alfabeto : %s Digito : %s", 
					caractere,!java.lang.Character.isLetterOrDigit(caractere),java.lang.Character.isAlphabetic(caractere),java.lang.Character.isDigit(caractere)));
		}
		
		AUTGerenciadorLogs.registrarLog(conteudo.toString().getClass().getName());
	}

	/**
	 * Remove o registro na linha especificada da tabela
	 * 
	 * @param numeroLinha - Número do linha
	 * 
	 */
	public void removerRegistroSessao(int numeroLinha) {
		if(posicaoTabelaCorrenteSessaoEdit >0) {
			for(int coluna = 0; coluna < tabelaItensSalvos.getColumnCount();coluna++) {			
				tabelaItensSalvos.setValueAt(tabelaItensSalvos.getValueAt(posicaoTabelaCorrenteSessaoEdit-1, coluna), numeroLinha, coluna);
				tabelaItensSalvos.setValueAt("", posicaoTabelaCorrenteSessaoEdit-1, coluna);
			}		
			posicaoTabelaCorrenteSessaoEdit--;
		}

	}

	
	/**
	 * Limpa os registros antigos da tabela de registros da sessão atual
	 * 
	 * @param campoChave - Campo chave de comparação
	 * 
	 */
	public void limparRegistros(int campoChave) {
		/**
		String valorChave = tabelaItensSalvos.getColumnName(campoChave);
		String valorCorrente = "";
		
		for(int r = 0; r < tabelaItensSalvos.getRowCount();r++) {							
			for(int c = 0;c < tabelaItensSalvos.getColumnCount();c++) {
				valorCorrente = tabelaItensSalvos.getColumnName(0);	
				if(valorCorrente.equals(valorChave)) {
					for(int col = 0; col < tabelaItensSalvos.getColumnCount();col++) {
						tabelaItensSalvos.setValueAt("", r, col);
					}
				}
			}
		}
		**/
	}

	
	
	/**
	 * Adiciona itens na tabela da sessão atual
	 * 
	 * @param valor - Valor da coluna chave associada ao registro atual
	 */
	public void adicionarItemTabelaSessao(Object valor) {
		limparRegistros(0);
		tabelaItensSalvos.setValueAt(colunaCorrenteCapturaSessao,posicaoTabelaCorrenteSessaoEdit,0);
		tabelaItensSalvos.setValueAt(valor, posicaoTabelaCorrenteSessaoEdit, 1);
		tabelaItensSalvos.setValueAt(valor.toString().length(), posicaoTabelaCorrenteSessaoEdit, 2);
		tabelaItensSalvos.setValueAt(posicaoInicialCampo, posicaoTabelaCorrenteSessaoEdit, 4);
		tabelaItensSalvos.setValueAt(posicaoFinalCampo, posicaoTabelaCorrenteSessaoEdit, 5);
		
		posicaoTabelaCorrenteSessaoEdit++;
	}
	
	
	/** 
	 * Configura o layout das tabelas:
	 * * da Sessão atual 
	 * *
	 * @return javax.swing.JPanel - Container contendo as tabelas de registro da tela atual
	 */
	public javax.swing.JPanel gerarTabelasConfiguracao(){
		javax.swing.JPanel painel = new javax.swing.JPanel();
		javax.swing.JPanel painelTabelaSessao = gerarTabelaInput();
		javax.swing.JPanel painelTabelaArquivo = gerarTabelaArquivoCarregado();
		java.awt.GridBagConstraints configuracao = new java.awt.GridBagConstraints();
		
		painel.setLayout(new java.awt.GridBagLayout());
		configuracao.gridx = 0;
		configuracao.gridy = 0;
		
		painel.setBackground(java.awt.Color.white);
		painelTabelaSessao.setBackground(java.awt.Color.white);
		painelTabelaArquivo.setBackground(java.awt.Color.white);
		
		painel.add(painelTabelaSessao, configuracao);
		configuracao.gridx = 1;
		//painel.add(painelTabelaArquivo, configuracao);
		
		return painel;
	}
	
	/**
	 * 
	 * Gera um container com uma tabela de registros armazenada em arquivo (Criada em uma sessão anterior)
	 * 
	 * @return javax.swing.JPanel - Container com a tabela embutida
	 * 
	 */
	public javax.swing.JPanel gerarTabelaArquivoCarregado(){	
		
		javax.swing.JLabel titulo = new javax.swing.JLabel("CAMPOS GRAVADOS : ARQUIVO DE DADOS");
		javax.swing.JPanel painel = new javax.swing.JPanel();
		Object[] colunas = new Object[] {"CAMPO","CONTEUDO","TAMANHO","TIPO","POSICAO INICIAL","POSICAO FINAL"};
		Object[][] dados = new Object[totalLinhasInicial][colunas.length];
		tabelaItensCarregados = new javax.swing.JTable(dados,colunas);	
		
		javax.swing.JScrollPane containerTabela = new javax.swing.JScrollPane(tabelaItensCarregados,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		painel.setLayout(new javax.swing.BoxLayout(painel, javax.swing.BoxLayout.Y_AXIS));
		tabelaItensCarregados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		containerTabela.setPreferredSize(new java.awt.Dimension(450,200));
		Enumeration<TableColumn> colunasTab =  tabelaItensCarregados.getColumnModel().getColumns();
		while(colunasTab.hasMoreElements()) {
			colunasTab.nextElement().setMinWidth(tamanhoPadraoColunasConfiguracao);
		}
		titulo.setBackground(java.awt.Color.WHITE);
		painel.setBackground(java.awt.Color.WHITE);
		containerTabela.setBackground(java.awt.Color.WHITE);
		
		painel.add(titulo);
		painel.add(containerTabela);
		
		
		return painel;
	}

	/**
	 * 
	 * Gera tabela de registros da sessão atual
	 * 
	 * @return javax.swing.JPanel - Container com a tabela embutida
	 * 
	 */
	public javax.swing.JPanel gerarTabelaInput(){	
		
		javax.swing.JLabel titulo = new javax.swing.JLabel("CAMPOS GRAVADOS : SESSAO ATUAL");
		javax.swing.JPanel painel = new javax.swing.JPanel();
		Object[] colunas = new Object[] {"CAMPO","CONTEUDO","TAMANHO","TIPO","POSICAO INICIAL","POSICAO FINAL","EXPRESSAO REGULAR"};
		Object[][] dados = new Object[totalLinhasInicial][colunas.length];
		tabelaItensSalvos = new javax.swing.JTable(dados,colunas);	
		javax.swing.JScrollPane containerTabela = new javax.swing.JScrollPane(tabelaItensSalvos,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		java.awt.GridBagConstraints configuracao = new java.awt.GridBagConstraints();
		
		
		painel.setLayout(new java.awt.GridBagLayout());		
		tabelaItensSalvos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		containerTabela.setPreferredSize(new java.awt.Dimension(950,200));
		Enumeration<TableColumn> colunasTab =  tabelaItensSalvos.getColumnModel().getColumns();
		while(colunasTab.hasMoreElements()) {
			colunasTab.nextElement().setMinWidth(tamanhoPadraoColunasConfiguracao);
		}
		configuracao.gridx=0;
		configuracao.gridy=0;
		configuracao.weightx=1;
		
		painel.add(titulo,configuracao);
		configuracao.gridy=1;
		painel.add(containerTabela,configuracao);
		
		
		return painel;
	}
	
	/**
	 * Gera uma caixa de dialogo para capturar nome do campo durante o processo 
	 * de configuração do layout da massa de dados
	 * 
	 * @param form - Formulário sobre o qual a caixa de diálogo será exibida
	 * 
	 * @return String - Nome do campo definido pelo usuário
	 * 
	 */
	private String carregarParametroUsuario(javax.swing.JDialog form) {
		String result = "";
		javax.swing.JDialog formInput = new javax.swing.JDialog(form);
		javax.swing.JLabel rotuloCampo = new javax.swing.JLabel("Campo: ");
		javax.swing.JTextField txtCampo = new javax.swing.JTextField(20);
		javax.swing.JButton botaoOk = new javax.swing.JButton("Ok");		
		javax.swing.JPanel painel = new javax.swing.JPanel();
		formInput.setSize(new java.awt.Dimension(450,100));
		painel.add(rotuloCampo);
		painel.add(txtCampo);
		painel.add(botaoOk);
		painel.setBackground(java.awt.Color.WHITE);
		formInput.getContentPane().setBackground(java.awt.Color.WHITE);

		
		formInput.add(painel);	
		formInput.setLocationRelativeTo(form);
		botaoOk.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				formInput.setVisible(false);
			}
		});
		
		formInput.setModal(true);
		formInput.setVisible(true);
		
		result = txtCampo.getText();
		
		return result;
	}
	
	/**
	 * Retorna o conteúdo da caixa de texto específica
	 * 
	 * @param caixaTexto - Caixa de Texto
	 * @return String - Conteúdo do campo
	 */
	public String retornarConteudoCaixaTexto(javax.swing.JTextArea caixaTexto) {
		String conteudo = "";
		try {
			if(caixaTexto.getSelectedText()!=null) {
				conteudo = caixaTexto.getSelectedText();
				posicaoInicialCampo = caixaTexto.getSelectionStart();
				posicaoFinalCampo = caixaTexto.getSelectionEnd();
			}
			
			return conteudo;
		}
		catch(java.lang.Exception e) {
			return conteudo;
		}		
	}
	
	
	public void selecionarCampoArquivo(int numeroLinha,javax.swing.JTextArea caixaTexto) {
		Integer posicaoInicio = null;
		Integer posicaoFinal = null;
		
		for(Highlight itemHighLight : caixaTexto.getHighlighter().getHighlights()) {
			caixaTexto.getHighlighter().removeHighlight(itemHighLight);
		}
		
		if(!tabelaItensSalvos.getValueAt(numeroLinha, 4).toString().equals("")) {
			posicaoInicio = Integer.parseInt(tabelaItensSalvos.getValueAt(numeroLinha, 4).toString());
		}
		
		if(!tabelaItensSalvos.getValueAt(numeroLinha, 5).toString().equals("")) {
			posicaoFinal = Integer.parseInt(tabelaItensSalvos.getValueAt(numeroLinha, 5).toString());
		}
		
		if(posicaoInicio!=null && posicaoFinal!=null) {
			caixaTexto.setCaretPosition(posicaoInicio);
			try {
				caixaTexto.getHighlighter().addHighlight(posicaoInicio, posicaoFinal, new javax.swing.text.DefaultHighlighter.DefaultHighlightPainter(java.awt.Color.blue));
			} catch (BadLocationException e1) {
				
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * Configurações de inicialização da tela GUI
	 * 
	 */
	private void configurarGUI() {
		java.awt.GridBagConstraints configuracao = new java.awt.GridBagConstraints();
		formularioConfiguracao = new javax.swing.JDialog((formularioPrincipal!=null?formularioPrincipal:null));
		javax.swing.JButton botaoArquivo = new javax.swing.JButton("Arquivo");
		javax.swing.JButton botaoAdicCampo = new javax.swing.JButton("Adicionar Campo");
		javax.swing.JButton botaoRemoverCampo = new javax.swing.JButton("Remover Campo");
		javax.swing.JButton botaoSalvarConteudoTemp = new javax.swing.JButton("Salvar");
		javax.swing.JButton botaoSinalizarCampos = new javax.swing.JButton("Sinalizar Campos");
		formularioConfiguracao.getContentPane().setBackground(java.awt.Color.WHITE);
		formularioConfiguracao.setModal(true);
		formularioConfiguracao.setSize(new java.awt.Dimension(1024,700));
		formularioConfiguracao.setLocationRelativeTo(formularioPrincipal);
		
		javax.swing.JPanel painelConfigArquivo = new javax.swing.JPanel();
		javax.swing.JPanel painelExpressoes = new javax.swing.JPanel();
		javax.swing.JPanel painelConfigEntradas = new javax.swing.JPanel();
		javax.swing.JPanel painelBotoesComando = new javax.swing.JPanel();
		javax.swing.JPanel painelConteudoTemp = new javax.swing.JPanel();
		javax.swing.JLabel rotuloArquivo = new javax.swing.JLabel("Arquivo : ");
		javax.swing.JLabel rotuloRegistro = new javax.swing.JLabel("Registro : ");
		javax.swing.JLabel rotuloCampo = new javax.swing.JLabel("Campo : ");
		javax.swing.JLabel rotuloConteudTemp = new javax.swing.JLabel("Conteudo : ");
		javax.swing.JTextField txtConteudoTemp = new javax.swing.JTextField(30);
		javax.swing.JTextField txtArquivo = new javax.swing.JTextField(50);
		javax.swing.JTextField txtExpRegistro = new javax.swing.JTextField(40);
		javax.swing.JTextField txtExpCampo = new javax.swing.JTextField(30);
		javax.swing.BoxLayout boxLayout = new javax.swing.BoxLayout(formularioConfiguracao.getContentPane(), javax.swing.BoxLayout.Y_AXIS);
		javax.swing.JSeparator sep = new javax.swing.JSeparator(javax.swing.JSeparator.VERTICAL);
		javax.swing.JTextArea conteudoArquivo = new javax.swing.JTextArea(15, 85);
		javax.swing.JScrollPane containerArquivo = new javax.swing.JScrollPane(conteudoArquivo);
		
		formularioConfiguracao.setLayout(boxLayout);
		
		botaoArquivo.setPreferredSize(new java.awt.Dimension(200, 30));
		
		conteudoArquivo.addKeyListener(new java.awt.event.KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {				
				if(e.getKeyCode() >= 37 && e.getKeyCode() <= 40) {
					if(conteudoArquivo.getSelectedText() !=null) {
						AUTGerenciadorLogs.registrarLog(retornarConteudoCaixaTexto(conteudoArquivo));
						txtConteudoTemp.setText(retornarConteudoCaixaTexto(conteudoArquivo));
					}
				}				
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
			
		});
		
		
		conteudoArquivo.addMouseMotionListener(new java.awt.event.MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				
				if(habilitarCaptura) {
					AUTGerenciadorLogs.registrarLog(retornarConteudoCaixaTexto(conteudoArquivo));
					txtConteudoTemp.setText(retornarConteudoCaixaTexto(conteudoArquivo));
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
			}
			
		});
			
		
		botaoAdicCampo.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				habilitarCaptura = true;
				colunaCorrenteCapturaSessao = carregarParametroUsuario(formularioConfiguracao);
				tabelaItensSalvos.setValueAt(colunaCorrenteCapturaSessao, posicaoTabelaCorrenteSessaoEdit, 0);
				AUTGerenciadorLogs.registrarLog(colunaCorrenteCapturaSessao);
			}
		});
		botaoArquivo.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				javax.swing.JFileChooser fileDialog = new javax.swing.JFileChooser();
						
				
				fileDialog.setBackground(java.awt.Color.WHITE);
				fileDialog.showOpenDialog(formularioConfiguracao);
				
				conteudoArquivo.setText("");
				if(fileDialog.getSelectedFile() != null) {
					txtArquivo.setText(fileDialog.getSelectedFile().toString());
					for(String linha : AUTAPI.carregarArquivo(txtArquivo.getText())) {
						conteudoArquivo.append(linha.concat("\n"));
					}
					AUTGerenciadorLogs.registrarLog(fileDialog.getSelectedFile());
				}
			}
			
		});
		
		botaoSalvarConteudoTemp.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String valor = txtConteudoTemp.getText();
				adicionarItemTabelaSessao(valor);
				analisarConteudo(valor);				
			}
		});
		
		
		botaoRemoverCampo.addActionListener(new java.awt.event.ActionListener() {			
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				removerRegistroSessao(linhaTabelaSessaoSelecionada);
			}
		});
		
		painelConfigArquivo.add(rotuloArquivo);		
		painelConfigArquivo.add(txtArquivo);
		painelConfigArquivo.add(botaoArquivo);
		
		painelExpressoes.add(rotuloRegistro);
		painelExpressoes.add(txtExpRegistro);
		painelExpressoes.add(rotuloCampo);
		painelExpressoes.add(txtExpCampo);

		painelBotoesComando.add(botaoSinalizarCampos);
		painelBotoesComando.add(botaoAdicCampo);
		painelBotoesComando.add(botaoRemoverCampo);
		
		

		painelConteudoTemp.add(rotuloConteudTemp);
		painelConteudoTemp.add(txtConteudoTemp);
		painelConteudoTemp.add(botaoSalvarConteudoTemp);
		
		painelConfigArquivo.setBackground(java.awt.Color.white);
		painelExpressoes.setBackground(java.awt.Color.white);
		containerArquivo.setBackground(java.awt.Color.white);
		painelConteudoTemp.setBackground(java.awt.Color.white);
		painelBotoesComando.setBackground(java.awt.Color.white);
		javax.swing.JPanel painelTabelas = gerarTabelasConfiguracao();
		painelTabelas.setBackground(java.awt.Color.white);
		painelConfigEntradas.setBackground(java.awt.Color.white);
		
		painelConfigEntradas.add(painelConfigArquivo);
		painelConfigEntradas.add(painelExpressoes);
		painelConfigEntradas.add(containerArquivo);
		painelConfigEntradas.add(painelConteudoTemp);
		painelConfigEntradas.add(painelBotoesComando);
		painelConfigEntradas.add(painelTabelas);

		botaoSinalizarCampos.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				for(Highlight itemHighLight : conteudoArquivo.getHighlighter().getHighlights()) {
					conteudoArquivo.getHighlighter().removeHighlight(itemHighLight);
				}
				
				for(int row =  0;row < posicaoTabelaCorrenteSessaoEdit;row++) {
					Integer posicaoInicio = null;
					Integer posicaoFinal = null;
					
					if(!tabelaItensSalvos.getValueAt(row, 4).toString().equals("")) {
						posicaoInicio = Integer.parseInt(tabelaItensSalvos.getValueAt(row, 4).toString());
					}
					
					if(!tabelaItensSalvos.getValueAt(row, 5).toString().equals("")) {
						posicaoFinal = Integer.parseInt(tabelaItensSalvos.getValueAt(row, 5).toString());
					}
					
					if(posicaoInicio!=null && posicaoFinal!=null) {
						conteudoArquivo.setCaretPosition(posicaoInicio);
						try {
							conteudoArquivo.getHighlighter().addHighlight(posicaoInicio, posicaoFinal, new javax.swing.text.DefaultHighlighter.DefaultHighlightPainter(java.awt.Color.GREEN));
						} catch (BadLocationException e1) {
							
							e1.printStackTrace();
						}
					}
				}
			}
		});
		
		tabelaItensSalvos.addMouseListener(new java.awt.event.MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				javax.swing.JTable tabela = (javax.swing.JTable)e.getSource();
				colunaTabelaSessaoSelecionada = tabela.getSelectedColumn();
				
				AUTGerenciadorLogs.registrarLog("COLUNA SELECIONADA TABELA: ");
				AUTGerenciadorLogs.registrarLog(colunaTabelaSessaoSelecionada);
				
				AUTGerenciadorLogs.registrarLog("LINHA SELECIONADA NA TABELA: ");
				linhaTabelaSessaoSelecionada = tabela.getSelectedRow();
				
				selecionarCampoArquivo(linhaTabelaSessaoSelecionada, conteudoArquivo);
				
				conteudoArquivo.setSelectionStart(Integer.parseInt(tabelaItensSalvos.getValueAt(linhaTabelaSessaoSelecionada, 4).toString()));
				conteudoArquivo.setSelectionEnd(Integer.parseInt(tabelaItensSalvos.getValueAt(linhaTabelaSessaoSelecionada, 5).toString()));

					
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
		});

		
		tabelaItensSalvos.addKeyListener(new java.awt.event.KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {				
				if(e.getKeyCode() >= 37 && e.getKeyCode() <= 40) {
					if(conteudoArquivo.getSelectedText() !=null) {

						javax.swing.JTable tabela = (javax.swing.JTable)e.getSource();
						colunaTabelaSessaoSelecionada = tabela.getSelectedColumn();
						
						AUTGerenciadorLogs.registrarLog("COLUNA SELECIONADA TABELA: ");
						AUTGerenciadorLogs.registrarLog(colunaTabelaSessaoSelecionada);
						
						AUTGerenciadorLogs.registrarLog("LINHA SELECIONADA NA TABELA: ");
						linhaTabelaSessaoSelecionada = tabela.getSelectedRow();
						
						selecionarCampoArquivo(linhaTabelaSessaoSelecionada, conteudoArquivo);
						
						conteudoArquivo.setSelectionStart(Integer.parseInt(tabelaItensSalvos.getValueAt(linhaTabelaSessaoSelecionada, 4).toString()));
						conteudoArquivo.setSelectionEnd(Integer.parseInt(tabelaItensSalvos.getValueAt(linhaTabelaSessaoSelecionada, 5).toString()));						
						
					}
				}				
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
			
		});

		
		formularioConfiguracao.add(painelConfigEntradas);
		formularioConfiguracao.setVisible(true);
	}
	
	public void executarAplicacao() {
		try {
			AUTGerenciadorLogs.registrarLog("*** INICIALIZANDO APLICATIVO DE CONFIGURACAO DE ARQUIVOS");
			configurarGUI();
			AUTGerenciadorLogs.registrarLog("*** FINALIZANDO APLICATIVO DE CONFIGURACAO DE ARQUIVOS");
		}
		catch(java.lang.Exception e) {
			AUTGerenciadorLogs.registrarLog(e, "ERRO NA CONSTRUÇÃO DA INTERFACE GRAFICA DA APLICAÇÃO");
		}		
	}
	
	public AUTFormConfiguracaoArquivo() {
		super();
		executarAplicacao();
	}
	
	
	public AUTFormConfiguracaoArquivo(javax.swing.JDialog formularioPrincipal) {
		super();
		this.formularioPrincipal = formularioPrincipal;
		executarAplicacao();
		
	}
}
