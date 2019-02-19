package steps;

import cucumber.api.java.en.Then;
import pages.ResultPage;

public class ResultPageSteps {

    private ResultPage resultPage;

    public ResultPageSteps() {
        resultPage = new ResultPage();
    }

    @Then("^Title of 'Result' page contains \"([^\"]*)\"$")
    public void titleOfResultPageContains(String expectedText) {
        resultPage.titleShouldContain(expectedText);
    }
}
