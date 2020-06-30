package com.selenium.library;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/***
 * 
 * @author Armin Date: 02/29/2020
 */
public class Base {
	final static Logger logger = Logger.getLogger(Base.class);

	public static WebDriver driver;
	public static GlobalSeleniumLibrary selLib;
	private String myBrowser;
	private String myDemoMode;
	private String mySendEmail;
	private String myIsRemote;

	@BeforeMethod 
	public void set_up() {
		try {
			logger.info("Start a single test...");

			if (myDemoMode.toLowerCase().contains("on")) {
				selLib.setDemoMode(true);
			}
			if (myIsRemote.contains("true")) {
				driver = selLib.startRemoteBrowser("http://192.168.1.228:4444/wd/hub/", myBrowser);
			} else {
				driver = selLib.startBrowser(myBrowser);
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	@AfterMethod 
	public void tearDown(ITestResult result) {
		try {
			logger.info("End a single test...");
			if (ITestResult.FAILURE == result.getStatus()) {
			
				selLib.screenCapture(result.getName());
			}
			
			if (driver != null) {
				Thread.sleep(4 * 1000);
				driver.close();
			}
			Thread.sleep(5 * 1000);
			// if(driver.getWindowHandles().size() > 0) {
			// driver.close(); // closing the browser
			// }
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	@AfterClass 
	public void afterAllTests() {
		try {
			if (driver != null) {
				// driver.quit();
			}
			logger.info("All the test is completed...");

			
			EmailManager email = new EmailManager();
			email.toAddress = "arminfarshchi@yahoo.com;arminfarshchi@gmail.com";

			selLib.automaticallyAttachErrorImgToEmail();
			selLib.errorScreenshots.add("target/logs/log4j-selenium.log");
			selLib.errorScreenshots.add("target/logs/Selenium-Report.html");
			selLib.errorScreenshots.add("test-output/Extent.html");
			if (mySendEmail.contains("true")) {
				email.sendEmail(selLib.errorScreenshots);
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			// assertTrue(false);
		}
	}

	@BeforeClass 
	public void beforeAllTests() {
		try {
			logger.info("Starting all the tests...");
			JavaPropertiesManager properties = new JavaPropertiesManager("src/test/resources/config.properties");
			myBrowser = properties.readProperty("browserType");
			myDemoMode = properties.readProperty("demoMode");
			mySendEmail = properties.readProperty("sendEmail");
			myIsRemote = properties.readProperty("remote");

			if (myDemoMode.toLowerCase().contains("on")) {
				logger.info("Demo mode is ON ....");
			} else {
				logger.info("Demo mode is OFF ...");
			}

			JavaPropertiesManager propSession = new JavaPropertiesManager(
					"src/test/resources/dynamicConfig.properties");
			selLib = new GlobalSeleniumLibrary(driver);
			String tempSessionTime = selLib.getCurrentTime();
			propSession.setProperty("sessionTime", tempSessionTime);
			logger.info("Session Time: " + tempSessionTime);

		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}
}
