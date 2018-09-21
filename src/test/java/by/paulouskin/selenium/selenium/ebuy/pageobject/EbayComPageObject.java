package by.paulouskin.selenium.selenium.ebuy.pageobject;

import org.openqa.selenium.WebDriver;

public class EbayComPageObject extends BasePageObject{
    private final String mainPage = "http://www.ebay.com";

    public EbayComPageObject(WebDriver webDriver){
        super(webDriver);
    }
    public EbayComPageObject goToMainPage() {
         webDriver.get(mainPage);
        return this;
    }
}
