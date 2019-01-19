package by.bntu.lms.waits;

import by.bntu.lms.driver.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWait {
    private Wait<WebDriver> wait;

    public ExplicitWait() {
        WebDriver driverWait = Driver.getWebDriverInstance();
        wait = new WebDriverWait(driverWait, 1)
                .ignoring(StaleElementReferenceException.class, WebDriverException.class)
                .withMessage("Element was not found by locator");
    }

    public void waitForElementIsVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementIsClickable(WebElement element) {
        waitForElementIsVisible(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForPageToLoad() {
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}