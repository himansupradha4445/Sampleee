package com.inetBanking.TestCases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.inetBanking.Utililities.ReadConfig;

public class BaseClass {
	
	ReadConfig readconfig=new ReadConfig();
	public String baseURL=readconfig.getUrl();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		logger=Logger.getLogger("EBANKING "); 
		PropertyConfigurator.configure("log4j.properties");

			if(br.equals("chrome"))

			{

			System.setProperty("webdriver.chrome.driver", readconfig.getChromepath()); 
			driver=new ChromeDriver();

			}

			else if(br.equals("firefox"))

			{

			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxpath());
			driver = new FirefoxDriver();

			}

			else if(br.equals("ie"))

			{

			System.setProperty("webdriver.ie.driver", readconfig.getIEpath()); 
			driver = new InternetExplorerDriver();

			} 
			driver.get(baseURL);
		
	}
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
