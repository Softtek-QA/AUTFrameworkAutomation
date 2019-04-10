package br.stk.framework.db.management;

public class AUTDBProfiles extends AUTDBProject {
	
	public enum AUT_PROFILES_OPERATIONS{
		INSERT_PROFILES_V0,
		UPDATE_PROFILE_DESCRIPTION_BY_PJT_ID,
		UPDATE_PFL_DESCRIPTION_BY_PJT_ID_OR_PFL_ID,
		UPDATE_PROFILES_BY_ID,
		UPDATE_PROFILES_BY_ID_PROJ_OR_PROFILE,
		SELECT_LIST_PROFILES_BY_PJT_ID,
		DELETE_PROFILES_V0;		
		@Override
		public String toString() {
			switch(this) {
			case UPDATE_PFL_DESCRIPTION_BY_PJT_ID_OR_PFL_ID:
			{
				return "UPDATE lry.aut_access_profiles SET pfl_description=? WHERE pjt_id=? or pfl_id=?";
			}	
			case UPDATE_PROFILE_DESCRIPTION_BY_PJT_ID:
			{
				return "UPDATE lry.aut_access_profiles SET pfl_description=? WHERE pjt_id=?";
			}	
			case SELECT_LIST_PROFILES_BY_PJT_ID:{
				return "SELECT PFL_ID,PJT_ID FROM lry.aut_access_profiles WHERE PJT_ID=?";
			}
			case DELETE_PROFILES_V0:
			{				
				break;				
			}
			case INSERT_PROFILES_V0:{
				return "INSERT INTO lry.aut_access_profiles(pjt_id,pfl_name,pfl_description) VALUES(?,?,?);";
			}
			case UPDATE_PROFILES_BY_ID:{
				return "UPDATE lry.aut_access_profiles SET pfl_name=? WHERE pjt_id=?;";
			}
			case UPDATE_PROFILES_BY_ID_PROJ_OR_PROFILE:{
				return "UPDATE lry.aut_access_profiles SET pfl_name=? WHERE pjt_id=? or pfl_id=?;";
			}
			}
			return super.toString();
		}
	}
	
	/**
	 * 
	 * Inclui na base de dados um perfil de acesso base
	 * 
	 * @param name - Nome do perfil
	 * @param description - Descrição do perfil de acesso
	 * @param idProject - Id do projeto
	 * 
	 * @return boolean - True em caso de sucesso false caso contrário
	 */
	public boolean autInsertProfiles(String idProject,String name,String description) {
		try {
			System.out.println("AUT INFO : INCLUIR PERFIL DE ACESSO : INICIO");
			
			autExecSubStatements(AUT_PROFILES_OPERATIONS.INSERT_PROFILES_V0.toString(), new Object[]{idProject,name,description});
			
			System.out.println("AUT INFO : INCLUIR PERFIL DE ACESSO");
			
			return true;
		}
		catch(java.lang.Exception e) {
			
			return false;
		}
	}
	
	/**
	 * 
	 * Altera o nome do perfil pelo id de cadastro
	 * 
	 * @param idProfiles - Id do perfil cadastrado no banco de dados
	 * @param name - Novo nome do perfil
	 * 
	 * @return boolean - True se a operação tiver sido realizada com sucesso false caso contrário
	 * 
	 */
	public boolean autChangeNameProfile(String idProfiles,String name) {
		try 
		{
		
			System.out.println("AUT INFO : CHANGE NAME PROFILE BY NAME : INIT");
			
			autExecSubStatements(AUT_PROFILES_OPERATIONS.UPDATE_PROFILES_BY_ID.toString(),
					new Object[] {name,idProfiles});
						
			System.out.println("AUT INFO : CHANGE NAME BY ID : END");
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT INFO ERROR : CHANGE NAME PROFILE");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
		
	} 
	
	/**
	 * Lista os perfis cadastrados na tabela base para um projeto
	 * 
	 * @param idProject - Id do  projeto
	 * @return java.util.List - Lista com a lista de Id dos perfis associados ao projeto
	 * 
	 *  Observação: Para cadastro um perfil de acesso para projeto ou usuário, o cadastro na tabela base é necessário 
	 */
	public java.util.List<String> autListProfiles(String idProject){
		try {
			System.out.println("\n\n**AUT INFO : LIST PROFILES BY PROJECT");
			java.util.List<String> itensOut = new java.util.ArrayList<String>();			
			java.sql.ResultSet rsData = autExecSubStatementsWithResult(AUT_PROFILES_OPERATIONS.SELECT_LIST_PROFILES_BY_PJT_ID.toString(), 
					new Object[] {idProject});			
			while(rsData.next()) {
				String idPfl = rsData.getString(1);
				System.out.println(idPfl);
				itensOut.add(idPfl);
			}			
			return itensOut;
		}
		catch(java.lang.Exception e) {			
			System.out.println("AUT ERROR : LIST PROFILES");
			System.out.println(e.getMessage());
			e.printStackTrace();			
			return null;
		}		
	}

	public boolean autChangeDescriptionProfile(String idProject,String idProfile,String description) {
		try {
			System.out.println("\n\n**AUT INFO : CHANGE PROFILE DESCRIPTION : BY ID PROJECT OR PROFILE");
			autExecSubStatements(AUT_PROFILES_OPERATIONS.UPDATE_PFL_DESCRIPTION_BY_PJT_ID_OR_PFL_ID.toString(), 
					new Object[] {description,idProject,idProfile});			
			
			return true;
		}
		catch(java.lang.Exception e) {
			
			System.out.println("AUT ERROR : CHANGE PROFILE DESCRIPTION : BY PROJECT ID OR PROFILE");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean autChangeDescriptionProfile(String idProject,String description) {
		try {
			System.out.println("\n\n**AUT INFO : CHANGE PROFILE DESCRIPTION : BY PROJECT ID");
			autExecSubStatements(AUT_PROFILES_OPERATIONS.UPDATE_PROFILE_DESCRIPTION_BY_PJT_ID.toString(), 
					new Object[] {description,idProject});			
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT ERROR : CHANGE PROFILE DESCRIPTION : BY PROJECT");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	/**
	 * 
	 * Altera o nome do perfil pelo id de cadastro
	 * 
	 * @param idProfiles - Id do perfil cadastrado no banco de dados
	 * @param name - Novo nome do perfil
	 * @param idProject - Id do projeto
	 * @return boolean - True se a operação tiver sido realizada com sucesso false caso contrário
	 * 
	 */
	public boolean autChangeNameProfile(String idProject,String idProfiles,String name) {
		try 
		{
		
			System.out.println("AUT INFO : CHANGE NAME PROFILE BY NAME : INIT");
			
			autExecSubStatements(AUT_PROFILES_OPERATIONS.UPDATE_PROFILES_BY_ID_PROJ_OR_PROFILE.toString(),
					new Object[] {name,idProject,idProfiles});
						
			System.out.println("AUT INFO : CHANGE NAME BY ID : END");
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("AUT INFO ERROR : CHANGE NAME PROFILE");
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
	public AUTDBProfiles() {
		// TODO Auto-generated constructor stub
	}

}
