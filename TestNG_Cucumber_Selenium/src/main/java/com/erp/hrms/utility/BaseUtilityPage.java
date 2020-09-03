package com.erp.hrms.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.erp.hrms.object.repositories.Credential_PropertiesUtils;
import com.erp.hrms.object.repositories.ObjectRepoPropertyUtils;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;

/*BasePage class is used as utility class and it has the functions to initialize the driver, select drop down values, 
 * select radio button values, sending the values to text box, next & save button Click and screenshots */

public class BaseUtilityPage {

	public static WebDriver driver;
	public static List<HashMap<String, String>> datamap;
	protected static ObjectRepoPropertyUtils prop;
	protected static Credential_PropertiesUtils credentail_prop;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	public static ExtentTest features;
	public static String reportLocation = System.getProperty("user.dir")+"\\ExtentReport";
	public static Actions action;
	public static String screenshotdir = System.getProperty("user.dir") + "\\ExtentReport\\screenshots\\";

	/* init() function used to initialize the driver */
	/*
	 * public static void init_ie_browser() throws InterruptedException {
	 * System.setProperty("webdriver.ie.driver", "drivers/IEDriver.exe");
	 * InternetExplorerOptions options = new InternetExplorerOptions();
	 * options.destructivelyEnsureCleanSession(); driver = new
	 * InternetExplorerDriver(options); }
	 */
	public static void init_chrome_browser() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
	}

	public BaseUtilityPage() {
		prop = new ObjectRepoPropertyUtils();
		credentail_prop = new Credential_PropertiesUtils();
	}

	/* select_dropdown_value() function used to select the drop down values */
	protected void select_menu_value(By xpath, String value) {
		List<WebElement> menuvalues = driver.findElements(xpath);

		for (int i = 0; i < menuvalues.size(); i++) {
			if (menuvalues.get(i).getText().equals(value)) {
				menuvalues.get(i).click();
				break;
			}			
		}
	}
	
	
	/*send_textbox_value() function used to send the values to text box*/
	protected void send_textbox_value(By xpath, String excel_value) throws InterruptedException {
		driver.findElement(xpath).clear();
		/*((JavascriptExecutor)driver).executeScript("arguments[0].value='"+ excel_value +"';", 
				driver.findElement(xpath));*/
		driver.findElement(xpath).sendKeys(excel_value);
	}
	
	/*select_dropdown_value() function used to select the drop down values*/
	protected void dropdown_select(By xpath, String excel_value) {
		Select ddvalue = new Select(driver.findElement(xpath));
		ddvalue.selectByVisibleText(excel_value);
	}

	public void logout() throws Exception {
		driver.findElement(prop.getLocator("LogoutButton")).click();
	}
	
	public static String getBase64Screenshot() throws IOException 
	{
		String Base64StringOfScreenshot="";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		    
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYY_HHmmss");
		String sDate = sdf.format(date);
		FileUtils.copyFile(src, new File(screenshotdir + "image_" + sDate + ".png"));
		    
		byte[] fileContent = FileUtils.readFileToByteArray(src);
		Base64StringOfScreenshot = "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);
		return Base64StringOfScreenshot;
	}

	/* screenShot() function used to take the IE Browser screenshots */
	public void screenShot(String ScenarioName, String PageName) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		String SimpleDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		File mainfolder = new File(currentDir + "\\Screenshots");
		if (!mainfolder.exists() && !mainfolder.isDirectory()) {
			mainfolder.mkdir();
		}
		File folder = new File(mainfolder + "\\" + SimpleDate);
		if (!folder.exists() && !folder.isDirectory()) {
			folder.mkdir();
		}
		File innerfolder = new File(folder + "\\" + ScenarioName);
		if (!innerfolder.exists() && !innerfolder.isDirectory()) {
			innerfolder.mkdir();
		}
		File pagefolder = new File(innerfolder + "\\" + PageName);
		if (!pagefolder.exists() && !pagefolder.isDirectory()) {
			pagefolder.mkdir();
		}

		File dstFile = new File(pagefolder + "\\" + getClass().getSimpleName() + "_" + timestamp + ".png");
		FileUtils.copyFile(srcFile, dstFile);

	}
	
	/*failure screenShot() function used to take the screenshots*/
	public void failure_screenShot(String ScenarioName,String PageName) throws IOException {
		
		Screenshot screenshot = new AShot().shootingStrategy(new ViewportPastingStrategy(100)).takeScreenshot(driver);
		String currentDir = System.getProperty("user.dir");
		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		String SimpleDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		File mainfolder = new File(currentDir + "\\Failure_Screenshots");
		if (!mainfolder.exists() && !mainfolder.isDirectory()) {
			mainfolder.mkdir();
		}
		File folder = new File(mainfolder + "\\" + SimpleDate);
		if (!folder.exists() && !folder.isDirectory()) {
			folder.mkdir();
		}
		File innerfolder = new File(folder + "\\" + ScenarioName);
		if (!innerfolder.exists() && !innerfolder.isDirectory()) {
			innerfolder.mkdir();
		}
		File pagefolder = new File(innerfolder + "\\" + PageName);
		if (!pagefolder.exists() && !pagefolder.isDirectory()) {
			pagefolder.mkdir();
		}

		File dstFile = new File(pagefolder + "\\" + getClass().getSimpleName() + "_" + timestamp + ".png");

		ImageIO.write(screenshot.getImage(), "png", dstFile);
	}

	// take screenshot of the entire page
	public void full_page_screenshot(String ScenarioName, String PageName) throws Exception {
		// Screenshot screenshot=new AShot().takeScreenshot(driver);
		Screenshot screenshot = new AShot().shootingStrategy(new ViewportPastingStrategy(100)).takeScreenshot(driver);
		String currentDir = System.getProperty("user.dir");
		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		String SimpleDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		File mainfolder = new File(currentDir + "\\Screenshots");
		if (!mainfolder.exists() && !mainfolder.isDirectory()) {
			mainfolder.mkdir();
		}
		File folder = new File(mainfolder + "\\" + SimpleDate);
		if (!folder.exists() && !folder.isDirectory()) {
			folder.mkdir();
		}

		File innerfolder = new File(folder + "\\" + ScenarioName);
		if (!innerfolder.exists() && !innerfolder.isDirectory()) {
			innerfolder.mkdir();
		}
		File pagefolder = new File(innerfolder + "\\" + PageName);
		if (!pagefolder.exists() && !pagefolder.isDirectory()) {
			pagefolder.mkdir();
		}

		File dstFile = new File(pagefolder + "\\" + getClass().getSimpleName() + "_" + timestamp + ".png");

		ImageIO.write(screenshot.getImage(), "png", dstFile);
	}
}
