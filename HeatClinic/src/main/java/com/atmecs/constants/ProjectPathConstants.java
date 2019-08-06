package com.atmecs.constants;

import java.io.File;

public class ProjectPathConstants
{
	public static String currentdir = System.getProperty("user.dir") + File.separator;
	public static String resources = currentdir +  File.separator + "resources" + File.separator;
	
	//config
	public static String config = resources + "config.properties";
	
	//Object Repository
	public static  String objectrepo = resources + "objectrepo.properties";
}
