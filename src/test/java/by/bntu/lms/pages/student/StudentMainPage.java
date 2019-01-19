package by.bntu.lms.pages.student;

import by.bntu.lms.pages.AbstractPage;
import by.bntu.lms.pages.admin.AdminProfessorsPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
@EqualsAndHashCode(callSuper = false)
public class StudentMainPage extends AbstractPage {
    @FindBy(xpath = "//i[contains(@class,'user')]")
    private WebElement userIcon;

    private WebElement failedMessage;

    public StudentMainPage() {
    }

    public void checkThatLoginIsSuccessful() {
        wait.waitForPageToLoad();
        wait.waitForElementIsVisible(userIcon);
    }

    public void checkThatLoginIsFailed(String expectedErrorMessage) {
        wait.waitForPageToLoad();
        driver.switchTo().activeElement();
        failedMessage = initWebElement("//div[contains(text(),'" + expectedErrorMessage + "')]");
        wait.waitForElementIsVisible(failedMessage);
        new AdminProfessorsPage();
    }
}