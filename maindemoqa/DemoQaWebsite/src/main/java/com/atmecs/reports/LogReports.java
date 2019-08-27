package com.atmecs.reports;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogReports
{
	Logger log=null;
	//String logpath = ProjectPathConstants.log4j + "/resources/log4j/log4j.properties";
	String logpath = System.getProperty("user.dir") + "/resources/log4j/log4j.properties";
	public LogReports() {
	log = Logger.getLogger(LogReports.class.getName());
	getloggerConfig();
	}

	public void getloggerConfig() {
	PropertyConfigurator.configure(logpath);
	}

	public void printLog(String message) {
	log.info(message);
	}
}
