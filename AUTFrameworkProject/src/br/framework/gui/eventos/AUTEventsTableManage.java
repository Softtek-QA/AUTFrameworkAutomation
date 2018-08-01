package br.framework.gui.eventos;

import javax.swing.JTable;
import javax.swing.event.AncestorEvent;

/**
 * Classe padrão para gerenciamento de componentes de tabela
 * 
 * @author Softtek - QA
 *
 */
public abstract class AUTEventsTableManage{
	public class ItemTabela implements IAUTEventosTabela{
		@Override
		public <TTabela extends JTable> void linhaAlterada(TTabela tabela) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	/**
	 * Interface para gerenciamento de eventos de tabela
	 * 
	 * @author Softtek - QA
	 *
	 */
	public interface IAUTEventosTabela{
		
		/**
		 * Manipula o evento de alteração de linha da tabela especificada
		 * 
		 * @param <TTabela> - tabela de dados
		 * @param tabela - Tabela de dados
		 * 
		 */
		public <TTabela extends javax.swing.JTable>void linhaAlterada(TTabela tabela);
	}
	
}
