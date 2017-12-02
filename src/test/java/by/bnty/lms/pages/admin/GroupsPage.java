package by.bnty.lms.pages.admin;

import by.bnty.lms.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class GroupsPage extends AbstractPage {
    private final String GROUP_NUMB = "12345678";
    private final String ENTERING_YEAR = "2013";
    private final String GRADUATING_YEAR = "2017";

    private final String CHANGED_GROUP_NUMBER = "87654321";
    private final String CHANGED_ENTERING_YEAR = "2015";
    private final String CHANGED_GRADUATION_YEAR = "2019";

    public GroupsPage(WebDriver driver) {
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

    @FindBy(xpath = "//tr/td[5][not(contains(text(),'0'))]/../td[@class='']/div/a[contains(@href,'DeleteGroup')]")
    private WebElement removeGroupWithStudentsButton;

    @FindBy(xpath = "//tr/td[text()='" + CHANGED_GROUP_NUMBER + "']" +
            "/../td[@class='']/div/a[contains(@href,'DeleteGroup')]")
    private WebElement removeGroupButton;

    @FindBy(xpath = "//tr/td[text()='" +
            GROUP_NUMB + "']" + "/../td[@class='']/div/a[contains(@href,'EditGroup')]")
    private WebElement editGroupButton;

    WebElement notification;

    public GroupsPage addGroup() throws InterruptedException {
        wait.waitForPageToLoad();
        waitForElementIsClickableAndClick(addGroupButton);
        sendKeysIntoWebElement(groupNameField, GROUP_NUMB);
        Select enteringYearBox = new Select(listYearsOfGroupEntering);
        Select graduatingYearBox = new Select(listYearsOfGroupGraduation);
        enteringYearBox.selectByVisibleText(ENTERING_YEAR);
        graduatingYearBox.selectByVisibleText(GRADUATING_YEAR);
        waitForElementIsClickableAndClick(submitButton);
        notification = driver.findElement(new By.ByXPath("//section[@id='alertify-logs']/" +
                "article[contains(text(),'сохранена')]"));
        wait.waitForElementIsVisible(notification);
        return GroupsPage.this;
    }

    public GroupsPage changeGroupInformation() {
        wait.waitForPageToLoad();
        waitForElementIsClickableAndClick(editGroupButton);
        sendKeysIntoWebElement(groupNameField, CHANGED_GROUP_NUMBER);
        Select enteringYearBox = new Select(listYearsOfGroupEntering);
        Select graduatingYearBox = new Select(listYearsOfGroupGraduation);
        enteringYearBox.selectByVisibleText(CHANGED_ENTERING_YEAR);
        graduatingYearBox.selectByVisibleText(CHANGED_GRADUATION_YEAR);
        waitForElementIsClickableAndClick(submitButton);
        // checkListOfStuddents();
        return GroupsPage.this;
    }

    public GroupsPage removeEmptyGroup() {
        wait.waitForPageToLoad();
        waitForElementIsClickableAndClick(removeGroupButton);
        waitForElementIsClickableAndClick(confirmButton);
        notification = driver.findElement(new By.ByXPath("//section[@id='alertify-logs']/" +
                "article[contains(text(),'удалена')]"));
        wait.waitForElementIsVisible(notification);
        return GroupsPage.this;
    }

    public GroupsPage removeGroupWithStudents() {
        wait.waitForPageToLoad();
        waitForElementIsClickableAndClick(removeGroupWithStudentsButton);
        waitForElementIsClickableAndClick(confirmButton);
        notification = driver.findElement(new By.ByXPath("//section[@id='alertify-logs']/" +
                "article[contains(text(),'не может быть удалена')]"));
        wait.waitForElementIsVisible(notification);
        return GroupsPage.this;
    }

    public void checkListOfStuddents() {
        waitForElementIsClickableAndClick(listOFStudentsLink);
        driver.switchTo().activeElement();
        waitForElementIsClickableAndClickUsingJS(closeButton);
    }

}
