package br.stk.framework.api;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import br.stk.framework.gui.logs.AUTGerenciadorLogs;;
/**
 * Fornece um conjunto padrão de classes que oferecem serviços diversos para desenvolvimento dos
 * testes e configuração da parametrização base do sistema
 * 
 * @author Softtek - QA
 *
 */
public class AUTAPIGUI {

	/**
	 * 
	 * Funções para auxiliar na execução de processos externos 
	 * 
	 * @author Softtek - QA
	 *
	 */
	public static class AUTProcessoExternoUtils{
		/**
		 * Classe responsável pelo processamento de dados de saída
		 * 
		 * @author Softtek - QA
		 *
		 */
		public static class AUTDadosProcesso{
			private static int contArqData = 0;
			private static String expDefArquivo = "";
			private static String expDefReg = "";
			private static String expDefCampo = "";
			private static String resDefArquivo = "";
			private static String resDefReg = "";
			private static String resDefCampo = "";	
			private static java.util.HashMap<String, String> arquivosProc = null;
			private static java.lang.StringBuffer conteudoProcessamento = null;


			/**
			 * configura a expressão regular responsável pela divisão do arquivo em sessões
			 * 
			 * @param expressaoRegular - Expressão regular para configuração da seção que representa o arquivo de dados
			 * 
			 */
			public static void configExpArquivo(String expressaoRegular) {
				expDefArquivo = expressaoRegular;
			}


			/**
			 * Retorna a expressão regular que configura o layout do arquivo de dados
			 * 
			 * @return String - Expressão regular que está sendo utilizada para divisão de arquivos
			 * 
			 */
			public static String retornarExpArquivo() {
				return expDefArquivo;
			}

			/**
			 * Configura a expressão regular que divide a seção de arquivos em regiões menores, linhas de dados
			 * 
			 * @param expressaoRegistro - Expressão regular para definição de registros de dados
			 * 
			 */
			public static void configExpRegistro(String expressaoRegistro) {
				expDefReg = expressaoRegistro;
			}


			/**
			 * Configura a expressão regular responsável pela divisão do registro em campos de dados mapeados pelo sistema
			 * 
			 * 
			 * @param expressaoCampoDados - Expressão regular de definição do campo de dados
			 */
			public static void configExpCampoDados(String expressaoCampoDados) {
				expDefCampo = expressaoCampoDados;
			}


			/**
			 * 
			 * Retorna a expressão regular de configuração do registro de dados
			 * 
			 */
			public static String retornarExpRegistroDados() {
				return expDefReg;
			}



			/**
			 * Retorna o resultado do processamento de dados para o campos
			 * @return String - Conteúdo que representa o arquivo de dados
			 * 
			 */
			public static String retornarResultProcArquivo() {

				try {

					java.util.Date data = new java.util.Date();

					arquivosProc = new java.util.HashMap<String,String>();

					System.out.println(String.format("INFO: INICIANDO PROCESSAMENTO DE DADOS : REGEXP : %s : DATA E HORA : %s", retornarExpArquivo(),data.toString()));
					System.out.println("**** INFO : CONTEUDO DO ARQUIVO : INICIO");
					System.out.println(retornarEntradaDeDados());
					System.out.println("**** INFO : CONTEUDO DO ARQUIVO : FIM");
					java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(retornarExpArquivo());

					java.util.regex.Matcher matPad = padrao.matcher(retornarEntradaDeDados());

					while(matPad.find()) {
						System.out.println("###### INFO : CORRESPONDENCIA ENCONTRADA : INICIO");
						System.out.println(matPad.group());
						System.out.println("###### INFO : CORRESPONDENCIA ENCONTRADA : FIM");
					}

					System.out.println(String.format("INFO: FIM PROCESSAMENTO DE DADOS : REGEXP : %s : DATA E HORA : %s", retornarExpArquivo(),data.toString()));


					return resDefArquivo;

				}
				catch(java.lang.Exception e) {

					arquivosProc = null;

					System.out.println("ERRO: DURANTE O PROCESSAMENTO DE DADOS : EXPRESSAO REGULAR");

					System.out.println(e.getMessage());

					e.printStackTrace();
					return null;
				}
			}


			/**
			 * Representa o registro de dados do conteúdo processado
			 * @return String - Registro de dados extraí
			 */
			public static String retornarResultProcRegistro() {
				return resDefReg;
			}

			/**
			 * Configura a fonte de entrada de dados para processamento pelo sistema
			 * - O contéudo passado para essa função será submetido as funções de processamento de dados para
			 * extração das sessões: Arquivo, registro e campo de dados
			 * 
			 * @param conteudo - Conteúdo para processamento de dados
			 */
			public static void configEntradaDeDados(java.lang.StringBuffer conteudo) {
				conteudoProcessamento = conteudo;
			}


			/**
			 * 
			 * Retornar o conteúdo para entrada de dados e processamento
			 * 
			 * @return java.lang.StringBuffer - Conteúdo para processamento de dados
			 * 
			 */
			public static java.lang.StringBuffer retornarEntradaDeDados(){
				return conteudoProcessamento;
			}
		}

		public static<TEntradaDados extends java.io.InputStream>java.lang.StringBuffer coletorDadosStream(TEntradaDados stream){
			byte byteInput;
			try {
				byteInput = (byte) stream.read();
				java.lang.StringBuffer stringOut = new java.lang.StringBuffer();

				while(byteInput != -1) {
					if(Character.isDefined(byteInput)) {
						stringOut.append(Character.toChars(byteInput));
					}

					byteInput = (byte) stream.read();
				}

				System.out.println(stringOut);
				return stringOut;

			} catch (IOException e) {
				System.out.println("ERRO : NAO FOI POSSÍVEL EXECUTAR O COMANDO SOLICITADO : ");
				System.out.println(e.getMessage());
				e.printStackTrace();
				return null;
			}
		}
		/**
		 * Executa um processo externo e retorna o conteúdo direcionado para o console de saída relacionado ao processo
		 * @param linhaComando - Linha de comando de inicialização do processo
		 * 
		 * @return java.lang.StringBuffer  - Conteúdo de saída do processo que foi inicializado pela linha de comando passada como parametro da função
		 */
		/**
		 * @param linhaComando
		 * @return {@link java.lang.StringBuffer - Conteúdo enviado para o console de saída do processo
		 */
		public static java.lang.StringBuffer executarProcesso(String linhaComando) {

			try {				
				java.util.Date data = new java.util.Date();

				System.out.println(String.format("*** INFO: PROCESSO: %s : INICIALIZANDO : DATA E HORA: %s", linhaComando,data.toString()));

				java.lang.Process processo =  java.lang.Runtime.getRuntime().exec(linhaComando);

				byte byteInput = (byte) processo.getInputStream().read();
				java.lang.StringBuffer stringOut = new java.lang.StringBuffer();

				while(byteInput != -1) {
					if(Character.isDefined(byteInput)) {
						stringOut.append(Character.toChars(byteInput));
					}					
					byteInput = (byte) processo.getInputStream().read();
				}

				System.out.println(String.format("*** INFO: PROCESSO: %s : FIM DA EXECUCAO : DATA E HORA: %s", linhaComando,data.toString()));

				return stringOut;
			} catch (IOException e1) {
				System.out.println("ERRO : NAO FOI POSSÍVEL EXECUTAR O COMANDO SOLICITADO : ");
				System.out.println(e1.getMessage());
				e1.printStackTrace();
				return null;
			}
		} 

		public static java.lang.Process executarProcessoExterno(String linhaComando) {

			try {				
				java.util.Date data = new java.util.Date();

				System.out.println(String.format("*** INFO: PROCESSO: %s : INICIALIZANDO : DATA E HORA: %s", linhaComando,data.toString()));

				java.lang.Process processo =  java.lang.Runtime.getRuntime().exec(linhaComando);

				byte byteInput = (byte) processo.getInputStream().read();
				java.lang.StringBuffer stringOut = new java.lang.StringBuffer();

				while(byteInput != -1) {
					if(Character.isDefined(byteInput)) {
						stringOut.append(Character.toChars(byteInput));
					}					
					byteInput = (byte) processo.getInputStream().read();
				}

				System.out.println(String.format("*** INFO: PROCESSO: %s : FIM DA EXECUCAO : DATA E HORA: %s", linhaComando,data.toString()));

				return processo;
			} catch (IOException e1) {
				System.out.println("ERRO : NAO FOI POSSÍVEL EXECUTAR O COMANDO SOLICITADO : ");
				System.out.println(e1.getMessage());
				e1.printStackTrace();
				return null;
			}
		} 


	}


	/**
	 * 
	 * Serviços gerais de configuração do formulário de dados, criado para agilizar o processo de desenvolvimento do software
	 * e formentar o reaproveitamento de código
	 * 
	 * @author Softtek - QA
	 *
	 */
	public static abstract class AUTFormularioUtils{
		public static enum AUT_TIPO_MSG_USUARIO{
			INFORMATIVA,
			ATENCAO_ALERTA,
			ERRO,
			CUSTOM1
		}
		/**
		 * Configura o espaço entre as margens limite o do elemento e o seu próprio conteúdo
		 * 
		 * @param margem - Margem que será configurada para os lados: direito, esquerdo, superior e inferior
		 * @return java.awt.Insets - Objeto de configuração da propriedade
		 */
		public static java.awt.Insets configurarEspacoInternoElementoGUI(int margem){
			return new java.awt.Insets(margem, margem,margem, margem);
		}


		/**
		 * Carregamento de dados diretamente pelo console de saída do processo
		 * @param comandoInicializacao - Comando de inicialização do processo
		 * 
		 * @return javax.swing.DefaultListModel - Modelo de dados para objetos do tipo : javax.swing.JList
		 */
		public static javax.swing.DefaultListModel carregarFonteDados(String comandoInicializacao){
			javax.swing.DefaultListModel lista = null;


			String resultProc = AUTAPI.AUTProcessoExternoUtils.executarProcesso(comandoInicializacao).toString();

			String[] lineResult = resultProc.split("\n");

			javax.swing.DefaultListModel<String> itensLista = new DefaultListModel<String>();

			int contIndex = 0;
			for(String linha:lineResult) {

				if(contIndex>0) {
					itensLista.addElement(linha.replace("device", "").replace(" ", ""));
				}						
				contIndex++;
			}				

			return itensLista;
		}


		public static javax.swing.DefaultListModel carregarFonteDados(int condicao,String palavraChaveSubstituicao,String comandoInicializacao){
			javax.swing.DefaultListModel lista = null;


			String resultProc = AUTAPI.AUTProcessoExternoUtils.executarProcesso(comandoInicializacao).toString();

			String[] lineResult = resultProc.split("\n");

			javax.swing.DefaultListModel<String> itensLista = new DefaultListModel<String>();

			int contIndex = 0;
			for(String linha:lineResult) {

				if(condicao==1) {
					if(contIndex>0) {
						itensLista.addElement(linha.replace(palavraChaveSubstituicao, "").replace(" ", ""));
					}				
				}
				else if(condicao==2) {
					if(!linha.trim().isEmpty()) {
						itensLista.addElement(linha.replace(palavraChaveSubstituicao, "").replace(" ", ""));
					}
				}
				contIndex++;
			}				

			return itensLista;
		}



		/**
		 * Exibi uma mensagem para o usuário
		 * @param titulo - Título da mensagem que será exibida
		 * 
		 * @param mensagem - Mensagem para o usuário
		 * 
		 */
		public static void exibirMensagem(String titulo,String mensagem) {
			javax.swing.JOptionPane.showConfirmDialog(null,mensagem,titulo,JOptionPane.YES_OPTION,JOptionPane.INFORMATION_MESSAGE);
		}

		public static void exibirMensagem(AUTAPI.AUTFormularioUtils.AUT_TIPO_MSG_USUARIO tipoMensagem,String titulo,String mensagem) {
			if(tipoMensagem.equals(AUT_TIPO_MSG_USUARIO.INFORMATIVA)) {
				javax.swing.JOptionPane.showConfirmDialog(null,mensagem,titulo,JOptionPane.YES_OPTION,JOptionPane.INFORMATION_MESSAGE);
			}
			else if(tipoMensagem.equals(AUT_TIPO_MSG_USUARIO.ATENCAO_ALERTA)) {
				javax.swing.JOptionPane.showConfirmDialog(null,mensagem,titulo,JOptionPane.YES_OPTION,JOptionPane.WARNING_MESSAGE);
			}
			else if(tipoMensagem.equals(AUT_TIPO_MSG_USUARIO.ERRO)) {
				javax.swing.JOptionPane.showConfirmDialog(null,mensagem,titulo,JOptionPane.YES_OPTION,JOptionPane.ERROR_MESSAGE);
			}
			else if(tipoMensagem.equals(AUT_TIPO_MSG_USUARIO.CUSTOM1)) {
				javax.swing.JOptionPane.showConfirmDialog(null,mensagem,titulo,JOptionPane.YES_OPTION,JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}



	
	/**
	 * Classe para carregamento de dados relacionados a configuração da base de dados
	 * 
	 * @author Softtek - QA
	 *
	 */

	/**
	 * Classe customizada para gerenciamento de conexões que implementam o protoco JDBC
	 * 
	 * @author Softtek - QA
	 *
	 */
	public static class AUTJDBCConector{
		private String strConexao,usr = "",pwd = "",nomeClassJDBC = "";
		private java.sql.Connection conTemp = null;

		public static class AUTBancoDeDadosInfo{
			private String nomeProvedor;		
			/**
			 * Nome do provedor de serviço de banco de dados
			 * 
			 * @param provedor - Provedor de serviço
			 */
			public void configurarNomeProvedor(String provedor) {
				this.nomeProvedor = provedor;
			}

			/**
			 * 
			 * Retorna o nome do provedor de serviço de banco de dados
			 * 
			 * @return String - Nome do provedor
			 */
			public String retornarNomeProvedor() {
				return this.nomeProvedor;
			}
			/**
			 * 
			 * Construtor padrão da classe de dados
			 * 
			 */
			public AUTBancoDeDadosInfo() {

			}

			public AUTBancoDeDadosInfo(java.sql.Connection conexao) {
				carregarDadosConfiguracao(conexao);			
			}

			/**
			 * Carrega os atributos de configuração da classe
			 * 
			 * @param conexao - Conexão alvo para extração de dados
			 */
			public void carregarDadosConfiguracao(java.sql.Connection conexao) {
				try {
					configurarNomeProvedor(conexao.getMetaData().getDatabaseProductName());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		public AUTJDBCConector(String nomeClasseDriver,String stringConexao,String usuario,String senha) {
			configurarJDBCConector(nomeClasseDriver, stringConexao, usuario, senha);
		}

		public void configurarSenhaConexao(String senha) {
			this.pwd = senha;
		}

		public String retornarSenhaConexao() {
			return this.pwd;
		}

		public String retornarUsuarioConexao() {
			return this.usr;
		}

		public void configurarUsuarioConexao(String usuario) {
			this.usr = usuario;
		}

		public String retornarStringConexao() {
			return this.strConexao;
		}

		public void configurarStringConexao(String stringConexao) {
			this.strConexao = stringConexao;
		}

		public String retornarNomeClasseDriver() {
			return  this.nomeClassJDBC;
		}

		public void configurarNomeClasseDriver(String classe) {
			nomeClassJDBC = classe;
		}

		public void configurarJDBCConector(String nomeClasseDriver,String stringConexao,String usuario,String senha) {
			AUTGerenciadorLogs.registrarLog("INFO : ALTERANDO : INICIALIZANDO CONECTOR JDBC");
			configurarStringConexao(stringConexao);
			configurarUsuarioConexao(usuario);
			configurarSenhaConexao(senha);		
			configurarNomeClasseDriver(nomeClasseDriver);
			AUTGerenciadorLogs.registrarLog("INFO : ALTERANDO : CONECTOR JDBC INICIALIZADO");
		}

		public int executarSQL(boolean conectar,String cmdSql) {
			try {
				if(java.util.regex.Pattern.matches("?:i(SELECT)", cmdSql)) {
					if(conectar) {

						return retornarConexaoAtiva().prepareStatement(cmdSql).executeUpdate(cmdSql);

					}
					else {
						conectarBancoDeDados();
						return retornarConexaoAtiva().prepareStatement(cmdSql).executeUpdate(cmdSql);
					}			
				}
				else {

					AUTGerenciadorLogs.registrarLog(String.format("INFO : COMANDO SQL NÃO RETORNA DADOS : %s",cmdSql));

					return -1;
				}
			} catch (SQLException e) {

				AUTGerenciadorLogs.registrarLog(e, String.format("ERRO : EXECUÇÃO DO PROCEDIMENTO SQL : %s",cmdSql));

				return -1;
			}
		}

		public java.sql.ResultSet executarSQLCargaDados(boolean conectar,String cmdSql){
			try {
				if(java.util.regex.Pattern.matches("?:i(SELECT)", cmdSql)) {
					if(conectar) {

						return retornarConexaoAtiva().prepareStatement(cmdSql).executeQuery();

					}
					else {
						conectarBancoDeDados();
						return retornarConexaoAtiva().prepareStatement(cmdSql).executeQuery();
					}			
				}
				else {

					AUTGerenciadorLogs.registrarLog(String.format("INFO : COMANDO SQL NÃO RETORNA DADOS : %s",cmdSql));

					return null;
				}
			} catch (SQLException e) {

				AUTGerenciadorLogs.registrarLog(e, String.format("ERRO : EXECUÇÃO DO PROCEDIMENTO SQL : %s",cmdSql));

				return null;
			}
		}

		public java.sql.Connection retornarConexaoAtiva(){
			return this.conTemp;
		}

		public java.sql.Connection conectarBancoDeDados(){
			try {
				AUTGerenciadorLogs.registrarLog("INFO : CONECTANDO A BASE DE DADOS");
				java.lang.Class.forName(nomeClassJDBC).newInstance();				
				conTemp = java.sql.DriverManager.getConnection(strConexao, usr, pwd);
				AUTGerenciadorLogs.registrarLog("INFO : CONEXAO REALIZADA COM SUCESSO");

				return conTemp;
			}
			catch(java.lang.Exception e) {

				AUTGerenciadorLogs.registrarLog(e, String.format("ERRO : NÃO FOI POSSÍVEL CONECTAR A BASE DE DADOS COM STRINGCONEXAO: %s",strConexao));

				return null;
			}
		}

		public java.sql.Connection conectarBancoDeDadosAPI(){
			try {

				AUTGerenciadorLogs.registrarLog("INFO : CONECTANDO A BASE DE DADOS VIA API");
				java.sql.Connection conTemp = java.sql.DriverManager.getConnection(retornarStringConexao(), retornarUsuarioConexao(), retornarSenhaConexao());
				AUTGerenciadorLogs.registrarLog(conTemp.getMetaData().getDatabaseProductName());
				AUTGerenciadorLogs.registrarLog(conTemp.getMetaData().getDatabaseProductName());
				AUTGerenciadorLogs.registrarLog("INFO : CONEXAO REALIZADA COM SUCESSO VIA API");

				return conTemp;
			}
			catch(java.lang.Exception e) {

				AUTGerenciadorLogs.registrarLog(e, String.format("ERRO : NÃO FOI POSSÍVEL CONECTAR A BASE DE DADOS COM STRINGCONEXAO: %s",strConexao));

				return null;
			}
		}
		/**
		 * 
		 * Fecha a conexão passada como parametro com o banco de dados
		 * 
		 * @param conexao - Conexão que será fechada
		 */
		public void fecharConexao(java.sql.Connection conexao) {
			try {
				conexao.close();
			} catch (SQLException e) {
				AUTGerenciadorLogs.registrarLog(e,"ERRO: AO FECHAR A CONEXÃO COM O BANCO DE DADOS");
			}
		}

		/**
		 * 
		 * Fecha a conexão ativa com o banco de dados
		 * 
		 */
		public void fecharConexao() {
			try {
				this.retornarConexaoAtiva().close();
			} catch (SQLException e) {
				AUTGerenciadorLogs.registrarLog(e,"ERRO: AO FECHAR A CONEXÃO COM O BANCO DE DADOS");
			}
		}
	}

	/**
	 * 
	 * Classe responsável pela manipulação de imagens do sistema
	 * 
	 * @author Softtek - QA
	 *
	 */
	public static class AUTPainelImagem extends javax.swing.JComponent{
		java.awt.Image imageEntrada = null;
		int larguraLocal = 300,alturaLocal = 300,posHorizontal = 200,posVertical = 50;

		public AUTPainelImagem() {

		}

		public AUTPainelImagem atualizarGUI(String caminhoImagem) {		
			return new AUTPainelImagem(caminhoImagem);
		}

		public  java.awt.Image extrairComponenteIMG(){
			return imageEntrada;
		}

		/**
		 * Construtor padrão da classe para carga de imagem customizada
		 * @param caminhoImagem - Diretório de origem da imagem
		 * @param posicaoHorizontal - Posição inicial da imagem na tela (eixo X)
		 * @param posicaoVertical - Posição inicial da imagem na tela (eixo Y)
		 * @param largura - Largura em que a imagem será configurada
		 * @param altura - Altura em que a imagem será configurada
		 */
		public AUTPainelImagem(String caminhoImagem,int posicaoHorizontal,int posicaoVertical,int largura,int altura) {
			imageEntrada = carregarImagem(caminhoImagem);
			larguraLocal = largura;
			alturaLocal = altura;	
			posHorizontal = posicaoHorizontal;
			posVertical = posicaoVertical;		
		}

		public AUTPainelImagem(java.awt.Image img,int largura,int altura) {
			imageEntrada = img;
			larguraLocal = largura;
			alturaLocal = altura;	
			posHorizontal = 0;
			posVertical = 0;		
		}

		public AUTPainelImagem(String caminhoImagem) {
			imageEntrada = carregarImagem(caminhoImagem);
			configImagemPadrao(imageEntrada);
			posHorizontal = 0;
			posVertical = 0;			
		}

		public AUTPainelImagem(String caminhoImagem,int percentualReducao) {
			imageEntrada = carregarImagem(caminhoImagem);
			configImagemReduzida(imageEntrada,percentualReducao);
			posHorizontal = 0;
			posVertical = 0;			
		}


		public void configImagemPadrao(java.awt.Image imagem) {
			sun.awt.image.ToolkitImage imgConfig = new sun.awt.image.ToolkitImage(imagem.getSource());

			alturaLocal = imgConfig.getHeight();
			larguraLocal = imgConfig.getWidth();	
		}

		public void configImagemReduzida(java.awt.Image imagem,Integer percentualReducao) {
			try {
				sun.awt.image.ToolkitImage imgConfig = new sun.awt.image.ToolkitImage(imagem.getSource());
				Float escalaRed = (percentualReducao.floatValue() / 100);
				Float alt = imgConfig.getHeight() - (imgConfig.getHeight() * escalaRed);
				Float larg = imgConfig.getWidth() - (imgConfig.getWidth() * escalaRed);

				System.out.println(String.format("### INFO : REDUZINDO TAMANHO DA IMAGEM EM %s",escalaRed));
				System.out.println(String.format("### INFO : LARGURA NORMAL: %s ",larguraLocal));
				System.out.println(String.format("### INFO : ALTURA NORMAL %s ",alturaLocal));	

				alturaLocal = alt.intValue();
				larguraLocal = larg.intValue();	

				System.out.println(String.format("### INFO : LARGURA REDUZIDA: %s ",larguraLocal));
				System.out.println(String.format("### INFO : ALTURA REDUZIDA %s ",alturaLocal));	

			}
			catch(java.lang.Exception e) {
				System.out.println("INFO : ERRO DURANTE REDUCAO DA IMAGEM");
				System.out.println(e.getMessage());
				e.printStackTrace();				
			}			
		}

		/**
		 * Carrega a imagem do sistema de arquivos do sistema operacional (Diretório do sistema)
		 * @param caminhoImagem - Caminho da imagem no sistema de arquivo do sistema
		 * @return java.awt.Image - Objeto imagem do arquivo informado como parametro
		 */
		public java.awt.Image carregarImagem(String caminhoImagem){
			java.awt.Image imagem = getToolkit().getImage(caminhoImagem);							

			return imagem;
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);

			g.drawImage(imageEntrada, posHorizontal, posVertical,larguraLocal,alturaLocal,this);

		}	
	}

	public static javax.swing.JComponent carregarImagem(String caminhoImagem,int posicaoHorizontal,int posicaoVertical,int largura,int altura){
		return new AUTPainelImagem(caminhoImagem,posicaoHorizontal,posicaoVertical,largura,altura);
	}


	public static String gerarCaixaDialog(javax.swing.JDialog formulario){
		javax.swing.JFileChooser fileDialog = new javax.swing.JFileChooser();

		fileDialog.setDialogTitle("Softtek - QA : Assistente de Configuração");
		fileDialog.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);

		fileDialog.showOpenDialog(formulario);

		AUTGerenciadorLogs.registrarLog(fileDialog.getSelectedFile());

		return (fileDialog.getSelectedFile() != null ? fileDialog.getSelectedFile().toString() : "");
	}

	public static String gerarCaixaDialog(javax.swing.JDialog formulario,String[] extencoesArquivo){
		javax.swing.JFileChooser fileDialog = new javax.swing.JFileChooser();
		javax.swing.filechooser.FileNameExtensionFilter filtro = new javax.swing.filechooser.FileNameExtensionFilter("Arquivos Automação", extencoesArquivo);
		fileDialog.setFileFilter(null);
		fileDialog.setFileFilter(filtro);		
		fileDialog.setDialogTitle("Softtek - QA : Assistente de Configuração");
		fileDialog.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);		
		fileDialog.showOpenDialog(formulario);

		AUTGerenciadorLogs.registrarLog(fileDialog.getSelectedFile());

		return (fileDialog.getSelectedFile() != null ? fileDialog.getSelectedFile().toString() : "");
	}

	/**
	 * 
	 * Carrega o conteúdo do  arquivo especificado
	 * @param arquivo - Caminho do arquivo
	 * @return java.util.List<String> - Conteúdo do arquivo
	 * 
	 */
	public static java.util.List<String> carregarArquivo(String arquivo){
		java.util.List<String> conteudo = new java.util.ArrayList<String>();
		java.io.FileReader arq = null;

		try {
			arq = new java.io.FileReader(arquivo);
		} catch (FileNotFoundException e) {
			AUTGerenciadorLogs.registrarLog(e, "ARQUIVO INFORMADO NAO ENCONTRADO");
		}

		java.io.BufferedReader leitorArq = new java.io.BufferedReader(arq);
		try {
			String linha = leitorArq.readLine();

			while(linha != null) {
				conteudo.add(linha);
				AUTGerenciadorLogs.registrarLog(linha);
				linha = leitorArq.readLine();
			}
		} catch (IOException e) {
			AUTGerenciadorLogs.registrarLog(e,"ERRO NA LEITURA DO ARQUIVO");
		}


		return conteudo;
	}
}
