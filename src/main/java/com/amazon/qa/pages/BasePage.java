package com.amazon.qa.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class BasePage {
    protected Page page;
    
    public BasePage(Page page) {
        this.page = page;
    }
    
    protected void navigateTo(String url) {
        try {
            page.navigate(url, new Page.NavigateOptions().setTimeout(60000));
        } catch (Exception e) {
            System.err.println("Erro ao navegar para " + url + ": " + e.getMessage());
            takeScreenshot("navigation_error_" + url.replaceAll("[^a-zA-Z0-9]", "_"));
        }
    }
    
    protected Locator getElement(String selector) {
        return page.locator(selector);
    }
    
    protected List<Locator> getElements(String selector) {
        return page.locator(selector).all();
    }
    
    protected void click(String selector) {
        try {
            getElement(selector).click(new Locator.ClickOptions().setTimeout(30000));
        } catch (Exception e) {
            System.err.println("Erro ao clicar em " + selector + ": " + e.getMessage());
            takeScreenshot("click_error_" + selector.replaceAll("[^a-zA-Z0-9]", "_"));
        }
    }
    
    protected void type(String selector, String text) {
        try {
            getElement(selector).fill(text);
        } catch (Exception e) {
            System.err.println("Erro ao preencher " + selector + ": " + e.getMessage());
            takeScreenshot("type_error_" + selector.replaceAll("[^a-zA-Z0-9]", "_"));
        }
    }
    
    protected void typeSlowly(String selector, String text) {
        try {
            getElement(selector).fill("");
            for (char c : text.toCharArray()) {
                getElement(selector).type(String.valueOf(c));
                page.waitForTimeout(150);
            }
        } catch (Exception e) {
            System.err.println("Erro ao digitar lentamente em " + selector + ": " + e.getMessage());
            takeScreenshot("type_slowly_error_" + selector.replaceAll("[^a-zA-Z0-9]", "_"));
        }
    }
    
    protected String getText(String selector) {
        try {
            return getElement(selector).textContent();
        } catch (Exception e) {
            System.err.println("Erro ao obter texto de " + selector + ": " + e.getMessage());
            return "";
        }
    }
    
    public boolean isVisible(String selector) {
        try {
            // Usar um timeout menor para verificação de visibilidade
            return page.locator(selector).isVisible(new Locator.IsVisibleOptions().setTimeout(5000));
        } catch (Exception e) {
            System.err.println("Erro ao verificar visibilidade de " + selector + ": " + e.getMessage());
            return false;
        }
    }
    
    protected void waitForElement(String selector) {
        try {
            System.out.println("Aguardando elemento: " + selector);
            page.waitForSelector(selector, 
                new Page.WaitForSelectorOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(30000)); // 30 segundos de timeout
            System.out.println("Elemento encontrado: " + selector);
        } catch (Exception e) {
            System.err.println("Timeout ao esperar pelo elemento " + selector + ": " + e.getMessage());
            takeScreenshot("wait_timeout_" + selector.replaceAll("[^a-zA-Z0-9]", "_"));
            
            // Capturar o HTML da página para debug
            String pageContent = page.content();
            System.err.println("HTML da página no momento do timeout:");
            System.err.println(pageContent.substring(0, Math.min(pageContent.length(), 1000)) + "...");
        }
    }
    
    protected void waitForElementToBeHidden(String selector) {
        try {
            page.waitForSelector(selector, 
                new Page.WaitForSelectorOptions()
                    .setState(WaitForSelectorState.HIDDEN)
                    .setTimeout(30000));
        } catch (Exception e) {
            System.err.println("Timeout ao esperar pelo elemento " + selector + " ficar oculto: " + e.getMessage());
        }
    }
    
    // Alterando de protected para public para permitir acesso das classes de step definitions
    public void waitForTimeout(int milliseconds) {
        try {
            page.waitForTimeout(milliseconds);
        } catch (Exception e) {
            System.err.println("Erro ao esperar timeout: " + e.getMessage());
        }
    }
    
    protected void takeScreenshot(String name) {
        try {
            String filename = "target/screenshots/" + name + "_" + System.currentTimeMillis() + ".png";
            System.out.println("Tirando screenshot: " + filename);
            page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(filename))
                .setFullPage(true));
        } catch (Exception e) {
            System.err.println("Erro ao tirar screenshot: " + e.getMessage());
        }
    }
    
    protected void waitForNavigation() {
        try {
            page.waitForLoadState();
        } catch (Exception e) {
            System.err.println("Erro ao esperar navegação: " + e.getMessage());
            takeScreenshot("navigation_wait_error");
        }
    }
    
    protected double getPageLoadTime() {
        try {
            Object timing = page.evaluate("() => {" +
                "const perfData = window.performance.timing;" +
                "const loadTime = perfData.loadEventEnd - perfData.navigationStart;" +
                "return loadTime > 0 ? loadTime : 0;" +
                "}");
            
            // Garantir que o resultado seja convertido para double corretamente
            if (timing instanceof Integer) {
                return ((Integer) timing).doubleValue();
            } else if (timing instanceof Double) {
                return (Double) timing;
            } else if (timing instanceof Long) {
                return ((Long) timing).doubleValue();
            } else {
                System.err.println("Tipo inesperado retornado para tempo de carregamento: " + 
                                  (timing != null ? timing.getClass().getName() : "null"));
                return 0.0;
            }
        } catch (Exception e) {
            System.err.println("Erro ao obter tempo de carregamento: " + e.getMessage());
            return 0.0;
        }
    }
    
    protected boolean isResponsiveElement(String selector, int minWidth, int maxWidth) {
        try {
            // Corrigindo o método evaluate para usar a forma correta de passar parâmetros
            Map<String, Object> params = new HashMap<>();
            params.put("selector", selector);
            params.put("minWidth", minWidth);
            params.put("maxWidth", maxWidth);
            
            return (Boolean) page.evaluate("({selector, minWidth, maxWidth}) => {" +
                "const element = document.querySelector(selector);" +
                "if (!element) return false;" +
                "const style = window.getComputedStyle(element);" +
                "const width = parseFloat(style.width);" +
                "return width >= minWidth && width <= maxWidth;" +
                "}", params);
        } catch (Exception e) {
            System.err.println("Erro ao verificar responsividade: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
