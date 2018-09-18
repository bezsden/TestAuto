package by.paulouskin.selenium.intro;

import by.paulouskin.selenium.intro.pages.EtsyComHomePage;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.hamcrest.Matchers.containsInAnyOrder;

public class PageObjectModelTest {

    private WebDriver wd;
    EtsyComHomePage homePage;


    @DataProvider
    public Object[][] testData() {
        return new Object[][] {
                new Object[] {"leather bag"},
                new Object[] {"wedding gifts"},
                new Object[] {"paper toys"}
        };
    }

    @BeforeTest
    public void setUp() {
        wd = new ChromeDriver();
        homePage = new EtsyComHomePage(wd);

    }
    @Test(dataProvider = "testData")
    public void SearchForItemAndApplySearchCriteria(String searchCrit) {
        homePage.searchForItem(searchCrit);
        homePage.selectSpecialOffersFilter("On sale");
        homePage.selectShippingOptions("Free shipping");
        homePage.selectItemType("Handmade");
        MatcherAssert.assertThat(homePage.getAppliedSearchFilters(), containsInAnyOrder("On sale","Free shipping","Handmade"));
    }

    @AfterTest
    public void tearDown() {
        wd.close();
    }
}
