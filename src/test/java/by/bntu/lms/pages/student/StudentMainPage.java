package by.bntu.lms.pages.student;

import by.bntu.lms.pages.AbstractPage;
import by.bntu.lms.pages.admin.AdminProfessorsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StudentMainPage extends AbstractPage {

    public StudentMainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//i[contains(@class,'user')]")
    private WebElement userIcon;

    private WebElement failedMessage;

    private WebElement initWebElement(String xpath) {
        return driver.findElement(new By.ByXPath(xpath));
    }

    public AdminProfessorsPage checkThatLoginIsSuccessful() {
        wait.waitForPageToLoad();
        wait.waitForElementIsVisible(userIcon);
        return new AdminProfessorsPage(driver);
    }

    public AdminProfessorsPage checkThatLoginIsFailed(String expectedErrorMessage) {
        wait.waitForPageToLoad();
        driver.switchTo().activeElement();
        failedMessage = initWebElement("//div[contains(text(),'" + expectedErrorMessage + "')]");
        wait.waitForElementIsVisible(failedMessage);
        return new AdminProfessorsPage(driver);
    }

}
