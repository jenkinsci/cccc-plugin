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

import com.thalesgroup.hudson.plugins.cccc.model.ProjectSummary;


public class CcccSummary {

    private CcccSummary(){
        super();
    }

    public static String createReportSummary(CcccReport report, CcccReport previous){
        StringBuilder builder = new StringBuilder();
        builder.append("<a href=\"" + CcccBuildAction.URL_NAME + "\">Cccc Results</a>");
        builder.append("\n");
        return builder.toString();
    }

    public static String createReportSummaryDetails(CcccReport report, CcccReport previousReport){

    	ProjectSummary previous =  null;
    	StringBuilder builder = new StringBuilder();
        ProjectSummary summary = report.getProjectSummary();        
        
        if (previousReport!=null){
        	previous =  previousReport.projectSummary;
    	}
    
        builder.append("<li>");               
        builder.append("Number of modules :");
        builder.append(summary.getNbModules());
        if(previous != null){
            printDifference(summary.getNbModules(), previous.getNbModules(), builder);
        }
        builder.append("</li>");
        
        builder.append("<li>");               
        builder.append("Lines of code :");
        builder.append(summary.getLinesOfCode());
        if(previous != null){
            printDifference(summary.getLinesOfCode(), previous.getLinesOfCode(), builder);
        }
        builder.append("</li>");        
        
        builder.append("<li>");               
        builder.append("McCabe's Cyclomatic Number :");
        builder.append(summary.getMcCabesCyclomaticComplexity());
        if(previous != null){
            printDifference(summary.getMcCabesCyclomaticComplexity(), previous.getMcCabesCyclomaticComplexity(), builder);
        }
        builder.append("</li>");                
        

        builder.append("<li>");               
        builder.append("Lines of Comment :");
        builder.append(summary.getLinesOfComment());
        if(previous != null){
            printDifference(summary.getLinesOfComment(), previous.getLinesOfComment(), builder);
        }
        builder.append("</li>");                
        
        

        builder.append("<li>");               
        builder.append("LOC/COM :");
        builder.append(summary.getLinesOfCode());
        if(previous != null){
            printDifference(summary.getLinesOfCode(), previous.getLinesOfCode(), builder);
        }
        builder.append("</li>");                      
        
        builder.append("<li>");               
        builder.append("MVG/COM :");
        builder.append(summary.getMcCabesCyclomaticComplexityPerLineOfComment());
        if(previous != null){
            builder.append("(not available)");
        }
        builder.append("</li>");   
        
        
        builder.append("<li>");               
        builder.append("Information Flow measure ( inclusive ) :");
        builder.append(summary.getIF4());
        if(previous != null){
            printDifference(summary.getIF4(), previous.getIF4(), builder);
        }
        builder.append("</li>");             
        
        
        builder.append("<li>");               
        builder.append("Information Flow measure ( visible ) :");
        builder.append(summary.getIF4Visible());
        if(previous != null){
            printDifference(summary.getIF4Visible(), previous.getIF4Visible(), builder);
        }
        builder.append("</li>");               
        
        
        builder.append("<li>");               
        builder.append("Information Flow measure ( concrete ) :");
        builder.append(summary.getIF4Concrete());
        if(previous != null){
            printDifference(summary.getIF4Concrete(), previous.getIF4Concrete(), builder);
        }
        builder.append("</li>");  
        
        builder.append("<li>");               
        builder.append("Lines of Code rejected by parser :");
        builder.append(summary.getRejectedLinesOfCode());
        if(previous != null){
            printDifference(summary.getRejectedLinesOfCode(), previous.getRejectedLinesOfCode(), builder);
        }
        builder.append("</li>");  
                
        
        return builder.toString();
    }

    private static void printDifference(float current, float previous, StringBuilder builder){
        float difference = current - previous;
        builder.append(" (");

        if(difference >= 0){
            builder.append('+');
        }
        builder.append(difference);
        builder.append(")");
    }
}
