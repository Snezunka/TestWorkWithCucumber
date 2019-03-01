package steps;

import base.Utils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import pages.GooglePage;
import pages.ResultPage;
import pages.SearchResultsPage;

public class GoogleSteps {

    @Given("^Open 'Google' page$")
    public void openGooglePage() {
        String siteName = Utils.getProperties("domain.properties").getProperty("site.url");
        new GooglePage().openPage(siteName);
    }

    @When("^Search in google word \"([^\"]*)\"$")
    public void searchInGoogleWordAndWaitSearchResults(String searchText){
        new GooglePage().searchInGoogle(searchText);
        new SearchResultsPage().waitUntilNavigationPanelIsVisible();
    }

    @When("^User select first result link on 'Search results' page$")
    public void userSelectFirstResultLinkOnSearchResultsPage() {
        new SearchResultsPage().clickOnFirstResultLink();
    }

    @Then("^(\\d+) first search results pages contain \"([^\"]*)\" domain$")
    public void searchResultsPagesContainExpectedDomain(int numberOfResultPages, String expectedDomain) {
        Assertions.assertThat(new SearchResultsPage().fiveFirstSearchResultPagesShouldContain(numberOfResultPages, expectedDomain))
                .withFailMessage("Domain " + expectedDomain + " is NOT present on first " + numberOfResultPages + " pages of Search results")
                .isTrue();
    }

    @Then("^Title of 'Result' page contains \"([^\"]*)\"$")
    public void titleOfResultPageContains(String expectedText) {
        new ResultPage().titleShouldContain(expectedText);
    }
}
