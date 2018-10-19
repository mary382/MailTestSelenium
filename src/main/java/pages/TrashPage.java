package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Collection;

public class TrashPage extends AbstractPage{
    public static final String TRASH_URL ="https://e.mail.ru/messages/trash/";
    public static final String AUTHOR_NAME="Maryia Sidaruk";

    public TrashPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver,this);
    }

    public boolean findMailInTrash(String adress, String subject, String text){
        Collection<WebElement> draftList = driver.findElements(By.xpath("//div[@class='b-datalist__item__info']"));
        if (!draftList.isEmpty()){
            for (WebElement element : draftList) {
                if ((element.findElement(By.xpath("//div[@class='b-datalist__item__subj']")).getText().equalsIgnoreCase(subject+text))
                        && (element.findElement(By.xpath("//div[@class='b-datalist__item__addr']")).getText().equalsIgnoreCase(AUTHOR_NAME))) {
                    element.click();
                    return true;
                }
                System.out.println(element.findElement(By.xpath("//div[@class='b-datalist__item__subj']")).getText());
                System.out.println(element.findElement(By.xpath("//div[@class='b-datalist__item__addr']")).getText());
            }
        }
        return false;
    }

    public void openPage() {
        driver.navigate().to(TRASH_URL);
    }
}
