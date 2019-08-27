package com.atmecs.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil 
{
	public static Properties loadProperty(String propertiesFilePath)  {
		Properties properties = new Properties();
		PropertyUtil PropertyUtil = new PropertyUtil();

		try {
			properties = PropertyUtil.loadProperty(propertiesFilePath);
		} catch (IOException ioException) {
			System.out.println("IOException occured as " + ioException.getMessage());
			return null;
		}

		return properties;
}
}