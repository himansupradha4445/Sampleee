package com.inetBanking.Utililities;

import com.aventstack.extentreports.ExtentTest;

public class CustomExtentTest {
    
    private CustomExtentTest() {        
    }
    
    private static final ThreadLocal<CustomExtentTest> _localStorage = new ThreadLocal<CustomExtentTest>(){
        protected CustomExtentTest initialValue() {
          return new CustomExtentTest();
       }
      };
    
    
    ExtentTest testextent;

    public ExtentTest getExtentTest() {
        return this.testextent;
    }

    public void setExtentTest(ExtentTest testextent) {
        this.testextent = testextent;
    }

	public static CustomExtentTest getInstance() {
		// TODO Auto-generated method stub
		return _localStorage.get();
	}

}