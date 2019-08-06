package test;

import main.Config;
import org.junit.Test;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
public class ConfigTest {

    @Test (expected = MissingResourceException.class)
    public void testConfig(){
    Config config = new Config();
    config.setBundle(ResourceBundle.getBundle("resourcesabsentes.config"));

    }


}