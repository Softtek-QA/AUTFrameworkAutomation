package br.framework.db.main;

import java.sql.Connection;

import br.framework.db.connectors.AUTDBConnection;
import br.framework.db.connectors.AUTDBConnection.AUT_TYPE_SGDB;
import br.framework.db.management.AUTDBBusinessAreas;
import br.framework.db.management.AUTDBBusinessComponent;
import br.framework.db.management.AUTDBBusinessComponentFunctions;
import br.framework.db.management.AUTDBBusinessProcess;
import br.framework.db.management.AUTDBComponentStepsAutomation;
import br.framework.db.management.AUTDBComponentStepsDefinition;
import br.framework.db.management.AUTDBDataColumn;
import br.framework.db.management.AUTDBDataColumnRuntime;
import br.framework.db.management.AUTDBDataFlow;
import br.framework.db.management.AUTDBDataSet;
import br.framework.db.management.AUTDBDataTable;
import br.framework.db.management.AUTDBDataTableRuntime;
import br.framework.db.management.AUTDBDataValueObject;
import br.framework.db.management.AUTDBDataValueObjectRuntime;
import br.framework.db.management.AUTDBDataValueText;
import br.framework.db.management.AUTDBDataValueTextRuntime;
import br.framework.db.management.AUTDBAPPDynamicParameters;
import br.framework.db.management.AUTDBAPPStaticParameters;
import br.framework.db.management.AUTDBApplicationConfiguration;
import br.framework.db.management.AUTDBBSIBaseConfiguration;
import br.framework.db.management.AUTDBBSIRSCLibraries;
import br.framework.db.management.AUTDBBSIRSCLibrariesFunctions;
import br.framework.db.management.AUTDBBSIResourceBase;
import br.framework.db.management.AUTDBPemissionsUser;
import br.framework.db.management.AUTDBPhysicalWorkStation;
import br.framework.db.management.AUTDBProfiles;
import br.framework.db.management.AUTDBProject;
import br.framework.db.management.AUTDBResourcesConfiguration;
import br.framework.db.management.AUTDBScheduleEnd;
import br.framework.db.management.AUTDBScheduleInit;
import br.framework.db.management.AUTDBScheduleProcess;
import br.framework.db.management.AUTDBScheduleTaskManager;
import br.framework.db.management.AUTDBServicesConfiguration;
import br.framework.db.management.AUTDBSessionsUsers;
import br.framework.db.management.AUTDBSubBusinessProcess;
import br.framework.db.management.AUTDBSubProcessFunctions;
import br.framework.db.management.AUTDBDataRow;
import br.framework.db.management.AUTDBDataRowRuntime;
import br.framework.db.management.AUTDBTasksComponents;
import br.framework.db.management.AUTDBUsers;
import br.framework.db.management.AUTDBVirtualMachine;
import br.framework.db.transactions.AUTJDBCProcess;
import br.framework.gui.network.connectors.AUTNTWProjectsClientConnector;
import br.framework.gui.network.connectors.AUTNTWProjectsServerConnector;
import br.framework.gui.network.connectors.AUTNTWUtils;
import br.framework.gui.network.connectors.AUTNTWProjectsServerConnector.AUTObjectServer;
import br.framework.gui.network.connectors.AUTNTWUtils.AUT_TYPE_CONNECTION;
import br.framework.utils.AUTFMKUtils.AUTObject;
import br.framework.utils.AUTFMKUtils.AUTRandomGeneratorObject;
import br.framework.utils.AUTFMKUtils.AUTThreadProcess;
import br.framework.utils.AUTFMKUtils.AUTThreadProcessCollection;
import br.framework.db.connectors.AUTDBUtils.AUT_SHELL_OPERATIONS;




public class AUTMain {
	public static void main(String args[]) {
		
		AUTNTWProjectsServerConnector server = new AUTNTWProjectsServerConnector();
		//AUTNTWProjectsClientConnector client = new AUTNTWProjectsClientConnector();
		Integer portInit = 60120, portEnd=60124;		
		server.autAddServiceListener(server.autStartServiceToConnections(new AUTObjectServer(),portInit, portEnd));
		
		
				
		/*
		AUTDBBSIRSCLibrariesFunctions connectorResources = new AUTDBBSIRSCLibrariesFunctions();
		AUTDBApplicationConfiguration connectorApp = new AUTDBApplicationConfiguration();
		AUTDBAPPStaticParameters connectorStaticParam = new AUTDBAPPStaticParameters();
		AUTDBAPPDynamicParameters connectorDynamicParam = new AUTDBAPPDynamicParameters();
		AUTDBBusinessComponent bsiCmp = new AUTDBBusinessComponent();
		AUTDBBusinessComponentFunctions bsiCmpFunc = new AUTDBBusinessComponentFunctions();
		AUTDBSubBusinessProcess bsiSubProcess = new AUTDBSubBusinessProcess();
		AUTDBSubProcessFunctions bsiSubProcessFuncs = new AUTDBSubProcessFunctions();
		AUTDBComponentStepsDefinition bsiCompSteps = new AUTDBComponentStepsDefinition();
		AUTDBTasksComponents bsiTask = new AUTDBTasksComponents();
		AUTDBScheduleTaskManager bsiSched = new AUTDBScheduleTaskManager();
		AUTDBScheduleInit bsiSchedInit = new AUTDBScheduleInit();
		AUTDBScheduleProcess bsiSchedProc = new AUTDBScheduleProcess();
		AUTDBScheduleEnd bsiSchedEnd = new AUTDBScheduleEnd();
		AUTDBComponentStepsAutomation bsiStepAut = new AUTDBComponentStepsAutomation();
		AUTDBDataSet bsiDataSet = new AUTDBDataSet();
		AUTDBDataFlow bsiDataFlow = new AUTDBDataFlow();
		AUTDBDataTable bsiDataTable = new AUTDBDataTable();
		AUTDBDataTableRuntime bsiDataTableRT = new AUTDBDataTableRuntime();
		AUTDBDataRow bsiDataRow = new AUTDBDataRow();
		AUTDBDataColumn bsiDataColumn = new AUTDBDataColumn();
		AUTDBDataValueObject bsiDataValuesObject = new AUTDBDataValueObject();
		AUTDBDataValueText bsiDataValuesText = new AUTDBDataValueText();
		AUTDBDataRowRuntime bsiRowsRuntime = new AUTDBDataRowRuntime();
		AUTDBDataColumnRuntime bsiColumnRuntime = new AUTDBDataColumnRuntime();
		AUTDBDataValueObjectRuntime bsiColumnObjectRT = new AUTDBDataValueObjectRuntime();
		AUTDBDataValueTextRuntime bsiColumnTextRT = new AUTDBDataValueTextRuntime();

		System.out.println("Memoria inicial:");
		System.out.println(java.lang.management.ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getInit());
		System.out.println("Memoria maxima:");
		System.out.println(java.lang.management.ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax());
		System.out.println("Memoria usada:");
		System.out.println(java.lang.management.ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed());
		System.out.println("Memoria confirmada:");
		System.out.println(java.lang.management.ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getCommitted());

		//AUTDBSessionsUsers connector = new AUTDBSessionsUsers();				

		connectorResources.startConnection(AUT_TYPE_SGDB.MYSQL,"127.0.0.1",3306,"lry","root", "Auto5@2018");
		connectorResources.autExecSubStatementsWithResult("select * from lry.aut_projects",new Object[] {});	
		connectorResources.autInsertProject("PJT 001", "DESCRIPTION PJT001");

		connectorApp.autSetConnection(connectorResources.getActiveConnection());
		connectorApp.autInsertApplicationConfiguration("1", "APPKEY001", "APPNAME", "APPDESCRIPTION", "DIRETORIODOAPLICATIVOEXECUTAVEL", "0", "0");

		connectorStaticParam.autSetConnection(connectorApp.getActiveConnection());
		connectorStaticParam.autInsertStaticParameterByParamId("1", "PARAM1", "VALOR PARAMETRO 1");

		connectorDynamicParam.autSetConnection(connectorApp.getActiveConnection());
		connectorDynamicParam.autInsertStaticParameterByParamId("1", "PARAM1", "VALOR PARAMETRO 1");

		bsiCmp.autSetConnection(connectorApp.getActiveConnection());
		bsiCmpFunc.autSetConnection(bsiCmp.getActiveConnection());
		bsiSubProcess.autSetConnection(connectorApp.getActiveConnection());
		bsiSubProcessFuncs.autSetConnection(connectorApp.getActiveConnection());
		bsiCompSteps.autSetConnection(connectorApp.getActiveConnection());
		bsiTask.autSetConnection(connectorApp.getActiveConnection());
		bsiSched.autSetConnection(connectorApp.getActiveConnection());
		bsiSchedInit.autSetConnection(connectorApp.getActiveConnection());
		bsiSchedProc.autSetConnection(connectorApp.getActiveConnection());
		bsiSchedEnd.autSetConnection(connectorApp.getActiveConnection());
		bsiStepAut.autSetConnection(connectorApp.getActiveConnection());
		bsiDataSet.autSetConnection(connectorApp.getActiveConnection());
		bsiDataFlow.autSetConnection(connectorApp.getActiveConnection());
		bsiDataTable.autSetConnection(connectorApp.getActiveConnection());
		bsiDataRow.autSetConnection(connectorApp.getActiveConnection());
		bsiDataColumn.autSetConnection(connectorApp.getActiveConnection());
		bsiDataValuesObject.autSetConnection(connectorApp.getActiveConnection());
		bsiDataValuesText.autSetConnection(connectorApp.getActiveConnection());
		bsiDataTableRT.autSetConnection(connectorApp.getActiveConnection());
		bsiRowsRuntime.autSetConnection(connectorApp.getActiveConnection());
		bsiColumnRuntime.autSetConnection(connectorApp.getActiveConnection());
		bsiColumnObjectRT.autSetConnection(connectorApp.getActiveConnection());
		bsiColumnTextRT.autSetConnection(connectorApp.getActiveConnection());

		connector.autInsertService(connector.autCurrentIdProject(), "SAP", "SISTEMA ERP", "LEROY-GRANJA JULIETA", "127.0.0.1", "2323","OK");
		connector.autInsertService(connector.autCurrentIdProject(), "HYBRIS", "SISTEMA ERP", "LEROY-GRANJA JULIETA", "127.0.0.1", "2323","OK");
		connector.autInsertService(connector.autCurrentIdProject(), "VA-DEV", "VENDAS ASSISTIDAS HOMOLOG DESENVOLVIMENTO", "LEROY-GRANJA JULIETA", "127.0.0.1", "2323","OK");
		connector.autInsertService(connector.autCurrentIdProject(), "VA-HOMOLOG", "VENDAS ASSISTIDAS HOMOLOG 1", "LEROY-GRANJA JULIETA", "127.0.0.1", "2323","OK");
		connector.autInsertService(connector.autCurrentIdProject(), "VA-HOMOLOG2", "VENDAS ASSISTIDAS HOMOLOG 2", "LEROY-GRANJA JULIETA", "127.0.0.1", "2323","OK");
		connector.autInsertService(connector.autCurrentIdProject(), "PDV", "SISTEMA DE PAGAMENTO PDV", "LEROY-GRANJA JULIETA", "127.0.0.1", "2323","OK");
		connector.autInsertService(connector.autCurrentIdProject(), "MIDI-CLIENT", "GERENTE DE AUTORIZAÇÕES NFCe", "LEROY-GRANJA JULIETA", "127.0.0.1", "2323","OK");
		connector.autInsertService(connector.autCurrentIdProject(), "SAT-NFCe", "GERENTE DE AUTORIZAÇÕES SAT NFCe", "LEROY-GRANJA JULIETA", "127.0.0.1", "2323","OK");
		connector.autListServicesAll();
		connector.autListServicesByProjectId("1");
		connector.autInsertResource(connector.autCurrentIdProject(), "LEROY-GRANJA JULIETA", "c:/AUT RSP 001", "c:/AUT RSP 001", "\\\\LOCALHOST\\AUT-HST-001", "OK", "Windows");
		connector.autListResourcesAll();
		connector.autListResourcesByProjectId(connector.autCurrentIdProject());
		connector.autInsertWorkStation("1","1", "LSTKBR08519", "DESK AUTOMATION", "10.10.10.23", "3389", "LISTEN", "172.32.20.31", "50001", "OK");
		connector.autListWorkStationAll();
		connector.autListWorkStationByResourceId("1");
		connector.autInsertVirtualMachineByResourceId("1", "1", "VM-TMP001-HST001-LJ0035", "VM + PDV SERVICE", "10.0.10.1", "3389", "CONNECTED", "172.32.18.31", "50002", "CONNECTED","Windows");
		connector.autListVirtualMachineAll();
		connector.autListVirtualMachine("1");

		connector.autInsertProfiles(connector.autCurrentIdProject(), "PJTNAME0005", "DESCRIPTIONPJT00555  66");
		connector.autInsertPermissionUser("1", "PERMISSAO 1", "DESCRIPTION 1");
		connector.autInsertUser("1", "automation", "OK", "AUTOMATION", "passwordadmin");
		connector.autInsertSession("1", "SESADMIN");


		connectorResources.autInsertBusinessArea(connectorResources.autCurrentIdProject(), "Atendimento a Clientes", "Envolve os diversos tipos de sistemas envolvidos no processo de gerenciamento como um todo", "ATIVA");
		connectorResources.autInsertBusinessProcessByBSIArea("1", "Cadastro-PF", "Cadastro para clientes pessoa física");
		connectorResources.autInsertConfigurationBaseByBSIProcess("1", connectorResources.autGenerateRandomKey() ,"CONFIG BASE", "BSI CONFIGURATION BASE TESTE","LIB", "ZIP");		

		//connectorResources.autInsertResourceBaseByConfigBSId("1", "NAME CFG 001", "DESCRIPTION CFG 001", "c:\\RSP001", "C:\\RSP001", "C:\\Users\\Marcos\\Desktop\\nome.saida\\dados.xlsx", "DOCUMENTATION");		

		connectorResources.autInsertLibraryByIdConfigurationBase("1", "LIB001", "FRAMEWORK001", "JAVA", "C:\\Framework Softtek V1\\recursos\\IBM DB2\\db2jcc4.jar", "C:\\Framework Softtek V1\\recursos\\IBM DB2", "C:\\Framework Softtek V1\\recursos\\IBM DB2", false, false, true, false, false);
		connectorResources.autInsertFunctionByLibrary("1", "10", "30", "FUNCTION LOADER", "FUNCTION INIT", "FUNCTION PROCESS", "FUNCTION END", "1");						
		bsiCmp.autInsertBSIComponent("1", "CMP 001", "CMP DESCRIPTION 001", "JAVA", "C:\\Users\\Marcos\\Desktop\\Videos Apresentação\\Padrão Modelagem - V1.pptx", "CMPKEY001", "CMPINPUT", "CMPOUTPUT", true,false,false,false,false);
		bsiCmp.autListAllBusinessComponents();
		bsiCmp.autListBusinessComponentByConfigBase("1");	
		bsiCmpFunc.autInsertFunctionByBSIComponent("1","1", "11", "12", "functionload", "functioninit", "functionprocess", "functionend");		
		bsiSubProcess.autInsertSubProcessByIdConfigurationBase("1", "NAME SUBPROCESS", "DESCRIPTION SUBPROCESS", "JAVA", "C:\\Users\\Marcos\\Desktop\\Videos Apresentação\\Padrão Modelagem - V1.pptx", "CHAVEITEMKEY", "INPUTLOCAL", "OUTPUTLOCAL", true, false, true, false, true);
		bsiSubProcessFuncs.autInsertFunctionBySubProcessId("1", "1", "00", "11", "functionloadimp", "functioninitimp", "functionprocessimp", "functionend");		
		bsiCompSteps.autInsertStepsDefinitions("1", "1", "STEPNAME", "STEPDESCRIPTION", "STEPACTIONS", "STEPSEXPECTRESULT", "STEPEXPECTRESULT", "OK", true);
		bsiCompSteps.autInsertStepsDefinitions("1", "1", "STEPNAME", "STEPDESCRIPTION", "STEPACTIONS", "STEPSEXPECTRESULT", "STEPEXPECTRESULT", "OK", true);
		bsiCompSteps.autInsertStepsDefinitions("1", "1", "STEPNAME", "STEPDESCRIPTION", "STEPACTIONS", "STEPSEXPECTRESULT", "STEPEXPECTRESULT", "OK", true);
		bsiTask.autInsertTaskByComponent("1", "TASKNAME", "TASKDESCRIPTION", "20");
		bsiSched.autInsertScheduledByTaskId("1", "SCHEDNAME", "SCHEDDESCRIPTION", "START", "CANCEL", "STOP");
		bsiSchedInit.autInsertScheduledInitBySchedManager("1", "STATESCHEDINIT", "STATESCHEDOBJECTINIT", "STATESCHEDINITDESCRIPTION");
		bsiSchedProc.autInsertScheduledProcessBySchedManager("1", "STATESCHEDPROCESS", "STATESCHEDOBJECTPROCESS", "STATESCHEDPROCESSDESCRIPTION");
		bsiSchedEnd.autInsertScheduledEndBySchedManager("1", "STATESCHEDEND", "STATESCHEDOBJECTEND", "STATESCHEDENDDESCRIPTION");

		bsiStepAut.autInsertStepAutomation("1", "1", "0", "AUTSTEPNAME", "AUTSTEPDESCRIPTION", "AUTSTEPACTIONS", "AUTEXPECTRESULT", "AUTTYPEEXPECTRESULT", "AUTBEFOREEXEC", "AUTAFTEREXEC", "AUTLOG", "AUTSTATEOK", false, "RUNNING");		
		bsiDataSet.autInsertDataSetByProjectId("1", "DATASETVAREGRESSIVO", "VS001", "OK");

		bsiDataFlow.autInsertDataFlowByDataSetId("1", true, false, false, false,true,true,"SIGSTART", 60, 70, 80, 90, "SECOND");

		bsiDataFlow.autListAllDataFlow();
		String tabela = "1";
		String row = "1";
		bsiDataTable.autInsertDataTableByDataFlow(tabela, "TBL_CADASTRO", "TABELA DE CADASTRO DE CLIENTES", false, true, true, false, true, true, "MD00001-CN00001-CTP00000");
		bsiDataRow.autInsertRowByTableId(tabela, "ROW DESCRIPTION", "ROW ATTCHMENT", "1", true);

		bsiDataColumn.autInsertDataColumn(tabela, "1", "COLUMN1", "COLUMNDESCRIPTION1", "TYPEVALUEOBJECT");

		bsiDataValuesObject.autInsertValueObjectByColumnId("1", "NUMERO CLIENTE 2", "CONTEUDO 2", "1");
		bsiDataValuesText.autInsertValueTextByColumnId("1", "VALUETEXT", "VALUECONTENT", "1");		
		bsiDataTableRT.autLoaderTableRuntimeByProcessId();

		bsiRowsRuntime.autLoaderRowsDataRuntime();
		bsiColumnRuntime.autLoaderColumnsDataRuntime();
		//bsiColumnObjectRT.autInsertValueObjectRuntimeByColumnId("1", "VALORDESCRIPTION1", "VALOROBJECT0001", "1");

		bsiColumnObjectRT.autLoaderValuesObjectRuntime();		
		bsiColumnTextRT.autLoaderValuesTextRuntime();
		 */

	}


}
