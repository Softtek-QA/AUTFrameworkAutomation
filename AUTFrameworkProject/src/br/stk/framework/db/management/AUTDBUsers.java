/**
 * 
 */
package br.stk.framework.db.management;

/**
 * Gerencia o cadastro de usu�rios no sistema
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBUsers extends AUTDBPemissionsUser {
	public enum AUT_USERS_OPERATIONS{
		INSERT_USER_BY_PMS_ID,
		UPDATE_USER_NAME_BY_PMS_ID,
		UPDATE_USER_BSI_AREA_OPERATION_BY_PMS_ID,
		UPDATE_USER_STATUS_OPERATION_BY_PMS_ID,
		UPDATE_USER_PWD,
		SELECT_USERS_BY_PMS_ID;
		
		@Override
		public String toString() {
			
			switch(this) {			
			case SELECT_USERS_BY_PMS_ID:{
				return "SELECT USR_ID FROM lry.aut_users WHERE PMS_ID=?";
			}
			case INSERT_USER_BY_PMS_ID:
			{
				return "INSERT INTO lry.aut_users(PMS_ID,USR_NAME,USR_STATUS,USR_BUSINESS_AREA,USR_PWD) VALUES(?,?,?,?,?);";
			}
			case UPDATE_USER_NAME_BY_PMS_ID:{
				return "UPDATE lry.aut_users SET USR_NAME=? WHERE PMS_ID=?";
			}
			case UPDATE_USER_PWD:{
				return "UPDATE lry.aut_users SET USR_PWD=? WHERE PMS_ID=?";
			}
			case UPDATE_USER_STATUS_OPERATION_BY_PMS_ID:{
				return "UPDATE lry.aut_users SET USR_STATUS=? WHERE PMS_ID=?";
			}
			case UPDATE_USER_BSI_AREA_OPERATION_BY_PMS_ID:{
				return "UPDATE lry.aut_users SET usr_business_area=? WHERE PMS_ID=?";
			}			
			default:{
				return this.name();
			}
			}
		}
	}
	
	/**
	 * 
	 * Altera a senha do usu�rio
	 * 
	 * @param idUser - Id do usu�rio
	 * @param password - Nova senha do usu�rio
	 * @return boolean - True caso opera��o seja realizada com sucesso false caso contr�rio
	 * 
	 */
	public boolean autChangeUserPassWord(String idUser,String password) {
		try {
			System.out.println("AUT INFO : CHANGE USER PASSWORD BY ID");
			autExecSubStatements(AUT_USERS_OPERATIONS.UPDATE_USER_PWD.toString(), 
					new Object[] {password,idUser});
			
			return true;
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR : CHANGE PWD FOR USER BY USER ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	/**
	 * 
	 * Lista usu�rios cadastros com o perfil espec�fico
	 * 
	 * @param idPms - Id da permiss�o de acesso
	 * 
	 * @return boolean - True caso a opera��o seja realizada com sucesso false caso contr�rio
	 * 
	 */
	public java.util.List<String> autListUsers(String idPms){
		try {
			System.out.println("AUT INFO : LIST USERS BY PERMISSIONS");
			java.util.List<String> listUsr = new java.util.ArrayList<String>();
			
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_USERS_OPERATIONS.SELECT_USERS_BY_PMS_ID.toString(), 
					new Object[] {idPms});
			
			while(rsData.next()) {
				listUsr.add(rsData.getString(1));
				System.out.println(rsData.getString(1));
			}
			
			return listUsr;
		}
		catch(java.lang.Exception e)
		{
			System.out.println("AUT ERROR: LIST USERS BY PERMISSION");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return null;
		}
	}
	/**
	 * 
	 * Altera a area de neg�cio associada ao usu�rio
	 * 
	 * @param idUser - Id do usu�rio
	 * @param businessArea - �rea de neg�cio
	 * @return boolean - True em caso de sucesso false caso contr�rio
	 * 
	 */
	public boolean autChangeUserBusinessArea(String idUser, String businessArea) {
		try {			
			System.out.println("AUT INFO : CHANGE BUSINESS AREA BY USER ID");
			autExecSubStatements(AUT_USERS_OPERATIONS.UPDATE_USER_BSI_AREA_OPERATION_BY_PMS_ID.toString(),
					new Object[] {businessArea,idUser});
						
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: CHANGE BUSINESS AREA BY USER ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Altera o status operacional do usu�rio
	 * 
	 * @param idUser - Id do usu�rio
	 * @param status - novo status operacional do usu�rio
	 * 
	 * @return booelan - True caso a opera��o seja conclu�da com sucesso false contr�ri
	 * 
	 */
	public boolean autChangeUserStatus(String idUser,String status) {
		try {
			System.out.println("AUT INFO : CHANGE STATUS USER BY ID");
			autExecSubStatements(AUT_USERS_OPERATIONS.UPDATE_USER_STATUS_OPERATION_BY_PMS_ID.toString(), 
					new Object[] {status,idUser});
			
			return true;
		}
		catch(java.lang.Exception e) 
		{
			System.out.println("AUT ERROR: CHANGE USER STATUS");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}	
	}
	/**
	 * 
	 * Altera o nome do usu�rio espec�fico
	 * 
	 * @param idUser - Id do usu�rio
	 * @param name - Nome do usu�rio
	 * @return - True em caso de sucesso false caso contr�rio
	 * 
	 */
	public boolean autChangeUserName(String idUser, String name) {
		try {
			
			System.out.println("AUT INFO : CHANGE USER NAME BY ID");
			
			autExecSubStatements(AUT_USERS_OPERATIONS.UPDATE_USER_NAME_BY_PMS_ID.toString(), 
					new Object[] {name,idUser});
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: CHANGE USER NAME BY USER ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		
	}
	/**
	 * Inclui usu�rio no banco de dados
	 * 
	 * 
	 * 
	 * @param idPermission - Id da permiss�o de acesso inicial para usu�rio
	 * @param name - Nome do usu�rio
	 * @param status - Status operacional do usu�rio
	 * @param businessArea - �rea de neg�cio inicial inicial para usu�rio
	 * @param pwd - Senha do usu�rio
	 * 
	 * @return - True caso a opera��o seja conclu�da com sucesso false caso contr�rio
	 *
	 */
	public boolean autInsertUser(String idPermission,String name,String status,String businessArea,String pwd) {
		try {
			
			System.out.println("AUT INFO : INSERT USER FOR PERMISSION");
			
			autExecSubStatements(AUT_USERS_OPERATIONS.INSERT_USER_BY_PMS_ID.toString(), 
					new Object[] {idPermission,name,status,businessArea,pwd});
			
			return true;
		}
		catch(java.lang.Exception e)
		{
			System.out.println("AUT ERROR: INSERT USER FOR PERMISSION");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 
	 * Construtor padr�o
	 * 
	 */
	public AUTDBUsers() {
		// TODO Auto-generated constructor stub
	}

}
