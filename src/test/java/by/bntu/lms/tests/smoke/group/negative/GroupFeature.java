package by.bntu.lms.tests.smoke.group.negative;

import by.bntu.lms.data.TestData;
import by.bntu.lms.pages.common.LoginPage;
import org.testng.annotations.Test;

public class GroupFeature {

    LoginPage loginPage;
    String ADMIN_LOGIN;
    String ADMIN_PASSWORD;

    public GroupFeature(LoginPage loginPage, String ADMIN_LOGIN, String ADMIN_PASSWORD) {
        this.loginPage = loginPage;
        this.ADMIN_LOGIN = ADMIN_LOGIN;
        this.ADMIN_PASSWORD = ADMIN_PASSWORD;
    }


    @Test()
    public void addGroupTest(TestData testData) {
        loginPage.loginUsingCredentials(ADMIN_LOGIN, ADMIN_PASSWORD).chooseGroupsTab().
                addGroup(testData.getGroupNumber(), testData.getGroupEnteringYear(),
                        testData.getGroupGraduatingYear()).checkFailedMessage(testData.getErrorMessage());
    }

    @Test()
    public void changeGroupTest(TestData testData) {
        loginPage.loginUsingCredentials(ADMIN_LOGIN, ADMIN_PASSWORD).chooseGroupsTab().
                changeGroupInformation(testData.getGroupNumber(), testData.getChangedGroupNumber(),
                        testData.getChangedGroupEnteringYear(), testData.getChangedGroupGraduationYear())
                .checkFailedMessage(testData.getErrorMessage());
    }

    @Test()
    public void removeGroupWithStudents(TestData testData) {
        loginPage.loginUsingCredentials(ADMIN_LOGIN, ADMIN_PASSWORD).chooseGroupsTab()
                .removeGroupByNumber(testData.getGroupNumber()).checkFailureNotification(testData.getErrorMessage());
    }

}
