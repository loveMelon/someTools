package com.hy.tools2.replace.converter;

import java.io.File;
import java.util.List;

import com.hy.tools2.replace.Config;

@FunctionalInterface
public interface ILineConverter {
	public String convert(File sourceFile, List<String> sourceContent, int currentLineNo, Config c);
}
