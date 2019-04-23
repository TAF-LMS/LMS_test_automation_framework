package by.bntu.lms.tests.smoke.professor.positive;

import by.bntu.lms.data.TestData;
import by.bntu.lms.pages.common.LoginPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.testng.annotations.Test;

@Data
@AllArgsConstructor
public class ProfessorFeature {
    private LoginPage loginPage;
    private String adminLogin;
    private String adminPassword;

    @Test
    public void addProfessorTest(TestData testData) {
        loginPage
                .loginUsingCredentials(adminLogin, adminPassword)
                .chooseProfessorsTab()
                .addProfessor(testData.getProfessorLogin(), testData.getProfessorPassword(),
                        testData.getProfessorSurname(), testData.getProfessorName(), testData.getProfessorPatronymic())
                .checkSuccessfulNotification();
    }

    @Test
    public void loginAsProfessorTest(TestData testData) {
        loginPage
                .loginAsProfessor(testData.getProfessorLogin(), testData.getProfessorPassword())
                .checkThatLoginIsSuccessful();
    }

    @Test
    public void createNewSubjectTest(TestData testData) {
        loginPage
                .loginAsProfessor(testData.getProfessorLogin(), testData.getProfessorPassword())
                .moveToSubjectManagementPage()
                .addNewSubject(testData.getSubjectName(), testData.getSubjectAbbreviation(), testData.getSubjectGroups())
                .checkSuccessfulNotification();
    }

    @Test
    public void removeAllSubjectsTest(TestData testData) {
        loginPage
                .loginAsProfessor(testData.getProfessorLogin(), testData.getProfessorPassword())
                .moveToSubjectManagementPage()
                .deleteAllSubjects();
    }

    @Test
    public void applyStudentTest(TestData testData) {
        loginPage
                .loginAsProfessor(testData.getProfessorLogin(), testData.getProfessorPassword())
                .moveToStudentManagementPage()
                .applyStudent(testData.getStudentName(), testData.getStudentGroupNumber());
    }

    @Test()
    public void changeProfessorTest(TestData testData) {
        loginPage
                .loginUsingCredentials(adminLogin, adminPassword)
                .chooseProfessorsTab()
                .changeProfessorInformation(testData.getProfessorLogin(), testData.getChangedProfessorName(),
                        testData.getChangedProfessorSurname(), testData.getChangedProfessorPatronymic())
                .checkSuccessfulNotification();
    }

    @Test()
    public void searchProfessorTest(TestData testData) throws InterruptedException {
        loginPage
                .loginUsingCredentials(adminLogin, adminPassword)
                .chooseProfessorsTab()
                .searchProfessor(testData.getProfessorSearchKeyword())
                .checkProfessorInSearchResults(testData.getProfessorSearchKeyword());
    }

    @Test()
    public void removeProfessorTest(TestData testData) {
        loginPage
                .loginUsingCredentials(adminLogin, adminPassword)
                .chooseProfessorsTab()
                .removeProfessor(testData.getProfessorLogin())
                .checkSuccessfulNotification();
    }
}