package com.hy.tools2.replace.utils;

public class StringUtil {
	public static void main(String[] a){
		System.out.println(replaceOnce(new StringBuffer("you are welcome"), "e w", "e also w"));
	}
	public static StringBuffer replaceOnce(StringBuffer sb, String from, String to){
		String r = sb.toString();
		int index = r.indexOf(from);
		if(index==-1)
			return sb;
		sb.replace(index, index+from.length(), to);
		return sb;
	}
}
