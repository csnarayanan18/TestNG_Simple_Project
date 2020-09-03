package com.erp.hrms.stepdefinitions;

import static org.testng.Assert.fail;
import com.erp.hrms.utility.BaseUtilityPage;
import io.cucumber.java.en.Given;

public class ERP_HRMS_Request extends BaseUtilityPage {
	
	@Given("User able to Launch Google")
	public void user_able_to_launch_google() throws Exception {
		System.out.println(driver.getTitle());
	    
	    Thread.sleep(10000);
	    
	    if(!driver.getTitle().equals("Meet Guru99 - Free Training Tutorials & Video for IT Courses")) {
			 failure_screenShot("Login_Failure", "Test"); 
			 fail("Login Launch Failed"); 
			 }
	    full_page_screenshot("Google Page", "Full_page");
	}
}
