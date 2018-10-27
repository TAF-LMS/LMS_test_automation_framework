package by.bntu.lms.pages.student;

import by.bntu.lms.pages.AbstractPage;
import by.bntu.lms.pages.MainPage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;

@Log4j2
public class RegistrationPage extends AbstractPage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "UserName")
    private WebElement loginField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    @FindBy(id = "UserName")
    private WebElement userNameField;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(id = "Name")
    private WebElement nameField;

    @FindBy(id = "Surname")
    private WebElement surnameField;

    @FindBy(id = "Patronymic")
    private WebElement patronymicField;

    @FindBy(id = "Group")
    private WebElement listGroups;

    private WebElement failedMessage;

    private WebElement initWebElement(String xpath) {
        return driver.findElement(new By.ByXPath(xpath));
    }


    public void checkFailedMessage(String expectedErrorMessage) {
        Arrays.asList(expectedErrorMessage.split(";")).forEach(
                errorMessage -> {
                    failedMessage = initWebElement("//li[contains(text(),'" + errorMessage + "')]");
                    wait.waitForElementIsVisible(failedMessage);
                }
        );
    }

    public MainPage registerStudent(String login, String password, String name,
                                    String surname, String patronymic, String groupNumber) {
        log.info("Registering new student");
        registerStudentAndBeOnPage(login, password, name, surname, patronymic, groupNumber);
        return new MainPage(driver);
    }

    public RegistrationPage registerStudentAndBeOnPage(String login, String password, String name,
                                                       String surname, String patronymic, String groupNumber) {
        log.info("Registering new student");
        wait.waitForPageToLoad();
        sendKeysIntoWebElement(loginField, login);
        sendKeysIntoWebElement(passwordField, password);
        sendKeysIntoWebElement(confirmPasswordField, password);
        sendKeysIntoWebElement(surnameField, surname);
        sendKeysIntoWebElement(nameField, name);
        sendKeysIntoWebElement(patronymicField, patronymic);
        sendKeysIntoWebElement(patronymicField, patronymic);
        Select groups = new Select(listGroups);
        groups.selectByVisibleText(groupNumber);
        waitForElementIsClickableAndClick(submitButton);
        return this;
    }

}
