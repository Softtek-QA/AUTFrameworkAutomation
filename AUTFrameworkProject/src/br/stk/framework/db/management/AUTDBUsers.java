/**
 * 
 */
package br.stk.framework.db.management;

/**
 * Gerencia o cadastro de usuários no sistema
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
	 * Altera a senha do usuário
	 * 
	 * @param idUser - Id do usuário
	 * @param password - Nova senha do usuário
	 * @return boolean - True caso operação seja realizada com sucesso false caso contrário
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
	 * Lista usuários cadastros com o perfil específico
	 * 
	 * @param idPms - Id da permissão de acesso
	 * 
	 * @return boolean - True caso a operação seja realizada com sucesso false caso contrário
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
	 * Altera a area de negócio associada ao usuário
	 * 
	 * @param idUser - Id do usuário
	 * @param businessArea - Área de negócio
	 * @return boolean - True em caso de sucesso false caso contrário
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
	 * Altera o status operacional do usuário
	 * 
	 * @param idUser - Id do usuário
	 * @param status - novo status operacional do usuário
	 * 
	 * @return booelan - True caso a operação seja concluída com sucesso false contrári
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
	 * Altera o nome do usuário específico
	 * 
	 * @param idUser - Id do usuário
	 * @param name - Nome do usuário
	 * @return - True em caso de sucesso false caso contrário
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
	 * Inclui usuário no banco de dados
	 * 
	 * 
	 * 
	 * @param idPermission - Id da permissão de acesso inicial para usuário
	 * @param name - Nome do usuário
	 * @param status - Status operacional do usuário
	 * @param businessArea - Área de negócio inicial inicial para usuário
	 * @param pwd - Senha do usuário
	 * 
	 * @return - True caso a operação seja concluída com sucesso false caso contrário
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
	 * Construtor padrão
	 * 
	 */
	public AUTDBUsers() {
		// TODO Auto-generated constructor stub
	}

}
