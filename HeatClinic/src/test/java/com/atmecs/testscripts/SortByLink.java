package com.atmecs.testscripts;

import java.util.ArrayList;
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
 * The SortByLink program implements an program 
 * that sorts the data on page according to the 
 * link selected
 * 
 * @author Sampada.Joshi
 *
 */
public class SortByLink extends TestBase
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
	 * The linksort method is regarding to the sorting of data
	 * as per the price range
	 */
	@Test
	public void linksort()
	{
		//To navigate to the Clearance Page
		driver.findElement(By.xpath(objectrepoProps.getProperty("navigateToClearancePage"))).click();

		//To click on particular price link for sorting
		driver.findElement(By.xpath("//a[@href=\"http://10.10.10.232:8080/clearance?price=range%5B0.00000%3A5.00000%5D\"]")).click();
		List<WebElement> listImages=driver.findElements(By.xpath(objectrepoProps.getProperty("imageList")));
		System.out.println("No. of Product Images: "+listImages.size());
		driver.findElement(By.xpath("//input[@value='range[0.00000:5.00000]']"));


		List<WebElement> prices = driver.findElements(By.xpath(objectrepoProps.getProperty("originalPriceList")));
		List<Double> originalList = new ArrayList<>();

		//To get the data present on page
		for(WebElement price:prices) 
		{
			String actual = price.getText();
			originalList.add(Double.parseDouble(actual.replace("$", "")));
			System.out.println(actual);
		}

		System.out.println("original :"+originalList);

		//To verify whether values are in range	
		for (double itemPrice : originalList) 
		{
			if (itemPrice>0&&itemPrice<6) 
			{
				System.out.println("The values are in range");

			}

		}
	}

	/**
	 * This method is to close the browser
	 */
	@AfterTest
	public void tearDown()
	{
		driver.quit();

	}   	
}



