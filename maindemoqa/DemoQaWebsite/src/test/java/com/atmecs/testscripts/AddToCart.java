package com.atmecs.testscripts;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.atmecs.helper.ExcelReading;
import com.atmecs.helper.Helper;
import com.atmecs.testsuite.TestBase;

public class AddToCart extends TestBase
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
		Helper.clickOnElement("loc.dropdown.color");
		Helper.selectFromDropdown("loc.dropdown.color", color);
		Helper.clickOnElement("loc.dropdown.size");
		Helper.selectFromDropdown("loc.dropdown.size", size);
		Helper.clickOnElement("loc.value.quantity");
		Helper.sendkeysToElement("loc.value.quantity",quantity);
		Helper.clickOnElement("loc.img.addtocart");		
	}
	
	@Test(priority=2)
	public void removeProduct() 
	{
		double price1=Double.parseDouble(Helper.getTextOfElement("loc.text.product1price").substring(1));
		log.printLog("The price of first product is:" +price1);
		
		
		Helper.clickOnElement("loc.symbol.removeproduct");
		
		Helper.clickOnElement("loc.btn.update");
		
		double price2=Double.parseDouble(Helper.getTextOfElement("loc.text.product1price").substring(1));
		log.printLog("The price of product after removal is:" +price2);
		
		double price3=Double.parseDouble(Helper.getTextOfElement("loc.text.product2price").substring(1));
		log.printLog("The price of second product is:" +price3);
		
		double grandTotal=price1+price3;
		log.printLog("The price grandtotal of products before removal is:" +grandTotal);
		
		
		double total=Double.parseDouble(Helper.getTextOfElement("loc.text.totalamount").substring(1));
		log.printLog("The total price of product is:" +total);
		double grandTotal1=price2+price3;
		log.printLog("The price grandtotal of products after removal is:" +grandTotal1);
		Assert.assertEquals(total, grandTotal1,"grandtotal is verified");	
		}
	
}
