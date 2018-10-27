package by.bntu.lms.suites;

import by.bntu.lms.data.TestData;

import by.bntu.lms.tests.smoke.group.positive.GroupFeature;
import by.bntu.lms.tests.smoke.professor.positive.ProfessorFeature;
import by.bntu.lms.tests.smoke.student.positive.StudentFeature;
import org.testng.annotations.*;

import java.io.IOException;

import static by.bntu.lms.data.DataReader.dataReaderGeneric;

@Test(groups = {"smoke"})
public class SmokeSuite extends SuiteBase {

    private final String STUDENT_DATA_PATH = "./src/test/resources/data/Smoke_StudentFeature.xls";
    private final String PROFESSOR_DATA_PATH = "./src/test/resources/data/Smoke_ProfessorFeature.xls";
    private final String GROUP_DATA_PATH = "./src/test/resources/data/Smoke_GroupFeature.xls";

    public SmokeSuite() throws IOException {
    }

    //Student
    @DataProvider()
    public Object[][] addStudentData() throws Exception {
        return dataReaderGeneric(STUDENT_DATA_PATH, "AddStudent");
    }

    @DataProvider()
    public Object[][] loginStudentData() throws Exception {
        return dataReaderGeneric(STUDENT_DATA_PATH, "LoginAppliedStudent");
    }

    @DataProvider()
    public Object[][] changeStudentData() throws Exception {
        return dataReaderGeneric(STUDENT_DATA_PATH, "ChangeStudent");
    }

    @DataProvider()
    public Object[][] changeStudentPasswordData() throws Exception {
        return dataReaderGeneric(STUDENT_DATA_PATH, "ChangePassword");
    }

    @DataProvider()
    public Object[][] removeStudentData() throws Exception {
        return dataReaderGeneric(STUDENT_DATA_PATH, "RemoveStudent");
    }

    //Professor
    @DataProvider()
    public Object[][] addProfessorData() throws Exception {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "AddProfessor");
    }

    @DataProvider()
    public Object[][] loginProfessorData() throws Exception {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "LogInProfessor");
    }

    @DataProvider()
    public Object[][] createNewSubjectData() throws Exception {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "CreateSubject");
    }

    @DataProvider()
    public Object[][] applyNewStudentData() throws Exception {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "ApplyStudent");
    }

    @DataProvider()
    public Object[][] changeProfessorData() throws Exception {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "ChangeProfessor");
    }

    @DataProvider()
    public Object[][] searchProfessorData() throws Exception {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "SearchProfessor");
    }

    @DataProvider()
    public Object[][] removeProfessorData() throws Exception {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "DeleteProfessor");
    }

    //Group
    @DataProvider()
    public Object[][] addGroupData() throws Exception {
        return dataReaderGeneric(GROUP_DATA_PATH, "AddGroup");
    }

    @DataProvider()
    public Object[][] changeGroupData() throws Exception {
        return dataReaderGeneric(GROUP_DATA_PATH, "ChangeGroup");
    }

    @DataProvider()
    public Object[][] removeEmptyGroupData() throws Exception {
        return dataReaderGeneric(GROUP_DATA_PATH, "RemoveGroup");
    }

    @DataProvider()
    public Object[][] removeGroupWithStudentsData() throws Exception {
        return dataReaderGeneric(GROUP_DATA_PATH, "RemoveGroupWithStudents");
    }

    //Student
    @Test(priority = 3, dataProvider = "addStudentData")
    public void addStudentTest(TestData testData) {
        new StudentFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).addStudentTest(testData);
    }

    @Test(priority = 5, dataProvider = "changeStudentData")
    public void changeStudentTest(TestData testData) throws InterruptedException {
        new StudentFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).changeStudentTest(testData);
    }

    @Test(priority = 6, dataProvider = "changeStudentPasswordData")
    public void changeStudentPassword(TestData testData) throws InterruptedException {
        new StudentFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).changeStudentPassword(testData);
    }

    @Test(priority = 14, dataProvider = "removeStudentData")
    public void removeStudentTest(TestData testData) {
        new StudentFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).removeStudentTest(testData);
    }

    //Professor
    @Test(priority = 7, dataProvider = "addProfessorData")
    public void addProfessorTest(TestData testData) {
        new ProfessorFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).addProfessorTest(testData);
    }


    @Test(priority = 8, dataProvider = "changeProfessorData")
    public void changeProfessorTest(TestData testData) {
        new ProfessorFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).changeProfessorTest(testData);
    }

    /*//TODO: Clarify whether it is needed
    @Test(priority = 9, dataProvider = "loginProfessorData")
    public void checkProfessorLogInTest(TestData testData) {
        new ProfessorFeature(loginPage).loginAsProfessorTest(testData);
    }*/

    @Test(priority = 10, dataProvider = "createNewSubjectData")
    public void createNewSubjectTest(TestData testData) {
        new ProfessorFeature(loginPage).createNewSubjectTest(testData);
    }

    @Test(priority = 11, dataProvider = "applyNewStudentData")
    public void applyNewStudentTest(TestData testData) {
        new ProfessorFeature(loginPage).applyStudentTest(testData);
    }

    //Student
    @Test(priority = 12, dataProvider = "loginStudentData")
    public void loginAppliedStudentTest(TestData testData) {
        new StudentFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).loginStudentTest(testData);
    }
/*
    @Test(priority = 11, dataProvider = "searchProfessorData")
    public void searchProfessorTest(TestData testData) {
        new ProfessorFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).searchProfessorTest(testData);
    }*/

    @Test(priority = 13, dataProvider = "removeProfessorData")
    public void removeProfessorTest(TestData testData) {
        new ProfessorFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).removeProfessorTest(testData);
    }

    //Group
    @Test(priority = 1, dataProvider = "addGroupData")
    public void addGroupTest(TestData testData) throws Exception {
        new GroupFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).addGroupTest(testData);
    }

    @Test(priority = 2, dataProvider = "changeGroupData")
    public void changeGroupTest(TestData testData) {
        new GroupFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).changeGroupTest(testData);
    }

    //create
    @Test(priority = 15, dataProvider = "removeEmptyGroupData")
    public void removeGroupTest(TestData testData) {
        new GroupFeature(loginPage, ADMIN_LOGIN, ADMIN_PASSWORD).removeEmptyGroupTest(testData);
    }

}
