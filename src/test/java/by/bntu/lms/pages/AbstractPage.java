package by.bntu.lms.pages;

import by.bntu.lms.waits.ExplicitWait;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;

public class AbstractPage {
    protected WebDriver driver;
    protected ExplicitWait wait;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new ExplicitWait(driver);
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

    public String takeScreenshot(String screenName, String path) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File file = new File(path + screenName + ".png");
        FileUtils.copyFile(scrFile, file);
        return file.getAbsolutePath();
    }
}