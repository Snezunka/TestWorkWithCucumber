package runners;

import base.DriverFactory;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


@CucumberOptions(features = {"src/test/java/features"},
        glue = {"steps"},
        tags = "@Smoke")
public class CucumberRunner extends AbstractTestNGCucumberTests {

    protected static WebDriver driver;
    protected static WebDriverWait driverWaiter;
    protected static Logger logger;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driverWaiter = new WebDriverWait(driver, 20);
    }

    @AfterTest
    private void tearDown() {
        driver.quit();
    }
}
