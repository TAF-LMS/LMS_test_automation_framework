package by.bntu.lms.pages.admin;

import by.bntu.lms.pages.AbstractPage;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;

@Log4j2
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
    private WebElement successfulNotification;

    private WebElement failedMessage;

    private WebElement initWebElement(String xpath) {
        return driver.findElement(new By.ByXPath(xpath));
    }

    public void checkSuccessfulNotification() {
        wait.waitForElementIsVisible(successfulNotification);
    }

    //TODO: make a check for notification with failed message
    public void checkFailureNotification(String errorMessage) {
        wait.waitForElementIsVisible(initWebElement("//article[contains(text(),'" + errorMessage + "')]"));
    }


    /**
     * The method is able to check several error messages separated by ';' symbol
     *
     * @param expectedErrorMessage expected message at the page
     */
    public void checkFailedMessage(String expectedErrorMessage) {
        Arrays.asList(expectedErrorMessage.split(";")).forEach(
                errorMessage -> {
                    failedMessage = initWebElement("//li[contains(text(),'" + errorMessage + "')]");
                    wait.waitForElementIsVisible(failedMessage);
                }
        );
    }

    public AdminGroupsPage addGroup(String groupNumber, String enteringYear, String graduatingYear) {
        log.info("Adding new group");
        wait.waitForPageToLoad();
        waitForElementIsClickableAndClick(addGroupButton);
        sendKeysIntoWebElement(groupNameField, groupNumber);
        Select enteringYearBox = new Select(listYearsOfGroupEntering);
        Select graduatingYearBox = new Select(listYearsOfGroupGraduation);
        enteringYearBox.selectByVisibleText(enteringYear);
        graduatingYearBox.selectByVisibleText(graduatingYear);
        waitForElementIsClickableAndClick(submitButton);
        return AdminGroupsPage.this;
    }

    public AdminGroupsPage changeGroupInformation(String groupNumb, String changedGroupNumber,
                                                  String changedEnteringYear, String changedGraduationYear) {
        log.info("Changing group details");
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

    public AdminGroupsPage removeGroupByNumber(String emptyGroupNumb) {
        log.info("Removing group");
        wait.waitForPageToLoad();
        removeEmptyGroupButton = initWebElement("//tr/td[text()='" + emptyGroupNumb
                + "']/../td[@class='']/div/a[contains(@href,'DeleteGroup')]");
        waitForElementIsClickableAndClick(removeEmptyGroupButton);
        waitForElementIsClickableAndClick(confirmButton);
        wait.waitForPageToLoad();
        return AdminGroupsPage.this;
    }

    public void checkGroupWasRemoved(String groupNumb) {
        wait.waitForElementIsVisible(successfulNotification);
        removeEmptyGroupButton = initWebElement("//tr/td[text()='" + groupNumb
                + "']/../td[@class='']/div/a[contains(@href,'DeleteGroup')]");
        Assert.assertNull("Group has not been removed", removeEmptyGroupButton);
    }

}
