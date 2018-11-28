package by.bntu.lms.suites;

import by.bntu.lms.driver.Driver;
import by.bntu.lms.pages.AbstractPage;
import by.bntu.lms.pages.common.LoginPage;
import by.bntu.lms.properties.ProjectProperties;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

@Log4j2
public class SuiteBase {
    public static WebDriver driver;

    protected AbstractPage abstractPage;
    protected LoginPage loginPage;

    protected String adminLogin;
    protected String adminPassword;
    protected String startURL;
    protected String browserType;
    protected String extentConfig;
    protected String reportLocation;
    protected String workDir;

    private int counter = 0;

    //for reports
    private static ExtentReports extent;
    private static ExtentTest test;

    SuiteBase() throws IOException {
        Properties properties = ProjectProperties.getInstance();
        this.adminLogin = properties.getProperty("adminLogin");
        this.adminPassword = properties.getProperty("adminPassword");
        this.startURL = properties.getProperty("initialURL");
        this.browserType = properties.getProperty("browserType");
        this.extentConfig = properties.getProperty("extentConfig");
        this.reportLocation = properties.getProperty("reportLocation");
        this.workDir = properties.getProperty("workDir");
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws Exception {
        FileUtils.cleanDirectory(new File(workDir));
        //Report Directory and Report Name
        extent = new ExtentReports();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportLocation);
        extent.attachReporter(htmlReporter);
        htmlReporter.loadXMLConfig(new File(extentConfig), false);
        extent.setSystemInfo("Environment", "Test environment");
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        driver.quit();
        extent.flush();// save results
    }

    @BeforeMethod(alwaysRun = true)
    public void callReports(Method method) {
        test = extent.createTest((this.getClass().getSimpleName() + " :: " + method.getName()), method.getName());
        test.assignAuthor("LMS Test Bot"); //Test Script Author Name
        test.assignCategory("Smoke  :: " + "admin functionality" + " :: API VERSION - " + "v1.1");
    }

    @BeforeMethod(alwaysRun = true)
    public void init() throws Exception {
        driver = Driver.getWebDriverInstance(browserType);
        loginPage = new LoginPage(driver);
        abstractPage = new AbstractPage(driver);
        driver.get(startURL);
        driver.manage().window().fullscreen();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(Method method, ITestResult result) throws Exception {
        String screenShootPath = abstractPage.takeScreenshot(counter++ + "_" + method.getName(), workDir);
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(screenShootPath).build());
        } else {
            test.fail("passed", MediaEntityBuilder.createScreenCaptureFromPath(screenShootPath).build());
        }
        driver.close();
    }
}
