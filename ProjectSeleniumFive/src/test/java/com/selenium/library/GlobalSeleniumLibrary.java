package com.selenium.library;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GlobalSeleniumLibrary {
	final static Logger logger = Logger.getLogger(GlobalSeleniumLibrary.class);
	private boolean isDemoMode = false;

	public void setDemoMode(boolean isDemoMode) {
		this.isDemoMode = isDemoMode;
	}

	private WebDriver driver;
	public List<String> errorScreenshots;
	private boolean isRemote;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver _driver) {
		this.driver = _driver;
	}

	public GlobalSeleniumLibrary(WebDriver _driver) {
		driver = _driver;
	}

	public GlobalSeleniumLibrary(WebDriver _driver, String browserType) {
		driver = _driver;
	}
	
	private WebDriver startChromeBrowser() {
	WebDriverManager.chromedriver().setup();
//	System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
	
//	ChromeOptions options = new ChromeOptions();
//	options.addArguments("headless");
//	options.addArguments("window-size=1200x600");
	
	driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	return driver;
}

private WebDriver startFirefoxBrowser() {
	WebDriverManager.firefoxdriver().setup();
//	System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	return driver;
}

@SuppressWarnings("deprecation")
private WebDriver startIEBrowser() {
	try {
		System.setProperty("webdriver.ie.driver", "src/test/resources/IEDriverServer.exe");
		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		cap.setCapability("ignoreProtectedModeSettings", true);
		cap.setCapability("ie.ensureCleanSession", true);

		driver = new InternetExplorerDriver(cap);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	} catch (Exception e) {
		logger.error("Error: ", e);
		assertTrue(false);
	}
	return driver;
}

public WebDriver startBrowser(String browserType) {
	try {
		if (browserType.toLowerCase().contains("chrome")) {
			driver = startChromeBrowser();
		} else if (browserType.toLowerCase().contains("firefox")) {
			driver = startFirefoxBrowser();
		} else if (browserType.toLowerCase().contains("ie")) {
			driver = startIEBrowser();
		} else {
			logger.info("You are using: [" + browserType + "] browser, we dont support this browser yet");
			logger.info("starting default browser 'Chrome'");
			driver = startChromeBrowser();
		}
		driver.manage().deleteAllCookies();
	} catch (Exception e) {
		logger.error("Error: ", e);
		assertTrue(false);
	}
	return driver;
}

//public WebDriver startBrowser(String browserType) {
//	try {
//		switch (browserType) {
//		case "chrome":
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
//			break;
//		case "firefox":
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
//			break;
//		case "opera":
//			WebDriverManager.operadriver().setup();
//			driver = new OperaDriver();
//			break;
//		case "edge":
//			WebDriverManager.edgedriver().setup();
//			driver = new EdgeDriver();
//			break;
//		default:
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
//			break;
//		}
//		
//		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
//		
//	} catch (Exception e) {
//		
//	}
//	return driver;
//}

	public WebDriver startRemoteBrowser(String hubURL, String browser) {
		DesiredCapabilities cap = null;
		try {
			if (browser.toLowerCase().contains("chrome")) {
				cap = DesiredCapabilities.chrome();
			} else if (browser.toLowerCase().contains("ie")) {
				cap = DesiredCapabilities.internetExplorer();
				cap.setCapability("ignoreProtectedModeSettings", true);
				cap.setCapability("ie.ensureCleanSession", true);
			} else if (browser.toLowerCase().contains("firefox")) {
				cap = DesiredCapabilities.firefox();
			} else {
				logger.info("You choose: '" + browser.toLowerCase() + "', Currently, framework does't support it.");
				logger.info("starting default browser 'Internet Explorer'");
				cap = DesiredCapabilities.internetExplorer();
				cap.setCapability("ignoreProtectedModeSettings", true);
				cap.setCapability("ie.ensureCleanSession", true);
			}
			driver = new RemoteWebDriver(new URL(hubURL), cap);
			isRemote = true;
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	public void handleCheckBox(By by, boolean isCheck) {
		try {
			WebElement checkBoxElem = driver.findElement(by);
			highlightElement(checkBoxElem);
			boolean checkBoxState = checkBoxElem.isSelected();

			// Scenario: 1 ===> User wants to check the check-box
			if (isCheck == true) {
				// check-box state 1: check box is checked already
				if (checkBoxState == true) {
					// do nothing
				}
				// check-box state 2: check box is not checked
				else {
					checkBoxElem.click();
				}
			}
			// Scenario: 2 ===> User wants to uncheck the check-box
			else {
				// check-box state 1: check box is checked already
				if (checkBoxState == true) {
					checkBoxElem.click();
				} else
				// check-box state 2: check box is not checked
				{
					// do nothing
				}
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}



	public void selectDropDown(By by, int index) {
		try {
			WebElement element = driver.findElement(by);
			highlightElement(element);
			Select dropDown = new Select(element);
			dropDown.selectByIndex(index);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void selectDropDown(By by, String visibleText) {
		try {
			WebElement element = driver.findElement(by);
			highlightElement(element);
			Select dropDown = new Select(element);
			dropDown.selectByVisibleText(visibleText);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void selectDropDown(String attributeValue, By by) {
		try {
			WebElement element = driver.findElement(by);
			highlightElement(element);
			Select dropDown = new Select(element);
			dropDown.selectByValue(attributeValue);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void enterText(By by, String textString) {
		try {
			WebElement element = driver.findElement(by);
			highlightElement(element);
			element.clear();
			element.sendKeys(textString);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void clickButton(By by) {
		try {
			WebElement button = driver.findElement(by);
			highlightElement(button);
			button.click();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void clickHiddenElement(By by) {
		try {
			WebElement elem = driver.findElement(by);
			highlightElement(elem);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", elem);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void clickHiddenElement(WebElement element) {
		try {
			highlightElement(element);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void customWait(double inSecs) {
		try {
			Thread.sleep((long) (inSecs * 1000));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void moveMouseToElement(WebElement targetElem) {
		try {
			highlightElement(targetElem);
			Actions action = new Actions(driver);
			action.moveToElement(targetElem).build().perform();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void moveMouseToElement(WebElement firstElem, WebElement secElem) {
		try {
			highlightElement(firstElem);
			Actions action = new Actions(driver);
			action.moveToElement(firstElem).build().perform();
			customWait(1);
			highlightElement(secElem);
			action.moveToElement(secElem).build().perform();
			customWait(1);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void highlightElement(WebElement element) {
		try {
			if (isDemoMode == true) {
				for (int i = 0; i < 2; i++) {
					WrapsDriver wrappedElement = (WrapsDriver) element;
					JavascriptExecutor js = (JavascriptExecutor) wrappedElement.getWrappedDriver();
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
							"color: red; border: 2px solid yellow");
					customWait(0.5);
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
					customWait(0.5);
				}
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void scrollToElement(WebElement element) {
		try {
			highlightElement(element);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void scrollByVertically(String verticalPixel) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("scroll(0," + verticalPixel + ")");
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void scrollByHorizontally(String horizontalPixel) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("scroll(" + horizontalPixel + ",0)");
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void sendKeyBoard(CharSequence... keysToSend) {
		try {
			WebElement webpage = driver.findElement(By.tagName("body"));
			highlightElement(webpage);
			webpage.sendKeys(keysToSend);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public String getCurrentTime() {
		String finalTime = null;
		try {
			Date date = new Date();
			String tempTime = new Timestamp(date.getTime()).toString();
			logger.debug("time: " + tempTime);
			finalTime = tempTime.replace("-", "").replace(" ", "").replace(":", "").replace(".", "");
			logger.debug("updated time: " + finalTime);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return finalTime;
	}

	public String screenCapture(String screenshotFileName) {
		String filePath = null;
		String fileName = null;
		try {
			fileName = screenshotFileName + getCurrentTime() + ".png";
			filePath = "target/screenshots/";
			File tempfile = new File(filePath);
			if (!tempfile.exists()) {
				tempfile.mkdirs();
			}
			filePath = tempfile.getAbsolutePath();

			if (isRemote == true) {
				driver = new Augmenter().augment(driver);
			}
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(scrFile, new File(filePath + "/" + fileName));

		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		logger.info(filePath + "/" + fileName);
		return filePath + "/" + fileName;
	}

	public WebElement waitForElementPresence(By by) {
		WebElement elem = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			elem = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			highlightElement(elem);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return elem;
	}

	public WebElement waitForElementVisibility(By by) {
		WebElement elem = null;
		try {
			elem = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(by));
			highlightElement(elem);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return elem;
	}

	public WebElement waitForElementToBeClickable(By by) {
		WebElement elem = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			elem = wait.until(ExpectedConditions.elementToBeClickable(by));
			highlightElement(elem);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return elem;
	}

	public WebElement fluentWait(final By by) {
		WebElement targetElem = null;
		try {
			@SuppressWarnings("deprecation")
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
					.pollingEvery(3, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			targetElem = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(by);
				}
			});
			highlightElement(targetElem);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return targetElem;
	}

	public void clickButton(WebElement element) {
		try {
			highlightElement(element);
			element.click();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}

	}

	public WebDriver switchToWindow(int browserIndex) {
		try {
			Set<String> allBrowsers = driver.getWindowHandles();
			Iterator<String> iterator = allBrowsers.iterator();
			List<String> windowHandles = new ArrayList<String>();
			while (iterator.hasNext()) {
				String window = iterator.next();
				windowHandles.add(window);
			}
			// switch to index N
			driver.switchTo().window(windowHandles.get(browserIndex));
			highlightElement(By.tagName("body"));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	public void highlightElement(By by) {
		try {
			if (isDemoMode == true) {
				WebElement element = driver.findElement(by);
				for (int i = 0; i < 2; i++) {
					WrapsDriver wrappedElement = (WrapsDriver) element;
					JavascriptExecutor js = (JavascriptExecutor) wrappedElement.getWrappedDriver();
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
							"color: red; border: 2px solid yellow");
					customWait(0.5);
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
					customWait(0.5);
				}
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void fileUpload(By by, String fileRelativePath) {
		try {
			WebElement fileUploadElem = driver.findElement(by);
			highlightElement(fileUploadElem);
			File tempFile = new File(fileRelativePath);
			String fullPath = tempFile.getAbsolutePath();
			logger.info("file uploading : " + fullPath);
			if (isRemote == true) {
				((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
			}
			fileUploadElem.sendKeys(fullPath);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public Alert isAlertPresent() {
		Alert alert = null;
		try {
			alert = driver.switchTo().alert();
			logger.info("Popup Alert detected: {" + alert.getText() + "}");
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return alert;
	}

	public void closeAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			logger.info("Popup Alert detected: {" + alert.getText() + "}");
			alert.dismiss();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			logger.info("Popup Alert detected: {" + alert.getText() + "}");
			alert.accept();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public List<String> automaticallyAttachErrorImgToEmail() {
		List<String> fileNames = new ArrayList<String>();
		JavaPropertiesManager propertyReader = new JavaPropertiesManager("src/test/resources/dynamicConfig.properties");
		String tempTimeStamp = propertyReader.readProperty("sessionTime");
		String numberTimeStamp = tempTimeStamp.replaceAll("_", "");
		long testStartTime = Long.parseLong(numberTimeStamp);
		File file = new File("target/screenshots");  
//		first check if error-screenshot folder  has file
		
		if (file.isDirectory()) {
			if (file.list().length > 0) {
				File[] screenshotFiles = file.listFiles();
				for (int i = 0; i < screenshotFiles.length; i++) {
					// checking if file is a file, not a folder
					if (screenshotFiles[i].isFile()) {
						String eachFileName = screenshotFiles[i].getName();
						logger.debug("Testing file names: " + eachFileName);
						int indexOf20 = searchSubstringInString("20", eachFileName);
						String timeStampFromScreenshotFile = eachFileName.substring(indexOf20,
								eachFileName.length() - 4);
						logger.debug("Testing file timestamp: " + timeStampFromScreenshotFile);
						String fileNumberStamp = timeStampFromScreenshotFile.replaceAll("_", "");
						long screenshotfileTime = Long.parseLong(fileNumberStamp);

						testStartTime = Long.parseLong(numberTimeStamp.substring(0, 14));
						screenshotfileTime = Long.parseLong(fileNumberStamp.substring(0, 14));
						if (screenshotfileTime > testStartTime) {
							fileNames.add("target/screenshots/" + eachFileName);
							logger.info("Screenshots attaching: " + eachFileName);
						}
					}
				}
			}
		}
		errorScreenshots = fileNames;
		return fileNames;
	}

	public int searchSubstringInString(String target, String message) {
		int targetIndex = 0;
		for (int i = -1; (i = message.indexOf(target, i + 1)) != -1;) {
			targetIndex = i;
			break;
		}
		return targetIndex;
	}

	/*
	 * public static void main(String[] args) { GlobalSeleniumLibrary myLib = new
	 * GlobalSeleniumLibrary(null); myLib.getCurrentTime(); }
	 */

} // closing class
