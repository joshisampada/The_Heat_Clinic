package com.atmecs.DemoQaWebsite.constants;

import java.io.File;

public class ProjectPathConstants 
{
	public static String currentdir = System.getProperty("user.dir") + File.separator;
	public static String resources = currentdir +  File.separator + "resources" + File.separator;
	public static String lib = currentdir +  File.separator + "lib" + File.separator;
	
	//config
	public static String config = resources + "config.properties";
	
	public final static String locator_path = resources + "locator_path.properties";
	
	public final static String chromepath = lib + "chromedriver.exe";
	public final static String firefoxpath = lib + "geckodriver.exe";
	public final static String iepath = lib + "IEDriverServer.exe";
	
	//Object Repository
	public static  String objectrepo = resources + "objectrepo.properties";
	
}
