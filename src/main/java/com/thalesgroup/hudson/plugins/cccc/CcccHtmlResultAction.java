package com.thalesgroup.hudson.plugins.cccc;

import hudson.model.AbstractItem;
import hudson.model.AbstractProject;
import hudson.model.ProminentProjectAction;
import hudson.model.Run;

import java.io.File;

public class CcccHtmlResultAction extends CcccBaseHtmlResultAction implements ProminentProjectAction{

	private final AbstractItem project;

	public CcccHtmlResultAction(AbstractItem project) {
		this.project = project;
	}

	
	protected File dir() {

		if (project instanceof AbstractProject) {
			AbstractProject abstractProject = (AbstractProject) project;
			Run run = abstractProject.getLastSuccessfulBuild();
			if (run != null) {
				File ccccDir = CcccUtil.getCcccDir(run);

				if (ccccDir.exists())
					return ccccDir;
			}
		}
		return CcccUtil.getCcccDir(project);
	}

	protected String getTitle() {
		return project.getDisplayName() + " Cccc HTML";
	}

	
}
