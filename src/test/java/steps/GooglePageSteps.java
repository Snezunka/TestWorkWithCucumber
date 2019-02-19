package steps;

import base.Utils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.GooglePage;

public class GooglePageSteps {

    private GooglePage googlePage;

    public GooglePageSteps() {
        googlePage = new GooglePage();
    }

    @When("^Open 'Google' page$")
    public void openGooglePage() {
        String siteName = Utils.getProperties("domain.properties").getProperty("site.url");
        googlePage.openPage(siteName);
    }

    @Then("^User is on 'Google' page$")
    public void googlePageShouldBeOpened() {
        googlePage.googlePageShouldBeOpened();
    }

    @When("^Search in google word \"([^\"]*)\"$")
    public void searchInGoogleWord(String searchText){
        googlePage.searchInGoogle(searchText);
    }
}
