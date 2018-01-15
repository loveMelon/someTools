package com.hy.tools2.replace;

import java.io.File;
import java.util.List;

import com.hy.tools2.replace.utils.FolderUtil;

public class HYLogger {

	public void p(String desc, List<File> lf, Config c) {
		p(desc);
		lf.forEach(f->p(FolderUtil.getRelativePathFromSourceFile(f, c.getSourceFolder())));
	}
	public void p(String desc){
		System.out.println(desc);
	}
	public void p(String desc, List<Result> lr) {
		p(desc);
		lr.forEach(r->p(r.toString()));
	}

}
