/**
 * 
 */
package br.stk.framework.db.connectors;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.LinkOption;

import javax.print.DocFlavor.READER;

import com.mysql.cj.exceptions.DataReadException;

import br.stk.framework.db.connectors.AUTDBUtils.AUT_SHELL_OPERATIONS;
import br.stk.framework.utils.AUTFMKUtils;

/**
 * 
 *API de programação direcionada ao projeto
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBUtils extends AUTFMKUtils{
	public byte[] AUT_INPUT_BYTES = null; //Entrada de bytes
	public byte[] AUT_OUTPUT_BYTES = null; //Saída de bytes
	AUTBytesManipulation autBytesManage = null;
	
	
	/**
	 * 
	 * Comandos templates para gerenciamento da estrutura de dados do framework
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_DB_STATEMENTS_TEMPLATE{
		STANDAR_UPDATE_WITH_CONDITIONS,
		STANDAR_CONDITION_FOR_ALL_TABLES;
		
		@Override
		public String toString() {
			switch(this) {
			case STANDAR_UPDATE_WITH_CONDITIONS:{
				return "UPDATE %s SET %s=? WHERE %s";
			}
			case STANDAR_CONDITION_FOR_ALL_TABLES:{
				return " PJT_ID=%s";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	/**
	 * Define os tipos de atributos que serão utilizados em funções de pesquisa 
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_TYPE_COMPARE_PROPERTIES{
		ENUMERATION_VALUE,
		ENUMERATION_NAME,
		ENUMERATION_CUSTOM
	}
	
	
	/**
	 * 
	 * Define os comandos para execução em interpretadores de comando interativos
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_SHELL_OPERATIONS{
		SISTEMA_OPERACIONAL_WINDOWS,
		SISTEMA_OPERACIONAL_PLATAFORMA_LINUX,
		SISTEMA_OPERACIONAL_PLATAFORMA_UNIX,
		CURRENT_PLATAFORM,
		CURRENT_PLATAFORM_DESCRIPTION,
		SHELL_CMD_LIST_DIRETORIES;
		
		@Override
		public String toString() {
			switch(this) {		
			case SHELL_CMD_LIST_DIRETORIES:{
				switch(AUT_SHELL_OPERATIONS.valueOf(AUT_SHELL_OPERATIONS.CURRENT_PLATAFORM.toString())) {
				case SISTEMA_OPERACIONAL_PLATAFORMA_LINUX:{
					return "ls %s -l";
				}
				case SISTEMA_OPERACIONAL_PLATAFORMA_UNIX:{
					return "ls %s -l";
				}
				case SISTEMA_OPERACIONAL_WINDOWS:{
					return "cmd /c \"powershell\" \"Get-ChildItem -Recurse -Path \'%s\'|select-object -property fullname\"";
				}
				default:{
					return "ls %s -l";
				}
				}
			}
			case CURRENT_PLATAFORM:{
				String OSCurrent = System.getProperty("os.name");
				java.util.regex.Pattern regDef = java.util.regex.Pattern.compile(SISTEMA_OPERACIONAL_PLATAFORMA_LINUX.toString());
				java.util.regex.Matcher regAnalist = regDef.matcher(OSCurrent);
				if(regAnalist.find()) {
					return SISTEMA_OPERACIONAL_PLATAFORMA_LINUX.name();
				}
				else {
					regDef = regDef.compile(SISTEMA_OPERACIONAL_PLATAFORMA_UNIX.toString());
					regAnalist = regDef.matcher(OSCurrent);
					if(regAnalist.find()) {
						return SISTEMA_OPERACIONAL_PLATAFORMA_UNIX.name();
					}
					else {
						regDef = regDef.compile(SISTEMA_OPERACIONAL_WINDOWS.toString());
						regAnalist = regDef.matcher(OSCurrent);
						if(regAnalist.find()) {
							return SISTEMA_OPERACIONAL_WINDOWS.name();
						}
					}
				}
				
				return "UNDEFINE_OS_TYPE";
			}
			case CURRENT_PLATAFORM_DESCRIPTION:{
				return String.format("AUT PLATAFORM: %s : DESCRIPTION : %s",CURRENT_PLATAFORM.toString(),System.getProperty("os.name"));
			}
			case SISTEMA_OPERACIONAL_PLATAFORMA_LINUX:{
				return "(?i:linux)";
			}
			case SISTEMA_OPERACIONAL_PLATAFORMA_UNIX:{
				return "(?i:unix)";
			}
			case SISTEMA_OPERACIONAL_WINDOWS:{
				return "(?i:windows)";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	
	/**
	 * Classe responsável pela manipulação de bytes
	 * 
	 * @author Softtek-QA
	 *
	 */
	public class AUTBytesManipulation{		
		/**
		 * 
		 * Carrega um arquivo da máquina local
		 * 
		 * @param file - Caminho completo do arquivo de dados
		 * 
		 * @return boolean - True caso o processo sejá finalizado com sucesso false caso contrário
		 * 
		 */
		public boolean autLoaderFile(String file) {
			try {
				
				System.out.println("AUT INFO : LOADER FILE: INIT : ".concat(file));
				
				java.io.File fl = java.nio.file.Paths.get(file).toFile();
				Long fileSize = fl.length();
				java.io.FileInputStream fileInputData = new java.io.FileInputStream(fl);
				BufferedInputStream bufInput = new BufferedInputStream(fileInputData);
				AUT_INPUT_BYTES = new byte[fileSize.intValue()];
				int byteInput = bufInput.read();
				int contBytes = 0;
				while(byteInput != -1) {
					AUT_INPUT_BYTES[contBytes]=(byte)byteInput;
					byteInput = bufInput.read();
					contBytes++;
				}
							
				System.out.println("AUT INFO : LOADER FILE: END : ".concat(file));
				
				return true;
			}
			catch(java.lang.Exception e) {
				System.out.println("AUT ERROR: LOADER FILE :".concat(file));
				System.out.println(e.getMessage());
				e.printStackTrace();
				
				return false;
			}
		}
		/**
		 * 
		 * Construtor padrão da classe
		 * 
		 */
		public AUTBytesManipulation() {
			super();
		}
	}
	
	public boolean autFileLocationValidation(String parametro) {
		try {
			
			java.io.File arqInput = java.nio.file.Paths.get(parametro).toFile();
			
			if(arqInput.exists() && arqInput.isFile()) {
				
				System.out.println("AUT INFO : ARQUIVO ENCONTRADO : ".concat(parametro));				
				
				return true;
			}
			else {
				//System.out.println("AUT INFO : DIRETÓRIO DE ARQUIVO NÃO ENCONTRADO : ".concat(parametro));
				return false;
			}
		}
		catch(java.lang.Exception e) {			
			return false;
		}
	}
	
	public java.util.HashMap<String,Object> autGetFileInfo(String file){
		try {
			System.out.println("AUT INFO : LOADING FILE INFO METADATA");
			
			java.util.HashMap<String,Object> fileInfoData = new java.util.HashMap<String,Object>();
			java.io.File fl = java.nio.file.Paths.get(file).toFile();
			fileInfoData.put("EXIST", fl.exists());
			fileInfoData.put("SIZE", fl.length());
			fileInfoData.put("ISDIRECTORY", fl.isDirectory());
			fileInfoData.put("ISFILE",fl.isFile());
			fileInfoData.put("SIMPLENAME",fl.getName());
			fileInfoData.put("ABSOLUTEPATH", fl.getAbsolutePath());
			fileInfoData.put("PATH",fl.getPath());
			fileInfoData.put("PARENT",fl.getParent());
			fileInfoData.put("ISHIDDEN",fl.isHidden());
			fileInfoData.put("CANREAD",fl.canRead());
			fileInfoData.put("CANWRITE",fl.canWrite());
			fileInfoData.put("CANEXECUTE",fl.canExecute());						
			if((boolean)fileInfoData.get("ISDIRECTORY")) {								
				fileInfoData.put("HAVEITENS", (fl.listFiles().length > 0 ? true : false));
				if((boolean)fileInfoData.get("HAVEITENS")) {
					java.util.List<String> ltSubItens = new java.util.ArrayList<String>();
					File[] fileDir = fl.listFiles();
				
					for(int i = 0 ; i < fileDir.length ;i++) {
						ltSubItens.add(fileDir[i].getAbsolutePath());
					}					
					fileInfoData.put("SUBITENS", (fileDir.length > 0 ? ltSubItens : null));
				}				
			}
			else {
				java.util.regex.Pattern padrao = java.util.regex.Pattern.compile("([\\.]\\w+)");
				java.util.regex.Matcher analise = padrao.matcher(file);
				while(analise.find()) {
					fileInfoData.put("EXTENSION", analise.group());
				}				
				fileInfoData.put("ISLINK",java.nio.file.Files.isSymbolicLink(fl.toPath()));								
			}
			
			System.out.println(fileInfoData);
			return fileInfoData;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: GET FILE INFO");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * 
	 * Retorna uma nova instancia da classe para gerenciamento de bytes
	 * 
	 * @return AUTBytesManipulation - Classe responsável pelo gerenciamento de bytes
	 * 
	 */
	public AUTBytesManipulation autGetNewBytesManager() {
		autBytesManage = new AUTBytesManipulation();
		return this.autBytesManage;
	}
	
	/**
	 * Retorna a classe para gerenciamento 
	 * 
	 * @return AUTBytesManipulation - Classe responsável pelo gerenciamento de fluxo de dados baseado em bytes
	 */
	public AUTBytesManipulation autGetCurrentBytesManager() {
		if(autBytesManage!=null) {
			return autBytesManage;
		}
		else {
			return autGetNewBytesManager();
		}
	}
	
	/**
	 * 
	 * Responsável pelo carregamento de arquivos em sistemas operacionais windows
	 * 
	 * @param file - Nome completo do arquivo que será carregado do diretório local
	 * 
	 * @return java.util.HashMap(String,Object) - Hash com os metadados de configuração do arquivo + conteudo
	 * 
	 */
	public java.util.HashMap<String,Object> autGetFileOnWindows(String file){
		java.util.HashMap<String,Object> fileParams = new java.util.HashMap<String,Object>();		
		try {
			
			System.out.println("AUT INFO : LOADING FILE FROM WINDOWS : ".concat(file));
			java.io.File fileInput = java.nio.file.Paths.get(file).toFile();
			if(fileInput.exists()) {				
				fileParams.put("EXIST", fileInput.exists());
				fileParams.put("MESSAGE001", "FILE FOUND ON SYSTEM");
				fileParams.put("ISDIRECTORY",fileInput.isDirectory());
				fileParams.put("ISEXECFILE",fileInput.isFile());
				fileParams.put("ISFILELINK",fileInput.isFile());
				fileParams.put("FILENAMEFULL",file);
				fileParams.put("SIZE",fileInput.length());
				
				boolean isDirectory = (Boolean)fileParams.get("ISDIRECTORY");
				
				if(isDirectory) {
					fileParams.put("CONTENT_DIR", file);
				}
				else {
					/*
					java.io.InputStream inputFile = new FileInputStream(file);
					java.io.BufferedInputStream bufInputFile = new java.io.BufferedInputStream(inputFile);					
					java.util.List<Integer> ltBytesOut = new java.util.ArrayList<Integer>();
					int byteOut = bufInputFile.read();
					ltBytesOut.add(byteOut);
					
					fileParams.put("CONTENT_FILE",new java.util.ArrayList<Byte>());
					
					while(byteOut != -1) {
						//System.out.println(byteOut);
						byteOut = bufInputFile.read();
						ltBytesOut.add(byteOut);
					}
										
					fileParams.put("CONTENT_FILE",ltBytesOut);
					
					//System.out.println("AUT INFO : LOADED FILE : FILE : ".concat(file));
					
					try {
						bufInputFile.close();
						inputFile.close();
					}
					catch(java.lang.Exception e) {
						
					}
					*/
					return fileParams;
				}
				
			}
			else {
				fileParams.put("ERROR", "AUT ERROR");
				fileParams.put("EXIST", false);
				fileParams.put("MESSAGE001", "FILE NOT FOUND");
				fileParams.put("FILE",file);				
				throw new Exception(fileParams.toString());				
			}
			
			return fileParams;
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR: LOADING FILE FROM WINDOWS OPERATION SYSTEM");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}		
	}
	
	/**
	 * 
	 * Gera uma chave exclusiva com valores aleatória que pode ser usada internamente no sistema
	 * 
	 * @return String - Chave aleatória
	 * 
	 */
	public String autGenerateRandomKey() {
		java.util.Random rnd = new java.util.Random();

		String keyPrefix = "autkey";
		Integer index = rnd.nextInt(999999);
		String[] keyLetters = new String[] {"0ab","1bc","2cd","3de","4ef","5fg","6gh","7hi","8ij","9zop","10wop","11yop","12jop"};
		String letters = keyLetters[rnd.nextInt(keyLetters.length-1)] 
				+= keyLetters[rnd.nextInt(keyLetters.length-1)]
				+= keyLetters[rnd.nextInt(keyLetters.length-1)]
				+= keyLetters[rnd.nextInt(keyLetters.length-1)]
				+= keyLetters[rnd.nextInt(keyLetters.length-1)]
				+= keyLetters[rnd.nextInt(keyLetters.length-1)]
				+= keyLetters[rnd.nextInt(keyLetters.length-1)]
				+= keyLetters[rnd.nextInt(keyLetters.length-1)]
				+= keyLetters[rnd.nextInt(keyLetters.length-1)];
		String keyOut = keyPrefix.concat(index.toString()).concat(letters);
		System.out.println("AUT INFO : KEY OUT : ");
		System.out.println(keyOut);
		return keyOut;
	}
	
	
	/**
	 * Carrega a estrutura de colunas de uma tabela
	 * 
	 * @param rsData - java.sql.ResultSet - Estrutura de dados da tabela
	 * @return java.util.HashMap(String,Integer) - lista (coluna,indiceColuna)
	 */
	public java.util.HashMap<String, Integer> autListColumns(java.sql.ResultSet rsData){
		try {
			
			//System.out.println("AUT INFO: LOADER COLUMNS OF TABLE");
			
			java.util.HashMap<String,Integer> columnsOut = new java.util.HashMap<String,Integer>();
			java.sql.ResultSetMetaData rsMetData = rsData.getMetaData();
			for(int c = 1; c <= rsMetData.getColumnCount();c++) {
				String colName = rsMetData.getColumnName(c);
				Integer index = c;
				columnsOut.put(colName, index);
				//System.out.println(colName);
			}
			
			return columnsOut;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST COLUMNS OF TABLE");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * Carrega uma lista com os valores da coluna selecionada
	 * 
	 * @param rsData - Estrutura de dados da tabela
	 * @param nomeColuna - Nome da coluna
	 * @return java.util.List(String) - lista de valores da coluna informada
	 * 
	 */
	public java.util.List<Object> autGetListAllColumnName(java.sql.ResultSet rsData,String nomeColuna){
		try {			
			System.out.println("AUT INFO : LIST ITEMS OF TABLE");
			java.util.List<Object> listOut = new java.util.ArrayList<Object>();
			java.util.HashMap<String,Integer> colunas = autListColumns(rsData);						
			while(rsData.next()) {
				if(colunas.containsKey(nomeColuna)) {
					//System.out.println(rsData.getString(colunas.get(nomeColuna)));
					listOut.add(rsData.getString(colunas.get(nomeColuna)));
				}
				else {
					System.out.println("AUT INFO-ERROR: COLUNA NAO ENCONTRADA : ".concat(nomeColuna));
				}
			}	
			System.out.println(listOut);
			return listOut;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: GET LIST ITEMS");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}		
	}
	
	
	
	/**
	 * Carrega uma lista de valores da primeira coluna na estrutura de dados
	 * 
	 * @param rsData - Estrutura de dados
	 * 
	 * 
	 * @return java.util.List(String) - Lista com os valores encontrados na tabela para essa coluna
	 *
	 */
	public java.util.List<Object> autGetListItems(java.sql.ResultSet rsData){
		try {			
			//System.out.println("AUT INFO : LIST ITEMS OF TABLE");
			java.util.List<Object> listOut = new java.util.ArrayList<Object>();
			java.util.HashMap<String,Integer> colunas = autListColumns(rsData);						
			while(rsData.next()) {
				listOut.add(rsData.getString(1));
			}	
			System.out.println(listOut);
			
			rsData.close();
			
			rsData = null;
			return listOut;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: GET LIST ITEMS");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}		
	}
	
	/**
	 * Construtor padrão da classe
	 * 
	 */
	public AUTDBUtils() {
		// TODO Auto-generated constructor stub
	}

}
