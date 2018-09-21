package by.paulouskin.selenium.selenium.etsy;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

 import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class EstyComEmptyCartTest  extends EtsyBaseTest{
    private final String ETSY ="http://www.etsy.com";
    private final String PRIVAT_POLICY_ACCEPT_BUTTON="//button[@class~='btn-outline']";
    //private final String PRIVAT_POLICY_ACCEPT_BUTTON="//button.btn-outline";
    private final String PRIVACY_POLICY_FORM =".alert";
    private final String SHOPING_CART_ICON = ".etsy-icon-cart";
    private final String CART_ADDRESS ="https://www.etsy.com/cart?ref=hdr";
    private final String PRIVACY_POLICY_SUCCESS_MODAL=".alert-success";
    private final String EMPTY_HEADER="div.empty-header";


    @Test
    public void cartIsEmptyOnFirstVisit() throws InterruptedException {
        // go to Etsy

        webDriver.get(ETSY);
        Thread.sleep(3000);
        // accept privacy rules
        try {
            WebElement acceptPrivacyRules = webDriver.findElement(By.cssSelector(PRIVACY_POLICY_FORM))
                   .findElement(By.xpath(PRIVAT_POLICY_ACCEPT_BUTTON));
            //.findElement(By.cssSelector(PRIVAT_POLICY_ACCEPT_BUTTON));
            acceptPrivacyRules.click();
            Thread.sleep(3000);
        } catch (Throwable t) {}
        // go to cart

//        new WebDriverWait(webDriver,20).until(
//                webDriver1 -> webDriver1.findElement(By.cssSelector(PRIVACY_POLICY_SUCCESS_MODAL))
//        );
        webDriver.get(CART_ADDRESS);
        //webDriver.findElement(By.cssSelector(SHOPING_CART_ICON)).click();
        Thread.sleep(3000);
        // check if empty
        boolean empyCartResult=webDriver.findElement(By.cssSelector(EMPTY_HEADER)).isDisplayed();
        assertThat(empyCartResult        , is (true ));


    }

}
