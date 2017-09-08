/*******************************************************************************
 * Copyright (c) 2009-2011 Thales Corporate Services SAS                        *
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

import hudson.FilePath;
import hudson.Launcher;
import hudson.model.*;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Recorder;
import jenkins.tasks.SimpleBuildStep;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;

// TODO : Add a workflow step to allow the plugin to work alongside with the new Pipeline system of Jenkins ( https://github.com/jenkinsci/workflow-step-api-plugin )

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

    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) {

        if (this.canContinue(build.getResult()) || runOnFailedBuild) {

            listener.getLogger().println("Parsing cccc results");

            FilePath workspace = build.getWorkspace();
            PrintStream logger = listener.getLogger();

            FilePath metricFile = new FilePath(build.getWorkspace(), metricFilePath);
            CcccReport report;
            try {
                if (!metricFile.exists()) {
                    listener.getLogger().println(String.format("The given '%s' metric path doesn't exist.", metricFilePath));
                    build.setResult(Result.FAILURE);
                    return false;
                }
                CccccParser parser = new CccccParser(metricFile);
                report = workspace.act(parser);

            } catch (IOException ioe) {
                ioe.printStackTrace(logger);
                build.setResult(Result.FAILURE);
                return false;

            } catch (InterruptedException ie) {
                ie.printStackTrace(logger);
                build.setResult(Result.FAILURE);
                return false;
            } catch (Throwable t) {
                t.printStackTrace(logger);
                build.setResult(Result.FAILURE);
                return false;
            }

            CcccResult result = new CcccResult(report, build);
            CcccBuildAction buildAction = new CcccBuildAction(build, result);
            build.addAction(buildAction);
            listener.getLogger().println("End Processing cccc results");
        }
        build.setResult(Result.SUCCESS);
        return true;
    }

    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.NONE;
    }

    @SuppressWarnings("unused")
    public String getMetricFilePath() {
        return metricFilePath;
    }

    @Override
    public void perform(@Nonnull Run<?, ?> run, @Nonnull FilePath filePath, @Nonnull Launcher launcher, @Nonnull TaskListener taskListener) throws InterruptedException, IOException {
        taskListener.getLogger().println("If it works I'll become vegan.");
        taskListener.getLogger().println(this.metricFilePath);
    }
}
