package br.stk.framework.gui.testes.mobile.gui;

import java.awt.event.WindowEvent;
import java.io.IOException;

import br.stk.framework.api.AUTAPI;
import br.stk.framework.api.AUTAPI.*;
import br.stk.framework.gui.testes.mobile.gui.AUTFormMobileTesteManual.*;

/**
 * 
 * Formulário para exibição do progresso na transferência de arquivos
 * 
 * @author Softtek - QA
 * 
 */
public class AUTFormStatusSincronizacao {
	private javax.swing.JDialog formPrincipal = null;
	private String diretorio = "";
	
	public void configurarDiretorioDespejoArquivos(String dir) {
		diretorio = dir;
		try {
			java.lang.Runtime.getRuntime().exec(String.format("cmd /c mkdir \"%s\"",dir));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void configurarGUI() {
		javax.swing.JDialog formProgressoTransferencia = new javax.swing.JDialog(formPrincipal);
		javax.swing.JList listaLogs = new javax.swing.JList();
		//javax.swing.JScrollPane scrLlogs = new javax.swing.JScrollPane(listaLogs,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JProgressBar barraProgresso = new javax.swing.JProgressBar();
		
		barraProgresso.setForeground(java.awt.Color.green);
		java.awt.GridBagConstraints configLayoutPrincipal = new java.awt.GridBagConstraints();		
		
		formProgressoTransferencia.setLayout(new java.awt.GridLayout());
		
		formProgressoTransferencia.setTitle("Softtek - Progresso da Transferência de Arquivos");
		formProgressoTransferencia.setSize(new java.awt.Dimension(500,100));
		formProgressoTransferencia.getContentPane().setBackground(java.awt.Color.WHITE);

		formProgressoTransferencia.add(barraProgresso);
		barraProgresso.setMaximum(AUTDispositivoConfiguracao.AUTDadosTransferenciaArquivos.keySet().size() * 2);
		barraProgresso.setValue(0);
		
		formProgressoTransferencia.setLocationRelativeTo(formPrincipal);
		
		formProgressoTransferencia.addWindowListener(new java.awt.event.WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				
				javax.swing.SwingWorker swkProgress = new javax.swing.SwingWorker() {
				@Override
				protected Object doInBackground() throws Exception {
					javax.swing.DefaultListModel dados = new javax.swing.DefaultListModel();
					
					listaLogs.setModel(dados);
					//AUTAPI.AUTFormularioUtils.exibirMensagem("Softtek : Assistente de Configuração", "Iremos iniciar a sincronização de imagens e videos\naproximadamente " + (AUTDispositivoConfiguracao.AUTDadosTransferenciaArquivos.size() * 2));
					
					for(String evdAtual:AUTDispositivoConfiguracao.AUTDadosTransferenciaArquivos.keySet()) {
						AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb pull \"%s\" \"%s\"",
								evdAtual,diretorio));
						//Adicionando arquivo status atual
						dados.addElement(evdAtual);
						barraProgresso.setValue(barraProgresso.getValue()+1);
						AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb pull \"%s\" \"%s\"",
								AUTDispositivoConfiguracao.AUTDadosTransferenciaArquivos.get(evdAtual),
								diretorio
								));
						//Adicionando arquivo resultado esperado
						dados.addElement(AUTDispositivoConfiguracao.AUTDadosTransferenciaArquivos.get(evdAtual));
						barraProgresso.setValue(barraProgresso.getValue()+1);
						AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb shell rm \"%s\"",
								evdAtual));
						
						AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb shell rm \"%s\"",
								AUTDispositivoConfiguracao.AUTDadosTransferenciaArquivos.get(evdAtual)
								));
					}
										
					System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb pull \"%s\" \"%s\"",
							AUTDispositivoConfiguracao.ANDROID_ARQUIVO_VIDEO_PADRAO,diretorio)));
					
					System.out.println(AUTAPI.AUTProcessoExternoUtils.executarProcesso(String.format("adb shell rm \"%s\"",
							AUTDispositivoConfiguracao.ANDROID_ARQUIVO_VIDEO_PADRAO
							)));
					
					formProgressoTransferencia.setVisible(false);
					//AUTAPI.AUTFormularioUtils.exibirMensagem("Softtek : Assistente de Configuração", "Sincronização de imagens e videos: " + (AUTDispositivoConfiguracao.AUTDadosTransferenciaArquivos.size() * 2) + " realizada com sucesso!!!");
					
					return null;
				}
				
				@Override
					protected void done() {
						// TODO Auto-generated method stub
						super.done();
					}
				};	
				
				
				swkProgress.execute();
			}
			
		});
		
		formProgressoTransferencia.setVisible(true);
	}
	
	
	/**
	 * Construtor da classe
	 * 
	 * @param form - Formulário sobre o qual será exibido o progresso no processo de transferência de arquivos
	 * 
	 */
	public AUTFormStatusSincronizacao(javax.swing.JDialog form,String diretorio) {
		formPrincipal = form;
		configurarDiretorioDespejoArquivos(diretorio);		
		configurarGUI();
	}
	/**
	 * Construtor padrão da classe
	 */
	public AUTFormStatusSincronizacao() {
		
	}
}
