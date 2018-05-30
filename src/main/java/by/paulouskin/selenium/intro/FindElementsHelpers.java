package by.paulouskin.selenium.intro;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FindElementsHelpers {

    public static WebElement findElementWithWait(WebDriver wd, By locator) {
        return new WebDriverWait(wd,30)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement findElementWithFluentWait(WebDriver wd, By locator) {
        return new FluentWait<>(wd)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(StaleElementReferenceException.class, NoSuchElementException.class)
                .until(webDriver -> webDriver.findElement(locator));
    }
}
