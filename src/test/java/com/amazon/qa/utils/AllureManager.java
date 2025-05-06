package com.amazon.qa.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;

import java.io.ByteArrayInputStream;
import java.util.UUID;

public class AllureManager {
    
    public static void takeScreenshot(byte[] screenshot) {
        Allure.addAttachment("Screenshot", "image/png", 
            new ByteArrayInputStream(screenshot), "png");
    }
    
    public static void saveTextLog(String message) {
        Allure.addAttachment("Log", "text/plain", message);
    }
    
    public static void saveHtmlSource(String pageSource) {
        Allure.addAttachment("Page source", "text/html", pageSource, "html");
    }
    
    public static void startStep(String stepName) {
        Allure.getLifecycle().startStep(UUID.randomUUID().toString(), 
            new StepResult().setName(stepName).setStatus(Status.PASSED));
    }
    
    public static void endStep() {
        Allure.getLifecycle().stopStep();
    }
    
    public static void addEnvironmentInfo() {
        Allure.addAttachment("Environment Info", "text/plain", 
            "Browser: " + System.getProperty("browser.type", "chromium") + "\n" +
            "Headless: " + System.getProperty("browser.headless", "true") + "\n" +
            "OS: " + System.getProperty("os.name") + "\n" +
            "Java Version: " + System.getProperty("java.version") + "\n" +
            "Test URL: " + System.getProperty("base.url", "https://www.amazon.com.br"));
    }
}
