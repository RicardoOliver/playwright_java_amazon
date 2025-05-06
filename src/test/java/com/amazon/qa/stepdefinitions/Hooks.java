package com.amazon.qa.stepdefinitions;

import com.amazon.qa.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Hooks {
    
    @Before
    public void setUp() {
        DriverManager.initializeDriver();
    }
    
    @After
    public void tearDown() {
        DriverManager.closeDriver();
    }
    
    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take screenshot on failure
            byte[] screenshot = DriverManager.getPage().screenshot();
            scenario.attach(screenshot, "image/png", "Screenshot on failure");
            
            // Add screenshot to Allure report
            Allure.addAttachment("Screenshot on failure", 
                new ByteArrayInputStream(screenshot));
            
            // Capture page source
            String pageSource = DriverManager.getPage().content();
            scenario.attach(pageSource, "text/html", "Page source on failure");
            
            // Add page source to Allure report
            Allure.addAttachment("Page source on failure", "text/html", 
                pageSource);
        }
    }
}
