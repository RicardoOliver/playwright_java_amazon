package com.amazon.qa.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import java.util.List;
import java.util.ArrayList;

public class HomePage extends BasePage {
    // Selectors - usando múltiplas opções para maior robustez
    private static final String SEARCH_BOX = "#twotabsearchtextbox";
    // Múltiplos seletores possíveis para sugestões de pesquisa
    private static final String[] SEARCH_SUGGESTIONS_SELECTORS = {
        "div.s-suggestion-container", 
        ".s-suggestion", 
        "[data-component-type='s-suggestion']"
    };
    // Múltiplos seletores possíveis para o logo
    private static final String[] LOGO_SELECTORS = {
        "#nav-logo-sprites", 
        "#nav-logo", 
        "[aria-label='Amazon']",
        "a[href='/ref=nav_logo']"
    };
    private static final String HAMBURGER_MENU = "#nav-hamburger-menu";
    private static final String MAIN_MENU = "#hmenu-content";
    private static final String MAIN_MENU_ITEMS = "#hmenu-content ul.hmenu li";
    private static final String DEPARTMENT_LINKS = "#nav-xshop a";
    private static final String SEARCH_SUBMIT = "#nav-search-submit-button";
    
    public HomePage(Page page) {
        super(page);
    }
    
    public void navigateToHomePage() {
        try {
            System.out.println("Navegando para a página inicial da Amazon...");
            
            // Configurar interceptação de requisições para detectar possíveis bloqueios
            page.route("**/*", route -> {
                String url = route.request().url();
                if (url.contains("captcha") || url.contains("robot") || url.contains("challenge")) {
                    System.out.println("Possível desafio de segurança detectado: " + url);
                    takeScreenshot("security_challenge");
                }
                route.resume();
            });
            
            // Navegar para a página com timeout aumentado
            page.navigate("https://www.amazon.com.br", 
                new Page.NavigateOptions().setTimeout(60000));
            
            // Esperar pelo carregamento completo da página
            page.waitForLoadState(LoadState.NETWORKIDLE);
            
            // Verificar se algum dos seletores do logo está presente
            boolean logoFound = false;
            for (String logoSelector : LOGO_SELECTORS) {
                if (isVisible(logoSelector)) {
                    System.out.println("Logo encontrado com seletor: " + logoSelector);
                    logoFound = true;
                    break;
                }
            }
            
            if (!logoFound) {
                System.err.println("Nenhum logo encontrado. Tirando screenshot para debug.");
                takeScreenshot("no_logo_found");
                // Verificar se há algum elemento visível que possa indicar que a página carregou
                if (isVisible("body")) {
                    System.out.println("Página carregou, mas logo não foi encontrado.");
                }
            }
            
            // Verificar se há algum captcha ou desafio de segurança
            if (isVisible("[id*='captcha']") || isVisible("[id*='robot']") || 
                isVisible("[id*='challenge']") || page.url().contains("captcha")) {
                System.err.println("ALERTA: Possível desafio de segurança/captcha detectado!");
                takeScreenshot("captcha_detected");
            }
            
        } catch (Exception e) {
            System.err.println("Erro ao navegar para a página inicial da Amazon: " + e.getMessage());
            e.printStackTrace();
            takeScreenshot("navigation_error");
        }
    }
    
    public void enterSearchText(String text) {
        try {
            System.out.println("Digitando texto de pesquisa: " + text);
            
            // Verificar se a caixa de pesquisa está visível
            if (!isVisible(SEARCH_BOX)) {
                System.err.println("Caixa de pesquisa não encontrada. Tirando screenshot.");
                takeScreenshot("search_box_not_found");
                return;
            }
            
            // Focar na caixa de pesquisa primeiro
            getElement(SEARCH_BOX).click();
            waitForTimeout(500);
            
            // Limpar qualquer texto existente
            getElement(SEARCH_BOX).fill("");
            waitForTimeout(500);
            
            // Digitar o texto lentamente para simular comportamento humano
            for (char c : text.toCharArray()) {
                getElement(SEARCH_BOX).type(String.valueOf(c));
                waitForTimeout(150); // Intervalo entre teclas
            }
            
            // Esperar mais tempo para as sugestões aparecerem
            waitForTimeout(3000);
            
            System.out.println("Texto digitado com sucesso.");
            takeScreenshot("after_typing_" + text);
            
        } catch (Exception e) {
            System.err.println("Erro ao digitar texto de pesquisa: " + e.getMessage());
            e.printStackTrace();
            takeScreenshot("typing_error");
        }
    }
    
    public List<String> getSearchSuggestions() {
        List<String> suggestions = new ArrayList<>();
        
        try {
            System.out.println("Buscando sugestões de pesquisa...");
            
            // Tentar cada um dos seletores possíveis
            for (String selector : SEARCH_SUGGESTIONS_SELECTORS) {
                System.out.println("Tentando seletor: " + selector);
                
                if (isVisible(selector)) {
                    System.out.println("Seletor encontrado: " + selector);
                    List<Locator> suggestionElements = getElements(selector);
                    
                    if (!suggestionElements.isEmpty()) {
                        System.out.println("Encontradas " + suggestionElements.size() + " sugestões");
                        
                        for (Locator element : suggestionElements) {
                            String text = element.textContent().trim();
                            if (!text.isEmpty()) {
                                suggestions.add(text);
                                System.out.println("Sugestão encontrada: " + text);
                            }
                        }
                        
                        if (!suggestions.isEmpty()) {
                            break; // Sair do loop se encontrou sugestões
                        }
                    }
                }
            }
            
            // Se não encontrou sugestões, tentar uma abordagem alternativa
            if (suggestions.isEmpty()) {
                System.out.println("Tentando abordagem alternativa para encontrar sugestões...");
                
                // Tentar encontrar qualquer elemento que apareça após digitar na caixa de pesquisa
                List<Locator> possibleSuggestions = page.locator("div[role='listbox'] div, .autocomplete-results div, [class*='suggestion']:not([id*='suggestions']), [class*='autocomplete']").all();
                
                for (Locator element : possibleSuggestions) {
                    if (element.isVisible()) {
                        String text = element.textContent().trim();
                        if (!text.isEmpty()) {
                            suggestions.add(text);
                            System.out.println("Sugestão alternativa encontrada: " + text);
                        }
                    }
                }
            }
            
            // Tirar screenshot para debug
            takeScreenshot("search_suggestions");
            
        } catch (Exception e) {
            System.err.println("Erro ao obter sugestões de pesquisa: " + e.getMessage());
            e.printStackTrace();
            takeScreenshot("suggestions_error");
        }
        
        return suggestions;
    }
    
    public boolean areSearchSuggestionsDisplayed() {
        try {
            System.out.println("Verificando se as sugestões de pesquisa estão visíveis...");
            
            // Esperar um pouco mais para garantir que as sugestões apareçam
            waitForTimeout(3000);
            
            // Tentar cada um dos seletores possíveis
            for (String selector : SEARCH_SUGGESTIONS_SELECTORS) {
                System.out.println("Verificando seletor: " + selector);
                if (isVisible(selector)) {
                    System.out.println("Sugestões encontradas com seletor: " + selector);
                    return true;
                }
            }
            
            // Abordagem alternativa - verificar se há qualquer elemento novo que possa ser uma sugestão
            boolean alternativeFound = isVisible("div[role='listbox'] div") || 
                          isVisible(".autocomplete-results div") || 
                          isVisible("[class*='suggestion']:not([id*='suggestions'])") ||
                          isVisible("[class*='autocomplete']");
            
            if (alternativeFound) {
                System.out.println("Sugestões encontradas com seletor alternativo");
                return true;
            }
            
            System.out.println("Nenhuma sugestão de pesquisa encontrada");
            takeScreenshot("no_suggestions");
            return false;
            
        } catch (Exception e) {
            System.err.println("Erro ao verificar sugestões de pesquisa: " + e.getMessage());
            e.printStackTrace();
            takeScreenshot("check_suggestions_error");
            return false;
        }
    }
    
    public void clickHamburgerMenu() {
        try {
            System.out.println("Clicando no menu hamburger...");
            
            if (!isVisible(HAMBURGER_MENU)) {
                System.err.println("Menu hamburger não encontrado. Tirando screenshot.");
                takeScreenshot("hamburger_not_found");
                return;
            }
            
            click(HAMBURGER_MENU);
            waitForTimeout(2000); // Dar tempo para o menu abrir
            
            if (!isVisible(MAIN_MENU)) {
                System.err.println("Menu principal não apareceu após clicar no menu hamburger.");
                takeScreenshot("main_menu_not_visible");
            } else {
                System.out.println("Menu principal visível após clicar no menu hamburger.");
            }
            
        } catch (Exception e) {
            System.err.println("Erro ao clicar no menu hamburger: " + e.getMessage());
            e.printStackTrace();
            takeScreenshot("hamburger_click_error");
        }
    }
    
    public boolean isMainMenuDisplayed() {
        return isVisible(MAIN_MENU);
    }
    
    public List<String> getMainMenuItems() {
        List<String> menuItems = new ArrayList<>();
        try {
            List<Locator> menuElements = getElements(MAIN_MENU_ITEMS);
            
            for (Locator element : menuElements) {
                String text = element.textContent().trim();
                if (!text.isEmpty()) {
                    menuItems.add(text);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao obter itens do menu: " + e.getMessage());
            e.printStackTrace();
        }
        
        return menuItems;
    }
    
    public List<String> getDepartmentLinks() {
        List<String> departments = new ArrayList<>();
        try {
            // Evitar o erro de strict mode usando all() e verificando cada elemento
            List<Locator> departmentElements = page.locator(DEPARTMENT_LINKS).all();
            
            for (Locator element : departmentElements) {
                try {
                    if (element.isVisible()) {
                        String text = element.textContent().trim();
                        if (!text.isEmpty()) {
                            departments.add(text);
                        }
                    }
                } catch (Exception e) {
                    // Ignorar elementos individuais que causam erro
                    System.err.println("Erro ao processar um link de departamento: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao obter links de departamentos: " + e.getMessage());
            e.printStackTrace();
            takeScreenshot("department_links_error");
        }
        
        return departments;
    }
    
    public void submitSearch() {
        try {
            System.out.println("Submetendo pesquisa...");
            
            if (!isVisible(SEARCH_SUBMIT)) {
                System.err.println("Botão de pesquisa não encontrado. Tentando alternativas...");
                
                // Tentar pressionar Enter na caixa de pesquisa
                getElement(SEARCH_BOX).press("Enter");
            } else {
                click(SEARCH_SUBMIT);
            }
            
            waitForNavigation();
            System.out.println("Pesquisa submetida com sucesso.");
            
        } catch (Exception e) {
            System.err.println("Erro ao submeter pesquisa: " + e.getMessage());
            e.printStackTrace();
            takeScreenshot("submit_search_error");
        }
    }
    
    public double getPageLoadTimeInSeconds() {
        try {
            return getPageLoadTime() / 1000.0;
        } catch (Exception e) {
            System.err.println("Erro ao obter tempo de carregamento: " + e.getMessage());
            e.printStackTrace();
            return 0.0;
        }
    }
    
    public boolean isMenuResponsive() {
        try {
            System.out.println("Verificando responsividade do menu...");
            
            // Check if hamburger menu is visible on small screens
            page.setViewportSize(375, 667); // Mobile size
            waitForTimeout(2000); // Dar tempo para a página se ajustar
            takeScreenshot("mobile_viewport");
            boolean mobileMenuVisible = isVisible(HAMBURGER_MENU);
            System.out.println("Menu hamburger visível em tela móvel: " + mobileMenuVisible);
            
            // Check if department links are visible on large screens
            page.setViewportSize(1280, 800); // Desktop size
            waitForTimeout(2000); // Dar tempo para a página se ajustar
            takeScreenshot("desktop_viewport");
            
            // Verificar se pelo menos um link de departamento está visível
            List<String> departments = getDepartmentLinks();
            boolean desktopLinksVisible = !departments.isEmpty();
            System.out.println("Links de departamento visíveis em desktop: " + desktopLinksVisible + 
                              " (encontrados " + departments.size() + " links)");
            
            return mobileMenuVisible && desktopLinksVisible;
            
        } catch (Exception e) {
            System.err.println("Erro ao verificar responsividade do menu: " + e.getMessage());
            e.printStackTrace();
            takeScreenshot("responsive_check_error");
            return false;
        }
    }
    
    public boolean isHamburgerMenuVisible() {
        boolean visible = isVisible(HAMBURGER_MENU);
        System.out.println("Menu hamburger visível: " + visible);
        return visible;
    }
    
    public void clearSearchBox() {
        try {
            System.out.println("Limpando caixa de pesquisa...");
            getElement(SEARCH_BOX).fill("");
            System.out.println("Caixa de pesquisa limpa com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao limpar caixa de pesquisa: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Método para verificar se a página está bloqueada ou tem captcha
    public boolean isPageBlocked() {
        return isVisible("[id*='captcha']") || 
               isVisible("[id*='robot']") || 
               isVisible("[id*='challenge']") || 
               page.url().contains("captcha") ||
               page.url().contains("robot") ||
               page.url().contains("challenge");
    }
}
