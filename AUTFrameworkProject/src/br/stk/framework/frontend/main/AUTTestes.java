package br.stk.framework.frontend.main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.stk.framework.test.files.gui.AUTFormConfiguracaoArquivo;

public class AUTTestes {
	
	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//AUTFormConfiguracaoArquivo formConfig = new AUTFormConfiguracaoArquivo();
		AUTFrameworkMain mainFramework = new AUTFrameworkMain();
		
	}
}
