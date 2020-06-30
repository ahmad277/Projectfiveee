package com.selenium.orangehrm.pages;

import static org.testng.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;

import com.selenium.library.Base;
import com.selenium.library.DatabaseManager;
import com.selenium.library.ExcelManager;

public class DataReader extends Base {

	final static Logger logger = Logger.getLogger(LeavePage.class);
	int counter = 0;

	@DataProvider(name = "OrangeHRM")
	private Object[][] empInfo() {
		Object[][] data = null;
		ExcelManager excel = new ExcelManager("src/test/resources/TestData/empInfo.xlsx", 0);
		data = excel.getExcelData();
		return data;
	}

	@DataProvider(name = "DataBase")
	public String[][] read() {

		int col = 0;
		int row = 0;
		String data[][] = null;
		DatabaseManager db = new DatabaseManager();
		try {
			ResultSet rs = db.runSQLQuery("select * from login");
			
			ResultSetMetaData rsmd = rs.getMetaData();
			col = rsmd.getColumnCount();

			while (rs.next())
				row++;

			data = new String[row][col];

			rs.beforeFirst();

			for (int i = 0; i < row; i++) {
				rs.next();
				for (int j = 1; j <= col; j++) {
					data[i][j-1] = rs.getString(j);
					logger.info(data[i][j-1]);
				}
			}
		} catch (Exception e) {
			logger.error("Error:" + e.getMessage());
		}
		return data;
	}

	public void login(String username, String password) {
		try {
			driver.get("https://opensource-demo.orangehrmlive.com/");
			selLib.enterText(By.id("txtUsername"), username);
			selLib.enterText(By.id("txtPassword"), password);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void empInfo(String username, String password, String firstName, String lastName, String emergContact,
			String emergContactRelation, String supervisorName, String salaryCom, String salary) {
		counter++;

		LoginInPage log = new LoginInPage();
		log.gotosite();
		log.userAndPass(username, password);
		log.clickloginBut();
		DashboardPage dash = new DashboardPage();
		dash.menuPIM();
		selLib.clickButton(By.partialLinkText("Add Employee"));
		selLib.enterText(By.id("firstName"), firstName);
		selLib.enterText(By.id("lastName"), lastName);
		PimPage pim = new PimPage();
		pim.editAndSaveBut();
		pim.emergencyInfo();
		selLib.clickButton(By.id("btnAddContact"));
		selLib.enterText(By.id("emgcontacts_name"), emergContact);
		selLib.enterText(By.id("emgcontacts_relationship"), emergContactRelation);
		selLib.clickButton(By.id("btnSaveEContact"));
		pim.reportedTo();
		selLib.clickButton(By.id("btnAddSupervisorDetail"));
		selLib.enterText(By.id("reportto_supervisorName_empName"), supervisorName);
		selLib.selectDropDown(By.id("reportto_reportingMethodType"), "Direct");
		pim.salaryInfo();
		selLib.clickButton(By.id("addSalary"));
		selLib.enterText(By.id("salary_salary_component"), salaryCom);
		selLib.selectDropDown(By.id("salary_currency_id"), "United States Dollar");
		selLib.enterText(By.id("salary_basic_salary"), salary);
		selLib.scrollByVertically("250");
		selLib.clickButton(By.id("btnSalarySave"));
	}	 

}
