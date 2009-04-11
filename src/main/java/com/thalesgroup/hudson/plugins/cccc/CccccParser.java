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

import hudson.AbortException;
import hudson.FilePath;
import hudson.remoting.VirtualChannel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.thalesgroup.hudson.plugins.cccc.model.ObjectOrientedDesignModule;
import com.thalesgroup.hudson.plugins.cccc.model.OtherExtentsRejectedExtend;
import com.thalesgroup.hudson.plugins.cccc.model.ProceduralSummaryModule;
import com.thalesgroup.hudson.plugins.cccc.model.ProjectSummary;
import com.thalesgroup.hudson.plugins.cccc.model.StructuralSummaryModule;



public class CccccParser implements FilePath.FileCallable<CcccReport> {

    private final FilePath resultFilePath;
    private PrintStream logger;

    public CccccParser(FilePath resultFilePath, PrintStream logger){
        this.logger = logger;
        this.resultFilePath = resultFilePath;
    }

    public CcccReport invoke(java.io.File workspace, VirtualChannel channel) throws IOException {
        CcccReport ccccReport = new CcccReport();
        
        Document document = null;
        try {
            SAXBuilder sxb = new SAXBuilder();
            document = sxb.build(new InputStreamReader(new  FileInputStream(new File(resultFilePath.toURI())), "UTF-8"));
        }
        catch (Exception e) {
        	logger.println("Error = " + e.toString());
        	throw new AbortException();
        }
        
		Element root = document.getRootElement();
		
		Element  projectSummaryElt = root.getChild("project_summary");		
		if (projectSummaryElt!=null){
			logger.println("Process Project Summary element.");
			ProjectSummary projectSummary = fillSummaryProject(projectSummaryElt);
			ccccReport.setProjectSummary(projectSummary);
		}
				
		Element  proceduralSummaryElt = root.getChild("procedural_summary");	
		if (proceduralSummaryElt!=null){
			logger.println("Process Procedural Summary element.");
			List<ProceduralSummaryModule> proceduralSummaryModuleList = fillProceduralSummary(proceduralSummaryElt);
			ccccReport.setProceduralSummaryModuleList(proceduralSummaryModuleList);
		}
		
		Element  ooDesignElt = root.getChild("oo_design");	
		if (ooDesignElt!=null){
			logger.println("Process Object Oriented Design element.");
			List<ObjectOrientedDesignModule> ooDesignEltList = fillObjectOrientedDesign(ooDesignElt);
			ccccReport.setObjectOrientedDesignModuleList(ooDesignEltList);
		}
	
		Element structuralSummaryElt = root.getChild("structural_summary");
		if (structuralSummaryElt!=null){
			logger.println("Process Structural Summary element.");
			List<StructuralSummaryModule> structuralSummaryModuleList = fillStructuralSummary(structuralSummaryElt);
			ccccReport.setStructuralSummaryModuleList(structuralSummaryModuleList);
		}
		
		Element otherExtentsElt = root.getChild("other_extents");
		if (otherExtentsElt!=null){
			logger.println("Process Other Extents element.");
			List<OtherExtentsRejectedExtend> otherExtentsRejectedExtendList = fillOtherExtentsRejectedExtend(otherExtentsElt);
			ccccReport.setOtherExtentsRejectedExtendList(otherExtentsRejectedExtendList);
		}

        return ccccReport;
    }
    
    private String  getValue(Element rootElement, String eletLine){
		Element lineElement =  rootElement.getChild(eletLine);
		return (lineElement!=null)?lineElement.getAttribute("value").getValue():"";
    }
    

	
	private ProjectSummary fillSummaryProject(Element projectSummaryElt){
		
		ProjectSummary projectSummary = new ProjectSummary();
		
		projectSummary.setNbModules(Integer.valueOf(getValue(projectSummaryElt,"number_of_modules")));
		projectSummary.setLinesOfCode(Integer.valueOf(getValue(projectSummaryElt,"lines_of_code")));
		projectSummary.setLinesOfCodePerModule(Float.valueOf(getValue(projectSummaryElt,"lines_of_code_per_module")));
		projectSummary.setMcCabesCyclomaticComplexity(Integer.valueOf(getValue(projectSummaryElt,"McCabes_cyclomatic_complexity")));
		projectSummary.setMcCabesCyclomaticComplexityPerModule(Float.valueOf(getValue(projectSummaryElt,"McCabes_cyclomatic_complexity_per_module")));
		
		projectSummary.setLinesOfComment(Integer.valueOf(getValue(projectSummaryElt,"lines_of_comment")));
		projectSummary.setLinesOfCommentPerModule(Float.valueOf(getValue(projectSummaryElt,"lines_of_comment_per_module")));
		projectSummary.setLinesOfCodePerLineOfComment(getValue(projectSummaryElt,"lines_of_code_per_line_of_comment"));
		projectSummary.setMcCabesCyclomaticComplexityPerLineOfComment(getValue(projectSummaryElt,"McCabes_cyclomatic_complexity_per_line_of_comment"));
		
		projectSummary.setIF4(Integer.valueOf(getValue(projectSummaryElt,"IF4")));
		projectSummary.setIF4PerModule(Float.valueOf(getValue(projectSummaryElt,"IF4_per_module")));
		projectSummary.setIF4Visible(Integer.valueOf(getValue(projectSummaryElt,"IF4_visible")));
		projectSummary.setIF4PerModule(Float.valueOf(getValue(projectSummaryElt,"IF4_visible_per_module")));
		projectSummary.setRejectedLinesOfCode(Integer.valueOf(getValue(projectSummaryElt,"rejected_lines_of_code")));
		
		return projectSummary;
	}

	
	private List<ProceduralSummaryModule> fillProceduralSummary(Element proceduralSummaryElt){
		
		List<ProceduralSummaryModule> proceduralSummaryModuleList = new ArrayList<ProceduralSummaryModule>();
		
		List<Element> modules = proceduralSummaryElt.getChildren("module");
		for (Element moduleElt:modules){
			ProceduralSummaryModule proceduralSummaryModule = new ProceduralSummaryModule();
			proceduralSummaryModule.setName(moduleElt.getChildText("name"));
			proceduralSummaryModule.setLinesOfCode(Integer.valueOf(getValue(moduleElt,"lines_of_code")));
			proceduralSummaryModule.setMcCabesCyclomaticComplexity(Float.valueOf(getValue(moduleElt,"McCabes_cyclomatic_complexity")));			
			proceduralSummaryModule.setLinesOfComment(Integer.valueOf(getValue(moduleElt,"lines_of_comment")));
			proceduralSummaryModule.setLinesOfCodePerLineOfComment(getValue(moduleElt,"lines_of_code_per_line_of_comment"));
			proceduralSummaryModule.setMcCabesCyclomaticComplexityPerLineOfComment(getValue(moduleElt,"McCabes_cyclomatic_complexity_per_line_of_comment"));
			proceduralSummaryModuleList.add(proceduralSummaryModule);
		}
		return proceduralSummaryModuleList;
	}

	
	private List<ObjectOrientedDesignModule> fillObjectOrientedDesign(Element ooDesignElt){
		List<ObjectOrientedDesignModule> objectOrientedDesignModuleList = new ArrayList<ObjectOrientedDesignModule>();
		
		List<Element> modules = ooDesignElt.getChildren("module");
		for (Element moduleElt:modules){
			ObjectOrientedDesignModule objectOrientedDesignModule = new ObjectOrientedDesignModule();
			objectOrientedDesignModule.setName(moduleElt.getChildText("name"));
			objectOrientedDesignModule.setWeightedMethodsPerClassUnity(Integer.valueOf(getValue(moduleElt,"weighted_methods_per_class_unity")));
			objectOrientedDesignModule.setWeightedMethodsPerClassVisibility(Integer.valueOf(getValue(moduleElt,"weighted_methods_per_class_visibility")));
			objectOrientedDesignModule.setDepthOfInheritanceTree(Integer.valueOf(getValue(moduleElt,"depth_of_inheritance_tree")));
			objectOrientedDesignModule.setNumberOfChildren(Integer.valueOf(getValue(moduleElt,"number_of_children")));
			objectOrientedDesignModule.setCouplingBetweenObjects(Integer.valueOf(getValue(moduleElt,"coupling_between_objects")));			
			objectOrientedDesignModuleList.add(objectOrientedDesignModule);
		}		
		return objectOrientedDesignModuleList;
	}
	
	
	private List<StructuralSummaryModule> fillStructuralSummary(Element structuralSummaryElt){
		List<StructuralSummaryModule> structuralSummaryModuleList = new ArrayList<StructuralSummaryModule>();
		
		List<Element> modules = structuralSummaryElt.getChildren("module");
		for (Element moduleElt:modules){
			StructuralSummaryModule structuralSummaryModule = new StructuralSummaryModule();
			structuralSummaryModule.setName(moduleElt.getChildText("name"));
			structuralSummaryModule.setFanOutVisible(Float.valueOf(getValue(moduleElt,"fan_out_visible")));
			structuralSummaryModule.setFanOutConcrete(Float.valueOf(getValue(moduleElt,"fan_out_concrete")));
			structuralSummaryModule.setFanOut(Float.valueOf(getValue(moduleElt,"fan_out")));
			structuralSummaryModule.setFanInVisible(Float.valueOf(getValue(moduleElt,"fan_in_visible")));
			structuralSummaryModule.setFanInConcrete(Float.valueOf(getValue(moduleElt,"fan_in_concrete")));
			structuralSummaryModule.setFanIin(Float.valueOf(getValue(moduleElt,"fan_in")));
			structuralSummaryModule.setIF4Visible(Float.valueOf(getValue(moduleElt,"IF4_visible")));
			structuralSummaryModule.setIF4Concrete(Float.valueOf(getValue(moduleElt,"IF4_concrete")));
			structuralSummaryModule.setIF4(Float.valueOf(getValue(moduleElt,"IF4")));
			
			
			structuralSummaryModuleList.add(structuralSummaryModule);
		}		
		return structuralSummaryModuleList;
	}
	
	private List<OtherExtentsRejectedExtend> fillOtherExtentsRejectedExtend(Element otherExtentsRejectedExtendElt){
		List<OtherExtentsRejectedExtend> otherExtentsRejectedExtendList = new ArrayList<OtherExtentsRejectedExtend>();
		
		List<Element> rejectedExtentList = otherExtentsRejectedExtendElt.getChildren("rejected_extent");
		for (Element rejectedExtentElt:rejectedExtentList){
			OtherExtentsRejectedExtend otherExtendsRejectedExtend = new OtherExtentsRejectedExtend();
			otherExtendsRejectedExtend.setName(rejectedExtentElt.getChildText("name"));
			
			Element sourceReferenceElt =  rejectedExtentElt.getChild("source_reference");	    	
			otherExtendsRejectedExtend.setSourceReference(sourceReferenceElt.getAttribute("file").getValue());
			otherExtendsRejectedExtend.setSourceReferenceLine(Integer.valueOf(sourceReferenceElt.getAttribute("line").getValue()));
			
			otherExtendsRejectedExtend.setLinesOfCode(Integer.valueOf(getValue(rejectedExtentElt,"lines_of_code")));
			otherExtendsRejectedExtend.setLinesOfComment(Integer.valueOf(getValue(rejectedExtentElt,"lines_of_comment")));
			otherExtendsRejectedExtend.setMcCabesCyclomaticComplexity(getValue(rejectedExtentElt,"McCabes_cyclomatic_complexity"));
			
			otherExtentsRejectedExtendList.add(otherExtendsRejectedExtend);
		}		
		return otherExtentsRejectedExtendList;
	}
    
}
