package com.hy.tools2.replace;

public class Result {
	public Result(String relativeFileName, int lineNumber, String lineSource, String lineTarget) {
		super();
		this.relativeFileName = relativeFileName;
		this.lineNumber = lineNumber;
		this.lineSource = lineSource;
		this.lineTarget = lineTarget;
	}

	String relativeFileName;
	int lineNumber;
	String lineSource;
	String lineTarget;

	public String toString() {
		String s = relativeFileName + ", " + lineNumber + "";
		return s + "\n\t" + lineSource + "\n\t" + lineTarget;
	}
}
