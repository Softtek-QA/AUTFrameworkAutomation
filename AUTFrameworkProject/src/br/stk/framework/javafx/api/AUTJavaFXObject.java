/**
 * 
 */
package br.stk.framework.javafx.api;

import java.net.UnknownHostException;

import org.junit.Test;

import com.sun.tools.visualvm.application.Application;
import com.sun.tools.visualvm.application.ApplicationDescriptor;
import com.sun.tools.visualvm.host.Host;
import com.sun.tools.visualvm.host.LocalHostDescriptor;
import com.sun.tools.visualvm.jmx.impl.JmxApplication;

/**
 * 
 * Classe base para elementos de tela baseados em JavaFX
 * 
 * @author Softtek-QA
 *
 */
public class AUTJavaFXObject {

	public static class AUTHostApplication extends Host{
		
		
		public AUTHostApplication(String hostName) throws UnknownHostException {
			super(hostName);
			
			// TODO Auto-generated constructor stub
		}

		public  void autInitApplication() {
		
		}
		
		
	}
	
	
	public void autListApplication() {
		
		
		
	}

	public static void main(String[] args) {
		AUTHostApplication app;
		try {
			app = new AUTHostApplication("127.0.0.1");
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	public AUTJavaFXObject() {
		
	}
}
