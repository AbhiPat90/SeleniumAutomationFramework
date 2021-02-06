package com;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.components.BasePage;
import com.config.EnvironmentProperties;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected EnvironmentProperties envProp = ConfigFactory.create(EnvironmentProperties.class);

    public WebDriver driver;
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest logger;
    public String site_URL;

    public void browserSetup(String browserName) {
        if("Firefox".equalsIgnoreCase(browserName)) {
            // open firefox browser
            System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver");
            driver = new FirefoxDriver();
        }else{
            // open chrome browser set as default
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
    }

    public void getTestUrl(){
        // get url
        site_URL = envProp.getPracticeURL();
    }

    @BeforeTest
    public void startReport(){
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/target/results/OnlineTestExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Host Name","AlphaSense");
        extent.setSystemInfo("Candidate Name","Abhishek Patre");
        sparkReporter.config().setDocumentTitle("Online Test Report");
        sparkReporter.config().setReportName("UI Automation Test");
        sparkReporter.config().setTheme(Theme.DARK);
    }

    @AfterMethod
    public void getResult(ITestResult result) throws Exception{
        if(result.getStatus() == ITestResult.FAILURE){
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
            new BasePage(driver).getScreenShot(result.getName());
        }
        else if(result.getStatus() == ITestResult.SKIP){
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.BLUE));
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        driver.quit();
    }

    @AfterTest
    public void endReport(){
        extent.flush();
    }

}
