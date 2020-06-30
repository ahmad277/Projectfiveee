package com.selenium.test.scripts;

import org.testng.annotations.Test;

import com.selenium.library.Base;
import com.selenium.orangehrm.pages.DataReader;

public class LoginInTest extends Base{
	
	@Test(dataProvider = "OrangeHRM",dataProviderClass = DataReader.class)
	public void newEmp(String username,String password,String firstName,String lastName,String emergContact,
			String emergContactRelation,String supervisorName,String salaryCom,String salary) {
		
		DataReader dr = new DataReader();
		dr.empInfo(username, password, firstName, lastName, emergContact,
				emergContactRelation, supervisorName, salaryCom, salary);
	}
	
	
	@Test(dataProvider = "DataBase", dataProviderClass = DataReader.class)
	public void login(String username,String password) {
		DataReader dr = new DataReader();
		dr.login(username, password);
		
	}
	
		
	
}
