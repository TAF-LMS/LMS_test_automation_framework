package by.bntu.lms.pages.professor;

import by.bntu.lms.pages.AbstractPage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

@Log4j2
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

    private WebElement acceptStudentElement(String studentName) {
        return driver.findElement(By.xpath("//tr[@ng-repeat='data in students']/td[contains(text()," +
                "'" + studentName + "')]/../td[3]/div/a[@ng-click='confirmationStudent(data.StudentId)']"));
    }

    private WebElement unassignStudentElement(String studentName) {
        return driver.findElement(By.xpath("//tr[@ng-repeat='data in students']/td[contains(text()," +
                "'" + studentName + "')]/../td[3]/div/a[@ng-click='unConfirmationStudent(data.StudentId)']"));
    }

    public void applyStudent(String studentName, String studentGroupNumber) {
        log.info("Applying new student");
        wait.waitForPageToLoad();
        Select groupSelect = new Select(groupSelector);
        String fullGroupNumber = studentGroupNumber;
        for (WebElement groupOption : groupSelect.getOptions()) {
            if (groupOption.getText().contains(studentGroupNumber)) {
                fullGroupNumber = groupOption.getText();
                break;
            }
        }
        groupSelect.selectByVisibleText(fullGroupNumber);

        if (acceptStudentElement(studentName).isDisplayed())
            waitForElementIsClickableAndClick(acceptStudentElement(studentName));
        else if (unassignStudentElement(studentName).isDisplayed()) {
            log.error("The student is already confirmed");
        } else throw new IllegalArgumentException("No student confirmation request has been found for: " +
                studentGroupNumber + " " + studentName);

        checkSuccessfulConfirmation(studentName);
    }

}
