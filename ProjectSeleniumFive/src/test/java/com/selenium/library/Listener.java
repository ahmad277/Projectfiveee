package com.selenium.library;

import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;


public class Listener implements IReporter {
	
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		
		
		
		// TODO Auto-generated method stub
		//Iterate over each suites
				for(ISuite suite: suites) {
					
					//gets the suite name
					String suiteName = suite.getName();
					Map<String, ISuiteResult> suiteResults = suite.getResults();
					for(ISuiteResult sr:suiteResults.values()) {
						ITestContext tc = sr.getTestContext();
						
						System.out.println("Passed test for suite " + suiteName + " is:"
								+ tc.getPassedTests().getAllResults().size());
						System.out.println("Failed test for suite " + suiteName + " is:"
								+ tc.getFailedTests().getAllResults().size());
						System.out.println("Skipped test for suite " + suiteName + " is:"
								+ tc.getSkippedTests().getAllResults().size());
					}
				}
	}
}
