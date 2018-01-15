package com.hy.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MutilReplaceInFolders {
	static List<Result> replaceAll(List<File> list, String[][] replaces) {
		List<List<Result>> results = new ArrayList<>();
		list.stream().forEach(f ->{results.add(replacOne(f, replaces));});
		return results.stream().reduce((l1, l2)->{
			l1.addAll(l2);
			return l1;
		}).get();
	}
	private static List<Result> replacOne(File f, String[][] replaces) {
		
		List<String> content  = readOne(f);
		List<String> newContent = new ArrayList<>();
		List<Result> result = new ArrayList<>();
		int lineNumber = 0;
		for(String s:content){
			lineNumber++;
			String sN = new String(s);
			for(String[] pair: replaces){
				int index = sN.indexOf(pair[0]);
				if(index>-1){
					sN=sN.substring(0, index) + pair[1] + sN.substring(index + pair[0].length());
					result.add(new Result(f.getAbsolutePath(), lineNumber, pair[0], pair[1],s,sN));
				}
			}
			newContent.add(sN);
		}
		
		writeOne(f, newContent);
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
	static List<File> getTargetFileLists(List<File> list, String[] fileTypes) {
		list = list.stream().filter(f->{
			return Arrays.asList(fileTypes).stream().map(type ->{
				return f.getName().toLowerCase().endsWith(type.toLowerCase());
			}).reduce((b1,b2)->b1 || b2).get();
		}).collect(Collectors.toList());
		return list;
	}
	static List<File> getAllFilelists(String... paths){
		List<List<File>> lss = new ArrayList<>();
		Arrays.stream(paths).forEach(p->{
			List<File> fs = new ArrayList<>();
			fs = listFile(p, fs);
			lss.add(fs);
		});
		List<File> lr = lss.stream().reduce((l1, l2)->{
			l1.addAll(l2);
			return l1;
		}).get();
		return lr;
		
	}
	
	private static List<File> listFile(String path, List<File> list){
		try {
			Files.list(Paths.get(path)).map(Path::toFile)
			.forEach(f -> {
				if(f.isDirectory()){
					listFile(f.getAbsolutePath(), list);
				}else{
					list.add(f);
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	static class Result{
		public Result(String fileName, int lineNumber, String find, String replaceTo, String ori, String end) {
			super();
			this.fileName = fileName;
			this.lineNumber = lineNumber;
			this.find = find;
			this.replaceTo = replaceTo;
			this.ori = ori;
			this.end = end;
		}
		String fileName;
		int lineNumber;
		String find;
		String replaceTo;
		String ori;
		String end;
		public String toString(){
			return fileName + ", " + lineNumber + ", " + find + " -> " + replaceTo + ", <c>" + ori;
		}
		public String toStringAll(){
			return fileName + ", " + lineNumber + ": " + find + " -> " + replaceTo + "("+ori+")" + "("+end+")";
		}
	}

}
