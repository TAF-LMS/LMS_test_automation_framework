package by.bnty.lms.tests;

import by.bnty.lms.driver.Driver;
import by.bnty.lms.pages.AbstractPage;
import by.bnty.lms.pages.common.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.lang.reflect.Method;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class TestBase {

    private static WebDriver driver;
    private final String START_URL = "http://172.16.11.72:2015/Account/Login";
    private AbstractPage abstractPage;
    protected LoginPage loginPage;
    //for reports
    private static ExtentReports extent;
    private static ExtentTest test;
    public static ExtentTestInterruptedException testexception;

    @BeforeSuite
    public void beforeSuite() {
        //Report Directory and Report Name
        extent = new ExtentReports("./src/test/resources/work/api_execution_report.html", true);
        extent.loadConfig(new File("./src/test/resources/extent-config.xml")); //Supporting File for Extent Reporting
        extent.addSystemInfo("Environment", "Test environment"); //It will provide Execution Machine Information
    }

    @AfterSuite
    public void afterSuite() {
        extent.close();  // close the Test Suite
    }

    @BeforeMethod(alwaysRun = true)
    public void init() throws Exception {
        driver = Driver.getWebDriverInstance(CHROME);
        loginPage = new LoginPage(driver);
        abstractPage = new AbstractPage(driver);
        driver.get(START_URL);
        driver.manage().window().fullscreen();
    }

    @BeforeMethod(alwaysRun = true)
    public void callReports(Method method) {
        test = extent.startTest((this.getClass().getSimpleName() + " :: " + method.getName()), method.getName());
        test.assignAuthor("LMS Test Bot"); //Test Script Author Name
        test.assignCategory("Smoke  :: " + "admin functionality" + " :: API VERSION - " + "v1.1");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(Method method) throws Exception {
        abstractPage.takeScreenshot(method.getName());
        driver.quit();
    }
}
