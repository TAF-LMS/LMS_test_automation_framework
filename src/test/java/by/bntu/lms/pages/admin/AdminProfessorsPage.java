package by.bntu.lms.pages.admin;

import by.bntu.lms.pages.AbstractPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Data
@Log4j2
@EqualsAndHashCode(callSuper = false)
public class AdminProfessorsPage extends AbstractPage {
    @FindBy(xpath = "//a[@class='statButton']")
    private WebElement statButton;

    @FindBy(xpath = "//a[@class='listButton']")
    private WebElement listButton;

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

    @FindBy(xpath = "//article[contains(@class,'success')]")
    private WebElement successfulNotification;

    private WebElement editProfessorsButton;
    private WebElement professorForSearch;
    private WebElement failedMessage;

    public AdminProfessorsPage() {
    }

    public void checkSuccessfulNotification() {
        wait.waitForElementIsVisible(successfulNotification);
    }

    /**
     * The method is able to check several error messages separated by ';' symbol
     *
     * @param expectedErrorMessage expected message at the page
     */
    public void checkFailedMessage(String expectedErrorMessage) {
        Arrays.asList(expectedErrorMessage.split(";")).forEach(
                errorMessage -> {
                    failedMessage = initWebElement("//li[contains(text(),'" + errorMessage + "')]");
                    wait.waitForElementIsVisible(failedMessage);
                }
        );
    }

    public void checkFailureNotification(String errorMessage) {
        wait.waitForElementIsVisible(initWebElement("//article[contains(text(),'" + errorMessage + "')]"));
    }

    //TODO: add submit password field
    public AdminProfessorsPage addProfessor(String userName, String password,
                                            String surname, String name,
                                            String patronymic) {
        log.info("Adding new professor");
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
        return AdminProfessorsPage.this;
    }

    public AdminProfessorsPage removeProfessor(String login) {
        log.info("Removing professor");
        wait.waitForPageToLoad();
        deleteProfessorButton = initWebElement("//tr/td[text()='" +
                login + "']" + "/../td[@class='']/div/a[contains(@href,'DeleteLecturer')]");
        waitForElementIsClickableAndClick(deleteProfessorButton);
        waitForElementIsClickableAndClick(confirmButton);
        wait.waitForPageToLoad();
        return this;
    }

    public AdminProfessorsPage changeProfessorInformation(String login, String changedName, String changedSurname,
                                                          String changedPatronymic) {
        log.info("Changing professor information");
        wait.waitForPageToLoad();
        editProfessorsButton = initWebElement("//tr/td[text()='" +
                login + "']" + "/../td[@class='']/div/a[contains(@href,'EditProfessor')]");
        waitForElementIsClickableAndClick(editProfessorsButton);
        sendKeysIntoWebElement(nameField, changedName);
        sendKeysIntoWebElement(surnameField, changedSurname);
        sendKeysIntoWebElement(patronymicField, changedPatronymic);
        waitForElementIsClickableAndClick(submitButton);
        return AdminProfessorsPage.this;
    }

    //TODO:Check this method Wait 3 seconds for professor
    public AdminProfessorsPage searchProfessor(String surname) throws InterruptedException {
        log.info("Search for a professor");
        wait.waitForPageToLoad();
        sendKeysIntoWebElement(searchField, surname);
        TimeUnit.SECONDS.sleep(3);
        //wait.waitForElementIsVisible(driver.findElement(By.xpath("//td[contains(text(),'" + surname + "')]")));
        return AdminProfessorsPage.this;
    }

    //TODO: not surname but login
    public void checkProfessorInSearchResults(String surname) {
        wait.waitForPageToLoad();
        professorForSearch = initWebElement("//td[contains(text(),'" + surname + "')]");
        wait.waitForElementIsVisible(professorForSearch);
    }
}
