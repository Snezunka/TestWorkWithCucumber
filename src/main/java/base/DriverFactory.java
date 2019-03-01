package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static WebDriver driver;
    protected static WebDriverWait driverWaiter;
    protected Logger logger;

    public WebDriver createDriver() {
        if (driver == null) {
            String browserName = Utils.getProperties("browser.properties").getProperty("browser.name");
            if ("firefox".equals(browserName)) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if("chrome".equals(browserName)) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if("safari".equals(browserName) || "edge".equals(browserName)) {
                throw new UnsupportedOperationException("Sorry, currently tests don't support safari and edge browsers");
            } else throw new IllegalArgumentException("You entered incorrect browser name. Please check.");
        }
        driverWaiter = new WebDriverWait(driver, DriverTimeouts.MEDIUM_TIMEOUT.getSeconds());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(DriverTimeouts.SHORT_TIMEOUT.getSeconds(), TimeUnit.SECONDS);
        return driver;
    }

    public void tearDown() {
        driver.quit();
        driver = null;
    }

    protected void initElements(DriverFactory page) {
        PageFactory.initElements(driver, page);
    }
}

