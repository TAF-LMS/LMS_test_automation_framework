package by.bntu.lms.pages;

import by.bntu.lms.driver.Driver;
import by.bntu.lms.waits.ExplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AbstractPage {
    protected WebDriver driver;
    protected ExplicitWait wait;

    public AbstractPage() {
        driver = Driver.getWebDriverInstance();
        PageFactory.initElements(driver, this);
        wait = new ExplicitWait();
    }

    protected WebElement initWebElement(String xpath) {
        return driver.findElement(new By.ByXPath(xpath));
    }

    protected void sendKeysIntoWebElement(WebElement webElement, String text) {
        waitForElementIsClickableAndClick(webElement);
        webElement.clear();
        webElement.sendKeys(text);
    }

    protected void waitForElementIsClickableAndClick(WebElement webElement) {
        wait.waitForElementIsClickable(webElement);
        webElement.click();
    }
}