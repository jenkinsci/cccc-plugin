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

import java.io.Serializable;

public class OtherExtentsRejectedExtend implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	
	private String sourceReference;
	
	private int sourceReferenceLine;
	
	private int linesOfCode;
	
	private int linesOfComment;
	
	private String mcCabesCyclomaticComplexity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSourceReference() {
		return sourceReference;
	}

	public void setSourceReference(String sourceReference) {
		this.sourceReference = sourceReference;
	}

	public int getSourceReferenceLine() {
		return sourceReferenceLine;
	}

	public void setSourceReferenceLine(int sourceReferenceLine) {
		this.sourceReferenceLine = sourceReferenceLine;
	}

	public int getLinesOfCode() {
		return linesOfCode;
	}

	public void setLinesOfCode(int linesOfCode) {
		this.linesOfCode = linesOfCode;
	}

	public int getLinesOfComment() {
		return linesOfComment;
	}

	public void setLinesOfComment(int linesOfComment) {
		this.linesOfComment = linesOfComment;
	}

	public String getMcCabesCyclomaticComplexity() {
		return mcCabesCyclomaticComplexity;
	}

	public void setMcCabesCyclomaticComplexity(String mcCabesCyclomaticComplexity) {
		this.mcCabesCyclomaticComplexity = mcCabesCyclomaticComplexity;
	}
	
	
	
}
