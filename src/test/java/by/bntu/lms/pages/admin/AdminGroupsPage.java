package by.bntu.lms.pages.admin;

import by.bntu.lms.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AdminGroupsPage extends AbstractPage {

    public AdminGroupsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@data-bb-handler='confirm']")
    private WebElement confirmButton;

    @FindBy(xpath = "//*[contains(@href,'AddGroup')]")
    private WebElement addGroupButton;

    @FindBy(id = "Name")
    private WebElement groupNameField;

    @FindBy(id = "StartYear")
    private WebElement listYearsOfGroupEntering;

    @FindBy(id = "GraduationYear")
    private WebElement listYearsOfGroupGraduation;

    @FindBy(xpath = "//*[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//a[contains(@href,'ListOfStudents')]")
    private WebElement listOFStudentsLink;

    @FindBy(xpath = "//a[@class='bootbox-close-button close']")
    private WebElement closeButton;

    private WebElement removeGroupWithStudentsButton;

    private WebElement removeEmptyGroupButton;

    private WebElement editGroupButton;

    @FindBy(xpath = "//article[contains(@class,'success')]")
    WebElement successfulNotification;

    private WebElement initWebElement(String xpath) {
        return driver.findElement(new By.ByXPath(xpath));
    }

    public AdminGroupsPage addGroup(String groupNumber, String enteringYear, String graduatingYear) {
        wait.waitForPageToLoad();
        waitForElementIsClickableAndClick(addGroupButton);
        sendKeysIntoWebElement(groupNameField, groupNumber);
        Select enteringYearBox = new Select(listYearsOfGroupEntering);
        Select graduatingYearBox = new Select(listYearsOfGroupGraduation);
        enteringYearBox.selectByVisibleText(enteringYear);
        graduatingYearBox.selectByVisibleText(graduatingYear);
        waitForElementIsClickableAndClick(submitButton);
        wait.waitForElementIsVisible(successfulNotification);
        return AdminGroupsPage.this;
    }

    public AdminGroupsPage changeGroupInformation(String groupNumb, String changedGroupNumber,
                                                  String changedEnteringYear, String changedGraduationYear) {
        wait.waitForPageToLoad();
        editGroupButton = initWebElement("//tr/td[text()='" +
                groupNumb + "']" + "/../td[@class='']/div/a[contains(@href,'EditGroup')]");
        waitForElementIsClickableAndClick(editGroupButton);
        sendKeysIntoWebElement(groupNameField, changedGroupNumber);
        Select enteringYearBox = new Select(listYearsOfGroupEntering);
        Select graduatingYearBox = new Select(listYearsOfGroupGraduation);
        enteringYearBox.selectByVisibleText(changedEnteringYear);
        graduatingYearBox.selectByVisibleText(changedGraduationYear);
        waitForElementIsClickableAndClick(submitButton);
        return AdminGroupsPage.this;
    }

    public AdminGroupsPage removeEmptyGroup(String emptyGroupNumb) {
        wait.waitForPageToLoad();
        removeEmptyGroupButton = initWebElement("//tr/td[text()='" + emptyGroupNumb
                + "']/../td[@class='']/div/a[contains(@href,'DeleteGroup')]");
        waitForElementIsClickableAndClick(removeEmptyGroupButton);
        waitForElementIsClickableAndClick(confirmButton);
        wait.waitForElementIsVisible(successfulNotification);
        return AdminGroupsPage.this;
    }

    public AdminGroupsPage removeGroupWithStudents(String groupWithStudentsNumber) {
        wait.waitForPageToLoad();
        removeGroupWithStudentsButton = initWebElement("//tr/td[5][not(contains(text(),'0'))]/../" +
                "td[@class='']/div/a[contains(@href,'DeleteGroup')]");
        waitForElementIsClickableAndClick(removeGroupWithStudentsButton);
        waitForElementIsClickableAndClick(confirmButton);
        wait.waitForElementIsVisible(successfulNotification);
        return AdminGroupsPage.this;
    }

}
