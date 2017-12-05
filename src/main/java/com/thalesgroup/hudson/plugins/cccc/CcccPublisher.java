/*******************************************************************************
 * Copyright (c) 2009-2011 Thales Corporate Services SAS                        *
 * Author : Gregory Boissinot                                                   *
 *                                                                              *
 * Copyright (c) 2017 CEA IRFU LILAS                                            *
 * Developer : Guillaume Jorandon                                               *
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

import hudson.FilePath;
import hudson.Launcher;
import hudson.model.*;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Recorder;
import jenkins.tasks.SimpleBuildStep;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.Nonnull;
import java.io.PrintStream;
import java.io.Serializable;

public class CcccPublisher extends Recorder implements Serializable, SimpleBuildStep {

    private static final long serialVersionUID = 1L;

    private final String metricFilePath;

    private final boolean runOnFailedBuild;

    @DataBoundConstructor
    public CcccPublisher(String metricFilePath, boolean runOnFailedBuild) {
        this.metricFilePath = metricFilePath;
        this.runOnFailedBuild = runOnFailedBuild;
    }

    @Override
    public Action getProjectAction(AbstractProject<?, ?> project) {
        return new CcccProjectAction(project);
    }

    protected boolean canContinue(final Result result) {
        return result != Result.ABORTED && result != Result.FAILURE;
    }

    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.NONE;
    }

    @SuppressWarnings("unused")
    public String getMetricFilePath() {
        return metricFilePath;
    }

    @Override
    public void perform(@Nonnull Run<?, ?> run, @Nonnull FilePath filePath, @Nonnull Launcher launcher, @Nonnull TaskListener taskListener) {

        if (this.canContinue(run.getResult()) || runOnFailedBuild) {

            taskListener.getLogger().println("Parsing cccc results");

            PrintStream logger = taskListener.getLogger();

            FilePath metricFile = new FilePath(filePath, metricFilePath);
            CcccReport report;
            try {
                if (!metricFile.exists()) {
                    taskListener.getLogger().println(String.format("The given '%s' metric path doesn't exist.", metricFilePath));
                    run.setResult(Result.FAILURE);
                }
                CccccParser parser = new CccccParser(metricFile);
                report = filePath.act(parser);

            } catch (Throwable e) {
                e.printStackTrace(logger);
                run.setResult(Result.FAILURE);
                return;
            }

            CcccResult result = new CcccResult(report, run);
            CcccBuildAction buildAction = new CcccBuildAction(run, result);
            run.addAction(buildAction);
            taskListener.getLogger().println("End Processing cccc results");
        }
        run.setResult(Result.SUCCESS);
    }

    public boolean getRunOnFailedBuild() {
        return this.runOnFailedBuild;
    }
}
