package pages;

import base.DriverTimeouts;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.LoggerFactory;
import runners.CucumberRunner;

public class GooglePage extends CucumberRunner {

    public GooglePage() {
        PageFactory.initElements(driver, this);
        logger = LoggerFactory.getLogger(GooglePage.class);
    }

    @FindBy(name = "q")
    private WebElement searchField;

    @FindBy(name = "btnK")
    private WebElement searchButton;

    public void openPage(String url) {
        driver.get(url);
    }

    public void googlePageShouldBeOpened() {
        driverWaiter.withTimeout(DriverTimeouts.MEDIUM_TIMEOUT).until(ExpectedConditions.visibilityOf(searchField));
        Assertions.assertThat(searchButton.isEnabled()).isTrue();
        logger.info("Google page is opened successfully");
    }

    public void searchInGoogle(String searchText) {
        searchField.sendKeys(searchText);
        driverWaiter.withTimeout(DriverTimeouts.SHORT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
        logger.info("Text " + searchText + " was search in google");
    }
}
