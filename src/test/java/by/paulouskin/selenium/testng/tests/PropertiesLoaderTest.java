package by.paulouskin.selenium.testng.tests;

import by.paulouskin.selenium.intro.LoadWebDriverProperties;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class PropertiesLoaderTest {

    @Test
    public void loadPropertiesFromFile() {
        Map<String, String> map1 = LoadWebDriverProperties.loadPropertiesFromFile("run.properties");
        Assert.assertEquals(map1.get("target_address"),"http://www.etsy.com");
    }
}
