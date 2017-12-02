package by.bnty.lms.pages.common;


import by.bnty.lms.pages.AbstractPage;
import by.bnty.lms.pages.admin.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final String LOGIN = "admin";
    private final String PASSWORD = "123456";

    @FindBy(id = "UserName")
    private WebElement loginField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    public MainPage loginAsAdmin() {
        sendKeysIntoWebElement(loginField, LOGIN);
        sendKeysIntoWebElement(passwordField, PASSWORD);
        waitForElementIsClickableAndClick(submitButton);
        return new MainPage(driver);
    }
}
