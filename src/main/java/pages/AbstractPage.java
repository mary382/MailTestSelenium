package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public abstract void openPage();

    public AbstractPage dragNDropMailToSpam(By draggable, By droppable) {
        waitForElementVisible(draggable);
        waitForElementVisible(droppable);
        WebElement element = driver.findElement(draggable);
        WebElement target = driver.findElement(droppable);
        new Actions(driver).dragAndDrop(element, target).build().perform();
        return this;
    }

    public void waitForElementVisible(By locator) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitForElementClickable(By locator) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(locator));
    }
}
