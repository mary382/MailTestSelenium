package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class AbstractPage {

    protected WebDriver driver;
    private static final int WAIT_FOR_ELEMENT_SECONDS =30;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public abstract void openPage();


    public void waitForElementVisible(By locator)
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitForElementClickable(By locator)
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(locator));
    }
}
