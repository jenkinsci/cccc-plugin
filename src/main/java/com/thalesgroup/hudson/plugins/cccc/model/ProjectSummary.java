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

package com.thalesgroup.hudson.plugins.cccc.model;

public class ProjectSummary {

	
	private int nbModules;
	
	private int linesOfCode;
	
	private float linesOfCodePerModule;
	
	private int mcCabesCyclomaticComplexity;
	
	private float mcCabesCyclomaticComplexityPerModule;
	
	private int linesOfComment;
	
	private float linesOfCommentPerModule;
	
	private String linesOfCodePerLineOfComment;
	
	private String mcCabesCyclomaticComplexityPerLineOfComment;
	
	private int iF4;
	
	private float iF4PerModule;

	private int iF4Visible; 
		
	private float iF4VisiblePerModule; 
	
	private int iF4Concrete;

	private float iF4ConcretePerModule;
	
	private int rejectedLinesOfCode;

	public int getNbModules() {
		return nbModules;
	}

	public void setNbModules(int nbModules) {
		this.nbModules = nbModules;
	}

	public int getLinesOfCode() {
		return linesOfCode;
	}

	public void setLinesOfCode(int linesOfCode) {
		this.linesOfCode = linesOfCode;
	}

	public float getLinesOfCodePerModule() {
		return linesOfCodePerModule;
	}

	public void setLinesOfCodePerModule(float linesOfCodePerModule) {
		this.linesOfCodePerModule = linesOfCodePerModule;
	}

	public int getMcCabesCyclomaticComplexity() {
		return mcCabesCyclomaticComplexity;
	}

	public void setMcCabesCyclomaticComplexity(int mcCabesCyclomaticComplexity) {
		this.mcCabesCyclomaticComplexity = mcCabesCyclomaticComplexity;
	}

	public float getMcCabesCyclomaticComplexityPerModule() {
		return mcCabesCyclomaticComplexityPerModule;
	}

	public void setMcCabesCyclomaticComplexityPerModule(
			float mcCabesCyclomaticComplexityPerModule) {
		this.mcCabesCyclomaticComplexityPerModule = mcCabesCyclomaticComplexityPerModule;
	}

	public int getLinesOfComment() {
		return linesOfComment;
	}

	public void setLinesOfComment(int linesOfComment) {
		this.linesOfComment = linesOfComment;
	}

	public float getLinesOfCommentPerModule() {
		return linesOfCommentPerModule;
	}

	public void setLinesOfCommentPerModule(float linesOfCommentPerModule) {
		this.linesOfCommentPerModule = linesOfCommentPerModule;
	}

	public String getLinesOfCodePerLineOfComment() {
		return linesOfCodePerLineOfComment;
	}

	public void setLinesOfCodePerLineOfComment(String linesOfCodePerLineOfComment) {
		this.linesOfCodePerLineOfComment = linesOfCodePerLineOfComment;
	}

	public String getMcCabesCyclomaticComplexityPerLineOfComment() {
		return mcCabesCyclomaticComplexityPerLineOfComment;
	}

	public void setMcCabesCyclomaticComplexityPerLineOfComment(
			String mcCabesCyclomaticComplexityPerLineOfComment) {
		this.mcCabesCyclomaticComplexityPerLineOfComment = mcCabesCyclomaticComplexityPerLineOfComment;
	}

	public int getIF4() {
		return iF4;
	}

	public void setIF4(int if4) {
		iF4 = if4;
	}

	public float getIF4PerModule() {
		return iF4PerModule;
	}

	public void setIF4PerModule(float perModule) {
		iF4PerModule = perModule;
	}

	public int getIF4Visible() {
		return iF4Visible;
	}

	public void setIF4Visible(int visible) {
		iF4Visible = visible;
	}

	public float getIF4VisiblePerModule() {
		return iF4VisiblePerModule;
	}

	public void setIF4VisiblePerModule(float visiblePerModule) {
		iF4VisiblePerModule = visiblePerModule;
	}

	public int getIF4Concrete() {
		return iF4Concrete;
	}

	public void setIF4Concrete(int concrete) {
		iF4Concrete = concrete;
	}

	public float getIF4ConcretePerModule() {
		return iF4ConcretePerModule;
	}

	public void setIF4ConcretePerModule(float concretePerModule) {
		iF4ConcretePerModule = concretePerModule;
	}

	public int getRejectedLinesOfCode() {
		return rejectedLinesOfCode;
	}

	public void setRejectedLinesOfCode(int rejectedLinesOfCode) {
		this.rejectedLinesOfCode = rejectedLinesOfCode;
	}
	
	
 }
