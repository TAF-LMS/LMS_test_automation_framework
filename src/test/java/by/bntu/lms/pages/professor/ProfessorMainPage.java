package by.bntu.lms.pages.professor;

import by.bntu.lms.pages.AbstractPage;
import by.bntu.lms.pages.admin.AdminProfessorsPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
@Log4j2
@EqualsAndHashCode(callSuper = false)
public class ProfessorMainPage extends AbstractPage {
    @FindBy(id = "UserName")
    private WebElement loginField;

    @FindBy(xpath = "//i[contains(@class,'user')]")
    private WebElement userIcon;

    @FindBy(xpath = "//ui[@class='sidebar-menu']/li[1]/a")
    private WebElement subjectsMenu;

    @FindBy(xpath = "//a[contains(@href,'Subject/Management')]")
    private WebElement subjectsManagementMenu;

    @FindBy(xpath = "//a[contains(@href,'StudentManagement')]")
    private WebElement studentsManagementMenu;

    public ProfessorMainPage() {
    }

    public void checkThatLoginIsSuccessful() {
        wait.waitForPageToLoad();
        wait.waitForElementIsVisible(userIcon);
        new AdminProfessorsPage();
    }

    public SubjectManagementPage moveToSubjectManagementPage() {
        log.info("Moving to subject management page");
        wait.waitForPageToLoad();
        waitForElementIsClickableAndClick(subjectsMenu);
        waitForElementIsClickableAndClick(subjectsManagementMenu);
        return new SubjectManagementPage();
    }

    public StudentManagementPage moveToStudentManagementPage() {
        log.info("Moving to student management page");
        wait.waitForPageToLoad();
        waitForElementIsClickableAndClick(studentsManagementMenu);
        return new StudentManagementPage();
    }
}