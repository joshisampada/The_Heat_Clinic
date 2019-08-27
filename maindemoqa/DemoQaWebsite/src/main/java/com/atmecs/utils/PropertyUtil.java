package com.atmecs.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil
{
	public static Properties loadProperty(String filePath) throws IOException{
		Properties props = new Properties();
		FileInputStream ip = new FileInputStream(filePath);
		props.load(ip);
		return props;
}
}