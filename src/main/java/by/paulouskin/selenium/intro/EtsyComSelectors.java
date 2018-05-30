package by.paulouskin.selenium.intro;

import org.openqa.selenium.By;

public class EtsyComSelectors {
    public static final By GDPR_ALERT_WINDOW = By.cssSelector("div.alert");
    public static final By SEARCH_FIELD = By.cssSelector("#search-query");
    public static final By SEARCH_FIELD_WRAPPER = By.cssSelector("div.gnav-search-inner");
    public static final By SEARCH_SUGGESTIONS_WRAPPER = By.cssSelector("#search-suggestions");
    public static final By SEARCH_SUGGESTIONS_LIST = By.cssSelector("#search-suggestions ul li");
    public static final By SEARCH_SUGGESTIONS_FIRST_RESULT = By.cssSelector("#search-suggestions ul li[class*='as-first']");
    public static final By SEARCH_RESULT_LIST = By.cssSelector("div[class*='search-listings-group']");
    public static final By PAGINATION_WRAPPER = By.cssSelector("nav[class*='pagination']");
    public static final By SEARCH_FILTERS_FORM = By.cssSelector("#search-filter-reset-form");
    public static final By SHIP_TO_SELECTION = By.id("ship_to_select");
    public static final By FREE_SHIPPING_CHECKBOX = By.name("free_shipping");
    public static final By FILTER_TAGS = By.cssSelector("div.tag");
}
