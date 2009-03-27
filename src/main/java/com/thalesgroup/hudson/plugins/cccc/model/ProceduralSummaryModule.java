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

public class ProceduralSummaryModule {


	private String name;
	
	private int linesOfCode;
	
	private float mcCabesCyclomaticComplexity;
	
	private int linesOfComment;
	
	private String linesOfCodePerLineOfComment;
	
	private String mcCabesCyclomaticComplexityPerLineOfComment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLinesOfCode() {
		return linesOfCode;
	}

	public void setLinesOfCode(int linesOfCode) {
		this.linesOfCode = linesOfCode;
	}

	public float getMcCabesCyclomaticComplexity() {
		return mcCabesCyclomaticComplexity;
	}

	public void setMcCabesCyclomaticComplexity(float mcCabesCyclomaticComplexity) {
		this.mcCabesCyclomaticComplexity = mcCabesCyclomaticComplexity;
	}

	public int getLinesOfComment() {
		return linesOfComment;
	}

	public void setLinesOfComment(int linesOfComment) {
		this.linesOfComment = linesOfComment;
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

	
}
