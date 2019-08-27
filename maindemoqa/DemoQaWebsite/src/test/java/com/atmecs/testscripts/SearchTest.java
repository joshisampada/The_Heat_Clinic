package com.atmecs.testscripts;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.atmecs.helper.ExcelReading;
import com.atmecs.helper.Helper;
import com.atmecs.testsuite.TestBase;

public class SearchTest extends TestBase 
{
	@DataProvider
	public Object[][] getExcelData()
	{
		Object[][] data = ExcelReading.getTestData("Sheet1");
		return data;
	}

	@Test(dataProvider="getExcelData",priority=1)
		public void selectProduct(String product, String size, String color, String quantity) throws InterruptedException 
		{
			Helper.clickOnElement("loc.link.search");
			Helper.sendkeysToElement("loc.link.product",product);
			scrollDown(driver);
			Helper.clickOnElement("loc.dropdown.color");
			Helper.selectFromDropdown("loc.dropdown.color", color);
			Helper.clickOnElement("loc.dropdown.size");
			Helper.selectFromDropdown("loc.dropdown.size", size);
			Helper.clickOnElement("loc.value.quantity");
			Helper.sendkeysToElement("loc.value.quantity",quantity);
					
		}
	
	public void scrollDown(WebDriver driver) 
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,1000)");
	}
		@Test(priority=2)
		public void clickOnCart() throws InterruptedException
		{
			Helper.clickOnElement("loc.img.addtocart");
			double price1=Double.parseDouble(Helper.getTextOfElement("loc.text.product1price").substring(1));
			log.printLog("The price of first product is:" +price1);
			double price2=Double.parseDouble(Helper.getTextOfElement("loc.text.product2price").substring(1));
			log.printLog("The price of second product is:" +price2);
			double total=Double.parseDouble(Helper.getTextOfElement("loc.text.totalamount").substring(1));
			log.printLog("The total price of product is:" +total);
			double grandTotal=price1+price2;
			log.printLog("The grandtotal of both product price is:" +grandTotal);
			Assert.assertEquals(total, grandTotal,"grandtotal is verified");	
			
		}
		

		@Test(priority=3)
		public void getTableCount() {
			int count=Helper.getCountOfElement("loc.div.rowsoftable");
			log.printLog("The number of products added to cart is:" +count);
			Assert.assertEquals(count, 2,"products are added to cart");
		}
		
	}
