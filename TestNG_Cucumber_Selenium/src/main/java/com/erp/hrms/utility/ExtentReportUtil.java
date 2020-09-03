package com.erp.hrms.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportUtil extends BaseUtilityPage {

    String fileName = reportLocation + "\\extentreport.html";
    Scenario scenario;

    public void ExtentReport() {
        //First is to create Extent Reports
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
        sparkReporter.config().setReportName("ERP-HRMS Automation Execution Report");
        sparkReporter.config().setDocumentTitle("ERP-HRMS Automation Execution");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setTheme(Theme.STANDARD);
        extentReports.attachReporter(sparkReporter);
        
        
        //ExtentTest extentTest = new ExtentTest
       /* ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("ERP-HRMS Automation Execution");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("ERP-HRMS Automation Execution Report");
        extent.attachReporter(htmlReporter);*/
       }

    public void ExtentReportScreenshot() throws IOException {
    	String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    	File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(srcFile.toPath(), new File(reportLocation + "\\screenshot"+timestamp+".png").toPath());
        extentTest.fail("ScenarioFailed").addScreenCaptureFromPath(getBase64Screenshot(),"failed");
    }

    public void FlushReport(){
    	extentReports.flush();
    }
}
