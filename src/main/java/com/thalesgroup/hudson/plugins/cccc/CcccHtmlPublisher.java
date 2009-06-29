/*******************************************************************************
* Copyright (c) 2009 Thales Corporate Services SAS                             *
* Author : Gregory Boissinot                                                   *
*                                                                              *
* Permission is hereby granted, free of charge, to any person obtaining a copy *
* of this software and associated documentation files (the "Software"), to deal*
* in the Software without restriction, including without limitation the rights *
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell    *
* copies of the Software, and to permit persons to whom the Software is        *
* furnished to do so, subject to the following conditions:                     *
*                                                                              *
* The above copyright notice and this permission notice shall be included in   *
* all copies or substantial portions of the Software.                          *
*                                                                              *
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR   *
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,     *
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE  *
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER       *
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,*
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN    *
* THE SOFTWARE.                                                                *
*******************************************************************************/

package com.thalesgroup.hudson.plugins.cccc;

import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.Action;
import hudson.model.BuildListener;
import hudson.model.Descriptor;
import hudson.model.Result;
import hudson.tasks.Publisher;

import java.io.File;
import java.io.Serializable;

import org.kohsuke.stapler.DataBoundConstructor;



public class CcccHtmlPublisher extends Publisher implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final CcccHtmlDescriptor DESCRIPTOR = new CcccHtmlDescriptor();
    
    private final String metricFileHtmlPath;
    
    private final boolean retainAllHtml;
    
    public Descriptor<Publisher> getDescriptor() {
        return DESCRIPTOR;
    }
    
    @DataBoundConstructor
    public CcccHtmlPublisher(String metricFileHtmlPath, boolean retainAllHtml){
        this.metricFileHtmlPath=metricFileHtmlPath;
        this.retainAllHtml=retainAllHtml;
    }

    @Override
    public Action getProjectAction(AbstractProject<?,?> project){
        return new CcccHtmlResultAction(project);
    }

    protected boolean canContinue(final Result result) {
        return result != Result.ABORTED && result != Result.FAILURE;
    }

    @Override
    public boolean perform(AbstractBuild<?,?> build, Launcher launcher, BuildListener listener){
    	
        if(this.canContinue(build.getResult())){
            
        	listener.getLogger().println("Processing cccc Html report");
        	
            try{
            
	            File fMetricFileHtmlPath= new File(metricFileHtmlPath);
	            File fParentMetricFileHtmlPath= fMetricFileHtmlPath.getParentFile();
	            FilePath fPathMetricFileHtmlPath = new FilePath(fParentMetricFileHtmlPath);
	            
				listener.getLogger().println("The determined CCCC HTML directory is '"+ fPathMetricFileHtmlPath + "'.");
	
				// Determine the future stored doxygen directory
				FilePath target = new FilePath(retainAllHtml ? CcccUtil.getCcccDir(build): CcccUtil.getCcccDir(build.getProject()));
	
				
				
				
				if (fPathMetricFileHtmlPath.copyRecursiveTo("**/*", target) == 0) {
					if (build.getResult().isBetterOrEqualTo(Result.UNSTABLE)) {
						// If the build failed, don't complain that there was no
						// cccc html.
						// The build probably didn't even get to the point where
						// it produces html.
					}
	
					listener.getLogger().println("Failure to copy the generated doxygen html documentation at '"+ fPathMetricFileHtmlPath + "' to '" + target+ "'");
	
					build.setResult(Result.FAILURE);
					return true;
				}else{
					//rename the main HTML report into index.html
					String fMainHtmlReport= fMetricFileHtmlPath.getName();
					(new FilePath(target,fMainHtmlReport)).renameTo(new FilePath(target,"index.html"));
					
				}
	
				// add build action, if cccc HTML is recorded for each build
				//build.getProject().addAction(new CcccHtmlResultAction(build.getProject(),"titi.html"));
				if (retainAllHtml)
					build.addAction(new CcccHtmlResultBuildAction(build));
				
			} catch (Exception e) {
				e.printStackTrace(listener.fatalError("error"));
				build.setResult(Result.FAILURE);
				return true;
			}

            
            
            listener.getLogger().println("End Processing cccc html report");
        }
        return true;
    }


	public String getMetricFileHtmlPath() {
		return metricFileHtmlPath;
	}

	public boolean getRetainAllHtml() {
		return retainAllHtml;
	}


    
}
