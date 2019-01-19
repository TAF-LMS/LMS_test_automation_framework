package by.bntu.lms.pages.common;

import by.bntu.lms.pages.AbstractPage;
import by.bntu.lms.pages.MainPage;
import by.bntu.lms.pages.professor.ProfessorMainPage;
import by.bntu.lms.pages.student.RegistrationPage;
import by.bntu.lms.pages.student.StudentMainPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
@Log4j2
@EqualsAndHashCode(callSuper = false)
public class LoginPage extends AbstractPage {
    @FindBy(id = "UserName")
    private WebElement loginField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//a[contains(@href,'/Account/Register')]")
    private WebElement registerButton;

    public LoginPage() {
    }

    public MainPage loginUsingCredentials(String login, String password) {
        log.info("Login with: " + login + " " + password);
        sendKeysIntoWebElement(loginField, login);
        sendKeysIntoWebElement(passwordField, password);
        waitForElementIsClickableAndClick(submitButton);
        return new MainPage();
    }

    public ProfessorMainPage loginAsProfessor(String login, String password) {
        log.info("Login with: " + login + " " + password);
        sendKeysIntoWebElement(loginField, login);
        sendKeysIntoWebElement(passwordField, password);
        waitForElementIsClickableAndClick(submitButton);
        return new ProfessorMainPage();
    }

    public StudentMainPage loginAsStudent(String login, String password) {
        log.info("Login with: " + login + " " + password);
        sendKeysIntoWebElement(loginField, login);
        sendKeysIntoWebElement(passwordField, password);
        waitForElementIsClickableAndClick(submitButton);
        return new StudentMainPage();
    }

    public RegistrationPage moveToRegistration() {
        log.info("Moving to registration");
        waitForElementIsClickableAndClick(registerButton);
        return new RegistrationPage();
    }
}