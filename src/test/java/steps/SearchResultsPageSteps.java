package steps;

import base.Utils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.SearchResultsPage;

public class SearchResultsPageSteps {

    private SearchResultsPage searchResultsPage;

    public SearchResultsPageSteps() {
        searchResultsPage = new SearchResultsPage();
    }

    @Then("^User is on 'Search results' page$")
    public void searchResultsPageShouldBeOpened() {
        searchResultsPage.searchResultsPageShouldBeOpened();
    }

    @When("^User select first result link on 'Search results' page$")
    public void userSelectFirstResultLinkOnSearchResultsPage() {
        searchResultsPage.clickOnFirstResultLink();
    }

    @Then("^Five first search results pages contain expected domain$")
    public void fiveFirstSearchResultsPagesContainExpectedDomain() {
        String expectedDomain = Utils.getProperties("domain.properties").getProperty("expectedDomain");
        searchResultsPage.fiveFirstSearchResultPagesShouldContain(expectedDomain);
    }
}
