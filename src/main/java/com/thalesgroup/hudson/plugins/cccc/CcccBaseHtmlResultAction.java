package com.thalesgroup.hudson.plugins.cccc;

import hudson.FilePath;
import hudson.model.Action;
import hudson.model.DirectoryBrowserSupport;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;

import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

public abstract class CcccBaseHtmlResultAction implements Action {
	
	public static final String URL_NAME = "ccccHtmlResult";
	
	public String getUrlName() {
		return URL_NAME;
	}

	public String getDisplayName() {
		return "Cccc HTML Results";
	}

	public String getIconFileName() {
		if (dir().exists())
			return "help.gif";
		else
			// hide it since we don't have doxygen yet.
			return null;
	}

	public void doDynamic(StaplerRequest req, StaplerResponse rsp)
			throws IOException, ServletException, InterruptedException {
		new DirectoryBrowserSupport(this, getTitle()).serveFile(req, rsp,
				new FilePath(dir()), "help.gif", false);
	}

	protected abstract String getTitle();

	protected abstract File dir();


}
