package by.bnty.lms.pages.common;


import by.bnty.lms.pages.AbstractPage;
import by.bnty.lms.pages.admin.MainPage;
import by.bnty.lms.pages.student.RegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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

    public MainPage loginAsAdmin(String login, String password) {
        sendKeysIntoWebElement(loginField, login);
        sendKeysIntoWebElement(passwordField, password);
        waitForElementIsClickableAndClick(submitButton);
        return new MainPage(driver);
    }

    public RegistrationPage moveToRegistration() {
        waitForElementIsClickableAndClick(registerButton);
        return new RegistrationPage(driver);
    }

}
