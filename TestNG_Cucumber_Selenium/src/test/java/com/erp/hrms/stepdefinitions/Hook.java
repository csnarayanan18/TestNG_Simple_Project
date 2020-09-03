package com.erp.hrms.stepdefinitions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.erp.hrms.utility.BaseUtilityPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hook extends BaseUtilityPage{
	
	public ExtentSparkReporter htmlReporter;
	public   ExtentReports extent;
	Scenario scenario;
	public ExtentTest logger;

	
	@Before
    public void before(Scenario scenario) throws InterruptedException, IOException
    {
		
        System.out.println("Running Scenario: "+scenario.getName());
        //init_ie_browser();
        init_chrome_browser();
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	    driver.get(credentail_prop.getURL());
	   
	    
	}
 
    @After
    public void after(Scenario scenario) throws Exception
    {
    	
    	if (scenario.isFailed()) {
            //Take screenshot logic goes here
            System.out.println(scenario.getName());
        }
        System.out.println("Closing the browser");

        driver.close();
        driver.quit();
    }
}
