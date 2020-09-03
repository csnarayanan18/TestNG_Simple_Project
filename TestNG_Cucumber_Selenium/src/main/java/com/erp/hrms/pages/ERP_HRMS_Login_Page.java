package com.erp.hrms.pages;

import org.openqa.selenium.WebElement;

import com.erp.hrms.utility.BaseUtilityPage;

public class ERP_HRMS_Login_Page extends BaseUtilityPage {
	
	public void ERP_HRMS_Login() throws Throwable {
		final String username = credentail_prop.getUsername();
		final String password = credentail_prop.getPassword();
		login(username, password);
	}

	protected void login(final String user, final String pass) throws Exception {
		WebElement username= driver.findElement(prop.getLocator("Username"));	
		username.click();
		username.sendKeys(user);
		
		WebElement password= driver.findElement(prop.getLocator("Password"));		
		password.sendKeys(pass);
		
		Thread.sleep(1000);
		WebElement login= driver.findElement(prop.getLocator("Login"));		
		login.click();
	}
}
