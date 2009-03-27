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

public class StructuralSummaryModule {

	
	private String name;
	
	private float fanOutVisible;
	
	private float fanOutConcrete;
	
	private float fanOut;
	
	private float fanInVisible;
	
	private float fanInConcrete;
	
	private float fanIin;
	
	private float iF4Visible;
	
	private float iF4Concrete;
	
	private float iF4;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getFanOutVisible() {
		return fanOutVisible;
	}

	public void setFanOutVisible(float fanOutVisible) {
		this.fanOutVisible = fanOutVisible;
	}

	public float getFanOutConcrete() {
		return fanOutConcrete;
	}

	public void setFanOutConcrete(float fanOutConcrete) {
		this.fanOutConcrete = fanOutConcrete;
	}

	public float getFanOut() {
		return fanOut;
	}

	public void setFanOut(float fanOut) {
		this.fanOut = fanOut;
	}

	public float getFanInVisible() {
		return fanInVisible;
	}

	public void setFanInVisible(float fanInVisible) {
		this.fanInVisible = fanInVisible;
	}

	public float getFanInConcrete() {
		return fanInConcrete;
	}

	public void setFanInConcrete(float fanInConcrete) {
		this.fanInConcrete = fanInConcrete;
	}

	public float getFanIin() {
		return fanIin;
	}

	public void setFanIin(float fanIin) {
		this.fanIin = fanIin;
	}

	public float getIF4Visible() {
		return iF4Visible;
	}

	public void setIF4Visible(float visible) {
		iF4Visible = visible;
	}

	public float getIF4Concrete() {
		return iF4Concrete;
	}

	public void setIF4Concrete(float concrete) {
		iF4Concrete = concrete;
	}

	public float getIF4() {
		return iF4;
	}

	public void setIF4(float if4) {
		iF4 = if4;
	}	
}