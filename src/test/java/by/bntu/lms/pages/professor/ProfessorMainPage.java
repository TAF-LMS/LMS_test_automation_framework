package by.bntu.lms.pages.professor;

import by.bntu.lms.pages.AbstractPage;
import by.bntu.lms.pages.admin.AdminProfessorsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public AdminProfessorsPage checkThatLoginIsSuccessful() {
        wait.waitForPageToLoad();
        wait.waitForElementIsVisible(userIcon);
        return new AdminProfessorsPage(driver);
    }

    public SubjectManagementPage moveToSubjectManagementPage() {
        wait.waitForPageToLoad();
        waitForElementIsClickableAndClick(subjectsMenu);
        waitForElementIsClickableAndClick(subjectsManagementMenu);
        return new SubjectManagementPage(driver);
    }

    public StudentManagementPage moveToStudentManagementPage() {
        wait.waitForPageToLoad();
        waitForElementIsClickableAndClick(studentsManagementMenu);
        return new StudentManagementPage(driver);
    }
}
