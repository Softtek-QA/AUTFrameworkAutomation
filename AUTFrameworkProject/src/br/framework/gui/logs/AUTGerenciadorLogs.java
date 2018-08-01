/**
 * 
 */
package br.framework.gui.logs;

/**
 * Gerenciamento de logs do sistema
 * 
 * @author Softtek - QA
 *
 */
public class AUTGerenciadorLogs {
	private static boolean habilitarListaRegistros = false;
	private static java.util.List<Object> listaMensagemTemp = new java.util.ArrayList<Object>();
	
	public AUTGerenciadorLogs() {
		
	}

	public static void habilitarCapturaListaLogs() {
		habilitarListaRegistros = true;
		limparListaLogs();
	}
	
	public static void desabilitarCapturaListaLogs() {
		habilitarListaRegistros = false;		
	}
	
	public static void limparListaLogs() {
		listaMensagemTemp.clear();
	}
	
	public static java.util.List<Object> carregarMensagensLog(){
		return listaMensagemTemp;
	}
	/**
	 * Registra a mensagem de log no console de saída padrão do sistema
	 * 
	 * @param mensagemLog - Mensagem de log
	 * 
	 */
	public static void registrarLog(Object mensagemLog) {
		System.out.println(mensagemLog.toString());
		if(habilitarListaRegistros) {
			listaMensagemTemp.add(mensagemLog);
		}
	}
	
	
	/**
	 * 
	 * Registro mensagem no log de saída com exceção gerada pelo sistema
	 * 
	 * @param e - Exceção do sistema
	 * @param mensagemLog - Mensagem detalhando erro gerado pelo sistema
	 * 
	 */
	public static void registrarLog(java.lang.Exception e,Object mensagemLog) {
		System.out.println(String.format("MENSAGEM ERRO : %s DETALHE : %s", e.getMessage(),mensagemLog));
		
		if(habilitarListaRegistros) {
			listaMensagemTemp.add(mensagemLog);
			listaMensagemTemp.add(String.format("MENSAGEM DEBUG: %s",e.getMessage()));
			for(java.lang.StackTraceElement item : e.getStackTrace()) {
				listaMensagemTemp.add(String.format("CLASSE: %s METODO: %s LINHA : %s ARQUIVO: %s",
						item.getClassName(),item.getMethodName(),item.getLineNumber(),item.getFileName()));
			}
		}
		
		e.printStackTrace();
	}
}
