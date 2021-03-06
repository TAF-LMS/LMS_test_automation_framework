package by.bntu.lms.pages.professor;

import by.bntu.lms.pages.AbstractPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Data
@Log4j2
@EqualsAndHashCode(callSuper = false)
public class SubjectManagementPage extends AbstractPage {
    @FindBy(xpath = "//a[contains(@href,'Subject/Create')]")
    private WebElement addSubjectButton;

    @FindBy(id = "DisplayName")
    private WebElement subjectNameField;

    @FindBy(id = "ShortName")
    private WebElement subjectAbbreviationField;

    @FindBy(xpath = "//div[contains(@class,'btn-group')]/button")
    private WebElement showGroupsButton;

    @FindBy(xpath = "//label/input[@type='checkbox']/..")
    private WebElement groupLabel;

    @FindBy(id = "saveButton")
    private WebElement saveButton;

    @FindBy(xpath = "//article[contains(@class,'success')]")
    private WebElement successfulNotification;


    @FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
    private WebElement removeSubjectButton;

    private String removedSubjectsXpath = "//a[@class='deleteSubjectButton']";

    public SubjectManagementPage() {
    }

    public void checkSuccessfulNotification() {
        wait.waitForElementIsVisible(successfulNotification);
    }

    private WebElement initGroupCheckBox(String groupNumber) {
        return driver.findElement(By.xpath("//label[contains(text(),'" + groupNumber + "')]" +
                "/input[@type='checkbox']"));
    }

    public SubjectManagementPage addNewSubject(String subjectName, String subjectAbbreviation, String groupNumbers) {
        log.info("Adding new subject");
        waitForElementIsClickableAndClick(addSubjectButton);
        sendKeysIntoWebElement(subjectNameField, subjectName);
        sendKeysIntoWebElement(subjectAbbreviationField, subjectAbbreviation);
        waitForElementIsClickableAndClick(showGroupsButton);
        String[] groupNumberList = groupNumbers.split(";");

        for (String groupNumber : groupNumberList) {
            waitForElementIsClickableAndClick(initGroupCheckBox(groupNumber));
        }
        waitForElementIsClickableAndClick(saveButton);
        return this;
    }

    public void deleteAllSubjects() {
        log.info("Removing all subjects");
        List<WebElement> removedSubjects;
        int removedSubjectsSize = initWebElements(removedSubjectsXpath).size();
        log.info("Subjects quantity: " + removedSubjectsSize);
        for (int i = 0; i < removedSubjectsSize; i++) {
            driver.navigate().refresh();
            wait.waitForPageToLoad();
            removedSubjects = initWebElements(removedSubjectsXpath);
            waitForElementIsClickableAndClick(removedSubjects.get(0));
            waitForElementIsClickableAndClick(removeSubjectButton);
            checkSuccessfulNotification();
        }
    }
}