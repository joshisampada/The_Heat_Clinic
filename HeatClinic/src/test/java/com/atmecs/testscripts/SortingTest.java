package com.atmecs.testscripts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atmecs.constants.ProjectPathConstants;
import com.atmecs.testsuite.TestBase;
import com.atmecs.utils.PropertiesUtil;

/**
 * The SortingTest program implements an application that 
 * simply compares the prices & displays it in ascending 
 * and descending order
 * 
 * @author Sampada.Joshi
 *
 */
public class SortingTest extends TestBase
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
     *The method is regarding to the sorting of price
     *present on the webpage 
     */

	@Test
	public void sortData()
	{
		driver.get("http://10.10.10.232:8080/");

		//To navigate to the Clearance Page
		driver.findElement(By.xpath(objectrepoProps.getProperty("navigateToClearancePage"))).click();

		//Taking the price on the page in List & considering as original list
		List<WebElement> prices = driver.findElements(By.xpath(objectrepoProps.getProperty("originalPriceList")));
		List<Double> originalList = new ArrayList<>();

		// To get the data present on page
		for(WebElement price:prices) 
		{
			String actual = price.getText();
			originalList.add(Double.parseDouble(actual.replace("$", "")));
			System.out.println(actual);
		}
		System.out.println("original :"+originalList);

		//To sort the collected data in ascending order
		List<Double> expectedAscendingList = originalList;
		Collections.sort(expectedAscendingList);
		System.out.println("asc :"+expectedAscendingList);

		//To check whether data is getting sorted ascending on the page by click
		driver.findElement(By.xpath(objectrepoProps.getProperty("ascendingPricelink"))).click();

		//Taking the data list after sorting
		List<WebElement> sorted = driver.findElements(By.xpath(objectrepoProps.getProperty("postClickPriceList")));
		List<Double> actualListAfterSort = new ArrayList<>();

		//To get the data present on page after sorting
		for(WebElement price:sorted) 
		{
			String actual = price.getText();
			actualListAfterSort.add(Double.parseDouble(actual.replace("$", "")));
			System.out.println(actual);
		}
		System.out.println("after sort click :"+actualListAfterSort);

		System.out.println(actualListAfterSort);
		System.out.println(expectedAscendingList);
		Assert.assertEquals(actualListAfterSort, expectedAscendingList);

		//To check whether data is getting sorted descending
		List<Double> expectedDesceningList = expectedAscendingList;
		Collections.reverse(expectedAscendingList);
		System.out.println("desc :"+expectedDesceningList);

		//To check whether data is getting sorted descending
		driver.findElement(By.xpath(objectrepoProps.getProperty("descendingPriceLink"))).click();

		//Taking the data list after sorting
		List<WebElement> sorted1 = driver.findElements(By.xpath(objectrepoProps.getProperty("postClickPriceDisplay")));
		List<Double> actualListAfterSort1 = new ArrayList<>();

		//To get the data present on page after sorting
		for(WebElement price:sorted1) 
		{
			String actual1 = price.getText();
			actualListAfterSort1.add(Double.parseDouble(actual1.replace("$", "")));
			System.out.println(actual1);
		}
		System.out.println("after sort click :"+actualListAfterSort1);

		System.out.println(actualListAfterSort1);
		System.out.println(expectedDesceningList);
		Assert.assertEquals(actualListAfterSort1, expectedDesceningList);
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


