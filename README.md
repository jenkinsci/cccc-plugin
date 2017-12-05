# CCCC Plugin

## Description

This plugin generates the trend report for CCCC (C and C++ Code Counter).

CCCC is a tool which analyzes C++ and Java files and generates a report on various metrics of the code. Metrics supported include lines of code, McCabe's complexity and metrics proposed by Chidamber&Kemerer and Henry&Kafura. 

## Project configuration

If you use a pipeline, add this line to your Jenkinsfile.

```
cccc metricFilePath: '.cccc/cccc.xml', runOnFailedBuild: false
```

Options:
* metricFilePath: relative path to the results.
* runOnFailedBuild: if true, will be performed even if there was a failure in the pipeline.

You can also use this plug-in a a classic freestyle job.
