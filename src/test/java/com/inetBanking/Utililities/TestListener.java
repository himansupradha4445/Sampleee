package com.inetBanking.Utililities;

import java.util.Iterator;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


public class TestListener  extends TestListenerAdapter {
    private static ExtentReports extent;
    public ExtentTest test;
    public TestListener() {
        
        this.test = CustomExtentTest.getInstance().getExtentTest();
        
    }
    
    @Override
    public void onFinish(ITestContext context) {
        Iterator<ITestResult> skippedTestCases = context.getSkippedTests().getAllResults().iterator();
        while (skippedTestCases.hasNext()) {
            ITestResult skippedTestCase = skippedTestCases.next();
            ITestNGMethod method = skippedTestCase.getMethod();
            if (context.getSkippedTests().getResults(method).size() > 0) {
                System.out.println("Removing:" + skippedTestCase.getTestClass().toString());
                skippedTestCases.remove();
                extent.removeTest(test);
            }
        }
    }

    public void onTestStart(ITestResult result) { 
        
        
    }

    public void onTestSuccess(ITestResult result) { 
        
        
    }

    public void onTestFailure(ITestResult result) {  
        

        
    }

    public void onTestSkipped(ITestResult result) {
        
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

    public void onStart(ITestContext context) {
        
    }

}