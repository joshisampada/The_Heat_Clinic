package com.atmecs.testscripts;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
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
 * The CheckoutTest program implements the functionality
 * of updating the no of items and payment regarding. 
 * 
 * @author Sampada.Joshi
 *
 */
public class CheckoutTest extends TestBase
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
	 * The method is regarding to checkout process
	 * after buying the product
	 * 
	 */
	@Test
	public void checkout() 
	{

		//To navigate to the Clearance Page
		driver.findElement(By.xpath(objectrepoProps.getProperty("navigateToClearancePage"))).click();

		//To click on Buy Now option
		driver.findElement(By.xpath(objectrepoProps.getProperty("BuyNowbtn"))).click();

		//To click on cart
		driver.findElement(By.xpath(objectrepoProps.getProperty("clickCart"))).click();

		driver.findElement(By.xpath(objectrepoProps.getProperty("Quantity"))).clear();

		driver.findElement(By.xpath(objectrepoProps.getProperty("Quantity"))).sendKeys("5");

		List<WebElement> listElement = driver.findElements(By.id("subtotal"));
		for(int i =0;i<listElement.size();i++)
		{
			String elementText = listElement.get(i).getText(); 
			System.out.println(elementText); 
		}

		driver.findElement(By.xpath(objectrepoProps.getProperty("updateClick"))).click();

		//Fluent wait
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
				.withTimeout(30, TimeUnit.SECONDS) 			
				.pollingEvery(5, TimeUnit.SECONDS) 			
				.ignoring(NoSuchElementException.class);	

		List<WebElement> listElement1 = driver.findElements(By.id("subtotal"));
		for(int i =0;i<listElement1.size();i++)
		{
			String elementText1 = listElement1.get(i).getText(); 
			System.out.println(elementText1); 
		}
		if (listElement.equals(listElement1)) 
			System.out.println("Equal - There is no reflection in price"); 
		else
			System.out.println("Not equal - There is reflection in price");

		//Click on checkout button
		driver.findElement(By.xpath(objectrepoProps.getProperty("checkoutBtn"))).click();

		//Verifying whether user is navigated to checkout page by using title after clicking on checkout btn
		String baseUrl = "http://10.10.10.232:8080/checkout";
		String expectedTitle = "Broadleaf Commerce Demo Store - Heat Clinic - Checkout";
		String actualTitle = "";
		driver.get(baseUrl);
		actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle, expectedTitle);

		//Click on link to open product info
		driver.findElement(By.xpath(objectrepoProps.getProperty("productInfoLink"))).click();

		//Verifying whether user is navigated to particular product page by using title 
		String baseUrl1 = "http://10.10.10.232:8080/hot-sauces/green_ghost";
		String expectedTitle1 = "Broadleaf Commerce Demo Store - Heat Clinic - Green Ghost";
		String actualTitle1 = "";
		driver.get(baseUrl1);
		actualTitle1 = driver.getTitle();
		System.out.println(actualTitle1);
		Assert.assertEquals(actualTitle1, expectedTitle1);

		//Verifying whether the Edit link is properly working or not
		//To click on Edit link
		driver.findElement(By.xpath(objectrepoProps.getProperty("clickCart"))).click();
		driver.findElement(By.xpath(objectrepoProps.getProperty("checkoutBtn"))).click();
		driver.findElement(By.xpath(objectrepoProps.getProperty("clickOnEdit"))).click();

		//To clear the quantity
		driver.findElement(By.xpath(objectrepoProps.getProperty("Quantity1"))).clear();

		//To give the input to quantity
		driver.findElement(By.xpath(objectrepoProps.getProperty("Quantity1"))).sendKeys("3");

		List<WebElement> element = driver.findElements(By.id("subtotal"));
		for(int i =0;i<element.size();i++)
		{
			String elementText = element.get(i).getText(); 
			System.out.println(elementText); 
		}
		driver.findElement(By.xpath(objectrepoProps.getProperty("updateClick"))).click();

		//Fluent wait
		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)							
				.withTimeout(30, TimeUnit.SECONDS) 			
				.pollingEvery(5, TimeUnit.SECONDS) 			
				.ignoring(NoSuchElementException.class);	

		List<WebElement> element1 = driver.findElements(By.id("subtotal"));
		for(int i =0;i<element1.size();i++)
		{
			String elementText = element1.get(i).getText(); 
			System.out.println(elementText); 
		}
		if (element.equals(element1)) 
			System.out.println("Equal - There is no reflection in price"); 
		else
			System.out.println("Not equal - There is reflection in price");

		//Verifying whether as a guest user should able to buy product
		driver.findElement(By.xpath(objectrepoProps.getProperty("checkoutBtn"))).click();
		driver.findElement(By.xpath(objectrepoProps.getProperty("emailId"))).sendKeys("sam@gmail.com");
		driver.findElement(By.xpath(objectrepoProps.getProperty("guestLink"))).click();

		//Verifying whether user is able to fill billing information
		driver.findElement(By.xpath(objectrepoProps.getProperty("firstName"))).sendKeys("Ram");
		driver.findElement(By.xpath(objectrepoProps.getProperty("lastName"))).sendKeys("Sharma");
		driver.findElement(By.xpath(objectrepoProps.getProperty("contactNumber"))).sendKeys("9856321245");
		driver.findElement(By.xpath(objectrepoProps.getProperty("address"))).sendKeys("Ramnagar, Mumbai-09");
		driver.findElement(By.xpath(objectrepoProps.getProperty("state"))).sendKeys("Maharashtra");
		
		//To handle checkbox
		WebElement state = driver.findElement(By.xpath(objectrepoProps.getProperty("stateCheckbox")));
		Select oSelect = new Select(state);
		oSelect.selectByVisibleText("AP");
		
		driver.findElement(By.xpath(objectrepoProps.getProperty("postalCode"))).sendKeys("658965");
		driver.findElement(By.xpath(objectrepoProps.getProperty("billingBtn"))).click();

		//Verifying whether user is able to fill shipping information

		//Handling checkbox
		WebElement option1 = driver.findElement(By.xpath(objectrepoProps.getProperty("shippingAddressCheckbox")));
		option1.click();	

		//Handling radiobutton
		WebElement radio1 = driver.findElement(By.xpath(objectrepoProps.getProperty("Standard")));							
		WebElement radio2 = driver.findElement(By.xpath(objectrepoProps.getProperty("Priority")));
		WebElement radio3 = driver.findElement(By.xpath(objectrepoProps.getProperty("Express")));							
		radio1.click();

		//To click on select shipping option
		driver.findElement(By.xpath(objectrepoProps.getProperty("shippingBtn"))).click();

		//To verify the payment method
		WebElement radiobtn1 = driver.findElement(By.xpath(objectrepoProps.getProperty("creditCard")));							
		WebElement radiobtn2 = driver.findElement(By.xpath(objectrepoProps.getProperty("PayPal")));
		WebElement radiobtn3 = driver.findElement(By.xpath(objectrepoProps.getProperty("collectOnDelivery")));
		radiobtn3.click();
	}

	
	@AfterTest
	private void tearDown()
	{
		driver.quit();

	}
}
