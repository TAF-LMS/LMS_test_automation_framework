package by.bntu.lms.suites;

import by.bntu.lms.data.TestData;

import by.bntu.lms.tests.smoke.group.positive.GroupFeature;
import by.bntu.lms.tests.smoke.professor.positive.ProfessorFeature;
import by.bntu.lms.tests.smoke.student.positive.StudentFeature;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static by.bntu.lms.data.DataReader.dataReaderGeneric;

@Test(groups = {"smoke"})
public class SmokeSuite extends SuiteBase {
    private final String STUDENT_DATA_PATH = "./src/test/resources/data/smoke/Smoke_StudentFeature.xls";
    private final String PROFESSOR_DATA_PATH = "./src/test/resources/data/smoke/Smoke_ProfessorFeature.xls";
    private final String GROUP_DATA_PATH = "./src/test/resources/data/smoke/Smoke_GroupFeature.xls";

    public SmokeSuite() throws IOException {
    }

    //Student
    @DataProvider()
    public Object[][] addStudentData() {
        return dataReaderGeneric(STUDENT_DATA_PATH, "AddStudent");
    }

    @DataProvider()
    public Object[][] loginStudentData() {
        return dataReaderGeneric(STUDENT_DATA_PATH, "LoginAppliedStudent");
    }

    @DataProvider()
    public Object[][] changeStudentData() {
        return dataReaderGeneric(STUDENT_DATA_PATH, "ChangeStudent");
    }

    @DataProvider()
    public Object[][] changeStudentPasswordData() {
        return dataReaderGeneric(STUDENT_DATA_PATH, "ChangePassword");
    }

    @DataProvider()
    public Object[][] removeStudentData() {
        return dataReaderGeneric(STUDENT_DATA_PATH, "RemoveStudent");
    }

    //Professor
    @DataProvider()
    public Object[][] addProfessorData() {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "AddProfessor");
    }

    @DataProvider()
    public Object[][] loginProfessorData() {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "LogInProfessor");
    }

    @DataProvider()
    public Object[][] createNewSubjectData() {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "CreateSubject");
    }

    @DataProvider()
    public Object[][] applyNewStudentData() {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "ApplyStudent");
    }

    @DataProvider()
    public Object[][] changeProfessorData() {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "ChangeProfessor");
    }

    @DataProvider()
    public Object[][] searchProfessorData() {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "SearchProfessor");
    }

    @DataProvider()
    public Object[][] removeAllSubjectsData() {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "RemoveAllSubjects");
    }

    @DataProvider()
    public Object[][] removeProfessorData() {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "DeleteProfessor");
    }

    //Group
    @DataProvider()
    public Object[][] addGroupData() {
        return dataReaderGeneric(GROUP_DATA_PATH, "AddGroup");
    }

    @DataProvider()
    public Object[][] changeGroupData() {
        return dataReaderGeneric(GROUP_DATA_PATH, "ChangeGroup");
    }

    @DataProvider()
    public Object[][] removeEmptyGroupData() {
        return dataReaderGeneric(GROUP_DATA_PATH, "RemoveGroup");
    }

    @DataProvider()
    public Object[][] removeGroupWithStudentsData() {
        return dataReaderGeneric(GROUP_DATA_PATH, "RemoveGroupWithStudents");
    }

    //Student
    @Test(priority = 3, dataProvider = "addStudentData")
    public void addStudentTest(TestData testData) {
        new StudentFeature(loginPage, adminLogin, adminPassword).addStudentTest(testData);
    }

    @Test(priority = 5, dataProvider = "changeStudentData")
    public void changeStudentTest(TestData testData) throws InterruptedException {
        new StudentFeature(loginPage, adminLogin, adminPassword).changeStudentTest(testData);
    }

    @Test(priority = 6, dataProvider = "changeStudentPasswordData")
    public void changeStudentPassword(TestData testData) {
        new StudentFeature(loginPage, adminLogin, adminPassword).changeStudentPassword(testData);
    }

    @Test(priority = 15, dataProvider = "removeStudentData")
    public void removeStudentTest(TestData testData) {
        new StudentFeature(loginPage, adminLogin, adminPassword).removeStudentTest(testData);
    }

    //Professor
    @Test(priority = 7, dataProvider = "addProfessorData")
    public void addProfessorTest(TestData testData) {
        new ProfessorFeature(loginPage, adminLogin, adminPassword).addProfessorTest(testData);
    }

    @Test(priority = 8, dataProvider = "changeProfessorData")
    public void changeProfessorTest(TestData testData) {
        new ProfessorFeature(loginPage, adminLogin, adminPassword).changeProfessorTest(testData);
    }

    /*
     * There is no need to check that professor is created,
     * 'cause their change requires login and is checked later on
     */
    @Test(priority = 9, dataProvider = "loginProfessorData", enabled = false)
    public void checkProfessorLogInTest(TestData testData) {
        new ProfessorFeature(loginPage, adminLogin, adminPassword).loginAsProfessorTest(testData);
    }

    @Test(priority = 10, dataProvider = "createNewSubjectData")
    public void createNewSubjectTest(TestData testData) {
        new ProfessorFeature(loginPage, adminLogin, adminPassword).createNewSubjectTest(testData);
    }

    /*
     * Changed students do not require to be applied, set to disabled
     */
    @Test(priority = 11, dataProvider = "applyNewStudentData", enabled = false)
    public void applyNewStudentTest(TestData testData) {
        new ProfessorFeature(loginPage, adminLogin, adminPassword).applyStudentTest(testData);
    }

    //Student
    @Test(priority = 12, dataProvider = "loginStudentData")
    public void loginAppliedStudentTest(TestData testData) {
        new StudentFeature(loginPage, adminLogin, adminPassword).loginStudentTest(testData);
    }

    @Test(priority = 11, dataProvider = "searchProfessorData")
    public void searchProfessorTest(TestData testData) throws InterruptedException {
        new ProfessorFeature(loginPage, adminLogin, adminPassword).searchProfessorTest(testData);
    }

    @Test(priority = 13, dataProvider = "removeAllSubjectsData")
    public void removeAllSubjectsTest(TestData testData) {
        new ProfessorFeature(loginPage, adminLogin, adminPassword).removeAllSubjectsTest(testData);
    }

    @Test(priority = 14, dataProvider = "removeProfessorData")
    public void removeProfessorTest(TestData testData) {
        new ProfessorFeature(loginPage, adminLogin, adminPassword).removeProfessorTest(testData);
    }

    //Group
    @Test(priority = 1, dataProvider = "addGroupData")
    public void addGroupTest(TestData testData) throws Exception {
        new GroupFeature(loginPage, adminLogin, adminPassword).addGroupTest(testData);
    }

    @Test(priority = 2, dataProvider = "changeGroupData")
    public void changeGroupTest(TestData testData) {
        new GroupFeature(loginPage, adminLogin, adminPassword).changeGroupTest(testData);
    }

    //create
    @Test(priority = 16, dataProvider = "removeEmptyGroupData")
    public void removeGroupTest(TestData testData) {
        new GroupFeature(loginPage, adminLogin, adminPassword).removeEmptyGroupTest(testData);
    }
}