package com.selenium.orangehrm.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.selenium.library.Base;

public class DashboardPage extends Base {

	final static Logger logger = Logger.getLogger(DashboardPage.class);

	public DashboardPage menuPIM() {
		menu("menu_pim_viewPimModule");
		return this;
	}

	public DashboardPage menuLeave() {
		menu("menu_leave_viewLeaveModule");
		return this;
	}

	public DashboardPage menuAdmin() {
		menu("menu_admin_viewAdminModule");
		return this;
	}

	private void menu(String id) {
		try {
			WebElement region = driver.findElement(By.cssSelector("#wrapper > div.menu"));
			List<WebElement> elems = region.findElements(By.tagName("a"));
			for (WebElement elem : elems) {
				String elemTxt = elem.getAttribute("id");
				if (id.contains(elemTxt)) {
					selLib.clickButton(elem);
					break;
				}
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}

	}

	public DashboardPage distribution() {
		try {
			WebElement region = driver.findElement(By.id("div_graph_display_emp_distribution"));
			selLib.highlightElement(region);
			List<WebElement> dist = region.findElements(By.className("pieLabel"));
			for (WebElement elem : dist) {
				String txt = elem.getText();
				logger.info("Distribution: " + txt);
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public DashboardPage clickAbout() {
		try {
			selLib.clickButton(By.id("welcome"));
			selLib.clickButton(By.id("aboutDisplayLink"));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public DashboardPage about(int number, String expected) {
		try {
			WebElement about = driver.findElement(By.id("companyInfo"));
			WebElement info = about.findElements(By.tagName("li")).get(number);
			selLib.highlightElement(info);
			String txt = info.getText();
			assertEquals(txt, expected);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public DashboardPage marketPlace() {
		try {
			selLib.clickButton(By.id("MP_link"));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public DashboardPage marketInstall() {
		try {
			selLib.clickButton(By.id("installButton2"));
			selLib.clickButton(By.id("modal_confirm_install"));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public DashboardPage legend() {
		try {
			WebElement legend = driver
					.findElement(By.cssSelector("#div_legend_pim_employee_distribution_legend > table > tbody"));
			selLib.highlightElement(legend);
			String txt = legend.getText();
			logger.info(txt);
			assertEquals(txt, "Not assigned to Subunits\n" + "Administration\n" + "Finance\n" + "IT\n" + "Sales");
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public DashboardPage pendingleaveReq() {
		try {
			WebElement req = driver.findElement(By.id("task-list-group-panel-menu_holder"));
			selLib.highlightElement(req);
			String txt = req.getText();
			logger.info(txt);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}
}
