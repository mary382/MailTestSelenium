package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AbstractPage;

public class LoginPage extends AbstractPage {
 public static final String URL= "https://mail.ru/";

 @FindBy(xpath ="//input[@id='mailbox:login']")
 private WebElement inputLogin;

 @FindBy(xpath = "//input[@id='mailbox:password']")
 private WebElement inputPassword;

 @FindBy(css = "input.o-control")
 private WebElement buttonSubmit;

 @FindBy(xpath = "//i[@id='PH_user-email']")
 private WebElement linkloggedInUser;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver,this);
    }

    @Override
    public void openPage(){
        driver.navigate().to(URL);
    }

    public void login(String username, String password){
        inputLogin.sendKeys(username);
        inputPassword.sendKeys(password);
        buttonSubmit.click();


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean homePageStatus(String username){
        WebDriverWait wait = new WebDriverWait(driver, 50);
        linkloggedInUser =wait.until(ExpectedConditions.visibilityOf(linkloggedInUser));
        return username.equals(linkloggedInUser.getText());
    }
}
