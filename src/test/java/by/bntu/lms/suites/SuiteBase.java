package by.bntu.lms.suites;

import by.bntu.lms.driver.Driver;
import by.bntu.lms.pages.common.LoginPage;
import by.bntu.lms.properties.ProjectProperties;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.Properties;

@Log4j2
public class SuiteBase {
    private String startURL;
    private String workDir;
    LoginPage loginPage;
    String adminLogin;
    String adminPassword;

    private int counter = 0;

    SuiteBase() {
        Properties properties = ProjectProperties.getInstance();
        this.adminLogin = System.getProperty("adminLogin") == null ?
                properties.getProperty("adminLogin") : System.getProperty("adminLogin");
        this.adminPassword = System.getProperty("adminPassword") == null ?
                properties.getProperty("adminPassword") : System.getProperty("adminPassword");
        this.startURL = System.getProperty("initialURL") == null ?
                properties.getProperty("initialURL") : System.getProperty("initialURL");
        this.workDir = properties.getProperty("workDir");
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws Exception {
        File workingDir = new File(workDir);
        FileUtils.forceMkdir(workingDir);
        FileUtils.cleanDirectory(workingDir);
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
        takeScreenshot(counter++ + "_" + method.getName(), workDir);
        Driver.getWebDriverInstance().close();
        Driver.getWebDriverInstance().quit();
    }

    @Attachment
    @Step
    private byte[] takeScreenshot(String screenName, String path) throws Exception {
        File scrFile = ((TakesScreenshot) Driver.getWebDriverInstance()).getScreenshotAs(OutputType.FILE);
        File file = new File(path + screenName + ".png");
        FileUtils.copyFile(scrFile, file);
        return Files.readAllBytes(file.toPath());
    }
}