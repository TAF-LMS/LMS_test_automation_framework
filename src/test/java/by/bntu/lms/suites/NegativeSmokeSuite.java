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
    public Object[][] addGroupData() {
        return dataReaderGeneric(GROUP_DATA_PATH, "AddGroup");
    }

    //Group
    @Test(priority = 1, dataProvider = "addGroupData")
    public void addGroupTest(TestData testData) {
        new GroupFeature(loginPage, adminLogin, adminPassword).addGroupTest(testData);
    }

    //Group
    //One group should be created to check change group and add student features
    @DataProvider()
    public Object[][] addGroupPositiveData() {
        return dataReaderGeneric(GROUP_DATA_PATH, "AddGroup (1 positive)");
    }

    //Group
    //One group should be created to check change group and add student features
    @Test(priority = 2, dataProvider = "addGroupPositiveData")
    public void addGroupPositiveTest(TestData testData) throws Exception {
        new by.bntu.lms.tests.smoke.group.positive.GroupFeature(loginPage, adminLogin, adminPassword)
                .addGroupTest(testData);
    }

    @DataProvider()
    public Object[][] changeGroupData() {
        return dataReaderGeneric(GROUP_DATA_PATH, "ChangeGroup");
    }

    @Test(priority = 3, dataProvider = "changeGroupData")
    public void changeGroupTest(TestData testData) {
        new GroupFeature(loginPage, adminLogin, adminPassword)
                .changeGroupTest(testData);
    }

    //Professor
    @DataProvider()
    public Object[][] addProfessorData() {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "AddProfessor");
    }

    //Professor
    @Test(priority = 4, dataProvider = "addProfessorData")
    public void addProfessorTest(TestData testData) {
        new ProfessorFeature(loginPage, adminLogin, adminPassword).addProfessorTest(testData);
    }

    @DataProvider()
    public Object[][] addProfessorPositiveData() {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "AddProfessor (1 positive)");
    }

    @Test(priority = 5, dataProvider = "addProfessorPositiveData")
    public void addProfessorPositiveTest(TestData testData) {
        new by.bntu.lms.tests.smoke.professor.positive.ProfessorFeature(loginPage, adminLogin, adminPassword)
                .addProfessorTest(testData);
    }

    @DataProvider()
    public Object[][] changeProfessorData() {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "ChangeProfessor");
    }

    @Test(priority = 6, dataProvider = "changeProfessorData")
    public void changeProfessorTest(TestData testData) {
        new ProfessorFeature(loginPage, adminLogin, adminPassword).changeProfessorTest(testData);
    }

    //Student
    @DataProvider()
    public Object[][] addStudentData() {
        return dataReaderGeneric(STUDENT_DATA_PATH, "AddStudent");
    }

    //Student
    @Test(priority = 7, dataProvider = "addStudentData")
    public void addStudentTest(TestData testData) {
        new StudentFeature(loginPage, adminLogin, adminPassword).addStudentTest(testData);
    }

    //Student
    @DataProvider()
    public Object[][] addStudentPositiveData() {
        return dataReaderGeneric(STUDENT_DATA_PATH, "AddStudent (1 positive)");
    }

    //Student
    @Test(priority = 8, dataProvider = "addStudentPositiveData")
    public void addStudentPositiveTest(TestData testData) {
        new by.bntu.lms.tests.smoke.student.positive.StudentFeature(loginPage, adminLogin, adminPassword)
                .addStudentTest(testData);
    }

    @DataProvider()
    public Object[][] loginNotConfirmedStudentData() {
        return dataReaderGeneric(STUDENT_DATA_PATH, "LoginNotConfirmed");
    }

    //Student
    @Test(priority = 9, dataProvider = "loginNotConfirmedStudentData")
    public void loginNotConfirmedStudentTest(TestData testData) {
        new StudentFeature(loginPage, adminLogin, adminPassword).loginStudentTest(testData);
    }

    @DataProvider()
    public Object[][] createNewSubjectPositiveData() {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "CreateSubject (1 positive)");
    }

    @Test(priority = 10, dataProvider = "createNewSubjectPositiveData")
    public void createNewSubjectTest(TestData testData) {
        new by.bntu.lms.tests.smoke.professor.positive.ProfessorFeature(loginPage, adminLogin, adminPassword)
                .createNewSubjectTest(testData);
    }

    @DataProvider()
    public Object[][] applyNewStudentPositiveData() {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "ApplyStudent (1 positive)");
    }

    @Test(priority = 11, dataProvider = "applyNewStudentPositiveData")
    public void applyNewStudentTest(TestData testData) {
        new by.bntu.lms.tests.smoke.professor.positive.ProfessorFeature(loginPage, adminLogin, adminPassword)
                .applyStudentTest(testData);
    }

    @DataProvider()
    public Object[][] changeStudentData() {
        return dataReaderGeneric(STUDENT_DATA_PATH, "ChangeStudent");
    }

    @Test(priority = 12, dataProvider = "changeStudentData")
    public void changeStudentTest(TestData testData) throws InterruptedException {
        new StudentFeature(loginPage, adminLogin, adminPassword).changeStudentTest(testData);
    }

    @DataProvider()
    public Object[][] changeStudentPasswordData() {
        return dataReaderGeneric(STUDENT_DATA_PATH, "ChangePassword");
    }

    @Test(priority = 13, dataProvider = "changeStudentPasswordData")
    public void changeStudentPassword(TestData testData) {
        new StudentFeature(loginPage, adminLogin, adminPassword).changeStudentPassword(testData);
    }

    @DataProvider()
    public Object[][] removeGroupWithStudentsData() {
        return dataReaderGeneric(GROUP_DATA_PATH, "RemoveGroupWithStudents");
    }

    @Test(priority = 14, dataProvider = "removeGroupWithStudentsData")
    public void removeGroupWithStudentsTest(TestData testData) {
        new GroupFeature(loginPage, adminLogin, adminPassword).removeGroupWithStudents(testData);
    }

    /*
        Positive cases to clean up the data after tests
     */
    @DataProvider()
    public Object[][] removeStudentsData() {
        return dataReaderGeneric(STUDENT_DATA_PATH, "RemoveStudent (1 positive)");
    }

    @Test(priority = 15, dataProvider = "removeStudentsData")
    public void removeStudentsTest(TestData testData) {
        new by.bntu.lms.tests.smoke.student.positive
                .StudentFeature(loginPage, adminLogin, adminPassword).removeStudentTest(testData);
    }

    @DataProvider()
    public Object[][] removeGroupData() {
        return dataReaderGeneric(GROUP_DATA_PATH, "RemoveGroup (1 positive)");
    }

    @Test(priority = 16, dataProvider = "removeGroupData")
    public void removeGroupTest(TestData testData) {
        new by.bntu.lms.tests.smoke.group.positive.
                GroupFeature(loginPage, adminLogin, adminPassword).removeEmptyGroupTest(testData);
    }

    @DataProvider()
    public Object[][] removeProfessorWithSubjectsData() {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "RemoveProfessorWithSubjects");
    }

    //Negative
    @Test(priority = 17, dataProvider = "removeProfessorWithSubjectsData")
    public void removeProfessorWithSubjectsTest(TestData testData) {
        new ProfessorFeature(loginPage, adminLogin, adminPassword).removeProfessorTest(testData);
    }

    @DataProvider()
    public Object[][] removeAllSubjectsData() {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "removeAllSubjects");
    }

    @Test(priority = 18, dataProvider = "removeAllSubjectsData")
    public void removeAllSubjectsTest(TestData testData) {
        new by.bntu.lms.tests.smoke.professor.positive.ProfessorFeature(loginPage, adminLogin, adminPassword)
                .removeAllSubjectsTest(testData);
    }

    @DataProvider()
    public Object[][] removeProfessorData() {
        return dataReaderGeneric(PROFESSOR_DATA_PATH, "RemoveProfessor (1 positive)");
    }

    @Test(priority = 19, dataProvider = "removeProfessorData")
    public void removeProfessorTest(TestData testData) {
        new by.bntu.lms.tests.smoke.professor.positive.
                ProfessorFeature(loginPage, adminLogin, adminPassword).removeProfessorTest(testData);
    }
}