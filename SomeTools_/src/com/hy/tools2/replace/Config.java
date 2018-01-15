package com.hy.tools2.replace;

import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Predicate;

import com.hy.tools2.replace.converter.ILineConverter;
import com.hy.tools2.replace.utils.FolderUtil;

public class Config {
	private String sourceFolder;
	private String targetFolder;

	private Predicate<File> predicate;
	private ILineConverter lineConverter;
	private HYLogger logger;
	private Hashtable<String, List<Integer>> findings;

	public Config(String sourceFolder, String targetFolder, Predicate<File> predicate, ILineConverter lineConverter, HYLogger logger, Hashtable<String, List<Integer>> findings) {
		super();
		this.sourceFolder = FolderUtil.removeEndSeparatorLetter(sourceFolder);
		this.targetFolder = FolderUtil.removeEndSeparatorLetter(targetFolder);
		this.predicate = predicate;
		this.lineConverter = lineConverter;
		this.logger = logger;
		this.findings = findings;
	}

	public Hashtable<String, List<Integer>> getFindings() {
		return findings;
	}

	public String getSourceFolder() {
		return sourceFolder;
	}
	public Predicate<File> getPredicate() {
		return predicate;
	}
	public String getTargetFolder() {
		return targetFolder;
	}
	public ILineConverter getLineConverter() {
		return lineConverter;
	}
	public HYLogger getLogger() {
		return logger;
	}

}
