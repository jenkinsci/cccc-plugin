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

import hudson.util.DataSetBuilder;
import hudson.util.ShiftedCategoryAxis;
import hudson.util.ChartUtil.NumberOnlyBuildLabel;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.DefaultCategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RectangleInsets;

import com.thalesgroup.hudson.plugins.cccc.model.ProjectSummary;


public class CcccChartBuilder {

    private CcccChartBuilder(){
        super();
    }

    public static JFreeChart buildChart(CcccBuildAction action){
        JFreeChart chart = ChartFactory.createStackedAreaChart(null, null, "Number of modules", buildDataset(action), PlotOrientation.VERTICAL, true, false, true);

        chart.setBackgroundPaint(Color.white);


        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlinePaint(null);
        plot.setForegroundAlpha(0.8f);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.black);

        CategoryAxis domainAxis = new ShiftedCategoryAxis(null);
        plot.setDomainAxis(domainAxis);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        domainAxis.setCategoryMargin(0.0);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // crop extra space around the graph
        plot.setInsets(new RectangleInsets(0, 0, 0, 5.0));

        
        CategoryItemRenderer firstRender= new DefaultCategoryItemRenderer();
        CcccAreaRenderer renderer = new CcccAreaRenderer(action.getUrlName());
        plot.setRenderer(firstRender); 
        

        //Second
        NumberAxis axis2= new NumberAxis("Lines of Code");
        axis2.setLabelPaint(Color.BLUE);
        axis2.setAxisLinePaint(Color.BLUE);
        axis2.setTickLabelPaint(Color.BLUE);        
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setRangeAxis(1, axis2);        
        //CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
        categoryPlot.setDataset(1, buildDataset2(action));
        categoryPlot.mapDatasetToRangeAxis(1,1);
        CategoryItemRenderer rendu= new DefaultCategoryItemRenderer();        
        rendu.setPaint(Color.BLUE);
        categoryPlot.setRenderer(1,rendu);

        
        //Third
        NumberAxis axis3= new NumberAxis("McCabe's Cyclomatic Number");
        axis3.setLabelPaint(Color.GREEN);
        axis3.setAxisLinePaint(Color.GREEN);
        axis3.setTickLabelPaint(Color.GREEN);        
        CategoryPlot categoryPlot3 = chart.getCategoryPlot();
        categoryPlot3.setRangeAxis(2, axis3);  
        categoryPlot3.setDataset(2, buildDataset3(action));
        categoryPlot3.mapDatasetToRangeAxis(2,2);
        categoryPlot3.mapDatasetToDomainAxis(2,0);
        CategoryItemRenderer rendu3= new DefaultCategoryItemRenderer();        
        rendu3.setPaint(Color.GREEN);
        categoryPlot3.setRenderer(2, rendu3);

        return chart;
    }

    private static CategoryDataset buildDataset(CcccBuildAction lastAction){
        DataSetBuilder<String, NumberOnlyBuildLabel> builder = new DataSetBuilder<String, NumberOnlyBuildLabel>();

        CcccBuildAction action = lastAction;
        do{
            CcccResult result = action.getResult();
            if(result != null){
                CcccReport report = result.getReport();
                NumberOnlyBuildLabel buildLabel = new NumberOnlyBuildLabel(action.getBuild());
                
                try{
                	Class projectSummaryClass = ProjectSummary.class;
                	Method method = projectSummaryClass.getMethod("nbNodules", null);
                	Number n = (Number)method.invoke(report.getStructuralSummaryModuleList());
                }
                catch (NoSuchMethodException nsm){
                	
                }
                catch (IllegalAccessException nsm){
                	
                }   
                catch (InvocationTargetException ite){
                	
                }  
                builder.add(report.getProjectSummary().getNbModules(), "Number of module", buildLabel);               
            }
            action = action.getPreviousAction();
        }while(action != null);

        return builder.build();
    }
    
    private static CategoryDataset buildDataset2(CcccBuildAction lastAction){
        DataSetBuilder<String, NumberOnlyBuildLabel> builder = new DataSetBuilder<String, NumberOnlyBuildLabel>();

        CcccBuildAction action = lastAction;
        do{
            CcccResult result = action.getResult();
            if(result != null){
                CcccReport report = result.getReport();
                NumberOnlyBuildLabel buildLabel = new NumberOnlyBuildLabel(action.getBuild());
                builder.add(report.getProjectSummary().getLinesOfCode(), "Lines of Code", buildLabel);               
            }
            action = action.getPreviousAction();
        }while(action != null);

        return builder.build();
    }
    
    private static CategoryDataset buildDataset3(CcccBuildAction lastAction){
        DataSetBuilder<String, NumberOnlyBuildLabel> builder = new DataSetBuilder<String, NumberOnlyBuildLabel>();

        CcccBuildAction action = lastAction;
        do{
            CcccResult result = action.getResult();
            if(result != null){
                CcccReport report = result.getReport();
                NumberOnlyBuildLabel buildLabel = new NumberOnlyBuildLabel(action.getBuild());
                builder.add(report.getProjectSummary().getMcCabesCyclomaticComplexity(), "McCabe's Cyclomatic Number", buildLabel);               
            }
            action = action.getPreviousAction();
        }while(action != null);

        return builder.build();
    }    
    
}
