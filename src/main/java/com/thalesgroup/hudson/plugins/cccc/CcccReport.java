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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thalesgroup.hudson.plugins.cccc.model.ObjectOrientedDesignModule;
import com.thalesgroup.hudson.plugins.cccc.model.ProceduralSummaryModule;
import com.thalesgroup.hudson.plugins.cccc.model.ProjectSummary;
import com.thalesgroup.hudson.plugins.cccc.model.StructuralSummaryModule;


public class CcccReport implements Serializable {

	//Project Summary
	ProjectSummary projectSummary = null;
	
	//Procedural Metrics Summary	
	List<ProceduralSummaryModule> proceduralSummaryModuleList = new ArrayList<ProceduralSummaryModule>();	
	
	//Object Oriented design
	List<ObjectOrientedDesignModule> objectOrientedDesignModuleList  = new ArrayList<ObjectOrientedDesignModule>();
	
	//Structural Metrics Summary
	List<StructuralSummaryModule> structuralSummaryModuleList = new ArrayList<StructuralSummaryModule>();
	

	public ProjectSummary getProjectSummary() {
		return projectSummary;
	}

	public void setProjectSummary(ProjectSummary projectSummary) {
		this.projectSummary = projectSummary;
	}

	public List<ProceduralSummaryModule> getProceduralSummaryModuleList() {
		return proceduralSummaryModuleList;
	}

	public void setProceduralSummaryModuleList(
			List<ProceduralSummaryModule> proceduralSummaryModuleList) {
		this.proceduralSummaryModuleList = proceduralSummaryModuleList;
	}

	public List<ObjectOrientedDesignModule> getObjectOrientedDesignModuleList() {
		return objectOrientedDesignModuleList;
	}

	public void setObjectOrientedDesignModuleList(
			List<ObjectOrientedDesignModule> objectOrientedDesignModuleList) {
		this.objectOrientedDesignModuleList = objectOrientedDesignModuleList;
	}

	public List<StructuralSummaryModule> getStructuralSummaryModuleList() {
		return structuralSummaryModuleList;
	}

	public void setStructuralSummaryModuleList(
			List<StructuralSummaryModule> structuralSummaryModuleList) {
		this.structuralSummaryModuleList = structuralSummaryModuleList;
	}
	

}
