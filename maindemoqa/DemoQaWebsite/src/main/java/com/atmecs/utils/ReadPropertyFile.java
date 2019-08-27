package com.atmecs.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile
{
	public static String readProperty(String path,String PropertyName)
	{
		String returnProperty="";
		Properties property = new Properties();
		try {
			FileInputStream file =
					new FileInputStream(path);
			property.load(file);
			returnProperty=property.getProperty(PropertyName);
			if(returnProperty==null)
			{
				throw new Exception("Property with name : "+PropertyName+" not found in "+path);
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnProperty;
	}
	
	
	
	
}
