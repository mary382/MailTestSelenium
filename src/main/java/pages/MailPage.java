package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailPage extends AbstractPage {

private static final String MAIL_URL="https://e.mail.ru/messages/inbox/?back=1";

    @FindBy(xpath ="//*[@id='b-toolbar__left']/div/div/div[2]/div/a" )
    private WebElement writeNewMailButton;

    @FindBy(css = "textarea.js-input.compose__labels__input")
    private WebElement areaForMailAdress;

    @FindBy(css = "input.b-input")
    private WebElement areaForMailSubject;

    @FindBy(tagName = "iframe")
    private WebElement iframeForTextField;

    @FindBy(xpath = "//body[@id='tinymce']")
    private WebElement bodyForTextField;

    @FindBy(xpath ="//div[@data-name='saveDraft']")
    private WebElement saveMailButton;

    @FindBy(css="span[class='time']")
    private WebElement waitFor;

    @FindBy(xpath = "a[@href]='/messages/drafts/'")
    private WebElement draftButton;

    @FindBy(xpath = "//div[@data-name = 'send']")
    private WebElement sendButton;

    @FindBy(id="PH_logoutLink")
    private WebElement logOff;

    public MailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver,this);
    }

    @Override
    public void openPage() {
        driver.navigate().to(MAIL_URL);
    }

    public MailPage writeNewMailButton(){
        writeNewMailButton.click();
        return this;
    }


    public void clickLogOff(){
        logOff.click();
    }

    public void writeMailButtonClick()  {
        writeNewMailButton.click();
    }

    public void fillAdressField(String  adress)  {
        areaForMailAdress.sendKeys(adress);
    }

    public void fillSubjectField(String  subject)  {
        areaForMailSubject.sendKeys(subject);
    }

    public void fillFrame(String  string)  {
        driver.switchTo().frame(iframeForTextField);
        bodyForTextField.clear();
        bodyForTextField.sendKeys(string);
        driver.switchTo().defaultContent();
    }

    public void saveButtonClick()  {
        saveMailButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(waitFor));
    }


}
