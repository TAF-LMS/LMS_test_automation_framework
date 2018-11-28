package by.bntu.lms.tests.smoke.professor.negative;

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
                .checkFailedMessage(testData.getErrorMessage());
    }

    @Test()
    public void changeProfessorTest(TestData testData) {
        loginPage
                .loginUsingCredentials(adminLogin, adminPassword)
                .chooseProfessorsTab()
                .changeProfessorInformation(testData.getProfessorLogin(), testData.getChangedProfessorName(),
                        testData.getChangedProfessorSurname(), testData.getChangedProfessorPatronymic())
                .checkFailedMessage(testData.getErrorMessage());
    }
}
