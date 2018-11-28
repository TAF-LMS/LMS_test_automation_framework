package by.bntu.lms.pages.professor;

import by.bntu.lms.pages.AbstractPage;
import by.bntu.lms.pages.admin.AdminProfessorsPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
@Log4j2
@EqualsAndHashCode(callSuper = false)
public class ProfessorMainPage extends AbstractPage {

    public ProfessorMainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "UserName")
    private WebElement loginField;

    @FindBy(xpath = "//i[contains(@class,'user')]")
    private WebElement userIcon;

    @FindBy(xpath = "//span[contains(text(),'Предметы')]")
    private WebElement subjectsMenu;

    @FindBy(xpath = "//a[contains(@href,'Subject/Management')]")
    private WebElement subjectsManagementMenu;

    @FindBy(xpath = "//a[contains(@href,'StudentManagement')]")
    private WebElement studentsManagementMenu;

    public void checkThatLoginIsSuccessful() {
        wait.waitForPageToLoad();
        wait.waitForElementIsVisible(userIcon);
        new AdminProfessorsPage(driver);
    }

    public SubjectManagementPage moveToSubjectManagementPage() {
        log.info("Moving to subject management page");
        wait.waitForPageToLoad();
        waitForElementIsClickableAndClick(subjectsMenu);
        waitForElementIsClickableAndClick(subjectsManagementMenu);
        return new SubjectManagementPage(driver);
    }

    public StudentManagementPage moveToStudentManagementPage() {
        log.info("Moving to student management page");
        wait.waitForPageToLoad();
        waitForElementIsClickableAndClick(studentsManagementMenu);
        return new StudentManagementPage(driver);
    }
}
