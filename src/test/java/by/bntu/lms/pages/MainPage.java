package by.bntu.lms.pages;

import by.bntu.lms.pages.admin.AdminGroupsPage;
import by.bntu.lms.pages.admin.AdminProfessorsPage;
import by.bntu.lms.pages.admin.AdminStudentsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractPage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

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


    public AdminProfessorsPage chooseProfessorsTab() {
        waitForElementIsClickableAndClick(professorsLink);
        return new AdminProfessorsPage(driver);
    }

    public AdminGroupsPage chooseGroupsTab() {
        waitForElementIsClickableAndClick(groupsLink);
        return new AdminGroupsPage(driver);
    }

    public AdminStudentsPage chooseStudentsTab() {
        waitForElementIsClickableAndClick(studentsLink);
        return new AdminStudentsPage(driver);
    }

}
