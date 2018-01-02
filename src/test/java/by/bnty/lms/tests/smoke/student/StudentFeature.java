package by.bnty.lms.tests.smoke.student;

import by.bnty.lms.data.TestData;
import by.bnty.lms.tests.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static by.bnty.lms.data.DataReader.dataReaderGeneric;

public class StudentFeature extends TestBase {

    @Test(priority = 2, dataProvider = "createStudentData")
    public void addStudentTest(TestData testData) throws Exception {
        loginPage.moveToRegistration().registerStudent(testData.getLogin(), testData.getPassword(), testData.getName(),
                testData.getSurname(), testData.getPatronymic(),testData.getGroupNumber());
    }

    //rework
    @Test(priority = 1, dataProvider = "xlsData")
    public void changeStudentTest(TestData testData) throws Exception {
        loginPage.loginAsAdmin(testData.getAdminLogin(), testData.getAdminPassword()).chooseStudentsTab().changeStudent(
                testData.getName(), testData.getChangedName(), testData.getChangedSurname(), testData.getChangedPatronymic(),
        testData.getChangedGroupNumber());
    }

    @Test(priority = 2, dataProvider = "xlsData")
    public void removeStudentTest(TestData testData) throws Exception {
        loginPage.loginAsAdmin(testData.getAdminLogin(), testData.getAdminPassword()).chooseStudentsTab().removeStudent(
                testData.getName());
    }
}
