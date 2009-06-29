package com.thalesgroup.hudson.plugins.cccc;

import hudson.model.AbstractBuild;

import java.io.File;

public class CcccHtmlResultBuildAction extends CcccBaseHtmlResultAction{

	
	private final AbstractBuild<?, ?> build;
	
	public CcccHtmlResultBuildAction(AbstractBuild<?, ?> build) {
		this.build = build;
	}

	protected String getTitle() {
		return build.getDisplayName() + " cccc/html";
	}

	protected File dir() {
		return new File(build.getRootDir(), "cccc/html/" );
	}

}
