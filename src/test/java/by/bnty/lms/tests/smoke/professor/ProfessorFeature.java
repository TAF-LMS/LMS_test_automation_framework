package by.bnty.lms.tests.smoke.professor;

import by.bnty.lms.data.TestData;
import by.bnty.lms.tests.TestBase;
import org.testng.annotations.Test;

public class ProfessorFeature extends TestBase {

    @Test(priority = 1)
    public void addProfessorTest(TestData testData) throws Exception {
        loginPage.loginAsAdmin(testData.getAdminLogin(), testData.getAdminPassword()).chooseProfessorsTab().addProfessor(
                testData.getUserName(), testData.getPassword(), testData.getSurname(), testData.getName(),
                testData.getPatronymic());
    }

    @Test(priority = 2)
    public void changeProfessorTest(TestData testData) throws Exception {
        loginPage.loginAsAdmin(testData.getAdminLogin(), testData.getAdminPassword()).chooseProfessorsTab().
                changeProfessorInformation(testData.getName(),testData.getChangedName(), testData.getChangedSurname(),
                        testData.getChangedPatronymic());
    }

    @Test(priority = 3)
    public void searchProfessorTest(TestData testData) throws Exception {
        loginPage.loginAsAdmin(testData.getAdminLogin(), testData.getAdminPassword()).chooseProfessorsTab().
                searchProfessor(testData.getName());
    }

    @Test(priority = 4)
    public void deleteProfessorTest(TestData testData) throws Exception {
        loginPage.loginAsAdmin(testData.getAdminLogin(), testData.getAdminPassword()).chooseProfessorsTab().
                removeProfessor(testData.getName());
    }
}
