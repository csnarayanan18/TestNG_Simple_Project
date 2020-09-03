package com.erp.hrms.testrunner;

import org.testng.annotations.DataProvider;

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
/*@ExtendedCucumberOptions(jsonReport = "target/cucumber.json",
        retryCount = 1,
        detailedReport = true,
        detailedAggregatedReport = true,
        overviewReport = true,
        coverageReport = true,
        jsonUsageReport = "target/cucumber-usage.json",
        usageReport = true,
        toPDF = true,
        outputFolder = "target/Reports/")*/

@CucumberOptions(features = { "src/test/java/com/erp/hrms/feature" }, 
				glue = {"com.erp.hrms.stepdefinitions" }, 
				plugin = { "pretty",
				"html:target/cucumber-html-report.html", 
				"json:target/cucumber.json",
				"pretty:target/cucumber-pretty.txt", 
				"usage:target/cucumber-usage.json",
				"junit:target/cucumber-results.xml",
				"rerun:target/rerun.txt" }, 
				tags = ("@ERPHRMS"), 
				monochrome = true, 
				dryRun = false)
public class ERP_HRMS_Runner extends AbstractTestNGCucumberTests {

	@DataProvider
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
