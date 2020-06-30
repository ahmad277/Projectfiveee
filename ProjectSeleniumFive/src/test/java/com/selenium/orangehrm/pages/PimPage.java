package com.selenium.orangehrm.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.selenium.library.Base;

public class PimPage extends Base {

	final static Logger logger = Logger.getLogger(PimPage.class);

	public PimPage hanaEmp() {
		selLib.scrollByVertically("500");
		table("Hana");
		return this;
	}

	private void table(String name) {
		try {
			WebElement region = driver.findElement(By.cssSelector("#resultTable > tbody"));
			List<WebElement> elems = region.findElements(By.tagName("a"));
			for (WebElement elem : elems) {
				String elemTxt = elem.getText();
				if (name.contains(elemTxt)) {
					selLib.clickButton(elem);
					break;
				}
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public PimPage editAndSaveBut() {
		try {
			selLib.clickButton(By.id("btnSave"));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public PimPage licenseDate() {
		try {
			selLib.enterText(By.id("personal_txtLicExpDate"), "2022-03-22");
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public PimPage picture() {
		try {
			WebElement pic = driver.findElement(By.id("empPic"));
			selLib.highlightElement(pic);
			String picture = pic.getAttribute("height");
			logger.info(picture);
			assertEquals(picture, "101");
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public PimPage emergencyInfo() {
		empInfo("Emergency Contacts");
		return this;
	}

	public PimPage salaryInfo() {
		empInfo("Salary");
		return this;
	}

	public PimPage reportedTo() {
		empInfo("Report-to");
		return this;
	}

	private void empInfo(String name) {
		try {
			WebElement region = driver.findElement(By.id("sidenav"));
			List<WebElement> elems = region.findElements(By.tagName("a"));
			for (WebElement elem : elems) {
				String elemTxt = elem.getText();
				if (name.contains(elemTxt)) {
					selLib.clickButton(elem);
					break;
				}
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public PimPage emergencyRel() {
		try {
			WebElement rel = driver.findElement(By.id("relationship_1"));
			selLib.highlightElement(rel);
			String name = rel.getAttribute("value");
			logger.debug(name);
			assertEquals(name, "Father");
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public PimPage empJob() {
		empInfo("IT Executive", 12, 4);
		return this;
	}

	public PimPage empSupersisor() {
		empInfo("Jasmine Morgan", 12, 7);
		return this;
	}
	
	private void empInfo(String expected, int listNum, int item) {
		try {
			WebElement empInfo = driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[" + listNum + "]"));
			List<WebElement> elems = empInfo.findElements(By.tagName("td"));
			WebElement title = elems.get(item);
			String elemTxt = title.getText();
			if (expected.contains(elemTxt)) {
				selLib.highlightElement(title);
				logger.debug(elemTxt);
				assertEquals(elemTxt, expected);
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public PimPage salaryAmount() {
		salaryInfo("55000");
		return this;
	}

	private void salaryInfo(String salary) {
		try {
			WebElement salaryInfo = driver.findElement(By.cssSelector("#tblSalary > tbody > tr"));
			List<WebElement> elems = salaryInfo.findElements(By.tagName("td"));
			WebElement salaryEmp = elems.get(4);
			String amount = salaryEmp.getText();
			if (salary.contains(amount)) {
				selLib.highlightElement(salaryEmp);
				logger.debug(amount);
				assertEquals(amount, salary);
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

}
