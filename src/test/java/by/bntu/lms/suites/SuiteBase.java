package by.bntu.lms.suites;

import by.bntu.lms.driver.Driver;
import by.bntu.lms.pages.common.LoginPage;
import by.bntu.lms.properties.ProjectProperties;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Properties;

@Log4j2
public class SuiteBase {
    private String startURL;
    private String extentConfig;
    private String reportLocation;
    private String workDir;
    LoginPage loginPage;
    String adminLogin;
    String adminPassword;

    private int counter = 0;

    //for reports
    private static ExtentReports extent;
    private static ExtentTest test;

    SuiteBase() {
        Properties properties = ProjectProperties.getInstance();
        this.adminLogin = System.getProperty("adminLogin") == null ?
                properties.getProperty("adminLogin") : System.getProperty("adminLogin");
        this.adminPassword = System.getProperty("adminPassword") == null ?
                properties.getProperty("adminPassword") : System.getProperty("adminPassword");
        this.startURL = System.getProperty("initialURL") == null ?
                properties.getProperty("initialURL") : System.getProperty("initialURL");
        this.extentConfig = properties.getProperty("extentConfig");
        this.reportLocation = properties.getProperty("reportLocation");
        this.workDir = properties.getProperty("workDir");
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws Exception {
        FileUtils.forceMkdir(new File(workDir));
        //Report Directory and Report Name
        extent = new ExtentReports();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportLocation);
        extent.attachReporter(htmlReporter);
        htmlReporter.loadXMLConfig(new File(extentConfig), false);
        extent.setSystemInfo("Environment", "Test environment");
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        Driver.getWebDriverInstance().quit();
        extent.flush();// save results
    }

    @BeforeMethod(alwaysRun = true)
    public void callReports(Method method) {
        test = extent.createTest((this.getClass().getSimpleName() + " :: " + method.getName()), method.getName());
        test.assignAuthor("LMS Test Bot");
        test.assignCategory("Smoke  :: " + "admin functionality" + " :: API VERSION - " + "v1.1");
    }

    @BeforeMethod(alwaysRun = true)
    public void init() {
        Driver.setWebDriver(null);
        loginPage = new LoginPage();
        Driver.getWebDriverInstance().get(startURL);
        Driver.getWebDriverInstance().manage().window().fullscreen();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(Method method, ITestResult result) throws Exception {
        String screenShootPath = takeScreenshot(counter++ + "_" + method.getName(), workDir);
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable().getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(screenShootPath).build());
        } else {
            test.fail("passed", MediaEntityBuilder.createScreenCaptureFromPath(screenShootPath).build());
        }
        Driver.getWebDriverInstance().close();
    }

    private String takeScreenshot(String screenName, String path) throws Exception {
        File scrFile = ((TakesScreenshot) Driver.getWebDriverInstance()).getScreenshotAs(OutputType.FILE);
        File file = new File(path + screenName + ".png");
        FileUtils.copyFile(scrFile, file);
        return file.getAbsolutePath();
    }
}