package com.thalesgroup.hudson.plugins.cccc;

import hudson.model.AbstractItem;
import hudson.model.Run;

import java.io.File;

public class CcccUtil {

	public static File getCcccDir(Run run) {
		return new File(run.getRootDir(), "cccc/html");
	}
	
	public  static File getCcccDir(AbstractItem project) {
		return new File(project.getRootDir(), "cccc/html");
	}		
}
