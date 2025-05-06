package com.amazon.qa.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConfig {
    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "src/test/resources/config.properties";
    
    static {
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Could not load config file: " + e.getMessage());
        }
    }
    
    public static String getBrowserType() {
        return getProperty("browser.type", "chromium");
    }
    
    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("browser.headless", "true"));
    }
    
    public static int getDefaultTimeout() {
        return Integer.parseInt(getProperty("default.timeout", "10000"));
    }
    
    public static String getBaseUrl() {
        return getProperty("base.url", "https://www.amazon.com.br");
    }
    
    public static double getAcceptableLoadTimeSeconds() {
        return Double.parseDouble(getProperty("acceptable.load.time.seconds", "3.0"));
    }
    
    private static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
