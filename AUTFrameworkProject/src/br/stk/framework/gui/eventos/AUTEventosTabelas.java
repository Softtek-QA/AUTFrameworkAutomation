package br.stk.framework.gui.eventos;

import javax.swing.JTable;
import javax.swing.event.AncestorEvent;

/**
 * Classe padrão para gerenciamento de componentes de tabela
 * 
 * @author Softtek - QA
 *
 */
public abstract class AUTEventosTabelas{
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
	 * @param <TTabela> - Tipo de Tabela para manipulução eventos
	 */
	public interface IAUTEventosTabela{
		
		/**
		 * Manipula o evento de alteração de linha da tabela especificada
		 * 
		 * @param tabela - Objeto tabela que terá seus eventos monitorados
		 * 
		 */
		public <TTabela extends javax.swing.JTable>void linhaAlterada(TTabela tabela);
	}
	
	/**
	 * 
	 * Adiciona um gerenciador de eventos de tabela
	 * 
	 */
	
	public static <Tabela extends ItemTabela,TabelaGUI extends javax.swing.JTable> void adicionarGerenciadorEventos(Tabela tabelaItem,TabelaGUI tabelaItemGUI) {
		
	}
	
}
