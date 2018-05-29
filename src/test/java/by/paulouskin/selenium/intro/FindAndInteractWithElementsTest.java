package by.paulouskin.selenium.intro;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static by.paulouskin.selenium.intro.EtsyComSelectors.*;
import static by.paulouskin.selenium.intro.FindElementsHelpers.findElementWithFluentWait;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class FindAndInteractWithElementsTest {

    private WebDriver wd;

    @BeforeMethod
    public void setUp() {
        wd = new ChromeDriver();
        wd.get("http://www.etsy.com");
        new WebDriverWait(wd, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(GDPR_ALERT_WINDOW));
        wd.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div/div/div[2]/button")).click();
        wd.manage().window().maximize();
    }

    @Test
    public void FindElementByTagName() {
        WebElement search_field = wd.findElement(SEARCH_FIELD);
        search_field.clear();
        search_field.sendKeys("Leather bag");
        new WebDriverWait(wd,30).until(ExpectedConditions.visibilityOfElementLocated(SEARCH_SUGGESTIONS_LIST));
        WebElement we = findElementWithFluentWait(wd,SEARCH_SUGGESTIONS_FIRST_RESULT);
        we.click();
        /*WebElement first_result_suggested = new WebDriverWait(wd,30).
                until(ExpectedConditions.visibilityOfElementLocated(SEARCH_SUGGESTIONS_FIRST_RESULT));
        first_result_suggested.click();*/
        new WebDriverWait(wd,30).until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(SEARCH_RESULT_LIST),
                ExpectedConditions.visibilityOfElementLocated(SEARCH_FILTERS_FORM)));
        WebElement on_sale = wd.findElement(By.linkText("On sale"));
        on_sale.click();
        WebElement ship_to = new WebDriverWait(wd,30).until(ExpectedConditions.visibilityOfElementLocated(SHIP_TO_SELECTION));
        new Select(ship_to).selectByVisibleText("Nigeria");
        /*WebElement handmade = wd.findElement(By.linkText("Handmade"));
        handmade.click();*/
        List<String> tags= wd.findElement(SEARCH_RESULT_LIST).findElements(FILTER_TAGS)
                .stream().map(webElement -> webElement.getText()).collect(Collectors.toList());
        //Assert.assertEquals(wd.getTitle().contains("Leather bag"),true);
        MatcherAssert.assertThat(tags, containsInAnyOrder("On sale", "Ships to Nigeria", "Handmade"));
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }

    private WebElement getRadioButtonForFilterSection(WebDriver wd, String radioButton) {
        return new WebDriverWait(wd,30).until(ExpectedConditions.visibilityOfElementLocated(SEARCH_FILTERS_FORM))
                .findElements(By.cssSelector("input[type='radio']"))
                .stream()
                .peek(webElement -> System.out.println(webElement.getText()))
                .filter(webElement -> webElement.getText().equalsIgnoreCase(radioButton))
                .findFirst().get();
    }

}
