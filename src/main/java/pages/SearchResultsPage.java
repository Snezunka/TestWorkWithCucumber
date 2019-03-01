package pages;

import base.DriverFactory;
import base.DriverTimeouts;
import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class SearchResultsPage extends DriverFactory {

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

    public void waitUntilNavigationPanelIsVisible() {
        driverWaiter.until(ExpectedConditions.visibilityOf(navigationPanel));
    }

    public void clickOnFirstResultLink() {
        Optional<WebElement> firstResultLink = linkResults.stream().findFirst();
        firstResultLink.ifPresent(WebElement::click);
        logger.info("Click on first result link");
    }

    public boolean fiveFirstSearchResultPagesShouldContain(int numberOfSearchResultPages, String expectedDomain) {
        boolean isDomainPresent = false;
        int pageIndex = 1;
        while(pageIndex <= numberOfSearchResultPages ) {
            isDomainPresent = domainResults.stream().anyMatch(domain -> domain.getText().contains(expectedDomain));
            if (isDomainPresent) {
                logger.info("Domain " + expectedDomain + " is present on search result page with index " + pageIndex);
                break;
            } else if (pageIndex != numberOfSearchResultPages) {
                goToPage(++pageIndex);
            } else ++pageIndex;
        }
        return isDomainPresent;
    }

    private void goToPage(int pageIndex) {
        logger.info("Try to open page with index " + pageIndex);
        By pagePath = getPathForPage(pageIndex);
        driver.findElement(pagePath).click();
        waitSearchResultsOnNewPageLoad();
    }

    private void waitSearchResultsOnNewPageLoad() {
        Awaitility.await()
                .atMost(DriverTimeouts.MEDIUM_TIMEOUT.getSeconds(), TimeUnit.SECONDS)
                .until(() -> domainResults.size() > 0);
    }

    private By getPathForPage(int pageIndex) {
        return By.xpath(pagePath + pageIndex + "']");
    }
}
