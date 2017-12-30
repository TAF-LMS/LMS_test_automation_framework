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

    @FindBy(xpath = "//*[@data-bb-handler='confirm']")
    private WebElement confirmButton;

    private WebElement deleteStudentButton;

    WebElement notification;

    public void initDynamicWebElements(String studentName) {
        deleteStudentButton = driver.findElement(new By.ByXPath("//tr/td[contains(text(),'" + studentName + "')]" +
                "/../td[@class='']/div/a[contains(@href,'DeleteStudent')]"));
    }

    public void changeStudent(String name, String changedName, String changedSurname, String changedPatronymic) {
        waitForElementIsClickableAndClick(editStudentButton);
        Select groups = new Select(listGroups);
        groups.selectByIndex(0);
        sendKeysIntoWebElement(nameField, changedName);
        sendKeysIntoWebElement(surnameField, changedSurname);
        sendKeysIntoWebElement(patronymicField, changedPatronymic);
        waitForElementIsClickableAndClick(submitButton);
        WebElement notification = driver.findElement(new By.ByXPath("//section[@id='alertify-logs']/" +
                "article[contains(text(),'Студент сохранен')]"));
        wait.waitForElementIsVisible(notification);
    }

    public StudentsPage removeStudent(String name) {
        wait.waitForPageToLoad();
        initDynamicWebElements(name);
        waitForElementIsClickableAndClick(deleteStudentButton);
        waitForElementIsClickableAndClick(confirmButton);
        wait.waitForPageToLoad();
        notification = driver.findElement(new By.ByXPath("//section[@id='alertify-logs']/" +
                "article[contains(text(),'удален')]"));
        wait.waitForElementIsVisible(notification);
        return StudentsPage.this;
    }

}
