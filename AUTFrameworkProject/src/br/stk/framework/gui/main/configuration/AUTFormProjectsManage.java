package br.stk.framework.gui.main.configuration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import br.stk.framework.api.AUTAPI;
import br.stk.framework.db.connectors.AUTDBUtils.AUT_TYPE_COMPARE_PROPERTIES;
import br.stk.framework.gui.backend.management.AUTBKEProjectManament;
import br.stk.framework.gui.backend.management.AUTBKEContactManagement.AUT_CONTACT_GUI_PROPERTIES;
import br.stk.framework.gui.backend.management.AUTBKEUtils.AUTGUIObjectBase;
import br.stk.framework.gui.backend.management.AUTBKEUtils.AUTGUITable;
import br.stk.framework.gui.backend.management.AUTBKEUtils.AUTGUITreeObject;
import br.stk.framework.gui.backend.management.AUTBKEUtils.AUTGUITable.AUTGUITableDefinition;
import br.stk.framework.gui.eventos.AUTEventsButtonParallelProcess;
import br.stk.framework.gui.eventos.AUTEventsTreeObject;
import br.stk.framework.gui.eventos.AUTEventsWindowManage;
import br.stk.framework.gui.logs.AUTLogsManage;
import br.stkframework.db.management.AUTDBProject;
import br.stkframework.db.management.AUTDBContacts.AUT_CONTACTS_OPERATIONS;
import br.stkframework.db.management.AUTDBContacts.AUT_CONTACTS_SQL_PROPERTIES;
import br.stkframework.db.management.AUTDBProject.AUT_PROJECT_SQL_PROPERTY;
/**
 * GERENCIAMENTO DE DADOS DO PROJETOS
 * 
 *  
 * @author Softtek - QA
 *
 */
public class AUTFormProjectsManage{	
	public javax.swing.JDialog formPrincipal = null;	
	AUTBKEProjectManament autDBProject = new AUTBKEProjectManament();

	public void configurarInicializacao() {
		javax.swing.JDialog formGerenciamentoProj = new javax.swing.JDialog(formPrincipal);
		java.awt.GridBagConstraints configLayout = new java.awt.GridBagConstraints();		
		javax.swing.JPanel pnInputProject = new javax.swing.JPanel();
		javax.swing.JLabel lbProjKey = new javax.swing.JLabel("PROJ-KEY:");
		javax.swing.JLabel lbNomeProj = new javax.swing.JLabel("NOME PROJETO:");
		javax.swing.JTextField txtNomeProj = new javax.swing.JTextField(35);
		javax.swing.JTextField txtProjKey = new javax.swing.JTextField(40);

		javax.swing.JLabel lbDecricaoProj = new javax.swing.JLabel("DESCRICAO: ");
		javax.swing.JTextArea txtDescricao = new javax.swing.JTextArea(5,30);
		javax.swing.JScrollPane scrDescricao = new javax.swing.JScrollPane(txtDescricao,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JPanel pnContatos = new javax.swing.JPanel();
		javax.swing.JPanel pnContatosInput = new javax.swing.JPanel();
		javax.swing.JPanel pnContatosControles = new javax.swing.JPanel();
		javax.swing.JPanel pnContatosLista = new javax.swing.JPanel();
		javax.swing.JTable tabelaContatos = new javax.swing.JTable(new javax.swing.table.DefaultTableModel(new Object[] {"NOME","TELEFONE","CELULAR","EMAIL","ASSUNTO"},10));
		javax.swing.JScrollPane scrTabelaContatos = new javax.swing.JScrollPane(tabelaContatos,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		javax.swing.JLabel lbTituloContatos = new javax.swing.JLabel("CONTATOS DO PROJETO:");
		javax.swing.JLabel lbNomeContato = new javax.swing.JLabel("NOME: ");
		javax.swing.JLabel lbTelContato = new javax.swing.JLabel("TELEFONE: ");
		javax.swing.JLabel lbCelContato = new javax.swing.JLabel("CELULAR: ");
		javax.swing.JLabel lbEmailContato = new javax.swing.JLabel("EMAIL: ");
		javax.swing.JLabel lbAssuntos  = new javax.swing.JLabel("ASSUNTOS DE SUA RESPONSABILIDADE:");
		javax.swing.JLabel lbTituloProjetos = new javax.swing.JLabel("PORTFÓLIO DE PROJETOS:");	
		javax.swing.JTree treeProjetos = new javax.swing.JTree();
		javax.swing.JScrollPane scrProjetos = new javax.swing.JScrollPane(treeProjetos,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JTextField txtNomeContato = new javax.swing.JTextField(30);
		javax.swing.JTextField txtTelContato  = new javax.swing.JTextField(15);
		javax.swing.JTextField txtCelContato  = new javax.swing.JTextField(15);
		javax.swing.JTextField txtEmailContato  = new javax.swing.JTextField(15);
		javax.swing.JTextArea txtAssuntoContato  = new javax.swing.JTextArea(5,30);
		javax.swing.JScrollPane scrAssuntoContato  = new javax.swing.JScrollPane(txtAssuntoContato,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		javax.swing.JButton btAdicionarContato = new javax.swing.JButton("Adicionar Contato");
		javax.swing.JButton btAtualizarContato = new javax.swing.JButton("Atualizar Contato");
		javax.swing.JButton btExcluirContato = new javax.swing.JButton("Excluir Contato");
		javax.swing.JButton btFecharApp = new javax.swing.JButton("Sair");
		javax.swing.JPanel pnControlesProjeto = new javax.swing.JPanel();
		javax.swing.JButton btAdicionarProj = new javax.swing.JButton("Adicionar");
		javax.swing.JButton btAtualizarProj = new javax.swing.JButton("Atualizar");
		javax.swing.JButton btExcluirProj = new javax.swing.JButton("Excluir");
		javax.swing.JButton btGerenciarAnexosProj = new javax.swing.JButton("Gerenciar Anexos");

		formGerenciamentoProj.setLayout(new java.awt.GridBagLayout());

		configLayout.gridx = 0;
		configLayout.gridy = 0;
		configLayout.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(10);		
		formGerenciamentoProj.add(lbNomeProj,configLayout);	

		configLayout.gridx = 1;
		configLayout.fill = configLayout.HORIZONTAL;
		configLayout.weightx = 1;

		formGerenciamentoProj.add(txtNomeProj,configLayout);	

		configLayout.gridx = 0;
		configLayout.gridy = 1;
		configLayout.weightx = 0;
		formGerenciamentoProj.add(lbProjKey, configLayout);
		configLayout.weightx = 1;
		configLayout.gridx = 1;
		configLayout.gridy = 1;

		txtProjKey.setEditable(false);
		txtProjKey.setEnabled(false);

		formGerenciamentoProj.add(txtProjKey, configLayout);


		configLayout.gridx = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		configLayout.weightx = 1;
		configLayout.gridwidth = 2;
		configLayout.gridy = 2;
		formGerenciamentoProj.add(lbDecricaoProj,configLayout);

		configLayout.gridy = 3;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;
		formGerenciamentoProj.add(scrDescricao,configLayout);

		configLayout.gridx = 0;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.gridy = 4;
		configLayout.fill = configLayout.HORIZONTAL;

		formGerenciamentoProj.add(lbTituloContatos, configLayout);

		java.awt.GridBagConstraints configContInput = new java.awt.GridBagConstraints();		


		//configContInput.fill = configContInput.HORIZONTAL;
		pnContatosInput.setLayout(new java.awt.GridBagLayout());
		pnContatosInput.setBackground(java.awt.Color.WHITE);

		configContInput.gridx = 0;
		configContInput.gridy = 0;
		configContInput.weightx = 0;
		configContInput.fill = configContInput.NONE;
		configContInput.insets = AUTAPI.AUTFormularioUtils.configurarEspacoInternoElementoGUI(5);
		pnContatosInput.add(lbNomeContato,configContInput);
		configContInput.gridx = 1;
		configContInput.weightx = 1;
		configContInput.fill = configContInput.BOTH;
		pnContatosInput.add(txtNomeContato,configContInput);
		configContInput.weightx = 0;
		configContInput.gridx = 2;
		configContInput.fill = configContInput.NONE;
		pnContatosInput.add(lbTelContato,configContInput);
		configContInput.gridx = 3;
		configContInput.weightx = 1;
		configContInput.fill = configContInput.BOTH;
		pnContatosInput.add(txtTelContato,configContInput);
		configContInput.weightx = 0;
		configContInput.gridx = 4;
		configContInput.fill = configContInput.NONE;
		pnContatosInput.add(lbCelContato,configContInput);
		configContInput.gridx = 5;
		configContInput.weightx = 1;
		configContInput.fill = configContInput.BOTH;
		pnContatosInput.add(txtCelContato,configContInput);

		configContInput.weightx = 0;
		configContInput.fill = configContInput.NONE;
		configContInput.weighty = 0;
		configContInput.gridx = 0;
		configContInput.gridy = 1;
		pnContatosInput.add(lbEmailContato,configContInput);

		configContInput.gridx = 1;
		configContInput.weightx = 1;
		configContInput.gridwidth = 4;		
		configContInput.fill = configContInput.HORIZONTAL;

		pnContatosInput.add(txtEmailContato, configContInput);

		configContInput.gridx = 0;
		configContInput.gridy = 2;
		configContInput.gridwidth = 6;
		configContInput.weightx = 1;
		configContInput.fill = configContInput.HORIZONTAL;

		pnContatosInput.add(lbAssuntos,configContInput);

		configContInput.gridx = 0;
		configContInput.gridy = 3;
		configContInput.weightx = 1;
		configContInput.weighty = 1;
		configContInput.fill = configContInput.BOTH;
		configContInput.gridwidth = 6;

		pnContatosInput.add(scrAssuntoContato,configContInput);

		btExcluirContato.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int rowIndex : tabelaContatos.getSelectedRows()) {
					autDBProject.autGUIGetContactManager().autGUIRemoveContact(autDBProject, tabelaContatos.getValueAt(rowIndex,tabelaContatos.getColumnModel().getColumnIndex(AUT_CONTACT_GUI_PROPERTIES.ID.toString())).toString());	
					tabelaContatos.setModel(autDBProject.autGUIGetContactManager().autGUIGetNewTableFromContacts(autDBProject, AUTGUITreeObject.autGetValueByPropertyConfig(AUT_CONTACTS_SQL_PROPERTIES.ID_PROJECT, treeProjetos.getLastSelectedPathComponent()).toString()));
				}				
			}
		});
		
		pnContatosControles.setLayout(new java.awt.GridLayout(1,4));
		pnContatosControles.setBackground(java.awt.Color.WHITE);
		pnContatosControles.add(btAdicionarContato);
		pnContatosControles.add(btAtualizarContato);
		pnContatosControles.add(btExcluirContato);


		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;
		configLayout.gridy = 5;

		formGerenciamentoProj.add(pnContatosInput,configLayout);		


		configLayout.gridx = 0;
		configLayout.gridy = 6;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;

		formGerenciamentoProj.add(scrTabelaContatos,configLayout);

		configLayout.gridx = 0;
		configLayout.gridy = 7;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;

		formGerenciamentoProj.add(pnContatosControles,configLayout);

		configLayout.gridx = 0;
		configLayout.gridy = 7;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;

		formGerenciamentoProj.add(lbTituloProjetos, configLayout);

		configLayout.gridx = 0;
		configLayout.gridy = 9;
		configLayout.weightx = 1;
		configLayout.weighty = 1;
		configLayout.fill = configLayout.BOTH;		
		formGerenciamentoProj.add(scrProjetos, configLayout);


		pnControlesProjeto.setLayout(new java.awt.GridLayout(1,5));
		pnControlesProjeto.setBackground(java.awt.Color.WHITE);
		pnControlesProjeto.add(btAdicionarProj);
		pnControlesProjeto.add(btAtualizarProj);
		pnControlesProjeto.add(btExcluirProj);
		pnControlesProjeto.add(btGerenciarAnexosProj);
		pnControlesProjeto.add(btFecharApp);

		configLayout.gridx = 0;
		configLayout.gridy = 10;
		configLayout.weightx = 1;
		configLayout.weighty = 0;
		configLayout.fill = configLayout.HORIZONTAL;

		formGerenciamentoProj.add(pnControlesProjeto, configLayout);




		//TRATAMENTO DE EVENTOS GERADOS PELO SISTEMA



		btAdicionarProj.addActionListener(new AUTEventsButtonParallelProcess() {			
			@Override
			public void autStartParallelProcess() {				
				autDBProject.autGUIInsertProject(false,txtNomeProj.getText().trim(), txtDescricao.getText().trim());
				txtProjKey.setText(autDBProject.autCurrentKeyProject());				
				treeProjetos.setModel(new javax.swing.tree.DefaultTreeModel(autDBProject.autGUITreeNodeFromProject(false)));				
			}
		}.autGetActionListener());


		btFecharApp.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("INFO: FECHANDO TELA DE GERENCIAMENTO DE PROJETOS");

				formGerenciamentoProj.setVisible(false);				
			}
		});


		btGerenciarAnexosProj.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				AUTFormAttachmentManage formAnexos = new AUTFormAttachmentManage(formGerenciamentoProj);

			}
		});


		btAtualizarProj.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				autDBProject.autStartDefaultConnection();
				autDBProject.autGUIChangeProject(autDBProject,txtProjKey.getText(),txtNomeProj.getText(), txtDescricao.getText());				
				treeProjetos.setModel( new javax.swing.tree.DefaultTreeModel(autDBProject.autGUITreeNodeFromProject(false)));

			}
		});

		btExcluirProj.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				autDBProject.autGUIRemoveProjectByKey(false, txtProjKey.getText());	
				txtNomeProj.setText("");
				txtProjKey.setText("");
				txtDescricao.setText("");				
				treeProjetos.setModel(new javax.swing.tree.DefaultTreeModel(autDBProject.autGUITreeNodeFromProject(true)));				
			}
		});

		btAdicionarContato.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				autDBProject.autGUIInsertContact(AUTGUITreeObject.autGetValueByPropertyConfig(AUT_PROJECT_SQL_PROPERTY.ID, treeProjetos.getLastSelectedPathComponent()).toString(),
						txtNomeContato.getText(), txtTelContato.getText(), txtCelContato.getText(), txtEmailContato.getText(), txtAssuntoContato.getText());
				autDBProject.autGUIGetContactManager().autValidationContactsProperties(AUTGUITreeObject.autGetValueByPropertyConfig(AUT_PROJECT_SQL_PROPERTY.ID, treeProjetos.getLastSelectedPathComponent()).toString());
				//AUTGUITable autModel = new AUTGUITable(autDBProject);
				//autModel.autCreateTableFromPropertiesDefinition(autDBProject, AUT_CONTACTS_OPERATIONS.SELECT_ALL_COLUMNS_FROM_CONTACT, AUT_CONTACTS_SQL_PROPERTIES.class, AUT_CONTACT_GUI_PROPERTIES.class,new Object[] {});
				tabelaContatos.setModel(autDBProject.autGUIGetContactManager().autGUIGetNewTableFromContacts(autDBProject, AUTGUITreeObject.autGetValueByPropertyConfig(AUT_CONTACTS_SQL_PROPERTIES.ID_PROJECT, treeProjetos.getLastSelectedPathComponent()).toString()));
			}
		});


		treeProjetos.addTreeSelectionListener(new AUTEventsTreeObject() {
			@Override
			public void autTreeObjectSelected(javax.swing.JTree item) {
				// TODO Auto-generated method stub
				System.out.println("AUT INFO: TREE OBJECT SELECT CHANGET");

				try {	
					
					System.out.println(item.getLastSelectedPathComponent().getClass());
					txtNomeProj.setText(AUTGUITreeObject.autGetValueByPropertyConfig(AUT_PROJECT_SQL_PROPERTY.NAME,item.getLastSelectedPathComponent()).toString());
					txtProjKey.setText(AUTGUITreeObject.autGetValueByPropertyConfig(AUT_PROJECT_SQL_PROPERTY.KEY,item.getLastSelectedPathComponent()).toString());
					txtDescricao.setText(AUTGUITreeObject.autGetValueByPropertyConfig(AUT_PROJECT_SQL_PROPERTY.DESCRIPTION,item.getLastSelectedPathComponent()).toString());
					AUTGUITable autTable = autDBProject.autGUIGetContactManager().autGUIGetNewTableFromContacts(autDBProject, AUTGUITreeObject.autGetValueByPropertyConfig(AUT_CONTACTS_SQL_PROPERTIES.ID_PROJECT, treeProjetos.getLastSelectedPathComponent()).toString());
			
					autTable.autAddListiner(new AUTGUITableDefinition() {

						@Override
						public AUTDBProject autGetProject() {
							// TODO Auto-generated method stub
							return autDBProject;
						}

						@Override
						public String autGetTable() {
							// TODO Auto-generated method stub
							return AUT_CONTACTS_OPERATIONS.DEFAULT_TABLE_IN_SGDB_APLICATION.toString();
						}

						@Override
						public String autGetColumn() {
							// TODO Auto-generated method stub
							return this.autGetGUITableInstance().getColumnName(this.autGetGUITableInstance().AUT_TABLE_MODEL_EVENT.getColumn());
						}

						@Override
						public String autGetWhereCondition() {
							// TODO Auto-generated method stub
							/*
							String contactId = AUTGUITreeObject.autGetValueByPropertyConfig(
									AUT_CONTACTS_SQL_PROPERTIES.ID,
									treeProjetos.getLastSelectedPathComponent()
									).toString();
									*/
							
							String contactId = "";
							
							contactId = autGetGUITableInstance().autGetValueByProperty(AUT_CONTACT_GUI_PROPERTIES.ID).toString();
							
							return String.format(AUT_CONTACTS_OPERATIONS.DEFAULT_TABLE_CONDITION_FOR_ALL_OPERATION.toString(), contactId);
						}

						@Override
						public Object autGetNewValueForColumn() {
							// TODO Auto-generated method stub
							return this.autGetGUITableInstance().getValueAt(this.autGetGUITableInstance().AUT_TABLE_MODEL_EVENT.getLastRow(), this.autGetGUITableInstance().AUT_TABLE_MODEL_EVENT.getColumn());
						}

						@Override
						public AUTGUITable autGetGUITableInstance() {
							// TODO Auto-generated method stub
							return autTable;
						}
						
					});
					tabelaContatos.setModel(autTable);
				}
				catch(java.lang.Exception e) {
					//AUTLogsManage.registrarLog(e, "AUT ERROR: HANDLER DEFINITION");
				}
			}
		});

		formGerenciamentoProj.setSize(new java.awt.Dimension(850,700));

		formGerenciamentoProj.getContentPane().setBackground(java.awt.Color.WHITE);

		formGerenciamentoProj.setTitle("SOFTTEK: GERENCIAMENTO DE PROJETOS");

		formGerenciamentoProj.setLocationRelativeTo(formPrincipal);

		formGerenciamentoProj.addWindowListener(new AUTEventsWindowManage() {

			@Override
			public void janelaFechada(WindowEvent evento) {
				// TODO Auto-generated method stub

			}

			@Override
			public void janelaExibidaNaTela(WindowEvent evento) {
				try {
					AUTBKEProjectManament conPjt = new AUTBKEProjectManament();			
					treeProjetos.setModel( new javax.swing.tree.DefaultTreeModel(conPjt.autGUITreeNodeFromProject(true)));
				}
				catch(java.lang.Exception e) {
					AUTLogsManage.registrarLog(e, "AUT ERROR: LOADER PROJECTS FROM DATABASE");
				}
			}
		});


		formGerenciamentoProj.setVisible(true);

	} 
	/**
	 * 
	 * CONSTRUTOR PADRÃO DO PROJETO
	 * 
	 */
	public AUTFormProjectsManage() {
		configurarInicializacao();
	}

	public AUTFormProjectsManage(javax.swing.JDialog form) {
		formPrincipal = form;
		configurarInicializacao();
	}
}
