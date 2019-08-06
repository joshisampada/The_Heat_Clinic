package com.atmecs.testscripts;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atmecs.constants.ProjectPathConstants;
import com.atmecs.testsuite.TestBase;
import com.atmecs.utils.PropertiesUtil;

/**
 * 
 * The CountProdTest program is to test that 
 * how many products are present on the webpage.
 * 
 * @author Sampada.Joshi
 *
 */

public class CountProdTest extends TestBase
{

	WebDriver driver;
	static Properties configProps = PropertiesUtil.loadProperty(ProjectPathConstants.config);


	/**
	 * This method is invoke browser
	 */
	@BeforeTest
	public void preSetup()
	{
		this.driver = invokeBrowser();
		String baseUrl = configProps.getProperty("applicationurl");
		driver.get(baseUrl);
		this.driver = windowOperations();
	}

	/**
	 * The method is regarding to count the elements present 
	 * on the webpage. 
	 * 
	 */
	@Test
	public void CountProdTest() 
	{
		//To get current url
		String url=driver.getCurrentUrl();
		System.out.println(url);

		//To check CurrentPageUrl
		String i = driver.getCurrentUrl();
		System.out.println(i);	    

		//To navigate to the Clearance Page
		driver.findElement(By.xpath(objectrepoProps.getProperty("navigateToClearancePage"))).click();

		//To get the list of product images present on page
		List<WebElement> listImages=driver.findElements(By.xpath(objectrepoProps.getProperty("imageList")));
		System.out.println("No. of Product Images: "+listImages.size());
	}

	/**
	 * This method is to close the browser
	 */
	@AfterTest   
	public void tearDown() 
	{
		driver.close();

	}
}
