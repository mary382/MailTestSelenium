package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;
import java.util.Iterator;

public class SendPage extends AbstractPage {

    public static final String SEND_URL="https://e.mail.ru/messages/sent/";

    @FindBy(xpath = "//*[@id='b-nav_folders']/div/div[2]/a")
    private WebElement sentItems;

    @FindBy(xpath = "//div[@class='b-datalist__item__info']")
    private WebElement waitFor;

    @FindBy(css = "//div[@class='b-datalist__item__subj']")
    private WebElement selectMail;

    @FindBy(xpath = "//span[@class='b-toolbar__btn__text b-toolbar__btn__text_pad'][text()='Спам']")
    private WebElement moveMailToSpamButton;


    public SendPage sentItemsClick() {
        sentItems.click();
        return this;
    }

    public SendPage clickSelectMailButton(){
        selectMail.click();
        return this;
    }

    public SendPage clickMoveToSpamButton(){
        moveMailToSpamButton.click();
        return this;
    }


    public boolean verifySentFolder(String addresseeMail, String subject, String text) {
        Collection<WebElement> sentList = driver.findElements(By.xpath("//div[@class='b-datalist__item__info']"));
        for (WebElement element : sentList) {
            if ((element.findElement(By.xpath("//div[@class='b-datalist__item__subj']")).getText().equalsIgnoreCase(subject+text))
                    && (element.findElement(By.xpath("//div[@class='b-datalist__item__addr']")).getText().equalsIgnoreCase(addresseeMail))) {
                element.click();
                return true;
            }
        }
        return false;
    }

    public SendPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver,this);
    }

    public void openPage() {
      driver.navigate().to(SEND_URL);
    }



    public boolean finderMail(String addresseeMail, String subject){
        Collection<WebElement> inboxList = driver.findElements(By.xpath("//div[@data-bem='b-datalist__item']"));
        if (!inboxList.isEmpty()){
            for (WebElement element : inboxList) {
                System.out.println(element.findElement(By.xpath("//div[@class='b-datalist__item__subj']")).getText().equalsIgnoreCase(subject));
                if ((element.findElement(By.xpath("//div[@class='b-datalist__item__subj']")).getText().equalsIgnoreCase(subject))
                        && (element.findElement(By.xpath("//div[@class='b-datalist__item__addr")).getText().equalsIgnoreCase(addresseeMail))) {
                    return true;
                }
            }
        }
        return false;
    }
}
