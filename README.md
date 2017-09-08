# CCCC Plugin

## Description

This plug-in publishes CCCC results on Jenkins. This fork updates the original plug-in to work with the new pipeline system.

## Usage

Add this line to your Jenkinsfile.

'''
step([$class: 'CcccPublisher', metricFilePath: '.cccc/cccc.xml', runOnFailedBuild: false])
'''

Options:
* metricFilePath: relative path to the results.
* runOnFailedBuild: if true, will be performed even if there was a failure in the pipeline.

## Copyright notice

Copyright (c) 2009-2011 Thales Corporate Services SAS                        
Author : Gregory Boissinot                                                   
                                                                             
Copyright (c) 2017 CEA IRFU LILAS                                            
Developer : Guillaume Jorandon                                               
       
Permission is hereby granted, free of charge, to any person obtaining a copy 
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights 
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN 
THE SOFTWARE.
