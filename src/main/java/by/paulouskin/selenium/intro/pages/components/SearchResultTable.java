package by.paulouskin.selenium.intro.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultTable {
    
    private WebDriver webDriver;

    private static final By FILTER_TAG = By.cssSelector("div.tag");

    @FindBy(css = "div.search-listings-group")
    WebElement searchResultWrapper;

    public SearchResultTable(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public List<String> getAppliedSearchFilters() {
        return searchResultWrapper.findElements(FILTER_TAG)
                .stream()
                .map(webElement -> webElement.getText())
                .collect(Collectors.toList());
    }
}
