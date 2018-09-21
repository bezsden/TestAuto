package by.paulouskin.selenium.selenium.ebuy;

import by.paulouskin.selenium.selenium.ebuy.pageobject.EbayComPageObject;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


public class EbayComTest {
    protected static WebDriver webDriver;
    EbayComPageObject ebayPage;

    @BeforeAll
    public void setUpAll(){
    webDriver = new ChromeDriver();
    }
    @BeforeEach
    public void setUp(){
        ebayPage = new EbayComPageObject(webDriver);
    }
    @Test
    public void shouldShowBestPriceWhenSelectAuctionAfterSearch(){
        EbayComPageObject ebayPage = new EbayComPageObject(webDriver);
        ebayPage.goToMainPage()
                .searchFor("leather bags")
                .selectActionItems();
        assertThat(ebayPage.getAuctionBestPrices(),
                containsInAnyOrder("Under $8.00", "$8.00 - $18.00")) ;
     }

     @AfterEach

    @AfterAll

}
