package by.bntu.lms.tests.smoke.student.negative;

import by.bntu.lms.data.TestData;
import by.bntu.lms.pages.common.LoginPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.testng.annotations.Test;

@Data
@AllArgsConstructor
public class StudentFeature {
    private LoginPage loginPage;
    private String adminLogin;
    private String adminPassword;

    @Test()
    public void addStudentTest(TestData testData) {
        loginPage
                .moveToRegistration()
                .registerStudentAndBeOnPage(testData.getStudentLogin(), testData.getStudentPassword(),
                        testData.getStudentName(), testData.getStudentSurname(), testData.getStudentPatronymic(),
                        testData.getStudentGroupNumber())
                .checkFailedMessage(testData.getErrorMessage());
    }

    @Test()
    public void loginStudentTest(TestData testData) {
        loginPage
                .loginAsStudent(testData.getStudentLogin(), testData.getStudentPassword())
                .checkThatLoginIsFailed(testData.getErrorMessage());
    }

    @Test()
    public void changeStudentTest(TestData testData) throws InterruptedException {
        loginPage
                .loginUsingCredentials(adminLogin, adminPassword)
                .chooseStudentsTab()
                .changeStudent(testData.getStudentLogin(), testData.getChangedStudentName(),
                        testData.getChangedStudentSurname(), testData.getChangedStudentPatronymic(),
                        testData.getChangedStudentGroupNumber())
                .checkFailedMessage(testData.getErrorMessage());
    }

    @Test()
    public void changeStudentPassword(TestData testData) {
        loginPage
                .loginUsingCredentials(adminLogin, adminPassword)
                .chooseStudentsTab()
                .changeStudentPassword(testData.getStudentLogin(), testData.getChangedStudentPassword())
                .checkFailedMessage(testData.getErrorMessage());
    }
}
