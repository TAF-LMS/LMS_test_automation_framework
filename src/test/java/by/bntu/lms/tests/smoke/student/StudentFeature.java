package by.bntu.lms.tests.smoke.student;

import by.bntu.lms.driver.Driver;
import by.bntu.lms.pages.AbstractPage;
import by.bntu.lms.pages.common.LoginPage;
import by.bntu.lms.tests.TestBase;
import by.bntu.lms.data.TestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class StudentFeature {
    LoginPage loginPage;
    String ADMIN_LOGIN;
    String ADMIN_PASSWORD;

    public StudentFeature(LoginPage loginPage, String ADMIN_LOGIN, String ADMIN_PASSWORD) {
        this.loginPage = loginPage;
        this.ADMIN_LOGIN = ADMIN_LOGIN;
        this.ADMIN_PASSWORD = ADMIN_PASSWORD;
    }

    @Test()
    public void addStudentTest(TestData testData) throws Exception {
        loginPage.moveToRegistration().registerStudent(testData.getLogin(), testData.getPassword(), testData.getName(),
                testData.getSurname(), testData.getPatronymic(), testData.getGroupNumber());
    }

    @Test()
    public void changeStudentTest(TestData testData) throws Exception {
        loginPage.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD).chooseStudentsTab().changeStudent(
                testData.getLogin(), testData.getChangedName(), testData.getChangedSurname(), testData.getChangedPatronymic(),
                testData.getChangedGroupNumber());
    }

    @Test()
    public void removeStudentTest(TestData testData) throws Exception {
        loginPage.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD).chooseStudentsTab().removeStudent(
                testData.getLogin());
    }
}
