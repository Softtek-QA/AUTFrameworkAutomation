package br.softtek.framework.gui.eventos;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public abstract class AUTEventosJanela implements WindowListener {
	public abstract void janelaExibidaNaTela(java.awt.event.WindowEvent evento);
	public abstract void janelaFechada(java.awt.event.WindowEvent evento);
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent evento) {
		janelaFechada(evento);
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent evento) {
		janelaExibidaNaTela(evento);

	}

}
