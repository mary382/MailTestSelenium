package utils;

import drivers.DriverSingleton;
import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;

public class Screenshoter extends TestListenerAdapter{
    private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";
    private WebDriver driver;

    @Override
    public void onTestFailure(ITestResult tr) {
          Screenshoter.takeScreenshot();
    }

    public static void takeScreenshot() {
        WebDriver driver = DriverSingleton.getWebDriverInstance();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
            File copy = new File(screenshotName + ".png");
            FileUtils.copyFile(screenshot, copy);
            System.out.println("Saved screenshot: " + screenshotName);
        } catch (IOException e) {
            System.out.println("Failed to make screenshot");
        }
    }

    public void highlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='5px solid red'", element);
    }
}
