package com.amazon.qa.utils;

import com.microsoft.playwright.Page;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {
    private static final String SCREENSHOT_DIR = "target/screenshots/";
    
    static {
        // Ensure screenshot directory exists
        File directory = new File(SCREENSHOT_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
    
    public static String takeScreenshot(Page page, String name) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = name + "_" + timestamp + ".png";
        String filePath = SCREENSHOT_DIR + filename;
        
        page.screenshot(new Page.ScreenshotOptions()
            .setPath(Paths.get(filePath))
            .setFullPage(true));
            
        return filePath;
    }
    
    public static byte[] getScreenshotAsBytes(Page page) {
        return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
    }
    
    public static String getScreenshotAsBase64(Page page) {
        byte[] screenshotBytes = getScreenshotAsBytes(page);
        return java.util.Base64.getEncoder().encodeToString(screenshotBytes);
    }
    
    public static void saveScreenshot(byte[] screenshot, String name) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = name + "_" + timestamp + ".png";
        String filePath = SCREENSHOT_DIR + filename;
        
        try {
            Files.write(Paths.get(filePath), screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
