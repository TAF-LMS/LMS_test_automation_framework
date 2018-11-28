package by.bntu.lms.tests.smoke.group.negative;

import by.bntu.lms.data.TestData;
import by.bntu.lms.pages.common.LoginPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.testng.annotations.Test;

@Data
@AllArgsConstructor
public class GroupFeature {
    private LoginPage loginPage;
    private String adminLogin;
    private String adminPassword;

    @Test()
    public void addGroupTest(TestData testData) {
        loginPage
                .loginUsingCredentials(adminLogin, adminPassword).chooseGroupsTab()
                .addGroup(testData.getGroupNumber(), testData.getGroupEnteringYear(), testData.getGroupGraduatingYear())
                .checkFailedMessage(testData.getErrorMessage());
    }

    @Test()
    public void changeGroupTest(TestData testData) {
        loginPage
                .loginUsingCredentials(adminLogin, adminPassword)
                .chooseGroupsTab()
                .changeGroupInformation(testData.getGroupNumber(), testData.getChangedGroupNumber(),
                        testData.getChangedGroupEnteringYear(), testData.getChangedGroupGraduationYear())
                .checkFailedMessage(testData.getErrorMessage());
    }

    @Test()
    public void removeGroupWithStudents(TestData testData) {
        loginPage.loginUsingCredentials(adminLogin, adminPassword)
                .chooseGroupsTab()
                .removeGroupByNumber(testData.getGroupNumber())
                .checkFailureNotification(testData.getErrorMessage());
    }
}
