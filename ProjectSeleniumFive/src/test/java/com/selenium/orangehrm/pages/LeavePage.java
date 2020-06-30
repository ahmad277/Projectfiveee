package com.selenium.orangehrm.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.selenium.library.Base;

public class LeavePage extends Base {

	final static Logger logger = Logger.getLogger(LeavePage.class);

	public LeavePage leaveBut() {
		try {
			WebElement leave = driver.findElement(By.partialLinkText("Leave"));
			selLib.moveMouseToElement(leave);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public LeavePage assignLeaveBut() {
		try {
			selLib.clickButton(By.partialLinkText("Assign Leave"));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public LeavePage assignLeaveFilling() {
		try {
			selLib.enterText(By.id("assignleave_txtEmployee_empName"), "Hana Joey");
			selLib.selectDropDown(By.id("assignleave_txtLeaveType"), "Vacation US");
			selLib.enterText(By.id("assignleave_txtFromDate"), "2020-05-19");
			selLib.enterText(By.id("assignleave_txtToDate"), "2020-05-26");
			selLib.scrollByVertically("250");
			selLib.clickButton(By.id("assignBtn"));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public LeavePage allStatus() {
		try {
			leaveStatus("All");
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	private void leaveStatus(String name) {
		try {
			WebElement region = driver.findElement(By.id("leaveList_chkSearchFilter_checkboxgroup"));
			List<WebElement> elems = region.findElements(By.tagName("label"));
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

	public LeavePage searchBut() {
		try {
			selLib.clickButton(By.id("btnSearch"));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public LeavePage leaveList() {
		try {
			WebElement list = driver.findElement(By.cssSelector("#resultTable > tbody > tr"));
			String txt = list.getText();
			logger.info(txt);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public LeavePage vacationType() {
		leaveListInfo("Vacation US", 2, 2);
		return this;
	}

	public LeavePage leavingStatus() {
		leaveListInfo("Scheduled(6.00)", 2, 5);
		return this;
	}

	public LeavePage leavingDays() {
		leaveListInfo("6.00", 2, 4);
		return this;
	}

	private void leaveListInfo(String info, int listNum, int item) {
		try {
			WebElement empInfo = driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[" + listNum + "]"));
			List<WebElement> elems = empInfo.findElements(By.tagName("td"));
			WebElement title = elems.get(item);
			String elemTxt = title.getText();
			if (info.contains(elemTxt)) {
				selLib.highlightElement(title);
				logger.debug(elemTxt);
				assertEquals(elemTxt, info);
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public LeavePage leaveActions() {
		selLib.selectDropDown(By.id("select_leave_action_6"), "Cancel");
		return this;
	}

	public LeavePage clickSave() {
		selLib.clickButton(By.id("btnSave"));
		return this;
	}

}
