package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverSingleton {
    private static WebDriver driver;
    private static final String CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String CHROME_DRIVER_EXE_PATH = ".\\chromedriver\\chromedriver.exe";

     private DriverSingleton(){};

    public static WebDriver getWebDriverInstance() {
        if (driver != null) {
            return driver;
        }
        return driver = getDriver();
    }

    public static WebDriver getDriver(){
        if (null == driver){
            System.setProperty(CHROME_DRIVER, CHROME_DRIVER_EXE_PATH);
            //driver = new ChromeDriver();
            try {
                driver = new RemoteWebDriver(new URL("http://10.6.103.19:4445/wd/hub"), DesiredCapabilities.chrome());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver(){
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Cannot kill browser");
            } finally {
                driver = null;
            }
        }
    }
}