package pages;

import base.DriverFactory;
import base.DriverTimeouts;
import org.awaitility.Awaitility;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ResultPage extends DriverFactory {

    public ResultPage() {
        initElements(this);
        logger = LoggerFactory.getLogger(ResultPage.class);
    }

    public void titleShouldContain(String text) {
        Awaitility.await()
                .atMost(DriverTimeouts.MEDIUM_TIMEOUT.getSeconds(), TimeUnit.SECONDS)
                .until(() -> driver.getTitle().toLowerCase().contains(text));
        logger.info("Page title contains " + text);
    }
}
