package com.hy.tools2.replace;

import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Predicate;

import com.hy.tools2.replace.converter.CWE89Converter;
import com.hy.tools2.replace.converter.ILineConverter;
import com.hy.tools2.replace.predicate.VeracodePredicate;
import com.hy.tools2.replace.utils.FolderUtil;
import com.hy.tools2.replace.utils.XmlUtil;

public class CWE89ReplaceAll {
	public static void main(String[] as){
		String sourceFolder="C:\\project\\dmweb\\workspace\\DM";
		String targetFolder="C:\\project\\dmweb\\workspace\\DM.new";
		String veracodeScanResultFileName = "C:\\project\\dmweb\\workspace\\SomeTools\\CWE89.xml";
		Hashtable<String, List<Integer>> findings = (new XmlUtil()).parseResult(veracodeScanResultFileName);
		Predicate<File> predicate = new VeracodePredicate(findings, FolderUtil.removeEndSeparatorLetter(sourceFolder));
		ILineConverter lineConverter = new CWE89Converter();
		HYLogger logger = new HYLogger();
	
		Config c = new Config(sourceFolder, targetFolder, predicate, lineConverter, logger, findings );
		
		(new ReplaceAll()).handle(c);
	}
	

}
