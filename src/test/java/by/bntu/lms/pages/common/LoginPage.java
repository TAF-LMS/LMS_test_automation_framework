package by.bntu.lms.pages.common;


import by.bntu.lms.pages.AbstractPage;
import by.bntu.lms.pages.MainPage;
import by.bntu.lms.pages.professor.ProfessorMainPage;
import by.bntu.lms.pages.student.RegistrationPage;
import by.bntu.lms.pages.student.StudentMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "UserName")
    private WebElement loginField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//a[contains(@href,'/Account/Register')]")
    private WebElement registerButton;

    public MainPage loginUsingCredentials(String login, String password) {
        sendKeysIntoWebElement(loginField, login);
        sendKeysIntoWebElement(passwordField, password);
        waitForElementIsClickableAndClick(submitButton);
        return new MainPage(driver);
    }

    public ProfessorMainPage loginAsProfessor(String login, String password) {
        sendKeysIntoWebElement(loginField, login);
        sendKeysIntoWebElement(passwordField, password);
        waitForElementIsClickableAndClick(submitButton);
        return new ProfessorMainPage(driver);
    }

    public StudentMainPage loginAsStudent(String login, String password) {
        sendKeysIntoWebElement(loginField, login);
        sendKeysIntoWebElement(passwordField, password);
        waitForElementIsClickableAndClick(submitButton);
        return new StudentMainPage(driver);
    }

    public RegistrationPage moveToRegistration() {
        waitForElementIsClickableAndClick(registerButton);
        return new RegistrationPage(driver);
    }

}
