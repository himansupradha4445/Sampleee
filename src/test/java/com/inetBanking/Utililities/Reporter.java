package com.inetBanking.Utililities;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeSuite;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public abstract class Reporter{

    public RemoteWebDriver driver;
    public static ExtentReports extent;
    public  ExtentTest test;
    public String testcaseName, testcaseDec, author ; 
    public String category;
    private Logger log=Logger.getLogger(Reporter.class);
    
    @BeforeSuite (alwaysRun = true)
    public void startReport(ITestContext c) throws IOException 
    {
        String timeStamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
        System.setProperty("org.freemarker.loggerLibrary", "none");
        try {
            Properties prop=new Properties();
            prop.load(new FileInputStream("./Resources/log4j.properties"));
            PropertyConfigurator.configure(prop);
            } catch (Exception e) {
            log.error(e);
        }
        log.debug("Configuring Extent Report...");
        ExtentSparkReporter reporter;
        String extentreportpath;
        String reportName=this.getClass().getName().substring(29, 33).toUpperCase() +" - Test Report";
        String suiteName = c.getCurrentXmlTest().getSuite().getName()+"-Test Report";
        if (suiteName.contains("Default suite")||suiteName.contains("Failed suite"))
        {
            suiteName =reportName;
        }
        extentreportpath="./reports/"+suiteName+"_"+timeStamp+".html";
        reporter = new ExtentSparkReporter(extentreportpath);
        extent   = new ExtentReports(); 
        extent.attachReporter(reporter);
        try {
        	reporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		} catch (IOException e) {
		    e.printStackTrace(); // Handle the exception, you can log the error or take other actions here
		}
       
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setReportName(suiteName);
        log.info("Extent Report Configured Successfully");
    }

   


    public abstract long takeSnap();
    
    public void reportStep(String desc,String status,boolean bSnap)
    {
        Media img=null;
        if(bSnap && !status.equalsIgnoreCase("INFO"))
        {
            long snapNumber=100000L;
            snapNumber=takeSnap();
            img=MediaEntityBuilder.createScreenCaptureFromPath("images/"+snapNumber+".jpg").build();
        }
        if(status.equalsIgnoreCase("pass"))
        {
            test.log(Status.PASS, desc, img);
        }
        else if(status.equalsIgnoreCase("fail"))
        {
            test.log(Status.FAIL, desc, img);
        }
        else if(status.equalsIgnoreCase("INFO"))
        {
            test.log(Status.INFO, desc,img);
        }
    }

    public void reportStep(String desc,String status)
    {

        reportStep(desc,status,true);
    }


    @AfterSuite (alwaysRun=true )
    public void stopReport() 
    {
        log.debug("Stopping and preparing the report...");
        extent.flush();
        log.info("Report prepared successfully");
    }
}