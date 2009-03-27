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

public class ObjectOrientedDesignModule {

	
	private String name;
	
	private float weightedMethodsPerClassUnity;
	
	private float weightedMethodsPerClassVisibility;
	
	private float depthOfInheritanceTree;
	
	private float numberOfChildren;
	
	private float couplingBetweenObjects;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getWeightedMethodsPerClassUnity() {
		return weightedMethodsPerClassUnity;
	}

	public void setWeightedMethodsPerClassUnity(float weightedMethodsPerClassUnity) {
		this.weightedMethodsPerClassUnity = weightedMethodsPerClassUnity;
	}

	public float getWeightedMethodsPerClassVisibility() {
		return weightedMethodsPerClassVisibility;
	}

	public void setWeightedMethodsPerClassVisibility(
			float weightedMethodsPerClassVisibility) {
		this.weightedMethodsPerClassVisibility = weightedMethodsPerClassVisibility;
	}

	public float getDepthOfInheritanceTree() {
		return depthOfInheritanceTree;
	}

	public void setDepthOfInheritanceTree(float depthOfInheritanceTree) {
		this.depthOfInheritanceTree = depthOfInheritanceTree;
	}

	public float getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setNumberOfChildren(float numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

	public float getCouplingBetweenObjects() {
		return couplingBetweenObjects;
	}

	public void setCouplingBetweenObjects(float couplingBetweenObjects) {
		this.couplingBetweenObjects = couplingBetweenObjects;
	}
}
