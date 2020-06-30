package com.selenium.test.scripts;

import org.testng.annotations.Test;

import com.selenium.library.Base;
import com.selenium.orangehrm.pages.DashboardPage;
import com.selenium.orangehrm.pages.LoginInPage;
import com.selenium.orangehrm.pages.PimPage;

public class Group extends Base {

	@Test(enabled = false, priority = 1, groups = { "checkList", "credit" })
	public void loginValidInfo() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();
	}

	@Test(enabled = false, priority = 2, groups = { "checkList" })
	public void loginInvalidInfo() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admiaa", "admin22");
		log.clickloginBut();
	}

	@Test(enabled = false, priority = 3, groups = { "credit" })
	public void licenseUpdate() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.menuPIM();

		PimPage pim = new PimPage();
		pim.hanaEmp();
		pim.editAndSaveBut();
		pim.licenseDate();
		pim.editAndSaveBut();
	}

}
