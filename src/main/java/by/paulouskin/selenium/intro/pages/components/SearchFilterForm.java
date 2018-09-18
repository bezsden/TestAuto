package by.paulouskin.selenium.intro.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchFilterForm {
    private WebDriver webDriver;

    @FindBy(css = "#search-filter-reset-form")
    private WebElement searchFilterForm;

    
    public SearchFilterForm(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    //Interactions

    public void selectSpecialOffer(String specialOffer) {
        getSpecialOffersLink(specialOffer).click();
    }

    public void selectShippingOption(String shipping_option) {
        getShippingOptionLink(shipping_option).click();
    }

    public void selectItemType(String itemType) {
        getItemType(itemType).click();
    }

    //Getters
    private WebElement getItemType(String itemType) {
        return returnSpecificFilterForFilterGroup(itemType,5);
    }

    private WebElement getShippingOptionLink(String shipping_option) {
        return returnSpecificFilterForFilterGroup(shipping_option,2);
    }

    private WebElement getSpecialOffersLink(String specialOffer) {
        return returnSpecificFilterForFilterGroup(specialOffer, 3);
    }

    //Private
    private WebElement returnSpecificFilterForFilterGroup(String filter, int childNumber) {
        String cssQuery = String.format("div.mb-xs-3:nth-child(%s) a", childNumber);
        return searchFilterForm.findElements(By.cssSelector(cssQuery))
                .stream()
                .filter(webElement -> webElement.getText().trim().equalsIgnoreCase(filter))
                .findFirst().get();
    }



}
