package com.selenium.library;

import static org.testng.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DatabaseManager {

	final static Logger logger = Logger.getLogger(DatabaseManager.class);

	private String dataBaseServerName;
	private String dataBasePort;
	private String dataBaseSID;
	private String dataBaseName;
	private String userName;
	private String userPassword;

	private String connectionURL = null;
	private ResultSet resultSet = null;
	private Statement statement = null;
	private Connection connection = null;

	private void connectToOracleDB() {
//		dataBaseServerName = "localhost";
//		dataBasePort = "1521";
//		dataBaseSID = "xe";
//		dataBaseName = "hr";
//		userName = "hr";
//		userPassword = "hr";
		
		JavaPropertiesManager properties = new JavaPropertiesManager("src/test/resources/config.properties");
		dataBaseServerName=	properties.readProperty("dataBaseServerName");
		dataBasePort=properties.readProperty("dataBasePort");
		dataBaseSID=properties.readProperty("dataBaseSID");
		dataBaseName=properties.readProperty("dataBaseName");
		userName=properties.readProperty("userName");
		userPassword=properties.readProperty("userPassword");

		try {
			connectionURL = "jdbc:oracle:thin:" + dataBaseName + "@//" + dataBaseServerName + ":" + dataBasePort + "/"
					+ dataBaseSID;
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection(connectionURL, userName, userPassword);
			statement = connection.createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_READ_ONLY);
			
			if(connection !=null) {
				logger.info("DataBase connection is Success !!!");
				
			}else {
				logger.info("Failure to connect to the DataBase !!!");
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public ResultSet runSQLQuery(String sqlQuery) {
		try {
			connectToOracleDB();
			resultSet = statement.executeQuery(sqlQuery);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return resultSet;
	}

}
