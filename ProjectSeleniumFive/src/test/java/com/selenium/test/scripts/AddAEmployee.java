package com.selenium.test.scripts;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.selenium.library.Base;
import com.selenium.library.TextFileManager;
import com.selenium.orangehrm.pages.DashboardPage;
import com.selenium.orangehrm.pages.LoginInPage;
import com.selenium.orangehrm.pages.PimPage;

public class AddAEmployee extends Base {

	final static Logger logger = Logger.getLogger(AddAEmployee.class);

	@Test
	public void addEmp() {

		TextFileManager reader = new TextFileManager("src/test/resources/TestData/employeInfo.txt");
		
		try {
			String data = reader.readFile();
			logger.info("data:------ \n" + data);

			String sp[]= data.split(";");
			
			
			LoginInPage log = new LoginInPage();
			log.gotosite();
			log.userAndPass(sp[0],sp[1]);
			log.clickloginBut();
			DashboardPage dash = new DashboardPage();
			dash.menuPIM();
			selLib.clickButton(By.partialLinkText("Add Employee"));
			selLib.enterText(By.id("firstName"), sp[2]);
			selLib.enterText(By.id("lastName"), sp[3]);
			PimPage pim = new PimPage();
			pim.editAndSaveBut();
			pim.emergencyInfo();
			selLib.clickButton(By.id("btnAddContact"));
			selLib.enterText(By.id("emgcontacts_name"), sp[4]);
			selLib.enterText(By.id("emgcontacts_relationship"), sp[5]);
			selLib.clickButton(By.id("btnSaveEContact"));
			pim.reportedTo();
			selLib.clickButton(By.id("btnAddSupervisorDetail"));
			selLib.enterText(By.id("reportto_supervisorName_empName"), sp[6]);
			selLib.selectDropDown(By.id("reportto_reportingMethodType"), sp[7]);
			pim.salaryInfo();
			selLib.clickButton(By.id("addSalary"));
			selLib.enterText(By.id("salary_salary_component"), sp[8]);
			selLib.selectDropDown(By.id("salary_currency_id"), "United States Dollar");
			selLib.enterText(By.id("salary_basic_salary"), sp[9]);
			selLib.scrollByVertically("250");
			selLib.clickButton(By.id("btnSalarySave"));
			
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

}
