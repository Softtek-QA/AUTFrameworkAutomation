/**
 * 
 */
package br.stk.framework.gui.eventos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.TreeSelectionEvent;

/**
 * 
 * Gerenciamento de eventos relacionados a objetos baseados em �rvore
 * 
 * @author Softtek-QA
 *
 */
public abstract class AUTEventsTreeObject implements javax.swing.event.TreeSelectionListener {

	/**
	 * 
	 * Fun��o para sele��o de item tree object
	 * 
	 * @param e - �rvore de objetos
	 * 
	 */	
	abstract public void autTreeObjectSelected(javax.swing.JTree e);	
	@Override
	public void valueChanged(TreeSelectionEvent e) {	
		javax.swing.JTree treeItem = (javax.swing.JTree)e.getSource();								
		autTreeObjectSelected(treeItem);
	}

}
