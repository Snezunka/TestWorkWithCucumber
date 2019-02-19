package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String browserName = Utils.getProperties("browser.properties").getProperty("browser.name");
            if ("firefox".equals(browserName)) {
                System.setProperty("webdriver.gecko.driver",
                        System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
            } else if("chrome".equals(browserName)) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
            } else if("safari".equals(browserName) || "edge".equals(browserName)) {
                throw new UnsupportedOperationException("Sorry, currently tests don't support safari and edge browsers");
            } else throw new IllegalArgumentException("You entered incorrect browser name. Please check.");
        }
        return driver;
    }
}

