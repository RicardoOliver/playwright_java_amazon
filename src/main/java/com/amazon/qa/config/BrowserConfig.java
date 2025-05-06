package com.amazon.qa.config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;

import java.nio.file.Paths;
import java.util.Arrays;

public class BrowserConfig {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;
    
    public void initBrowser(String browserType, boolean headless) {
        playwright = Playwright.create();
        
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
            .setHeadless(headless) // Usa o parâmetro headless para controlar a visibilidade
            .setSlowMo(100);       // Atraso de 100ms para facilitar a visualização
            
        switch (browserType.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(launchOptions);
                break;
            case "firefox":
                browser = playwright.firefox().launch(launchOptions);
                break;
            case "webkit":
                browser = playwright.webkit().launch(launchOptions);
                break;
            default:
                browser = playwright.chromium().launch(launchOptions);
        }
        
        context = browser.newContext(new Browser.NewContextOptions()
            .setViewportSize(1280, 720)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
            .setLocale("pt-BR")
            .setTimezoneId("America/Sao_Paulo")
            .setPermissions(Arrays.asList("geolocation"))
        );
        
        // Enable tracing for debugging
        context.tracing().start(new Tracing.StartOptions()
            .setScreenshots(true)
            .setSnapshots(true));
            
        page = context.newPage();
        
        // Set default navigation timeout
        page.setDefaultNavigationTimeout(50000);
        page.setDefaultTimeout(15000); // Aumentado para 15s para evitar TimeoutError
    }
    
    public void closeBrowser() {
        if (context != null) {
            context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("target/trace.zip")));
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
    
    public Page getPage() {
        return page;
    }
    
    public void setViewportSize(int width, int height) {
        page.setViewportSize(width, height);
    }
}