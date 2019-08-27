package com.atmecs.testscripts;

import org.testng.annotations.Test;


import com.atmecs.testsuite.TestBase;

public class HomePage extends TestBase
{
	@Test
	public void navigateToUrl()
	{
	//Verifying whether user is navigated to correct url welcome page.
			String expectedTitle = "ToolsQA Demo Site – ToolsQA – Demo E-Commerce Site";
			String actualTitle = "";
			actualTitle = driver.getTitle();
			log.printLog(actualTitle);
			asert.Assertion(actualTitle, expectedTitle, "Navigation");
		
			
			//Verifying whether user is navigated to correct url welcome page.
			String expectedUrl = "http://shop.demoqa.com/";
			String actualUrl = "";
			actualUrl = driver.getCurrentUrl();
			actualTitle=driver.getTitle();
			log.printLog(actualTitle);
			log.printLog(actualUrl);
			asert.Assertion(actualUrl, expectedUrl, "Navigation");
			
				
	}
}
