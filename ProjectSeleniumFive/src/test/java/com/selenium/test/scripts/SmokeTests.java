package com.selenium.test.scripts;

import org.testng.annotations.Test;

import com.selenium.library.Base;
import com.selenium.orangehrm.pages.AdminPage;
import com.selenium.orangehrm.pages.DashboardPage;
import com.selenium.orangehrm.pages.LeavePage;
import com.selenium.orangehrm.pages.LoginInPage;
import com.selenium.orangehrm.pages.PimPage;

public class SmokeTests extends Base {

	@Test(enabled = false, priority = 1)
	public void loginValidInfo() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();
	}

	@Test(enabled = false, priority = 2)
	public void loginInvalidInfo() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admiaa", "admin22");
		log.clickloginBut();
	}

	@Test(enabled = false, priority = 3)
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

	@Test(enabled = false, priority = 4)
	public void PictureEmp() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.menuPIM();

		PimPage pim = new PimPage();
		pim.hanaEmp();
		pim.picture();
	}

	@Test(enabled = false, priority = 5)
	public void emergencyEmpInfo() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.menuPIM();

		PimPage pim = new PimPage();
		pim.hanaEmp();
		pim.emergencyInfo();
		pim.emergencyRel();
	}

	@Test(enabled = false, priority = 6)
	public void jobtitleEmp() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.menuPIM();
		selLib.scrollByVertically("500");

		PimPage pim = new PimPage();
		pim.empJob();
	}

	@Test(enabled = false, priority = 7)
	public void empSalary() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.menuPIM();
		selLib.scrollByVertically("500");

		PimPage pim = new PimPage();
		pim.hanaEmp();
		pim.salaryInfo();
		pim.salaryAmount();
	}

	@Test(enabled = false, priority = 8)
	public void empSupervisor() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.menuPIM();
		selLib.scrollByVertically("500");

		PimPage pim = new PimPage();
		pim.empSupersisor();
	}

	@Test(enabled = false, priority = 9)
	public void leaveList() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.menuLeave();

		LeavePage leave = new LeavePage();
		leave.allStatus();
		leave.searchBut();
		selLib.scrollByVertically("250");
		leave.leaveList();
		leave.vacationType();
	}

	@Test(enabled = false, priority = 10)
	public void leaveStatus() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.menuLeave();

		LeavePage leave = new LeavePage();
		leave.allStatus();
		leave.searchBut();
		selLib.scrollByVertically("250");
		leave.leaveList();
		leave.leavingStatus();
	}

	@Test(enabled = false, priority = 11)
	public void leaveDays() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.menuLeave();

		LeavePage leave = new LeavePage();
		leave.allStatus();
		leave.searchBut();
		selLib.scrollByVertically("250");
		leave.leaveList();
		leave.leavingDays();
	}

	@Test(enabled = false, priority = 12)
	public void leaveCancel() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.menuLeave();

		LeavePage leave = new LeavePage();
		leave.allStatus();
		leave.searchBut();
		selLib.scrollByVertically("250");
		leave.leaveList();
		leave.leaveActions();
		leave.clickSave();
		leave.allStatus();
		leave.searchBut();
	}

	@Test(enabled = false, priority = 13)
	public void addNewJob() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.menuAdmin();

		AdminPage ad = new AdminPage();
		ad.jobTilte();
		ad.addBut();
		ad.addJob();
		ad.saveBut();
		selLib.customWait(2);
		ad.job();
	}

	@Test(enabled = true, priority = 14)
	public void empNumbers() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.menuAdmin();

		AdminPage ad = new AdminPage();
		ad.genInfo();
		ad.empNum("32");
	}

	@Test(enabled = true, priority = 15)
	public void taxId() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.menuAdmin();

		AdminPage ad = new AdminPage();
		ad.genInfo();
    	ad.taxId();
	}

	@Test(enabled = false, priority = 16)
	public void hqLocation() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.menuAdmin();

		AdminPage ad = new AdminPage();
		ad.locations();
		selLib.scrollByVertically("250");
		ad.locHQ();
	}

	@Test(enabled = false, priority = 17)
	public void nationalNum() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.menuAdmin();

		AdminPage ad = new AdminPage();
		ad.nationalities();
		ad.nationalitiesNum();
	}

	@Test(enabled = false, priority = 18)
	public void workShift() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.menuAdmin();

		AdminPage ad = new AdminPage();
		ad.workShift();
		ad.generalShift();
	}
	
	@Test(enabled = false, priority = 19)
	public void companyName() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.clickAbout();
		dash.about(0, "Company Name: OrangeHRM (Pvt) Ltd");
	}
	
	@Test(enabled = true, priority = 20)
	public void version() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.clickAbout();
		dash.about(1, "Version: Orangehrm OS 4.4");
	}
	
	@Test(enabled = false, priority = 21)
	public void distribution() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.distribution();
	}
	
	@Test(enabled = false, priority = 22)
	public void marketPlace() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.marketPlace();
		dash.marketInstall();
	}
	
	@Test(enabled = false, priority = 24)
	public void legend() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.legend();
	}
	
	@Test(enabled = false, priority = 25)
	public void leaveReq() {
		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass("admin", "admin123");
		log.clickloginBut();

		DashboardPage dash = new DashboardPage();
		dash.pendingleaveReq();
	}

}
