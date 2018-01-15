package com.hy.tools2.replace.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.hy.tools2.replace.Config;
import com.hy.tools2.replace.Result;

public class FileUtil {
	public static List<Result> replaceAll(List<File> lf, Config c) {
		List<List<Result>> results = new ArrayList<>();
		lf.stream().forEach(f ->{results.add(replacOne(f, c));});
		return results.stream().reduce((l1, l2)->{
			l1.addAll(l2);
			return l1;
		}).get();
	}
	private static List<Result> replacOne(File f, Config c) {
		
		List<String> content  = readOne(f);
		List<String> newContent = new ArrayList<>();
		List<Result> result = new ArrayList<>();
		for(int lineNumber=0;lineNumber<content.size();lineNumber++){
			String sN = c.getLineConverter().convert(f, content, lineNumber, c);
//			for(String[] pair: replaces){
//				int index = sN.indexOf(pair[0]);
//				if(index>-1){
//					sN=sN.substring(0, index) + pair[1] + sN.substring(index + pair[0].length());
//				}
//			}
			if(!sN.equals(content.get(lineNumber)))
				result.add(new Result(com.hy.tools2.replace.utils.FolderUtil.getRelativePathFromSourceFile(f, c.getSourceFolder()), lineNumber, content.get(lineNumber),sN));
			newContent.add(sN);
		}
		
		writeOne(com.hy.tools2.replace.utils.FolderUtil.getTargetFile(f,c), newContent);
		return result;
	}
	private static void writeOne(File f, List<String> content) {
		try {
			PrintWriter out = new PrintWriter(f);
			content.forEach(s->{	
				try {
					out.println(new String(s.getBytes(),"UTF-8"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static List<String> readOne(File f){
		List<String> content = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String strline;
			while( (strline = reader.readLine())!=null){
				content.add(strline);
			}
			reader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}

}
