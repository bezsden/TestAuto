package by.paulouskin.selenium.intro;

import by.paulouskin.selenium.intro.pages.EtsyComHomePage;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static by.paulouskin.selenium.intro.EtsyComSelectors.GDPR_ALERT_WINDOW;
import static org.hamcrest.Matchers.*;

public class PageObjectModelTest {

    private WebDriver wd;
    EtsyComHomePage homePage;

    @BeforeMethod
    public void setUp() {
        wd = new ChromeDriver();
        wd.get("http://www.etsy.com");
        homePage = new EtsyComHomePage(wd);
        new WebDriverWait(wd, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(GDPR_ALERT_WINDOW));
        wd.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div/div/div[2]/button")).click();
    }
    @Test
    public void SearchForItemAndApplySearchCriteria() {
        homePage.searchForItem("leather bag");
        homePage.selectSpecialOffersFilter("On sale");
        homePage.selectShippingOptions("Free shipping");
        homePage.selectItemType("Handmade");
        MatcherAssert.assertThat(homePage.getAppliedSearchFilters(), containsInAnyOrder("On sale","Free shipping","Handmade"));
    }

    @AfterMethod
    public void tearDown() {
        wd.close();
    }
}
