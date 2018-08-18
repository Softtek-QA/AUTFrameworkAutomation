/**
 * 
 */
package br.stk.framework.gui.eventos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.TreeSelectionEvent;

/**
 * 
 * Gerenciamento de eventos relacionados a objetos baseados em árvore
 * 
 * @author Softtek-QA
 *
 */
public abstract class AUTEventsTreeObject implements javax.swing.event.TreeSelectionListener {

	/**
	 * 
	 * Função para seleção de item tree object
	 * 
	 * @param e - Árvore de objetos
	 * 
	 */	
	abstract public void autTreeObjectSelected(javax.swing.JTree e);	
	@Override
	public void valueChanged(TreeSelectionEvent e) {	
		javax.swing.JTree treeItem = (javax.swing.JTree)e.getSource();								
		autTreeObjectSelected(treeItem);
	}

}
