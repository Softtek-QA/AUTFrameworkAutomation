package br.stk.framework.gui.api.runtime;
/**
 * Classe para gerenciamento e manipulação de dados em tempo de execução
 * 
 * @author Softtek - QA
 *
 */
public class AUTRuntimeConfiguration {
	
	/**
	 * 
	 */
	public static boolean AUT_SINC_IMAGEM_CUSTOM = true;
	
	/**
	 * 
	 * Variável de configuração do modo de validação que será usado nas validações
	 * das expressões regulares criadas pelo assistente, no menu de gerenciamento de expressões regulares.
	 * 
	 */
	public static boolean AUT_REGEX_MODO_AUTOMATICO = false;
	/**
	 * 
	 * HABILITA APENAS EXECUCAO DE COMANDO NO TERMINAL (SEM CAPTURA DE TELA OU VIDEO)
	 * 
	 */
	public static boolean AUT_HABILITAR_TERMINAL_DE_COMANDO = false;
	/**
	 * 
	 * HABILITA A CATURA DE TELA DURANTE A EXECUCAO
	 * 
	 */
	public static boolean AUT_HABILITAR_CAPTURA_SCREEN = false;
	/**
	 * 
	 * HABILITA CAPTURA DE TELA E GRAVAÇÃO DE VIDEO
	 *
	 */
	public static boolean AUT_HABILITAR_CAPTURA_SCREEN_E_VIDEO = false;
	
	
	/**
	 * 
	 * CONEXÃO COM O BANCO DE DADOS PRE CONFIGURADO NO MENU DE CONFIGURACOES
	 * GERAIS DO BANCO DE DADOS
	 * 
	 */
	public static java.sql.Connection AUT_DB_CONEXAO_PRINCIPAL = null;
	
	/**
	 * 
	 * INDICA SE A CONEXAO COM O BANCO DE DADOS UTILIZADO NOS TESTES ESTÁ PRÉ CONFIGURADA
	 * 
	 */
	 public static boolean AUT_DB_STATUS_CONEXAO_ATUAL = false;
	 
	 /**
	  * 
	  * INDICA SE ESTÁ SENDO GRAVADO OS PROCESSOS EXECUTADOS NO DISPOSITIVO ATUALMENTE CONECTADO
	  * 
	  */
	 public static boolean AUT_GRAVACAO_VIDEO_ATIVA = false;
	 
	 
	 /**
	  * 
	  * PROCESSO PARALELO DE GRAVAÇÃO DE VIDEO
	  * 
	  */
	 
	 public static java.lang.Thread AUT_PROCESSO_GRAVACAO_VIDEO = null;
	 
	 /**
	  * 
	  * 
	  */
	 public static boolean AUT_ARVORE_OBJETOS_EXIBICAO_EXPANDIDA = false; 
}

