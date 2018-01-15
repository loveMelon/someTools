package com.hy.tools;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.hy.tools.MutilReplaceInFolders.Result;

public class Replace2 {
	public static void main(String... a) throws IOException{
		String[] paths = new String[]{
				"C:\\Users\\IBM_ADMIN\\Desktop\\sql\\"
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
			{"si_i_tprmdets","ttt"},
			{"si_i_tprmtrxn","ttt"},
			{"si_i_triliab","ttt"},
			{"si_i_trilayer","ttt"},
			{"si_i_triattch","ttt"},
			{"si_i_tcmdebitsrec","ttt"},
			{"si_i_tclprdcr","ttt"},
			{"si_i_tahgrp","ttt"},
			{"si_i_tbenefic","ttt"},
			{"si_i_taddress","ttt"},
			{"si_i_tcovgobj","ttt"},
			{"si_i_triprmtr","ttt"},
			{"si_i_tphone","ttt"},
			{"si_i_taddinsd","ttt"},
			{"si_i_tcontact","ttt"},
			{"si_i_tprmcomm","ttt"},
			{"si_i_tprmchg","ttt"},
			{"si_i_tpolprm","ttt"},
			{"si_i_tpolrisk","ttt"},
			{"si_i_tpolsect","ttt"},
			{"si_i_triskloc","ttt"},
			{"si_i_tclient","ttt"},
			{"si_i_tcliento_bc","ttt"},
			{"si_i_tcliento","ttt"},
			{"si_i_tclienti_bc","ttt"},
			{"si_i_tclienti","ttt"},
			{"si_i_tpolchg","ttt"},
			{"si_i_tpolloc","ttt"},
			{"si_i_tpolcomm_bc","ttt"},
			{"si_i_tpolcomm","ttt"},
			{"si_i_tpolcoin","ttt"},
			{"si_i_tpolinst_bc","ttt"},
			{"si_i_tpoldebits","ttt"},
			{"si_i_tpolkey","ttt"},
			{"si_i_tpolprdr","ttt"},
			{"si_i_tpoladj","ttt"},
			{"si_i_tpoldebits_factors","ttt"},
			{"si_i_tendchng","ttt"},
			{"si_i_tendhist","ttt"},
			{"si_i_tpolicy","ttt"},
			{"si_i_tpolicy_bc","ttt"},
			{"si_i_ttext","ttt"},
			{"si_i_ttext_bc","ttt"},
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
