package by.bnty.lms.tests.smoke.group;

import by.bnty.lms.data.TestData;
import by.bnty.lms.tests.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static by.bnty.lms.data.DataReader.dataReaderGeneric;

public class GroupFeature extends TestBase {

    @DataProvider()
    public Object[][] xlsData() throws Exception {
        return dataReaderGeneric("./src/test/resources/data/Smoke_GroupFeature.xlsx", "UsersData");
    }

    @Test(priority = 1, dataProvider = "xlsData")
    public void addGroupTest(TestData testData) throws Exception {
        loginPage.loginAsAdmin(testData.getAdminLogin(),testData.getAdminPassword()).chooseGroupsTab().
                addGroup(testData.getGroupNumber(),testData.getEnteringYear(),testData.getGraduatingYear());
    }

    @Test(priority = 2, dataProvider = "xlsData" )
    public void changeGroupTest(TestData testData) throws Exception {
        loginPage.loginAsAdmin(testData.getAdminLogin(),testData.getAdminPassword()).chooseGroupsTab().
                changeGroupInformation(testData.getGroupNumber(),testData.getChangedGroupNumber(),
                        testData.getChangedEnteringYear(),testData.getChangedGraduationYear());
    }

    @Test(priority = 3, dataProvider = "xlsData")
    public void removeEmptyGroupTest(TestData testData) throws Exception {
        loginPage.loginAsAdmin(testData.getAdminLogin(),testData.getAdminPassword()).chooseGroupsTab().
                removeEmptyGroup(testData.getGroupNumber());
    }

    @Test(priority = 4, dataProvider = "xlsData")
    public void removeGroupWithStudents(TestData testData) throws Exception {
        loginPage.loginAsAdmin(testData.getAdminLogin(),testData.getAdminPassword()).chooseGroupsTab()
                .removeGroupWithStudents(testData.getGroupNumber());
    }
}
