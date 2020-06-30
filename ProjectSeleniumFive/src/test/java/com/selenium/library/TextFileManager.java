package com.selenium.library;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.log4j.Logger;

public class TextFileManager {
	final static Logger logger = Logger.getLogger(TextFileManager.class);
	
	private String fileName;
	
	public TextFileManager (String filePath){
		fileName = filePath;
	}
	
	public String readFile(){
		String finalTxt = null;
		String line = null;
		String newLine = System.lineSeparator();
		try{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bfr = new BufferedReader(fileReader);
			StringBuffer sb = new StringBuffer();
			
			while((line = bfr.readLine()) !=null){
				sb.append(line + newLine);
				
			}
			finalTxt = sb.toString();
			bfr.close();
			fileReader.close();			
		}catch(Exception e){
			logger.error("Error: ", e);
			assertTrue(false);
		}		
		return finalTxt;
	}
	
	public void writeFile(String inputData){
		try{
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.write(inputData);
			bw.close();			
			logger.info("Text file is created: \n" + fileName);
		}catch(Exception e){
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}
	
	public static void main(String[] args) {
//		// Testing write txt file method
//		String inputData = "I love Java Programming! "
//				+ "\nI am successfully completed the selenium training in last 7 plus months!";
//		
//		TextFileManager myTxtWriter = new TextFileManager("src/test/resources/testData/myFirstData.txt");
//		myTxtWriter.writeFile(inputData);
		
		// Testing read txt file method
		TextFileManager myTxtReader = new TextFileManager("src/test/resources/testData/myFirstData.txt");
		String data = myTxtReader.readFile();
		logger.info("data:----------- \n" + data );
	}
	
}







