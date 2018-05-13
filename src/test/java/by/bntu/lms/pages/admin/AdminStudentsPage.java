package by.bntu.lms.pages.admin;

import by.bntu.lms.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


public class AdminStudentsPage extends AbstractPage {
    public AdminStudentsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@class='statButton']")
    private WebElement statButton;

    @FindBy(xpath = "//a[@class='listButton']")
    private WebElement listSubjects;

    @FindBy(xpath = "//a[contains(@href,'Profile')]")
    private WebElement profileButton;

    @FindBy(xpath = "//a[contains(@href,'EditStudent')]")
    private WebElement editStudentButton;

    @FindBy(id = "Name")
    private WebElement nameField;

    @FindBy(id = "Surname")
    private WebElement surnameField;

    @FindBy(id = "Patronymic")
    private WebElement patronymicField;

    @FindBy(id = "Group")
    private WebElement listGroups;

    @FindBy(xpath = "//*[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@data-bb-handler='confirm']")
    private WebElement confirmButton;

    @FindBy(xpath = "//a[contains(@href,'Administration/ResetPassword')]")
    private WebElement changePasswordLink;

    @FindBy(id = "Password")
    private WebElement changePasswordField;

    @FindBy(id = "ConfirmPassword")
    private WebElement changePasswordConfirmField;

    private WebElement deleteStudentButton;

    @FindBy(xpath = "//article[contains(@class,'success')]")
    WebElement successfulNotification;


    private WebElement initWebElement(String xpath) {
        return driver.findElement(new By.ByXPath(xpath));
    }

    public void changeStudent(String login, String changedName, String changedSurname, String changedPatronymic, String changedGroup) {
        editStudentButton = initWebElement("//tr/td[text()='" +
                login + "']" + "/../td[@class='']/div/a[contains(@href,'EditStudent')]");
        waitForElementIsClickableAndClick(editStudentButton);
        Select groups = new Select(listGroups);
        groups.selectByVisibleText(changedGroup);
        sendKeysIntoWebElement(nameField, changedName);
        sendKeysIntoWebElement(surnameField, changedSurname);
        sendKeysIntoWebElement(patronymicField, changedPatronymic);
        waitForElementIsClickableAndClick(submitButton);
        wait.waitForElementIsVisible(successfulNotification);
    }

    public void changeStudentPassword(String login, String newPassword) throws InterruptedException {
        editStudentButton = initWebElement("//tr/td[text()='" +
                login + "']" + "/../td[@class='']/div/a[contains(@href,'EditStudent')]");
        waitForElementIsClickableAndClick(editStudentButton);
        waitForElementIsClickableAndClick(changePasswordLink);
        wait.waitForPageToLoad();
        sendKeysIntoWebElement(changePasswordField, newPassword);
        sendKeysIntoWebElement(changePasswordConfirmField, newPassword);
        waitForElementIsClickableAndClick(submitButton);
        wait.waitForElementIsVisible(successfulNotification);
    }

    public AdminStudentsPage removeStudent(String login) {
        wait.waitForPageToLoad();
        deleteStudentButton = initWebElement("//tr/td[text()='" +
                login + "']" + "/../td[@class='']/div/a[contains(@href,'DeleteStudent')]");
        waitForElementIsClickableAndClick(deleteStudentButton);
        waitForElementIsClickableAndClick(confirmButton);
        wait.waitForPageToLoad();
        wait.waitForElementIsVisible(successfulNotification);
        return AdminStudentsPage.this;
    }
}
