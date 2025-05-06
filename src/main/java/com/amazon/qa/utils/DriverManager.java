package com.amazon.qa.utils;

import com.amazon.qa.config.BrowserConfig;
import com.amazon.qa.config.TestConfig;
import com.microsoft.playwright.Page;

public class DriverManager {
    private static final ThreadLocal<BrowserConfig> browserConfigThreadLocal = new ThreadLocal<>();
    
    public static void initializeDriver() {
        BrowserConfig browserConfig = new BrowserConfig();
        browserConfig.initBrowser(TestConfig.getBrowserType(), TestConfig.isHeadless());
        browserConfigThreadLocal.set(browserConfig);
    }
    
    public static Page getPage() {
        return browserConfigThreadLocal.get().getPage();
    }
    
    public static void closeDriver() {
        BrowserConfig browserConfig = browserConfigThreadLocal.get();
        if (browserConfig != null) {
            browserConfig.closeBrowser();
            browserConfigThreadLocal.remove();
        }
    }
    
    public static void setViewportSize(int width, int height) {
        browserConfigThreadLocal.get().setViewportSize(width, height);
    }
}
