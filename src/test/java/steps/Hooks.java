package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before("@Smoke")
    public void beforeScenario(Scenario scenario) {
        logger.info("**********************************************");
        logger.info("Start new test: " + scenario.getName());

    }

    @After("@Smoke")
    public void afterAll(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.error("Test " + scenario.getName() + " is failed.");
        }
    }
}
