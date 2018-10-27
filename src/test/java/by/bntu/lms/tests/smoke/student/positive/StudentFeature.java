package by.bntu.lms.tests.smoke.student.positive;

import by.bntu.lms.pages.common.LoginPage;
import by.bntu.lms.data.TestData;
import org.testng.annotations.Test;


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
    public void addStudentTest(TestData testData) {
        loginPage.moveToRegistration().registerStudent(testData.getStudentLogin(), testData.getStudentPassword(),
                testData.getStudentName(), testData.getStudentSurname(), testData.getStudentPatronymic(),
                testData.getStudentGroupNumber());
    }

    @Test()
    public void loginStudentTest(TestData testData) {
        loginPage.loginAsStudent(testData.getStudentLogin(), testData.getStudentPassword()).
                checkThatLoginIsSuccessful();
    }

    @Test()
    public void changeStudentTest(TestData testData) throws InterruptedException {
        loginPage.loginUsingCredentials(ADMIN_LOGIN, ADMIN_PASSWORD).chooseStudentsTab().changeStudent(
                testData.getStudentLogin(), testData.getChangedStudentName(), testData.getChangedStudentSurname(),
                testData.getChangedStudentPatronymic(), testData.getChangedStudentGroupNumber())
                .checkSuccessfulNotification();
    }

    @Test()
    public void changeStudentPassword(TestData testData) throws InterruptedException {
        loginPage.loginUsingCredentials(ADMIN_LOGIN, ADMIN_PASSWORD)
                .chooseStudentsTab()
                .changeStudentPassword(testData.getStudentLogin(), testData.getChangedStudentPassword())
                .checkSuccessfulNotification();
    }

    @Test()
    public void removeStudentTest(TestData testData) {
        loginPage.loginUsingCredentials(ADMIN_LOGIN, ADMIN_PASSWORD).chooseStudentsTab().removeStudent(
                testData.getStudentLogin());
    }
}
