package com.atmecs.testsuite;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.atmecs.DemoQaWebsite.constants.ProjectPathConstants;
import com.atmecs.reports.LogReports;
import com.atmecs.utils.Assertion;
import com.atmecs.utils.PropertiesUtil;

public class TestBase
{

	public static WebDriver driver;
	//public CommanUtils list=new CommanUtils();


	static public Properties configProps = PropertiesUtil.loadProperty(ProjectPathConstants.config);
	public static Properties objectrepoProps = PropertiesUtil.loadProperty(ProjectPathConstants.objectrepo);
	public static Assertion asert=new Assertion();
	public static LogReports log = new LogReports();

	public enum BrowserType {
		FIREFOX, CHROME, IE
	}

	public WebDriver getwebDriver() {
		return this.driver;
	}


	public WebDriver invokeBrowser() 
	{
		System.out.println("invoke");
		String browser = configProps.getProperty("browserName");
		switch (BrowserType.valueOf(browser)) {
		case CHROME:{
			this.driver = setChromeDriver();
			System.out.println("Chrome driver set "+driver);
			break;
		}
		case FIREFOX:
			System.out.println("firefox case");
			this.driver = setFirefoxDriver();
			System.out.println("Firefox driver set "+driver);
			driver.get("https://www.google.com");
			break;

		case IE:
			this.driver = setInterExplorerDriver();
			System.out.println("Internet driver set "+driver);
		default:
			break;
		}
		return driver;

	}

	private WebDriver setChromeDriver() {

		System.out.println("path");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sampada.Joshi\\Downloads\\chromedriver.exe");

		return new ChromeDriver();
	}

	private WebDriver setFirefoxDriver() {
		//String currentdir = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
		//System.setProperty("webdriver.gecko.driver", currentdir + "geckodriver.exe");
		
		
		System.setProperty("webdriver.firefox.bin",
                "C:\\Users\\Sampada.Joshi\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Sampada.Joshi\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Sampada.Joshi\\eclipse_new_demo_workspace\\DemoQaWebsite\\lib\\geckodriver.exe");
		return new FirefoxDriver();
		
		
		
		
	}

	public WebDriver setInterExplorerDriver() {
		String currentdir = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
		System.setProperty("webdriver.ie.driver", currentdir + "IEDriverServer.exe");
		return new InternetExplorerDriver();
		
		  //System.setProperty("webdriver.ie.driver",  "C:\\Users\\Sampada.Joshi\\Downloads\\IEDriverServer.exe");
			    //driver = new InternetExplorerDriver();
		  //return new InternetExplorerDriver();
	}
	
	public WebDriver windowOperations()
	{
	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	return driver;
	}
	
	@BeforeTest
	public void preSetup()
	{
		this.driver = invokeBrowser();
		String baseUrl = configProps.getProperty("applicationurl");
		driver.get(baseUrl);
		this.driver = windowOperations();
	}
	
	@AfterTest   
	public void tearDown() 
	{
		driver.close();

	}
	
}
		
