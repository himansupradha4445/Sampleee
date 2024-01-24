package com.inetBanking.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.PageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass{
	
	@Test
	public void Login()
	{
		driver.get(baseURL);
		logger.info("URL IS OPENED");
		LoginPage lp=new LoginPage(driver);
		lp.setUsername(username);
		logger.info("ENTERED USERNAME");
		lp.setPassword(password);
		logger.info("ENTERED PASSWORD");
		lp.clickSubmit();
		logger.info("LOGIN TEST PASSED");
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage "))
		{
			Assert.assertTrue(true);
		}
		else
			
		{
			Assert.assertFalse(false);
		}
		
	}

}
