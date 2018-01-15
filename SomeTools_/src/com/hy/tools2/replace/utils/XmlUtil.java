package com.hy.tools2.replace.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlUtil {
	public static void main(String[] a){
		XmlUtil r = new XmlUtil();
		Hashtable<String, List<Integer>> h = r.parseResult("C:\\project\\dmweb\\workspace\\SomeTools\\CWE89.xml");
		h.keySet().forEach(s->h.get(s).forEach(line->System.out.println(s + ", " + line)));
	}

	public Hashtable<String, List<Integer>> parseResult(String file)
	{
		Hashtable<String, List<Integer>> h =new Hashtable<String, List<Integer>>();
	      try {  
	          Document doc = (new SAXReader()).read(new FileInputStream(file));  
	           List<Element> flaws = doc.getRootElement().element("staticflaws").elements("flaw");
	           flaws.forEach(e-> insertItem(h,e) );
	       } catch (Exception e) {  
	           e.printStackTrace();  
	       }  
	      return h;
	   }

	private void insertItem(Hashtable<String, List<Integer>> h, Element e) {
		//TODO need to fix to match any OS
		String key = e.attribute("sourcefilepath").getValue().replace('/', '\\') + e.attribute("sourcefile").getValue();
		int line = Integer.parseInt(e.attribute("line").getValue().trim());
		List<Integer> l = h.get(key);
		if(l==null){
			l = new ArrayList<Integer>();
			h.put(key, l);
		}
		l.add(line);
	} 
}
	






