package com.atmecs.testscripts;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atmecs.constants.ProjectPathConstants;
import com.atmecs.testsuite.TestBase;
import com.atmecs.utils.PropertiesUtil;

/**
 * 
 * The AddToCart program implements the functionality
 * for the Adding product to cart.
 *  
 * @author Sampada.Joshi
 *
 */
public class AddToCart extends TestBase
{
	WebDriver driver;
	static Properties configProps = PropertiesUtil.loadProperty(ProjectPathConstants.config);
	//verifyAssertion a=new verifyAssertion();

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
	 * The method is regarding to adding the product 
	 * to the cart
	 * 
	 */
	@Test
	public void addToCart() 
	{
		//Verifying whether user is navigated to clearance page by using title
		String baseUrl = "http://10.10.10.232:8080/clearance";
		String expectedTitle = "Broadleaf Commerce Demo Store - Heat Clinic - Clearance";
		String actualTitle = "";
		driver.get(baseUrl);
		actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle, expectedTitle);
		//a.verifyAssertion(actualTitle, expectedTitle, "msg");

		//Verifying whether the particular image is opening after clicking or not by PageTitle
		driver.findElement(By.xpath(objectrepoProps.getProperty("imageClick"))).click();
		String expectedImgTitle = "Broadleaf Commerce Demo Store - Heat Clinic - Green Ghost";
		String actualImgTitle = "";
		actualImgTitle = driver.getTitle();
		System.out.println(actualImgTitle);
		Assert.assertEquals(actualImgTitle, expectedImgTitle);

		//Verifying whether the notification message is displaying after clicking on Buy Now option
		driver.findElement(By.xpath(objectrepoProps.getProperty("BuyNowbtn"))).click();

		String Message=driver.findElement(By.xpath(objectrepoProps.getProperty("notification"))).getText();
		System.out.println(Message);

		//Fluent wait
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
				.withTimeout(30, TimeUnit.SECONDS) 			
				.pollingEvery(5, TimeUnit.SECONDS) 			
				.ignoring(NoSuchElementException.class);	

		String expectedMessage = "GREEN GHOST HAS BEEN ADDED TO THE CART!";
		String actualMessage = "";
		actualMessage = Message;
		System.out.println(actualMessage);
		Assert.assertEquals(actualMessage, expectedMessage);

		//To navigate to the Clearance Page
		driver.findElement(By.xpath(objectrepoProps.getProperty("navigateToClearancePage"))).click();

		//Verify whether the product is added to cart
		String productInCart=driver.findElement(By.xpath(objectrepoProps.getProperty("getItemCount"))).getText();
		System.out.println(productInCart);
		driver.findElement(By.xpath(objectrepoProps.getProperty("addToCart"))).click();;
		String productInCart1=driver.findElement(By.xpath(objectrepoProps.getProperty("getItemCount"))).getText();
		System.out.println(productInCart1);
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





