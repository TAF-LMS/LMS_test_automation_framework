package by.bnty.lms.pages.admin;


import by.bnty.lms.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class ProfessorsPage extends AbstractPage {

    protected ProfessorsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@class='statButton']")
    private WebElement statButton;

    @FindBy(xpath = "//a[@class='listButton']")
    private WebElement listButton;

    @FindBy(xpath = "//tr/td[contains(text(),'" + PROFESSOR_NAME + "')]" +
            "/../td[@class='']/div/a[contains(@href,'Profile')]")
    private WebElement profileButton;

    @FindBy(xpath = "//tr/td[contains(text(),'" + PROFESSOR_NAME + "')]" +
            "/../td[@class='']/div/a[contains(@href,'EditProfessor')]")
    private WebElement editProfessorsButton;

    @FindBy(xpath = "//a[@class='deleteButton']")
    private WebElement deleteButton;

    @FindBy(xpath = "//tr/td[contains(text(),'" + CHANGED_PROFESSORS_NAME + "')]" +
            "/../td[@class='']/div/a[contains(@href,'DeleteLecturer')]")
    private WebElement deleteProfessorButton;

    @FindBy(xpath = "//a[@class='addButton btn btn-primary btn-sm']")
    private WebElement addProfessorButton;

    @FindBy(id = "UserName")
    private WebElement userNameField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(id = "Name")
    private WebElement nameField;

    @FindBy(id = "Surname")
    private WebElement surnameField;

    @FindBy(id = "Patronymic")
    private WebElement patronymicField;

    @FindBy(name = "IsSecretary")
    private WebElement secretaryBox;

    @FindBy(id = "IsLecturerHasGraduateStudents")
    private WebElement lecturerHasGraduateStudentsBox;

    @FindBy(xpath = "//td[@class = '" + PROFESSOR_NAME + " " + PROFESSOR_NAME + " " + PROFESSOR_NAME + "']")
    private WebElement newProfessorName;

    @FindBy(xpath = "//td[@class = '" + PROFESSOR_NAME + "']")
    private WebElement newProfessorLogin;

    @FindBy(xpath = "//td[@class = 'TestUser']/../td[3]")
    private WebElement lastEnter;

    @FindBy(xpath = "//td[@class = 'TestUser']/../td[4]")
    private WebElement countOfSubjects;

    @FindBy(xpath = "//*[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//a[@class='modal-header panel-heading']")
    private WebElement modalDialog;

    @FindBy(xpath = "//a[@class='bootbox-close-button close']")
    private WebElement closeButton;

    @FindBy(id = "professorsList_filter_input")
    private WebElement searchField;

    @FindBy(xpath = "//*[@data-bb-handler='confirm']")
    private WebElement confirmButton;

    @FindBy(xpath = "//tr[@class='odd']/td[2]")
    private WebElement firstProfessorName;

    private final String PROFESSOR_NAME = "TestUser1";
    private final String CHANGED_PROFESSORS_NAME = "ChangedTestUser";
    WebElement notification;
    public ProfessorsPage checkActionButtons() {
        wait.waitForPageToLoad();
        SoftAssert sa = new SoftAssert();
        sa.assertTrue(statButton.isDisplayed(), "statButton is not displayed!");
        sa.assertTrue(listButton.isDisplayed(), "listButton is not displayed!");
        sa.assertTrue(profileButton.isDisplayed(), "profileButton is not displayed!");
        sa.assertTrue(editProfessorsButton.isDisplayed(), "editProfessorsButton is not displayed!");
        sa.assertTrue(deleteButton.isDisplayed(), "deleteButton is not displayed!");
        sa.assertTrue(addProfessorButton.isDisplayed(), "addProfessorButton is not displayed!");
        sa.assertAll();
        return ProfessorsPage.this;
    }

    public ProfessorsPage addProfessor() {
        wait.waitForPageToLoad();
        waitForElementIsClickableAndClick(addProfessorButton);
        sendKeysIntoWebElement(userNameField, PROFESSOR_NAME);
        sendKeysIntoWebElement(passwordField, PROFESSOR_NAME);
        sendKeysIntoWebElement(confirmPasswordField, PROFESSOR_NAME);
        sendKeysIntoWebElement(surnameField, PROFESSOR_NAME);
        sendKeysIntoWebElement(nameField, PROFESSOR_NAME);
        sendKeysIntoWebElement(patronymicField, PROFESSOR_NAME);
        waitForElementIsClickableAndClick(secretaryBox);
        waitForElementIsClickableAndClick(lecturerHasGraduateStudentsBox);
        waitForElementIsClickableAndClick(submitButton);
        notification = driver.findElement(new By.ByXPath("//section[@id='alertify-logs']/" +
                "article[contains(text(),'Преподаватель сохранен')]"));
        wait.waitForElementIsVisible(notification);
        return ProfessorsPage.this;

    }

    public void checkProffCreation() {
        wait.waitForPageToLoad();
        wait.waitForElementIsVisible(newProfessorName);
        wait.waitForElementIsVisible(newProfessorLogin);
        Assert.assertEquals(lastEnter.getText(), ("-"));
        Assert.assertEquals(countOfSubjects.getText(), ("-"));
    }

    public void checkStatistic() {
        waitForElementIsClickableAndClick(statButton);
        waitForElementIsClickableAndClick(closeButton);
    }

    public ProfessorsPage removeProfessor() {
        wait.waitForPageToLoad();
        /*String proffName = firstProfessorName.getText();*/
        waitForElementIsClickableAndClick(deleteProfessorButton);
        waitForElementIsClickableAndClick(confirmButton);
        wait.waitForPageToLoad();
        notification = driver.findElement(new By.ByXPath("//section[@id='alertify-logs']/" +
                "article[contains(text(),'удален')]"));
        wait.waitForElementIsVisible(notification);
        return ProfessorsPage.this;
    }

    public ProfessorsPage changeProfessorInformation() {
        wait.waitForPageToLoad();
        waitForElementIsClickableAndClick(editProfessorsButton);
        sendKeysIntoWebElement(nameField, CHANGED_PROFESSORS_NAME);
        sendKeysIntoWebElement(surnameField, CHANGED_PROFESSORS_NAME);
        sendKeysIntoWebElement(patronymicField, CHANGED_PROFESSORS_NAME);
        waitForElementIsClickableAndClick(submitButton);
        notification = driver.findElement(new By.ByXPath("//section[@id='alertify-logs']/" +
                "article[contains(text(),'Преподаватель сохранен')]"));
        wait.waitForElementIsVisible(notification);
        return ProfessorsPage.this;
    }

}
