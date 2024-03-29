package com.inetBanking.PageObjects;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.CacheLookup;

import org.openqa.selenium.support. FindBy; import org.openqa.selenium.support.PageFactory;

public class LoginPage {

   WebDriver Idriver;

public LoginPage (WebDriver rdriver)
	{
	Idriver=rdriver;
	PageFactory.initElements (rdriver, this);
	}


	
	@FindBy(name="uid") 
	@CacheLookup
	WebElement txtUserName;

	@FindBy (name="password")
	@CacheLookup 
	WebElement txtPassword;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	public void setUsername(String Uname )
	{
		txtUserName.sendKeys(Uname);
	}
	public void setPassword (String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	public void clickSubmit()
	{
		btnLogin.click();
	}
	
	
}