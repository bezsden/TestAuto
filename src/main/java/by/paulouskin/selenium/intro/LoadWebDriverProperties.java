package by.paulouskin.selenium.intro;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LoadWebDriverProperties {

    public static Map<String,String> loadPropertiesFromFile(String path) {
        Map<String, String> propertiesMap = new HashMap<>();
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(path));
            for(Object key : prop.keySet()) {
                propertiesMap.put((String)key,prop.getProperty((String)key));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertiesMap;
    }
}
