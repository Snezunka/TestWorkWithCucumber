package steps;

import base.Utils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.GooglePage;

public class GooglePageSteps {

    private GooglePage googlePage;

    public GooglePageSteps() {
        googlePage = new GooglePage();
    }

    @Given("^Open 'Google' page$")
    public void openGooglePage() {
        String siteName = Utils.getProperties("domain.properties").getProperty("site.url");
        googlePage.openPage(siteName);
    }


    @When("^Search in google word \"([^\"]*)\"$")
    public void searchInGoogleWord(String searchText){
        googlePage.searchInGoogle(searchText);
    }
}
