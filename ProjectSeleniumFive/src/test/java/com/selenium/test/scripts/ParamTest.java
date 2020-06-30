package com.selenium.test.scripts;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.selenium.library.Base;

public class ParamTest extends Base {
	final static Logger logger = Logger.getLogger(ParamTest.class);

	@Parameters({ "username", "password" })
	@Test
	public void parameter(String username, String password) {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		selLib.enterText(By.id("txtUsername"), username);
		selLib.enterText(By.id("txtPassword"), password);
		selLib.clickButton(By.id("btnLogin"));
	}
}

