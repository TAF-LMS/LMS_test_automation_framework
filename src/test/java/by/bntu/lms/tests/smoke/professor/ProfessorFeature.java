package by.bntu.lms.tests.smoke.professor;

import by.bntu.lms.data.TestData;
import by.bntu.lms.pages.common.LoginPage;
import lombok.Data;
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
                testData.getProfessorName(), testData.getProfessorPatronymic());
    }

    @Test
    public void loginAsProfessorTest(TestData testData) {
        loginPage.loginAsProfessor(testData.getProfessorLogin(), testData.getProfessorPassword()).
                checkThatLoginIsSuccessful();
    }

    @Test
    public void createNewSubjectTest(TestData testData) {
        loginPage.loginAsProfessor(testData.getProfessorLogin(), testData.getProfessorPassword()).
                moveToSubjectManagementPage().addNewSubject(testData.getSubjectName(),
                testData.getSubjectAbbreviation(), testData.getSubjectGroups());
    }

    @Test
    public void applyStudentTest(TestData testData) {
        loginPage.loginAsProfessor(testData.getProfessorLogin(), testData.getProfessorPassword()).
                moveToStudentManagementPage().applyStudent(testData.getStudentName(),
                testData.getStudentGroupNumber());
    }

    @Test()
    public void changeProfessorTest(TestData testData) {
        loginPage.loginUsingCredentials(ADMIN_LOGIN, ADMIN_PASSWORD).chooseProfessorsTab().
                changeProfessorInformation(testData.getProfessorLogin(), testData.getChangedProfessorName(),
                        testData.getChangedProfessorSurname(), testData.getChangedProfessorPatronymic());
    }

    @Test()
    public void searchProfessorTest(TestData testData) {
        loginPage.loginUsingCredentials(ADMIN_LOGIN, ADMIN_PASSWORD).chooseProfessorsTab().
                searchProfessor(testData.getProfessorSurname()).
                checkProfessorInSearchResults(testData.getProfessorSurname());
    }

    @Test()
    public void removeProfessorTest(TestData testData) {
        loginPage.loginUsingCredentials(ADMIN_LOGIN, ADMIN_PASSWORD).chooseProfessorsTab().
                removeProfessor(testData.getProfessorLogin());
    }
}
