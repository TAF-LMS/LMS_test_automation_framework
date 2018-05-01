package by.bntu.lms.tests.smoke.group;

import by.bntu.lms.data.DataReader;
import by.bntu.lms.data.TestData;
import by.bntu.lms.pages.common.LoginPage;
import by.bntu.lms.tests.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GroupFeature extends TestBase {

    LoginPage loginPage;
    String ADMIN_LOGIN;
    String ADMIN_PASSWORD;

    public GroupFeature(LoginPage loginPage, String ADMIN_LOGIN, String ADMIN_PASSWORD) {
        this.loginPage = loginPage;
        this.ADMIN_LOGIN = ADMIN_LOGIN;
        this.ADMIN_PASSWORD = ADMIN_PASSWORD;
    }

    @DataProvider()
    public Object[][] xlsData() throws Exception {
        return DataReader.dataReaderGeneric("./src/test/resources/data/Smoke_GroupFeature.xls", "UsersData");
    }

    @Test()
    public void addGroupTest(TestData testData) throws Exception {
        loginPage.loginUsingCredentials(ADMIN_LOGIN, ADMIN_PASSWORD).chooseGroupsTab().
                addGroup(testData.getGroupNumber(), testData.getGroupEnteringYear(),
                        testData.getGroupGraduatingYear());
    }

    @Test()
    public void changeGroupTest(TestData testData) {
        loginPage.loginUsingCredentials(ADMIN_LOGIN, ADMIN_PASSWORD).chooseGroupsTab().
                changeGroupInformation(testData.getGroupNumber(), testData.getChangedGroupNumber(),
                        testData.getChangedGroupEnteringYear(), testData.getChangedGroupGraduationYear());
    }

    @Test()
    public void removeEmptyGroupTest(TestData testData) {
        loginPage.loginUsingCredentials(ADMIN_LOGIN, ADMIN_PASSWORD).chooseGroupsTab().
                removeEmptyGroup(testData.getGroupNumber());
    }

    @Test()
    public void removeGroupWithStudents(TestData testData) {
        loginPage.loginUsingCredentials(ADMIN_LOGIN, ADMIN_PASSWORD).chooseGroupsTab()
                .removeGroupWithStudents(testData.getGroupNumber());
    }
}
