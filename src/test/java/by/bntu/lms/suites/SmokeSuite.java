package by.bntu.lms.suites;

import by.bntu.lms.data.TestData;
import by.bntu.lms.driver.Driver;
import by.bntu.lms.pages.AbstractPage;
import by.bntu.lms.pages.common.LoginPage;

import by.bntu.lms.tests.smoke.group.GroupFeature;
import by.bntu.lms.tests.smoke.professor.ProfessorFeature;
import by.bntu.lms.tests.smoke.student.StudentFeature;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;

import static by.bntu.lms.data.DataReader.dataReaderGeneric;
import static org.openqa.selenium.remote.BrowserType.CHROME;

@Test(groups = {"smoke"})
public class SmokeSuite {

    private final String STUDENT_DATA_PATH = "./src/test/resources/data/Smoke_StudentFeature.xls";
    private final String PROFESSOR_DATA_PATH = "./src/test/resources/data/Smoke_ProfessorFeature.xls";
    private final String GROUP_DATA_PATH = "./src/test/resources/data/Smoke_GroupFeature.xls";

    public static WebDriver driver;
    protected final String START_URL = "http://172.16.11.72:2015/Account/Login";
    protected final String ADMIN_LOGIN = "admin";
    protected final String ADMIN_PASSWORD = "123456";

    protected AbstractPage abstractPage;
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
    public void callReports(Method method) {
        test = extent.startTest((this.getClass().getSimpleName() + " :: " + method.getName()), method.getName());
        test.assignAuthor("LMS Test Bot"); //Test Script Author Name
        test.assignCategory("Smoke  :: " + "admin functionality" + " :: API VERSION - " + "v1.1");
    }

    @BeforeMethod(alwaysRun = true)
    public void init() throws Exception {
        driver = Driver.getWebDriverInstance(CHROME);
        loginPage = new LoginPage(driver);
        abstractPage = new AbstractPage(driver);
        driver.get(START_URL);
        driver.manage().window().fullscreen();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(Method method) throws Exception {
        abstractPage.takeScreenshot(method.getName());
        driver.quit();
    }

    //Student
    @DataProvider()
    public Object[][] addStudentData() throws Exception {
        return dataReaderGeneric(STUDENT_DATA_PATH, "AddStudent");
    }

    @DataProvider()
    public Object[][] changeStudentData() throws Exception {
        return dataReaderGeneric(STUDENT_DATA_PATH, "ChangeStudent");
    }

    @DataProvider()
    public Object[][] removeStudentData() throws Exception {
        return dataReaderGeneric(STUDENT_DATA_PATH, "RemoveStudent");
    }

    //Professor
    @DataProvider()
    public Object[][] addProfessorData() throws Exception {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "AddProfessor");
    }

    @DataProvider()
    public Object[][] changeProfessorData() throws Exception {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "ChangeProfessor");
    }

    @DataProvider()
    public Object[][] searchProfessorData() throws Exception {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "SearchProfessor");
    }

    @DataProvider()
    public Object[][] removeProfessorData() throws Exception {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "DeleteProfessor");
    }

    //Group
    @DataProvider()
    public Object[][] addGroupData() throws Exception {
        return dataReaderGeneric(GROUP_DATA_PATH, "AddGroup");
    }

    @DataProvider()
    public Object[][] changeGroupData() throws Exception {
        return dataReaderGeneric(GROUP_DATA_PATH, "ChangeGroup");
    }

    @DataProvider()
    public Object[][] removeEmptyGroupData() throws Exception {
        return dataReaderGeneric(GROUP_DATA_PATH, "RemoveEmptyGroup");
    }

    @DataProvider()
    public Object[][] removeGroupWithStudentsData() throws Exception {
        return dataReaderGeneric(GROUP_DATA_PATH, "RemoveGroupWithStudents");
    }

    //Student
    @Test(priority = 3, dataProvider = "addStudentData")
    public void addStudentTest(TestData testData) throws Exception {
        new StudentFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).addStudentTest(testData);
    }

    @Test(priority = 4, dataProvider = "changeStudentData")
    public void changeStudentTest(TestData testData) throws Exception {
        new StudentFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).changeStudentTest(testData);
    }

    @Test(priority = 6, dataProvider = "removeStudentData")
    public void removeStudentTest(TestData testData) throws Exception {
        new StudentFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).removeStudentTest(testData);
    }

    //Professor
    @Test(priority = 8, dataProvider = "addProfessorData")
    public void addProfessorTest(TestData testData) throws Exception {
        new ProfessorFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).addProfessorTest(testData);
    }

    @Test(priority = 9, dataProvider = "changeProfessorData")
    public void changeProfessorTest(TestData testData) throws Exception {
        new ProfessorFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).changeProfessorTest(testData);
    }

    @Test(priority = 10, dataProvider = "searchProfessorData")
    public void searchProfessorTest(TestData testData) throws Exception {
        new ProfessorFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).searchProfessorTest(testData);
    }

    @Test(priority = 11, dataProvider = "removeProfessorData")
    public void removeProfessorTest(TestData testData) throws Exception {
        new ProfessorFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).removeProfessorTest(testData);
    }

    //Group
    @Test(priority = 1, dataProvider = "addGroupData")
    public void addGroupTest(TestData testData) throws Exception {
        new GroupFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).addGroupTest(testData);
    }

    @Test(priority = 2, dataProvider = "changeGroupData")
    public void changeGroupTest(TestData testData) throws Exception {
        new GroupFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).changeGroupTest(testData);
    }

    //create
    @Test(priority = 7, dataProvider = "removeEmptyGroupData")
    public void removeEmptyGroupTest(TestData testData) throws Exception {
        new GroupFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).removeEmptyGroupTest(testData);
    }

    @Test(priority = 5, dataProvider = "removeGroupWithStudentsData")
    public void removeGroupWithStudentsTest(TestData testData) throws Exception {
        new GroupFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).removeGroupWithStudents(testData);
    }
}
