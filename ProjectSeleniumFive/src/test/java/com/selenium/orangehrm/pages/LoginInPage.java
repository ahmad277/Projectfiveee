package com.selenium.orangehrm.pages;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.selenium.library.Base;

public class LoginInPage extends Base {

	final static Logger logger = Logger.getLogger(LoginInPage.class);

	public LoginInPage gotosite() {
		try {
			driver.get("https://opensource-demo.orangehrmlive.com/");
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public LoginInPage userAndPass(String username, String password) {
		try {
			selLib.enterText(By.id("txtUsername"), username);
			selLib.enterText(By.id("txtPassword"), password);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public LoginInPage clickloginBut() {
		try {
			selLib.clickButton(By.id("btnLogin"));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

}
