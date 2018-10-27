package by.bntu.lms.tests.smoke.professor.negative;

import by.bntu.lms.data.TestData;
import by.bntu.lms.pages.common.LoginPage;
import org.testng.annotations.Test;

public class ProfessorFeature {
    LoginPage loginPage;
    String ADMIN_LOGIN;
    String ADMIN_PASSWORD;

    public ProfessorFeature(LoginPage loginPage, String ADMIN_LOGIN, String ADMIN_PASSWORD) {
        this.loginPage = loginPage;
        this.ADMIN_LOGIN = ADMIN_LOGIN;
        this.ADMIN_PASSWORD = ADMIN_PASSWORD;
    }

    public ProfessorFeature(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    @Test
    public void addProfessorTest(TestData testData) {
        loginPage.loginUsingCredentials(ADMIN_LOGIN, ADMIN_PASSWORD).chooseProfessorsTab().addProfessor(
                testData.getProfessorLogin(), testData.getProfessorPassword(), testData.getProfessorSurname(),
                testData.getProfessorName(), testData.getProfessorPatronymic())
                .checkFailedMessage(testData.getErrorMessage());
    }

    @Test()
    public void changeProfessorTest(TestData testData) {
        loginPage.loginUsingCredentials(ADMIN_LOGIN, ADMIN_PASSWORD).chooseProfessorsTab().
                changeProfessorInformation(testData.getProfessorLogin(), testData.getChangedProfessorName(),
                        testData.getChangedProfessorSurname(), testData.getChangedProfessorPatronymic()).
                checkFailedMessage(testData.getErrorMessage());
    }
}
