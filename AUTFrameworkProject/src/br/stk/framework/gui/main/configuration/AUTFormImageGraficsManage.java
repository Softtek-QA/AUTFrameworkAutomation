package br.stk.framework.gui.main.configuration;

import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 * 
 * CLASSE RESPONSÁVEL PELO GERENCIAMENTO DE IMAGENS UTILIDADAS NA ESTRUTURA DO PROJETO
 * 
 * @author Softtek - QA
 *
 */
public class AUTFormImageGraficsManage {
	private javax.swing.JDialog formPrincipal = null;
	/**
	 * 
	 * Classe responsável pela configuração do interface gráfica do sistema
	 * 
	 */
	public void configGUI() {
		javax.swing.JDialog formIMG = new javax.swing.JDialog(formPrincipal);
		javax.swing.JPanel pnControles = new javax.swing.JPanel();
		javax.swing.JPanel pnImgs = new javax.swing.JPanel();
		javax.swing.JScrollPane scrImgs = new javax.swing.JScrollPane(pnImgs,
				javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		java.awt.GridBagConstraints configLayout = new java.awt.GridBagConstraints();
		javax.swing.JButton btCarregarImagens = new javax.swing.JButton("Carregar");
		
		formIMG.setLayout(new java.awt.GridBagLayout());	
		formIMG.getContentPane().setBackground(java.awt.Color.WHITE);
		formIMG.setSize(new java.awt.Dimension(750,500));
		formIMG.setLocationRelativeTo(formPrincipal);
		
		pnImgs.setBackground(java.awt.Color.WHITE);
		pnImgs.setLayout(new java.awt.GridLayout(20000,10,5,5));
		
		pnControles.setLayout(new javax.swing.BoxLayout(pnControles,javax.swing.BoxLayout.X_AXIS));
		pnControles.setBackground(java.awt.Color.WHITE);
		pnControles.add(btCarregarImagens);
		
		class AUTIMGComponte extends javax.swing.JComponent{
			int imgPaint = 0;
			public AUTIMGComponte(int img) {
				imgPaint = img;
			}
			
			@Override
			public void paint(Graphics g) {
				// TODO Auto-generated method stub
				super.paint(g);
				
				g.setFont(new java.awt.Font("Webdings",java.awt.Font.PLAIN,50));
				byte byteInput = (byte)0x21;
				Character chrInput = (char)byteInput;
				g.drawString(chrInput.toString(), 40, 10);				
			}
			
		}
		
		btCarregarImagens.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				javax.swing.SwingWorker swkLoader = new javax.swing.SwingWorker() {
					@Override
					protected Object doInBackground() throws Exception {
						System.out.println("INFO : INICIANDO CARREGAMENTO DE IMAGENS");
						java.lang.StringBuffer strBuf = new java.lang.StringBuffer();
						HexBinaryAdapter hex = new HexBinaryAdapter();
						for(int i = 1; i <= 19998;i++) {
							if(Character.isDefined(i)) {
								byte byteItem = (byte)i;
								Character charItem = (char)(byteItem);
								//System.out.println("INFO: CONVERTENDO BYTE");
								//System.out.println(String.valueOf(charItem));
								pnImgs.add(new AUTIMGComponte(i));
								if(java.awt.Font.decode("Webdings").canDisplay(charItem)) {
									System.out.println(String.format(" CODIGO UNICODE: %s : PD: %s : UNC BLOCK: %s UNC SCRIPT: %s UNC NUM: %s",i,Character.getName(i),Character.UnicodeBlock.of(i),Character.UnicodeScript.of(i),Integer.toHexString(i)));
									
								}
																
								//System.out.println(hex.marshal(String.valueOf(charItem).getBytes()));
								
							}
						}
												
						System.out.println("INFO : FIM DO CARREGAMENTO DE IMAGENS");
						return null;
					}
					
					@Override
					protected void done() {
						// TODO Auto-generated method stub
						super.done();
					}
				};
				
				swkLoader.execute();
			}
		});
		
		configLayout.gridx = 0;
		configLayout.gridy = 0;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		
		formIMG.add(pnControles,configLayout);

		
		configLayout.gridx = 0;
		configLayout.gridy = 1;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
		formIMG.add(scrImgs,configLayout);
			
		formIMG.setVisible(true);
	}
	
	
	public AUTFormImageGraficsManage(javax.swing.JDialog form) {
		formPrincipal = form;
		configGUI();
	}
	
	
	public AUTFormImageGraficsManage() {
		configGUI();
	}
}
