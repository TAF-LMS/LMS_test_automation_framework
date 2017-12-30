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

    private WebElement editProfessorsButton;

    @FindBy(xpath = "//a[@class='deleteButton']")
    private WebElement deleteButton;

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

    private WebElement professorForSearch;

    WebElement notification;

  /*  public ProfessorsPage checkActionButtons() {
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
    }*/

    public ProfessorsPage addProfessor(String userName, String password, String surname, String name, String patronymic) {
        wait.waitForPageToLoad();
        waitForElementIsClickableAndClick(addProfessorButton);
        sendKeysIntoWebElement(userNameField, userName);
        sendKeysIntoWebElement(passwordField, password);
        sendKeysIntoWebElement(confirmPasswordField, password);
        sendKeysIntoWebElement(surnameField, surname);
        sendKeysIntoWebElement(nameField, name);
        sendKeysIntoWebElement(patronymicField, patronymic);
        waitForElementIsClickableAndClick(secretaryBox);
        waitForElementIsClickableAndClick(lecturerHasGraduateStudentsBox);
        waitForElementIsClickableAndClick(submitButton);
        notification = driver.findElement(new By.ByXPath("//section[@id='alertify-logs']/" +
                "article[contains(text(),'Преподаватель сохранен')]"));
        wait.waitForElementIsVisible(notification);
        return ProfessorsPage.this;
    }

    /*public void checkProffCreation() {
        wait.waitForPageToLoad();
        wait.waitForElementIsVisible(newProfessorName);
        wait.waitForElementIsVisible(newProfessorLogin);
        Assert.assertEquals(lastEnter.getText(), ("-"));
        Assert.assertEquals(countOfSubjects.getText(), ("-"));
    }
*/

    public void checkStatistic() {
        waitForElementIsClickableAndClick(statButton);
        waitForElementIsClickableAndClick(closeButton);
    }

    public ProfessorsPage removeProfessor(String name) {
        wait.waitForPageToLoad();
        /*String proffName = firstProfessorName.getText();*/
        deleteButton = driver.findElement(By.xpath("//tr/td[contains(text(),'" + name + "')]" +
                "/../td[@class='']/div/a[contains(@href,'DeleteLecturer')]"));
        waitForElementIsClickableAndClick(deleteProfessorButton);
        waitForElementIsClickableAndClick(confirmButton);
        wait.waitForPageToLoad();
        notification = driver.findElement(new By.ByXPath("//section[@id='alertify-logs']/" +
                "article[contains(text(),'удален')]"));
        wait.waitForElementIsVisible(notification);
        return ProfessorsPage.this;
    }

    public ProfessorsPage changeProfessorInformation(String name, String changedName, String changedSurname, String changedPatronymic) {
        wait.waitForPageToLoad();
        editProfessorsButton = driver.findElement(By.xpath("//tr/td[contains(text(),'" + name + "')]" +
                "/../td[@class='']/div/a[contains(@href,'EditProfessor')]"));
        waitForElementIsClickableAndClick(editProfessorsButton);
        sendKeysIntoWebElement(nameField, changedName);
        sendKeysIntoWebElement(surnameField, changedSurname);
        sendKeysIntoWebElement(patronymicField, changedPatronymic);
        waitForElementIsClickableAndClick(submitButton);
        notification = driver.findElement(new By.ByXPath("//section[@id='alertify-logs']/" +
                "article[contains(text(),'Преподаватель сохранен')]"));
        wait.waitForElementIsVisible(notification);
        return ProfessorsPage.this;
    }

    public ProfessorsPage searchProfessor(String nameForSearch) {
        wait.waitForPageToLoad();
        professorForSearch = driver.findElement(By.xpath("//td[contains(text(),'" + nameForSearch + "')]"));
        sendKeysIntoWebElement(searchField, nameForSearch);
        wait.waitForElementIsVisible(professorForSearch);
        return ProfessorsPage.this;
    }

}
