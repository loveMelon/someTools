package com.hy.tools2.replace;

import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Predicate;

import com.hy.tools2.replace.converter.ILineConverter;
import com.hy.tools2.replace.utils.FileUtil;
import com.hy.tools2.replace.utils.FolderUtil;

public class ReplaceAll {

	
	public static void main(String[] as){
		String sourceFolder="C:\\project\\dmweb\\workspace\\DM\\com\\ibm\\";
		String targetFolder="C:\\project\\dmweb\\workspace\\DM\\com\\ibm.bak\\";
		Predicate<File> predicate = s-> {return s.getName().endsWith("java");};
		ILineConverter lineConverter = (s1,s2,s3,s4) -> {return s2.get(s3);};
		HYLogger logger = new HYLogger();
		Hashtable<String, List<Integer>> findings = new Hashtable<String, List<Integer>>();
		Config c = new Config(sourceFolder, targetFolder, predicate, lineConverter, logger, findings );
		
		(new ReplaceAll()).handle(c);
	}
	
	public void handle(Config c){
		List<File> flAll = FolderUtil.getAllFilelists(c.getSourceFolder());
//		c.getLogger().p(flAll.size() + " as all Files in source folders '" + c.getSourceFolder() + "'",flAll, c);
		
		List<File> flTarget = FolderUtil.getTargetFileList(flAll, c.getPredicate());
		c.getLogger().p(flTarget.size() + " matched Files in source folders '" + c.getSourceFolder() + "'",flTarget, c);

		flTarget.forEach(f->FolderUtil.initFolder(c,f));
		c.getLogger().p("inited all folders in target folder '" + c.getTargetFolder());
		
		List<Result> lr = FileUtil.replaceAll(flTarget, c);
		c.getLogger().p("All the result are: " , lr);
	}

}
