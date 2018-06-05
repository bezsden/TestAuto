package by.paulouskin.selenium.intro.pages;

import by.paulouskin.selenium.intro.LoadWebDriverProperties;
import by.paulouskin.selenium.intro.pages.components.SearchFilterForm;
import by.paulouskin.selenium.intro.pages.components.SearchResultTable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static by.paulouskin.selenium.intro.EtsyComSelectors.GDPR_ALERT_WINDOW;
import static java.lang.System.currentTimeMillis;

public class EtsyComHomePage extends LoadableComponent<EtsyComHomePage>{

    private WebDriver webDriver;

    private SearchFilterForm filterForm;
    private SearchResultTable searchResultTable;

    private static final By SIGN_IN = By.cssSelector("#sign-in");

    private final int WAIT_FOR_PAGE_LOAD_PERIOD = 10;

    @FindBy(css = "div.search-button-wrapper")
    private WebElement searchButton;
    @FindBy(css = "#search-query")
    private WebElement searchQuery;

    public EtsyComHomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
        webDriver.get(LoadWebDriverProperties.loadPropertiesFromFile("run.properties").get("target_address"));
        new WebDriverWait(webDriver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(GDPR_ALERT_WINDOW));
        webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div/div/div[2]/button")).click();
        isLoaded();
        filterForm = new SearchFilterForm(webDriver);
        searchResultTable = new SearchResultTable(webDriver);
    }


    public EtsyComHomePage get() {
        try {
            isLoaded();
            return this;
        } catch (Error e) {
            load();
        }
        isLoaded();
        return this;
    }
    public void searchForItem(String query) {
        enterSearchQuery(query);
        getSearchButton().click();
    }

    private void enterSearchQuery(String query) {
        searchQuery.clear();
        searchQuery.sendKeys(query);
    }

    private WebElement getSearchButton() {
        return searchButton;
    }

    public void selectSpecialOffersFilter(String specialOffer) {
        filterForm.selectSpecialOffer(specialOffer);
    }

    public void selectShippingOptions(String shipping_option) {
        filterForm.selectShippingOption(shipping_option);
    }


    public void selectItemType(String itemType) {
        filterForm.selectItemType(itemType);
    }

    public List<String> getAppliedSearchFilters() {
        return searchResultTable.getAppliedSearchFilters();
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        if(!etsyComPageLoaded(webDriver)) {
            webDriver.quit();
            throw new Error("Page have not been loaded in time(");
        }
    }

    private boolean etsyComPageLoaded(WebDriver webDriver) {
        try {
            new WebDriverWait(webDriver, WAIT_FOR_PAGE_LOAD_PERIOD)
                    .until(ExpectedConditions.visibilityOfElementLocated(SIGN_IN));
        } catch (WebDriverException ex) {
            String time = String.valueOf(currentTimeMillis());
            File screenshot1 = new File("target/screenshot"+time+".png");
            File outFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(outFile.toPath(), screenshot1.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }
}
