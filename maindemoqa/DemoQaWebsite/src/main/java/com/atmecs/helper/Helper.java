package com.atmecs.helper;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.atmecs.DemoQaWebsite.constants.ProjectPathConstants;
import com.atmecs.testsuite.TestBase;
import com.atmecs.utils.ReadPropertyFile;


public class Helper extends TestBase
{
	public static WebElement returnWebElement(String element){
	
		String locator_value=ReadPropertyFile.readProperty(ProjectPathConstants.locator_path,element);
		WebDriverWait wait = new WebDriverWait(driver, 30); 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator_value)));
		WebElement element1 = driver.findElement(By.xpath(locator_value));
		return element1;
	}
	public static void clickOnElement(String element) {
		String locator_value=ReadPropertyFile.readProperty(ProjectPathConstants.locator_path,element);
		WebDriverWait wait = new WebDriverWait(driver, 30); 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator_value)));
		WebElement element1 = driver.findElement(By.xpath(locator_value));
		element1.click();
		
	}
	
	
	public static void sendkeysToElement(String element,String valueToSend)
	{
		String locator_value=ReadPropertyFile.readProperty(ProjectPathConstants.locator_path,element);
		driver.findElement(By.xpath(locator_value)).clear();
		driver.findElement(By.xpath(locator_value)).sendKeys(valueToSend+Keys.ENTER);
	}
	
	
	
	
	public static void selectFromDropdown(final String element,final String valueToSelect) throws InterruptedException
	{  
		final String locator_value=ReadPropertyFile.readProperty(ProjectPathConstants.locator_path,element);
//		System.out.println("locator value is "+locator_value);
	   FluentWait<WebDriver> fluentWait= new FluentWait<WebDriver>(driver).pollingEvery(1, TimeUnit.SECONDS)
			   .withTimeout(30, TimeUnit.SECONDS);
	   fluentWait.until(new Function<WebDriver, Boolean>() {
		   public Boolean apply(WebDriver driver) {
			   WebElement element1= driver.findElement(By.xpath(locator_value));
				Select select= new Select(element1);
				select.selectByVisibleText(valueToSelect);
				   return true;
		   }
		
	});
		
	}
	public static String getTextOfElement(String element)
	{
		String locator_value=ReadPropertyFile.readProperty(ProjectPathConstants.locator_path,element);
		String text=driver.findElement(By.xpath(locator_value)).getText();
		return text;
	}
	public static int getCountOfElement(String element)
	{
		String locator_value=ReadPropertyFile.readProperty(ProjectPathConstants.locator_path,element);
		List<WebElement> count = driver.findElements(By.xpath(locator_value));
		 return count.size();
	}
	
}
