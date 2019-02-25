package runners;

import base.DriverFactory;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;


@CucumberOptions(features = {"src/test/java/features"},
        glue = {"steps"},
        tags = "@Smoke")
public class CucumberRunner extends AbstractTestNGCucumberTests {

    protected static WebDriver driver;
    protected static WebDriverWait driverWaiter;
    protected static Logger logger;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driverWaiter = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    protected void initElements(CucumberRunner page) {
        PageFactory.initElements(driver, page);
    }

    @AfterClass(alwaysRun = true)
    private void tearDown() {
        driver.quit();
    }
}
