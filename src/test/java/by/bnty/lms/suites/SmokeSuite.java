package by.bnty.lms.suites;


import by.bnty.lms.data.TestData;
import by.bnty.lms.tests.smoke.group.GroupFeature;
import by.bnty.lms.tests.smoke.professor.ProfessorFeature;
import by.bnty.lms.tests.smoke.student.StudentFeature;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static by.bnty.lms.data.DataReader.dataReaderGeneric;

public class SmokeSuite {

    private final String STUDENT_DATA_PATH = "./src/test/resources/data/Smoke_StudentFeature.xlsx";
    private final String PROFESSOR_DATA_PATH = "./src/test/resources/data/Smoke_ProfessorFeature.xlsx";
    private final String GROUP_DATA_PATH = "./src/test/resources/data/Smoke_GroupFeature.xlsx";

    //Student
    @DataProvider()
    public Object[][] addStudentData() throws Exception {
        return dataReaderGeneric(STUDENT_DATA_PATH, "AddStudent");
    }
    @DataProvider()
    public Object[][] changeStudentData() throws Exception {
        return dataReaderGeneric(STUDENT_DATA_PATH, "ChangeStudent");
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
        return dataReaderGeneric(GROUP_DATA_PATH, "RemoveEmptyGroup");
    }
    @DataProvider()
    public Object[][] removeGroupWithStudentsData() throws Exception {
        return dataReaderGeneric(GROUP_DATA_PATH, "RemoveGroupWithStudents");
    }
    //Student
    @Test(dataProvider = "")
    public void addStudentTest(TestData testData) throws Exception {
        new StudentFeature().addStudentTest(testData);
    }

    @Test(dataProvider = "")
    public void changeStudentTest(TestData testData) throws Exception {
        new StudentFeature().changeStudentTest(testData);
    }

    @Test(dataProvider = "")
    public void removeStudentTest(TestData testData) throws Exception {
        new StudentFeature().removeStudentTest(testData);
    }

    //Professor
    @Test(dataProvider = "")
    public void addProfessorTest(TestData testData) throws Exception {
        new ProfessorFeature().addProfessorTest(testData);
    }

    @Test(dataProvider = "")
    public void changeProfessorTest(TestData testData) throws Exception {
        new ProfessorFeature().changeProfessorTest(testData);
    }

    @Test(dataProvider = "")
    public void searchProfessorTest(TestData testData) throws Exception {
        new ProfessorFeature().searchProfessorTest(testData);
    }

    @Test(dataProvider = "")
    public void removeProfessorTest(TestData testData) throws Exception {
        new ProfessorFeature().removeProfessorTest(testData);
    }

    //Group
    @Test(dataProvider = "")
    public void addGroupTest(TestData testData) throws Exception {
        new GroupFeature().addGroupTest(testData);
    }

    @Test(dataProvider = "")
    public void changeGroupTest(TestData testData) throws Exception {
        new GroupFeature().changeGroupTest(testData);
    }

    @Test(dataProvider = "")
    public void removeEmptyGroupTest(TestData testData) throws Exception {
        new GroupFeature().removeEmptyGroupTest(testData);
    }

    @Test(dataProvider = "")
    public void removeGroupWithStudentsTest(TestData testData) throws Exception {
        new GroupFeature().removeGroupWithStudents(testData);
    }
}
