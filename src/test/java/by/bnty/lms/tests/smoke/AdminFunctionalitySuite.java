package by.bnty.lms.tests.smoke;

import by.bnty.lms.tests.TestBase;
import org.testng.annotations.Test;

public class AdminFunctionalitySuite extends TestBase {

    @Test()
    public void checkProfessorFunctionalityTest() throws Exception {
        loginPage.loginAsAdmin().chooseProfessorsTab().addProfessor().checkActionButtons().
                changeProfessorInformation().removeProfessor();
    }

    @Test
    public void checkGroupsFunctionalityTest() throws Exception {
        loginPage.loginAsAdmin().chooseGroupsTab().addGroup().changeGroupInformation().removeEmptyGroup().removeGroupWithStudents();
    }

    @Test
    public void checkStudentsFunctionalityTest() throws Exception {
        loginPage.loginAsAdmin().chooseStudentsTab().changeStudent();
    }

}
