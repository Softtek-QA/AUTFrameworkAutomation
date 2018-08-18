/**
 * 
 */
package br.stk.framework.gui.eventos;

import java.awt.event.ActionEvent;

import br.stk.framework.api.AUTAPI.AUTGUIParallelProcess;

/**
 * Classe para o gerenciamento de eventos gerados por botões
 * 
 * @author Softtek-QA
 *
 */
public abstract class AUTEventsButtonParallelProcess extends AUTGUIParallelProcess{	
	public AUTEventButton AUT_ACTION_LISTENER;
	public abstract void autStartParallelProcess();
	public class AUTEventButton <TProcessParallel extends AUTEventsButtonParallelProcess> implements java.awt.event.ActionListener{
		TProcessParallel processLocal;		
		@Override
		public void actionPerformed(ActionEvent e) {
			processLocal.executarProcesso(true);		
		}
		
		public AUTEventButton(TProcessParallel process){
			processLocal = process;
		}
	}
	
	@Override
	protected void rotinasInicializacao() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void rotinasExecucao() {
		// TODO Auto-generated method stub
		autStartParallelProcess();
		
	}

	@Override
	protected void rotinasFinalizacao() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void rotinasMonitoramentoProcesso() {
		// TODO Auto-generated method stub
		
	}

	public AUTEventButton autGetActionListener() {
		return AUT_ACTION_LISTENER;
	}
	
	public AUTEventsButtonParallelProcess() {
		AUT_ACTION_LISTENER = new AUTEventButton(this);
	}
}
