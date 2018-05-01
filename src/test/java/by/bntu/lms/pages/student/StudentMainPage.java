package by.bntu.lms.pages.student;

import by.bntu.lms.pages.AbstractPage;
import by.bntu.lms.pages.admin.AdminProfessorsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StudentMainPage extends AbstractPage {
    public StudentMainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//i[contains(@class,'user')]")
    private WebElement userIcon;

    public AdminProfessorsPage checkThatLoginIsSuccessful() {
        wait.waitForPageToLoad();
        wait.waitForElementIsVisible(userIcon);
        return new AdminProfessorsPage(driver);
    }

}
