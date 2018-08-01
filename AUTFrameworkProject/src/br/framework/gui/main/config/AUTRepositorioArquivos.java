package br.framework.gui.main.config;

import br.framework.api.AUTAPI;
import br.framework.gui.logs.AUTGerenciadorLogs;

public class AUTRepositorioArquivos {
	/**
	 * 
	 * Configura o repositório de dados local para armazenar arquivos relacionados ao testes e processos de configuração do 
	 * sistema.
	 * 
	 */
	public void configurarRepositorioDados(javax.swing.JDialog formularioPrincipal) {
		javax.swing.JDialog formRepositorio =new javax.swing.JDialog(formularioPrincipal);
		javax.swing.JLabel rotuloDirConfiguracoes =new javax.swing.JLabel("Diretório Configurações :");
		javax.swing.JLabel rotuloDirEvidencias =new javax.swing.JLabel("Diretório Configurações :");
		javax.swing.JTextField txtDirConfiguracao = new javax.swing.JTextField(20);
		javax.swing.JTextField txtDirEvidencias = new javax.swing.JTextField(20);
		javax.swing.JButton botaoDirConfiguracao = new javax.swing.JButton("Diretório...");
		javax.swing.JButton botaoDirEvidencias = new javax.swing.JButton("Diretório...");
		javax.swing.JButton botaoSalvar = new javax.swing.JButton("Salvar Configurações");
		javax.swing.JPanel containerConfiguracao = new javax.swing.JPanel();
		javax.swing.JPanel containerEvidencias = new javax.swing.JPanel();
		javax.swing.JPanel containerBotoesComando = new javax.swing.JPanel();
		javax.swing.JPanel containerPrincipal = new javax.swing.JPanel();
		String diretorioSelecionado = "";
		
		containerPrincipal.setBackground(java.awt.Color.WHITE);
		formRepositorio.setSize(new java.awt.Dimension(500,250));
		formRepositorio.setLocationRelativeTo(formularioPrincipal);
		formRepositorio.setModal(true);	

		containerConfiguracao.setBackground(java.awt.Color.WHITE);
		containerConfiguracao.add(rotuloDirConfiguracoes);
		containerConfiguracao.add(txtDirConfiguracao);
		containerConfiguracao.add(botaoDirConfiguracao);

	
		containerEvidencias.setBackground(java.awt.Color.WHITE);
		containerEvidencias.add(rotuloDirEvidencias);
		containerEvidencias.add(txtDirEvidencias);
		containerEvidencias.add(botaoDirEvidencias);
		
		
		containerBotoesComando.setBackground(java.awt.Color.WHITE);
		containerBotoesComando.add(botaoSalvar);
		
		containerPrincipal.add(containerConfiguracao);
		containerPrincipal.add(containerEvidencias);
		containerPrincipal.add(containerBotoesComando);
		
		botaoDirConfiguracao.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				txtDirConfiguracao.setText(AUTAPI.gerarCaixaDialog(formularioPrincipal));
			}
		});

		
		botaoDirEvidencias.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				txtDirEvidencias.setText(AUTAPI.gerarCaixaDialog(formularioPrincipal));				
			}
		});
		
		
		botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String dir = txtDirEvidencias.getText();
				String dirConfiguracoes = txtDirConfiguracao.getText();
				java.nio.file.Path pasta = java.nio.file.Paths.get(dir);
				java.nio.file.Path pastaConfiguracoes = java.nio.file.Paths.get(dirConfiguracoes);
				
				
				if(java.nio.file.Files.isDirectory(pasta, java.nio.file.LinkOption.NOFOLLOW_LINKS)) {
					AUTGerenciadorLogs.registrarLog("PARA EVIDENCIAS: DIRETORIO CONFIGURADO CORRETAMENTE");
					AUTGerenciadorLogs.registrarLog(dir);
				}
				else {
					javax.swing.JOptionPane.showConfirmDialog(formRepositorio, "PARA EVIDENCIAS : O DESTINO INFORMADO NÃO É UM REPOSITÓRIO(PASTA DE ARQUIVOS)");
				}
				
				if(java.nio.file.Files.isDirectory(pastaConfiguracoes, java.nio.file.LinkOption.NOFOLLOW_LINKS)) {
					AUTGerenciadorLogs.registrarLog("PARA AS CONFIGURACOES: DIRETORIO CONFIGURADO CORRETAMENTE");
					AUTGerenciadorLogs.registrarLog(pastaConfiguracoes);
				}
				else {
					javax.swing.JOptionPane.showConfirmDialog(formRepositorio, "PARA AS CONFIGURACOES : O DESTINO INFORMADO NÃO É UM REPOSITÓRIO(PASTA DE ARQUIVOS)");
				}
						
				formRepositorio.setVisible(false);
			}
		});
		formRepositorio.add(containerPrincipal);
		
		formRepositorio.setVisible(true);
			
	}
	
}
