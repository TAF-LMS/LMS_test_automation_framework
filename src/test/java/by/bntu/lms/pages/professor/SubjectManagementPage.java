package by.bntu.lms.pages.professor;

import by.bntu.lms.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SubjectManagementPage extends AbstractPage {

    public SubjectManagementPage(WebDriver driver) {
        super(driver);
    }

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

    private WebElement initGroupCheckBox(String groupNumber) {
        return driver.findElement(By.xpath("//label[contains(text(),'" + groupNumber + "')]" +
                "/input[@type='checkbox']"));
    }

    public void addNewSubject(String subjectName, String subjectAbbreviation, String groupNumbers) {
        waitForElementIsClickableAndClick(addSubjectButton);
        sendKeysIntoWebElement(subjectNameField, subjectName);
        sendKeysIntoWebElement(subjectAbbreviationField, subjectAbbreviation);
        waitForElementIsClickableAndClick(showGroupsButton);
        String[] groupNumberList = groupNumbers.split(";");

        for (String groupNumber : groupNumberList) {
            waitForElementIsClickableAndClick(initGroupCheckBox(groupNumber));
        }
        waitForElementIsClickableAndClick(saveButton);

    }

}
