package by.paulouskin.selenium.intro.pages;

import by.paulouskin.selenium.intro.LoadWebDriverProperties;
import by.paulouskin.selenium.intro.pages.components.SearchFilterForm;
import by.paulouskin.selenium.intro.pages.components.SearchResultTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static by.paulouskin.selenium.intro.EtsyComSelectors.GDPR_ALERT_WINDOW;

public class EtsyComHomePage extends LoadableComponent<EtsyComHomePage>{

    private WebDriver webDriver;

    private SearchFilterForm filterForm;
    private SearchResultTable searchResultTable;

    private static final By SIGN_IN = By.cssSelector("#sign-in");

    private final int WAIT_FOR_PAGE_LOAD_PERIOD = 60;

    @FindBy(css = "div.search-button-wrapper")
    private WebElement searchButton;
    @FindBy(css = "#search-query")
    private WebElement searchQuery;

    public EtsyComHomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
        filterForm = new SearchFilterForm(webDriver);
        searchResultTable = new SearchResultTable(webDriver);
        webDriver.get(LoadWebDriverProperties.loadPropertiesFromFile("run.properties").get("target_address"));
        new WebDriverWait(webDriver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(GDPR_ALERT_WINDOW));
        webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div/div/div[2]/button")).click();
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
            throw new Error("Page have not been loaded in time(");
        }
    }

    private boolean etsyComPageLoaded(WebDriver webDriver) {
        try {
            new WebDriverWait(webDriver, WAIT_FOR_PAGE_LOAD_PERIOD)
                    .until(ExpectedConditions.visibilityOfElementLocated(SIGN_IN));
        } catch (WebDriverException ex) {
            return false;
        }
        return true;
    }
}
