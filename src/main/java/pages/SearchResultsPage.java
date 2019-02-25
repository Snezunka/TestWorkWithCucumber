package pages;

import base.DriverTimeouts;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.LoggerFactory;
import runners.CucumberRunner;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class SearchResultsPage extends CucumberRunner {

    public SearchResultsPage() {
        initElements(this);
        logger = LoggerFactory.getLogger(SearchResultsPage.class);
    }

    private String pagePath = "//table[@id='nav']//a[@aria-label='Page ";

    @FindBy(name = "q")
    private WebElement searchField;

    @FindBy(id = "top_nav")
    private WebElement navigationPanel;

    @FindBy(xpath = "//div[@class='rc']//h3")
    private List<WebElement> linkResults;

    @FindBy(xpath = "//div[@class='rc']//cite")
    private List<WebElement> domainResults;

    public void searchResultsPageShouldBeOpened() {
        driverWaiter.withTimeout(DriverTimeouts.MEDIUM_TIMEOUT).until(ExpectedConditions.visibilityOf(navigationPanel));
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(searchField.isDisplayed()).isTrue().withFailMessage("'Search' field is not displayed");
        softAssert.assertThat(linkResults.size()).isGreaterThan(0).withFailMessage("Number of link results should be > 0");
        softAssert.assertAll();
        logger.info("'Search results' page is opened successfully");
    }

    public void clickOnFirstResultLink() {
        Optional<WebElement> firstResultLink = linkResults.stream().findFirst();
        firstResultLink.ifPresent(WebElement::click);
        logger.info("Click on first result link");
    }

    public void fiveFirstSearchResultPagesShouldContain(String expectedDomain) {
        boolean isDomainPresent = false;
        int pageIndex = 1;
        int maxNumberOfSearchResultPages = 5;
        while(pageIndex <= maxNumberOfSearchResultPages ) {
            isDomainPresent = domainResults.stream().anyMatch(domain -> domain.getText().contains(expectedDomain));
            if (isDomainPresent) {
                logger.info("Domain " + expectedDomain + " is present on search result page with index " + pageIndex);
                return;
            } else if (pageIndex != maxNumberOfSearchResultPages ) {
                goToPage(++pageIndex);
            } else ++pageIndex;
        }
        Assertions.assertThat(isDomainPresent).withFailMessage("Domain " + expectedDomain + " is NOT present on first 5 pages of Search results").isTrue();
    }

    private void goToPage(int pageIndex) {
        logger.info("Try to open page with index " + pageIndex);
        By pagePath = getPathForPage(pageIndex);
        driver.findElement(pagePath).click();
        waitSearchResultsOnNewPageLoad();
    }

    private void waitSearchResultsOnNewPageLoad() {
        Awaitility.await()
                .atMost((10000), TimeUnit.MILLISECONDS)
                .until(() -> domainResults.size() > 0);
    }

    private By getPathForPage(int pageIndex) {
        return By.xpath(pagePath + pageIndex + "']");
    }
}
