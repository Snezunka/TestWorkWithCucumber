package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class )
@CucumberOptions(features = {"src/test/java/features"},
        glue = {"steps"},
        tags = "@Smoke")
public class RunCukesTest {

}