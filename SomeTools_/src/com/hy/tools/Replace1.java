package com.hy.tools;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.hy.tools.MutilReplaceInFolders.Result;

public class Replace1 {
	public static void main(String... a) throws IOException{
		String[] paths = new String[]{
				"C:\\projects\\ihub\\source\\trunk\\China\\TVL_SEAMLESS\\SrcCode\\",
				"C:\\projects\\ihub\\source\\trunk\\China\\China_Travel\\Reporting\\",
				"C:\\projects\\ihub\\source\\trunk\\China\\China_Travel\\Grouping\\Script\\iHub\\DB\\"
			};
		String[] fileTypes = new String[]{
				"rdl",
				"txt",
				"sql",
				"xsd",
				"msgflow",
				"esql",
				"java",
				"mxsd",
				"wsdl",
				"mset",
				"service",
				"xml",
				"tst"
			};
		String[][] replaces = new String[][]{
			{"SI_A_EVENT_AGGREGATION","T_CS_EVENT_AGGREGATION"},
			{"SI_A_EVENT_AUDIT","T_CS_EVENT_AUDIT"},
			{"SI_A_EVENT_COMMAND","T_CS_EVENT_COMMAND"},
			{"SI_A_EVENT_EXCEPTION","T_CS_EVENT_EXCEPTION"},
			{"SI_A_EVENT_EXTENSION","T_CS_EVENT_EXTENSION"},
			{"SI_A_EVENT_INDEX","T_CS_EVENT_INDEX"},
			{"SI_A_EVENT_LOG","T_CS_EVENT_LOG"},
			{"SI_A_EVENT_LOG_DETAIL","T_CS_EVENT_LOG_DETAIL"},
			{"SI_A_EVENT_MONITOR","T_CS_EVENT_MONITOR"},
			{"SI_A_EVENT_PAYLOAD","T_CS_EVENT_PAYLOAD"},
			{"SI_A_EVENT_RAW_DATA","T_CS_EVENT_RAW_DATA"},
			{"SI_A_EVENT_REPLAY","T_CS_EVENT_REPLAY"},
			{"SI_A_EVENT_STATUS","T_CS_EVENT_STATUS"},
			{"SI_A_EVENT_TRANSACTION_DETAIL","T_CS_EVENT_TRANSACTION_DETAIL"},
			{"SI_A_INCIDENT","T_CS_INCIDENT"},
			{"SI_A_INCIDENT_EVENT","T_CS_INCIDENT_EVENT"},
			{"SI_C_BUSINESS_DATA","T_CS_IHUB_BUSINESS_DATA"},
			{"SI_C_ERR_CD_CONFIG","T_CS_TVL_IHUB_ERR_CD_CONFIG"},
			{"SI_C_GATEWAY_CONFIG","T_CS_IHUB_GATEWAY_CONFIG"},
			{"SI_C_Product_Mapping","T_CS_Product_Mapping"},
			{"SI_C_ROUTING_CONFIG","T_CS_IHUB_ROUTING_CONFIG"},
			{"SI_C_RULE_CATALOGUE","T_CS_IHUB_RULE_CATALOGUE"},
			{"SI_C_RULES_CONFIG","T_CS_IHUB_RULES_CONFIG"},
			{"SI_C_RULES_XPATH","T_CS_IHUB_RULES_XPATH"},
			{"SI_C_SERVICE_CONFIG","T_CS_IHUB_SERVICE_CONFIG"},
			{"SI_C_SERVICE_LIST","T_CS_IHUB_SERVICE_LIST"},
			{"SI_C_SERVICE_LOOKUP","T_CS_TVL_IHUB_SERVICE_LOOKUP"},
			{"SI_C_STATE_MAPPING_CONFIG","T_CS_IHUB_STATE_MAPPING_CONFIG"},
			{"T_CS_IHUB_ERROR_CODE_CONFIG","T_CS_IHUB_ERROR_CODE_CONFIG"},
		};
		
		System.out.println("Start to replace...");
		List<File> list = MutilReplaceInFolders.getAllFilelists(paths);
		list = MutilReplaceInFolders.getTargetFileLists(list, fileTypes);
		
		List<Result> result = MutilReplaceInFolders.replaceAll(list, replaces);
		
//		list.forEach(System.out::println);
		result.forEach(System.out::println);
		System.out.println("    matched: " + list.size() + " files, replaced: " + result.size());
		System.out.println("End");
		
	}

}
