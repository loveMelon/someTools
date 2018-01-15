package com.hy.tools2.replace.converter;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.hy.tools2.replace.Config;
import com.hy.tools2.replace.utils.FolderUtil;
import com.hy.tools2.replace.utils.StringUtil;

public class CWE89Converter implements ILineConverter {
	String strReplacement[][] = new String[][]{
		{".execute (SQLBuffer.toString())", ".execute(ibmVeracodeEncodeSql(SQLBuffer.toString()))"},
		{".execute(SQLBuffer.toString())", ".execute(ibmVeracodeEncodeSql(SQLBuffer.toString()))"},
		{".execute (sqlBuffer.toString())", ".execute(ibmVeracodeEncodeSql(sqlBuffer.toString()))"},
		{".execute(sqlBuffer.toString())", ".execute(ibmVeracodeEncodeSql(sqlBuffer.toString()))"},
		{".execute (sqlCommand.toString())", ".executeQuery(ibmVeracodeEncodeSql(sqlCommand.toString()))"},
		{".execute(sqlCommand.toString())", ".execute(ibmVeracodeEncodeSql(sqlCommand.toString()))"}, 
		{".execute(strBuf.toString())", ".execute(ibmVeracodeEncodeSql(strBuf.toString()))"},
		{".executeQuery(sqlBuffer.toString())", ".executeQuery(ibmVeracodeEncodeSql(sqlBuffer.toString()))"},
		{".executeQuery (sqlBuffer.toString())", ".executeQuery(ibmVeracodeEncodeSql(sqlBuffer.toString()))"},
		{".executeQuery (sqlBuffer.toString ())", ".executeQuery(ibmVeracodeEncodeSql(sqlBuffer.toString()))"},
		{".executeQuery(SQLBuffer.toString())", ".executeQuery(ibmVeracodeEncodeSql(SQLBuffer.toString()))"},
		{".executeQuery (SQLBuffer.toString ())", ".executeQuery(ibmVeracodeEncodeSql(SQLBuffer.toString()))"},
		{".executeQuery (sqlCommand.toString())", ".executeQuery(ibmVeracodeEncodeSql(sqlCommand.toString()))"},
		{".executeQuery (sqlCommand.toString ())", ".executeQuery(ibmVeracodeEncodeSql(sqlCommand.toString()))"},
		{".executeQuery(sqlCommand.toString ())", ".executeQuery(ibmVeracodeEncodeSql(sqlCommand.toString()))"},
		{".executeQuery(sSQL.toString())", ".executeQuery(ibmVeracodeEncodeSql(sSQL.toString()))"},
		{".executeQuery(sql1)", ".executeQuery(ibmVeracodeEncodeSql(sql1))"},
		{".executeQuery(sql2.toString())", ".executeQuery(ibmVeracodeEncodeSql(sql2.toString()))"},
		{".executeQuery(strBuf.toString())", ".executeQuery(ibmVeracodeEncodeSql(strBuf.toString()))"},
		{".executeQuery(sqlCommand.toString())", ".executeQuery(ibmVeracodeEncodeSql(sqlCommand.toString()))"},
		{".executeUpdate(sqlBuffer.toString())", ".executeUpdate(ibmVeracodeEncodeSql(sqlBuffer.toString()))"},
		{".executeUpdate (sqlBuffer.toString())", ".executeUpdate(ibmVeracodeEncodeSql(sqlBuffer.toString()))"},
		{".executeUpdate(SQLBuffer.toString())", ".executeUpdate(ibmVeracodeEncodeSql(SQLBuffer.toString()))"},
		{".executeUpdate(addquery.toString())", ".executeUpdate(ibmVeracodeEncodeSql(addquery.toString()))"}
	};

	private static String m ="	private java.util.HashMap ibmVeravodeHash = new java.util.HashMap();\n"
			+"	private String ibmVeracodeEncodeSql(String s){\n"
			+"		com.ibm.scan.Veracode.initSql(ibmVeravodeHash, s);\n"
			+"		return (String)ibmVeravodeHash.get(s);\n"
			+"	}\n";

	@Override
	public String convert(File sourceFile, List<String> sourceContent, int currentLineNo, Config c) {
		List<Integer> l = c.getFindings().get(FolderUtil.getRelativePathFromSourceFile(sourceFile, c.getSourceFolder()));
		String r = "";
		if(!l.contains(currentLineNo+1))
			r = sourceContent.get(currentLineNo);
		else{
			r = theConvert(sourceContent.get(currentLineNo));
		}
		//insert code just for every java file
		if(currentLineNo==sourceContent.size()-1){
			r = r.trim().substring(0, r.length()-1).trim() + m + "}";
		}
		return r;
	}
	private String theConvert(String s){
		StringBuffer sb = new StringBuffer(s);
		Arrays.asList(strReplacement).forEach(one -> StringUtil.replaceOnce(sb, one[0], one[1]));
		
		return sb.toString();
	}

}
