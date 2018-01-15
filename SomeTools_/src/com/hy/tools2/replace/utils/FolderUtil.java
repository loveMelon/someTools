package com.hy.tools2.replace.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.hy.tools2.replace.Config;

public class FolderUtil {
	public static List<File> getTargetFileList(List<File> flAll, Predicate<File> predicate) {
		return flAll.stream().filter(predicate).collect(Collectors.toList());
//		list = list.stream().filter(f->{
//			return Arrays.asList(fileTypes).stream().map(type ->{
//				return f.getName().toLowerCase().endsWith(type.toLowerCase());
//			}).reduce((b1,b2)->b1 || b2).get();
//		}).collect(Collectors.toList());
//		return list;
	}
	public static List<File> getAllFilelists(String... paths){
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
	public static boolean initFolder(Config c, File f){
		File folder = getTargetFile(f,c).getParentFile();
		if(folder.exists())
			return true;
		return folder.mkdirs();
		
//		return initTheFolder(getTargetFile(f,c).getParentFile());
	}
//	private static boolean initTheFolder(File folder){
//		if (folder.exists())
//			return true;
//		if (initTheFolder(folder.getParentFile())){
//			try {
//				return folder..createNewFile();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return false;
//
//	}
	public static File getTargetFile(File f, Config c){
		String sNew = c.getTargetFolder() + File.separator + getRelativePathFromSourceFile(f,c.getSourceFolder());
		return new File(sNew);
	}
	public static String getRelativePathFromSourceFile(File f, String sourceFolder){
		return f.getAbsolutePath().substring(sourceFolder.length()+1);
	}
	public static String removeEndSeparatorLetter(String s){
		File f = new File(s);
		return f.getAbsolutePath();
	}

}
