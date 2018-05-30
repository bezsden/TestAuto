package by.paulouskin.selenium.intro.pages;

import by.paulouskin.selenium.intro.pages.components.SearchFilterForm;
import by.paulouskin.selenium.intro.pages.components.SearchResultTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EtsyComHomePage {

    private WebDriver webDriver;

    private SearchFilterForm filterForm;
    private SearchResultTable searchResultTable;

    @FindBy(css = "div.search-button-wrapper")
    private WebElement searchButton;
    @FindBy(css = "#search-query")
    private WebElement searchQuery;

    public EtsyComHomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
        filterForm = new SearchFilterForm(webDriver);
        searchResultTable = new SearchResultTable(webDriver);
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
}
