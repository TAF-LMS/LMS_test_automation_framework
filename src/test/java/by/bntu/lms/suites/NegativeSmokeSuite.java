package by.bntu.lms.suites;

import by.bntu.lms.data.TestData;
import by.bntu.lms.tests.smoke.group.negative.GroupFeature;
import by.bntu.lms.tests.smoke.professor.negative.ProfessorFeature;
import by.bntu.lms.tests.smoke.student.negative.StudentFeature;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static by.bntu.lms.data.DataReader.dataReaderGeneric;

@Test(groups = {"negativesmoke"})
public class NegativeSmokeSuite extends SuiteBase {

    private final String GROUP_DATA_PATH = "./src/test/resources/data/negativesmoke/Smoke_GroupFeature.xls";
    private final String STUDENT_DATA_PATH = "./src/test/resources/data/negativesmoke/Smoke_StudentFeature.xls";
    private final String PROFESSOR_DATA_PATH = "./src/test/resources/data/negativesmoke/Smoke_ProfessorFeature.xls";

    NegativeSmokeSuite() throws IOException {
    }

    //Group
    @DataProvider()
    public Object[][] addGroupData() throws Exception {
        return dataReaderGeneric(GROUP_DATA_PATH, "AddGroup");
    }
    //Group
    //One group should be created to check change group and add student features
    @Test(priority = 1, dataProvider = "addGroupData")
    public void addGroupTest(TestData testData) throws Exception {
        new GroupFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).addGroupTest(testData);
    }

    //Group
    //One group should be created to check change group and add student features
    @DataProvider()
    public Object[][] addGroupPositiveData() throws Exception {
        return dataReaderGeneric(GROUP_DATA_PATH, "AddGroup (1 positive)");
    }
    //Group
    //One group should be created to check change group and add student features
    @Test(priority = 2, dataProvider = "addGroupPositiveData")
    public void addGroupPositiveTest(TestData testData) throws Exception {
        new by.bntu.lms.tests.smoke.group.positive.GroupFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD)
                .addGroupTest(testData);
    }

    @DataProvider()
    public Object[][] changeGroupData() throws Exception {
        return dataReaderGeneric(GROUP_DATA_PATH, "ChangeGroup");
    }
    @Test(priority = 3, dataProvider = "changeGroupData")
    public void changeGroupTest(TestData testData) throws Exception {
        new GroupFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD)
                .changeGroupTest(testData);
    }

    //Professor
    @DataProvider()
    public Object[][] addProfessorData() throws Exception {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "AddProfessor");
    }
    //Professor
    @Test(priority = 4, dataProvider = "addProfessorData")
    public void addProfessorTest(TestData testData) {
        new ProfessorFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).addProfessorTest(testData);
    }

    @DataProvider()
    public Object[][] addProfessorPositiveData() throws Exception {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "AddProfessor (1 positive)");
    }
    @Test(priority = 5, dataProvider = "addProfessorPositiveData")
    public void addProfessorPositiveTest(TestData testData) {
        new by.bntu.lms.tests.smoke.professor.positive.ProfessorFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD)
                .addProfessorTest(testData);
    }

    @DataProvider()
    public Object[][] changeProfessorData() throws Exception {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "ChangeProfessor");
    }
    @Test(priority = 6, dataProvider = "changeProfessorData")
    public void changeProfessorTest(TestData testData) {
        new ProfessorFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).changeProfessorTest(testData);
    }


    //Student
    @DataProvider()
    public Object[][] addStudentData() throws Exception {
        return dataReaderGeneric(STUDENT_DATA_PATH, "AddStudent");
    }
    //Student
    @Test(priority = 7, dataProvider = "addStudentData")
    public void addStudentTest(TestData testData) {
        new StudentFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).addStudentTest(testData);
    }

    //Student
    @DataProvider()
    public Object[][] addStudentPositiveData() throws Exception {
        return dataReaderGeneric(STUDENT_DATA_PATH, "AddStudent (1 positive)");
    }
    //Student
    @Test(priority = 8, dataProvider = "addStudentPositiveData")
    public void addStudentPositiveTest(TestData testData) {
        new by.bntu.lms.tests.smoke.student.positive.StudentFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD)
                .addStudentTest(testData);
    }

    @DataProvider()
    public Object[][] loginNotConfirmedStudentData() throws Exception {
        return dataReaderGeneric(STUDENT_DATA_PATH, "LoginNotConfirmed");
    }
    //Student
    @Test(priority = 9, dataProvider = "loginNotConfirmedStudentData")
    public void loginNotConfirmedStudentTest(TestData testData) {
        new StudentFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).loginStudentTest(testData);
    }

    @DataProvider()
    public Object[][] createNewSubjectPositiveData() throws Exception {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "CreateSubject (1 positive)");
    }
    @Test(priority = 10, dataProvider = "createNewSubjectPositiveData")
    public void createNewSubjectTest(TestData testData) {
        new by.bntu.lms.tests.smoke.professor.positive.ProfessorFeature(loginPage).createNewSubjectTest(testData);
    }

    @DataProvider()
    public Object[][] applyNewStudentPositiveData() throws Exception {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "ApplyStudent (1 positive)");
    }
    @Test(priority = 11, dataProvider = "applyNewStudentPositiveData")
    public void applyNewStudentTest(TestData testData) {
        new by.bntu.lms.tests.smoke.professor.positive.ProfessorFeature(loginPage).applyStudentTest(testData);
    }

    @DataProvider()
    public Object[][] changeStudentData() throws Exception {
        return dataReaderGeneric(STUDENT_DATA_PATH, "ChangeStudent");
    }
    @Test(priority = 12, dataProvider = "changeStudentData")
    public void changeStudentTest(TestData testData) throws InterruptedException {
        new StudentFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).changeStudentTest(testData);
    }

    @DataProvider()
    public Object[][] changeStudentPasswordData() throws Exception {
        return dataReaderGeneric(STUDENT_DATA_PATH, "ChangePassword");
    }
    @Test(priority = 13, dataProvider = "changeStudentPasswordData")
    public void changeStudentPassword(TestData testData) throws InterruptedException {
        new StudentFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).changeStudentPassword(testData);
    }

    @DataProvider()
    public Object[][] removeGroupWithStudentsData() throws Exception {
        return dataReaderGeneric(GROUP_DATA_PATH, "RemoveGroupWithStudents");
    }
    @Test(priority = 14, dataProvider = "removeGroupWithStudentsData")
    public void removeGroupWithStudentsTest(TestData testData) {
        new GroupFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).removeGroupWithStudents(testData);
    }

}
