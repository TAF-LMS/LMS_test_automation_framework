package by.bntu.lms.pages.professor;

import by.bntu.lms.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class StudentManagementPage extends AbstractPage {
    public StudentManagementPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//select[@ng-model='selectedGroup']")
    private WebElement groupSelector;

    private WebElement checkSuccessfulConfirmation(String studentName) {
        return driver.findElement(By.xpath("//tr[@ng-repeat='data in students']/td[contains(text()," +
                "'" + studentName + "')]/../td/div[@ng-if='data.Confirmed']"));
    }

    private WebElement initStudentElement(String studentName) {
        return driver.findElement(By.xpath("//tr[@ng-repeat='data in students']/td[contains(text()," +
                "'" + studentName + "')]/../td/div/a[contains(@ng-click,'confirmationStudent')]"));
    }

    public void applyStudent(String studentName, String studentGroupNumber) {
        Select groupSelect = new Select(groupSelector);
        String fullGroupNumber = studentGroupNumber;
        for (WebElement groupOption : groupSelect.getOptions()) {
            if (groupOption.getText().contains(studentGroupNumber)) {
                fullGroupNumber = groupOption.getText();
                break;
            }
        }
        groupSelect.selectByVisibleText(fullGroupNumber);

        waitForElementIsClickableAndClick(initStudentElement(studentName));
        wait.waitForElementIsVisible(checkSuccessfulConfirmation(studentName));
    }

}
