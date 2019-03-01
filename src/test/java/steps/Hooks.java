package steps;

import base.DriverFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    Logger logger = LoggerFactory.getLogger(Hooks.class);
    DriverFactory driverFactory = new DriverFactory();

    @Before
    public void beforeScenario(Scenario scenario) {
        logger.info("**********************************************");
        logger.info("Start new test: " + scenario.getName());
        driverFactory.createDriver();
    }

    @After
    public void afterAll() {
        driverFactory.tearDown();
    }
}
