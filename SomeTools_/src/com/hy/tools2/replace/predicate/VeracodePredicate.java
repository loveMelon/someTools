package com.hy.tools2.replace.predicate;

import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Predicate;

import com.hy.tools2.replace.utils.FolderUtil;

//T is File
public class VeracodePredicate implements Predicate {
	Hashtable<String, List<Integer>> findings;
	String sourceFolder;
	public VeracodePredicate(Hashtable<String, List<Integer>> findings, String sourceFolder){
		this.findings = findings;
		this.sourceFolder = sourceFolder;
	}

	@Override
	public boolean test(Object o) {
		File f = (File)o;
		return findings.containsKey(FolderUtil.getRelativePathFromSourceFile(f, sourceFolder));
	}

}
