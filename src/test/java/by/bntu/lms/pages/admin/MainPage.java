package by.bntu.lms.pages.admin;

import by.bntu.lms.pages.AbstractPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
@EqualsAndHashCode(callSuper = false)
public class MainPage extends AbstractPage {
    @FindBy(xpath = "//a[contains(@href,'Professors')]")
    private WebElement professorsLink;

    @FindBy(xpath = "//a[contains(@href,'Students')]")
    private WebElement studentsLink;

    @FindBy(xpath = "//a[contains(@href,'Groups')]")
    private WebElement groupsLink;

    @FindBy(xpath = "//a[contains(@href,'Files')]")
    private WebElement filesLink;

    @FindBy(xpath = "//a[contains(@href,'Message')]")
    private WebElement messageLink;

    public MainPage() {
    }

    public AdminProfessorsPage chooseProfessorsTab() {
        waitForElementIsClickableAndClick(professorsLink);
        return new AdminProfessorsPage();
    }

    public AdminGroupsPage chooseGroupsTab() {
        waitForElementIsClickableAndClick(groupsLink);
        return new AdminGroupsPage();
    }

    public AdminStudentsPage chooseStudentsTab() {
        waitForElementIsClickableAndClick(studentsLink);
        return new AdminStudentsPage();
    }
}