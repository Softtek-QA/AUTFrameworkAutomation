/**
 * 
 */
package br.stk.framework.db.management;

/**
 * 
 * Gerenciamento de permissões em função de perfis de usuários
 * 
 * @author Softtek-QA
 *
 */
public class AUTDBPemissionsUser extends AUTDBProfiles {

	/**
	 * Operações de gerenciamento para permissões de usuário
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
	 * Lista as permissões cadastradas para o perfil específico
	 * 
	 * @param idProfile - Id do perfil de acesso
	 * 
	 * @return java.util.List - Lista de permissões
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
	 * Altera descrição da permissão de acesso da permissão especificada
	 * 
	 * @param idPms - Id da permissão de acesso
	 * @param description - Descrição de acesso
	 * @return boolean - True caso a operação seja realizada com sucesso false caso contrário
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
	 * Altera o nome abreviado da permissão de acesso
	 * 
	 * @param idPms - Id da permissão de acesso
	 * @param name - Nome da permissão de acesso
	 * 
	 * @return boolean - True se a operação tiver sido realizada com sucesso false caso contrário
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
	 * Insere um permissão associada a um perfil específico
	 * 
	 * @param idProfile - Id do perfil
	 * @param name - Nome abreviado da permissão
	 * @param description - Descrição da permissão
	 * @return String - True em caso de sucesso false caso contrário
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
	 * Construtor padrão
	 * 
	 */
	public AUTDBPemissionsUser() {
		// TODO Auto-generated constructor stub
	}

}
