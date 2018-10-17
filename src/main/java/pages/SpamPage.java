package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Collection;


public class SpamPage extends AbstractPage {

    public static final String SPAM_URL="https://e.mail.ru/messages/spam/";



    public boolean findMailInSpam(String adress,String subject,String text) {
        Collection<WebElement> draftList = driver.findElements(By.xpath("//div[@class='b-datalist__item__info']"));
        if (!draftList.isEmpty()){
            for (WebElement element : draftList) {
                if ((element.findElement(By.xpath("//div[@class='b-datalist__item__subj']")).getText().equalsIgnoreCase(subject+text))
                        && (element.findElement(By.xpath("//div[@class='b-datalist__item__addr']")).getText().equalsIgnoreCase(adress))) {
                    element.click();
                    return true;
                }
            }
        }
        return false;

    }

    public SpamPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver,this);
    }

    public void openPage() {
        driver.navigate().to(SPAM_URL);
    }
}
