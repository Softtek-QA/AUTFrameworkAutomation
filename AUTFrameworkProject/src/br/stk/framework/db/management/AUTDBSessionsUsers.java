package br.stk.framework.db.management;

/**
 * Gerenciamento de sess�es para usu�rios cadastrados no sistema
 *
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBSessionsUsers extends AUTDBUsers {

	/**
	 * Define os tipos de opera��es poss�vel no gerenciamento de sess�es de acesso
	 * 
	 * @author Softtek-QA
	 * 
	 */
	public enum AUT_SESSIONS_OPERATIONS{
		INSERT_SESSION_BY_ID_USER,
		DELETE_SESSION_BY_ID_USER,
		SELECT_SESSIONS_BY_USER_ID,
		SELECT_SESSIONS;		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			switch(this) {
			case SELECT_SESSIONS:{
				return "SELECT SES_ID FROM lry.aut_session;";
			}
			case SELECT_SESSIONS_BY_USER_ID:{
				return "SELECT SES_ID FROM lry.aut_session WHERE USR_ID=?;";
			}
			case DELETE_SESSION_BY_ID_USER:
			{
				return "DELETE FROM lry.aut_session WHERE usr_id=?;";
			}
			case INSERT_SESSION_BY_ID_USER:{
				return "INSERT INTO lry.aut_session(USR_ID,SES_NAME) VALUES(?,?);";
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	/**
	 * Lista todas as sess�es ativas
	 * 
	 * @return true 
	 */
	public java.util.List<String> autListSessions() {
		try {
			java.util.List<String> listOut = new java.util.ArrayList<String>();			
			System.out.println("AUT INFO : LIST ALL SESSIONS");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_SESSIONS_OPERATIONS.SELECT_SESSIONS.toString(), new Object[] {});			
			while(rsData.next()) {
				listOut.add(rsData.getString(1));
				System.out.println(rsData.getString(1));
			}			
			return listOut;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: LIST ALL SESSIONS REGISTERED");
			System.out.println(e.getMessage());
			e.printStackTrace();
	
			return null;
		}
	}
	
	/**
	 * Lista todas as sess�es ativas para o usu�rio especificado
	 * 
	 * @param idUser - Id do usu�rio
	 * @return boolean - True caso o processo seja finalizado com sucesso
	 * 
	 */
	public java.util.List<String> autListSessionsByUserId(String idUser) {
		try {
			java.util.List<String> listOut = new java.util.ArrayList<String>();			
			System.out.println("AUT INFO : LIST ALL SESSIONS BY USER ID");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_SESSIONS_OPERATIONS.SELECT_SESSIONS_BY_USER_ID.toString(), 
					new Object[] {idUser});			
			while(rsData.next()) {
				listOut.add(rsData.getString(1));
				System.out.println(rsData.getString(1));
			}			
			return listOut;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR : LIST ALL SESSIONS BY USER ID");
			System.out.println(e.getMessage());
			e.printStackTrace();			
			return null;
		}
	}
	/**
	 * Remove a sess�es ativas do usu�rio espec�ficado
	 * 
	 * @param idUser - Id do usu�rio que ter� suas sess�es removidas
	 * @return boolean - True caso o processo seja finalizado com sucesso
	 * 
	 */
	public boolean autDeleteSession(String idUser) {
		try {
			
			System.out.println("AUT INFO : DELETE SESSION FOR USER");
			autExecSubStatements(AUT_SESSIONS_OPERATIONS.DELETE_SESSION_BY_ID_USER.toString(), 
					new Object[] {idUser});			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR : DELETE SESSION FOR USER");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Cria uma nova sess�o para o usu�rio
	 * 
	 * @param idUser - Id do usu�rio
	 * @param name - Nome da sess�o
	 * 
	 * @return boolean - True caso a opera��o seja finalizada com sucesso
	 * 
	 */
	public boolean autInsertSession(String idUser,String name) {
		try {			
			System.out.println("AUT INFO : INSERT SESSION FOR USER");
			autExecSubStatements(AUT_SESSIONS_OPERATIONS.INSERT_SESSION_BY_ID_USER.toString(), 
					new Object[] {idUser,name});			
			return true;
		}
		catch(java.lang.Exception e) {			
			System.out.println("AUT ERROR: INSERT SESSION");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}		
	}
	
	/**
	 * 
	 * Construtor padr�o da class
	 * 
	 */
	public AUTDBSessionsUsers() {
		// TODO Auto-generated constructor stub
	}

}
