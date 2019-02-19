package pages;

import org.awaitility.Awaitility;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;
import runners.CucumberRunner;

import java.util.concurrent.TimeUnit;

public class ResultPage extends CucumberRunner {

    public ResultPage() {
        PageFactory.initElements(driver, this);
        logger = LoggerFactory.getLogger(ResultPage.class);
    }

    public void titleShouldContain(String text) {
        Awaitility.await()
                .atMost((10), TimeUnit.SECONDS)
                .until(() -> driver.getTitle().toLowerCase().contains(text));
        logger.info("Page title contains " + text);
    }
}
