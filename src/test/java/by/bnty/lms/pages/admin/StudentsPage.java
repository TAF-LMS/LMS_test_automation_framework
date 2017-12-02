package by.bnty.lms.pages.admin;

import by.bnty.lms.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static by.bnty.lms.utils.Utils.generateRandomString;


public class StudentsPage extends AbstractPage {
    protected StudentsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@class='statButton']")
    private WebElement statButton;

    @FindBy(xpath = "//a[@class='listButton']")
    private WebElement listSubjects;

    @FindBy(xpath = "//a[contains(@href,'Profile')]")
    private WebElement profileButton;

    @FindBy(xpath = "//a[contains(@href,'EditStudent')]")
    private WebElement editStudentButton;

    @FindBy(id = "Name")
    private WebElement nameField;

    @FindBy(id = "Surname")
    private WebElement surnameField;

    @FindBy(id = "Patronymic")
    private WebElement patronymicField;

    @FindBy(id = "Group")
    private WebElement listGroups;

    @FindBy(xpath = "//*[@type='submit']")
    private WebElement submitButton;

    public void changeStudent() {
        waitForElementIsClickableAndClick(editStudentButton);
        Select groups = new Select(listGroups);
        String studentName = generateRandomString(6);
        String studentSurname = generateRandomString(10);
        String studentPatronymic = generateRandomString(12);
        groups.selectByIndex(0);
        sendKeysIntoWebElement(nameField, studentName);
        sendKeysIntoWebElement(surnameField, studentSurname);
        sendKeysIntoWebElement(patronymicField, studentPatronymic);
        waitForElementIsClickableAndClick(submitButton);
        WebElement notification = driver.findElement(new By.ByXPath("//section[@id='alertify-logs']/" +
                "article[contains(text(),'Студент сохранен')]"));
        wait.waitForElementIsVisible(notification);
    }

}
