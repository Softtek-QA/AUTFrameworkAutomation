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
 * Fornece um conjunto padr�o de classes que oferecem servi�os diversos para desenvolvimento dos
 * testes e configura��o da parametriza��o base do sistema
 * 
 * @author Softtek - QA
 *
 */
public class AUTAPIGUI {

	/**
	 * 
	 * Fun��es para auxiliar na execu��o de processos externos 
	 * 
	 * @author Softtek - QA
	 *
	 */
	public static class AUTProcessoExternoUtils{
		/**
		 * Classe respons�vel pelo processamento de dados de sa�da
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
			 * configura a express�o regular respons�vel pela divis�o do arquivo em sess�es
			 * 
			 * @param expressaoRegular - Express�o regular para configura��o da se��o que representa o arquivo de dados
			 * 
			 */
			public static void configExpArquivo(String expressaoRegular) {
				expDefArquivo = expressaoRegular;
			}


			/**
			 * Retorna a express�o regular que configura o layout do arquivo de dados
			 * 
			 * @return String - Express�o regular que est� sendo utilizada para divis�o de arquivos
			 * 
			 */
			public static String retornarExpArquivo() {
				return expDefArquivo;
			}

			/**
			 * Configura a express�o regular que divide a se��o de arquivos em regi�es menores, linhas de dados
			 * 
			 * @param expressaoRegistro - Express�o regular para defini��o de registros de dados
			 * 
			 */
			public static void configExpRegistro(String expressaoRegistro) {
				expDefReg = expressaoRegistro;
			}


			/**
			 * Configura a express�o regular respons�vel pela divis�o do registro em campos de dados mapeados pelo sistema
			 * 
			 * 
			 * @param expressaoCampoDados - Express�o regular de defini��o do campo de dados
			 */
			public static void configExpCampoDados(String expressaoCampoDados) {
				expDefCampo = expressaoCampoDados;
			}


			/**
			 * 
			 * Retorna a express�o regular de configura��o do registro de dados
			 * 
			 */
			public static String retornarExpRegistroDados() {
				return expDefReg;
			}



			/**
			 * Retorna o resultado do processamento de dados para o campos
			 * @return String - Conte�do que representa o arquivo de dados
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
			 * Representa o registro de dados do conte�do processado
			 * @return String - Registro de dados extra�
			 */
			public static String retornarResultProcRegistro() {
				return resDefReg;
			}

			/**
			 * Configura a fonte de entrada de dados para processamento pelo sistema
			 * - O cont�udo passado para essa fun��o ser� submetido as fun��es de processamento de dados para
			 * extra��o das sess�es: Arquivo, registro e campo de dados
			 * 
			 * @param conteudo - Conte�do para processamento de dados
			 */
			public static void configEntradaDeDados(java.lang.StringBuffer conteudo) {
				conteudoProcessamento = conteudo;
			}


			/**
			 * 
			 * Retornar o conte�do para entrada de dados e processamento
			 * 
			 * @return java.lang.StringBuffer - Conte�do para processamento de dados
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
				System.out.println("ERRO : NAO FOI POSS�VEL EXECUTAR O COMANDO SOLICITADO : ");
				System.out.println(e.getMessage());
				e.printStackTrace();
				return null;
			}
		}
		/**
		 * Executa um processo externo e retorna o conte�do direcionado para o console de sa�da relacionado ao processo
		 * @param linhaComando - Linha de comando de inicializa��o do processo
		 * 
		 * @return java.lang.StringBuffer  - Conte�do de sa�da do processo que foi inicializado pela linha de comando passada como parametro da fun��o
		 */
		/**
		 * @param linhaComando
		 * @return {@link java.lang.StringBuffer - Conte�do enviado para o console de sa�da do processo
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
				System.out.println("ERRO : NAO FOI POSS�VEL EXECUTAR O COMANDO SOLICITADO : ");
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
				System.out.println("ERRO : NAO FOI POSS�VEL EXECUTAR O COMANDO SOLICITADO : ");
				System.out.println(e1.getMessage());
				e1.printStackTrace();
				return null;
			}
		} 


	}


	/**
	 * 
	 * Servi�os gerais de configura��o do formul�rio de dados, criado para agilizar o processo de desenvolvimento do software
	 * e formentar o reaproveitamento de c�digo
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
		 * Configura o espa�o entre as margens limite o do elemento e o seu pr�prio conte�do
		 * 
		 * @param margem - Margem que ser� configurada para os lados: direito, esquerdo, superior e inferior
		 * @return java.awt.Insets - Objeto de configura��o da propriedade
		 */
		public static java.awt.Insets configurarEspacoInternoElementoGUI(int margem){
			return new java.awt.Insets(margem, margem,margem, margem);
		}


		/**
		 * Carregamento de dados diretamente pelo console de sa�da do processo
		 * @param comandoInicializacao - Comando de inicializa��o do processo
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
		 * Exibi uma mensagem para o usu�rio
		 * @param titulo - T�tulo da mensagem que ser� exibida
		 * 
		 * @param mensagem - Mensagem para o usu�rio
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
	 * Classe para carregamento de dados relacionados a configura��o da base de dados
	 * 
	 * @author Softtek - QA
	 *
	 */

	/**
	 * Classe customizada para gerenciamento de conex�es que implementam o protoco JDBC
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
			 * Nome do provedor de servi�o de banco de dados
			 * 
			 * @param provedor - Provedor de servi�o
			 */
			public void configurarNomeProvedor(String provedor) {
				this.nomeProvedor = provedor;
			}

			/**
			 * 
			 * Retorna o nome do provedor de servi�o de banco de dados
			 * 
			 * @return String - Nome do provedor
			 */
			public String retornarNomeProvedor() {
				return this.nomeProvedor;
			}
			/**
			 * 
			 * Construtor padr�o da classe de dados
			 * 
			 */
			public AUTBancoDeDadosInfo() {

			}

			public AUTBancoDeDadosInfo(java.sql.Connection conexao) {
				carregarDadosConfiguracao(conexao);			
			}

			/**
			 * Carrega os atributos de configura��o da classe
			 * 
			 * @param conexao - Conex�o alvo para extra��o de dados
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

					AUTGerenciadorLogs.registrarLog(String.format("INFO : COMANDO SQL N�O RETORNA DADOS : %s",cmdSql));

					return -1;
				}
			} catch (SQLException e) {

				AUTGerenciadorLogs.registrarLog(e, String.format("ERRO : EXECU��O DO PROCEDIMENTO SQL : %s",cmdSql));

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

					AUTGerenciadorLogs.registrarLog(String.format("INFO : COMANDO SQL N�O RETORNA DADOS : %s",cmdSql));

					return null;
				}
			} catch (SQLException e) {

				AUTGerenciadorLogs.registrarLog(e, String.format("ERRO : EXECU��O DO PROCEDIMENTO SQL : %s",cmdSql));

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

				AUTGerenciadorLogs.registrarLog(e, String.format("ERRO : N�O FOI POSS�VEL CONECTAR A BASE DE DADOS COM STRINGCONEXAO: %s",strConexao));

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

				AUTGerenciadorLogs.registrarLog(e, String.format("ERRO : N�O FOI POSS�VEL CONECTAR A BASE DE DADOS COM STRINGCONEXAO: %s",strConexao));

				return null;
			}
		}
		/**
		 * 
		 * Fecha a conex�o passada como parametro com o banco de dados
		 * 
		 * @param conexao - Conex�o que ser� fechada
		 */
		public void fecharConexao(java.sql.Connection conexao) {
			try {
				conexao.close();
			} catch (SQLException e) {
				AUTGerenciadorLogs.registrarLog(e,"ERRO: AO FECHAR A CONEX�O COM O BANCO DE DADOS");
			}
		}

		/**
		 * 
		 * Fecha a conex�o ativa com o banco de dados
		 * 
		 */
		public void fecharConexao() {
			try {
				this.retornarConexaoAtiva().close();
			} catch (SQLException e) {
				AUTGerenciadorLogs.registrarLog(e,"ERRO: AO FECHAR A CONEX�O COM O BANCO DE DADOS");
			}
		}
	}

	/**
	 * 
	 * Classe respons�vel pela manipula��o de imagens do sistema
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
		 * Construtor padr�o da classe para carga de imagem customizada
		 * @param caminhoImagem - Diret�rio de origem da imagem
		 * @param posicaoHorizontal - Posi��o inicial da imagem na tela (eixo X)
		 * @param posicaoVertical - Posi��o inicial da imagem na tela (eixo Y)
		 * @param largura - Largura em que a imagem ser� configurada
		 * @param altura - Altura em que a imagem ser� configurada
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
		 * Carrega a imagem do sistema de arquivos do sistema operacional (Diret�rio do sistema)
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

		fileDialog.setDialogTitle("Softtek - QA : Assistente de Configura��o");
		fileDialog.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);

		fileDialog.showOpenDialog(formulario);

		AUTGerenciadorLogs.registrarLog(fileDialog.getSelectedFile());

		return (fileDialog.getSelectedFile() != null ? fileDialog.getSelectedFile().toString() : "");
	}

	public static String gerarCaixaDialog(javax.swing.JDialog formulario,String[] extencoesArquivo){
		javax.swing.JFileChooser fileDialog = new javax.swing.JFileChooser();
		javax.swing.filechooser.FileNameExtensionFilter filtro = new javax.swing.filechooser.FileNameExtensionFilter("Arquivos Automa��o", extencoesArquivo);
		fileDialog.setFileFilter(null);
		fileDialog.setFileFilter(filtro);		
		fileDialog.setDialogTitle("Softtek - QA : Assistente de Configura��o");
		fileDialog.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);		
		fileDialog.showOpenDialog(formulario);

		AUTGerenciadorLogs.registrarLog(fileDialog.getSelectedFile());

		return (fileDialog.getSelectedFile() != null ? fileDialog.getSelectedFile().toString() : "");
	}

	/**
	 * 
	 * Carrega o conte�do do  arquivo especificado
	 * @param arquivo - Caminho do arquivo
	 * @return java.util.List<String> - Conte�do do arquivo
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
