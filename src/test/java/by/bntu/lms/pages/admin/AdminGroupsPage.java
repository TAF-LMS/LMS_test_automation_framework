package by.bntu.lms.pages.admin;

import by.bntu.lms.driver.Driver;
import by.bntu.lms.pages.AbstractPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Data
@Log4j2
@EqualsAndHashCode(callSuper = false)
public class AdminGroupsPage extends AbstractPage {
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

    @FindBy(xpath = "//article[contains(@class,'success')]")
    private WebElement successfulNotification;

    private WebElement removeEmptyGroupButton;
    private WebElement failedMessage;

    public AdminGroupsPage() {
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
        WebElement editGroupButton = initWebElement("//tr/td[text()='" +
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

    /*
     * Set implicityWait to 1 and then return it back
     * to check that elements are absent without spending lots of time
     */
    public void checkGroupWasRemoved(String groupNumb) {
        wait.waitForElementIsVisible(successfulNotification);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> groups = initWebElements("//tr/td[text()='" + groupNumb
                + "']/../td[@class='']/div/a[contains(@href,'DeleteGroup')]");
        driver.manage().timeouts().implicitlyWait(Driver.IMPLICITY_WAIT_TIME, TimeUnit.SECONDS);
        Assert.assertTrue(groups.isEmpty(), "Group has not been removed");
    }
}