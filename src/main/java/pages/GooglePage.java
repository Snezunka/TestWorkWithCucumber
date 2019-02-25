package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.LoggerFactory;
import runners.CucumberRunner;

public class GooglePage extends CucumberRunner {

    public GooglePage() {
        initElements(this);
        logger = LoggerFactory.getLogger(GooglePage.class);
    }

    @FindBy(name = "q")
    private WebElement searchField;

    @FindBy(name = "btnK")
    private WebElement searchButton;

    public void openPage(String url) {
        driver.get(url);
    }


    public void searchInGoogle(String searchText) {
        driverWaiter.until(ExpectedConditions.visibilityOf(searchField));
        searchField.sendKeys(searchText);
        driverWaiter.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
        logger.info("Text " + searchText + " was search in google");
    }
}
