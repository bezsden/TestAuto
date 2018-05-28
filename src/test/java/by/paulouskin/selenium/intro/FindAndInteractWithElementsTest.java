package by.paulouskin.selenium.intro;

import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static by.paulouskin.selenium.intro.EtsyComSelectors.*;
import static by.paulouskin.selenium.intro.FindElementsHelpers.findElementWithWait;

public class FindAndInteractWithElementsTest {

    private WebDriver wd;

    @BeforeMethod
    public void setUp() {
        wd = new ChromeDriver();
        wd.get("http://www.etsy.com");
        new WebDriverWait(wd, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(GDPR_ALERT_WINDOW));
        wd.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div/div/div[2]/button")).click();
    }

    @Test
    public void FindElementByTagName() {
        WebElement search_field = wd.findElement(SEARCH_FIELD);
        search_field.clear();
        search_field.sendKeys("Leather bag");
        new WebDriverWait(wd,30).until(ExpectedConditions.visibilityOfElementLocated(SEARCH_SUGGESTIONS_LIST));
        /*WebElement first_result_suggested = new WebDriverWait(wd,30).
                until(ExpectedConditions.visibilityOfElementLocated(SEARCH_SUGGESTIONS_FIRST_RESULT));
        first_result_suggested.click();*/
        WebElement we = findElementWithWait(wd,SEARCH_SUGGESTIONS_FIRST_RESULT);
        we.click();
        new WebDriverWait(wd,30).until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(SEARCH_RESULT_LIST),
                ExpectedConditions.visibilityOfElementLocated(SEARCH_FILTERS_FORM)));
        WebElement on_sale = wd.findElement(By.linkText("On sale"));
        on_sale.click();
        WebElement ship_to = new WebDriverWait(wd,30).until(ExpectedConditions.visibilityOfElementLocated(SHIP_TO_SELECTION));
        new Select(ship_to).selectByVisibleText("Nigeria");
        Assert.assertEquals(wd.getTitle().contains("Leather bag"),true);

    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }

}
