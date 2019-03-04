/**
 * 
 */
package br.stk.framework.db.management;

/**
 * 
 * Gerenciamento de permiss�es em fun��o de perfis de usu�rios
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBPemissionsUser extends AUTDBProfiles {

	/**
	 * Opera��es de gerenciamento para permiss�es de usu�rio
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_PERMISSIONS_OPERATIONS{
		INSERT_PERMISSIONS_BY_PROFILE_ID,
		SELECT_PERMISSIONS_BY_ID_PROFILE,
		UPDATE_PERMISSION_NAME_BY_ID,
		UPDATE_PERMISSION_DESCRIPTION_BY_ID;
		
		@Override
		public String toString() {
			
			switch(this) {
			case UPDATE_PERMISSION_DESCRIPTION_BY_ID:{
				return "UPDATE lry.aut_permissions_users SET pms_description=? WHERE pms_id=?";
			}
			case SELECT_PERMISSIONS_BY_ID_PROFILE:{
				return "SELECT PMS_ID FROM lry.aut_permissions_users WHERE pfl_id=?;";
			}
			case UPDATE_PERMISSION_NAME_BY_ID:{
				return "UPDATE lry.aut_permissions_users SET pms_name=? WHERE pms_id=?";
			}
			case INSERT_PERMISSIONS_BY_PROFILE_ID:{				
				return "INSERT INTO lry.aut_permissions_users(pfl_id,pms_name,pms_description) VALUES(?,?,?);";
			}
			default:{
				return this.name();
			}			
			}
			
		}
	}
	
	/**
	 * 
	 * Lista as permiss�es cadastradas para o perfil espec�fico
	 * 
	 * @param idProfile - Id do perfil de acesso
	 * 
	 * @return java.util.List - Lista de permiss�es
	 */
	public java.util.List<String> autListPermissionsId(String idProfile){
		try {
			java.util.List<String> listOut = new java.util.ArrayList<String>();		
			System.out.println("AUT INFO : LIST PERMISSIONS BY PROFILE ID");
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_PERMISSIONS_OPERATIONS.SELECT_PERMISSIONS_BY_ID_PROFILE.toString(),
					new Object[] {idProfile});			
			while(rsData.next()) {
				System.out.println(rsData.getString(1));
			}			
			return listOut;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: ERROR LIST PERMISSIONS BY PROFILE ID");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}	
	}
	
	/**
	 * Altera descri��o da permiss�o de acesso da permiss�o especificada
	 * 
	 * @param idPms - Id da permiss�o de acesso
	 * @param description - Descri��o de acesso
	 * @return boolean - True caso a opera��o seja realizada com sucesso false caso contr�rio
	 * 
	 */
	public boolean autChangePemissionDescription(String idPms,String description) {
		try {
			
			System.out.println("AUT INFO : CHANGE PERMISSION DESCRIPTION");
			autExecSubStatements(AUT_PERMISSIONS_OPERATIONS.UPDATE_PERMISSION_DESCRIPTION_BY_ID.toString(), 
					new Object[] {description,idPms});
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR : CHANGE PERMISSION DESCRIPTION");
			System.out.println(e.getMessage());
			e.printStackTrace();			
			return false;
		}
	}
	/**
	 * 
	 * Altera o nome abreviado da permiss�o de acesso
	 * 
	 * @param idPms - Id da permiss�o de acesso
	 * @param name - Nome da permiss�o de acesso
	 * 
	 * @return boolean - True se a opera��o tiver sido realizada com sucesso false caso contr�rio
	 */
	public boolean autChangePermissionName(String idPms,String name) {
		try {
			System.out.println("AUT INFO : CHANGE PERMISSION NAME");
			autExecSubStatements(AUT_PERMISSIONS_OPERATIONS.UPDATE_PERMISSION_NAME_BY_ID.toString(), 
					new Object[] {name,idPms});
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR: CHANGE PERMISSION NAME");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Insere um permiss�o associada a um perfil espec�fico
	 * 
	 * @param idProfile - Id do perfil
	 * @param name - Nome abreviado da permiss�o
	 * @param description - Descri��o da permiss�o
	 * @return String - True em caso de sucesso false caso contr�rio
	 * 
	 */
	public boolean autInsertPermissionUser(String idProfile,String name,String description) {
		try {
			System.out.println("AUT INFO : INSERT PERMISSION USER");
			autExecSubStatements(AUT_PERMISSIONS_OPERATIONS.INSERT_PERMISSIONS_BY_PROFILE_ID.toString(),
					new Object[] {idProfile,name,description});
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR : INSERT PERMISSION USER");
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
	public AUTDBPemissionsUser() {
		// TODO Auto-generated constructor stub
	}

}
