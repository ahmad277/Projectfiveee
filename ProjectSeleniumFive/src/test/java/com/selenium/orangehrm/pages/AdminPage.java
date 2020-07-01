package com.selenium.orangehrm.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.selenium.library.Base;

public class AdminPage extends Base {

	final static Logger logger = Logger.getLogger(AdminPage.class);

	public AdminPage jobTilte() {
		try {
			WebElement job = driver.findElement(By.id("menu_admin_Job"));
			selLib.moveMouseToElement(job);
			WebElement jobTitle = driver.findElement(By.id("menu_admin_viewJobTitle"));
			System.out.println("Hi");
			selLib.clickButton(jobTitle);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminPage addBut() {
		try {
			selLib.clickButton(By.id("btnAdd"));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminPage addJob() {
		try {
			selLib.enterText(By.id("jobTitle_jobTitle"), "Cleaner");
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminPage saveBut() {
		try {
			selLib.clickButton(By.id("btnSave"));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	private void jobList(String job) {
		try {
			WebElement region = driver.findElement(By.cssSelector("#resultTable > tbody"));
			List<WebElement> elems = region.findElements(By.tagName("tr"));
			for (WebElement elem : elems) {
				WebElement jobs = elem.findElements(By.tagName("td")).get(1);
				String elemTxt = jobs.getText();
				if (job.contains(elemTxt)) {
					selLib.highlightElement(jobs);
					logger.info(elemTxt);
					assertEquals(elemTxt, job);
					break;
				}

			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public AdminPage job() {
		jobList("Cleaner");
		return this;
	}

	public AdminPage genInfo() {
		try {
			WebElement info = driver.findElement(By.id("menu_admin_Organization"));
			selLib.moveMouseToElement(info);
			selLib.clickButton(By.partialLinkText("General Information"));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminPage empNum(String expected) {
		try {
			WebElement empNum = driver.findElement(By.id("numOfEmployees"));
			selLib.highlightElement(empNum);
			String number = empNum.getText();
			logger.info(number);
			assertEquals(number, expected);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminPage taxId() {
		try {
			WebElement taxId = driver.findElement(By.id("organization_taxId"));
			selLib.highlightElement(taxId);
			String number = taxId.getAttribute("value");
			logger.info(number);
			assertEquals(number, "1234560");
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminPage locations() {
		try {
			WebElement loc = driver.findElement(By.id("menu_admin_Organization"));
			selLib.moveMouseToElement(loc);
			selLib.clickButton(By.id("menu_admin_viewLocations"));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminPage locHQ() {
		try {
			WebElement region = driver.findElement(By.cssSelector("#resultTable > tbody > tr:nth-child(2)"));
			WebElement loc = region.findElements(By.tagName("td")).get(3);
			selLib.highlightElement(loc);
			String locTxt = loc.getText();
			logger.debug(locTxt);
			assertEquals(locTxt, "United States");
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminPage nationalities() {
		try {
			selLib.clickButton(By.id("menu_admin_nationality"));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminPage nationalitiesNum() {
		try {
			WebElement nation = driver.findElement(By.cssSelector("#resultTable > tbody"));
			List<WebElement> nationality = nation.findElements(By.tagName("tr"));
			int size = nationality.size();
			logger.debug(size);
			assertEquals(size, 193);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminPage workShift() {
		try {
			WebElement job = driver.findElement(By.id("menu_admin_Job"));
			selLib.moveMouseToElement(job);
			selLib.clickButton(By.id("menu_admin_workShift"));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminPage generalShift() {
		try {
			WebElement shift = driver.findElement(By.cssSelector("#resultTable > tbody > tr.odd"));
			selLib.highlightElement(shift);
			String txt = shift.getText();
			logger.debug(txt);
			assertEquals(txt, "General 08:00 17:00 9.00");
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

}
